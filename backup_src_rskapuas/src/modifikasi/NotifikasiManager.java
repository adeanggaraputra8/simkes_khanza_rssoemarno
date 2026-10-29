package modifikasi;

import fungsi.koneksiDB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class NotifikasiManager {

    private static final int INTERVAL_MS = 5000;
    private static final int MAX_POPUP = 5;
    private static final int MARGIN_RIGHT = 10;
    private static final int MARGIN_TOP = 10;
    private static final int JARAK_POPUP = 8;

    private static Timer timer;
    private static boolean sedangCek = false;
    private static boolean baselineSudahDibuat = false;
    private static String kodeDokter = "";

    private static final Set<String> sudahNotif = new HashSet<String>();
    private static final List<NotifikasiLab> popupAktif =
            new ArrayList<NotifikasiLab>();

    private NotifikasiManager() {}

    public static synchronized void start() {
        if (timer != null && timer.isRunning()) return;

        SwingUtilities.invokeLater(() -> {
            buatBaseline();
            timer = new Timer(INTERVAL_MS, e -> cekHasilBaru());
            timer.setInitialDelay(INTERVAL_MS);
            timer.start();
        });
    }

    public static synchronized void stop() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        sedangCek = false;
    }

    public static void setkodeDokter(String kode) {
        kodeDokter = kode == null ? "" : kode.trim();
    }

    public static String getkodeDokter() {
        return kodeDokter;
    }

    private static void buatBaseline() {
        Connection c = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            c = koneksiDB.condb();
            p = c.prepareStatement(getSql());
            setKodeDokter(p);
            r = p.executeQuery();
            while (r.next()) sudahNotif.add(key(r));
            baselineSudahDibuat = true;
        } catch (Exception e) {
            System.out.println("NotifikasiManager baseline : " + e);
        } finally {
            tutup(r,p,c);
        }
    }

    private static void cekHasilBaru() {
                if (!baselineSudahDibuat || sedangCek) return;
                sedangCek = true;

                Connection c = null;
                PreparedStatement p = null;
                ResultSet r = null;

                try {
                    c = koneksiDB.condb();

//                    System.out.println(
//                        "NotifikasiManager: connection=" +
//                        (c != null ? "ADA" : "NULL") +
//                        ", closed=" +
//                        (c != null && c.isClosed())
//                    );

                    p = c.prepareStatement(getSql());
                    setKodeDokter(p);
                    r = p.executeQuery();

            List<DataNotifikasi> baru = new ArrayList<DataNotifikasi>();

            while (r.next()) {
                String k = key(r);
                if (!sudahNotif.contains(k)) {
                    baru.add(new DataNotifikasi(
                        r.getString("jenis"),
                        r.getString("noorder"),
                        r.getString("no_rawat"),
                        r.getString("no_rkm_medis"),
                        r.getString("nm_pasien"),
                        r.getString("tgl_hasil"),
                        r.getString("jam_hasil")
                    ));
                    sudahNotif.add(k);
                }
            }

            for (DataNotifikasi d : baru) {
                SwingUtilities.invokeLater(() -> tampilkanPopup(d));
            }

        } catch (Exception e) {
           // System.out.println("NotifikasiManager cek : " + e);
        } finally {
            tutup(r,p,c);
            sedangCek = false;
        }
    }

    private static String getSql() {
        StringBuilder s = new StringBuilder();

        s.append("SELECT x.jenis,x.noorder,x.no_rawat,x.tgl_hasil,x.jam_hasil,x.dokter_perujuk,");
        s.append("rp.no_rkm_medis,p.nm_pasien ");
        s.append("FROM (");

        s.append("SELECT 'LAB' AS jenis,pl.noorder,pl.no_rawat,");
        s.append("pl.tgl_hasil,pl.jam_hasil,pl.dokter_perujuk FROM permintaan_lab pl ");
        s.append("WHERE pl.tgl_hasil <> '0000-00-00' ");
        s.append("AND pl.jam_hasil <> '00:00:00' ");

        s.append("UNION ALL ");

        s.append("SELECT 'LAB MB' AS jenis,pl.noorder,pl.no_rawat,");
        s.append("pl.tgl_hasil,pl.jam_hasil,pl.dokter_perujuk FROM permintaan_labmb pl ");
        s.append("WHERE pl.tgl_hasil <> '0000-00-00' ");
        s.append("AND pl.jam_hasil <> '00:00:00' ");

        s.append("UNION ALL ");

        s.append("SELECT 'LAB PA' AS jenis,pl.noorder,pl.no_rawat,");
        s.append("pl.tgl_hasil,pl.jam_hasil,pl.dokter_perujuk FROM permintaan_labpa pl ");
        s.append("WHERE pl.tgl_hasil <> '0000-00-00' ");
        s.append("AND pl.jam_hasil <> '00:00:00' ");

        s.append(") x ");
        s.append("INNER JOIN reg_periksa rp ON x.no_rawat=rp.no_rawat ");
        s.append("INNER JOIN pasien p ON rp.no_rkm_medis=p.no_rkm_medis ");

         if (kodeDokter.isEmpty()) {
                return "";
            }

        s.append("WHERE x.dokter_perujuk=? ");

        s.append("ORDER BY x.tgl_hasil DESC,x.jam_hasil DESC");

        return s.toString();
    }

    private static void setKodeDokter(PreparedStatement p) throws Exception {
          if (!kodeDokter.isEmpty()) {
            p.setString(1, kodeDokter);
        }
    }

    private static String key(ResultSet r) throws Exception {
        return safe(r.getString("jenis"))+"|"+
               safe(r.getString("noorder"))+"|"+
               safe(r.getString("no_rawat"))+"|"+
               safe(r.getString("tgl_hasil"))+"|"+
               safe(r.getString("jam_hasil"));
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }

    private static void tampilkanPopup(DataNotifikasi d) {
        while (popupAktif.size() >= MAX_POPUP) {
            NotifikasiLab lama = popupAktif.remove(0);
            if (lama != null) lama.dispose();
        }

        NotifikasiLab popup =
            new NotifikasiLab(d.noRM,d.namaPasien,d.jenis);

        popupAktif.add(popup);
        aturPosisiPopup();
        popup.setVisible(true);
        popup.toFront();
    }

    public static void popupDitutup(NotifikasiLab popup) {
        popupAktif.remove(popup);
        aturPosisiPopup();
    }

    private static void aturPosisiPopup() {
        if (popupAktif.isEmpty()) return;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int lebar = popupAktif.get(0).getWidth();
        int x = screen.width - lebar - MARGIN_RIGHT;
        int y = MARGIN_TOP;

        for (NotifikasiLab popup : popupAktif) {
            if (popup != null && popup.isDisplayable()) {
                popup.setLocation(x,y);
                y += popup.getHeight()+JARAK_POPUP;
            }
        }
    }

    private static void tutup(ResultSet r, PreparedStatement p, Connection c) {
            try {
                if (r != null) {
                    r.close();
                }
            } catch (Exception e) {
            }

            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            }

            // JANGAN CLOSE CONNECTION
            // Karena koneksiDB.condb() adalah koneksi global Khanza.
        }

    private static class DataNotifikasi {
        String jenis,noorder,noRawat,noRM,namaPasien,tglHasil,jamHasil;

        DataNotifikasi(String jenis,String noorder,String noRawat,String noRM,
                       String namaPasien,String tglHasil,String jamHasil) {
            this.jenis=jenis;
            this.noorder=noorder;
            this.noRawat=noRawat;
            this.noRM=noRM;
            this.namaPasien=namaPasien;
            this.tglHasil=tglHasil;
            this.jamHasil=jamHasil;
        }
    }
}

/*
 * By Mas Elkhanza
 */
package modifikasi;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perpustakaan
 */
public final class RMStatusAnestesi extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3,
            tabMode4, tabMode5, tabMode6, tabMode7, tabMode8,
            tabMode9, tabMode10, tabMode11, tabMode12;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    public DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
    public DlgCariPegawai penginput = new DlgCariPegawai(null, false);
    private int pilihan = 0;
    private StringBuilder htmlContent;
    private String finger = "";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMStatusAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Diagnosa Pre Op",
            "Jenis Pembedahan", "BB", "Kamar", "Jenis Operasi", "Bagian", "Inform Consent",
            "Tanggal Operasi", "Kode Dokter Bedah", "Nama Dokter Bedah", "Kode Asisten Bedah",
            "Nama Asisten Bedah", "Kode Dokter Anestesi", "Nama Dokter Anestesi", "Kode Asisten Anestesi",
            "Nama Dokter Anestesi", "Kesadaran", "E", "M", "V", "Tensi", "Nadi", "Respirasi", "RR",
            "Selang", "O2", "SpO2", "Tipe Pernapasan", "Regular/Irregular", "Airway", "Support",
            "Status", "NIP Penginput", "Nama Penginput"}) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbAnestesi.setModel(tabMode);
        tbAnestesi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// Mengatur lebar kolom menggunakan array
        int[] columnWidths = {50, 50, 100, 50, 180, 90, 180, 80, 75, 90, 90, 90, 90, 90, 90, 90, 105, 70, 180, 180,
            90, 180, 80, 75, 90, 90, 90, 90, 90, 90, 90, 75, 90, 90, 90, 90, 90, 90, 90, 90};

        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tbAnestesi.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        tbAnestesi.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Jalur", "Tanggal ",
            "Obat", "Dosis", "Ramsaya skor", "Kode Pegawai", "Nama Pegawai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi2.setModel(tabMode2);
        //tampilDr();

        tbAnestesi2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbAnestesi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            }
        }
        tbAnestesi2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Tanggal", "Kesadaran", "E", "M", "V", "Tensi", "Nadi",
            "Respirasi", "RR", "Alat nafas", "O2", "SpO2", "Tipe Pernapasan", "Keterangan Pernapasan", "Support", "Kode Pegawai", "Nama Pegawai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi3.setModel(tabMode3);
        //tampilDr();

        tbAnestesi3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 23; i++) {
            TableColumn column = tbAnestesi3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(105);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(90);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            }
        }
        tbAnestesi3.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Tanggal",
            "Induksi", "Metode", "Pengaturan Nafas", "Ventilator Tidal Volume", "RR", "I:E Ratio",
            "PEEP", "PIP", "Fi02", "Teknik Khusus", "Pemeliharaan", "Perhitungan Cairan", "EBV", "EBL",
            "Jenis Darah", "Jumlah", "Monitoring", "Perubahan Teknik Anestesi", "Alasan", "Pegawai", "Nama Pegawai"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi4.setModel(tabMode4);
        //tampilDr();

        tbAnestesi4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 27; i++) {
            TableColumn column = tbAnestesi4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(105);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(90);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(75);
            } else if (i == 24) {
                column.setPreferredWidth(90);
            } else if (i == 25) {
                column.setPreferredWidth(90);
            } else if (i == 26) {
                column.setPreferredWidth(90);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            }
        }
        tbAnestesi4.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode5 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Tanggal", "Teknik", "Lokasi Tusukan", "Anelgesi Setinggi Segmen",
            "Anestesi Lokal", "Konsentrasi", "Jumlah", "Obat Tambahan", "Dosis", "Adrenalin", "Nor Adrenalin", "Konsentrasi", "Pemeliharaan", "Monitoring",
            "Perubahan Teknik Anestesi", "Alasan", "NIP", "Nama Pegawai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi6.setModel(tabMode5);
        //tampilDr();

        tbAnestesi6.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
            TableColumn column = tbAnestesi6.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(105);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(90);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(80);
            }
        }
        tbAnestesi6.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Tanggal", "Teknik", "Obat Anestesi", "Pemeliharaan", "Monitoring", "Perubahan Teknik Anestesi", "Alasan",
            "NIP", "Nama Pegawai"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi7.setModel(tabMode6);
        //tampilDr();

        tbAnestesi7.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbAnestesi7.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            }
        }
        tbAnestesi7.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode7 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Tanggal", "Posisi",
            "Airway", "Posisi ETT", "Ukuran", "Komplikasi", "Tindakan", "Kode Pegawai", "Nama Pegawai"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi8.setModel(tabMode7);
        //tampilDr();

        tbAnestesi8.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi8.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbAnestesi8.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            }
        }
        tbAnestesi8.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode8 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "O2",
            "N2O", "AIR", "Halothane", "Isoflurane", "Sefoflurane", "DML", "tVS", "Nadi", "Sistolik", "Diastolik", "Kode Pegawai", "Nama Pegawai"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi9.setModel(tabMode8);
        //tampilDr();

        tbAnestesi9.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi9.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbAnestesi9.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(105);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            }
        }
        tbAnestesi9.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode9 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "SPO2", "ETCO2", "FIO2", "Cairan", "Urin", "Perdarahan", "Kode Pegawai", "Nama Pegawai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi10.setModel(tabMode9);
        //tampilDr();

        tbAnestesi10.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi10.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbAnestesi10.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            }
        }
        tbAnestesi10.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode10 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Kristaloid", "Koloid", "Darah", "Komponen Darah", "Perdarahan", "Diuresis",
            "Cairan", "Urine", "NIP", "Nama Pegawai"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi11.setModel(tabMode10);
        //tampilDr();

        tbAnestesi11.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi11.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbAnestesi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);

            }
        }
        tbAnestesi11.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode11 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "Keadaaan Bayi", "JK", "APGAR Score 1 Menit",
            "APGAR Score 5 menit", "Berat Badan", "Panjang Badan", "NIP", "Nama Pegawai"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi12.setModel(tabMode11);
        //tampilDr();

        tbAnestesi12.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi12.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbAnestesi12.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            }
        }
        tbAnestesi12.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode12 = new DefaultTableModel(null, new Object[]{
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Diagnosa Post Op", "Pindah Ke", "Keadaan Umum", "Nadi", "RR", "Suhu", "Sp02",
            "Instruksi Pasca Operasi/ Anestesi", "Ventilator Mode", "Tidal Volume", "MLRR", "I:E Ratio", "PEEP", "PIIP", "PS", "Fi02", "Trigger", "NIP", "Nama Pegawai"

        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAnestesi13.setModel(tabMode12);
        //tampilDr();

        tbAnestesi13.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnestesi13.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
            TableColumn column = tbAnestesi13.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(105);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(90);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(80);
            } else if (i == 23) {
                column.setPreferredWidth(75);
            }
        }
        tbAnestesi13.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        DiagnosaPreOp.setDocument(new batasInput((byte) 100).getKata(DiagnosaPreOp));
        JenisPembedahan.setDocument(new batasInput((byte) 100).getKata(JenisPembedahan));
        Bb.setDocument(new batasInput((byte) 5).getOnlyAngka(Bb));
        E.setDocument(new batasInput((byte) 1).getOnlyAngka(E));
        M.setDocument(new batasInput((byte) 1).getOnlyAngka(M));
        V.setDocument(new batasInput((byte) 1).getOnlyAngka(V));
        Nadi.setDocument(new batasInput((byte) 5).getOnlyAngka(Nadi));
        Td.setDocument(new batasInput((byte) 7).getKata(Td));
        Rr.setDocument(new batasInput((byte) 5).getKata(Rr));
        O2.setDocument(new batasInput((byte) 100).getKata(O2));
        TipePernapasan.setDocument(new batasInput((byte) 100).getKata(TipePernapasan));
        jam();

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            /*    TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            }); */
        }

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdDokterBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        NmDokterBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        KdDokterBedah.requestFocus();
                    } else if (pilihan == 2) {
                        KdDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        NmDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        KdDokterAnestesi.requestFocus();
                    }

                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penginput.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penginput.getTable().getSelectedRow() != -1) {
                    NIPPenginput.setText(penginput.getTable().getValueAt(penginput.getTable().getSelectedRow(), 0).toString());
                    NamaPenginput.setText(penginput.getTable().getValueAt(penginput.getTable().getSelectedRow(), 1).toString());
                    NIPPenginput.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdAsistenBedah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmAsistenBedah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        KdAsistenBedah.requestFocus();
                    } else if (pilihan == 2) {
                        KdAsistenAnestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmAsistenAnestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        KdAsistenAnestesi.requestFocus();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //   if(akses.getform().equals("RMStatusAnestesi")){
                if (pegawai.getTable().getSelectedRow() != -1) {
                    if (TabRawat.getSelectedIndex() == 1) {
                        Pegawaipremedikasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPegawaiPremedikasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 2) {
                        NIP2.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai2.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 3) {
                        NIP3.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai3.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        NIP4.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai4.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        NIP5.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai5.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                        //  }
                    } else if (TabRawat.getSelectedIndex() == 4) {
                        NIP6.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai6.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 5) {
                        NIP7.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai7.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 6) {
                        NIP8.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai8.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 7) {
                        NIP9.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai9.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 8) {
                        NIP10.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai10.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    } else if (TabRawat.getSelectedIndex() == 9) {
                        NIP11.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NamaPegawai11.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakStatusAnestesi = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnCetakMonitoring = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnCetakMonitoring2 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel11 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel14 = new widget.Label();
        Alamat = new widget.TextBox();
        jLabel91 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        label14 = new widget.Label();
        KdDokterBedah = new widget.TextBox();
        NmDokterBedah = new widget.TextBox();
        BtnDokterBedah = new widget.Button();
        label12 = new widget.Label();
        TglOperasi = new widget.Tanggal();
        jLabel12 = new widget.Label();
        DiagnosaPreOp = new widget.TextBox();
        jLabel13 = new widget.Label();
        JenisPembedahan = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new widget.Label();
        E = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel16 = new widget.Label();
        Bb = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel22 = new widget.Label();
        M = new widget.TextBox();
        jLabel18 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel25 = new widget.Label();
        Td = new widget.TextBox();
        jLabel29 = new widget.Label();
        V = new widget.TextBox();
        jLabel27 = new widget.Label();
        Rr = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel30 = new widget.Label();
        O2 = new widget.TextBox();
        jLabel31 = new widget.Label();
        TipePernapasan = new widget.TextBox();
        jLabel49 = new widget.Label();
        Kamar = new widget.TextBox();
        jLabel50 = new widget.Label();
        JenisOperasi = new widget.ComboBox();
        jLabel51 = new widget.Label();
        Bagian = new widget.TextBox();
        InformConsent = new widget.ComboBox();
        jLabel52 = new widget.Label();
        label15 = new widget.Label();
        KdAsistenBedah = new widget.TextBox();
        NmAsistenBedah = new widget.TextBox();
        BtnAsistenBedah = new widget.Button();
        label16 = new widget.Label();
        KdDokterAnestesi = new widget.TextBox();
        NmDokterAnestesi = new widget.TextBox();
        BtnDokterAnestesi = new widget.Button();
        label17 = new widget.Label();
        KdAsistenAnestesi = new widget.TextBox();
        NmAsistenAnestesi = new widget.TextBox();
        BtnAsistenAnestesi = new widget.Button();
        jLabel53 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        jLabel54 = new widget.Label();
        Regular = new widget.ComboBox();
        Respirasi = new widget.ComboBox();
        jLabel23 = new widget.Label();
        jLabel35 = new widget.Label();
        Spo2 = new widget.TextBox();
        Kanul = new widget.ComboBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        Support = new widget.TextBox();
        jLabel57 = new widget.Label();
        StatusFisik = new widget.ComboBox();
        jLabel32 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel110 = new widget.Label();
        Airway = new widget.ComboBox();
        label30 = new widget.Label();
        NIPPenginput = new widget.TextBox();
        NamaPenginput = new widget.TextBox();
        BtnAsistenBedah2 = new widget.Button();
        Scroll10 = new widget.ScrollPane();
        tbAnestesi = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        panelGlass13 = new widget.panelisi();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel111 = new widget.Label();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        Rute = new widget.ComboBox();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        label24 = new widget.Label();
        TglPremedikasi = new widget.Tanggal();
        jLabel77 = new widget.Label();
        Obat = new widget.TextBox();
        jLabel78 = new widget.Label();
        Dosis = new widget.TextBox();
        jLabel79 = new widget.Label();
        Ramsaya = new widget.ComboBox();
        label19 = new widget.Label();
        Pegawaipremedikasi = new widget.TextBox();
        NmPegawaiPremedikasi = new widget.TextBox();
        BtnAsistenBedah1 = new widget.Button();
        Scroll11 = new widget.ScrollPane();
        tbAnestesi2 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        PanelInput2 = new javax.swing.JPanel();
        ChkInput2 = new widget.CekBox();
        panelGlass14 = new widget.panelisi();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel58 = new widget.Label();
        Kesadaran2 = new widget.ComboBox();
        jLabel19 = new widget.Label();
        jLabel33 = new widget.Label();
        E2 = new widget.TextBox();
        jLabel34 = new widget.Label();
        M2 = new widget.TextBox();
        jLabel36 = new widget.Label();
        V2 = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tensi = new widget.TextBox();
        jLabel38 = new widget.Label();
        jLabel21 = new widget.Label();
        Nadi1 = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel59 = new widget.Label();
        Respirasi2 = new widget.ComboBox();
        jLabel40 = new widget.Label();
        Rr2 = new widget.TextBox();
        jLabel41 = new widget.Label();
        Selang2 = new widget.ComboBox();
        jLabel42 = new widget.Label();
        o22 = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        Spo22 = new widget.TextBox();
        jLabel45 = new widget.Label();
        TipePernapasan2 = new widget.TextBox();
        jLabel60 = new widget.Label();
        Support2 = new widget.TextBox();
        Pernapasan = new widget.ComboBox();
        label18 = new widget.Label();
        NIP2 = new widget.TextBox();
        NamaPegawai2 = new widget.TextBox();
        BtnPegawai = new widget.Button();
        label31 = new widget.Label();
        TglPrainduksi = new widget.Tanggal();
        Scroll12 = new widget.ScrollPane();
        tbAnestesi3 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        TabTeknikAnestesi = new javax.swing.JTabbedPane();
        internalFrame14 = new widget.InternalFrame();
        PanelInput3 = new javax.swing.JPanel();
        ChkInput3 = new widget.CekBox();
        panelGlass15 = new widget.panelisi();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jSeparator27 = new javax.swing.JSeparator();
        jLabel112 = new widget.Label();
        jLabel46 = new widget.Label();
        Induksi = new widget.TextBox();
        RuteInduksi = new widget.ComboBox();
        jLabel47 = new widget.Label();
        PengaturanNafas = new widget.ComboBox();
        jLabel48 = new widget.Label();
        Ventilator = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        Rr3 = new widget.TextBox();
        jLabel63 = new widget.Label();
        ERatio = new widget.TextBox();
        jLabel64 = new widget.Label();
        Peep = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        Pip = new widget.TextBox();
        jLabel67 = new widget.Label();
        Fi02 = new widget.TextBox();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        TeknikKhusus = new widget.ComboBox();
        jLabel70 = new widget.Label();
        Pemeliharaan = new widget.TextBox();
        jLabel71 = new widget.Label();
        PerhitunganCairan = new widget.TextBox();
        jLabel72 = new widget.Label();
        Ebv = new widget.TextBox();
        jLabel73 = new widget.Label();
        Ebl = new widget.TextBox();
        jLabel74 = new widget.Label();
        JenisDarah = new widget.TextBox();
        jLabel75 = new widget.Label();
        JumlahDarah = new widget.TextBox();
        Monitoring = new widget.TextBox();
        jLabel76 = new widget.Label();
        PerubahanTeknikAnestesi = new widget.TextBox();
        jLabel80 = new widget.Label();
        Alasan = new widget.TextBox();
        jLabel81 = new widget.Label();
        label20 = new widget.Label();
        NIP3 = new widget.TextBox();
        NamaPegawai3 = new widget.TextBox();
        BtnPegawai1 = new widget.Button();
        label32 = new widget.Label();
        TglTeknikAnestesiUmum = new widget.Tanggal();
        Scroll16 = new widget.ScrollPane();
        tbAnestesi4 = new widget.Table();
        internalFrame15 = new widget.InternalFrame();
        PanelInput4 = new javax.swing.JPanel();
        ChkInput4 = new widget.CekBox();
        panelGlass16 = new widget.panelisi();
        jSeparator58 = new javax.swing.JSeparator();
        jSeparator60 = new javax.swing.JSeparator();
        jSeparator59 = new javax.swing.JSeparator();
        jSeparator61 = new javax.swing.JSeparator();
        jSeparator62 = new javax.swing.JSeparator();
        jSeparator63 = new javax.swing.JSeparator();
        jLabel82 = new widget.Label();
        LokasiTusukan = new widget.TextBox();
        jLabel83 = new widget.Label();
        Teknik = new widget.ComboBox();
        jLabel85 = new widget.Label();
        Analgesi = new widget.TextBox();
        jLabel101 = new widget.Label();
        Anestesi = new widget.TextBox();
        jLabel102 = new widget.Label();
        Konsentrasi = new widget.TextBox();
        jLabel103 = new widget.Label();
        Jumlah = new widget.TextBox();
        jLabel104 = new widget.Label();
        ObatTambahan = new widget.TextBox();
        jLabel105 = new widget.Label();
        Dosis2 = new widget.TextBox();
        jLabel106 = new widget.Label();
        Vasokonstriktor = new widget.TextBox();
        jLabel107 = new widget.Label();
        Noradernalin = new widget.TextBox();
        jLabel108 = new widget.Label();
        Konsentrasi2 = new widget.TextBox();
        jLabel109 = new widget.Label();
        Pemeliharaan2 = new widget.TextBox();
        jLabel113 = new widget.Label();
        jLabel119 = new widget.Label();
        Monitoring2 = new widget.TextBox();
        PerubahanTeknikAnestesi2 = new widget.TextBox();
        Alasan2 = new widget.TextBox();
        jLabel120 = new widget.Label();
        jLabel121 = new widget.Label();
        label21 = new widget.Label();
        NIP4 = new widget.TextBox();
        NamaPegawai4 = new widget.TextBox();
        BtnPegawai2 = new widget.Button();
        label33 = new widget.Label();
        TglBlokadeRegional = new widget.Tanggal();
        Scroll17 = new widget.ScrollPane();
        tbAnestesi6 = new widget.Table();
        internalFrame16 = new widget.InternalFrame();
        PanelInput5 = new javax.swing.JPanel();
        ChkInput5 = new widget.CekBox();
        panelGlass17 = new widget.panelisi();
        jSeparator64 = new javax.swing.JSeparator();
        jSeparator65 = new javax.swing.JSeparator();
        jSeparator66 = new javax.swing.JSeparator();
        jSeparator67 = new javax.swing.JSeparator();
        jSeparator68 = new javax.swing.JSeparator();
        jSeparator69 = new javax.swing.JSeparator();
        jLabel122 = new widget.Label();
        Monitoring4 = new widget.TextBox();
        jLabel123 = new widget.Label();
        PerubahanTeknikAnestesi3 = new widget.TextBox();
        Alasan3 = new widget.TextBox();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        ObatAnestesi = new widget.TextBox();
        jLabel127 = new widget.Label();
        Pemeliharaan3 = new widget.TextBox();
        label22 = new widget.Label();
        NIP5 = new widget.TextBox();
        NamaPegawai5 = new widget.TextBox();
        BtnPegawai3 = new widget.Button();
        TeknikCombine = new widget.ComboBox();
        Scroll18 = new widget.ScrollPane();
        tbAnestesi7 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll19 = new widget.ScrollPane();
        tbAnestesi8 = new widget.Table();
        PanelInput6 = new javax.swing.JPanel();
        ChkInput6 = new widget.CekBox();
        panelGlass18 = new widget.panelisi();
        jLabel128 = new widget.Label();
        Posisi = new widget.ComboBox();
        jLabel129 = new widget.Label();
        Airway2 = new widget.ComboBox();
        jLabel130 = new widget.Label();
        Ett = new widget.ComboBox();
        jLabel131 = new widget.Label();
        Ukuran = new widget.ComboBox();
        jLabel132 = new widget.Label();
        Komplikasi = new widget.TextBox();
        jLabel133 = new widget.Label();
        Tindakan = new widget.TextBox();
        label23 = new widget.Label();
        NIP6 = new widget.TextBox();
        NamaPegawai6 = new widget.TextBox();
        BtnPegawai4 = new widget.Button();
        internalFrame7 = new widget.InternalFrame();
        Scroll20 = new widget.ScrollPane();
        tbAnestesi9 = new widget.Table();
        PanelInput7 = new javax.swing.JPanel();
        ChkInput7 = new widget.CekBox();
        panelGlass19 = new widget.panelisi();
        jLabel89 = new widget.Label();
        O22 = new widget.TextBox();
        jLabel99 = new widget.Label();
        N2o2 = new widget.TextBox();
        jLabel100 = new widget.Label();
        Air = new widget.TextBox();
        jLabel162 = new widget.Label();
        Halothane = new widget.TextBox();
        jLabel163 = new widget.Label();
        Isoflurane = new widget.TextBox();
        jLabel164 = new widget.Label();
        Sevo = new widget.TextBox();
        jLabel165 = new widget.Label();
        Dml = new widget.TextBox();
        jLabel166 = new widget.Label();
        Tvs = new widget.TextBox();
        jLabel167 = new widget.Label();
        Nadi_1 = new widget.TextBox();
        jLabel168 = new widget.Label();
        Sistolik = new widget.TextBox();
        label25 = new widget.Label();
        NIP7 = new widget.TextBox();
        NamaPegawai7 = new widget.TextBox();
        BtnPegawai5 = new widget.Button();
        Diastolik = new widget.TextBox();
        internalFrame11 = new widget.InternalFrame();
        Scroll21 = new widget.ScrollPane();
        tbAnestesi10 = new widget.Table();
        PanelInput8 = new javax.swing.JPanel();
        ChkInput8 = new widget.CekBox();
        panelGlass20 = new widget.panelisi();
        jLabel90 = new widget.Label();
        Monitoring2_spo2 = new widget.TextBox();
        jLabel115 = new widget.Label();
        Monitoring2_etco2 = new widget.TextBox();
        jLabel169 = new widget.Label();
        Monitoring2_fio2 = new widget.TextBox();
        jLabel170 = new widget.Label();
        Cairan = new widget.TextBox();
        jLabel171 = new widget.Label();
        Urine = new widget.TextBox();
        jLabel172 = new widget.Label();
        Perdarahan = new widget.TextBox();
        label26 = new widget.Label();
        NIP8 = new widget.TextBox();
        NamaPegawai8 = new widget.TextBox();
        BtnPegawai6 = new widget.Button();
        internalFrame8 = new widget.InternalFrame();
        Scroll22 = new widget.ScrollPane();
        tbAnestesi11 = new widget.Table();
        PanelInput9 = new javax.swing.JPanel();
        ChkInput9 = new widget.CekBox();
        panelGlass21 = new widget.panelisi();
        jLabel116 = new widget.Label();
        jLabel92 = new widget.Label();
        Kristaloid = new widget.TextBox();
        jLabel114 = new widget.Label();
        jLabel134 = new widget.Label();
        Koloid = new widget.TextBox();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        Darah = new widget.TextBox();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        KomponenDarah = new widget.TextBox();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        Perdarahan2 = new widget.TextBox();
        jLabel142 = new widget.Label();
        jLabel143 = new widget.Label();
        Diuresis = new widget.TextBox();
        jLabel144 = new widget.Label();
        jLabel145 = new widget.Label();
        CairanLain = new widget.TextBox();
        jLabel146 = new widget.Label();
        label27 = new widget.Label();
        NIP9 = new widget.TextBox();
        NamaPegawai9 = new widget.TextBox();
        BtnPegawai7 = new widget.Button();
        jLabel174 = new widget.Label();
        Urin = new widget.TextBox();
        jLabel175 = new widget.Label();
        internalFrame9 = new widget.InternalFrame();
        PanelInput10 = new javax.swing.JPanel();
        ChkInput10 = new widget.CekBox();
        panelGlass22 = new widget.panelisi();
        Jk2 = new widget.ComboBox();
        jLabel95 = new widget.Label();
        Apgar1 = new widget.TextBox();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        KeadaanBayi = new widget.ComboBox();
        Apgar5 = new widget.TextBox();
        jLabel117 = new widget.Label();
        jLabel147 = new widget.Label();
        Bb2 = new widget.TextBox();
        jLabel148 = new widget.Label();
        Pb2 = new widget.TextBox();
        label28 = new widget.Label();
        NIP10 = new widget.TextBox();
        NamaPegawai10 = new widget.TextBox();
        BtnPegawai8 = new widget.Button();
        Scroll23 = new widget.ScrollPane();
        tbAnestesi12 = new widget.Table();
        internalFrame10 = new widget.InternalFrame();
        PanelInput11 = new javax.swing.JPanel();
        ChkInput11 = new widget.CekBox();
        panelGlass23 = new widget.panelisi();
        jLabel118 = new widget.Label();
        Pindah = new widget.ComboBox();
        jLabel98 = new widget.Label();
        KeadaaanUmum = new widget.TextBox();
        jLabel86 = new widget.Label();
        Nadi2 = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        RR2 = new widget.TextBox();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel149 = new widget.Label();
        jLabel150 = new widget.Label();
        Pasca_Spo2 = new widget.TextBox();
        jLabel151 = new widget.Label();
        Trigger = new widget.TextBox();
        jLabel152 = new widget.Label();
        Vetilator = new widget.TextBox();
        jLabel153 = new widget.Label();
        TidalVolume = new widget.TextBox();
        jLabel154 = new widget.Label();
        Mlrr = new widget.TextBox();
        jLabel155 = new widget.Label();
        jLabel156 = new widget.Label();
        Ieratio = new widget.TextBox();
        jLabel157 = new widget.Label();
        Peep2 = new widget.TextBox();
        jLabel158 = new widget.Label();
        Pip2 = new widget.TextBox();
        jLabel159 = new widget.Label();
        Ps = new widget.TextBox();
        jLabel160 = new widget.Label();
        Fio2 = new widget.TextBox();
        jLabel161 = new widget.Label();
        Instruksi = new widget.TextBox();
        jLabel173 = new widget.Label();
        DiagnosaPostOp = new widget.TextBox();
        label29 = new widget.Label();
        NIP11 = new widget.TextBox();
        NamaPegawai11 = new widget.TextBox();
        BtnPegawai9 = new widget.Button();
        Scroll24 = new widget.ScrollPane();
        tbAnestesi13 = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        jLabel26 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel84 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnTambahTindakan = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnPrint = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakStatusAnestesi.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakStatusAnestesi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakStatusAnestesi.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakStatusAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakStatusAnestesi.setText("Cetak Status Anestesi");
        MnCetakStatusAnestesi.setName("MnCetakStatusAnestesi"); // NOI18N
        MnCetakStatusAnestesi.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakStatusAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakStatusAnestesiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakStatusAnestesi);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnCetakMonitoring.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakMonitoring.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakMonitoring.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakMonitoring.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakMonitoring.setText("Cetak Monitoring Anestesi");
        MnCetakMonitoring.setName("MnCetakMonitoring"); // NOI18N
        MnCetakMonitoring.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakMonitoring.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakMonitoringActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCetakMonitoring);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnCetakMonitoring2.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakMonitoring2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakMonitoring2.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakMonitoring2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakMonitoring2.setText("Cetak Monitoring Anestesi");
        MnCetakMonitoring2.setName("MnCetakMonitoring2"); // NOI18N
        MnCetakMonitoring2.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakMonitoring2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakMonitoring2ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCetakMonitoring2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Status Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 43));

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(200, 24));
        FormInput.add(TNoRM);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);

        jLabel14.setText("Alamat:");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);

        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        Alamat.setPreferredSize(new java.awt.Dimension(300, 24));
        Alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlamatActionPerformed(evt);
            }
        });
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);

        jLabel91.setText("Tanggal :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 320));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass12.setLayout(null);

        label14.setText("Dokter Bedah:");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label14);
        label14.setBounds(200, 70, 80, 23);

        KdDokterBedah.setEditable(false);
        KdDokterBedah.setName("KdDokterBedah"); // NOI18N
        KdDokterBedah.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(KdDokterBedah);
        KdDokterBedah.setBounds(290, 70, 90, 23);

        NmDokterBedah.setEditable(false);
        NmDokterBedah.setName("NmDokterBedah"); // NOI18N
        NmDokterBedah.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass12.add(NmDokterBedah);
        NmDokterBedah.setBounds(390, 70, 180, 23);

        BtnDokterBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterBedah.setMnemonic('2');
        BtnDokterBedah.setToolTipText("Alt+2");
        BtnDokterBedah.setName("BtnDokterBedah"); // NOI18N
        BtnDokterBedah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterBedahActionPerformed(evt);
            }
        });
        BtnDokterBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterBedahKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnDokterBedah);
        BtnDokterBedah.setBounds(570, 70, 28, 23);

        label12.setText("Tgl.Operasi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label12);
        label12.setBounds(0, 70, 70, 23);

        TglOperasi.setForeground(new java.awt.Color(50, 70, 50));
        TglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025 07:33:11" }));
        TglOperasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglOperasi.setName("TglOperasi"); // NOI18N
        TglOperasi.setOpaque(false);
        TglOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglOperasiKeyPressed(evt);
            }
        });
        panelGlass12.add(TglOperasi);
        TglOperasi.setBounds(70, 70, 130, 23);

        jLabel12.setText("Diagnosa Pre Op :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass12.add(jLabel12);
        jLabel12.setBounds(0, 10, 100, 23);

        DiagnosaPreOp.setHighlighter(null);
        DiagnosaPreOp.setName("DiagnosaPreOp"); // NOI18N
        DiagnosaPreOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaPreOpKeyPressed(evt);
            }
        });
        panelGlass12.add(DiagnosaPreOp);
        DiagnosaPreOp.setBounds(100, 10, 139, 23);

        jLabel13.setText("Jenis Pembedahan:");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass12.add(jLabel13);
        jLabel13.setBounds(240, 10, 100, 23);

        JenisPembedahan.setHighlighter(null);
        JenisPembedahan.setName("JenisPembedahan"); // NOI18N
        JenisPembedahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPembedahanKeyPressed(evt);
            }
        });
        panelGlass12.add(JenisPembedahan);
        JenisPembedahan.setBounds(350, 10, 210, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        panelGlass12.add(jSeparator2);
        jSeparator2.setBounds(0, 140, 1290, 3);

        jLabel15.setText("GCS:");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass12.add(jLabel15);
        jLabel15.setBounds(250, 170, 30, 23);

        E.setFocusTraversalPolicyProvider(true);
        E.setName("E"); // NOI18N
        E.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKeyPressed(evt);
            }
        });
        panelGlass12.add(E);
        E.setBounds(310, 170, 40, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("E:");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass12.add(jLabel24);
        jLabel24.setBounds(290, 170, 20, 23);

        jLabel16.setText("BB :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(560, 10, 30, 23);

        Bb.setFocusTraversalPolicyProvider(true);
        Bb.setName("Bb"); // NOI18N
        Bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BbKeyPressed(evt);
            }
        });
        panelGlass12.add(Bb);
        Bb.setBounds(590, 10, 55, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Kg");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass12.add(jLabel17);
        jLabel17.setBounds(650, 10, 20, 23);

        jLabel22.setText("M:");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(350, 170, 30, 23);

        M.setFocusTraversalPolicyProvider(true);
        M.setName("M"); // NOI18N
        M.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MKeyPressed(evt);
            }
        });
        panelGlass12.add(M);
        M.setBounds(390, 170, 40, 23);

        jLabel18.setText("Nadi :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass12.add(jLabel18);
        jLabel18.setBounds(680, 170, 40, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NadiActionPerformed(evt);
            }
        });
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        panelGlass12.add(Nadi);
        Nadi.setBounds(730, 170, 55, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("liter/menit");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass12.add(jLabel20);
        jLabel20.setBounds(570, 200, 60, 23);

        jLabel25.setText("mmHg");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass12.add(jLabel25);
        jLabel25.setBounds(630, 170, 40, 23);

        Td.setFocusTraversalPolicyProvider(true);
        Td.setName("Td"); // NOI18N
        Td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdKeyPressed(evt);
            }
        });
        panelGlass12.add(Td);
        Td.setBounds(550, 170, 80, 23);

        jLabel29.setText("V :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass12.add(jLabel29);
        jLabel29.setBounds(430, 170, 30, 23);

        V.setFocusTraversalPolicyProvider(true);
        V.setName("V"); // NOI18N
        V.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VKeyPressed(evt);
            }
        });
        panelGlass12.add(V);
        V.setBounds(460, 170, 40, 23);

        jLabel27.setText("RR:");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass12.add(jLabel27);
        jLabel27.setBounds(190, 200, 30, 23);

        Rr.setFocusTraversalPolicyProvider(true);
        Rr.setName("Rr"); // NOI18N
        Rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RrKeyPressed(evt);
            }
        });
        panelGlass12.add(Rr);
        Rr.setBounds(230, 200, 55, 23);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("x/menit");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass12.add(jLabel28);
        jLabel28.setBounds(290, 200, 50, 23);

        jLabel30.setText("O2:");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass12.add(jLabel30);
        jLabel30.setBounds(470, 200, 20, 23);

        O2.setFocusTraversalPolicyProvider(true);
        O2.setName("O2"); // NOI18N
        O2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                O2KeyPressed(evt);
            }
        });
        panelGlass12.add(O2);
        O2.setBounds(490, 200, 70, 23);

        jLabel31.setText("Tipe Pernapasan:");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass12.add(jLabel31);
        jLabel31.setBounds(0, 230, 90, 23);

        TipePernapasan.setFocusTraversalPolicyProvider(true);
        TipePernapasan.setName("TipePernapasan"); // NOI18N
        TipePernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipePernapasanKeyPressed(evt);
            }
        });
        panelGlass12.add(TipePernapasan);
        TipePernapasan.setBounds(100, 230, 260, 23);

        jLabel49.setText("Jenis Operasi:");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass12.add(jLabel49);
        jLabel49.setBounds(240, 40, 80, 23);

        Kamar.setHighlighter(null);
        Kamar.setName("Kamar"); // NOI18N
        Kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KamarActionPerformed(evt);
            }
        });
        Kamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KamarKeyPressed(evt);
            }
        });
        panelGlass12.add(Kamar);
        Kamar.setBounds(60, 40, 180, 23);

        jLabel50.setText("Kamar:");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass12.add(jLabel50);
        jLabel50.setBounds(0, 40, 50, 23);

        JenisOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cito", "Elektif" }));
        JenisOperasi.setName("JenisOperasi"); // NOI18N
        panelGlass12.add(JenisOperasi);
        JenisOperasi.setBounds(330, 40, 140, 20);

        jLabel51.setText("Bagian:");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass12.add(jLabel51);
        jLabel51.setBounds(470, 40, 50, 23);

        Bagian.setHighlighter(null);
        Bagian.setName("Bagian"); // NOI18N
        Bagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BagianActionPerformed(evt);
            }
        });
        Bagian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BagianKeyPressed(evt);
            }
        });
        panelGlass12.add(Bagian);
        Bagian.setBounds(530, 40, 180, 23);

        InformConsent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        InformConsent.setName("InformConsent"); // NOI18N
        panelGlass12.add(InformConsent);
        InformConsent.setBounds(800, 40, 72, 20);

        jLabel52.setText("Inform Consent:");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass12.add(jLabel52);
        jLabel52.setBounds(710, 40, 80, 23);

        label15.setText("Asisten Bedah:");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label15);
        label15.setBounds(600, 70, 80, 23);

        KdAsistenBedah.setEditable(false);
        KdAsistenBedah.setName("KdAsistenBedah"); // NOI18N
        KdAsistenBedah.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(KdAsistenBedah);
        KdAsistenBedah.setBounds(690, 70, 90, 23);

        NmAsistenBedah.setEditable(false);
        NmAsistenBedah.setName("NmAsistenBedah"); // NOI18N
        NmAsistenBedah.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass12.add(NmAsistenBedah);
        NmAsistenBedah.setBounds(790, 70, 180, 23);

        BtnAsistenBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsistenBedah.setMnemonic('2');
        BtnAsistenBedah.setToolTipText("Alt+2");
        BtnAsistenBedah.setName("BtnAsistenBedah"); // NOI18N
        BtnAsistenBedah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsistenBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenBedahActionPerformed(evt);
            }
        });
        BtnAsistenBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAsistenBedahKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnAsistenBedah);
        BtnAsistenBedah.setBounds(970, 70, 28, 23);

        label16.setText("Dokter Anestesi:");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label16);
        label16.setBounds(0, 100, 90, 23);

        KdDokterAnestesi.setEditable(false);
        KdDokterAnestesi.setName("KdDokterAnestesi"); // NOI18N
        KdDokterAnestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(KdDokterAnestesi);
        KdDokterAnestesi.setBounds(90, 100, 90, 23);

        NmDokterAnestesi.setEditable(false);
        NmDokterAnestesi.setName("NmDokterAnestesi"); // NOI18N
        NmDokterAnestesi.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass12.add(NmDokterAnestesi);
        NmDokterAnestesi.setBounds(190, 100, 180, 23);

        BtnDokterAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnestesi.setMnemonic('2');
        BtnDokterAnestesi.setToolTipText("Alt+2");
        BtnDokterAnestesi.setName("BtnDokterAnestesi"); // NOI18N
        BtnDokterAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnestesiActionPerformed(evt);
            }
        });
        BtnDokterAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterAnestesiKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnDokterAnestesi);
        BtnDokterAnestesi.setBounds(370, 100, 28, 23);

        label17.setText("Asisten Anestesi:");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label17);
        label17.setBounds(390, 100, 90, 23);

        KdAsistenAnestesi.setEditable(false);
        KdAsistenAnestesi.setName("KdAsistenAnestesi"); // NOI18N
        KdAsistenAnestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(KdAsistenAnestesi);
        KdAsistenAnestesi.setBounds(480, 100, 90, 23);

        NmAsistenAnestesi.setEditable(false);
        NmAsistenAnestesi.setName("NmAsistenAnestesi"); // NOI18N
        NmAsistenAnestesi.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass12.add(NmAsistenAnestesi);
        NmAsistenAnestesi.setBounds(580, 100, 180, 23);

        BtnAsistenAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsistenAnestesi.setMnemonic('2');
        BtnAsistenAnestesi.setToolTipText("Alt+2");
        BtnAsistenAnestesi.setName("BtnAsistenAnestesi"); // NOI18N
        BtnAsistenAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsistenAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenAnestesiActionPerformed(evt);
            }
        });
        BtnAsistenAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAsistenAnestesiKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnAsistenAnestesi);
        BtnAsistenAnestesi.setBounds(760, 100, 28, 23);

        jLabel53.setText("Kesadaran:");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass12.add(jLabel53);
        jLabel53.setBounds(0, 170, 80, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos mentis", "Apatis", "Delirium", "Sopor", "Koma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        panelGlass12.add(Kesadaran);
        Kesadaran.setBounds(90, 170, 140, 20);

        jLabel54.setText("Respirasi:");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass12.add(jLabel54);
        jLabel54.setBounds(0, 200, 80, 23);

        Regular.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Iregular", "Adekuat", "Inadekuat" }));
        Regular.setName("Regular"); // NOI18N
        panelGlass12.add(Regular);
        Regular.setBounds(370, 230, 110, 20);

        Respirasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Assist", "Kontrol" }));
        Respirasi.setName("Respirasi"); // NOI18N
        panelGlass12.add(Respirasi);
        Respirasi.setBounds(80, 200, 110, 20);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("x/menit");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(790, 170, 50, 23);

        jLabel35.setText("SpO2:");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass12.add(jLabel35);
        jLabel35.setBounds(630, 200, 40, 23);

        Spo2.setFocusTraversalPolicyProvider(true);
        Spo2.setName("Spo2"); // NOI18N
        Spo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Spo2KeyPressed(evt);
            }
        });
        panelGlass12.add(Spo2);
        Spo2.setBounds(670, 200, 70, 23);

        Kanul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kanul Nasal", "SM", "SMNR" }));
        Kanul.setName("Kanul"); // NOI18N
        panelGlass12.add(Kanul);
        Kanul.setBounds(330, 200, 110, 20);

        jLabel55.setText("Airway:");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass12.add(jLabel55);
        jLabel55.setBounds(500, 230, 80, 23);

        jLabel56.setText("Support:");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass12.add(jLabel56);
        jLabel56.setBounds(0, 260, 90, 23);

        Support.setFocusTraversalPolicyProvider(true);
        Support.setName("Support"); // NOI18N
        Support.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SupportKeyPressed(evt);
            }
        });
        panelGlass12.add(Support);
        Support.setBounds(100, 260, 260, 23);

        jLabel57.setText("STATUS FISIK:");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass12.add(jLabel57);
        jLabel57.setBounds(380, 260, 80, 23);

        StatusFisik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I", "II", "III", "IV", "V", "E" }));
        StatusFisik.setName("StatusFisik"); // NOI18N
        panelGlass12.add(StatusFisik);
        StatusFisik.setBounds(470, 260, 110, 20);

        jLabel32.setText("TD:");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass12.add(jLabel32);
        jLabel32.setBounds(500, 170, 40, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        panelGlass12.add(jSeparator5);
        jSeparator5.setBounds(0, 140, 1290, 3);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        panelGlass12.add(jSeparator6);
        jSeparator6.setBounds(0, 140, 1290, 3);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("A. STATUS MEDIS SAAT MASUK KAMAR OPERASI");
        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setName("jLabel110"); // NOI18N
        panelGlass12.add(jLabel110);
        jLabel110.setBounds(10, 150, 280, 23);

        Airway.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terintubasi", "Tidak Terintubasi" }));
        Airway.setName("Airway"); // NOI18N
        panelGlass12.add(Airway);
        Airway.setBounds(590, 230, 110, 20);

        label30.setText("Penginput:");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label30);
        label30.setBounds(600, 260, 60, 23);

        NIPPenginput.setEditable(false);
        NIPPenginput.setName("NIPPenginput"); // NOI18N
        NIPPenginput.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(NIPPenginput);
        NIPPenginput.setBounds(680, 260, 90, 23);

        NamaPenginput.setEditable(false);
        NamaPenginput.setName("NamaPenginput"); // NOI18N
        NamaPenginput.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass12.add(NamaPenginput);
        NamaPenginput.setBounds(780, 260, 180, 23);

        BtnAsistenBedah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsistenBedah2.setMnemonic('2');
        BtnAsistenBedah2.setToolTipText("Alt+2");
        BtnAsistenBedah2.setName("BtnAsistenBedah2"); // NOI18N
        BtnAsistenBedah2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsistenBedah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenBedah2ActionPerformed(evt);
            }
        });
        BtnAsistenBedah2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAsistenBedah2KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnAsistenBedah2);
        BtnAsistenBedah2.setBounds(960, 260, 28, 23);

        PanelInput.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbAnestesi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi.setComponentPopupMenu(jPopupMenu1);
        tbAnestesi.setName("tbAnestesi"); // NOI18N
        tbAnestesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesiMouseClicked(evt);
            }
        });
        tbAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesiKeyReleased(evt);
            }
        });
        Scroll10.setViewportView(tbAnestesi);

        internalFrame2.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("A. Status Medis Saat Masuk", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 120));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('I');
        ChkInput1.setText(".: Input Data");
        ChkInput1.setToolTipText("Alt+I");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass13.setLayout(null);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        panelGlass13.add(jSeparator3);
        jSeparator3.setBounds(0, 381, 1290, 0);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("B. PREMEDIKASI");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        panelGlass13.add(jLabel111);
        jLabel111.setBounds(10, 10, 100, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        panelGlass13.add(jSeparator12);
        jSeparator12.setBounds(0, 770, 750, 1);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        panelGlass13.add(jSeparator13);
        jSeparator13.setBounds(0, 770, 750, 1);

        Rute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oral", "IM", "IV", "Rektal" }));
        Rute.setName("Rute"); // NOI18N
        panelGlass13.add(Rute);
        Rute.setBounds(120, 10, 110, 20);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        panelGlass13.add(jSeparator16);
        jSeparator16.setBounds(0, 351, 1290, 0);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        panelGlass13.add(jSeparator17);
        jSeparator17.setBounds(0, 351, 1290, 0);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        panelGlass13.add(jSeparator18);
        jSeparator18.setBounds(0, 351, 1290, 0);

        label24.setText("Tanggal :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(label24);
        label24.setBounds(240, 10, 52, 23);

        TglPremedikasi.setForeground(new java.awt.Color(50, 70, 50));
        TglPremedikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025 07:33:11" }));
        TglPremedikasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPremedikasi.setName("TglPremedikasi"); // NOI18N
        TglPremedikasi.setOpaque(false);
        TglPremedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglPremedikasiKeyPressed(evt);
            }
        });
        panelGlass13.add(TglPremedikasi);
        TglPremedikasi.setBounds(300, 10, 130, 23);

        jLabel77.setText("Obat:");
        jLabel77.setName("jLabel77"); // NOI18N
        panelGlass13.add(jLabel77);
        jLabel77.setBounds(70, 40, 40, 23);

        Obat.setFocusTraversalPolicyProvider(true);
        Obat.setName("Obat"); // NOI18N
        Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatKeyPressed(evt);
            }
        });
        panelGlass13.add(Obat);
        Obat.setBounds(120, 40, 260, 23);

        jLabel78.setText("Dosis:");
        jLabel78.setName("jLabel78"); // NOI18N
        panelGlass13.add(jLabel78);
        jLabel78.setBounds(380, 40, 40, 23);

        Dosis.setFocusTraversalPolicyProvider(true);
        Dosis.setName("Dosis"); // NOI18N
        Dosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DosisKeyPressed(evt);
            }
        });
        panelGlass13.add(Dosis);
        Dosis.setBounds(430, 40, 160, 23);

        jLabel79.setText("Hasil: Ramsaya sedation score:");
        jLabel79.setName("jLabel79"); // NOI18N
        panelGlass13.add(jLabel79);
        jLabel79.setBounds(600, 40, 160, 23);

        Ramsaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "I", "II", "III", "IV" }));
        Ramsaya.setName("Ramsaya"); // NOI18N
        panelGlass13.add(Ramsaya);
        Ramsaya.setBounds(760, 40, 60, 20);

        label19.setText("Pegawai:");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(label19);
        label19.setBounds(30, 70, 90, 23);

        Pegawaipremedikasi.setEditable(false);
        Pegawaipremedikasi.setName("Pegawaipremedikasi"); // NOI18N
        Pegawaipremedikasi.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(Pegawaipremedikasi);
        Pegawaipremedikasi.setBounds(120, 70, 90, 23);

        NmPegawaiPremedikasi.setEditable(false);
        NmPegawaiPremedikasi.setName("NmPegawaiPremedikasi"); // NOI18N
        NmPegawaiPremedikasi.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass13.add(NmPegawaiPremedikasi);
        NmPegawaiPremedikasi.setBounds(220, 70, 160, 23);

        BtnAsistenBedah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsistenBedah1.setMnemonic('2');
        BtnAsistenBedah1.setToolTipText("Alt+2");
        BtnAsistenBedah1.setName("BtnAsistenBedah1"); // NOI18N
        BtnAsistenBedah1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsistenBedah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsistenBedah1ActionPerformed(evt);
            }
        });
        BtnAsistenBedah1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAsistenBedah1KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnAsistenBedah1);
        BtnAsistenBedah1.setBounds(380, 70, 28, 23);

        PanelInput1.add(panelGlass13, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbAnestesi2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi2.setName("tbAnestesi2"); // NOI18N
        tbAnestesi2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi2MouseClicked(evt);
            }
        });
        tbAnestesi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi2KeyReleased(evt);
            }
        });
        Scroll11.setViewportView(tbAnestesi2);

        internalFrame3.add(Scroll11, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("B. Premedikasi", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput2.setMnemonic('I');
        ChkInput2.setText(".: Input Data");
        ChkInput2.setToolTipText("Alt+I");
        ChkInput2.setBorderPainted(true);
        ChkInput2.setBorderPaintedFlat(true);
        ChkInput2.setFocusable(false);
        ChkInput2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput2.setName("ChkInput2"); // NOI18N
        ChkInput2.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput2ActionPerformed(evt);
            }
        });
        PanelInput2.add(ChkInput2, java.awt.BorderLayout.PAGE_END);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass14.setLayout(null);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        panelGlass14.add(jSeparator4);
        jSeparator4.setBounds(0, 381, 1290, 0);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        panelGlass14.add(jSeparator14);
        jSeparator14.setBounds(0, 770, 750, 1);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        panelGlass14.add(jSeparator15);
        jSeparator15.setBounds(0, 770, 750, 1);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        panelGlass14.add(jSeparator19);
        jSeparator19.setBounds(0, 351, 1290, 0);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        panelGlass14.add(jSeparator20);
        jSeparator20.setBounds(0, 351, 1290, 0);

        jSeparator21.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator21.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator21.setName("jSeparator21"); // NOI18N
        panelGlass14.add(jSeparator21);
        jSeparator21.setBounds(0, 351, 1290, 0);

        jLabel58.setText("Kesadaran:");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass14.add(jLabel58);
        jLabel58.setBounds(200, 10, 80, 23);

        Kesadaran2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos mentis", "Apatis", "Delirium", "Sopor", "Koma" }));
        Kesadaran2.setName("Kesadaran2"); // NOI18N
        panelGlass14.add(Kesadaran2);
        Kesadaran2.setBounds(290, 10, 140, 20);

        jLabel19.setText("GCS:");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass14.add(jLabel19);
        jLabel19.setBounds(450, 10, 30, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("E:");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass14.add(jLabel33);
        jLabel33.setBounds(490, 10, 20, 23);

        E2.setFocusTraversalPolicyProvider(true);
        E2.setName("E2"); // NOI18N
        E2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                E2KeyPressed(evt);
            }
        });
        panelGlass14.add(E2);
        E2.setBounds(510, 10, 40, 23);

        jLabel34.setText("M:");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass14.add(jLabel34);
        jLabel34.setBounds(550, 10, 30, 23);

        M2.setFocusTraversalPolicyProvider(true);
        M2.setName("M2"); // NOI18N
        M2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M2KeyPressed(evt);
            }
        });
        panelGlass14.add(M2);
        M2.setBounds(590, 10, 40, 23);

        jLabel36.setText("V :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass14.add(jLabel36);
        jLabel36.setBounds(630, 10, 30, 23);

        V2.setFocusTraversalPolicyProvider(true);
        V2.setName("V2"); // NOI18N
        V2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                V2KeyPressed(evt);
            }
        });
        panelGlass14.add(V2);
        V2.setBounds(660, 10, 40, 23);

        jLabel37.setText("TD:");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass14.add(jLabel37);
        jLabel37.setBounds(700, 10, 40, 23);

        Tensi.setFocusTraversalPolicyProvider(true);
        Tensi.setName("Tensi"); // NOI18N
        Tensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TensiKeyPressed(evt);
            }
        });
        panelGlass14.add(Tensi);
        Tensi.setBounds(750, 10, 80, 23);

        jLabel38.setText("mmHg");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass14.add(jLabel38);
        jLabel38.setBounds(830, 10, 40, 23);

        jLabel21.setText("Nadi :");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass14.add(jLabel21);
        jLabel21.setBounds(880, 10, 40, 23);

        Nadi1.setFocusTraversalPolicyProvider(true);
        Nadi1.setName("Nadi1"); // NOI18N
        Nadi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nadi1ActionPerformed(evt);
            }
        });
        Nadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi1KeyPressed(evt);
            }
        });
        panelGlass14.add(Nadi1);
        Nadi1.setBounds(930, 10, 55, 23);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("x/menit");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass14.add(jLabel39);
        jLabel39.setBounds(990, 10, 50, 23);

        jLabel59.setText("Respirasi:");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass14.add(jLabel59);
        jLabel59.setBounds(0, 40, 80, 23);

        Respirasi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Assist", "Kontrol" }));
        Respirasi2.setName("Respirasi2"); // NOI18N
        panelGlass14.add(Respirasi2);
        Respirasi2.setBounds(80, 40, 110, 20);

        jLabel40.setText("RR:");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass14.add(jLabel40);
        jLabel40.setBounds(190, 40, 30, 23);

        Rr2.setFocusTraversalPolicyProvider(true);
        Rr2.setName("Rr2"); // NOI18N
        Rr2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Rr2KeyPressed(evt);
            }
        });
        panelGlass14.add(Rr2);
        Rr2.setBounds(230, 40, 55, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("x/menit");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass14.add(jLabel41);
        jLabel41.setBounds(290, 40, 50, 23);

        Selang2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kanul Nasal", "SM", "SMNR" }));
        Selang2.setName("Selang2"); // NOI18N
        panelGlass14.add(Selang2);
        Selang2.setBounds(330, 40, 110, 20);

        jLabel42.setText("O2:");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass14.add(jLabel42);
        jLabel42.setBounds(470, 40, 20, 23);

        o22.setFocusTraversalPolicyProvider(true);
        o22.setName("o22"); // NOI18N
        o22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                o22KeyPressed(evt);
            }
        });
        panelGlass14.add(o22);
        o22.setBounds(490, 40, 70, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("liter/menit");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass14.add(jLabel43);
        jLabel43.setBounds(570, 40, 70, 23);

        jLabel44.setText("SpO2:");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass14.add(jLabel44);
        jLabel44.setBounds(680, 40, 40, 23);

        Spo22.setFocusTraversalPolicyProvider(true);
        Spo22.setName("Spo22"); // NOI18N
        Spo22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Spo22KeyPressed(evt);
            }
        });
        panelGlass14.add(Spo22);
        Spo22.setBounds(720, 40, 70, 23);

        jLabel45.setText("Tipe Pernapasan:");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass14.add(jLabel45);
        jLabel45.setBounds(0, 70, 90, 23);

        TipePernapasan2.setFocusTraversalPolicyProvider(true);
        TipePernapasan2.setName("TipePernapasan2"); // NOI18N
        TipePernapasan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipePernapasan2KeyPressed(evt);
            }
        });
        panelGlass14.add(TipePernapasan2);
        TipePernapasan2.setBounds(100, 70, 260, 23);

        jLabel60.setText("Support:");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass14.add(jLabel60);
        jLabel60.setBounds(0, 100, 90, 23);

        Support2.setFocusTraversalPolicyProvider(true);
        Support2.setName("Support2"); // NOI18N
        Support2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Support2KeyPressed(evt);
            }
        });
        panelGlass14.add(Support2);
        Support2.setBounds(100, 100, 260, 23);

        Pernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Iregular", "Adekuat", "Inadekuat" }));
        Pernapasan.setName("Pernapasan"); // NOI18N
        panelGlass14.add(Pernapasan);
        Pernapasan.setBounds(370, 70, 110, 20);

        label18.setText("Pegawai:");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(label18);
        label18.setBounds(370, 100, 80, 23);

        NIP2.setEditable(false);
        NIP2.setName("NIP2"); // NOI18N
        NIP2.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass14.add(NIP2);
        NIP2.setBounds(460, 100, 90, 23);

        NamaPegawai2.setEditable(false);
        NamaPegawai2.setName("NamaPegawai2"); // NOI18N
        NamaPegawai2.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass14.add(NamaPegawai2);
        NamaPegawai2.setBounds(560, 100, 180, 23);

        BtnPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai.setMnemonic('2');
        BtnPegawai.setToolTipText("Alt+2");
        BtnPegawai.setName("BtnPegawai"); // NOI18N
        BtnPegawai.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawaiActionPerformed(evt);
            }
        });
        BtnPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawaiKeyPressed(evt);
            }
        });
        panelGlass14.add(BtnPegawai);
        BtnPegawai.setBounds(740, 100, 28, 23);

        label31.setText("Tanggal :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(label31);
        label31.setBounds(10, 10, 52, 23);

        TglPrainduksi.setForeground(new java.awt.Color(50, 70, 50));
        TglPrainduksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025 07:33:11" }));
        TglPrainduksi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPrainduksi.setName("TglPrainduksi"); // NOI18N
        TglPrainduksi.setOpaque(false);
        TglPrainduksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglPrainduksiKeyPressed(evt);
            }
        });
        panelGlass14.add(TglPrainduksi);
        TglPrainduksi.setBounds(70, 10, 130, 23);

        PanelInput2.add(panelGlass14, java.awt.BorderLayout.CENTER);

        internalFrame4.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbAnestesi3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi3.setName("tbAnestesi3"); // NOI18N
        tbAnestesi3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi3MouseClicked(evt);
            }
        });
        tbAnestesi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi3KeyReleased(evt);
            }
        });
        Scroll12.setViewportView(tbAnestesi3);

        internalFrame4.add(Scroll12, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("C. Pra Induksi", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        TabTeknikAnestesi.setBackground(new java.awt.Color(254, 255, 254));
        TabTeknikAnestesi.setForeground(new java.awt.Color(50, 50, 50));
        TabTeknikAnestesi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabTeknikAnestesi.setName("TabTeknikAnestesi"); // NOI18N
        TabTeknikAnestesi.setPreferredSize(new java.awt.Dimension(457, 480));

        internalFrame14.setBorder(null);
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 270));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setMnemonic('I');
        ChkInput3.setText(".: Input Data");
        ChkInput3.setToolTipText("Alt+I");
        ChkInput3.setBorderPainted(true);
        ChkInput3.setBorderPaintedFlat(true);
        ChkInput3.setFocusable(false);
        ChkInput3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput3.setName("ChkInput3"); // NOI18N
        ChkInput3.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput3ActionPerformed(evt);
            }
        });
        PanelInput3.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass15.setLayout(null);

        jSeparator22.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator22.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator22.setName("jSeparator22"); // NOI18N
        panelGlass15.add(jSeparator22);
        jSeparator22.setBounds(0, 381, 1290, 0);

        jSeparator23.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator23.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator23.setName("jSeparator23"); // NOI18N
        panelGlass15.add(jSeparator23);
        jSeparator23.setBounds(0, 770, 750, 1);

        jSeparator24.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator24.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator24.setName("jSeparator24"); // NOI18N
        panelGlass15.add(jSeparator24);
        jSeparator24.setBounds(0, 770, 750, 1);

        jSeparator25.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator25.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator25.setName("jSeparator25"); // NOI18N
        panelGlass15.add(jSeparator25);
        jSeparator25.setBounds(0, 351, 1290, 0);

        jSeparator26.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator26.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator26.setName("jSeparator26"); // NOI18N
        panelGlass15.add(jSeparator26);
        jSeparator26.setBounds(0, 351, 1290, 0);

        jSeparator27.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator27.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator27.setName("jSeparator27"); // NOI18N
        panelGlass15.add(jSeparator27);
        jSeparator27.setBounds(0, 351, 1290, 0);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("ANESTESI UMUM");
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        panelGlass15.add(jLabel112);
        jLabel112.setBounds(0, 0, 280, 23);

        jLabel46.setText("Induksi:");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass15.add(jLabel46);
        jLabel46.setBounds(170, 20, 50, 23);

        Induksi.setFocusTraversalPolicyProvider(true);
        Induksi.setName("Induksi"); // NOI18N
        Induksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InduksiKeyPressed(evt);
            }
        });
        panelGlass15.add(Induksi);
        Induksi.setBounds(230, 20, 260, 23);

        RuteInduksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semi open", "Semi closed", "Closed" }));
        RuteInduksi.setName("RuteInduksi"); // NOI18N
        panelGlass15.add(RuteInduksi);
        RuteInduksi.setBounds(500, 20, 110, 20);

        jLabel47.setText("Pengaturan Nafas:");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass15.add(jLabel47);
        jLabel47.setBounds(620, 20, 110, 23);

        PengaturanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Assisted", "Controlled" }));
        PengaturanNafas.setName("PengaturanNafas"); // NOI18N
        panelGlass15.add(PengaturanNafas);
        PengaturanNafas.setBounds(740, 20, 110, 20);

        jLabel48.setText("ml");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass15.add(jLabel48);
        jLabel48.setBounds(1080, 20, 20, 23);

        Ventilator.setFocusTraversalPolicyProvider(true);
        Ventilator.setName("Ventilator"); // NOI18N
        Ventilator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentilatorActionPerformed(evt);
            }
        });
        Ventilator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VentilatorKeyPressed(evt);
            }
        });
        panelGlass15.add(Ventilator);
        Ventilator.setBounds(1000, 20, 80, 23);

        jLabel61.setText("Ventiliator Tidal Volume:");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass15.add(jLabel61);
        jLabel61.setBounds(860, 20, 130, 23);

        jLabel62.setText("x/mnt");
        jLabel62.setName("jLabel62"); // NOI18N
        panelGlass15.add(jLabel62);
        jLabel62.setBounds(140, 60, 40, 23);

        Rr3.setFocusTraversalPolicyProvider(true);
        Rr3.setName("Rr3"); // NOI18N
        Rr3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Rr3KeyPressed(evt);
            }
        });
        panelGlass15.add(Rr3);
        Rr3.setBounds(70, 60, 70, 23);

        jLabel63.setText("I : E ratio:");
        jLabel63.setName("jLabel63"); // NOI18N
        panelGlass15.add(jLabel63);
        jLabel63.setBounds(190, 60, 50, 23);

        ERatio.setFocusTraversalPolicyProvider(true);
        ERatio.setName("ERatio"); // NOI18N
        ERatio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ERatioKeyPressed(evt);
            }
        });
        panelGlass15.add(ERatio);
        ERatio.setBounds(250, 60, 90, 23);

        jLabel64.setText("PEEP:");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass15.add(jLabel64);
        jLabel64.setBounds(350, 60, 40, 23);

        Peep.setFocusTraversalPolicyProvider(true);
        Peep.setName("Peep"); // NOI18N
        Peep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PeepKeyPressed(evt);
            }
        });
        panelGlass15.add(Peep);
        Peep.setBounds(400, 60, 90, 23);

        jLabel65.setText("PIP:");
        jLabel65.setName("jLabel65"); // NOI18N
        panelGlass15.add(jLabel65);
        jLabel65.setBounds(500, 60, 30, 23);

        jLabel66.setText("RR:");
        jLabel66.setName("jLabel66"); // NOI18N
        panelGlass15.add(jLabel66);
        jLabel66.setBounds(10, 60, 50, 23);

        Pip.setFocusTraversalPolicyProvider(true);
        Pip.setName("Pip"); // NOI18N
        Pip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PipKeyPressed(evt);
            }
        });
        panelGlass15.add(Pip);
        Pip.setBounds(540, 60, 70, 23);

        jLabel67.setText("%");
        jLabel67.setName("jLabel67"); // NOI18N
        panelGlass15.add(jLabel67);
        jLabel67.setBounds(740, 60, 20, 23);

        Fi02.setFocusTraversalPolicyProvider(true);
        Fi02.setName("Fi02"); // NOI18N
        Fi02.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Fi02KeyPressed(evt);
            }
        });
        panelGlass15.add(Fi02);
        Fi02.setBounds(660, 60, 70, 23);

        jLabel68.setText("FIO2:");
        jLabel68.setName("jLabel68"); // NOI18N
        panelGlass15.add(jLabel68);
        jLabel68.setBounds(620, 60, 30, 23);

        jLabel69.setText("Teknik Khusus:");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass15.add(jLabel69);
        jLabel69.setBounds(20, 90, 80, 23);

        TeknikKhusus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hipotermi", "Hipotensi", "By Pass", "Ventilasi satu paru" }));
        TeknikKhusus.setName("TeknikKhusus"); // NOI18N
        panelGlass15.add(TeknikKhusus);
        TeknikKhusus.setBounds(100, 90, 110, 20);

        jLabel70.setText("Pemeliharaan:");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass15.add(jLabel70);
        jLabel70.setBounds(220, 90, 80, 23);

        Pemeliharaan.setFocusTraversalPolicyProvider(true);
        Pemeliharaan.setName("Pemeliharaan"); // NOI18N
        Pemeliharaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeliharaanKeyPressed(evt);
            }
        });
        panelGlass15.add(Pemeliharaan);
        Pemeliharaan.setBounds(310, 90, 180, 23);

        jLabel71.setText("Perhitungan Cairan:");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass15.add(jLabel71);
        jLabel71.setBounds(500, 90, 120, 23);

        PerhitunganCairan.setFocusTraversalPolicyProvider(true);
        PerhitunganCairan.setName("PerhitunganCairan"); // NOI18N
        PerhitunganCairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerhitunganCairanKeyPressed(evt);
            }
        });
        panelGlass15.add(PerhitunganCairan);
        PerhitunganCairan.setBounds(630, 90, 170, 23);

        jLabel72.setText("EBV:");
        jLabel72.setName("jLabel72"); // NOI18N
        panelGlass15.add(jLabel72);
        jLabel72.setBounds(810, 90, 50, 23);

        Ebv.setFocusTraversalPolicyProvider(true);
        Ebv.setName("Ebv"); // NOI18N
        Ebv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EbvKeyPressed(evt);
            }
        });
        panelGlass15.add(Ebv);
        Ebv.setBounds(870, 90, 100, 23);

        jLabel73.setText("EBL:");
        jLabel73.setName("jLabel73"); // NOI18N
        panelGlass15.add(jLabel73);
        jLabel73.setBounds(20, 120, 50, 23);

        Ebl.setFocusTraversalPolicyProvider(true);
        Ebl.setName("Ebl"); // NOI18N
        Ebl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EblKeyPressed(evt);
            }
        });
        panelGlass15.add(Ebl);
        Ebl.setBounds(90, 120, 100, 23);

        jLabel74.setText("Persiapan Darah: Jenis:");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass15.add(jLabel74);
        jLabel74.setBounds(210, 120, 130, 23);

        JenisDarah.setFocusTraversalPolicyProvider(true);
        JenisDarah.setName("JenisDarah"); // NOI18N
        JenisDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JenisDarahActionPerformed(evt);
            }
        });
        JenisDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisDarahKeyPressed(evt);
            }
        });
        panelGlass15.add(JenisDarah);
        JenisDarah.setBounds(350, 120, 140, 23);

        jLabel75.setText("Jumlah:");
        jLabel75.setName("jLabel75"); // NOI18N
        panelGlass15.add(jLabel75);
        jLabel75.setBounds(510, 120, 60, 23);

        JumlahDarah.setFocusTraversalPolicyProvider(true);
        JumlahDarah.setName("JumlahDarah"); // NOI18N
        JumlahDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahDarahKeyPressed(evt);
            }
        });
        panelGlass15.add(JumlahDarah);
        JumlahDarah.setBounds(580, 120, 150, 23);

        Monitoring.setFocusTraversalPolicyProvider(true);
        Monitoring.setName("Monitoring"); // NOI18N
        Monitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKeyPressed(evt);
            }
        });
        panelGlass15.add(Monitoring);
        Monitoring.setBounds(100, 150, 240, 23);

        jLabel76.setText("Monitoring:");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass15.add(jLabel76);
        jLabel76.setBounds(20, 150, 70, 23);

        PerubahanTeknikAnestesi.setFocusTraversalPolicyProvider(true);
        PerubahanTeknikAnestesi.setName("PerubahanTeknikAnestesi"); // NOI18N
        PerubahanTeknikAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerubahanTeknikAnestesiKeyPressed(evt);
            }
        });
        panelGlass15.add(PerubahanTeknikAnestesi);
        PerubahanTeknikAnestesi.setBounds(500, 150, 470, 23);

        jLabel80.setText("Perubahan Teknik Anestesi:");
        jLabel80.setName("jLabel80"); // NOI18N
        panelGlass15.add(jLabel80);
        jLabel80.setBounds(350, 150, 140, 23);

        Alasan.setFocusTraversalPolicyProvider(true);
        Alasan.setName("Alasan"); // NOI18N
        Alasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlasanKeyPressed(evt);
            }
        });
        panelGlass15.add(Alasan);
        Alasan.setBounds(100, 180, 870, 23);

        jLabel81.setText("Alasan:");
        jLabel81.setName("jLabel81"); // NOI18N
        panelGlass15.add(jLabel81);
        jLabel81.setBounds(40, 180, 50, 23);

        label20.setText("Pegawai:");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass15.add(label20);
        label20.setBounds(10, 210, 80, 23);

        NIP3.setName("NIP3"); // NOI18N
        NIP3.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass15.add(NIP3);
        NIP3.setBounds(100, 210, 90, 23);

        NamaPegawai3.setName("NamaPegawai3"); // NOI18N
        NamaPegawai3.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass15.add(NamaPegawai3);
        NamaPegawai3.setBounds(200, 210, 180, 23);

        BtnPegawai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai1.setMnemonic('2');
        BtnPegawai1.setToolTipText("Alt+2");
        BtnPegawai1.setName("BtnPegawai1"); // NOI18N
        BtnPegawai1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai1ActionPerformed(evt);
            }
        });
        BtnPegawai1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai1KeyPressed(evt);
            }
        });
        panelGlass15.add(BtnPegawai1);
        BtnPegawai1.setBounds(380, 210, 28, 23);

        label32.setText("Tanggal :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass15.add(label32);
        label32.setBounds(-20, 20, 52, 23);

        TglTeknikAnestesiUmum.setForeground(new java.awt.Color(50, 70, 50));
        TglTeknikAnestesiUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025 07:33:11" }));
        TglTeknikAnestesiUmum.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglTeknikAnestesiUmum.setName("TglTeknikAnestesiUmum"); // NOI18N
        TglTeknikAnestesiUmum.setOpaque(false);
        TglTeknikAnestesiUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglTeknikAnestesiUmumKeyPressed(evt);
            }
        });
        panelGlass15.add(TglTeknikAnestesiUmum);
        TglTeknikAnestesiUmum.setBounds(40, 20, 130, 23);

        PanelInput3.add(panelGlass15, java.awt.BorderLayout.CENTER);

        internalFrame14.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);
        Scroll16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Scroll16MouseClicked(evt);
            }
        });

        tbAnestesi4.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi4.setName("tbAnestesi4"); // NOI18N
        tbAnestesi4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi4MouseClicked(evt);
            }
        });
        tbAnestesi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi4KeyReleased(evt);
            }
        });
        Scroll16.setViewportView(tbAnestesi4);

        internalFrame14.add(Scroll16, java.awt.BorderLayout.CENTER);

        TabTeknikAnestesi.addTab("ANESTESI UMUM", internalFrame14);

        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput4.setName("PanelInput4"); // NOI18N
        PanelInput4.setOpaque(false);
        PanelInput4.setPreferredSize(new java.awt.Dimension(192, 320));
        PanelInput4.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setMnemonic('I');
        ChkInput4.setText(".: Input Data");
        ChkInput4.setToolTipText("Alt+I");
        ChkInput4.setBorderPainted(true);
        ChkInput4.setBorderPaintedFlat(true);
        ChkInput4.setFocusable(false);
        ChkInput4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput4.setName("ChkInput4"); // NOI18N
        ChkInput4.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput4ActionPerformed(evt);
            }
        });
        PanelInput4.add(ChkInput4, java.awt.BorderLayout.PAGE_END);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass16.setLayout(null);

        jSeparator58.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator58.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator58.setName("jSeparator58"); // NOI18N
        panelGlass16.add(jSeparator58);
        jSeparator58.setBounds(0, 381, 1290, 0);

        jSeparator60.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator60.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator60.setName("jSeparator60"); // NOI18N
        panelGlass16.add(jSeparator60);
        jSeparator60.setBounds(0, 770, 750, 1);

        jSeparator59.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator59.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator59.setName("jSeparator59"); // NOI18N
        panelGlass16.add(jSeparator59);
        jSeparator59.setBounds(0, 770, 750, 1);

        jSeparator61.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator61.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator61.setName("jSeparator61"); // NOI18N
        panelGlass16.add(jSeparator61);
        jSeparator61.setBounds(0, 351, 1290, 0);

        jSeparator62.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator62.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator62.setName("jSeparator62"); // NOI18N
        panelGlass16.add(jSeparator62);
        jSeparator62.setBounds(0, 351, 1290, 0);

        jSeparator63.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator63.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator63.setName("jSeparator63"); // NOI18N
        panelGlass16.add(jSeparator63);
        jSeparator63.setBounds(0, 351, 1290, 0);

        jLabel82.setText("Lokasi Tusukan:");
        jLabel82.setName("jLabel82"); // NOI18N
        panelGlass16.add(jLabel82);
        jLabel82.setBounds(410, 10, 90, 23);

        LokasiTusukan.setFocusTraversalPolicyProvider(true);
        LokasiTusukan.setName("LokasiTusukan"); // NOI18N
        LokasiTusukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiTusukanKeyPressed(evt);
            }
        });
        panelGlass16.add(LokasiTusukan);
        LokasiTusukan.setBounds(510, 10, 230, 23);

        jLabel83.setText("Teknik:");
        jLabel83.setName("jLabel83"); // NOI18N
        panelGlass16.add(jLabel83);
        jLabel83.setBounds(220, 10, 40, 23);

        Teknik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Caudal", "Saddles block", "IV regional", "Epidural", "Blokade saraf tepi", "Spinal", "Topikal" }));
        Teknik.setName("Teknik"); // NOI18N
        panelGlass16.add(Teknik);
        Teknik.setBounds(270, 10, 130, 20);

        jLabel85.setText("Analgesi setinggi segmen:");
        jLabel85.setName("jLabel85"); // NOI18N
        panelGlass16.add(jLabel85);
        jLabel85.setBounds(10, 40, 130, 23);

        Analgesi.setFocusTraversalPolicyProvider(true);
        Analgesi.setName("Analgesi"); // NOI18N
        Analgesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnalgesiKeyPressed(evt);
            }
        });
        panelGlass16.add(Analgesi);
        Analgesi.setBounds(150, 40, 230, 23);

        jLabel101.setText("Anestesi lokal:");
        jLabel101.setName("jLabel101"); // NOI18N
        panelGlass16.add(jLabel101);
        jLabel101.setBounds(390, 40, 90, 23);

        Anestesi.setFocusTraversalPolicyProvider(true);
        Anestesi.setName("Anestesi"); // NOI18N
        Anestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnestesiKeyPressed(evt);
            }
        });
        panelGlass16.add(Anestesi);
        Anestesi.setBounds(490, 40, 230, 23);

        jLabel102.setText("Konsentrasi:");
        jLabel102.setName("jLabel102"); // NOI18N
        panelGlass16.add(jLabel102);
        jLabel102.setBounds(730, 40, 70, 23);

        Konsentrasi.setFocusTraversalPolicyProvider(true);
        Konsentrasi.setName("Konsentrasi"); // NOI18N
        Konsentrasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonsentrasiKeyPressed(evt);
            }
        });
        panelGlass16.add(Konsentrasi);
        Konsentrasi.setBounds(810, 40, 130, 23);

        jLabel103.setText("ml");
        jLabel103.setName("jLabel103"); // NOI18N
        panelGlass16.add(jLabel103);
        jLabel103.setBounds(1090, 40, 20, 23);

        Jumlah.setFocusTraversalPolicyProvider(true);
        Jumlah.setName("Jumlah"); // NOI18N
        Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahKeyPressed(evt);
            }
        });
        panelGlass16.add(Jumlah);
        Jumlah.setBounds(1000, 40, 90, 23);

        jLabel104.setText("Obat tambahan:");
        jLabel104.setName("jLabel104"); // NOI18N
        panelGlass16.add(jLabel104);
        jLabel104.setBounds(50, 70, 90, 23);

        ObatTambahan.setFocusTraversalPolicyProvider(true);
        ObatTambahan.setName("ObatTambahan"); // NOI18N
        ObatTambahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatTambahanKeyPressed(evt);
            }
        });
        panelGlass16.add(ObatTambahan);
        ObatTambahan.setBounds(150, 70, 230, 23);

        jLabel105.setText("dosis:");
        jLabel105.setName("jLabel105"); // NOI18N
        panelGlass16.add(jLabel105);
        jLabel105.setBounds(390, 70, 40, 23);

        Dosis2.setFocusTraversalPolicyProvider(true);
        Dosis2.setName("Dosis2"); // NOI18N
        Dosis2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Dosis2KeyPressed(evt);
            }
        });
        panelGlass16.add(Dosis2);
        Dosis2.setBounds(440, 70, 230, 23);

        jLabel106.setText("Vasokontriktor: adrenalin/");
        jLabel106.setName("jLabel106"); // NOI18N
        panelGlass16.add(jLabel106);
        jLabel106.setBounds(0, 100, 130, 23);

        Vasokonstriktor.setFocusTraversalPolicyProvider(true);
        Vasokonstriktor.setName("Vasokonstriktor"); // NOI18N
        Vasokonstriktor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VasokonstriktorKeyPressed(evt);
            }
        });
        panelGlass16.add(Vasokonstriktor);
        Vasokonstriktor.setBounds(140, 100, 230, 23);

        jLabel107.setText("Noraderenalin/");
        jLabel107.setName("jLabel107"); // NOI18N
        panelGlass16.add(jLabel107);
        jLabel107.setBounds(370, 100, 90, 23);

        Noradernalin.setFocusTraversalPolicyProvider(true);
        Noradernalin.setName("Noradernalin"); // NOI18N
        Noradernalin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoradernalinKeyPressed(evt);
            }
        });
        panelGlass16.add(Noradernalin);
        Noradernalin.setBounds(470, 100, 230, 23);

        jLabel108.setText("Konsentrasi:");
        jLabel108.setName("jLabel108"); // NOI18N
        panelGlass16.add(jLabel108);
        jLabel108.setBounds(710, 100, 90, 23);

        Konsentrasi2.setFocusTraversalPolicyProvider(true);
        Konsentrasi2.setName("Konsentrasi2"); // NOI18N
        Konsentrasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Konsentrasi2KeyPressed(evt);
            }
        });
        panelGlass16.add(Konsentrasi2);
        Konsentrasi2.setBounds(810, 100, 230, 23);

        jLabel109.setText("Jumlah:");
        jLabel109.setName("jLabel109"); // NOI18N
        panelGlass16.add(jLabel109);
        jLabel109.setBounds(940, 40, 50, 23);

        Pemeliharaan2.setFocusTraversalPolicyProvider(true);
        Pemeliharaan2.setName("Pemeliharaan2"); // NOI18N
        Pemeliharaan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pemeliharaan2KeyPressed(evt);
            }
        });
        panelGlass16.add(Pemeliharaan2);
        Pemeliharaan2.setBounds(140, 130, 230, 23);

        jLabel113.setText("Pemeliharaan:");
        jLabel113.setName("jLabel113"); // NOI18N
        panelGlass16.add(jLabel113);
        jLabel113.setBounds(40, 130, 90, 23);

        jLabel119.setText("Monitoring:");
        jLabel119.setName("jLabel119"); // NOI18N
        panelGlass16.add(jLabel119);
        jLabel119.setBounds(50, 160, 70, 23);

        Monitoring2.setFocusTraversalPolicyProvider(true);
        Monitoring2.setName("Monitoring2"); // NOI18N
        Monitoring2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring2KeyPressed(evt);
            }
        });
        panelGlass16.add(Monitoring2);
        Monitoring2.setBounds(130, 160, 240, 23);

        PerubahanTeknikAnestesi2.setFocusTraversalPolicyProvider(true);
        PerubahanTeknikAnestesi2.setName("PerubahanTeknikAnestesi2"); // NOI18N
        PerubahanTeknikAnestesi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PerubahanTeknikAnestesi2ActionPerformed(evt);
            }
        });
        PerubahanTeknikAnestesi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerubahanTeknikAnestesi2KeyPressed(evt);
            }
        });
        panelGlass16.add(PerubahanTeknikAnestesi2);
        PerubahanTeknikAnestesi2.setBounds(530, 160, 470, 23);

        Alasan2.setFocusTraversalPolicyProvider(true);
        Alasan2.setName("Alasan2"); // NOI18N
        Alasan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Alasan2KeyPressed(evt);
            }
        });
        panelGlass16.add(Alasan2);
        Alasan2.setBounds(130, 190, 870, 23);

        jLabel120.setText("Perubahan Teknik Anestesi:");
        jLabel120.setName("jLabel120"); // NOI18N
        panelGlass16.add(jLabel120);
        jLabel120.setBounds(380, 160, 140, 23);

        jLabel121.setText("Alasan:");
        jLabel121.setName("jLabel121"); // NOI18N
        panelGlass16.add(jLabel121);
        jLabel121.setBounds(70, 190, 50, 23);

        label21.setText("Pegawai:");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass16.add(label21);
        label21.setBounds(40, 220, 80, 23);

        NIP4.setEditable(false);
        NIP4.setName("NIP4"); // NOI18N
        NIP4.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass16.add(NIP4);
        NIP4.setBounds(130, 220, 90, 23);

        NamaPegawai4.setEditable(false);
        NamaPegawai4.setName("NamaPegawai4"); // NOI18N
        NamaPegawai4.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass16.add(NamaPegawai4);
        NamaPegawai4.setBounds(230, 220, 180, 23);

        BtnPegawai2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai2.setMnemonic('2');
        BtnPegawai2.setToolTipText("Alt+2");
        BtnPegawai2.setName("BtnPegawai2"); // NOI18N
        BtnPegawai2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai2ActionPerformed(evt);
            }
        });
        BtnPegawai2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai2KeyPressed(evt);
            }
        });
        panelGlass16.add(BtnPegawai2);
        BtnPegawai2.setBounds(410, 220, 28, 23);

        label33.setText("Tanggal :");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass16.add(label33);
        label33.setBounds(10, 10, 52, 23);

        TglBlokadeRegional.setForeground(new java.awt.Color(50, 70, 50));
        TglBlokadeRegional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025 07:33:11" }));
        TglBlokadeRegional.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglBlokadeRegional.setName("TglBlokadeRegional"); // NOI18N
        TglBlokadeRegional.setOpaque(false);
        TglBlokadeRegional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBlokadeRegionalKeyPressed(evt);
            }
        });
        panelGlass16.add(TglBlokadeRegional);
        TglBlokadeRegional.setBounds(70, 10, 130, 23);

        PanelInput4.add(panelGlass16, java.awt.BorderLayout.CENTER);

        internalFrame15.add(PanelInput4, java.awt.BorderLayout.PAGE_START);

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        tbAnestesi6.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi6.setName("tbAnestesi6"); // NOI18N
        tbAnestesi6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi6MouseClicked(evt);
            }
        });
        tbAnestesi6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi6KeyReleased(evt);
            }
        });
        Scroll17.setViewportView(tbAnestesi6);

        internalFrame15.add(Scroll17, java.awt.BorderLayout.CENTER);

        TabTeknikAnestesi.addTab("BLOKADE REGIONAL", internalFrame15);

        internalFrame16.setBorder(null);
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput5.setName("PanelInput5"); // NOI18N
        PanelInput5.setOpaque(false);
        PanelInput5.setPreferredSize(new java.awt.Dimension(192, 320));
        PanelInput5.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput5.setMnemonic('I');
        ChkInput5.setText(".: Input Data");
        ChkInput5.setToolTipText("Alt+I");
        ChkInput5.setBorderPainted(true);
        ChkInput5.setBorderPaintedFlat(true);
        ChkInput5.setFocusable(false);
        ChkInput5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput5.setName("ChkInput5"); // NOI18N
        ChkInput5.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput5ActionPerformed(evt);
            }
        });
        PanelInput5.add(ChkInput5, java.awt.BorderLayout.PAGE_END);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass17.setLayout(null);

        jSeparator64.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator64.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator64.setName("jSeparator64"); // NOI18N
        panelGlass17.add(jSeparator64);
        jSeparator64.setBounds(0, 381, 1290, 0);

        jSeparator65.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator65.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator65.setName("jSeparator65"); // NOI18N
        panelGlass17.add(jSeparator65);
        jSeparator65.setBounds(0, 770, 750, 1);

        jSeparator66.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator66.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator66.setName("jSeparator66"); // NOI18N
        panelGlass17.add(jSeparator66);
        jSeparator66.setBounds(0, 770, 750, 1);

        jSeparator67.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator67.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator67.setName("jSeparator67"); // NOI18N
        panelGlass17.add(jSeparator67);
        jSeparator67.setBounds(0, 351, 1290, 0);

        jSeparator68.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator68.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator68.setName("jSeparator68"); // NOI18N
        panelGlass17.add(jSeparator68);
        jSeparator68.setBounds(0, 351, 1290, 0);

        jSeparator69.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator69.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator69.setName("jSeparator69"); // NOI18N
        panelGlass17.add(jSeparator69);
        jSeparator69.setBounds(0, 351, 1290, 0);

        jLabel122.setText("Monitoring:");
        jLabel122.setName("jLabel122"); // NOI18N
        panelGlass17.add(jLabel122);
        jLabel122.setBounds(50, 70, 70, 23);

        Monitoring4.setFocusTraversalPolicyProvider(true);
        Monitoring4.setName("Monitoring4"); // NOI18N
        Monitoring4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring4KeyPressed(evt);
            }
        });
        panelGlass17.add(Monitoring4);
        Monitoring4.setBounds(130, 70, 240, 23);

        jLabel123.setText("Perubahan Teknik Anestesi:");
        jLabel123.setName("jLabel123"); // NOI18N
        panelGlass17.add(jLabel123);
        jLabel123.setBounds(380, 70, 140, 23);

        PerubahanTeknikAnestesi3.setFocusTraversalPolicyProvider(true);
        PerubahanTeknikAnestesi3.setName("PerubahanTeknikAnestesi3"); // NOI18N
        PerubahanTeknikAnestesi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerubahanTeknikAnestesi3KeyPressed(evt);
            }
        });
        panelGlass17.add(PerubahanTeknikAnestesi3);
        PerubahanTeknikAnestesi3.setBounds(530, 70, 470, 23);

        Alasan3.setFocusTraversalPolicyProvider(true);
        Alasan3.setName("Alasan3"); // NOI18N
        Alasan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Alasan3KeyPressed(evt);
            }
        });
        panelGlass17.add(Alasan3);
        Alasan3.setBounds(130, 100, 870, 23);

        jLabel124.setText("Alasan:");
        jLabel124.setName("jLabel124"); // NOI18N
        panelGlass17.add(jLabel124);
        jLabel124.setBounds(70, 100, 50, 23);

        jLabel125.setText("Teknik:");
        jLabel125.setName("jLabel125"); // NOI18N
        panelGlass17.add(jLabel125);
        jLabel125.setBounds(40, 10, 70, 23);

        jLabel126.setText("Obat Anestesi:");
        jLabel126.setName("jLabel126"); // NOI18N
        panelGlass17.add(jLabel126);
        jLabel126.setBounds(390, 10, 110, 23);

        ObatAnestesi.setFocusTraversalPolicyProvider(true);
        ObatAnestesi.setName("ObatAnestesi"); // NOI18N
        ObatAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatAnestesiKeyPressed(evt);
            }
        });
        panelGlass17.add(ObatAnestesi);
        ObatAnestesi.setBounds(520, 10, 190, 23);

        jLabel127.setText("Pemeliharaan:");
        jLabel127.setName("jLabel127"); // NOI18N
        panelGlass17.add(jLabel127);
        jLabel127.setBounds(40, 40, 80, 23);

        Pemeliharaan3.setFocusTraversalPolicyProvider(true);
        Pemeliharaan3.setName("Pemeliharaan3"); // NOI18N
        Pemeliharaan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pemeliharaan3KeyPressed(evt);
            }
        });
        panelGlass17.add(Pemeliharaan3);
        Pemeliharaan3.setBounds(130, 40, 580, 23);

        label22.setText("Pegawai:");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(label22);
        label22.setBounds(40, 130, 80, 23);

        NIP5.setEditable(false);
        NIP5.setName("NIP5"); // NOI18N
        NIP5.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass17.add(NIP5);
        NIP5.setBounds(130, 130, 90, 23);

        NamaPegawai5.setEditable(false);
        NamaPegawai5.setName("NamaPegawai5"); // NOI18N
        NamaPegawai5.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass17.add(NamaPegawai5);
        NamaPegawai5.setBounds(230, 130, 180, 23);

        BtnPegawai3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai3.setMnemonic('2');
        BtnPegawai3.setToolTipText("Alt+2");
        BtnPegawai3.setName("BtnPegawai3"); // NOI18N
        BtnPegawai3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai3ActionPerformed(evt);
            }
        });
        BtnPegawai3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai3KeyPressed(evt);
            }
        });
        panelGlass17.add(BtnPegawai3);
        BtnPegawai3.setBounds(410, 130, 28, 23);

        TeknikCombine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Caudal", "Saddles block", "IV regional", "Epidural", "Blokade saraf tepi", "Spinal", "Topikal" }));
        TeknikCombine.setName("TeknikCombine"); // NOI18N
        panelGlass17.add(TeknikCombine);
        TeknikCombine.setBounds(130, 10, 130, 20);

        PanelInput5.add(panelGlass17, java.awt.BorderLayout.CENTER);

        internalFrame16.add(PanelInput5, java.awt.BorderLayout.PAGE_START);

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        tbAnestesi7.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi7.setName("tbAnestesi7"); // NOI18N
        tbAnestesi7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi7MouseClicked(evt);
            }
        });
        tbAnestesi7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi7KeyReleased(evt);
            }
        });
        Scroll18.setViewportView(tbAnestesi7);

        internalFrame16.add(Scroll18, java.awt.BorderLayout.CENTER);

        TabTeknikAnestesi.addTab("COMBINED", internalFrame16);

        internalFrame5.add(TabTeknikAnestesi, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("D. Teknik Anestesi", internalFrame5);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        tbAnestesi8.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi8.setName("tbAnestesi8"); // NOI18N
        tbAnestesi8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi8MouseClicked(evt);
            }
        });
        tbAnestesi8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi8KeyReleased(evt);
            }
        });
        Scroll19.setViewportView(tbAnestesi8);

        internalFrame6.add(Scroll19, java.awt.BorderLayout.CENTER);

        PanelInput6.setName("PanelInput6"); // NOI18N
        PanelInput6.setOpaque(false);
        PanelInput6.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput6.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput6.setMnemonic('I');
        ChkInput6.setText(".: Input Data");
        ChkInput6.setToolTipText("Alt+I");
        ChkInput6.setBorderPainted(true);
        ChkInput6.setBorderPaintedFlat(true);
        ChkInput6.setFocusable(false);
        ChkInput6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput6.setName("ChkInput6"); // NOI18N
        ChkInput6.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput6ActionPerformed(evt);
            }
        });
        PanelInput6.add(ChkInput6, java.awt.BorderLayout.PAGE_END);

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass18.setLayout(null);

        jLabel128.setText("Posisi Penderita:");
        jLabel128.setName("jLabel128"); // NOI18N
        panelGlass18.add(jLabel128);
        jLabel128.setBounds(10, 10, 80, 23);

        Posisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supine", "Prone", "Litotomi", "Semi sitting", "Fower", "Lateral dekubitus (R/L)", "Trendelenburg", "Knee chest", "Jack knife" }));
        Posisi.setName("Posisi"); // NOI18N
        panelGlass18.add(Posisi);
        Posisi.setBounds(100, 10, 140, 20);

        jLabel129.setText("Airway:");
        jLabel129.setName("jLabel129"); // NOI18N
        panelGlass18.add(jLabel129);
        jLabel129.setBounds(260, 10, 50, 23);

        Airway2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Face mask", "LMA", "Single lumen spiral ETT", "Double Lumen ETT (R/L)" }));
        Airway2.setName("Airway2"); // NOI18N
        panelGlass18.add(Airway2);
        Airway2.setBounds(320, 10, 150, 20);

        jLabel130.setText("Posisi ETT:");
        jLabel130.setName("jLabel130"); // NOI18N
        panelGlass18.add(jLabel130);
        jLabel130.setBounds(480, 10, 70, 23);

        Ett.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sesuai pada tempatnya", "Tidak pada tempatnya" }));
        Ett.setName("Ett"); // NOI18N
        panelGlass18.add(Ett);
        Ett.setBounds(550, 10, 120, 20);

        jLabel131.setText("Ukuran:");
        jLabel131.setName("jLabel131"); // NOI18N
        panelGlass18.add(jLabel131);
        jLabel131.setBounds(20, 40, 80, 23);

        Ukuran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Balon", "Tanpa balon" }));
        Ukuran.setName("Ukuran"); // NOI18N
        panelGlass18.add(Ukuran);
        Ukuran.setBounds(100, 40, 110, 20);

        jLabel132.setText("Komplikasi selama operasi/ anestesi:");
        jLabel132.setName("jLabel132"); // NOI18N
        panelGlass18.add(jLabel132);
        jLabel132.setBounds(230, 40, 190, 23);

        Komplikasi.setFocusTraversalPolicyProvider(true);
        Komplikasi.setName("Komplikasi"); // NOI18N
        Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKeyPressed(evt);
            }
        });
        panelGlass18.add(Komplikasi);
        Komplikasi.setBounds(430, 40, 250, 23);

        jLabel133.setText("Tindakan:");
        jLabel133.setName("jLabel133"); // NOI18N
        panelGlass18.add(jLabel133);
        jLabel133.setBounds(30, 70, 70, 23);

        Tindakan.setFocusTraversalPolicyProvider(true);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        panelGlass18.add(Tindakan);
        Tindakan.setBounds(110, 70, 570, 23);

        label23.setText("Pegawai:");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass18.add(label23);
        label23.setBounds(20, 100, 80, 23);

        NIP6.setEditable(false);
        NIP6.setName("NIP6"); // NOI18N
        NIP6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass18.add(NIP6);
        NIP6.setBounds(110, 100, 90, 23);

        NamaPegawai6.setEditable(false);
        NamaPegawai6.setName("NamaPegawai6"); // NOI18N
        NamaPegawai6.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass18.add(NamaPegawai6);
        NamaPegawai6.setBounds(210, 100, 180, 23);

        BtnPegawai4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai4.setMnemonic('2');
        BtnPegawai4.setToolTipText("Alt+2");
        BtnPegawai4.setName("BtnPegawai4"); // NOI18N
        BtnPegawai4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai4ActionPerformed(evt);
            }
        });
        BtnPegawai4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai4KeyPressed(evt);
            }
        });
        panelGlass18.add(BtnPegawai4);
        BtnPegawai4.setBounds(390, 100, 28, 23);

        PanelInput6.add(panelGlass18, java.awt.BorderLayout.CENTER);

        internalFrame6.add(PanelInput6, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("E. Selama Operasi", internalFrame6);

        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        tbAnestesi9.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi9.setComponentPopupMenu(jPopupMenu2);
        tbAnestesi9.setName("tbAnestesi9"); // NOI18N
        tbAnestesi9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi9MouseClicked(evt);
            }
        });
        tbAnestesi9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi9KeyReleased(evt);
            }
        });
        Scroll20.setViewportView(tbAnestesi9);

        internalFrame7.add(Scroll20, java.awt.BorderLayout.CENTER);

        PanelInput7.setName("PanelInput7"); // NOI18N
        PanelInput7.setOpaque(false);
        PanelInput7.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput7.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput7.setMnemonic('I');
        ChkInput7.setText(".: Input Data");
        ChkInput7.setToolTipText("Alt+I");
        ChkInput7.setBorderPainted(true);
        ChkInput7.setBorderPaintedFlat(true);
        ChkInput7.setFocusable(false);
        ChkInput7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput7.setName("ChkInput7"); // NOI18N
        ChkInput7.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput7ActionPerformed(evt);
            }
        });
        PanelInput7.add(ChkInput7, java.awt.BorderLayout.PAGE_END);

        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass19.setLayout(null);

        jLabel89.setText("O2:");
        jLabel89.setName("jLabel89"); // NOI18N
        panelGlass19.add(jLabel89);
        jLabel89.setBounds(20, 10, 20, 23);

        O22.setFocusTraversalPolicyProvider(true);
        O22.setName("O22"); // NOI18N
        O22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                O22KeyPressed(evt);
            }
        });
        panelGlass19.add(O22);
        O22.setBounds(50, 10, 100, 23);

        jLabel99.setText("N2O:");
        jLabel99.setName("jLabel99"); // NOI18N
        panelGlass19.add(jLabel99);
        jLabel99.setBounds(160, 10, 40, 23);

        N2o2.setFocusTraversalPolicyProvider(true);
        N2o2.setName("N2o2"); // NOI18N
        N2o2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                N2o2KeyPressed(evt);
            }
        });
        panelGlass19.add(N2o2);
        N2o2.setBounds(210, 10, 100, 23);

        jLabel100.setText("AIR:");
        jLabel100.setName("jLabel100"); // NOI18N
        panelGlass19.add(jLabel100);
        jLabel100.setBounds(330, 10, 30, 23);

        Air.setFocusTraversalPolicyProvider(true);
        Air.setName("Air"); // NOI18N
        Air.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AirKeyPressed(evt);
            }
        });
        panelGlass19.add(Air);
        Air.setBounds(370, 10, 100, 23);

        jLabel162.setText("Halothane:");
        jLabel162.setName("jLabel162"); // NOI18N
        panelGlass19.add(jLabel162);
        jLabel162.setBounds(480, 10, 70, 23);

        Halothane.setFocusTraversalPolicyProvider(true);
        Halothane.setName("Halothane"); // NOI18N
        Halothane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HalothaneKeyPressed(evt);
            }
        });
        panelGlass19.add(Halothane);
        Halothane.setBounds(560, 10, 100, 23);

        jLabel163.setText("Isoflurane:");
        jLabel163.setName("jLabel163"); // NOI18N
        panelGlass19.add(jLabel163);
        jLabel163.setBounds(670, 10, 70, 23);

        Isoflurane.setFocusTraversalPolicyProvider(true);
        Isoflurane.setName("Isoflurane"); // NOI18N
        Isoflurane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IsofluraneKeyPressed(evt);
            }
        });
        panelGlass19.add(Isoflurane);
        Isoflurane.setBounds(750, 10, 100, 23);

        jLabel164.setText("Sevoflurane:");
        jLabel164.setName("jLabel164"); // NOI18N
        panelGlass19.add(jLabel164);
        jLabel164.setBounds(0, 40, 70, 23);

        Sevo.setFocusTraversalPolicyProvider(true);
        Sevo.setName("Sevo"); // NOI18N
        Sevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SevoKeyPressed(evt);
            }
        });
        panelGlass19.add(Sevo);
        Sevo.setBounds(80, 40, 100, 23);

        jLabel165.setText("DML:");
        jLabel165.setName("jLabel165"); // NOI18N
        panelGlass19.add(jLabel165);
        jLabel165.setBounds(190, 40, 70, 23);

        Dml.setFocusTraversalPolicyProvider(true);
        Dml.setName("Dml"); // NOI18N
        Dml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DmlKeyPressed(evt);
            }
        });
        panelGlass19.add(Dml);
        Dml.setBounds(270, 40, 100, 23);

        jLabel166.setText("tVS:");
        jLabel166.setName("jLabel166"); // NOI18N
        panelGlass19.add(jLabel166);
        jLabel166.setBounds(390, 40, 70, 23);

        Tvs.setFocusTraversalPolicyProvider(true);
        Tvs.setName("Tvs"); // NOI18N
        Tvs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TvsKeyPressed(evt);
            }
        });
        panelGlass19.add(Tvs);
        Tvs.setBounds(470, 40, 100, 23);

        jLabel167.setText("Nadi:");
        jLabel167.setName("jLabel167"); // NOI18N
        panelGlass19.add(jLabel167);
        jLabel167.setBounds(590, 40, 70, 23);

        Nadi_1.setFocusTraversalPolicyProvider(true);
        Nadi_1.setName("Nadi_1"); // NOI18N
        Nadi_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi_1KeyPressed(evt);
            }
        });
        panelGlass19.add(Nadi_1);
        Nadi_1.setBounds(670, 40, 100, 23);

        jLabel168.setText("Tensi Darah:");
        jLabel168.setName("jLabel168"); // NOI18N
        panelGlass19.add(jLabel168);
        jLabel168.setBounds(780, 40, 70, 23);

        Sistolik.setFocusTraversalPolicyProvider(true);
        Sistolik.setName("Sistolik"); // NOI18N
        Sistolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SistolikKeyPressed(evt);
            }
        });
        panelGlass19.add(Sistolik);
        Sistolik.setBounds(860, 40, 100, 23);

        label25.setText("Pegawai:");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass19.add(label25);
        label25.setBounds(20, 70, 80, 23);

        NIP7.setEditable(false);
        NIP7.setName("NIP7"); // NOI18N
        NIP7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass19.add(NIP7);
        NIP7.setBounds(110, 70, 90, 23);

        NamaPegawai7.setEditable(false);
        NamaPegawai7.setName("NamaPegawai7"); // NOI18N
        NamaPegawai7.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass19.add(NamaPegawai7);
        NamaPegawai7.setBounds(210, 70, 180, 23);

        BtnPegawai5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai5.setMnemonic('2');
        BtnPegawai5.setToolTipText("Alt+2");
        BtnPegawai5.setName("BtnPegawai5"); // NOI18N
        BtnPegawai5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai5ActionPerformed(evt);
            }
        });
        BtnPegawai5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai5KeyPressed(evt);
            }
        });
        panelGlass19.add(BtnPegawai5);
        BtnPegawai5.setBounds(390, 70, 28, 23);

        Diastolik.setFocusTraversalPolicyProvider(true);
        Diastolik.setName("Diastolik"); // NOI18N
        Diastolik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiastolikKeyPressed(evt);
            }
        });
        panelGlass19.add(Diastolik);
        Diastolik.setBounds(970, 40, 100, 23);

        PanelInput7.add(panelGlass19, java.awt.BorderLayout.CENTER);

        internalFrame7.add(PanelInput7, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Monitoring", internalFrame7);

        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        tbAnestesi10.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi10.setComponentPopupMenu(jPopupMenu3);
        tbAnestesi10.setName("tbAnestesi10"); // NOI18N
        tbAnestesi10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi10MouseClicked(evt);
            }
        });
        tbAnestesi10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi10KeyReleased(evt);
            }
        });
        Scroll21.setViewportView(tbAnestesi10);

        internalFrame11.add(Scroll21, java.awt.BorderLayout.CENTER);

        PanelInput8.setName("PanelInput8"); // NOI18N
        PanelInput8.setOpaque(false);
        PanelInput8.setPreferredSize(new java.awt.Dimension(192, 100));
        PanelInput8.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput8.setMnemonic('I');
        ChkInput8.setText(".: Input Data");
        ChkInput8.setToolTipText("Alt+I");
        ChkInput8.setBorderPainted(true);
        ChkInput8.setBorderPaintedFlat(true);
        ChkInput8.setFocusable(false);
        ChkInput8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput8.setName("ChkInput8"); // NOI18N
        ChkInput8.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput8.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput8ActionPerformed(evt);
            }
        });
        PanelInput8.add(ChkInput8, java.awt.BorderLayout.PAGE_END);

        panelGlass20.setName("panelGlass20"); // NOI18N
        panelGlass20.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass20.setLayout(null);

        jLabel90.setText("SPO2:");
        jLabel90.setName("jLabel90"); // NOI18N
        panelGlass20.add(jLabel90);
        jLabel90.setBounds(10, 10, 30, 23);

        Monitoring2_spo2.setFocusTraversalPolicyProvider(true);
        Monitoring2_spo2.setName("Monitoring2_spo2"); // NOI18N
        Monitoring2_spo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring2_spo2KeyPressed(evt);
            }
        });
        panelGlass20.add(Monitoring2_spo2);
        Monitoring2_spo2.setBounds(50, 10, 100, 23);

        jLabel115.setText("ETCO2:");
        jLabel115.setName("jLabel115"); // NOI18N
        panelGlass20.add(jLabel115);
        jLabel115.setBounds(160, 10, 40, 23);

        Monitoring2_etco2.setFocusTraversalPolicyProvider(true);
        Monitoring2_etco2.setName("Monitoring2_etco2"); // NOI18N
        Monitoring2_etco2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring2_etco2KeyPressed(evt);
            }
        });
        panelGlass20.add(Monitoring2_etco2);
        Monitoring2_etco2.setBounds(210, 10, 100, 23);

        jLabel169.setText("FIO2:");
        jLabel169.setName("jLabel169"); // NOI18N
        panelGlass20.add(jLabel169);
        jLabel169.setBounds(330, 10, 30, 23);

        Monitoring2_fio2.setFocusTraversalPolicyProvider(true);
        Monitoring2_fio2.setName("Monitoring2_fio2"); // NOI18N
        Monitoring2_fio2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monitoring2_fio2KeyPressed(evt);
            }
        });
        panelGlass20.add(Monitoring2_fio2);
        Monitoring2_fio2.setBounds(370, 10, 100, 23);

        jLabel170.setText("Cairan:");
        jLabel170.setName("jLabel170"); // NOI18N
        panelGlass20.add(jLabel170);
        jLabel170.setBounds(480, 10, 70, 23);

        Cairan.setFocusTraversalPolicyProvider(true);
        Cairan.setName("Cairan"); // NOI18N
        Cairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CairanKeyPressed(evt);
            }
        });
        panelGlass20.add(Cairan);
        Cairan.setBounds(560, 10, 100, 23);

        jLabel171.setText("Urine:");
        jLabel171.setName("jLabel171"); // NOI18N
        panelGlass20.add(jLabel171);
        jLabel171.setBounds(670, 10, 70, 23);

        Urine.setFocusTraversalPolicyProvider(true);
        Urine.setName("Urine"); // NOI18N
        Urine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UrineKeyPressed(evt);
            }
        });
        panelGlass20.add(Urine);
        Urine.setBounds(750, 10, 100, 23);

        jLabel172.setText("Perdarahan:");
        jLabel172.setName("jLabel172"); // NOI18N
        panelGlass20.add(jLabel172);
        jLabel172.setBounds(0, 40, 70, 23);

        Perdarahan.setFocusTraversalPolicyProvider(true);
        Perdarahan.setName("Perdarahan"); // NOI18N
        Perdarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerdarahanKeyPressed(evt);
            }
        });
        panelGlass20.add(Perdarahan);
        Perdarahan.setBounds(80, 40, 100, 23);

        label26.setText("Pegawai:");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass20.add(label26);
        label26.setBounds(190, 40, 80, 23);

        NIP8.setEditable(false);
        NIP8.setName("NIP8"); // NOI18N
        NIP8.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass20.add(NIP8);
        NIP8.setBounds(280, 40, 90, 23);

        NamaPegawai8.setEditable(false);
        NamaPegawai8.setName("NamaPegawai8"); // NOI18N
        NamaPegawai8.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass20.add(NamaPegawai8);
        NamaPegawai8.setBounds(380, 40, 180, 23);

        BtnPegawai6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai6.setMnemonic('2');
        BtnPegawai6.setToolTipText("Alt+2");
        BtnPegawai6.setName("BtnPegawai6"); // NOI18N
        BtnPegawai6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai6ActionPerformed(evt);
            }
        });
        BtnPegawai6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai6KeyPressed(evt);
            }
        });
        panelGlass20.add(BtnPegawai6);
        BtnPegawai6.setBounds(560, 40, 28, 23);

        PanelInput8.add(panelGlass20, java.awt.BorderLayout.CENTER);

        internalFrame11.add(PanelInput8, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Monitoring 2", internalFrame11);

        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        tbAnestesi11.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi11.setName("tbAnestesi11"); // NOI18N
        tbAnestesi11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi11MouseClicked(evt);
            }
        });
        tbAnestesi11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi11KeyReleased(evt);
            }
        });
        Scroll22.setViewportView(tbAnestesi11);

        internalFrame8.add(Scroll22, java.awt.BorderLayout.CENTER);

        PanelInput9.setName("PanelInput9"); // NOI18N
        PanelInput9.setOpaque(false);
        PanelInput9.setPreferredSize(new java.awt.Dimension(192, 200));
        PanelInput9.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput9.setMnemonic('I');
        ChkInput9.setText(".: Input Data");
        ChkInput9.setToolTipText("Alt+I");
        ChkInput9.setBorderPainted(true);
        ChkInput9.setBorderPaintedFlat(true);
        ChkInput9.setFocusable(false);
        ChkInput9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput9.setName("ChkInput9"); // NOI18N
        ChkInput9.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput9.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput9ActionPerformed(evt);
            }
        });
        PanelInput9.add(ChkInput9, java.awt.BorderLayout.PAGE_END);

        panelGlass21.setName("panelGlass21"); // NOI18N
        panelGlass21.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass21.setLayout(null);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Total Asupan Cairan");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setName("jLabel116"); // NOI18N
        panelGlass21.add(jLabel116);
        jLabel116.setBounds(10, 10, 160, 23);

        jLabel92.setText("ml");
        jLabel92.setName("jLabel92"); // NOI18N
        panelGlass21.add(jLabel92);
        jLabel92.setBounds(230, 40, 20, 23);

        Kristaloid.setFocusTraversalPolicyProvider(true);
        Kristaloid.setName("Kristaloid"); // NOI18N
        Kristaloid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KristaloidKeyPressed(evt);
            }
        });
        panelGlass21.add(Kristaloid);
        Kristaloid.setBounds(100, 40, 130, 23);

        jLabel114.setText("Kristaloid:");
        jLabel114.setName("jLabel114"); // NOI18N
        panelGlass21.add(jLabel114);
        jLabel114.setBounds(10, 40, 90, 23);

        jLabel134.setText("Koloid:");
        jLabel134.setName("jLabel134"); // NOI18N
        panelGlass21.add(jLabel134);
        jLabel134.setBounds(10, 70, 90, 23);

        Koloid.setFocusTraversalPolicyProvider(true);
        Koloid.setName("Koloid"); // NOI18N
        Koloid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KoloidKeyPressed(evt);
            }
        });
        panelGlass21.add(Koloid);
        Koloid.setBounds(100, 70, 130, 23);

        jLabel135.setText("ml");
        jLabel135.setName("jLabel135"); // NOI18N
        panelGlass21.add(jLabel135);
        jLabel135.setBounds(230, 70, 20, 23);

        jLabel136.setText("Darah:");
        jLabel136.setName("jLabel136"); // NOI18N
        panelGlass21.add(jLabel136);
        jLabel136.setBounds(10, 100, 90, 23);

        Darah.setFocusTraversalPolicyProvider(true);
        Darah.setName("Darah"); // NOI18N
        Darah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DarahKeyPressed(evt);
            }
        });
        panelGlass21.add(Darah);
        Darah.setBounds(100, 100, 130, 23);

        jLabel137.setText("ml");
        jLabel137.setName("jLabel137"); // NOI18N
        panelGlass21.add(jLabel137);
        jLabel137.setBounds(230, 100, 20, 23);

        jLabel138.setText("Komponen Darah:");
        jLabel138.setName("jLabel138"); // NOI18N
        panelGlass21.add(jLabel138);
        jLabel138.setBounds(10, 130, 90, 23);

        KomponenDarah.setFocusTraversalPolicyProvider(true);
        KomponenDarah.setName("KomponenDarah"); // NOI18N
        KomponenDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomponenDarahKeyPressed(evt);
            }
        });
        panelGlass21.add(KomponenDarah);
        KomponenDarah.setBounds(100, 130, 130, 23);

        jLabel139.setText("ml");
        jLabel139.setName("jLabel139"); // NOI18N
        panelGlass21.add(jLabel139);
        jLabel139.setBounds(230, 130, 20, 23);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("Total Keluaran Cairan:");
        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel140.setName("jLabel140"); // NOI18N
        panelGlass21.add(jLabel140);
        jLabel140.setBounds(280, 10, 160, 23);

        jLabel141.setText("ml");
        jLabel141.setName("jLabel141"); // NOI18N
        panelGlass21.add(jLabel141);
        jLabel141.setBounds(500, 40, 20, 23);

        Perdarahan2.setFocusTraversalPolicyProvider(true);
        Perdarahan2.setName("Perdarahan2"); // NOI18N
        Perdarahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Perdarahan2KeyPressed(evt);
            }
        });
        panelGlass21.add(Perdarahan2);
        Perdarahan2.setBounds(370, 40, 130, 23);

        jLabel142.setText("Perdarahan (EBL):");
        jLabel142.setName("jLabel142"); // NOI18N
        panelGlass21.add(jLabel142);
        jLabel142.setBounds(280, 40, 90, 23);

        jLabel143.setText("Diuresis:");
        jLabel143.setName("jLabel143"); // NOI18N
        panelGlass21.add(jLabel143);
        jLabel143.setBounds(280, 70, 90, 23);

        Diuresis.setFocusTraversalPolicyProvider(true);
        Diuresis.setName("Diuresis"); // NOI18N
        Diuresis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiuresisKeyPressed(evt);
            }
        });
        panelGlass21.add(Diuresis);
        Diuresis.setBounds(370, 70, 130, 23);

        jLabel144.setText("ml");
        jLabel144.setName("jLabel144"); // NOI18N
        panelGlass21.add(jLabel144);
        jLabel144.setBounds(500, 70, 20, 23);

        jLabel145.setText("Cairan Lain:");
        jLabel145.setName("jLabel145"); // NOI18N
        panelGlass21.add(jLabel145);
        jLabel145.setBounds(280, 100, 90, 23);

        CairanLain.setFocusTraversalPolicyProvider(true);
        CairanLain.setName("CairanLain"); // NOI18N
        CairanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CairanLainKeyPressed(evt);
            }
        });
        panelGlass21.add(CairanLain);
        CairanLain.setBounds(370, 100, 130, 23);

        jLabel146.setText("ml");
        jLabel146.setName("jLabel146"); // NOI18N
        panelGlass21.add(jLabel146);
        jLabel146.setBounds(500, 100, 20, 23);

        label27.setText("Pegawai:");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass21.add(label27);
        label27.setBounds(280, 130, 80, 23);

        NIP9.setEditable(false);
        NIP9.setName("NIP9"); // NOI18N
        NIP9.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass21.add(NIP9);
        NIP9.setBounds(370, 130, 90, 23);

        NamaPegawai9.setEditable(false);
        NamaPegawai9.setName("NamaPegawai9"); // NOI18N
        NamaPegawai9.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass21.add(NamaPegawai9);
        NamaPegawai9.setBounds(470, 130, 180, 23);

        BtnPegawai7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai7.setMnemonic('2');
        BtnPegawai7.setToolTipText("Alt+2");
        BtnPegawai7.setName("BtnPegawai7"); // NOI18N
        BtnPegawai7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai7ActionPerformed(evt);
            }
        });
        BtnPegawai7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai7KeyPressed(evt);
            }
        });
        panelGlass21.add(BtnPegawai7);
        BtnPegawai7.setBounds(650, 130, 28, 23);

        jLabel174.setText("Urine:");
        jLabel174.setName("jLabel174"); // NOI18N
        panelGlass21.add(jLabel174);
        jLabel174.setBounds(420, 10, 90, 23);

        Urin.setFocusTraversalPolicyProvider(true);
        Urin.setName("Urin"); // NOI18N
        Urin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UrinKeyPressed(evt);
            }
        });
        panelGlass21.add(Urin);
        Urin.setBounds(510, 10, 130, 23);

        jLabel175.setText("ml");
        jLabel175.setName("jLabel175"); // NOI18N
        panelGlass21.add(jLabel175);
        jLabel175.setBounds(640, 10, 20, 23);

        PanelInput9.add(panelGlass21, java.awt.BorderLayout.CENTER);

        internalFrame8.add(PanelInput9, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Cairan", internalFrame8);

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput10.setName("PanelInput10"); // NOI18N
        PanelInput10.setOpaque(false);
        PanelInput10.setPreferredSize(new java.awt.Dimension(192, 200));
        PanelInput10.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput10.setMnemonic('I');
        ChkInput10.setText(".: Input Data");
        ChkInput10.setToolTipText("Alt+I");
        ChkInput10.setBorderPainted(true);
        ChkInput10.setBorderPaintedFlat(true);
        ChkInput10.setFocusable(false);
        ChkInput10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput10.setName("ChkInput10"); // NOI18N
        ChkInput10.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput10.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput10.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput10ActionPerformed(evt);
            }
        });
        PanelInput10.add(ChkInput10, java.awt.BorderLayout.PAGE_END);

        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass22.setLayout(null);

        Jk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L", "P" }));
        Jk2.setName("Jk2"); // NOI18N
        panelGlass22.add(Jk2);
        Jk2.setBounds(190, 10, 40, 20);

        jLabel95.setText("JK:");
        jLabel95.setName("jLabel95"); // NOI18N
        panelGlass22.add(jLabel95);
        jLabel95.setBounds(160, 10, 30, 23);

        Apgar1.setFocusTraversalPolicyProvider(true);
        Apgar1.setName("Apgar1"); // NOI18N
        Apgar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Apgar1KeyPressed(evt);
            }
        });
        panelGlass22.add(Apgar1);
        Apgar1.setBounds(370, 10, 80, 23);

        jLabel96.setText("APGAR Score 1 Menit:");
        jLabel96.setName("jLabel96"); // NOI18N
        panelGlass22.add(jLabel96);
        jLabel96.setBounds(250, 10, 110, 23);

        jLabel97.setText("Keadaan bayi:");
        jLabel97.setName("jLabel97"); // NOI18N
        panelGlass22.add(jLabel97);
        jLabel97.setBounds(10, 10, 80, 23);

        KeadaanBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hidup", "Mati" }));
        KeadaanBayi.setName("KeadaanBayi"); // NOI18N
        panelGlass22.add(KeadaanBayi);
        KeadaanBayi.setBounds(90, 10, 60, 20);

        Apgar5.setFocusTraversalPolicyProvider(true);
        Apgar5.setName("Apgar5"); // NOI18N
        Apgar5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Apgar5KeyPressed(evt);
            }
        });
        panelGlass22.add(Apgar5);
        Apgar5.setBounds(580, 10, 80, 23);

        jLabel117.setText("APGAR Score 5 Menit:");
        jLabel117.setName("jLabel117"); // NOI18N
        panelGlass22.add(jLabel117);
        jLabel117.setBounds(460, 10, 110, 23);

        jLabel147.setText("Berat Badan:");
        jLabel147.setName("jLabel147"); // NOI18N
        panelGlass22.add(jLabel147);
        jLabel147.setBounds(250, 40, 110, 23);

        Bb2.setFocusTraversalPolicyProvider(true);
        Bb2.setName("Bb2"); // NOI18N
        Bb2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Bb2KeyPressed(evt);
            }
        });
        panelGlass22.add(Bb2);
        Bb2.setBounds(370, 40, 80, 23);

        jLabel148.setText("Panjang Badan:");
        jLabel148.setName("jLabel148"); // NOI18N
        panelGlass22.add(jLabel148);
        jLabel148.setBounds(460, 40, 110, 23);

        Pb2.setFocusTraversalPolicyProvider(true);
        Pb2.setName("Pb2"); // NOI18N
        Pb2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pb2KeyPressed(evt);
            }
        });
        panelGlass22.add(Pb2);
        Pb2.setBounds(580, 40, 80, 23);

        label28.setText("Pegawai:");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass22.add(label28);
        label28.setBounds(20, 70, 80, 23);

        NIP10.setEditable(false);
        NIP10.setName("NIP10"); // NOI18N
        NIP10.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass22.add(NIP10);
        NIP10.setBounds(110, 70, 90, 23);

        NamaPegawai10.setEditable(false);
        NamaPegawai10.setName("NamaPegawai10"); // NOI18N
        NamaPegawai10.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass22.add(NamaPegawai10);
        NamaPegawai10.setBounds(210, 70, 180, 23);

        BtnPegawai8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai8.setMnemonic('2');
        BtnPegawai8.setToolTipText("Alt+2");
        BtnPegawai8.setName("BtnPegawai8"); // NOI18N
        BtnPegawai8.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai8ActionPerformed(evt);
            }
        });
        BtnPegawai8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai8KeyPressed(evt);
            }
        });
        panelGlass22.add(BtnPegawai8);
        BtnPegawai8.setBounds(390, 70, 28, 23);

        PanelInput10.add(panelGlass22, java.awt.BorderLayout.CENTER);

        internalFrame9.add(PanelInput10, java.awt.BorderLayout.PAGE_START);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        tbAnestesi12.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi12.setName("tbAnestesi12"); // NOI18N
        tbAnestesi12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi12MouseClicked(evt);
            }
        });
        tbAnestesi12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi12KeyReleased(evt);
            }
        });
        Scroll23.setViewportView(tbAnestesi12);

        internalFrame9.add(Scroll23, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("F. Sectio Sesar", internalFrame9);

        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput11.setName("PanelInput11"); // NOI18N
        PanelInput11.setOpaque(false);
        PanelInput11.setPreferredSize(new java.awt.Dimension(192, 230));
        PanelInput11.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput11.setMnemonic('I');
        ChkInput11.setText(".: Input Data");
        ChkInput11.setToolTipText("Alt+I");
        ChkInput11.setBorderPainted(true);
        ChkInput11.setBorderPaintedFlat(true);
        ChkInput11.setFocusable(false);
        ChkInput11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput11.setName("ChkInput11"); // NOI18N
        ChkInput11.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput11.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput11.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput11ActionPerformed(evt);
            }
        });
        PanelInput11.add(ChkInput11, java.awt.BorderLayout.PAGE_END);

        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass23.setLayout(null);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("Pindah Ke:");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        panelGlass23.add(jLabel118);
        jLabel118.setBounds(530, 10, 100, 23);

        Pindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PACU", "GICU", "ICCU", "PICU", "NICU", "Rawat Inap" }));
        Pindah.setName("Pindah"); // NOI18N
        panelGlass23.add(Pindah);
        Pindah.setBounds(640, 10, 110, 20);

        jLabel98.setText("Keadaan Umum:");
        jLabel98.setName("jLabel98"); // NOI18N
        panelGlass23.add(jLabel98);
        jLabel98.setBounds(760, 10, 90, 23);

        KeadaaanUmum.setFocusTraversalPolicyProvider(true);
        KeadaaanUmum.setName("KeadaaanUmum"); // NOI18N
        KeadaaanUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaaanUmumKeyPressed(evt);
            }
        });
        panelGlass23.add(KeadaaanUmum);
        KeadaaanUmum.setBounds(860, 10, 250, 23);

        jLabel86.setText("Nadi :");
        jLabel86.setName("jLabel86"); // NOI18N
        panelGlass23.add(jLabel86);
        jLabel86.setBounds(10, 40, 40, 23);

        Nadi2.setFocusTraversalPolicyProvider(true);
        Nadi2.setName("Nadi2"); // NOI18N
        Nadi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nadi2ActionPerformed(evt);
            }
        });
        Nadi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi2KeyPressed(evt);
            }
        });
        panelGlass23.add(Nadi2);
        Nadi2.setBounds(60, 40, 55, 23);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("x/menit");
        jLabel87.setName("jLabel87"); // NOI18N
        panelGlass23.add(jLabel87);
        jLabel87.setBounds(120, 40, 50, 23);

        jLabel88.setText("RR:");
        jLabel88.setName("jLabel88"); // NOI18N
        panelGlass23.add(jLabel88);
        jLabel88.setBounds(160, 40, 30, 23);

        RR2.setFocusTraversalPolicyProvider(true);
        RR2.setName("RR2"); // NOI18N
        RR2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RR2KeyPressed(evt);
            }
        });
        panelGlass23.add(RR2);
        RR2.setBounds(200, 40, 55, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("x/menit");
        jLabel93.setName("jLabel93"); // NOI18N
        panelGlass23.add(jLabel93);
        jLabel93.setBounds(260, 40, 50, 23);

        jLabel94.setText("Suhu:");
        jLabel94.setName("jLabel94"); // NOI18N
        panelGlass23.add(jLabel94);
        jLabel94.setBounds(30, 70, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        panelGlass23.add(Suhu);
        Suhu.setBounds(70, 70, 70, 23);

        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel149.setText("C");
        jLabel149.setName("jLabel149"); // NOI18N
        panelGlass23.add(jLabel149);
        jLabel149.setBounds(150, 70, 50, 23);

        jLabel150.setText("SpO2:");
        jLabel150.setName("jLabel150"); // NOI18N
        panelGlass23.add(jLabel150);
        jLabel150.setBounds(200, 70, 40, 23);

        Pasca_Spo2.setFocusTraversalPolicyProvider(true);
        Pasca_Spo2.setName("Pasca_Spo2"); // NOI18N
        Pasca_Spo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pasca_Spo2KeyPressed(evt);
            }
        });
        panelGlass23.add(Pasca_Spo2);
        Pasca_Spo2.setBounds(240, 70, 70, 23);

        jLabel151.setText("Trigger:");
        jLabel151.setName("jLabel151"); // NOI18N
        panelGlass23.add(jLabel151);
        jLabel151.setBounds(20, 160, 70, 23);

        Trigger.setFocusTraversalPolicyProvider(true);
        Trigger.setName("Trigger"); // NOI18N
        Trigger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriggerKeyPressed(evt);
            }
        });
        panelGlass23.add(Trigger);
        Trigger.setBounds(100, 160, 410, 23);

        jLabel152.setText("Vetilatior Mode:");
        jLabel152.setName("jLabel152"); // NOI18N
        panelGlass23.add(jLabel152);
        jLabel152.setBounds(20, 100, 100, 23);

        Vetilator.setFocusTraversalPolicyProvider(true);
        Vetilator.setName("Vetilator"); // NOI18N
        Vetilator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VetilatorKeyPressed(evt);
            }
        });
        panelGlass23.add(Vetilator);
        Vetilator.setBounds(120, 100, 250, 23);

        jLabel153.setText("Tidal Volume:");
        jLabel153.setName("jLabel153"); // NOI18N
        panelGlass23.add(jLabel153);
        jLabel153.setBounds(370, 100, 90, 23);

        TidalVolume.setFocusTraversalPolicyProvider(true);
        TidalVolume.setName("TidalVolume"); // NOI18N
        TidalVolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TidalVolumeKeyPressed(evt);
            }
        });
        panelGlass23.add(TidalVolume);
        TidalVolume.setBounds(470, 100, 250, 23);

        jLabel154.setText("MLRR:");
        jLabel154.setName("jLabel154"); // NOI18N
        panelGlass23.add(jLabel154);
        jLabel154.setBounds(730, 100, 40, 23);

        Mlrr.setFocusTraversalPolicyProvider(true);
        Mlrr.setName("Mlrr"); // NOI18N
        Mlrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MlrrActionPerformed(evt);
            }
        });
        Mlrr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MlrrKeyPressed(evt);
            }
        });
        panelGlass23.add(Mlrr);
        Mlrr.setBounds(780, 100, 90, 23);

        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("x/menit");
        jLabel155.setName("jLabel155"); // NOI18N
        panelGlass23.add(jLabel155);
        jLabel155.setBounds(870, 100, 50, 23);

        jLabel156.setText("I:E Ratio:");
        jLabel156.setName("jLabel156"); // NOI18N
        panelGlass23.add(jLabel156);
        jLabel156.setBounds(20, 130, 90, 23);

        Ieratio.setFocusTraversalPolicyProvider(true);
        Ieratio.setName("Ieratio"); // NOI18N
        Ieratio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IeratioKeyPressed(evt);
            }
        });
        panelGlass23.add(Ieratio);
        Ieratio.setBounds(120, 130, 140, 23);

        jLabel157.setText("PEEP:");
        jLabel157.setName("jLabel157"); // NOI18N
        panelGlass23.add(jLabel157);
        jLabel157.setBounds(270, 130, 40, 23);

        Peep2.setFocusTraversalPolicyProvider(true);
        Peep2.setName("Peep2"); // NOI18N
        Peep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peep2ActionPerformed(evt);
            }
        });
        Peep2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Peep2KeyPressed(evt);
            }
        });
        panelGlass23.add(Peep2);
        Peep2.setBounds(310, 130, 90, 23);

        jLabel158.setText("PIP:");
        jLabel158.setName("jLabel158"); // NOI18N
        panelGlass23.add(jLabel158);
        jLabel158.setBounds(410, 130, 40, 23);

        Pip2.setFocusTraversalPolicyProvider(true);
        Pip2.setName("Pip2"); // NOI18N
        Pip2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pip2ActionPerformed(evt);
            }
        });
        Pip2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pip2KeyPressed(evt);
            }
        });
        panelGlass23.add(Pip2);
        Pip2.setBounds(450, 130, 90, 23);

        jLabel159.setText("PS:");
        jLabel159.setName("jLabel159"); // NOI18N
        panelGlass23.add(jLabel159);
        jLabel159.setBounds(550, 130, 40, 23);

        Ps.setFocusTraversalPolicyProvider(true);
        Ps.setName("Ps"); // NOI18N
        Ps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PsActionPerformed(evt);
            }
        });
        Ps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PsKeyPressed(evt);
            }
        });
        panelGlass23.add(Ps);
        Ps.setBounds(590, 130, 90, 23);

        jLabel160.setText("Fi02:");
        jLabel160.setName("jLabel160"); // NOI18N
        panelGlass23.add(jLabel160);
        jLabel160.setBounds(700, 130, 40, 23);

        Fio2.setFocusTraversalPolicyProvider(true);
        Fio2.setName("Fio2"); // NOI18N
        Fio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fio2ActionPerformed(evt);
            }
        });
        Fio2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Fio2KeyPressed(evt);
            }
        });
        panelGlass23.add(Fio2);
        Fio2.setBounds(740, 130, 90, 23);

        jLabel161.setText("Instruksi Pasca Operasi/ Anestesi:");
        jLabel161.setName("jLabel161"); // NOI18N
        panelGlass23.add(jLabel161);
        jLabel161.setBounds(320, 70, 180, 23);

        Instruksi.setFocusTraversalPolicyProvider(true);
        Instruksi.setName("Instruksi"); // NOI18N
        Instruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InstruksiKeyPressed(evt);
            }
        });
        panelGlass23.add(Instruksi);
        Instruksi.setBounds(510, 70, 410, 23);

        jLabel173.setText("Diagnosa Post Op:");
        jLabel173.setName("jLabel173"); // NOI18N
        panelGlass23.add(jLabel173);
        jLabel173.setBounds(0, 10, 100, 23);

        DiagnosaPostOp.setFocusTraversalPolicyProvider(true);
        DiagnosaPostOp.setName("DiagnosaPostOp"); // NOI18N
        DiagnosaPostOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaPostOpKeyPressed(evt);
            }
        });
        panelGlass23.add(DiagnosaPostOp);
        DiagnosaPostOp.setBounds(110, 10, 410, 23);

        label29.setText("Pegawai:");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(label29);
        label29.setBounds(510, 160, 80, 23);

        NIP11.setEditable(false);
        NIP11.setName("NIP11"); // NOI18N
        NIP11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass23.add(NIP11);
        NIP11.setBounds(600, 160, 90, 23);

        NamaPegawai11.setEditable(false);
        NamaPegawai11.setName("NamaPegawai11"); // NOI18N
        NamaPegawai11.setPreferredSize(new java.awt.Dimension(207, 23));
        panelGlass23.add(NamaPegawai11);
        NamaPegawai11.setBounds(700, 160, 180, 23);

        BtnPegawai9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai9.setMnemonic('2');
        BtnPegawai9.setToolTipText("Alt+2");
        BtnPegawai9.setName("BtnPegawai9"); // NOI18N
        BtnPegawai9.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPegawai9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai9ActionPerformed(evt);
            }
        });
        BtnPegawai9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPegawai9KeyPressed(evt);
            }
        });
        panelGlass23.add(BtnPegawai9);
        BtnPegawai9.setBounds(880, 160, 28, 23);

        PanelInput11.add(panelGlass23, java.awt.BorderLayout.CENTER);

        internalFrame10.add(PanelInput11, java.awt.BorderLayout.PAGE_START);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        tbAnestesi13.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAnestesi13.setName("tbAnestesi13"); // NOI18N
        tbAnestesi13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAnestesi13MouseClicked(evt);
            }
        });
        tbAnestesi13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbAnestesi13KeyReleased(evt);
            }
        });
        Scroll24.setViewportView(tbAnestesi13);

        internalFrame10.add(Scroll24, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("G. Pasca Bedah", internalFrame10);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel26.setText("Tgl.Rawat :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(64, 23));
        panelGlass10.add(jLabel26);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("s.d.");
        jLabel84.setName("jLabel84"); // NOI18N
        jLabel84.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel84);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-01-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

        jSeparator7.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator7.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setName("jSeparator7"); // NOI18N
        jSeparator7.setOpaque(true);
        jSeparator7.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass10.add(jSeparator7);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        BtnTambahTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahTindakan.setMnemonic('3');
        BtnTambahTindakan.setToolTipText("Alt+3");
        BtnTambahTindakan.setName("BtnTambahTindakan"); // NOI18N
        BtnTambahTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahTindakanActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnTambahTindakan);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            //   Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (DiagnosaPreOp.getText().trim().equals("")) {
                    Valid.textKosong(DiagnosaPreOp, "Diagnosa");
                } else if (JenisPembedahan.getText().trim().equals("")) {
                    Valid.textKosong(JenisPembedahan, "Jenis Pembedahan");
                } else if (Kamar.getText().trim().equals("")) {
                    Valid.textKosong(Kamar, "Kamar");
                } else if (KdDokterBedah.getText().trim().equals("")) {
                    Valid.textKosong(KdDokterBedah, "Dokter Bedah");
                } else if (KdAsistenBedah.getText().trim().equals("")) {
                    Valid.textKosong(KdAsistenBedah, "Assisten Bedah");
                } else if (KdDokterAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(KdDokterAnestesi, "Dokter Anestesi");
                } else if (KdAsistenAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(KdAsistenAnestesi, "Assisten Anestesi");
                } else if (E.getText().trim().equals("")) {
                    Valid.textKosong(E, "E");
                } else if (M.getText().trim().equals("")) {
                    Valid.textKosong(M, "M");
                } else if (V.getText().trim().equals("")) {
                    Valid.textKosong(V, "Assisten Anestesi");
                } else if (Td.getText().trim().equals("")) {
                    Valid.textKosong(Td, "Tensi");
                } else if (Nadi.getText().trim().equals("")) {
                    Valid.textKosong(Nadi, "Nadi");
                } else if (Rr.getText().trim().equals("")) {
                    Valid.textKosong(Rr, "Respirasi");
                } else if (O2.getText().trim().equals("")) {
                    Valid.textKosong(O2, "O2");
                } else if (Spo2.getText().trim().equals("")) {
                    Valid.textKosong(Spo2, "Spo2");
                } else if (TipePernapasan.getText().trim().equals("")) {
                    Valid.textKosong(TipePernapasan, "Tipe Pernapasan");
                } else if (Support.getText().trim().equals("")) {
                    Valid.textKosong(Support, "Support");
                } else {
                    if (Sequel.menyimpantf("anestesi_masuk", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 30, new String[]{
                        TNoRw.getText(), DiagnosaPreOp.getText(), JenisPembedahan.getText(), Bb.getText(), Kamar.getText(), JenisOperasi.getSelectedItem().toString(), Bagian.getText(), InformConsent.getSelectedItem().toString(),
                        Valid.SetTgl(TglOperasi.getSelectedItem() + "") + " " + TglOperasi.getSelectedItem().toString().substring(11, 19), KdDokterBedah.getText(), KdAsistenBedah.getText(), KdDokterAnestesi.getText(),
                        KdAsistenAnestesi.getText(), Kesadaran.getSelectedItem().toString(), E.getText(), M.getText(), V.getText(), Td.getText(), Nadi.getText(), Respirasi.getSelectedItem().toString(),
                        Rr.getText(), Kanul.getSelectedItem().toString(), O2.getText(), Spo2.getText(), TipePernapasan.getText(), Regular.getSelectedItem().toString(), Airway.getSelectedItem().toString(),
                        Support.getText(), StatusFisik.getSelectedItem().toString(), NIPPenginput.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), DiagnosaPreOp.getText(), JenisPembedahan.getText(), Bb.getText(), Kamar.getText(), JenisOperasi.getSelectedItem().toString(), Bagian.getText(), InformConsent.getSelectedItem().toString(),
                            Valid.SetTgl(TglOperasi.getSelectedItem() + "") + " " + TglOperasi.getSelectedItem().toString().substring(11, 19), KdDokterBedah.getText(), NmDokterBedah.getText(), KdAsistenBedah.getText(), NmAsistenBedah.getText(), KdDokterAnestesi.getText(), NmDokterAnestesi.getText(),
                            KdAsistenAnestesi.getText(), NmAsistenAnestesi.getText(), Kesadaran.getSelectedItem().toString(), E.getText(), M.getText(), V.getText(), Td.getText(), Nadi.getText(), Respirasi.getSelectedItem().toString(),
                            Rr.getText(), Kanul.getSelectedItem().toString(), O2.getText(), Spo2.getText(), TipePernapasan.getText(), Regular.getSelectedItem().toString(), Airway.getSelectedItem().toString(),
                            Support.getText(), StatusFisik.getSelectedItem().toString(), NIPPenginput.getText(), NamaPenginput.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Obat.getText().trim().equals("")) {
                    Valid.textKosong(Obat, "Obat");
                } else if (Dosis.getText().trim().equals("")) {
                    Valid.textKosong(Dosis, "Obat");
                } else if (Pegawaipremedikasi.getText().trim().equals("")) {
                    Valid.textKosong(Pegawaipremedikasi, "Pegawai");
                } else {
                    if (Sequel.menyimpantf("anestesi_premedikasi", "?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 7, new String[]{
                        TNoRw.getText(), Rute.getSelectedItem().toString(), Valid.SetTgl(TglPremedikasi.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19),
                        Obat.getText(), Dosis.getText(), Ramsaya.getSelectedItem().toString(), Pegawaipremedikasi.getText()
                    }) == true) {
                        tabMode2.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Rute.getSelectedItem().toString(), Valid.SetTgl(TglPremedikasi.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19),
                            Obat.getText(), Dosis.getText(), Ramsaya.getSelectedItem().toString(), Pegawaipremedikasi.getText(), NmPegawaiPremedikasi.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    if (Sequel.menyimpantf("anestesi_prainduksi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 17, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglPrainduksi.getSelectedItem() + "") + " " + TglPrainduksi.getSelectedItem().toString().substring(11, 19), Kesadaran2.getSelectedItem().toString(),
                        E2.getText(), M2.getText(), V2.getText(), Tensi.getText(), Nadi1.getText(), Respirasi2.getSelectedItem().toString(), Rr2.getText(), Selang2.getSelectedItem().toString(), o22.getText(), Spo22.getText(), TipePernapasan2.getText(),
                        Pernapasan.getSelectedItem().toString(), Support2.getText(), NIP2.getText()
                    }) == true) {
                        tabMode3.addRow(new String[]{
                            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Kesadaran2.getSelectedItem().toString(),
                            E2.getText(), M2.getText(), V2.getText(), Tensi.getText(), Nadi1.getText(), Respirasi2.getSelectedItem().toString(), Rr2.getText(), Selang2.getSelectedItem().toString(), o22.getText(), Spo22.getText(), TipePernapasan2.getText(),
                            Pernapasan.getSelectedItem().toString(), Support2.getText(), NIP2.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 3:
                TabTeknikAnestesiSimpan();
            case 4:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (NIP6.getText().trim().equals("")) {
                    Valid.textKosong(NIP6, "Pegawai");
                } else if (Komplikasi.getText().trim().equals("")) {
                    Valid.textKosong(Komplikasi, "Komplikasi");
                } else if (Tindakan.getText().trim().equals("")) {
                    Valid.textKosong(Tindakan, "Tindakan");
                } else {
                    if (Sequel.menyimpantf("anestesi_selama", "?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 9, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Posisi.getSelectedItem().toString(),
                        Airway2.getSelectedItem().toString(), Ett.getSelectedItem().toString(), Ukuran.getSelectedItem().toString(), Komplikasi.getText(), Tindakan.getText(), NIP6.getText()

                    }) == true) {
                        tabMode7.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Posisi.getSelectedItem().toString(),
                            Airway2.getSelectedItem().toString(), Ett.getSelectedItem().toString(), Ukuran.getSelectedItem().toString(), Komplikasi.getText(), Tindakan.getText(), NIP6.getText(), NamaPegawai6.getText()

                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 5:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (NIP7.getText().trim().equals("")) {
                    Valid.textKosong(NIP7, "Pegawai");
                } else if (O22.getText().trim().equals("")) {
                    Valid.textKosong(O22, "O2");
                } else if (N2o2.getText().trim().equals("")) {
                    Valid.textKosong(N2o2, "N2O");
                } else if (Air.getText().trim().equals("")) {
                    Valid.textKosong(Air, "Air");
                } else if (Halothane.getText().trim().equals("")) {
                    Valid.textKosong(Halothane, "Halothane");
                } else if (Isoflurane.getText().trim().equals("")) {
                    Valid.textKosong(Isoflurane, "Isoflurane");
                } else if (Sevo.getText().trim().equals("")) {
                    Valid.textKosong(Sevo, "Sevo");
                } else if (Dml.getText().trim().equals("")) {
                    Valid.textKosong(Dml, "Dml");
                } else if (Tvs.getText().trim().equals("")) {
                    Valid.textKosong(Tvs, "Tvs");
                } else if (Nadi_1.getText().trim().equals("")) {
                    Valid.textKosong(Nadi_1, "Nadi_1");
                } else if (Sistolik.getText().trim().equals("")) {
                    Valid.textKosong(Sistolik, "Sistolik");
                } else if (Diastolik.getText().trim().equals("")) {
                    Valid.textKosong(Diastolik, "Diastolik");
                } else {
                    if (Sequel.menyimpantf("anestesi_monitoring", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 14, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), O22.getText(),
                        N2o2.getText(), Air.getText(), Halothane.getText(), Isoflurane.getText(), Sevo.getText(), Dml.getText(), Tvs.getText(), Nadi_1.getText(), Sistolik.getText(), Diastolik.getText(), NIP7.getText()

                    }) == true) {
                        tabMode8.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), O22.getText(),
                            N2o2.getText(), Air.getText(), Halothane.getText(), Isoflurane.getText(), Sevo.getText(), Dml.getText(), Tvs.getText(), Nadi_1.getText(), Sistolik.getText(), Diastolik.getText(), NIP7.getText(), NamaPegawai7.getText()

                        });
                        emptTeks();
                    }
                }

                break;
            case 6:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Monitoring2_spo2.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring2_spo2, "Spo2");
                } else if (Monitoring2_etco2.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring2_etco2, "Etco2");
                } else if (Monitoring2_fio2.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring2_fio2, "Fi02");
                } else if (Cairan.getText().trim().equals("")) {
                    Valid.textKosong(Cairan, "Cairan");
                } else if (Urine.getText().trim().equals("")) {
                    Valid.textKosong(Urine, "Urine");
                } else if (Perdarahan.getText().trim().equals("")) {
                    Valid.textKosong(Perdarahan, "Perdarahan");
                } else if (NIP8.getText().trim().equals("")) {
                    Valid.textKosong(NIP8, "Pegawai");
                } else {
                    if (Sequel.menyimpantf("anestesi_monitoring2", "?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 9, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Monitoring2_spo2.getText(), Monitoring2_etco2.getText(), Monitoring2_fio2.getText(), Cairan.getText(), Urine.getText(), Perdarahan.getText(), NIP8.getText()

                    }) == true) {
                        tabMode9.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Monitoring2_spo2.getText(), Monitoring2_etco2.getText(), Monitoring2_fio2.getText(), Cairan.getText(), Urine.getText(), Perdarahan.getText(), NIP8.getText(), NamaPegawai8.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 7:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Kristaloid.getText().trim().equals("")) {
                    Valid.textKosong(Kristaloid, "Kristaloid");
                } else if (Koloid.getText().trim().equals("")) {
                    Valid.textKosong(Koloid, "Koloid");
                } else if (Darah.getText().trim().equals("")) {
                    Valid.textKosong(Darah, "Darah");
                } else if (KomponenDarah.getText().trim().equals("")) {
                    Valid.textKosong(KomponenDarah, "Komponen Darah");
                } else if (NIP9.getText().trim().equals("")) {
                    Valid.textKosong(NIP9, "Pegawai");
                } else {
                    if (Sequel.menyimpantf("anestesi_cairan", "?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 11, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Kristaloid.getText(),
                        Koloid.getText(), Darah.getText(), KomponenDarah.getText(), Urin.getText(), Perdarahan2.getText(), Diuresis.getText(), CairanLain.getText(), NIP9.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Kristaloid.getText(),
                            Koloid.getText(), Darah.getText(), KomponenDarah.getText(), Urin.getText(), Perdarahan2.getText(), Diuresis.getText(), CairanLain.getText(), NIP9.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 8:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Apgar1.getText().trim().equals("")) {
                    Valid.textKosong(Apgar1, "Apgar");
                } else if (Apgar5.getText().trim().equals("")) {
                    Valid.textKosong(Apgar5, "Apgar");
                } else if (Bb2.getText().trim().equals("")) {
                    Valid.textKosong(Bb2, "BB");
                } else if (NIP10.getText().trim().equals("")) {
                    Valid.textKosong(NIP10, "Rencana Tindakan");
                } else {
                    if (Sequel.menyimpantf("anestesi_sectio", "?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 9, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        KeadaanBayi.getSelectedItem().toString(), Jk2.getSelectedItem().toString(), Apgar1.getText(), Apgar5.getText(), Bb2.getText(), Pb2.getText(), NIP10.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            KeadaanBayi.getSelectedItem().toString(), Jk2.getSelectedItem().toString(), Apgar1.getText(), Apgar5.getText(), Bb2.getText(), Pb2.getText(), NIP10.getText()

                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 9:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (DiagnosaPostOp.getText().trim().equals("")) {
                    Valid.textKosong(DiagnosaPostOp, "Diagnosa Post Op");
                } else if (Nadi2.getText().trim().equals("")) {
                    Valid.textKosong(Nadi2, "Nadi");
                } else if (RR2.getText().trim().equals("")) {
                    Valid.textKosong(RR2, "RR");
                } else if (NIP11.getText().trim().equals("")) {
                    Valid.textKosong(NIP11, "Petugas");
                } else {
                    if (Sequel.menyimpantf("anestesi_pasca", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 20, new String[]{
                        TNoRw.getText(), DiagnosaPostOp.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        Pindah.getSelectedItem().toString(), KeadaaanUmum.getText(), Nadi2.getText(), RR2.getText(), Suhu.getText(), Pasca_Spo2.getText(), Instruksi.getText(), Vetilator.getText(),
                        TidalVolume.getText(), Mlrr.getText(), Ieratio.getText(), Peep2.getText(), Pip2.getText(), Ps.getText(), Fio2.getText(), Trigger.getText(), NIP11.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), DiagnosaPostOp.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Pindah.getSelectedItem().toString(), KeadaaanUmum.getText(), Nadi2.getText(), RR2.getText(), Suhu.getText(), Pasca_Spo2.getText(), Instruksi.getText(), Vetilator.getText(),
                            TidalVolume.getText(), Mlrr.getText(), Ieratio.getText(), Peep2.getText(), Pip2.getText(), Ps.getText(), Fio2.getText(), Trigger.getText(), NIP11.getText(), NamaPegawai11.getText()

                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            default:

        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
           Valid.pindah(evt,CatatanKhusus,BtnBatal);
        } */
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_masuk where no_rawat=? and tanggaloperasi=?", 2, new String[]{
                                tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 0).toString(), tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 13).toString()
                            }) == true) {
                                tabMode.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_masuk where no_rawat=? and tanggaloperasi=?", 2, new String[]{
                                    tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 0).toString(), tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 13).toString()
                                }) == true) {
                                    tabMode.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }

                break;

            case 1:
                if (tabMode2.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi2.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_premedikasi where no_rawat=? and tanggalpremedikasi=?", 2, new String[]{
                                tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 0).toString(), tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 7).toString()
                            }) == true) {
                                tabMode2.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_premedikasi where no_rawat=? and tanggalpremedikasi=?", 2, new String[]{
                                    tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 0).toString(), tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 7).toString()
                                }) == true) {
                                    tabMode2.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }

                break;
            case 2:
                if (tabMode3.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi3.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_prainduksi where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 0).toString(), tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 5).toString()
                            }) == true) {
                                tabMode3.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_prainduksi where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 0).toString(), tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 5).toString()
                                }) == true) {
                                    tabMode3.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                TabTeknikAnestesiHapus();
                break;
            case 4:
                if (tabMode7.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi8.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_selama where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 0).toString(), tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode7.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_selama where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 0).toString(), tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode7.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 5:
                if (tabMode8.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi13.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_monitoring where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode8.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_monitoring where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode8.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 6:
                if (tabMode9.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi10.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_monitoring2 where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 0).toString(), tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode9.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_monitoring2 where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 0).toString(), tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode9.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 7:
                if (tabMode10.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi11.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_cairan where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 0).toString(), tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode10.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_cairan where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 0).toString(), tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode10.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 8:
                if (tabMode11.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi12.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_sectio where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 0).toString(), tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode11.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_sectio where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 0).toString(), tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode11.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            case 9:
                if (tabMode12.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi13.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_pasca where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode12.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_pasca where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode12.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            default:
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRM, "Nama Pasien");
                    } else if (DiagnosaPreOp.getText().trim().equals("")) {
                        Valid.textKosong(DiagnosaPreOp, "Diagnosa");
                    } else if (JenisPembedahan.getText().trim().equals("")) {
                        Valid.textKosong(JenisPembedahan, "Jenis Pembedahan");
                    } else if (Kamar.getText().trim().equals("")) {
                        Valid.textKosong(Kamar, "Kamar");
                    } else if (KdDokterBedah.getText().trim().equals("")) {
                        Valid.textKosong(KdDokterBedah, "Dokter Bedah");
                    } else if (KdAsistenBedah.getText().trim().equals("")) {
                        Valid.textKosong(KdAsistenBedah, "Assisten Bedah");
                    } else if (KdDokterAnestesi.getText().trim().equals("")) {
                        Valid.textKosong(KdDokterAnestesi, "Dokter Anestesi");
                    } else if (KdAsistenAnestesi.getText().trim().equals("")) {
                        Valid.textKosong(KdAsistenAnestesi, "Assisten Anestesi");
                    } else if (E.getText().trim().equals("")) {
                        Valid.textKosong(E, "E");
                    } else if (M.getText().trim().equals("")) {
                        Valid.textKosong(M, "M");
                    } else if (V.getText().trim().equals("")) {
                        Valid.textKosong(V, "Assisten Anestesi");
                    } else if (Td.getText().trim().equals("")) {
                        Valid.textKosong(Td, "Tensi");
                    } else if (Nadi.getText().trim().equals("")) {
                        Valid.textKosong(Nadi, "Nadi");
                    } else if (Rr.getText().trim().equals("")) {
                        Valid.textKosong(Rr, "Respirasi");
                    } else if (O2.getText().trim().equals("")) {
                        Valid.textKosong(O2, "O2");
                    } else if (Spo2.getText().trim().equals("")) {
                        Valid.textKosong(Spo2, "Spo2");
                    } else if (TipePernapasan.getText().trim().equals("")) {
                        Valid.textKosong(TipePernapasan, "Tipe Pernapasan");
                    } else if (Support.getText().trim().equals("")) {
                        Valid.textKosong(Support, "Support");
                    } else {
                        if (tbAnestesi.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti();
                            } else {
                                if (NIPPenginput.getText().equals(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 38).toString())) {
                                    ganti();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 1:
                    if ((TNoRw.getText().trim().equals("")) || (TNoRM.getText().trim().equals("")) || (TPasien.getText().trim().equals(""))) {
                        Valid.textKosong(TNoRM, "Nama Pasien");
                    } else if (Obat.getText().trim().equals("")) {
                        Valid.textKosong(Obat, "Obat");
                    } else if (Dosis.getText().trim().equals("")) {
                        Valid.textKosong(Dosis, "Dosis");
                    } else {
                        if (tbAnestesi2.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti2();
                            } else {
                                if (Pegawaipremedikasi.getText().equals(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 11).toString())) {
                                    ganti2();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 2:
                    if ((TNoRw.getText().trim().equals("")) || (TNoRM.getText().trim().equals("")) || (TPasien.getText().trim().equals(""))) {
                        Valid.textKosong(TNoRM, "Nama Pasien");
                    } else if (E2.getText().trim().equals("")) {
                        Valid.textKosong(E2, "E");
                    } else if (M2.getText().trim().equals("")) {
                        Valid.textKosong(M2, "M");
                    } else if (V2.getText().trim().equals("")) {
                        Valid.textKosong(V2, "V");
                    } else if (Tensi.getText().trim().equals("")) {
                        Valid.textKosong(Tensi, "Tensi");
                    } else if (Nadi1.getText().trim().equals("")) {
                        Valid.textKosong(Nadi1, "Nadi");
                    } else if (Rr2.getText().trim().equals("")) {
                        Valid.textKosong(Rr2, "Respirasi");
                    } else if (o22.getText().trim().equals("")) {
                        Valid.textKosong(o22, "O2");
                    } else if (Spo22.getText().trim().equals("")) {
                        Valid.textKosong(Spo22, "Spo2");
                    } else if (TipePernapasan2.getText().trim().equals("")) {
                        Valid.textKosong(TipePernapasan2, "Tipe Pernapasan");
                    } else if (Support2.getText().trim().equals("")) {
                        Valid.textKosong(Support2, "Support");
                    } else if (NIP2.getText().trim().equals("")) {
                        Valid.textKosong(NIP2, "Pegawai");
                    } else {
                        if (tbAnestesi3.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti3();
                            } else {
                                if (NIP2.getText().equals(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 21).toString())) {
                                    ganti3();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 3:
                    TabTeknikAnestesiGanti();
                    break;
                case 4:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (NIP6.getText().trim().equals("")) {
                        Valid.textKosong(NIP6, "Pegawai");
                    } else if (Komplikasi.getText().trim().equals("")) {
                        Valid.textKosong(Komplikasi, "Komplikasi");
                    } else if (Tindakan.getText().trim().equals("")) {
                        Valid.textKosong(Tindakan, "Tindakan");
                    } else {
                        if (tbAnestesi8.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti7();
                            } else {
                                if (NIP6.getText().equals(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 13).toString())) {
                                    ganti7();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 5:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (NIP7.getText().trim().equals("")) {
                        Valid.textKosong(NIP7, "Pegawai");
                    } else if (O22.getText().trim().equals("")) {
                        Valid.textKosong(O22, "O2");
                    } else if (N2o2.getText().trim().equals("")) {
                        Valid.textKosong(N2o2, "N2O");
                    } else if (Air.getText().trim().equals("")) {
                        Valid.textKosong(Air, "Air");
                    } else if (Halothane.getText().trim().equals("")) {
                        Valid.textKosong(Halothane, "Halothane");
                    } else if (Isoflurane.getText().trim().equals("")) {
                        Valid.textKosong(Isoflurane, "Isoflurane");
                    } else if (Sevo.getText().trim().equals("")) {
                        Valid.textKosong(Sevo, "Sevo");
                    } else if (Dml.getText().trim().equals("")) {
                        Valid.textKosong(Dml, "Dml");
                    } else if (Tvs.getText().trim().equals("")) {
                        Valid.textKosong(Tvs, "Tvs");
                    } else if (Nadi_1.getText().trim().equals("")) {
                        Valid.textKosong(Nadi_1, "Nadi_1");
                    } else if (Sistolik.getText().trim().equals("")) {
                        Valid.textKosong(Sistolik, "Sistolik");
                    } else if (Diastolik.getText().trim().equals("")) {
                        Valid.textKosong(Diastolik, "Diastolik");
                    } else {
                        if (tbAnestesi9.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti();
                            } else {
                                if (NIP7.getText().equals(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 18).toString())) {
                                    ganti8();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 6:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (Monitoring2_spo2.getText().trim().equals("")) {
                        Valid.textKosong(Monitoring2_spo2, "Spo2");
                    } else if (Monitoring2_etco2.getText().trim().equals("")) {
                        Valid.textKosong(Monitoring2_etco2, "Etco2");
                    } else if (Monitoring2_fio2.getText().trim().equals("")) {
                        Valid.textKosong(Monitoring2_fio2, "Fi02");
                    } else if (Cairan.getText().trim().equals("")) {
                        Valid.textKosong(Cairan, "Cairan");
                    } else if (Urine.getText().trim().equals("")) {
                        Valid.textKosong(Urine, "Urine");
                    } else if (Perdarahan.getText().trim().equals("")) {
                        Valid.textKosong(Perdarahan, "Perdarahan");
                    } else if (NIP8.getText().trim().equals("")) {
                        Valid.textKosong(NIP8, "Pegawai");
                    } else {
                        if (tbAnestesi10.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti();
                            } else {
                                if (NIP8.getText().equals(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 13).toString())) {
                                    ganti9();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 7:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (Kristaloid.getText().trim().equals("")) {
                        Valid.textKosong(Kristaloid, "Kristaloid");
                    } else if (Koloid.getText().trim().equals("")) {
                        Valid.textKosong(Koloid, "Koloid");
                    } else if (Darah.getText().trim().equals("")) {
                        Valid.textKosong(Darah, "Darah");
                    } else if (KomponenDarah.getText().trim().equals("")) {
                        Valid.textKosong(KomponenDarah, "Komponen Darah");
                    } else if (NIP9.getText().trim().equals("")) {
                        Valid.textKosong(NIP9, "Pegawai");
                    } else {
                        if (tbAnestesi11.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti10();
                            } else {
                                if (NIP9.getText().equals(tbAnestesi11.getValueAt(tbAnestesi.getSelectedRow(), 14).toString())) {
                                    ganti10();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 8:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (Apgar1.getText().trim().equals("")) {
                        Valid.textKosong(Apgar1, "Apgar");
                    } else if (Apgar5.getText().trim().equals("")) {
                        Valid.textKosong(Apgar5, "Apgar");
                    } else if (Bb2.getText().trim().equals("")) {
                        Valid.textKosong(Bb2, "BB");
                    } else if (NIP10.getText().trim().equals("")) {
                        Valid.textKosong(NIP10, "Rencana Tindakan");
                    } else {
                        if (tbAnestesi12.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti11();
                            } else {
                                if (NIP10.getText().equals(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 38).toString())) {
                                    ganti11();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 9:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRw, "Nama Pasien");
                    } else if (DiagnosaPostOp.getText().trim().equals("")) {
                        Valid.textKosong(DiagnosaPostOp, "Diagnosa Post Op");
                    } else if (Nadi2.getText().trim().equals("")) {
                        Valid.textKosong(Nadi2, "Nadi");
                    } else if (RR2.getText().trim().equals("")) {
                        Valid.textKosong(RR2, "RR");
                    } else if (NIP11.getText().trim().equals("")) {
                        Valid.textKosong(NIP11, "Petugas");
                    } else {
                        if (tbAnestesi13.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti12();
                            } else {
                                if (NIP11.getText().equals(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 23).toString())) {
                                    ganti12();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 10:
                    if (TNoRM.getText().trim().equals("")) {
                        Valid.textKosong(TNoRM, "Nama Pasien");
                    } else if (DiagnosaPreOp.getText().trim().equals("")) {
                        Valid.textKosong(DiagnosaPreOp, "Diagnosa");
                    } else if (JenisPembedahan.getText().trim().equals("")) {
                        Valid.textKosong(JenisPembedahan, "Jenis Pembedahan");
                    } else if (Kamar.getText().trim().equals("")) {
                        Valid.textKosong(Kamar, "Kamar");
                    } else if (KdDokterBedah.getText().trim().equals("")) {
                        Valid.textKosong(KdDokterBedah, "Dokter Bedah");
                    } else if (KdAsistenBedah.getText().trim().equals("")) {
                        Valid.textKosong(KdAsistenBedah, "Assisten Bedah");
                    } else if (KdDokterAnestesi.getText().trim().equals("")) {
                        Valid.textKosong(KdDokterAnestesi, "Dokter Anestesi");
                    } else if (KdAsistenAnestesi.getText().trim().equals("")) {
                        Valid.textKosong(KdAsistenAnestesi, "Assisten Anestesi");
                    } else if (E.getText().trim().equals("")) {
                        Valid.textKosong(E, "E");
                    } else if (M.getText().trim().equals("")) {
                        Valid.textKosong(M, "M");
                    } else if (V.getText().trim().equals("")) {
                        Valid.textKosong(V, "Assisten Anestesi");
                    } else if (Td.getText().trim().equals("")) {
                        Valid.textKosong(Td, "Tensi");
                    } else if (Nadi.getText().trim().equals("")) {
                        Valid.textKosong(Nadi, "Nadi");
                    } else if (Rr.getText().trim().equals("")) {
                        Valid.textKosong(Rr, "Respirasi");
                    } else if (O2.getText().trim().equals("")) {
                        Valid.textKosong(O2, "O2");
                    } else if (Spo2.getText().trim().equals("")) {
                        Valid.textKosong(Spo2, "Spo2");
                    } else if (TipePernapasan.getText().trim().equals("")) {
                        Valid.textKosong(TipePernapasan, "Tipe Pernapasan");
                    } else if (Support.getText().trim().equals("")) {
                        Valid.textKosong(Support, "Support");
                    } else {
                        if (tbAnestesi.getSelectedRow() > -1) {
                            if (akses.getkode().equals("Admin Utama")) {
                                ganti();
                            } else {
                                if (NIPPenginput.getText().equals(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 38).toString())) {
                                    ganti();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                default:
            }

        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);} */
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        /*    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Operasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Tindakan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TB</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>BB</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TD</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>IO2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nadi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pernapasan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suhu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Cardiovasculer</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Paru</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Abdomen</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Extrimitas</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Endokrin</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Ginjal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Obat-obatan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Laborat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Penunjang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Obat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Lainnya</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Terapi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Merokok</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Rokok</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Alkohol</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Alko</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penggunaan Obat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Obat Dikonsumsi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Cardiovasculer</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Respiratory</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Endocrine</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Lainnya</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Angka ASA</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mulai Puasa</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Anestesi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Perawatan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Catatan Khusus</b></td>"+
                    "</tr>"
                );
                
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbAnestesi.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,35).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,36).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,37).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,38).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,39).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,40).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,41).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,42).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,43).toString()+"</td>"+
                            "<td valign='top'>"+tbAnestesi.getValueAt(i,44).toString()+"</td>"+ 
                        "</tr>");
                }
                
                LoadHTML.setText(
                    "<html>"+
                      "<table width='4500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>"
                );

                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                );
                bg.close();

                File f = new File("DataPenilaianPreAnestesi.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='4500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN PRE ANESTESI<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());

            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor()); */
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        //    TCari.setText("");
        //    tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        } */
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnDokterBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterBedahActionPerformed
        pilihan = 1;
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterBedahActionPerformed

    private void BtnDokterBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterBedahKeyPressed
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnDokterBedahKeyPressed

    private void MnCetakStatusAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakStatusAnestesiActionPerformed
        if (tbAnestesi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("norawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 5).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + " Ditandatangani secara elektronik oleh " + tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 6).toString() + " ID " + (finger.equals("") ? tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 5).toString() : finger) + " " + Valid.SetTgl3(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 7).toString()));

            Valid.MyReportqry(
                    "rptCetakStatusAnestesi.jasper",
                    "report",
                    "::[ Laporan Penilaian Status Anestesi ]::",
                    "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien,pasien.jk,pasien.tgl_lahir, reg_periksa.almt_pj, anestesi_masuk.diagnosapreop, anestesi_masuk.jenispembedahan, "
                    + "anestesi_masuk.bb, anestesi_masuk.kamar, anestesi_masuk.jenisoperasi, anestesi_masuk.bagian, anestesi_masuk.informconsent, anestesi_masuk.tanggaloperasi, anestesi_masuk.kd_dokter, "
                    + "anestesi_masuk.asistenbedah, anestesi_masuk.dokteranestesi, anestesi_masuk.asistenanestesi, anestesi_masuk.kesadaran, anestesi_masuk.e, anestesi_masuk.m, anestesi_masuk.v, "
                    + "anestesi_masuk.td, anestesi_masuk.nadi, anestesi_masuk.respirasi, anestesi_masuk.rr, anestesi_masuk.selang, anestesi_masuk.o2, anestesi_masuk.spo2, anestesi_masuk.tipepernapasan, "
                    + "anestesi_masuk.pernapasan, anestesi_masuk.airway, anestesi_masuk.support, anestesi_masuk.statusfisik, anestesi_masuk.nip, "
                    + "dokter1.nm_dokter AS nama_dokter_utama, dokter2.nm_dokter AS nama_dokter_anestesi, "
                    + "petugas1.nama AS nama_asisten_bedah, petugas2.nama AS nama_asisten_anestesi,anestesi_premedikasi.rute,anestesi_premedikasi.obat,anestesi_premedikasi.dosis,anestesi_premedikasi.ramsaya, "
                    + "anestesi_premedikasi.tanggalpremedikasi,(anestesi_prainduksi.kesadaran)as kesadaran2,(anestesi_prainduksi.e)as e2,(anestesi_prainduksi.m)as m2,(anestesi_prainduksi.v)as v2,"
                    + "(anestesi_prainduksi.td)as td2,(anestesi_prainduksi.nadi)as nadi2,(anestesi_prainduksi.respirasi)as respirasi2,(anestesi_prainduksi.rr)as rr2,anestesi_prainduksi.selang,"
                    + "(anestesi_prainduksi.o2)as o22,(anestesi_prainduksi.spo2)as spo22,(anestesi_prainduksi.tipepernapasan)as tipepernapasan2,(anestesi_prainduksi.pernapasan)as pernapasan2,(anestesi_prainduksi.support)as support2, "
                    + "anestesi_teknikumum.induksi,anestesi_teknikumum.metode,anestesi_teknikumum.nafas,anestesi_teknikumum.tidalvolume,(anestesi_teknikumum.rr)as rr3,anestesi_teknikumum.eratio,"
                    + "anestesi_teknikumum.peep,anestesi_teknikumum.pip,anestesi_teknikumum.fi02,anestesi_teknikumum.teknikkhusus,anestesi_teknikumum.pemeliharaan,anestesi_teknikumum.perhitungancairan, "
                    + "anestesi_teknikumum.ebv,anestesi_teknikumum.ebl,anestesi_teknikumum.jenisdarah,anestesi_teknikumum.jumlahdarah,anestesi_teknikumum.monitoring,anestesi_teknikumum.perubahanteknik,"
                    + "anestesi_teknikumum.alasan,anestesi_teknikregional.teknik, anestesi_teknikregional.lokasitusukan, anestesi_teknikregional.analgesi, anestesi_teknikregional.anestesilokal, anestesi_teknikregional.konsentrasianestesi, "
                    + "anestesi_teknikregional.jumlah, anestesi_teknikregional.obattambahan, (anestesi_teknikregional.dosis)as dosis2, anestesi_teknikregional.adrenalin, anestesi_teknikregional.noradrenalin, "
                    + "anestesi_teknikregional.konsentrasivasokontriktor, (anestesi_teknikregional.pemeliharaan)as pemeliharaan2, (anestesi_teknikregional.monitoring)as monitoring2, (anestesi_teknikregional.perubahanteknik)as perubahanteknik2, "
                    + "(anestesi_teknikregional.alasan)as alasan2,(anestesi_teknikcombined.teknik)as teknik2,(anestesi_teknikcombined.obat)as obat2,(anestesi_teknikcombined.pemeliharaan)as pemeliharaan3,(anestesi_teknikcombined.monitoring)as monitoring3,"
                    + "(anestesi_teknikcombined.perubahanteknik)as perubahanteknik3,(anestesi_teknikcombined.alasan)as alasan3,anestesi_selama.posisi,(anestesi_selama.airway)as airway2,anestesi_selama.ett,anestesi_selama.ukuran,anestesi_selama.komplikasi,"
                    + "anestesi_selama.tindakan,anestesi_cairan.kristaloid,anestesi_cairan.koloid,anestesi_cairan.darah,anestesi_cairan.komponendarah,anestesi_cairan.urine,anestesi_cairan.perdarahan,anestesi_cairan.diuresis,anestesi_cairan.cairanlain,"
                    + "anestesi_sectio.keadaanbayi,(anestesi_sectio.jk)as jk2,anestesi_sectio.apgar1,anestesi_sectio.apgar5,(anestesi_sectio.bb)as bb2,anestesi_sectio.pb,anestesi_pasca.diagnosapostop, anestesi_pasca.pindah, anestesi_pasca.ku, "
                    + "(anestesi_pasca.nadi)as nadi3, (anestesi_pasca.rr)as rr4, anestesi_pasca.suhu, anestesi_pasca.spo2, anestesi_pasca.instruksi, anestesi_pasca.vetilator, anestesi_pasca.tidal, anestesi_pasca.mlrr, anestesi_pasca.ieratio, "
                    + "(anestesi_pasca.peep)as peep2, (anestesi_pasca.pip)as pip2, anestesi_pasca.ps, (anestesi_pasca.fi02)as fi022, anestesi_pasca.trig FROM reg_periksa "
                    + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN anestesi_masuk ON reg_periksa.no_rawat = anestesi_masuk.no_rawat INNER JOIN anestesi_premedikasi ON reg_periksa.no_rawat = anestesi_premedikasi.no_rawat INNER JOIN anestesi_prainduksi ON "
                    + "reg_periksa.no_rawat=anestesi_prainduksi.no_rawat INNER JOIN anestesi_teknikumum ON reg_periksa.no_rawat = anestesi_teknikumum.no_rawat INNER JOIN anestesi_teknikregional on reg_periksa.no_rawat = anestesi_teknikregional.no_rawat "
                    + "INNER JOIN anestesi_teknikcombined ON reg_periksa.no_rawat = anestesi_teknikcombined.no_rawat INNER JOIN anestesi_selama ON reg_periksa.no_rawat = anestesi_selama.no_rawat "
                    + "INNER JOIN anestesi_sectio ON reg_periksa.no_rawat = anestesi_sectio.no_rawat INNER JOIN anestesi_cairan ON reg_periksa.no_rawat = anestesi_cairan.no_rawat "
                    + "INNER JOIN anestesi_pasca ON reg_periksa.no_rawat = anestesi_pasca.no_rawat "
                    + "INNER JOIN dokter dokter1 ON anestesi_masuk.kd_dokter = dokter1.kd_dokter "
                    + "INNER JOIN dokter dokter2 ON anestesi_masuk.dokteranestesi = dokter2.kd_dokter "
                    + "INNER JOIN petugas petugas1 ON anestesi_masuk.asistenbedah = petugas1.nip "
                    + "INNER JOIN petugas petugas2 ON anestesi_masuk.asistenanestesi = petugas2.nip "
                    + "WHERE reg_periksa.no_rawat = '" + tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 0).toString() + "'",
                    param
            );

        }
    }//GEN-LAST:event_MnCetakStatusAnestesiActionPerformed

    private void TglOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglOperasiKeyPressed
        Valid.pindah(evt, JenisPembedahan, E);
    }//GEN-LAST:event_TglOperasiKeyPressed

    private void DiagnosaPreOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaPreOpKeyPressed
        //   Valid.pindah(evt,TglAsuhan,RencanaTindakan);
    }//GEN-LAST:event_DiagnosaPreOpKeyPressed

    private void JenisPembedahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPembedahanKeyPressed
        Valid.pindah(evt, DiagnosaPreOp, E);
    }//GEN-LAST:event_JenisPembedahanKeyPressed

    private void EKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKeyPressed
        Valid.pindah(evt, JenisPembedahan, Bb);
    }//GEN-LAST:event_EKeyPressed

    private void BbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BbKeyPressed
        Valid.pindah(evt, E, M);
    }//GEN-LAST:event_BbKeyPressed

    private void MKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MKeyPressed
        Valid.pindah(evt, Bb, V);
    }//GEN-LAST:event_MKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt, V, Td);
    }//GEN-LAST:event_NadiKeyPressed

    private void TdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdKeyPressed
        Valid.pindah(evt, Nadi, Rr);
    }//GEN-LAST:event_TdKeyPressed

    private void VKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VKeyPressed
        Valid.pindah(evt, M, Nadi);
    }//GEN-LAST:event_VKeyPressed

    private void RrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RrKeyPressed
        Valid.pindah(evt, Td, O2);
    }//GEN-LAST:event_RrKeyPressed

    private void O2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_O2KeyPressed
        Valid.pindah(evt, Rr, TipePernapasan);
    }//GEN-LAST:event_O2KeyPressed

    private void TipePernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipePernapasanKeyPressed
        //    Valid.pindah(evt,FisikCardio,FisikAbdomen);
    }//GEN-LAST:event_TipePernapasanKeyPressed

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatKeyPressed

    private void AlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatActionPerformed

    private void KamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KamarActionPerformed

    private void KamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KamarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KamarKeyPressed

    private void BagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BagianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BagianActionPerformed

    private void BagianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BagianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BagianKeyPressed

    private void BtnAsistenBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenBedahActionPerformed
        pilihan = 1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAsistenBedahActionPerformed

    private void BtnAsistenBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAsistenBedahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAsistenBedahKeyPressed

    private void BtnDokterAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnestesiActionPerformed
        pilihan = 2;
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterAnestesiActionPerformed

    private void BtnDokterAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnestesiKeyPressed

    private void BtnAsistenAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenAnestesiActionPerformed
        pilihan = 2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAsistenAnestesiActionPerformed

    private void BtnAsistenAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAsistenAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAsistenAnestesiKeyPressed

    private void Spo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Spo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Spo2KeyPressed

    private void SupportKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SupportKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupportKeyPressed

    private void NadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NadiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiActionPerformed

    private void TglPremedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglPremedikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglPremedikasiKeyPressed

    private void ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatKeyPressed

    private void DosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DosisKeyPressed

    private void KristaloidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KristaloidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KristaloidKeyPressed

    private void KeadaaanUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaaanUmumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeadaaanUmumKeyPressed

    private void E2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_E2KeyPressed

    private void M2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_M2KeyPressed

    private void V2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_V2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_V2KeyPressed

    private void TensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TensiKeyPressed

    private void Nadi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nadi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi1ActionPerformed

    private void Nadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi1KeyPressed

    private void Rr2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Rr2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rr2KeyPressed

    private void o22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_o22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_o22KeyPressed

    private void Spo22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Spo22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Spo22KeyPressed

    private void TipePernapasan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipePernapasan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipePernapasan2KeyPressed

    private void Support2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Support2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Support2KeyPressed

    private void InduksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InduksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InduksiKeyPressed

    private void VentilatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VentilatorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VentilatorKeyPressed

    private void Rr3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Rr3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rr3KeyPressed

    private void ERatioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ERatioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ERatioKeyPressed

    private void PeepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PeepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PeepKeyPressed

    private void PipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PipKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PipKeyPressed

    private void Fi02KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fi02KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fi02KeyPressed

    private void PemeliharaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeliharaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PemeliharaanKeyPressed

    private void PerhitunganCairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerhitunganCairanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerhitunganCairanKeyPressed

    private void EbvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EbvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EbvKeyPressed

    private void EblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EblKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EblKeyPressed

    private void JenisDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisDarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisDarahKeyPressed

    private void JumlahDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahDarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahDarahKeyPressed

    private void JenisDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JenisDarahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisDarahActionPerformed

    private void MonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKeyPressed

    private void PerubahanTeknikAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerubahanTeknikAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerubahanTeknikAnestesiKeyPressed

    private void AlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlasanKeyPressed

    private void LokasiTusukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiTusukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LokasiTusukanKeyPressed

    private void AnalgesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnalgesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnalgesiKeyPressed

    private void AnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnestesiKeyPressed

    private void KonsentrasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonsentrasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KonsentrasiKeyPressed

    private void JumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahKeyPressed

    private void ObatTambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatTambahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatTambahanKeyPressed

    private void Dosis2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Dosis2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dosis2KeyPressed

    private void VasokonstriktorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VasokonstriktorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VasokonstriktorKeyPressed

    private void NoradernalinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoradernalinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoradernalinKeyPressed

    private void Konsentrasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Konsentrasi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Konsentrasi2KeyPressed

    private void Pemeliharaan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pemeliharaan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pemeliharaan2KeyPressed

    private void Monitoring2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring2KeyPressed

    private void PerubahanTeknikAnestesi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerubahanTeknikAnestesi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerubahanTeknikAnestesi2KeyPressed

    private void Alasan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Alasan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Alasan2KeyPressed

    private void Monitoring4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring4KeyPressed

    private void PerubahanTeknikAnestesi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerubahanTeknikAnestesi3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerubahanTeknikAnestesi3KeyPressed

    private void Alasan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Alasan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Alasan3KeyPressed

    private void ObatAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatAnestesiKeyPressed

    private void Pemeliharaan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pemeliharaan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pemeliharaan3KeyPressed

    private void KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KomplikasiKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakanKeyPressed

    private void KoloidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KoloidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KoloidKeyPressed

    private void DarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DarahKeyPressed

    private void KomponenDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomponenDarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KomponenDarahKeyPressed

    private void Perdarahan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Perdarahan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Perdarahan2KeyPressed

    private void DiuresisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiuresisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiuresisKeyPressed

    private void CairanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CairanLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CairanLainKeyPressed

    private void Apgar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Apgar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apgar1KeyPressed

    private void Apgar5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Apgar5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apgar5KeyPressed

    private void Bb2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bb2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bb2KeyPressed

    private void Pb2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pb2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pb2KeyPressed

    private void Nadi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nadi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi2ActionPerformed

    private void Nadi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi2KeyPressed

    private void RR2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RR2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RR2KeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuKeyPressed

    private void Pasca_Spo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pasca_Spo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pasca_Spo2KeyPressed

    private void TriggerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriggerKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TriggerKeyPressed

    private void VetilatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VetilatorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_VetilatorKeyPressed

    private void TidalVolumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TidalVolumeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TidalVolumeKeyPressed

    private void MlrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MlrrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MlrrActionPerformed

    private void MlrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MlrrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MlrrKeyPressed

    private void IeratioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IeratioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IeratioKeyPressed

    private void Peep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peep2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Peep2ActionPerformed

    private void Peep2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Peep2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Peep2KeyPressed

    private void Pip2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pip2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pip2ActionPerformed

    private void Pip2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pip2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pip2KeyPressed

    private void PsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PsActionPerformed

    private void PsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PsKeyPressed

    private void Fio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fio2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fio2ActionPerformed

    private void Fio2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fio2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fio2KeyPressed

    private void InstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InstruksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InstruksiKeyPressed

    private void O22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_O22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_O22KeyPressed

    private void N2o2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_N2o2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_N2o2KeyPressed

    private void AirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AirKeyPressed

    private void HalothaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HalothaneKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HalothaneKeyPressed

    private void IsofluraneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IsofluraneKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IsofluraneKeyPressed

    private void SevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SevoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SevoKeyPressed

    private void DmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DmlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DmlKeyPressed

    private void TvsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TvsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TvsKeyPressed

    private void Nadi_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi_1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi_1KeyPressed

    private void SistolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SistolikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SistolikKeyPressed

    private void Monitoring2_spo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring2_spo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring2_spo2KeyPressed

    private void Monitoring2_etco2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring2_etco2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring2_etco2KeyPressed

    private void Monitoring2_fio2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monitoring2_fio2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Monitoring2_fio2KeyPressed

    private void CairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CairanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CairanKeyPressed

    private void UrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UrineKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UrineKeyPressed

    private void PerdarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerdarahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerdarahanKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        //    Valid.pindah(evt,BtnSeekDokter,cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        //    Valid.pindah(evt,cmbMnt,TCari);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void BtnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawaiActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawaiActionPerformed

    private void BtnPegawaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawaiKeyPressed

    private void DiagnosaPostOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaPostOpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaPostOpKeyPressed

    private void BtnAsistenBedah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenBedah1ActionPerformed
        akses.setform("RMStatusAnestesi");
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnAsistenBedah1ActionPerformed

    private void BtnAsistenBedah1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAsistenBedah1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAsistenBedah1KeyPressed

    private void BtnPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai1ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai1ActionPerformed

    private void BtnPegawai1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai1KeyPressed

    private void BtnPegawai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai2ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai2ActionPerformed

    private void BtnPegawai2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai2KeyPressed

    private void BtnPegawai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai3ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai3ActionPerformed

    private void BtnPegawai3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai3KeyPressed

    private void BtnPegawai4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai4ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai4ActionPerformed

    private void BtnPegawai4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai4KeyPressed

    private void BtnPegawai5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai5ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai5ActionPerformed

    private void BtnPegawai5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai5KeyPressed

    private void BtnPegawai6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai6ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai6ActionPerformed

    private void BtnPegawai6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai6KeyPressed

    private void BtnPegawai7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai7ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai7ActionPerformed

    private void BtnPegawai7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai7KeyPressed

    private void BtnPegawai8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai8ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai8ActionPerformed

    private void BtnPegawai8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai8KeyPressed

    private void BtnPegawai9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai9ActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPegawai9ActionPerformed

    private void BtnPegawai9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPegawai9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPegawai9KeyPressed

    private void VentilatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentilatorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VentilatorActionPerformed

    private void PerubahanTeknikAnestesi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PerubahanTeknikAnestesi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerubahanTeknikAnestesi2ActionPerformed

    private void UrinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UrinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UrinKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbAnestesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesiMouseClicked

    private void tbAnestesiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesiKeyReleased
        /*    if(tabModePemeriksaan.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }

        } */
    }//GEN-LAST:event_tbAnestesiKeyReleased

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TampilkanData();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                if(TabRawatTindakanDokter.getSelectedIndex()==0){
                    tbTindakan.requestFocus();
                }else if(TabRawatTindakanDokter.getSelectedIndex()==1){
                    tbRawatDr.requestFocus();
                }
                break;
                case 1:
                if(TabRawatTindakanPetugas.getSelectedIndex()==0){
                    tbTindakan2.requestFocus();
                }else if(TabRawatTindakanPetugas.getSelectedIndex()==1){
                    tbRawatPr.requestFocus();
                }
                break;
                case 2:
                if(TabRawatTindakanDokterPetugas.getSelectedIndex()==0){
                    tbTindakan3.requestFocus();
                }else if(TabRawatTindakanDokterPetugas.getSelectedIndex()==1){
                    tbRawatDrPr.requestFocus();
                }
                break;
                case 3:
                tbAnestesi.requestFocus();
                break;
                case 4:
                tbAnestesiObstetri.requestFocus();
                break;
                case 5:
                tbAnestesiGinekologi.requestFocus();
                break;
                default:
                break;
            }
        } */
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        TampilkanData();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnTambahTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahTindakanActionPerformed
        /*    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanRalan perawatan=new DlgJnsPerawatanRalan(null,false);
        perawatan.emptTeks();
        perawatan.isCek();
        perawatan.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        perawatan.setLocationRelativeTo(internalFrame1);
        perawatan.setAlwaysOnTop(false);
        perawatan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor()); */
    }//GEN-LAST:event_BtnTambahTindakanActionPerformed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isForm2();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbAnestesi2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi2MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi2MouseClicked

    private void tbAnestesi2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi2KeyReleased

    private void ChkInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput2ActionPerformed
        isForm3();
    }//GEN-LAST:event_ChkInput2ActionPerformed

    private void tbAnestesi3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi3MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi3MouseClicked

    private void tbAnestesi3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi3KeyReleased

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
        isForm4();
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void ChkInput4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput4ActionPerformed

    private void ChkInput5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput5ActionPerformed

    private void tbAnestesi4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi4MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi4MouseClicked

    private void tbAnestesi4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi4KeyReleased

    private void tbAnestesi6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi6MouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getData5();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi6MouseClicked

    private void tbAnestesi6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi6KeyReleased

    private void tbAnestesi7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi7MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi7MouseClicked

    private void tbAnestesi7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi7KeyReleased

    private void tbAnestesi8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi8MouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getData7();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi8MouseClicked

    private void tbAnestesi8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi8KeyReleased

    private void ChkInput6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput6ActionPerformed
        isForm7();
    }//GEN-LAST:event_ChkInput6ActionPerformed

    private void tbAnestesi9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi9MouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getData8();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi9MouseClicked

    private void tbAnestesi9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi9KeyReleased

    private void ChkInput7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput7ActionPerformed

    private void DiastolikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiastolikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiastolikKeyPressed

    private void tbAnestesi10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi10MouseClicked
        if (tabMode10.getRowCount() != 0) {
            try {
                getData10();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi10MouseClicked

    private void tbAnestesi10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi10KeyReleased

    private void ChkInput8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput8ActionPerformed

    private void tbAnestesi11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi11MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi11MouseClicked

    private void tbAnestesi11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi11KeyReleased

    private void ChkInput9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput9ActionPerformed

    private void ChkInput10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput10ActionPerformed

    private void ChkInput11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput11ActionPerformed

    private void BtnAsistenBedah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsistenBedah2ActionPerformed
        penginput.emptTeks();
        penginput.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        penginput.setLocationRelativeTo(internalFrame1);
        penginput.setVisible(true);
    }//GEN-LAST:event_BtnAsistenBedah2ActionPerformed

    private void BtnAsistenBedah2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAsistenBedah2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAsistenBedah2KeyPressed

    private void tbAnestesi12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi12MouseClicked
        if (tabMode12.getRowCount() != 0) {
            try {
                getData12();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi12MouseClicked

    private void tbAnestesi12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi12KeyReleased

    private void tbAnestesi13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAnestesi13MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getData6();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbAnestesi13MouseClicked

    private void tbAnestesi13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAnestesi13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAnestesi13KeyReleased

    private void TglPrainduksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglPrainduksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglPrainduksiKeyPressed

    private void TglTeknikAnestesiUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglTeknikAnestesiUmumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglTeknikAnestesiUmumKeyPressed

    private void Scroll16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Scroll16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll16MouseClicked

    private void TglBlokadeRegionalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBlokadeRegionalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglBlokadeRegionalKeyPressed

    private void MnCetakMonitoringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakMonitoringActionPerformed
        if (tbAnestesi9.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("norawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 17).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + " Ditandatangani secara elektronik oleh " + tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 18).toString() + " ID " + (finger.equals("") ? tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 17).toString() : finger) + " " + Valid.SetTgl3(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 7).toString()));

            Valid.MyReportqry(
                    "rptCetakMonitoringAnestesi.jasper",
                    "report",
                    "::[ Laporan Monitoring Anestesi ]::",
                    "SELECT anestesi_monitoring.no_rawat, anestesi_monitoring.tanggal, anestesi_monitoring.o2, anestesi_monitoring.n2o,\n"
                    + "anestesi_monitoring.air, anestesi_monitoring.halothane, anestesi_monitoring.isoflurane, anestesi_monitoring.sevolurane,\n"
                    + "anestesi_monitoring.dml, anestesi_monitoring.tvs, anestesi_monitoring.nadi, anestesi_monitoring.sistolik,\n"
                    + "anestesi_monitoring.diastolik, anestesi_monitoring.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,\n"
                    + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring INNER JOIN pegawai ON anestesi_monitoring.nip = pegawai.nik \n"
                    + "INNER JOIN reg_periksa ON anestesi_monitoring.no_rawat = reg_periksa.no_rawat INNER JOIN \n"
                    + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "WHERE reg_periksa.no_rawat = '" + tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 0).toString() + "'",
                    param
            );

        }
    }//GEN-LAST:event_MnCetakMonitoringActionPerformed

    private void MnCetakMonitoring2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakMonitoring2ActionPerformed
           if (tbAnestesi10.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("norawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            /*
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "SPO2", "ETCO2", "FIO2", "Cairan", "Urin", "Perdarahan", "Kode Pegawai", "Nama Pegawai"
            */
            
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 12).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + " Ditandatangani secara elektronik oleh " + tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 13).toString() + " ID " + (finger.equals("") ? tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 12).toString() : finger) + " " + Valid.SetTgl3(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 12).toString()));

            Valid.MyReportqry(
                    "rptCetakMonitoringAnestesi.jasper",
                    "report",
                    "::[ Laporan Monitoring Anestesi 2 ]::",
                    "SELECT reg_periksa.no_rawat, DATE_FORMAT(anestesi_monitoring2.tanggal,'%d-%m-%Y %H:%i:%s')as tanggal, anestesi_monitoring2.sp02, anestesi_monitoring2.etco2,"
                        + "anestesi_monitoring2.fio2, anestesi_monitoring2.cairan, anestesi_monitoring2.urine, anestesi_monitoring2.perdarahan,"
                        + "anestesi_monitoring2.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring2 INNER JOIN pegawai ON anestesi_monitoring2.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_monitoring2.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "WHERE reg_periksa.no_rawat = '" + tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 0).toString() + "'",
                    param
            );

        }
    }//GEN-LAST:event_MnCetakMonitoring2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMStatusAnestesi dialog = new RMStatusAnestesi(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox Air;
    private widget.ComboBox Airway;
    private widget.ComboBox Airway2;
    private widget.TextBox Alamat;
    private widget.TextBox Alasan;
    private widget.TextBox Alasan2;
    private widget.TextBox Alasan3;
    private widget.TextBox Analgesi;
    private widget.TextBox Anestesi;
    private widget.TextBox Apgar1;
    private widget.TextBox Apgar5;
    private widget.TextBox Bagian;
    private widget.TextBox Bb;
    private widget.TextBox Bb2;
    private widget.Button BtnAll;
    private widget.Button BtnAsistenAnestesi;
    private widget.Button BtnAsistenBedah;
    private widget.Button BtnAsistenBedah1;
    private widget.Button BtnAsistenBedah2;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokterAnestesi;
    private widget.Button BtnDokterBedah;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPegawai;
    private widget.Button BtnPegawai1;
    private widget.Button BtnPegawai2;
    private widget.Button BtnPegawai3;
    private widget.Button BtnPegawai4;
    private widget.Button BtnPegawai5;
    private widget.Button BtnPegawai6;
    private widget.Button BtnPegawai7;
    private widget.Button BtnPegawai8;
    private widget.Button BtnPegawai9;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahTindakan;
    private widget.TextBox Cairan;
    private widget.TextBox CairanLain;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkInput10;
    private widget.CekBox ChkInput11;
    private widget.CekBox ChkInput2;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkInput4;
    private widget.CekBox ChkInput5;
    private widget.CekBox ChkInput6;
    private widget.CekBox ChkInput7;
    private widget.CekBox ChkInput8;
    private widget.CekBox ChkInput9;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox Darah;
    private widget.TextBox DiagnosaPostOp;
    private widget.TextBox DiagnosaPreOp;
    private widget.TextBox Diastolik;
    private widget.TextBox Diuresis;
    private widget.TextBox Dml;
    private widget.TextBox Dosis;
    private widget.TextBox Dosis2;
    private widget.TextBox E;
    private widget.TextBox E2;
    private widget.TextBox ERatio;
    private widget.TextBox Ebl;
    private widget.TextBox Ebv;
    private widget.ComboBox Ett;
    private widget.TextBox Fi02;
    private widget.TextBox Fio2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Halothane;
    private widget.TextBox Ieratio;
    private widget.TextBox Induksi;
    private widget.ComboBox InformConsent;
    private widget.TextBox Instruksi;
    private widget.TextBox Isoflurane;
    private widget.TextBox JenisDarah;
    private widget.ComboBox JenisOperasi;
    private widget.TextBox JenisPembedahan;
    private widget.TextBox Jk;
    private widget.ComboBox Jk2;
    private widget.TextBox Jumlah;
    private widget.TextBox JumlahDarah;
    private widget.TextBox Kamar;
    private widget.ComboBox Kanul;
    private widget.TextBox KdAsistenAnestesi;
    private widget.TextBox KdAsistenBedah;
    private widget.TextBox KdDokterAnestesi;
    private widget.TextBox KdDokterBedah;
    private widget.TextBox KeadaaanUmum;
    private widget.ComboBox KeadaanBayi;
    private widget.ComboBox Kesadaran;
    private widget.ComboBox Kesadaran2;
    private widget.TextBox Koloid;
    private widget.TextBox Komplikasi;
    private widget.TextBox KomponenDarah;
    private widget.TextBox Konsentrasi;
    private widget.TextBox Konsentrasi2;
    private widget.TextBox Kristaloid;
    private widget.editorpane LoadHTML;
    private widget.TextBox LokasiTusukan;
    private widget.TextBox M;
    private widget.TextBox M2;
    private widget.TextBox Mlrr;
    private javax.swing.JMenuItem MnCetakMonitoring;
    private javax.swing.JMenuItem MnCetakMonitoring2;
    private javax.swing.JMenuItem MnCetakStatusAnestesi;
    private widget.TextBox Monitoring;
    private widget.TextBox Monitoring2;
    private widget.TextBox Monitoring2_etco2;
    private widget.TextBox Monitoring2_fio2;
    private widget.TextBox Monitoring2_spo2;
    private widget.TextBox Monitoring4;
    private widget.TextBox N2o2;
    private widget.TextBox NIP10;
    private widget.TextBox NIP11;
    private widget.TextBox NIP2;
    private widget.TextBox NIP3;
    private widget.TextBox NIP4;
    private widget.TextBox NIP5;
    private widget.TextBox NIP6;
    private widget.TextBox NIP7;
    private widget.TextBox NIP8;
    private widget.TextBox NIP9;
    private widget.TextBox NIPPenginput;
    private widget.TextBox Nadi;
    private widget.TextBox Nadi1;
    private widget.TextBox Nadi2;
    private widget.TextBox Nadi_1;
    private widget.TextBox NamaPegawai10;
    private widget.TextBox NamaPegawai11;
    private widget.TextBox NamaPegawai2;
    private widget.TextBox NamaPegawai3;
    private widget.TextBox NamaPegawai4;
    private widget.TextBox NamaPegawai5;
    private widget.TextBox NamaPegawai6;
    private widget.TextBox NamaPegawai7;
    private widget.TextBox NamaPegawai8;
    private widget.TextBox NamaPegawai9;
    private widget.TextBox NamaPenginput;
    private widget.TextBox NmAsistenAnestesi;
    private widget.TextBox NmAsistenBedah;
    private widget.TextBox NmDokterAnestesi;
    private widget.TextBox NmDokterBedah;
    private widget.TextBox NmPegawaiPremedikasi;
    private widget.TextBox Noradernalin;
    private widget.TextBox O2;
    private widget.TextBox O22;
    private widget.TextBox Obat;
    private widget.TextBox ObatAnestesi;
    private widget.TextBox ObatTambahan;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput10;
    private javax.swing.JPanel PanelInput11;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private javax.swing.JPanel PanelInput4;
    private javax.swing.JPanel PanelInput5;
    private javax.swing.JPanel PanelInput6;
    private javax.swing.JPanel PanelInput7;
    private javax.swing.JPanel PanelInput8;
    private javax.swing.JPanel PanelInput9;
    private widget.TextBox Pasca_Spo2;
    private widget.TextBox Pb2;
    private widget.TextBox Peep;
    private widget.TextBox Peep2;
    private widget.TextBox Pegawaipremedikasi;
    private widget.TextBox Pemeliharaan;
    private widget.TextBox Pemeliharaan2;
    private widget.TextBox Pemeliharaan3;
    private widget.ComboBox PengaturanNafas;
    private widget.TextBox Perdarahan;
    private widget.TextBox Perdarahan2;
    private widget.TextBox PerhitunganCairan;
    private widget.ComboBox Pernapasan;
    private widget.TextBox PerubahanTeknikAnestesi;
    private widget.TextBox PerubahanTeknikAnestesi2;
    private widget.TextBox PerubahanTeknikAnestesi3;
    private widget.ComboBox Pindah;
    private widget.TextBox Pip;
    private widget.TextBox Pip2;
    private widget.ComboBox Posisi;
    private widget.TextBox Ps;
    private widget.TextBox RR2;
    private widget.ComboBox Ramsaya;
    private widget.ComboBox Regular;
    private widget.ComboBox Respirasi;
    private widget.ComboBox Respirasi2;
    private widget.TextBox Rr;
    private widget.TextBox Rr2;
    private widget.TextBox Rr3;
    private widget.ComboBox Rute;
    private widget.ComboBox RuteInduksi;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ComboBox Selang2;
    private widget.TextBox Sevo;
    private widget.TextBox Sistolik;
    private widget.TextBox Spo2;
    private widget.TextBox Spo22;
    private widget.ComboBox StatusFisik;
    private widget.TextBox Suhu;
    private widget.TextBox Support;
    private widget.TextBox Support2;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabTeknikAnestesi;
    private widget.TextBox Td;
    private widget.ComboBox Teknik;
    private widget.ComboBox TeknikCombine;
    private widget.ComboBox TeknikKhusus;
    private widget.TextBox Tensi;
    private widget.Tanggal TglBlokadeRegional;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglOperasi;
    private widget.Tanggal TglPrainduksi;
    private widget.Tanggal TglPremedikasi;
    private widget.Tanggal TglTeknikAnestesiUmum;
    private widget.TextBox TidalVolume;
    private widget.TextBox Tindakan;
    private widget.TextBox TipePernapasan;
    private widget.TextBox TipePernapasan2;
    private widget.TextBox Trigger;
    private widget.TextBox Tvs;
    private widget.ComboBox Ukuran;
    private widget.TextBox Urin;
    private widget.TextBox Urine;
    private widget.TextBox V;
    private widget.TextBox V2;
    private widget.TextBox Vasokonstriktor;
    private widget.TextBox Ventilator;
    private widget.TextBox Vetilator;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator58;
    private javax.swing.JSeparator jSeparator59;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator60;
    private javax.swing.JSeparator jSeparator61;
    private javax.swing.JSeparator jSeparator62;
    private javax.swing.JSeparator jSeparator63;
    private javax.swing.JSeparator jSeparator64;
    private javax.swing.JSeparator jSeparator65;
    private javax.swing.JSeparator jSeparator66;
    private javax.swing.JSeparator jSeparator67;
    private javax.swing.JSeparator jSeparator68;
    private javax.swing.JSeparator jSeparator69;
    private javax.swing.JSeparator jSeparator7;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.TextBox o22;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass20;
    private widget.panelisi panelGlass21;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass8;
    private widget.Table tbAnestesi;
    private widget.Table tbAnestesi10;
    private widget.Table tbAnestesi11;
    private widget.Table tbAnestesi12;
    private widget.Table tbAnestesi13;
    private widget.Table tbAnestesi2;
    private widget.Table tbAnestesi3;
    private widget.Table tbAnestesi4;
    private widget.Table tbAnestesi6;
    private widget.Table tbAnestesi7;
    private widget.Table tbAnestesi8;
    private widget.Table tbAnestesi9;
    // End of variables declaration//GEN-END:variables

    public void tampilMasuk() {
        /*    Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,anestesi_masuk.tanggaloperasi,"+
                        "anestesi_masuk.kd_dokter,anestesi_masuk.tanggaloperasi_operasi,anestesi_masuk.diagnosa,anestesi_masuk.rencana_tindakan,anestesi_masuk.tb,"+
                        "anestesi_masuk.bb,anestesi_masuk.td,anestesi_masuk.io2,anestesi_masuk.nadi,anestesi_masuk.pernapasan,anestesi_masuk.suhu,"+
                        "anestesi_masuk.fisik_cardiovasculer,anestesi_masuk.fisik_paru,anestesi_masuk.fisik_abdomen,anestesi_masuk.fisik_extrimitas,"+
                        "anestesi_masuk.fisik_endokrin,anestesi_masuk.fisik_ginjal,anestesi_masuk.fisik_obatobatan,anestesi_masuk.fisik_laborat,"+
                        "anestesi_masuk.fisik_penunjang,anestesi_masuk.riwayat_penyakit_alergiobat,anestesi_masuk.riwayat_penyakit_alergilainnya,"+
                        "anestesi_masuk.riwayat_penyakit_terapi,anestesi_masuk.riwayat_kebiasaan_merokok,anestesi_masuk.riwayat_kebiasaan_ket_merokok,"+
                        "anestesi_masuk.riwayat_kebiasaan_alkohol,anestesi_masuk.riwayat_kebiasaan_ket_alkohol,anestesi_masuk.riwayat_kebiasaan_obat,"+
                        "anestesi_masuk.riwayat_kebiasaan_ket_obat,anestesi_masuk.riwayat_medis_cardiovasculer,anestesi_masuk.riwayat_medis_respiratory,"+
                        "anestesi_masuk.riwayat_medis_endocrine,anestesi_masuk.riwayat_medis_lainnya,anestesi_masuk.asa,anestesi_masuk.puasa,"+
                        "anestesi_masuk.rencana_anestesi,anestesi_masuk.rencana_perawatan,anestesi_masuk.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join anestesi_masuk on reg_periksa.no_rawat=anestesi_masuk.no_rawat "+
                        "inner join dokter on anestesi_masuk.kd_dokter=dokter.kd_dokter where "+
                        "anestesi_masuk.tanggaloperasi between ? and ? order by anestesi_masuk.tanggaloperasi");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,anestesi_masuk.tanggaloperasi,"+
                        "anestesi_masuk.kd_dokter,anestesi_masuk.tanggaloperasi_operasi,anestesi_masuk.diagnosa,anestesi_masuk.rencana_tindakan,anestesi_masuk.tb,"+
                        "anestesi_masuk.bb,anestesi_masuk.td,anestesi_masuk.io2,anestesi_masuk.nadi,anestesi_masuk.pernapasan,anestesi_masuk.suhu,"+
                        "anestesi_masuk.fisik_cardiovasculer,anestesi_masuk.fisik_paru,anestesi_masuk.fisik_abdomen,anestesi_masuk.fisik_extrimitas,"+
                        "anestesi_masuk.fisik_endokrin,anestesi_masuk.fisik_ginjal,anestesi_masuk.fisik_obatobatan,anestesi_masuk.fisik_laborat,"+
                        "anestesi_masuk.fisik_penunjang,anestesi_masuk.riwayat_penyakit_alergiobat,anestesi_masuk.riwayat_penyakit_alergilainnya,"+
                        "anestesi_masuk.riwayat_penyakit_terapi,anestesi_masuk.riwayat_kebiasaan_merokok,anestesi_masuk.riwayat_kebiasaan_ket_merokok,"+
                        "anestesi_masuk.riwayat_kebiasaan_alkohol,anestesi_masuk.riwayat_kebiasaan_ket_alkohol,anestesi_masuk.riwayat_kebiasaan_obat,"+
                        "anestesi_masuk.riwayat_kebiasaan_ket_obat,anestesi_masuk.riwayat_medis_cardiovasculer,anestesi_masuk.riwayat_medis_respiratory,"+
                        "anestesi_masuk.riwayat_medis_endocrine,anestesi_masuk.riwayat_medis_lainnya,anestesi_masuk.asa,anestesi_masuk.puasa,"+
                        "anestesi_masuk.rencana_anestesi,anestesi_masuk.rencana_perawatan,anestesi_masuk.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join anestesi_masuk on reg_periksa.no_rawat=anestesi_masuk.no_rawat "+
                        "inner join dokter on anestesi_masuk.kd_dokter=dokter.kd_dokter where "+
                        "anestesi_masuk.tanggaloperasi between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "anestesi_masuk.kd_dokter like ? or dokter.nm_dokter like ?) order by anestesi_masuk.tanggaloperasi");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("tanggal_operasi"),rs.getString("diagnosa"),rs.getString("rencana_tindakan"),rs.getString("tb"),rs.getString("bb"),rs.getString("td"),rs.getString("io2"),rs.getString("nadi"),rs.getString("pernapasan"),
                        rs.getString("suhu"),rs.getString("fisik_cardiovasculer"),rs.getString("fisik_paru"),rs.getString("fisik_abdomen"),rs.getString("fisik_extrimitas"),rs.getString("fisik_endokrin"),rs.getString("fisik_ginjal"),
                        rs.getString("fisik_obatobatan"),rs.getString("fisik_laborat"),rs.getString("fisik_penunjang"),rs.getString("riwayat_penyakit_alergiobat"),rs.getString("riwayat_penyakit_alergilainnya"),
                        rs.getString("riwayat_penyakit_terapi"),rs.getString("riwayat_kebiasaan_merokok"),rs.getString("riwayat_kebiasaan_ket_merokok"),rs.getString("riwayat_kebiasaan_alkohol"),rs.getString("riwayat_kebiasaan_ket_alkohol"),
                        rs.getString("riwayat_kebiasaan_obat"),rs.getString("riwayat_kebiasaan_ket_obat"),rs.getString("riwayat_medis_cardiovasculer"),rs.getString("riwayat_medis_respiratory"),rs.getString("riwayat_medis_endocrine"),
                        rs.getString("riwayat_medis_lainnya"),rs.getString("asa"),rs.getString("puasa"),rs.getString("rencana_anestesi"),rs.getString("rencana_perawatan"),rs.getString("catatan_khusus")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount()); */
    }

    public void emptTeks() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                DiagnosaPreOp.setText("");
                JenisPembedahan.setText("");
                Bb.setText("");
                Kamar.setText("");
                Bagian.setText("");
                KdDokterBedah.setText("");
                NmDokterBedah.setText("");
                KdAsistenBedah.setText("");
                NmAsistenBedah.setText("");
                KdDokterAnestesi.setText("");
                NmDokterAnestesi.setText("");
                KdAsistenAnestesi.setText("");
                NmAsistenAnestesi.setText("");
                E.setText("");
                M.setText("");
                V.setText("");
                Td.setText("");
                Nadi.setText("");
                Rr.setText("");
                O2.setText("");
                Spo2.setText("");
                TipePernapasan.setText("");
                Support.setText("");
                NIPPenginput.setText("");
                NamaPenginput.setText("");
                break;
            case 1:
                Obat.setText("");
                Dosis.setText("");
                Pegawaipremedikasi.setText("");
                NmPegawaiPremedikasi.setText("");
                break;
            case 2:
                E2.setText("");
                M2.setText("");
                V2.setText("");
                Tensi.setText("");
                Nadi1.setText("");
                Rr2.setText("");
                o22.setText("");
                Spo22.setText("");
                TipePernapasan2.setText("");
                Support2.setText("");
                NIP2.setText("");
                NamaPegawai2.setText("");
                break;
            case 3:
                TabTeknikAnestesiBaru();
                break;
            case 4:
                Komplikasi.setText("");
                Tindakan.setText("");
                NIP6.setText("");
                NamaPegawai6.setText("");
                break;
            case 5:
                O22.setText("");
                N2o2.setText("");
                Air.setText("");
                Halothane.setText("");
                Isoflurane.setText("");
                Sevo.setText("");
                Dml.setText("");
                Tvs.setText("");
                Nadi_1.setText("");
                Sistolik.setText("");
                Diastolik.setText("");
                NIP7.setText("");
                NamaPegawai7.setText("");
                break;
            case 6:
                Monitoring2_spo2.setText("");
                Monitoring2_etco2.setText("");
                Monitoring2_fio2.setText("");
                Cairan.setText("");
                Urine.setText("");
                Perdarahan.setText("");
                NIP8.setText("");
                NamaPegawai8.setText("");
                break;
            case 7:
                Kristaloid.setText("");
                Koloid.setText("");
                Darah.setText("");
                KomponenDarah.setText("");
                Perdarahan2.setText("");
                Diuresis.setText("");
                CairanLain.setText("");
                NIP9.setText("");
                NamaPegawai9.setText("");
                Urin.setText("");
                break;
            case 8:
                Apgar1.setText("");
                Apgar5.setText("");
                Bb2.setText("");
                Pb2.setText("");
                NIP10.setText("");
                NamaPegawai10.setText("");
                break;
            case 9:
                DiagnosaPostOp.setText("");
                KeadaaanUmum.setText("");
                Nadi2.setText("");
                RR2.setText("");
                Suhu.setText("");
                Pasca_Spo2.setText("");
                Instruksi.setText("");
                Vetilator.setText("");
                TidalVolume.setText("");
                Mlrr.setText("");
                Ieratio.setText("");
                Peep2.setText("");
                Pip2.setText("");
                Ps.setText("");
                Fio2.setText("");
                Trigger.setText("");
                NIP11.setText("");
                NamaPegawai11.setText("");
                break;
            default:

        }

    }

    private void getData() {
        if (tbAnestesi.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 5).toString());
            DiagnosaPreOp.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 6).toString());
            JenisPembedahan.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 7).toString());
            Bb.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 8).toString());
            Kamar.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 9).toString());
            JenisOperasi.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 10).toString());
            Bagian.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 11).toString());
            InformConsent.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 12).toString());
            KdDokterBedah.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 14).toString());
            NmDokterBedah.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 15).toString());
            KdAsistenBedah.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 16).toString());
            NmAsistenBedah.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 17).toString());
            KdDokterAnestesi.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 18).toString());
            NmDokterAnestesi.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 19).toString());
            KdAsistenAnestesi.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 20).toString());
            NmAsistenAnestesi.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 21).toString());
            Kesadaran.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 22).toString());
            E.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 23).toString());
            M.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 24).toString());
            V.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 25).toString());
            Td.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 26).toString());
            Nadi.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 27).toString());
            Respirasi.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 28).toString());
            Rr.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 29).toString());
            Kanul.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 30).toString());
            O2.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 31).toString());
            Spo2.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 32).toString());
            TipePernapasan.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 32).toString());
            Regular.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 34).toString());
            Airway.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 35).toString());
            Support.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 36).toString());
            StatusFisik.setSelectedItem(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 37).toString());
            NIPPenginput.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 38).toString());
            NamaPenginput.setText(tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 39).toString());
            Valid.SetTgl2(TglOperasi, tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 13).toString());
        }
    }

    private void getData2() {
        if (tbAnestesi2.getSelectedRow() != -1) {
            TNoRw.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 5).toString());
            Rute.setSelectedItem(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 6).toString());
            Obat.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 8).toString());
            Dosis.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 9).toString());
            Ramsaya.setSelectedItem(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 10).toString());
            Pegawaipremedikasi.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 11).toString());
            NmPegawaiPremedikasi.setText(tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 12).toString());
            Valid.SetTgl2(TglOperasi, tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 7).toString());

        }
    }

    private void getData3() {
        if (tbAnestesi3.getSelectedRow() != -1) {
            /*
                    "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat","Tanggal", "Kesadaran", "E", "M", "V", "Tensi", "Nadi",
            "Respirasi", "RR", "Alat nafas", "O2", "SpO2", "Tipe Pernapasan", "Keterangan Pernapasan", "Support", "Kode Pegawai", "Nama Pegawai"
             */
            TNoRw.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 5).toString());

            Kesadaran2.setSelectedItem(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 7).toString());
            E2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 8).toString());
            M2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 9).toString());
            V2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 10).toString());
            Tensi.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 11).toString());
            Nadi1.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 12).toString());
            Respirasi2.setSelectedItem(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 13).toString());
            Rr2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 14).toString());
            Selang2.setSelectedItem(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 15).toString());
            o22.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 16).toString());
            Spo22.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 17).toString());
            TipePernapasan2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 18).toString());
            Pernapasan.setSelectedItem(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 19).toString());
            Support2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 20).toString());
            NIP2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 21).toString());
            NamaPegawai2.setText(tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 22).toString());
            Valid.SetTgl2(TglPrainduksi, tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 6).toString());

        }
    }

    private void getData4() {
        if (tbAnestesi4.getSelectedRow() != -1) {
            TNoRw.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 5).toString());
            Induksi.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 7).toString());
            RuteInduksi.setSelectedItem(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 8).toString());
            PengaturanNafas.setSelectedItem(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 9).toString());
            Ventilator.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 10).toString());
            Rr3.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 11).toString());
            ERatio.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 12).toString());
            Peep.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 13).toString());
            Pip.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 14).toString());
            Fi02.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 15).toString());
            TeknikKhusus.setSelectedItem(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 16).toString());
            Pemeliharaan.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 17).toString());
            PerhitunganCairan.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 18).toString());
            Ebv.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 19).toString());
            Ebl.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 20).toString());
            JenisDarah.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 21).toString());
            JumlahDarah.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 22).toString());
            Monitoring.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 23).toString());
            PerubahanTeknikAnestesi.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 24).toString());
            Alasan.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 25).toString());
            NIP3.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 26).toString());
            NamaPegawai3.setText(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 27).toString());
            Valid.SetTgl2(TglPrainduksi, tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 6).toString());

        }
    }

    private void getData5() {
        if (tbAnestesi6.getSelectedRow() != -1) {
            /*
            "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat","Tanggal", "Teknik", "Lokasi Tusukan", "Anelgesi Setinggi Segmen",
            "Anestesi Lokal", "Konsentrasi", "Jumlah", "Obat Tambahan", "Dosis", "Adrenalin", "Nor Adrenalin", "Konsentrasi", "Pemeliharaan", "Monitoring",
            "Perubahan Teknik Anestesi", "Alasan", "NIP", "Nama Pegawai"
             */

            TNoRw.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 5).toString());
            Teknik.setSelectedItem(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 7).toString());
            LokasiTusukan.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 8).toString());
            Analgesi.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 9).toString());
            Anestesi.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 10).toString());
            Konsentrasi.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 11).toString());
            Jumlah.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 12).toString());
            ObatTambahan.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 13).toString());
            Dosis2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 14).toString());
            Vasokonstriktor.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 15).toString());
            Noradernalin.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 16).toString());
            Konsentrasi2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 17).toString());
            Pemeliharaan2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 18).toString());
            Monitoring2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 19).toString());
            PerubahanTeknikAnestesi2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 20).toString());
            Alasan2.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 21).toString());
            NIP4.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 22).toString());
            NamaPegawai4.setText(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 23).toString());
            Valid.SetTgl2(TglBlokadeRegional, tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 6).toString());

        }
    }

    private void getData6() {
        if (tbAnestesi7.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 5).toString());

            Teknik.setSelectedItem(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 7).toString());
            ObatAnestesi.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 8).toString());
            Pemeliharaan3.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 9).toString());
            Monitoring4.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 10).toString());
            PerubahanTeknikAnestesi3.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 11).toString());
            Alasan3.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 12).toString());
            NIP5.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 13).toString());
            NamaPegawai5.setText(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 14).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 6).toString());

        }
    }

    private void getData7() {
        if (tbAnestesi8.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 5).toString());

            Posisi.setSelectedItem(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 7).toString());
            Airway2.setSelectedItem(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 8).toString());
            Ett.setSelectedItem(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 9).toString());
            Ukuran.setSelectedItem(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 10).toString());
            Komplikasi.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 11).toString());
            Tindakan.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 12).toString());
            NIP6.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 13).toString());
            NamaPegawai6.setText(tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 14).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 6).toString());

        }
    }

    private void getData8() {
        if (tbAnestesi9.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 5).toString());

            O22.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 7).toString());
            N2o2.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 8).toString());
            Air.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 9).toString());
            Halothane.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 10).toString());
            Isoflurane.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 11).toString());
            Sevo.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 12).toString());
            Dml.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 13).toString());
            Tvs.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 14).toString());
            Nadi_1.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 15).toString());
            Sistolik.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 16).toString());
            Diastolik.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 17).toString());
            NIP7.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 18).toString());
            NamaPegawai7.setText(tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 19).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 6).toString());

        }
    }

    private void getData9() {
        if (tbAnestesi10.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 5).toString());

            Monitoring2_spo2.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 7).toString());
            Monitoring2_etco2.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 8).toString());
            Monitoring2_fio2.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 9).toString());
            Cairan.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 10).toString());
            Urine.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 11).toString());
            Perdarahan.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 12).toString());
            NIP8.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 13).toString());
            NamaPegawai8.setText(tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 14).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 6).toString());

        }
    }

    private void getData10() {
        if (tbAnestesi11.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 5).toString());

            Kristaloid.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 7).toString());
            Koloid.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 8).toString());
            Darah.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 9).toString());
            KomponenDarah.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 10).toString());
            Urin.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 11).toString());
            Perdarahan2.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 12).toString());
            Diuresis.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 13).toString());
            CairanLain.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 14).toString());
            NIP9.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 15).toString());
            NamaPegawai9.setText(tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 16).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 6).toString());

        }
    }

    private void getData11() {
        if (tbAnestesi12.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 5).toString());

            KeadaanBayi.setSelectedItem(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 7).toString());
            Jk2.setSelectedItem(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 8).toString());
            Apgar1.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 9).toString());
            Apgar5.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 10).toString());
            Bb2.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 11).toString());
            Pb2.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 12).toString());
            NIP10.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 13).toString());
            NamaPegawai10.setText(tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 14).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 6).toString());

        }
    }

    private void getData12() {
        if (tbAnestesi13.getSelectedRow() != -1) {

            TNoRw.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 1).toString());
            TPasien.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 2).toString());
            TglLahir.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 3).toString());
            Jk.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 4).toString());
            Alamat.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 5).toString());

            DiagnosaPostOp.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 7).toString());
            Pindah.setSelectedItem(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 8).toString());
            KeadaaanUmum.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 9).toString());
            Nadi2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 10).toString());
            RR2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 11).toString());
            Suhu.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 12).toString());
            Pasca_Spo2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 13).toString());
            Instruksi.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 14).toString());
            Vetilator.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 15).toString());
            TidalVolume.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 16).toString());
            Mlrr.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 17).toString());
            Ieratio.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 18).toString());
            Peep2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 19).toString());
            Pip2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 20).toString());
            Ps.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 21).toString());
            Fio2.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 22).toString());
            Trigger.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 23).toString());
            NIP11.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 24).toString());
            NamaPegawai11.setText(tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 25).toString());

            Valid.SetTgl2(DTPTgl, tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString());

        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.almt_pj "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    Alamat.setText(rs.getString("almt_pj"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
//        TCari.setText(norwt);
//        DTPCari2.setDate(tgl2);    
        isRawat();
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnHapus.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
        if (akses.getjml2() >= 1) {
            KdDokterBedah.setEditable(false);
            BtnDokterBedah.setEnabled(false);
            KdDokterBedah.setText(akses.getkode());
            NmDokterBedah.setText(dokter.tampil3(KdDokterBedah.getText()));

        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        /*    if(Sequel.queryu2tf("delete from anestesi_masuk where no_rawat=? and tanggal=?",2,new String[]{
            tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(),0).toString(),tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(),7).toString()
        })==true){
            tabMode.removeRow(tbAnestesi.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        } */
    }

    private void ganti() {
        if (Sequel.mengedittf("anestesi_masuk", "no_rawat=? and tanggaloperasi=?", "no_rawat=?,diagnosapreop=?,jenispembedahan=?,bb=?,kamar=?,jenisoperasi=?,bagian=?,informconsent=?,tanggaloperasi=?,kd_dokter=?,"
                + "asistenbedah=?,dokteranestesi=?,asistenanestesi=?,kesadaran=?,e=?,m=?,v=?,td=?,nadi=?,respirasi=?,rr=?,selang=?,o2=?,spo2=?,tipepernapasan=?,pernapasan=?,airway=?,"
                + "support=?,statusfisik=?,nip=?", 32, new String[]{
                    TNoRw.getText(), DiagnosaPreOp.getText(), JenisPembedahan.getText(), Bb.getText(), Kamar.getText(), JenisOperasi.getSelectedItem().toString(), Bagian.getText(), InformConsent.getSelectedItem().toString(),
                    Valid.SetTgl(TglOperasi.getSelectedItem() + "") + " " + TglOperasi.getSelectedItem().toString().substring(11, 19), KdDokterBedah.getText(), KdAsistenBedah.getText(), KdDokterAnestesi.getText(),
                    KdAsistenAnestesi.getText(), Kesadaran.getSelectedItem().toString(), E.getText(), M.getText(), V.getText(), Td.getText(), Nadi.getText(), Respirasi.getSelectedItem().toString(),
                    Rr.getText(), Kanul.getSelectedItem().toString(), O2.getText(), Spo2.getText(), TipePernapasan.getText(), Regular.getSelectedItem().toString(), Airway.getSelectedItem().toString(),
                    Support.getText(), StatusFisik.getSelectedItem().toString(), NIPPenginput.getText(), tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 0).toString(), tbAnestesi.getValueAt(tbAnestesi.getSelectedRow(), 13).toString()
                }) == true) {
            tbAnestesi.setValueAt(TNoRw.getText(), tbAnestesi.getSelectedRow(), 0);
            tbAnestesi.setValueAt(TNoRM.getText(), tbAnestesi.getSelectedRow(), 1);
            tbAnestesi.setValueAt(TPasien.getText(), tbAnestesi.getSelectedRow(), 2);
            tbAnestesi.setValueAt(TglLahir.getText(), tbAnestesi.getSelectedRow(), 3);
            tbAnestesi.setValueAt(Jk.getText(), tbAnestesi.getSelectedRow(), 4);
            tbAnestesi.setValueAt(Alamat.getText(), tbAnestesi.getSelectedRow(), 5);
            tbAnestesi.setValueAt(DiagnosaPreOp.getText(), tbAnestesi.getSelectedRow(), 6);
            tbAnestesi.setValueAt(JenisPembedahan.getText(), tbAnestesi.getSelectedRow(), 7);
            tbAnestesi.setValueAt(Bb.getText(), tbAnestesi.getSelectedRow(), 8);
            tbAnestesi.setValueAt(Kamar.getText(), tbAnestesi.getSelectedRow(), 9);
            tbAnestesi.setValueAt(JenisOperasi.getSelectedItem(), tbAnestesi.getSelectedRow(), 10);
            tbAnestesi.setValueAt(Bagian.getText(), tbAnestesi.getSelectedRow(), 11);
            tbAnestesi.setValueAt(InformConsent.getSelectedItem(), tbAnestesi.getSelectedRow(), 12);
            tbAnestesi.setValueAt(KdDokterBedah.getText(), tbAnestesi.getSelectedRow(), 14);
            tbAnestesi.setValueAt(NmDokterBedah.getText(), tbAnestesi.getSelectedRow(), 15);
            tbAnestesi.setValueAt(KdAsistenBedah.getText(), tbAnestesi.getSelectedRow(), 16);
            tbAnestesi.setValueAt(NmAsistenBedah.getText(), tbAnestesi.getSelectedRow(), 17);
            tbAnestesi.setValueAt(KdDokterAnestesi.getText(), tbAnestesi.getSelectedRow(), 18);
            tbAnestesi.setValueAt(NmDokterAnestesi.getText(), tbAnestesi.getSelectedRow(), 19);
            tbAnestesi.setValueAt(KdAsistenAnestesi.getText(), tbAnestesi.getSelectedRow(), 20);
            tbAnestesi.setValueAt(NmAsistenAnestesi.getText(), tbAnestesi.getSelectedRow(), 21);
            tbAnestesi.setValueAt(Kesadaran.getSelectedItem(), tbAnestesi.getSelectedRow(), 22);
            tbAnestesi.setValueAt(E.getText(), tbAnestesi.getSelectedRow(), 23);
            tbAnestesi.setValueAt(M.getText(), tbAnestesi.getSelectedRow(), 24);
            tbAnestesi.setValueAt(V.getText(), tbAnestesi.getSelectedRow(), 25);
            tbAnestesi.setValueAt(Td.getText(), tbAnestesi.getSelectedRow(), 26);
            tbAnestesi.setValueAt(Nadi.getText(), tbAnestesi.getSelectedRow(), 27);
            tbAnestesi.setValueAt(Respirasi.getSelectedItem(), tbAnestesi.getSelectedRow(), 28);
            tbAnestesi.setValueAt(Rr.getText(), tbAnestesi.getSelectedRow(), 29);
            tbAnestesi.setValueAt(Kanul.getSelectedItem(), tbAnestesi.getSelectedRow(), 30);
            tbAnestesi.setValueAt(O2.getText(), tbAnestesi.getSelectedRow(), 31);
            tbAnestesi.setValueAt(Spo2.getText(), tbAnestesi.getSelectedRow(), 32);
            tbAnestesi.setValueAt(TipePernapasan.getText(), tbAnestesi.getSelectedRow(), 33);
            tbAnestesi.setValueAt(Regular.getSelectedItem(), tbAnestesi.getSelectedRow(), 34);
            tbAnestesi.setValueAt(Airway.getSelectedItem(), tbAnestesi.getSelectedRow(), 35);
            tbAnestesi.setValueAt(Support.getText(), tbAnestesi.getSelectedRow(), 36);
            tbAnestesi.setValueAt(StatusFisik.getSelectedItem(), tbAnestesi.getSelectedRow(), 37);
            tbAnestesi.setValueAt(NIPPenginput.getText(), tbAnestesi.getSelectedRow(), 38);
            tbAnestesi.setValueAt(NamaPenginput.getText(), tbAnestesi.getSelectedRow(), 39);
            tbAnestesi.setValueAt(Valid.SetTgl(TglOperasi.getSelectedItem() + "") + " " + TglOperasi.getSelectedItem().toString().substring(11, 19), tbAnestesi.getSelectedRow(), 13);
            emptTeks();
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 320));
            panelGlass12.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass12.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isForm2() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 120));
            panelGlass13.setVisible(true);
            ChkInput1.setVisible(true);
        } else if (ChkInput1.isSelected() == false) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass13.setVisible(false);
            ChkInput1.setVisible(true);
        }
    }

    private void isForm3() {
        if (ChkInput2.isSelected() == true) {
            ChkInput2.setVisible(false);
            PanelInput2.setPreferredSize(new Dimension(WIDTH, 180));
            panelGlass14.setVisible(true);
            ChkInput2.setVisible(true);
        } else if (ChkInput2.isSelected() == false) {
            ChkInput2.setVisible(false);
            PanelInput2.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass14.setVisible(false);
            ChkInput2.setVisible(true);
        }
    }

    private void isForm4() {
        if (ChkInput3.isSelected() == true) {
            ChkInput3.setVisible(false);
            PanelInput3.setPreferredSize(new Dimension(WIDTH, 180));
            panelGlass15.setVisible(true);
            ChkInput3.setVisible(true);
        } else if (ChkInput3.isSelected() == false) {
            ChkInput3.setVisible(false);
            PanelInput3.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass15.setVisible(false);
            ChkInput3.setVisible(true);
        }
    }

    private void isForm7() {
        if (ChkInput6.isSelected() == true) {
            ChkInput6.setVisible(false);
            PanelInput6.setPreferredSize(new Dimension(WIDTH, 180));
            panelGlass18.setVisible(true);
            ChkInput6.setVisible(true);
        } else if (ChkInput6.isSelected() == false) {
            ChkInput6.setVisible(false);
            PanelInput6.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass18.setVisible(false);
            ChkInput6.setVisible(true);
        }
    }

    private void isForm11() {
        if (ChkInput11.isSelected() == true) {
            ChkInput11.setVisible(false);
            PanelInput11.setPreferredSize(new Dimension(WIDTH, 230));
            panelGlass23.setVisible(true);
            ChkInput11.setVisible(true);
        } else if (ChkInput11.isSelected() == false) {
            ChkInput11.setVisible(false);
            PanelInput11.setPreferredSize(new Dimension(WIDTH, 20));
            panelGlass23.setVisible(false);
            ChkInput11.setVisible(true);
        }
    }

    private void TampilkanData() {
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
            case 2:
                tampil3();
                break;
            case 3:
                tampil4();
                break;
            case 4:
                tampil5();
                break;
            case 5:
                tampil6();
                break;
            case 6:
                tampil7();
                break;
            case 7:
                tampil8();
                break;
            case 8:
                tampil9();
                break;
            case 9:
                tampil10();
                break;
            default:
                break;
        }
    }

    private void tampil() {
        Valid.tabelKosong(tabMode);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_masuk.no_rawat,anestesi_masuk.diagnosapreop,anestesi_masuk.jenispembedahan,anestesi_masuk.bb,anestesi_masuk.kamar,anestesi_masuk.jenisoperasi,"
                        + "anestesi_masuk.bagian,anestesi_masuk.informconsent,anestesi_masuk.tanggaloperasi,anestesi_masuk.kd_dokter,anestesi_masuk.asistenbedah,anestesi_masuk.dokteranestesi,anestesi_masuk.asistenanestesi,"
                        + "anestesi_masuk.kesadaran,anestesi_masuk.e,anestesi_masuk.m,anestesi_masuk.v,anestesi_masuk.td,anestesi_masuk.nadi,anestesi_masuk.respirasi,anestesi_masuk.rr,anestesi_masuk.selang,"
                        + "anestesi_masuk.o2,anestesi_masuk.spo2,anestesi_masuk.tipepernapasan,anestesi_masuk.pernapasan,anestesi_masuk.airway,anestesi_masuk.support,anestesi_masuk.statusfisik,reg_periksa.no_rkm_medis,"
                        + "pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.almt_pj,anestesi_masuk.nip FROM anestesi_masuk INNER JOIN reg_periksa ON anestesi_masuk.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_masuk.tanggaloperasi between ? and ? order by anestesi_masuk.tanggaloperasi");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_masuk.no_rawat,anestesi_masuk.diagnosapreop,anestesi_masuk.jenispembedahan,anestesi_masuk.bb,anestesi_masuk.kamar,anestesi_masuk.jenisoperasi,"
                        + "anestesi_masuk.bagian,anestesi_masuk.informconsent,anestesi_masuk.tanggaloperasi,anestesi_masuk.kd_dokter,anestesi_masuk.asistenbedah,anestesi_masuk.dokteranestesi,anestesi_masuk.asistenanestesi,"
                        + "anestesi_masuk.kesadaran,anestesi_masuk.e,anestesi_masuk.m,anestesi_masuk.v,anestesi_masuk.td,anestesi_masuk.nadi,anestesi_masuk.respirasi,anestesi_masuk.rr,anestesi_masuk.selang,"
                        + "anestesi_masuk.o2,anestesi_masuk.spo2,anestesi_masuk.tipepernapasan,anestesi_masuk.pernapasan,anestesi_masuk.airway,anestesi_masuk.support,anestesi_masuk.statusfisik,reg_periksa.no_rkm_medis,"
                        + "pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.almt_pj,anestesi_masuk.nip FROM anestesi_masuk INNER JOIN reg_periksa ON anestesi_masuk.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_masuk.tanggaloperasi between ? and ? order by anestesi_masuk.tanggaloperasi");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("tanggaloperasi");
                    String formattedDate = dateFormat.format(timestamp);

                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("diagnosapreop"), rs.getString("jenispembedahan"), rs.getString("bb"),
                        rs.getString("kamar"), rs.getString("jenisoperasi"), rs.getString("bagian"), rs.getString("informconsent"), formattedDate, rs.getString("kd_dokter"), dokter.tampil3(rs.getString("kd_dokter")),
                        rs.getString("asistenbedah"), petugas.tampil3(rs.getString("asistenbedah")), rs.getString("dokteranestesi"), dokter.tampil3(rs.getString("dokteranestesi")), rs.getString("asistenanestesi"), petugas.tampil3(rs.getString("asistenanestesi")),
                        rs.getString("kesadaran"), rs.getString("e"), rs.getString("m"), rs.getString("v"), rs.getString("td"), rs.getString("nadi"),
                        rs.getString("respirasi"), rs.getString("rr"), rs.getString("selang"), rs.getString("o2"), rs.getString("spo2"), rs.getString("tipepernapasan"), rs.getString("pernapasan"), rs.getString("airway"), rs.getString("support"),
                        rs.getString("statusfisik"), rs.getString("nip"), pegawai.tampil3(rs.getString("nip"))
                    });

                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void TabTeknikAnestesiSimpan() {
        switch (TabTeknikAnestesi.getSelectedIndex()) {
            case 0:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Induksi.getText().trim().equals("")) {
                    Valid.textKosong(Induksi, "Induksi");
                } else if (Ventilator.getText().trim().equals("")) {
                    Valid.textKosong(Ventilator, "Ventilator");
                } else if (Rr3.getText().trim().equals("")) {
                    Valid.textKosong(Rr3, "Rr3");
                } else if (ERatio.getText().trim().equals("")) {
                    Valid.textKosong(ERatio, "ERatio");
                } else if (Peep.getText().trim().equals("")) {
                    Valid.textKosong(Peep, "Peep");
                } else if (Pip.getText().trim().equals("")) {
                    Valid.textKosong(Pip, "Pip");
                } else if (Fi02.getText().trim().equals("")) {
                    Valid.textKosong(Fi02, "Fi02");
                } else if (Pemeliharaan.getText().trim().equals("")) {
                    Valid.textKosong(Pemeliharaan, "Pemeliharaan");
                } else if (PerhitunganCairan.getText().trim().equals("")) {
                    Valid.textKosong(PerhitunganCairan, "Perhitungan Cairan");
                } else if (Ebv.getText().trim().equals("")) {
                    Valid.textKosong(Ebv, "Ebv");
                } else if (Ebl.getText().trim().equals("")) {
                    Valid.textKosong(Ebl, "Ebl");
                } else if (JenisDarah.getText().trim().equals("")) {
                    Valid.textKosong(JenisDarah, "Jenis Darah");
                } else if (JumlahDarah.getText().trim().equals("")) {
                    Valid.textKosong(JumlahDarah, "Jumlah Darah");
                } else if (Monitoring.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring, "Monitoring");
                } else if (PerubahanTeknikAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(PerubahanTeknikAnestesi, "Perubahan Teknik Anestesi");
                } else if (Alasan.getText().trim().equals("")) {
                    Valid.textKosong(Alasan, "Alasan");
                } else {
                    if (Sequel.menyimpantf("anestesi_teknikumum", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 22, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglTeknikAnestesiUmum.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Induksi.getText(), RuteInduksi.getSelectedItem().toString(),
                        PengaturanNafas.getSelectedItem().toString(), Ventilator.getText(), Rr3.getText(), ERatio.getText(), Peep.getText(), Pip.getText(), Fi02.getText(), TeknikKhusus.getSelectedItem().toString(), Pemeliharaan.getText(),
                        PerhitunganCairan.getText(), Ebv.getText(), Ebl.getText(), JenisDarah.getText(), JumlahDarah.getText(), Monitoring.getText(), PerubahanTeknikAnestesi.getText(), Alasan.getText(),
                        NIP3.getText()

                    }) == true) {
                        tabMode4.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(TglTeknikAnestesiUmum.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Induksi.getText(), RuteInduksi.getSelectedItem().toString(),
                            PengaturanNafas.getSelectedItem().toString(), Ventilator.getText(), Rr3.getText(), ERatio.getText(), Peep.getText(), Pip.getText(), Fi02.getText(), TeknikKhusus.getSelectedItem().toString(), Pemeliharaan.getText(),
                            PerhitunganCairan.getSelectedText(), Ebv.getText(), Ebl.getText(), JenisDarah.getText(), JumlahDarah.getText(), Monitoring.getText(), PerubahanTeknikAnestesi.getText(), Alasan.getText(),
                            NIP3.getText(), NamaPegawai3.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (LokasiTusukan.getText().trim().equals("")) {
                    Valid.textKosong(LokasiTusukan, "Lokasi Tusukan");
                } else if (Analgesi.getText().trim().equals("")) {
                    Valid.textKosong(Analgesi, "Analgesi");
                } else if (Anestesi.getText().trim().equals("")) {
                    Valid.textKosong(Anestesi, "Anestesi");
                } else if (Konsentrasi.getText().trim().equals("")) {
                    Valid.textKosong(Konsentrasi, "Konsentrasi");
                } else if (Jumlah.getText().trim().equals("")) {
                    Valid.textKosong(Jumlah, "Jumlah");
                } else {
                    if (Sequel.menyimpantf("anestesi_teknikregional", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 18, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglBlokadeRegional.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Teknik.getSelectedItem().toString(),
                        LokasiTusukan.getText(), Analgesi.getText(), Anestesi.getText(), Konsentrasi.getText(), Jumlah.getText(), ObatTambahan.getText(), Dosis2.getText(), Vasokonstriktor.getText(), Noradernalin.getText(),
                        Konsentrasi2.getText(), Pemeliharaan2.getText(), Monitoring2.getText(), PerubahanTeknikAnestesi2.getText(), Alasan2.getText(), NIP4.getText()

                    }) == true) {
                        tabMode5.addRow(new String[]{
                            TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(), Jk.getText(), Alamat.getText(), Valid.SetTgl(TglBlokadeRegional.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Teknik.getSelectedItem().toString(),
                            LokasiTusukan.getText(), Analgesi.getText(), Anestesi.getText(), Konsentrasi.getText(), Jumlah.getText(), ObatTambahan.getText(), Dosis2.getText(), Vasokonstriktor.getText(), Noradernalin.getText(),
                            Konsentrasi2.getText(), Pemeliharaan2.getText(), Monitoring2.getText(), PerubahanTeknikAnestesi2.getText(), Alasan2.getText(), NIP4.getText(), NamaPegawai4.getText()

                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (ObatAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(ObatAnestesi, "Obat Anestesi");
                } else if (Pemeliharaan3.getText().trim().equals("")) {
                    Valid.textKosong(Pemeliharaan3, "Pemeliharaan");
                } else if (Monitoring4.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring4, "Monitoring");
                } else if (NIP5.getText().trim().equals("")) {
                    Valid.textKosong(NIP5, "NIP5");

                } else {
                    if (Sequel.menyimpantf("anestesi_teknikcombined", "?,?,?,?,?,?,?,?,?", "No.Rawat, Tanggal & Jam", 9, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TeknikCombine.getSelectedItem().toString(),
                        ObatAnestesi.getText(), Pemeliharaan3.getText(), Monitoring4.getText(), PerubahanTeknikAnestesi3.getText(), Alasan3.getText(), NIP5.getText()

                    }) == true) {
                        tabMode.addRow(new String[]{
                            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TeknikCombine.getSelectedItem().toString(),
                            ObatAnestesi.getText(), Pemeliharaan3.getText(), Monitoring4.getText(), PerubahanTeknikAnestesi3.getText(), Alasan3.getText(), NIP5.getText()
                        });
                        emptTeks();
                        // LCount.setText(""+tabMode.getRowCount());
                    }
                }
                break;
            default:
        }
    }

    private void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_premedikasi.no_rawat,anestesi_premedikasi.rute, anestesi_premedikasi.tanggalpremedikasi,anestesi_premedikasi.obat,"
                        + "anestesi_premedikasi.dosis, anestesi_premedikasi.ramsaya,anestesi_premedikasi.nip,reg_periksa.no_rkm_medis,pasien.nm_pasien,pegawai.nama,pasien.tgl_lahir,pasien.jk,reg_periksa.almt_pj FROM "
                        + "anestesi_premedikasi INNER JOIN reg_periksa ON anestesi_premedikasi.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN pegawai ON anestesi_premedikasi.nip = pegawai.nik where anestesi_premedikasi.tanggalpremedikasi between ? and ? order by anestesi_premedikasi.tanggalpremedikasi");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_premedikasi.no_rawat,anestesi_premedikasi.rute,anestesi_premedikasi.tanggalpremedikasi,anestesi_premedikasi.obat,anestesi_premedikasi.dosis,"
                        + "anestesi_premedikasi.ramsaya,anestesi_premedikasi.nip,reg_periksa.no_rkm_medis,pasien.nm_pasien, pegawai.nama,pasien.tgl_lahir,pasien.jk,reg_periksa.almt_pj  FROM anestesi_premedikasi INNER JOIN reg_periksa ON anestesi_premedikasi.no_rawat = reg_periksa.no_rawat "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis INNER JOIN pegawai ON anestesi_premedikasi.nip = pegawai.nik where "
                        + "anestesi_premedikasi.tanggalpremedikasi between ? and ? order by anestesi_premedikasi.tanggalpremedikasi");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("rute"),
                        rs.getString("tanggalpremedikasi"), rs.getString("obat"), rs.getString("dosis"), rs.getString("ramsaya"), rs.getString("nip"), "Nama Pegawai"

                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil3() {
        Valid.tabelKosong(tabMode3);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_prainduksi.no_rawat,anestesi_prainduksi.tanggal, anestesi_prainduksi.kesadaran,anestesi_prainduksi.e,"
                        + "anestesi_prainduksi.m, anestesi_prainduksi.v,anestesi_prainduksi.td,anestesi_prainduksi.nadi,anestesi_prainduksi.respirasi,anestesi_prainduksi.rr,anestesi_prainduksi.o2,anestesi_prainduksi.selang,anestesi_prainduksi.spo2,"
                        + "anestesi_prainduksi.tipepernapasan,anestesi_prainduksi.pernapasan,anestesi_prainduksi.support,anestesi_prainduksi.nip,reg_periksa.no_rkm_medis,pasien.nm_pasien,pegawai.nama,pasien.tgl_lahir,pasien.jk,reg_periksa.almt_pj FROM "
                        + "anestesi_prainduksi INNER JOIN reg_periksa ON anestesi_prainduksi.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN pegawai ON anestesi_prainduksi.nip = pegawai.nik where anestesi_prainduksi.tanggal between ? and ? order by anestesi_prainduksi.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_prainduksi.no_rawat,anestesi_prainduksi.tanggal, anestesi_prainduksi.kesadaran,anestesi_prainduksi.e,"
                        + "anestesi_prainduksi.m, anestesi_prainduksi.v,anestesi_prainduksi.td,anestesi_prainduksi.nadi,anestesi_prainduksi.respirasi,anestesi_prainduksi.rr,anestesi_prainduksi.o2,anestesi_prainduksi.selang,anestesi_prainduksi.spo2,"
                        + "anestesi_prainduksi.tipepernapasan,anestesi_prainduksi.pernapasan,anestesi_prainduksi.support,anestesi_prainduksi.nip,reg_periksa.no_rkm_medis,pasien.nm_pasien,pegawai.nama,pasien.tgl_lahir,pasien.jk,reg_periksa.almt_pj FROM "
                        + "anestesi_prainduksi INNER JOIN reg_periksa ON anestesi_prainduksi.no_rawat = reg_periksa.no_rawat INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + "INNER JOIN pegawai ON anestesi_prainduksi.nip = pegawai.nik where "
                        + "anestesi_prainduksi.tanggal between ? and ? order by anestesi_prainduksi.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode3.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("tanggal"),
                        rs.getString("kesadaran"), rs.getString("e"), rs.getString("m"), rs.getString("v"), rs.getString("td"), rs.getString("nadi"), rs.getString("respirasi"), rs.getString("rr"), rs.getString("selang"),
                        rs.getString("o2"), rs.getString("spo2"), rs.getString("tipepernapasan"), rs.getString("pernapasan"), rs.getString("support"), rs.getString("nip"), rs.getString("nip")

                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil4() {
        switch (TabTeknikAnestesi.getSelectedIndex()) {
            case 0:
                Valid.tabelKosong(tabMode4);
                try {
                    if (TCari.getText().trim().equals("")) {
                        ps = koneksi.prepareStatement("SELECT anestesi_teknikumum.no_rawat, anestesi_teknikumum.tanggal, anestesi_teknikumum.induksi,"
                                + "anestesi_teknikumum.metode, anestesi_teknikumum.nafas, anestesi_teknikumum.tidalvolume, anestesi_teknikumum.rr,"
                                + "anestesi_teknikumum.eratio, anestesi_teknikumum.peep, anestesi_teknikumum.pip, anestesi_teknikumum.fi02,"
                                + "anestesi_teknikumum.teknikkhusus, anestesi_teknikumum.pemeliharaan, anestesi_teknikumum.perhitungancairan,"
                                + "anestesi_teknikumum.ebv, anestesi_teknikumum.ebl, anestesi_teknikumum.jenisdarah, anestesi_teknikumum.jumlahdarah,"
                                + "anestesi_teknikumum.monitoring, anestesi_teknikumum.perubahanteknik, anestesi_teknikumum.alasan, anestesi_teknikumum.nip,"
                                + "reg_periksa.no_rkm_medis, reg_periksa.almt_pj, pasien.nm_pasien, pasien.tgl_lahir, pasien.jk, pegawai.nama "
                                + "FROM anestesi_teknikumum "
                                + "INNER JOIN reg_periksa ON anestesi_teknikumum.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikumum.nip = pegawai.nik where anestesi_teknikumum.tanggal between ? and ? order by anestesi_teknikumum.tanggal");
                    } else {
                        ps = koneksi.prepareStatement("SELECT anestesi_teknikumum.no_rawat, anestesi_teknikumum.tanggal, anestesi_teknikumum.induksi,"
                                + "anestesi_teknikumum.metode, anestesi_teknikumum.nafas, anestesi_teknikumum.tidalvolume, anestesi_teknikumum.rr,"
                                + "anestesi_teknikumum.eratio, anestesi_teknikumum.peep, anestesi_teknikumum.pip, anestesi_teknikumum.fi02,"
                                + "anestesi_teknikumum.teknikkhusus, anestesi_teknikumum.pemeliharaan, anestesi_teknikumum.perhitungancairan,"
                                + "anestesi_teknikumum.ebv, anestesi_teknikumum.ebl, anestesi_teknikumum.jenisdarah, anestesi_teknikumum.jumlahdarah,"
                                + "anestesi_teknikumum.monitoring, anestesi_teknikumum.perubahanteknik, anestesi_teknikumum.alasan, anestesi_teknikumum.nip,"
                                + "reg_periksa.no_rkm_medis, reg_periksa.almt_pj, pasien.nm_pasien, pasien.tgl_lahir, pasien.jk, pegawai.nama "
                                + "FROM anestesi_teknikumum "
                                + "INNER JOIN reg_periksa ON anestesi_teknikumum.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikumum.nip = pegawai.nik where where "
                                + "anestesi_teknikumum.tanggal between ? and ? and anestesi_teknikumum.induksi ? order by anestesi_teknikumum.tanggal");
                    }
                    try {
                        if (TCari.getText().trim().equals("")) {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        } else {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                            ps.setString(3, "%" + TCari.getText() + "%");
                            ps.setString(4, "%" + TCari.getText() + "%");
                            ps.setString(5, "%" + TCari.getText() + "%");
                            ps.setString(6, "%" + TCari.getText() + "%");
                            ps.setString(7, "%" + TCari.getText() + "%");
                        }
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            tabMode4.addRow(new String[]{
                                rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                                rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("tanggal"),
                                rs.getString("induksi"), rs.getString("metode"), rs.getString("nafas"), rs.getString("tidalvolume"),
                                rs.getString("rr"), rs.getString("eratio"), rs.getString("peep"), rs.getString("pip"), rs.getString("fi02"),
                                rs.getString("teknikkhusus"), rs.getString("pemeliharaan"), rs.getString("perhitungancairan"),
                                rs.getString("ebv"), rs.getString("ebl"), rs.getString("jenisdarah"), rs.getString("jumlahdarah"),
                                rs.getString("monitoring"), rs.getString("perubahanteknik"), rs.getString("alasan"), rs.getString("nip"), rs.getString("nama")

                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                break;
            case 1:
                Valid.tabelKosong(tabMode5);
                try {
                    if (TCari.getText().trim().equals("")) {
                        ps = koneksi.prepareStatement("SELECT reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                                + "reg_periksa.almt_pj, anestesi_teknikregional.no_rawat, anestesi_teknikregional.tanggal,"
                                + "anestesi_teknikregional.teknik, anestesi_teknikregional.lokasitusukan, anestesi_teknikregional.analgesi,"
                                + "anestesi_teknikregional.anestesilokal, anestesi_teknikregional.konsentrasianestesi,"
                                + "anestesi_teknikregional.jumlah, anestesi_teknikregional.obattambahan, anestesi_teknikregional.dosis,"
                                + "anestesi_teknikregional.adrenalin, anestesi_teknikregional.noradrenalin,"
                                + "anestesi_teknikregional.konsentrasivasokontriktor, anestesi_teknikregional.pemeliharaan,"
                                + "anestesi_teknikregional.monitoring, anestesi_teknikregional.perubahanteknik,"
                                + "anestesi_teknikregional.alasan, anestesi_teknikregional.nip,pegawai.nama "
                                + "FROM anestesi_teknikregional "
                                + "INNER JOIN reg_periksa ON anestesi_teknikregional.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikregional.nip = pegawai.nik "
                                + "AND anestesi_teknikregional.nip = pegawai.nik where anestesi_teknikregional.tanggal between ? and ? order by anestesi_teknikregional.tanggal");
                    } else {
                        ps = koneksi.prepareStatement("SELECT reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                                + "reg_periksa.almt_pj, anestesi_teknikregional.no_rawat, anestesi_teknikregional.tanggal,"
                                + "anestesi_teknikregional.teknik, anestesi_teknikregional.lokasitusukan, anestesi_teknikregional.analgesi,"
                                + "anestesi_teknikregional.anestesilokal, anestesi_teknikregional.konsentrasianestesi,"
                                + "anestesi_teknikregional.jumlah, anestesi_teknikregional.obattambahan, anestesi_teknikregional.dosis,"
                                + "anestesi_teknikregional.adrenalin, anestesi_teknikregional.noradrenalin,"
                                + "anestesi_teknikregional.konsentrasivasokontriktor, anestesi_teknikregional.pemeliharaan,"
                                + "anestesi_teknikregional.monitoring, anestesi_teknikregional.perubahanteknik,"
                                + "anestesi_teknikregional.alasan, anestesi_teknikregional.nip,pegawai.nama "
                                + "FROM anestesi_teknikregional "
                                + "INNER JOIN reg_periksa ON anestesi_teknikregional.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikregional.nip = pegawai.nik "
                                + "AND anestesi_teknikregional.nip = pegawai.nik where "
                                + "anestesi_teknikregional.tanggal between ? and ? order by anestesi_teknikregional.tanggal");
                    }
                    try {
                        if (TCari.getText().trim().equals("")) {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        } else {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                            ps.setString(3, "%" + TCari.getText() + "%");
                            ps.setString(4, "%" + TCari.getText() + "%");
                            ps.setString(5, "%" + TCari.getText() + "%");
                            ps.setString(6, "%" + TCari.getText() + "%");
                            ps.setString(7, "%" + TCari.getText() + "%");
                        }
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            tabMode5.addRow(new String[]{
                                rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"),
                                rs.getString("tanggal"), rs.getString("teknik"), rs.getString("lokasitusukan"), rs.getString("analgesi"), rs.getString("anestesilokal"), rs.getString("konsentrasianestesi"),
                                rs.getString("jumlah"), rs.getString("obattambahan"), rs.getString("dosis"), rs.getString("adrenalin"), rs.getString("noradrenalin"),
                                rs.getString("konsentrasivasokontriktor"), rs.getString("pemeliharaan"), rs.getString("monitoring"), rs.getString("perubahanteknik"), rs.getString("alasan"),
                                rs.getString("nip"), rs.getString("nama")

                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                break;
            case 2:
                Valid.tabelKosong(tabMode6);
                try {
                    if (TCari.getText().trim().equals("")) {
                        ps = koneksi.prepareStatement("SELECT anestesi_teknikcombined.no_rawat, anestesi_teknikcombined.tanggal, anestesi_teknikcombined.teknik,"
                                + "anestesi_teknikcombined.obat, anestesi_teknikcombined.pemeliharaan, anestesi_teknikcombined.monitoring,"
                                + "anestesi_teknikcombined.perubahanteknik, anestesi_teknikcombined.alasan, anestesi_teknikcombined.nip,"
                                + "reg_periksa.no_rkm_medis, reg_periksa.almt_pj, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                                + "anestesi_teknikcombined.nip,pegawai.nama "
                                + "FROM "
                                + "anestesi_teknikcombined INNER JOIN reg_periksa ON anestesi_teknikcombined.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikcombined.nip = pegawai.nik "
                                + "AND anestesi_teknikcombined.nip = pegawai.nik where anestesi_teknikcombined.tanggal between ? and ? order by anestesi_teknikcombined.tanggal");
                    } else {
                        ps = koneksi.prepareStatement("SELECT "
                                + "anestesi_teknikcombined.no_rawat, anestesi_teknikcombined.tanggal, anestesi_teknikcombined.teknik,"
                                + "anestesi_teknikcombined.obat, anestesi_teknikcombined.pemeliharaan, anestesi_teknikcombined.monitoring,"
                                + "anestesi_teknikcombined.perubahanteknik, anestesi_teknikcombined.alasan, anestesi_teknikcombined.nip,"
                                + "reg_periksa.no_rkm_medis, reg_periksa.almt_pj, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                                + "anestesi_teknikcombined.nip,pegawai.nama "
                                + "FROM "
                                + "anestesi_teknikcombined INNER JOIN reg_periksa ON anestesi_teknikcombined.no_rawat = reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                                + "INNER JOIN pegawai ON anestesi_teknikcombined.nip = pegawai.nik "
                                + "AND anestesi_teknikcombined.nip = pegawai.nik where "
                                + "anestesi_teknikcombined.tanggal between ? and ? order by anestesi_teknikcombined.tanggal");
                    }
                    try {
                        if (TCari.getText().trim().equals("")) {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        } else {
                            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                            ps.setString(3, "%" + TCari.getText() + "%");
                            ps.setString(4, "%" + TCari.getText() + "%");
                            ps.setString(5, "%" + TCari.getText() + "%");
                            ps.setString(6, "%" + TCari.getText() + "%");
                            ps.setString(7, "%" + TCari.getText() + "%");
                        }
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            tabMode6.addRow(new String[]{
                                rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                                rs.getString("jk"), rs.getString("almt_pj"), rs.getString("tanggal"), rs.getString("teknik"),
                                rs.getString("obat"), rs.getString("pemeliharaan"), rs.getString("monitoring"), rs.getString("perubahanteknik"),
                                rs.getString("alasan"), rs.getString("nip"), rs.getString("nip"), rs.getString("nama")
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                break;
            default:
        }

    }

    private void tampil5() {
        Valid.tabelKosong(tabMode7);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi.no_rawat, anestesi.tanggal, anestesi.posisi, anestesi.airway, anestesi.ett,"
                        + "anestesi.ukuran, anestesi.komplikasi, anestesi.tindakan, anestesi.nip,"
                        + "reg.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                        + "pegawai.nik, pegawai.nama,reg.almt_pj FROM  anestesi_selama AS anestesi "
                        + "INNER JOIN pegawai AS pegawai ON anestesi.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa AS reg ON anestesi.no_rawat = reg.no_rawat "
                        + "INNER JOIN pasien AS pasien ON reg.no_rkm_medis = pasien.no_rkm_medis where anestesi.tanggal between ? and ? order by anestesi.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi.no_rawat, anestesi.tanggal, anestesi.posisi, anestesi.airway, anestesi.ett,"
                        + "anestesi.ukuran, anestesi.komplikasi, anestesi.tindakan, anestesi.nip,"
                        + "reg.no_rkm_medis, pasien.nm_pasien, pasien.jk, pasien.tgl_lahir,"
                        + "pegawai.nik, pegawai.nama,reg.almt_pj FROM  rsudalmulk.anestesi_selama AS anestesi "
                        + "INNER JOIN pegawai AS pegawai ON anestesi.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa AS reg ON anestesi.no_rawat = reg.no_rawat "
                        + "INNER JOIN pasien AS pasien ON reg.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi.tanggal between ? and ? order by anestesi.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode7.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("jk"), rs.getString("almt_pj"), rs.getString("tanggal"), rs.getString("posisi"),
                        rs.getString("airway"), rs.getString("ett"), rs.getString("ukuran"), rs.getString("komplikasi"),
                        rs.getString("tindakan"), rs.getString("nip"), rs.getString("nama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    private void tampil6() {
        Valid.tabelKosong(tabMode8);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_monitoring.no_rawat, anestesi_monitoring.tanggal, anestesi_monitoring.o2, anestesi_monitoring.n2o,"
                        + "anestesi_monitoring.air, anestesi_monitoring.halothane, anestesi_monitoring.isoflurane, anestesi_monitoring.sevolurane,"
                        + "anestesi_monitoring.dml, anestesi_monitoring.tvs, anestesi_monitoring.nadi, anestesi_monitoring.sistolik,"
                        + "anestesi_monitoring.diastolik, anestesi_monitoring.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring INNER JOIN pegawai ON anestesi_monitoring.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_monitoring.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where anestesi_monitoring.tanggal between ? and ? order by anestesi_monitoring.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_monitoring.no_rawat, anestesi_monitoring.tanggal, anestesi_monitoring.o2, anestesi_monitoring.n2o,"
                        + "anestesi_monitoring.air, anestesi_monitoring.halothane, anestesi_monitoring.isoflurane, anestesi_monitoring.sevolurane,"
                        + "anestesi_monitoring.dml, anestesi_monitoring.tvs, anestesi_monitoring.nadi, anestesi_monitoring.sistolik,"
                        + "anestesi_monitoring.diastolik, anestesi_monitoring.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring INNER JOIN pegawai ON anestesi_monitoring.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_monitoring.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_monitoring.tanggal between ? and ? order by anestesi_monitoring.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode8.addRow(new String[]{
                    /*
                        "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat", "O2",
            "N2O", "AIR", "Halothane", "Isoflurane", "Sefoflurane", "DML", "tVS", "Nadi", "Sistolik", "Diastolik", "Kode Pegawai", "Nama Pegawai"
                        */    
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), 
                        rs.getString("jk"), rs.getString("almt_pj"), rs.getString("o2"),rs.getString("n2o"),rs.getString("air"),
                        rs.getString("halothane"),rs.getString("isoflurane"),rs.getString("sevolurane"),rs.getString("dml"),
                        rs.getString("tvs"),rs.getString("nadi"),rs.getString("sistolik"),rs.getString("diastolik"),rs.getString("nip"),
                        rs.getString("nama")
                            
                    
                    
                    
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil7() {
        Valid.tabelKosong(tabMode9);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_monitoring2.no_rawat, anestesi_monitoring2.tanggal, anestesi_monitoring2.sp02, anestesi_monitoring2.etco2,"
                        + "anestesi_monitoring2.fio2, anestesi_monitoring2.cairan, anestesi_monitoring2.urine, anestesi_monitoring2.perdarahan,"
                        + "anestesi_monitoring2.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring2 INNER JOIN pegawai ON anestesi_monitoring2.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_monitoring2.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where anestesi_monitoring2.tanggal between ? and ? order by anestesi_monitoring2.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_monitoring2.no_rawat, anestesi_monitoring2.tanggal, anestesi_monitoring2.sp02, anestesi_monitoring2.etco2,"
                        + "anestesi_monitoring2.fio2, anestesi_monitoring2.cairan, anestesi_monitoring2.urine, anestesi_monitoring2.perdarahan,"
                        + "anestesi_monitoring2.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_monitoring2 INNER JOIN pegawai ON anestesi_monitoring2.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_monitoring2.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_monitoring2.tanggal between ? and ? order by anestesi_monitoring2.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode9.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("tanggal"),
                        rs.getString("sp02"),rs.getString("etco2"),rs.getString("fio2"),rs.getString("cairan"),rs.getString("urine"),rs.getString("perdarahan"),rs.getString("nip"),rs.getString("nama")
                    
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil8() {
        Valid.tabelKosong(tabMode10);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_cairan.no_rawat, anestesi_cairan.tanggal, anestesi_cairan.kristaloid, anestesi_cairan.koloid,"
                        + "anestesi_cairan.darah, anestesi_cairan.komponendarah, anestesi_cairan.urine, anestesi_cairan.perdarahan,anestesi_cairan.diuresis,anestesi_cairan.cairanlain,"
                        + "anestesi_cairan.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_cairan INNER JOIN pegawai ON anestesi_cairan.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_cairan.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where anestesi_cairan.tanggal between ? and ? order by anestesi_cairan.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_cairan.no_rawat, anestesi_cairan.tanggal, anestesi_cairan.kristaloid, anestesi_cairan.koloid,"
                        + "anestesi_cairan.darah, anestesi_cairan.komponendarah, anestesi_cairan.urine, anestesi_cairan.perdarahan,anestesi_cairan.diuresis,anestesi_cairan.cairanlain,"
                        + "anestesi_cairan.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_cairan INNER JOIN pegawai ON anestesi_cairan.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_cairan.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_cairan.tanggal between ? and ? order by anestesi_cairan.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode10.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("kristaloid"),rs.getString("koloid"),rs.getString("darah"),rs.getString("komponendarah"),rs.getString("perdarahan"),rs.getString("diuresis"),rs.getString("cairanlain"),rs.getString("urine"),rs.getString("nip"),rs.getString("nama")});
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil9() {
        Valid.tabelKosong(tabMode11);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_sectio.no_rawat, anestesi_sectio.tanggal, anestesi_sectio.keadaanbayi, anestesi_sectio.jk,"
                        + "anestesi_sectio.apgar1, anestesi_sectio.apgar5, anestesi_sectio.bb, anestesi_sectio.pb,"
                        + "anestesi_sectio.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_sectio INNER JOIN pegawai ON anestesi_sectio.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_sectio.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where anestesi_sectio.tanggal between ? and ? order by anestesi_sectio.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_sectio.no_rawat, anestesi_sectio.tanggal, anestesi_sectio.keadaanbayi, anestesi_sectio.jk,"
                        + "anestesi_sectio.apgar1, anestesi_sectio.apgar5, anestesi_sectio.bb, anestesi_sectio.pb,"
                        + "anestesi_sectio.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_sectio INNER JOIN pegawai ON anestesi_sectio.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_sectio.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where  where "
                        + "anestesi_sectio.tanggal between ? and ? order by anestesi_sectio.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode11.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("almt_pj"), rs.getString("keadaanbayi"),rs.getString("jk"),rs.getString("apgar1"),rs.getString("apgar5"),rs.getString("bb"),rs.getString("pb"),rs.getString("nip"),rs.getString("nama")});
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());
    }

    private void tampil10() {
        Valid.tabelKosong(tabMode12);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT anestesi_pasca.no_rawat, anestesi_pasca.diagnosapostop, anestesi_pasca.tanggal, anestesi_pasca.pindah, "
                        + "anestesi_pasca.ku, anestesi_pasca.nadi, anestesi_pasca.rr, anestesi_pasca.suhu, anestesi_pasca.spo2, anestesi_pasca.instruksi, "
                        + "anestesi_pasca.vetilator, anestesi_pasca.tidal, anestesi_pasca.mlrr, anestesi_pasca.ieratio, anestesi_pasca.peep, anestesi_pasca.pip, "
                        + "anestesi_pasca.ps, anestesi_pasca.fi02, anestesi_pasca.trig, anestesi_pasca.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_pasca INNER JOIN pegawai ON anestesi_pasca.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_pasca.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where anestesi_pasca.tanggal between ? and ? order by anestesi_pasca.tanggal");
            } else {
                ps = koneksi.prepareStatement("SELECT anestesi_pasca.no_rawat, anestesi_pasca.diagnosapostop, anestesi_pasca.tanggal, anestesi_pasca.pindah, "
                        + "anestesi_pasca.ku, anestesi_pasca.nadi, anestesi_pasca.rr, anestesi_pasca.suhu, anestesi_pasca.spo2, anestesi_pasca.instruksi, "
                        + "anestesi_pasca.vetilator, anestesi_pasca.tidal, anestesi_pasca.mlrr, anestesi_pasca.ieratio, anestesi_pasca.peep, anestesi_pasca.pip, "
                        + "anestesi_pasca.ps, anestesi_pasca.fi02, anestesi_pasca.trig, anestesi_pasca.nip, reg_periksa.no_rkm_medis, reg_periksa.almt_pj,"
                        + "pasien.nm_pasien, pasien.jk, pasien.tgl_lahir, pegawai.nama FROM anestesi_pasca INNER JOIN pegawai ON anestesi_pasca.nip = pegawai.nik "
                        + "INNER JOIN reg_periksa ON anestesi_pasca.no_rawat = reg_periksa.no_rawat INNER JOIN "
                        + "pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis where "
                        + "anestesi_pasca.tanggal between ? and ? order by anestesi_pasca.tanggal");
            }
            try {
                if (TCari.getText().trim().equals("")) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode12.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("diagnosapostop"), rs.getString("pindah"),rs.getString("ku"), rs.getString("nadi"),rs.getString("rr"), rs.getString("suhu"),rs.getString("spo2"), rs.getString("instruksi"),
                        rs.getString("vetilator"), rs.getString("tidal"),rs.getString("mlrr"), rs.getString("ieratio"),rs.getString("peep"), rs.getString("pip"),rs.getString("ps"),rs.getString("fi02"),rs.getString("trig"),rs.getString("nip"),rs.getString("nama")});
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        //   LCount.setText(""+tabMode.getRowCount());

    }

    private void ganti2() {
        if (Sequel.mengedittf("anestesi_premedikasi", "no_rawat=? and tanggalpremedikasi=?", "no_rawat=?,rute=?,tanggalpremedikasi=?,obat=?,dosis=?,ramsaya=?,nip=?", 9, new String[]{
            TNoRw.getText(), Rute.getSelectedItem().toString(), Valid.SetTgl(TglPremedikasi.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19),
            Obat.getText(), Dosis.getText(), Ramsaya.getSelectedItem().toString(), Pegawaipremedikasi.getText(), tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 7).toString(),
            tbAnestesi2.getValueAt(tbAnestesi2.getSelectedRow(), 13).toString()
        }) == true) {
            tbAnestesi2.setValueAt(TNoRw.getText(), tbAnestesi2.getSelectedRow(), 0);
            tbAnestesi2.setValueAt(TNoRM.getText(), tbAnestesi2.getSelectedRow(), 1);
            tbAnestesi2.setValueAt(TPasien.getText(), tbAnestesi2.getSelectedRow(), 2);
            tbAnestesi2.setValueAt(TglLahir.getText(), tbAnestesi2.getSelectedRow(), 3);
            tbAnestesi2.setValueAt(Jk.getText(), tbAnestesi2.getSelectedRow(), 4);
            tbAnestesi2.setValueAt(Alamat.getText(), tbAnestesi2.getSelectedRow(), 5);
            tbAnestesi2.setValueAt(Rute.getSelectedItem(), tbAnestesi2.getSelectedRow(), 6);
            tbAnestesi2.setValueAt(Obat.getText(), tbAnestesi2.getSelectedRow(), 8);
            tbAnestesi2.setValueAt(Dosis.getText(), tbAnestesi2.getSelectedRow(), 9);
            tbAnestesi2.setValueAt(Ramsaya.getSelectedItem(), tbAnestesi2.getSelectedRow(), 10);
            tbAnestesi2.setValueAt(Pegawaipremedikasi.getText(), tbAnestesi2.getSelectedRow(), 11);
            tbAnestesi2.setValueAt(NmPegawaiPremedikasi.getText(), tbAnestesi2.getSelectedRow(), 12);
            tbAnestesi2.setValueAt(Valid.SetTgl(TglPremedikasi.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19), tbAnestesi2.getSelectedRow(), 7);
            emptTeks();
        }
    }

    private void ganti3() {
        if (Sequel.mengedittf("anestesi_prainduksi", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,kesadaran=?,e=?,m=?,v=?,td=?,nadi=?,respirasi=?,rr=?,selang=?,o2=?,spo2=?,tipepernapasan=?,pernapasan=?,support=?,nip=?", 19, new String[]{
            TNoRw.getText(), Valid.SetTgl(TglPrainduksi.getSelectedItem() + "") + " " + TglPrainduksi.getSelectedItem().toString().substring(11, 19),
            E2.getText(), M2.getText(), V2.getText(), Tensi.getText(), Nadi1.getText(), Respirasi2.getSelectedItem().toString(), Rr2.getText(), Selang2.getSelectedItem().toString(), o22.getText(), Spo22.getText(), TipePernapasan2.getText(),
            Pernapasan.getSelectedItem().toString(), Support2.getText(), NIP2.getText(), tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 0).toString(),
            tbAnestesi3.getValueAt(tbAnestesi3.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi3.setValueAt(TNoRw.getText(), tbAnestesi3.getSelectedRow(), 0);
            tbAnestesi3.setValueAt(TNoRM.getText(), tbAnestesi3.getSelectedRow(), 1);
            tbAnestesi3.setValueAt(TPasien.getText(), tbAnestesi3.getSelectedRow(), 2);
            tbAnestesi3.setValueAt(TglLahir.getText(), tbAnestesi3.getSelectedRow(), 3);
            tbAnestesi3.setValueAt(Jk.getText(), tbAnestesi3.getSelectedRow(), 4);
            tbAnestesi3.setValueAt(Alamat.getText(), tbAnestesi3.getSelectedRow(), 5);
            tbAnestesi3.setValueAt(Kesadaran2.getSelectedItem(), tbAnestesi3.getSelectedRow(), 6);
            tbAnestesi3.setValueAt(E2.getText(), tbAnestesi3.getSelectedRow(), 8);
            tbAnestesi3.setValueAt(M2.getText(), tbAnestesi3.getSelectedRow(), 9);
            tbAnestesi3.setValueAt(V2.getText(), tbAnestesi3.getSelectedRow(), 10);
            tbAnestesi3.setValueAt(Tensi.getText(), tbAnestesi3.getSelectedRow(), 11);
            tbAnestesi3.setValueAt(Nadi1.getText(), tbAnestesi3.getSelectedRow(), 12);
            tbAnestesi3.setValueAt(Respirasi2.getSelectedItem(), tbAnestesi3.getSelectedRow(), 13);
            tbAnestesi3.setValueAt(Rr2.getText(), tbAnestesi3.getSelectedRow(), 14);
            tbAnestesi3.setValueAt(Selang2.getSelectedItem(), tbAnestesi3.getSelectedRow(), 15);
            tbAnestesi3.setValueAt(o22.getText(), tbAnestesi3.getSelectedRow(), 16);
            tbAnestesi3.setValueAt(Spo22.getText(), tbAnestesi3.getSelectedRow(), 17);
            tbAnestesi3.setValueAt(TipePernapasan2.getText(), tbAnestesi3.getSelectedRow(), 18);
            tbAnestesi3.setValueAt(Support2.getText(), tbAnestesi3.getSelectedRow(), 19);
            tbAnestesi3.setValueAt(NIP2.getText(), tbAnestesi3.getSelectedRow(), 20);
            tbAnestesi3.setValueAt(NamaPegawai2.getText(), tbAnestesi3.getSelectedRow(), 21);
            tbAnestesi3.setValueAt(Valid.SetTgl(TglPremedikasi.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19), tbAnestesi3.getSelectedRow(), 7);
            emptTeks();
        }

    }

    private void ganti4() {
        if (Sequel.mengedittf("anestesi_teknikumum", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,induksi=?,metode=?,nafas=?,tidalvolume=?,rr=?,eratio=?,peep=?,pip=?,fi02=?,teknikkhusus=?,pemeliharaan=?,perhitungancairan=?,ebv=?,ebl=?,jenisdarah=?,jumlahdarah=?,monitoring=?,perubahanteknik=?,alasan=?,nip=?", 24, new String[]{
            TNoRw.getText(), Valid.SetTgl(TglTeknikAnestesiUmum.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Induksi.getText(), RuteInduksi.getSelectedItem().toString(),
            PengaturanNafas.getSelectedItem().toString(), Ventilator.getText(), Rr3.getText(), ERatio.getText(), Peep.getText(), Pip.getText(), Fi02.getText(), TeknikKhusus.getSelectedItem().toString(), Pemeliharaan.getText(),
            PerhitunganCairan.getText(), Ebv.getText(), Ebl.getText(), JenisDarah.getText(), JumlahDarah.getText(), Monitoring.getText(), PerubahanTeknikAnestesi.getText(), Alasan.getText(),
            NIP3.getText(), tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 0).toString(), tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi4.setValueAt(TNoRw.getText(), tbAnestesi4.getSelectedRow(), 0);
            tbAnestesi4.setValueAt(TNoRM.getText(), tbAnestesi4.getSelectedRow(), 1);
            tbAnestesi4.setValueAt(TPasien.getText(), tbAnestesi4.getSelectedRow(), 2);
            tbAnestesi4.setValueAt(TglLahir.getText(), tbAnestesi4.getSelectedRow(), 3);
            tbAnestesi4.setValueAt(Jk.getText(), tbAnestesi4.getSelectedRow(), 4);
            tbAnestesi4.setValueAt(Alamat.getText(), tbAnestesi4.getSelectedRow(), 5);
            tbAnestesi4.setValueAt(Induksi.getText(), tbAnestesi4.getSelectedRow(), 7);
            tbAnestesi4.setValueAt(RuteInduksi.getSelectedItem(), tbAnestesi4.getSelectedRow(), 8);
            tbAnestesi4.setValueAt(PengaturanNafas.getSelectedItem(), tbAnestesi4.getSelectedRow(), 9);
            tbAnestesi4.setValueAt(Ventilator.getText(), tbAnestesi4.getSelectedRow(), 10);
            tbAnestesi4.setValueAt(Rr3.getText(), tbAnestesi4.getSelectedRow(), 11);
            tbAnestesi4.setValueAt(ERatio.getText(), tbAnestesi4.getSelectedRow(), 12);
            tbAnestesi4.setValueAt(Peep.getText(), tbAnestesi4.getSelectedRow(), 13);
            tbAnestesi4.setValueAt(Pip.getText(), tbAnestesi4.getSelectedRow(), 14);
            tbAnestesi4.setValueAt(Fi02.getText(), tbAnestesi4.getSelectedRow(), 15);
            tbAnestesi4.setValueAt(TeknikKhusus.getSelectedItem(), tbAnestesi4.getSelectedRow(), 16);
            tbAnestesi4.setValueAt(Pemeliharaan.getText(), tbAnestesi4.getSelectedRow(), 17);
            tbAnestesi4.setValueAt(PerhitunganCairan.getText(), tbAnestesi4.getSelectedRow(), 18);
            tbAnestesi4.setValueAt(Ebv.getText(), tbAnestesi4.getSelectedRow(), 19);
            tbAnestesi4.setValueAt(Ebl.getText(), tbAnestesi4.getSelectedRow(), 20);
            tbAnestesi4.setValueAt(JenisDarah.getText(), tbAnestesi4.getSelectedRow(), 21);
            tbAnestesi4.setValueAt(JumlahDarah.getText(), tbAnestesi4.getSelectedRow(), 22);
            tbAnestesi4.setValueAt(Monitoring.getText(), tbAnestesi4.getSelectedRow(), 23);
            tbAnestesi4.setValueAt(PerubahanTeknikAnestesi.getText(), tbAnestesi4.getSelectedRow(), 24);
            tbAnestesi4.setValueAt(Alasan.getText(), tbAnestesi4.getSelectedRow(), 25);
            tbAnestesi4.setValueAt(NIP3.getText(), tbAnestesi4.getSelectedRow(), 26);
            tbAnestesi4.setValueAt(NamaPegawai3.getText(), tbAnestesi4.getSelectedRow(), 27);
            tbAnestesi4.setValueAt(Valid.SetTgl(TglTeknikAnestesiUmum.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19), tbAnestesi4.getSelectedRow(), 6);
            emptTeks();
        }

    }

    private void ganti5() {
        if (Sequel.mengedittf("anestesi_teknikregional", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,teknik=?,lokasitusukan=?,analgesi=?,anestesilokal=?,konsentrasianestesi=?,jumlah=?,obattambahan=?,dosis=?,adrenalin=?,noradrenalin=?,konsentrasivasokontriktor=?,pemeliharaan=?,monitoring=?,perubahanteknik=?,alasan=?,nip=?", 20, new String[]{
            TNoRw.getText(), Valid.SetTgl(TglBlokadeRegional.getSelectedItem() + "") + " " + TglTeknikAnestesiUmum.getSelectedItem().toString().substring(11, 19), Teknik.getSelectedItem().toString(),
            LokasiTusukan.getText(), Analgesi.getText(), Anestesi.getText(), Konsentrasi.getText(), Jumlah.getText(), ObatTambahan.getText(), Dosis2.getText(), Vasokonstriktor.getText(), Noradernalin.getText(),
            Konsentrasi2.getText(), Pemeliharaan2.getText(), Monitoring2.getText(), PerubahanTeknikAnestesi2.getText(), Alasan2.getText(), NIP4.getText(), tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 0).toString(), tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi6.setValueAt(TNoRw.getText(), tbAnestesi6.getSelectedRow(), 0);
            tbAnestesi6.setValueAt(TNoRM.getText(), tbAnestesi6.getSelectedRow(), 1);
            tbAnestesi6.setValueAt(TPasien.getText(), tbAnestesi6.getSelectedRow(), 2);
            tbAnestesi6.setValueAt(TglLahir.getText(), tbAnestesi6.getSelectedRow(), 3);
            tbAnestesi6.setValueAt(Jk.getText(), tbAnestesi6.getSelectedRow(), 4);
            tbAnestesi6.setValueAt(Alamat.getText(), tbAnestesi6.getSelectedRow(), 5);

            tbAnestesi6.setValueAt(Teknik.getSelectedItem(), tbAnestesi6.getSelectedRow(), 7);
            tbAnestesi6.setValueAt(LokasiTusukan.getText(), tbAnestesi6.getSelectedRow(), 8);
            tbAnestesi6.setValueAt(Analgesi.getText(), tbAnestesi6.getSelectedRow(), 9);
            tbAnestesi6.setValueAt(Anestesi.getText(), tbAnestesi6.getSelectedRow(), 10);
            tbAnestesi6.setValueAt(Konsentrasi.getText(), tbAnestesi6.getSelectedRow(), 11);
            tbAnestesi6.setValueAt(Jumlah.getText(), tbAnestesi6.getSelectedRow(), 12);
            tbAnestesi6.setValueAt(ObatTambahan.getText(), tbAnestesi6.getSelectedRow(), 13);

            tbAnestesi6.setValueAt(Dosis2.getText(), tbAnestesi6.getSelectedRow(), 14);
            tbAnestesi6.setValueAt(Vasokonstriktor.getText(), tbAnestesi6.getSelectedRow(), 15);
            tbAnestesi6.setValueAt(Noradernalin.getText(), tbAnestesi6.getSelectedRow(), 16);
            tbAnestesi6.setValueAt(Konsentrasi2.getText(), tbAnestesi6.getSelectedRow(), 17);
            tbAnestesi6.setValueAt(Pemeliharaan2.getText(), tbAnestesi6.getSelectedRow(), 18);
            tbAnestesi6.setValueAt(Monitoring2.getText(), tbAnestesi6.getSelectedRow(), 19);
            tbAnestesi6.setValueAt(PerubahanTeknikAnestesi2.getText(), tbAnestesi6.getSelectedRow(), 20);
            tbAnestesi6.setValueAt(Alasan2.getText(), tbAnestesi6.getSelectedRow(), 21);
            tbAnestesi6.setValueAt(NIP4.getText(), tbAnestesi6.getSelectedRow(), 22);
            tbAnestesi6.setValueAt(NamaPegawai4.getText(), tbAnestesi6.getSelectedRow(), 23);

            tbAnestesi6.setValueAt(Valid.SetTgl(TglBlokadeRegional.getSelectedItem() + "") + " " + TglPremedikasi.getSelectedItem().toString().substring(11, 19), tbAnestesi6.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti6() {
        if (Sequel.mengedittf("anestesi_teknikcombined", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,teknik=?,obat=?,pemeliharaan=?,monitoring=?,perubahanteknik=?,alasan=?,nip=?", 11, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TeknikCombine.getSelectedItem().toString(),
            ObatAnestesi.getText(), Pemeliharaan3.getText(), Monitoring4.getText(), PerubahanTeknikAnestesi3.getText(), Alasan3.getText(), NIP5.getText(), tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 0).toString(), tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi7.setValueAt(TNoRw.getText(), tbAnestesi7.getSelectedRow(), 0);
            tbAnestesi7.setValueAt(TNoRM.getText(), tbAnestesi7.getSelectedRow(), 1);
            tbAnestesi7.setValueAt(TPasien.getText(), tbAnestesi7.getSelectedRow(), 2);
            tbAnestesi7.setValueAt(TglLahir.getText(), tbAnestesi7.getSelectedRow(), 3);
            tbAnestesi7.setValueAt(Jk.getText(), tbAnestesi7.getSelectedRow(), 4);
            tbAnestesi7.setValueAt(Alamat.getText(), tbAnestesi7.getSelectedRow(), 5);

            tbAnestesi7.setValueAt(Teknik.getSelectedItem(), tbAnestesi7.getSelectedRow(), 7);
            tbAnestesi7.setValueAt(ObatAnestesi.getText(), tbAnestesi7.getSelectedRow(), 8);
            tbAnestesi7.setValueAt(Pemeliharaan3.getText(), tbAnestesi7.getSelectedRow(), 9);
            tbAnestesi7.setValueAt(Monitoring4.getText(), tbAnestesi7.getSelectedRow(), 10);
            tbAnestesi7.setValueAt(PerubahanTeknikAnestesi3.getText(), tbAnestesi7.getSelectedRow(), 11);
            tbAnestesi7.setValueAt(Alasan3.getText(), tbAnestesi7.getSelectedRow(), 12);
            tbAnestesi7.setValueAt(NIP5.getText(), tbAnestesi7.getSelectedRow(), 13);
            tbAnestesi7.setValueAt(NamaPegawai5.getText(), tbAnestesi7.getSelectedRow(), 14);

            tbAnestesi7.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi7.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti7() {
        if (Sequel.mengedittf("anestesi_selama", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,posisi=?,airway=?,ett=?,ukuran=?,komplikasi=?,tindakan=?,nip=?", 11, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Posisi.getSelectedItem().toString(),
            Airway2.getSelectedItem().toString(), Ett.getSelectedItem().toString(), Ukuran.getSelectedItem().toString(), Komplikasi.getText(), Tindakan.getText(), NIP6.getText(), tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 0).toString(), tbAnestesi8.getValueAt(tbAnestesi8.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi8.setValueAt(TNoRw.getText(), tbAnestesi8.getSelectedRow(), 0);
            tbAnestesi8.setValueAt(TNoRM.getText(), tbAnestesi8.getSelectedRow(), 1);
            tbAnestesi8.setValueAt(TPasien.getText(), tbAnestesi8.getSelectedRow(), 2);
            tbAnestesi8.setValueAt(TglLahir.getText(), tbAnestesi8.getSelectedRow(), 3);
            tbAnestesi8.setValueAt(Jk.getText(), tbAnestesi8.getSelectedRow(), 4);
            tbAnestesi8.setValueAt(Alamat.getText(), tbAnestesi8.getSelectedRow(), 5);

            tbAnestesi8.setValueAt(Posisi.getSelectedItem(), tbAnestesi8.getSelectedRow(), 7);
            tbAnestesi8.setValueAt(Airway2.getSelectedItem(), tbAnestesi8.getSelectedRow(), 8);
            tbAnestesi8.setValueAt(Ett.getSelectedItem(), tbAnestesi8.getSelectedRow(), 9);
            tbAnestesi8.setValueAt(Ukuran.getSelectedItem(), tbAnestesi8.getSelectedRow(), 10);
            tbAnestesi8.setValueAt(Komplikasi.getText(), tbAnestesi8.getSelectedRow(), 11);
            tbAnestesi8.setValueAt(Tindakan.getText(), tbAnestesi8.getSelectedRow(), 12);
            tbAnestesi8.setValueAt(NIP6.getText(), tbAnestesi8.getSelectedRow(), 13);
            tbAnestesi8.setValueAt(NamaPegawai6.getText(), tbAnestesi8.getSelectedRow(), 14);

            tbAnestesi8.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi8.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti8() {
        if (Sequel.mengedittf("anestesi_monitoring", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,o2=?,n2o=?,air=?,halothane=?,isoflurane=?,sevolurane=?,dml=?,tvs=?,nadi=?,sistolik=?,diastolik=?,nip=?", 16, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), O22.getText(),
            N2o2.getText(), Air.getText(), Halothane.getText(), Isoflurane.getText(), Sevo.getText(), Dml.getText(), Tvs.getText(), Nadi_1.getText(), Sistolik.getText(), Diastolik.getText(), NIP7.getText(), tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 0).toString(), tbAnestesi9.getValueAt(tbAnestesi9.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi9.setValueAt(TNoRw.getText(), tbAnestesi9.getSelectedRow(), 0);
            tbAnestesi9.setValueAt(TNoRM.getText(), tbAnestesi9.getSelectedRow(), 1);
            tbAnestesi9.setValueAt(TPasien.getText(), tbAnestesi9.getSelectedRow(), 2);
            tbAnestesi9.setValueAt(TglLahir.getText(), tbAnestesi9.getSelectedRow(), 3);
            tbAnestesi9.setValueAt(Jk.getText(), tbAnestesi9.getSelectedRow(), 4);
            tbAnestesi9.setValueAt(Alamat.getText(), tbAnestesi9.getSelectedRow(), 5);

            tbAnestesi9.setValueAt(O22.getText(), tbAnestesi9.getSelectedRow(), 7);
            tbAnestesi9.setValueAt(N2o2.getText(), tbAnestesi9.getSelectedRow(), 8);
            tbAnestesi9.setValueAt(Air.getText(), tbAnestesi9.getSelectedRow(), 9);
            tbAnestesi9.setValueAt(Halothane.getText(), tbAnestesi9.getSelectedRow(), 10);
            tbAnestesi9.setValueAt(Isoflurane.getText(), tbAnestesi9.getSelectedRow(), 11);
            tbAnestesi9.setValueAt(Sevo.getText(), tbAnestesi9.getSelectedRow(), 12);
            tbAnestesi9.setValueAt(Dml.getText(), tbAnestesi9.getSelectedRow(), 13);
            tbAnestesi9.setValueAt(Tvs.getText(), tbAnestesi9.getSelectedRow(), 14);
            tbAnestesi9.setValueAt(Nadi_1.getText(), tbAnestesi9.getSelectedRow(), 14);
            tbAnestesi9.setValueAt(Sistolik.getText(), tbAnestesi9.getSelectedRow(), 14);
            tbAnestesi9.setValueAt(Diastolik.getText(), tbAnestesi9.getSelectedRow(), 14);
            tbAnestesi9.setValueAt(NIP7.getText(), tbAnestesi9.getSelectedRow(), 14);
            tbAnestesi9.setValueAt(NamaPegawai7.getText(), tbAnestesi9.getSelectedRow(), 14);

            tbAnestesi9.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi9.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti9() {
        if (Sequel.mengedittf("anestesi_monitoring2", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,sp02=?,etco2=?,fio2=?,cairan=?,urine=?,perdarahan=?,nip=?", 11, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            Monitoring2_spo2.getText(), Monitoring2_etco2.getText(), Monitoring2_fio2.getText(), Cairan.getText(), Urine.getText(), Perdarahan.getText(), NIP8.getText(), tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 0).toString(), tbAnestesi10.getValueAt(tbAnestesi10.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi10.setValueAt(TNoRw.getText(), tbAnestesi10.getSelectedRow(), 0);
            tbAnestesi10.setValueAt(TNoRM.getText(), tbAnestesi10.getSelectedRow(), 1);
            tbAnestesi10.setValueAt(TPasien.getText(), tbAnestesi10.getSelectedRow(), 2);
            tbAnestesi10.setValueAt(TglLahir.getText(), tbAnestesi10.getSelectedRow(), 3);
            tbAnestesi10.setValueAt(Jk.getText(), tbAnestesi10.getSelectedRow(), 4);
            tbAnestesi10.setValueAt(Alamat.getText(), tbAnestesi10.getSelectedRow(), 5);

            tbAnestesi10.setValueAt(Monitoring2_spo2.getText(), tbAnestesi10.getSelectedRow(), 7);
            tbAnestesi10.setValueAt(Monitoring2_etco2.getText(), tbAnestesi10.getSelectedRow(), 8);
            tbAnestesi10.setValueAt(Monitoring2_fio2.getText(), tbAnestesi10.getSelectedRow(), 9);
            tbAnestesi10.setValueAt(Cairan.getText(), tbAnestesi10.getSelectedRow(), 10);
            tbAnestesi10.setValueAt(Urine.getText(), tbAnestesi10.getSelectedRow(), 11);
            tbAnestesi10.setValueAt(Perdarahan.getText(), tbAnestesi10.getSelectedRow(), 12);
            tbAnestesi10.setValueAt(NIP8.getText(), tbAnestesi10.getSelectedRow(), 13);
            tbAnestesi10.setValueAt(NamaPegawai8.getText(), tbAnestesi10.getSelectedRow(), 14);

            tbAnestesi10.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi10.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti10() {
        if (Sequel.mengedittf("anestesi_cairan", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,kristaloid=?,koloid=?,darah=?,komponendarah=?,urine=?,perdarahan=?,diuresis=?,cairanlain=?,nip=?", 13, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Kristaloid.getText(),
            Koloid.getText(), Darah.getText(), KomponenDarah.getText(), Urin.getText(), Perdarahan2.getText(), Diuresis.getText(), CairanLain.getText(), NIP9.getText(), tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 0).toString(), tbAnestesi11.getValueAt(tbAnestesi11.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi11.setValueAt(TNoRw.getText(), tbAnestesi11.getSelectedRow(), 0);
            tbAnestesi11.setValueAt(TNoRM.getText(), tbAnestesi11.getSelectedRow(), 1);
            tbAnestesi11.setValueAt(TPasien.getText(), tbAnestesi11.getSelectedRow(), 2);
            tbAnestesi11.setValueAt(TglLahir.getText(), tbAnestesi11.getSelectedRow(), 3);
            tbAnestesi11.setValueAt(Jk.getText(), tbAnestesi11.getSelectedRow(), 4);
            tbAnestesi11.setValueAt(Alamat.getText(), tbAnestesi11.getSelectedRow(), 5);

            tbAnestesi11.setValueAt(Kristaloid.getText(), tbAnestesi11.getSelectedRow(), 7);
            tbAnestesi11.setValueAt(Koloid.getText(), tbAnestesi11.getSelectedRow(), 8);
            tbAnestesi11.setValueAt(Darah.getText(), tbAnestesi11.getSelectedRow(), 9);
            tbAnestesi11.setValueAt(KomponenDarah.getText(), tbAnestesi11.getSelectedRow(), 10);
            tbAnestesi11.setValueAt(Urin.getText(), tbAnestesi11.getSelectedRow(), 11);
            tbAnestesi11.setValueAt(Perdarahan2.getText(), tbAnestesi11.getSelectedRow(), 12);
            tbAnestesi11.setValueAt(Diuresis.getText(), tbAnestesi11.getSelectedRow(), 13);
            tbAnestesi11.setValueAt(CairanLain.getText(), tbAnestesi11.getSelectedRow(), 14);
            tbAnestesi11.setValueAt(NIP9.getText(), tbAnestesi11.getSelectedRow(), 15);
            tbAnestesi11.setValueAt(NamaPegawai9.getText(), tbAnestesi11.getSelectedRow(), 16);

            tbAnestesi11.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi11.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti11() {
        if (Sequel.mengedittf("anestesi_sectio", "no_rawat=? and tanggal=?", "no_rawat=?,tanggal=?,keadaaanbayi=?,jk=?,apgar1=?,apgar5=?,bb=?,pb=?,nip=?", 11, new String[]{
            TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            KeadaanBayi.getSelectedItem().toString(), Jk2.getSelectedItem().toString(), Apgar1.getText(), Apgar5.getText(), Bb2.getText(), Pb2.getText(), NIP10.getText(), NIP6.getText(), tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 0).toString(), tbAnestesi12.getValueAt(tbAnestesi12.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi12.setValueAt(TNoRw.getText(), tbAnestesi12.getSelectedRow(), 0);
            tbAnestesi12.setValueAt(TNoRM.getText(), tbAnestesi12.getSelectedRow(), 1);
            tbAnestesi12.setValueAt(TPasien.getText(), tbAnestesi12.getSelectedRow(), 2);
            tbAnestesi12.setValueAt(TglLahir.getText(), tbAnestesi12.getSelectedRow(), 3);
            tbAnestesi12.setValueAt(Jk.getText(), tbAnestesi12.getSelectedRow(), 4);
            tbAnestesi12.setValueAt(Alamat.getText(), tbAnestesi12.getSelectedRow(), 5);

            tbAnestesi12.setValueAt(Posisi.getSelectedItem(), tbAnestesi12.getSelectedRow(), 7);
            tbAnestesi12.setValueAt(Airway2.getSelectedItem(), tbAnestesi12.getSelectedRow(), 8);
            tbAnestesi12.setValueAt(Ett.getSelectedItem(), tbAnestesi12.getSelectedRow(), 9);
            tbAnestesi12.setValueAt(Ukuran.getSelectedItem(), tbAnestesi12.getSelectedRow(), 10);
            tbAnestesi12.setValueAt(Komplikasi.getText(), tbAnestesi12.getSelectedRow(), 11);
            tbAnestesi12.setValueAt(Tindakan.getText(), tbAnestesi12.getSelectedRow(), 12);
            tbAnestesi12.setValueAt(NIP6.getText(), tbAnestesi12.getSelectedRow(), 13);
            tbAnestesi12.setValueAt(NamaPegawai6.getText(), tbAnestesi12.getSelectedRow(), 14);

            tbAnestesi12.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi12.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void ganti12() {
        if (Sequel.mengedittf("anestesi_pasca", "no_rawat=? and tanggal=?", "no_rawat=?,diagnosapostop=?,tanggal=?,pindah=?,ku=?,nadi=?,rr=?,suhu=?,spo2=?,instruksi=?,vetilator=?,tidal=?,mirr=?,ieratio=?,peep=?,pip=?,ps=?,fi02=?,trig=?,nip=?,", 22, new String[]{
            TNoRw.getText(), DiagnosaPostOp.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            Pindah.getSelectedItem().toString(), KeadaaanUmum.getText(), Nadi2.getText(), RR2.getText(), Suhu.getText(), Pasca_Spo2.getText(), Instruksi.getText(), Vetilator.getText(),
            TidalVolume.getText(), Mlrr.getText(), Ieratio.getText(), Peep2.getText(), Pip2.getText(), Ps.getText(), Fio2.getText(), Trigger.getText(), NIP11.getText(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 0).toString(), tbAnestesi13.getValueAt(tbAnestesi13.getSelectedRow(), 6).toString()
        }) == true) {
            tbAnestesi13.setValueAt(TNoRw.getText(), tbAnestesi13.getSelectedRow(), 0);
            tbAnestesi13.setValueAt(TNoRM.getText(), tbAnestesi13.getSelectedRow(), 1);
            tbAnestesi13.setValueAt(TPasien.getText(), tbAnestesi13.getSelectedRow(), 2);
            tbAnestesi13.setValueAt(TglLahir.getText(), tbAnestesi13.getSelectedRow(), 3);
            tbAnestesi13.setValueAt(Jk.getText(), tbAnestesi13.getSelectedRow(), 4);
            tbAnestesi13.setValueAt(Alamat.getText(), tbAnestesi13.getSelectedRow(), 5);

            tbAnestesi13.setValueAt(Posisi.getSelectedItem(), tbAnestesi13.getSelectedRow(), 7);
            tbAnestesi13.setValueAt(Airway2.getSelectedItem(), tbAnestesi13.getSelectedRow(), 8);
            tbAnestesi13.setValueAt(Ett.getSelectedItem(), tbAnestesi13.getSelectedRow(), 9);
            tbAnestesi13.setValueAt(Ukuran.getSelectedItem(), tbAnestesi13.getSelectedRow(), 10);
            tbAnestesi13.setValueAt(Komplikasi.getText(), tbAnestesi13.getSelectedRow(), 11);
            tbAnestesi13.setValueAt(Tindakan.getText(), tbAnestesi13.getSelectedRow(), 12);
            tbAnestesi13.setValueAt(NIP6.getText(), tbAnestesi13.getSelectedRow(), 13);
            tbAnestesi13.setValueAt(NamaPegawai6.getText(), tbAnestesi13.getSelectedRow(), 14);

            tbAnestesi13.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + DTPTgl.getSelectedItem().toString().substring(11, 19), tbAnestesi13.getSelectedRow(), 6);
            emptTeks();
        }
    }

    private void TabTeknikAnestesiHapus() {
        switch (TabTeknikAnestesi.getSelectedIndex()) {
            case 0:
                if (tabMode4.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi4.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_teknikumum where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 0).toString(), tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode4.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_teknikumum where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 0).toString(), tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode4.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }

                break;

            case 1:
                if (tabMode5.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    for (i = 0; i < tbAnestesi6.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_teknikregional where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 0).toString(), tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 7).toString()
                            }) == true) {
                                tabMode5.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_teknikregional where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 0).toString(), tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 7).toString()
                                }) == true) {
                                    tabMode5.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }

                break;
            case 2:
                if (tabMode6.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else {
                    /*
                    "No Rawat", "No RM", "Nama Pasien", "Tanggal Lahir", "JK", "Alamat","Tanggal", "Teknik", "Obat Anestesi", "Pemeliharaan", "Monitoring", "Perubahan Teknik Anestesi", "Alasan",
            "NIP", "Nama Pegawai"
                     */

                    for (i = 0; i < tbAnestesi7.getRowCount(); i++) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryu2tf("delete from anestesi_teknikcombined where no_rawat=? and tanggal=?", 2, new String[]{
                                tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 0).toString(), tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 6).toString()
                            }) == true) {
                                tabMode6.removeRow(i);
                                i--;
                            } else {
                                //    if (Sequel.cekTanggal48jam(tbAnestesi.getValueAt(i, 13).toString(), Sequel.ambiltanggalsekarang()) == true) {
                                if (Sequel.queryu2tf("delete from anestesi_teknikcombined where no_rawat=? and tanggal=?", 2, new String[]{
                                    tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 0).toString(), tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 6).toString()
                                }) == true) {
                                    tabMode6.removeRow(i);
                                    i--;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                }
                break;
            default:
        }

    }

    private void TabTeknikAnestesiGanti() {
        switch (TabTeknikAnestesi.getSelectedIndex()) {
            case 0:
                if (TNoRM.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Induksi.getText().trim().equals("")) {
                    Valid.textKosong(Induksi, "Induksi");
                } else if (Ventilator.getText().trim().equals("")) {
                    Valid.textKosong(Ventilator, "Ventilator");
                } else if (Rr3.getText().trim().equals("")) {
                    Valid.textKosong(Rr3, "Rr3");
                } else if (ERatio.getText().trim().equals("")) {
                    Valid.textKosong(ERatio, "ERatio");
                } else if (Peep.getText().trim().equals("")) {
                    Valid.textKosong(Peep, "Peep");
                } else if (Pip.getText().trim().equals("")) {
                    Valid.textKosong(Pip, "Pip");
                } else if (Fi02.getText().trim().equals("")) {
                    Valid.textKosong(Fi02, "Fi02");
                } else if (Pemeliharaan.getText().trim().equals("")) {
                    Valid.textKosong(Pemeliharaan, "Pemeliharaan");
                } else if (PerhitunganCairan.getText().trim().equals("")) {
                    Valid.textKosong(PerhitunganCairan, "Perhitungan Cairan");
                } else if (Ebv.getText().trim().equals("")) {
                    Valid.textKosong(Ebv, "Ebv");
                } else if (Ebl.getText().trim().equals("")) {
                    Valid.textKosong(Ebl, "Ebl");
                } else if (JenisDarah.getText().trim().equals("")) {
                    Valid.textKosong(JenisDarah, "Jenis Darah");
                } else if (JumlahDarah.getText().trim().equals("")) {
                    Valid.textKosong(JumlahDarah, "Jumlah Darah");
                } else if (Monitoring.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring, "Monitoring");
                } else if (PerubahanTeknikAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(PerubahanTeknikAnestesi, "Perubahan Teknik Anestesi");
                } else if (Alasan.getText().trim().equals("")) {
                    Valid.textKosong(Alasan, "Alasan");
                } else {
                    if (tbAnestesi4.getSelectedRow() > -1) {
                        if (akses.getkode().equals("Admin Utama")) {
                            ganti4();
                        } else {
                            if (NIP3.getText().equals(tbAnestesi4.getValueAt(tbAnestesi4.getSelectedRow(), 26).toString())) {
                                ganti4();
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                }
                break;
            case 1:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (LokasiTusukan.getText().trim().equals("")) {
                    Valid.textKosong(LokasiTusukan, "Lokasi Tusukan");
                } else if (Analgesi.getText().trim().equals("")) {
                    Valid.textKosong(Analgesi, "Analgesi");
                } else if (Anestesi.getText().trim().equals("")) {
                    Valid.textKosong(Anestesi, "Anestesi");
                } else if (Konsentrasi.getText().trim().equals("")) {
                    Valid.textKosong(Konsentrasi, "Konsentrasi");
                } else if (Jumlah.getText().trim().equals("")) {
                    Valid.textKosong(Jumlah, "Jumlah");
                } else {
                    if (tbAnestesi6.getSelectedRow() > -1) {
                        if (akses.getkode().equals("Admin Utama")) {
                            ganti5();
                        } else {
                            if (NIP4.getText().equals(tbAnestesi6.getValueAt(tbAnestesi6.getSelectedRow(), 22).toString())) {
                                ganti5();
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                }
                break;
            case 2:
                if (TNoRw.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (ObatAnestesi.getText().trim().equals("")) {
                    Valid.textKosong(ObatAnestesi, "Obat Anestesi");
                } else if (Pemeliharaan3.getText().trim().equals("")) {
                    Valid.textKosong(Pemeliharaan3, "Pemeliharaan");
                } else if (Monitoring4.getText().trim().equals("")) {
                    Valid.textKosong(Monitoring4, "Monitoring");
                } else if (NIP5.getText().trim().equals("")) {
                    Valid.textKosong(NIP5, "NIP5");
                } else {
                    if (tbAnestesi7.getSelectedRow() > -1) {
                        if (akses.getkode().equals("Admin Utama")) {
                            ganti6();
                        } else {
                            if (NIP5.getText().equals(tbAnestesi7.getValueAt(tbAnestesi7.getSelectedRow(), 13).toString())) {
                                ganti6();
                            } else {
                                JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas penginput yang bersangkutan..!!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                }
                break;
            default:
        }
    }

    private void TabTeknikAnestesiBaru() {
        switch (TabTeknikAnestesi.getSelectedIndex()) {
            case 0:
                break;
            case 1:
                TNoRw.setText("");
                TPasien.setText("");
                TNoRM.setText("");
                TglLahir.setText("");
                Jk.setText("");
                Alamat.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                TNoRw.setText("");
                break;
            case 2:
                break;
            default:
        }
    }
}

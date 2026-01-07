package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTabelPenjualan extends DefaultTableCellRenderer {

    public int kolom;

    // SESUAIKAN FORMAT TANGGAL
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        /* =============================
           WARNA DASAR (ZEBRA)
        ============================== */
        if (row % 2 == 1) {
            c.setBackground(new Color(255, 246, 244));
        } else {
            c.setBackground(Color.WHITE);
        }
        c.setForeground(new Color(70, 70, 70));

        /* =============================
           HANYA KOLOM KADALUARSA (index 3)
        ============================== */
        if (column == 3) {
            try {
                Object tglObj = table.getValueAt(row, 3);
                if (tglObj != null && !tglObj.toString().trim().equals("")) {

                    Date tglKadaluarsa = sdf.parse(tglObj.toString());
                    Date hariIni = new Date();

                    long diffMillis = tglKadaluarsa.getTime() - hariIni.getTime();
                    long diffHari = TimeUnit.MILLISECONDS.toDays(diffMillis);

                    if (diffHari < 0) {
                        // 🔴 EXPIRED
                        c.setBackground(new Color(255, 102, 102));
                        c.setForeground(Color.WHITE);

                    } else if (diffHari <= 30) {
                        // 🟡 MENDEKATI EXPIRED
                        c.setBackground(new Color(255, 255, 153));
                        c.setForeground(Color.BLACK);
                    }
                }
            } catch (Exception e) {
                // abaikan
            }
        }

        /* =============================
           WARNA KOLOM AKTIF (LOGIKA LAMA)
        ============================== */
        if (column == kolom) {
            c.setBackground(new Color(215, 215, 255));
            c.setForeground(Color.WHITE);

            if (table.getValueAt(row, kolom) != null &&
                !table.getValueAt(row, kolom).toString().equals("")) {

                c.setBackground(Color.WHITE);
                c.setForeground(new Color(55, 55, 175));
            }
        }

        /* =============================
           SELECTION PALING AKHIR
        ============================== */
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }

        return c;
    }
}

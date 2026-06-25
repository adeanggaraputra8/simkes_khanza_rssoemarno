//package fungsi;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//
//public class WarnaTabelPO extends DefaultTableCellRenderer {
//
//       public int kolom;
//
//    // Format tanggal dari DB
//    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//    @Override
//    public Component getTableCellRendererComponent(
//            JTable table, Object value, boolean isSelected,
//            boolean hasFocus, int row, int column) {
//
//        Component c = super.getTableCellRendererComponent(
//                table, value, isSelected, hasFocus, row, column);
//
//        /* =============================
//           WARNA DASAR (ZEBRA)
//        ============================== */
//        if (row % 2 == 1) {
//            c.setBackground(new Color(255, 246, 244));
//        } else {
//            c.setBackground(Color.WHITE);
//        }
//        c.setForeground(new Color(70, 70, 70));
//
//        /* =============================
//           KADALUARSA → FULL BARIS
//           Kolom index 18
//        ============================== */
//        try {
//            Object tglObj = table.getValueAt(row, 18);
//            if (tglObj != null && !tglObj.toString().trim().equals("")) {
//
//                Date tglKadaluarsa = sdf.parse(tglObj.toString());
//                Date hariIni = new Date();
//
//                long diffMillis = tglKadaluarsa.getTime() - hariIni.getTime();
//                long diffHari = TimeUnit.MILLISECONDS.toDays(diffMillis);
//
//                if (diffHari < 0) {
//                    // 🔴 EXPIRED
//                    c.setBackground(new Color(255, 102, 102));
//                    c.setForeground(Color.WHITE);
//
//                } else if (diffHari <= 30) {
//                    // 🟡 MENDEKATI EXPIRED
//                    c.setBackground(new Color(255, 255, 153));
//                    c.setForeground(Color.BLACK);
//                }
//            }
//        } catch (Exception e) {
//            // abaikan error
//        }
//
//        /* =============================
//           WARNA KOLOM AKTIF (LOGIKA LAMA)
//           TETAP JALAN, tapi tidak override expired
//        ============================== */
//        if (column == kolom) {
//            // hanya override jika TIDAK expired
//            try {
//                Object tglObj = table.getValueAt(row, 18);
//                Date tglKadaluarsa = sdf.parse(tglObj.toString());
//                long diffHari = TimeUnit.MILLISECONDS.toDays(
//                        tglKadaluarsa.getTime() - new Date().getTime()
//                );
//
//                if (diffHari > 30) {
//                    c.setBackground(new Color(215, 215, 255));
//                    c.setForeground(Color.WHITE);
//
//                    if (table.getValueAt(row, kolom) != null &&
//                        !table.getValueAt(row, kolom).toString().equals("")) {
//
//                        c.setBackground(Color.WHITE);
//                        c.setForeground(new Color(55, 55, 175));
//                    }
//                }
//            } catch (Exception e) {
//                // abaikan
//            }
//        }
//
//        /* =============================
//           SELECTION PALING AKHIR
//        ============================== */
//        if (isSelected) {
//            c.setBackground(table.getSelectionBackground());
//            c.setForeground(table.getSelectionForeground());
//        }
//
//        return c;
//    }
//}

package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTabelPO extends DefaultTableCellRenderer {

    public int kolom;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final DecimalFormat df = new DecimalFormat("#,##0");

    private final Font fontNormal = new Font("Tahoma", Font.PLAIN, 12);
    private final Font fontBold   = new Font("Tahoma", Font.BOLD, 12);

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        /* =============================
           RESET STATE RENDERER (WAJIB)
        ============================== */
        setText(value == null ? "" : value.toString());
        setHorizontalAlignment(LEFT);
        c.setFont(fontNormal);

        if (value instanceof Number) {
            setHorizontalAlignment(RIGHT);
            setText(df.format(value));
        }

        boolean isExpired = false;
        boolean isWarning = false;

        /* =============================
           CEK KADALUARSA (PER BARIS)
           KOLOM INDEX 18
        ============================== */
        try {
            Object tglObj = table.getValueAt(row, 18);
            if (tglObj != null && !tglObj.toString().trim().isEmpty()) {

                Date tglKadaluarsa = sdf.parse(tglObj.toString());
                Date hariIni = new Date();

                long diffHari = TimeUnit.MILLISECONDS.toDays(
                        tglKadaluarsa.getTime() - hariIni.getTime()
                );

                if (diffHari < 0) {
                    isExpired = true;
                } else if (diffHari <= 30) {
                    isWarning = true;
                }
            }
        } catch (Exception e) {
            // abaikan
        }

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
           WARNA STATUS (FULL BARIS)
        ============================== */
        if (isExpired) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
            c.setFont(fontBold);

        } else if (isWarning) {
            c.setBackground(Color.RED);
            c.setForeground(Color.BLACK);
            c.setFont(fontBold);
        }

        /* =============================
           WARNA KOLOM AKTIF
           (TIDAK TIMPA STATUS)
        ============================== */
        if (!isExpired && !isWarning && column == kolom) {
            c.setBackground(new Color(215, 215, 255));
            c.setForeground(new Color(55, 55, 175));
        }

        /* =============================
           SELECTION PALING AKHIR
        ============================== */
        if (isSelected) {
            if (isExpired) {
                c.setBackground(Color.BLACK);
                c.setForeground(Color.WHITE);
            } else if (isWarning) {
                c.setBackground(Color.RED);
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(table.getSelectionBackground());
                c.setForeground(table.getSelectionForeground());
            }
        }

        return c;
    }
}


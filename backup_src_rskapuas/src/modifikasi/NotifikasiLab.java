package modifikasi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NotifikasiLab extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public NotifikasiLab() {
        initComponents();
        setSize(494, 100);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public NotifikasiLab(String noRM, String namaPasien, String jenis) {
        initComponents();
        setSize(494, 100);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        String judul = "HASIL LAB SELESAI";
        if ("LAB MB".equalsIgnoreCase(jenis)) judul = "HASIL LAB MB SELESAI";
        if ("LAB PA".equalsIgnoreCase(jenis)) judul = "HASIL LAB PA SELESAI";

        Pesan.setText(
            "<html><b>" + judul + "</b><br>" +
            "No. RM : " + html(noRM) + "<br>" +
            "Nama : " + html(namaPasien) +
            "</html>"
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                NotifikasiManager.popupDitutup(NotifikasiLab.this);
            }
        });
    }

    private String html(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;")
                .replace(">","&gt;").replace("\"","&quot;");
    }

    public void tampil(int x, int y) {
        setLocation(x, y);
        setAlwaysOnTop(true);
        setVisible(true);
        toFront();
    }

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            NotifikasiLab n = new NotifikasiLab(
                "000123", "BUDI SANTOSO", "LAB");
            n.tampil(
                Toolkit.getDefaultToolkit().getScreenSize().width - 504, 10);
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        alertIconLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Pesan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informasi");

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(80, 80));

        try {
            alertIconLabel.setIcon(new javax.swing.ImageIcon(
                getClass().getResource(
                    "/48x48/iconfinder_Male-User-Warning_49595.png")));
        } catch (Exception e) {
            alertIconLabel.setText("!");
        }
        alertIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alertIconLabel.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel2.add(alertIconLabel);

        jPanel1.setBackground(java.awt.Color.WHITE);
        Pesan.setFont(new java.awt.Font("Tahoma", 0, 12));
        Pesan.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Pesan.setPreferredSize(new java.awt.Dimension(350, 80));
        jPanel1.add(Pesan);

        jPanel3.setPreferredSize(new java.awt.Dimension(40, 80));

        try {
            BtnKeluar.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/picture/exit.png")));
        } catch (Exception e) {}
        BtnKeluar.setToolTipText("Tutup");
        BtnKeluar.setPreferredSize(new java.awt.Dimension(35, 30));
        BtnKeluar.addActionListener(e -> BtnKeluarActionPerformed(e));
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        jPanel3.add(BtnKeluar);

        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanel3, java.awt.BorderLayout.EAST);
        pack();
    }

    private widget.Button BtnKeluar;
    private javax.swing.JLabel Pesan;
    private javax.swing.JLabel alertIconLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
}

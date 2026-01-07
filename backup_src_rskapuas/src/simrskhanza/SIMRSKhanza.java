/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package simrskhanza;
//
//import usu.widget.util.WidgetUtilities;
//
///**
// *
// * @author khanzasoft
// */
//public class SIMRSKhanza {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        WidgetUtilities.invokeLater(() -> {
//           frmUtama utama=frmUtama.getInstance();
//           utama.isWall();
//           utama.setVisible(true);   
//        }); 
//    }
//    
//}


package simrskhanza;

import usu.widget.util.WidgetUtilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class SIMRSKhanza {

    public static void main(String[] args) {
        WidgetUtilities.invokeLater(() -> {
            frmUtama utama = frmUtama.getInstance();
                                    
            SplashScreen splash = new SplashScreen();
            splash.setVisible(true);

            new Thread(() -> {
                try {
                    splash.updateStatus("Membaca konfigurasi aplikasi...");
                    Thread.sleep(1000);

                    splash.updateStatus("Mengecek koneksi database...");
                    Thread.sleep(1000);


                    splash.updateStatus("Memuat modul utama...");
                    Thread.sleep(1000);

                    splash.updateStatus("Menyiapkan tampilan...");
                    Thread.sleep(1000);

                    splash.updateStatus("Selesai! Membuka aplikasi...");
                    Thread.sleep(500);

                    SwingUtilities.invokeLater(() -> {
                        splash.dispose();
                        utama.isWall();
                        utama.setVisible(true); 
                        
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Gagal memuat aplikasi: " + e.getMessage(),
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    splash.dispose();
                }
            }).start();
        });
    }

    // ==============================
    // CLASS SPLASH SCREEN
    // ==============================
    public static class SplashScreen extends JWindow {
        private final JLabel statusLabel = new JLabel("Memulai...", SwingConstants.CENTER);
        private final JProgressBar progress = new JProgressBar();

        public SplashScreen() {
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BorderLayout(0, 10));
            mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

            // Progress bar di atas
            progress.setIndeterminate(true);
            progress.setPreferredSize(new Dimension(300, 6));
            progress.setBorderPainted(false);
            mainPanel.add(progress, BorderLayout.NORTH);

            // Panel tengah: logo dan animasi loading
            JPanel centerPanel = new JPanel();
            centerPanel.setBackground(Color.WHITE);
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            // Logo RS Soemarno
            JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/picture/LOGO_Sumarno copy2.png")));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Spasi kecil antar logo dan gif
            centerPanel.add(Box.createVerticalStrut(15));

            // Loading GIF
            JLabel loadingGif = new JLabel(new ImageIcon(getClass().getResource("/picture/loading.gif")));
            loadingGif.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Tambahkan ke panel tengah
            centerPanel.add(logo);
            centerPanel.add(Box.createVerticalStrut(10));
            centerPanel.add(loadingGif);

            // Label status di bawah
            statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            statusLabel.setForeground(new Color(60, 60, 60));

            mainPanel.add(centerPanel, BorderLayout.CENTER);
            mainPanel.add(statusLabel, BorderLayout.SOUTH);

            setContentPane(mainPanel);
            setSize(420, 440);
            setLocationRelativeTo(null);
        }

        public void updateStatus(String message) {
            SwingUtilities.invokeLater(() -> statusLabel.setText(message));
        }
    }
}

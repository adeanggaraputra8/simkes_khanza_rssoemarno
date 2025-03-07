/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgAdmin.java
 *
 * Created on 04 Des 13, 12:59:34
 */
package khanzahmsanjungan;

import bridging.BPJSCekRujukanKartuPCare;
import fungsi.Prgreesbar2;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Kode
 */
public class DlgCekinMobileJKN extends javax.swing.JDialog {

    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String umur = "0", sttsumur = "Th";
    private String status = "Baru", BASENOREG = "", URUTNOREG = "", aktifjadwal = "";
    private Properties prop = new Properties();
    private int lebar = 0, tinggi = 0;
    private Prgreesbar2 progress=new Prgreesbar2(null,true);

    /**
     * Creates new form DlgAdmin
     *
     * @param parent
     * @param id
     */
    public DlgCekinMobileJKN(java.awt.Frame parent, boolean id) {
        super(parent, id);
        initComponents();

        try {
            ps = koneksi.prepareStatement(
                    "select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                    + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                    + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                    + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where pasien.no_rkm_medis=?");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
            URUTNOREG = prop.getProperty("URUTNOREG");
            BASENOREG = prop.getProperty("BASENOREG");
        } catch (Exception ex) {
            aktifjadwal = "";
            URUTNOREG = "";
            BASENOREG = "";
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        LblKdPoli = new component.Label();
        LblKdDokter = new component.Label();
        NoReg = new component.TextBox();
        NoRawat = new component.TextBox();
        Biaya = new component.TextBox();
        jPanel2 = new javax.swing.JPanel();
        PanelWall = new usu.widget.glass.PanelGlass();
        jPanel1 = new component.Panel();
        NoRMPasien = new component.TextBox();
        jLabel28 = new component.Label();
        BtnClose = new widget.ButtonBig();
        BtnClose2 = new widget.ButtonBig();
        jPanel3 = new javax.swing.JPanel();
        btnAngka8 = new javax.swing.JButton();
        btnAngka7 = new javax.swing.JButton();
        btnAngka9 = new javax.swing.JButton();
        btnAngka4 = new javax.swing.JButton();
        btnAngka5 = new javax.swing.JButton();
        btnAngka6 = new javax.swing.JButton();
        btnAngka2 = new javax.swing.JButton();
        btnAngka1 = new javax.swing.JButton();
        btnAngka3 = new javax.swing.JButton();
        btnAngka0 = new javax.swing.JButton();
        btnAngkaHps = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        LblKdPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdPoli.setText("Norm");
        LblKdPoli.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdPoli.setPreferredSize(new java.awt.Dimension(20, 14));

        LblKdDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdDokter.setText("Norm");
        LblKdDokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdDokter.setPreferredSize(new java.awt.Dimension(20, 14));

        NoReg.setPreferredSize(new java.awt.Dimension(320, 30));
        NoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRegActionPerformed(evt);
            }
        });
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });

        NoRawat.setPreferredSize(new java.awt.Dimension(320, 30));
        NoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRawatActionPerformed(evt);
            }
        });
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });

        Biaya.setPreferredSize(new java.awt.Dimension(320, 30));
        Biaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BiayaActionPerformed(evt);
            }
        });
        Biaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BiayaKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setBackground(new java.awt.Color(238, 238, 255));
        jPanel2.setForeground(new java.awt.Color(238, 238, 255));

        PanelWall.setBackground(new java.awt.Color(238, 238, 255));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/48x48/mobilejkn.png"))); // NOI18N
        PanelWall.setForeground(new java.awt.Color(238, 238, 255));
        PanelWall.setPreferredSize(new java.awt.Dimension(500, 150));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(238, 238, 255));

        javax.swing.GroupLayout PanelWallLayout = new javax.swing.GroupLayout(PanelWall);
        PanelWall.setLayout(PanelWallLayout);
        PanelWallLayout.setHorizontalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        PanelWallLayout.setVerticalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel2.add(PanelWall);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(238, 238, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 215, 255)), "::[ Cek Data Pasien!!! ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 30), new java.awt.Color(0, 131, 62))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 131, 62));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 70));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        NoRMPasien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 131, 62), 2, true));
        NoRMPasien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NoRMPasien.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        NoRMPasien.setPreferredSize(new java.awt.Dimension(350, 75));
        NoRMPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRMPasienActionPerformed(evt);
            }
        });
        NoRMPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMPasienKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(NoRMPasien, gridBagConstraints);

        jLabel28.setForeground(new java.awt.Color(0, 131, 62));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("No Peserta / No RM");
        jLabel28.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(500, 75));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipady = 5;
        jPanel1.add(jLabel28, gridBagConstraints);

        BtnClose.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/exit.png"))); // NOI18N
        BtnClose.setMnemonic('U');
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        BtnClose.setIconTextGap(2);
        BtnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        jPanel1.add(BtnClose, gridBagConstraints);

        BtnClose2.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose2.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/konfirmasi.png"))); // NOI18N
        BtnClose2.setMnemonic('U');
        BtnClose2.setToolTipText("Alt+U");
        BtnClose2.setFont(new java.awt.Font("Poppins", 1, 11)); // NOI18N
        BtnClose2.setIconTextGap(0);
        BtnClose2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose2.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClose2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        jPanel1.add(BtnClose2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(238, 238, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 399));

        btnAngka8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka8.setText("8");
        btnAngka8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka8ActionPerformed(evt);
            }
        });

        btnAngka7.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka7.setText("7");
        btnAngka7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka7ActionPerformed(evt);
            }
        });

        btnAngka9.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka9.setText("9");
        btnAngka9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka9ActionPerformed(evt);
            }
        });

        btnAngka4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka4.setText("4");
        btnAngka4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka4ActionPerformed(evt);
            }
        });

        btnAngka5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka5.setText("5");
        btnAngka5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka5ActionPerformed(evt);
            }
        });

        btnAngka6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka6.setText("6");
        btnAngka6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka6ActionPerformed(evt);
            }
        });

        btnAngka2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka2.setText("2");
        btnAngka2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka2ActionPerformed(evt);
            }
        });

        btnAngka1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka1.setText("1");
        btnAngka1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka1ActionPerformed(evt);
            }
        });

        btnAngka3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka3.setText("3");
        btnAngka3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka3ActionPerformed(evt);
            }
        });

        btnAngka0.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka0.setText("0");
        btnAngka0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka0ActionPerformed(evt);
            }
        });

        btnAngkaHps.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAngkaHps.setText("<-");
        btnAngkaHps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngkaHpsActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnClear.setText("C");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka0, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnAngkaHps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAngka4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAngka5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAngka6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAngkaHps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void NoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegKeyPressed

    private void NoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatActionPerformed

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatKeyPressed

    private void BiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BiayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaActionPerformed

    private void BiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BiayaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaKeyPressed

    private void NoRMPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRMPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRMPasienActionPerformed

    private void NoRMPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorkartu='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status='Belum' and  referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPMobileJKN form = new DlgRegistrasiSEPMobileJKN(null, true);
                form.tampil(NoRMPasien.getText());
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status='Belum' and referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPMobileJKN form = new DlgRegistrasiSEPMobileJKN(null, true);
                form.tampil(Sequel.cariIsi("select referensi_mobilejkn_bpjs.nomorkartu from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status='Belum' and referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()"));
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data Booking MobileJKN tidak ditemukan. ");
            }

        }

    }//GEN-LAST:event_NoRMPasienKeyPressed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClose2ActionPerformed
           
        if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorkartu='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status in ('Belum','Checkin') and  referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()") > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRegistrasiSEPMobileJKN form = new DlgRegistrasiSEPMobileJKN(null, true);
            form.tampil(NoRMPasien.getText());
            form.setSize(this.getWidth(), this.getHeight());
            form.setLocationRelativeTo(jPanel1);
            this.dispose();
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status in ('Belum','Checkin') and referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()") > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRegistrasiSEPMobileJKN form = new DlgRegistrasiSEPMobileJKN(null, true);
            form.tampil(Sequel.cariIsi("select referensi_mobilejkn_bpjs.nomorkartu from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.status in ('Belum','Checkin') and referensi_mobilejkn_bpjs.tanggalperiksa=CURRENT_DATE()"));
            form.setSize(this.getWidth(), this.getHeight());
            form.setLocationRelativeTo(jPanel1);
            this.dispose();
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Data Booking MobileJKN tidak ditemukan. ");
        }

    }//GEN-LAST:event_BtnClose2ActionPerformed

    private void btnAngka8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka8ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "8");
    }//GEN-LAST:event_btnAngka8ActionPerformed

    private void btnAngka7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka7ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "7");
    }//GEN-LAST:event_btnAngka7ActionPerformed

    private void btnAngka9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka9ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "9");
    }//GEN-LAST:event_btnAngka9ActionPerformed

    private void btnAngka4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka4ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "4");
    }//GEN-LAST:event_btnAngka4ActionPerformed

    private void btnAngka5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka5ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "5");
    }//GEN-LAST:event_btnAngka5ActionPerformed

    private void btnAngka6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka6ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "6");
    }//GEN-LAST:event_btnAngka6ActionPerformed

    private void btnAngka2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka2ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "2");
    }//GEN-LAST:event_btnAngka2ActionPerformed

    private void btnAngka1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka1ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "1");
    }//GEN-LAST:event_btnAngka1ActionPerformed

    private void btnAngka3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka3ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "3");
    }//GEN-LAST:event_btnAngka3ActionPerformed

    private void btnAngka0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka0ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "0");
    }//GEN-LAST:event_btnAngka0ActionPerformed

    private void btnAngkaHpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngkaHpsActionPerformed
        int length = NoRMPasien.getText().length();
        int number = NoRMPasien.getText().length() - 1;
        String store;
        if (length > 0) {
            StringBuilder back = new StringBuilder(NoRMPasien.getText());
            back.deleteCharAt(number);
            store = back.toString();
            NoRMPasien.setText(store);
        }
    }//GEN-LAST:event_btnAngkaHpsActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        int length = NoRMPasien.getText().length();
        if (length > 0) {
            NoRMPasien.setText("");
        }
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCekinMobileJKN dialog = new DlgCekinMobileJKN(new javax.swing.JFrame(), true);
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
    private component.TextBox Biaya;
    private widget.ButtonBig BtnClose;
    private widget.ButtonBig BtnClose2;
    private component.Label LblKdDokter;
    private component.Label LblKdPoli;
    private component.TextBox NoRMPasien;
    private component.TextBox NoRawat;
    private component.TextBox NoReg;
    private usu.widget.glass.PanelGlass PanelWall;
    private javax.swing.JButton btnAngka0;
    private javax.swing.JButton btnAngka1;
    private javax.swing.JButton btnAngka2;
    private javax.swing.JButton btnAngka3;
    private javax.swing.JButton btnAngka4;
    private javax.swing.JButton btnAngka5;
    private javax.swing.JButton btnAngka6;
    private javax.swing.JButton btnAngka7;
    private javax.swing.JButton btnAngka8;
    private javax.swing.JButton btnAngka9;
    private javax.swing.JButton btnAngkaHps;
    private javax.swing.JButton btnClear;
    private component.Label jLabel28;
    private component.Panel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    public void setPasien(String norm, String kodepoli, String kddokter) {
    }

    private void UpdateUmur() {

    }

    private void isNumber() {
    }
}

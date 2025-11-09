/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package modifikasi;

import bridging.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dosen
 */
public final class BPJSListDataPRB extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0,row=0;
    private ApiBPJS api=new ApiBPJS();
    private String URL="",link="",utc="",requestJson="", user="",URL2="",link2="",requestJson2="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectMapper mapper2 = new ObjectMapper();
    private JsonNode root,root2;
    private JsonNode nameNode,nameNode2;
    private JsonNode response,response2;
    private Properties prop = new Properties();

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSListDataPRB(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new String[]{"No. SEP", "No. SRB", "Tgl. SRB","Nama Pasien", "Alamat","Email", "Kode Program", "Nama Program",
            "kode Dokter","Nama Dokter","User","Keterangan","Saran"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else {
                column.setPreferredWidth(150);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        
         tabMode1 = new DefaultTableModel(null, new String[]{"No. SEP", "No. SRB", "Kode Obat","Nama Obat", "Jumlah","Signa 1", "Signa 2"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbobat.setModel(tabMode1);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbobat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbobat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbobat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(80);
            }
        }
        tbobat.setDefaultRenderer(Object.class, new WarnaTable());
        
  
        
        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        try {
            link=koneksiDB.URLAPIBPJS();
            link2=koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
              
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnTampilkanData = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel17 = new widget.Label();
        LCount = new widget.Label();
        jLabel7 = new widget.Label();
        jLabel16 = new widget.Label();
        Tcari = new widget.TextBox();
        TanggalAwal = new widget.Tanggal();
        jLabel21 = new widget.Label();
        TanggalAkhir = new widget.Tanggal();
        BtnCari = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel8 = new widget.Label();
        LCount1 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbobat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(192, 24));

        MnTampilkanData.setBackground(new java.awt.Color(255, 255, 254));
        MnTampilkanData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTampilkanData.setForeground(java.awt.Color.darkGray);
        MnTampilkanData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTampilkanData.setText("Tampil Data Terbaru");
        MnTampilkanData.setName("MnTampilkanData"); // NOI18N
        MnTampilkanData.setPreferredSize(new java.awt.Dimension(170, 28));
        MnTampilkanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTampilkanDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTampilkanData);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ List PRB BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 452));

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamarMouseClicked(evt);
            }
        });
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(LCount);

        jLabel7.setText("Record Tabel Kiri :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass6.add(jLabel7);

        jLabel16.setText("Keyword :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass6.add(jLabel16);

        Tcari.setName("Tcari"); // NOI18N
        Tcari.setPreferredSize(new java.awt.Dimension(200, 23));
        Tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TcariActionPerformed(evt);
            }
        });
        Tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcariKeyPressed(evt);
            }
        });
        panelGlass6.add(Tcari);

        TanggalAwal.setForeground(new java.awt.Color(50, 70, 50));
        TanggalAwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2025" }));
        TanggalAwal.setDisplayFormat("dd-MM-yyyy");
        TanggalAwal.setName("TanggalAwal"); // NOI18N
        TanggalAwal.setOpaque(false);
        TanggalAwal.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass6.add(TanggalAwal);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass6.add(jLabel21);

        TanggalAkhir.setForeground(new java.awt.Color(50, 70, 50));
        TanggalAkhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2025" }));
        TanggalAkhir.setDisplayFormat("dd-MM-yyyy");
        TanggalAkhir.setName("TanggalAkhir"); // NOI18N
        TanggalAkhir.setOpaque(false);
        TanggalAkhir.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass6.add(TanggalAkhir);

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
        panelGlass6.add(BtnCari);

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
        panelGlass6.add(BtnSimpan);

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
        panelGlass6.add(BtnKeluar);

        jLabel8.setText("Record Tabel Kanan :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass6.add(jLabel8);

        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(LCount1);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbobat.setAutoCreateRowSorter(true);
        tbobat.setName("tbobat"); // NOI18N
        Scroll1.setViewportView(tbobat);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
//            Valid.pindah(evt,NoKaBPJS,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampildata(Valid.SetTgl(TanggalAwal.getSelectedItem().toString()), Valid.SetTgl(TanggalAkhir.getSelectedItem().toString()), Tcari.getText());
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKamarMouseClicked

    private void TcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TcariActionPerformed

    private void TcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcariKeyPressed

    }//GEN-LAST:event_TcariKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
         if (tabMode.getRowCount() == 0 && tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data pada ke dua tabel kosong ...!");
                return;
           }

            try {
                for (int i = 0; i < tbKamar.getRowCount(); i++) {
                    String noSEP = tbKamar.getValueAt(i, 0).toString();
                    String noSRB = tbKamar.getValueAt(i, 1).toString();

                    // === CEK APAKAH DATA SUDAH ADA ===
                    if (Sequel.cariInteger("SELECT COUNT(*) FROM bridging_srb_bpjs WHERE no_srb='" + noSRB + "' AND no_sep='" + noSEP + "'") == 0) {
                        // === INSERT BARU ===
                        boolean inserted = Sequel.menyimpantf2(
                            "bridging_srb_bpjs",
                            "?,?,?,?,?,?,?,?,?,?,?,?",
                            "No.SEP SRB",
                            12,
                            new String[]{
                                tbKamar.getValueAt(i, 0).toString(),
                                tbKamar.getValueAt(i, 1).toString(),
                                tbKamar.getValueAt(i, 2).toString(),
                                tbKamar.getValueAt(i, 4).toString(),
                                tbKamar.getValueAt(i, 5).toString(),
                                tbKamar.getValueAt(i, 6).toString(),
                                tbKamar.getValueAt(i, 7).toString(),
                                tbKamar.getValueAt(i, 8).toString(),
                                tbKamar.getValueAt(i, 9).toString(),
                                tbKamar.getValueAt(i, 10).toString(),
                                tbKamar.getValueAt(i, 11).toString(),
                                tbKamar.getValueAt(i, 12).toString()
                            }
                        );

                        if (inserted) {
                            for (int j = 0; j < tbobat.getRowCount(); j++) {
                                // hanya simpan obat milik pasien ini
                                if (tbobat.getValueAt(j, 0).toString().equals(noSEP)
                                    && tbobat.getValueAt(j, 1).toString().equals(noSRB)) {

                                    Sequel.menyimpan("bridging_srb_bpjs_obat", "?,?,?,?,?,?,?", 7, new String[]{
                                        tbobat.getValueAt(j, 0).toString(),
                                        tbobat.getValueAt(j, 1).toString(),
                                        tbobat.getValueAt(j, 2).toString(),
                                        tbobat.getValueAt(j, 3).toString(),
                                        tbobat.getValueAt(j, 4).toString(),
                                        tbobat.getValueAt(j, 5).toString(),
                                        tbobat.getValueAt(j, 6).toString()
                                    });
                                }
                            }
                        }
                    } else {
                        // === UPDATE JIKA SUDAH ADA ===
                        boolean updated = Sequel.queryu2tf(
                            "UPDATE bridging_srb_bpjs SET no_sep=?, no_srb=?, tgl_srb=?, alamat=?, email=?, kodeprogram=?, namaprogram=?, kodedpjp=?, nmdpjp=?, user=?, keterangan=?, saran=? WHERE no_sep=? AND no_srb=?",
                            14,
                            new String[]{
                                tbKamar.getValueAt(i, 0).toString(),
                                tbKamar.getValueAt(i, 1).toString(),
                                tbKamar.getValueAt(i, 2).toString(),
                                tbKamar.getValueAt(i, 4).toString(),
                                tbKamar.getValueAt(i, 5).toString(),
                                tbKamar.getValueAt(i, 6).toString(),
                                tbKamar.getValueAt(i, 7).toString(),
                                tbKamar.getValueAt(i, 8).toString(),
                                tbKamar.getValueAt(i, 9).toString(),
                                tbKamar.getValueAt(i, 10).toString(),
                                tbKamar.getValueAt(i, 11).toString(),
                                tbKamar.getValueAt(i, 12).toString(),
                                tbKamar.getValueAt(i, 0).toString(),
                                tbKamar.getValueAt(i, 1).toString()
                            }
                        );

                        if (updated) {
                            Sequel.meghapus("bridging_srb_bpjs_obat", "no_sep", "no_srb", noSEP, noSRB);

                            for (int j = 0; j < tbobat.getRowCount(); j++) {
                                if (tbobat.getValueAt(j, 0).toString().equals(noSEP)
                                    && tbobat.getValueAt(j, 1).toString().equals(noSRB)) {

                                    Sequel.menyimpan("bridging_srb_bpjs_obat", "?,?,?,?,?,?,?", 7, new String[]{
                                        tbobat.getValueAt(j, 0).toString(),
                                        tbobat.getValueAt(j, 1).toString(),
                                        tbobat.getValueAt(j, 2).toString(),
                                        tbobat.getValueAt(j, 3).toString(),
                                        tbobat.getValueAt(j, 4).toString(),
                                        tbobat.getValueAt(j, 5).toString(),
                                        tbobat.getValueAt(j, 6).toString()
                                    });
                                }
                            }
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan/diupdate!");
            } catch (Exception ex) {
                System.out.println("Notifikasi error: " + ex);
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data!");
            }
         
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnCari,BtnKeluar);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void MnTampilkanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTampilkanDataActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampildataYangBelumAda(Valid.SetTgl(TanggalAwal.getSelectedItem().toString()), Valid.SetTgl(TanggalAkhir.getSelectedItem().toString()), Tcari.getText());
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTampilkanDataActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSListDataPRB dialog = new BPJSListDataPRB(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnTampilkanData;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.Tanggal TanggalAkhir;
    private widget.Tanggal TanggalAwal;
    private widget.TextBox Tcari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel21;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    private widget.Table tbobat;
    // End of variables declaration//GEN-END:variables

    public void tampildata(String tanggalawal, String tanggalakhir, String cari) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
	    requestEntity = new HttpEntity(headers);
            URL = link+"/prb/tglMulai/"+tanggalawal+"/tglAkhir/"+tanggalakhir;
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode);
                 Valid.tabelKosong(tabMode1);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc)).path("prb");
                //response = root.path("response");
                if(response.path("list").isArray()){
                    for(JsonNode list:response.path("list")){ 
                        if(list.path("no").asText().toLowerCase().contains(cari.toLowerCase())||
                                    list.path("noSEP").asText().toLowerCase().contains(cari.toLowerCase())||
                                    list.path("noSRB").asText().toLowerCase().contains(cari.toLowerCase())||
                                    list.path("DPJP").path("nama").asText().toLowerCase().contains(cari.toLowerCase())||
                                    list.path("peserta").path("nama").asText().toLowerCase().contains(cari.toLowerCase())){
                            tabMode.addRow(new Object[]{
                                list.path("noSEP").asText(),
                                list.path("noSRB").asText(),
                                list.path("tglSRB").asText(),
                                list.path("peserta").path("nama").asText(),
                                list.path("peserta").path("alamat").asText(),
                                list.path("peserta").path("email").asText(),
                                list.path("programPRB").path("kode").asText(),
                                list.path("programPRB").path("nama").asText(),
                                list.path("DPJP").path("kode").asText(),
                                list.path("DPJP").path("nama").asText(),
                                list.path("DPJP").path("kode").asText(),
                                list.path("keterangan").asText(),
                                list.path("saran").asText(),
                            });
                        }
                        
                        URL2 = link2+"/prb/"+list.path("noSRB").asText()+"/nosep/"+list.path("noSEP").asText();
                        root2 = mapper2.readTree(api.getRest().exchange(URL2, HttpMethod.GET, requestEntity, String.class).getBody());
                        nameNode2 = root2.path("metaData");
                        if(nameNode2.path("code").asText().equals("200")){
                            response2 = mapper2.readTree(api.Decrypt(root2.path("response").asText(),utc)).path("prb");
                                if(response2.path("obat").path("obat").isArray()){
                                    for(JsonNode list2 : response2.path("obat").path("obat")) {
                                    tabMode1.addRow(new Object[]{
                                        response2.path("noSEP").asText(),
                                        response2.path("noSRB").asText(),
                                        list2.path("kdObat").asText(),
                                        list2.path("nmObat").asText(),
                                        list2.path("jmlObat").asText(),
                                        list2.path("signa1").asText(),
                                        list2.path("signa2").asText()
                                    });
                                }
                            }                    
                      } else {
                            JOptionPane.showMessageDialog(null, nameNode2.path("message").asText());
                      }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
         LCount.setText(""+tabMode.getRowCount());
         LCount1.setText(""+tabMode1.getRowCount());
    }
    
    private void tampildataYangBelumAda(String tanggalawal, String tanggalakhir, String cari) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            requestEntity = new HttpEntity(headers);

            URL = link + "/prb/tglMulai/" + tanggalawal + "/tglAkhir/" + tanggalakhir;
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                Valid.tabelKosong(tabMode1);

                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("prb");

                if (response.path("list").isArray()) {
                    for (JsonNode list : response.path("list")) {
                        String noSEP = list.path("noSEP").asText();
                        String noSRB = list.path("noSRB").asText();

                        // 🔍 Cek apakah sudah ada di database lokal
                        boolean sudahAda = Sequel.cariInteger(
                            "SELECT COUNT(*) FROM bridging_srb_bpjs WHERE no_srb = ? AND no_sep = ?", 
                            noSRB, noSEP
                        ) > 0;

                        if (sudahAda) {
                            // Lewati data yang sudah ada di database
                            continue;
                        }

                        // Filter pencarian manual (cari)
                        if (noSRB.toLowerCase().contains(cari.toLowerCase()) ||
                            noSEP.toLowerCase().contains(cari.toLowerCase()) ||
                            list.path("DPJP").path("nama").asText().toLowerCase().contains(cari.toLowerCase()) ||
                            list.path("peserta").path("nama").asText().toLowerCase().contains(cari.toLowerCase())) {

                            // ✅ Tambahkan ke tabel utama (pasien)
                            tabMode.addRow(new Object[]{
                                noSEP,
                                noSRB,
                                list.path("tglSRB").asText(),
                                list.path("peserta").path("nama").asText(),
                                list.path("peserta").path("alamat").asText(),
                                list.path("peserta").path("email").asText(),
                                list.path("programPRB").path("kode").asText(),
                                list.path("programPRB").path("nama").asText(),
                                list.path("DPJP").path("kode").asText(),
                                list.path("DPJP").path("nama").asText(),
                                list.path("DPJP").path("kode").asText(),
                                list.path("keterangan").asText(),
                                list.path("saran").asText()
                            });
                        }

                        // 🔁 Ambil data obat PRB dari API detail
                        URL2 = link2 + "/prb/" + noSRB + "/nosep/" + noSEP;
                        root2 = mapper2.readTree(api.getRest().exchange(URL2, HttpMethod.GET, requestEntity, String.class).getBody());
                        nameNode2 = root2.path("metaData");

                        if (nameNode2.path("code").asText().equals("200")) {
                            response2 = mapper2.readTree(api.Decrypt(root2.path("response").asText(), utc)).path("prb");

                            if (response2.path("obat").path("obat").isArray()) {
                                for (JsonNode list2 : response2.path("obat").path("obat")) {
                                    tabMode1.addRow(new Object[]{
                                        response2.path("noSEP").asText(),
                                        response2.path("noSRB").asText(),
                                        list2.path("kdObat").asText(),
                                        list2.path("nmObat").asText(),
                                        list2.path("jmlObat").asText(),
                                        list2.path("signa1").asText(),
                                        list2.path("signa2").asText()
                                    });
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, nameNode2.path("message").asText());
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }

        LCount.setText("" + tabMode.getRowCount());
        LCount1.setText("" + tabMode1.getRowCount());
    }


    public JTable getTable(){
        return tbKamar;
    }
       
    
     private void getData() {
        if(tbKamar.getSelectedRow()!= -1){
              try {
//                    tampilobat(tbKamar.getValueAt(tbKamar.getSelectedRow(),3).toString(),Valid.SetTgl(TanggalAwal.getSelectedItem().toString()), Valid.SetTgl(TanggalAkhir.getSelectedItem().toString()));
            } catch (Exception e) {
                 System.out.println("Notif : "+e);
            }
        }
     
     }
      
    
    public void setNoRm(String nokartu, Date tgl1, Date tgl2) {        
        Tcari.setText(nokartu);
        TanggalAwal.setDate(tgl1);
        TanggalAkhir.setDate(tgl2);
    }
    
}

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

package bridging;

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
public final class ApotekBPJSRiwayatPelayananResep extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeobat;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0,row=0;
    private ApiApotekBPJS api=new ApiApotekBPJS();
    private String URL="",link="",utc="",requestJson="",kodeppk="",namafaskes="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private Properties prop = new Properties();

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public ApotekBPJSRiwayatPelayananResep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new String[]{"NO RESEP", "NO APOTIK", "NO SEP", "NO KARTU", "NAMA", "TGL ENTRY", "TGL RESEP", "TGL PEL RESEP", "BY TAG RESEP", "BY VER RESEP", "JENIS", "FASKES"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(150);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeobat = new DefaultTableModel(null, new String[]{
            "No.Kartu", "Nama Peserta", "tanggal lahir", "No.SJP", "Tgl Pelayanan", "No Resep", "Kode Obat", "Nama Obat","Jml Obat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbobat.setModel(tabModeobat);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbobat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbobat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbobat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbobat.setDefaultRenderer(Object.class, new WarnaTable());
        tbobat.setRowSorter(null);
        
        try {
            link = koneksiDB.URLAPIAPOTEKBPJS();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            kodeppk=prop.getProperty("KODEPPKAPOTEKBPJS");
        } catch (Exception ex) {
            kodeppk="";
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
        ppTampilkanFaskses = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel17 = new widget.Label();
        jLabel16 = new widget.Label();
        Tcari = new widget.TextBox();
        TanggalAwal = new widget.Tanggal();
        jLabel21 = new widget.Label();
        TanggalAkhir = new widget.Tanggal();
        Jenis = new javax.swing.JComboBox<>();
        BtnCari = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbobat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(192, 24));

        ppTampilkanFaskses.setBackground(new java.awt.Color(255, 255, 254));
        ppTampilkanFaskses.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppTampilkanFaskses.setForeground(new java.awt.Color(50, 50, 50));
        ppTampilkanFaskses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppTampilkanFaskses.setText("Tampilkan Faskes");
        ppTampilkanFaskses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppTampilkanFaskses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppTampilkanFaskses.setName("ppTampilkanFaskses"); // NOI18N
        ppTampilkanFaskses.setPreferredSize(new java.awt.Dimension(150, 26));
        ppTampilkanFaskses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppTampilkanFasksesActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppTampilkanFaskses);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Daftar Resep Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 452));

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setComponentPopupMenu(jPopupMenu1);
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

        jLabel16.setText("Cari Nama/No.Ka :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(110, 23));
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
        TanggalAwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-05-2025" }));
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
        TanggalAkhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-05-2025" }));
        TanggalAkhir.setDisplayFormat("dd-MM-yyyy");
        TanggalAkhir.setName("TanggalAkhir"); // NOI18N
        TanggalAkhir.setOpaque(false);
        TanggalAkhir.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass6.add(TanggalAkhir);

        Jenis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tgl Pelayanan", "Tgl Resep" }));
        Jenis.setName("Jenis"); // NOI18N
        Jenis.setPreferredSize(new java.awt.Dimension(116, 23));
        panelGlass6.add(Jenis);

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
        panelGlass6.add(BtnPrint);

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass6.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(LCount);

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
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            for(int i=0;i<tabMode.getRowCount();i++){  
                Sequel.menyimpan("temporary","'"+i+"','"+
                                tabMode.getValueAt(i,0).toString()+"','"+
                                tabMode.getValueAt(i,1).toString()+"','"+
                                tabMode.getValueAt(i,2).toString()+"','"+
                                tabMode.getValueAt(i,3).toString()+"','"+
                                tabMode.getValueAt(i,4).toString()+"','"+
                                tabMode.getValueAt(i,5).toString()+"','"+
                                tabMode.getValueAt(i,6).toString()+"','"+
                                tabMode.getValueAt(i,7).toString()+"','"+
                                tabMode.getValueAt(i,8).toString()+"','"+
                                tabMode.getValueAt(i,9).toString()+"','"+
                                tabMode.getValueAt(i,10).toString()+"','"+
                                tabMode.getValueAt(i,11).toString()+"','"+
                                tabMode.getValueAt(i,12).toString()+"','"+
                                tabMode.getValueAt(i,14).toString()+"','"+
                                tabMode.getValueAt(i,15).toString()+"','"+
                                tabMode.getValueAt(i,16).toString()+"','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Daftar Pelayanan Obat Apotek BPJS"); 
            }
            
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            //param.put("peserta","No.Peserta : "+NoKartu.getText()+" Nama Peserta : "+NamaPasien.getText());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptApotekBPJSDaftarPelayananKlaim.jasper","report","[ Daftar Pelayanan Apotek BPJS ]","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
//            Valid.pindah(evt,NoKaBPJS,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String jnstanggal = "";

        if (Jenis.getSelectedIndex() == 0) {
            jnstanggal = "TGLPELSJP";
        } else {
            jnstanggal = "TGLRSP";
        }
        tampilresep(kodeppk, jnstanggal, Valid.SetTgl(TanggalAwal.getSelectedItem().toString()) + " 00:00:00", Valid.SetTgl(TanggalAkhir.getSelectedItem().toString()) + " 23:59:59", String.valueOf(Jenis.getSelectedIndex()), Tcari.getText());
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

    private void ppTampilkanFasksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppTampilkanFasksesActionPerformed
        if (tbKamar.getSelectedRow() != -1) {
           row=tbKamar.getRowCount();
            for(i=0;i<row;i++){
                    try{     
                        tampilfaskes(tbKamar.getValueAt(i, 11).toString());
                } catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih tampilkan data..!!");
        }
    }//GEN-LAST:event_ppTampilkanFasksesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSRiwayatPelayananResep dialog = new ApotekBPJSRiwayatPelayananResep(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private javax.swing.JComboBox<String> Jenis;
    private widget.Label LCount;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass6;
    private javax.swing.JMenuItem ppTampilkanFaskses;
    private widget.Table tbKamar;
    private widget.Table tbobat;
    // End of variables declaration//GEN-END:variables

    public void tampilresep(String kodeppk, String jnstgl, String tanggalawal, String tanggalakhir, String jenisobat, String cari) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/daftarresep/";
            System.out.println(URL);
            requestJson = "{\n"
                    + "            \"kdppk\": \"" + kodeppk + "\",\n"
                    + "            \"KdJnsObat\": \"" + jenisobat + "\",\n"
                    + "            \"JnsTgl\": \"" + jnstgl + "\",\n"
                    + "            \"TglMulai\": \"" + tanggalawal + "\",\n"
                    + "            \"TglAkhir\": \"" + tanggalakhir + "\"\n"
                    + "        } ";
            requestEntity = new HttpEntity(requestJson, headers);
//            System.out.println(requestEntity);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                System.out.println(response);
                if (response.isArray()) {
                    for (JsonNode list : response) {
                        if(list.path("NOKARTU").asText().toLowerCase().contains(cari.toLowerCase())||
                                list.path("NAMA").asText().toLowerCase().contains(cari.toLowerCase())){
                        tabMode.addRow(new Object[]{
                            list.path("NORESEP").asText(),
                            list.path("NOAPOTIK").asText(),
                            list.path("NOSEP_KUNJUNGAN").asText(),
                            list.path("NOKARTU").asText(),
                            list.path("NAMA").asText(),
                            list.path("TGLENTRY").asText(),
                            list.path("TGLRESEP").asText(),
                            list.path("TGLPELRSP").asText(),
                            list.path("BYTAGRSP").asText(),
                            list.path("BYVERRSP").asText(),
                            list.path("KDJNSOBAT").asText(),
                            list.path("FASKESASAL").asText()
                        });
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
    }  

    public JTable getTable(){
        return tbKamar;
    }
    
     public void tampilobat(String noKartu, String tglAwal, String tglAkhir) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/riwayatobat/" + tglAwal + "/" + tglAkhir + "/" + noKartu + "";
            System.out.println(URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabModeobat);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                tabModeobat.addRow(new Object[]{
                    response.path("list").path("nokartu").asText(), 
                    response.path("list").path("namapeserta").asText(), 
                    response.path("list").path("tgllhr").asText(),
                    "","","","","",""}
                );
                
                if (response.path("list").path("history").isArray()) {
                    for (JsonNode list : response.path("list").path("history")) {
                        tabModeobat.addRow(new Object[]{
                            "","","",
                            list.path("nosjp").asText(),
                            list.path("tglpelayanan").asText(),
                            list.path("noresep").asText(),
                            list.path("kodeobat").asText(),
                            list.path("namaobat").asText(),
                            list.path("jmlobat").asText()
                        });
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
    }   
    
     private void getData() {
        if(tbKamar.getSelectedRow()!= -1){
              try {
                    tampilobat(tbKamar.getValueAt(tbKamar.getSelectedRow(),3).toString(),Valid.SetTgl(TanggalAwal.getSelectedItem().toString()), Valid.SetTgl(TanggalAkhir.getSelectedItem().toString()));
            } catch (Exception e) {
                 System.out.println("Notif : "+e);
            }
        }
     
     }
     
    public void tampilfaskes(String faskes) {
        try {
            namafaskes="";
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIAPOTEKBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIAPOTEKBPJS());
	    requestEntity = new HttpEntity(headers);
            URL = link+"/referensi/ppk/1/"+faskes;	
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");            
            if(nameNode.path("code").asText().equals("200")){ 
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                //response = root.path("response");
                if(response.path("list").isArray()){
                    for(JsonNode list:response.path("list")){ 
                        row=tbKamar.getRowCount();
                         for(i=0;i<row;i++){
                            try{     
                                namafaskes= list.path("nama").asText();
                            } catch(Exception e){
                                System.out.println("Notifikasi : "+e);
                            }
                              tbKamar.setValueAt(namafaskes,i,10);
                          }
                    }
                }
            }else {
                System.out.println("Notif Faskes 1 : "+nameNode.path("message").asText());              
            }
            
            // Request data untuk referensi PPK tingkat 2
            URL = link + "/referensi/ppk/2/" + faskes;
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));

                if (response.path("list").isArray()) {
                    for (JsonNode list : response.path("list")) {
                        row=tbKamar.getRowCount();
                         for(i=0;i<row;i++){
                            try{     
                                namafaskes= list.path("nama").asText();
                            } catch(Exception e){
                                System.out.println("Notifikasi : "+e);
                            }
                              tbKamar.setValueAt(namafaskes,i,10);
                          }
                    }
                }
            } else {
                System.out.println("Notif Faskes 2: " + nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }    
    
    public void tampilfaskes2(String faskes) {
        
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIAPOTEKBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIAPOTEKBPJS());
	    requestEntity = new HttpEntity(headers);
            URL = link+"/referensi/ppk/2/"+faskes;	
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if(nameNode.path("message").asText().equals("Sukses")){ 
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                //response = root.path("response");
                if(response.path("list").isArray()){
           
                    for(JsonNode list:response.path("list")){
                        row=tbKamar.getRowCount();
                         for(i=0;i<row;i++){
                            try{     
                                namafaskes= list.path("nama").asText();
                            } catch(Exception e){
                                System.out.println("Notifikasi : "+e);
                            }
                              tbKamar.setValueAt(namafaskes,i,10);
                          }
                    }
                }
            }else {
                System.out.println("Notif Faskes 2 : "+nameNode.path("message").asText());              
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
        
        
    }
    
    public void setNoRm(String nokartu, Date tgl1, Date tgl2) {        
        Tcari.setText(nokartu);
        TanggalAwal.setDate(tgl1);
        TanggalAkhir.setDate(tgl2);
    }
    
}

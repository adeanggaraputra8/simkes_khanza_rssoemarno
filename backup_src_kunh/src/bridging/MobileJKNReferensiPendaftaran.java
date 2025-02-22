/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJnsPerawatanRalan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */

package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class MobileJKNReferensiPendaftaran extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;    
    private int i=0;
    private  ApiMobileJKN api=new ApiMobileJKN();
    private  HttpHeaders headers;
    private  HttpEntity requestEntity;
    private  ObjectMapper mapper= new ObjectMapper();
    private  JsonNode root;
    private  JsonNode nameNode;
    private  JsonNode response;
    private  Calendar cal = Calendar.getInstance();
    private  int day = cal.get(Calendar.DAY_OF_WEEK);
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  Date parsedDate;
    private  String requestJson,URL="",kodeppk=Sequel.cariIsi("select kode_ppk from setting"),utc="",link="";
    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public MobileJKNReferensiPendaftaran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.RM","Nama Pasien","No.HP","No.Kartu","NIK","Tanggal","Poliklinik","Dokter","Jam Praktek","Jenis Kunjungan","Nomor Referensi","Status","Validasi Checkin","No.Booking"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(110);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(83);
            }else if(i==4){
                column.setPreferredWidth(90);
            }else if(i==5){
                column.setPreferredWidth(103);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(140);
            }else if(i==8){
                column.setPreferredWidth(160);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(110);
            }else if(i==11){
                column.setPreferredWidth(125);
            }else if(i==12){
                column.setPreferredWidth(50);
            }else if(i==13){
                column.setPreferredWidth(115);
            }else if(i==14){
                column.setPreferredWidth(150);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
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
            });
        }  
        
        try {
            link=koneksiDB.URLAPIMOBILEJKN();
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

        DlgBatal = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelBiasa4 = new widget.PanelBiasa();
        jLabel37 = new widget.Label();
        BtnKeluar4 = new widget.Button();
        kdbooking = new widget.TextBox();
        keterangan = new widget.TextBox();
        jLabel24 = new widget.Label();
        BtnBatal = new widget.Button();
        Popup1 = new javax.swing.JPopupMenu();
        ppBatal = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnCheckin = new widget.Button();
        BtnBelum = new widget.Button();
        BtnKeluar1 = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        btnsimpan = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        DlgBatal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgBatal.setName("DlgBatal"); // NOI18N
        DlgBatal.setUndecorated(true);
        DlgBatal.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Pembatalan MJKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa4.setName("panelBiasa4"); // NOI18N
        panelBiasa4.setLayout(null);

        jLabel37.setText("Kode Booking :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa4.add(jLabel37);
        jLabel37.setBounds(10, 10, 140, 23);

        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnKeluar4);
        BtnKeluar4.setBounds(300, 80, 100, 30);

        kdbooking.setEditable(false);
        kdbooking.setHighlighter(null);
        kdbooking.setName("kdbooking"); // NOI18N
        panelBiasa4.add(kdbooking);
        kdbooking.setBounds(150, 10, 220, 23);

        keterangan.setName("keterangan"); // NOI18N
        keterangan.setPreferredSize(new java.awt.Dimension(300, 23));
        panelBiasa4.add(keterangan);
        keterangan.setBounds(150, 40, 340, 23);

        jLabel24.setText("Alasan/Keterangan :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(60, 23));
        panelBiasa4.add(jLabel24);
        jLabel24.setBounds(7, 40, 140, 23);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnBatal.setMnemonic('H');
        BtnBatal.setText("Batal");
        BtnBatal.setToolTipText("Alt+H");
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
        panelBiasa4.add(BtnBatal);
        BtnBatal.setBounds(120, 80, 100, 30);

        internalFrame5.add(panelBiasa4, java.awt.BorderLayout.CENTER);

        DlgBatal.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        Popup1.setName("Popup1"); // NOI18N

        ppBatal.setBackground(new java.awt.Color(255, 255, 254));
        ppBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBatal.setForeground(new java.awt.Color(50, 50, 50));
        ppBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBatal.setText("Pembatalan MJKN");
        ppBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBatal.setName("ppBatal"); // NOI18N
        ppBatal.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBatalBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppBatal);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Referensi Pendaftaran Mobile JKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setComponentPopupMenu(Popup1);
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnCheckin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnCheckin.setMnemonic('S');
        BtnCheckin.setText("Checkin");
        BtnCheckin.setToolTipText("Alt+S");
        BtnCheckin.setName("BtnCheckin"); // NOI18N
        BtnCheckin.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCheckinActionPerformed(evt);
            }
        });
        BtnCheckin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCheckinKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnCheckin);

        BtnBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBelum.setMnemonic('B');
        BtnBelum.setText("Belum");
        BtnBelum.setToolTipText("Alt+B");
        BtnBelum.setName("BtnBelum"); // NOI18N
        BtnBelum.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBelumActionPerformed(evt);
            }
        });
        BtnBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBelumKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBelum);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar1);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass10.add(BtnAll);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        btnsimpan.setMnemonic('S');
        btnsimpan.setText("Add Antrian");
        btnsimpan.setToolTipText("Alt+S");
        btnsimpan.setName("btnsimpan"); // NOI18N
        btnsimpan.setPreferredSize(new java.awt.Dimension(120, 30));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        btnsimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsimpanKeyPressed(evt);
            }
        });
        panelGlass10.add(btnsimpan);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass10.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass10.add(BtnKeluar);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReportqry("rptReferensiPendaftaranMobileJKN.jasper","report","::[ Data Referensi Pendaftaran Mobile JKN ]::",
                    "SELECT referensi_mobilejkn_bpjs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,"+
                    "referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,"+
                    "referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi "+
                    "FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat "+
                    "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli "+
                    "INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter "+
                    "WHERE referensi_mobilejkn_bpjs.tanggalperiksa BETWEEN '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+(TCari.getText().equals("")?"":
                    "and (referensi_mobilejkn_bpjs.no_rawat LIKE '%"+TCari.getText()+"%' OR reg_periksa.no_rkm_medis LIKE '%"+TCari.getText()+"%' OR pasien.nm_pasien LIKE '%"+TCari.getText()+"%' OR "+
                    "referensi_mobilejkn_bpjs.nohp LIKE '%"+TCari.getText()+"%' OR referensi_mobilejkn_bpjs.nomorkartu LIKE '%"+TCari.getText()+"%' OR referensi_mobilejkn_bpjs.nik LIKE '%"+TCari.getText()+"%' OR "+
                    "poliklinik.nm_poli LIKE '%"+TCari.getText()+"%' OR dokter.nm_dokter LIKE '%"+TCari.getText()+"%' OR referensi_mobilejkn_bpjs.jeniskunjungan LIKE '%"+TCari.getText()+"%' OR "+
                    "referensi_mobilejkn_bpjs.nomorreferensi LIKE '%"+TCari.getText()+"%' OR referensi_mobilejkn_bpjs.status LIKE '%"+TCari.getText()+"%') ")+
                    "order by referensi_mobilejkn_bpjs.tanggalperiksa",param);
        }
        this.setCursor(Cursor.getDefaultCursor());  
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed

        for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
               Sequel.menyimpan("antrian_bantu","'"+tbJnsPerawatan.getValueAt(i,0).toString()+"','','"+tbJnsPerawatan.getValueAt(i,6).toString()+" "+"16:00:00','Belum Valid','"+tbJnsPerawatan.getValueAt(i,6).toString()+" "+"16:28:00','0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00','' ");
            
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnsimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsimpanKeyPressed

    }//GEN-LAST:event_btnsimpanKeyPressed

    private void BtnCheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCheckinActionPerformed
        if(Sequel.mengedittf("referensi_mobilejkn_bpjs","nobooking=?","status='Checkin',validasi=now()",1,new String[]{
            tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString()
        })==true){
            Sequel.meghapus("referensi_mobilejkn_bpjs_batal","nobooking",tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString());
            tampil();
        }
    }//GEN-LAST:event_BtnCheckinActionPerformed

    private void BtnCheckinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCheckinKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCheckinActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnBatal);
        }
    }//GEN-LAST:event_BtnCheckinKeyPressed

    private void BtnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBelumActionPerformed
        if(Sequel.mengedittf("referensi_mobilejkn_bpjs","nobooking=?","status='Belum',validasi='0000-00-00 00:00:00'",1,new String[]{
            tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString()
        })==true){
            Sequel.meghapus("referensi_mobilejkn_bpjs_batal","nobooking",tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString());
            tampil();
        }
    }//GEN-LAST:event_BtnBelumActionPerformed

    private void BtnBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBelumKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBelumActionPerformed(null);
        }else{Valid.pindah(evt, BtnCheckin, BtnBatal);}
    }//GEN-LAST:event_BtnBelumKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
               try {     
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                    utc=String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("x-timestamp",utc);
                    headers.add("x-signature",api.getHmac(utc));
                    headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                    requestJson ="{" +
                                     "\"kodebooking\": \""+kdbooking.getText()+"\"," +
                                     "\"keterangan\": \""+keterangan.getText()+"\"" +
                                  "}";
                    requestEntity = new HttpEntity(requestJson,headers);
                    URL = link+"/antrean/batal";	
                    System.out.println("URL : "+URL);
                    //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    nameNode = root.path("metadata");
                    if(nameNode.path("code").asText().equals("200")){
                         if(tbJnsPerawatan.getSelectedRow()!= -1){
                          if(Sequel.mengedittf("referensi_mobilejkn_bpjs","nobooking=?","status='Batal',validasi=now()",1,new String[]{
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString()
                        })==true){
                            Sequel.menyimpan2("referensi_mobilejkn_bpjs_batal","?,?,?,now(),?,?,?",6,new String[]{
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),1).toString(),tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),0).toString(),
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),11).toString(),keterangan.getText(),"Belum",tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString()
                            });
                        }
                    }tampil();
                     DlgBatal.dispose(); 
              } 
                }catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : "+ex);
                }            
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCheckin, BtnPrint);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,TCari);}
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        DlgBatal.dispose();
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void ppBatalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBatalBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
             if(tbJnsPerawatan.getSelectedRow()!= -1){
                DlgBatal.setSize(507,144);
                DlgBatal.setLocationRelativeTo(internalFrame1);
                DlgBatal.setVisible(true);
                kdbooking.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(),14).toString());
             }else{
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data pada tabel...!!!!");
            }  
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppBatalBtnPrintActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MobileJKNReferensiPendaftaran dialog = new MobileJKNReferensiPendaftaran(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnBelum;
    private widget.Button BtnCari;
    private widget.Button BtnCheckin;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar4;
    private widget.Button BtnPrint;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JDialog DlgBatal;
    private widget.Label LCount;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Button btnsimpan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel24;
    private widget.Label jLabel37;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdbooking;
    private widget.TextBox keterangan;
    private widget.PanelBiasa panelBiasa4;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBatal;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration//GEN-END:variables

       private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                   "SELECT referensi_mobilejkn_bpjs.no_rawat,referensi_mobilejkn_bpjs.norm,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,"+
                   "referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,referensi_mobilejkn_bpjs.kodepoli,referensi_mobilejkn_bpjs.kodedokter,referensi_mobilejkn_bpjs.jampraktek,"+
                   "referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi,"+
                   "referensi_mobilejkn_bpjs.nobooking FROM referensi_mobilejkn_bpjs INNER JOIN pasien ON referensi_mobilejkn_bpjs.norm=pasien.no_rkm_medis "+
                   "WHERE referensi_mobilejkn_bpjs.tanggalperiksa BETWEEN ? AND ? "+(TCari.getText().equals("")?"":
                   "and (referensi_mobilejkn_bpjs.no_rawat LIKE ? OR referensi_mobilejkn_bpjs.norm LIKE ? OR pasien.nm_pasien LIKE ? OR "+
                   "referensi_mobilejkn_bpjs.nohp LIKE ? OR referensi_mobilejkn_bpjs.nomorkartu LIKE ? OR referensi_mobilejkn_bpjs.nik LIKE ? OR "+
                   "referensi_mobilejkn_bpjs.jeniskunjungan LIKE ? OR referensi_mobilejkn_bpjs.nomorreferensi LIKE ? OR referensi_mobilejkn_bpjs.status LIKE ? OR referensi_mobilejkn_bpjs.kodepoli LIKE ?) ")+
                   "order by referensi_mobilejkn_bpjs.tanggalperiksa");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                    ps.setString(10,"%"+TCari.getText()+"%");
                    ps.setString(11,"%"+TCari.getText()+"%");
                    ps.setString(12,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),rs.getString("norm"),rs.getString("nm_pasien"),
                        rs.getString("nohp"),rs.getString("nomorkartu"),rs.getString("nik"),
                        rs.getString("tanggalperiksa"),Sequel.cariIsi("select maping_poli_bpjs.nm_poli_bpjs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_bpjs=?",rs.getString("kodepoli")),
                        Sequel.cariIsi("select maping_dokter_dpjpvclaim.nm_dokter_bpjs from maping_dokter_dpjpvclaim where maping_dokter_dpjpvclaim.kd_dokter_bpjs=?",rs.getString("kodedokter")),
                        rs.getString("jampraktek"),rs.getString("jeniskunjungan"),rs.getString("nomorreferensi"),
                        rs.getString("status"),rs.getString("validasi"),rs.getString("nobooking")
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
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void setNoRm (String norawat) {
        TCari.setText(norawat);
        tampil();
    }
}

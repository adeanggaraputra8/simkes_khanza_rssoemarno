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

import inventory.*;
import bridging.ApiPcare;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariPoli;
import widget.Button;

/**
 *
 * @author dosen
 */
public final class DlgCariObatMaping extends javax.swing.JDialog {
    private final DefaultTableModel tabModeobat;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement psobat,pscarikapasitas,psstok,ps2,psbatch,psrekening;
    private ResultSet rsobat,carikapasitas,rsstok,rs2,rsbatch,rsrekening;
    private double h_belicari=0, hargacari=0, sisacari=0,x=0,y=0,embalase=Sequel.cariIsiAngka("select set_embalase.embalase_per_obat from set_embalase"),
                   tuslah=Sequel.cariIsiAngka("select set_embalase.tuslah_per_obat from set_embalase"),kenaikan=0,stokbarang=0,ttl=0,ppnobat=0,ttlhpp,ttljual;
    private int i=0,z=0,row=0,row2,r;
    private Jurnal jur=new Jurnal();
    private boolean[] pilih; 
    private double[] jumlah,harga,eb,ts,stok,beli,kapasitas,kandungan;
    private String[] kodebarang,namabarang,kodesatuan,letakbarang,namajenis,aturan,industri,kategori,golongan,no,nobatch,nofaktur,kadaluarsa;
    private String signa1="1",signa2="1",nokunjungan="",kdObatSK="",requestJson="",URL="",otorisasi,sql="",aktifpcare="no",no_batchcari="", tgl_kadaluarsacari="", no_fakturcari="", aktifkanbatch="no",kodedokter="",namadokter="",noresep="",bangsal="",bangsaldefault=Sequel.cariIsi("select set_lokasi.kd_bangsal from set_lokasi limit 1"),tampilkan_ppnobat_ralan="",
                   Suspen_Piutang_Obat_Ralan="",Obat_Ralan="",HPP_Obat_Rawat_Jalan="",Persediaan_Obat_Rawat_Jalan="",hppfarmasi="",VALIDASIULANGBERIOBAT="",DEPOAKTIFOBAT="",utc="";
    private DlgCariBangsal caribangsal=new DlgCariBangsal(null,false);
    public DlgCariAturanPakai aturanpakai=new DlgCariAturanPakai(null,false);
    private DlgCariMetodeRacik metoderacik=new DlgCariMetodeRacik(null,false);
    private WarnaTable2 warna=new WarnaTable2();
    private WarnaTable2 warna2=new WarnaTable2();
    private WarnaTable2 warna3=new WarnaTable2();
    private riwayatobat Trackobat=new riwayatobat();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApiPcare api=new ApiPcare();
    private String[] arrSplit;
    private boolean sukses=true;
    private DlgCariPoli caripoli=new DlgCariPoli(null,false);
    
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public DlgCariObatMaping(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

        tabModeobat=new DefaultTableModel(null,new Object[]{
                "K","Kode Barang","Nama Barang","Satuan","Jenis","Kandungan","I.F.",
                "Kategori","Golongan"
            }){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if(colIndex==0) {
                    a=true;
                }
                return a;
             }
            
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class
             };
             
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbObat.setModel(tabModeobat);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 9; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(250);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(100);
            }                 
        }
        warna.kolom=1;
        tbObat.setDefaultRenderer(Object.class,warna);
        
     
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        BtnCariActionPerformed(null);
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        BtnCariActionPerformed(null);
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        BtnCariActionPerformed(null);
                    }
                }
            });
        }
        
       
        
        caripoli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(caripoli.getTable().getSelectedRow()!= -1){                   
                    KdPoli.setText(caripoli.getTable().getValueAt(caripoli.getTable().getSelectedRow(),0).toString());
                    NmPoli.setText(caripoli.getTable().getValueAt(caripoli.getTable().getSelectedRow(),1).toString());
                    KdPoli.requestFocus();
                }                  
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        caripoli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    caripoli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        

    }    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        TNoRw = new widget.TextBox();
        Tanggal = new widget.TextBox();
        Jam = new widget.TextBox();
        KdPj = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnSimpan = new widget.Button();
        label13 = new widget.Label();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel19 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        btnPoli = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Jumlah");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N

        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N

        Jam.setHighlighter(null);
        Jam.setName("Jam"); // NOI18N

        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setToolTipText("Alt+C");
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelisi3.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("Alt+2");
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
        panelisi3.add(BtnAll);

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpan);

        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi3.add(label13);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('5');
        BtnKeluar.setToolTipText("Alt+5");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 60));
        FormInput.setLayout(null);

        jLabel19.setText("Poliklinik :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(20, 20, 70, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(90, 20, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(160, 20, 190, 23);

        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('3');
        btnPoli.setToolTipText("ALt+3");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        FormInput.add(btnPoli);
        btnPoli.setBounds(360, 20, 28, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbObatPropertyChange(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        TabRawat.addTab("Umum", Scroll);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbObat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed

            tampilobat();
              
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
        BtnCariActionPerformed(evt);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
//        if(tbObat.getRowCount()!=0){
//            try {
//        
//            } catch (java.lang.NullPointerException e) {
//            }
//            
//            if(evt.getClickCount()==2){
//                if(akses.getform().equals("DlgPemberianObat")){
//                    dispose();
//                }
//            }
//        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
//        if(tbObat.getRowCount()!=0){
//            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//                try {
//
//                    i=tbObat.getSelectedColumn();
//                    if(i==8){
//                        try {
//                            if(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0.0")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0,0")) {
//                                tbObat.setValueAt(embalase,tbObat.getSelectedRow(),8);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0,tbObat.getSelectedRow(),8);
//                        }
//                    }else if(i==9){
//                        try {
//                            if(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0.0")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0,0")) {
//                                tbObat.setValueAt(tuslah,tbObat.getSelectedRow(),9);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0,tbObat.getSelectedRow(),9);
//                        }
//                            
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    }else if((i==10)||(i==3)){
//                  
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    }else if(i==11){
//                        TCari.setText("");
//                        TCari.requestFocus();
//                    }
//                } catch (java.lang.NullPointerException e) {
//                }
//            }else if((evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
//                try {
//              
//                } catch (java.lang.NullPointerException e) {
//                }
//            }else if(evt.getKeyCode()==KeyEvent.VK_DELETE){
//                i=tbObat.getSelectedColumn();
//                if((i==1)||(i==11)||(i==8)||(i==9)){
//                    if(tbObat.getSelectedRow()!= -1){
//                        tbObat.setValueAt("",tbObat.getSelectedRow(),i);
//                    }
//                }   
//            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
//                i=tbObat.getSelectedColumn();
//                if(i!=11){
//                    TCari.requestFocus();
//                }                
//            }else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
//                i=tbObat.getSelectedColumn();
//                if(i==2){
//                    try {
//                    
//                        
//                        try {
//                            if(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0.0")||tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().equals("0,0")) {
//                                tbObat.setValueAt(embalase,tbObat.getSelectedRow(),8);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0,tbObat.getSelectedRow(),8);
//                        }
//
//                        try {
//                            if(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0.0")||tbObat.getValueAt(tbObat.getSelectedRow(),9).toString().equals("0,0")) {
//                                tbObat.setValueAt(tuslah,tbObat.getSelectedRow(),9);
//                            }
//                        } catch (Exception e) {
//                            tbObat.setValueAt(0,tbObat.getSelectedRow(),9);
//                        }  
//                    } catch (Exception e) {
//                        tbObat.setValueAt(0,tbObat.getSelectedRow(),10);
//                    }
//              
//                }else if(i==11){
//                    akses.setform("DlgCariObat");
//                    aturanpakai.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
//                    aturanpakai.setLocationRelativeTo(internalFrame1);
//                    aturanpakai.setVisible(true);
//                }
//            }   
//        }
}//GEN-LAST:event_tbObatKeyPressed

    private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kd2KeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed

//            for(i=0;i<tbObat.getRowCount();i++){ 
//                if(tbObat.getValueAt(i,0).toString().equals("true")){
//                    getDataobat(i);
//                } 
//            }   

        if(KdPoli.getText().equals("")){
            Valid.textKosong(KdPoli,"Poliklinik");
            btnPoli.requestFocus();
        }else{
            int reply = JOptionPane.showConfirmDialog(rootPane,"Eeiiiiiits, udah bener belum data yang mau disimpan..??","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {  
                    //Sequel.AutoComitFalse();
                    //sukses=true;
                    for(i=0;i<tbObat.getRowCount();i++){                        
                            if(tbObat.getValueAt(i,0).toString().equals("true")){                             
                                try {
                                        if(Sequel.menyimpantf2("maping_obat_poli","?,?,?","Mapping Obat",3,new String[]{
                                            tbObat.getValueAt(i,1).toString(),KdPoli.getText(),NmPoli.getText()
                                        })==true){
                                          emptTeksobat();
                                        }else{
                                            sukses=false;
                                        }  
                                    
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : "+e);
                                } 
                            }                                        
                    }  

                } catch (Exception ex) {
                    System.out.println(ex);                
                }
            }                
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
 
//        for(i=0;i<tbObat.getRowCount();i++){ 
//            tbObat.setValueAt("",i,1);
//            tbObat.setValueAt(0,i,10);
//            tbObat.setValueAt(0,i,9);
//            tbObat.setValueAt(0,i,8);
//        }

}//GEN-LAST:event_ppBersihkanActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
            label13.setPreferredSize(new Dimension(65, 23));        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(KdPoli.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Poliklinik Terlebih Dahulu");
        }else{
            tampilobat();
        }
                    
    }//GEN-LAST:event_formWindowOpened

    private void tbObatPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbObatPropertyChange

    }//GEN-LAST:event_tbObatPropertyChange

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked

            label13.setPreferredSize(new Dimension(65, 23));
        
    }//GEN-LAST:event_TabRawatMouseClicked

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        caripoli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        caripoli.setLocationRelativeTo(internalFrame1);
        caripoli.setVisible(true);
    }//GEN-LAST:event_btnPoliActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariObatMaping dialog = new DlgCariObatMaping(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jam;
    private widget.TextBox Kd2;
    private widget.TextBox KdPj;
    private widget.TextBox KdPoli;
    private widget.TextBox NmPoli;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRw;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tanggal;
    private widget.Button btnPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label label13;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private javax.swing.JMenuItem ppBersihkan;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampilobat() {        
        z=0;
        for(i=0;i<tbObat.getRowCount();i++){
            if(!tbObat.getValueAt(i,0).toString().equals("")){
                z++;
            }
        }    
        
        pilih=null;
        pilih=new boolean[z]; 
        kodebarang=null;
        kodebarang=new String[z];
        namabarang=null;
        namabarang=new String[z];
        kodesatuan=null;
        kodesatuan=new String[z];
        letakbarang=null;
        letakbarang=new String[z];
        namajenis=null;
        namajenis=new String[z];                   
        aturan=null;
        aturan=new String[z];           
        industri=null;
        industri=new String[z];         
        kategori=null;
        kategori=new String[z];
        golongan=null;
        golongan=new String[z];
        z=0;
        for(i=0;i<tbObat.getRowCount();i++){
            if(!tbObat.getValueAt(i,0).toString().equals("false")){
                 try {
                    pilih[z]=Boolean.parseBoolean(tbObat.getValueAt(i,0).toString()); 
                } catch (Exception e) {
                    pilih[z]=false;
                }  
                //pilih[z]=Boolean.parseBoolean(tbObat.getValueAt(i,0).toString());                 
                kodebarang[z]=tbObat.getValueAt(i,1).toString();
                namabarang[z]=tbObat.getValueAt(i,2).toString();
                kodesatuan[z]=tbObat.getValueAt(i,3).toString();
                letakbarang[z]=tbObat.getValueAt(i,4).toString();
                namajenis[z]=tbObat.getValueAt(i,5).toString();
                industri[z]=tbObat.getValueAt(i,6).toString();
                kategori[z]=tbObat.getValueAt(i,7).toString();
                golongan[z]=tbObat.getValueAt(i,8).toString();
   
                z++;
            }
        }
        
        Valid.tabelKosong(tabModeobat);             
        
        for(i=0;i<z;i++){
            tabModeobat.addRow(new Object[] {
                pilih[i],kodebarang[i],namabarang[i],kodesatuan[i],letakbarang[i],namajenis[i],
                industri[i],kategori[i],golongan[i]
            });
        }
        
        try {

                        sql="select databarang.kode_brng, databarang.nama_brng,jenis.nama, databarang.kode_sat,"+
                            " databarang.letak_barang,industrifarmasi.nama_industri,kategori_barang.nama as kategori,golongan_barang.nama as golongan "+
                            " from databarang inner join jenis on databarang.kdjns=jenis.kdjns "+
                            " inner join industrifarmasi on industrifarmasi.kode_industri=databarang.kode_industri "+
                            " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "+
                            " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode ";
                    
                    psobat=koneksi.prepareStatement(
                        sql+" where databarang.kode_brng not IN (SELECT kode_brng  FROM maping_obat_poli mop WHERE mop.kd_poli=? ) and databarang.kode_brng like ? or "+
                        " databarang.status='1' and databarang.kode_brng not IN (SELECT kode_brng  FROM maping_obat_poli mop WHERE mop.kd_poli=? ) and databarang.nama_brng like ? or "+
                        " databarang.status='1' and databarang.kode_brng not IN (SELECT kode_brng  FROM maping_obat_poli mop WHERE mop.kd_poli=? ) and kategori_barang.nama like ? or "+
                        " databarang.status='1' and databarang.kode_brng not IN (SELECT kode_brng  FROM maping_obat_poli mop WHERE mop.kd_poli=? ) and golongan_barang.nama like ? or "+
                        " databarang.status='1' and databarang.kode_brng not IN (SELECT kode_brng  FROM maping_obat_poli mop WHERE mop.kd_poli=? ) and jenis.nama like ? order by databarang.nama_brng");
                    try{
                            psobat.setString(1,KdPoli.getText());
                            psobat.setString(2,"%"+TCari.getText().trim()+"%");
                            psobat.setString(3,KdPoli.getText());
                            psobat.setString(4,"%"+TCari.getText().trim()+"%");
                            psobat.setString(5,KdPoli.getText());
                            psobat.setString(6,"%"+TCari.getText().trim()+"%");
                            psobat.setString(7,KdPoli.getText());
                            psobat.setString(8,"%"+TCari.getText().trim()+"%");
                            psobat.setString(9,KdPoli.getText());
                            psobat.setString(10,"%"+TCari.getText().trim()+"%");
                        rsobat=psobat.executeQuery();
                            while(rsobat.next()){
                                tabModeobat.addRow(new Object[] {false,rsobat.getString("kode_brng"),rsobat.getString("nama_brng"),
                                   rsobat.getString("kode_sat"),rsobat.getString("letak_barang"),
                                   rsobat.getString("nama"),rsobat.getString("nama_industri"),
                                   rsobat.getString("kategori"),rsobat.getString("golongan")
                                });     
                            }
                          
                    }catch(Exception e){
                        System.out.println("Notifikasi : "+e);
                    }finally{
                        if(rsobat != null){
                            rsobat.close();
                        }
                        if(psobat != null){
                            psobat.close();
                        }
                    }
                                                                 
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }            
    }
    
    


    public void emptTeksobat() {
        Kd2.setText(""); 
        TCari.setText("");
        TCari.requestFocus();

    }

    public JTextField getTextField(){
        return Kd2;
    }

    public JTable getTable(){
        return tbObat;
    }
    
    public Button getButton(){
        return BtnSimpan;
    }
    
    
    
    public void setNoRm(String norwt,String norm,String nama,String tanggal, String jam) {        
        aktifpcare="no";
        TNoRw.setText(norwt);
        noresep="";
        Tanggal.setText(tanggal);
        Jam.setText(jam);  
        KdPj.setText(Sequel.cariIsi("select reg_periksa.kd_pj from reg_periksa where reg_periksa.no_rawat=?",norwt));
        kenaikan=Sequel.cariIsiAngka("select (set_harga_obat_ralan.hargajual/100) from set_harga_obat_ralan where set_harga_obat_ralan.kd_pj=?",KdPj.getText());
        TCari.requestFocus();
    }
  
    
}

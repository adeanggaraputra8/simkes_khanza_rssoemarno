/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgRujuk.java
 *
 * Created on 31 Mei 10, 20:19:56
 */

package laporan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author perpustakaan
 */
public final class DlgDataHAIs extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabmode1;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private Date date = new Date();
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DlgDataHAIs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "Tanggal","No.Rawat","No.R.M.","Nama Pasien","ETT","CVL",
                "IVL","UC","VAP","IAD","PLEB","ISK","ILO","HAP","Tinea",
                "Scabies","Deku","Sputum","Darah","Urine","Antibiotik","Kamar","Kode kamar","Diagnosa"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 23; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(75);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(35);
            }else if(i==5){
                column.setPreferredWidth(35);
            }else if(i==6){
                column.setPreferredWidth(35);
            }else if(i==7){
                column.setPreferredWidth(35);
            }else if(i==8){
                column.setPreferredWidth(35);
            }else if(i==9){
                column.setPreferredWidth(35);
            }else if(i==10){
                column.setPreferredWidth(35);
            }else if(i==11){
                column.setPreferredWidth(35);
            }else if(i==12){
                column.setPreferredWidth(35);
            }else if(i==13){
                column.setPreferredWidth(35);
            }else if(i==14){
                column.setPreferredWidth(35);
            }else if(i==15){
                column.setPreferredWidth(45);
            }else if(i==16){
                column.setPreferredWidth(50);
            }else if(i==17){
                column.setPreferredWidth(130);
            }else if(i==18){
                column.setPreferredWidth(130);
            }else if(i==19){
                column.setPreferredWidth(130);
            }else if(i==20){
                column.setPreferredWidth(130);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==120){
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabmode1=new DefaultTableModel(null,new Object[]{
                "Kode Bed","Nama Kamar"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbcaribangsal.setModel(tabmode1);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbcaribangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbcaribangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbcaribangsal.getColumnModel().getColumn(i);
            if(i==0){
             column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(120);
            }
        }
    
        tbcaribangsal.setDefaultRenderer(Object.class, new WarnaTable());

        ETT.setDocument(new batasInput((byte)2).getOnlyAngka(ETT));
        CVL.setDocument(new batasInput((byte)2).getOnlyAngka(CVL));
        IVL.setDocument(new batasInput((byte)2).getOnlyAngka(IVL));
        UC.setDocument(new batasInput((byte)2).getOnlyAngka(UC));
        VAP.setDocument(new batasInput((byte)2).getOnlyAngka(VAP));
        IAD.setDocument(new batasInput((byte)2).getOnlyAngka(IAD));
        PLEB.setDocument(new batasInput((byte)2).getOnlyAngka(PLEB));
        ISK.setDocument(new batasInput((byte)2).getOnlyAngka(ISK));
        ILO.setDocument(new batasInput((byte)2).getOnlyAngka(ILO));
        Sputum.setDocument(new batasInput((int)200).getKata(Sputum));
        Darah.setDocument(new batasInput((int)200).getKata(Darah));
        Urine.setDocument(new batasInput((int)200).getKata(Urine));
        Antibiotik.setDocument(new batasInput((int)200).getKata(Antibiotik));
        Diagnosa.setDocument(new batasInput((int)200).getKata(Diagnosa));
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
    
        
        ChkInput.setSelected(false);
        isForm();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowCariBangsal = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbcaribangsal = new widget.Table();
        BtnAll1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        CVL = new widget.TextBox();
        jLabel8 = new widget.Label();
        ETT = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        jLabel12 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel13 = new widget.Label();
        Deku = new widget.ComboBox();
        jLabel5 = new widget.Label();
        IVL = new widget.TextBox();
        jLabel9 = new widget.Label();
        UC = new widget.TextBox();
        jLabel10 = new widget.Label();
        VAP = new widget.TextBox();
        jLabel11 = new widget.Label();
        IAD = new widget.TextBox();
        jLabel14 = new widget.Label();
        PLEB = new widget.TextBox();
        jLabel15 = new widget.Label();
        ISK = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Sputum = new widget.TextBox();
        Urine = new widget.TextBox();
        jLabel20 = new widget.Label();
        Antibiotik = new widget.TextBox();
        jLabel22 = new widget.Label();
        Darah = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        ILO = new widget.TextBox();
        HAP = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        Tania = new widget.TextBox();
        jLabel27 = new widget.Label();
        Scabies = new widget.TextBox();
        Kamar = new widget.TextBox();
        Diagnosa = new widget.TextBox();
        jLabel28 = new widget.Label();
        btncaribangsal = new widget.Button();
        ChkInput = new widget.CekBox();

        WindowCariBangsal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariBangsal.setName("WindowCariBangsal"); // NOI18N
        WindowCariBangsal.setUndecorated(true);
        WindowCariBangsal.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pilih Bed dan Kamar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(null);

        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(110, 30, 100, 30);

        scrollPane1.setName("scrollPane1"); // NOI18N

        tbcaribangsal.setAutoCreateRowSorter(true);
        tbcaribangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbcaribangsal.setMaximumSize(new java.awt.Dimension(450, 400));
        tbcaribangsal.setName("tbcaribangsal"); // NOI18N
        tbcaribangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcaribangsalMouseClicked(evt);
            }
        });
        tbcaribangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbcaribangsalKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbcaribangsal);

        internalFrame5.add(scrollPane1);
        scrollPane1.setBounds(10, 90, 230, 130);

        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Cari Data");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame5.add(BtnAll1);
        BtnAll1.setBounds(20, 30, 100, 30);

        WindowCariBangsal.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data HAIs ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-07-2019" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-07-2019" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 195));
        FormInput.setLayout(null);

        jLabel3.setText("CVL :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(447, 45, 40, 23);

        CVL.setHighlighter(null);
        CVL.setName("CVL"); // NOI18N
        CVL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CVLKeyPressed(evt);
            }
        });
        FormInput.add(CVL);
        CVL.setBounds(490, 45, 40, 23);

        jLabel8.setText("Infeksi RS :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel8);
        jLabel8.setBounds(230, 75, 130, 23);

        ETT.setHighlighter(null);
        ETT.setName("ETT"); // NOI18N
        ETT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ETTKeyPressed(evt);
            }
        });
        FormInput.add(ETT);
        ETT.setBounds(400, 45, 40, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 71, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(75, 10, 130, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 309, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-07-2019" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(75, 40, 100, 23);

        jLabel12.setText("ETT :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(362, 45, 35, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        jLabel13.setText("Deku :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 70, 71, 23);

        Deku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IYA", "TIDAK" }));
        Deku.setName("Deku"); // NOI18N
        Deku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DekuKeyPressed(evt);
            }
        });
        FormInput.add(Deku);
        Deku.setBounds(75, 70, 100, 23);

        jLabel5.setText("IVL :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(537, 45, 40, 23);

        IVL.setHighlighter(null);
        IVL.setName("IVL"); // NOI18N
        IVL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IVLKeyPressed(evt);
            }
        });
        FormInput.add(IVL);
        IVL.setBounds(580, 45, 40, 23);

        jLabel9.setText("UC :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(627, 45, 50, 23);

        UC.setHighlighter(null);
        UC.setName("UC"); // NOI18N
        UC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UCKeyPressed(evt);
            }
        });
        FormInput.add(UC);
        UC.setBounds(680, 45, 40, 23);

        jLabel10.setText("VAP :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(362, 80, 35, 23);

        VAP.setHighlighter(null);
        VAP.setName("VAP"); // NOI18N
        VAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VAPKeyPressed(evt);
            }
        });
        FormInput.add(VAP);
        VAP.setBounds(400, 80, 40, 23);

        jLabel11.setText("IAD :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(447, 80, 40, 23);

        IAD.setHighlighter(null);
        IAD.setName("IAD"); // NOI18N
        IAD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IADKeyPressed(evt);
            }
        });
        FormInput.add(IAD);
        IAD.setBounds(490, 80, 40, 23);

        jLabel14.setText("PLEB :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(537, 80, 40, 23);

        PLEB.setHighlighter(null);
        PLEB.setName("PLEB"); // NOI18N
        PLEB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PLEBKeyPressed(evt);
            }
        });
        FormInput.add(PLEB);
        PLEB.setBounds(580, 80, 40, 23);

        jLabel15.setText("ISK :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(627, 80, 50, 23);

        ISK.setHighlighter(null);
        ISK.setName("ISK"); // NOI18N
        ISK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ISKKeyPressed(evt);
            }
        });
        FormInput.add(ISK);
        ISK.setBounds(680, 80, 40, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 71, 23);

        jLabel17.setText("Hari Pemasangan Alat :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel17);
        jLabel17.setBounds(230, 40, 130, 23);

        jLabel18.setText("Sputum :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 100, 71, 23);

        Sputum.setHighlighter(null);
        Sputum.setName("Sputum"); // NOI18N
        Sputum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SputumKeyPressed(evt);
            }
        });
        FormInput.add(Sputum);
        Sputum.setBounds(75, 100, 240, 23);

        Urine.setHighlighter(null);
        Urine.setName("Urine"); // NOI18N
        Urine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UrineKeyPressed(evt);
            }
        });
        FormInput.add(Urine);
        Urine.setBounds(75, 160, 240, 23);

        jLabel20.setText("Urine :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 160, 71, 23);

        Antibiotik.setHighlighter(null);
        Antibiotik.setName("Antibiotik"); // NOI18N
        Antibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntibiotikKeyPressed(evt);
            }
        });
        FormInput.add(Antibiotik);
        Antibiotik.setBounds(490, 145, 230, 22);

        jLabel22.setText("Diagnosa :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(415, 168, 71, 23);

        Darah.setHighlighter(null);
        Darah.setName("Darah"); // NOI18N
        Darah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DarahKeyPressed(evt);
            }
        });
        FormInput.add(Darah);
        Darah.setBounds(75, 130, 240, 23);

        jLabel23.setText("Darah :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 130, 71, 23);

        jLabel24.setText("ILO :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(362, 110, 35, 23);

        ILO.setHighlighter(null);
        ILO.setName("ILO"); // NOI18N
        ILO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ILOKeyPressed(evt);
            }
        });
        FormInput.add(ILO);
        ILO.setBounds(400, 110, 40, 23);

        HAP.setHighlighter(null);
        HAP.setName("HAP"); // NOI18N
        HAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HAPKeyPressed(evt);
            }
        });
        FormInput.add(HAP);
        HAP.setBounds(490, 110, 40, 23);

        jLabel25.setText("HAP :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(447, 110, 40, 23);

        jLabel26.setText("Tinea :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(537, 110, 40, 23);

        Tania.setHighlighter(null);
        Tania.setName("Tania"); // NOI18N
        Tania.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaniaKeyPressed(evt);
            }
        });
        FormInput.add(Tania);
        Tania.setBounds(580, 110, 40, 23);

        jLabel27.setText("Scabies :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(627, 110, 50, 23);

        Scabies.setHighlighter(null);
        Scabies.setName("Scabies"); // NOI18N
        Scabies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScabiesKeyPressed(evt);
            }
        });
        FormInput.add(Scabies);
        Scabies.setBounds(680, 110, 40, 23);

        Kamar.setEditable(false);
        Kamar.setHighlighter(null);
        Kamar.setName("Kamar"); // NOI18N
        Kamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KamarKeyPressed(evt);
            }
        });
        FormInput.add(Kamar);
        Kamar.setBounds(620, 10, 100, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(490, 170, 230, 23);

        jLabel28.setText("Antibiotik :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(416, 145, 71, 23);

        btncaribangsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaribangsal.setMnemonic('7');
        btncaribangsal.setText("Cari Kamar");
        btncaribangsal.setToolTipText("ALt+7");
        btncaribangsal.setName("btncaribangsal"); // NOI18N
        btncaribangsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaribangsalActionPerformed(evt);
            }
        });
        FormInput.add(btncaribangsal);
        btncaribangsal.setBounds(720, 10, 110, 26);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CVLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CVLKeyPressed
        Valid.pindah(evt,ETT,IVL);
}//GEN-LAST:event_CVLKeyPressed

    private void ETTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ETTKeyPressed
        Valid.pindah(evt,Tanggal,CVL);
}//GEN-LAST:event_ETTKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{            
            //Valid.pindah(evt,TTmpRujuk,TDiagnosa);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        //Valid.pindah(evt,TDokter,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(ETT.getText().trim().equals("")){
            Valid.textKosong(ETT,"ETT");
        }else if(CVL.getText().trim().equals("")){
            Valid.textKosong(CVL,"CVL");
        }else if(IVL.getText().trim().equals("")){
            Valid.textKosong(IVL,"IVL");
        }else if(UC.getText().trim().equals("")){
            Valid.textKosong(UC,"UC");
        }else if(VAP.getText().trim().equals("")){
            Valid.textKosong(VAP,"VAP");
        }else if(IAD.getText().trim().equals("")){
            Valid.textKosong(IAD,"IAD");
        }else if(PLEB.getText().trim().equals("")){
            Valid.textKosong(PLEB,"PLEB");
        }else if(ISK.getText().trim().equals("")){
            Valid.textKosong(ISK,"ISK");
        }else if(ILO.getText().trim().equals("")){
            Valid.textKosong(ILO,"ILO");
        }else if(HAP.getText().trim().equals("")){
            Valid.textKosong(HAP,"HAP");
        }else if(Tania.getText().trim().equals("")){
            Valid.textKosong(Tania,"Tania");
        }else if(Scabies.getText().trim().equals("")){
            Valid.textKosong(Scabies,"Scabies");
        }else if(Diagnosa.getText().trim().equals("")){
            Valid.textKosong(Diagnosa,"diagnosa");
        }else if(Kamar.getText().trim().equals("")){
            Valid.textKosong(Kamar,"kamar");
        }else{
            if(Sequel.menyimpantf("data_HAIs","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",21,new String[]{
                    Valid.SetTgl(Tanggal.getSelectedItem()+""),TNoRw.getText(),ETT.getText(),CVL.getText(),
                    IVL.getText(),UC.getText(),VAP.getText(),IAD.getText(),PLEB.getText(),ISK.getText(),ILO.getText(),
                    HAP.getText(),Tania.getText(),Scabies.getText(),Deku.getSelectedItem().toString(),
                    Sputum.getText(),Darah.getText(),Urine.getText(),Antibiotik.getText(),Kamar.getText(),Diagnosa.getText()
                })==true){
                    tampil();
                    emptTeks();
            }   
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Antibiotik,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            if(Sequel.queryu2tf("delete from data_HAIs where tanggal=? and no_rawat=?",2,new String[]{
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()
            })==true){
                tampil();
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
            }
        }            
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(ETT.getText().trim().equals("")){
            Valid.textKosong(ETT,"ETT");
        }else if(CVL.getText().trim().equals("")){
            Valid.textKosong(CVL,"CVL");
        }else if(IVL.getText().trim().equals("")){
            Valid.textKosong(IVL,"IVL");
        }else if(UC.getText().trim().equals("")){
            Valid.textKosong(UC,"UC");
        }else if(VAP.getText().trim().equals("")){
            Valid.textKosong(VAP,"VAP");
        }else if(IAD.getText().trim().equals("")){
            Valid.textKosong(IAD,"IAD");
        }else if(PLEB.getText().trim().equals("")){
            Valid.textKosong(PLEB,"PLEB");
        }else if(ISK.getText().trim().equals("")){
            Valid.textKosong(ISK,"ISK");
        }else if(ILO.getText().trim().equals("")){
            Valid.textKosong(ILO,"ILO");
        }else if(HAP.getText().trim().equals("")){
            Valid.textKosong(HAP,"HAP");
        }else if(Tania.getText().trim().equals("")){
            Valid.textKosong(Tania,"Tania");
        }else if(Scabies.getText().trim().equals("")){
            Valid.textKosong(Scabies,"Scabies");
        }else if(Diagnosa.getText().trim().equals("")){
            Valid.textKosong(Diagnosa,"diagnosa");
        }else if(Kamar.getText().trim().equals("")){
            Valid.textKosong(Kamar,"kamar");
        }else{         
            Sequel.mengedit("data_HAIs","tanggal=? and no_rawat=?","tanggal=?,no_rawat=?,ETT=?,CVL=?,IVL=?,UC=?,VAP=?,IAD=?,PLEB=?,ISK=?,ILO=?,DEKU=?,SPUTUM=?,DARAH=?,URINE=?,ANTIBIOTIK=?,HAP=?,Tinea=?,Scabies=?,diagnosa=?",22,new String[]{
                Valid.SetTgl(Tanggal.getSelectedItem()+""),TNoRw.getText(),ETT.getText(),CVL.getText(),
                IVL.getText(),UC.getText(),VAP.getText(),IAD.getText(),PLEB.getText(),ISK.getText(),ILO.getText(),
                Deku.getSelectedItem().toString(),Sputum.getText(),Darah.getText(),Urine.getText(),Antibiotik.getText(),
                HAP.getText(),Tania.getText(),Scabies.getText(),Diagnosa.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()
            });
            if(tabMode.getRowCount()!=0){tampil();}
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("tanggal1",Valid.SetTgl(DTPCari1.getSelectedItem()+""));   
                param.put("tanggal2",Valid.SetTgl(DTPCari2.getSelectedItem()+""));   
                param.put("parameter","%"+TCari.getText().trim()+"%");   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDataHAIs.jrxml",param,"::[ Data HAIs Pasien ]::");
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
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
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TNoRw,ETT);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void DekuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DekuKeyPressed
        Valid.pindah(evt,UC,VAP);
    }//GEN-LAST:event_DekuKeyPressed

    private void IVLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IVLKeyPressed
        Valid.pindah(evt,CVL,UC);
    }//GEN-LAST:event_IVLKeyPressed

    private void UCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UCKeyPressed
        Valid.pindah(evt,IVL,Deku);
    }//GEN-LAST:event_UCKeyPressed

    private void VAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VAPKeyPressed
        Valid.pindah(evt,Deku,IAD);
    }//GEN-LAST:event_VAPKeyPressed

    private void IADKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IADKeyPressed
        Valid.pindah(evt,VAP,PLEB);
    }//GEN-LAST:event_IADKeyPressed

    private void PLEBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PLEBKeyPressed
        Valid.pindah(evt,IAD,ISK);
    }//GEN-LAST:event_PLEBKeyPressed

    private void ISKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ISKKeyPressed
       Valid.pindah(evt,PLEB,ILO);
    }//GEN-LAST:event_ISKKeyPressed

    private void SputumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SputumKeyPressed
        Valid.pindah(evt,Scabies,Darah);
    }//GEN-LAST:event_SputumKeyPressed

    private void UrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UrineKeyPressed
        Valid.pindah(evt,Darah,Antibiotik);
    }//GEN-LAST:event_UrineKeyPressed

    private void AntibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AntibiotikKeyPressed
        Valid.pindah(evt,Urine,BtnSimpan);
    }//GEN-LAST:event_AntibiotikKeyPressed

    private void DarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DarahKeyPressed
        Valid.pindah(evt,Sputum,Urine);
    }//GEN-LAST:event_DarahKeyPressed

    private void ILOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ILOKeyPressed
        Valid.pindah(evt,ISK,HAP);
    }//GEN-LAST:event_ILOKeyPressed

    private void HAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HAPKeyPressed
        Valid.pindah(evt,ILO,Tania);
    }//GEN-LAST:event_HAPKeyPressed

    private void TaniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaniaKeyPressed
        Valid.pindah(evt,HAP,Scabies);
    }//GEN-LAST:event_TaniaKeyPressed

    private void ScabiesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScabiesKeyPressed
        Valid.pindah(evt,Tania,Sputum);
    }//GEN-LAST:event_ScabiesKeyPressed

    private void KamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KamarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KamarKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCariBangsal.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void btncaribangsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaribangsalActionPerformed
        if(TNoRw.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
                tbcaribangsal.requestFocus();
        
        }else{
            WindowCariBangsal.setLocationRelativeTo(internalFrame1); 
            WindowCariBangsal.setSize(384,221);
            WindowCariBangsal.setVisible(true);
            tampil1();
            getdata1();
        }
    }//GEN-LAST:event_btncaribangsalActionPerformed

    private void tbcaribangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcaribangsalMouseClicked
         if (tabmode1.getRowCount() != 0) {
            try {
                getdata1();
            } catch (java.lang.NullPointerException e) {
            }
             WindowCariBangsal.dispose();
        }
    }//GEN-LAST:event_tbcaribangsalMouseClicked

    private void tbcaribangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbcaribangsalKeyPressed
         if (tabmode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdata1();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariBangsal.dispose();
        }
    }//GEN-LAST:event_tbcaribangsalKeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        tampil1();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAll1KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDataHAIs dialog = new DlgDataHAIs(new javax.swing.JFrame(), true);
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
    private widget.TextBox Antibiotik;
    private widget.Button BtnAll;
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox CVL;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Darah;
    private widget.ComboBox Deku;
    private widget.TextBox Diagnosa;
    private widget.TextBox ETT;
    private widget.PanelBiasa FormInput;
    private widget.TextBox HAP;
    private widget.TextBox IAD;
    private widget.TextBox ILO;
    private widget.TextBox ISK;
    private widget.TextBox IVL;
    private widget.TextBox Kamar;
    private widget.Label LCount;
    private widget.TextBox PLEB;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Scabies;
    private widget.ScrollPane Scroll;
    private widget.TextBox Sputum;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox Tania;
    private widget.TextBox UC;
    private widget.TextBox Urine;
    private widget.TextBox VAP;
    private javax.swing.JDialog WindowCariBangsal;
    private widget.Button btncaribangsal;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
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
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    private widget.Table tbcaribangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select data_HAIs.tanggal,data_HAIs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "data_HAIs.ETT,data_HAIs.CVL,data_HAIs.IVL,data_HAIs.UC,data_HAIs.VAP,data_HAIs.IAD,"+
                    "data_HAIs.PLEB,data_HAIs.ISK,data_HAIs.ILO,data_HAIs.HAP,data_HAIs.Tinea,data_HAIs.Scabies,"+
                    "data_HAIs.DEKU,data_HAIs.SPUTUM,data_HAIs.DARAH,data_HAIs.URINE,data_HAIs.ANTIBIOTIK,"+
                    "concat(data_HAIs.kd_kamar,', ',bangsal.nm_bangsal),data_HAIs.kd_kamar,data_HAIs.diagnosa from data_HAIs inner join reg_periksa "+
                    "inner join pasien inner join kamar inner join bangsal on data_HAIs.kd_kamar=kamar.kd_kamar "+
                    "and kamar.kd_bangsal=bangsal.kd_bangsal and data_HAIs.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where "+
                    "data_HAIs.tanggal between ? and ? and data_HAIs.no_rawat like ? or "+
                    "data_HAIs.tanggal between ? and ? and reg_periksa.no_rkm_medis like ? or "+
                    "data_HAIs.tanggal between ? and ? and bangsal.nm_bangsal like ? or "+
                    "data_HAIs.tanggal between ? and ? and pasien.nm_pasien like ? order by data_HAIs.tanggal ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(12,"%"+TCari.getText()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),
                        rs.getString(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18),
                        rs.getString(19),rs.getString(20),rs.getString(21),
                        rs.getString(22),rs.getString(23),rs.getString(24)
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
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        int b=tabMode.getRowCount();
        LCount.setText(""+b);
    }
    
    public void tampil1() {
        Valid.tabelKosong(tabmode1);
        try{
            ps=koneksi.prepareStatement(
                    "SELECT kamar_inap.kd_kamar,bangsal.nm_bangsal FROM kamar_inap INNER JOIN bangsal INNER JOIN kamar " +
                    "ON kamar_inap.kd_kamar=kamar.kd_kamar " +
                    "AND kamar.kd_bangsal=bangsal.kd_bangsal " +
                    "WHERE kamar_inap.no_rawat='"+TNoRw.getText()+"' ");
            try {
    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabmode1.addRow(new String[]{
                        rs.getString(1),rs.getString(2)
                        
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
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        int b=tabmode1.getRowCount();
        LCount.setText(""+b);
    }

    public void emptTeks() {
        Tanggal.requestFocus();
        ETT.setText("0");
        CVL.setText("0");
        IVL.setText("0");
        UC.setText("0");
        VAP.setText("0");
        IAD.setText("0");
        PLEB.setText("0");
        ISK.setText("0");
        HAP.setText("0");
        ILO.setText("0");
        Tania.setText("0");
        Scabies.setText("0");
        Sputum.setText("");
        Urine.setText("");
        Darah.setText("");
        Antibiotik.setText("");
        Diagnosa.setText("-");
        Tanggal.setDate(new Date());
    }

   

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            ETT.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            CVL.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            IVL.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            UC.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            VAP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            IAD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            PLEB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            ISK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            ILO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            HAP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Tania.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Scabies.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Deku.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Sputum.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Darah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            Urine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            Antibiotik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Kamar.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
        }
    }
    
    
     private void getdata1() {
        if(tbcaribangsal.getSelectedRow()!= -1){
            Kamar.setText(tbcaribangsal.getValueAt(tbcaribangsal.getSelectedRow(),0).toString());
        }
    }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
   
    
    public void setNoRm(String norwt, Date tgl1, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();              
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,216));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_HAIs());
        BtnHapus.setEnabled(akses.getdata_HAIs());
        BtnPrint.setEnabled(akses.getdata_HAIs());
    }



   
}

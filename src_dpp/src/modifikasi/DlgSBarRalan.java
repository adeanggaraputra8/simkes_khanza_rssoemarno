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
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import restore.DlgRestoreObat;
import simrskhanza.DlgCariBangsal;
import kepegawaian.DlgCariPegawai;
import simrskhanza.DlgPasien;

public class DlgSBarRalan extends javax.swing.JDialog {

    private DefaultTableModel tabModePemeriksaanSbar;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps7;
    private ResultSet rs;
    private int i = 0;
    private String kdlokasi = "", nmlokasi = "", tanggal = "0000-00-00",qrystok="";
    public  DlgCariPegawai pegawai2=new DlgCariPegawai(null,false);
    private DlgPasien pasien=new DlgPasien(null,false);


    public DlgSBarRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
         tabModePemeriksaanSbar=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam",
            "Situation","Background","Assesment","Recommendation","NIP","Dokter/Paramedis","Profesi/Jabatan","Tulis","Baca","Konfirmasi"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPemeriksaanSbar.setModel(tabModePemeriksaanSbar);
        tbPemeriksaanSbar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaanSbar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbPemeriksaanSbar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(190);
            }else if(i==7){
                column.setPreferredWidth(190);
            }else if(i==8){
                column.setPreferredWidth(190);
            }else if(i==9){
                column.setPreferredWidth(180);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(120);
            }else if(i==13){
                column.setPreferredWidth(200);
            }else if(i==14){
                column.setPreferredWidth(50);
            }else if(i==15){
                column.setPreferredWidth(50);
            }
        }
        tbPemeriksaanSbar.setDefaultRenderer(Object.class, new WarnaTable());
        
         pegawai2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgRawatJalan")){
                    if(pegawai2.getTable().getSelectedRow()!= -1){  
                        KdPeg2.setText(pegawai2.getTable().getValueAt(pegawai2.getTable().getSelectedRow(),0).toString());
                        TPegawai2.setText(pegawai2.getTable().getValueAt(pegawai2.getTable().getSelectedRow(),1).toString());
                        Jabatan1.setText(pegawai2.getTable().getValueAt(pegawai2.getTable().getSelectedRow(),3).toString());
                        KdPeg2.requestFocus();                    
                    }   
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
         
         
          pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgRawatJalan")){
                    if(pasien.getTable().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                    } 
                    if(pasien.getTable2().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),1).toString());
                    } 
                    if(pasien.getTable3().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),1).toString());
                    } 
                    TCariPasien.requestFocus();
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgRawatJalan")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilPemeriksaanSbar();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilPemeriksaanSbar();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilPemeriksaanSbar();
                    }
                }
            });
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

        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        jLabel19 = new widget.Label();
        jLabel21 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        DTPCari2 = new widget.Tanggal();
        jLabel24 = new widget.Label();
        TCariPasien = new widget.TextBox();
        btnPasien = new widget.Button();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnBatal = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbPemeriksaanSbar = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        scrollPane5 = new widget.ScrollPane();
        TSituation = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        TBackground = new widget.TextArea();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        TAssesment = new widget.TextArea();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        TRecommendation = new widget.TextArea();
        jLabel92 = new widget.Label();
        KdPeg2 = new widget.TextBox();
        TPegawai2 = new widget.TextBox();
        BtnSeekPegawai3 = new widget.Button();
        Jabatan1 = new widget.TextBox();
        jLabel93 = new widget.Label();
        TPegawai3 = new widget.TextBox();
        KdPeg3 = new widget.TextBox();
        jLabel104 = new widget.Label();
        BtnVerifSbar = new widget.Button();
        scrollPane12 = new widget.ScrollPane();
        Tulis = new widget.TextArea();
        jLabel94 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        Konfirmasi = new widget.ComboBox();
        Baca = new widget.ComboBox();
        jLabel109 = new widget.Label();
        jLabel99 = new widget.Label();
        BtnVerifSbar1 = new widget.Button();
        cmbJam = new widget.ComboBox();
        TNoRM = new widget.TextBox();
        cmbMnt = new widget.ComboBox();
        DTPTgl = new widget.Tanggal();
        TPasien = new widget.TextBox();
        ChkJln = new widget.CekBox();
        cmbDtk = new widget.ComboBox();
        jLabel23 = new widget.Label();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        ChkInput = new widget.CekBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ SBAR Ralan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
        panelisi2.add(jLabel19);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelisi2.add(jLabel21);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi2.add(DTPCari1);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi2.add(DTPCari2);

        jLabel24.setText("No.RM :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel24);

        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(140, 23));
        panelisi2.add(TCariPasien);

        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('6');
        btnPasien.setToolTipText("Alt+6");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        btnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPasienKeyPressed(evt);
            }
        });
        panelisi2.add(btnPasien);

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

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
        panelisi2.add(BtnCari);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(LCount);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        panelisi1.add(BtnSimpan);

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
        panelisi1.add(BtnEdit);

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
        panelisi1.add(BtnHapus);

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
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnAll);

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
        panelisi1.add(BtnKeluar);

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPemeriksaanSbar.setAutoCreateRowSorter(true);
        tbPemeriksaanSbar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPemeriksaanSbar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaanSbar.setName("tbPemeriksaanSbar"); // NOI18N
        tbPemeriksaanSbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanSbarMouseClicked(evt);
            }
        });
        tbPemeriksaanSbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanSbarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanSbarKeyReleased(evt);
            }
        });
        scrollPane1.setViewportView(tbPemeriksaanSbar);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(660, 338));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(660, 257));
        FormInput.setLayout(null);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        TSituation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TSituation.setColumns(20);
        TSituation.setRows(5);
        TSituation.setName("TSituation"); // NOI18N
        TSituation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSituationKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TSituation);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(70, 90, 360, 70);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        TBackground.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TBackground.setColumns(20);
        TBackground.setRows(5);
        TBackground.setName("TBackground"); // NOI18N
        TBackground.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBackgroundKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(TBackground);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(70, 170, 360, 70);

        jLabel100.setText("Situation :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 90, 70, 23);

        jLabel101.setText("Background :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 170, 70, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        TAssesment.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAssesment.setColumns(20);
        TAssesment.setRows(5);
        TAssesment.setName("TAssesment"); // NOI18N
        TAssesment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAssesmentKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(TAssesment);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(560, 90, 360, 70);

        jLabel102.setText("Asesmen :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(440, 90, 100, 23);

        jLabel103.setText("Recommendation :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(440, 170, 110, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        TRecommendation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TRecommendation.setColumns(20);
        TRecommendation.setRows(5);
        TRecommendation.setName("TRecommendation"); // NOI18N
        TRecommendation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRecommendationKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(TRecommendation);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(560, 170, 360, 70);

        jLabel92.setText("Dilakukan :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 60, 70, 23);

        KdPeg2.setHighlighter(null);
        KdPeg2.setName("KdPeg2"); // NOI18N
        KdPeg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KdPeg2ActionPerformed(evt);
            }
        });
        KdPeg2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPeg2KeyPressed(evt);
            }
        });
        FormInput.add(KdPeg2);
        KdPeg2.setBounds(70, 60, 115, 23);

        TPegawai2.setEditable(false);
        TPegawai2.setHighlighter(null);
        TPegawai2.setName("TPegawai2"); // NOI18N
        TPegawai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPegawai2ActionPerformed(evt);
            }
        });
        FormInput.add(TPegawai2);
        TPegawai2.setBounds(190, 60, 212, 23);

        BtnSeekPegawai3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai3.setMnemonic('4');
        BtnSeekPegawai3.setToolTipText("ALt+4");
        BtnSeekPegawai3.setName("BtnSeekPegawai3"); // NOI18N
        BtnSeekPegawai3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawai3ActionPerformed(evt);
            }
        });
        FormInput.add(BtnSeekPegawai3);
        BtnSeekPegawai3.setBounds(410, 60, 28, 23);

        Jabatan1.setEditable(false);
        Jabatan1.setHighlighter(null);
        Jabatan1.setName("Jabatan1"); // NOI18N
        FormInput.add(Jabatan1);
        Jabatan1.setBounds(630, 60, 209, 23);

        jLabel93.setText("Profesi / Jabatan / Departemen :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(440, 60, 190, 23);

        TPegawai3.setEditable(false);
        TPegawai3.setHighlighter(null);
        TPegawai3.setName("TPegawai3"); // NOI18N
        FormInput.add(TPegawai3);
        TPegawai3.setBounds(1100, 60, 212, 23);

        KdPeg3.setHighlighter(null);
        KdPeg3.setName("KdPeg3"); // NOI18N
        KdPeg3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPeg3KeyPressed(evt);
            }
        });
        FormInput.add(KdPeg3);
        KdPeg3.setBounds(980, 60, 115, 23);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("Advis Dokter");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(230, 270, 80, 23);

        BtnVerifSbar.setMnemonic('4');
        BtnVerifSbar.setToolTipText("ALt+4");
        BtnVerifSbar.setName("BtnVerifSbar"); // NOI18N
        BtnVerifSbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifSbarActionPerformed(evt);
            }
        });
        FormInput.add(BtnVerifSbar);
        BtnVerifSbar.setBounds(550, 180, 28, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Tulis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tulis.setColumns(20);
        Tulis.setRows(5);
        Tulis.setName("Tulis"); // NOI18N
        Tulis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TulisKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Tulis);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(320, 250, 360, 70);

        jLabel94.setText("Ketik:");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(220, 250, 90, 23);

        jLabel95.setText("Baca:");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(700, 270, 30, 23);

        jLabel96.setText("Konfirmasi: :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(680, 250, 70, 23);

        Konfirmasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        Konfirmasi.setName("Konfirmasi"); // NOI18N
        FormInput.add(Konfirmasi);
        Konfirmasi.setBounds(760, 280, 58, 23);

        Baca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        Baca.setName("Baca"); // NOI18N
        FormInput.add(Baca);
        Baca.setBounds(760, 250, 58, 23);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Dokter DPJP Utama");
        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(850, 60, 130, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("Status Verifikasi SBAR");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(730, 300, 200, 23);

        BtnVerifSbar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/folder.png"))); // NOI18N
        BtnVerifSbar1.setMnemonic('4');
        BtnVerifSbar1.setToolTipText("ALt+4");
        BtnVerifSbar1.setName("BtnVerifSbar1"); // NOI18N
        BtnVerifSbar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifSbar1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnVerifSbar1);
        BtnVerifSbar1.setBounds(700, 300, 28, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(720, 10, 62, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(210, 10, 80, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(790, 10, 62, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(630, 10, 90, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(290, 10, 270, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(920, 10, 23, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(850, 10, 62, 23);

        jLabel23.setText("Tanggal :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(570, 10, 60, 23);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 125, 23);

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

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPemeriksaanSbar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
         tampilPemeriksaanSbar();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbPemeriksaanSbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanSbarMouseClicked
        if (tabModePemeriksaanSbar.getRowCount() != 0) {
            try {
                getDataPemeriksaanSbar();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPemeriksaanSbarMouseClicked

    private void tbPemeriksaanSbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanSbarKeyPressed
        if (tabModePemeriksaanSbar.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbPemeriksaanSbarKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
       if(tabModePemeriksaanSbar.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    for(i=0;i<tbPemeriksaanSbar.getRowCount();i++){
                        if(tbPemeriksaanSbar.getValueAt(i,0).toString().equals("true")){
                            if(akses.getkode().equals("Admin Utama")){
                                Sequel.queryu("delete from pemeriksaan_ralan_sbar where no_rawat='"+tbPemeriksaanSbar.getValueAt(i,1).toString()+
                                        "' and tgl_perawatan='"+tbPemeriksaanSbar.getValueAt(i,4).toString()+
                                        "' and jam_rawat='"+tbPemeriksaanSbar.getValueAt(i,5).toString()+"' ");
                                tabModePemeriksaanSbar.removeRow(i);
                                i--;
                            }else{
                                if(akses.getkode().equals(tbPemeriksaanSbar.getValueAt(i,10).toString())){
                                    Sequel.queryu("delete from pemeriksaan_ralan_sbar where no_rawat='"+tbPemeriksaanSbar.getValueAt(i,1).toString()+
                                            "' and tgl_perawatan='"+tbPemeriksaanSbar.getValueAt(i,4).toString()+
                                            "' and jam_rawat='"+tbPemeriksaanSbar.getValueAt(i,5).toString()+"' ");
                                    tabModePemeriksaanSbar.removeRow(i);
                                    i--;
                                }else{
                                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    }
                    LCount.setText(""+tabModePemeriksaanSbar.getRowCount());
                }   
                tampilPemeriksaanSbar();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if((!TSituation.getText().trim().equals(""))||(!TBackground.getText().trim().equals(""))||(!TAssesment.getText().trim().equals(""))||
                            (!TRecommendation.getText().trim().equals(""))){
                        if(tbPemeriksaanSbar.getSelectedRow()>-1){
                            if(akses.getkode().equals("Admin Utama")){
                                Sequel.mengedit("pemeriksaan_ralan_sbar","no_rawat='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),1)+
                                    "' and tgl_perawatan='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),4)+
                                    "' and jam_rawat='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),5)+"'",
                                    "no_rawat='"+TNoRw.getText()+"',situation='"+TSituation.getText()+"',background='"+TBackground.getText()+"',"+
                                    "assesment='"+TAssesment.getText()+"',recommendation='"+TRecommendation.getText()+"',"+
                                    "tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                    "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"',"+
                                    "nip='"+KdPeg2.getText()+"'");
                                tampilPemeriksaanSbar();
                                BtnBatalActionPerformed(evt);
                            }else{
                                if(akses.getkode().equals(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),22).toString())){
                                    Sequel.mengedit("pemeriksaan_ralan_sbar","no_rawat='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),1)+
                                        "' and tgl_perawatan='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),4)+
                                        "' and jam_rawat='"+tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),5)+"'",
                                        "no_rawat='"+TNoRw.getText()+"',situation='"+TSituation.getText()+"',background='"+TBackground.getText()+"',"+
                                        "assesment='"+TAssesment.getText()+"',recommendation='"+TRecommendation.getText()+"',"+
                                        "tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                        "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"'");
                                        
                                    tampilPemeriksaanSbar();
                                    BtnBatalActionPerformed(evt);
                                }else{
                                    JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampilPemeriksaanSbar();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         isForm();
    }//GEN-LAST:event_formWindowOpened

    private void tbPemeriksaanSbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanSbarKeyReleased
        if (tabModePemeriksaanSbar.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPemeriksaanSbar();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanSbarKeyReleased

    private void TSituationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSituationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSituationKeyPressed

    private void TBackgroundKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBackgroundKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBackgroundKeyPressed

    private void TAssesmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAssesmentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAssesmentKeyPressed

    private void TRecommendationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRecommendationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRecommendationKeyPressed

    private void KdPeg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KdPeg2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPeg2ActionPerformed

    private void KdPeg2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPeg2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",TPegawai2,KdPeg2.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekPegawai3ActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,TSituation);
        }
    }//GEN-LAST:event_KdPeg2KeyPressed

    private void TPegawai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPegawai2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPegawai2ActionPerformed

    private void BtnSeekPegawai3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawai3ActionPerformed
        akses.setform("DlgRawatJalan");
        pegawai2.emptTeks();
        pegawai2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai2.setLocationRelativeTo(internalFrame1);
        pegawai2.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawai3ActionPerformed

    private void KdPeg3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPeg3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPeg3KeyPressed

    private void BtnVerifSbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifSbarActionPerformed
        if(TNoRw.getText().trim().equals("") ){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgSBARRalan soap=new DlgSBARRalan(null,false);
            soap.setNoRawat(TNoRw.getText(),TNoRw.getText());
            soap.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            soap.setLocationRelativeTo(internalFrame1);
            soap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnVerifSbarActionPerformed

    private void TulisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TulisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TulisKeyPressed

    private void BtnVerifSbar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifSbar1ActionPerformed
        if(TNoRw.getText().trim().equals("") ){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgSBARRalan soap=new DlgSBARRalan(null,false);
            soap.setNoRawat(TNoRw.getText(),TNoRw.getText());
            soap.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            soap.setLocationRelativeTo(internalFrame1);
            soap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnVerifSbar1ActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
       if((!TSituation.getText().trim().equals(""))||(!TBackground.getText().trim().equals(""))||(!TAssesment.getText().trim().equals(""))||
                            (!TRecommendation.getText().trim().equals(""))){
                        if(KdPeg2.getText().trim().equals("")||TPegawai2.getText().trim().equals("")){
                            Valid.textKosong(KdPeg2,"Dokter/Paramedis masih kosong...!!");
                        }else{
                            if(akses.getkode().equals("Admin Utama")){
                                Sequel.menyimpan("pemeriksaan_ralan_sbar","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                                    TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),                      
                                    TSituation.getText(),TBackground.getText(),TAssesment.getText(),TRecommendation.getText(),KdPeg2.getText(),Tulis.getText(),
                                    Baca.getSelectedItem().toString(),Konfirmasi.getSelectedItem().toString()
                                });
                                tampilPemeriksaanSbar();
                                BtnBatalActionPerformed(evt);
                            }else{
                                if(akses.getkode().equals(KdPeg2.getText())){
                                    Sequel.menyimpan("pemeriksaan_ralan_sbar","?,?,?,?,?,?,?,?","Data",8,new String[]{
                                        TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),                      
                                        TSituation.getText(),TBackground.getText(),TAssesment.getText(),TRecommendation.getText(),KdPeg2.getText(),Tulis.getText(),
                                        Baca.getSelectedItem().toString(),Konfirmasi.getSelectedItem().toString() 
                                    });
                                    tampilPemeriksaanSbar();
                                    BtnBatalActionPerformed(evt);
                                }else{
                                    JOptionPane.showMessageDialog(null,"Hanya bisa disimpan oleh dokter/petugas yang bersangkutan..!!");
                                }
                            }
                        }
                    } 
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Tulis,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt,BtnSeekPegawai3,cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt,cmbMnt,TCari);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
       
    }//GEN-LAST:event_TNoRwKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        TSituation.setText("");
        TBackground.setText("");
        TAssesment.setText("");
        TRecommendation.setText("");
        Tulis.setText("");
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgRawatJalan");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt,TCariPasien,DTPCari1);
    }//GEN-LAST:event_btnPasienKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSBarRalan dialog = new DlgSBarRalan(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Baca;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeekPegawai3;
    private widget.Button BtnSimpan;
    private widget.Button BtnVerifSbar;
    private widget.Button BtnVerifSbar1;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jabatan1;
    private widget.TextBox KdPeg2;
    private widget.TextBox KdPeg3;
    private widget.ComboBox Konfirmasi;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea TAssesment;
    private widget.TextArea TBackground;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPegawai2;
    private widget.TextBox TPegawai3;
    private widget.TextArea TRecommendation;
    private widget.TextArea TSituation;
    private widget.TextArea Tulis;
    private widget.Button btnPasien;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel109;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel3;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel2;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbPemeriksaanSbar;
    // End of variables declaration//GEN-END:variables

    private void tampilPemeriksaanSbar() {
        Valid.tabelKosong(tabModePemeriksaanSbar);
        try{  
            ps7=koneksi.prepareStatement("select pemeriksaan_ralan_sbar.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                "pemeriksaan_ralan_sbar.tgl_perawatan,pemeriksaan_ralan_sbar.jam_rawat,pemeriksaan_ralan_sbar.situation,pemeriksaan_ralan_sbar.background, " +
                "pemeriksaan_ralan_sbar.assesment,pemeriksaan_ralan_sbar.recommendation,pemeriksaan_ralan_sbar.nip,pegawai.nama,pegawai.jbtn, " +
                "pemeriksaan_ralan_sbar.tulis,pemeriksaan_ralan_sbar.baca,pemeriksaan_ralan_sbar.konfirmasi from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join pemeriksaan_ralan_sbar on pemeriksaan_ralan_sbar.no_rawat=reg_periksa.no_rawat "+
                "inner join pegawai on pemeriksaan_ralan_sbar.nip=pegawai.nik where "+
                "pemeriksaan_ralan_sbar.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? "+
                (TCari.getText().trim().equals("")?"":"and (pemeriksaan_ralan_sbar.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                "pemeriksaan_ralan_sbar.situation like ? or pemeriksaan_ralan_sbar.background like ? or pemeriksaan_ralan_sbar.assesment like ? or "+
                "pemeriksaan_ralan_sbar.recommendation like ?)")+
                "order by pemeriksaan_ralan_sbar.no_rawat,pemeriksaan_ralan_sbar.tgl_perawatan,pemeriksaan_ralan_sbar.jam_rawat desc"); 
            try{
                ps7.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps7.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps7.setString(3,"%"+TCariPasien.getText()+"%");
                if(!TCari.getText().trim().equals("")){
                    ps7.setString(4,"%"+TCari.getText().trim()+"%");
                    ps7.setString(5,"%"+TCari.getText().trim()+"%");
                    ps7.setString(6,"%"+TCari.getText().trim()+"%");
                    ps7.setString(7,"%"+TCari.getText().trim()+"%");
                    ps7.setString(8,"%"+TCari.getText().trim()+"%");
                    ps7.setString(9,"%"+TCari.getText().trim()+"%");
                    ps7.setString(10,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps7.executeQuery();
                while(rs.next()){
                    tabModePemeriksaanSbar.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps7!=null){
                    ps7.close();
                }
            }                  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModePemeriksaanSbar.getRowCount());
    }

   

    public void emptTeks() {
        TSituation.setText("");
        TBackground.setText("");
        TAssesment.setText("");
        TRecommendation.setText("");
        Tulis.setText("");
    }

    private void getDataPemeriksaanSbar() {
        if(tbPemeriksaanSbar.getSelectedRow()!= -1){
            TNoRw.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),1).toString());
            TNoRM.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),2).toString());
            TPasien.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),3).toString());             
            TSituation.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),6).toString()); 
            TBackground.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),7).toString()); 
            TAssesment.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),8).toString()); 
            TRecommendation.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),9).toString()); 
            cmbJam.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),5).toString().substring(6,8));
            Valid.SetTgl(DTPTgl,tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),4).toString());
            Tulis.setText(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),13).toString()); 
            Baca.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),14).toString());
            Konfirmasi.setSelectedItem(tbPemeriksaanSbar.getValueAt(tbPemeriksaanSbar.getSelectedRow(),15).toString());
        }
    }

    public JTable getTable() {
        return tbPemeriksaanSbar;
    }

    

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 363));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

        public void isCek(){;   
        if(akses.getjml2()>=1){
            KdPeg2.setEditable(false);
            BtnSeekPegawai3.setEnabled(false);
            Sequel.cariIsi("select nama from pegawai where nik=?", TPegawai2,KdPeg2.getText());
            if(TPegawai2.getText().equals("")){
                KdPeg3.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas/dokter yang input...!!");
            }
        }            
    }
        
     private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);    
        isRawat();
        isPsien();              
        ChkInput.setSelected(true);
        isForm();
        TNoRw.requestFocus();
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =cmbJam.getSelectedIndex();
                    nilai_menit =cmbMnt.getSelectedIndex();
                    nilai_detik =cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

}

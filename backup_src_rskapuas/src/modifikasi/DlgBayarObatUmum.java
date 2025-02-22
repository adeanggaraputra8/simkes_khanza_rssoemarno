/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kontribusi dari dokter Salim Mulyana
 */

package modifikasi;

import surat.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;


/**
 * 
 * @author salimmulyana
 */
public final class DlgBayarObatUmum extends javax.swing.JDialog {
    private final DefaultTableModel tabmodebayar;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,psobat;
    private ResultSet rs,rsobat;
    private int i=0;
    private String tgl;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DlgBayarObatUmum(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabmodebayar=new DefaultTableModel(null,new Object[]{
            "No.Resep","No.Rawat","No.R.M.","Nama Pasien","Tanggal Bayar","Status","Jaminan","Petugas Kasir",
            "Lokasi Obat","Status Bayar","Keterangan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}{
                
             };
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbkembali.setModel(tabmodebayar);
        tbkembali.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbkembali.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbkembali.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(70);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(70);
            }else if(i==5){
                column.setPreferredWidth(70);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(50);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(150);
            }
        }
        tbkembali.setDefaultRenderer(Object.class, new WarnaTable());
        
          
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilbayar();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilbayar();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilbayar();
                    }
                }
            });
        }
        
      
        
       petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                     kd_nip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                     Nmpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                Nmpetugas.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnNotaPx = new javax.swing.JMenuItem();
        Mnrekaplaporan = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        No_rw2 = new widget.TextBox();
        kdpj = new widget.TextBox();
        status1 = new widget.TextBox();
        lokasi1 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbkembali = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatalBayar = new widget.Button();
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
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        DTPBayar = new widget.Tanggal();
        Tagihan1 = new widget.TextBox();
        NoResep = new widget.TextBox();
        jLabel46 = new widget.Label();
        kd_nip = new widget.TextBox();
        Nmpetugas = new widget.TextBox();
        btncaripetugas = new widget.Button();
        Keterangan = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel39 = new widget.Label();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnNotaPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNotaPx.setForeground(new java.awt.Color(70, 70, 70));
        MnNotaPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNotaPx.setText("Cetak Nota");
        MnNotaPx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNotaPx.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNotaPx.setIconTextGap(5);
        MnNotaPx.setName("MnNotaPx"); // NOI18N
        MnNotaPx.setPreferredSize(new java.awt.Dimension(250, 25));
        MnNotaPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNotaPxActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnNotaPx);

        Mnrekaplaporan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnrekaplaporan.setForeground(new java.awt.Color(70, 70, 70));
        Mnrekaplaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnrekaplaporan.setText("Rekapan Setoran");
        Mnrekaplaporan.setToolTipText("");
        Mnrekaplaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnrekaplaporan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnrekaplaporan.setIconTextGap(5);
        Mnrekaplaporan.setName("Mnrekaplaporan"); // NOI18N
        Mnrekaplaporan.setPreferredSize(new java.awt.Dimension(250, 25));
        Mnrekaplaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnrekaplaporanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Mnrekaplaporan);

        No_rw2.setEditable(false);
        No_rw2.setForeground(new java.awt.Color(255, 255, 255));
        No_rw2.setHighlighter(null);
        No_rw2.setName("No_rw2"); // NOI18N
        No_rw2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                No_rw2KeyPressed(evt);
            }
        });

        kdpj.setEditable(false);
        kdpj.setForeground(new java.awt.Color(255, 255, 255));
        kdpj.setHighlighter(null);
        kdpj.setName("kdpj"); // NOI18N
        kdpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpjKeyPressed(evt);
            }
        });

        status1.setEditable(false);
        status1.setForeground(new java.awt.Color(255, 255, 255));
        status1.setHighlighter(null);
        status1.setName("status1"); // NOI18N
        status1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                status1KeyPressed(evt);
            }
        });

        lokasi1.setEditable(false);
        lokasi1.setForeground(new java.awt.Color(255, 255, 255));
        lokasi1.setHighlighter(null);
        lokasi1.setName("lokasi1"); // NOI18N
        lokasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lokasi1KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pembayaran Obat Umum ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbkembali.setAutoCreateRowSorter(true);
        tbkembali.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbkembali.setComponentPopupMenu(jPopupMenu1);
        tbkembali.setName("tbkembali"); // NOI18N
        tbkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkembaliMouseClicked(evt);
            }
        });
        tbkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbkembaliKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbkembali);

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
        BtnSimpan.setText("Simpan Pembayaran");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(200, 30));
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

        BtnBatalBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnBatalBayar.setMnemonic('H');
        BtnBatalBayar.setText("Batalkan Pembayaran");
        BtnBatalBayar.setToolTipText("Alt+H");
        BtnBatalBayar.setName("BtnBatalBayar"); // NOI18N
        BtnBatalBayar.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnBatalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalBayarActionPerformed(evt);
            }
        });
        BtnBatalBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalBayarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatalBayar);

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

        jLabel19.setText("Tgl. Bayar :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 186));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 195));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 95, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(99, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(355, 10, 365, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(242, 10, 111, 23);

        DTPBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPBayar.setDisplayFormat("dd-MM-yyyy");
        DTPBayar.setName("DTPBayar"); // NOI18N
        DTPBayar.setOpaque(false);
        DTPBayar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPBayarItemStateChanged(evt);
            }
        });
        DTPBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPBayarKeyPressed(evt);
            }
        });
        FormInput.add(DTPBayar);
        DTPBayar.setBounds(360, 50, 109, 23);

        Tagihan1.setEditable(false);
        Tagihan1.setBackground(new java.awt.Color(102, 102, 255));
        Tagihan1.setForeground(new java.awt.Color(0, 0, 0));
        Tagihan1.setCaretColor(new java.awt.Color(0, 0, 0));
        Tagihan1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Tagihan1.setName("Tagihan1"); // NOI18N
        Tagihan1.setPreferredSize(new java.awt.Dimension(150, 24));
        Tagihan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tagihan1ActionPerformed(evt);
            }
        });
        FormInput.add(Tagihan1);
        Tagihan1.setBounds(560, 50, 130, 24);

        NoResep.setEditable(false);
        NoResep.setName("NoResep"); // NOI18N
        NoResep.setPreferredSize(new java.awt.Dimension(150, 24));
        NoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoResepActionPerformed(evt);
            }
        });
        FormInput.add(NoResep);
        NoResep.setBounds(150, 50, 100, 24);

        jLabel46.setText("Petugas Kasir : ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(40, 80, 90, 23);

        kd_nip.setEditable(false);
        kd_nip.setName("kd_nip"); // NOI18N
        kd_nip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_nipActionPerformed(evt);
            }
        });
        FormInput.add(kd_nip);
        kd_nip.setBounds(150, 80, 50, 24);

        Nmpetugas.setEditable(false);
        Nmpetugas.setName("Nmpetugas"); // NOI18N
        Nmpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmpetugasActionPerformed(evt);
            }
        });
        FormInput.add(Nmpetugas);
        Nmpetugas.setBounds(200, 80, 180, 24);

        btncaripetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaripetugas.setMnemonic('3');
        btncaripetugas.setToolTipText("Alt+3");
        btncaripetugas.setName("btncaripetugas"); // NOI18N
        btncaripetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btncaripetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugasActionPerformed(evt);
            }
        });
        btncaripetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncaripetugasKeyPressed(evt);
            }
        });
        FormInput.add(btncaripetugas);
        btncaripetugas.setBounds(380, 80, 28, 23);

        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganActionPerformed(evt);
            }
        });
        FormInput.add(Keterangan);
        Keterangan.setBounds(150, 110, 390, 24);

        jLabel45.setText("Keterangan Saat Panjar : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 110, 140, 23);

        jLabel42.setText("No.Resep :  ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(50, 50, 80, 23);

        jLabel41.setText("Tanggal Bayar :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(260, 50, 90, 23);

        jLabel39.setText("Tagihan :  Rp");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(470, 50, 80, 23);

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
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
       
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
       if(NoResep.getText().trim().equals("")){
            Valid.textKosong(NoResep,"No.Resep");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{
            
            Sequel.mengedit("status_resep","no_resep=?","tgl_resep=?,no_rawat=?,kd_pj=?,status=?,petugas=?,lokasi=?,keterangan=?,ket1=?",9,new String[]{
                    Valid.SetTgl(DTPBayar.getSelectedItem()+""),No_rw2.getText(),
                    kdpj.getText(),status1.getText(),
                    kd_nip.getText(),lokasi1.getText(),"sudah bayar",Keterangan.getText(),NoResep.getText()});
             {
                    JOptionPane.showMessageDialog(null,"Pembayaran Disimpan...!!!");
           }
             MnNotaPxActionPerformed(null);
              tampilbayar();     
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalBayarActionPerformed
        if(NoResep.getText().trim().equals("")){
            Valid.textKosong(NoResep,"No.Resep");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{
          
           Sequel.mengedit("status_resep","no_resep=?","tgl_resep=?,no_rawat=?,kd_pj=?,status=?,petugas=?,lokasi=?,keterangan=?,ket1=?",9,new String[]{
               Valid.SetTgl(DTPBayar.getSelectedItem()+""),No_rw2.getText(),
                    kdpj.getText(),status1.getText(),
                    kd_nip.getText(),lokasi1.getText(),"belum bayar",Keterangan.getText(),NoResep.getText()});
           {
                    JOptionPane.showMessageDialog(null,"Pembayaran Dibatalkan...!!!");
           } 
           tampilbayar();
        
        }     
}//GEN-LAST:event_BtnBatalBayarActionPerformed

    private void BtnBatalBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalBayarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnBatalBayarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
       dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatalBayar,TNoRw);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DTPCari1.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbkembali.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
         tampilbayar();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbkembali.requestFocus();
        }
}//GEN-LAST:event_BtnCariKeyPressed
   
                                  
    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
       
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkembaliMouseClicked
      if(tabmodebayar.getRowCount()!=0){
            try {
                getbayar();
            } catch (java.lang.NullPointerException e){

            }
        }
}//GEN-LAST:event_tbkembaliMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
       isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbkembaliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkembaliKeyReleased
        if(tabmodebayar.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getbayar();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();

            }
        }
    }//GEN-LAST:event_tbkembaliKeyReleased

    private void DTPBayarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPBayarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPBayarItemStateChanged

    private void DTPBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPBayarKeyPressed

    private void Tagihan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tagihan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tagihan1ActionPerformed

    private void NoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoResepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoResepActionPerformed

    private void kd_nipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_nipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_nipActionPerformed

    private void NmpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmpetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmpetugasActionPerformed

    private void btncaripetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugasActionPerformed
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.isCek();
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btncaripetugasActionPerformed

    private void btncaripetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncaripetugasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{Valid.pindah(evt,DTPBayar,BtnSimpan);
        }
    }//GEN-LAST:event_btncaripetugasKeyPressed

    private void KeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganActionPerformed

    private void No_rw2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_No_rw2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_rw2KeyPressed

    private void kdpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpjKeyPressed

    private void status1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_status1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_status1KeyPressed

    private void lokasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lokasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lokasi1KeyPressed

    private void MnNotaPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNotaPxActionPerformed
        if(tabmodebayar.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan Pilih Dulu Petugas Kasir...!!!");
            btncaripetugas.requestFocus();
        }else{
            Valid.panggilUrl("billing/NotaApotek8.php?noresep="+NoResep.getText()+"&tanggal="+Valid.SetTgl(DTPBayar.getSelectedItem()+"")+"&keterangan="+Keterangan.getText().replaceAll(" ","_")+"&petugas="+Nmpetugas.getText().replaceAll(" ","_")+"&norawat="+No_rw2.getText().replaceAll(" ","_"));
        }
    }//GEN-LAST:event_MnNotaPxActionPerformed

    private void MnrekaplaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnrekaplaporanActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptPenjualan3.jasper", "report", "::[ Laporan Setoran Obat dari Kasir ]::",
            "SELECT ((SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') " +
            "-(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar')) as ttlapotek" +
            ",((SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') " +
            "-(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar')) as ttldpo " +
            ",(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as ttlembadpo " +
            ",(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as ttlembaapt  " +
            ",(SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE  cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as grandtotal " +
            "from  " +
            "(SELECT * FROM detail_pemberian_obat) AS NAMA INNER JOIN status_resep " +
            "ON status_resep.no_resep=NAMA.no_resep " +
            "where NAMA.tgl_perawatan between '"  + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " +
            "AND NAMA.cara_bayar='UMUM'  " +
            "AND status_resep.keterangan='sudah bayar '", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnrekaplaporanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBayarObatUmum dialog = new DlgBayarObatUmum(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatalBayar;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPBayar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Keterangan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnNotaPx;
    private javax.swing.JMenuItem Mnrekaplaporan;
    private widget.TextBox Nmpetugas;
    private widget.TextBox NoResep;
    private widget.TextBox No_rw2;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tagihan1;
    private widget.Button btncaripetugas;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kd_nip;
    private widget.TextBox kdpj;
    private widget.TextBox lokasi1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox status1;
    private widget.Table tbkembali;
    // End of variables declaration//GEN-END:variables

    private void tampilbayar() {
        Valid.tabelKosong(tabmodebayar);
        try{
         psobat=koneksi.prepareStatement("SELECT status_resep.no_resep,status_resep.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,status_resep.tgl_resep,status_resep.status,penjab.png_jawab,status_resep.petugas,status_resep.lokasi,status_resep.keterangan,status_resep.ket1 " +
                                        "FROM status_resep INNER JOIN reg_periksa on reg_periksa.no_rawat=status_resep.no_rawat " +
                                        "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                                        "INNER JOIN penjab ON penjab.kd_pj=status_resep.kd_pj " +
                                        "WHERE status_resep.tgl_resep BETWEEN ? and ? and status_resep.no_resep like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.no_rawat like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and reg_periksa.no_rkm_medis like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and pasien.nm_pasien like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and penjab.png_jawab like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.lokasi like ? or "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.status like ? or "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.keterangan like ? ORDER BY status_resep.no_resep DESC ");
                                        
            try { 
                psobat.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(3,"%"+TCari.getText()+"%");
                psobat.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(6,"%"+TCari.getText()+"%");
                psobat.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(9,"%"+TCari.getText()+"%");
                psobat.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(12,"%"+TCari.getText()+"%");
                psobat.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(15,"%"+TCari.getText()+"%");
                psobat.setString(16,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(17,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(18,"%"+TCari.getText()+"%");
                psobat.setString(19,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(20,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(21,"%"+TCari.getText()+"%");
                psobat.setString(22,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                psobat.setString(23,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                psobat.setString(24,"%"+TCari.getText()+"%");
              
                rsobat=psobat.executeQuery();
                while(rsobat.next()){
                    tabmodebayar.addRow(new Object[]{
                                   rsobat.getString(1),
                                   rsobat.getString(2),
                                   rsobat.getString(3),
                                   rsobat.getString(4),
                                   rsobat.getString(5),
                                   rsobat.getString(6),
                                   rsobat.getString(7),
                                   rsobat.getString(8),
                                   rsobat.getString(9),
                                   rsobat.getString(10),
                                   rsobat.getString(11)});
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rsobat!=null){
                    rsobat.close();
                }
                if(psobat!=null){
                    psobat.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabmodebayar.getRowCount());
    }



 
   private void getbayar() {
        if(tbkembali.getSelectedRow()!= -1){
            NoResep.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),0).toString());
            No_rw2.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),1).toString());
            Sequel.cariIsi("SELECT kd_pj FROM status_resep WHERE no_resep=?",kdpj,NoResep.getText());
            status1.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),5).toString());
            lokasi1.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),8).toString());
            Sequel.cariIsi("SELECT nama FROM petugas WHERE nip=?",Nmpetugas,kd_nip.getText());
            kd_nip.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),7).toString());
            Keterangan.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(), 10).toString());
            Valid.SetTgl(DTPBayar,tbkembali.getValueAt(tbkembali.getSelectedRow(),4).toString());
            Sequel.cariIsi("SELECT SUM(total) FROM detail_pemberian_obat WHERE cara_bayar='UMUM' AND no_rawat='"+No_rw2.getText()+"' AND tgl_perawatan='"+Valid.SetTgl(DTPBayar.getSelectedItem() + "") +"' AND no_resep=?",Tagihan1,NoResep.getText());
                 
        }
    }
    
       public void emptyteks(){
          NoResep.setText("");
          kd_nip.setText(akses.getkode());
          Sequel.cariIsi("select nama from petugas where nip=?", Nmpetugas,kd_nip.getText());
          Keterangan.setText("");
          Tagihan1.setText("");
     }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
    
    public void setNoRm(String norwt, String nama, String rm) {
        TNoRw.setText(norwt);
        TNoRM.setText(rm);
        TPasien.setText(nama);
        TCari.setText(nama);
        isRawat();
        isPsien(); 
        ChkInput.setSelected(true);
        isForm();
        BtnCariActionPerformed(null);
    }
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,188));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
       
   
}




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package keuangan;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import fungsi.batasInput;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public final class DlgOmsetPenerimaan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2,tabMode3,tabMode4,tabMode5,tabMode6,tabmodeclosing;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;
    private double rawatjalan=0,rawatinap=0,jualbebas=0,pemasukanlain=0,deposit=0,bayarpiutang=0,hitung=0,ttlpendapatan=0,ttlreal=0,ttlselisih=0,ttlresep=0;
    private DlgCariPetugas pegawai=new DlgCariPetugas(null,false);
    private final DecimalFormat df7 = new DecimalFormat("######.#"); 


    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgOmsetPenerimaan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new Object[]{"Tanggal","No.Nota","No.Rawat","No.RM","Nama Pasien","Akun Bayar","Pembayaran"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRawatJalan.setModel(tabMode);
        tbRawatJalan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRawatJalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbRawatJalan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(103);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(100);
            }
        }
        tbRawatJalan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new Object[]{"Tanggal","No.Nota","No.Rawat","No.RM","Nama Pasien","Akun Bayar","Pembayaran"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbRawatInap.setModel(tabMode2);
        tbRawatInap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRawatInap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbRawatInap.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(103);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(100);
            }
        }
        tbRawatInap.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null,new Object[]{"Tanggal","No.Nota","Jenis Harga","No.RM","Nama Pasien","Akun Bayar","Pembayaran"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPenjualanBebas.setModel(tabMode3);
        tbPenjualanBebas.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenjualanBebas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPenjualanBebas.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(103);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(100);
            }
        }
        tbPenjualanBebas.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4=new DefaultTableModel(null,new Object[]{"Tanggal","Nomor","Terima Dari","Keperluan","Kategori","Pemasukan"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPemasukanLain.setModel(tabMode4);
        tbPemasukanLain.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemasukanLain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbPemasukanLain.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(168);
            }else if(i==3){
                column.setPreferredWidth(190);
            }else if(i==4){
                column.setPreferredWidth(205);
            }else if(i==5){
                column.setPreferredWidth(100);
            }
        }
        tbPemasukanLain.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5=new DefaultTableModel(null,new Object[]{"Tanggal","No.Deposit","No.Rawat","No.RM","Nama Pasien","Akun Bayar","Deposit"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDeposit.setModel(tabMode5);
        tbDeposit.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDeposit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbDeposit.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(103);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(176);
            }else if(i==5){
                column.setPreferredWidth(204);
            }else if(i==6){
                column.setPreferredWidth(100);
            }
        }
        tbDeposit.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6=new DefaultTableModel(null,new Object[]{"Tanggal","No.Tagihan","No.RM","Nama Pasien","Akun Bayar","Akun Piutang","Pembayaran"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Double.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPiutangDibayar.setModel(tabMode6);
        tbPiutangDibayar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPiutangDibayar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPiutangDibayar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(103);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(166);
            }else if(i==5){
                column.setPreferredWidth(167);
            }else if(i==6){
                column.setPreferredWidth(100);
            }
        }
        tbPiutangDibayar.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabmodeclosing=new DefaultTableModel(null,new Object[]{"Tanggal","Total Real Penjualan Umum","Kasir","Kode Kasir","Total Real Penjualan Resep","Shif Jaga"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Double.class,java.lang.Object.class,java.lang.Object.class,java.lang.Double.class,java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbclosing.setModel(tabmodeclosing);
        tbclosing.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbclosing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbclosing.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setMinWidth(0);
                column.setMaxWidth(0); 
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(100);
            }
        }
        tbclosing.setDefaultRenderer(Object.class, new WarnaTable());
        
        TPendapatan.setDocument(new batasInput((byte)15).getKata(TPendapatan));
        KdPeg.setDocument(new batasInput((byte)20).getKata(KdPeg));
        TPegawai.setDocument(new batasInput((byte)20).getKata(TPendapatan));
        
          pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai.getTable().getSelectedRow()!= -1){                   
                    KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),0).toString());
                    TPegawai.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(),1).toString());                    
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
        
        pegawai.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pegawai.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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

        ttlpu = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel16 = new javax.swing.JLabel();
        LCountTotal = new javax.swing.JLabel();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel10 = new javax.swing.JLabel();
        LCountRawatJalan = new javax.swing.JLabel();
        LCountRawatInap = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        LCountJualBebas = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LCountPemasukanLain = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LCountDeposit = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        LCountPiutangDibayar = new javax.swing.JLabel();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbRawatJalan = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbRawatInap = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbPenjualanBebas = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbPemasukanLain = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbDeposit = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbPiutangDibayar = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        Lselisih = new widget.Label();
        jLabel37 = new widget.Label();
        KdPeg = new widget.TextBox();
        TPegawai = new widget.TextBox();
        BtnSeekPegawai = new widget.Button();
        TPendapatan = new widget.TextBox();
        jLabel41 = new widget.Label();
        DTPTanggal = new widget.Tanggal();
        jLabel26 = new widget.Label();
        jLabel20 = new widget.Label();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        TPendapatanResep = new widget.TextBox();
        jLabel42 = new widget.Label();
        Shif = new widget.ComboBox();
        label12 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        tbclosing = new widget.Table();

        ttlpu.setText("0");
        ttlpu.setHighlighter(null);
        ttlpu.setName("ttlpu"); // NOI18N
        ttlpu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ttlpuMouseMoved(evt);
            }
        });
        ttlpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ttlpuMouseExited(evt);
            }
        });
        ttlpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ttlpuKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penerimaan/Omset/Kas Masuk ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 130));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass8.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-03-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-03-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(DTPCari2);

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
        panelGlass8.add(BtnCari);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(50, 50, 50));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Total Penerimaan :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(jLabel16);

        LCountTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountTotal.setForeground(new java.awt.Color(50, 50, 50));
        LCountTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountTotal.setText("0");
        LCountTotal.setName("LCountTotal"); // NOI18N
        LCountTotal.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass8.add(LCountTotal);

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
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 74));
        panelGlass9.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(50, 50, 50));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Rawat Jalan :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel10);
        jLabel10.setBounds(0, 10, 120, 23);

        LCountRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountRawatJalan.setForeground(new java.awt.Color(50, 50, 50));
        LCountRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountRawatJalan.setText("0");
        LCountRawatJalan.setName("LCountRawatJalan"); // NOI18N
        LCountRawatJalan.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountRawatJalan);
        LCountRawatJalan.setBounds(127, 10, 150, 23);

        LCountRawatInap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountRawatInap.setForeground(new java.awt.Color(50, 50, 50));
        LCountRawatInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountRawatInap.setText("0");
        LCountRawatInap.setName("LCountRawatInap"); // NOI18N
        LCountRawatInap.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountRawatInap);
        LCountRawatInap.setBounds(381, 10, 150, 23);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 50, 50));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Rawat Inap :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel11);
        jLabel11.setBounds(294, 10, 80, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(50, 50, 50));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Penjualan Bebas :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel12);
        jLabel12.setBounds(550, 10, 100, 23);

        LCountJualBebas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountJualBebas.setForeground(new java.awt.Color(50, 50, 50));
        LCountJualBebas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountJualBebas.setText("0");
        LCountJualBebas.setName("LCountJualBebas"); // NOI18N
        LCountJualBebas.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountJualBebas);
        LCountJualBebas.setBounds(657, 10, 150, 23);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(50, 50, 50));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Pemasukan Lain-lain :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel13);
        jLabel13.setBounds(0, 40, 120, 23);

        LCountPemasukanLain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountPemasukanLain.setForeground(new java.awt.Color(50, 50, 50));
        LCountPemasukanLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountPemasukanLain.setText("0");
        LCountPemasukanLain.setName("LCountPemasukanLain"); // NOI18N
        LCountPemasukanLain.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountPemasukanLain);
        LCountPemasukanLain.setBounds(127, 40, 150, 23);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(50, 50, 50));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Deposit :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel14);
        jLabel14.setBounds(294, 40, 80, 23);

        LCountDeposit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountDeposit.setForeground(new java.awt.Color(50, 50, 50));
        LCountDeposit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountDeposit.setText("0");
        LCountDeposit.setName("LCountDeposit"); // NOI18N
        LCountDeposit.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountDeposit);
        LCountDeposit.setBounds(381, 40, 150, 23);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(50, 50, 50));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Piutang Dibayar :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel15);
        jLabel15.setBounds(550, 40, 100, 23);

        LCountPiutangDibayar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LCountPiutangDibayar.setForeground(new java.awt.Color(50, 50, 50));
        LCountPiutangDibayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountPiutangDibayar.setText("0");
        LCountPiutangDibayar.setName("LCountPiutangDibayar"); // NOI18N
        LCountPiutangDibayar.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(LCountPiutangDibayar);
        LCountPiutangDibayar.setBounds(657, 40, 150, 23);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRawatJalan.setName("tbRawatJalan"); // NOI18N
        Scroll.setViewportView(tbRawatJalan);

        TabRawat.addTab("Rawat Jalan", Scroll);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbRawatInap.setName("tbRawatInap"); // NOI18N
        Scroll2.setViewportView(tbRawatInap);

        TabRawat.addTab("Rawat Inap", Scroll2);

        Scroll3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPenjualanBebas.setName("tbPenjualanBebas"); // NOI18N
        Scroll3.setViewportView(tbPenjualanBebas);

        TabRawat.addTab("Penjualan Bebas", Scroll3);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbPemasukanLain.setName("tbPemasukanLain"); // NOI18N
        Scroll4.setViewportView(tbPemasukanLain);

        TabRawat.addTab("Pemasukan Lain-lain", Scroll4);

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbDeposit.setName("tbDeposit"); // NOI18N
        Scroll7.setViewportView(tbDeposit);

        TabRawat.addTab("Deposit", Scroll7);

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbPiutangDibayar.setName("tbPiutangDibayar"); // NOI18N
        Scroll8.setViewportView(tbPiutangDibayar);

        TabRawat.addTab("Piutang Dibayar", Scroll8);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(180, 150));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 135));
        panelGlass12.setLayout(null);

        Lselisih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lselisih.setText("0");
        Lselisih.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Lselisih.setName("Lselisih"); // NOI18N
        panelGlass12.add(Lselisih);
        Lselisih.setBounds(670, 10, 140, 23);

        jLabel37.setText("Petugas :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(0, 10, 70, 23);

        KdPeg.setEditable(false);
        KdPeg.setHighlighter(null);
        KdPeg.setName("KdPeg"); // NOI18N
        KdPeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPegKeyPressed(evt);
            }
        });
        panelGlass12.add(KdPeg);
        KdPeg.setBounds(73, 10, 115, 23);

        TPegawai.setEditable(false);
        TPegawai.setHighlighter(null);
        TPegawai.setName("TPegawai"); // NOI18N
        panelGlass12.add(TPegawai);
        TPegawai.setBounds(190, 10, 212, 23);

        BtnSeekPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeekPegawai.setMnemonic('4');
        BtnSeekPegawai.setToolTipText("ALt+4");
        BtnSeekPegawai.setName("BtnSeekPegawai"); // NOI18N
        BtnSeekPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPegawaiActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnSeekPegawai);
        BtnSeekPegawai.setBounds(405, 10, 28, 23);

        TPendapatan.setText("0");
        TPendapatan.setHighlighter(null);
        TPendapatan.setName("TPendapatan"); // NOI18N
        TPendapatan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TPendapatanMouseMoved(evt);
            }
        });
        TPendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TPendapatanMouseExited(evt);
            }
        });
        TPendapatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPendapatanKeyPressed(evt);
            }
        });
        panelGlass12.add(TPendapatan);
        TPendapatan.setBounds(310, 40, 130, 23);

        jLabel41.setText("Pedapatan Real P. Umum :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass12.add(jLabel41);
        jLabel41.setBounds(140, 40, 170, 23);

        DTPTanggal.setForeground(new java.awt.Color(50, 70, 50));
        DTPTanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-03-2023" }));
        DTPTanggal.setDisplayFormat("dd-MM-yyyy");
        DTPTanggal.setName("DTPTanggal"); // NOI18N
        DTPTanggal.setOpaque(false);
        DTPTanggal.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPTanggal);
        DTPTanggal.setBounds(50, 40, 90, 23);

        jLabel26.setText("Selisih   : Rp.");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass12.add(jLabel26);
        jLabel26.setBounds(590, 10, 70, 23);

        jLabel20.setText("Tanggal :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass12.add(jLabel20);
        jLabel20.setBounds(0, 40, 50, 23);

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
        panelGlass12.add(BtnSimpan);
        BtnSimpan.setBounds(40, 80, 100, 30);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/refresh.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Reset");
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
        panelGlass12.add(BtnBatal);
        BtnBatal.setBounds(140, 80, 100, 30);

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
        panelGlass12.add(BtnHapus);
        BtnHapus.setBounds(250, 80, 100, 30);

        TPendapatanResep.setText("0");
        TPendapatanResep.setHighlighter(null);
        TPendapatanResep.setName("TPendapatanResep"); // NOI18N
        TPendapatanResep.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TPendapatanResepMouseMoved(evt);
            }
        });
        TPendapatanResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TPendapatanResepMouseExited(evt);
            }
        });
        TPendapatanResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPendapatanResepKeyPressed(evt);
            }
        });
        panelGlass12.add(TPendapatanResep);
        TPendapatanResep.setBounds(610, 40, 130, 23);

        jLabel42.setText("Pedapatan Real Resep :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass12.add(jLabel42);
        jLabel42.setBounds(440, 40, 170, 23);

        Shif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore" }));
        Shif.setName("Shif"); // NOI18N
        Shif.setPreferredSize(new java.awt.Dimension(40, 23));
        Shif.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShifItemStateChanged(evt);
            }
        });
        Shif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShifKeyPressed(evt);
            }
        });
        panelGlass12.add(Shif);
        Shif.setBounds(500, 10, 90, 23);

        label12.setText("Jenis Jual :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(label12);
        label12.setBounds(430, 10, 65, 23);

        PanelInput.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame5.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);
        Scroll9.setPreferredSize(new java.awt.Dimension(424, 400));

        tbclosing.setName("tbclosing"); // NOI18N
        tbclosing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclosingMouseClicked(evt);
            }
        });
        Scroll9.setViewportView(tbclosing);

        internalFrame5.add(Scroll9, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Closing Penjualan", internalFrame5);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, DTPCari2, BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,DTPCari2);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetRalan.jasper","report","::[ Penerimaan Pembayaran Pasien Rawat Jalan ]::",
                "select DATE_FORMAT(nota_jalan.tanggal,'%d-%m-%Y') as tanggal,nota_jalan.no_nota,nota_jalan.no_rawat,reg_periksa.no_rkm_medis,"+
                "pasien.nm_pasien,detail_nota_jalan.nama_bayar,detail_nota_jalan.besar_bayar "+
                "from nota_jalan inner join reg_periksa on nota_jalan.no_rawat=reg_periksa.no_rawat "+
                "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                "inner join detail_nota_jalan on detail_nota_jalan.no_rawat=reg_periksa.no_rawat "+
                "where nota_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+"' order by nota_jalan.tanggal,nota_jalan.jam ",param);
        }
        
        if(tabMode2.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetRanap.jasper","report","::[ Penerimaan Pembayaran Pasien Rawat Inap ]::",
                "select DATE_FORMAT(nota_inap.tanggal,'%d-%m-%Y') as tanggal,nota_inap.no_nota,nota_inap.no_rawat,reg_periksa.no_rkm_medis,"+
                "pasien.nm_pasien,detail_nota_inap.nama_bayar,detail_nota_inap.besar_bayar "+
                "from nota_inap inner join reg_periksa on nota_inap.no_rawat=reg_periksa.no_rawat "+
                "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                "inner join detail_nota_inap on detail_nota_inap.no_rawat=reg_periksa.no_rawat "+
                "where nota_inap.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+"' order by nota_inap.tanggal,nota_inap.jam ",param);
        }
        
        if(tabMode3.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetPenjualanObat.jasper","report","::[ Penerimaan Penjualan Bebas ]::",
                "select DATE_FORMAT(penjualan.tgl_jual,'%d-%m-%Y') as tanggal,penjualan.nota_jual,penjualan.jns_jual,penjualan.no_rkm_medis,"+
                "penjualan.nm_pasien,penjualan.nama_bayar,round(penjualan.ongkir+penjualan.ppn+sum(detailjual.total)) as total "+
                "from penjualan inner join detailjual on detailjual.nota_jual=penjualan.nota_jual "+
                "where penjualan.status='Sudah Dibayar' and penjualan.tgl_jual between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+"' "+
                "group by penjualan.nota_jual order by penjualan.tgl_jual,penjualan.nota_jual ",param);
        }
        
        if(tabMode4.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetPemasukanLain.jasper","report","::[ Penerimaan Pemasukan Lain-lain ]::",
                "select DATE_FORMAT(pemasukan_lain.tanggal,'%d-%m-%Y') as tanggal,pemasukan_lain.no_masuk,pemasukan_lain.keterangan,"+
                "pemasukan_lain.keperluan,kategori_pemasukan_lain.nama_kategori,pemasukan_lain.besar "+
                "from pemasukan_lain inner join kategori_pemasukan_lain on pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori "+
                "where pemasukan_lain.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+" 00:00:00"+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+" 23:59:59"+"' order by pemasukan_lain.tanggal ",param);
        }
        
        if(tabMode5.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetDeposit.jasper","report","::[ Penerimaan Deposit Pasien ]::",
                "select DATE_FORMAT(deposit.tgl_deposit,'%d-%m-%Y') as tanggal,deposit.no_deposit,deposit.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,deposit.nama_bayar, "+
                "deposit.besar_deposit from deposit inner join reg_periksa on deposit.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "where deposit.tgl_deposit between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+" 00:00:00"+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+" 23:59:59"+"' order by deposit.tgl_deposit ",param);
        }
        
        if(tabMode6.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptOmsetPiutangDibayar.jasper","report","::[ Penerimaan Pembayaran Piutang ]::",
                "select DATE_FORMAT(bayar_piutang.tgl_bayar,'%d-%m-%Y') as tanggal,bayar_piutang.no_rawat,bayar_piutang.no_rkm_medis,pasien.nm_pasien,"+
                "rekening.nm_rek,rekening2.nm_rek,bayar_piutang.besar_cicilan "+
                "from bayar_piutang inner join pasien on bayar_piutang.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join rekening on rekening.kd_rek=bayar_piutang.kd_rek "+
                "inner join rekening as rekening2 on rekening2.kd_rek=bayar_piutang.kd_rek_kontra "+
                "where bayar_piutang.tgl_bayar between '"+Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+"' order by bayar_piutang.tgl_bayar ",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==6){
             tampil();
             
             
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void KdPegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPegKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",TPegawai,KdPeg.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeekPegawaiActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnSimpan,TPendapatan);
        }
    }//GEN-LAST:event_KdPegKeyPressed

    private void BtnSeekPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPegawaiActionPerformed
        akses.setform("DlgOmsetPenerimaan");
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnSeekPegawaiActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(KdPeg.getText().trim().equals("")||TPegawai.getText().trim().equals("")){
            Valid.textKosong(KdPeg,"petugas");
        }else if(TPendapatan.getText().trim().equals("")||TPendapatan.getText().trim().equals("0")){
            Valid.textKosong(TPendapatan,"Pendapatan Real");
        }else if(LCountJualBebas.getText().trim().equals("")||LCountJualBebas.getText().trim().equals("0")){
           JOptionPane.showMessageDialog(null, "Penjualan Umum Masih Kosong ...");
        }else if(Sequel.cariInteger("SELECT mcp.tgl_closing,mcp.real  FROM md_closing_pu mcp WHERE mcp.tgl_closing ='"+Valid.SetTgl(DTPTanggal.getSelectedItem()+"")+"' ")>0){
           JOptionPane.showMessageDialog(null, "Anda sudah Closing Hari ini..");
        }else{
            if(Sequel.menyimpantf("md_closing_pu","?,?,?,?,?","closing",5,new String[]{
                Valid.SetTgl(DTPTanggal.getSelectedItem()+""),KdPeg.getText(),TPendapatan.getText(),TPendapatanResep.getText(),Shif.getSelectedItem().toString()
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
            Valid.pindah(evt,TPendapatan,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabmodeclosing.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            BtnCari.requestFocus();
        }else if(TPendapatan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.Klik data pada table untuk memilih...!!!!");
        }else{
            Sequel.queryu("delete from md_closing_pu where tgl_closing='"+tbclosing.getValueAt(tbclosing.getSelectedRow(),0).toString()+"' and shif_jaga='"+tbclosing.getValueAt(tbclosing.getSelectedRow(),5).toString()+"' ");
            tampil();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal,BtnPrint);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TPendapatanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPendapatanMouseMoved
         if (TPendapatan.getText().equals("0") || TPendapatan.getText().equals("0.0")) {
            TPendapatan.setText("");
        }
    }//GEN-LAST:event_TPendapatanMouseMoved

    private void TPendapatanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPendapatanMouseExited
       if (TPendapatan.getText().equals("")) {
            TPendapatan.setText("0");
        }
    }//GEN-LAST:event_TPendapatanMouseExited

    private void TPendapatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPendapatanKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            setData();        
        }
    }//GEN-LAST:event_TPendapatanKeyPressed

    private void ttlpuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttlpuMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_ttlpuMouseMoved

    private void ttlpuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttlpuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ttlpuMouseExited

    private void ttlpuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttlpuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttlpuKeyPressed

    private void tbclosingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclosingMouseClicked
        if(tabmodeclosing.getRowCount()!=0){
            try {
                getdata();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbclosingMouseClicked

    private void TPendapatanResepMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPendapatanResepMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_TPendapatanResepMouseMoved

    private void TPendapatanResepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPendapatanResepMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TPendapatanResepMouseExited

    private void TPendapatanResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPendapatanResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPendapatanResepKeyPressed

    private void ShifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShifItemStateChanged

    }//GEN-LAST:event_ShifItemStateChanged

    private void ShifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShifKeyPressed

    }//GEN-LAST:event_ShifKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgOmsetPenerimaan dialog = new DlgOmsetPenerimaan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeekPegawai;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTanggal;
    private widget.TextBox KdPeg;
    private javax.swing.JLabel LCountDeposit;
    private javax.swing.JLabel LCountJualBebas;
    private javax.swing.JLabel LCountPemasukanLain;
    private javax.swing.JLabel LCountPiutangDibayar;
    private javax.swing.JLabel LCountRawatInap;
    private javax.swing.JLabel LCountRawatJalan;
    private javax.swing.JLabel LCountTotal;
    private widget.Label Lselisih;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox Shif;
    private widget.TextBox TPegawai;
    private widget.TextBox TPendapatan;
    private widget.TextBox TPendapatanResep;
    private javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel26;
    private widget.Label jLabel37;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private javax.swing.JPanel jPanel3;
    private widget.Label label12;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDeposit;
    private widget.Table tbPemasukanLain;
    private widget.Table tbPenjualanBebas;
    private widget.Table tbPiutangDibayar;
    private widget.Table tbRawatInap;
    private widget.Table tbRawatJalan;
    private widget.Table tbclosing;
    private widget.TextBox ttlpu;
    // End of variables declaration//GEN-END:variables

    public void tampil(){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        try{    
            Valid.tabelKosong(tabMode);
            rawatjalan=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(nota_jalan.tanggal,'%d-%m-%Y'),nota_jalan.no_nota,nota_jalan.no_rawat,reg_periksa.no_rkm_medis,"+
                    "pasien.nm_pasien,detail_nota_jalan.nama_bayar,detail_nota_jalan.besar_bayar "+
                    "from nota_jalan inner join reg_periksa on nota_jalan.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                    "inner join detail_nota_jalan on detail_nota_jalan.no_rawat=reg_periksa.no_rawat "+
                    "where nota_jalan.tanggal between ? and ? order by nota_jalan.tanggal,nota_jalan.jam ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)
                    });
                    rawatjalan=rawatjalan+rs.getDouble(7);
                }
            } catch (Exception e) {
                System.out.println("Notif Rawat Jalan : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LCountRawatJalan.setText(Valid.SetAngka(rawatjalan));
            
            Valid.tabelKosong(tabMode2);
            rawatinap=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(nota_inap.tanggal,'%d-%m-%Y'),nota_inap.no_nota,nota_inap.no_rawat,reg_periksa.no_rkm_medis,"+
                    "pasien.nm_pasien,detail_nota_inap.nama_bayar,detail_nota_inap.besar_bayar "+
                    "from nota_inap inner join reg_periksa on nota_inap.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                    "inner join detail_nota_inap on detail_nota_inap.no_rawat=reg_periksa.no_rawat "+
                    "where nota_inap.tanggal between ? and ? order by nota_inap.tanggal,nota_inap.jam ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode2.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)
                    });
                    rawatinap=rawatinap+rs.getDouble(7);
                }
            } catch (Exception e) {
                System.out.println("Notif Rawat Inap : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LCountRawatInap.setText(Valid.SetAngka(rawatinap));
            
            Valid.tabelKosong(tabMode3);
            jualbebas=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(penjualan.tgl_jual,'%d-%m-%Y'),penjualan.nota_jual,penjualan.jns_jual,penjualan.no_rkm_medis,"+
                    "penjualan.nm_pasien,penjualan.nama_bayar,(penjualan.ongkir+penjualan.ppn+sum(detailjual.total)) "+
                    "from penjualan inner join detailjual on detailjual.nota_jual=penjualan.nota_jual "+
                    "where penjualan.tgl_jual between ? and ? "+
                    "group by penjualan.nota_jual order by penjualan.tgl_jual,penjualan.nota_jual ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode3.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Math.round(rs.getDouble(7))
                    });
                    jualbebas=jualbebas+rs.getDouble(7);
                }
            } catch (Exception e) {
                System.out.println("Notif Jual Bebas : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LCountJualBebas.setText(Valid.SetAngka(jualbebas));
            ttlpu.setText(df7.format(jualbebas));
            
            Valid.tabelKosong(tabMode4);
            pemasukanlain=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(pemasukan_lain.tanggal,'%d-%m-%Y'),pemasukan_lain.no_masuk,pemasukan_lain.keterangan,"+
                    "pemasukan_lain.keperluan,kategori_pemasukan_lain.nama_kategori,pemasukan_lain.besar "+
                    "from pemasukan_lain inner join kategori_pemasukan_lain on pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori "+
                    "where pemasukan_lain.tanggal between ? and ? order by pemasukan_lain.tanggal ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+" 23:59:59");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode4.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)
                    });
                    pemasukanlain=pemasukanlain+rs.getDouble(6);
                }
            } catch (Exception e) {
                System.out.println("Notif Pemasukan Lain : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LCountPemasukanLain.setText(Valid.SetAngka(pemasukanlain));
            
            Valid.tabelKosong(tabMode5);
            deposit=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(deposit.tgl_deposit,'%d-%m-%Y'),deposit.no_deposit,deposit.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,deposit.nama_bayar, "+
                    "deposit.besar_deposit from deposit inner join reg_periksa on deposit.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where deposit.tgl_deposit between ? and ? order by deposit.tgl_deposit ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+" 23:59:59");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode5.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)
                    });
                    deposit=deposit+rs.getDouble(7);
                }
            } catch (Exception e) {
                System.out.println("Notif Deposit : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LCountDeposit.setText(Valid.SetAngka(deposit));
            
            Valid.tabelKosong(tabMode6);
            bayarpiutang=0;
            ps=koneksi.prepareStatement(
                    "select DATE_FORMAT(bayar_piutang.tgl_bayar,'%d-%m-%Y'),bayar_piutang.no_rawat,bayar_piutang.no_rkm_medis,pasien.nm_pasien,"+
                    "rekening.nm_rek,rekening2.nm_rek,bayar_piutang.besar_cicilan "+
                    "from bayar_piutang inner join pasien on bayar_piutang.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join rekening on rekening.kd_rek=bayar_piutang.kd_rek "+
                    "inner join rekening as rekening2 on rekening2.kd_rek=bayar_piutang.kd_rek_kontra "+
                    "where bayar_piutang.tgl_bayar between ? and ? order by bayar_piutang.tgl_bayar ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+"")+" 23:59:59");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode6.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)
                    });
                    bayarpiutang=bayarpiutang+rs.getDouble(7);
                }
            } catch (Exception e) {
                System.out.println("Notif Deposit : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
            Valid.tabelKosong(tabmodeclosing);
            ps1=koneksi.prepareStatement(
                    "SELECT mcp.tgl_closing,mcp.real,p.nama,mcp.nip,mcp.real_resep,mcp.shif_jaga FROM md_closing_pu mcp inner join petugas p  on mcp.nip=p.nip WHERE mcp.tgl_closing BETWEEN ? and ? ");
            try {
                ps1.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem().toString()+""));
                ps1.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem().toString()+""));
                rs1=ps1.executeQuery();
                ttlreal=0;ttlresep=0;
                rs1.beforeFirst();
                while(rs1.next()){
                    tabmodeclosing.addRow(new Object[]{
                        rs1.getString(1),rs1.getDouble(2),rs1.getString(3),rs1.getString(4),rs1.getDouble(5),rs1.getString(6)
                    });
                     ttlresep=ttlreal+rs1.getDouble(5);
                     ttlreal=ttlreal+rs1.getDouble(2);
                }
              
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs1!=null){
                    rs1.close();
                }
                if(ps1!=null){
                    ps1.close();
                }
            }
            tabmodeclosing.addRow(new Object[]{"Total >>",ttlreal,"","",ttlresep,"<<"});
            
        LCountTotal.setText(Valid.SetAngka(rawatjalan+rawatinap+jualbebas+pemasukanlain+deposit+bayarpiutang));
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void  emptTeks(){
    TPendapatan.setText("");
    Lselisih.setText("");
    ttlpu.setText("");
    TPendapatanResep.setText("0");
    if(akses.getkode().equals("Admin Utama")){
    KdPeg.setText("");
    TPegawai.setText("");
    BtnSeekPegawai.setEnabled(true);
    }else{
    KdPeg.setText(akses.getkode());
    Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", TPegawai,KdPeg.getText());
    BtnSeekPegawai.setEnabled(false);
    }
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,150));
            panelGlass12.setVisible(true);      
            ChkInput.setVisible(true);
             if(akses.getkode().equals("Admin Utama")){
                KdPeg.setText("");
                TPegawai.setText("");
                BtnSeekPegawai.setEnabled(true);
                }else{
                KdPeg.setText(akses.getkode());
                Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", TPegawai,KdPeg.getText());
                BtnSeekPegawai.setEnabled(false);
                }
            }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass12.setVisible(false);      
            ChkInput.setVisible(true);
             if(akses.getkode().equals("Admin Utama")){
                KdPeg.setText("");
                TPegawai.setText("");
                BtnSeekPegawai.setEnabled(true);
                }else{
                KdPeg.setText(akses.getkode());
                Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", TPegawai,KdPeg.getText());
                BtnSeekPegawai.setEnabled(false);
                }
        }
    }
    
    private void setData(){
        Lselisih.setText(df7.format(Valid.SetAngka(TPendapatan.getText()) - (Valid.SetAngka(ttlpu.getText()))));
    }
    
    private void getdata (){
        int row=tbclosing.getSelectedRow();
        if(row!= -1){
            Valid.SetTgl(DTPTanggal,tbclosing.getValueAt(row,0).toString());
            TPendapatan.setText(tbclosing.getValueAt(row,1).toString());
            Lselisih.setText(tbclosing.getValueAt(row,3).toString());
            KdPeg.setText(tbclosing.getValueAt(row,5).toString());
            TPegawai.setText(tbclosing.getValueAt(row,4).toString());
        }
    }
       
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgObatPenyakit.java
 *
 * Created on May 23, 2010, 12:40:35 AM
 */

package modifikasi;


import fungsi.WarnaTable;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;


/**
 *
 * @author dosen
 */
public final class DlgOftamonologi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2,tabMode3;
    private sekuel Sequel=new sekuel();
    private PreparedStatement ps;
    private ResultSet rs;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
     private DlgCariPegawai petugas=new DlgCariPegawai(null,false);

    /** Creates new form DlgObatPenyakit
     * @param parent
     * @param modal */
    public DlgOftamonologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);
 
        tabMode=new DefaultTableModel(null,new String[]{
            "No.Rawat","No. RM","Nama Pasien","Tanggal","Jam","Numenerator Mata Kanan","Numenerator Mata Kiri","Denumerator Mata Kanan","Denumerator Mata Kiri","Petugas","Kode Petugas"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        Table1.setModel(tabMode);

        //tbObatPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObatPenyakit.getBackground()));
        Table1.setPreferredScrollableViewportSize(new Dimension(800,800));
        Table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

         for (int i = 0; i < 11; i++) {
            TableColumn column = Table1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(200);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(120);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        Table1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{
           "No.Rawat","No. RM","Nama Pasien","Tanggal","Jam","High/Low Mata Kanan","Nilai mmHg Mata Kanan","High/Low Mata Kiri","Nilai mmHg Mata Kiri","Petugas","Kode Petugas"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        Table2.setModel(tabMode2);

        //tbObatPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObatPenyakit.getBackground()));
        Table2.setPreferredScrollableViewportSize(new Dimension(800,800));
        Table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

         for (int i = 0; i < 11; i++) {
            TableColumn column = Table2.getColumnModel().getColumn(i);
            if(i==0){
                 column.setPreferredWidth(100);
             }else if(i==1){
                 column.setPreferredWidth(80);
             }else if(i==2){
                 column.setPreferredWidth(150);
             }else if(i==3){
                 column.setPreferredWidth(80);
             }else if(i==4){
                 column.setPreferredWidth(80);
             }else if(i==5){
                 column.setPreferredWidth(200);
             }else if(i==6){
                 column.setPreferredWidth(200);
             }else if(i==7){
                 column.setPreferredWidth(200);
             }else if(i==8){
                 column.setPreferredWidth(200);
             }else if(i==9){
                 column.setPreferredWidth(120);
             }else if(i==10){
                 column.setMinWidth(0);
                 column.setMaxWidth(0);
             }
        }
        Table2.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null,new String[]{
           "No.Rawat","No. RM","Nama Pasien","Tanggal","Jam","Keadaan Mata Kanan","Optik Mata Kanan","Retina Mata Kanan","Makula Mata Kanan","Pembuluh Darah Mata Kanan",
            "Keadaan Mata Kiri","Optik Mata Kiri","Retina Mata Kiri","Makula Mata Kiri","Pembuluh Darah Mata Kiri","Petugas","Kode Petugas"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        Table3.setModel(tabMode3);

        //tbObatPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObatPenyakit.getBackground()));
        Table3.setPreferredScrollableViewportSize(new Dimension(800,800));
        Table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

         for (int i = 0; i < 17; i++) {
            TableColumn column = Table3.getColumnModel().getColumn(i);
            if(i==0){
                 column.setPreferredWidth(100);
             }else if(i==1){
                 column.setPreferredWidth(80);
             }else if(i==2){
                 column.setPreferredWidth(150);
             }else if(i==3){
                 column.setPreferredWidth(80);
             }else if(i==4){
                 column.setPreferredWidth(80);
             }else if(i==5){
                 column.setPreferredWidth(200);
             }else if(i==6){
                 column.setPreferredWidth(200);
             }else if(i==7){
                 column.setPreferredWidth(200);
             }else if(i==8){
                 column.setPreferredWidth(200);
             }else if(i==9){
                 column.setPreferredWidth(200);
             }else if(i==10){
                 column.setPreferredWidth(200);
             }else if(i==11){
                 column.setPreferredWidth(200);
             }else if(i==12){
                 column.setPreferredWidth(200);
             }else if(i==13){
                 column.setPreferredWidth(200);
             }else if(i==14){
                 column.setPreferredWidth(200);
             }else if(i==15){
                 column.setPreferredWidth(120);
             }else if(i==16){
                 column.setMinWidth(0);
                 column.setMaxWidth(0);
             }
        }
        Table3.setDefaultRenderer(Object.class, new WarnaTable());
        

        DenomeratorKananFundus.setDocument(new batasInput((byte)20).getOnlyAngka(DenomeratorKananFundus));
        NumeratorKananFundus.setDocument(new batasInput((byte)20).getOnlyAngka(NumeratorKananFundus));
        DenomeratorKiriFundus.setDocument(new batasInput((byte)20).getOnlyAngka(DenomeratorKiriFundus));
        NumeratorKiriFundus.setDocument(new batasInput((byte)20).getOnlyAngka(NumeratorKiriFundus));
        TenometriKiri.setDocument(new batasInput((byte)20).getOnlyAngka(TenometriKiri));
        TenometriKanan.setDocument(new batasInput((byte)20).getOnlyAngka(TenometriKanan));
        PenilaianMataKanan.setDocument(new batasInput((byte)100).getKata(PenilaianMataKanan));
        OptikKanan.setDocument(new batasInput((byte)100).getKata(OptikKanan));
        RetinaKanan.setDocument(new batasInput((byte)100).getKata(RetinaKanan));
        MakulaKanan.setDocument(new batasInput((byte)100).getKata(MakulaKanan));
        PembuluhKanan.setDocument(new batasInput((byte)100).getKata(PembuluhKanan));
        PenilaianMataKiri.setDocument(new batasInput((byte)100).getKata(PenilaianMataKiri));
        OptikKiri.setDocument(new batasInput((byte)100).getKata(OptikKiri));
        RetinaKiri.setDocument(new batasInput((byte)100).getKata(RetinaKiri));
        MakulaKiri.setDocument(new batasInput((byte)100).getKata(MakulaKiri));
        PembuluhKiri.setDocument(new batasInput((byte)100).getKata(PembuluhKiri));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
         if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilvisus();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                         if(TabRawat.getSelectedIndex()==0){
                            tampilvisus();
                         } else  if(TabRawat.getSelectedIndex()==1){
                            tampiltenometri();
                         } else if(TabRawat.getSelectedIndex()==0){
                             tampilfundus();
                         }
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampilvisus();
                    }
                }
            });
        }
         jam();
        ChkJln.setSelected(true);                       
        
        TabRawat.setSelectedIndex(0);

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(petugas.getTable().getSelectedRow()!= -1){
                        if(petugas.getTable().getSelectedRow()!= -1){ 
                        NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());   
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
        
        petugas.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    petugas.dispose();
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel28 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        Table1 = new widget.Table();
        panelGlass2 = new widget.panelisi();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        NumeratorKananFundus = new widget.TextBox();
        DenomeratorKananFundus = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        NumeratorKiriFundus = new widget.TextBox();
        DenomeratorKiriFundus = new widget.TextBox();
        jLabel16 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        Table2 = new widget.Table();
        panelGlass3 = new widget.panelisi();
        jLabel17 = new widget.Label();
        TenometriKanan = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        cmbTenometriKanan = new widget.ComboBox();
        cmbTenometriKiri = new widget.ComboBox();
        TenometriKiri = new widget.TextBox();
        internalFrame4 = new widget.InternalFrame();
        panelGlass4 = new widget.panelisi();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        PenilaianMataKanan = new widget.TextBox();
        OptikKanan = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel30 = new widget.Label();
        RetinaKanan = new widget.TextBox();
        jLabel31 = new widget.Label();
        MakulaKanan = new widget.TextBox();
        jLabel32 = new widget.Label();
        PembuluhKanan = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        PembuluhKiri = new widget.TextBox();
        MakulaKiri = new widget.TextBox();
        RetinaKiri = new widget.TextBox();
        OptikKiri = new widget.TextBox();
        PenilaianMataKiri = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        Table3 = new widget.Table();
        FormInput = new widget.PanelBiasa();
        jLabel13 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        NIP = new widget.TextBox();
        jLabel19 = new widget.Label();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Status Oftomologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel28.setText("Periode :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel28);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("s.d.");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel23);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
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
        panelGlass9.add(BtnAll);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        Table1.setAutoCreateRowSorter(true);
        Table1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Table1.setName("Table1"); // NOI18N
        Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked(evt);
            }
        });
        Table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table1KeyPressed(evt);
            }
        });
        Scroll.setViewportView(Table1);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass2.setName("panelGlass2"); // NOI18N
        panelGlass2.setPreferredSize(new java.awt.Dimension(711, 107));
        panelGlass2.setLayout(null);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mata Kanan");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass2.add(jLabel3);
        jLabel3.setBounds(70, 10, 170, 23);

        jLabel4.setText("Nilai Denomerator :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass2.add(jLabel4);
        jLabel4.setBounds(20, 70, 120, 23);

        NumeratorKananFundus.setHighlighter(null);
        NumeratorKananFundus.setName("NumeratorKananFundus"); // NOI18N
        NumeratorKananFundus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NumeratorKananFundusKeyPressed(evt);
            }
        });
        panelGlass2.add(NumeratorKananFundus);
        NumeratorKananFundus.setBounds(140, 40, 100, 23);

        DenomeratorKananFundus.setHighlighter(null);
        DenomeratorKananFundus.setName("DenomeratorKananFundus"); // NOI18N
        DenomeratorKananFundus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DenomeratorKananFundusKeyPressed(evt);
            }
        });
        panelGlass2.add(DenomeratorKananFundus);
        DenomeratorKananFundus.setBounds(140, 70, 100, 23);

        jLabel5.setText("Nilai Numerator :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass2.add(jLabel5);
        jLabel5.setBounds(20, 40, 120, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Mata Kiri");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass2.add(jLabel14);
        jLabel14.setBounds(440, 10, 170, 23);

        jLabel15.setText("Nilai Numerator :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass2.add(jLabel15);
        jLabel15.setBounds(390, 40, 120, 23);

        NumeratorKiriFundus.setHighlighter(null);
        NumeratorKiriFundus.setName("NumeratorKiriFundus"); // NOI18N
        NumeratorKiriFundus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NumeratorKiriFundusKeyPressed(evt);
            }
        });
        panelGlass2.add(NumeratorKiriFundus);
        NumeratorKiriFundus.setBounds(510, 40, 100, 23);

        DenomeratorKiriFundus.setHighlighter(null);
        DenomeratorKiriFundus.setName("DenomeratorKiriFundus"); // NOI18N
        DenomeratorKiriFundus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DenomeratorKiriFundusKeyPressed(evt);
            }
        });
        panelGlass2.add(DenomeratorKiriFundus);
        DenomeratorKiriFundus.setBounds(510, 70, 100, 23);

        jLabel16.setText("Nilai Denomerator :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass2.add(jLabel16);
        jLabel16.setBounds(390, 70, 120, 23);

        internalFrame2.add(panelGlass2, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemriksaan Visus", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        Table2.setAutoCreateRowSorter(true);
        Table2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Table2.setName("Table2"); // NOI18N
        Table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table2MouseClicked(evt);
            }
        });
        Table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table2KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(Table2);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass3.setName("panelGlass3"); // NOI18N
        panelGlass3.setPreferredSize(new java.awt.Dimension(711, 107));
        panelGlass3.setLayout(null);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Mata Kanan");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass3.add(jLabel17);
        jLabel17.setBounds(70, 10, 170, 23);

        TenometriKanan.setHighlighter(null);
        TenometriKanan.setName("TenometriKanan"); // NOI18N
        TenometriKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenometriKananKeyPressed(evt);
            }
        });
        panelGlass3.add(TenometriKanan);
        TenometriKanan.setBounds(220, 40, 60, 23);

        jLabel20.setText("Nilai mmHg :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass3.add(jLabel20);
        jLabel20.setBounds(20, 40, 120, 23);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Mata Kiri");
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass3.add(jLabel21);
        jLabel21.setBounds(440, 10, 170, 23);

        jLabel22.setText("Nilai mmHg :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass3.add(jLabel22);
        jLabel22.setBounds(390, 40, 120, 23);

        cmbTenometriKanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "High" }));
        cmbTenometriKanan.setName("cmbTenometriKanan"); // NOI18N
        cmbTenometriKanan.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbTenometriKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTenometriKananKeyPressed(evt);
            }
        });
        panelGlass3.add(cmbTenometriKanan);
        cmbTenometriKanan.setBounds(140, 40, 70, 23);

        cmbTenometriKiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "High" }));
        cmbTenometriKiri.setName("cmbTenometriKiri"); // NOI18N
        cmbTenometriKiri.setPreferredSize(new java.awt.Dimension(62, 28));
        cmbTenometriKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTenometriKiriKeyPressed(evt);
            }
        });
        panelGlass3.add(cmbTenometriKiri);
        cmbTenometriKiri.setBounds(510, 40, 70, 23);

        TenometriKiri.setHighlighter(null);
        TenometriKiri.setName("TenometriKiri"); // NOI18N
        TenometriKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenometriKiriKeyPressed(evt);
            }
        });
        panelGlass3.add(TenometriKiri);
        TenometriKiri.setBounds(590, 40, 60, 23);

        internalFrame3.add(panelGlass3, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemeriksaan Tenometri", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass4.setName("panelGlass4"); // NOI18N
        panelGlass4.setPreferredSize(new java.awt.Dimension(711, 207));
        panelGlass4.setLayout(null);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Mata Kanan");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass4.add(jLabel24);
        jLabel24.setBounds(70, 10, 170, 23);

        jLabel25.setText("Penilaian Optik Mata :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass4.add(jLabel25);
        jLabel25.setBounds(20, 70, 120, 23);

        PenilaianMataKanan.setHighlighter(null);
        PenilaianMataKanan.setName("PenilaianMataKanan"); // NOI18N
        PenilaianMataKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenilaianMataKananKeyPressed(evt);
            }
        });
        panelGlass4.add(PenilaianMataKanan);
        PenilaianMataKanan.setBounds(160, 40, 410, 23);

        OptikKanan.setHighlighter(null);
        OptikKanan.setName("OptikKanan"); // NOI18N
        OptikKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OptikKananKeyPressed(evt);
            }
        });
        panelGlass4.add(OptikKanan);
        OptikKanan.setBounds(160, 70, 410, 23);

        jLabel26.setText("Penilaian Keadaan Mata :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass4.add(jLabel26);
        jLabel26.setBounds(10, 40, 130, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Mata Kiri");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass4.add(jLabel27);
        jLabel27.setBounds(860, 10, 170, 23);

        jLabel30.setText("Penilaian Retina Mata :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass4.add(jLabel30);
        jLabel30.setBounds(20, 100, 120, 23);

        RetinaKanan.setHighlighter(null);
        RetinaKanan.setName("RetinaKanan"); // NOI18N
        RetinaKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RetinaKananKeyPressed(evt);
            }
        });
        panelGlass4.add(RetinaKanan);
        RetinaKanan.setBounds(160, 100, 410, 23);

        jLabel31.setText("Penilaian Makula Mata :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass4.add(jLabel31);
        jLabel31.setBounds(20, 130, 120, 23);

        MakulaKanan.setHighlighter(null);
        MakulaKanan.setName("MakulaKanan"); // NOI18N
        MakulaKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MakulaKananKeyPressed(evt);
            }
        });
        panelGlass4.add(MakulaKanan);
        MakulaKanan.setBounds(160, 130, 410, 23);

        jLabel32.setText("Penilaian Pembuluh Mata :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass4.add(jLabel32);
        jLabel32.setBounds(10, 160, 130, 23);

        PembuluhKanan.setHighlighter(null);
        PembuluhKanan.setName("PembuluhKanan"); // NOI18N
        PembuluhKanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PembuluhKananKeyPressed(evt);
            }
        });
        panelGlass4.add(PembuluhKanan);
        PembuluhKanan.setBounds(160, 160, 410, 23);

        jLabel33.setText("Penilaian Keadaan Mata :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass4.add(jLabel33);
        jLabel33.setBounds(630, 40, 130, 23);

        jLabel34.setText("Penilaian Optik Mata :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass4.add(jLabel34);
        jLabel34.setBounds(640, 70, 120, 23);

        jLabel35.setText("Penilaian Retina Mata :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass4.add(jLabel35);
        jLabel35.setBounds(640, 100, 120, 23);

        jLabel36.setText("Penilaian Makula Mata :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass4.add(jLabel36);
        jLabel36.setBounds(640, 130, 120, 23);

        jLabel37.setText("Penilaian Pembuluh Mata :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass4.add(jLabel37);
        jLabel37.setBounds(630, 160, 130, 23);

        PembuluhKiri.setHighlighter(null);
        PembuluhKiri.setName("PembuluhKiri"); // NOI18N
        PembuluhKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PembuluhKiriKeyPressed(evt);
            }
        });
        panelGlass4.add(PembuluhKiri);
        PembuluhKiri.setBounds(780, 160, 410, 23);

        MakulaKiri.setHighlighter(null);
        MakulaKiri.setName("MakulaKiri"); // NOI18N
        MakulaKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MakulaKiriKeyPressed(evt);
            }
        });
        panelGlass4.add(MakulaKiri);
        MakulaKiri.setBounds(780, 130, 410, 23);

        RetinaKiri.setHighlighter(null);
        RetinaKiri.setName("RetinaKiri"); // NOI18N
        RetinaKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RetinaKiriKeyPressed(evt);
            }
        });
        panelGlass4.add(RetinaKiri);
        RetinaKiri.setBounds(780, 100, 410, 23);

        OptikKiri.setHighlighter(null);
        OptikKiri.setName("OptikKiri"); // NOI18N
        OptikKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OptikKiriKeyPressed(evt);
            }
        });
        panelGlass4.add(OptikKiri);
        OptikKiri.setBounds(780, 70, 410, 23);

        PenilaianMataKiri.setHighlighter(null);
        PenilaianMataKiri.setName("PenilaianMataKiri"); // NOI18N
        PenilaianMataKiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenilaianMataKiriKeyPressed(evt);
            }
        });
        panelGlass4.add(PenilaianMataKiri);
        PenilaianMataKiri.setBounds(780, 40, 410, 23);

        internalFrame4.add(panelGlass4, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        Table3.setAutoCreateRowSorter(true);
        Table3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Table3.setName("Table3"); // NOI18N
        Table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table3MouseClicked(evt);
            }
        });
        Table3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table3KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(Table3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pemeriksaan Fundus Reflex", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 70));
        FormInput.setLayout(null);

        jLabel13.setText("No.Rawat :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(null);
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TNoRwMouseClicked(evt);
            }
        });
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 125, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(201, 10, 80, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(283, 10, 210, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-12-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(560, 10, 90, 23);

        jLabel18.setText("Tanggal :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(500, 10, 60, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(660, 10, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(720, 10, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(790, 10, 62, 23);

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
        ChkJln.setBounds(850, 10, 23, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(90, 40, 94, 23);

        jLabel19.setText("Petugas :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(10, 40, 70, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(190, 40, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(380, 40, 28, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                Valid.textKosong(TNoRw,"No.Rawat dan Petugas");
            }else if(DenomeratorKananFundus.getText().trim().equals("")||NumeratorKananFundus.getText().trim().equals("")){
                Valid.textKosong(NumeratorKananFundus,"Nume dan Denome Mata Kanan");
            }else if(NumeratorKiriFundus.getText().trim().equals("")||DenomeratorKiriFundus.getText().trim().equals("")){
                Valid.textKosong(NumeratorKiriFundus,"Nume dan Denome Mata Kanan");
            }else{
                if(Sequel.menyimpantf(
                        "pemeriksaan_visus","'"+TNoRw.getText()+"','"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"','"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"','"+NumeratorKananFundus.getText()+"','"+DenomeratorKananFundus.getText()+"','"+NumeratorKiriFundus.getText()+"','"+DenomeratorKiriFundus.getText()+"','"+NIP.getText()+"'",
                        "Pemeriksaan Visus")==true){
                    tampilvisus();
                    emptTeksvisus();
                }                
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                Valid.textKosong(TNoRw,"No.Rawat dan Petugas");
            }else if(TenometriKanan.getText().trim().equals("")){
                Valid.textKosong(TenometriKanan,"Nilai Tenometri Mata Kanan");
            }else if(TenometriKiri.getText().trim().equals("")){
                Valid.textKosong(TenometriKiri,"ilai Tenometri Mata Kiri");
            }else{
                if(Sequel.menyimpantf(
                        "pemeriksaan_tenometri","'"+TNoRw.getText()+"','"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"','"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"','"+cmbTenometriKanan.getSelectedItem()+"','"+TenometriKanan.getText()+"','"+cmbTenometriKiri.getSelectedItem()+"','"+TenometriKiri.getText()+"','"+NIP.getText()+"'","Pemeriksaan Tenometri")==true){
                    tampiltenometri();
                    emptTekstenometri();
                }                
            }
        }else if(TabRawat.getSelectedIndex()==2){
            if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                Valid.textKosong(TNoRw,"No.Rawat dan Petugas");
            }else if(PenilaianMataKanan.getText().trim().equals("")||OptikKanan.getText().trim().equals("")||RetinaKanan.getText().trim().equals("")||MakulaKanan.getText().trim().equals("")||PembuluhKanan.getText().trim().equals("")){
                Valid.textKosong(PenilaianMataKanan,"Penilaian dan lainnya mata kanan");
            }else if(PenilaianMataKiri.getText().trim().equals("")||OptikKiri.getText().trim().equals("")||RetinaKiri.getText().trim().equals("")||MakulaKiri.getText().trim().equals("")||PembuluhKiri.getText().trim().equals("")){
                Valid.textKosong(PenilaianMataKiri,"Penilaian dan lainnya mata kanan");
            }else{
                if(Sequel.menyimpantf(
                        "pemeriksaan_fundus","'"+TNoRw.getText()+"','"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"','"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"','"+PenilaianMataKanan.getText()+"','"+OptikKanan.getText()+"','"+RetinaKanan.getText()+"','"+MakulaKanan.getText()+"','"+PembuluhKanan.getText()+"',"
                                + "'"+PenilaianMataKiri.getText()+"','"+OptikKiri.getText()+"','"+RetinaKiri.getText()+"','"+MakulaKiri.getText()+"','"+PembuluhKiri.getText()+"','"+NIP.getText()+"' "  ,"Tindakan")==true){
                    tampilfundus();
                    emptTeksfundus();
                }                
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            if(TabRawat.getSelectedIndex()==0){
                Valid.pindah(evt,DenomeratorKiriFundus,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==1){
                Valid.pindah(evt,TenometriKiri,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==2){
                Valid.pindah(evt,PembuluhKiri,BtnBatal);
            }                
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            emptTeksvisus();
        }else if(TabRawat.getSelectedIndex()==1){
            emptTekstenometri();
        }else if(TabRawat.getSelectedIndex()==2){
            emptTeksfundus();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeksvisus();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            if(tabMode.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                NumeratorKananFundus.requestFocus();
            }else if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
            }else{
                Valid.hapusTable(tabMode,TNoRw,"pemeriksaan_visus","tgl_perawatan='"+Table1.getValueAt(Table1.getSelectedRow(),3).toString()+"' and jam_rawat='"+Table1.getValueAt(Table1.getSelectedRow(),4).toString()+"' and numerator_kanan='"+NumeratorKananFundus.getText()+"' and numerator_kiri='"+NumeratorKiriFundus.getText()+"' and nip='"+NIP.getText()+"' and no_rawat ");
                tampilvisus();
                emptTeksvisus();
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if(tabMode2.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                TenometriKanan.requestFocus();
            }else if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
            }else {
               Valid.hapusTable(tabMode2,TNoRw,"pemeriksaan_tenometri","tgl_perawatan='"+Table2.getValueAt(Table2.getSelectedRow(),3).toString()+"' and jam_rawat='"+Table2.getValueAt(Table2.getSelectedRow(),4).toString()+"' and nilai_kanan='"+TenometriKanan.getText()+"' and nilai_kiri='"+TenometriKiri.getText()+"' and nip='"+NIP.getText()+"' and no_rawat ");
                tampiltenometri();
                emptTekstenometri();
            }
        }else if(TabRawat.getSelectedIndex()==2){
            if(tabMode3.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                PenilaianMataKanan.requestFocus();
            }else if(TNoRw.getText().trim().equals("")||NIP.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
            }else {
                Valid.hapusTable(tabMode3,TNoRw,"pemeriksaan_fundus","tgl_perawatan='"+Table3.getValueAt(Table3.getSelectedRow(),3).toString()+"' and jam_rawat='"+Table3.getValueAt(Table3.getSelectedRow(),4).toString()+"' and keadaan_kanan='"+PenilaianMataKanan.getText()+"' and keadaan_kiri='"+PenilaianMataKiri.getText()+"' and optik_kanan='"+OptikKanan.getText()+"' and optik_kiri='"+OptikKiri.getText()+"' and nip='"+NIP.getText()+"' and no_rawat ");
                tampilfundus();
                emptTeksfundus();
            }
        }
            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed

            JOptionPane.showMessageDialog(null,"Maaf, Fitur ini belum bisa digunakan");
         
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnAll,BtnSimpan);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

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
        TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table1MouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getDatavisus();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_Table1MouseClicked

    private void Table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDatavisus();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_Table1KeyPressed

private void NumeratorKananFundusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeratorKananFundusKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//             nmdokter.setText(dokter.tampil3(NumeratorKananFundus.getText()));
//        }else{
//             Valid.pindah(evt,TCari,DenomeratorKananFundus);
//        }
}//GEN-LAST:event_NumeratorKananFundusKeyPressed

private void DenomeratorKananFundusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DenomeratorKananFundusKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//             Valid.pindah(evt,NumeratorKananFundus,kdpj);
//        }else{
//             Valid.pindah(evt,NumeratorKananFundus,kdpj);
//        }
}//GEN-LAST:event_DenomeratorKananFundusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilfundus();
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampilvisus();
        }else if(TabRawat.getSelectedIndex()==1){
            tampiltenometri();
        }else if(TabRawat.getSelectedIndex()==2){
            tampilfundus();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        if(tabMode2.getRowCount()!=0){
            try {
                getDatatenometri();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_Table2MouseClicked

    private void Table2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyPressed
        if(tabMode2.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDatatenometri();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_Table2KeyPressed

    private void Table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table3MouseClicked
        if(tabMode3.getRowCount()!=0){
            try {
                getDatafundus();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_Table3MouseClicked

    private void Table3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table3KeyPressed
        if(tabMode3.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDatafundus();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_Table3KeyPressed

    private void TNoRwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TNoRwMouseClicked
//        Window[] wins = Window.getWindows();
//        for (Window win : wins) {
//            if (win instanceof JDialog) {
//                win.setLocationRelativeTo(internalFrame1);
//                win.toFront();
//            }
//        }
    }//GEN-LAST:event_TNoRwMouseClicked

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            isRawat();
//            isPsien();
//        }else{
//            if(TabRawat.getSelectedIndex()==0){
//                Valid.pindah(evt,DTPTgl,KdDok);
//            }else if(TabRawat.getSelectedIndex()==1){
//                Valid.pindah(evt,DTPTgl,kdptg);
//            }else if(TabRawat.getSelectedIndex()==2){
//                Valid.pindah(evt,DTPTgl,KdDok2);
//            }else if(TabRawat.getSelectedIndex()==3){
//                Valid.pindah(evt,DTPTgl,KdPeg);
//            }else if(TabRawat.getSelectedIndex()==4){
//                Valid.pindah(evt,DTPTgl,TTinggi_uteri);
//            }else if(TabRawat.getSelectedIndex()==5){
//                Valid.pindah(evt,DTPTgl,TInspeksi);
//            }
//        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void NumeratorKiriFundusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeratorKiriFundusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeratorKiriFundusKeyPressed

    private void DenomeratorKiriFundusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DenomeratorKiriFundusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DenomeratorKiriFundusKeyPressed

    private void PenilaianMataKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenilaianMataKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenilaianMataKananKeyPressed

    private void OptikKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OptikKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptikKananKeyPressed

    private void TenometriKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenometriKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenometriKananKeyPressed

    private void cmbTenometriKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTenometriKananKeyPressed
     
    }//GEN-LAST:event_cmbTenometriKananKeyPressed

    private void cmbTenometriKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTenometriKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTenometriKiriKeyPressed

    private void TenometriKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenometriKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenometriKiriKeyPressed

    private void RetinaKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RetinaKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RetinaKananKeyPressed

    private void MakulaKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MakulaKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MakulaKananKeyPressed

    private void PembuluhKananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembuluhKananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PembuluhKananKeyPressed

    private void PembuluhKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembuluhKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PembuluhKiriKeyPressed

    private void MakulaKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MakulaKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MakulaKiriKeyPressed

    private void RetinaKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RetinaKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RetinaKiriKeyPressed

    private void OptikKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OptikKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptikKiriKeyPressed

    private void PenilaianMataKiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenilaianMataKiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenilaianMataKiriKeyPressed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
//        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            Detik.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            //GCS.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
//            btnPetugasActionPerformed(null);
//        }
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
      //  petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        //Valid.pindah(evt,Detik,RPS);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
    //    Valid.pindah(evt,cmbMnt,TKeluhan);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
       // Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
     //   Valid.pindah(evt,TKdPrw,cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgOftamonologi dialog = new DlgOftamonologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox DenomeratorKananFundus;
    private widget.TextBox DenomeratorKiriFundus;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.TextBox MakulaKanan;
    private widget.TextBox MakulaKiri;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NumeratorKananFundus;
    private widget.TextBox NumeratorKiriFundus;
    private widget.TextBox OptikKanan;
    private widget.TextBox OptikKiri;
    private widget.TextBox PembuluhKanan;
    private widget.TextBox PembuluhKiri;
    private widget.TextBox PenilaianMataKanan;
    private widget.TextBox PenilaianMataKiri;
    private widget.TextBox RetinaKanan;
    private widget.TextBox RetinaKiri;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Table Table1;
    private widget.Table Table2;
    private widget.Table Table3;
    private widget.TextBox TenometriKanan;
    private widget.TextBox TenometriKiri;
    private widget.Button btnPetugas;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbTenometriKanan;
    private widget.ComboBox cmbTenometriKiri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
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
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass2;
    private widget.panelisi panelGlass3;
    private widget.panelisi panelGlass4;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    // End of variables declaration//GEN-END:variables


    private void tampilvisus() {
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
               "SELECT pv.no_rawat, p.no_rkm_medis,p.nm_pasien,pv.tgl_perawatan,pv.jam_rawat,pv.numerator_kanan,pv.denomerator_kanan,pv.numerator_kiri,pv.denumerator_kiri,pg.nama,pg.nik FROM pemeriksaan_visus pv INNER JOIN reg_periksa rp on rp.no_rawat=pv.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg ON pv.nip= pg.nik " +
                "WHERE pv.tgl_perawatan between ? AND ? and rp.no_rkm_medis LIKE ? OR " +
                "pv.tgl_perawatan between ? AND ? and p.nm_pasien LIKE ? OR " +
                "pv.tgl_perawatan between ? AND ? and rp.no_rawat LIKE ? " +
                "ORDER BY pv.tgl_perawatan asc");
            try{            
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11)
                    });
                }
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        } 
            
        LCount.setText(""+tabMode.getRowCount());
    }
    
    private void tampiltenometri() {
        Valid.tabelKosong(tabMode2);
        try {
            ps=koneksi.prepareStatement(
                " SELECT pv.no_rawat, p.no_rkm_medis,p.nm_pasien,pv.tgl_perawatan,pv.jam_rawat,pv.status_kanan,pv.nilai_kanan,pv.status_kiri,pv.nilai_kiri,pg.nama,pg.nik FROM pemeriksaan_tenometri pv INNER JOIN reg_periksa rp on rp.no_rawat=pv.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg ON pv.nip= pg.nik " +
                " WHERE pv.tgl_perawatan between ? AND ? and rp.no_rkm_medis LIKE ? OR " +
                " pv.tgl_perawatan between ? AND ? and p.nm_pasien LIKE ? OR " +
                " pv.tgl_perawatan between ? AND ? and rp.no_rawat LIKE ? " +
                " ORDER BY pv.tgl_perawatan asc ");
            try{            
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode2.addRow(new String[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)
                    });
                }
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("notifikasi : "+e);
        } 
            
        LCount.setText(""+tabMode2.getRowCount());
    }
    
    private void tampilfundus() {
        Valid.tabelKosong(tabMode3);
        try {
            ps=koneksi.prepareStatement(
                "SELECT pv.no_rawat, p.no_rkm_medis,p.nm_pasien,pv.tgl_perawatan,pv.jam_rawat,pv.keadaan_kanan,optik_kanan,retina_kanan,makula_kanan,pembuluh_kanan,keadaan_kiri,optik_kiri,retina_kiri,makula_kiri,pembuluh_kiri,pg.nama,pg.nik FROM pemeriksaan_fundus pv " +
                "INNER JOIN reg_periksa rp on rp.no_rawat=pv.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg ON pv.nip= pg.nik " +
                "WHERE pv.tgl_perawatan between ? AND ? and rp.no_rkm_medis LIKE ?  OR " +
                "pv.tgl_perawatan between ? AND ? and p.nm_pasien LIKE ? OR " +
                "pv.tgl_perawatan between ? AND ? and rp.no_rawat LIKE ? " +
                "ORDER BY pv.tgl_perawatan asc");
            try{            
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode3.addRow(new String[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),
                        rs.getString(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17)
                    });
                }
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("notifikasi : "+e);
        } 
            
        LCount.setText(""+tabMode3.getRowCount());
    }

    public void emptTeksvisus() {
        DenomeratorKananFundus.setText("");
        NumeratorKiriFundus.setText("");
        DenomeratorKiriFundus.setText("");
        NumeratorKananFundus.setText("");
        TNoRw.setText("");
        TPasien.setText("");
        TNoRM.setText("");
        NIP.setText("");
        NamaPetugas.setText("");
        DTPTgl.setDate(new Date());
    }
    
    public void emptTekstenometri() {
        TenometriKanan.setText("");
        TenometriKiri.setText("");
        cmbTenometriKanan.setSelectedIndex(0);
        cmbTenometriKiri.setSelectedIndex(0);
        TNoRw.setText("");
        TPasien.setText("");
        TNoRM.setText("");
        NIP.setText("");
        NamaPetugas.setText("");
         DTPTgl.setDate(new Date());
    }
    
    public void emptTeksfundus() {
        PenilaianMataKanan.setText("");
        OptikKanan.setText("");
        RetinaKanan.setText("");
        MakulaKanan.setText("");
        PembuluhKanan.setText("");
        PenilaianMataKiri.setText("");
        OptikKiri.setText("");
        RetinaKiri.setText("");
        MakulaKiri.setText("");
        PembuluhKiri.setText("");
        TNoRw.setText("");
        TPasien.setText("");
        TNoRM.setText("");
        NIP.setText("");
        NamaPetugas.setText("");
        DTPTgl.setDate(new Date());
       
    }

    private void getDatavisus() {
        int row=Table1.getSelectedRow();
        if(row!= -1){
            TNoRw.setText(Table1.getValueAt(row,0).toString());
            TNoRM.setText(Table1.getValueAt(row,1).toString());
            TPasien.setText(Table1.getValueAt(row,2).toString());
            Valid.SetTgl(DTPTgl,Table1.getValueAt(row,3).toString());
            cmbJam.setSelectedItem(Table1.getValueAt(row,4).toString().substring(0,2));
            cmbMnt.setSelectedItem(Table1.getValueAt(row,4).toString().substring(3,5));
            cmbDtk.setSelectedItem(Table1.getValueAt(row,4).toString().substring(6,8));
            NumeratorKananFundus.setText(Table1.getValueAt(row,5).toString());
            DenomeratorKananFundus.setText(Table1.getValueAt(row,6).toString());
            NumeratorKiriFundus.setText(Table1.getValueAt(row,7).toString());
            DenomeratorKiriFundus.setText(Table1.getValueAt(row,8).toString());
            NamaPetugas.setText(Table1.getValueAt(row,9).toString());
            NIP.setText(Table1.getValueAt(row,10).toString());
        }
    }
    
    private void getDatatenometri() {
        int row=Table2.getSelectedRow();
        if(row!= -1){
            TNoRw.setText(Table2.getValueAt(row,0).toString());
            TNoRM.setText(Table2.getValueAt(row,1).toString());
            TPasien.setText(Table2.getValueAt(row,2).toString());
            Valid.SetTgl(DTPTgl,Table2.getValueAt(row,3).toString());
            cmbJam.setSelectedItem(Table2.getValueAt(row,4).toString().substring(0,2));
            cmbMnt.setSelectedItem(Table2.getValueAt(row,4).toString().substring(3,5));
            cmbDtk.setSelectedItem(Table2.getValueAt(row,4).toString().substring(6,8));
            cmbTenometriKanan.setSelectedItem(Table2.getValueAt(row,5).toString());
            TenometriKanan.setText(Table2.getValueAt(row,6).toString());
            cmbTenometriKiri.setSelectedItem(Table2.getValueAt(row,7).toString());
            TenometriKiri.setText(Table2.getValueAt(row,8).toString());
            NamaPetugas.setText(Table2.getValueAt(row,9).toString());
            NIP.setText(Table2.getValueAt(row,10).toString());
        }
    }
    
    private void getDatafundus() {
        int row=Table3.getSelectedRow();
        if(row!= -1){
            TNoRw.setText(Table3.getValueAt(row,0).toString());
            TNoRM.setText(Table3.getValueAt(row,1).toString());
            TPasien.setText(Table3.getValueAt(row,2).toString());
            Valid.SetTgl(DTPTgl,Table3.getValueAt(row,3).toString());
            cmbJam.setSelectedItem(Table3.getValueAt(row,4).toString().substring(0,2));
            cmbMnt.setSelectedItem(Table3.getValueAt(row,4).toString().substring(3,5));
            cmbDtk.setSelectedItem(Table3.getValueAt(row,4).toString().substring(6,8));
            PenilaianMataKanan.setText(Table3.getValueAt(row,5).toString());
            OptikKanan.setText(Table3.getValueAt(row,6).toString());
            RetinaKanan.setText(Table3.getValueAt(row,7).toString());
            MakulaKanan.setText(Table3.getValueAt(row,8).toString());
            PembuluhKanan.setText(Table3.getValueAt(row,9).toString());
            PenilaianMataKiri.setText(Table3.getValueAt(row,10).toString());
            OptikKiri.setText(Table3.getValueAt(row,11).toString());
            RetinaKiri.setText(Table3.getValueAt(row,12).toString());
            MakulaKiri.setText(Table3.getValueAt(row,13).toString());
            PembuluhKiri.setText(Table3.getValueAt(row,14).toString());
            NamaPetugas.setText(Table3.getValueAt(row,15).toString());
            NIP.setText(Table3.getValueAt(row,16).toString());
        }
    }
    
     private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
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
     
       public void setNoRm(String norwt,String norm, String pasien, Date tgl) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(pasien);
        TCari.setText(norwt);
        DTPTgl.setDate(tgl);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kontribusi dari dokter Salim Mulyana
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
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgRujuk;


/**
 * 
 * @author salimmulyana
 */
public final class DlgBuktiRujukan extends javax.swing.JDialog {
    private final DefaultTableModel tabmodeskpp;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,psobat;
    private ResultSet rs,rsobat;
    private int i=0;
    private String tgl;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariPetugas petugas1=new DlgCariPetugas(null,false);
    public DlgRujuk rujuk=new DlgRujuk(null,false);
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DlgBuktiRujukan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabmodeskpp=new DefaultTableModel(null,new Object[]{
             "No.Rawat","No.R.M.","Nama Pasien","SEP","No.Surat","Tangal","Jam","No.Surat Tugas","Kode RS","RS Rujukan","Kode Sopir","Nama Sopir","Kode Petugas","Nama Petugas"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}{
                
             };
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbskpp.setModel(tabmodeskpp);
        tbskpp.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbskpp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbskpp.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(60);
            }else if(i==2){
                column.setPreferredWidth(170);
            }else if(i==3){
                column.setPreferredWidth(170);
            }else if(i==4){
                column.setPreferredWidth(130);
            }else if(i==5){
                column.setPreferredWidth(130);
            }else if(i==6){
                column.setPreferredWidth(70);
            }else if(i==7){
                column.setPreferredWidth(130);
            }else if(i==8){
                column.setPreferredWidth(50);
            }else if(i==9){
                column.setPreferredWidth(180);
            }else if(i==10){
                column.setPreferredWidth(50);
            }else if(i==11){
                column.setPreferredWidth(130);
            }else if(i==12){
                column.setPreferredWidth(50);
            }else if(i==13){
                column.setPreferredWidth(130);
            }
        }
        tbskpp.setDefaultRenderer(Object.class, new WarnaTable());
        
          
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
        
       jam();
        
       petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                     kd_sopir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                     nmsopir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                } 
                kd_sopir.requestFocus();
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
       
        petugas1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                 if(petugas1.getTable().getSelectedRow()!= -1){
                    kd_petugas.setText(petugas1.getTable().getValueAt(petugas1.getTable().getSelectedRow(),0).toString());
                    nmpetugas.setText(petugas1.getTable().getValueAt(petugas1.getTable().getSelectedRow(),1).toString());
                }  
                kd_petugas.requestFocus();
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
       
        rujuk.WindowRSRujukan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(rujuk.tbkembali.getSelectedRow()!= -1){
                    kd_rs.setText(rujuk.tbkembali.getValueAt(rujuk.tbkembali.getSelectedRow(),0).toString());
                    nm_rs.setText(rujuk.tbkembali.getValueAt(rujuk.tbkembali.getSelectedRow(),1).toString());
                }   
                kd_rs.requestFocus();
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

        kd_dokter_bpjs = new widget.TextBox();
        kd_dokter = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbskpp = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnCloseIn = new widget.Button();
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
        jLabel82 = new widget.Label();
        kd_sopir = new widget.TextBox();
        nmsopir = new widget.TextBox();
        btnsopir = new widget.Button();
        no_surat = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel73 = new widget.Label();
        tanggal = new widget.Tanggal();
        jLabel83 = new widget.Label();
        kd_petugas = new widget.TextBox();
        nmpetugas = new widget.TextBox();
        btnpetugas = new widget.Button();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel84 = new widget.Label();
        kd_rs = new widget.TextBox();
        nm_rs = new widget.TextBox();
        btncarirs = new widget.Button();
        no_surattugas = new widget.TextBox();
        jLabel78 = new widget.Label();
        no_sep = new widget.TextBox();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        diagnosa = new widget.TextBox();
        ChkInput = new widget.CekBox();

        kd_dokter_bpjs.setEditable(false);
        kd_dokter_bpjs.setName("kd_dokter_bpjs"); // NOI18N
        kd_dokter_bpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_dokter_bpjsActionPerformed(evt);
            }
        });

        kd_dokter.setEditable(false);
        kd_dokter.setName("kd_dokter"); // NOI18N
        kd_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_dokterActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Bukti Pelayanan Ambulan Rujukan Pasien JKN KIS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(430, 220));

        tbskpp.setAutoCreateRowSorter(true);
        tbskpp.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbskpp.setName("tbskpp"); // NOI18N
        tbskpp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbskppMouseClicked(evt);
            }
        });
        tbskpp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbskppKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbskpp);

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
        BtnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('U');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+U");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

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

        BtnCloseIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn.setMnemonic('U');
        BtnCloseIn.setText("Tutup");
        BtnCloseIn.setToolTipText("Alt+U");
        BtnCloseIn.setName("BtnCloseIn"); // NOI18N
        BtnCloseIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCloseIn);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl. Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-04-2021" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-04-2021" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 195));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 95, 23);

        TNoRw.setEditable(false);
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

        jLabel82.setText("Sopir Ambulan :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(10, 55, 90, 23);

        kd_sopir.setEditable(false);
        kd_sopir.setName("kd_sopir"); // NOI18N
        kd_sopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_sopirActionPerformed(evt);
            }
        });
        FormInput.add(kd_sopir);
        kd_sopir.setBounds(120, 50, 50, 24);

        nmsopir.setEditable(false);
        nmsopir.setName("nmsopir"); // NOI18N
        nmsopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmsopirActionPerformed(evt);
            }
        });
        FormInput.add(nmsopir);
        nmsopir.setBounds(170, 50, 180, 24);

        btnsopir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnsopir.setMnemonic('3');
        btnsopir.setToolTipText("Alt+3");
        btnsopir.setName("btnsopir"); // NOI18N
        btnsopir.setPreferredSize(new java.awt.Dimension(28, 23));
        btnsopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsopirActionPerformed(evt);
            }
        });
        btnsopir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsopirKeyPressed(evt);
            }
        });
        FormInput.add(btnsopir);
        btnsopir.setBounds(350, 50, 28, 23);

        no_surat.setEditable(false);
        no_surat.setName("no_surat"); // NOI18N
        no_surat.setPreferredSize(new java.awt.Dimension(150, 24));
        no_surat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_suratActionPerformed(evt);
            }
        });
        FormInput.add(no_surat);
        no_surat.setBounds(550, 85, 230, 24);

        jLabel77.setText("No Surat :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(460, 85, 77, 23);

        jLabel73.setText("Tgl. Pelayanan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 120, 100, 23);

        tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-04-2021" }));
        tanggal.setDisplayFormat("dd-MM-yyyy");
        tanggal.setName("tanggal"); // NOI18N
        tanggal.setOpaque(false);
        tanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tanggalItemStateChanged(evt);
            }
        });
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tanggalKeyPressed(evt);
            }
        });
        FormInput.add(tanggal);
        tanggal.setBounds(120, 120, 90, 23);

        jLabel83.setText("Perawat Rujukan :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(10, 85, 90, 23);

        kd_petugas.setEditable(false);
        kd_petugas.setName("kd_petugas"); // NOI18N
        kd_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_petugasActionPerformed(evt);
            }
        });
        FormInput.add(kd_petugas);
        kd_petugas.setBounds(120, 85, 50, 24);

        nmpetugas.setEditable(false);
        nmpetugas.setName("nmpetugas"); // NOI18N
        nmpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmpetugasActionPerformed(evt);
            }
        });
        FormInput.add(nmpetugas);
        nmpetugas.setBounds(170, 85, 180, 24);

        btnpetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnpetugas.setMnemonic('3');
        btnpetugas.setToolTipText("Alt+3");
        btnpetugas.setName("btnpetugas"); // NOI18N
        btnpetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpetugasActionPerformed(evt);
            }
        });
        btnpetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnpetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnpetugas);
        btnpetugas.setBounds(350, 85, 28, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(210, 120, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(280, 120, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(340, 120, 62, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.setPreferredSize(new java.awt.Dimension(22, 23));
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(410, 120, 22, 23);

        jLabel84.setText("Tujuan RS :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(10, 155, 90, 23);

        kd_rs.setEditable(false);
        kd_rs.setName("kd_rs"); // NOI18N
        kd_rs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_rsActionPerformed(evt);
            }
        });
        FormInput.add(kd_rs);
        kd_rs.setBounds(120, 155, 50, 24);

        nm_rs.setEditable(false);
        nm_rs.setName("nm_rs"); // NOI18N
        nm_rs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_rsActionPerformed(evt);
            }
        });
        FormInput.add(nm_rs);
        nm_rs.setBounds(170, 155, 180, 24);

        btncarirs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncarirs.setMnemonic('3');
        btncarirs.setToolTipText("Alt+3");
        btncarirs.setName("btncarirs"); // NOI18N
        btncarirs.setPreferredSize(new java.awt.Dimension(28, 23));
        btncarirs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarirsActionPerformed(evt);
            }
        });
        btncarirs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncarirsKeyPressed(evt);
            }
        });
        FormInput.add(btncarirs);
        btncarirs.setBounds(350, 155, 28, 23);

        no_surattugas.setEditable(false);
        no_surattugas.setName("no_surattugas"); // NOI18N
        no_surattugas.setPreferredSize(new java.awt.Dimension(150, 24));
        no_surattugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_surattugasActionPerformed(evt);
            }
        });
        FormInput.add(no_surattugas);
        no_surattugas.setBounds(550, 120, 230, 24);

        jLabel78.setText("No Surat Tugas :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(450, 120, 90, 23);

        no_sep.setEditable(false);
        no_sep.setName("no_sep"); // NOI18N
        no_sep.setPreferredSize(new java.awt.Dimension(150, 24));
        no_sep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_sepActionPerformed(evt);
            }
        });
        FormInput.add(no_sep);
        no_sep.setBounds(550, 50, 230, 24);

        jLabel79.setText("No. SEP :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(460, 50, 77, 23);

        jLabel80.setText("Diagnosa Rujuk :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(450, 160, 90, 23);

        diagnosa.setName("diagnosa"); // NOI18N
        diagnosa.setPreferredSize(new java.awt.Dimension(150, 24));
        diagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosaActionPerformed(evt);
            }
        });
        FormInput.add(diagnosa);
        diagnosa.setBounds(550, 160, 230, 24);

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

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DTPCari1.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbskpp.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
         tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbskpp.requestFocus();
        }
}//GEN-LAST:event_BtnCariKeyPressed
   
                                  
    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
       
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbskppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbskppMouseClicked
     if(tabmodeskpp.getRowCount()!=0){
            try {
                getdatarujukan();
            } catch (java.lang.NullPointerException e){
                
            }
        }
}//GEN-LAST:event_tbskppMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
       isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbskppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbskppKeyReleased
            if(tbskpp.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getdatarujukan();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }
        }
    }//GEN-LAST:event_tbskppKeyReleased

    private void kd_sopirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_sopirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_sopirActionPerformed

    private void nmsopirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmsopirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmsopirActionPerformed

    private void btnsopirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsopirActionPerformed
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.isCek();
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnsopirActionPerformed

    private void btnsopirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsopirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsopirKeyPressed

    private void no_suratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_suratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_suratActionPerformed

    private void tanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tanggalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalItemStateChanged

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalActionPerformed

    private void tanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
            }else if(kd_sopir.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
            }else if(no_surat.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, No.Surat Masih Kosong..");
            }else{
            ChkJln.setSelected(false);
            if(Sequel.cariInteger("select count(no_rawat) from rujuk where no_rawat=?",TNoRw.getText())>0){
               
       
                
                      Sequel.menyimpan("bukti_rujukan","?,?,?,?,?,?,?,?,?,?",10,new String[]{TNoRw.getText(),no_sep.getText(),no_surat.getText(),no_surattugas.getText(),Valid.SetTgl(tanggal.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),kd_rs.getText(),kd_sopir.getText(),kd_petugas.getText(),kd_dokter.getText()});
                
                }else{
                    Sequel.menyimpan("rujuk","'"+no_surattugas.getText()+"','"+
                    TNoRw.getText()+"','"+
                    nm_rs.getText()+"','"+
                    Valid.SetTgl(tanggal.getSelectedItem()+"")+"','"+ 
                    diagnosa.getText()+"','"+
                    kd_dokter.getText()+"','-','AGD','-','"+
                    cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"'","No.Rujuk");
            //Sequel.meghapus("ubah_penjab","no_rawat",norawat.getText());
            Sequel.menyimpan("bukti_rujukan","?,?,?,?,?,?,?,?,?,?",10,new String[]{TNoRw.getText(),no_sep.getText(),no_surat.getText(),no_surattugas.getText(),Valid.SetTgl(tanggal.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),kd_rs.getText(),kd_sopir.getText(),kd_petugas.getText(),kd_dokter.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()});          
                     }
          emptTeks1();
          autoNomorSurat();
          tampil();
          ChkJln.setSelected(true);
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"No.Rawat");
       }else if(kd_sopir.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{
            ChkJln.setSelected(false);
           
            Sequel.mengedit("bukti_rujukan","no_rawat=? and no_sep=?","no_surat=?,surat_tugas=?,tanggal=?,jam=?,rs_tujuan=?,kd_sopir=?,kd_petugas=?",9,new String[]{no_surat.getText(),no_surattugas.getText(),Valid.SetTgl(tanggal.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),kd_rs.getText(),kd_sopir.getText(),kd_petugas.getText(),TNoRw.getText(),no_sep.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()}); 
             Sequel.mengedit("rujuk","no_rawat=? and no_rujuk=?","rujuk_ket=?,tgl_rujuk=?,kd_dokter=?,jam=?",6,new String[]{nm_rs.getText(),Valid.SetTgl(tanggal.getSelectedItem()+""),kd_dokter.getText(),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),TNoRw.getText(),no_surattugas.getText()});
            emptTeks1();
            autoNomorSurat();
            tampil();
            ChkJln.setSelected(true);
        }

    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabmodeskpp.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    BtnCloseIn.requestFocus();
                }else {
                       Sequel.queryu("delete from bukti_rujukan where no_rawat='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString()+
                                    "' and no_sep='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString()+"' ");
                       
                        
                        emptTeks1();
                        autoNomorSurat();
                        tampil();
                       }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    param.put("logo1",Sequel.cariGambar("select pemda from gambar"));
                    param.put("no_surat",no_surat.getText());  
                    Valid.MyReport("rptbuktiambulanjkn.jasper",param,"::[ Bukti Pelayanan Ambulan Rujukan Pasien JKN KIS ]::");
         this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
      dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void kd_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_petugasActionPerformed

    private void nmpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmpetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpetugasActionPerformed

    private void btnpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpetugasActionPerformed
        petugas1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas1.isCek();
        petugas1.setLocationRelativeTo(internalFrame1);
        petugas1.setVisible(true);
    }//GEN-LAST:event_btnpetugasActionPerformed

    private void btnpetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnpetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpetugasKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
       
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed

    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed

    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void kd_rsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_rsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_rsActionPerformed

    private void nm_rsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_rsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_rsActionPerformed

    private void btncarirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarirsActionPerformed

            rujuk.WindowRSRujukan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            rujuk.WindowRSRujukan.setLocationRelativeTo(internalFrame1);
            rujuk.WindowRSRujukan.setSize(619,419);
            rujuk.WindowRSRujukan.setVisible(true);
           
        
    }//GEN-LAST:event_btncarirsActionPerformed

    private void btncarirsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncarirsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncarirsKeyPressed

    private void no_surattugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_surattugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_surattugasActionPerformed

    private void no_sepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_sepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_sepActionPerformed

    private void diagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diagnosaActionPerformed

    private void kd_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_dokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_dokterActionPerformed

    private void kd_dokter_bpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_dokter_bpjsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_dokter_bpjsActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBuktiRujukan dialog = new DlgBuktiRujukan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Button btncarirs;
    private widget.Button btnpetugas;
    private widget.Button btnsopir;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.TextBox diagnosa;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kd_dokter;
    private widget.TextBox kd_dokter_bpjs;
    private widget.TextBox kd_petugas;
    private widget.TextBox kd_rs;
    private widget.TextBox kd_sopir;
    private widget.TextBox nm_rs;
    private widget.TextBox nmpetugas;
    private widget.TextBox nmsopir;
    private widget.TextBox no_sep;
    private widget.TextBox no_surat;
    private widget.TextBox no_surattugas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Tanggal tanggal;
    private widget.Table tbskpp;
    // End of variables declaration//GEN-END:variables

     public void tampil() {
        Valid.tabelKosong(tabmodeskpp);
        try{
            ps=koneksi.prepareStatement("SELECT bukti_rujukan.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,bukti_rujukan.no_sep,bukti_rujukan.no_surat,bukti_rujukan.tanggal,bukti_rujukan.jam,bukti_rujukan.surat_tugas," +
                                        "bukti_rujukan.rs_tujuan,rs_rujukan.rs_rujuk,bukti_rujukan.kd_sopir,petugas.nama as nama1,bukti_rujukan.kd_petugas,pegawai.nama as nama2 " +
                                        "FROM bukti_rujukan INNER JOIN reg_periksa ON reg_periksa.no_rawat=bukti_rujukan.no_rawat " +
                                        "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
                                        "INNER JOIN petugas ON petugas.nip=bukti_rujukan.kd_sopir " +
                                        "INNER JOIN pegawai ON pegawai.nik=bukti_rujukan.kd_petugas " +
                                        "INNER JOIN rs_rujukan ON bukti_rujukan.rs_tujuan=rs_rujukan.kode WHERE " +
                                        "bukti_rujukan.tanggal between ? and ? and bukti_rujukan.no_rawat like ? or " + 
                                        "bukti_rujukan.tanggal between ? and ? and pasien.no_rkm_medis like ? or " +
                                        "bukti_rujukan.tanggal between ? and ? and pasien.nm_pasien like ? ");
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
 
                rs=ps.executeQuery();
                while(rs.next()){
                    tabmodeskpp.addRow(new Object[]{rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7),
                                   rs.getString(8),
                                   rs.getString(9),
                                   rs.getString(10),
                                   rs.getString(11),
                                   rs.getString(12),
                                   rs.getString(13),
                                   rs.getString(14)});
                }
            } catch (Exception e) {
                System.out.println(e);
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
        LCount.setText(""+tabmodeskpp.getRowCount());
    }



 
      private void getdatarujukan() {
        if(tbskpp.getSelectedRow()!= -1){
            TNoRw.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            Sequel.cariIsi("SELECT pasien.no_rkm_medis FROM pasien INNER JOIN reg_periksa ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis WHERE reg_periksa.no_rawat=?",TNoRM,tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            Sequel.cariIsi("SELECT pasien.nm_pasien FROM pasien INNER JOIN reg_periksa ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis WHERE reg_periksa.no_rawat=?",TPasien,tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            no_sep.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString());
            no_surat.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),4).toString());
            Valid.SetTgl(tanggal,tbskpp.getValueAt(tbskpp.getSelectedRow(),5).toString());
            no_surattugas.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),7).toString());
            kd_rs.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),8).toString());
            nm_rs.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),9).toString());
            kd_sopir.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),10).toString());
            nmsopir.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),11).toString());
            kd_petugas.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),12).toString());
            nmpetugas.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),13).toString());
            kd_dokter.setText(Sequel.cariIsi("select kd_dokter from bukti_rujukan where no_rawat=?",TNoRw.getText()));
        }
    }
      
   
    
    
    public void emptTeks1() { 
         autoNomorSurat();
         if(akses.getkode().equals("Admin Utama")){
              kd_petugas.setText("");
              nmpetugas.setText("");
            }else{
                kd_petugas.setText(akses.getkode());
                Sequel.cariIsi("select nama from petugas where nip=?", nmsopir,kd_petugas.getText());
                }
         tanggal.setDate(new Date());
         kd_sopir.setText("");
         nmsopir.setText("");
         
         if(Sequel.cariInteger("select count(no_rawat) from rujuk where no_rawat=?",TNoRw.getText())>0){
             no_surattugas.setText(Sequel.cariIsi("select no_rujuk from rujuk where no_rawat=?",TNoRw.getText()));
             diagnosa.setText(Sequel.cariIsi("select keterangan_diagnosa from rujuk where no_rawat=?",TNoRw.getText()));
             nm_rs.setText(Sequel.cariIsi("select rujuk_ke from rujuk where no_rawat=?",TNoRw.getText()));
             kd_rs.setText(Sequel.cariIsi("select kode from rs_rujukan where rs_rujuk=?",nm_rs.getText()));
             kd_dokter.setText(Sequel.cariIsi("select kd_dokter from rujuk where no_rawat=?",TNoRw.getText()));
         } else {
         diagnosa.setText("");
         Valid.autoNomer("rujuk","R",9,no_surattugas);
         nm_rs.setText("");
         kd_rs.setText("");
         kd_dokter.setText(Sequel.cariIsi("SELECT IFNULL(kd_dokter,'-') FROM maping_dokter_dpjpvclaim WHERE kd_dokter_bpjs=?",kd_dokter_bpjs.getText()));
         }
         
         autoNomorSurat();
            }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
    
    public void setNoRw(String norwt, String SEP, String dpjp) {
        TNoRw.setText(norwt);
        no_sep.setText(SEP);
        kd_dokter_bpjs.setText(dpjp);
        isRawat();
        isPsien(); 
        ChkInput.setSelected(true);
        isForm();
        tampil();
        autoNomorSurat();

    }
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,280));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
      public void autoNomorSurat() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from bukti_rujukan where "
                + "tanggal like '%" + Valid.SetTgl(tanggal.getSelectedItem() + "").substring(0, 7) + "%' ", "/Ambulan JKN/RSUD KPS/" + Valid.SetTgl(tanggal.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tanggal.getSelectedItem() + "").substring(0, 4), 4, no_surat);
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




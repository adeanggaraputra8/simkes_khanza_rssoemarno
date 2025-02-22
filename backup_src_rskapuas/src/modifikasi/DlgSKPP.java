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
public final class DlgSKPP extends javax.swing.JDialog {
    private final DefaultTableModel tabmodeskpp;
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
    public DlgSKPP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabmodeskpp=new DefaultTableModel(null,new Object[]{
             "No.Rawat","No.R.M.","Nama Pasien","No.Surat","Tgl. Masuk Ranap 1",
             "Tgl.Keluar Ranap 1","kode P.J","Tgl. Masuk Ranap 2","Tgl.Keluar Ranap 2","Tgl. Masuk Ranap Biasa","Tgl.Alih Rawat atau Ke Rg. ISolasi","Tgl.Surat"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}{
                
             };
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbskpp.setModel(tabmodeskpp);
        tbskpp.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbskpp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbskpp.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(60);
            }else if(i==2){
                column.setPreferredWidth(170);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(130);
            }else if(i==5){
                column.setPreferredWidth(130);
            }else if(i==6){
                column.setPreferredWidth(70);
            }else if(i==7){
                column.setPreferredWidth(130);
            }else if(i==8){
                column.setPreferredWidth(130);
            }else if(i==9){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
        
      
        
       petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                     kd_petugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                     namapetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                namapetugas.requestFocus();
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

        tglbantu = new widget.Tanggal();
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
        kd_petugas = new widget.TextBox();
        namapetugas = new widget.TextBox();
        btncaripetugas1 = new widget.Button();
        noskpp = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel78 = new widget.Label();
        tgl_masuk1 = new widget.Tanggal();
        tgl_keluar1 = new widget.Tanggal();
        Chk1 = new widget.CekBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        tgl_masuk3 = new widget.Tanggal();
        jLabel86 = new widget.Label();
        tgl_keluar3 = new widget.Tanggal();
        Chk3 = new widget.CekBox();
        tgl_keluar2 = new widget.Tanggal();
        Chk2 = new widget.CekBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel83 = new widget.Label();
        tgl_masuk2 = new widget.Tanggal();
        ChkInput = new widget.CekBox();

        tglbantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tglbantu.setDisplayFormat("dd-MM-yyyy");
        tglbantu.setName("tglbantu"); // NOI18N
        tglbantu.setOpaque(false);
        tglbantu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglbantuItemStateChanged(evt);
            }
        });
        tglbantu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglbantuActionPerformed(evt);
            }
        });
        tglbantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglbantuKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surat Keterangan Perawatan Pasien Covid 19 ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
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

        jLabel82.setText("P.J :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(10, 55, 77, 23);

        kd_petugas.setEditable(false);
        kd_petugas.setText("P105");
        kd_petugas.setName("kd_petugas"); // NOI18N
        kd_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_petugasActionPerformed(evt);
            }
        });
        FormInput.add(kd_petugas);
        kd_petugas.setBounds(100, 55, 50, 24);

        namapetugas.setEditable(false);
        namapetugas.setName("namapetugas"); // NOI18N
        namapetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namapetugasActionPerformed(evt);
            }
        });
        FormInput.add(namapetugas);
        namapetugas.setBounds(150, 55, 180, 24);

        btncaripetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaripetugas1.setMnemonic('3');
        btncaripetugas1.setToolTipText("Alt+3");
        btncaripetugas1.setName("btncaripetugas1"); // NOI18N
        btncaripetugas1.setPreferredSize(new java.awt.Dimension(28, 23));
        btncaripetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugas1ActionPerformed(evt);
            }
        });
        btncaripetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncaripetugas1KeyPressed(evt);
            }
        });
        FormInput.add(btncaripetugas1);
        btncaripetugas1.setBounds(330, 55, 28, 23);

        noskpp.setEditable(false);
        noskpp.setName("noskpp"); // NOI18N
        noskpp.setPreferredSize(new java.awt.Dimension(150, 24));
        noskpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noskppActionPerformed(evt);
            }
        });
        FormInput.add(noskpp);
        noskpp.setBounds(520, 55, 230, 24);

        jLabel77.setText("No Surat :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(430, 55, 77, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("1. Atas kondisi memerlukan perawatan di ruang Isolasi Covid 19 pada :");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(40, 100, 420, 23);

        jLabel73.setText("Tgl. Mulai Ranap :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(66, 130, 100, 23);

        jLabel78.setText("Tgl. Selesai Ranap :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(66, 160, 100, 23);

        tgl_masuk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_masuk1.setDisplayFormat("dd-MM-yyyy");
        tgl_masuk1.setName("tgl_masuk1"); // NOI18N
        tgl_masuk1.setOpaque(false);
        tgl_masuk1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_masuk1ItemStateChanged(evt);
            }
        });
        tgl_masuk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_masuk1ActionPerformed(evt);
            }
        });
        tgl_masuk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_masuk1KeyPressed(evt);
            }
        });
        FormInput.add(tgl_masuk1);
        tgl_masuk1.setBounds(170, 130, 90, 23);

        tgl_keluar1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_keluar1.setDisplayFormat("dd-MM-yyyy");
        tgl_keluar1.setName("tgl_keluar1"); // NOI18N
        tgl_keluar1.setOpaque(false);
        tgl_keluar1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_keluar1ItemStateChanged(evt);
            }
        });
        tgl_keluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_keluar1ActionPerformed(evt);
            }
        });
        tgl_keluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_keluar1KeyPressed(evt);
            }
        });
        FormInput.add(tgl_keluar1);
        tgl_keluar1.setBounds(170, 160, 90, 23);

        Chk1.setBorder(null);
        Chk1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Chk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Chk1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Chk1.setName("Chk1"); // NOI18N
        Chk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk1ActionPerformed(evt);
            }
        });
        FormInput.add(Chk1);
        Chk1.setBounds(260, 130, 23, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("3. Peralihan Ruang Rawat *");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(320, 180, 240, 23);

        jLabel85.setText("Tgl. Mulai Ranap Biasa :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(270, 210, 130, 23);

        tgl_masuk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_masuk3.setDisplayFormat("dd-MM-yyyy");
        tgl_masuk3.setName("tgl_masuk3"); // NOI18N
        tgl_masuk3.setOpaque(false);
        tgl_masuk3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_masuk3ItemStateChanged(evt);
            }
        });
        tgl_masuk3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_masuk3ActionPerformed(evt);
            }
        });
        tgl_masuk3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_masuk3KeyPressed(evt);
            }
        });
        FormInput.add(tgl_masuk3);
        tgl_masuk3.setBounds(410, 210, 90, 23);

        jLabel86.setText("Tgl. Alih Rawat dari / ke Rg. Isolasi  :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(210, 240, 190, 23);

        tgl_keluar3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_keluar3.setDisplayFormat("dd-MM-yyyy");
        tgl_keluar3.setName("tgl_keluar3"); // NOI18N
        tgl_keluar3.setOpaque(false);
        tgl_keluar3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_keluar3ItemStateChanged(evt);
            }
        });
        tgl_keluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_keluar3ActionPerformed(evt);
            }
        });
        tgl_keluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_keluar3KeyPressed(evt);
            }
        });
        FormInput.add(tgl_keluar3);
        tgl_keluar3.setBounds(410, 240, 90, 23);

        Chk3.setBorder(null);
        Chk3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Chk3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Chk3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Chk3.setName("Chk3"); // NOI18N
        Chk3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk3ActionPerformed(evt);
            }
        });
        FormInput.add(Chk3);
        Chk3.setBounds(510, 210, 23, 23);

        tgl_keluar2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_keluar2.setDisplayFormat("dd-MM-yyyy");
        tgl_keluar2.setName("tgl_keluar2"); // NOI18N
        tgl_keluar2.setOpaque(false);
        tgl_keluar2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_keluar2ItemStateChanged(evt);
            }
        });
        tgl_keluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_keluar2ActionPerformed(evt);
            }
        });
        tgl_keluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_keluar2KeyPressed(evt);
            }
        });
        FormInput.add(tgl_keluar2);
        tgl_keluar2.setBounds(600, 160, 90, 23);

        Chk2.setBorder(null);
        Chk2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Chk2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Chk2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Chk2.setName("Chk2"); // NOI18N
        Chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chk2ActionPerformed(evt);
            }
        });
        FormInput.add(Chk2);
        Chk2.setBounds(690, 130, 23, 23);

        jLabel80.setText("Tgl. Mulai Ranap :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(490, 130, 100, 23);

        jLabel81.setText("Tgl. Selesai Ranap :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(490, 160, 100, 23);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("2. Atas kondisi medis lainnya, memerlukan perawatan Intensiv (ICU) * :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(470, 100, 420, 23);

        tgl_masuk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2021" }));
        tgl_masuk2.setDisplayFormat("dd-MM-yyyy");
        tgl_masuk2.setName("tgl_masuk2"); // NOI18N
        tgl_masuk2.setOpaque(false);
        tgl_masuk2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_masuk2ItemStateChanged(evt);
            }
        });
        tgl_masuk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_masuk2ActionPerformed(evt);
            }
        });
        tgl_masuk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_masuk2KeyPressed(evt);
            }
        });
        FormInput.add(tgl_masuk2);
        tgl_masuk2.setBounds(600, 130, 90, 23);

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
                getdataskpp();
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
                    getdataskpp();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }
        }
    }//GEN-LAST:event_tbskppKeyReleased

    private void kd_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_petugasActionPerformed

    private void namapetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namapetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namapetugasActionPerformed

    private void btncaripetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugas1ActionPerformed
//        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//        petugas.isCek();
//        petugas.setLocationRelativeTo(internalFrame1);
//        petugas.setVisible(true);
    }//GEN-LAST:event_btncaripetugas1ActionPerformed

    private void btncaripetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncaripetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncaripetugas1KeyPressed

    private void noskppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noskppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noskppActionPerformed

    private void tgl_masuk1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_masuk1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk1ItemStateChanged

    private void tgl_masuk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_masuk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk1ActionPerformed

    private void tgl_masuk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_masuk1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk1KeyPressed

    private void tgl_keluar1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_keluar1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar1ItemStateChanged

    private void tgl_keluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_keluar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar1ActionPerformed

    private void tgl_keluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_keluar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar1KeyPressed

    private void Chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk1ActionPerformed
        cek1();
    }//GEN-LAST:event_Chk1ActionPerformed

    private void tgl_masuk3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_masuk3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk3ItemStateChanged

    private void tgl_masuk3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_masuk3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk3ActionPerformed

    private void tgl_masuk3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_masuk3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk3KeyPressed

    private void tgl_keluar3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_keluar3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar3ItemStateChanged

    private void tgl_keluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_keluar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar3ActionPerformed

    private void tgl_keluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_keluar3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar3KeyPressed

    private void Chk3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk3ActionPerformed
        cek3();
    }//GEN-LAST:event_Chk3ActionPerformed

    private void tgl_keluar2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_keluar2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar2ItemStateChanged

    private void tgl_keluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_keluar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar2ActionPerformed

    private void tgl_keluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_keluar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_keluar2KeyPressed

    private void Chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chk2ActionPerformed
        cek2();
    }//GEN-LAST:event_Chk2ActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
            }else if(kd_petugas.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
            }else if(noskpp.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, No.Surat Masih Kosong..");
            }else{
            //String kdpj=Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",norawat.getText());
            
            //Sequel.meghapus("ubah_penjab","no_rawat",norawat.getText());
            Sequel.menyimpan("surat_skpp","?,?,?,?,?,?,?,?,?,?",10,new String[]{TNoRw.getText(),noskpp.getText(),Valid.SetTgl(tglbantu.getSelectedItem()+""),Valid.SetTgl(tgl_masuk1.getSelectedItem()+""),Valid.SetTgl(tgl_keluar1.getSelectedItem()+""),kd_petugas.getText(),Valid.SetTgl(tgl_masuk2.getSelectedItem()+""),Valid.SetTgl(tgl_keluar2.getSelectedItem()+""),Valid.SetTgl(tgl_masuk3.getSelectedItem()+""),Valid.SetTgl(tgl_keluar3.getSelectedItem()+"")});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()});          
          
          emptTeks1();
          autoNomorSurat();
          tampil();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"No.Rawat");
       }else if(kd_petugas.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{
            //String kdpj=Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",norawat.getText());
            
            Sequel.mengedit("surat_skpp","no_rawat=? and no_surat=?","tgl_surat=?,tgl_masuk1=?,tglkeluar1=?,kd_petugas=?,tglmasuk2=?,tglkeluar2=?,tglmasuk3=?,tglkeluar3=?",10,new String[]{Valid.SetTgl(tglbantu.getSelectedItem()+""),Valid.SetTgl(tgl_masuk1.getSelectedItem()+""),Valid.SetTgl(tgl_keluar1.getSelectedItem()+""),kd_petugas.getText(),Valid.SetTgl(tgl_masuk2.getSelectedItem()+""),Valid.SetTgl(tgl_keluar2.getSelectedItem()+""),Valid.SetTgl(tgl_masuk3.getSelectedItem()+""),Valid.SetTgl(tgl_keluar3.getSelectedItem()+""),TNoRw.getText(),noskpp.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()}); 
            
            emptTeks1();
            autoNomorSurat();
            tampil();
        }

    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabmodeskpp.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    BtnCloseIn.requestFocus();
                }else {
                       Sequel.queryu("delete from surat_skpp where no_rawat='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString()+
                                    "' and no_surat='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString()+"' ");
                       
                        
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
                    Valid.MyReportqry("rptskpp.jasper","report","::[ Surat Keterangan Perawatan Pasien Covid 19 ]::",
                            "SELECT surat_skpp.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat,pasien.tgl_lahir,surat_skpp.tgl_surat,surat_skpp.no_surat,IF(surat_skpp.tgl_masuk1= '0000-00-00','-',surat_skpp.tgl_masuk1) AS tglmasuk1,IF(surat_skpp.tglkeluar1= '0000-00-00','-',surat_skpp.tglkeluar1) AS tglkeluar1,petugas.nama," +
                            "IF(surat_skpp.tglmasuk2= '0000-00-00','-',surat_skpp.tglmasuk2) AS tglmasuk2,IF(surat_skpp.tglkeluar2= '0000-00-00','-',surat_skpp.tglkeluar2) AS tglkeluar2,IF(surat_skpp.tglmasuk3= '0000-00-00','-',surat_skpp.tglmasuk3) AS tglmasuk3,IF(surat_skpp.tglkeluar3= '0000-00-00','-',surat_skpp.tglkeluar3) AS tglkeluar3 FROM surat_skpp INNER JOIN reg_periksa ON reg_periksa.no_rawat=surat_skpp.no_rawat " +
                            "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
                            "inner join kelurahan inner join kecamatan inner join kabupaten " +
                            "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel " +
                            "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab " +
                            "INNER JOIN petugas ON petugas.nip=surat_skpp.kd_petugas " +
                            "WHERE surat_skpp.no_rawat ='"+TNoRw.getText()+"'" ,param);
        
         this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
      dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void tglbantuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglbantuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tglbantuItemStateChanged

    private void tglbantuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglbantuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglbantuActionPerformed

    private void tglbantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglbantuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglbantuKeyPressed

    private void tgl_masuk2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_masuk2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk2ItemStateChanged

    private void tgl_masuk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_masuk2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk2ActionPerformed

    private void tgl_masuk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_masuk2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_masuk2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSKPP dialog = new DlgSKPP(new javax.swing.JFrame(), true);
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
    private widget.CekBox Chk1;
    private widget.CekBox Chk2;
    private widget.CekBox Chk3;
    private widget.CekBox ChkInput;
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
    private widget.Button btncaripetugas1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kd_petugas;
    private widget.TextBox namapetugas;
    private widget.TextBox noskpp;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbskpp;
    private widget.Tanggal tgl_keluar1;
    private widget.Tanggal tgl_keluar2;
    private widget.Tanggal tgl_keluar3;
    private widget.Tanggal tgl_masuk1;
    private widget.Tanggal tgl_masuk2;
    private widget.Tanggal tgl_masuk3;
    private widget.Tanggal tglbantu;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabmodeskpp);
        try{
            ps=koneksi.prepareStatement("SELECT surat_skpp.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,surat_skpp.no_surat,surat_skpp.tgl_masuk1,surat_skpp.tglkeluar1,surat_skpp.kd_petugas,surat_skpp.tglmasuk2,surat_skpp.tglkeluar2,surat_skpp.tglmasuk3,surat_skpp.tglkeluar3,surat_skpp.tgl_surat FROM surat_skpp " +
                                        "INNER JOIN reg_periksa ON reg_periksa.no_rawat=surat_skpp.no_rawat " +
                                        "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
                                        "where surat_skpp.tgl_surat between ? and ? and surat_skpp.no_rawat like ? or " + 
                                        "surat_skpp.tgl_surat between ? and ? and pasien.no_rkm_medis like ? or " +
                                        "surat_skpp.tgl_surat between ? and ? and surat_skpp.no_surat like ? or " +
                                        "surat_skpp.tgl_surat between ? and ? and pasien.nm_pasien like ? ");
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
                                   rs.getString(12)});
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



 
      private void getdataskpp() {
        if(tbskpp.getSelectedRow()!= -1){
            TNoRw.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            Sequel.cariIsi("SELECT pasien.no_rkm_medis FROM pasien INNER JOIN reg_periksa ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis WHERE reg_periksa.no_rawat=?",TNoRM,tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            Sequel.cariIsi("SELECT pasien.nm_pasien FROM pasien INNER JOIN reg_periksa ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis WHERE reg_periksa.no_rawat=?",TPasien,tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            kd_petugas.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),6).toString());
            Sequel.cariIsi("SELECT nama FROM pegawai WHERE nik=?",namapetugas,tbskpp.getValueAt(tbskpp.getSelectedRow(),6).toString());
            noskpp.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString());
            Valid.SetTgl(tgl_masuk1,tbskpp.getValueAt(tbskpp.getSelectedRow(),4).toString());
            Valid.SetTgl(tgl_keluar1,tbskpp.getValueAt(tbskpp.getSelectedRow(),5).toString());
            Valid.SetTgl(tgl_masuk2,tbskpp.getValueAt(tbskpp.getSelectedRow(),7).toString());
            Valid.SetTgl(tgl_keluar2,tbskpp.getValueAt(tbskpp.getSelectedRow(),8).toString());
            Valid.SetTgl(tgl_masuk3,tbskpp.getValueAt(tbskpp.getSelectedRow(),9).toString());
            Valid.SetTgl(tgl_keluar3,tbskpp.getValueAt(tbskpp.getSelectedRow(),10).toString());
            Valid.SetTgl(tglbantu,tbskpp.getValueAt(tbskpp.getSelectedRow(),11).toString()); 
        }
    }
      
      private void cek1() {
         if(Chk1.isSelected()==false){
         tgl_masuk1.setSelectedItem("00-00-0000");
         tgl_keluar1.setSelectedItem("00-00-0000");
         }else {
          tgl_masuk1.setDate(new Date());
          tgl_keluar1.setDate(new Date());
        }
      }
         
       private void cek3() {
         if(Chk3.isSelected()==false){
         tgl_masuk3.setSelectedItem("00-00-0000");
         tgl_keluar3.setSelectedItem("00-00-0000");
         }else {
          tgl_masuk3.setDate(new Date());
          tgl_keluar3.setDate(new Date());
          }
       }
      
       private void cek2() {
         if(Chk2.isSelected()==false){
         tgl_masuk2.setSelectedItem("00-00-0000");
         tgl_keluar2.setSelectedItem("00-00-0000");
         }else {
          tgl_masuk2.setDate(new Date());
          tgl_keluar2.setDate(new Date());
          }
       
       }
    
    
        public void emptTeks1() {
         tglbantu.setDate(new  Date());
         autoNomorSurat();
         if(akses.getkode().equals("Admin Utama")){
              kd_petugas.setText("");
              namapetugas.setText("");
            }else{
                kd_petugas.setText("P105");
                Sequel.cariIsi("select nama from petugas where nip=?", namapetugas,kd_petugas.getText());
                }       
            }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
    }
    
    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
        isRawat();
        isPsien(); 
        ChkInput.setSelected(true);
        isForm();
        tampil();
        cek1();
        cek2();
        cek3();
    }
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,300));
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
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_skpp where "
                + "tgl_surat like '%" + Valid.SetTgl(tglbantu.getSelectedItem() + "").substring(0, 7) + "%' ", "/RM./RSUD-Kapuas/" + Valid.SetTgl(tglbantu.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglbantu.getSelectedItem() + "").substring(0, 4), 4, noskpp);
    }    
   
}




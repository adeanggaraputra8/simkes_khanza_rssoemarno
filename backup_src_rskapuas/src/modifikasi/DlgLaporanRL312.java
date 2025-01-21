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
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modifikasi.DlgMetodaRL312;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;


/**
 * 
 * @author salimmulyana
 */
public final class DlgLaporanRL312 extends javax.swing.JDialog {
    private final DefaultTableModel tabmodeskpp;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,psobat;
    private ResultSet rs,rsobat;
    private int i=0;
    private double a=0,b=0,c=0; 
    private String tgl;
    private DlgMetodaRL312 metoda=new DlgMetodaRL312(null,false);
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DlgLaporanRL312(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabmodeskpp=new DefaultTableModel(null,new Object[]{
             "No.Rawat","No.R.M.","Nama Pasien","Tanggal","Metoda",
             "ANC","Pasca Persalinan","Bukan Rujukan","Rujukan Ranap",
             "Rujukan Ralan","Total","Pasca Persalinan/ Nifas","Abortus",
             "Lainnya","Kunjungan Ulang","Jumlah","Dirujuk","kd_metoda"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbskpp.setModel(tabmodeskpp);
        tbskpp.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbskpp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbskpp.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(60);
            }else if(i==2){
                column.setPreferredWidth(170);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(130);
            }else if(i==5){
                column.setPreferredWidth(130);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(150);
            }else if(i==16){
                column.setPreferredWidth(150);
            }else if(i==17){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbskpp.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        
        ANC.setDocument(new batasInput((byte)2).getOnlyAngka(ANC));
        Pascapersalinan.setDocument(new batasInput((byte)2).getOnlyAngka(Pascapersalinan));
        BukanRujukan.setDocument(new batasInput((byte)2).getOnlyAngka(BukanRujukan));
        RujukanRanap.setDocument(new batasInput((byte)2).getOnlyAngka(RujukanRanap));
        RujukanRalan.setDocument(new batasInput((byte)2).getOnlyAngka(RujukanRalan));
        Nifas.setDocument(new batasInput((byte)2).getOnlyAngka(Nifas));
        Abortus.setDocument(new batasInput((byte)2).getOnlyAngka(Abortus));
        Lainnya.setDocument(new batasInput((byte)2).getOnlyAngka(Lainnya));
        KunjunganUlang.setDocument(new batasInput((byte)2).getOnlyAngka(KunjunganUlang));
        Jumlah.setDocument(new batasInput((byte)2).getOnlyAngka(Jumlah));
        Dirujuk.setDocument(new batasInput((byte)2).getOnlyAngka(Dirujuk));
        
          
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
        
      
        
       metoda.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(metoda.getTable().getSelectedRow()!= -1){
                     Kdmetoda.setText(metoda.getTable().getValueAt(metoda.getTable().getSelectedRow(),0).toString());
                     NMetoda.setText(metoda.getTable().getValueAt(metoda.getTable().getSelectedRow(),1).toString());
                }  
                NMetoda.requestFocus();
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
        emptTeks1();
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
        Scroll = new widget.ScrollPane();
        tbskpp = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
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
        jLabel76 = new widget.Label();
        jLabel73 = new widget.Label();
        tanggal = new widget.Tanggal();
        Kdmetoda = new widget.TextBox();
        NMetoda = new widget.TextBox();
        BtnMetoda = new widget.Button();
        jLabel74 = new widget.Label();
        jLabel12 = new widget.Label();
        ANC = new widget.TextBox();
        Nifas = new widget.TextBox();
        KunjunganUlang = new widget.TextBox();
        Jumlah = new widget.TextBox();
        Dirujuk = new widget.TextBox();
        Lainnya = new widget.TextBox();
        Abortus = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        Total = new widget.TextBox();
        RujukanRanap = new widget.TextBox();
        jLabel9 = new widget.Label();
        BukanRujukan = new widget.TextBox();
        jLabel5 = new widget.Label();
        Pascapersalinan = new widget.TextBox();
        jLabel3 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        RujukanRalan = new widget.TextBox();
        jLabel78 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel17 = new widget.Label();
        ChkInput = new widget.CekBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan RL. 3.12 ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-11-2022" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-11-2022" }));
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

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Keluhan efek samping :");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(50, 220, 140, 23);

        jLabel73.setText("Metoda :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(220, 40, 60, 23);

        tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-11-2022" }));
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
        tanggal.setBounds(130, 40, 90, 23);

        Kdmetoda.setEditable(false);
        Kdmetoda.setHighlighter(null);
        Kdmetoda.setName("Kdmetoda"); // NOI18N
        FormInput.add(Kdmetoda);
        Kdmetoda.setBounds(290, 40, 40, 23);

        NMetoda.setEditable(false);
        NMetoda.setHighlighter(null);
        NMetoda.setName("NMetoda"); // NOI18N
        FormInput.add(NMetoda);
        NMetoda.setBounds(330, 40, 140, 23);

        BtnMetoda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnMetoda.setMnemonic('X');
        BtnMetoda.setToolTipText("Alt+X");
        BtnMetoda.setName("BtnMetoda"); // NOI18N
        BtnMetoda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMetodaActionPerformed(evt);
            }
        });
        BtnMetoda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnMetodaKeyPressed(evt);
            }
        });
        FormInput.add(BtnMetoda);
        BtnMetoda.setBounds(480, 40, 28, 23);

        jLabel74.setText("Tgl. Mulai Ranap :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(30, 40, 100, 23);

        jLabel12.setText("ANC :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(190, 70, 35, 23);

        ANC.setHighlighter(null);
        ANC.setName("ANC"); // NOI18N
        ANC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ANCKeyPressed(evt);
            }
        });
        FormInput.add(ANC);
        ANC.setBounds(230, 70, 40, 23);

        Nifas.setHighlighter(null);
        Nifas.setName("Nifas"); // NOI18N
        Nifas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NifasKeyPressed(evt);
            }
        });
        FormInput.add(Nifas);
        Nifas.setBounds(320, 140, 40, 23);

        KunjunganUlang.setHighlighter(null);
        KunjunganUlang.setName("KunjunganUlang"); // NOI18N
        KunjunganUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KunjunganUlangActionPerformed(evt);
            }
        });
        KunjunganUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KunjunganUlangKeyPressed(evt);
            }
        });
        FormInput.add(KunjunganUlang);
        KunjunganUlang.setBounds(190, 180, 40, 23);

        Jumlah.setHighlighter(null);
        Jumlah.setName("Jumlah"); // NOI18N
        Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahKeyPressed(evt);
            }
        });
        FormInput.add(Jumlah);
        Jumlah.setBounds(240, 220, 40, 23);

        Dirujuk.setHighlighter(null);
        Dirujuk.setName("Dirujuk"); // NOI18N
        Dirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DirujukKeyPressed(evt);
            }
        });
        FormInput.add(Dirujuk);
        Dirujuk.setBounds(360, 220, 40, 23);

        Lainnya.setHighlighter(null);
        Lainnya.setName("Lainnya"); // NOI18N
        Lainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LainnyaKeyPressed(evt);
            }
        });
        FormInput.add(Lainnya);
        Lainnya.setBounds(540, 140, 40, 23);

        Abortus.setHighlighter(null);
        Abortus.setName("Abortus"); // NOI18N
        Abortus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbortusKeyPressed(evt);
            }
        });
        FormInput.add(Abortus);
        Abortus.setBounds(420, 140, 40, 23);

        jLabel11.setText("Abortus:");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(370, 140, 50, 23);

        jLabel14.setText("Lainnya :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(470, 140, 70, 23);

        jLabel15.setText("Dirujuk :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(310, 220, 50, 23);

        Total.setEditable(false);
        Total.setHighlighter(null);
        Total.setName("Total"); // NOI18N
        Total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalKeyPressed(evt);
            }
        });
        FormInput.add(Total);
        Total.setBounds(630, 100, 40, 23);

        RujukanRanap.setHighlighter(null);
        RujukanRanap.setName("RujukanRanap"); // NOI18N
        RujukanRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RujukanRanapMouseClicked(evt);
            }
        });
        RujukanRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RujukanRanapKeyPressed(evt);
            }
        });
        FormInput.add(RujukanRanap);
        RujukanRanap.setBounds(400, 100, 40, 23);

        jLabel9.setText("Total :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(580, 100, 50, 23);

        BukanRujukan.setHighlighter(null);
        BukanRujukan.setName("BukanRujukan"); // NOI18N
        BukanRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BukanRujukanMouseClicked(evt);
            }
        });
        BukanRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BukanRujukanKeyPressed(evt);
            }
        });
        FormInput.add(BukanRujukan);
        BukanRujukan.setBounds(270, 100, 40, 23);

        jLabel5.setText("Rujukan R. Inap:");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(310, 100, 90, 23);

        Pascapersalinan.setHighlighter(null);
        Pascapersalinan.setName("Pascapersalinan"); // NOI18N
        Pascapersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PascapersalinanKeyPressed(evt);
            }
        });
        FormInput.add(Pascapersalinan);
        Pascapersalinan.setBounds(370, 70, 40, 23);

        jLabel3.setText("Pasca Persalinan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(280, 70, 90, 20);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText(" Konseling :");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(120, 70, 70, 23);

        jLabel8.setText("Pasca Persalinan/Nifas :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(190, 140, 130, 23);

        jLabel13.setText("Rujukan R. Jalan:");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(450, 100, 90, 23);

        RujukanRalan.setHighlighter(null);
        RujukanRalan.setName("RujukanRalan"); // NOI18N
        RujukanRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RujukanRalanMouseClicked(evt);
            }
        });
        RujukanRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RujukanRalanKeyPressed(evt);
            }
        });
        FormInput.add(RujukanRalan);
        RujukanRalan.setBounds(540, 100, 40, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("KB Baru dengan Cara Masuk :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(20, 100, 170, 23);

        jLabel16.setText("Bukan Rujukan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(190, 100, 80, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("KB Baru dengan Kondisi :");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(40, 140, 150, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Kunjungan Ulang :");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(80, 180, 110, 23);

        jLabel17.setText("Jumlah :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(190, 220, 50, 23);

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

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
            }else if(Kdmetoda.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih Metoda dulu...!!!");
                BtnMetoda.requestFocus();
            }else{
            Sequel.menyimpan("i_rl312","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",16,new String[]
            {"0",TNoRw.getText(),Kdmetoda.getText(),Valid.SetTgl(tanggal.getSelectedItem()+""),ANC.getText(),Pascapersalinan.getText(),BukanRujukan.getText(),RujukanRanap.getText(),RujukanRanap.getText(),
              Total.getText(),Nifas.getText(),Abortus.getText(),Lainnya.getText(),KunjunganUlang.getText(),Jumlah.getText(),Dirujuk.getText()});       
          
          emptTeks1();
          tampil();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
       }else if(Kdmetoda.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih metoda...!!!");
                BtnMetoda.requestFocus();
        }else{            
            Sequel.mengedit("i_rl312","no_rawat='"+TNoRw.getText()+"' and tanggal='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString()+"' and kd_rl='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),17).toString()+"' ","tanggal='"+Valid.SetTgl(tanggal.getSelectedItem()+"")+"',kd_rl='"+Kdmetoda.getText()+"',anc='"+ANC.getText()+"',p_persalinan='"+Pascapersalinan.getText()+"',bukan_rujukan='"+BukanRujukan.getText()+"',rujukan_ranap='"+RujukanRanap.getText()+"',rujukan_ralan='"+RujukanRalan.getText()+"',total='"+Total.getText()+"',p_nifas='"+Nifas.getText()+"',abortus='"+Abortus.getText()+"',lainnya='"+Lainnya.getText()+"',kunj_ulang='"+KunjunganUlang.getText()+"',jumlah='"+Jumlah.getText()+"',dirujuk='"+Dirujuk.getText()+"'");
            tampil();
             emptTeks1();
        }

    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabmodeskpp.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    BtnCloseIn.requestFocus();
                }else {
                       Sequel.queryu("delete from i_rl312 where no_rawat='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString()+
                                    "' and tanggal='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString()+"' and kd_rl='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),17).toString()+"' ");      
                        tampil();
                         emptTeks1();
                       }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
      dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void tanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalKeyPressed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalActionPerformed

    private void tanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tanggalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalItemStateChanged

    private void BtnMetodaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMetodaActionPerformed
            metoda.setLocationRelativeTo(internalFrame1); 
            metoda.setSize(621,346);
            metoda.setVisible(true);
    }//GEN-LAST:event_BtnMetodaActionPerformed

    private void BtnMetodaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnMetodaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnMetodaActionPerformed(null);
        }else{
            Valid.pindah(evt,tanggal,BtnMetoda);
        }
    }//GEN-LAST:event_BtnMetodaKeyPressed

    private void ANCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ANCKeyPressed
        Valid.pindah(evt,tanggal,Pascapersalinan);
    }//GEN-LAST:event_ANCKeyPressed

    private void NifasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NifasKeyPressed
        Valid.pindah(evt,RujukanRalan,Abortus);
    }//GEN-LAST:event_NifasKeyPressed

    private void KunjunganUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KunjunganUlangKeyPressed
        Valid.pindah(evt,Lainnya,Jumlah);
    }//GEN-LAST:event_KunjunganUlangKeyPressed

    private void JumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyPressed
        Valid.pindah(evt,KunjunganUlang,Dirujuk);
    }//GEN-LAST:event_JumlahKeyPressed

    private void DirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirujukKeyPressed
        Valid.pindah(evt,Jumlah,BtnSimpan);
    }//GEN-LAST:event_DirujukKeyPressed

    private void LainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LainnyaKeyPressed
        Valid.pindah(evt,Abortus,KunjunganUlang);
    }//GEN-LAST:event_LainnyaKeyPressed

    private void AbortusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbortusKeyPressed
        Valid.pindah(evt,Nifas,Lainnya);
    }//GEN-LAST:event_AbortusKeyPressed

    private void TotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalKeyPressed
       
    }//GEN-LAST:event_TotalKeyPressed

    private void RujukanRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RujukanRanapKeyPressed
       total();
    }//GEN-LAST:event_RujukanRanapKeyPressed

    private void BukanRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BukanRujukanKeyPressed
        total();
    }//GEN-LAST:event_BukanRujukanKeyPressed

    private void PascapersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PascapersalinanKeyPressed
        Valid.pindah(evt,ANC,BukanRujukan);
    }//GEN-LAST:event_PascapersalinanKeyPressed

    private void RujukanRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RujukanRalanKeyPressed
        total();
    }//GEN-LAST:event_RujukanRalanKeyPressed

    private void KunjunganUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KunjunganUlangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KunjunganUlangActionPerformed

    private void BukanRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BukanRujukanMouseClicked
      total();
    }//GEN-LAST:event_BukanRujukanMouseClicked

    private void RujukanRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RujukanRanapMouseClicked
       total();
    }//GEN-LAST:event_RujukanRanapMouseClicked

    private void RujukanRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RujukanRalanMouseClicked
       total();
    }//GEN-LAST:event_RujukanRalanMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanRL312 dialog = new DlgLaporanRL312(new javax.swing.JFrame(), true);
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
    private widget.TextBox ANC;
    private widget.TextBox Abortus;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnMetoda;
    private widget.Button BtnSimpan;
    private widget.TextBox BukanRujukan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Dirujuk;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jumlah;
    private widget.TextBox Kdmetoda;
    private widget.TextBox KunjunganUlang;
    private widget.Label LCount;
    private widget.TextBox Lainnya;
    private widget.TextBox NMetoda;
    private widget.TextBox Nifas;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Pascapersalinan;
    private widget.TextBox RujukanRalan;
    private widget.TextBox RujukanRanap;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Total;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Tanggal tanggal;
    private widget.Table tbskpp;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabmodeskpp);
        try{
            ps=koneksi.prepareStatement("SELECT ir.no_rawat,p.no_rkm_medis,p.nm_pasien,ir.tanggal,mr.nm_rl,ir.anc,ir.p_persalinan,ir.bukan_rujukan,ir.rujukan_ranap,ir.rujukan_ralan,ir.total,ir.p_nifas,ir.abortus,ir.lainnya,ir.kunj_ulang,ir.jumlah,ir.dirujuk,ir.kd_rl  FROM i_rl312 ir inner join reg_periksa rp on rp.no_rawat = ir.no_rawat INNER  JOIN pasien p on p.no_rkm_medis =rp.no_rkm_medis  INNER  JOIN m_rl312 mr  on " +
                                        "mr.id_rl = ir.kd_rl WHERE ir.tanggal  BETWEEN ? and ? and ir.no_rawat like ? or " +
                                        "ir.tanggal  BETWEEN ? and ? and rp.no_rkm_medis like ? or " + 
                                        "ir.tanggal  BETWEEN ? and ? and p.nm_pasien like ? or " +
                                        "ir.tanggal  BETWEEN ? and ? and rp.no_rkm_medis like ? or " +
                                        "ir.tanggal  between ? and ? and mr.nm_rl like ? order by ir.tanggal asc ");
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
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(15,"%"+TCari.getText()+"%");  
                rs=ps.executeQuery();
                while(rs.next()){
                    tabmodeskpp.addRow(new Object[]{
                                   rs.getString("no_rawat"),
                                   rs.getString("no_rkm_medis"),
                                   rs.getString("nm_pasien"),
                                   rs.getString("tanggal"),
                                   rs.getString("nm_rl"),
                                   rs.getString("anc"),
                                   rs.getString("p_persalinan"),
                                   rs.getString("bukan_rujukan"),
                                   rs.getString("rujukan_ranap"),
                                   rs.getString("rujukan_ralan"),
                                   rs.getString("total"),
                                   rs.getString("p_nifas"),
                                   rs.getString("abortus"),
                                   rs.getString("lainnya"),
                                   rs.getString("kunj_ulang"),
                                   rs.getString("jumlah"),
                                   rs.getString("dirujuk"),
                                   rs.getString("kd_rl")
                                    });
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
            Valid.SetTgl(tanggal,tbskpp.getValueAt(tbskpp.getSelectedRow(),3).toString());
            NMetoda.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),4).toString());
            ANC.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),5).toString());
            Pascapersalinan.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),6).toString());
            BukanRujukan.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),7).toString());
            RujukanRanap.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),8).toString());
            RujukanRalan.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),9).toString());
            Total.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),10).toString());
            Nifas.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),11).toString());
            Abortus.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),12).toString());
            Lainnya.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),13).toString());
            KunjunganUlang.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),14).toString());
            Jumlah.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),15).toString());
            Dirujuk.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),16).toString());
            Kdmetoda.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),17).toString());
        }
    }
      
    
    
        public void emptTeks1() {
            TNoRw.setText("");
            TNoRM.setText("");
            TPasien.setText("");
            tanggal.setDate(new  Date());
            Kdmetoda.setText("");
            NMetoda.setText("");
            ANC.setText("0");
            Pascapersalinan.setText("0");
            BukanRujukan.setText("0");
            RujukanRanap.setText("0");
            RujukanRalan.setText("0");
            Total.setText("0");
            Nifas.setText("0");
            Abortus.setText("0");
            Lainnya.setText("0");
            KunjunganUlang.setText("0");
            Jumlah.setText("0");
            Dirujuk.setText("0");            
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
    
    public void total() {
     if(RujukanRalan.getText().equals("")||(RujukanRanap.getText().equals(""))||(BukanRujukan.getText().equals(""))){
            a=0;
            b=0;
            c=0;
       } else{
        a = Double.parseDouble(BukanRujukan.getText().trim());
        b = Double.parseDouble(RujukanRanap.getText().trim());
        c = Double.parseDouble(RujukanRalan.getText().trim());
        Total.setText(Valid.SetAngka3(a+b+c).trim()); 
     }
    }
       
}




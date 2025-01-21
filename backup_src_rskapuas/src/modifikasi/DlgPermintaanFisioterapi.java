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


/**
 * 
 * @author salimmulyana
 */
public final class DlgPermintaanFisioterapi extends javax.swing.JDialog {
    private final DefaultTableModel tabmodeskpp;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,psobat;
    private ResultSet rs,rsobat;
    private int i=0;
    private String klg="SAUDARA";
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DlgPermintaanFisioterapi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabmodeskpp=new DefaultTableModel(null,new Object[]{
             "No.Rawat","No.R.M.","Nama Pasien","Cara Bayar","Tgl.Kunjungan","Kd. Dokter","DPJP","Kd Poli","Poliklinik","Diagnosa/Asessmen","Anjuran","Validasi","No.Permintaan","Status Pasien"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}{
                
             };
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class,java.lang.Object.class
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
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(170);
            }else if(i==6){
                column.setPreferredWidth(130);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(70);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(70);
            }else if(i==13){
                column.setPreferredWidth(70);
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

        kdpoli = new widget.TextBox();
        kdpng = new widget.TextBox();
        status_lanjut = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbskpp = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnValidasi = new widget.Button();
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
        jLabel8 = new widget.Label();
        Status = new widget.ComboBox();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel77 = new widget.Label();
        kddokter = new widget.TextBox();
        jLabel79 = new widget.Label();
        TPoli = new widget.TextBox();
        DTPTanggal = new widget.Tanggal();
        jLabel78 = new widget.Label();
        TJaminan = new widget.TextBox();
        tdokter = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        Diagnosa = new widget.TextArea();
        scrollPane3 = new widget.ScrollPane();
        Anjuran = new widget.TextArea();
        NoPermintaan = new widget.TextBox();
        jLabel80 = new widget.Label();
        ChkInput = new widget.CekBox();

        kdpoli.setEditable(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });

        kdpng.setEditable(false);
        kdpng.setHighlighter(null);
        kdpng.setName("kdpng"); // NOI18N
        kdpng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpngKeyPressed(evt);
            }
        });

        status_lanjut.setEditable(false);
        status_lanjut.setHighlighter(null);
        status_lanjut.setName("status_lanjut"); // NOI18N
        status_lanjut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                status_lanjutKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Permintaan Fisioterapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        BtnValidasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/unlock_32.png"))); // NOI18N
        BtnValidasi.setMnemonic('H');
        BtnValidasi.setText("Validasi");
        BtnValidasi.setToolTipText("Alt+H");
        BtnValidasi.setName("BtnValidasi"); // NOI18N
        BtnValidasi.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnValidasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnValidasiActionPerformed(evt);
            }
        });
        BtnValidasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnValidasiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnValidasi);

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

        jLabel19.setText("Tgl. Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2023" }));
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

        jLabel8.setText("Status :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel8);

        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Dikirim", "Diterima" }));
        Status.setName("Status"); // NOI18N
        Status.setOpaque(false);
        Status.setPreferredSize(new java.awt.Dimension(90, 23));
        Status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StatusItemStateChanged(evt);
            }
        });
        Status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatusMouseClicked(evt);
            }
        });
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });
        Status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKeyPressed(evt);
            }
        });
        panelGlass9.add(Status);

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
        TPasien.setBounds(355, 10, 240, 23);

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

        jLabel77.setText("Anjuran :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 150, 160, 23);

        kddokter.setEditable(false);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.setPreferredSize(new java.awt.Dimension(150, 24));
        kddokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kddokterActionPerformed(evt);
            }
        });
        FormInput.add(kddokter);
        kddokter.setBounds(170, 40, 70, 24);

        jLabel79.setText(" No. Permintaan :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(540, 40, 100, 23);

        TPoli.setEditable(false);
        TPoli.setHighlighter(null);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPoliKeyPressed(evt);
            }
        });
        FormInput.add(TPoli);
        TPoli.setBounds(620, 10, 111, 23);

        DTPTanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2023" }));
        DTPTanggal.setDisplayFormat("dd-MM-yyyy");
        DTPTanggal.setName("DTPTanggal"); // NOI18N
        DTPTanggal.setOpaque(false);
        DTPTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalItemStateChanged(evt);
            }
        });
        DTPTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKeyPressed(evt);
            }
        });
        FormInput.add(DTPTanggal);
        DTPTanggal.setBounds(870, 10, 100, 23);

        jLabel78.setText("Assesmen / Diagnosa :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 90, 160, 23);

        TJaminan.setEditable(false);
        TJaminan.setHighlighter(null);
        TJaminan.setName("TJaminan"); // NOI18N
        TJaminan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJaminanKeyPressed(evt);
            }
        });
        FormInput.add(TJaminan);
        TJaminan.setBounds(740, 10, 111, 23);

        tdokter.setEditable(false);
        tdokter.setName("tdokter"); // NOI18N
        tdokter.setPreferredSize(new java.awt.Dimension(150, 24));
        tdokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdokterActionPerformed(evt);
            }
        });
        FormInput.add(tdokter);
        tdokter.setBounds(250, 40, 260, 24);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Diagnosa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Diagnosa.setColumns(20);
        Diagnosa.setRows(5);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Diagnosa);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(170, 90, 260, 48);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Anjuran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Anjuran.setColumns(20);
        Anjuran.setRows(5);
        Anjuran.setName("Anjuran"); // NOI18N
        Anjuran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnjuranKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Anjuran);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(170, 150, 260, 48);

        NoPermintaan.setEditable(false);
        NoPermintaan.setHighlighter(null);
        NoPermintaan.setName("NoPermintaan"); // NOI18N
        NoPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoPermintaanKeyPressed(evt);
            }
        });
        FormInput.add(NoPermintaan);
        NoPermintaan.setBounds(640, 40, 160, 23);

        jLabel80.setText(" DPJP :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(80, 40, 80, 23);

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
   
                                  
    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
       
}//GEN-LAST:event_TNoRMKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
            }else if(kddokter.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
            }else{                       
                 Sequel.menyimpan("permintaan_fisioterapi","?,?,?,?,?,?,?,?,?,?",10,new String[]{TNoRw.getText(),kdpoli.getText(),kddokter.getText(),Valid.SetTgl(DTPTanggal.getSelectedItem()+""),kdpng.getText(),Diagnosa.getText(),Anjuran.getText(),status_lanjut.getText(),"Dikirim",NoPermintaan.getText()}); 
             JOptionPane.showMessageDialog(null, "Data berhasil Disimpan ");
             dispose();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnValidasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnValidasiActionPerformed
        if(tabmodeskpp.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    BtnCloseIn.requestFocus();
                }else {
                       Sequel.queryu("update permintaan_fisioterapi set status='Diterima' where noorder='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),12).toString()+"' ");
                        emptTeks1();
                        tampil();
                       }
    }//GEN-LAST:event_BtnValidasiActionPerformed

    private void BtnValidasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnValidasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnValidasiKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
      dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void kddokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kddokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddokterActionPerformed

    private void TPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPoliKeyPressed

    private void DTPTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalItemStateChanged
     
    }//GEN-LAST:event_DTPTanggalItemStateChanged

    private void DTPTanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKeyPressed
      
    }//GEN-LAST:event_DTPTanggalKeyPressed

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

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

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

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbskppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbskppKeyReleased
        if(tbskpp.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getdata();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }
        }
    }//GEN-LAST:event_tbskppKeyReleased

    private void tbskppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbskppMouseClicked
        if(tabmodeskpp.getRowCount()!=0){
            try {
                getdata();
            } catch (java.lang.NullPointerException e){

            }
        }
    }//GEN-LAST:event_tbskppMouseClicked

    private void TJaminanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJaminanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TJaminanKeyPressed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpoliKeyPressed

    private void tdokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tdokterActionPerformed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah2(evt,kddokter,Anjuran);
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void AnjuranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnjuranKeyPressed
        Valid.pindah2(evt,Diagnosa,BtnSimpan);
    }//GEN-LAST:event_AnjuranKeyPressed

    private void StatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StatusItemStateChanged
        tampil();
    }//GEN-LAST:event_StatusItemStateChanged

    private void StatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusMouseClicked

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void StatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusKeyPressed

    private void kdpngKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpngKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpngKeyPressed

    private void NoPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoPermintaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoPermintaanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
         if(tabmodeskpp.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    BtnCloseIn.requestFocus();
                }else {
                       Sequel.queryu("delete from permintaan_fisioterapi where noorder='"+tbskpp.getValueAt(tbskpp.getSelectedRow(),12).toString()+"' ");
 
                        emptTeks1();
                        tampil();
                       }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{Valid.pindah(evt, BtnValidasi, BtnCloseIn);}
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void status_lanjutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_status_lanjutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_lanjutKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanFisioterapi dialog = new DlgPermintaanFisioterapi(new javax.swing.JFrame(), true);
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
    private widget.TextArea Anjuran;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn;
    private widget.Button BtnHapus;
    private widget.Button BtnSimpan;
    private widget.Button BtnValidasi;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTanggal;
    private widget.TextArea Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.TextBox NoPermintaan;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TJaminan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kddokter;
    private widget.TextBox kdpng;
    private widget.TextBox kdpoli;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.TextBox status_lanjut;
    private widget.Table tbskpp;
    private widget.TextBox tdokter;
    // End of variables declaration//GEN-END:variables

     public void tampil() {
        Valid.tabelKosong(tabmodeskpp);
        try{
            ps=koneksi.prepareStatement("SELECT pf.no_rawat,p.no_rkm_medis,p.nm_pasien,pj.png_jawab,pf.tgl_permintaan,pf.kd_dokter,d.nm_dokter,pf.kd_poli,pl.nm_poli,pf.diagnosa,pf.anjuran,pf.status,pf.noorder,pf.stts_lanjut FROM  permintaan_fisioterapi pf inner join reg_periksa rp on rp.no_rawat =pf.no_rawat inner JOIN pasien p on p.no_rkm_medis =rp.no_rkm_medis " +
                                        "INNER JOIN dokter d on d.kd_dokter =pf.kd_dokter INNER JOIN poliklinik pl on pl.kd_poli =pf.kd_poli INNER JOIN penjab pj on pj.kd_pj =pf.kd_png WHERE " +
                                        "pf.tgl_permintaan BETWEEN ? AND ? and p.no_rkm_medis LIKE ? and pf.status like '%"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%' or " +
                                        "pf.tgl_permintaan BETWEEN ? AND ? and p.nm_pasien  LIKE ? and pf.status like '%"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%' or " +
                                        "pf.tgl_permintaan BETWEEN ? AND ? and d.nm_dokter LIKE ? and pf.status like '%"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%' or " +
                                        "pf.tgl_permintaan BETWEEN ? AND ? and pl.nm_poli LIKE ? and pf.status like '%"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%' or " +
                                        "pf.tgl_permintaan BETWEEN ? AND ? and pf.diagnosa  LIKE ? and pf.status like '%"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%' order by pf.noorder asc ");
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
                                   rs.getString(14)
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



 
      private void getdata() {
        if(tbskpp.getSelectedRow()!= -1){
            TNoRw.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),0).toString());
            isPsien();
            ispenjab();
            ispoli();
            isdokter();
            Diagnosa.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),9).toString());
            Anjuran.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),10).toString());
            NoPermintaan.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),12).toString());
            Valid.SetTgl(DTPTanggal,tbskpp.getValueAt(tbskpp.getSelectedRow(),4).toString());
            status_lanjut.setText(tbskpp.getValueAt(tbskpp.getSelectedRow(),13).toString());
        }
    }
      
   
    
    
    public void emptTeks1() {
            TNoRw.setText("");
            TNoRM.setText("");
            TPasien.setText("");
            TPoli.setText("");
            TJaminan.setText("");
            kddokter.setText("");
            kdpng.setText("");
            kdpoli.setText("");
            tdokter.setText("");
            kddokter.setText("");
            Anjuran.setText("");
            Diagnosa.setText("");
            DTPTanggal.setDate(new Date());
            NoPermintaan.setText("");
            status_lanjut.setText("");
            
    }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien); 
    }
    
    private void ispenjab() {
        Sequel.cariIsi("SELECT png_jawab FROM penjab INNER JOIN reg_periksa ON reg_periksa.kd_pj=penjab.kd_pj WHERE reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TJaminan);
        Sequel.cariIsi("SELECT kd_pj FROM reg_periksa WHERE reg_periksa.no_rawat='"+TNoRw.getText()+"' ",kdpng);
    }
    
    private void ispoli() {
        Sequel.cariIsi("SELECT kd_poli FROM reg_periksa WHERE reg_periksa.no_rawat='"+TNoRw.getText()+"' ",kdpoli);
        Sequel.cariIsi("SELECT nm_poli FROM poliklinik WHERE kd_poli='"+kdpoli.getText()+"' ",TPoli);
    }
    
    private void isdokter() {
        Sequel.cariIsi("SELECT kd_dokter FROM reg_periksa WHERE reg_periksa.no_rawat='"+TNoRw.getText()+"' ",kddokter);
        Sequel.cariIsi("SELECT nm_dokter FROM dokter WHERE kd_dokter='"+kddokter.getText()+"' ",tdokter);
    }
    
    public void setNoRw(String norwt, String stts_lanjut) {
        TNoRw.setText(norwt);
        status_lanjut.setText(stts_lanjut);
        isRawat();
        isPsien();
        ispenjab();
        ispoli();
        isdokter();
        Diagnosa.setText(Sequel.cariIsi("select penilaian from pemeriksaan_ralan where no_rawat='"+norwt+"' order by tgl_perawatan desc, jam_rawat desc limit 1"));
        ChkInput.setSelected(true);
        isForm();
        Scroll.setVisible(false);
        BtnValidasi.setVisible(false);
        BtnHapus.setVisible(false);
        autoNomor();
    }
        
    public void setform ()
     {
        ChkInput.setSelected(false);
        isForm();
        Scroll.setVisible(true);
        BtnValidasi.setVisible(true);
        BtnHapus.setVisible(true);
        BtnSimpan.setVisible(false);
        tampil();
        emptTeks1();
        
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,250));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    
    
    private void autoNomor() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(permintaan_fisioterapi.noorder,4),signed)),0) from permintaan_fisioterapi where permintaan_fisioterapi.tgl_permintaan='"+Valid.SetTgl(DTPTanggal.getSelectedItem()+"")+"' ","PF"+Valid.SetTgl(DTPTanggal.getSelectedItem()+"").replaceAll("-",""),4,NoPermintaan);           
    }
      
}




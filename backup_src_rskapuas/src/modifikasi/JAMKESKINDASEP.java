package modifikasi;

import bridging.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import keuangan.DlgKamar;


/**
 *
 * @author dosen
 */
public class JAMKESKINDASEP extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private String link="",requestJson="",URL="",user="",utc="",kdppkkemenkes="";
    public  DlgKamar kamar=new DlgKamar(null,false);
    private DlgCariPoli3 poli=new DlgCariPoli3(null,false);
    public  DlgCariDokter dokter=new DlgCariDokter(null,false);
    private BPJSCekReferensiFaskes faskes=new BPJSCekReferensiFaskes(null,false);
    

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public JAMKESKINDASEP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
                "No. Jaminan","Tanggal Kunjungan","No.Rawat","No.Kartu","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Keterangan","Status Kunjungan",
                "Kode Dokter","Nama Dokter/Sepesialis","Kode Poli/Bangsal","Nama Poli/Bangsal","Faskes Perujuk","Kirim Ke INACBG"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(75);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(80);
            }else if(i==7){
                column.setPreferredWidth(40);
            }else if(i==8){
                column.setPreferredWidth(125);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(70);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(70);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        NoRawat.setDocument(new batasInput((byte)15).getKata(NoRawat));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        Keterangan.setDocument(new batasInput((byte)50).getKata(Keterangan));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
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
        
        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dokter.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){                    
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        kamar.bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(kamar.bangsal.getTable().getSelectedRow()!= -1){                   
                        KdBangsal.setText(kamar.bangsal.getTable().getValueAt(kamar.bangsal.getTable().getSelectedRow(),0).toString());
                        NmBangsal.setText(kamar.bangsal.getTable().getValueAt(kamar.bangsal.getTable().getSelectedRow(),1).toString());    
                    NmBangsal.requestFocus();
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
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(faskes.getTable().getSelectedRow()!= -1){  
                        FaskesPerujuk.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
                        FaskesPerujuk.requestFocus();                       
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
        
        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        try {
            kdppkkemenkes=Sequel.cariIsi("select kode_ppkkemenkes from setting");
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat = new javax.swing.JMenuItem();
        MnKirimINACBG = new javax.swing.JMenuItem();
        MnResume = new javax.swing.JMenuItem();
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
        panelGlass10 = new widget.panelisi();
        jLabel8 = new widget.Label();
        DTPTgl1 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        DTPTgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoKartu = new widget.TextBox();
        DTPReg = new widget.Tanggal();
        jLabel10 = new widget.Label();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel15 = new widget.Label();
        NoJaminan = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        Keterangan = new widget.TextBox();
        jLabel18 = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        jLabel19 = new widget.Label();
        KdBangsal = new widget.TextBox();
        NmBangsal = new widget.TextBox();
        BtnBangsal = new widget.Button();
        BtnFaskes = new widget.Button();
        FaskesPerujuk = new widget.TextBox();
        jLabel20 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Surat Jaminan Jamkeskinda");
        MnSurat.setActionCommand("Cetak Jaminan");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        MnKirimINACBG.setBackground(new java.awt.Color(255, 255, 254));
        MnKirimINACBG.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimINACBG.setForeground(new java.awt.Color(50, 50, 50));
        MnKirimINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimINACBG.setText("Kiirm Ke INACBG");
        MnKirimINACBG.setActionCommand("Cetak Jaminan");
        MnKirimINACBG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimINACBG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimINACBG.setName("MnKirimINACBG"); // NOI18N
        MnKirimINACBG.setPreferredSize(new java.awt.Dimension(200, 26));
        MnKirimINACBG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimINACBGActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimINACBG);

        MnResume.setBackground(new java.awt.Color(255, 255, 254));
        MnResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResume.setForeground(new java.awt.Color(50, 50, 50));
        MnResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResume.setText("Resume Ralan");
        MnResume.setActionCommand("Cetak Jaminan");
        MnResume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResume.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResume.setName("MnResume"); // NOI18N
        MnResume.setPreferredSize(new java.awt.Dimension(200, 26));
        MnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResumeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnResume);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Jaminan Jamkeskinda ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setText("Periode :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        DTPTgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-02-2026" }));
        DTPTgl1.setDisplayFormat("dd-MM-yyyy");
        DTPTgl1.setName("DTPTgl1"); // NOI18N
        DTPTgl1.setOpaque(false);
        DTPTgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTgl1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTgl1ItemStateChanged(evt);
            }
        });
        DTPTgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTgl1KeyPressed(evt);
            }
        });
        panelGlass10.add(DTPTgl1);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("s.d");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass10.add(jLabel23);

        DTPTgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-02-2026" }));
        DTPTgl2.setDisplayFormat("dd-MM-yyyy");
        DTPTgl2.setName("DTPTgl2"); // NOI18N
        DTPTgl2.setOpaque(false);
        DTPTgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTgl2KeyPressed(evt);
            }
        });
        panelGlass10.add(DTPTgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setText("No.Kartu :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(236, 10, 60, 23);

        NoRawat.setEditable(false);
        NoRawat.setHighlighter(null);
        NoRawat.setName("NoRawat"); // NOI18N
        FormInput.add(NoRawat);
        NoRawat.setBounds(94, 10, 130, 23);

        jLabel9.setText("DPJP :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 100, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(184, 100, 160, 23);

        NoKartu.setEditable(false);
        NoKartu.setHighlighter(null);
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(300, 10, 110, 23);

        DTPReg.setForeground(new java.awt.Color(50, 70, 50));
        DTPReg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-02-2026" }));
        DTPReg.setDisplayFormat("dd-MM-yyyy");
        DTPReg.setName("DTPReg"); // NOI18N
        DTPReg.setOpaque(false);
        DTPReg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPRegItemStateChanged(evt);
            }
        });
        DTPReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPRegKeyPressed(evt);
            }
        });
        FormInput.add(DTPReg);
        DTPReg.setBounds(394, 70, 95, 23);

        jLabel10.setText("Tanggal Kunjungan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(280, 70, 110, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(94, 100, 87, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(347, 100, 28, 23);

        jLabel11.setText("Unit/Poli :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(394, 100, 60, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(458, 100, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(531, 100, 165, 23);

        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(699, 100, 28, 23);

        jLabel15.setText("No.Jaminan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 70, 90, 23);

        NoJaminan.setEditable(false);
        NoJaminan.setHighlighter(null);
        NoJaminan.setName("NoJaminan"); // NOI18N
        NoJaminan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoJaminanKeyPressed(evt);
            }
        });
        FormInput.add(NoJaminan);
        NoJaminan.setBounds(94, 70, 170, 23);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(197, 40, 230, 23);

        NoRM.setEditable(false);
        NoRM.setHighlighter(null);
        NoRM.setName("NoRM"); // NOI18N
        FormInput.add(NoRM);
        NoRM.setBounds(94, 40, 100, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(495, 40, 95, 23);

        jLabel13.setText("Tgl.Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(429, 40, 62, 23);

        jLabel16.setText("J.K. :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(588, 40, 40, 23);

        JK.setEditable(false);
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(632, 40, 95, 23);

        jLabel17.setText("Keterangan :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(420, 10, 65, 23);

        Keterangan.setHighlighter(null);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        FormInput.add(Keterangan);
        Keterangan.setBounds(489, 10, 207, 23);

        jLabel18.setText("Jns.Pelayanan :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(490, 70, 90, 23);

        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ranap", "Ralan" }));
        JenisPelayanan.setSelectedIndex(1);
        JenisPelayanan.setToolTipText("");
        JenisPelayanan.setName("JenisPelayanan"); // NOI18N
        JenisPelayanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayananItemStateChanged(evt);
            }
        });
        JenisPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayananKeyPressed(evt);
            }
        });
        FormInput.add(JenisPelayanan);
        JenisPelayanan.setBounds(590, 70, 100, 23);

        jLabel19.setText("Bangsal :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(720, 100, 60, 23);

        KdBangsal.setEditable(false);
        KdBangsal.setHighlighter(null);
        KdBangsal.setName("KdBangsal"); // NOI18N
        FormInput.add(KdBangsal);
        KdBangsal.setBounds(780, 100, 70, 23);

        NmBangsal.setEditable(false);
        NmBangsal.setHighlighter(null);
        NmBangsal.setName("NmBangsal"); // NOI18N
        FormInput.add(NmBangsal);
        NmBangsal.setBounds(850, 100, 165, 23);

        BtnBangsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBangsal.setMnemonic('X');
        BtnBangsal.setToolTipText("Alt+X");
        BtnBangsal.setName("BtnBangsal"); // NOI18N
        BtnBangsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBangsalActionPerformed(evt);
            }
        });
        BtnBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBangsalKeyPressed(evt);
            }
        });
        FormInput.add(BtnBangsal);
        BtnBangsal.setBounds(1020, 100, 28, 23);

        BtnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnFaskes.setMnemonic('X');
        BtnFaskes.setToolTipText("Alt+X");
        BtnFaskes.setName("BtnFaskes"); // NOI18N
        BtnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFaskesActionPerformed(evt);
            }
        });
        BtnFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnFaskesKeyPressed(evt);
            }
        });
        FormInput.add(BtnFaskes);
        BtnFaskes.setBounds(980, 70, 28, 23);

        FaskesPerujuk.setEditable(false);
        FaskesPerujuk.setHighlighter(null);
        FaskesPerujuk.setName("FaskesPerujuk"); // NOI18N
        FormInput.add(FaskesPerujuk);
        FaskesPerujuk.setBounds(810, 70, 165, 23);

        jLabel20.setText("Faskes Perujuk :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(710, 70, 90, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DTPRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPRegKeyPressed
        Valid.pindah(evt,Keterangan,JenisPelayanan);
}//GEN-LAST:event_DTPRegKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(NoRawat.getText().trim().equals("")||NoKartu.getText().trim().equals("")){
            Valid.textKosong(NoRawat,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(JenisPelayanan.getSelectedIndex()==1 && (KdPoli.getText().trim().equals("")||NmPoli.getText().trim().equals(""))){
            Valid.textKosong(KdPoli,"Poli");
        }else if(JenisPelayanan.getSelectedIndex()==0 && (KdBangsal.getText().trim().equals("")||NmBangsal.getText().trim().equals(""))){
            Valid.textKosong(KdPoli,"Bangsal");
        }else if(Keterangan.getText().trim().equals("")){
            Valid.textKosong(Keterangan,"Keterangan");
        }else{
             if(JenisPelayanan.getSelectedIndex()==1){        
                if(Sequel.menyimpantf("jamkeskinda_jaminan","?,?,?,?,?,?,?,?,?,?,?,?","No.Surat",12,new String[]{
                        NoJaminan.getText(),NoRawat.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),NoKartu.getText(),JenisPelayanan.getSelectedItem().toString(),KdDokter.getText(),NmDokter.getText(),KdPoli.getText(),NmPoli.getText(),Keterangan.getText(),FaskesPerujuk.getText(),"Belum"
                    })==true){
                    emptTeks();
                    tampil();
                }
             }else if (JenisPelayanan.getSelectedIndex()==0){
                if(Sequel.menyimpantf("jamkeskinda_jaminan","?,?,?,?,?,?,?,?,?,?,?,?","No.Surat",12,new String[]{
                           NoJaminan.getText(),NoRawat.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),NoKartu.getText(),JenisPelayanan.getSelectedItem().toString(),KdDokter.getText(),NmDokter.getText(),KdBangsal.getText(),NmBangsal.getText(),Keterangan.getText(),FaskesPerujuk.getText(),"Belum"
                       })==true){
                       emptTeks();
                       tampil();
                   }            
             }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,NoJaminan,BtnBatal);
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
     if(Sequel.cariInacbg(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data sudah masuk dalan pengajuan klaim..!!");              
        }else{
            if(tbObat.getSelectedRow()!= -1){
                Sequel.meghapus("jamkeskinda_jaminan","no_jaminan",NoJaminan.getText());
                    tampil();
                    emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau dihapus..!!");
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

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            if(R1.isSelected()==true){
//                Map<String, Object> param = new HashMap<>(); 
//                param.put("namars",akses.getnamars());
//                param.put("alamatrs",akses.getalamatrs());
//                param.put("kotars",akses.getkabupatenrs());
//                param.put("propinsirs",akses.getpropinsirs());
//                param.put("kontakrs",akses.getkontakrs());
//                param.put("emailrs",akses.getemailrs());   
//                param.put("logo",Sequel.cariGambar("select logo from setting")); 
//                Valid.MyReportqry("rptBridgingSuratPRI.jasper","report","::[ Data Surat PRI VClaim ]::",
//                    "select bridging_surat_pri_bpjs.no_rawat,bridging_surat_pri_bpjs.no_kartu,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
//                    "pasien.jk,bridging_surat_pri_bpjs.diagnosa,bridging_surat_pri_bpjs.tgl_surat,bridging_surat_pri_bpjs.no_surat,"+
//                    "bridging_surat_pri_bpjs.tgl_rencana,bridging_surat_pri_bpjs.kd_dokter_bpjs,bridging_surat_pri_bpjs.nm_dokter_bpjs,"+
//                    "bridging_surat_pri_bpjs.kd_poli_bpjs,bridging_surat_pri_bpjs.nm_poli_bpjs from reg_periksa inner join bridging_surat_pri_bpjs "+
//                    "on bridging_surat_pri_bpjs.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "where bridging_surat_pri_bpjs.tgl_surat between '"+Valid.SetTgl(DTPTanggalSurat1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalSurat2.getSelectedItem()+"")+"' "+
//                    (TCari.getText().trim().equals("")?"":"and (bridging_surat_pri_bpjs.no_rawat like '%"+TCari.getText().trim()+"%' or "+
//                    "bridging_surat_pri_bpjs.no_kartu like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_pri_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
//                    "bridging_surat_pri_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_pri_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
//                    "order by bridging_surat_pri_bpjs.tgl_surat",param);
//            }else if(R2.isSelected()==true){
//                Map<String, Object> param = new HashMap<>(); 
//                param.put("namars",akses.getnamars());
//                param.put("alamatrs",akses.getalamatrs());
//                param.put("kotars",akses.getkabupatenrs());
//                param.put("propinsirs",akses.getpropinsirs());
//                param.put("kontakrs",akses.getkontakrs());
//                param.put("emailrs",akses.getemailrs());   
//                param.put("logo",Sequel.cariGambar("select logo from setting")); 
//                Valid.MyReportqry("rptBridgingSuratPRI.jasper","report","::[ Data Surat PRI VClaim ]::",
//                    "select bridging_surat_pri_bpjs.no_rawat,bridging_surat_pri_bpjs.no_kartu,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
//                    "pasien.jk,bridging_surat_pri_bpjs.diagnosa,bridging_surat_pri_bpjs.tgl_surat,bridging_surat_pri_bpjs.no_surat,"+
//                    "bridging_surat_pri_bpjs.tgl_rencana,bridging_surat_pri_bpjs.kd_dokter_bpjs,bridging_surat_pri_bpjs.nm_dokter_bpjs,"+
//                    "bridging_surat_pri_bpjs.kd_poli_bpjs,bridging_surat_pri_bpjs.nm_poli_bpjs from reg_periksa inner join bridging_surat_pri_bpjs "+
//                    "on bridging_surat_pri_bpjs.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "where bridging_surat_pri_bpjs.tgl_rencana between '"+Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem()+"")+"' "+
//                    (TCari.getText().trim().equals("")?"":"and (bridging_surat_pri_bpjs.no_rawat like '%"+TCari.getText().trim()+"%' or "+
//                    "bridging_surat_pri_bpjs.no_kartu like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_pri_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
//                    "bridging_surat_pri_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_pri_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
//                    "order by bridging_surat_pri_bpjs.tgl_rencana",param);
//            }
//                
//        }
//        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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
            Valid.pindah(evt, BtnCari, NoKartu);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    dokter.isCek();        
    dokter.TCari.requestFocus();
    dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
        Valid.pindah(evt,JenisPelayanan,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(NoRawat.getText().trim().equals("")||NoKartu.getText().trim().equals("")){
            Valid.textKosong(NoRawat,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(JenisPelayanan.getSelectedIndex()==1 && (KdPoli.getText().trim().equals("")||NmPoli.getText().trim().equals(""))){
            Valid.textKosong(KdPoli,"Poli");
        }else if(JenisPelayanan.getSelectedIndex()==0 && (KdBangsal.getText().trim().equals("")||NmBangsal.getText().trim().equals(""))){
            Valid.textKosong(KdPoli,"Bangsal");
        }else if(Sequel.cariInacbg(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data sudah masuk dalan pengajuan klaim..!!");              
        }else{
            if(JenisPelayanan.getSelectedIndex()==1){    
                    if(Sequel.mengedittf("jamkeskinda_jaminan","no_jaminan=?","no_rawat=?,tgl_kunjungan=?,no_kartu=?,status=?,kd_dokter=?,nm_dokter=?,kd_poli=?,nm_poli=?,keterangan=?,faskes_perujuk=?",11,new String[]{
                            NoRawat.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),NoKartu.getText(),JenisPelayanan.getSelectedItem().toString(),KdDokter.getText(),NmDokter.getText(),KdPoli.getText(),NmPoli.getText(),Keterangan.getText(),FaskesPerujuk.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                        })==true){
                        emptTeks();
                        tampil();
                    }
                }else if(JenisPelayanan.getSelectedIndex()==0){
                    if(Sequel.mengedittf("jamkeskinda_jaminan","no_jaminan=?","no_rawat=?,tgl_kunjungan=?,no_kartu=?,status=?,kd_dokter=?,nm_dokter=?,kd_poli=?,nm_poli=?,keterangan=?,faskes_perujuk=?",11,new String[]{
                            NoRawat.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),NoKartu.getText(),JenisPelayanan.getSelectedItem().toString(),KdDokter.getText(),NmDokter.getText(),KdBangsal.getText(),NmBangsal.getText(),Keterangan.getText(),FaskesPerujuk.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                        })==true){
                        emptTeks();
                        tampil();
                    }
                }     
            }                      
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void NoJaminanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoJaminanKeyPressed
        Valid.pindah(evt,DTPReg,JenisPelayanan);
    }//GEN-LAST:event_NoJaminanKeyPressed

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnDokter,JenisPelayanan);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.isCek();        
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select pemda from gambar"));
            param.put("logo2",Sequel.cariGambar("select logo from setting"));
            param.put("nojaminan",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("logo3",Sequel.cariGambar("select jamkeskinda from gambar"));
            param.put("nourut",Sequel.cariIsi("SELECT no_reg FROM reg_periksa WHERE no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' AND no_rawat NOT IN (SELECT no_rawat FROM kamar_inap WHERE no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"')"));
            param.put("petugas",Sequel.cariIsi("select nama from pegawai where nik ='"+akses.getkode()+"' "));
            Valid.MyReport("rptJaminanJamkeskinda.jasper","report","::[ Surat Jaminan Jamkeskinda ]::",param);              
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat PRI yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }  
    }//GEN-LAST:event_MnSuratActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt,TCari,DTPReg);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        if(JenisPelayanan.getSelectedIndex()==0){
            KdBangsal.setVisible(true);
            NmBangsal.setVisible(true);
            BtnBangsal.setVisible(true);
            jLabel19.setVisible(true);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            jLabel11.setVisible(false);
            BtnPoli.setVisible(false);
            BtnFaskes.setEnabled(false);
        }else if(JenisPelayanan.getSelectedIndex()==1){
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            jLabel11.setVisible(true);
            BtnPoli.setVisible(true);
            KdBangsal.setVisible(false);
            NmBangsal.setVisible(false);
            BtnBangsal.setVisible(false);
            jLabel19.setVisible(false);
            BtnFaskes.setEnabled(true);
        }
         autoNumber();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void JenisPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyPressed
        Valid.pindah(evt,DTPReg,BtnDokter);
    }//GEN-LAST:event_JenisPelayananKeyPressed

    private void BtnBangsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBangsalActionPerformed
        kamar.bangsal.isCek();
        kamar.bangsal.emptTeks();        
        kamar.bangsal.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kamar.bangsal.setLocationRelativeTo(internalFrame1);
        kamar.bangsal.setVisible(true);
    }//GEN-LAST:event_BtnBangsalActionPerformed

    private void BtnBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBangsalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBangsalKeyPressed

    private void DTPTgl1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTgl1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTgl1ItemStateChanged

    private void DTPTgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTgl1KeyPressed

    private void DTPTgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTgl2KeyPressed

    private void DTPRegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPRegItemStateChanged
        autoNumber();
    }//GEN-LAST:event_DTPRegItemStateChanged

    private void BtnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFaskesActionPerformed
        faskes.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        faskes.setLocationRelativeTo(internalFrame1);        
        faskes.setVisible(true);
    }//GEN-LAST:event_BtnFaskesActionPerformed

    private void BtnFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnFaskesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnFaskesKeyPressed

    private void MnKirimINACBGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimINACBGActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbObat.requestFocus();
        } else if(Sequel.cariInacbg(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data sudah masuk dalan pengajuan klaim..!!");              
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString(), tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(), "JAMKESKINDA", "5");
            diklaim.setVisible(true);   
        }
    }//GEN-LAST:event_MnKirimINACBGActionPerformed

    private void MnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResumeActionPerformed
         if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbObat.requestFocus();
        } else {
                 if (JenisPelayanan.getSelectedIndex() == 1) {
                    Map<String, Object> param = new HashMap<>(); 
                      param.put("namars",akses.getnamars());
                      param.put("alamatrs",akses.getalamatrs());
                      param.put("kotars",akses.getkabupatenrs());
                      param.put("propinsirs",akses.getpropinsirs());
                      param.put("kontakrs",akses.getkontakrs());
                      param.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                      param.put("logo2",Sequel.cariGambar("select logo from setting"));
                      param.put("norawat",tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
                      param.put("pro1",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='1'"));
                      param.put("pro2",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='2'"));    
                      param.put("pro3",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='3'")); 
                      param.put("pro4",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='4'")); 
                      param.put("pro5",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='5'")); 
                      param.put("pro6",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='6'")); 
                      param.put("pro7",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='7'")); 
                      param.put("pro8",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and b.prioritas='8'"));
                      param.put("ttlobat",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and status='Obat'"));
                      param.put("tarifrs",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and status in ('Ralan Dokter','Ralan Paramedis','Ralan Dokter Paramedis')"));
                      param.put("carapulang",Sequel.cariIsiBanyak("SELECT IF(stts='Sudah','Atas Persetujuan Dokter',IF(stts='Dirujuk','Rujuk',IF(stts='Dirawat','MRS',''))) FROM reg_periksa WHERE no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' "));
                      param.put("nojaminan",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                      param.put("nourut",Sequel.cariIsi("SELECT no_reg FROM reg_periksa WHERE no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' AND no_rawat NOT IN (SELECT no_rawat FROM kamar_inap WHERE no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"')"));
                      param.put("petugas",Sequel.cariIsi("select nama from pegawai where nik ='"+akses.getkode()+"' ")); 
                      Valid.saveToPDF("rptJamkeskindaResume.jasper","report",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"-1rsumjkd",param);
              } 
                                     
       }  
                 
    }//GEN-LAST:event_MnResumeActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            JAMKESKINDASEP dialog = new JAMKESKINDASEP(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBangsal;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnFaskes;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPReg;
    private widget.Tanggal DTPTgl1;
    private widget.Tanggal DTPTgl2;
    private widget.TextBox FaskesPerujuk;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.TextBox KdBangsal;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.TextBox Keterangan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnKirimINACBG;
    private javax.swing.JMenuItem MnResume;
    private javax.swing.JMenuItem MnSurat;
    private widget.TextBox NmBangsal;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private widget.TextBox NoJaminan;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel23;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
                ps=koneksi.prepareStatement(
                    "select jj.no_jaminan,jj.no_rawat,jj.no_kartu,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
                    "pasien.jk,jj.keterangan,jj.tgl_kunjungan,jj.status,"+
                    "jj.kd_dokter,jj.nm_dokter,"+
                    "jj.kd_poli,jj.status_inacbg,jj.nm_poli,jj.faskes_perujuk from jamkeskinda_jaminan jj "+
                    "inner join reg_periksa on jj.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where jj.tgl_kunjungan between ? and ? "+(TCari.getText().trim().equals("")?"":"and (jj.no_rawat like ? or jj.no_jaminan like ? or "+
                    "jj.no_kartu like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                    "jj.nm_poli like ? or jj.status like ? or jj.status_inacbg like ? or jj.nm_dokter like ?)")+
                    "order by jj.no_jaminan asc");
                try {
                    ps.setString(1,Valid.SetTgl(DTPTgl1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPTgl2.getSelectedItem()+""));
                    if(!TCari.getText().trim().equals("")){
                        ps.setString(3,"%"+TCari.getText().trim()+"%");
                        ps.setString(4,"%"+TCari.getText().trim()+"%");
                        ps.setString(5,"%"+TCari.getText().trim()+"%");
                        ps.setString(6,"%"+TCari.getText().trim()+"%");
                        ps.setString(7,"%"+TCari.getText().trim()+"%");
                        ps.setString(8,"%"+TCari.getText().trim()+"%");
                        ps.setString(9,"%"+TCari.getText().trim()+"%");
                        ps.setString(10,"%"+TCari.getText().trim()+"%");
                        ps.setString(11,"%"+TCari.getText().trim()+"%");
                    }

                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabMode.addRow(new Object[]{
                            rs.getString("no_jaminan"),rs.getString("tgl_kunjungan"),rs.getString("no_rawat"),rs.getString("no_kartu"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                            rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("keterangan"),rs.getString("status"),
                            rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("kd_poli"),rs.getString("nm_poli"),rs.getString("faskes_perujuk"),rs.getString("status_inacbg")
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
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        } 
        LCount.setText(""+tabMode.getRowCount());
    }


    public void emptTeks() {
        NoRawat.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        Keterangan.setText("");
        DTPReg.setDate(new Date());
        NoJaminan.setText("");
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        KdBangsal.setText("");
        NmBangsal.setText("");
        FaskesPerujuk.setText("");
        JenisPelayanan.setSelectedIndex(1);
    }
   

    
    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            NoJaminan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            Valid.SetTgl(DTPReg,tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            NoKartu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            NoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            NmPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().replaceAll("P","PEREMPUAN").replaceAll("L","LAKI-LAKI"));
            Keterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            JenisPelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            if(JenisPelayanan.getSelectedIndex()==1){
                KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
                NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            }else if (JenisPelayanan.getSelectedIndex()==0){
                KdBangsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
                NmBangsal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
                
            }
            FaskesPerujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
                       
        }
    }
    
    public void setNoRm(String norawat,String nokartu,String norm,String namapasien,String tanggallahir,String jk,String ket, String kddokter,String namadokter,
            String kodepoli,String namapoli, String stts) {
        NoRawat.setText(norawat);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        Keterangan.setText(ket);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        JK.setText(jk.replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
        TCari.setText(nokartu);
        KdPoli.setText(kodepoli);
        NmPoli.setText(namapoli);
        KdDokter.setText(kddokter);
        NmDokter.setText(namadokter);
        JenisPelayanan.setSelectedItem(stts);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }
    
//    public void setNoRm(String norawat,String nokartu,String norm,String namapasien,String tanggallahir,String jk,String diagnosa,String nosep) {
//        NoRawat.setText(norawat);
//        NoKartu.setText(nokartu);
//        NoRM.setText(norm);
//        NmPasien.setText(namapasien);
//        TglLahir.setText(tanggallahir);
//        JK.setText(jk.replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
//        TCari.setText(nokartu);
//        ChkInput.setSelected(true);
//        Keterangan.setText(diagnosa);
//        isForm();
//        tampil();
//    }
    
    public void setNoRm(String norm) {
        TCari.setText(norm);
        ChkInput.setSelected(false);
        isForm();
        tampil();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,156));
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
        BtnSimpan.setEnabled(akses.getbpjs_sep());
        BtnHapus.setEnabled(akses.getbpjs_sep());
        BtnPrint.setEnabled(akses.getbpjs_sep());
        BtnEdit.setEnabled(akses.getbpjs_sep()); 
        
        if(JenisPelayanan.getSelectedIndex()==0){
            KdBangsal.setVisible(true);
            NmBangsal.setVisible(true);
            BtnBangsal.setVisible(true);
            jLabel19.setVisible(true);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            jLabel11.setVisible(false);
            BtnPoli.setVisible(false);
            BtnFaskes.setEnabled(false);
        }else if(JenisPelayanan.getSelectedIndex()==1){
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            jLabel11.setVisible(true);
            BtnPoli.setVisible(true);
            KdBangsal.setVisible(false);
            NmBangsal.setVisible(false);
            BtnBangsal.setVisible(false);
            jLabel19.setVisible(false);
            BtnFaskes.setEnabled(true);
        }
         autoNumber();
        
    }

    public JTable getTable(){
        return tbObat;
    }
    
    
    
    
  public void autoNumber() {
    try {
        if (DTPReg.getSelectedItem() == null) {
            return;
        }

        // ambil string tanggal dari DTPReg (dd-MM-yyyy)
        String tglText = DTPReg.getSelectedItem().toString();

        // parse ke Date
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
        Date tglReg = sdfInput.parse(tglText);

        // ambil bulan & tahun dengan Calendar (AMAN)
        Calendar cal = Calendar.getInstance();
        cal.setTime(tglReg);

//        String bulan = String.format("%02d", cal.get(Calendar.MONTH) + 1);
//        String tahun = String.valueOf(cal.get(Calendar.YEAR));
        
        String bulan = Valid.SetTgl(DTPReg.getSelectedItem().toString()).substring(5, 7);
        String tahun = Valid.SetTgl(DTPReg.getSelectedItem().toString()).substring(0, 4);

        String status = (JenisPelayanan.getSelectedIndex() == 0) ? "RI" : "RJ";
        String prefix = kdppkkemenkes + bulan + tahun ;

        String sql =
            "SELECT IFNULL(MAX(CAST(SUBSTRING(no_jaminan, LENGTH(no_jaminan)-3, 4) AS UNSIGNED)),0) AS urut " +
            "FROM jamkeskinda_jaminan " +
            "WHERE MONTH(tgl_kunjungan)=? " +
            "AND YEAR(tgl_kunjungan)=? " +
            "AND no_jaminan LIKE ?";

        PreparedStatement ps = koneksi.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(bulan));
        ps.setInt(2, Integer.parseInt(tahun));
        ps.setString(3, prefix + "%");

        ResultSet rs = ps.executeQuery();

        int nomor = 1;
        if (rs.next()) {
            nomor = rs.getInt("urut") + 1;
        }

        String nomorUrut = String.format("%04d", nomor);
        NoJaminan.setText(prefix + status +nomorUrut);

    } catch (Exception e) {
        System.out.println("AutoNumber Error: " + e.getMessage());
    }
}


      
}

package modifikasi;
import fungsi.WarnaTable;
import fungsi.WarnaTableVedika;
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
 * @author dosen
 */
public class DlgVedikaKlaim extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps,ps1,ps2,ps3,psdx,pspd;
    private ResultSet rs,rs1,rs2,rs3,rsdx,rspd;
    private String norawat = "", noSEPnya = "",cekdatanya="",tanggalfisterakhr="",finger="",datatriase="",dxralan="",pdralan="",catatan="";
    private Date tgl = new Date();
    private int i=0,z=0,row=0;
    private String[] nosep,norm,nmpasien,nmunit,tglmasuk,tglkeluar,statusklaim,tglupdate,norwt,petugasklaim,kdpetugas,kdunit,keterangan,catatan_vedika;
    private boolean[] pilih;
    private boolean sukses=true;
    private DlgCariPoli3 poli=new DlgCariPoli3(null,false);

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public DlgVedikaKlaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 10);
        setSize(459, 539);

         tabMode=new DefaultTableModel(null,new Object[]{
               "P","No. SEP", "No. Rawat", "No. RM","Nama Pasien","Nama Unit/Ruangan","Tgl. Reg./Msk.","Tgl. Klr./Plg.","Status Klaim","Tgl.Update Status","Petugas Klaim","kd.petugas","Kod. Unit","Keterangan","Catatan"
            }){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if(colIndex==0) {
                    a=true;
                }
                return a;
             }
            
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
             };
             
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbData.setModel(tabMode);
        tbData.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 15; i++) {
            TableColumn column = tbData.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(130);
            }else if(i==2){
                column.setPreferredWidth(105);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(85);
            }else if(i==6){
                column.setPreferredWidth(85);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(80);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }                 
        }
        tbData.setDefaultRenderer(Object.class, new WarnaTableVedika());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(poli.getTable().getSelectedRow()!= -1){
                            CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());                   
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
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup1 = new javax.swing.JPopupMenu();
        MnCoding = new javax.swing.JMenuItem();
        MnInputInacbg = new javax.swing.JMenuItem();
        MnVerif = new javax.swing.JMenuItem();
        MnPending = new javax.swing.JMenuItem();
        MnFinalKlaim = new javax.swing.JMenuItem();
        MnRevisi = new javax.swing.JMenuItem();
        MnSudahRevisi = new javax.swing.JMenuItem();
        MnCetakResume = new javax.swing.JMenuItem();
        MnCetakBillng = new javax.swing.JMenuItem();
        MnGabungPDF = new javax.swing.JMenuItem();
        MnBersihkanceklis = new javax.swing.JMenuItem();
        MnTampilDiagnosa = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbData = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnCariPoli = new widget.Button();
        jLabel24 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel8 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel25 = new widget.Label();
        cmbJnsPoli = new widget.ComboBox();

        Popup1.setName("Popup1"); // NOI18N

        MnCoding.setBackground(new java.awt.Color(242, 242, 242));
        MnCoding.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCoding.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCoding.setText("Set Status Coding");
        MnCoding.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCoding.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCoding.setIconTextGap(8);
        MnCoding.setName("MnCoding"); // NOI18N
        MnCoding.setPreferredSize(new java.awt.Dimension(180, 25));
        MnCoding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCodingBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnCoding);

        MnInputInacbg.setBackground(new java.awt.Color(242, 242, 242));
        MnInputInacbg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputInacbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputInacbg.setText("Set Status Input INACBG");
        MnInputInacbg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputInacbg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputInacbg.setIconTextGap(8);
        MnInputInacbg.setName("MnInputInacbg"); // NOI18N
        MnInputInacbg.setPreferredSize(new java.awt.Dimension(180, 25));
        MnInputInacbg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputInacbgBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnInputInacbg);

        MnVerif.setBackground(new java.awt.Color(242, 242, 242));
        MnVerif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerif.setText("Set Status Verif");
        MnVerif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnVerif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnVerif.setIconTextGap(8);
        MnVerif.setName("MnVerif"); // NOI18N
        MnVerif.setPreferredSize(new java.awt.Dimension(180, 25));
        MnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnVerif);

        MnPending.setBackground(new java.awt.Color(242, 242, 242));
        MnPending.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPending.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPending.setText("Set Status Pending");
        MnPending.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPending.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPending.setIconTextGap(8);
        MnPending.setName("MnPending"); // NOI18N
        MnPending.setPreferredSize(new java.awt.Dimension(180, 25));
        MnPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendingBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnPending);

        MnFinalKlaim.setBackground(new java.awt.Color(242, 242, 242));
        MnFinalKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFinalKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFinalKlaim.setText("Set Status Final Klaim");
        MnFinalKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFinalKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFinalKlaim.setIconTextGap(8);
        MnFinalKlaim.setName("MnFinalKlaim"); // NOI18N
        MnFinalKlaim.setPreferredSize(new java.awt.Dimension(180, 25));
        MnFinalKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFinalKlaimBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnFinalKlaim);

        MnRevisi.setBackground(new java.awt.Color(242, 242, 242));
        MnRevisi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRevisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRevisi.setText("Set Status Direvisi");
        MnRevisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRevisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRevisi.setIconTextGap(8);
        MnRevisi.setName("MnRevisi"); // NOI18N
        MnRevisi.setPreferredSize(new java.awt.Dimension(180, 25));
        MnRevisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRevisiBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnRevisi);

        MnSudahRevisi.setBackground(new java.awt.Color(242, 242, 242));
        MnSudahRevisi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudahRevisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSudahRevisi.setText("Set Status Sudah Revisi");
        MnSudahRevisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSudahRevisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSudahRevisi.setIconTextGap(8);
        MnSudahRevisi.setName("MnSudahRevisi"); // NOI18N
        MnSudahRevisi.setPreferredSize(new java.awt.Dimension(180, 25));
        MnSudahRevisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahRevisiBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnSudahRevisi);

        MnCetakResume.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakResume.setText("Download Resume Ralan / Ranap");
        MnCetakResume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResume.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResume.setIconTextGap(8);
        MnCetakResume.setName("MnCetakResume"); // NOI18N
        MnCetakResume.setPreferredSize(new java.awt.Dimension(180, 25));
        MnCetakResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResumeBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnCetakResume);

        MnCetakBillng.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakBillng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakBillng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakBillng.setText("Download Billing PDF");
        MnCetakBillng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakBillng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakBillng.setIconTextGap(8);
        MnCetakBillng.setName("MnCetakBillng"); // NOI18N
        MnCetakBillng.setPreferredSize(new java.awt.Dimension(180, 25));
        MnCetakBillng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakBillngBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnCetakBillng);

        MnGabungPDF.setBackground(new java.awt.Color(242, 242, 242));
        MnGabungPDF.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGabungPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGabungPDF.setText("Gabung PDF");
        MnGabungPDF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGabungPDF.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGabungPDF.setIconTextGap(8);
        MnGabungPDF.setName("MnGabungPDF"); // NOI18N
        MnGabungPDF.setPreferredSize(new java.awt.Dimension(180, 25));
        MnGabungPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGabungPDFBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnGabungPDF);

        MnBersihkanceklis.setBackground(new java.awt.Color(242, 242, 242));
        MnBersihkanceklis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBersihkanceklis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBersihkanceklis.setText("Bersihkan ceklist");
        MnBersihkanceklis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBersihkanceklis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBersihkanceklis.setIconTextGap(8);
        MnBersihkanceklis.setName("MnBersihkanceklis"); // NOI18N
        MnBersihkanceklis.setPreferredSize(new java.awt.Dimension(180, 25));
        MnBersihkanceklis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBersihkanceklisBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnBersihkanceklis);

        MnTampilDiagnosa.setBackground(new java.awt.Color(255, 255, 254));
        MnTampilDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTampilDiagnosa.setForeground(new java.awt.Color(50, 50, 50));
        MnTampilDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTampilDiagnosa.setText("Tampilkan Diagnosa dan Prosedur");
        MnTampilDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTampilDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTampilDiagnosa.setName("MnTampilDiagnosa"); // NOI18N
        MnTampilDiagnosa.setPreferredSize(new java.awt.Dimension(220, 26));
        MnTampilDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTampilDiagnosaActionPerformed(evt);
            }
        });
        Popup1.add(MnTampilDiagnosa);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Vedika Kllaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbData.setAutoCreateRowSorter(true);
        tbData.setToolTipText("Silahkan klik untuk memilih data yang akan diproses klaim");
        tbData.setComponentPopupMenu(Popup1);
        tbData.setName("tbData"); // NOI18N
        tbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataMouseClicked(evt);
            }
        });
        tbData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbData);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(150, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        jLabel16.setText("Poliklinik :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(CrPoli);

        BtnCariPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariPoli.setMnemonic('5');
        BtnCariPoli.setToolTipText("ALt+5");
        BtnCariPoli.setName("BtnCariPoli"); // NOI18N
        BtnCariPoli.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPoliActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnCariPoli);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Status Klaim : ");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel24);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Validasi", "Coding", "Verif", "InputInacbg", "Pending", "Final Klaim", "Revisi", "Sudah Revisi" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(130, 23));
        cmbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStatusKeyPressed(evt);
            }
        });
        panelGlass9.add(cmbStatus);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Reg./Masuk : ");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel8);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2025" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(tgl1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-04-2025" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(tgl2);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jenis Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass10.add(jLabel23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "JALAN", "INAP" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(70, 23));
        cmbJnsRawat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJnsRawatItemStateChanged(evt);
            }
        });
        cmbJnsRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJnsRawatKeyPressed(evt);
            }
        });
        panelGlass10.add(cmbJnsRawat);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Poliklinik : ");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass10.add(jLabel25);

        cmbJnsPoli.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsPoli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Poli Ralan", "Fisioterapi", "IGD/Ponek" }));
        cmbJnsPoli.setName("cmbJnsPoli"); // NOI18N
        cmbJnsPoli.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbJnsPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJnsPoliKeyPressed(evt);
            }
        });
        panelGlass10.add(cmbJnsPoli);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        CrPoli.setText("");
        cmbJnsRawat.setSelectedIndex(0);
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void cmbJnsRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJnsRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJnsRawatKeyPressed

    private void MnCodingBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCodingBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                if (cmbJnsRawat.getSelectedIndex() == 0) {
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                           Sequel.mengedit("vedika_ralan","no_rawat=? and no_sep=?","status='Coding',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){ 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                           Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Coding',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnCodingBtnPrintActionPerformed

    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if(evt.getClickCount()==2){
                i=tbData.getSelectedColumn();
                if(i==2){
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgCatatanVedika cp=new DlgCatatanVedika(null, false);
                            cp.setNoRm(tbData.getValueAt(tbData.getSelectedRow(),1).toString(),tbData.getValueAt(tbData.getSelectedRow(),2).toString(),cmbJnsRawat.getSelectedItem().toString());
                            cp.setLocationRelativeTo(internalFrame1);
                            cp.setVisible(true);
                        }                           
                        this.setCursor(Cursor.getDefaultCursor());
                }
            }
    }//GEN-LAST:event_tbDataMouseClicked

    private void tbDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
            if(evt.getKeyCode()==KeyEvent.VK_C){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgCatatanVedika cp=new DlgCatatanVedika(null, false);
                cp.setNoRm(tbData.getValueAt(tbData.getSelectedRow(),1).toString(),tbData.getValueAt(tbData.getSelectedRow(),2).toString(),cmbJnsRawat.getSelectedItem().toString());
                cp.setLocationRelativeTo(internalFrame1);
                cp.setVisible(true);
               }
             this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_tbDataKeyPressed

    private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusKeyPressed

    private void MnInputInacbgBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputInacbgBtnPrintActionPerformed
       if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='InputInacbg',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='InputInacbg',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnInputInacbgBtnPrintActionPerformed

    private void MnVerifBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifBtnPrintActionPerformed
          if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='Verif',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Verif',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnVerifBtnPrintActionPerformed

    private void MnPendingBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendingBtnPrintActionPerformed
          if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='Pending',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Pending',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnPendingBtnPrintActionPerformed

    private void MnFinalKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFinalKlaimBtnPrintActionPerformed
          if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='Final Klaim',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Final Klaim',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnFinalKlaimBtnPrintActionPerformed

    private void MnRevisiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRevisiBtnPrintActionPerformed
       if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='Revisi',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Revisi',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnRevisiBtnPrintActionPerformed

    private void MnCetakResumeBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResumeBtnPrintActionPerformed
          if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) {
                     if(cmbJnsPoli.getSelectedIndex()==0){
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true") && tbData.getValueAt(i,8).toString().equals("Final Klaim")){                             
                                        try {
                                              Map<String, Object> param = new HashMap<>(); 
                                                param.put("namars",akses.getnamars());
                                                param.put("alamatrs",akses.getalamatrs());
                                                param.put("kotars",akses.getkabupatenrs());
                                                param.put("propinsirs",akses.getpropinsirs());
                                                param.put("kontakrs",akses.getkontakrs());
                                                param.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                                                param.put("logo2",Sequel.cariGambar("select logo from setting"));
                                                param.put("norawat",tbData.getValueAt(i,2).toString());
                                                param.put("pro1",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='1'"));
                                                param.put("pro2",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='2'"));    
                                                param.put("pro3",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='3'")); 
                                                param.put("pro4",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='4'")); 
                                                param.put("pro5",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='5'")); 
                                                param.put("pro6",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='6'")); 
                                                param.put("pro7",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='7'")); 
                                                param.put("pro8",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='8'"));
                                                param.put("ttlobat",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status='Obat'"));
                                                param.put("tarifrs",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status in ('Ralan Dokter','Ralan Paramedis','Ralan Dokter Paramedis')"));
                                                param.put("carapulang",Sequel.cariIsiBanyak("SELECT IF(stts='Sudah','Atas Persetujuan Dokter',IF(stts='Dirujuk','Rujuk',IF(stts='Dirawat','MRS',''))) FROM reg_periksa WHERE no_rawat='"+tbData.getValueAt(i,2).toString()+"' "));
                                                param.put("kontrol",Sequel.cariIsiBanyak("SELECT tgl_rencana FROM bridging_surat_kontrol_bpjs WHERE no_sep='"+tbData.getValueAt(i,1).toString()+"'"));
                                                 Valid.saveToPDF("rptBridgingResume1.jasper","report",tbData.getValueAt(i,1).toString(),param);

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0); 
                                     
                            }  
                            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data . . .");
                     }else if (cmbJnsPoli.getSelectedIndex()==1){
                                  //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true") && tbData.getValueAt(i,8).toString().equals("Final Klaim")&& tbData.getValueAt(i,13).toString().equals("Data Resume Tidak Lengkap")){                             
                                        try {
                                                tanggalfisterakhr=Sequel.cariIsi("SELECT rp.tgl_registrasi from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1");
                                                 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                                 Map<String, Object> param1 = new HashMap<>(); 
                                                     param1.put("namars",akses.getnamars());
                                                     param1.put("alamatrs",akses.getalamatrs());
                                                     param1.put("kotars",akses.getkabupatenrs());
                                                     param1.put("propinsirs",akses.getpropinsirs());
                                                     param1.put("kontakrs",akses.getkontakrs());
                                                     param1.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                                                     param1.put("logo2",Sequel.cariGambar("select logo from setting"));
                                                     param1.put("norawat",tbData.getValueAt(i,2).toString());
                                                     param1.put("pro1",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='1'"));
                                                     param1.put("pro2",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='2'"));    
                                                     param1.put("pro3",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='3'")); 
                                                     param1.put("pro4",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='4'")); 
                                                     param1.put("pro5",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='5'")); 
                                                     param1.put("pro6",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='6'")); 
                                                     param1.put("pro7",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='7'")); 
                                                     param1.put("pro8",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='8'"));
                                                     param1.put("ttlobat",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status='Obat'"));
                                                     param1.put("tarifrs",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status in ('Ralan Dokter','Ralan Paramedis','Ralan Dokter Paramedis')"));
                                                     param1.put("carapulang",Sequel.cariIsiBanyak("SELECT IF(stts='Sudah','Atas Persetujuan Dokter',IF(stts='Dirujuk','Rujuk',IF(stts='Dirawat','MRS',''))) FROM reg_periksa WHERE no_rawat='"+tbData.getValueAt(i,2).toString()+"' "));
                                                     param1.put("kontrol",Sequel.cariIsiBanyak("SELECT tgl_rencana FROM bridging_surat_kontrol_bpjs WHERE no_sep='"+nosep+"'"));
                                                     param1.put("subjekdr",Sequel.cariIsiBanyak("SELECT pr.keluhan from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("objekdr",Sequel.cariIsiBanyak("SELECT pr.pemeriksaan from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("pemeriksaandr",Sequel.cariIsiBanyak("SELECT pr.penilaian from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("plandr",Sequel.cariIsiBanyak("SELECT pr.rtl from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("jumlahkunjungan",Sequel.cariIsiBanyak("SELECT COUNT(pr.no_rawat) from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT p.nip FROM petugas p) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"'  AND pr.tgl_perawatan BETWEEN '"+tanggalfisterakhr+"' and '"+tbData.getValueAt(i,6).toString()+"'  ORDER BY pr.no_rawat"));
                                                     param1.put("instruksidr",Sequel.cariIsiBanyak("SELECT pr.instruksi from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("pemeriksaanpr",Sequel.cariIsiBanyak("SELECT pr.penilaian  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT p.nip FROM petugas p) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                     param1.put("tglkunjungandr",tanggalfisterakhr); 
                                                      Valid.saveToPDF("rptBridgingFisioPetugas.jasper","report",tbData.getValueAt(i,1).toString(),param1);
                                                     this.setCursor(Cursor.getDefaultCursor());
                                              
                                                     
                                            } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    tbData.setValueAt(false,i,0); 

                     }else if (tbData.getValueAt(i,0).toString().equals("true") && tbData.getValueAt(i,8).toString().equals("Final Klaim")&& tbData.getValueAt(i,13).toString().equals("Download")){   
                                 try {
                                                 tanggalfisterakhr=Sequel.cariIsi("SELECT rp.tgl_registrasi from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT d.kd_dokter  FROM dokter d) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1");
                                                 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                                 Map<String, Object> param2 = new HashMap<>(); 
                                                     param2.put("namars",akses.getnamars());
                                                     param2.put("alamatrs",akses.getalamatrs());
                                                     param2.put("kotars",akses.getkabupatenrs());
                                                     param2.put("propinsirs",akses.getpropinsirs());
                                                     param2.put("kontakrs",akses.getkontakrs());
                                                     param2.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                                                     param2.put("logo2",Sequel.cariGambar("select logo from setting"));
                                                     param2.put("norawat",tbData.getValueAt(i,2).toString());
                                                     param2.put("pro1",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='1'"));
                                                     param2.put("pro2",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='2'"));    
                                                     param2.put("pro3",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='3'")); 
                                                     param2.put("pro4",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='4'")); 
                                                     param2.put("pro5",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='5'")); 
                                                     param2.put("pro6",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='6'")); 
                                                     param2.put("pro7",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='7'")); 
                                                     param2.put("pro8",Sequel.cariIsiBanyak("SELECT b.kode FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='8'"));
                                                     param2.put("ttlobat",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status='Obat'"));
                                                     param2.put("tarifrs",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status in ('Ralan Dokter','Ralan Paramedis','Ralan Dokter Paramedis')"));
                                                     param2.put("carapulang",Sequel.cariIsiBanyak("SELECT IF(stts='Sudah','Atas Persetujuan Dokter',IF(stts='Dirujuk','Rujuk',IF(stts='Dirawat','MRS',''))) FROM reg_periksa WHERE no_rawat='"+tbData.getValueAt(i,2).toString()+"' "));
                                                     param2.put("kontrol",Sequel.cariIsiBanyak("SELECT tgl_rencana FROM bridging_surat_kontrol_bpjs WHERE no_sep='"+nosep+"'"));
//                                                     param2.put("anamnesa",Sequel.cariIsiBanyak("SELECT pr.diagnosis_fisio from penilaian_fisioterapi  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("ujifungsi",Sequel.cariIsiBanyak("SELECT CONCAT(pr.palpasi,' , ',pr.luas_gerak_sendi) from penilaian_fisioterapi  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("penunjang",Sequel.cariIsiBanyak("SELECT pr.penunjang from penilaian_fisioterapi  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("jumlahkunjungan",Sequel.cariIsiBanyak("SELECT COUNT(pr.no_rawat) from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT p.nip FROM petugas p) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"'  AND pr.tgl_perawatan BETWEEN '"+tanggalfisterakhr+"' and '"+tbData.getValueAt(i,6).toString()+"'  ORDER BY pr.no_rawat"));
//                                                     param2.put("tatalaksana",Sequel.cariIsiBanyak("SELECT pr.rencana_terapi from penilaian_fisioterapi  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("anjuran",Sequel.cariIsiBanyak("SELECT pr.rekomedasi from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("kesimpulan",Sequel.cariIsiBanyak("SELECT pr.kesimpulan from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
//                                                     param2.put("rekomendasi",Sequel.cariIsiBanyak("SELECT pr.rekomedasi from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
                                                      param2.put("anamnesa",Sequel.cariIsiBanyak("SELECT pr.keluhan  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT d.kd_dokter  FROM dokter d) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                      param2.put("ujifungsi",Sequel.cariIsiBanyak("SELECT pr.pemeriksaan  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT d.kd_dokter  FROM dokter d) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                      param2.put("penunjang",Sequel.cariIsiBanyak("SELECT pr.penunjang from penilaian_fisioterapi  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
                                                      param2.put("jumlahkunjungan",Sequel.cariIsiBanyak("SELECT COUNT(pr.no_rawat) from reg_periksa rp INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat WHERE rp.kd_poli='IRM' and pr.nip in (SELECT p.nip FROM petugas p) and rp.no_rkm_medis='"+tbData.getValueAt(i,3).toString()+"'  AND pr.tgl_perawatan BETWEEN '"+tanggalfisterakhr+"' and '"+tbData.getValueAt(i,6).toString()+"'  ORDER BY pr.no_rawat"));
                                                      param2.put("tatalaksana",Sequel.cariIsiBanyak("SELECT pr.rtl  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT d.kd_dokter  FROM dokter d) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                      param2.put("anjuran",Sequel.cariIsiBanyak("SELECT pr.instruksi  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT d.kd_dokter  FROM dokter d) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                      param2.put("evaluasi",Sequel.cariIsiBanyak("SELECT pr.evaluasi  FROM  pemeriksaan_ralan pr WHERE pr.nip in (SELECT d.kd_dokter  FROM dokter d) and pr.no_rawat ='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tgl_perawatan DESC, pr.jam_rawat desc limit 1"));
                                                      param2.put("kesimpulan",Sequel.cariIsiBanyak("SELECT pr.kesimpulan from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
                                                      param2.put("rekomendasi",Sequel.cariIsiBanyak("SELECT pr.rekomedasi from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
                                                      param2.put("hasilkfr",Sequel.cariIsiBanyak("SELECT pr.hasil_didapat from uji_fungsi_kfr  pr WHERE pr.no_rawat='"+tbData.getValueAt(i,2).toString()+"' ORDER  BY  pr.tanggal DESC limit 1"));
                                                      param2.put("tglkunjungandr",tanggalfisterakhr); 
                                                      psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }
                                                     param2.put("dxmedis",dxralan);
                                                      Valid.saveToPDF("rptBridgingResumeFISLONG.jasper","report",tbData.getValueAt(i,1).toString(),param2);
                                                     this.setCursor(Cursor.getDefaultCursor());    
                                                
                                            } catch (Exception e) {
                                                System.out.println("Notifikasi : "+e);
                                            } 
                                         }tbData.setValueAt(false,i,0); 
                                        
                                } JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data . . .");
                     }else if (cmbJnsPoli.getSelectedIndex()==2){
                                //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true") && tbData.getValueAt(i,8).toString().equals("Final Klaim")&& tbData.getValueAt(i,13).toString().equals("Download")){                             
                                        try {
                                            
                                                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                                            if(Sequel.cariInteger("select count(no_rawat) from data_triase_igddetail_skala1 where no_rawat = '"+tbData.getValueAt(i,2).toString()+"' ")>0){
                                                                Map<String, Object> param = new HashMap<>(); 
                                                                param.put("namars",akses.getnamars());
                                                                param.put("alamatrs",akses.getalamatrs());
                                                                param.put("kotars",akses.getkabupatenrs());
                                                                param.put("propinsirs",akses.getpropinsirs());
                                                                param.put("kontakrs",akses.getkontakrs());
                                                                param.put("emailrs",akses.getemailrs());   
                                                                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                                                                try {
                                                                    ps=koneksi.prepareStatement(
                                                                        "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                                                                        "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                                                                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                                                        "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                                                                        "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdprimer inner join data_triase_igd "+
                                                                        "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                                                                        "data_triase_igd.no_rawat=data_triase_igdprimer.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                                                                        "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdprimer.nik "+
                                                                        "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                                                                    try {
                                                                        ps.setString(1,tbData.getValueAt(i,2).toString());
                                                                        rs=ps.executeQuery();
                                                                        if(rs.next()){
                                                                            param.put("norawat",rs.getString("no_rawat"));
                                                                            param.put("norm",rs.getString("no_rkm_medis"));
                                                                            param.put("namapasien",rs.getString("nm_pasien"));
                                                                            param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                                                            param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                                                            param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                                                            param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                                                            param.put("caradatang",rs.getString("cara_masuk"));
                                                                            param.put("macamkasus",rs.getString("macam_kasus"));
                                                                            param.put("keluhanutama",rs.getString("keluhan_utama"));
                                                                            param.put("kebutuhankhusus",rs.getString("kebutuhan_khusus"));
                                                                            param.put("plan",rs.getString("plan"));
                                                                            param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                                                            param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                                                            param.put("dokter",rs.getString("nama"));
                                                                            param.put("catatan",rs.getString("catatan"));
                                                                            param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                                                            param.put("askelutama",Sequel.cariIsi("SELECT keluhan_utama FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asttv",Sequel.cariIsiBanyak2("SELECT CONCAT('gcs :',' ',gcs) as gcs,CONCAT(', kesadaran :',' ',kesadaran) as kesadaran,CONCAT(', td :',' ',td) as td,CONCAT(', nadi :',' ',nadi) nadi,CONCAT(', rr :',' ',rr) rr,CONCAT(', suhu :',' ',suhu) suhu,CONCAT(', spo :',' ',spo) spo,CONCAT(', bb :',' ',bb) bb,CONCAT(', tb:',' ',tb) tb,CONCAT(', kepala :',' ',kepala) kepala,CONCAT(', mata :',' ',mata) mata,CONCAT(', gigi :',' ',gigi) gigi,CONCAT(', leher :',' ',leher) leher,CONCAT(', thoraks :',' ',thoraks) thoraks,CONCAT(', abdomen :',' ',abdomen) abdomen,CONCAT(' ekstremitas :',' ',ekstremitas) ekstremitas,CONCAT(', genital :',' ',genital) genital,CONCAT(', ket_fisik :',' ',ket_fisik) ket_fisik FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asdiagnosa",Sequel.cariIsi("SELECT diagnosis FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("astatalaksanan",Sequel.cariIsi("SELECT REPLACE(REPLACE(tata, CHAR(13), ' '), CHAR(10), ' ') FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                                                            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                                                            ps2=koneksi.prepareStatement(
                                                                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                                                                "from master_triase_pemeriksaan inner join master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                                                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala1.kode_pemeriksaan and "+
                                                                                "master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where data_triase_igddetail_skala1.no_rawat=? "+
                                                                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                                                            try {
                                                                                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                                                                ps2.setString(1,rs.getString("no_rawat"));
                                                                                rs2=ps2.executeQuery();
                                                                                while(rs2.next()){
                                                                                    datatriase="";
                                                                                    ps3=koneksi.prepareStatement(
                                                                                        "select master_triase_skala1.pengkajian_skala1 from master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                                                                        "on master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where "+
                                                                                        "master_triase_skala1.kode_pemeriksaan=? and data_triase_igddetail_skala1.no_rawat=? "+
                                                                                        "order by data_triase_igddetail_skala1.kode_skala1");
                                                                                    try {
                                                                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                                                        ps3.setString(2,rs.getString("no_rawat"));
                                                                                        rs3=ps3.executeQuery();
                                                                                        while(rs3.next()){
                                                                                            datatriase=rs3.getString("pengkajian_skala1")+", "+datatriase;
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        System.out.println("Notif : "+e);
                                                                                    } finally{
                                                                                        if(rs3!=null){
                                                                                            rs3.close();
                                                                                        }
                                                                                        if(ps3!=null){
                                                                                            ps3.close();
                                                                                        }
                                                                                    }

                                                                                    if(datatriase.endsWith(", ")){
                                                                                        datatriase = datatriase.substring(0,datatriase.length() - 2);
                                                                                    }
                                                                                    Sequel.menyimpan2("temporary","'"+row+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                                                                    row++;

                                                                                     psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }
                                                                                      pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                                                                                        pdralan="";
                                                                                        try {
                                                                                            pspd.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rspd=pspd.executeQuery();
                                                                                            while(rspd.next()){
                                                                                                pdralan=rspd.getString("kode")+", "+pdralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rspd!=null){
                                                                                                rspd.close();
                                                                                            }
                                                                                        }   
                                                                                   param.put("icd9",pdralan);    
                                                                                   param.put("icd10",dxralan); 
                                                                                   param.put("alamatip",akses.getalamatip());  
                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Notif : "+e);
                                                                            } finally{
                                                                                if(rs2!=null){
                                                                                    rs2.close();
                                                                                }
                                                                                if(ps2!=null){
                                                                                    ps2.close();
                                                                                }
                                                                            }
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
                                                         
                                                                      Valid.saveToPDF("rptLembarTriaseSkala1sep.jasper","report",tbData.getValueAt(i,1).toString(),param);
                                                               
                                                                

                                                            }else if(Sequel.cariInteger("select count(no_rawat) from data_triase_igddetail_skala2 where no_rawat = '"+tbData.getValueAt(i,2).toString()+"' ")>0){
                                                                Map<String, Object> param = new HashMap<>(); 
                                                                param.put("namars",akses.getnamars());
                                                                param.put("alamatrs",akses.getalamatrs());
                                                                param.put("kotars",akses.getkabupatenrs());
                                                                param.put("propinsirs",akses.getpropinsirs());
                                                                param.put("kontakrs",akses.getkontakrs());
                                                                param.put("emailrs",akses.getemailrs());   
                                                                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                                                                try {
                                                                    ps=koneksi.prepareStatement(
                                                                        "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                                                                        "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                                                                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                                                        "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                                                                        "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdprimer inner join data_triase_igd "+
                                                                        "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                                                                        "data_triase_igd.no_rawat=data_triase_igdprimer.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                                                                        "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdprimer.nik "+
                                                                        "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                                                                    try {
                                                                        ps.setString(1,tbData.getValueAt(i,2).toString());
                                                                        rs=ps.executeQuery();
                                                                        if(rs.next()){
                                                                            param.put("norawat",rs.getString("no_rawat"));
                                                                            param.put("norm",rs.getString("no_rkm_medis"));
                                                                            param.put("namapasien",rs.getString("nm_pasien"));
                                                                            param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                                                            param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                                                            param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                                                            param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                                                            param.put("caradatang",rs.getString("cara_masuk"));
                                                                            param.put("macamkasus",rs.getString("macam_kasus"));
                                                                            param.put("keluhanutama",rs.getString("keluhan_utama"));
                                                                            param.put("kebutuhankhusus",rs.getString("kebutuhan_khusus"));
                                                                            param.put("plan",rs.getString("plan"));
                                                                            param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                                                            param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                                                            param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                                                            param.put("dokter",rs.getString("nama"));
                                                                            param.put("catatan",rs.getString("catatan"));
                                                                            param.put("askelutama",Sequel.cariIsi("SELECT keluhan_utama FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asttv",Sequel.cariIsiBanyak2("SELECT CONCAT('gcs :',' ',gcs) as gcs,CONCAT(', kesadaran :',' ',kesadaran) as kesadaran,CONCAT(', td :',' ',td) as td,CONCAT(', nadi :',' ',nadi) nadi,CONCAT(', rr :',' ',rr) rr,CONCAT(', suhu :',' ',suhu) suhu,CONCAT(', spo :',' ',spo) spo,CONCAT(', bb :',' ',bb) bb,CONCAT(', tb:',' ',tb) tb,CONCAT(', kepala :',' ',kepala) kepala,CONCAT(', mata :',' ',mata) mata,CONCAT(', gigi :',' ',gigi) gigi,CONCAT(', leher :',' ',leher) leher,CONCAT(', thoraks :',' ',thoraks) thoraks,CONCAT(', abdomen :',' ',abdomen) abdomen,CONCAT(' ekstremitas :',' ',ekstremitas) ekstremitas,CONCAT(', genital :',' ',genital) genital,CONCAT(', ket_fisik :',' ',ket_fisik) ket_fisik FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asdiagnosa",Sequel.cariIsi("SELECT diagnosis FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("astatalaksanan",Sequel.cariIsi("SELECT REPLACE(REPLACE(tata, CHAR(13), ' '), CHAR(10), ' ') FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                                                            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                                                            ps2=koneksi.prepareStatement(
                                                                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                                                                "from master_triase_pemeriksaan inner join master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                                                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala2.kode_pemeriksaan and "+
                                                                                "master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where data_triase_igddetail_skala2.no_rawat=? "+
                                                                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                                                            try {
                                                                                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                                                                ps2.setString(1,rs.getString("no_rawat"));
                                                                                rs2=ps2.executeQuery();
                                                                                while(rs2.next()){
                                                                                    datatriase="";
                                                                                    ps3=koneksi.prepareStatement(
                                                                                        "select master_triase_skala2.pengkajian_skala2 from master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                                                                        "on master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where "+
                                                                                        "master_triase_skala2.kode_pemeriksaan=? and data_triase_igddetail_skala2.no_rawat=? "+
                                                                                        "order by data_triase_igddetail_skala2.kode_skala2");
                                                                                    try {
                                                                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                                                        ps3.setString(2,rs.getString("no_rawat"));
                                                                                        rs3=ps3.executeQuery();
                                                                                        while(rs3.next()){
                                                                                            datatriase=rs3.getString("pengkajian_skala2")+", "+datatriase;
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        System.out.println("Notif : "+e);
                                                                                    } finally{
                                                                                        if(rs3!=null){
                                                                                            rs3.close();
                                                                                        }
                                                                                        if(ps3!=null){
                                                                                            ps3.close();
                                                                                        }
                                                                                    }

                                                                                    if(datatriase.endsWith(", ")){
                                                                                        datatriase = datatriase.substring(0,datatriase.length() - 2);
                                                                                    }
                                                                                    Sequel.menyimpan2("temporary","'"+row+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                                                                    row++;

                                                                                     psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }

                                                                                        pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                                                                                        pdralan="";
                                                                                        try {
                                                                                            pspd.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rspd=pspd.executeQuery();
                                                                                            while(rspd.next()){
                                                                                                pdralan=rspd.getString("kode")+", "+pdralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rspd!=null){
                                                                                                rspd.close();
                                                                                            }
                                                                                        }   
                                                                                         param.put("icd9",pdralan);  
                                                                                         param.put("icd10",dxralan); 
                                                                                         param.put("alamatip",akses.getalamatip()); 

                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Notif : "+e);
                                                                            } finally{
                                                                                if(rs2!=null){
                                                                                    rs2.close();
                                                                                }
                                                                                if(ps2!=null){
                                                                                    ps2.close();
                                                                                }
                                                                            }
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
                                              
                                                                      Valid.saveToPDF("rptLembarTriaseSkala2sep.jasper","report",tbData.getValueAt(i,1).toString(),param);
                                                          
                                                               

                                                            }else  if(Sequel.cariInteger("select count(no_rawat) from data_triase_igddetail_skala3 where no_rawat = '"+tbData.getValueAt(i,2).toString()+"' ")>0){
                                                                Map<String, Object> param = new HashMap<>(); 
                                                                param.put("namars",akses.getnamars());
                                                                param.put("alamatrs",akses.getalamatrs());
                                                                param.put("kotars",akses.getkabupatenrs());
                                                                param.put("propinsirs",akses.getpropinsirs());
                                                                param.put("kontakrs",akses.getkontakrs());
                                                                param.put("emailrs",akses.getemailrs());   
                                                                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                                                                try {
                                                                    ps=koneksi.prepareStatement(
                                                                        "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                                                                        "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                                                                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                                                        "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                                                                        "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                                                                        "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                                                                        "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                                                                        "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                                                                        "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                                                                    try {
                                                                        ps.setString(1,tbData.getValueAt(i,2).toString());
                                                                        rs=ps.executeQuery();
                                                                        if(rs.next()){
                                                                            param.put("norawat",rs.getString("no_rawat"));
                                                                            param.put("norm",rs.getString("no_rkm_medis"));
                                                                            param.put("namapasien",rs.getString("nm_pasien"));
                                                                            param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                                                            param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                                                            param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                                                            param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                                                            param.put("caradatang",rs.getString("cara_masuk"));
                                                                            param.put("macamkasus",rs.getString("macam_kasus"));
                                                                            param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                                                            param.put("plan",rs.getString("plan"));
                                                                            param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                                                            param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                                                            param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                                                            param.put("dokter",rs.getString("nama"));
                                                                            param.put("catatan",rs.getString("catatan"));
                                                                            param.put("askelutama",Sequel.cariIsi("SELECT keluhan_utama FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asttv",Sequel.cariIsiBanyak2("SELECT CONCAT('gcs :',' ',gcs) as gcs,CONCAT(', kesadaran :',' ',kesadaran) as kesadaran,CONCAT(', td :',' ',td) as td,CONCAT(', nadi :',' ',nadi) nadi,CONCAT(', rr :',' ',rr) rr,CONCAT(', suhu :',' ',suhu) suhu,CONCAT(', spo :',' ',spo) spo,CONCAT(', bb :',' ',bb) bb,CONCAT(', tb:',' ',tb) tb,CONCAT(', kepala :',' ',kepala) kepala,CONCAT(', mata :',' ',mata) mata,CONCAT(', gigi :',' ',gigi) gigi,CONCAT(', leher :',' ',leher) leher,CONCAT(', thoraks :',' ',thoraks) thoraks,CONCAT(', abdomen :',' ',abdomen) abdomen,CONCAT(' ekstremitas :',' ',ekstremitas) ekstremitas,CONCAT(', genital :',' ',genital) genital,CONCAT(', ket_fisik :',' ',ket_fisik) ket_fisik FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asdiagnosa",Sequel.cariIsi("SELECT diagnosis FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("astatalaksanan",Sequel.cariIsi("SELECT REPLACE(REPLACE(tata, CHAR(13), ' '), CHAR(10), ' ') FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                                                            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                                                            ps2=koneksi.prepareStatement(
                                                                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                                                                "from master_triase_pemeriksaan inner join master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                                                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala3.kode_pemeriksaan and "+
                                                                                "master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where data_triase_igddetail_skala3.no_rawat=? "+
                                                                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                                                            try {
                                                                                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                                                                ps2.setString(1,rs.getString("no_rawat"));
                                                                                rs2=ps2.executeQuery();
                                                                                while(rs2.next()){
                                                                                    datatriase="";
                                                                                    ps3=koneksi.prepareStatement(
                                                                                        "select master_triase_skala3.pengkajian_skala3 from master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                                                                        "on master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where "+
                                                                                        "master_triase_skala3.kode_pemeriksaan=? and data_triase_igddetail_skala3.no_rawat=? "+
                                                                                        "order by data_triase_igddetail_skala3.kode_skala3");
                                                                                    try {
                                                                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                                                        ps3.setString(2,rs.getString("no_rawat"));
                                                                                        rs3=ps3.executeQuery();
                                                                                        while(rs3.next()){
                                                                                            datatriase=rs3.getString("pengkajian_skala3")+", "+datatriase;
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        System.out.println("Notif : "+e);
                                                                                    } finally{
                                                                                        if(rs3!=null){
                                                                                            rs3.close();
                                                                                        }
                                                                                        if(ps3!=null){
                                                                                            ps3.close();
                                                                                        }
                                                                                    }

                                                                                    if(datatriase.endsWith(", ")){
                                                                                        datatriase = datatriase.substring(0,datatriase.length() - 2);
                                                                                    }
                                                                                    Sequel.menyimpan2("temporary","'"+row+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                                                                    row++;

                                                                                      psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }

                                                                                       pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                                                                                        pdralan="";
                                                                                        try {
                                                                                            pspd.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rspd=pspd.executeQuery();
                                                                                            while(rspd.next()){
                                                                                                pdralan=rspd.getString("kode")+", "+pdralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rspd!=null){
                                                                                                rspd.close();
                                                                                            }
                                                                                        }   
                                                                                   param.put("icd9",pdralan);  
                                                                                   param.put("icd10",dxralan); 
                                                                                   param.put("alamatip",akses.getalamatip());  
                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Notif : "+e);
                                                                            } finally{
                                                                                if(rs2!=null){
                                                                                    rs2.close();
                                                                                }
                                                                                if(ps2!=null){
                                                                                    ps2.close();
                                                                                }
                                                                            }
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
                                                               
                                                                    Valid.saveToPDF("rptLembarTriaseSkala3sep.jasper","report",tbData.getValueAt(i,1).toString(),param);
                                                             

                                                                 

                                                            }else  if(Sequel.cariInteger("select count(no_rawat) from data_triase_igddetail_skala4 where no_rawat = '"+tbData.getValueAt(i,2).toString()+"' ")>0){
                                                                Map<String, Object> param = new HashMap<>(); 
                                                                param.put("namars",akses.getnamars());
                                                                param.put("alamatrs",akses.getalamatrs());
                                                                param.put("kotars",akses.getkabupatenrs());
                                                                param.put("propinsirs",akses.getpropinsirs());
                                                                param.put("kontakrs",akses.getkontakrs());
                                                                param.put("emailrs",akses.getemailrs());   
                                                                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                                                                try {
                                                                    ps=koneksi.prepareStatement(
                                                                        "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                                                                        "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                                                                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                                                        "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                                                                        "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                                                                        "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                                                                        "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                                                                        "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                                                                        "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                                                                    try {
                                                                        ps.setString(1,tbData.getValueAt(i,2).toString());
                                                                        rs=ps.executeQuery();
                                                                        if(rs.next()){
                                                                            param.put("norawat",rs.getString("no_rawat"));
                                                                            param.put("norm",rs.getString("no_rkm_medis"));
                                                                            param.put("namapasien",rs.getString("nm_pasien"));
                                                                            param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                                                            param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                                                            param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                                                            param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                                                            param.put("caradatang",rs.getString("cara_masuk"));
                                                                            param.put("macamkasus",rs.getString("macam_kasus"));
                                                                            param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                                                            param.put("plan",rs.getString("plan"));
                                                                            param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                                                            param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                                                            param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                                                            param.put("dokter",rs.getString("nama"));
                                                                            param.put("catatan",rs.getString("catatan"));
                                                                            param.put("askelutama",Sequel.cariIsi("SELECT keluhan_utama FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asttv",Sequel.cariIsiBanyak2("SELECT CONCAT('gcs :',' ',gcs) as gcs,CONCAT(', kesadaran :',' ',kesadaran) as kesadaran,CONCAT(', td :',' ',td) as td,CONCAT(', nadi :',' ',nadi) nadi,CONCAT(', rr :',' ',rr) rr,CONCAT(', suhu :',' ',suhu) suhu,CONCAT(', spo :',' ',spo) spo,CONCAT(', bb :',' ',bb) bb,CONCAT(', tb:',' ',tb) tb,CONCAT(', kepala :',' ',kepala) kepala,CONCAT(', mata :',' ',mata) mata,CONCAT(', gigi :',' ',gigi) gigi,CONCAT(', leher :',' ',leher) leher,CONCAT(', thoraks :',' ',thoraks) thoraks,CONCAT(', abdomen :',' ',abdomen) abdomen,CONCAT(' ekstremitas :',' ',ekstremitas) ekstremitas,CONCAT(', genital :',' ',genital) genital,CONCAT(', ket_fisik :',' ',ket_fisik) ket_fisik FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asdiagnosa",Sequel.cariIsi("SELECT diagnosis FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("astatalaksanan",Sequel.cariIsi("SELECT REPLACE(REPLACE(tata, CHAR(13), ' '), CHAR(10), ' ') FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                                                            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                                                            ps2=koneksi.prepareStatement(
                                                                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                                                                "from master_triase_pemeriksaan inner join master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                                                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala4.kode_pemeriksaan and "+
                                                                                "master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where data_triase_igddetail_skala4.no_rawat=? "+
                                                                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                                                            try {
                                                                                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                                                                ps2.setString(1,rs.getString("no_rawat"));
                                                                                rs2=ps2.executeQuery();
                                                                                while(rs2.next()){
                                                                                    datatriase="";
                                                                                    ps3=koneksi.prepareStatement(
                                                                                        "select master_triase_skala4.pengkajian_skala4 from master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                                                                        "on master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where "+
                                                                                        "master_triase_skala4.kode_pemeriksaan=? and data_triase_igddetail_skala4.no_rawat=? "+
                                                                                        "order by data_triase_igddetail_skala4.kode_skala4");
                                                                                    try {
                                                                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                                                        ps3.setString(2,rs.getString("no_rawat"));
                                                                                        rs3=ps3.executeQuery();
                                                                                        while(rs3.next()){
                                                                                            datatriase=rs3.getString("pengkajian_skala4")+", "+datatriase;
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        System.out.println("Notif : "+e);
                                                                                    } finally{
                                                                                        if(rs3!=null){
                                                                                            rs3.close();
                                                                                        }
                                                                                        if(ps3!=null){
                                                                                            ps3.close();
                                                                                        }
                                                                                    }

                                                                                    if(datatriase.endsWith(", ")){
                                                                                        datatriase = datatriase.substring(0,datatriase.length() - 2);
                                                                                    }
                                                                                    Sequel.menyimpan2("temporary","'"+row+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                                                                    row++;

                                                                                      psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }

                                                                                       pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                                                                                        pdralan="";
                                                                                        try {
                                                                                            pspd.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rspd=pspd.executeQuery();
                                                                                            while(rspd.next()){
                                                                                                pdralan=rspd.getString("kode")+", "+pdralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rspd!=null){
                                                                                                rspd.close();
                                                                                            }
                                                                                        }   
                                                                                   param.put("icd9",pdralan);  
                                                                                   param.put("icd10",dxralan); 
                                                                                   param.put("alamatip",akses.getalamatip());  

                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Notif : "+e);
                                                                            } finally{
                                                                                if(rs2!=null){
                                                                                    rs2.close();
                                                                                }
                                                                                if(ps2!=null){
                                                                                    ps2.close();
                                                                                }
                                                                            }
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
                                                            
                                                                     Valid.saveToPDF("rptLembarTriaseSkala4sep.jasper","report",tbData.getValueAt(i,1).toString(),param);

                                                          

                                                            }else  if(Sequel.cariInteger("select count(no_rawat) from data_triase_igddetail_skala5 where no_rawat = '"+tbData.getValueAt(i,2).toString()+"' ")>0){
                                                                Map<String, Object> param = new HashMap<>(); 
                                                                param.put("namars",akses.getnamars());
                                                                param.put("alamatrs",akses.getalamatrs());
                                                                param.put("kotars",akses.getkabupatenrs());
                                                                param.put("propinsirs",akses.getpropinsirs());
                                                                param.put("kontakrs",akses.getkontakrs());
                                                                param.put("emailrs",akses.getemailrs());   
                                                                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                                                                try {
                                                                    ps=koneksi.prepareStatement(
                                                                        "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                                                                        "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                                                                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                                                        "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                                                                        "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                                                                        "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                                                                        "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                                                                        "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                                                                        "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                                                                    try {
                                                                        ps.setString(1,tbData.getValueAt(i,2).toString());
                                                                        rs=ps.executeQuery();
                                                                        if(rs.next()){
                                                                            param.put("norawat",rs.getString("no_rawat"));
                                                                            param.put("norm",rs.getString("no_rkm_medis"));
                                                                            param.put("namapasien",rs.getString("nm_pasien"));
                                                                            param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                                                            param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                                                            param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                                                            param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                                                            param.put("caradatang",rs.getString("cara_masuk"));
                                                                            param.put("macamkasus",rs.getString("macam_kasus"));
                                                                            param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                                                            param.put("plan",rs.getString("plan"));
                                                                            param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                                                            param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                                                            param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                                                            param.put("dokter",rs.getString("nama"));
                                                                            param.put("catatan",rs.getString("catatan"));
                                                                             param.put("askelutama",Sequel.cariIsi("SELECT keluhan_utama FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asttv",Sequel.cariIsiBanyak2("SELECT CONCAT('gcs :',' ',gcs) as gcs,CONCAT(', kesadaran :',' ',kesadaran) as kesadaran,CONCAT(', td :',' ',td) as td,CONCAT(', nadi :',' ',nadi) nadi,CONCAT(', rr :',' ',rr) rr,CONCAT(', suhu :',' ',suhu) suhu,CONCAT(', spo :',' ',spo) spo,CONCAT(', bb :',' ',bb) bb,CONCAT(', tb:',' ',tb) tb,CONCAT(', kepala :',' ',kepala) kepala,CONCAT(', mata :',' ',mata) mata,CONCAT(', gigi :',' ',gigi) gigi,CONCAT(', leher :',' ',leher) leher,CONCAT(', thoraks :',' ',thoraks) thoraks,CONCAT(', abdomen :',' ',abdomen) abdomen,CONCAT(' ekstremitas :',' ',ekstremitas) ekstremitas,CONCAT(', genital :',' ',genital) genital,CONCAT(', ket_fisik :',' ',ket_fisik) ket_fisik FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("asdiagnosa",Sequel.cariIsi("SELECT diagnosis FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            param.put("astatalaksanan",Sequel.cariIsi("SELECT REPLACE(REPLACE(tata, CHAR(13), ' '), CHAR(10), ' ') FROM penilaian_medis_igd WHERE no_rawat='"+rs.getString("no_rawat")+"' "));
                                                                            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                                                            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                                                            ps2=koneksi.prepareStatement(
                                                                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                                                                "from master_triase_pemeriksaan inner join master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                                                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala5.kode_pemeriksaan and "+
                                                                                "master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where data_triase_igddetail_skala5.no_rawat=? "+
                                                                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                                                            try {
                                                                                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                                                                ps2.setString(1,rs.getString("no_rawat"));
                                                                                rs2=ps2.executeQuery();
                                                                                while(rs2.next()){
                                                                                    datatriase="";
                                                                                    ps3=koneksi.prepareStatement(
                                                                                        "select master_triase_skala5.pengkajian_skala5 from master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                                                                        "on master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where "+
                                                                                        "master_triase_skala5.kode_pemeriksaan=? and data_triase_igddetail_skala5.no_rawat=? "+
                                                                                        "order by data_triase_igddetail_skala5.kode_skala5");
                                                                                    try {
                                                                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                                                        ps3.setString(2,rs.getString("no_rawat"));
                                                                                        rs3=ps3.executeQuery();
                                                                                        while(rs3.next()){
                                                                                            datatriase=rs3.getString("pengkajian_skala5")+", "+datatriase;
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        System.out.println("Notif : "+e);
                                                                                    } finally{
                                                                                        if(rs3!=null){
                                                                                            rs3.close();
                                                                                        }
                                                                                        if(ps3!=null){
                                                                                            ps3.close();
                                                                                        }
                                                                                    }

                                                                                    if(datatriase.endsWith(", ")){
                                                                                        datatriase = datatriase.substring(0,datatriase.length() - 2);
                                                                                    }
                                                                                    Sequel.menyimpan2("temporary","'"+row+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                                                                    row++;

                                                                                      psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                                                                        dxralan="";
                                                                                        try {
                                                                                            psdx.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rsdx=psdx.executeQuery();
                                                                                            while(rsdx.next()){
                                                                                                dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rsdx!=null){
                                                                                                rsdx.close();
                                                                                            }
                                                                                        }

                                                                                        pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                                                                                        pdralan="";
                                                                                        try {
                                                                                            pspd.setString(1,tbData.getValueAt(i,2).toString());
                                                                                            rspd=pspd.executeQuery();
                                                                                            while(rspd.next()){
                                                                                                pdralan=rspd.getString("kode")+", "+pdralan;
                                                                                            }
                                                                                        } catch (Exception e) {
                                                                                            System.out.println("Notifikasi : "+e);
                                                                                        } finally{
                                                                                            if(rspd!=null){
                                                                                                rspd.close();
                                                                                            }
                                                                                        }   
                                                                                   param.put("icd9",pdralan);  
                                                                                   param.put("icd10",dxralan); 
                                                                                   param.put("alamatip",akses.getalamatip());  

                                                                                }
                                                                            } catch (Exception e) {
                                                                                System.out.println("Notif : "+e);
                                                                            } finally{
                                                                                if(rs2!=null){
                                                                                    rs2.close();
                                                                                }
                                                                                if(ps2!=null){
                                                                                    ps2.close();
                                                                                }
                                                                            }
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
 
                                                                    Valid.saveToPDF("rptLembarTriaseSkala5sep.jasper","report",tbData.getValueAt(i,1).toString(),param);
   
                                                              
                                                            }
                                                            this.setCursor(Cursor.getDefaultCursor());

                                                            } catch (Exception e) {
                                                                System.out.println("Notifikasi : "+e);
                                                            }
                                                      } tbData.setValueAt(false,i,0); 

                                                        } JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data . . .");
                                                 }
                } else if (cmbJnsRawat.getSelectedIndex() == 1){
                            //Sequel.AutoComitFalse();
//                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true") && tbData.getValueAt(i,8).toString().equals("Final Klaim")){                             
                                        try {
                                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                            Map<String, Object> param = new HashMap<>();
                                            param.put("namars",akses.getnamars());
                                            param.put("alamatrs",akses.getalamatrs());
                                            param.put("kotars",akses.getkabupatenrs());
                                            param.put("propinsirs",akses.getpropinsirs());
                                            param.put("kontakrs",akses.getkontakrs());
                                            param.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                                            param.put("logo2",Sequel.cariGambar("select logo from setting"));
                                            param.put("logo3",Sequel.cariGambar("select bpjs from gambar"));
                                            param.put("nosep",tbData.getValueAt(i,1).toString());
                                            Valid.saveToPDF("rptSEPSPRI3.jasper","report",tbData.getValueAt(i,1).toString(),param);
                                            
                                       this.setCursor(Cursor.getDefaultCursor());
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } tbData.setValueAt(false,i,0);        
                            }  JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data . . .");
                                
                }
        }
    }//GEN-LAST:event_MnCetakResumeBtnPrintActionPerformed

    private void MnBersihkanceklisBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBersihkanceklisBtnPrintActionPerformed
        bersihkan();
    }//GEN-LAST:event_MnBersihkanceklisBtnPrintActionPerformed

    private void cmbJnsPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJnsPoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJnsPoliKeyPressed

    private void cmbJnsRawatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJnsRawatItemStateChanged
       if (cmbJnsRawat.getSelectedIndex()==0){
           jLabel25.setVisible(true);
           cmbJnsPoli.setVisible(true);
           CrPoli.setVisible(true);
           BtnCariPoli.setVisible(true);
           jLabel16.setVisible(true);
       }else{
           jLabel25.setVisible(false);
           cmbJnsPoli.setVisible(false);
           CrPoli.setVisible(false);
           BtnCariPoli.setVisible(false);
           jLabel16.setVisible(false);
       }
    }//GEN-LAST:event_cmbJnsRawatItemStateChanged

    private void MnTampilDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTampilDiagnosaActionPerformed
        row=tbData.getRowCount();
        for(i=0;i<row;i++){
            try{
                psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                dxralan="";
                try {
                    psdx.setString(1,tbData.getValueAt(i,2).toString());
                    rsdx=psdx.executeQuery();
                    while(rsdx.next()){
                        dxralan=rsdx.getString("kd_penyakit")+", "+dxralan;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
                } finally{
                    if(rsdx!=null){
                        rsdx.close();
                    }
                }
                tbData.setValueAt(dxralan,i,6);
            } catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
            try{
                pspd=koneksi.prepareStatement("SELECT pp.kode  FROM  prosedur_pasien pp  WHERE  pp.no_rawat=? AND  pp.status='Ralan' ORDER BY  pp.prioritas desc ");
                pdralan="";
                try {
                    pspd.setString(1,tbData.getValueAt(i,2).toString());
                    rspd=pspd.executeQuery();
                    while(rspd.next()){
                        pdralan=rspd.getString("kode")+", "+pdralan;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
                } finally{
                    if(rspd!=null){
                        rspd.close();
                    }
                }
                tbData.setValueAt(pdralan,i,7);
            } catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
    }//GEN-LAST:event_MnTampilDiagnosaActionPerformed

    private void BtnCariPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        poli.isCek();
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariPoliActionPerformed

    private void MnSudahRevisiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahRevisiBtnPrintActionPerformed
       if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Sequel.mengedit("vedika_ralan","no_rawat='"+tbData.getValueAt(i,2).toString()+"' and no_sep='"+tbData.getValueAt(i,1).toString()+"'","status='Sudah Revisi',tgl=now(),kd_petugas='"+akses.getkode()+"' ");

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                          Sequel.mengedit("vedika_ranap","no_rawat=? and no_sep=?","status='Sudah Revisi',tgl=now(),kd_petugas='"+akses.getkode()+"' ",2,new String[]{
                                            tbData.getValueAt(i,2).toString(),tbData.getValueAt(i,1).toString()
                                                    });
                                                 // bersihkan();
     
                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnSudahRevisiBtnPrintActionPerformed

    private void MnCetakBillngBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakBillngBtnPrintActionPerformed
         if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                 Valid.convert2pdf("billing.php?no_rawat="+tbData.getValueAt(i,2).toString(), tbData.getValueAt(i,1).toString());

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                         Valid.convert2pdf("billing.php?no_rawat="+tbData.getValueAt(i,2).toString(), tbData.getValueAt(i,1).toString());

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnCetakBillngBtnPrintActionPerformed

    private void MnGabungPDFBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGabungPDFBtnPrintActionPerformed
         if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Centang dulu data pada tabel...!!!!");
            tbData.requestFocus();
        } else {
                 if (cmbJnsRawat.getSelectedIndex() == 0) { 
                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Map<String, Object> param = new HashMap<>(); 
                                                param.put("namars",akses.getnamars());
                                                param.put("alamatrs",akses.getalamatrs());
                                                param.put("kotars",akses.getkabupatenrs());
                                                param.put("propinsirs",akses.getpropinsirs());
                                                param.put("kontakrs",akses.getkontakrs());
                                                param.put("logo",Sequel.cariGambar("select bpjs from gambar"));
                                                param.put("logo2",Sequel.cariGambar("select logo from setting"));
                                                param.put("norawat",tbData.getValueAt(i,2).toString());
                                                param.put("pro1",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='1'"));
                                                param.put("pro2",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='2'"));    
                                                param.put("pro3",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='3'")); 
                                                param.put("pro4",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='4'")); 
                                                param.put("pro5",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='5'")); 
                                                param.put("pro6",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='6'")); 
                                                param.put("pro7",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='7'")); 
                                                param.put("pro8",Sequel.cariIsiBanyak("SELECT concat(b.kode,' ',a.deskripsi_panjang) FROM icd9 a INNER JOIN prosedur_pasien b ON a.kode=b.kode WHERE b.no_rawat='"+tbData.getValueAt(i,2).toString()+"' and b.prioritas='8'"));
                                                param.put("ttlobat",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status='Obat'"));
                                                param.put("tarifrs",Sequel.cariIsiBanyak("select if(sum(totalbiaya)='','0',sum(totalbiaya)) from billing where no_rawat='"+tbData.getValueAt(i,2).toString()+"' and status in ('Ralan Dokter','Ralan Paramedis','Ralan Dokter Paramedis')"));
                                                param.put("carapulang",Sequel.cariIsiBanyak("SELECT IF(stts='Sudah','Atas Persetujuan Dokter',IF(stts='Dirujuk','Rujuk',IF(stts='Dirawat','MRS',''))) FROM reg_periksa WHERE no_rawat='"+tbData.getValueAt(i,2).toString()+"' "));
                                                param.put("kontrol",Sequel.cariIsiBanyak("SELECT tgl_rencana FROM bridging_surat_kontrol_bpjs WHERE no_sep='"+tbData.getValueAt(i,1).toString()+"'"));
                                                 Valid.saveToPDF("rptBridgingResume1.jasper","report",tbData.getValueAt(i,1).toString(),param);
                                                 Valid.convert2pdf("billing.php?no_rawat="+tbData.getValueAt(i,2).toString(), tbData.getValueAt(i,1).toString());
                                                 Valid.gabungfile(tbData.getValueAt(i,1).toString());

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    }tbData.setValueAt(false,i,0);       
                            }  
                            tampil();
                } else if (cmbJnsRawat.getSelectedIndex() == 1){

                            //Sequel.AutoComitFalse();
                            //sukses=true;
                            for(i=0;i<tbData.getRowCount();i++){                        
                                    if(tbData.getValueAt(i,0).toString().equals("true")){                             
                                        try {
                                                Valid.gabungfile(tbData.getValueAt(i,1).toString());

                                        } catch (Exception e) {
                                            System.out.println("Notifikasi : "+e);
                                        } 
                                    } 
                                    //tbData.setValueAt(false,i,0);        
                            }  
                                tampil();
                }
        }
    }//GEN-LAST:event_MnGabungPDFBtnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgVedikaKlaim dialog = new DlgVedikaKlaim(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnCariPoli;
    private widget.Button BtnKeluar;
    private widget.TextBox CrPoli;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnBersihkanceklis;
    private javax.swing.JMenuItem MnCetakBillng;
    private javax.swing.JMenuItem MnCetakResume;
    private javax.swing.JMenuItem MnCoding;
    private javax.swing.JMenuItem MnFinalKlaim;
    private javax.swing.JMenuItem MnGabungPDF;
    private javax.swing.JMenuItem MnInputInacbg;
    private javax.swing.JMenuItem MnPending;
    private javax.swing.JMenuItem MnRevisi;
    private javax.swing.JMenuItem MnSudahRevisi;
    private javax.swing.JMenuItem MnTampilDiagnosa;
    private javax.swing.JMenuItem MnVerif;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    public widget.ComboBox cmbJnsPoli;
    public widget.ComboBox cmbJnsRawat;
    public widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private widget.Table tbData;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
         z=0;
        for(i=0;i<tbData.getRowCount();i++){
            if(!tbData.getValueAt(i,0).toString().equals("")){
                z++;
            }
        }    

        pilih=null;
        pilih=new boolean[z]; 
        nosep=null;
        nosep=new String[z];
        norwt=null;
        norwt=new String[z];
        norm=null;
        norm=new String[z];
        nmpasien=null;
        nmpasien=new String[z];
        nmunit=null;
        nmunit=new String[z];
        tglmasuk=null;
        tglmasuk=new String[z];                   
        tglkeluar=null;
        tglkeluar=new String[z];           
        statusklaim=null;
        statusklaim=new String[z];         
        tglupdate=null;
        tglupdate=new String[z];
        petugasklaim=null;
        petugasklaim=new String[z];
        kdpetugas=null;
        kdpetugas=new String[z];
        kdunit=null;
        kdunit=new String[z];
        keterangan=null;
        keterangan=new String[z];
        catatan_vedika=null;
        catatan_vedika=new String[z];
        
        z=0;
        for(i=0;i<tbData.getRowCount();i++){
            if(!tbData.getValueAt(i,0).toString().equals("false")){
                 try {
                    pilih[z]=Boolean.parseBoolean(tbData.getValueAt(i,0).toString()); 
                } catch (Exception e) {
                    pilih[z]=false;
                }  
                //pilih[z]=Boolean.parseBoolean(tbObat.getValueAt(i,0).toString());                 
                nosep[z]=tbData.getValueAt(i,1).toString();
                norwt[z]=tbData.getValueAt(i,2).toString();
                norm[z]=tbData.getValueAt(i,3).toString();    
                nmpasien[z]=tbData.getValueAt(i,4).toString();
                nmunit[z]=tbData.getValueAt(i,5).toString();
                tglmasuk[z]=tbData.getValueAt(i,6).toString();
                tglkeluar[z]=tbData.getValueAt(i,7).toString();
                statusklaim[z]=tbData.getValueAt(i,8).toString();
                tglupdate[z]=tbData.getValueAt(i,9).toString();
                petugasklaim[z]=tbData.getValueAt(i,10).toString();
                kdpetugas[z]=tbData.getValueAt(i,11).toString();
                kdunit[z]=tbData.getValueAt(i,12).toString();
                keterangan[z]=tbData.getValueAt(i,13).toString();
                catatan_vedika[z]=tbData.getValueAt(i,14).toString();
                z++;
            }
        }
        Valid.tabelKosong(tabMode);  
         for(i=0;i<z;i++){
            tabMode.addRow(new Object[] {
                pilih[i],nosep[i],norwt[i],norm[i],nmpasien[i],nmunit[i],
                tglmasuk[i],tglkeluar[i],statusklaim[i],tglupdate[i],petugasklaim[i],
                kdpetugas[i],kdunit[i],keterangan[i],catatan_vedika[i]
            });
        }
        
        try {
             if (cmbJnsRawat.getSelectedIndex() == 0) {
                 if (cmbJnsPoli.getSelectedIndex()==0){
                    ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) unit,IF (rp.status_lanjut = 'Ralan',p.kd_poli,b.kd_bangsal) kd_unit, "
                            + "rp.tgl_registrasi as tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'), DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y')) tglPlg ,v.status,v.tgl,pg.nama, pg.nik,if(v.catatan='','Tidak ada Catatan','Ada Catatan') as catatan "
                            + "FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                            + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis INNER JOIN vedika_ralan v ON v.no_sep=bs.no_sep "
                            + "LEFT JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                            + "LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal LEFT JOIN pegawai pg on pg.nik=v.kd_petugas WHERE "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli NOT IN ('IGDK','PPN','IRM') AND bs.tglsep BETWEEN ? AND ? AND bs.no_sep LIKE ? and v.status LIKE ? and p.nm_poli like '%"+CrPoli.getText()+"%' OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli NOT IN ('IGDK','PPN','IRM') AND bs.tglsep BETWEEN ? AND ? AND bs.no_rawat LIKE ? and v.status LIKE ? and p.nm_poli like '%"+CrPoli.getText()+"%' OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli NOT IN ('IGDK','PPN','IRM') AND bs.tglsep BETWEEN ? AND ? AND ps.no_rkm_medis LIKE ? and v.status LIKE ? and p.nm_poli like '%"+CrPoli.getText()+"%' OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli NOT IN ('IGDK','PPN','IRM') AND bs.tglsep BETWEEN ? AND ? AND ps.nm_pasien LIKE ? and v.status LIKE ? and p.nm_poli like '%"+CrPoli.getText()+"%' OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli NOT IN ('IGDK','PPN','IRM') AND bs.tglsep BETWEEN ? AND ? AND IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) LIKE ? and v.status LIKE ? and p.nm_poli like '%"+CrPoli.getText()+"%' "
                            + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', b.nm_bangsal))");
                    }else if (cmbJnsPoli.getSelectedIndex()==1){
                       ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) unit,IF (rp.status_lanjut = 'Ralan',p.kd_poli,b.kd_bangsal) kd_unit, "
                            + "rp.tgl_registrasi as tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'), DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y')) tglPlg ,v.status,v.tgl,pg.nama, pg.nik,if(v.catatan='','Tidak ada Catatan','Ada Catatan') as catatan "
                            + "FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                            + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis INNER JOIN vedika_ralan v ON v.no_sep=bs.no_sep "
                            + "LEFT JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                            + "LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal LEFT JOIN pegawai pg on pg.nik=v.kd_petugas WHERE "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli='IRM' AND bs.tglsep BETWEEN ? AND ? AND bs.no_sep LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli='IRM' AND bs.tglsep BETWEEN ? AND ? AND bs.no_rawat LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli='IRM' AND bs.tglsep BETWEEN ? AND ? AND ps.no_rkm_medis LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli='IRM' AND bs.tglsep BETWEEN ? AND ? AND ps.nm_pasien LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli='IRM' AND bs.tglsep BETWEEN ? AND ? AND IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) LIKE ? and v.status LIKE ? "
                            + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', b.nm_bangsal))");
                    }else if (cmbJnsPoli.getSelectedIndex()==2){
                      ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) unit,IF (rp.status_lanjut = 'Ralan',p.kd_poli,b.kd_bangsal) kd_unit, "
                            + "rp.tgl_registrasi as tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'), DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y')) tglPlg ,v.status,v.tgl,pg.nama, pg.nik,if(v.catatan='','Tidak ada Catatan','Ada Catatan') as catatan "
                            + "FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                            + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis INNER JOIN vedika_ralan v ON v.no_sep=bs.no_sep "
                            + "LEFT JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                            + "LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal LEFT JOIN pegawai pg on pg.nik=v.kd_petugas WHERE "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli IN ('IGDK','PPN') AND bs.tglsep BETWEEN ? AND ? AND bs.no_sep LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli IN ('IGDK','PPN') AND bs.tglsep BETWEEN ? AND ? AND bs.no_rawat LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli IN ('IGDK','PPN') AND bs.tglsep BETWEEN ? AND ? AND ps.no_rkm_medis LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli IN ('IGDK','PPN') AND bs.tglsep BETWEEN ? AND ? AND ps.nm_pasien LIKE ? and v.status LIKE ? OR "
                            + "rp.status_lanjut = 'Ralan' AND p.kd_poli IN ('IGDK','PPN') AND bs.tglsep BETWEEN ? AND ? AND IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) LIKE ? and v.status LIKE ? "
                            + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', b.nm_bangsal))");
                    }
            } else if (cmbJnsRawat.getSelectedIndex() == 1) {
                ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) unit,IF (rp.status_lanjut = 'Ralan',p.kd_poli,b.kd_bangsal) kd_unit, "
                        + "rp.tgl_registrasi as tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'), DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y')) tglPlg, v.status,v.tgl,pg.nama, pg.nik,if(v.catatan='','Tidak ada Catatan','Ada Catatan') as catatan "
                        + "FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                        + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis INNER JOIN vedika_ranap v ON v.no_sep=bs.no_sep "
                        + "LEFT JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat LEFT JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                        + "LEFT JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal LEFT JOIN pegawai pg on pg.nik=v.kd_petugas WHERE "
                        + "ki.stts_pulang NOT IN ('-','Pindah Kamar') AND ki.tgl_keluar<>'0000-00-00' AND "
                        + "rp.status_lanjut = 'Ranap' AND bs.tglsep BETWEEN ? AND ? AND bs.no_sep LIKE ? and v.status LIKE ? OR "
                        + "ki.stts_pulang NOT IN ('-','Pindah Kamar') AND ki.tgl_keluar<>'0000-00-00' AND "
                        + "rp.status_lanjut = 'Ranap' AND bs.tglsep BETWEEN ? AND ? AND bs.no_rawat LIKE ? and v.status LIKE ? OR "
                        + "ki.stts_pulang NOT IN ('-','Pindah Kamar') AND ki.tgl_keluar<>'0000-00-00' AND "
                        + "rp.status_lanjut = 'Ranap' AND bs.tglsep BETWEEN ? AND ? AND ps.no_rkm_medis LIKE ? and v.status LIKE ? OR "
                        + "ki.stts_pulang NOT IN ('-','Pindah Kamar') AND ki.tgl_keluar<>'0000-00-00' AND "
                        + "rp.status_lanjut = 'Ranap' AND bs.tglsep BETWEEN ? AND ? AND ps.nm_pasien LIKE ? and v.status LIKE ? OR "
                        + "ki.stts_pulang NOT IN ('-','Pindah Kamar') AND ki.tgl_keluar<>'0000-00-00' AND "
                        + "rp.status_lanjut = 'Ranap' AND bs.tglsep BETWEEN ? AND ? AND IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',b.nm_bangsal)) LIKE ? and v.status LIKE ? "
                        + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', b.nm_bangsal))");
            }                       
            
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");    
                ps.setString(4, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua","") + "%");    
                ps.setString(5, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(6, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua","") + "%");                
                ps.setString(9, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(10, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(11, "%" + TCari.getText().trim() + "%"); 
                ps.setString(12, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua","") + "%");
                ps.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua","") + "%");
                ps.setString(17, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(18, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(19, "%" + TCari.getText().trim() + "%");
                ps.setString(20, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua","") + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    cekdatanya="";
                    catatan="";
                if (cmbJnsRawat.getSelectedIndex() == 0) {
                    if (cmbJnsPoli.getSelectedIndex()==0||cmbJnsPoli.getSelectedIndex()==1 &&Sequel.cariInteger("SELECT COUNT(a.no_rawat) FROM pemeriksaan_ralan a WHERE a.no_rawat='"+rs.getString("no_rawat")+"' and a.nip in (SELECT kd_dokter FROM dokter WHERE status='1')")>0){
                        cekdatanya="Download";  
                    }else if (cmbJnsPoli.getSelectedIndex()==2 && Sequel.cariInteger("SELECT COUNT(b.no_rawat) FROM penilaian_medis_igd b,data_triase_igd c WHERE b.no_rawat='"+rs.getString("no_rawat")+"' and c.no_rawat='"+rs.getString("no_rawat")+"' ")>0){
                         cekdatanya="Download"; 
                    }else {
                        cekdatanya="Data Resume Tidak Lengkap";
                    }
                        
                   }
                      tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_sep"), 
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("unit"),
                        rs.getString("tglRegMsk"),
                        rs.getString("tglPlg"),
                        rs.getString("status"),
                        rs.getString("tgl"),
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("kd_unit"),
                        cekdatanya,
                        rs.getString("catatan"),
                    });  
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
        
    }

    public JTable getTable() {
        return tbData;
    }
    
    public void emptText() {
        TCari.setText("");
        tgl.setDate(1);
        tgl1.setDate(tgl);
        tgl2.setDate(new Date());
        cmbJnsRawat.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        norawat = "";
        noSEPnya = "";
    }
    
    public void isCek() {
//      if(akses.getkode().equals("Admin Utama")){
//         MnPengajuanKlaim.setEnabled(true);                                    
//       }else if (Sequel.cariInteger("SELECT icn.nik  FROM  inacbg_coder_nik icn inner join pegawai p on icn.nik =p.nik  WHERE icn.nik ='"+akses.getkode()+"' AND  p.departemen ='ENTR'")>0){
//         MnPengajuanKlaim.setEnabled(true);
//       }else {
//         MnPengajuanKlaim.setEnabled(false);
//       }
    }
        //MnPengajuanKlaim.setEnabled(var.getjkn_belum_diproses_klaim());
    
    
    private void getData() {
        norawat = "";
        noSEPnya = "";
        if (tbData.getSelectedRow() != -1) {
            row=tbData.getSelectedRow();
            norawat = tbData.getValueAt(row, 1).toString();
            noSEPnya = tbData.getValueAt(row, 0).toString();
        }
    }
    
    private void bersihkan () {
        for(i=0;i<tbData.getRowCount();i++){
            tbData.setValueAt(false,i,0);
        }
    }
}

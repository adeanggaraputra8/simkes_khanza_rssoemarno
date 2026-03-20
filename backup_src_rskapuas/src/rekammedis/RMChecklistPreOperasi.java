/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMChecklistPreOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0,pilihan=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariPetugas petugas2=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String finger="",finger2="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMChecklistPreOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Tanggal","SN/CN","Tindakan","Kode Dokter Bedah","Nama Dokter Bedah",
            "Kode Dokter Anest","Nama Dokter Anestesi","Identitas","Keadaan Umum","Penandaan Area Operasi","Surat Ijin Bedah","Surat Ijin Anestesi",
            "Surat Ijin Transfusi","Persiapan Darah","Keterangan Persiapan Darah","Perlengkapan Khusus","Radiologi","Keterangan Radiologi",
            "EKG","Keterangan EKG","USG","Keterangan USG","CT Scan","Keterangan CT Scan","MRI","Keterangan MRI","NIP Ruangan","Petugas Ruangan",
            "NIP OK","Petugas Sirkuler 1", "NIP OK 2","Petugas Sirkuler 2",
            "LAB","Keterangan Lab","RM Lengkap","Lain-Lain","Makan Minum Terakhrr","Tekanan Darah", "Nadi","Respirasi","Suhu","Riwayat Alergi",
            "Pernapasan","Hb","Hasil Hb","Hbs Ag","Hasil Hbs Ag","Infus","Keterangan Infus","Penghapusan Cat Kuku, Lipstik","NGT","Klisma",
            "Cukur Daerah OP","Penanggalan Protese","Obat Premedikasi","Keterangan Premedikasi","Antribiotik Profilaksis", "Keterangan Obat Antibiotik Profilaksis","Riwayat Penyakit"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 64; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(90);
            }else if(i==7){
                column.setPreferredWidth(160);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(160);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(53);
            }else if(i==13){
                column.setPreferredWidth(85);
            }else if(i==14){
                column.setPreferredWidth(130);
            }else if(i==15){
                column.setPreferredWidth(88);
            }else if(i==16){
                column.setPreferredWidth(98);
            }else if(i==17){
                column.setPreferredWidth(102);
            }else if(i==18){
                column.setPreferredWidth(89);
            }else if(i==19){
                column.setPreferredWidth(149);
            }else if(i==20){
                column.setPreferredWidth(109);
            }else if(i==21){
                column.setPreferredWidth(90);
            }else if(i==22){
                column.setPreferredWidth(120);
            }else if(i==23){
                column.setPreferredWidth(90);
            }else if(i==24){
                column.setPreferredWidth(120);
            }else if(i==25){
                column.setPreferredWidth(90);
            }else if(i==26){
                column.setPreferredWidth(120);
            }else if(i==27){
                column.setPreferredWidth(90);
            }else if(i==28){
                column.setPreferredWidth(120);
            }else if(i==29){
                column.setPreferredWidth(90);
            }else if(i==30){
                column.setPreferredWidth(120);
            }else if(i==31){
                column.setPreferredWidth(90);
            }else if(i==32){
                column.setPreferredWidth(150);
            }else if(i==33){
                column.setPreferredWidth(90);
            }else if(i==34){
                column.setPreferredWidth(150);
            }else if(i==35){
                column.setPreferredWidth(90);
            }else if(i==36){
                column.setPreferredWidth(150);
            }else if (i >= 37 && i <= 63) { 
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        SNCN.setDocument(new batasInput((byte)25).getKata(SNCN));
        Tindakan.setDocument(new batasInput((byte)50).getKata(Tindakan));
        KeteranganPersiapanDarah.setDocument(new batasInput((byte)20).getKata(KeteranganPersiapanDarah));
        KeteranganRadiologi.setDocument(new batasInput((byte)20).getKata(KeteranganRadiologi));
        KeteranganEKG.setDocument(new batasInput((byte)20).getKata(KeteranganEKG));
        KeteranganUSG.setDocument(new batasInput((byte)20).getKata(KeteranganUSG));
        KeteranganCTScan.setDocument(new batasInput((byte)20).getKata(KeteranganCTScan));
        KeteranganMRI.setDocument(new batasInput((byte)20).getKata(KeteranganMRI));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
                    if(pilihan==1){
                        KdPetugasRuangan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasRuangan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasRuangan.requestFocus();
                    }else if(pilihan==2){
                        KdPetugasOK.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasOK.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasOK.requestFocus();
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
        
         petugas2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas2.getTable().getSelectedRow()!= -1){      
                    if(pilihan==1){
                        KdPetugasRuangan.setText(petugas2.getTable().getValueAt(petugas2.getTable().getSelectedRow(),0).toString());
                        NmPetugasRuangan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        btnPetugasRuangan.requestFocus();
                    }else if(pilihan==2){
                        KdPetugasSirkuler.setText(petugas2.getTable().getValueAt(petugas2.getTable().getSelectedRow(),0).toString());
                        NmPetugasSirkuler2.setText(petugas2.getTable().getValueAt(petugas2.getTable().getSelectedRow(),1).toString());
                        btnPetugasOK.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){      
                    if(pilihan==1){
                        KodeDokterBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        NamaDokterBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokterBedah.requestFocus();
                    }else if(pilihan==2){
                        KodeDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        NamaDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokterAnestesi.requestFocus();
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
        

        isForm();
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
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
        MnSkriningNutrisi = new javax.swing.JMenuItem();
        LoadHTML = new widget.editorpane();
        JK = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel18 = new widget.Label();
        KdPetugasRuangan = new widget.TextBox();
        NmPetugasRuangan = new widget.TextBox();
        btnPetugasRuangan = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        jLabel20 = new widget.Label();
        KeteranganPersiapanDarah = new widget.TextBox();
        SNCN = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        KodeDokterBedah = new widget.TextBox();
        NamaDokterBedah = new widget.TextBox();
        btnDokterBedah = new widget.Button();
        btnDokterAnestesi = new widget.Button();
        NamaDokterAnestesi = new widget.TextBox();
        KodeDokterAnestesi = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel50 = new widget.Label();
        Identitas = new widget.ComboBox();
        jLabel51 = new widget.Label();
        AreaOperasi = new widget.ComboBox();
        KeadaanUmum = new widget.ComboBox();
        jLabel52 = new widget.Label();
        IjinBedah = new widget.ComboBox();
        IjinAnestesi = new widget.ComboBox();
        jLabel54 = new widget.Label();
        IjinTransfusi = new widget.ComboBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        PersiapanDarah = new widget.ComboBox();
        Tindakan = new widget.TextBox();
        jLabel57 = new widget.Label();
        PerlengkapanKhusus = new widget.ComboBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Radiologi = new widget.ComboBox();
        KeteranganRadiologi = new widget.TextBox();
        jLabel60 = new widget.Label();
        KeteranganEKG = new widget.TextBox();
        EKG = new widget.ComboBox();
        jLabel61 = new widget.Label();
        CTScan = new widget.ComboBox();
        KeteranganCTScan = new widget.TextBox();
        jLabel62 = new widget.Label();
        USG = new widget.ComboBox();
        KeteranganUSG = new widget.TextBox();
        jLabel63 = new widget.Label();
        MRI = new widget.ComboBox();
        KeteranganMRI = new widget.TextBox();
        jLabel26 = new widget.Label();
        KdPetugasOK = new widget.TextBox();
        NmPetugasOK = new widget.TextBox();
        btnPetugasOK = new widget.Button();
        jLabel5 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel27 = new widget.Label();
        btnPetugasOK1 = new widget.Button();
        NmPetugasSirkuler2 = new widget.TextBox();
        KdPetugasSirkuler = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel66 = new widget.Label();
        Lab = new widget.ComboBox();
        KeteranganLab = new widget.TextBox();
        jLabel147 = new widget.Label();
        TD = new widget.TextBox();
        jLabel148 = new widget.Label();
        jLabel149 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel150 = new widget.Label();
        jLabel153 = new widget.Label();
        RR = new widget.TextBox();
        jLabel154 = new widget.Label();
        jLabel151 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel152 = new widget.Label();
        jLabel17 = new widget.Label();
        TanggalTerakhir = new widget.Tanggal();
        scrollPane5 = new widget.ScrollPane();
        RAlergi = new widget.TextArea();
        jLabel121 = new widget.Label();
        jLabel67 = new widget.Label();
        Pernapasan = new widget.ComboBox();
        jLabel68 = new widget.Label();
        HB = new widget.ComboBox();
        jLabel69 = new widget.Label();
        hbsag = new widget.ComboBox();
        jLabel70 = new widget.Label();
        Infus = new widget.ComboBox();
        jLabel71 = new widget.Label();
        premedikasi = new widget.ComboBox();
        jLabel72 = new widget.Label();
        antibiotik = new widget.ComboBox();
        jLabel73 = new widget.Label();
        NGT = new widget.ComboBox();
        jLabel74 = new widget.Label();
        Klisma = new widget.ComboBox();
        jLabel75 = new widget.Label();
        CukurOP = new widget.ComboBox();
        jLabel76 = new widget.Label();
        penghapusan = new widget.ComboBox();
        jLabel77 = new widget.Label();
        protese = new widget.ComboBox();
        jLabel78 = new widget.Label();
        RMLengkap = new widget.ComboBox();
        jLabel79 = new widget.Label();
        KetLain = new widget.TextBox();
        HasilHB = new widget.TextBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        HasilHbsag = new widget.TextBox();
        KeternganInfus = new widget.TextBox();
        jLabel82 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        KetObatAntibiotik = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        KetObatPremedikasi = new widget.TextArea();
        jLabel83 = new widget.Label();
        RiwayatPenyakit = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSkriningNutrisi.setBackground(new java.awt.Color(255, 255, 254));
        MnSkriningNutrisi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSkriningNutrisi.setForeground(new java.awt.Color(50, 50, 50));
        MnSkriningNutrisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSkriningNutrisi.setText("Formulir Checklist Pre Operasi");
        MnSkriningNutrisi.setName("MnSkriningNutrisi"); // NOI18N
        MnSkriningNutrisi.setPreferredSize(new java.awt.Dimension(260, 26));
        MnSkriningNutrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSkriningNutrisiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSkriningNutrisi);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        JK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Check List Pre Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));

        internalFrame2.setBorder(null);
        internalFrame2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 760));
        FormInput.setLayout(null);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("No.Rawat");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(21, 10, 75, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(79, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 75, 23);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Petugas Ruangan");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(20, 360, 103, 23);

        KdPetugasRuangan.setEditable(false);
        KdPetugasRuangan.setHighlighter(null);
        KdPetugasRuangan.setName("KdPetugasRuangan"); // NOI18N
        FormInput.add(KdPetugasRuangan);
        KdPetugasRuangan.setBounds(120, 360, 95, 23);

        NmPetugasRuangan.setEditable(false);
        NmPetugasRuangan.setName("NmPetugasRuangan"); // NOI18N
        FormInput.add(NmPetugasRuangan);
        NmPetugasRuangan.setBounds(220, 360, 165, 23);

        btnPetugasRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasRuangan.setMnemonic('2');
        btnPetugasRuangan.setToolTipText("ALt+2");
        btnPetugasRuangan.setName("btnPetugasRuangan"); // NOI18N
        btnPetugasRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasRuanganActionPerformed(evt);
            }
        });
        btnPetugasRuangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasRuanganKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugasRuangan);
        btnPetugasRuangan.setBounds(390, 360, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026 02:56:51" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(79, 40, 130, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Perawat Melakukan Konfirmasi :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(21, 100, 190, 23);

        KeteranganPersiapanDarah.setHighlighter(null);
        KeteranganPersiapanDarah.setName("KeteranganPersiapanDarah"); // NOI18N
        KeteranganPersiapanDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganPersiapanDarahKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganPersiapanDarah);
        KeteranganPersiapanDarah.setBounds(283, 180, 150, 23);

        SNCN.setHighlighter(null);
        SNCN.setName("SNCN"); // NOI18N
        SNCN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SNCNKeyPressed(evt);
            }
        });
        FormInput.add(SNCN);
        SNCN.setBounds(264, 40, 120, 23);

        jLabel22.setText("SN/CN :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(210, 40, 50, 23);

        jLabel23.setText("Dokter Bedah :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(390, 40, 91, 23);

        KodeDokterBedah.setEditable(false);
        KodeDokterBedah.setHighlighter(null);
        KodeDokterBedah.setName("KodeDokterBedah"); // NOI18N
        FormInput.add(KodeDokterBedah);
        KodeDokterBedah.setBounds(485, 40, 97, 23);

        NamaDokterBedah.setEditable(false);
        NamaDokterBedah.setName("NamaDokterBedah"); // NOI18N
        FormInput.add(NamaDokterBedah);
        NamaDokterBedah.setBounds(584, 40, 175, 23);

        btnDokterBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokterBedah.setMnemonic('2');
        btnDokterBedah.setToolTipText("ALt+2");
        btnDokterBedah.setName("btnDokterBedah"); // NOI18N
        btnDokterBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterBedahActionPerformed(evt);
            }
        });
        btnDokterBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterBedahKeyPressed(evt);
            }
        });
        FormInput.add(btnDokterBedah);
        btnDokterBedah.setBounds(761, 40, 28, 23);

        btnDokterAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokterAnestesi.setMnemonic('2');
        btnDokterAnestesi.setToolTipText("ALt+2");
        btnDokterAnestesi.setName("btnDokterAnestesi"); // NOI18N
        btnDokterAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterAnestesiActionPerformed(evt);
            }
        });
        btnDokterAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(btnDokterAnestesi);
        btnDokterAnestesi.setBounds(761, 70, 28, 23);

        NamaDokterAnestesi.setEditable(false);
        NamaDokterAnestesi.setName("NamaDokterAnestesi"); // NOI18N
        FormInput.add(NamaDokterAnestesi);
        NamaDokterAnestesi.setBounds(584, 70, 175, 23);

        KodeDokterAnestesi.setEditable(false);
        KodeDokterAnestesi.setHighlighter(null);
        KodeDokterAnestesi.setName("KodeDokterAnestesi"); // NOI18N
        FormInput.add(KodeDokterAnestesi);
        KodeDokterAnestesi.setBounds(485, 70, 97, 23);

        jLabel24.setText("Dokter Anestesi :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(390, 70, 91, 23);

        jLabel25.setText("Tindakan :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 70, 75, 23);

        jLabel50.setText("Identitas :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 120, 140, 23);

        Identitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Identitas.setName("Identitas"); // NOI18N
        Identitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IdentitasKeyPressed(evt);
            }
        });
        FormInput.add(Identitas);
        Identitas.setBounds(144, 120, 80, 23);

        jLabel51.setText("Penandaan Area Operasi :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(510, 120, 140, 23);

        AreaOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        AreaOperasi.setName("AreaOperasi"); // NOI18N
        AreaOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AreaOperasiKeyPressed(evt);
            }
        });
        FormInput.add(AreaOperasi);
        AreaOperasi.setBounds(654, 120, 135, 23);

        KeadaanUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Lemah" }));
        KeadaanUmum.setName("KeadaanUmum"); // NOI18N
        KeadaanUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanUmum);
        KeadaanUmum.setBounds(394, 120, 90, 23);

        jLabel52.setText("Keadaan Umum Pasien :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(250, 120, 140, 23);

        IjinBedah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        IjinBedah.setName("IjinBedah"); // NOI18N
        IjinBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IjinBedahKeyPressed(evt);
            }
        });
        FormInput.add(IjinBedah);
        IjinBedah.setBounds(144, 150, 100, 23);

        IjinAnestesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        IjinAnestesi.setName("IjinAnestesi"); // NOI18N
        IjinAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IjinAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(IjinAnestesi);
        IjinAnestesi.setBounds(394, 150, 100, 23);

        jLabel54.setText("Surat Ijin Anestesi :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(280, 150, 110, 23);

        IjinTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        IjinTransfusi.setName("IjinTransfusi"); // NOI18N
        IjinTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IjinTransfusiKeyPressed(evt);
            }
        });
        FormInput.add(IjinTransfusi);
        IjinTransfusi.setBounds(654, 150, 135, 23);

        jLabel55.setText("Surat Ijin Tranfusi :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(520, 150, 130, 23);

        jLabel56.setText("Perlengkapan Khusus, Alat/Implan :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(460, 180, 190, 23);

        PersiapanDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        PersiapanDarah.setName("PersiapanDarah"); // NOI18N
        PersiapanDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PersiapanDarahKeyPressed(evt);
            }
        });
        FormInput.add(PersiapanDarah);
        PersiapanDarah.setBounds(144, 180, 135, 23);

        Tindakan.setHighlighter(null);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        FormInput.add(Tindakan);
        Tindakan.setBounds(79, 70, 305, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Hasil Pemeriksaan Penunjang :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(53, 210, 170, 23);

        PerlengkapanKhusus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        PerlengkapanKhusus.setName("PerlengkapanKhusus"); // NOI18N
        PerlengkapanKhusus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerlengkapanKhususKeyPressed(evt);
            }
        });
        FormInput.add(PerlengkapanKhusus);
        PerlengkapanKhusus.setBounds(654, 180, 135, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Persiapan Darah");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(53, 180, 90, 23);

        jLabel59.setText("Radiologi :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(66, 230, 100, 23);

        Radiologi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        Radiologi.setName("Radiologi"); // NOI18N
        Radiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RadiologiKeyPressed(evt);
            }
        });
        FormInput.add(Radiologi);
        Radiologi.setBounds(170, 230, 135, 23);

        KeteranganRadiologi.setHighlighter(null);
        KeteranganRadiologi.setName("KeteranganRadiologi"); // NOI18N
        KeteranganRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRadiologiKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganRadiologi);
        KeteranganRadiologi.setBounds(309, 230, 120, 23);

        jLabel60.setText("EKG :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(456, 230, 70, 23);

        KeteranganEKG.setHighlighter(null);
        KeteranganEKG.setName("KeteranganEKG"); // NOI18N
        KeteranganEKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEKGKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEKG);
        KeteranganEKG.setBounds(669, 230, 120, 23);

        EKG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        EKG.setName("EKG"); // NOI18N
        EKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKGKeyPressed(evt);
            }
        });
        FormInput.add(EKG);
        EKG.setBounds(530, 230, 135, 23);

        jLabel61.setText("CT Scan :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(456, 260, 70, 23);

        CTScan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        CTScan.setName("CTScan"); // NOI18N
        CTScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CTScanKeyPressed(evt);
            }
        });
        FormInput.add(CTScan);
        CTScan.setBounds(530, 260, 135, 23);

        KeteranganCTScan.setHighlighter(null);
        KeteranganCTScan.setName("KeteranganCTScan"); // NOI18N
        KeteranganCTScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganCTScanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganCTScan);
        KeteranganCTScan.setBounds(669, 260, 120, 23);

        jLabel62.setText("USG :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(66, 260, 100, 23);

        USG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        USG.setName("USG"); // NOI18N
        USG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                USGKeyPressed(evt);
            }
        });
        FormInput.add(USG);
        USG.setBounds(170, 260, 135, 23);

        KeteranganUSG.setHighlighter(null);
        KeteranganUSG.setName("KeteranganUSG"); // NOI18N
        KeteranganUSG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganUSGKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganUSG);
        KeteranganUSG.setBounds(309, 260, 120, 23);

        jLabel63.setText("MRI :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(66, 290, 100, 23);

        MRI.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        MRI.setName("MRI"); // NOI18N
        MRI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MRIKeyPressed(evt);
            }
        });
        FormInput.add(MRI);
        MRI.setBounds(170, 290, 135, 23);

        KeteranganMRI.setHighlighter(null);
        KeteranganMRI.setName("KeteranganMRI"); // NOI18N
        KeteranganMRI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganMRIKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganMRI);
        KeteranganMRI.setBounds(309, 290, 120, 23);

        jLabel26.setText("Ptg. Sirkuler 1 :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(440, 360, 80, 23);

        KdPetugasOK.setEditable(false);
        KdPetugasOK.setHighlighter(null);
        KdPetugasOK.setName("KdPetugasOK"); // NOI18N
        FormInput.add(KdPetugasOK);
        KdPetugasOK.setBounds(530, 360, 95, 23);

        NmPetugasOK.setEditable(false);
        NmPetugasOK.setName("NmPetugasOK"); // NOI18N
        FormInput.add(NmPetugasOK);
        NmPetugasOK.setBounds(630, 360, 165, 23);

        btnPetugasOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasOK.setMnemonic('2');
        btnPetugasOK.setToolTipText("ALt+2");
        btnPetugasOK.setName("btnPetugasOK"); // NOI18N
        btnPetugasOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasOKActionPerformed(evt);
            }
        });
        btnPetugasOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasOKKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugasOK);
        btnPetugasOK.setBounds(790, 360, 28, 23);

        jLabel5.setText(":");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 75, 23);

        jLabel64.setText("Surat Ijin Bedah :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 150, 140, 23);

        jLabel65.setText(":");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 180, 140, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 100, 810, 1);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 100, 810, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 390, 810, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 390, 810, 1);

        jLabel27.setText(":");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 360, 110, 23);

        btnPetugasOK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugasOK1.setMnemonic('2');
        btnPetugasOK1.setToolTipText("ALt+2");
        btnPetugasOK1.setName("btnPetugasOK1"); // NOI18N
        btnPetugasOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasOK1ActionPerformed(evt);
            }
        });
        btnPetugasOK1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasOK1KeyPressed(evt);
            }
        });
        FormInput.add(btnPetugasOK1);
        btnPetugasOK1.setBounds(790, 400, 28, 23);

        NmPetugasSirkuler2.setEditable(false);
        NmPetugasSirkuler2.setName("NmPetugasSirkuler2"); // NOI18N
        FormInput.add(NmPetugasSirkuler2);
        NmPetugasSirkuler2.setBounds(630, 400, 165, 23);

        KdPetugasSirkuler.setEditable(false);
        KdPetugasSirkuler.setHighlighter(null);
        KdPetugasSirkuler.setName("KdPetugasSirkuler"); // NOI18N
        FormInput.add(KdPetugasSirkuler);
        KdPetugasSirkuler.setBounds(530, 400, 95, 23);

        jLabel28.setText("Ptg. Sirkuler 2 :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(440, 400, 80, 23);

        jLabel66.setText("Lain-Lain :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(320, 320, 70, 23);

        Lab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada", "Tidak Diperlukan" }));
        Lab.setName("Lab"); // NOI18N
        Lab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LabKeyPressed(evt);
            }
        });
        FormInput.add(Lab);
        Lab.setBounds(530, 290, 135, 23);

        KeteranganLab.setHighlighter(null);
        KeteranganLab.setName("KeteranganLab"); // NOI18N
        KeteranganLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganLabKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganLab);
        KeteranganLab.setBounds(669, 290, 120, 23);

        jLabel147.setText("TD :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(-10, 430, 127, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(120, 430, 76, 23);

        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("mmHg");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(200, 430, 50, 23);

        jLabel149.setText("Nadi :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(260, 430, 40, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(310, 430, 45, 23);

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("x/menit");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(360, 430, 40, 23);

        jLabel153.setText("RR :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(420, 430, 40, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(460, 430, 45, 23);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel154.setText("x/menit");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(510, 430, 50, 23);

        jLabel151.setText("Suhu :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(590, 430, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(630, 430, 45, 23);

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("°C");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(680, 430, 30, 23);

        jLabel17.setText("Jam Makan dan Minum Terakhir :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel17);
        jLabel17.setBounds(60, 400, 190, 23);

        TanggalTerakhir.setForeground(new java.awt.Color(50, 70, 50));
        TanggalTerakhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026 02:56:51" }));
        TanggalTerakhir.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalTerakhir.setName("TanggalTerakhir"); // NOI18N
        TanggalTerakhir.setOpaque(false);
        TanggalTerakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalTerakhirKeyPressed(evt);
            }
        });
        FormInput.add(TanggalTerakhir);
        TanggalTerakhir.setBounds(250, 400, 130, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        RAlergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RAlergi.setColumns(20);
        RAlergi.setRows(5);
        RAlergi.setName("RAlergi"); // NOI18N
        RAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RAlergiKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(RAlergi);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(170, 460, 220, 42);

        jLabel121.setText("Riwayat Alergi :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(10, 460, 160, 23);

        jLabel67.setText("Pernapasan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(70, 510, 100, 23);

        Pernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Canula O2" }));
        Pernapasan.setName("Pernapasan"); // NOI18N
        Pernapasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernapasanKeyPressed(evt);
            }
        });
        FormInput.add(Pernapasan);
        Pernapasan.setBounds(170, 510, 135, 23);

        jLabel68.setText("HB Terakhir :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(70, 540, 100, 23);

        HB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        HB.setName("HB"); // NOI18N
        HB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HBKeyPressed(evt);
            }
        });
        FormInput.add(HB);
        HB.setBounds(170, 540, 135, 23);

        jLabel69.setText("HBs Ag :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(70, 570, 100, 23);

        hbsag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        hbsag.setName("hbsag"); // NOI18N
        hbsag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hbsagKeyPressed(evt);
            }
        });
        FormInput.add(hbsag);
        hbsag.setBounds(170, 570, 135, 23);

        jLabel70.setText("Hasil :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(310, 540, 40, 23);

        Infus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        Infus.setName("Infus"); // NOI18N
        Infus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InfusKeyPressed(evt);
            }
        });
        FormInput.add(Infus);
        Infus.setBounds(170, 600, 135, 23);

        jLabel71.setText("Obat Premedikasi :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(70, 630, 100, 23);

        premedikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        premedikasi.setName("premedikasi"); // NOI18N
        premedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                premedikasiKeyPressed(evt);
            }
        });
        FormInput.add(premedikasi);
        premedikasi.setBounds(170, 630, 135, 23);

        jLabel72.setText("Obat Antibiotik :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(570, 630, 100, 23);

        antibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        antibiotik.setName("antibiotik"); // NOI18N
        antibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                antibiotikKeyPressed(evt);
            }
        });
        FormInput.add(antibiotik);
        antibiotik.setBounds(670, 630, 135, 23);

        jLabel73.setText("NGT :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(570, 510, 100, 23);

        NGT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        NGT.setName("NGT"); // NOI18N
        NGT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NGTKeyPressed(evt);
            }
        });
        FormInput.add(NGT);
        NGT.setBounds(670, 510, 135, 23);

        jLabel74.setText("Klisma :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(570, 540, 100, 23);

        Klisma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        Klisma.setName("Klisma"); // NOI18N
        Klisma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KlismaKeyPressed(evt);
            }
        });
        FormInput.add(Klisma);
        Klisma.setBounds(670, 540, 135, 23);

        jLabel75.setText("Cukur Daerah OP :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(570, 570, 100, 23);

        CukurOP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        CukurOP.setName("CukurOP"); // NOI18N
        CukurOP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CukurOPKeyPressed(evt);
            }
        });
        FormInput.add(CukurOP);
        CukurOP.setBounds(670, 570, 135, 23);

        jLabel76.setText("Penghapusan Cat Kuku dll :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(500, 480, 170, 23);

        penghapusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        penghapusan.setName("penghapusan"); // NOI18N
        penghapusan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                penghapusanKeyPressed(evt);
            }
        });
        FormInput.add(penghapusan);
        penghapusan.setBounds(670, 480, 135, 23);

        jLabel77.setText("Penanggalan Protese :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(530, 600, 140, 23);

        protese.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak Dilakukan" }));
        protese.setName("protese"); // NOI18N
        protese.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                proteseKeyPressed(evt);
            }
        });
        FormInput.add(protese);
        protese.setBounds(670, 600, 135, 23);

        jLabel78.setText("RM Lengkap :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(66, 320, 100, 23);

        RMLengkap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        RMLengkap.setName("RMLengkap"); // NOI18N
        RMLengkap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RMLengkapKeyPressed(evt);
            }
        });
        FormInput.add(RMLengkap);
        RMLengkap.setBounds(170, 320, 135, 23);

        jLabel79.setText("Lab :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(456, 290, 70, 23);

        KetLain.setHighlighter(null);
        KetLain.setName("KetLain"); // NOI18N
        KetLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetLainKeyPressed(evt);
            }
        });
        FormInput.add(KetLain);
        KetLain.setBounds(399, 320, 390, 23);

        HasilHB.setHighlighter(null);
        HasilHB.setName("HasilHB"); // NOI18N
        HasilHB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilHBKeyPressed(evt);
            }
        });
        FormInput.add(HasilHB);
        HasilHB.setBounds(350, 540, 170, 23);

        jLabel80.setText("Infus :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(70, 600, 100, 23);

        jLabel81.setText("Hasil :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(310, 570, 40, 23);

        HasilHbsag.setHighlighter(null);
        HasilHbsag.setName("HasilHbsag"); // NOI18N
        HasilHbsag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilHbsagKeyPressed(evt);
            }
        });
        FormInput.add(HasilHbsag);
        HasilHbsag.setBounds(350, 570, 170, 23);

        KeternganInfus.setHighlighter(null);
        KeternganInfus.setName("KeternganInfus"); // NOI18N
        KeternganInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeternganInfusKeyPressed(evt);
            }
        });
        FormInput.add(KeternganInfus);
        KeternganInfus.setBounds(350, 600, 170, 23);

        jLabel82.setText("Ket :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(310, 600, 40, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        KetObatAntibiotik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KetObatAntibiotik.setColumns(20);
        KetObatAntibiotik.setRows(5);
        KetObatAntibiotik.setName("KetObatAntibiotik"); // NOI18N
        KetObatAntibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetObatAntibiotikKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(KetObatAntibiotik);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(590, 660, 220, 50);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        KetObatPremedikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KetObatPremedikasi.setColumns(20);
        KetObatPremedikasi.setRows(5);
        KetObatPremedikasi.setName("KetObatPremedikasi"); // NOI18N
        KetObatPremedikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetObatPremedikasiKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(KetObatPremedikasi);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(170, 660, 240, 50);

        jLabel83.setText("Penyakit yang pernah diderita :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(10, 720, 160, 23);

        RiwayatPenyakit.setHighlighter(null);
        RiwayatPenyakit.setName("RiwayatPenyakit"); // NOI18N
        RiwayatPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatPenyakitKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatPenyakit);
        RiwayatPenyakit.setBounds(170, 720, 640, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Cheklist Pre OP", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Cheklist Pre OP", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KodeDokterBedah.getText().trim().equals("")||NamaDokterBedah.getText().trim().equals("")){
            Valid.textKosong(btnDokterBedah,"Dokter Bedah");
        }else if(KodeDokterAnestesi.getText().trim().equals("")||NamaDokterAnestesi.getText().trim().equals("")){
            Valid.textKosong(KodeDokterAnestesi,"Dokter Anestesi");
        }else if(Tindakan.getText().trim().equals("")){
            Valid.textKosong(Tindakan,"Tindakan");
        }else if(SNCN.getText().trim().equals("")){
            Valid.textKosong(SNCN,"SN/CN");
        }else{
            if(Sequel.menyimpantf("checklist_pre_operasi",
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                    "Data",55,new String[]{

                    TNoRw.getText(),
                    Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                    SNCN.getText(),
                    Tindakan.getText(),
                    KodeDokterBedah.getText(),
                    KodeDokterAnestesi.getText(),

                    Identitas.getSelectedItem().toString(),
                    IjinBedah.getSelectedItem().toString(),
                    IjinAnestesi.getSelectedItem().toString(),
                    IjinTransfusi.getSelectedItem().toString(),
                    AreaOperasi.getSelectedItem().toString(),
                    KeadaanUmum.getSelectedItem().toString(),

                    Radiologi.getSelectedItem().toString(),
                    KeteranganRadiologi.getText(),
                    EKG.getSelectedItem().toString(),
                    KeteranganEKG.getText(),
                    USG.getSelectedItem().toString(),
                    KeteranganUSG.getText(),
                    CTScan.getSelectedItem().toString(),
                    KeteranganCTScan.getText(),
                    MRI.getSelectedItem().toString(),
                    KeteranganMRI.getText(),

                    PersiapanDarah.getSelectedItem().toString(),
                    KeteranganPersiapanDarah.getText(),
                    PerlengkapanKhusus.getSelectedItem().toString(),

                    KdPetugasRuangan.getText(),
                    KdPetugasOK.getText(),
                    KdPetugasSirkuler.getText(),

                    Lab.getSelectedItem().toString(),
                    KeteranganLab.getText(),
                    RMLengkap.getSelectedItem().toString(),
                    KetLain.getText(),

                    Valid.SetTgl(TanggalTerakhir.getSelectedItem()+"")+" "+TanggalTerakhir.getSelectedItem().toString().substring(11,19),

                    TD.getText(),
                    Nadi.getText(),
                    RR.getText(),
                    Suhu.getText(),

                    RAlergi.getText(),
                    Pernapasan.getSelectedItem().toString(),

                    HB.getSelectedItem().toString(),
                    HasilHB.getText(),

                    hbsag.getSelectedItem().toString(),
                    HasilHbsag.getText(),

                    Infus.getSelectedItem().toString(),
                    KeternganInfus.getText(),

                    penghapusan.getSelectedItem().toString(),
                    NGT.getSelectedItem().toString(),
                    Klisma.getSelectedItem().toString(),
                    CukurOP.getSelectedItem().toString(),
                    protese.getSelectedItem().toString(),

                    premedikasi.getSelectedItem().toString(),
                    KetObatPremedikasi.getText(),

                    antibiotik.getSelectedItem().toString(),
                    KetObatAntibiotik.getText(),

                    RiwayatPenyakit.getText()

                    })==true){
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JK.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                    SNCN.getText(),Tindakan.getText(),KodeDokterBedah.getText(),NamaDokterBedah.getText(),KodeDokterAnestesi.getText(),NamaDokterAnestesi.getText(),Identitas.getSelectedItem().toString(),
                    KeadaanUmum.getSelectedItem().toString(),AreaOperasi.getSelectedItem().toString(),IjinBedah.getSelectedItem().toString(),IjinAnestesi.getSelectedItem().toString(),IjinTransfusi.getSelectedItem().toString(),
                    PersiapanDarah.getSelectedItem().toString(),KeteranganPersiapanDarah.getText(),PerlengkapanKhusus.getSelectedItem().toString(),Radiologi.getSelectedItem().toString(),KeteranganRadiologi.getText(),
                    EKG.getSelectedItem().toString(),KeteranganEKG.getText(),USG.getSelectedItem().toString(),KeteranganUSG.getText(),CTScan.getSelectedItem().toString(),KeteranganCTScan.getText(),
                    MRI.getSelectedItem().toString(),KeteranganMRI.getText(),KdPetugasRuangan.getText(),NmPetugasRuangan.getText(),KdPetugasOK.getText(),NmPetugasOK.getText(),KdPetugasSirkuler.getText(),NmPetugasSirkuler2.getText()
                });
                LCount.setText(""+tabMode.getRowCount());
                emptTeks();
            } 
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,btnPetugasOK,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        isForm(); 
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else {
                if(akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Harus salah satu petugas sesuai user login..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KodeDokterBedah.getText().trim().equals("")||NamaDokterBedah.getText().trim().equals("")){
            Valid.textKosong(btnDokterBedah,"Dokter Bedah");
        }else if(KodeDokterAnestesi.getText().trim().equals("")||NamaDokterAnestesi.getText().trim().equals("")){
            Valid.textKosong(KodeDokterAnestesi,"Dokter Anestesi");
        }else if(Tindakan.getText().trim().equals("")){
            Valid.textKosong(Tindakan,"Tindakan");
        }else if(SNCN.getText().trim().equals("")){
            Valid.textKosong(SNCN,"SN/CN");
        }else{  
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else {
                    if(akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Harus salah satu petugas sesuai user login..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dokter.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            try{
//                htmlContent = new StringBuilder();
//                htmlContent.append(                             
//                    "<tr class='isi'>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tgl.Lahir</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>J.K.</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>SN/CN</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tindakan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kode Dokter Bedah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Dokter Bedah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kode Dokter Anest</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Dokter Anestesi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Identitas</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keadaan Umum</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Penandaan Area Operasi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Surat Ijin Bedah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Surat Ijin Anestesi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Surat Ijin Transfusi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Persiapan Darah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Persiapan Darah</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Perlengkapan Khusus</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Radiologi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Radiologi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>EKG</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan EKG</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>USG</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan USG</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>CT Scan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan CT Scan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>MRI</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan MRI</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>NIP Ruangan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas Ruangan</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>NIP OK</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas Sirkuler 1</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>NIP OK</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas Sirkuler 2</b></td>"+
//                    "</tr>"
//                );
//
//                for (i = 0; i < tabMode.getRowCount(); i++) {
//                    htmlContent.append(
//                        "<tr class='isi'>"+
//                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
//                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
//                            "<td valign='top'>"+tbObat.getValueAt(i,36).toString()+"</td>"+
//                        "</tr>");
//                }
//                
//                LoadHTML.setText(
//                    "<html>"+
//                      "<table width='3500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
//                       htmlContent.toString()+
//                      "</table>"+
//                    "</html>"
//                );
//
//                File g = new File("file2.css");            
//                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
//                bg.write(
//                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
//                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
//                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
//                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
//                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
//                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
//                );
//                bg.close();
//
//                File f = new File("DataChecklistPreOperasi.html");            
//                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
//                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
//                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
//                            "<table width='3500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                "<tr class='isi2'>"+
//                                    "<td valign='top' align='center'>"+
//                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
//                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
//                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
//                                        "<font size='2' face='Tahoma'>DATA CHECK LIST PRE OPERASI<br><br></font>"+        
//                                    "</td>"+
//                               "</tr>"+
//                            "</table>")
//                );
//                bw.close();                         
//                Desktop.getDesktop().browse(f.toURI());
//            }catch(Exception e){
//                System.out.println("Notifikasi : "+e);
//            }
//        }
//        this.setCursor(Cursor.getDefaultCursor());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            if(tabMode.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                BtnBatal.requestFocus();
            }else{
                try{
                    htmlContent = new StringBuilder();

                    // ===== HEADER KOLOM =====
                    htmlContent.append("<tr class='isi'>");
                    for(int col=0; col<tabMode.getColumnCount(); col++){
                        htmlContent.append(
                            "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>"+
                            tabMode.getColumnName(col)+
                            "</b></td>"
                        );
                    }
                    htmlContent.append("</tr>");

                    // ===== DATA =====
                    for (int i = 0; i < tabMode.getRowCount(); i++) {
                        htmlContent.append("<tr class='isi'>");

                        for(int col=0; col<tabMode.getColumnCount(); col++){
                            Object value = tbObat.getValueAt(i,col);
                            htmlContent.append(
                                "<td valign='top'>"+
                                (value==null?"":value.toString())+
                                "</td>"
                            );
                        }

                        htmlContent.append("</tr>");
                    }

                    // ===== SET HTML =====
                    LoadHTML.setText(
                        "<html>"+
                        "<table width='6000px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                        htmlContent.toString()+
                        "</table>"+
                        "</html>"
                    );

                    // ===== CSS =====
                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right:1px solid #e2e7dd;font:8px tahoma;height:12px;border-bottom:1px solid #e2e7dd;background:#ffffff;color:#323232;}"+
                        ".isi2 td{font:8px tahoma;border:none;height:12px;background:#ffffff;color:#323232;}"+
                        ".isi3 td{border-right:1px solid #e2e7dd;font:8px tahoma;height:12px;border-top:1px solid #e2e7dd;background:#ffffff;color:#323232;}"
                    );
                    bg.close();

                    // ===== FILE OUTPUT =====
                    File f = new File("DataChecklistPreOperasi.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(
                        LoadHTML.getText().replaceAll("<html>",
                        "<html>"+
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                        "<table width='6000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                        "<tr class='isi2'>"+
                        "<td valign='top' align='center'>"+
                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                        "<font size='2' face='Tahoma'>DATA CHECK LIST PRE OPERASI<br><br></font>"+
                        "</td></tr></table>"
                        )
                    );
                    bw.close();

                    Desktop.getDesktop().browse(f.toURI());

                }catch(Exception e){
                    System.out.println("Notifikasi : "+e);
                }
            }

            this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
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
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void btnPetugasRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasRuanganActionPerformed
        pilihan=1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasRuanganActionPerformed

    private void btnPetugasRuanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasRuanganKeyPressed
        Valid.pindah(evt,KeteranganMRI,btnPetugasOK);
    }//GEN-LAST:event_btnPetugasRuanganKeyPressed

    private void MnSkriningNutrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSkriningNutrisiActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),32).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),31).toString():finger)+"\n"+Tanggal.getSelectedItem()); 
            finger2=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),34).toString()+"\nID "+(finger2.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),33).toString():finger2)+"\n"+Tanggal.getSelectedItem()); 
            Valid.MyReportqry("rptFormulirChecklistPreOperasi.jasper","report","::[ Formulir Check List Pre Operasi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,checklist_pre_operasi.tanggal,"+
                    "checklist_pre_operasi.sncn,checklist_pre_operasi.tindakan,checklist_pre_operasi.kd_dokter_bedah,dokterbedah.nm_dokter as dokterbedah,"+
                    "checklist_pre_operasi.kd_dokter_anestesi,dokteranestesi.nm_dokter as dokteranestesi,checklist_pre_operasi.identitas,"+
                    "checklist_pre_operasi.surat_ijin_bedah,checklist_pre_operasi.surat_ijin_anestesi,checklist_pre_operasi.surat_ijin_transfusi,"+
                    "checklist_pre_operasi.penandaan_area_operasi,checklist_pre_operasi.keadaan_umum,checklist_pre_operasi.pemeriksaan_penunjang_rontgen,"+
                    "checklist_pre_operasi.keterangan_pemeriksaan_penunjang_rontgen,checklist_pre_operasi.pemeriksaan_penunjang_ekg,"+
                    "checklist_pre_operasi.keterangan_pemeriksaan_penunjang_ekg,checklist_pre_operasi.pemeriksaan_penunjang_usg,"+
                    "checklist_pre_operasi.keterangan_pemeriksaan_penunjang_usg,checklist_pre_operasi.pemeriksaan_penunjang_ctscan,"+
                    "checklist_pre_operasi.keterangan_pemeriksaan_penunjang_ctscan,checklist_pre_operasi.pemeriksaan_penunjang_mri,"+
                    "checklist_pre_operasi.keterangan_pemeriksaan_penunjang_mri,checklist_pre_operasi.persiapan_darah,checklist_pre_operasi.keterangan_persiapan_darah,"+
                    "checklist_pre_operasi.perlengkapan_khusus,checklist_pre_operasi.nip_petugas_ruangan,petugasruangan.nama as petugasruangan,"+
                    "checklist_pre_operasi.nip_perawat_ok,petugasok.nama as petugasok "+
                    "from checklist_pre_operasi inner join reg_periksa on checklist_pre_operasi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_pre_operasi.kd_dokter_bedah "+
                    "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_pre_operasi.kd_dokter_anestesi "+
                    "inner join petugas as petugasruangan on petugasruangan.nip=checklist_pre_operasi.nip_petugas_ruangan "+
                    "inner join petugas as petugasok on petugasok.nip=checklist_pre_operasi.nip_perawat_ok "+
                    "where checklist_pre_operasi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' and checklist_pre_operasi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"' ",param);
        }
    }//GEN-LAST:event_MnSkriningNutrisiActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
       // Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TanggalKeyPressed

    private void btnDokterBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterBedahActionPerformed
        pilihan=1;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterBedahActionPerformed

    private void btnDokterBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterBedahKeyPressed
        Valid.pindah(evt,Tindakan,btnDokterAnestesi);
    }//GEN-LAST:event_btnDokterBedahKeyPressed

    private void btnDokterAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterAnestesiActionPerformed
        pilihan=2;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterAnestesiActionPerformed

    private void btnDokterAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterAnestesiKeyPressed
        Valid.pindah(evt,btnDokterBedah,Identitas);
    }//GEN-LAST:event_btnDokterAnestesiKeyPressed

    private void IdentitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdentitasKeyPressed
        Valid.pindah(evt,btnDokterAnestesi,KeadaanUmum);
    }//GEN-LAST:event_IdentitasKeyPressed

    private void AreaOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaOperasiKeyPressed
        Valid.pindah(evt,KeadaanUmum,IjinBedah);
    }//GEN-LAST:event_AreaOperasiKeyPressed

    private void KeadaanUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanUmumKeyPressed
        Valid.pindah(evt,Identitas,AreaOperasi);
    }//GEN-LAST:event_KeadaanUmumKeyPressed

    private void IjinBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IjinBedahKeyPressed
        Valid.pindah(evt,AreaOperasi,IjinAnestesi);
    }//GEN-LAST:event_IjinBedahKeyPressed

    private void IjinAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IjinAnestesiKeyPressed
        Valid.pindah(evt,IjinBedah,IjinTransfusi);
    }//GEN-LAST:event_IjinAnestesiKeyPressed

    private void IjinTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IjinTransfusiKeyPressed
        Valid.pindah(evt,IjinAnestesi,PersiapanDarah);
    }//GEN-LAST:event_IjinTransfusiKeyPressed

    private void PersiapanDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersiapanDarahKeyPressed
       Valid.pindah(evt,IjinTransfusi,KeteranganPersiapanDarah);
    }//GEN-LAST:event_PersiapanDarahKeyPressed

    private void PerlengkapanKhususKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerlengkapanKhususKeyPressed
        Valid.pindah(evt,KeteranganPersiapanDarah,Radiologi);
    }//GEN-LAST:event_PerlengkapanKhususKeyPressed

    private void RadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RadiologiKeyPressed
        Valid.pindah(evt,PerlengkapanKhusus,KeteranganRadiologi);
    }//GEN-LAST:event_RadiologiKeyPressed

    private void EKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKGKeyPressed
        Valid.pindah(evt,KeteranganRadiologi,KeteranganEKG);
    }//GEN-LAST:event_EKGKeyPressed

    private void CTScanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CTScanKeyPressed
        Valid.pindah(evt,KeteranganUSG,KeteranganCTScan);
    }//GEN-LAST:event_CTScanKeyPressed

    private void USGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_USGKeyPressed
        Valid.pindah(evt,KeteranganEKG,KeteranganUSG);
    }//GEN-LAST:event_USGKeyPressed

    private void MRIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MRIKeyPressed
        Valid.pindah(evt,KeteranganCTScan,KeteranganMRI);
    }//GEN-LAST:event_MRIKeyPressed

    private void btnPetugasOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasOKActionPerformed
        pilihan=2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasOKActionPerformed

    private void btnPetugasOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasOKKeyPressed
        Valid.pindah(evt,btnPetugasRuangan,BtnSimpan);
    }//GEN-LAST:event_btnPetugasOKKeyPressed

    private void SNCNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SNCNKeyPressed
        Valid.pindah(evt,Tanggal,Tindakan);
    }//GEN-LAST:event_SNCNKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        Valid.pindah(evt,SNCN,btnDokterBedah);
    }//GEN-LAST:event_TindakanKeyPressed

    private void KeteranganPersiapanDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganPersiapanDarahKeyPressed
        Valid.pindah(evt,PersiapanDarah,PerlengkapanKhusus);
    }//GEN-LAST:event_KeteranganPersiapanDarahKeyPressed

    private void KeteranganRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRadiologiKeyPressed
        Valid.pindah(evt,Radiologi,EKG);
    }//GEN-LAST:event_KeteranganRadiologiKeyPressed

    private void KeteranganEKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEKGKeyPressed
        Valid.pindah(evt,EKG,USG);
    }//GEN-LAST:event_KeteranganEKGKeyPressed

    private void KeteranganUSGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganUSGKeyPressed
        Valid.pindah(evt,USG,CTScan);
    }//GEN-LAST:event_KeteranganUSGKeyPressed

    private void KeteranganCTScanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganCTScanKeyPressed
        Valid.pindah(evt,CTScan,MRI);
    }//GEN-LAST:event_KeteranganCTScanKeyPressed

    private void KeteranganMRIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganMRIKeyPressed
        Valid.pindah(evt,MRI,btnPetugasRuangan);
    }//GEN-LAST:event_KeteranganMRIKeyPressed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JKKeyPressed

    private void btnPetugasOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasOK1ActionPerformed
        pilihan=2;
        petugas2.emptTeks();
        petugas2.isCek();
        petugas2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas2.setLocationRelativeTo(internalFrame1);
        petugas2.setVisible(true);
    }//GEN-LAST:event_btnPetugasOK1ActionPerformed

    private void btnPetugasOK1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasOK1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPetugasOK1KeyPressed

    private void LabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LabKeyPressed

    private void KeteranganLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganLabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganLabKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RRKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuKeyPressed

    private void TanggalTerakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalTerakhirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalTerakhirKeyPressed

    private void RAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RAlergiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAlergiKeyPressed

    private void PernapasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernapasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernapasanKeyPressed

    private void HBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HBKeyPressed

    private void hbsagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hbsagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hbsagKeyPressed

    private void InfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InfusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfusKeyPressed

    private void premedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_premedikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_premedikasiKeyPressed

    private void antibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antibiotikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_antibiotikKeyPressed

    private void NGTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NGTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NGTKeyPressed

    private void KlismaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KlismaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KlismaKeyPressed

    private void CukurOPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CukurOPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CukurOPKeyPressed

    private void penghapusanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_penghapusanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_penghapusanKeyPressed

    private void proteseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_proteseKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_proteseKeyPressed

    private void RMLengkapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RMLengkapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RMLengkapKeyPressed

    private void KetLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetLainKeyPressed

    private void HasilHBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilHBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilHBKeyPressed

    private void HasilHbsagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilHbsagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilHbsagKeyPressed

    private void KeternganInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeternganInfusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeternganInfusKeyPressed

    private void KetObatAntibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetObatAntibiotikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetObatAntibiotikKeyPressed

    private void KetObatPremedikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetObatPremedikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetObatPremedikasiKeyPressed

    private void RiwayatPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatPenyakitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RiwayatPenyakitKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMChecklistPreOperasi dialog = new RMChecklistPreOperasi(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AreaOperasi;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CTScan;
    private widget.ComboBox CukurOP;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox EKG;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox HB;
    private widget.TextBox HasilHB;
    private widget.TextBox HasilHbsag;
    private widget.ComboBox Identitas;
    private widget.ComboBox IjinAnestesi;
    private widget.ComboBox IjinBedah;
    private widget.ComboBox IjinTransfusi;
    private widget.ComboBox Infus;
    private widget.TextBox JK;
    private widget.TextBox KdPetugasOK;
    private widget.TextBox KdPetugasRuangan;
    private widget.TextBox KdPetugasSirkuler;
    private widget.ComboBox KeadaanUmum;
    private widget.TextBox KetLain;
    private widget.TextArea KetObatAntibiotik;
    private widget.TextArea KetObatPremedikasi;
    private widget.TextBox KeteranganCTScan;
    private widget.TextBox KeteranganEKG;
    private widget.TextBox KeteranganLab;
    private widget.TextBox KeteranganMRI;
    private widget.TextBox KeteranganPersiapanDarah;
    private widget.TextBox KeteranganRadiologi;
    private widget.TextBox KeteranganUSG;
    private widget.TextBox KeternganInfus;
    private widget.ComboBox Klisma;
    private widget.TextBox KodeDokterAnestesi;
    private widget.TextBox KodeDokterBedah;
    private widget.Label LCount;
    private widget.ComboBox Lab;
    private widget.editorpane LoadHTML;
    private widget.ComboBox MRI;
    private javax.swing.JMenuItem MnSkriningNutrisi;
    private widget.ComboBox NGT;
    private widget.TextBox Nadi;
    private widget.TextBox NamaDokterAnestesi;
    private widget.TextBox NamaDokterBedah;
    private widget.TextBox NmPetugasOK;
    private widget.TextBox NmPetugasRuangan;
    private widget.TextBox NmPetugasSirkuler2;
    private widget.ComboBox PerlengkapanKhusus;
    private widget.ComboBox Pernapasan;
    private widget.ComboBox PersiapanDarah;
    private widget.TextArea RAlergi;
    private widget.ComboBox RMLengkap;
    private widget.TextBox RR;
    private widget.ComboBox Radiologi;
    private widget.TextBox RiwayatPenyakit;
    private widget.TextBox SNCN;
    private widget.ScrollPane Scroll;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tanggal;
    private widget.Tanggal TanggalTerakhir;
    private widget.TextBox TglLahir;
    private widget.TextBox Tindakan;
    private widget.ComboBox USG;
    private widget.ComboBox antibiotik;
    private widget.Button btnDokterAnestesi;
    private widget.Button btnDokterBedah;
    private widget.Button btnPetugasOK;
    private widget.Button btnPetugasOK1;
    private widget.Button btnPetugasRuangan;
    private widget.ComboBox hbsag;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel121;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ComboBox penghapusan;
    private widget.ComboBox premedikasi;
    private widget.ComboBox protese;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
            Valid.tabelKosong(tabMode);
            try{
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,"+
                        "checklist_pre_operasi.*,"+
                        "dokterbedah.nm_dokter as dokterbedah,"+
                        "dokteranestesi.nm_dokter as dokteranestesi,"+
                        "petugasruangan.nama as petugasruangan,"+
                        "petugasok.nama as petugasok,"+
                        "petugassirkuler.nama as petugassirkuler "+
                        "from checklist_pre_operasi "+
                        "inner join reg_periksa on checklist_pre_operasi.no_rawat=reg_periksa.no_rawat "+
                        "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_pre_operasi.kd_dokter_bedah "+
                        "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_pre_operasi.kd_dokter_anestesi "+
                        "inner join petugas as petugasruangan on petugasruangan.nip=checklist_pre_operasi.nip_petugas_ruangan "+
                        "inner join petugas as petugasok on petugasok.nip=checklist_pre_operasi.nip_perawat_ok "+
                        "left join petugas as petugassirkuler on petugassirkuler.nip=checklist_pre_operasi.nip_perawat_sirkuler2 "+
                        "where checklist_pre_operasi.tanggal between ? and ? "+
                        "order by checklist_pre_operasi.tanggal ");
                } else {
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,"+
                        "checklist_pre_operasi.*,"+
                        "dokterbedah.nm_dokter as dokterbedah,"+
                        "dokteranestesi.nm_dokter as dokteranestesi,"+
                        "petugasruangan.nama as petugasruangan,"+
                        "petugasok.nama as petugasok,"+
                        "petugassirkuler.nama as petugassirkuler "+
                        "from checklist_pre_operasi "+
                        "inner join reg_periksa on checklist_pre_operasi.no_rawat=reg_periksa.no_rawat "+
                        "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_pre_operasi.kd_dokter_bedah "+
                        "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_pre_operasi.kd_dokter_anestesi "+
                        "inner join petugas as petugasruangan on petugasruangan.nip=checklist_pre_operasi.nip_petugas_ruangan "+
                        "inner join petugas as petugasok on petugasok.nip=checklist_pre_operasi.nip_perawat_ok "+
                        "left join petugas as petugassirkuler on petugassirkuler.nip=checklist_pre_operasi.nip_perawat_sirkuler2 "+
                        "where checklist_pre_operasi.tanggal between ? and ? "+
                        "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or "+
                        "pasien.nm_pasien like ? or dokterbedah.nm_dokter like ?) "+
                        "order by checklist_pre_operasi.tanggal ");
                }

                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                } else {
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                }

                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("jk"),
                        rs.getString("tanggal"),
                        rs.getString("sncn"),
                        rs.getString("tindakan"),
                        rs.getString("kd_dokter_bedah"),
                        rs.getString("dokterbedah"),
                        rs.getString("kd_dokter_anestesi"),
                        rs.getString("dokteranestesi"),
                        rs.getString("identitas"),
                        rs.getString("keadaan_umum"),
                        rs.getString("penandaan_area_operasi"),
                        rs.getString("surat_ijin_bedah"),
                        rs.getString("surat_ijin_anestesi"),
                        rs.getString("surat_ijin_transfusi"),
                        rs.getString("persiapan_darah"),
                        rs.getString("keterangan_persiapan_darah"),
                        rs.getString("perlengkapan_khusus"),
                        rs.getString("pemeriksaan_penunjang_rontgen"),
                        rs.getString("keterangan_pemeriksaan_penunjang_rontgen"),
                        rs.getString("pemeriksaan_penunjang_ekg"),
                        rs.getString("keterangan_pemeriksaan_penunjang_ekg"),
                        rs.getString("pemeriksaan_penunjang_usg"),
                        rs.getString("keterangan_pemeriksaan_penunjang_usg"),
                        rs.getString("pemeriksaan_penunjang_ctscan"),
                        rs.getString("keterangan_pemeriksaan_penunjang_ctscan"),
                        rs.getString("pemeriksaan_penunjang_mri"),
                        rs.getString("keterangan_pemeriksaan_penunjang_mri"),
                        rs.getString("nip_petugas_ruangan"),
                        rs.getString("petugasruangan"),
                        rs.getString("nip_perawat_ok"),
                        rs.getString("petugasok"),
                        rs.getString("nip_perawat_sirkuler2"),
                        rs.getString("petugassirkuler"),
                        rs.getString("lab"),
                        rs.getString("ketlab"),
                        rs.getString("rmlengkap"),
                        rs.getString("lainlain"),
                        rs.getString("tanggalterakhir"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("suhu"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("pernapasan"),
                        rs.getString("hb"),
                        rs.getString("hasil_hb"),
                        rs.getString("hbsag"),
                        rs.getString("hasil_hbsag"),
                        rs.getString("infus"),
                        rs.getString("ket_infus"),
                        rs.getString("penghapusan"),
                        rs.getString("ngt"),
                        rs.getString("klisma"),
                        rs.getString("cukurop"),
                        rs.getString("protese"),
                        rs.getString("premedikasi"),
                        rs.getString("ket_premedikasi"),
                        rs.getString("antibiotik"),
                        rs.getString("ket_antibiotik"),
                        rs.getString("riwayat_penyakit")
                    });
                }

            } catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }

            LCount.setText(""+tabMode.getRowCount());
        }
    
    public void emptTeks() {
        // ===== DATA UTAMA =====
        SNCN.setText("");
        Tindakan.setText("");
        KodeDokterBedah.setText("");
        NamaDokterBedah.setText("");
        KodeDokterAnestesi.setText("");
        NamaDokterAnestesi.setText("");

        Identitas.setSelectedIndex(0);
        KeadaanUmum.setSelectedIndex(0);
        AreaOperasi.setSelectedIndex(0);
        IjinBedah.setSelectedIndex(0);
        IjinAnestesi.setSelectedIndex(0);
        IjinTransfusi.setSelectedIndex(0);

        PersiapanDarah.setSelectedIndex(0);
        KeteranganPersiapanDarah.setText("");
        PerlengkapanKhusus.setSelectedIndex(0);

        // ===== PENUNJANG =====
        Radiologi.setSelectedIndex(0);
        KeteranganRadiologi.setText("");

        EKG.setSelectedIndex(0);
        KeteranganEKG.setText("");

        USG.setSelectedIndex(0);
        KeteranganUSG.setText("");

        CTScan.setSelectedIndex(0);
        KeteranganCTScan.setText("");

        MRI.setSelectedIndex(0);
        KeteranganMRI.setText("");

        // ===== PETUGAS =====
        KdPetugasRuangan.setText("");
        NmPetugasRuangan.setText("");
        KdPetugasOK.setText("");
        NmPetugasOK.setText("");
        KdPetugasSirkuler.setText("");
        NmPetugasSirkuler2.setText("");

        // ===== LAB & ADMIN =====
        Lab.setSelectedIndex(0);
        KeteranganLab.setText("");
        RMLengkap.setSelectedIndex(0);
        KetLain.setText("");

        // ===== TANGGAL =====
        Tanggal.setDate(new Date());
        TanggalTerakhir.setDate(new Date());

        // ===== TTV =====
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");

        // ===== RIWAYAT =====
        RAlergi.setText("");
        RiwayatPenyakit.setText("");

        // ===== PERNAPASAN =====
        Pernapasan.setSelectedIndex(0);

        // ===== HB =====
        HB.setSelectedIndex(0);
        HasilHB.setText("");

        // ===== HBSAG =====
        hbsag.setSelectedIndex(0);
        HasilHbsag.setText("");

        // ===== INFUS =====
        Infus.setSelectedIndex(0);
        KeternganInfus.setText("");

        // ===== TINDAKAN TAMBAHAN =====
        penghapusan.setSelectedIndex(0);
        NGT.setSelectedIndex(0);
        Klisma.setSelectedIndex(0);
        CukurOP.setSelectedIndex(0);
        protese.setSelectedIndex(0);

        // ===== OBAT =====
        premedikasi.setSelectedIndex(0);
        KetObatPremedikasi.setText("");

        antibiotik.setSelectedIndex(0);
        KetObatAntibiotik.setText("");

        // ===== FOCUS =====
        SNCN.requestFocus();
    }

    private void getData() {
        if(tbObat.getSelectedRow() != -1){
            int r = tbObat.getSelectedRow();

            TNoRw.setText(tbObat.getValueAt(r,0).toString());
            TNoRM.setText(tbObat.getValueAt(r,1).toString());
            TPasien.setText(tbObat.getValueAt(r,2).toString());
            TglLahir.setText(tbObat.getValueAt(r,3).toString());
            JK.setText(tbObat.getValueAt(r,4).toString());

            Valid.SetTgl2(Tanggal, tbObat.getValueAt(r,5).toString());

            SNCN.setText(tbObat.getValueAt(r,6).toString());
            Tindakan.setText(tbObat.getValueAt(r,7).toString());

            KodeDokterBedah.setText(tbObat.getValueAt(r,8).toString());
            NamaDokterBedah.setText(tbObat.getValueAt(r,9).toString());
            KodeDokterAnestesi.setText(tbObat.getValueAt(r,10).toString());
            NamaDokterAnestesi.setText(tbObat.getValueAt(r,11).toString());

            Identitas.setSelectedItem(tbObat.getValueAt(r,12).toString());
            KeadaanUmum.setSelectedItem(tbObat.getValueAt(r,13).toString());
            AreaOperasi.setSelectedItem(tbObat.getValueAt(r,14).toString());
            IjinBedah.setSelectedItem(tbObat.getValueAt(r,15).toString());
            IjinAnestesi.setSelectedItem(tbObat.getValueAt(r,16).toString());
            IjinTransfusi.setSelectedItem(tbObat.getValueAt(r,17).toString());

            PersiapanDarah.setSelectedItem(tbObat.getValueAt(r,18).toString());
            KeteranganPersiapanDarah.setText(tbObat.getValueAt(r,19).toString());
            PerlengkapanKhusus.setSelectedItem(tbObat.getValueAt(r,20).toString());

            Radiologi.setSelectedItem(tbObat.getValueAt(r,21).toString());
            KeteranganRadiologi.setText(tbObat.getValueAt(r,22).toString());
            EKG.setSelectedItem(tbObat.getValueAt(r,23).toString());
            KeteranganEKG.setText(tbObat.getValueAt(r,24).toString());
            USG.setSelectedItem(tbObat.getValueAt(r,25).toString());
            KeteranganUSG.setText(tbObat.getValueAt(r,26).toString());
            CTScan.setSelectedItem(tbObat.getValueAt(r,27).toString());
            KeteranganCTScan.setText(tbObat.getValueAt(r,28).toString());
            MRI.setSelectedItem(tbObat.getValueAt(r,29).toString());
            KeteranganMRI.setText(tbObat.getValueAt(r,30).toString());

            KdPetugasRuangan.setText(tbObat.getValueAt(r,31).toString());
            NmPetugasRuangan.setText(tbObat.getValueAt(r,32).toString());
            KdPetugasOK.setText(tbObat.getValueAt(r,33).toString());
            NmPetugasOK.setText(tbObat.getValueAt(r,34).toString());
            KdPetugasSirkuler.setText(tbObat.getValueAt(r,35).toString());
            NmPetugasSirkuler2.setText(tbObat.getValueAt(r,36).toString());

            Lab.setSelectedItem(tbObat.getValueAt(r,37).toString());
            KeteranganLab.setText(tbObat.getValueAt(r,38).toString());
            RMLengkap.setSelectedItem(tbObat.getValueAt(r,39).toString());
            KetLain.setText(tbObat.getValueAt(r,40).toString());

            Valid.SetTgl2(TanggalTerakhir, tbObat.getValueAt(r,41).toString());

            TD.setText(tbObat.getValueAt(r,42).toString());
            Nadi.setText(tbObat.getValueAt(r,43).toString());
            RR.setText(tbObat.getValueAt(r,44).toString());
            Suhu.setText(tbObat.getValueAt(r,45).toString());

            RAlergi.setText(tbObat.getValueAt(r,46).toString());
            Pernapasan.setSelectedItem(tbObat.getValueAt(r,47).toString());

            HB.setSelectedItem(tbObat.getValueAt(r,48).toString());
            HasilHB.setText(tbObat.getValueAt(r,49).toString());

            hbsag.setSelectedItem(tbObat.getValueAt(r,50).toString());
            HasilHbsag.setText(tbObat.getValueAt(r,51).toString());

            Infus.setSelectedItem(tbObat.getValueAt(r,52).toString());
            KeternganInfus.setText(tbObat.getValueAt(r,53).toString());

            penghapusan.setSelectedItem(tbObat.getValueAt(r,54).toString());
            NGT.setSelectedItem(tbObat.getValueAt(r,55).toString());
            Klisma.setSelectedItem(tbObat.getValueAt(r,56).toString());
            CukurOP.setSelectedItem(tbObat.getValueAt(r,57).toString());
            protese.setSelectedItem(tbObat.getValueAt(r,58).toString());

            premedikasi.setSelectedItem(tbObat.getValueAt(r,59).toString());
            KetObatPremedikasi.setText(tbObat.getValueAt(r,60).toString());

            antibiotik.setSelectedItem(tbObat.getValueAt(r,61).toString());
            KetObatAntibiotik.setText(tbObat.getValueAt(r,62).toString());

            RiwayatPenyakit.setText(tbObat.getValueAt(r,63).toString());
            TabRawat.setSelectedIndex(0);
        }
    }
    
    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
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
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
//        ChkInput.setSelected(true);
        isForm();
    }
    
    public void setNoRm(String norwt, Date tgl2,String KodeDokter,String NamaDokter,String Operasi) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
//        ChkInput.setSelected(true);
        isForm();
        KodeDokterBedah.setText(KodeDokter);
        NamaDokterBedah.setText(NamaDokter);
        Tindakan.setText(Operasi);
    }
    
    private void isForm(){
//        if(ChkInput.isSelected()==true){
//            if(internalFrame1.getHeight()>558){
//                ChkInput.setVisible(false);
//                PanelInput.setPreferredSize(new Dimension(WIDTH,386));
//                FormInput.setVisible(true);      
//                ChkInput.setVisible(true);
//            }else{
//                ChkInput.setVisible(false);
//                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-172));
//                FormInput.setVisible(true);      
//                ChkInput.setVisible(true);
//            }
//        }else if(ChkInput.isSelected()==false){           
//            ChkInput.setVisible(false);            
//            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
//            FormInput.setVisible(false);      
//            ChkInput.setVisible(true);
//        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getchecklist_pre_operasi());
        BtnHapus.setEnabled(akses.getchecklist_pre_operasi());
        BtnEdit.setEnabled(akses.getchecklist_pre_operasi());
        BtnPrint.setEnabled(akses.getchecklist_pre_operasi()); 
    }

    private void ganti() {
            if(Sequel.mengedittf("checklist_pre_operasi",
                "no_rawat=? and tanggal=?",
                "no_rawat=?,tanggal=?,sncn=?,tindakan=?,kd_dokter_bedah=?,kd_dokter_anestesi=?,identitas=?,surat_ijin_bedah=?,"
                        + "surat_ijin_anestesi=?,surat_ijin_transfusi=?,penandaan_area_operasi=?,keadaan_umum=?,pemeriksaan_penunjang_rontgen=?,"
                        + "keterangan_pemeriksaan_penunjang_rontgen=?,pemeriksaan_penunjang_ekg=?,keterangan_pemeriksaan_penunjang_ekg=?,"
                        + "pemeriksaan_penunjang_usg=?,keterangan_pemeriksaan_penunjang_usg=?,pemeriksaan_penunjang_ctscan=?,keterangan_pemeriksaan_penunjang_ctscan=?,"
                        + "pemeriksaan_penunjang_mri=?,keterangan_pemeriksaan_penunjang_mri=?,persiapan_darah=?,keterangan_persiapan_darah=?,perlengkapan_khusus=?,"
                        + "nip_petugas_ruangan=?,nip_perawat_ok=?,nip_perawat_sirkuler2=?,lab=?,ketlab=?,rmlengkap=?,lainlain=?,tanggalterakhir=?,td=?,nadi=?,rr=?,suhu=?,"
                        + "riwayat_alergi=?,pernapasan=?,hb=?,hasil_hb=?,hbsag=?,hasil_hbsag=?,infus=?,ket_infus=?,penghapusan=?,ngt=?,klisma=?,"
                        + "cukurop=?,protese=?,premedikasi=?,ket_premedikasi=?,antibiotik=?,ket_antibiotik=?,riwayat_penyakit=?",
                57,
                new String[]{

                // ===== DATA BARU =====
                TNoRw.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                SNCN.getText(),
                Tindakan.getText(),
                KodeDokterBedah.getText(),
                KodeDokterAnestesi.getText(),

                Identitas.getSelectedItem().toString(),
                IjinBedah.getSelectedItem().toString(),
                IjinAnestesi.getSelectedItem().toString(),
                IjinTransfusi.getSelectedItem().toString(),
                AreaOperasi.getSelectedItem().toString(),
                KeadaanUmum.getSelectedItem().toString(),

                Radiologi.getSelectedItem().toString(),
                KeteranganRadiologi.getText(),
                EKG.getSelectedItem().toString(),
                KeteranganEKG.getText(),
                USG.getSelectedItem().toString(),
                KeteranganUSG.getText(),
                CTScan.getSelectedItem().toString(),
                KeteranganCTScan.getText(),
                MRI.getSelectedItem().toString(),
                KeteranganMRI.getText(),

                PersiapanDarah.getSelectedItem().toString(),
                KeteranganPersiapanDarah.getText(),
                PerlengkapanKhusus.getSelectedItem().toString(),

                KdPetugasRuangan.getText(),
                KdPetugasOK.getText(),
                KdPetugasSirkuler.getText(),

                Lab.getSelectedItem().toString(),
                KeteranganLab.getText(),
                RMLengkap.getSelectedItem().toString(),
                KetLain.getText(),

                Valid.SetTgl(TanggalTerakhir.getSelectedItem()+"")+" "+TanggalTerakhir.getSelectedItem().toString().substring(11,19),

                TD.getText(),
                Nadi.getText(),
                RR.getText(),
                Suhu.getText(),

                RAlergi.getText(),
                Pernapasan.getSelectedItem().toString(),

                HB.getSelectedItem().toString(),
                HasilHB.getText(),

                hbsag.getSelectedItem().toString(),
                HasilHbsag.getText(),

                Infus.getSelectedItem().toString(),
                KeternganInfus.getText(),

                penghapusan.getSelectedItem().toString(),
                NGT.getSelectedItem().toString(),
                Klisma.getSelectedItem().toString(),
                CukurOP.getSelectedItem().toString(),
                protese.getSelectedItem().toString(),

                premedikasi.getSelectedItem().toString(),
                KetObatPremedikasi.getText(),

                antibiotik.getSelectedItem().toString(),
                KetObatAntibiotik.getText(),

                RiwayatPenyakit.getText(),

                // ===== WHERE PARAMETER =====
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
            })==true){

                // ===== UPDATE TABLE 64 KOLOM =====

                int r = tbObat.getSelectedRow();

                tbObat.setValueAt(TNoRw.getText(), r, 0);
                tbObat.setValueAt(TNoRM.getText(), r, 1);
                tbObat.setValueAt(TPasien.getText(), r, 2);
                tbObat.setValueAt(TglLahir.getText(), r, 3);
                tbObat.setValueAt(JK.getText(), r, 4);
                tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19), r, 5);
                tbObat.setValueAt(SNCN.getText(), r, 6);
                tbObat.setValueAt(Tindakan.getText(), r, 7);
                tbObat.setValueAt(KodeDokterBedah.getText(), r, 8);
                tbObat.setValueAt(NamaDokterBedah.getText(), r, 9);
                tbObat.setValueAt(KodeDokterAnestesi.getText(), r, 10);
                tbObat.setValueAt(NamaDokterAnestesi.getText(), r, 11);

                tbObat.setValueAt(Identitas.getSelectedItem().toString(), r, 12);
                tbObat.setValueAt(KeadaanUmum.getSelectedItem().toString(), r, 13);
                tbObat.setValueAt(AreaOperasi.getSelectedItem().toString(), r, 14);
                tbObat.setValueAt(IjinBedah.getSelectedItem().toString(), r, 15);
                tbObat.setValueAt(IjinAnestesi.getSelectedItem().toString(), r, 16);
                tbObat.setValueAt(IjinTransfusi.getSelectedItem().toString(), r, 17);

                tbObat.setValueAt(PersiapanDarah.getSelectedItem().toString(), r, 18);
                tbObat.setValueAt(KeteranganPersiapanDarah.getText(), r, 19);
                tbObat.setValueAt(PerlengkapanKhusus.getSelectedItem().toString(), r, 20);

                tbObat.setValueAt(Radiologi.getSelectedItem().toString(), r, 21);
                tbObat.setValueAt(KeteranganRadiologi.getText(), r, 22);
                tbObat.setValueAt(EKG.getSelectedItem().toString(), r, 23);
                tbObat.setValueAt(KeteranganEKG.getText(), r, 24);
                tbObat.setValueAt(USG.getSelectedItem().toString(), r, 25);
                tbObat.setValueAt(KeteranganUSG.getText(), r, 26);
                tbObat.setValueAt(CTScan.getSelectedItem().toString(), r, 27);
                tbObat.setValueAt(KeteranganCTScan.getText(), r, 28);
                tbObat.setValueAt(MRI.getSelectedItem().toString(), r, 29);
                tbObat.setValueAt(KeteranganMRI.getText(), r, 30);

                tbObat.setValueAt(KdPetugasRuangan.getText(), r, 31);
                tbObat.setValueAt(NmPetugasRuangan.getText(), r, 32);
                tbObat.setValueAt(KdPetugasOK.getText(), r, 33);
                tbObat.setValueAt(NmPetugasOK.getText(), r, 34);
                tbObat.setValueAt(KdPetugasSirkuler.getText(), r, 35);
                tbObat.setValueAt(NmPetugasSirkuler2.getText(), r, 36);

                tbObat.setValueAt(Lab.getSelectedItem().toString(), r, 37);
                tbObat.setValueAt(KeteranganLab.getText(), r, 38);
                tbObat.setValueAt(RMLengkap.getSelectedItem().toString(), r, 39);
                tbObat.setValueAt(KetLain.getText(), r, 40);
                tbObat.setValueAt(TanggalTerakhir.getSelectedItem().toString(), r, 41);

                tbObat.setValueAt(TD.getText(), r, 42);
                tbObat.setValueAt(Nadi.getText(), r, 43);
                tbObat.setValueAt(RR.getText(), r, 44);
                tbObat.setValueAt(Suhu.getText(), r, 45);

                tbObat.setValueAt(RAlergi.getText(), r, 46);
                tbObat.setValueAt(Pernapasan.getSelectedItem().toString(), r, 47);
                tbObat.setValueAt(HB.getSelectedItem().toString(), r, 48);
                tbObat.setValueAt(HasilHB.getText(), r, 49);
                tbObat.setValueAt(hbsag.getSelectedItem().toString(), r, 50);
                tbObat.setValueAt(HasilHbsag.getText(), r, 51);
                tbObat.setValueAt(Infus.getSelectedItem().toString(), r, 52);
                tbObat.setValueAt(KeternganInfus.getText(), r, 53);
                tbObat.setValueAt(penghapusan.getSelectedItem().toString(), r, 54);
                tbObat.setValueAt(NGT.getSelectedItem().toString(), r, 55);
                tbObat.setValueAt(Klisma.getSelectedItem().toString(), r, 56);
                tbObat.setValueAt(CukurOP.getSelectedItem().toString(), r, 57);
                tbObat.setValueAt(protese.getSelectedItem().toString(), r, 58);
                tbObat.setValueAt(premedikasi.getSelectedItem().toString(), r, 59);
                tbObat.setValueAt(KetObatPremedikasi.getText(), r, 60);
                tbObat.setValueAt(antibiotik.getSelectedItem().toString(), r, 61);
                tbObat.setValueAt(KetObatAntibiotik.getText(), r, 62);
                tbObat.setValueAt(RiwayatPenyakit.getText(), r, 63);
                TabRawat.setSelectedIndex(1);
                emptTeks();
            }
        }

    private void hapus() {
        if(Sequel.queryu2tf("delete from checklist_pre_operasi where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
}

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
public final class RMChecklistPostOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0,pilihan=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String finger="",finger2="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMChecklistPostOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Tanggal","SN/CN","Tindakan","Kode Dokter Bedah","Nama Dokter Bedah",
            "Kode Dokter Anest","Nama Dokter Anestesi","Keadaan Umum","Radiologi","Keterangan Radiologi","EKG","Keterangan EKG","USG",
            "Keterangan USG","CT Scan","Keterangan CT Scan","MRI","Keterangan MRI","Jenis Cairan Infus","Kateter Urine","Tgl.Pemasangan",
            "Warna Urine","Jml.Urine","Area Luka Operasi","Drain","Jml.Drain","Letak Drain","Warna Drain","Jaringan PA","NIP OK",
            "Petugas Ruang OK","NIP Anestesi","Petugas Anestesi",
            "Jenis Pembiusan","Posisi Operasi","Jenis_Opersasi","Diatermi","Tampon","Jumlah Tampon","Jumlah Pendarahan","Masuk Infus CC","Masuk Darah CC","Keluar Infus CC",
            "Keluar Darah CC", "Keluar Drain CC", "Tekanan Darah","Nadi","Respirasi","Suhu", "Catatan Perawat OK","Keluar Cairan Urine CC"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 56; i++) {
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
                column.setPreferredWidth(85);
            }else if(i==13){
                column.setPreferredWidth(58);
            }else if(i==14){
                column.setPreferredWidth(115);
            }else if(i==15){
                column.setPreferredWidth(58);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setPreferredWidth(58);
            }else if(i==18){
                column.setPreferredWidth(100);
            }else if(i==19){
                column.setPreferredWidth(58);
            }else if(i==20){
                column.setPreferredWidth(110);
            }else if(i==21){
                column.setPreferredWidth(58);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(110);
            }else if(i==24){
                column.setPreferredWidth(75);
            }else if(i==25){
                column.setPreferredWidth(115);
            }else if(i==26){
                column.setPreferredWidth(68);
            }else if(i==27){
                column.setPreferredWidth(55);
            }else if(i==28){
                column.setPreferredWidth(150);
            }else if(i==29){
                column.setPreferredWidth(58);
            }else if(i==30){
                column.setPreferredWidth(57);
            }else if(i==31){
                column.setPreferredWidth(120);
            }else if(i==32){
                column.setPreferredWidth(90);
            }else if(i==33){
                column.setPreferredWidth(70);
            }else if(i==34){
                column.setPreferredWidth(90);
            }else if(i==35){
                column.setPreferredWidth(150);
            }else if(i==36){
                column.setPreferredWidth(90);
            }else if(i==37){
                column.setPreferredWidth(150);
            }else if (i >= 38 && i <= 53) { 
                column.setPreferredWidth(90);
            } else if(i==54){
                column.setPreferredWidth(175);
            } else if(i==55){
                column.setPreferredWidth(90);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        SNCN.setDocument(new batasInput((byte)25).getKata(SNCN));
        Tindakan.setDocument(new batasInput((byte)50).getKata(Tindakan));
        JumlahDrain.setDocument(new batasInput((byte)20).getKata(JumlahDrain));
        KeteranganRadiologi.setDocument(new batasInput((byte)20).getKata(KeteranganRadiologi));
        KeteranganEKG.setDocument(new batasInput((byte)20).getKata(KeteranganEKG));
        KeteranganUSG.setDocument(new batasInput((byte)20).getKata(KeteranganUSG));
        KeteranganCTScan.setDocument(new batasInput((byte)20).getKata(KeteranganCTScan));
        KeteranganMRI.setDocument(new batasInput((byte)20).getKata(KeteranganMRI));
        CairanInfus.setDocument(new batasInput((byte)40).getKata(CairanInfus));
        JumlahDrain.setDocument(new batasInput((byte)2).getKata(JumlahDrain));
        LetakDrain.setDocument(new batasInput((byte)40).getKata(LetakDrain));
        WarnaDrain.setDocument(new batasInput((byte)30).getKata(WarnaDrain));
        AreaLukaOperasi.setDocument(new batasInput((int)120).getKata(AreaLukaOperasi));
        JumlahUrine.setDocument(new batasInput((byte)4).getKata(JumlahUrine));
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
                        KdPetugasAnest.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugasAnest.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
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
        MnPostOperasi = new javax.swing.JMenuItem();
        LoadHTML = new widget.editorpane();
        JK = new widget.TextBox();
        BtnGroupJnsPembiusan = new javax.swing.ButtonGroup();
        BtnGroupTampon = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput1 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel11 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        KdPetugasAnest = new widget.TextBox();
        NmPetugasAnest = new widget.TextBox();
        btnPetugasRuangan = new widget.Button();
        jLabel12 = new widget.Label();
        TglLahir = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        jLabel90 = new widget.Label();
        JumlahDrain = new widget.TextBox();
        SNCN = new widget.TextBox();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        KodeDokterBedah = new widget.TextBox();
        NamaDokterBedah = new widget.TextBox();
        btnDokterBedah = new widget.Button();
        btnDokterAnestesi = new widget.Button();
        NamaDokterAnestesi = new widget.TextBox();
        KodeDokterAnestesi = new widget.TextBox();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        KeadaanUmum = new widget.ComboBox();
        jLabel95 = new widget.Label();
        Drain = new widget.ComboBox();
        Tindakan = new widget.TextBox();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        Radiologi = new widget.ComboBox();
        KeteranganRadiologi = new widget.TextBox();
        jLabel98 = new widget.Label();
        KeteranganEKG = new widget.TextBox();
        EKG = new widget.ComboBox();
        jLabel99 = new widget.Label();
        CTScan = new widget.ComboBox();
        KeteranganCTScan = new widget.TextBox();
        jLabel100 = new widget.Label();
        USG = new widget.ComboBox();
        KeteranganUSG = new widget.TextBox();
        jLabel101 = new widget.Label();
        MRI = new widget.ComboBox();
        KeteranganMRI = new widget.TextBox();
        jLabel102 = new widget.Label();
        KdPetugasOK = new widget.TextBox();
        NmPetugasOK = new widget.TextBox();
        btnPetugasOK = new widget.Button();
        jLabel13 = new widget.Label();
        jLabel103 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        AreaLukaOperasi = new widget.TextBox();
        KateterUrine = new widget.ComboBox();
        jLabel106 = new widget.Label();
        TanggalKateter = new widget.Tanggal();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        WarnaUrine = new widget.ComboBox();
        jLabel109 = new widget.Label();
        JumlahUrine = new widget.TextBox();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        LetakDrain = new widget.TextBox();
        WarnaDrain = new widget.TextBox();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        CairanInfus = new widget.TextBox();
        jLabel117 = new widget.Label();
        JaringanPA = new widget.ComboBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        CmbPosisiOp = new widget.ComboBox();
        jLabel120 = new widget.Label();
        CmbJenisOp = new widget.ComboBox();
        jLabel121 = new widget.Label();
        ChkSpinal = new widget.CekBox();
        ChkGA = new widget.CekBox();
        ChkLokal = new widget.CekBox();
        jLabel122 = new widget.Label();
        ChkTamYa = new widget.CekBox();
        ChkTamTidak = new widget.CekBox();
        jLabel123 = new widget.Label();
        CmbDiatermi = new widget.ComboBox();
        JumlahTampon = new widget.TextBox();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        JumlahCairanInfus = new widget.TextBox();
        jLabel126 = new widget.Label();
        jLabel127 = new widget.Label();
        JumlahCairanDarah = new widget.TextBox();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        JumlahCairanInfus5 = new widget.TextBox();
        jLabel131 = new widget.Label();
        jLabel132 = new widget.Label();
        JumlahCairanDarah5 = new widget.TextBox();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        JumlahCairanInfus1 = new widget.TextBox();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        JumlahCairanDarah1 = new widget.TextBox();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        JumlahCairanInfus7 = new widget.TextBox();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        JumlahCairanDarah7 = new widget.TextBox();
        jLabel142 = new widget.Label();
        jLabel143 = new widget.Label();
        JumlahCairanDrain = new widget.TextBox();
        jLabel144 = new widget.Label();
        jLabel145 = new widget.Label();
        JumlahPendarahan = new widget.TextBox();
        jLabel146 = new widget.Label();
        jLabel147 = new widget.Label();
        TD = new widget.TextBox();
        jLabel148 = new widget.Label();
        jLabel149 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel150 = new widget.Label();
        jLabel151 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel152 = new widget.Label();
        jLabel153 = new widget.Label();
        RR = new widget.TextBox();
        jLabel154 = new widget.Label();
        jLabel155 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        CatatanPerawat = new widget.TextArea();
        ChkTifa = new widget.CekBox();
        jLabel156 = new widget.Label();
        JumlahCairanUrine = new widget.TextBox();
        jLabel157 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel72 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel87 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelGlass11 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPostOperasi.setBackground(new java.awt.Color(255, 255, 254));
        MnPostOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPostOperasi.setForeground(new java.awt.Color(50, 50, 50));
        MnPostOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPostOperasi.setText("Formulir Checklist Post Operasi");
        MnPostOperasi.setName("MnPostOperasi"); // NOI18N
        MnPostOperasi.setPreferredSize(new java.awt.Dimension(260, 26));
        MnPostOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPostOperasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPostOperasi);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Check List Post Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput1.setBackground(new java.awt.Color(250, 255, 245));
        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 615));
        FormInput1.setLayout(null);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("No.Rawat");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(21, 10, 75, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput1.add(TNoRw);
        TNoRw.setBounds(79, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput1.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput1.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel88.setText("Tanggal :");
        jLabel88.setName("jLabel88"); // NOI18N
        jLabel88.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel88);
        jLabel88.setBounds(0, 40, 75, 23);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("Petugas Anestesi");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput1.add(jLabel89);
        jLabel89.setBounds(21, 330, 103, 23);

        KdPetugasAnest.setEditable(false);
        KdPetugasAnest.setHighlighter(null);
        KdPetugasAnest.setName("KdPetugasAnest"); // NOI18N
        FormInput1.add(KdPetugasAnest);
        KdPetugasAnest.setBounds(117, 330, 95, 23);

        NmPetugasAnest.setEditable(false);
        NmPetugasAnest.setName("NmPetugasAnest"); // NOI18N
        FormInput1.add(NmPetugasAnest);
        NmPetugasAnest.setBounds(214, 330, 165, 23);

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
        FormInput1.add(btnPetugasRuangan);
        btnPetugasRuangan.setBounds(381, 330, 28, 23);

        jLabel12.setText("Tgl.Lahir :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput1.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026 03:48:48" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput1.add(Tanggal);
        Tanggal.setBounds(79, 40, 130, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("Serah Terima Perawat Kamar Operasi Dengan Anestesi / Intensif / Ruangan. Perawat Melakukan Serah Terima Secara Verbal :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput1.add(jLabel90);
        jLabel90.setBounds(21, 100, 660, 23);

        JumlahDrain.setHighlighter(null);
        JumlahDrain.setName("JumlahDrain"); // NOI18N
        JumlahDrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahDrainKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahDrain);
        JumlahDrain.setBounds(356, 180, 40, 23);

        SNCN.setHighlighter(null);
        SNCN.setName("SNCN"); // NOI18N
        SNCN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SNCNKeyPressed(evt);
            }
        });
        FormInput1.add(SNCN);
        SNCN.setBounds(264, 40, 120, 23);

        jLabel91.setText("SN/CN :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput1.add(jLabel91);
        jLabel91.setBounds(210, 40, 50, 23);

        jLabel92.setText("Dokter Bedah :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput1.add(jLabel92);
        jLabel92.setBounds(390, 40, 91, 23);

        KodeDokterBedah.setEditable(false);
        KodeDokterBedah.setHighlighter(null);
        KodeDokterBedah.setName("KodeDokterBedah"); // NOI18N
        FormInput1.add(KodeDokterBedah);
        KodeDokterBedah.setBounds(485, 40, 97, 23);

        NamaDokterBedah.setEditable(false);
        NamaDokterBedah.setName("NamaDokterBedah"); // NOI18N
        FormInput1.add(NamaDokterBedah);
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
        FormInput1.add(btnDokterBedah);
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
        FormInput1.add(btnDokterAnestesi);
        btnDokterAnestesi.setBounds(761, 70, 28, 23);

        NamaDokterAnestesi.setEditable(false);
        NamaDokterAnestesi.setName("NamaDokterAnestesi"); // NOI18N
        FormInput1.add(NamaDokterAnestesi);
        NamaDokterAnestesi.setBounds(584, 70, 175, 23);

        KodeDokterAnestesi.setEditable(false);
        KodeDokterAnestesi.setHighlighter(null);
        KodeDokterAnestesi.setName("KodeDokterAnestesi"); // NOI18N
        FormInput1.add(KodeDokterAnestesi);
        KodeDokterAnestesi.setBounds(485, 70, 97, 23);

        jLabel93.setText("Dokter Anestesi :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput1.add(jLabel93);
        jLabel93.setBounds(390, 70, 91, 23);

        jLabel94.setText("Tindakan :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput1.add(jLabel94);
        jLabel94.setBounds(0, 70, 75, 23);

        KeadaanUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sadar", "Compos Mentis", "Tidur", "Terintubasi", "Apatis", "Samnolen", "Soporocoma", "Coma", " ", " " }));
        KeadaanUmum.setName("KeadaanUmum"); // NOI18N
        KeadaanUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanUmumKeyPressed(evt);
            }
        });
        FormInput1.add(KeadaanUmum);
        KeadaanUmum.setBounds(144, 120, 110, 23);

        jLabel95.setText(":");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput1.add(jLabel95);
        jLabel95.setBounds(0, 120, 140, 23);

        Drain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        Drain.setName("Drain"); // NOI18N
        Drain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DrainKeyPressed(evt);
            }
        });
        FormInput1.add(Drain);
        Drain.setBounds(144, 180, 110, 23);

        Tindakan.setHighlighter(null);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        FormInput1.add(Tindakan);
        Tindakan.setBounds(79, 70, 305, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("Kelengkapan Penunjang :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput1.add(jLabel96);
        jLabel96.setBounds(56, 210, 210, 23);

        jLabel97.setText("Radiologi :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput1.add(jLabel97);
        jLabel97.setBounds(66, 230, 74, 23);

        Radiologi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        Radiologi.setName("Radiologi"); // NOI18N
        Radiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RadiologiKeyPressed(evt);
            }
        });
        FormInput1.add(Radiologi);
        Radiologi.setBounds(144, 230, 100, 23);

        KeteranganRadiologi.setHighlighter(null);
        KeteranganRadiologi.setName("KeteranganRadiologi"); // NOI18N
        KeteranganRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRadiologiKeyPressed(evt);
            }
        });
        FormInput1.add(KeteranganRadiologi);
        KeteranganRadiologi.setBounds(247, 230, 75, 23);

        jLabel98.setText("EKG :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput1.add(jLabel98);
        jLabel98.setBounds(329, 230, 55, 23);

        KeteranganEKG.setHighlighter(null);
        KeteranganEKG.setName("KeteranganEKG"); // NOI18N
        KeteranganEKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEKGKeyPressed(evt);
            }
        });
        FormInput1.add(KeteranganEKG);
        KeteranganEKG.setBounds(491, 230, 75, 23);

        EKG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        EKG.setName("EKG"); // NOI18N
        EKG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKGKeyPressed(evt);
            }
        });
        FormInput1.add(EKG);
        EKG.setBounds(388, 230, 100, 23);

        jLabel99.setText("CT Scan :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput1.add(jLabel99);
        jLabel99.setBounds(329, 260, 55, 23);

        CTScan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        CTScan.setName("CTScan"); // NOI18N
        CTScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CTScanKeyPressed(evt);
            }
        });
        FormInput1.add(CTScan);
        CTScan.setBounds(388, 260, 100, 23);

        KeteranganCTScan.setHighlighter(null);
        KeteranganCTScan.setName("KeteranganCTScan"); // NOI18N
        KeteranganCTScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganCTScanKeyPressed(evt);
            }
        });
        FormInput1.add(KeteranganCTScan);
        KeteranganCTScan.setBounds(491, 260, 75, 23);

        jLabel100.setText("USG :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput1.add(jLabel100);
        jLabel100.setBounds(66, 260, 74, 23);

        USG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        USG.setName("USG"); // NOI18N
        USG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                USGKeyPressed(evt);
            }
        });
        FormInput1.add(USG);
        USG.setBounds(144, 260, 100, 23);

        KeteranganUSG.setHighlighter(null);
        KeteranganUSG.setName("KeteranganUSG"); // NOI18N
        KeteranganUSG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganUSGKeyPressed(evt);
            }
        });
        FormInput1.add(KeteranganUSG);
        KeteranganUSG.setBounds(247, 260, 75, 23);

        jLabel101.setText("MRI :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput1.add(jLabel101);
        jLabel101.setBounds(567, 230, 40, 23);

        MRI.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        MRI.setName("MRI"); // NOI18N
        MRI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MRIKeyPressed(evt);
            }
        });
        FormInput1.add(MRI);
        MRI.setBounds(611, 230, 100, 23);

        KeteranganMRI.setHighlighter(null);
        KeteranganMRI.setName("KeteranganMRI"); // NOI18N
        KeteranganMRI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganMRIKeyPressed(evt);
            }
        });
        FormInput1.add(KeteranganMRI);
        KeteranganMRI.setBounds(714, 230, 75, 23);

        jLabel102.setText("Petugas OK :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput1.add(jLabel102);
        jLabel102.setBounds(423, 330, 70, 23);

        KdPetugasOK.setEditable(false);
        KdPetugasOK.setHighlighter(null);
        KdPetugasOK.setName("KdPetugasOK"); // NOI18N
        FormInput1.add(KdPetugasOK);
        KdPetugasOK.setBounds(497, 330, 95, 23);

        NmPetugasOK.setEditable(false);
        NmPetugasOK.setName("NmPetugasOK"); // NOI18N
        FormInput1.add(NmPetugasOK);
        NmPetugasOK.setBounds(594, 330, 165, 23);

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
        FormInput1.add(btnPetugasOK);
        btnPetugasOK.setBounds(761, 330, 28, 23);

        jLabel13.setText(":");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(0, 10, 75, 23);

        jLabel103.setText("Drain :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput1.add(jLabel103);
        jLabel103.setBounds(0, 180, 140, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput1.add(jSeparator5);
        jSeparator5.setBounds(0, 100, 810, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput1.add(jSeparator6);
        jSeparator6.setBounds(0, 100, 810, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput1.add(jSeparator7);
        jSeparator7.setBounds(0, 320, 810, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput1.add(jSeparator8);
        jSeparator8.setBounds(0, 320, 810, 1);

        jLabel104.setText(":");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput1.add(jLabel104);
        jLabel104.setBounds(3, 330, 110, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("Area Luka Operasi");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput1.add(jLabel105);
        jLabel105.setBounds(56, 290, 100, 23);

        AreaLukaOperasi.setHighlighter(null);
        AreaLukaOperasi.setName("AreaLukaOperasi"); // NOI18N
        AreaLukaOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AreaLukaOperasiKeyPressed(evt);
            }
        });
        FormInput1.add(AreaLukaOperasi);
        AreaLukaOperasi.setBounds(156, 290, 633, 23);

        KateterUrine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        KateterUrine.setName("KateterUrine"); // NOI18N
        KateterUrine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KateterUrineKeyPressed(evt);
            }
        });
        FormInput1.add(KateterUrine);
        KateterUrine.setBounds(144, 150, 110, 23);

        jLabel106.setText("Kateter Urine :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput1.add(jLabel106);
        jLabel106.setBounds(0, 150, 140, 23);

        TanggalKateter.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKateter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026 03:48:48" }));
        TanggalKateter.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalKateter.setName("TanggalKateter"); // NOI18N
        TanggalKateter.setOpaque(false);
        TanggalKateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKateterKeyPressed(evt);
            }
        });
        FormInput1.add(TanggalKateter);
        TanggalKateter.setBounds(401, 150, 130, 23);

        jLabel107.setText("Jika Ada, Tgl.Pemasangan :");
        jLabel107.setName("jLabel107"); // NOI18N
        jLabel107.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel107);
        jLabel107.setBounds(247, 150, 150, 23);

        jLabel108.setText(", Warna :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput1.add(jLabel108);
        jLabel108.setBounds(529, 150, 52, 23);

        WarnaUrine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jernih", "Keruh", "Merah" }));
        WarnaUrine.setName("WarnaUrine"); // NOI18N
        WarnaUrine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaUrineKeyPressed(evt);
            }
        });
        FormInput1.add(WarnaUrine);
        WarnaUrine.setBounds(585, 150, 83, 23);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("buah, ");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput1.add(jLabel109);
        jLabel109.setBounds(399, 180, 40, 23);

        JumlahUrine.setHighlighter(null);
        JumlahUrine.setName("JumlahUrine"); // NOI18N
        JumlahUrine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahUrineKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahUrine);
        JumlahUrine.setBounds(727, 150, 45, 23);

        jLabel110.setText(", Jumlah :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput1.add(jLabel110);
        jLabel110.setBounds(653, 150, 70, 23);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Keadaan Umum");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput1.add(jLabel111);
        jLabel111.setBounds(56, 120, 100, 23);

        jLabel112.setText("Jika Ada, Jumlah :");
        jLabel112.setName("jLabel112"); // NOI18N
        jLabel112.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel112);
        jLabel112.setBounds(247, 180, 105, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("cc");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput1.add(jLabel113);
        jLabel113.setBounds(775, 150, 20, 23);

        jLabel114.setText("Letak :");
        jLabel114.setName("jLabel114"); // NOI18N
        jLabel114.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel114);
        jLabel114.setBounds(420, 180, 44, 23);

        LetakDrain.setHighlighter(null);
        LetakDrain.setName("LetakDrain"); // NOI18N
        LetakDrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LetakDrainKeyPressed(evt);
            }
        });
        FormInput1.add(LetakDrain);
        LetakDrain.setBounds(468, 180, 112, 23);

        WarnaDrain.setHighlighter(null);
        WarnaDrain.setName("WarnaDrain"); // NOI18N
        WarnaDrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaDrainKeyPressed(evt);
            }
        });
        FormInput1.add(WarnaDrain);
        WarnaDrain.setBounds(677, 180, 112, 23);

        jLabel115.setText(", Warna/Produksi :");
        jLabel115.setName("jLabel115"); // NOI18N
        jLabel115.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel115);
        jLabel115.setBounds(583, 180, 90, 23);

        jLabel116.setText("Jenis Cairan Infus :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput1.add(jLabel116);
        jLabel116.setBounds(256, 120, 110, 23);

        CairanInfus.setHighlighter(null);
        CairanInfus.setName("CairanInfus"); // NOI18N
        CairanInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CairanInfusKeyPressed(evt);
            }
        });
        FormInput1.add(CairanInfus);
        CairanInfus.setBounds(370, 120, 150, 23);

        jLabel117.setText("Jaringan/Organ Tubuh PA/VC :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput1.add(jLabel117);
        jLabel117.setBounds(525, 120, 160, 23);

        JaringanPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        JaringanPA.setName("JaringanPA"); // NOI18N
        JaringanPA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JaringanPAKeyPressed(evt);
            }
        });
        FormInput1.add(JaringanPA);
        JaringanPA.setBounds(689, 120, 100, 23);

        jLabel118.setText(":");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput1.add(jLabel118);
        jLabel118.setBounds(0, 290, 152, 23);

        jLabel119.setText("Jenis Pembiusan :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput1.add(jLabel119);
        jLabel119.setBounds(30, 370, 140, 23);

        CmbPosisiOp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terlentang", "Tengkurap", "Litotomi", "Lateral" }));
        CmbPosisiOp.setName("CmbPosisiOp"); // NOI18N
        CmbPosisiOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbPosisiOpKeyPressed(evt);
            }
        });
        FormInput1.add(CmbPosisiOp);
        CmbPosisiOp.setBounds(170, 400, 110, 23);

        jLabel120.setText("Cairan Masuk :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput1.add(jLabel120);
        jLabel120.setBounds(320, 400, 140, 23);

        CmbJenisOp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Kotor", "Bersih Terkontaminasi", "Tercemar" }));
        CmbJenisOp.setName("CmbJenisOp"); // NOI18N
        CmbJenisOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJenisOpKeyPressed(evt);
            }
        });
        FormInput1.add(CmbJenisOp);
        CmbJenisOp.setBounds(170, 430, 110, 23);

        jLabel121.setText("Catatan Perawatan :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput1.add(jLabel121);
        jLabel121.setBounds(10, 560, 160, 23);

        ChkSpinal.setBorder(null);
        BtnGroupJnsPembiusan.add(ChkSpinal);
        ChkSpinal.setText("Spinal");
        ChkSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkSpinal.setName("ChkSpinal"); // NOI18N
        ChkSpinal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkSpinalItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkSpinal);
        ChkSpinal.setBounds(180, 370, 60, 23);

        ChkGA.setBorder(null);
        BtnGroupJnsPembiusan.add(ChkGA);
        ChkGA.setText("GA");
        ChkGA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGA.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkGA.setName("ChkGA"); // NOI18N
        ChkGA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkGAItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkGA);
        ChkGA.setBounds(248, 370, 50, 23);

        ChkLokal.setBorder(null);
        BtnGroupJnsPembiusan.add(ChkLokal);
        ChkLokal.setText("Lokal");
        ChkLokal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLokal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkLokal.setName("ChkLokal"); // NOI18N
        ChkLokal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkLokalItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkLokal);
        ChkLokal.setBounds(318, 370, 50, 23);

        jLabel122.setText("Tampon :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput1.add(jLabel122);
        jLabel122.setBounds(30, 490, 140, 23);

        ChkTamYa.setBorder(null);
        BtnGroupTampon.add(ChkTamYa);
        ChkTamYa.setText("Ya");
        ChkTamYa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTamYa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkTamYa.setName("ChkTamYa"); // NOI18N
        ChkTamYa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTamYaItemStateChanged(evt);
            }
        });
        ChkTamYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTamYaActionPerformed(evt);
            }
        });
        FormInput1.add(ChkTamYa);
        ChkTamYa.setBounds(180, 490, 60, 23);

        ChkTamTidak.setBorder(null);
        BtnGroupTampon.add(ChkTamTidak);
        ChkTamTidak.setText("Tidak");
        ChkTamTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTamTidak.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkTamTidak.setName("ChkTamTidak"); // NOI18N
        ChkTamTidak.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTamTidakItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkTamTidak);
        ChkTamTidak.setBounds(340, 490, 50, 23);

        jLabel123.setText("Diatermi :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput1.add(jLabel123);
        jLabel123.setBounds(30, 460, 140, 23);

        CmbDiatermi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        CmbDiatermi.setName("CmbDiatermi"); // NOI18N
        CmbDiatermi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDiatermiKeyPressed(evt);
            }
        });
        FormInput1.add(CmbDiatermi);
        CmbDiatermi.setBounds(170, 460, 110, 23);

        JumlahTampon.setHighlighter(null);
        JumlahTampon.setName("JumlahTampon"); // NOI18N
        JumlahTampon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahTamponActionPerformed(evt);
            }
        });
        JumlahTampon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahTamponKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahTampon);
        JumlahTampon.setBounds(250, 490, 40, 23);

        jLabel124.setText("Posisi Operasi :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput1.add(jLabel124);
        jLabel124.setBounds(30, 400, 140, 23);

        jLabel125.setText("Infus :");
        jLabel125.setName("jLabel125"); // NOI18N
        jLabel125.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel125);
        jLabel125.setBounds(450, 400, 60, 23);

        JumlahCairanInfus.setHighlighter(null);
        JumlahCairanInfus.setName("JumlahCairanInfus"); // NOI18N
        JumlahCairanInfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanInfusKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanInfus);
        JumlahCairanInfus.setBounds(510, 400, 50, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("CC ");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput1.add(jLabel126);
        jLabel126.setBounds(560, 400, 30, 23);

        jLabel127.setText("Darah :");
        jLabel127.setName("jLabel127"); // NOI18N
        jLabel127.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel127);
        jLabel127.setBounds(450, 430, 60, 23);

        JumlahCairanDarah.setHighlighter(null);
        JumlahCairanDarah.setName("JumlahCairanDarah"); // NOI18N
        JumlahCairanDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanDarahKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanDarah);
        JumlahCairanDarah.setBounds(510, 430, 50, 23);

        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("CC ");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput1.add(jLabel128);
        jLabel128.setBounds(560, 430, 30, 23);

        jLabel129.setText("Cairan Masuk :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput1.add(jLabel129);
        jLabel129.setBounds(320, 400, 140, 23);

        jLabel130.setText("Infus :");
        jLabel130.setName("jLabel130"); // NOI18N
        jLabel130.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel130);
        jLabel130.setBounds(450, 400, 60, 23);

        JumlahCairanInfus5.setHighlighter(null);
        JumlahCairanInfus5.setName("JumlahCairanInfus5"); // NOI18N
        JumlahCairanInfus5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanInfus5KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanInfus5);
        JumlahCairanInfus5.setBounds(510, 400, 50, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("CC ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput1.add(jLabel131);
        jLabel131.setBounds(560, 400, 30, 23);

        jLabel132.setText("Darah :");
        jLabel132.setName("jLabel132"); // NOI18N
        jLabel132.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel132);
        jLabel132.setBounds(450, 430, 60, 23);

        JumlahCairanDarah5.setHighlighter(null);
        JumlahCairanDarah5.setName("JumlahCairanDarah5"); // NOI18N
        JumlahCairanDarah5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanDarah5KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanDarah5);
        JumlahCairanDarah5.setBounds(510, 430, 50, 23);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("CC ");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput1.add(jLabel133);
        jLabel133.setBounds(560, 430, 30, 23);

        jLabel134.setText("Cairan Keluar :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput1.add(jLabel134);
        jLabel134.setBounds(570, 400, 140, 23);

        jLabel135.setText("Infus :");
        jLabel135.setName("jLabel135"); // NOI18N
        jLabel135.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel135);
        jLabel135.setBounds(690, 400, 60, 23);

        JumlahCairanInfus1.setHighlighter(null);
        JumlahCairanInfus1.setName("JumlahCairanInfus1"); // NOI18N
        JumlahCairanInfus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanInfus1KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanInfus1);
        JumlahCairanInfus1.setBounds(750, 400, 50, 23);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("CC ");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput1.add(jLabel136);
        jLabel136.setBounds(800, 400, 30, 23);

        jLabel137.setText("Darah :");
        jLabel137.setName("jLabel137"); // NOI18N
        jLabel137.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel137);
        jLabel137.setBounds(690, 430, 60, 23);

        JumlahCairanDarah1.setHighlighter(null);
        JumlahCairanDarah1.setName("JumlahCairanDarah1"); // NOI18N
        JumlahCairanDarah1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanDarah1KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanDarah1);
        JumlahCairanDarah1.setBounds(750, 430, 50, 23);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("CC ");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput1.add(jLabel138);
        jLabel138.setBounds(800, 430, 30, 23);

        jLabel139.setText("Infus :");
        jLabel139.setName("jLabel139"); // NOI18N
        jLabel139.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel139);
        jLabel139.setBounds(690, 400, 60, 23);

        JumlahCairanInfus7.setHighlighter(null);
        JumlahCairanInfus7.setName("JumlahCairanInfus7"); // NOI18N
        JumlahCairanInfus7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanInfus7KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanInfus7);
        JumlahCairanInfus7.setBounds(750, 400, 50, 23);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("CC ");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput1.add(jLabel140);
        jLabel140.setBounds(800, 400, 30, 23);

        jLabel141.setText("Darah :");
        jLabel141.setName("jLabel141"); // NOI18N
        jLabel141.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel141);
        jLabel141.setBounds(690, 430, 60, 23);

        JumlahCairanDarah7.setHighlighter(null);
        JumlahCairanDarah7.setName("JumlahCairanDarah7"); // NOI18N
        JumlahCairanDarah7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanDarah7KeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanDarah7);
        JumlahCairanDarah7.setBounds(750, 430, 50, 23);

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("CC ");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput1.add(jLabel142);
        jLabel142.setBounds(800, 430, 30, 23);

        jLabel143.setText("Drain :");
        jLabel143.setName("jLabel143"); // NOI18N
        jLabel143.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel143);
        jLabel143.setBounds(690, 460, 60, 23);

        JumlahCairanDrain.setHighlighter(null);
        JumlahCairanDrain.setName("JumlahCairanDrain"); // NOI18N
        JumlahCairanDrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanDrainKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanDrain);
        JumlahCairanDrain.setBounds(750, 460, 50, 23);

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("CC ");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput1.add(jLabel144);
        jLabel144.setBounds(800, 460, 30, 23);

        jLabel145.setText("Pendarahan :");
        jLabel145.setName("jLabel145"); // NOI18N
        jLabel145.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel145);
        jLabel145.setBounds(420, 370, 80, 23);

        JumlahPendarahan.setHighlighter(null);
        JumlahPendarahan.setName("JumlahPendarahan"); // NOI18N
        JumlahPendarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahPendarahanKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahPendarahan);
        JumlahPendarahan.setBounds(510, 370, 50, 23);

        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("CC ");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput1.add(jLabel146);
        jLabel146.setBounds(560, 370, 30, 23);

        jLabel147.setText("TD :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput1.add(jLabel147);
        jLabel147.setBounds(0, 520, 127, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput1.add(TD);
        TD.setBounds(131, 520, 76, 23);

        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("mmHg");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput1.add(jLabel148);
        jLabel148.setBounds(210, 520, 50, 23);

        jLabel149.setText("Nadi :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput1.add(jLabel149);
        jLabel149.setBounds(278, 520, 40, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput1.add(Nadi);
        Nadi.setBounds(322, 520, 45, 23);

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("x/menit");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput1.add(jLabel150);
        jLabel150.setBounds(370, 520, 50, 23);

        jLabel151.setText("Suhu :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput1.add(jLabel151);
        jLabel151.setBounds(606, 520, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput1.add(Suhu);
        Suhu.setBounds(650, 520, 45, 23);

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("°C");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput1.add(jLabel152);
        jLabel152.setBounds(698, 520, 30, 23);

        jLabel153.setText("RR :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput1.add(jLabel153);
        jLabel153.setBounds(435, 520, 40, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput1.add(RR);
        RR.setBounds(479, 520, 45, 23);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel154.setText("x/menit");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput1.add(jLabel154);
        jLabel154.setBounds(527, 520, 50, 23);

        jLabel155.setText("Jenis Operasi :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput1.add(jLabel155);
        jLabel155.setBounds(30, 430, 140, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        CatatanPerawat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        CatatanPerawat.setColumns(20);
        CatatanPerawat.setRows(5);
        CatatanPerawat.setName("CatatanPerawat"); // NOI18N
        CatatanPerawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanPerawatKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(CatatanPerawat);

        FormInput1.add(scrollPane5);
        scrollPane5.setBounds(170, 560, 560, 42);

        ChkTifa.setBorder(null);
        BtnGroupJnsPembiusan.add(ChkTifa);
        ChkTifa.setText("Tifa");
        ChkTifa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTifa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkTifa.setName("ChkTifa"); // NOI18N
        ChkTifa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTifaItemStateChanged(evt);
            }
        });
        FormInput1.add(ChkTifa);
        ChkTifa.setBounds(370, 370, 50, 23);

        jLabel156.setText("Urine :");
        jLabel156.setName("jLabel156"); // NOI18N
        jLabel156.setVerifyInputWhenFocusTarget(false);
        FormInput1.add(jLabel156);
        jLabel156.setBounds(690, 490, 60, 23);

        JumlahCairanUrine.setHighlighter(null);
        JumlahCairanUrine.setName("JumlahCairanUrine"); // NOI18N
        JumlahCairanUrine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahCairanUrineKeyPressed(evt);
            }
        });
        FormInput1.add(JumlahCairanUrine);
        JumlahCairanUrine.setBounds(750, 490, 50, 23);

        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel157.setText("CC ");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput1.add(jLabel157);
        jLabel157.setBounds(800, 490, 30, 23);

        scrollInput1.setViewportView(FormInput1);

        internalFrame2.add(scrollInput1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Cheklist Post OP", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

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
        Scroll2.setViewportView(tbObat);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel72.setText("Tanggal :");
        jLabel72.setName("jLabel72"); // NOI18N
        jLabel72.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass12.add(jLabel72);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(DTPCari1);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("s.d.");
        jLabel87.setName("jLabel87"); // NOI18N
        jLabel87.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel87);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(DTPCari2);

        jLabel10.setText("Key Word :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass12.add(TCari);

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
        panelGlass12.add(BtnCari);

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
        panelGlass12.add(BtnAll);

        internalFrame3.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Cheklist Post OP", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass11.add(BtnSimpan);

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
        panelGlass11.add(BtnBatal);

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
        panelGlass11.add(BtnHapus);

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
        panelGlass11.add(BtnEdit);

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
        panelGlass11.add(BtnPrint);

        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass11.add(jLabel9);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(LCount);

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
        panelGlass11.add(BtnKeluar);

        internalFrame1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MnPostOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPostOperasiActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),35).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),34).toString():finger)+"\n"+Tanggal.getSelectedItem()); 
            finger2=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),37).toString()+"\nID "+(finger2.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),36).toString():finger2)+"\n"+Tanggal.getSelectedItem()); 
            Valid.MyReportqry("rptFormulirChecklistPostOperasi.jasper","report","::[ Formulir Check List Post Operasi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,checklist_post_operasi.tanggal,"+
                    "checklist_post_operasi.sncn,checklist_post_operasi.tindakan,checklist_post_operasi.kd_dokter_bedah,dokterbedah.nm_dokter as dokterbedah,"+
                    "checklist_post_operasi.kd_dokter_anestesi,dokteranestesi.nm_dokter as dokteranestesi,checklist_post_operasi.keadaan_umum,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_rontgen,checklist_post_operasi.keterangan_pemeriksaan_penunjang_rontgen,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_ekg,checklist_post_operasi.keterangan_pemeriksaan_penunjang_ekg,checklist_post_operasi.pemeriksaan_penunjang_usg,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_usg,checklist_post_operasi.pemeriksaan_penunjang_ctscan,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_ctscan,checklist_post_operasi.pemeriksaan_penunjang_mri,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_mri,checklist_post_operasi.jenis_cairan_infus,checklist_post_operasi.kateter_urine,"+
                    "checklist_post_operasi.tanggal_pemasangan_kateter,checklist_post_operasi.warna_kateter,checklist_post_operasi.jumlah_kateter,"+
                    "checklist_post_operasi.area_luka_operasi,checklist_post_operasi.drain,checklist_post_operasi.jumlah_drain,checklist_post_operasi.letak_drain,"+
                    "checklist_post_operasi.warna_drain,checklist_post_operasi.jaringan_pa,checklist_post_operasi.nip_perawat_ok,petugasok.nama as petugasok,"+
                    "checklist_post_operasi.nip_perawat_anestesi,petugasanestesi.nama as petugasanestesi "+
                    "from checklist_post_operasi inner join reg_periksa on checklist_post_operasi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_post_operasi.kd_dokter_bedah "+
                    "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_post_operasi.kd_dokter_anestesi "+
                    "inner join petugas as petugasanestesi on petugasanestesi.nip=checklist_post_operasi.nip_perawat_anestesi "+
                    "inner join petugas as petugasok on petugasok.nip=checklist_post_operasi.nip_perawat_ok "+
                    "where checklist_post_operasi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' and checklist_post_operasi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"' ",param);
        }
    }//GEN-LAST:event_MnPostOperasiActionPerformed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JKKeyPressed

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
            String pembiusan = "";
            if(ChkSpinal.isSelected()) pembiusan += "Spinal";
            if(ChkGA.isSelected()) pembiusan += "GA";
            if(ChkLokal.isSelected()) pembiusan += "Lokal";
            if(ChkTifa.isSelected()) pembiusan += "Tifa";
            String tampon = ChkTamYa.isSelected() ? "Ya" : "Tidak";
            if(Sequel.menyimpantf("checklist_post_operasi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",48,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),SNCN.getText(),Tindakan.getText(),
                KodeDokterBedah.getText(),KodeDokterAnestesi.getText(),KeadaanUmum.getSelectedItem().toString(),Radiologi.getSelectedItem().toString(),KeteranganRadiologi.getText(), 
                EKG.getSelectedItem().toString(),KeteranganEKG.getText(),USG.getSelectedItem().toString(),KeteranganUSG.getText(),CTScan.getSelectedItem().toString(),
                KeteranganCTScan.getText(),MRI.getSelectedItem().toString(),KeteranganMRI.getText(),CairanInfus.getText(),KateterUrine.getSelectedItem().toString(), 
                (KateterUrine.getSelectedIndex()==0?Valid.SetTgl(TanggalKateter.getSelectedItem()+"")+" "+TanggalKateter.getSelectedItem().toString().substring(11,19):"0000-00-00 00:00:0"),
                WarnaUrine.getSelectedItem().toString(),JumlahUrine.getText(),AreaLukaOperasi.getText(),Drain.getSelectedItem().toString(),JumlahDrain.getText(),LetakDrain.getText(),
                WarnaDrain.getText(),JaringanPA.getSelectedItem().toString(),KdPetugasOK.getText(),KdPetugasAnest.getText(),
                pembiusan,CmbPosisiOp.getSelectedItem().toString(),CmbJenisOp.getSelectedItem().toString(),CmbDiatermi.getSelectedItem().toString(),tampon,JumlahTampon.getText(),JumlahPendarahan.getText(),JumlahCairanInfus.getText(),JumlahCairanDarah.getText(),JumlahCairanInfus1.getText(),
                JumlahCairanDarah1.getText(),JumlahCairanDrain.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),CatatanPerawat.getText(),JumlahCairanUrine.getText()
            })==true){
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JK.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                    SNCN.getText(),Tindakan.getText(),KodeDokterBedah.getText(),NamaDokterBedah.getText(),KodeDokterAnestesi.getText(),NamaDokterAnestesi.getText(),KeadaanUmum.getSelectedItem().toString(),
                    Radiologi.getSelectedItem().toString(),KeteranganRadiologi.getText(),EKG.getSelectedItem().toString(),KeteranganEKG.getText(),USG.getSelectedItem().toString(),KeteranganUSG.getText(),
                    CTScan.getSelectedItem().toString(),KeteranganCTScan.getText(),MRI.getSelectedItem().toString(),KeteranganMRI.getText(),CairanInfus.getText(),KateterUrine.getSelectedItem().toString(),
                    (KateterUrine.getSelectedIndex()==0?Valid.SetTgl(TanggalKateter.getSelectedItem()+"")+" "+TanggalKateter.getSelectedItem().toString().substring(11,19):""),
                    WarnaUrine.getSelectedItem().toString(),JumlahUrine.getText(),AreaLukaOperasi.getText(),Drain.getSelectedItem().toString(),JumlahDrain.getText(),LetakDrain.getText(),WarnaDrain.getText(),
                    JaringanPA.getSelectedItem().toString(),KdPetugasOK.getText(),NmPetugasOK.getText(),KdPetugasAnest.getText(),NmPetugasAnest.getText(),
                    pembiusan,CmbPosisiOp.getSelectedItem().toString(),CmbJenisOp.getSelectedItem().toString(),CmbDiatermi.getSelectedItem().toString(),tampon,JumlahTampon.getText(),JumlahPendarahan.getText(),JumlahCairanInfus.getText(),JumlahCairanDarah.getText(),JumlahCairanInfus1.getText(),
                    JumlahCairanDarah1.getText(),JumlahCairanDrain.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),CatatanPerawat.getText(),JumlahCairanUrine.getText()
                });
                LCount.setText(""+tabMode.getRowCount());
                emptTeks();
                TabRawat.setSelectedIndex(1);
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
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
       if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else {
                if(akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString())){
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
                    if(akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString())||akses.getkode().equals(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString())){
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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>SN/CN</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tindakan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kode Dokter Bedah</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Dokter Bedah</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kode Dokter Anest</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Dokter Anestesi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keadaan Umum</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Radiologi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Radiologi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>EKG</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan EKG</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>USG</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan USG</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>CT Scan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan CT Scan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>MRI</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan MRI</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jenis Cairan Infus</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kateter Urine</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tgl.Pemasangan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Warna Urine</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jml.Urine</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Aarea Luka Operasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Drain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jml.Drain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Letak Drain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Warna Drain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jaringan PA</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>NIP OK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas Ruang OK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>NIP Anestesi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas Anestesi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jenis Bius</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Posisi Operasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jenis Operasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Diatermi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tampon</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jml Tampon</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Pendarahan (CC)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Masuk Infus</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Masuk Darah</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keluar Infus</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keluar Darah</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keluar Drain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>TD</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nadi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>RR</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Suhu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Catatan Perawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Jumlah Cairan Urine</b></td>"+
                    "</tr>"
                );
                
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,36).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,37).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,38).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,39).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,40).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,41).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,42).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,43).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,44).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,45).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,46).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,47).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,48).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,49).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,50).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,51).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,52).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,53).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,54).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,55).toString()+"</td>"+
                        "</tr>");
                }
                
                LoadHTML.setText(
                    "<html>"+
                      "<table width='5000px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>"
                );

                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
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
                bg.close();

                File f = new File("DataChecklistPostOperasi.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='3500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA CHECK LIST POST OPERASI<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
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

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPasienKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMKeyPressed

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

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
       Valid.pindah(evt,TCari,SNCN);
    }//GEN-LAST:event_TanggalKeyPressed

    private void JumlahDrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahDrainKeyPressed
         Valid.pindah(evt,Drain,LetakDrain);
    }//GEN-LAST:event_JumlahDrainKeyPressed

    private void SNCNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SNCNKeyPressed
       Valid.pindah(evt,Tanggal,Tindakan);
    }//GEN-LAST:event_SNCNKeyPressed

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
         Valid.pindah(evt,btnDokterBedah,KeadaanUmum);
    }//GEN-LAST:event_btnDokterAnestesiKeyPressed

    private void KeadaanUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanUmumKeyPressed
        Valid.pindah(evt,btnDokterAnestesi,CairanInfus);
    }//GEN-LAST:event_KeadaanUmumKeyPressed

    private void DrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DrainKeyPressed
       Valid.pindah(evt,JumlahUrine,JumlahDrain);
    }//GEN-LAST:event_DrainKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        Valid.pindah(evt,SNCN,btnDokterBedah);
    }//GEN-LAST:event_TindakanKeyPressed

    private void RadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RadiologiKeyPressed
       Valid.pindah(evt,WarnaDrain,KeteranganRadiologi);
    }//GEN-LAST:event_RadiologiKeyPressed

    private void KeteranganRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRadiologiKeyPressed
      Valid.pindah(evt,Radiologi,EKG);
    }//GEN-LAST:event_KeteranganRadiologiKeyPressed

    private void KeteranganEKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEKGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganEKGKeyPressed

    private void EKGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKGKeyPressed
         Valid.pindah(evt,KeteranganRadiologi,KeteranganEKG);
    }//GEN-LAST:event_EKGKeyPressed

    private void CTScanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CTScanKeyPressed
       Valid.pindah(evt,KeteranganUSG,KeteranganCTScan);
    }//GEN-LAST:event_CTScanKeyPressed

    private void KeteranganCTScanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganCTScanKeyPressed
        Valid.pindah(evt,CTScan,MRI);
    }//GEN-LAST:event_KeteranganCTScanKeyPressed

    private void USGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_USGKeyPressed
        Valid.pindah(evt,KeteranganEKG,KeteranganUSG);
    }//GEN-LAST:event_USGKeyPressed

    private void KeteranganUSGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganUSGKeyPressed
       Valid.pindah(evt,USG,CTScan);
    }//GEN-LAST:event_KeteranganUSGKeyPressed

    private void MRIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MRIKeyPressed
       Valid.pindah(evt,KeteranganCTScan,KeteranganMRI);
    }//GEN-LAST:event_MRIKeyPressed

    private void KeteranganMRIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganMRIKeyPressed
        Valid.pindah(evt,MRI,AreaLukaOperasi);
    }//GEN-LAST:event_KeteranganMRIKeyPressed

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

    private void AreaLukaOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaLukaOperasiKeyPressed
        Valid.pindah(evt,KeteranganMRI,btnPetugasRuangan);
    }//GEN-LAST:event_AreaLukaOperasiKeyPressed

    private void KateterUrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KateterUrineKeyPressed
         Valid.pindah(evt,JaringanPA,TanggalKateter);
    }//GEN-LAST:event_KateterUrineKeyPressed

    private void TanggalKateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKateterKeyPressed
         Valid.pindah2(evt,KateterUrine,WarnaUrine);
    }//GEN-LAST:event_TanggalKateterKeyPressed

    private void WarnaUrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaUrineKeyPressed
       Valid.pindah(evt,TanggalKateter,JumlahUrine);
    }//GEN-LAST:event_WarnaUrineKeyPressed

    private void JumlahUrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahUrineKeyPressed
        Valid.pindah(evt,WarnaUrine,Drain);
    }//GEN-LAST:event_JumlahUrineKeyPressed

    private void LetakDrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LetakDrainKeyPressed
        Valid.pindah(evt,JumlahDrain,WarnaDrain);
    }//GEN-LAST:event_LetakDrainKeyPressed

    private void WarnaDrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaDrainKeyPressed
        Valid.pindah(evt,LetakDrain,Radiologi);
    }//GEN-LAST:event_WarnaDrainKeyPressed

    private void CairanInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CairanInfusKeyPressed
        Valid.pindah(evt,KeadaanUmum,JaringanPA);
    }//GEN-LAST:event_CairanInfusKeyPressed

    private void JaringanPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JaringanPAKeyPressed
       Valid.pindah(evt,CairanInfus,KateterUrine);
    }//GEN-LAST:event_JaringanPAKeyPressed

    private void CmbPosisiOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbPosisiOpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbPosisiOpKeyPressed

    private void CmbJenisOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJenisOpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJenisOpKeyPressed

    private void ChkSpinalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkSpinalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSpinalItemStateChanged

    private void ChkGAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkGAItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkGAItemStateChanged

    private void ChkLokalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkLokalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkLokalItemStateChanged

    private void ChkTamYaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTamYaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTamYaItemStateChanged

    private void ChkTamYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTamYaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTamYaActionPerformed

    private void ChkTamTidakItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTamTidakItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTamTidakItemStateChanged

    private void CmbDiatermiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDiatermiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDiatermiKeyPressed

    private void JumlahTamponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahTamponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahTamponActionPerformed

    private void JumlahTamponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahTamponKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahTamponKeyPressed

    private void JumlahCairanInfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanInfusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanInfusKeyPressed

    private void JumlahCairanDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanDarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanDarahKeyPressed

    private void JumlahCairanInfus5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanInfus5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanInfus5KeyPressed

    private void JumlahCairanDarah5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanDarah5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanDarah5KeyPressed

    private void JumlahCairanInfus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanInfus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanInfus1KeyPressed

    private void JumlahCairanDarah1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanDarah1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanDarah1KeyPressed

    private void JumlahCairanInfus7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanInfus7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanInfus7KeyPressed

    private void JumlahCairanDarah7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanDarah7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanDarah7KeyPressed

    private void JumlahCairanDrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanDrainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanDrainKeyPressed

    private void JumlahPendarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahPendarahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahPendarahanKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RRKeyPressed

    private void CatatanPerawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanPerawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CatatanPerawatKeyPressed

    private void ChkTifaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTifaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTifaItemStateChanged

    private void JumlahCairanUrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahCairanUrineKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahCairanUrineKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMChecklistPostOperasi dialog = new RMChecklistPostOperasi(new javax.swing.JFrame(), true);
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
    private widget.TextBox AreaLukaOperasi;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private javax.swing.ButtonGroup BtnGroupJnsPembiusan;
    private javax.swing.ButtonGroup BtnGroupTampon;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CTScan;
    private widget.TextBox CairanInfus;
    private widget.TextArea CatatanPerawat;
    private widget.CekBox ChkGA;
    private widget.CekBox ChkLokal;
    private widget.CekBox ChkSpinal;
    private widget.CekBox ChkTamTidak;
    private widget.CekBox ChkTamYa;
    private widget.CekBox ChkTifa;
    private widget.ComboBox CmbDiatermi;
    private widget.ComboBox CmbJenisOp;
    private widget.ComboBox CmbPosisiOp;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Drain;
    private widget.ComboBox EKG;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox JK;
    private widget.ComboBox JaringanPA;
    private widget.TextBox JumlahCairanDarah;
    private widget.TextBox JumlahCairanDarah1;
    private widget.TextBox JumlahCairanDarah5;
    private widget.TextBox JumlahCairanDarah7;
    private widget.TextBox JumlahCairanDrain;
    private widget.TextBox JumlahCairanInfus;
    private widget.TextBox JumlahCairanInfus1;
    private widget.TextBox JumlahCairanInfus5;
    private widget.TextBox JumlahCairanInfus7;
    private widget.TextBox JumlahCairanUrine;
    private widget.TextBox JumlahDrain;
    private widget.TextBox JumlahPendarahan;
    private widget.TextBox JumlahTampon;
    private widget.TextBox JumlahUrine;
    private widget.ComboBox KateterUrine;
    private widget.TextBox KdPetugasAnest;
    private widget.TextBox KdPetugasOK;
    private widget.ComboBox KeadaanUmum;
    private widget.TextBox KeteranganCTScan;
    private widget.TextBox KeteranganEKG;
    private widget.TextBox KeteranganMRI;
    private widget.TextBox KeteranganRadiologi;
    private widget.TextBox KeteranganUSG;
    private widget.TextBox KodeDokterAnestesi;
    private widget.TextBox KodeDokterBedah;
    private widget.Label LCount;
    private widget.TextBox LetakDrain;
    private widget.editorpane LoadHTML;
    private widget.ComboBox MRI;
    private javax.swing.JMenuItem MnPostOperasi;
    private widget.TextBox Nadi;
    private widget.TextBox NamaDokterAnestesi;
    private widget.TextBox NamaDokterBedah;
    private widget.TextBox NmPetugasAnest;
    private widget.TextBox NmPetugasOK;
    private widget.TextBox RR;
    private widget.ComboBox Radiologi;
    private widget.TextBox SNCN;
    private widget.ScrollPane Scroll2;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tanggal;
    private widget.Tanggal TanggalKateter;
    private widget.TextBox TglLahir;
    private widget.TextBox Tindakan;
    private widget.ComboBox USG;
    private widget.TextBox WarnaDrain;
    private widget.ComboBox WarnaUrine;
    private widget.Button btnDokterAnestesi;
    private widget.Button btnDokterBedah;
    private widget.Button btnPetugasOK;
    private widget.Button btnPetugasRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel72;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.ScrollPane scrollInput1;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,checklist_post_operasi.tanggal,"+
                    "checklist_post_operasi.sncn,checklist_post_operasi.tindakan,checklist_post_operasi.kd_dokter_bedah,dokterbedah.nm_dokter as dokterbedah,"+
                    "checklist_post_operasi.kd_dokter_anestesi,dokteranestesi.nm_dokter as dokteranestesi,checklist_post_operasi.keadaan_umum,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_rontgen,checklist_post_operasi.keterangan_pemeriksaan_penunjang_rontgen,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_ekg,checklist_post_operasi.keterangan_pemeriksaan_penunjang_ekg,checklist_post_operasi.pemeriksaan_penunjang_usg,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_usg,checklist_post_operasi.pemeriksaan_penunjang_ctscan,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_ctscan,checklist_post_operasi.pemeriksaan_penunjang_mri,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_mri,checklist_post_operasi.jenis_cairan_infus,checklist_post_operasi.kateter_urine,"+
                    "checklist_post_operasi.tanggal_pemasangan_kateter,checklist_post_operasi.warna_kateter,checklist_post_operasi.jumlah_kateter,"+
                    "checklist_post_operasi.area_luka_operasi,checklist_post_operasi.drain,checklist_post_operasi.jumlah_drain,checklist_post_operasi.letak_drain,"+
                    "checklist_post_operasi.warna_drain,checklist_post_operasi.jaringan_pa,checklist_post_operasi.nip_perawat_ok,petugasok.nama as petugasok,"+
                    "checklist_post_operasi.nip_perawat_anestesi,petugasanestesi.nama as petugasanestesi, "+
                    "checklist_post_operasi.jenis_bius,checklist_post_operasi.posisi_op,checklist_post_operasi.jenis_op,checklist_post_operasi.diatermi,checklist_post_operasi.tampon,checklist_post_operasi.jumlah_tampon,checklist_post_operasi.jumlah_pendarahan," +
                    "checklist_post_operasi.masuk_ci,checklist_post_operasi.masuk_cd,checklist_post_operasi.keluar_ci,checklist_post_operasi.keluar_cd,checklist_post_operasi.keluar_drain,checklist_post_operasi.td,checklist_post_operasi.nadi,checklist_post_operasi.rr," +
                    "checklist_post_operasi.suhu,checklist_post_operasi.catatan_perawat,checklist_post_operasi.keluar_cu "+
                    "from checklist_post_operasi inner join reg_periksa on checklist_post_operasi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_post_operasi.kd_dokter_bedah "+
                    "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_post_operasi.kd_dokter_anestesi "+
                    "inner join petugas as petugasanestesi on petugasanestesi.nip=checklist_post_operasi.nip_perawat_anestesi "+
                    "inner join petugas as petugasok on petugasok.nip=checklist_post_operasi.nip_perawat_ok "+
                    "where checklist_post_operasi.tanggal between ? and ? order by checklist_post_operasi.tanggal ");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,checklist_post_operasi.tanggal,"+
                    "checklist_post_operasi.sncn,checklist_post_operasi.tindakan,checklist_post_operasi.kd_dokter_bedah,dokterbedah.nm_dokter as dokterbedah,"+
                    "checklist_post_operasi.kd_dokter_anestesi,dokteranestesi.nm_dokter as dokteranestesi,checklist_post_operasi.keadaan_umum,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_rontgen,checklist_post_operasi.keterangan_pemeriksaan_penunjang_rontgen,"+
                    "checklist_post_operasi.pemeriksaan_penunjang_ekg,checklist_post_operasi.keterangan_pemeriksaan_penunjang_ekg,checklist_post_operasi.pemeriksaan_penunjang_usg,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_usg,checklist_post_operasi.pemeriksaan_penunjang_ctscan,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_ctscan,checklist_post_operasi.pemeriksaan_penunjang_mri,"+
                    "checklist_post_operasi.keterangan_pemeriksaan_penunjang_mri,checklist_post_operasi.jenis_cairan_infus,checklist_post_operasi.kateter_urine,"+
                    "checklist_post_operasi.tanggal_pemasangan_kateter,checklist_post_operasi.warna_kateter,checklist_post_operasi.jumlah_kateter,"+
                    "checklist_post_operasi.area_luka_operasi,checklist_post_operasi.drain,checklist_post_operasi.jumlah_drain,checklist_post_operasi.letak_drain,"+
                    "checklist_post_operasi.warna_drain,checklist_post_operasi.jaringan_pa,checklist_post_operasi.nip_perawat_ok,petugasok.nama as petugasok,"+
                    "checklist_post_operasi.nip_perawat_anestesi,petugasanestesi.nama as petugasanestesi, "+
                    "checklist_post_operasi.jenis_bius,checklist_post_operasi.posisi_op,checklist_post_operasi.jenis_op,checklist_post_operasi.diatermi,checklist_post_operasi.tampon,checklist_post_operasi.jumlah_tampon,checklist_post_operasi.jumlah_pendarahan," +
                    "checklist_post_operasi.masuk_ci,checklist_post_operasi.masuk_cd,checklist_post_operasi.keluar_ci,checklist_post_operasi.keluar_cd,checklist_post_operasi.keluar_drain,checklist_post_operasi.td,checklist_post_operasi.nadi,checklist_post_operasi.rr," +
                    "checklist_post_operasi.suhu,checklist_post_operasi.catatan_perawat,checklist_post_operasi.keluar_cu "+
                    "from checklist_post_operasi inner join reg_periksa on checklist_post_operasi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter as dokterbedah on dokterbedah.kd_dokter=checklist_post_operasi.kd_dokter_bedah "+
                    "inner join dokter as dokteranestesi on dokteranestesi.kd_dokter=checklist_post_operasi.kd_dokter_anestesi "+
                    "inner join petugas as petugasanestesi on petugasanestesi.nip=checklist_post_operasi.nip_perawat_anestesi "+
                    "inner join petugas as petugasok on petugasok.nip=checklist_post_operasi.nip_perawat_ok "+
                    "where checklist_post_operasi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or dokterbedah.nm_dokter like ? or dokteranestesi.nm_dokter like ? or petugasanestesi.nama like ?) "+
                    "order by checklist_post_operasi.tanggal ");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),
                        rs.getString("tanggal"),rs.getString("sncn"),rs.getString("tindakan"),rs.getString("kd_dokter_bedah"),rs.getString("dokterbedah"),
                        rs.getString("kd_dokter_anestesi"),rs.getString("dokteranestesi"),rs.getString("keadaan_umum"),rs.getString("pemeriksaan_penunjang_rontgen"),
                        rs.getString("keterangan_pemeriksaan_penunjang_rontgen"),rs.getString("pemeriksaan_penunjang_ekg"),rs.getString("keterangan_pemeriksaan_penunjang_ekg"),
                        rs.getString("pemeriksaan_penunjang_usg"),rs.getString("keterangan_pemeriksaan_penunjang_usg"),rs.getString("pemeriksaan_penunjang_ctscan"),
                        rs.getString("keterangan_pemeriksaan_penunjang_ctscan"),rs.getString("pemeriksaan_penunjang_mri"),rs.getString("keterangan_pemeriksaan_penunjang_mri"),
                        rs.getString("jenis_cairan_infus"),rs.getString("kateter_urine"),rs.getString("tanggal_pemasangan_kateter"),rs.getString("warna_kateter"),
                        rs.getString("jumlah_kateter"),rs.getString("area_luka_operasi"),rs.getString("drain"),rs.getString("jumlah_drain"),rs.getString("letak_drain"),
                        rs.getString("warna_drain"),rs.getString("jaringan_pa"),rs.getString("nip_perawat_ok"),rs.getString("petugasok"),rs.getString("nip_perawat_anestesi"),
                        rs.getString("petugasanestesi"),rs.getString("jenis_bius"),rs.getString("posisi_op"),rs.getString("jenis_op"),rs.getString("diatermi"),rs.getString("tampon"),rs.getString("jumlah_tampon"),rs.getString("jumlah_pendarahan"),rs.getString("masuk_ci"),rs.getString("masuk_cd"),rs.getString("keluar_ci"),
                        rs.getString("keluar_cd"),rs.getString("keluar_drain"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("catatan_perawat"),rs.getString("keluar_cu")
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
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        SNCN.setText("");
        Tindakan.setText("");
        KodeDokterBedah.setText("");
        NamaDokterBedah.setText("");
        KodeDokterAnestesi.setText("");
        NamaDokterAnestesi.setText("");
        KeadaanUmum.setSelectedIndex(0);
        CairanInfus.setText("");
        JaringanPA.setSelectedIndex(0);
        KateterUrine.setSelectedIndex(0);
        TanggalKateter.setDate(new Date());
        WarnaUrine.setSelectedIndex(0);
        JumlahUrine.setText("");
        Drain.setSelectedIndex(0);
        JumlahDrain.setText("");
        LetakDrain.setText("");
        WarnaDrain.setText("");
        AreaLukaOperasi.setText("");
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
        
        // Tambahan baru
        ChkSpinal.setSelected(false);
        ChkGA.setSelected(false);
        ChkLokal.setSelected(false);
        ChkTifa.setSelected(false);

        ChkTamYa.setSelected(false);
        ChkTamTidak.setSelected(false);

        CmbPosisiOp.setSelectedIndex(0);
        CmbJenisOp.setSelectedIndex(0);
        CmbDiatermi.setSelectedIndex(0);

        JumlahTampon.setText("");
        JumlahPendarahan.setText("");

        JumlahCairanInfus.setText("");
        JumlahCairanDarah.setText("");
        JumlahCairanInfus1.setText("");
        JumlahCairanDarah1.setText("");
        JumlahCairanDrain.setText("");

        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");

        CatatanPerawat.setText("");
        JumlahCairanUrine.setText("");
        SNCN.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            SNCN.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Tindakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            KodeDokterBedah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NamaDokterBedah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KodeDokterAnestesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NamaDokterAnestesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            KeadaanUmum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Radiologi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            KeteranganRadiologi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            EKG.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            KeteranganEKG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            USG.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            KeteranganUSG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            CTScan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            KeteranganCTScan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            MRI.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            KeteranganMRI.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            CairanInfus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            KateterUrine.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            WarnaUrine.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            JumlahUrine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            AreaLukaOperasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Drain.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            JumlahDrain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            LetakDrain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            WarnaDrain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            JaringanPA.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            KdPetugasOK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            NmPetugasOK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            KdPetugasAnest.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            NmPetugasAnest.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Valid.SetTgl2(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            if(!tbObat.getValueAt(tbObat.getSelectedRow(),25).toString().equals("")){
                Valid.SetTgl2(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            }
            String jenisBius = tbObat.getValueAt(tbObat.getSelectedRow(),38).toString();

            ChkSpinal.setSelected(jenisBius.equals("Spinal"));
            ChkGA.setSelected(jenisBius.equals("GA"));
            ChkLokal.setSelected(jenisBius.equals("Lokal"));
            ChkTifa.setSelected(jenisBius.equals("Tifa"));

            CmbPosisiOp.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            CmbJenisOp.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            CmbDiatermi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());

            String tampon = tbObat.getValueAt(tbObat.getSelectedRow(),42).toString();
            ChkTamYa.setSelected(tampon.equals("Ya"));
            ChkTamTidak.setSelected(tampon.equals("Tidak"));

            JumlahTampon.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            JumlahPendarahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());

            JumlahCairanInfus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            JumlahCairanDarah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            JumlahCairanInfus1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            JumlahCairanDarah1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            JumlahCairanDrain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());

            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());

            CatatanPerawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            JumlahCairanUrine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
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
        isForm();
    }
    
    public void setNoRm(String norwt, Date tgl2,String KodeDokter,String NamaDokter,String Operasi) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        isForm();
        KodeDokterBedah.setText(KodeDokter);
        NamaDokterBedah.setText(NamaDokter);
        Tindakan.setText(Operasi);
    }
    
    private void isForm(){
//        if(ChkInput.isSelected()==true){
//            if(internalFrame1.getHeight()>558){
//                ChkInput.setVisible(false);
//                PanelInput.setPreferredSize(new Dimension(WIDTH,641));
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
        BtnSimpan.setEnabled(akses.getchecklist_post_operasi());
        BtnHapus.setEnabled(akses.getchecklist_post_operasi());
        BtnEdit.setEnabled(akses.getchecklist_post_operasi());
        BtnPrint.setEnabled(akses.getchecklist_post_operasi()); 
    }

    private void ganti() {
        String jenisBius = "";
        if(ChkSpinal.isSelected()) jenisBius = "Spinal";
        else if(ChkGA.isSelected()) jenisBius = "GA";
        else if(ChkLokal.isSelected()) jenisBius = "Lokal";
        else if(ChkTifa.isSelected()) jenisBius = "Tifa";
        
        String tampon = ChkTamYa.isSelected() ? "Ya" : "Tidak";
        if(Sequel.mengedittf("checklist_post_operasi","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,sncn=?,tindakan=?,kd_dokter_bedah=?,kd_dokter_anestesi=?,keadaan_umum=?,"+
                "pemeriksaan_penunjang_rontgen=?,keterangan_pemeriksaan_penunjang_rontgen=?,pemeriksaan_penunjang_ekg=?,keterangan_pemeriksaan_penunjang_ekg=?,"+
                "pemeriksaan_penunjang_usg=?,keterangan_pemeriksaan_penunjang_usg=?,pemeriksaan_penunjang_ctscan=?,keterangan_pemeriksaan_penunjang_ctscan=?,pemeriksaan_penunjang_mri=?,"+
                "keterangan_pemeriksaan_penunjang_mri=?,jenis_cairan_infus=?,kateter_urine=?,tanggal_pemasangan_kateter=?,warna_kateter=?,jumlah_kateter=?,area_luka_operasi=?,"+
                "drain=?,jumlah_drain=?,letak_drain=?,warna_drain=?,jaringan_pa=?,nip_perawat_ok=?,nip_perawat_anestesi=?,jenis_bius=?,posisi_op=?,jenis_op=?,diatermi=?,tampon=?," +
                "jumlah_tampon=?,jumlah_pendarahan=?," +
                "masuk_ci=?,masuk_cd=?,keluar_ci=?,keluar_cd=?,keluar_drain=?," +
                "td=?,nadi=?,rr=?,suhu=?,catatan_perawat=?,keluar_cu=?",50,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),SNCN.getText(),Tindakan.getText(),
                KodeDokterBedah.getText(),KodeDokterAnestesi.getText(),KeadaanUmum.getSelectedItem().toString(),Radiologi.getSelectedItem().toString(),KeteranganRadiologi.getText(), 
                EKG.getSelectedItem().toString(),KeteranganEKG.getText(),USG.getSelectedItem().toString(),KeteranganUSG.getText(),CTScan.getSelectedItem().toString(),
                KeteranganCTScan.getText(),MRI.getSelectedItem().toString(),KeteranganMRI.getText(),CairanInfus.getText(),KateterUrine.getSelectedItem().toString(), 
                (KateterUrine.getSelectedIndex()==0?Valid.SetTgl(TanggalKateter.getSelectedItem()+"")+" "+TanggalKateter.getSelectedItem().toString().substring(11,19):"0000-00-00 00:00:0"),
                WarnaUrine.getSelectedItem().toString(),JumlahUrine.getText(),AreaLukaOperasi.getText(),Drain.getSelectedItem().toString(),JumlahDrain.getText(),LetakDrain.getText(),
                WarnaDrain.getText(),JaringanPA.getSelectedItem().toString(),KdPetugasOK.getText(),KdPetugasAnest.getText(),
                jenisBius,CmbPosisiOp.getSelectedItem().toString(),CmbJenisOp.getSelectedItem().toString(),CmbDiatermi.getSelectedItem().toString(),tampon,JumlahTampon.getText(),JumlahPendarahan.getText(),JumlahCairanInfus.getText(),JumlahCairanDarah.getText(),JumlahCairanInfus1.getText(),
                JumlahCairanDarah1.getText(),JumlahCairanDrain.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),CatatanPerawat.getText(),JumlahCairanUrine.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
        })==true){
            tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
            tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
            tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
            tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
            tbObat.setValueAt(JK.getText(),tbObat.getSelectedRow(),4);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),5);
            tbObat.setValueAt(SNCN.getText(),tbObat.getSelectedRow(),6);
            tbObat.setValueAt(Tindakan.getText(),tbObat.getSelectedRow(),7);
            tbObat.setValueAt(KodeDokterBedah.getText(),tbObat.getSelectedRow(),8);
            tbObat.setValueAt(NamaDokterBedah.getText(),tbObat.getSelectedRow(),9);
            tbObat.setValueAt(KodeDokterAnestesi.getText(),tbObat.getSelectedRow(),10);
            tbObat.setValueAt(NamaDokterAnestesi.getText(),tbObat.getSelectedRow(),11);
            tbObat.setValueAt(KeadaanUmum.getSelectedItem().toString(),tbObat.getSelectedRow(),12);
            tbObat.setValueAt(Radiologi.getSelectedItem().toString(),tbObat.getSelectedRow(),13);
            tbObat.setValueAt(KeteranganRadiologi.getText(),tbObat.getSelectedRow(),14);
            tbObat.setValueAt(EKG.getSelectedItem().toString(),tbObat.getSelectedRow(),15);
            tbObat.setValueAt(KeteranganEKG.getText(),tbObat.getSelectedRow(),16);
            tbObat.setValueAt(USG.getSelectedItem().toString(),tbObat.getSelectedRow(),17);
            tbObat.setValueAt(KeteranganUSG.getText(),tbObat.getSelectedRow(),18);
            tbObat.setValueAt(CTScan.getSelectedItem().toString(),tbObat.getSelectedRow(),19);
            tbObat.setValueAt(KeteranganCTScan.getText(),tbObat.getSelectedRow(),20);
            tbObat.setValueAt(MRI.getSelectedItem().toString(),tbObat.getSelectedRow(),21);
            tbObat.setValueAt(KeteranganMRI.getText(),tbObat.getSelectedRow(),22);
            tbObat.setValueAt(CairanInfus.getText(),tbObat.getSelectedRow(),23);
            tbObat.setValueAt(KateterUrine.getSelectedItem().toString(),tbObat.getSelectedRow(),24);
            tbObat.setValueAt((KateterUrine.getSelectedIndex()==0?Valid.SetTgl(TanggalKateter.getSelectedItem()+"")+" "+TanggalKateter.getSelectedItem().toString().substring(11,19):""),tbObat.getSelectedRow(),25);
            tbObat.setValueAt(WarnaUrine.getSelectedItem().toString(),tbObat.getSelectedRow(),26);
            tbObat.setValueAt(JumlahUrine.getText(),tbObat.getSelectedRow(),27);
            tbObat.setValueAt(AreaLukaOperasi.getText(),tbObat.getSelectedRow(),28);
            tbObat.setValueAt(Drain.getSelectedItem().toString(),tbObat.getSelectedRow(),29);
            tbObat.setValueAt(JumlahDrain.getText(),tbObat.getSelectedRow(),30);
            tbObat.setValueAt(LetakDrain.getText(),tbObat.getSelectedRow(),31);
            tbObat.setValueAt(WarnaDrain.getText(),tbObat.getSelectedRow(),32);
            tbObat.setValueAt(JaringanPA.getSelectedItem().toString(),tbObat.getSelectedRow(),33);
            tbObat.setValueAt(KdPetugasOK.getText(),tbObat.getSelectedRow(),34);
            tbObat.setValueAt(NmPetugasOK.getText(),tbObat.getSelectedRow(),35);
            tbObat.setValueAt(KdPetugasAnest.getText(),tbObat.getSelectedRow(),36);
            tbObat.setValueAt(NmPetugasAnest.getText(),tbObat.getSelectedRow(),37);
            tbObat.setValueAt(jenisBius,tbObat.getSelectedRow(),38);
            tbObat.setValueAt(CmbPosisiOp.getSelectedItem().toString(),tbObat.getSelectedRow(),39);
            tbObat.setValueAt(CmbJenisOp.getSelectedItem().toString(),tbObat.getSelectedRow(),40);
            tbObat.setValueAt(CmbDiatermi.getSelectedItem().toString(),tbObat.getSelectedRow(),41);
            tbObat.setValueAt(tampon,tbObat.getSelectedRow(),42);
            tbObat.setValueAt(JumlahTampon.getText(),tbObat.getSelectedRow(),43);
            tbObat.setValueAt(JumlahPendarahan.getText(),tbObat.getSelectedRow(),44);
            tbObat.setValueAt(JumlahCairanInfus.getText(),tbObat.getSelectedRow(),45);
            tbObat.setValueAt(JumlahCairanDarah.getText(),tbObat.getSelectedRow(),46);
            tbObat.setValueAt(JumlahCairanInfus1.getText(),tbObat.getSelectedRow(),47);
            tbObat.setValueAt(JumlahCairanDarah1.getText(),tbObat.getSelectedRow(),48);
            tbObat.setValueAt(JumlahCairanDrain.getText(),tbObat.getSelectedRow(),49);
            tbObat.setValueAt(TD.getText(),tbObat.getSelectedRow(),50);
            tbObat.setValueAt(Nadi.getText(),tbObat.getSelectedRow(),51);
            tbObat.setValueAt(RR.getText(),tbObat.getSelectedRow(),52);
            tbObat.setValueAt(Suhu.getText(),tbObat.getSelectedRow(),53);
            tbObat.setValueAt(CatatanPerawat.getText(),tbObat.getSelectedRow(),54);
            tbObat.setValueAt(JumlahCairanUrine.getText(),tbObat.getSelectedRow(),55);
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from checklist_post_operasi where no_rawat=? and tanggal=?",2,new String[]{
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

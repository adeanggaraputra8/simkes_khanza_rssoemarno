/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laporan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import static jdk.internal.net.http.common.Log.headers;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgPasien;

/**
 *
 * @author khanzamedia
 */
public class PanelDiagnosa extends widget.panelisi {
    private final DefaultTableModel TabModeDiagnosaPasien,TabModeDiagnosaPasienIDRG,tabModeDiagnosa,tabModeProsedur,TabModeTindakanPasien,TabModeTindakanPasienIDRG,TabModeDiagnosaIDRG,TabModeProsedurIDRG;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    DlgPasien pasien=new DlgPasien(null,false);
    private PreparedStatement pspenyakit,psdiagnosapasien,psdiagnosapasienidrg,psprosedur,pstindakanpasien,pstindakanpasienidrg,ps,psdx,pspd;
    private ResultSet rs,rs2,rsdx,rspd,rsdxidrg,rspsidrg;
    private int jml=0,i=0,index=0,cekINADRG = 0,cekPremierINADRG = 0;
    private String[] kode,nama,ciripny,keterangan,kategori,cirium,kode2,panjang,pendek,kodedxidrg,namadxidrg,kodepsidrg,namapsidrg;
    private boolean[] pilih;
    public String norawat="",status="",norm="",tanggal1="",tanggal2="",keyword="",dxralan="",pdralan="",nosep="",sepvedikaralan="",sepvedikaranap="",URL = "",requestJsonDx = "", requestJsonPS = "",stringbalik="";
    private StringBuilder htmlContent;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    /**
     * Creates new form panelDiagnosa
     */
    public PanelDiagnosa() {
        initComponents();
        TabModeDiagnosaPasien=new DefaultTableModel(null,new Object[]{
            "P","Tgl.Rawat","No.Rawat","No.R.M.","Nama Pasien","Kode","Nama Penyakit",
            "Status","Kasus"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(160);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(350);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(50);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeDiagnosaPasienIDRG=new DefaultTableModel(null,new Object[]{
            "P","Tgl.Rawat","No.Rawat","No.R.M.","Nama Pasien","Kode","Nama Penyakit",
            "Status"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDiagnosaPasienIDRG.setModel(TabModeDiagnosaPasienIDRG);
        tbDiagnosaPasienIDRG.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiagnosaPasienIDRG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbDiagnosaPasienIDRG.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(160);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(350);
            }else if(i==7){
                column.setPreferredWidth(50);
            }
        }
        tbDiagnosaPasienIDRG.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDiagnosa=new DefaultTableModel(null,new Object[]{
            "P","Kode","Nama Penyakit","Ciri-ciri Penyakit","Keterangan","Ktg.Penyakit","Ciri-ciri Umum"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDiagnosa.setModel(tabModeDiagnosa);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i= 0; i < 7; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(40);
            }else if(i==2){
                column.setPreferredWidth(280);
            }else if(i==3){
                column.setPreferredWidth(285);
            }else if(i==4){
                column.setPreferredWidth(75);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeProsedur=new DefaultTableModel(null,new Object[]{
            "P","Kode","Deskripsi Panjang","Deskripsi Pendek"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbProsedur.setModel(tabModeProsedur);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(50);
            }else if(i==2){
                column.setPreferredWidth(350);
            }else if(i==3){
                column.setPreferredWidth(350);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeTindakanPasien=new DefaultTableModel(null,new Object[]{
            "P","Tgl.Rawat","No.Rawat","No.R.M.","Nama Pasien","Kode","Nama Prosedur","Status"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(160);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(300);
            }else if(i==7){
                column.setPreferredWidth(50);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeTindakanPasienIDRG=new DefaultTableModel(null,new Object[]{
            "P","Tgl.Rawat","No.Rawat","No.R.M.","Nama Pasien","Kode","Nama Prosedur","Status","Jumlah"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakanPasienIDRG.setModel(TabModeTindakanPasienIDRG);
        tbTindakanPasienIDRG.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTindakanPasienIDRG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasienIDRG.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(160);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(300);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==7){
                column.setPreferredWidth(80);
            }
        }
        tbTindakanPasienIDRG.setDefaultRenderer(Object.class, new WarnaTable());
         
        TabModeDiagnosaIDRG=new DefaultTableModel(null,new Object[]{
            "P","Kode Diagnosa IM","Nama Penyakit / Deskirpsi"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDiagnosaIDRG.setModel(TabModeDiagnosaIDRG);
        tbDiagnosaIDRG.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiagnosaIDRG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i= 0; i < 3; i++) {
            TableColumn column = tbDiagnosaIDRG.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(400);
            }
        }
        tbDiagnosaIDRG.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeProsedurIDRG=new DefaultTableModel(null,new Object[]{
            "P","Kode Prosedur IM","Nama Tindakan / Deskirpsi","Jumlah"}){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0 || colIndex==3) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbProsedurIDRG.setModel(TabModeProsedurIDRG);
        tbProsedurIDRG.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbProsedurIDRG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i= 0; i < 4; i++) {
            TableColumn column = tbProsedurIDRG.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(330);
            }else if(i==3){
                column.setPreferredWidth(80);
            }
        }
        tbProsedurIDRG.setDefaultRenderer(Object.class, new WarnaTable());
        
        Diagnosa.setDocument(new batasInput((byte)100).getKata(Diagnosa));
        Prosedur.setDocument(new batasInput((byte)100).getKata(Prosedur));
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTMLSOAPI.setEditorKit(kit);
         StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}.isi a{text-decoration:none;color:#8b9b95;padding:0 0 0 0px;font-family: Tahoma;font-size: 8.5px;border: white;}");
        Document doc = kit.createDefaultDocument();
        LoadHTMLSOAPI.setDocument(doc);
        LoadHTMLSOAPI.setEditable(false);
        LoadHTMLSOAPI.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                   desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
            }
        });
        
        LoadHTMLSOAPILAMA.setEditorKit(kit);
        LoadHTMLSOAPILAMA.setDocument(doc);
        LoadHTMLSOAPILAMA.setEditable(false);
        LoadHTMLSOAPILAMA.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                   desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
            }
        });
        LoadHTMLSOAPILAMA.setDocument(doc);
        LoadHTMLSOAPILAMA.setEditable(false);
        LoadHTMLSOAPILAMA.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                   desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
            }
        });
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            Diagnosa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(Diagnosa.getText().length()>2){;
                        tampildiagnosa();  
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(Diagnosa.getText().length()>2){
                        tampildiagnosa();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(Diagnosa.getText().length()>2){
                        tampildiagnosa();
                    }
                }
                
            });
        } 
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            Prosedur.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(Prosedur.getText().length()>2){
                        tampilprosedure();                       
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(Prosedur.getText().length()>2){
                        tampilprosedure();
                        
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(Prosedur.getText().length()>2){
 
                        tampilprosedure();
                    }
                }
            });
        }
        
        try {
            URL = koneksiDB.URL_EKLAIM_INACBG();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnStatusBaru = new javax.swing.JMenuItem();
        MnStatusLama = new javax.swing.JMenuItem();
        NoRM = new widget.TextBox();
        NmPasien = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        Jk = new widget.TextBox();
        TempatLahir = new widget.TextBox();
        Alamat = new widget.TextBox();
        GD = new widget.TextBox();
        IbuKandung = new widget.TextBox();
        TanggalLahir = new widget.TextBox();
        Agama = new widget.TextBox();
        StatusNikah = new widget.TextBox();
        Pendidikan = new widget.TextBox();
        Bahasa = new widget.TextBox();
        label29 = new widget.Label();
        CacatFisik = new widget.TextBox();
        Pekerjaan = new widget.TextBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        TabRawat = new javax.swing.JTabbedPane();
        ScrollInput = new widget.ScrollPane();
        FormData = new widget.PanelBiasa();
        jLabel13 = new widget.Label();
        Diagnosa = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        btnTambahPenyakit = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        jLabel15 = new widget.Label();
        Prosedur = new widget.TextBox();
        btnTambahProsedur = new widget.Button();
        BtnCariProsedur = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        DiagnosaIDRG = new widget.TextBox();
        jLabel14 = new widget.Label();
        BtnCariDiagnosaIDRG = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbDiagnosaIDRG = new widget.Table();
        jLabel16 = new widget.Label();
        ProsedurIDRG = new widget.TextBox();
        BtnCariProsedurIDRG = new widget.Button();
        Scroll7 = new widget.ScrollPane();
        tbProsedurIDRG = new widget.Table();
        jSeparator2 = new javax.swing.JSeparator();
        ChkCariDxIdrg = new widget.CekBox();
        ChkSalinPsIdrg = new widget.CekBox();
        internalFrame2 = new widget.InternalFrame();
        FormData1 = new widget.PanelBiasa();
        Scroll8 = new widget.ScrollPane();
        tbDiagnosaPasienIDRG = new widget.Table();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        FormData2 = new widget.PanelBiasa();
        Scroll9 = new widget.ScrollPane();
        tbTindakanPasienIDRG = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        LoadHTMLSOAPI = new widget.editorpane();
        panelGlass5 = new widget.panelisi();
        R1 = new widget.RadioButton();
        R2 = new widget.RadioButton();
        R3 = new widget.RadioButton();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        R4 = new widget.RadioButton();
        NoRawat = new widget.TextBox();
        BtnCari1 = new widget.Button();
        label19 = new widget.Label();
        BtnPrint = new widget.Button();
        internalFrame5 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        LoadHTMLSOAPILAMA = new widget.editorpane();
        panelGlass6 = new widget.panelisi();
        R5 = new widget.RadioButton();
        R6 = new widget.RadioButton();
        R7 = new widget.RadioButton();
        Tgl3 = new widget.Tanggal();
        label20 = new widget.Label();
        Tgl4 = new widget.Tanggal();
        BtnCari2 = new widget.Button();
        label21 = new widget.Label();
        BtnPrint1 = new widget.Button();

        MnStatusBaru.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusBaru.setForeground(new java.awt.Color(50, 50, 50));
        MnStatusBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusBaru.setText("Status Penyakit Baru");
        MnStatusBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusBaru.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusBaru.setPreferredSize(new java.awt.Dimension(170, 26));
        MnStatusBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusBaruActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusBaru);

        MnStatusLama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusLama.setForeground(new java.awt.Color(50, 50, 50));
        MnStatusLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusLama.setText("Status Penyakit Lama");
        MnStatusLama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusLama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusLama.setIconTextGap(5);
        MnStatusLama.setPreferredSize(new java.awt.Dimension(170, 26));
        MnStatusLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusLamaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusLama);

        NoRM.setPreferredSize(new java.awt.Dimension(100, 23));
        NoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMKeyPressed(evt);
            }
        });

        NmPasien.setEditable(false);
        NmPasien.setPreferredSize(new java.awt.Dimension(220, 23));

        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });

        Jk.setEditable(false);
        Jk.setPreferredSize(new java.awt.Dimension(100, 23));

        TempatLahir.setEditable(false);
        TempatLahir.setPreferredSize(new java.awt.Dimension(100, 23));

        Alamat.setEditable(false);
        Alamat.setPreferredSize(new java.awt.Dimension(100, 23));

        GD.setEditable(false);
        GD.setPreferredSize(new java.awt.Dimension(100, 23));

        IbuKandung.setEditable(false);
        IbuKandung.setPreferredSize(new java.awt.Dimension(100, 23));

        TanggalLahir.setEditable(false);
        TanggalLahir.setPreferredSize(new java.awt.Dimension(100, 23));

        Agama.setEditable(false);
        Agama.setPreferredSize(new java.awt.Dimension(100, 23));

        StatusNikah.setEditable(false);
        StatusNikah.setPreferredSize(new java.awt.Dimension(100, 23));

        Pendidikan.setEditable(false);
        Pendidikan.setPreferredSize(new java.awt.Dimension(100, 23));

        Bahasa.setEditable(false);
        Bahasa.setPreferredSize(new java.awt.Dimension(100, 23));

        label29.setText("Cacat Fisik :");
        label29.setPreferredSize(new java.awt.Dimension(55, 23));

        CacatFisik.setEditable(false);
        CacatFisik.setPreferredSize(new java.awt.Dimension(100, 23));

        Pekerjaan.setEditable(false);
        Pekerjaan.setPreferredSize(new java.awt.Dimension(100, 23));

        setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        ScrollInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ScrollInput.setOpaque(true);

        FormData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        FormData.setPreferredSize(new java.awt.Dimension(865, 417));
        FormData.setLayout(null);

        jLabel13.setText("Diagnosa :");
        FormData.add(jLabel13);
        jLabel13.setBounds(0, 10, 68, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormData.add(Diagnosa);
        Diagnosa.setBounds(71, 10, 650, 23);

        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormData.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(720, 10, 28, 23);

        btnTambahPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit.setMnemonic('2');
        btnTambahPenyakit.setToolTipText("Alt+2");
        btnTambahPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakitActionPerformed(evt);
            }
        });
        FormData.add(btnTambahPenyakit);
        btnTambahPenyakit.setBounds(760, 10, 28, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setOpaque(true);
        Scroll1.setViewportView(tbDiagnosa);

        FormData.add(Scroll1);
        Scroll1.setBounds(11, 36, 810, 165);

        jLabel15.setText("Prosedur :");
        FormData.add(jLabel15);
        jLabel15.setBounds(0, 240, 68, 23);

        Prosedur.setHighlighter(null);
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormData.add(Prosedur);
        Prosedur.setBounds(70, 240, 640, 23);

        btnTambahProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahProsedur.setMnemonic('2');
        btnTambahProsedur.setToolTipText("Alt+2");
        btnTambahProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProsedurActionPerformed(evt);
            }
        });
        FormData.add(btnTambahProsedur);
        btnTambahProsedur.setBounds(740, 240, 28, 23);

        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormData.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(710, 240, 28, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setOpaque(true);

        tbProsedur.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Scroll2.setViewportView(tbProsedur);

        FormData.add(Scroll2);
        Scroll2.setBounds(10, 270, 810, 165);

        DiagnosaIDRG.setHighlighter(null);
        DiagnosaIDRG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaIDRGKeyPressed(evt);
            }
        });
        FormData.add(DiagnosaIDRG);
        DiagnosaIDRG.setBounds(950, 10, 420, 23);

        jLabel14.setText("Diagnosa IDRG :");
        FormData.add(jLabel14);
        jLabel14.setBounds(840, 10, 100, 23);

        BtnCariDiagnosaIDRG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariDiagnosaIDRG.setMnemonic('1');
        BtnCariDiagnosaIDRG.setToolTipText("Alt+1");
        BtnCariDiagnosaIDRG.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariDiagnosaIDRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariDiagnosaIDRGActionPerformed(evt);
            }
        });
        FormData.add(BtnCariDiagnosaIDRG);
        BtnCariDiagnosaIDRG.setBounds(1380, 10, 28, 23);

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll6.setOpaque(true);
        Scroll6.setViewportView(tbDiagnosaIDRG);

        FormData.add(Scroll6);
        Scroll6.setBounds(850, 40, 630, 160);

        jLabel16.setText("Prosedur IDRG :");
        FormData.add(jLabel16);
        jLabel16.setBounds(850, 240, 90, 23);

        ProsedurIDRG.setHighlighter(null);
        ProsedurIDRG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurIDRGKeyPressed(evt);
            }
        });
        FormData.add(ProsedurIDRG);
        ProsedurIDRG.setBounds(940, 240, 430, 23);

        BtnCariProsedurIDRG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedurIDRG.setMnemonic('1');
        BtnCariProsedurIDRG.setToolTipText("Alt+1");
        BtnCariProsedurIDRG.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedurIDRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurIDRGActionPerformed(evt);
            }
        });
        FormData.add(BtnCariProsedurIDRG);
        BtnCariProsedurIDRG.setBounds(1380, 240, 28, 23);

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll7.setOpaque(true);

        tbProsedurIDRG.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Scroll7.setViewportView(tbProsedurIDRG);

        FormData.add(Scroll7);
        Scroll7.setBounds(860, 270, 620, 160);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        FormData.add(jSeparator2);
        jSeparator2.setBounds(833, 0, 10, 430);

        ChkCariDxIdrg.setBorder(null);
        ChkCariDxIdrg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkCariDxIdrg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkCariDxIdrg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkCariDxIdrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCariDxIdrgActionPerformed(evt);
            }
        });
        FormData.add(ChkCariDxIdrg);
        ChkCariDxIdrg.setBounds(790, 10, 23, 23);

        ChkSalinPsIdrg.setBorder(null);
        ChkSalinPsIdrg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkSalinPsIdrg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkSalinPsIdrg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkSalinPsIdrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSalinPsIdrgActionPerformed(evt);
            }
        });
        FormData.add(ChkSalinPsIdrg);
        ChkSalinPsIdrg.setBounds(780, 240, 23, 23);

        ScrollInput.setViewportView(FormData);

        TabRawat.addTab("Input Data", ScrollInput);

        internalFrame2.setBorder(null);
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        FormData1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        FormData1.setPreferredSize(new java.awt.Dimension(865, 417));
        FormData1.setLayout(null);

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll8.setOpaque(true);

        tbDiagnosaPasienIDRG.setAutoCreateRowSorter(true);
        tbDiagnosaPasienIDRG.setComponentPopupMenu(jPopupMenu1);
        Scroll8.setViewportView(tbDiagnosaPasienIDRG);

        FormData1.add(Scroll8);
        Scroll8.setBounds(570, 0, 550, 402);

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setOpaque(true);

        tbDiagnosaPasien.setAutoCreateRowSorter(true);
        tbDiagnosaPasien.setComponentPopupMenu(jPopupMenu1);
        Scroll.setViewportView(tbDiagnosaPasien);

        FormData1.add(Scroll);
        Scroll.setBounds(0, 0, 550, 402);

        internalFrame2.add(FormData1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Data Diagnosa", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        FormData2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        FormData2.setPreferredSize(new java.awt.Dimension(865, 417));
        FormData2.setLayout(null);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll9.setOpaque(true);

        tbTindakanPasienIDRG.setAutoCreateRowSorter(true);
        tbTindakanPasienIDRG.setComponentPopupMenu(jPopupMenu1);
        Scroll9.setViewportView(tbTindakanPasienIDRG);

        FormData2.add(Scroll9);
        Scroll9.setBounds(570, 0, 550, 402);

        Scroll3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll3.setOpaque(true);

        tbTindakanPasien.setAutoCreateRowSorter(true);
        tbTindakanPasien.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setViewportView(tbTindakanPasien);

        FormData2.add(Scroll3);
        Scroll3.setBounds(0, 0, 550, 402);

        internalFrame3.add(FormData2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Data Prosedur", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setBorder(null);
        Scroll4.setOpaque(true);

        LoadHTMLSOAPI.setBorder(null);
        Scroll4.setViewportView(LoadHTMLSOAPI);

        internalFrame4.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        R1.setBackground(new java.awt.Color(240, 250, 230));
        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("5 Riwayat Terakhir");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass5.add(R1);

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Semua Riwayat");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass5.add(R2);

        R3.setBackground(new java.awt.Color(240, 250, 230));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Tanggal :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass5.add(R3);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelGlass5.add(Tgl2);

        R4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R4);
        R4.setText("Nomor :");
        R4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R4.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass5.add(R4);

        NoRawat.setPreferredSize(new java.awt.Dimension(135, 23));
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });
        panelGlass5.add(NoRawat);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Cari data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari1);

        label19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label19.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass5.add(label19);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        internalFrame4.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Riwayat S.O.A.P.I.E", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setBorder(null);
        Scroll5.setOpaque(true);

        LoadHTMLSOAPILAMA.setBorder(null);
        Scroll5.setViewportView(LoadHTMLSOAPILAMA);

        internalFrame5.add(Scroll5, java.awt.BorderLayout.CENTER);

        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        R5.setBackground(new java.awt.Color(240, 250, 230));
        R5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(R5);
        R5.setSelected(true);
        R5.setText("5 Riwayat Terakhir");
        R5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R5.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass6.add(R5);

        R6.setBackground(new java.awt.Color(240, 250, 230));
        R6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(R6);
        R6.setText("Semua Riwayat");
        R6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R6.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass6.add(R6);

        R7.setBackground(new java.awt.Color(240, 250, 230));
        R7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(R7);
        R7.setText("Tanggal :");
        R7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass6.add(R7);

        Tgl3.setDisplayFormat("dd-MM-yyyy");
        Tgl3.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl3KeyPressed(evt);
            }
        });
        panelGlass6.add(Tgl3);

        label20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label20.setText("s.d.");
        label20.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(label20);

        Tgl4.setDisplayFormat("dd-MM-yyyy");
        Tgl4.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl4KeyPressed(evt);
            }
        });
        panelGlass6.add(Tgl4);

        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Cari data");
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass6.add(BtnCari2);

        label21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label21.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass6.add(label21);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnPrint1);

        internalFrame5.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Riwayat S.O.A.P Lama", internalFrame5);

        add(TabRawat, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampildiagnosa();
            if(ChkCariDxIdrg.isSelected()==true){
                TampilDiagnosaIDRG(Diagnosa.getText());
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(akses.getpenyakit()==true){
                btnTambahPenyakitActionPerformed(null);
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        tampildiagnosa();
        if(ChkCariDxIdrg.isSelected()==true){
                TampilDiagnosaIDRG(Diagnosa.getText());
            }
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void btnTambahPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit tariflab=new DlgPenyakit(null,false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(this.getWidth(),this.getHeight()+100);
        tariflab.setLocationRelativeTo(this);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakitActionPerformed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilprosedure();
            if(ChkSalinPsIdrg.isSelected()==true){
                TampilProsedurIDRG(Prosedur.getText());
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(akses.geticd9()==true){
                btnTambahProsedurActionPerformed(null);
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbProsedur.requestFocus();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void btnTambahProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProsedurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 tariflab=new DlgICD9(null,false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(this.getWidth(),this.getHeight()+100);
        tariflab.setLocationRelativeTo(this);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahProsedurActionPerformed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        tampilprosedure();
         if(ChkSalinPsIdrg.isSelected()==true){
                TampilProsedurIDRG(Prosedur.getText());
            }
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        pilihTab();
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnStatusBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusBaruActionPerformed
        if(norawat.equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            Sequel.queryu2("update diagnosa_pasien set status_penyakit='Baru' where no_rawat=? and kd_penyakit=?",2,new String[]{
                tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(),2).toString(),tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(),5).toString()
            });
            tampil();
        }
    }//GEN-LAST:event_MnStatusBaruActionPerformed

    private void MnStatusLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusLamaActionPerformed
        if(norawat.equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            Sequel.queryu2("update diagnosa_pasien set status_penyakit='Lama' where no_rawat=? and kd_penyakit=?",2,new String[]{
                tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(),2).toString(),tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(),5).toString()
            });
            tampil();
        }
    }//GEN-LAST:event_MnStatusLamaActionPerformed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnPrint, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1,NoRM);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        tampilSoapi();

        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed

    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(NoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            switch (TabRawat.getSelectedIndex()) {
                case 3:
                panggilLaporan(LoadHTMLSOAPI.getText());
                break;
                default:
                break;
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void NoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isPasien();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            isPasien();
            BtnCari1.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek2ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isPasien();
            BtnPrint.requestFocus();
        }
    }//GEN-LAST:event_NoRMKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        pasien.isCek();
        pasien.emptTeks();
        pasien.setSize(internalFrame4.getWidth()-20,internalFrame4.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame4);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,Tgl2,TKd);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_NoRawatKeyPressed

    private void Tgl3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl3KeyPressed
        Valid.pindah(evt, BtnPrint, Tgl2);
    }//GEN-LAST:event_Tgl3KeyPressed

    private void Tgl4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl4KeyPressed
        Valid.pindah(evt, Tgl1,NoRM);
    }//GEN-LAST:event_Tgl4KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        tampilSoapilama();

        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed

    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if(NoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            switch (TabRawat.getSelectedIndex()) {
                case 4:
                panggilLaporanlama(LoadHTMLSOAPILAMA.getText());
                break;
                default:
                break;
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void DiagnosaIDRGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaIDRGKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           TampilDiagnosaIDRG(DiagnosaIDRG.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDiagnosaIDRG.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaIDRGKeyPressed

    private void BtnCariDiagnosaIDRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariDiagnosaIDRGActionPerformed
        TampilDiagnosaIDRG(DiagnosaIDRG.getText());
    }//GEN-LAST:event_BtnCariDiagnosaIDRGActionPerformed

    private void ProsedurIDRGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurIDRGKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           TampilProsedurIDRG(ProsedurIDRG.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbProsedurIDRG.requestFocus();
        }
    }//GEN-LAST:event_ProsedurIDRGKeyPressed

    private void BtnCariProsedurIDRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurIDRGActionPerformed
        TampilProsedurIDRG(ProsedurIDRG.getText());
    }//GEN-LAST:event_BtnCariProsedurIDRGActionPerformed

    private void ChkCariDxIdrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCariDxIdrgActionPerformed

    }//GEN-LAST:event_ChkCariDxIdrgActionPerformed

    private void ChkSalinPsIdrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSalinPsIdrgActionPerformed

    }//GEN-LAST:event_ChkSalinPsIdrgActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox Agama;
    private widget.TextBox Alamat;
    private widget.TextBox Bahasa;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCariDiagnosaIDRG;
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariProsedur;
    private widget.Button BtnCariProsedurIDRG;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSeek2;
    private widget.TextBox CacatFisik;
    private widget.CekBox ChkCariDxIdrg;
    private widget.CekBox ChkSalinPsIdrg;
    public widget.TextBox Diagnosa;
    public widget.TextBox DiagnosaIDRG;
    public widget.PanelBiasa FormData;
    public widget.PanelBiasa FormData1;
    public widget.PanelBiasa FormData2;
    private widget.TextBox GD;
    private widget.TextBox IbuKandung;
    private widget.TextBox Jk;
    private widget.editorpane LoadHTMLSOAPI;
    private widget.editorpane LoadHTMLSOAPILAMA;
    private javax.swing.JMenuItem MnStatusBaru;
    private javax.swing.JMenuItem MnStatusLama;
    private widget.TextBox NmPasien;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox Pekerjaan;
    private widget.TextBox Pendidikan;
    private widget.TextBox Prosedur;
    private widget.TextBox ProsedurIDRG;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.RadioButton R4;
    private widget.RadioButton R5;
    private widget.RadioButton R6;
    private widget.RadioButton R7;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.ScrollPane ScrollInput;
    private widget.TextBox StatusNikah;
    public javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TanggalLahir;
    private widget.TextBox TempatLahir;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Tanggal Tgl3;
    private widget.Tanggal Tgl4;
    public widget.Button btnTambahPenyakit;
    public widget.Button btnTambahProsedur;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator2;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label29;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelGlass6;
    public widget.Table tbDiagnosa;
    public widget.Table tbDiagnosaIDRG;
    public widget.Table tbDiagnosaPasien;
    public widget.Table tbDiagnosaPasienIDRG;
    public widget.Table tbProsedur;
    public widget.Table tbProsedurIDRG;
    public widget.Table tbTindakanPasien;
    public widget.Table tbTindakanPasienIDRG;
    // End of variables declaration//GEN-END:variables
    public void tampil() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        Valid.tabelKosong(TabModeDiagnosaPasienIDRG);
        try{            
            psdiagnosapasien=koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status,diagnosa_pasien.status_penyakit "+
                    "from diagnosa_pasien inner join reg_periksa on diagnosa_pasien.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
                    "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? "+
                    (keyword.trim().equals("")?"":"and (diagnosa_pasien.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or diagnosa_pasien.kd_penyakit like ? or penyakit.nm_penyakit like ? or "+
                    "diagnosa_pasien.status_penyakit like ? or diagnosa_pasien.status like ?)")+
                    "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ");
            try {
                psdiagnosapasien.setString(1,tanggal1);
                psdiagnosapasien.setString(2,tanggal2);
                psdiagnosapasien.setString(3,"%"+norm+"%"); 
                if(!keyword.trim().equals("")){
                    psdiagnosapasien.setString(4,"%"+keyword+"%");         
                    psdiagnosapasien.setString(5,"%"+keyword+"%");         
                    psdiagnosapasien.setString(6,"%"+keyword+"%");         
                    psdiagnosapasien.setString(7,"%"+keyword+"%");         
                    psdiagnosapasien.setString(8,"%"+keyword+"%");         
                    psdiagnosapasien.setString(9,"%"+keyword+"%");          
                    psdiagnosapasien.setString(10,"%"+keyword+"%");   
                }
                    
                rs=psdiagnosapasien.executeQuery();
                while(rs.next()){
                    TabModeDiagnosaPasien.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                    });
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psdiagnosapasien!=null){
                    psdiagnosapasien.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        
       try{            
            psdiagnosapasienidrg=koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "diagnosa_pasien_inadrg.kd_penyakit,diagnosa_pasien_inadrg.nm_penyakit, diagnosa_pasien_inadrg.status "+
                    "from diagnosa_pasien_inadrg inner join reg_periksa on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? "+
                    (keyword.trim().equals("")?"":"and (diagnosa_pasien_inadrg.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or diagnosa_pasien_inadrg.kd_penyakit like ? or diagnosa_pasien_inadrg.nm_penyakit like ? or "+
                    "diagnosa_pasien_inadrg.status like ?)")+
                    "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas ");
            try {
                psdiagnosapasienidrg.setString(1,tanggal1);
                psdiagnosapasienidrg.setString(2,tanggal2);
                psdiagnosapasienidrg.setString(3,"%"+norm+"%"); 
                if(!keyword.trim().equals("")){
                    psdiagnosapasienidrg.setString(4,"%"+keyword+"%");         
                    psdiagnosapasienidrg.setString(5,"%"+keyword+"%");         
                    psdiagnosapasienidrg.setString(6,"%"+keyword+"%");         
                    psdiagnosapasienidrg.setString(7,"%"+keyword+"%");         
                    psdiagnosapasienidrg.setString(8,"%"+keyword+"%");         
                    psdiagnosapasienidrg.setString(9,"%"+keyword+"%");            
                }
                    
                rsdxidrg=psdiagnosapasienidrg.executeQuery();
                while(rsdxidrg.next()){
                    TabModeDiagnosaPasienIDRG.addRow(new Object[]{
                        false,rsdxidrg.getString(1),rsdxidrg.getString(2),rsdxidrg.getString(3),rsdxidrg.getString(4),rsdxidrg.getString(5),rsdxidrg.getString(6),rsdxidrg.getString(7)
                    });
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rsdxidrg!=null){
                    rsdxidrg.close();
                }
                if(psdiagnosapasienidrg!=null){
                    psdiagnosapasienidrg.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public int getRecord(){
        if(TabRawat.getSelectedIndex()==0){
            i=0;
        }else if(TabRawat.getSelectedIndex()==1){
            i=tbDiagnosaPasien.getRowCount();
        }else if(TabRawat.getSelectedIndex()==2){
            i=tbTindakanPasien.getRowCount();
        }
        return i;
    }
    
    private void tampildiagnosa() {
        try{
            jml=0;
            for(i=0;i<tbDiagnosa.getRowCount();i++){
                if(tbDiagnosa.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml];
            kode=null;
            kode=new String[jml];
            nama=null;
            nama=new String[jml];
            ciripny=null;
            ciripny=new String[jml];
            keterangan=null;
            keterangan=new String[jml];
            kategori=null;
            kategori=new String[jml];
            cirium=null;
            cirium=new String[jml];

            index=0; 
            for(i=0;i<tbDiagnosa.getRowCount();i++){
                if(tbDiagnosa.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbDiagnosa.getValueAt(i,1).toString();
                    nama[index]=tbDiagnosa.getValueAt(i,2).toString();
                    ciripny[index]=tbDiagnosa.getValueAt(i,3).toString();
                    keterangan[index]=tbDiagnosa.getValueAt(i,4).toString();
                    kategori[index]=tbDiagnosa.getValueAt(i,5).toString();
                    cirium[index]=tbDiagnosa.getValueAt(i,6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for(i=0;i<jml;i++){
                tabModeDiagnosa.addRow(new Object[] {pilih[i],kode[i],nama[i],ciripny[i],keterangan[i],kategori[i],cirium[i]});
            }       

            pspenyakit=koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "+
                    "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "+
                    "from kategori_penyakit inner join penyakit "+
                    "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "+
                    " penyakit.kd_penyakit like ? or "+
                    " penyakit.nm_penyakit like ? or "+
                    " penyakit.ciri_ciri like ? or "+
                    " penyakit.keterangan like ? or "+
                    " kategori_penyakit.nm_kategori like ? or "+
                    " kategori_penyakit.ciri_umum like ? "+
                    "order by penyakit.kd_penyakit  LIMIT 1000");
            try {
                pspenyakit.setString(1,"%"+Diagnosa.getText().trim()+"%");
                pspenyakit.setString(2,"%"+Diagnosa.getText().trim()+"%");
                pspenyakit.setString(3,"%"+Diagnosa.getText().trim()+"%");
                pspenyakit.setString(4,"%"+Diagnosa.getText().trim()+"%");
                pspenyakit.setString(5,"%"+Diagnosa.getText().trim()+"%");
                pspenyakit.setString(6,"%"+Diagnosa.getText().trim()+"%");  
                rs=pspenyakit.executeQuery();
                while(rs.next()){
                    tabModeDiagnosa.addRow(new Object[]{false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6)});
                } 
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pspenyakit!=null){
                    pspenyakit.close();
                }
            }           
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilprosedure() {
        try{
            jml=0;
            for(i=0;i<tbProsedur.getRowCount();i++){
                if(tbProsedur.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml];
            kode2=null;
            kode2=new String[jml];
            panjang=null;
            panjang=new String[jml];
            pendek=null;
            pendek=new String[jml];

            index=0; 
            for(i=0;i<tbProsedur.getRowCount();i++){
                if(tbProsedur.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode2[index]=tbProsedur.getValueAt(i,1).toString();
                    panjang[index]=tbProsedur.getValueAt(i,2).toString();
                    pendek[index]=tbProsedur.getValueAt(i,3).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeProsedur);
            for(i=0;i<jml;i++){
                tabModeProsedur.addRow(new Object[] {pilih[i],kode2[i],panjang[i],pendek[i]});
            }
            
            psprosedur=koneksi.prepareStatement("select * from icd9 where kode like ? or "+
                    " deskripsi_panjang like ? or  deskripsi_pendek like ? order by kode");
            try{
                psprosedur.setString(1,"%"+Prosedur.getText().trim()+"%");
                psprosedur.setString(2,"%"+Prosedur.getText().trim()+"%");
                psprosedur.setString(3,"%"+Prosedur.getText().trim()+"%");
                rs=psprosedur.executeQuery();
                while(rs.next()){
                    tabModeProsedur.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3)});
                }
            }catch(Exception ex){
                System.out.println(ex);
            }finally{
                if(rs != null){
                    rs.close();
                }
                if(psprosedur != null){
                    psprosedur.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void tampil2() {
        Valid.tabelKosong(TabModeTindakanPasien);
        Valid.tabelKosong(TabModeTindakanPasienIDRG);
        try{            
            pstindakanpasien=koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "+
                    "from prosedur_pasien inner join reg_periksa on prosedur_pasien.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join icd9 on prosedur_pasien.kode=icd9.kode "+
                    "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? "+
                    (keyword.trim().equals("")?"":"and (prosedur_pasien.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or prosedur_pasien.kode like ? or icd9.deskripsi_panjang like ? or "+
                    "prosedur_pasien.status like ?) ")+"order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ");
            try {
                pstindakanpasien.setString(1,tanggal1);
                pstindakanpasien.setString(2,tanggal2);
                pstindakanpasien.setString(3,"%"+norm+"%");  
                if(!keyword.trim().equals("")){
                    pstindakanpasien.setString(4,"%"+keyword+"%");       
                    pstindakanpasien.setString(5,"%"+keyword+"%");        
                    pstindakanpasien.setString(6,"%"+keyword+"%");         
                    pstindakanpasien.setString(7,"%"+keyword+"%");         
                    pstindakanpasien.setString(8,"%"+keyword+"%");          
                    pstindakanpasien.setString(9,"%"+keyword+"%");  
                }
                     
                rs=pstindakanpasien.executeQuery();
                while(rs.next()){
                    TabModeTindakanPasien.addRow(new Object[]{false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7)});
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pstindakanpasien!=null){
                    pstindakanpasien.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        
        
       try{            
            pstindakanpasienidrg=koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "prosedur_pasien_inadrg.kode,prosedur_pasien_inadrg.deskripsi_panjang, prosedur_pasien_inadrg.status, prosedur_pasien_inadrg.qty "+
                    "from prosedur_pasien_inadrg inner join reg_periksa on prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? "+
                    (keyword.trim().equals("")?"":"and (prosedur_pasien_inadrg.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or prosedur_pasien_inadrg.kode like ? or prosedur_pasien_inadrg.deskripsi_panjang like ? or "+
                    "prosedur_pasien_inadrg.status like ?) ")+"order by reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas ");
            try {
                pstindakanpasienidrg.setString(1,tanggal1);
                pstindakanpasienidrg.setString(2,tanggal2);
                pstindakanpasienidrg.setString(3,"%"+norm+"%");  
                if(!keyword.trim().equals("")){
                    pstindakanpasienidrg.setString(4,"%"+keyword+"%");       
                    pstindakanpasienidrg.setString(5,"%"+keyword+"%");        
                    pstindakanpasienidrg.setString(6,"%"+keyword+"%");         
                    pstindakanpasienidrg.setString(7,"%"+keyword+"%");         
                    pstindakanpasienidrg.setString(8,"%"+keyword+"%");          
                    pstindakanpasienidrg.setString(9,"%"+keyword+"%");  
                }
                     
                rspsidrg=pstindakanpasienidrg.executeQuery();
                while(rspsidrg.next()){
                    TabModeTindakanPasienIDRG.addRow(new Object[]{false,rspsidrg.getString(1),
                                   rspsidrg.getString(2),
                                   rspsidrg.getString(3),
                                   rspsidrg.getString(4),
                                   rspsidrg.getString(5),
                                   rspsidrg.getString(6),
                                   rspsidrg.getString(7),
                                   rspsidrg.getString(8)});
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rspsidrg!=null){
                    rspsidrg.close();
                }
                if(pstindakanpasienidrg!=null){
                    pstindakanpasienidrg.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void setRM(String norawat,String norm,String tanggal1,String tanggal2,String status,String keyword){
        this.norawat=norawat;
        isPasien();
        this.norm=norm;
        this.tanggal1=tanggal1;
        this.tanggal2=tanggal2;
        this.status=status;
        this.keyword=keyword;
        NoRM.setText(norm);
    }
    
    public void simpan(){
        nosep=Sequel.cariIsi("SELECT no_sep FROM bridging_sep WHERE no_rawat='"+norawat+"' ");
        sepvedikaralan=Sequel.cariIsi("SELECT no_sep FROM vedika_ralan WHERE no_rawat='"+norawat+"' ");
        sepvedikaranap=Sequel.cariIsi("SELECT no_sep FROM vedika_ranap WHERE no_rawat='"+norawat+"' ");
        try {
            koneksi.setAutoCommit(false);
            index=1;
            for(i=0;i<tbDiagnosa.getRowCount();i++){ 
                if(tbDiagnosa.getValueAt(i,0).toString().equals("true")){
                    if(Sequel.cariInteger(
                            "select count(diagnosa_pasien.kd_penyakit) from diagnosa_pasien "+
                            "inner join reg_periksa on diagnosa_pasien.no_rawat=reg_periksa.no_rawat "+
                            "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where "+
                            "pasien.no_rkm_medis='"+norm+"' and diagnosa_pasien.kd_penyakit='"+tbDiagnosa.getValueAt(i,1).toString()+"'")>0){
                        Sequel.menyimpan("diagnosa_pasien","?,?,?,?,?","Penyakit",5,new String[]{
                            norawat,tbDiagnosa.getValueAt(i,1).toString(),status,
                            Sequel.cariIsi("select ifnull(MAX(diagnosa_pasien.prioritas)+1,1) from diagnosa_pasien where diagnosa_pasien.no_rawat=? and diagnosa_pasien.status='"+status+"'",norawat),"Lama"
                        });
                    }else{
                        Sequel.menyimpan("diagnosa_pasien","?,?,?,?,?","Penyakit",5,new String[]{
                            norawat,tbDiagnosa.getValueAt(i,1).toString(),status,
                            Sequel.cariIsi("select ifnull(MAX(diagnosa_pasien.prioritas)+1,1) from diagnosa_pasien where diagnosa_pasien.no_rawat=? and diagnosa_pasien.status='"+status+"'",norawat),"Baru"
                        });
                    }  
                    
                    if(index==1){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_diagnosa_utama=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_diagnosa_utama=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }   
                    }else if(index==2){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_diagnosa_sekunder=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_diagnosa_sekunder=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }else if(index==3){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_diagnosa_sekunder2=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_diagnosa_sekunder2=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }else if(index==4){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_diagnosa_sekunder3=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_diagnosa_sekunder3=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }   
                    }else if(index==5){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_diagnosa_sekunder4=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_diagnosa_sekunder4=?",2,new String[]{
                                tbDiagnosa.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }
                        
                    index++;
                }                    
            }
            koneksi.setAutoCommit(true);  
            for(i=0;i<tbDiagnosa.getRowCount();i++){ 
               tbDiagnosa.setValueAt(false,i,0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }

        
        
        try {
            koneksi.setAutoCommit(false);
            index=1;
            for(i=0;i<tbProsedur.getRowCount();i++){ 
                if(tbProsedur.getValueAt(i,0).toString().equals("true")){
                    Sequel.menyimpan("prosedur_pasien","?,?,?,?","ICD 9",4,new String[]{
                        norawat,tbProsedur.getValueAt(i,1).toString(),status,Sequel.cariIsi("select ifnull(MAX(prosedur_pasien.prioritas)+1,1) from prosedur_pasien where prosedur_pasien.no_rawat=? and prosedur_pasien.status='"+status+"'",norawat)
                    });
                    
                    if(index==1){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_prosedur_utama=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_prosedur_utama=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }else if(index==2){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_prosedur_sekunder=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_prosedur_sekunder=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }else if(index==3){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_prosedur_sekunder2=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_prosedur_sekunder2=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }else if(index==4){
                        if(status.equals("Ralan")){
                            Sequel.mengedit("resume_pasien","no_rawat=?","kd_prosedur_sekunder3=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }else if(status.equals("Ranap")){
                            Sequel.mengedit("resume_pasien_ranap","no_rawat=?","kd_prosedur_sekunder3=?",2,new String[]{
                                tbProsedur.getValueAt(i,1).toString(),norawat
                            });
                        }
                    }
                        
                    index++;
                }                    
            }
            koneksi.setAutoCommit(true);  
            for(i=0;i<tbProsedur.getRowCount();i++){ 
               tbProsedur.setValueAt(false,i,0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }
        
        if(status.equals("Ralan") && !nosep.equals("") && sepvedikaralan.equals("")){
                    Sequel.menyimpan("vedika_ralan","?,?,?,now(),?,?","Vedika",5,new String[]{
                                      norawat,akses.getkode(),"Coding",nosep,""
                    });
        
        }else if (status.equals("Ranap") && !nosep.equals("") && sepvedikaranap.equals("")){
                    Sequel.menyimpan("vedika_ranap","?,?,?,now(),?,?","Vedika",5,new String[]{
                                      norawat,akses.getkode(),"Coding",nosep,""
                    });    
        }
        
         try {
            koneksi.setAutoCommit(false);
                for (i = 0; i < tbDiagnosaIDRG.getRowCount(); i++) {
                    if (tbDiagnosaIDRG.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            norawat,tbDiagnosaIDRG.getValueAt(i, 1).toString(), status, 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + status + "'", norawat),tbDiagnosaIDRG.getValueAt(i, 2).toString()
                        });
                    }
                }
            koneksi.setAutoCommit(true);
            for(i=0;i<tbDiagnosaIDRG.getRowCount();i++){ 
               tbDiagnosaIDRG.setValueAt(false,i,0);
            }       
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa INADRG yang sama dimasukkan sebelumnya...!");
        }
     
        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedurIDRG.getRowCount(); i++) {
                if (tbProsedurIDRG.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien_inadrg", "?,?,?,?,?,?", "ICD 9", 6, new String[]{
                        norawat, tbProsedurIDRG.getValueAt(i, 1).toString(),status, 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='" + status + "'", norawat), 
                        tbProsedurIDRG.getValueAt(i, 3).toString(), tbProsedurIDRG.getValueAt(i, 2).toString()
                    });
                }
            }
            koneksi.setAutoCommit(true);
             for(i=0;i<tbProsedurIDRG.getRowCount();i++){ 
               tbProsedurIDRG.setValueAt(false,i,0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 INADRG yang sama dimasukkan sebelumnya...!");
        }
        
        pilihTab();
    }

    public void pilihTab() {
        if(TabRawat.getSelectedIndex()==0){
            tampildiagnosa();
            tampilprosedure();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil();
        }else if(TabRawat.getSelectedIndex()==2){
            tampil2();
        }else if(TabRawat.getSelectedIndex()==3){
            tampilSoapi();
        }else if(TabRawat.getSelectedIndex()==4){
            tampilSoapilama();
        }
    }
    
    public void batal(){
        Diagnosa.setText("");
        DiagnosaIDRG.setText("");
        for(i=0;i<tbDiagnosa.getRowCount();i++){ 
            tbDiagnosa.setValueAt(false,i,0);
        }
        for(i=0;i<tbProsedur.getRowCount();i++){ 
            tbProsedur.setValueAt(false,i,0);
        }
        for(i=0;i<tbDiagnosaIDRG.getRowCount();i++){ 
            tbDiagnosaIDRG.setValueAt(false,i,0);
        }
        for(i=0;i<tbProsedurIDRG.getRowCount();i++){ 
            tbProsedurIDRG.setValueAt(false,i,0);
        }
        Prosedur.setText("");
        ProsedurIDRG.setText("");
    }
    
    public void hapus(){
        if(TabRawat.getSelectedIndex()==1){
            if(TabModeDiagnosaPasien.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            }else{
                for(i=0;i<tbDiagnosaPasien.getRowCount();i++){ 
                    if(tbDiagnosaPasien.getValueAt(i,0).toString().equals("true")){
                        Sequel.queryu2("delete from diagnosa_pasien where no_rawat=? and kd_penyakit=?",2,new String[]{
                            tbDiagnosaPasien.getValueAt(i,2).toString(),tbDiagnosaPasien.getValueAt(i,5).toString()
                        });
                        Sequel.queryu2("delete from eklaim_diagnosaimport_invalid where no_rawat=?",1,new String[]{
                            tbDiagnosaPasien.getValueAt(i,2).toString()
                        });
                    }
                }
            }
            if(TabModeDiagnosaPasienIDRG.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            }else{
                for(i=0;i<tbDiagnosaPasienIDRG.getRowCount();i++){ 
                    if(tbDiagnosaPasienIDRG.getValueAt(i,0).toString().equals("true")){
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?",2,new String[]{
                            tbDiagnosaPasienIDRG.getValueAt(i,2).toString(),tbDiagnosaPasienIDRG.getValueAt(i,5).toString()
                        });
                    }
                }
            } 
        }else if(TabRawat.getSelectedIndex()==2){
            if(TabModeTindakanPasien.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            }else{
                for(i=0;i<tbTindakanPasien.getRowCount();i++){ 
                    if(tbTindakanPasien.getValueAt(i,0).toString().equals("true")){
                        Sequel.queryu2("delete from prosedur_pasien where no_rawat=? and kode=?",2,new String[]{
                            tbTindakanPasien.getValueAt(i,2).toString(),tbTindakanPasien.getValueAt(i,5).toString()
                        });
                         Sequel.queryu2("delete from eklaim_prosedurimport_invalid where no_rawat=?",1,new String[]{
                            tbTindakanPasien.getValueAt(i,2).toString()
                        });
                    }
                }
            }
            
            if(TabModeTindakanPasienIDRG.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            }else{
                for(i=0;i<tbTindakanPasienIDRG.getRowCount();i++){ 
                    if(tbTindakanPasienIDRG.getValueAt(i,0).toString().equals("true")){
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?",2,new String[]{
                            tbTindakanPasienIDRG.getValueAt(i,2).toString(),tbTindakanPasienIDRG.getValueAt(i,5).toString()
                        });
                    }
                }
            }
        }  
        
        pilihTab();
    }
    
    public void cetak(){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(TabRawat.getSelectedIndex()==1){
            if(TabModeDiagnosaPasien.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(TabModeDiagnosaPasien.getRowCount()!=0){
                Map<String, Object> param = new HashMap<>();
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDiagnosa.jasper","report","::[ Data Diagnosa Pasien ]::",
                        "select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                        "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status,diagnosa_pasien.status_penyakit "+
                        "from diagnosa_pasien inner join reg_periksa inner join pasien inner join penyakit "+
                        "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
                        "where reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and reg_periksa.tgl_registrasi like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and diagnosa_pasien.no_rawat like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and reg_periksa.no_rkm_medis like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and pasien.nm_pasien like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and diagnosa_pasien.kd_penyakit like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and penyakit.nm_penyakit like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and diagnosa_pasien.status_penyakit like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and diagnosa_pasien.status like '%"+keyword+"%' "+
                        "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ",param);
            }
        }else if(TabRawat.getSelectedIndex()==2){
            if(TabModeTindakanPasien.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(TabModeTindakanPasien.getRowCount()!=0){
                Map<String, Object> param = new HashMap<>();
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptProsedur.jasper","report","::[ Data Prosedur Tindakan Pasien ]::",
                        "select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                        "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "+
                        "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "+
                        "on prosedur_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "and prosedur_pasien.kode=icd9.kode "+
                        "where reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and reg_periksa.tgl_registrasi like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and prosedur_pasien.no_rawat like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and reg_periksa.no_rkm_medis like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and pasien.nm_pasien like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and prosedur_pasien.kode like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and icd9.deskripsi_panjang like '%"+keyword+"%' or "+
                        "reg_periksa.tgl_registrasi between '"+tanggal1+"' and '"+tanggal2+"' and reg_periksa.no_rkm_medis like '%"+norm+"%' and prosedur_pasien.status like '%"+keyword+"%' "+
                        "order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ",param);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
     private void tampilSoapi() {
        try {
            NoRM.setText(norm);
            NoRawat.setText(norawat);
            htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'>Tgl.Reg</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='8%'>No.Rawat</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='3%'>Status</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84%'>S.O.A.P.I.E</td>"+
                "</tr>"
            );     
            if(R1.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.status_lanjut "+
                    "from reg_periksa where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc limit 5");
            }else if(R2.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.status_lanjut "+
                    "from reg_periksa where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi");
            }else if(R3.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.status_lanjut "+
                    "from reg_periksa where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? and "+
                    "reg_periksa.tgl_registrasi between ? and ? order by reg_periksa.tgl_registrasi");
            }else if(R4.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.status_lanjut "+
                    "from reg_periksa where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? and reg_periksa.no_rawat=?");
            }
            try {
                if(R1.isSelected()==true){
                    ps.setString(1,NoRM.getText().trim());
                }else if(R2.isSelected()==true){
                    ps.setString(1,NoRM.getText().trim());
                }else if(R3.isSelected()==true){
                    ps.setString(1,NoRM.getText().trim());
                    ps.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                    ps.setString(3,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                }else if(R4.isSelected()==true){
                    ps.setString(1,NoRM.getText().trim());
                    ps.setString(2,NoRawat.getText().trim());
                }  
                rs=ps.executeQuery();
                while(rs.next()){
                    htmlContent.append(
                        "<tr class='isi'>"+
                            "<td valign='top' align='center'>"+rs.getString("tgl_registrasi")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("no_rawat")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("status_lanjut")+"</td>"+
                            "<td valign='top' align='center'>"+
                                "<table width='100%' border='0' align='center' cellpadding='2px' cellspacing='0'>");
                    try {
                        rs2=koneksi.prepareStatement(
                                "select pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"+
                                "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.spo2,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, "+
                                "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.lingkar_perut,pemeriksaan_ralan.rtl,pemeriksaan_ralan.penilaian,"+
                                "pemeriksaan_ralan.instruksi,pemeriksaan_ralan.evaluasi,pemeriksaan_ralan.nip,pegawai.nama,pegawai.jbtn from pemeriksaan_ralan inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where "+
                                "pemeriksaan_ralan.no_rawat='"+rs.getString("no_rawat")+"' "+
                                "order by pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat").executeQuery();
                        if(rs2.next()){
                            htmlContent.append(
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='7%'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Dokter/Paramedis</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Subjek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Objek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Asesmen</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Plan</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Instruksi</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Evaluasi</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>ICD 10</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>ICD 9</td>"+    
                                    "</tr>");
                            rs2.beforeFirst();
                            while(rs2.next()){
                                 psdx=koneksi.prepareStatement("SELECT dp.kd_penyakit  FROM  diagnosa_pasien dp WHERE  dp.no_rawat=? AND  dp.status='Ralan' ORDER BY  dp.prioritas desc ");
                                        dxralan="";
                                        try {
                                            psdx.setString(1,rs.getString("no_rawat"));
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
                                        pspd.setString(1,rs.getString("no_rawat"));
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
                                 htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td align='center'>"+rs2.getString("tgl_perawatan")+"<br>"+rs2.getString("jam_rawat")+"</td>"+
                                        "<td align='center'>"+rs2.getString("nip")+"<br>"+rs2.getString("nama")+"</td>"+
                                        "<td align='left'>"+rs2.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+
                                        (rs2.getString("alergi").equals("")?"":"<br>Alergi : "+rs2.getString("alergi"))+
                                        (rs2.getString("suhu_tubuh").equals("")?"":"<br>Suhu(C) : "+rs2.getString("suhu_tubuh"))+
                                        (rs2.getString("tensi").equals("")?"":"<br>Tensi : "+rs2.getString("tensi"))+
                                        (rs2.getString("nadi").equals("")?"":"<br>Nadi(/menit) : "+rs2.getString("nadi"))+
                                        (rs2.getString("respirasi").equals("")?"":"<br>Respirasi(/menit) : "+rs2.getString("respirasi"))+
                                        (rs2.getString("tinggi").equals("")?"":"<br>Tinggi(Cm) : "+rs2.getString("tinggi"))+
                                        (rs2.getString("berat").equals("")?"":"<br>Berat(Kg) : "+rs2.getString("berat"))+
                                        (rs2.getString("lingkar_perut").equals("")?"":"<br>Lingkar Perut(Cm) : "+rs2.getString("lingkar_perut"))+
                                        (rs2.getString("spo2").equals("")?"":"<br>SpO2(%) : "+rs2.getString("spo2"))+
                                        (rs2.getString("gcs").equals("")?"":"<br>GCS(E,V,M) : "+rs2.getString("gcs"))+
                                        (rs2.getString("kesadaran").equals("")?"":"<br>Kesadaran : "+rs2.getString("kesadaran"))+
                                        "</td>"+
                                        "<td align='left'>"+rs2.getString("penilaian").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("rtl").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("instruksi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("evaluasi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+dxralan.replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+pdralan.replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                    "</tr>"
                                 );
                            } 
                        }       
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                    }
                    
                    try {
                        rs2=koneksi.prepareStatement(
                                "select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, " +
                                "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, " +
                                "pemeriksaan_ranap.berat,pemeriksaan_ranap.spo2,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, " +
                                "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl,"+
                                "pemeriksaan_ranap.instruksi,pemeriksaan_ranap.evaluasi,pemeriksaan_ranap.nip,pegawai.nama,pegawai.jbtn "+
                                "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                "inner join pemeriksaan_ranap on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat "+
                                "inner join pegawai on pemeriksaan_ranap.nip=pegawai.nik where pemeriksaan_ranap.no_rawat='"+rs.getString("no_rawat")+"' "+
                                "order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                        if(rs2.next()){
                            htmlContent.append(
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='7%'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Dokter/Paramedis</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Subjek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Objek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Asesmen</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Plan</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Instruksi</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='14%'>Evaluasi</td>"+
                                    "</tr>");
                            rs2.beforeFirst();
                            while(rs2.next()){
                                 htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td align='center'>"+rs2.getString("tgl_perawatan")+"<br>"+rs2.getString("jam_rawat")+"</td>"+
                                        "<td align='center'>"+rs2.getString("nip")+"<br>"+rs2.getString("nama")+"</td>"+
                                        "<td align='left'>"+rs2.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+
                                        (rs2.getString("alergi").equals("")?"":"<br>Alergi : "+rs2.getString("alergi"))+
                                        (rs2.getString("suhu_tubuh").equals("")?"":"<br>Suhu(C) : "+rs2.getString("suhu_tubuh"))+
                                        (rs2.getString("tensi").equals("")?"":"<br>Tensi : "+rs2.getString("tensi"))+
                                        (rs2.getString("nadi").equals("")?"":"<br>Nadi(/menit) : "+rs2.getString("nadi"))+
                                        (rs2.getString("respirasi").equals("")?"":"<br>Respirasi(/menit) : "+rs2.getString("respirasi"))+
                                        (rs2.getString("tinggi").equals("")?"":"<br>Tinggi(Cm) : "+rs2.getString("tinggi"))+
                                        (rs2.getString("berat").equals("")?"":"<br>Berat(Kg) : "+rs2.getString("berat"))+
                                        (rs2.getString("spo2").equals("")?"":"<br>SpO2(%) : "+rs2.getString("spo2"))+
                                        (rs2.getString("gcs").equals("")?"":"<br>GCS(E,V,M) : "+rs2.getString("gcs"))+
                                        (rs2.getString("kesadaran").equals("")?"":"<br>Kesadaran : "+rs2.getString("kesadaran"))+
                                        "</td>"+
                                        "<td align='left'>"+rs2.getString("penilaian").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("rtl").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("instruksi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("evaluasi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                    "</tr>"
                                 ); 
                            } 
                        }       
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                    }
                    htmlContent.append(
                                "</table>"+
                            "</td>"+
                        "</tr>"
                    );
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
            
            LoadHTMLSOAPI.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("laporan.DlgRL4A.prosesCari() 5 : "+e);
        } 
    }
    
     private void panggilLaporan(String teks) {
        try{
            File g = new File("file.css");            
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}.isi a{text-decoration:none;color:#8b9b95;padding:0 0 0 0px;font-family: Tahoma;font-size: 8.5px;border: white;}");
            bg.close();

            File f = new File("riwayat.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(
                 teks.replaceAll("<head>","<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />").
                      replaceAll("<body>",
                                 "<body>"+
                                    "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>No.RM</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+NoRM.getText().trim()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Nama Pasien</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+NmPasien.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Alamat</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Alamat.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Jenis Kelamin</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Jk.getText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Tempat & Tanggal Lahir</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+TempatLahir.getText()+" "+TanggalLahir.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Ibu Kandung</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+IbuKandung.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Golongan Darah</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+GD.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Status Nikah</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+StatusNikah.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Agama</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Agama.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Pendidikan Terakhir</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Pendidikan.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Bahasa Dipakai</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Bahasa.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Cacat Fisik</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+CacatFisik.getText()+"</td>"+
                                       "</tr>"+
                                    "</table>"            
                      ).
                      replaceAll((getClass().getResource("/picture/"))+"","./gambar/")
            );  
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }   
    }
     
    //tampilsoapie lama
     
     private void tampilSoapilama() {
        try {
            keyword=norm;
            htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'>Tgl.Reg</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='8%'>No.Rawat</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='3%'>Status</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84%'>S.O.A.P.I</td>"+
                "</tr>"
            );     
            if(R5.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,IF(reg_periksa.status_lanjut='Ralan',CONCAT('Ralan',' ',poliklinik.nm_poli),'Ranap') as status_lanjut "+
                    "from reg_periksa INNER JOIN poliklinik on poliklinik.kd_poli=reg_periksa.kd_poli where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc limit 5");
            }else if(R6.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,IF(reg_periksa.status_lanjut='Ralan',CONCAT('Ralan',' ',poliklinik.nm_poli),'Ranap') as status_lanjut "+
                    "from reg_periksa INNER JOIN poliklinik on poliklinik.kd_poli=reg_periksa.kd_poli where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi");
            }else if(R7.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,IF(reg_periksa.status_lanjut='Ralan',CONCAT('Ralan',' ',poliklinik.nm_poli),'Ranap') as status_lanjut "+
                    "from reg_periksa INNER JOIN poliklinik on poliklinik.kd_poli=reg_periksa.kd_poli where reg_periksa.stts<>'Batal' and reg_periksa.no_rkm_medis=? and "+
                    "reg_periksa.tgl_registrasi between ? and ? order by reg_periksa.tgl_registrasi");
            }
            try {
                if(R5.isSelected()==true){
                    ps.setString(1,NoRM.getText());
                }else if(R6.isSelected()==true){
                    ps.setString(1,NoRM.getText());
                }else if(R7.isSelected()==true){
                    ps.setString(1,NoRM.getText());
                    ps.setString(2,Valid.SetTgl(Tgl3.getSelectedItem()+""));
                    ps.setString(3,Valid.SetTgl(Tgl4.getSelectedItem()+""));
                }  
                rs=ps.executeQuery();
                while(rs.next()){
                    htmlContent.append(
                        "<tr class='isi'>"+
                            "<td valign='top' align='center'>"+rs.getString("tgl_registrasi")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("no_rawat")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("status_lanjut")+"</td>"+
                            "<td valign='top' align='center'>"+
                                "<table width='100%' border='0' align='center' cellpadding='2px' cellspacing='0'>");
                    try {
                        rs2=koneksi.prepareStatement(
                                "select pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"+
                                "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.kesadaran,pemeriksaan_ralan.keluhan, "+
                                "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.lingkar_perut,pemeriksaan_ralan.rtl,pemeriksaan_ralan.penilaian,"+
                                "pemeriksaan_ralan.instruksi,pemeriksaan_ralan.nip,pegawai.nama,pegawai.jbtn from pemeriksaan_ralan inner join pegawai on pemeriksaan_ralan.nip=pegawai.nik where "+
                                "pemeriksaan_ralan.no_rawat='"+rs.getString("no_rawat")+"' "+
                                "order by pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat").executeQuery();
                        if(rs2.next()){
                            htmlContent.append(
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='7%'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Dokter/Perawat/Bidan/Tenaga Medis</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Subjek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Objek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Asesmen</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Plan</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Instruksi</td>"+
                                    "</tr>");
                            rs2.beforeFirst();
                            while(rs2.next()){
                                 htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td align='center'>"+rs2.getString("tgl_perawatan")+"<br>"+rs2.getString("jam_rawat")+"</td>"+
                                        "<td align='center'>"+rs2.getString("nip")+"<br>"+rs2.getString("nama")+"</td>"+
                                        "<td align='left'>"+rs2.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+
                                        (rs2.getString("alergi").equals("")?"":"<br>Alergi : "+rs2.getString("alergi"))+
                                        (rs2.getString("suhu_tubuh").equals("")?"":"<br>Suhu(C) : "+rs2.getString("suhu_tubuh"))+
                                        (rs2.getString("tensi").equals("")?"":"<br>Tensi : "+rs2.getString("tensi"))+
                                        (rs2.getString("nadi").equals("")?"":"<br>Nadi(/menit) : "+rs2.getString("nadi"))+
                                        (rs2.getString("respirasi").equals("")?"":"<br>Respirasi(/menit) : "+rs2.getString("respirasi"))+
                                        (rs2.getString("tinggi").equals("")?"":"<br>Tinggi(Cm) : "+rs2.getString("tinggi"))+
                                        (rs2.getString("berat").equals("")?"":"<br>Berat(Kg) : "+rs2.getString("berat"))+
                                        (rs2.getString("gcs").equals("")?"":"<br>GCS(E,V,M) : "+rs2.getString("gcs"))+
                                        (rs2.getString("kesadaran").equals("")?"":"<br>Kesadaran : "+rs2.getString("kesadaran"))+
                                        "</td>"+
                                        "<td align='left'>"+rs2.getString("penilaian").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("rtl").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("instruksi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                    "</tr>"
                                 );
                            } 
                        }       
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                    }
                    
                    try {
                        rs2=koneksi.prepareStatement(
                                "select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, " +
                                "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, " +
                                "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.kesadaran,pemeriksaan_ranap.keluhan, " +
                                "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.rtl,"+
                                "pemeriksaan_ranap.instruksi,pemeriksaan_ranap.nip,pegawai.nama,pegawai.jbtn "+
                                "from pasien inner join reg_periksa on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                "inner join pemeriksaan_ranap on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat "+
                                "inner join pegawai on pemeriksaan_ranap.nip=pegawai.nik where pemeriksaan_ranap.no_rawat='"+rs.getString("no_rawat")+"' "+
                                "order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                        if(rs2.next()){
                            htmlContent.append(
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='7%'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='13%'>Dokter/Perawat/Bidan/Tenaga Medis</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Subjek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Objek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Asesmen</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Plan</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='16%'>Instruksi</td>"+
                                    "</tr>");
                            rs2.beforeFirst();
                            while(rs2.next()){
                                 htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td align='center'>"+rs2.getString("tgl_perawatan")+"<br>"+rs2.getString("jam_rawat")+"</td>"+
                                        "<td align='center'>"+rs2.getString("nip")+"<br>"+rs2.getString("nama")+"</td>"+
                                        "<td align='left'>"+rs2.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+
                                        (rs2.getString("alergi").equals("")?"":"<br>Alergi : "+rs2.getString("alergi"))+
                                        (rs2.getString("suhu_tubuh").equals("")?"":"<br>Suhu(C) : "+rs2.getString("suhu_tubuh"))+
                                        (rs2.getString("tensi").equals("")?"":"<br>Tensi : "+rs2.getString("tensi"))+
                                        (rs2.getString("nadi").equals("")?"":"<br>Nadi(/menit) : "+rs2.getString("nadi"))+
                                        (rs2.getString("respirasi").equals("")?"":"<br>Respirasi(/menit) : "+rs2.getString("respirasi"))+
                                        (rs2.getString("tinggi").equals("")?"":"<br>Tinggi(Cm) : "+rs2.getString("tinggi"))+
                                        (rs2.getString("berat").equals("")?"":"<br>Berat(Kg) : "+rs2.getString("berat"))+
                                        (rs2.getString("gcs").equals("")?"":"<br>GCS(E,V,M) : "+rs2.getString("gcs"))+
                                        (rs2.getString("kesadaran").equals("")?"":"<br>Kesadaran : "+rs2.getString("kesadaran"))+
                                        "</td>"+
                                        "<td align='left'>"+rs2.getString("penilaian").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("rtl").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                        "<td align='left'>"+rs2.getString("instruksi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                    "</tr>"
                                 ); 
                            } 
                        }       
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                    }
                    htmlContent.append(
                                "</table>"+
                            "</td>"+
                        "</tr>"
                    );
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
            
            LoadHTMLSOAPI.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("laporan.DlgRL4A.prosesCari() 5 : "+e);
        } 
    }
    
     private void panggilLaporanlama(String teks) {
        try{
            File g = new File("file.css");            
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}.isi a{text-decoration:none;color:#8b9b95;padding:0 0 0 0px;font-family: Tahoma;font-size: 8.5px;border: white;}");
            bg.close();

            File f = new File("riwayat.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(
                 teks.replaceAll("<head>","<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />").
                      replaceAll("<body>",
                                 "<body>"+
                                    "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>No.RM</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+NoRM.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Nama Pasien</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+NmPasien.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Alamat</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Alamat.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Jenis Kelamin</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Jk.getText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Tempat & Tanggal Lahir</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+TempatLahir.getText()+" "+TanggalLahir.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Ibu Kandung</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+IbuKandung.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Golongan Darah</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+GD.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Status Nikah</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+StatusNikah.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Agama</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Agama.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Pendidikan Terakhir</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Pendidikan.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Bahasa Dipakai</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+Bahasa.getText()+"</td>"+
                                       "</tr>"+
                                       "<tr class='isi'>"+ 
                                         "<td valign='top' width='20%'>Cacat Fisik</td>"+
                                         "<td valign='top' width='1%' align='center'>:</td>"+
                                         "<td valign='top' width='79%'>"+CacatFisik.getText()+"</td>"+
                                       "</tr>"+
                                    "</table>"            
                      )
            );  
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }   
    }
     
     
      private void isPasien() {
        try{
            ps=koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tmp_lahir,pasien.tgl_lahir,pasien.agama,"+
                    "bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,pasien.gol_darah,pasien.nm_ibu,pasien.stts_nikah,pasien.pnd, "+
                    "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat,pasien.pekerjaan "+
                    "from pasien inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                    "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik "+
                    "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                    "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                    "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "+
                    "where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,NoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    NoRM.setText(rs.getString("no_rkm_medis"));
                    NmPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TempatLahir.setText(rs.getString("tmp_lahir"));
                    TanggalLahir.setText(rs.getString("tgl_lahir"));
                    Alamat.setText(rs.getString("alamat"));
                    GD.setText(rs.getString("gol_darah"));
                    IbuKandung.setText(rs.getString("nm_ibu"));
                    Agama.setText(rs.getString("agama"));
                    StatusNikah.setText(rs.getString("stts_nikah"));
                    Pendidikan.setText(rs.getString("pnd"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    CacatFisik.setText(rs.getString("nama_cacat"));
                    Pekerjaan.setText(rs.getString("pekerjaan"));
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
      
     private void TampilDiagnosaIDRG(String DiagnosaIDRG) {
            try {  
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");
                requestJsonDx
                        = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"search_diagnosis_inagrouper\""
                        + "},"
                        + "\"data\": {"
                        + "\"keyword\": \"" + DiagnosaIDRG + "\""
                        + "}"
                        + "}";

                System.out.println("JSON : " + requestJsonDx);
                requestEntity = new HttpEntity(requestJsonDx, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);
                root = mapper.readTree(stringbalik);
                jml=0;
                   for(i=0;i<tbDiagnosaIDRG.getRowCount();i++){
                        if(tbDiagnosaIDRG.getValueAt(i,0).toString().equals("true")){
                            jml++;
                        }
                    }

                    pilih=null;
                    pilih=new boolean[jml];
                    kodedxidrg=null;
                    kodedxidrg=new String[jml];
                    namadxidrg=null;
                    namadxidrg=new String[jml];

                    index=0; 
                    for(i=0;i<tbDiagnosaIDRG.getRowCount();i++){
                        if(tbDiagnosaIDRG.getValueAt(i,0).toString().equals("true")){
                            pilih[index]=true;
                            kodedxidrg[index]=tbDiagnosaIDRG.getValueAt(i,1).toString();
                            namadxidrg[index]=tbDiagnosaIDRG.getValueAt(i,2).toString();
                            index++;
                        }
                    }
                    Valid.tabelKosong(TabModeDiagnosaIDRG);
                    for(i=0;i<jml;i++){
                        TabModeDiagnosaIDRG.addRow(new Object[] {pilih[i],kodedxidrg[i],namadxidrg[i]});
                    }
                if (root.path("response").path("data").isArray()) {
                    for (JsonNode list : root.path("response").path("data")) {
                        if (list.path("validcode").asText().equals("1")) {
                                TabModeDiagnosaIDRG.addRow(new Object[]{
                                    false,
                                    list.path("code").asText(),
                                    list.path("description").asText()+" ("+list.path("code_asterisk").asText()+" )"
                                });
                            }
                        }
                    }
                
            } catch (Exception erornya) {
                System.out.println("Notifikasi : " + erornya);
                if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                    JOptionPane.showMessageDialog(null, erornya);
                }
            }
        }
     
      private void TampilProsedurIDRG(String ProsedurIDRG) {
            try {  
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");
                requestJsonPS
                        = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"search_procedures_inagrouper\""
                        + "},"
                        + "\"data\": {"
                        + "\"keyword\": \"" + ProsedurIDRG + "\""
                        + "}"
                        + "}";

                System.out.println("JSON : " + requestJsonPS);
                requestEntity = new HttpEntity(requestJsonPS, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);
                root = mapper.readTree(stringbalik);
                jml=0;
                   for(i=0;i<tbProsedurIDRG.getRowCount();i++){
                        if(tbProsedurIDRG.getValueAt(i,0).toString().equals("true")){
                            jml++;
                        }
                    }

                    pilih=null;
                    pilih=new boolean[jml];
                    kodepsidrg=null;
                    kodepsidrg=new String[jml];
                    namapsidrg=null;
                    namapsidrg=new String[jml];

                    index=0; 
                    for(i=0;i<tbProsedurIDRG.getRowCount();i++){
                        if(tbProsedurIDRG.getValueAt(i,0).toString().equals("true")){
                            pilih[index]=true;
                            kodepsidrg[index]=tbProsedurIDRG.getValueAt(i,1).toString();
                            namapsidrg[index]=tbProsedurIDRG.getValueAt(i,2).toString();
                            index++;
                        }
                    }
                    Valid.tabelKosong(TabModeProsedurIDRG);
                    for(i=0;i<jml;i++){
                        TabModeProsedurIDRG.addRow(new Object[] {pilih[i],kodepsidrg[i],namapsidrg[i],"1"});
                    }
                if (root.path("response").path("data").isArray()) {
                    for (JsonNode list : root.path("response").path("data")) {
                        if (list.path("validcode").asText().equals("1")) {
                                TabModeProsedurIDRG.addRow(new Object[]{
                                    false,
                                    list.path("code").asText(),
                                    list.path("description").asText(),"1"
                                });
                            }
                        }
                    }
                
            } catch (Exception erornya) {
                System.out.println("Notifikasi : " + erornya);
                if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                    JOptionPane.showMessageDialog(null, erornya);
                }
            }
        }
        
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
}

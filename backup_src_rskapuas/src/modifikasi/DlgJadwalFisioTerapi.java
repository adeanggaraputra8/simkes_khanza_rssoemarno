package modifikasi;

import bridging.ApiWA;
import com.fasterxml.jackson.databind.JsonNode;
import permintaan.*;
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
import kepegawaian.DlgCariDokter2;
import modifikasi.Dlgwhatsapp;
import org.mozilla.javascript.regexp.SubString;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgCariPoli2;
import simrskhanza.DlgReg;

/**
 *
 * @author dosen
 */
public class DlgJadwalFisioTerapi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0,kuota=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariDokter2 dokter2=new DlgCariDokter2(null,false);
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariPoli2 poli2=new DlgCariPoli2(null,false);
    private ApiWA apiwa=new ApiWA();
    private DlgReg pasien=new DlgReg(null,false);
    private String aktifjadwal="",URUTNOREG="",status="",no_rawat="",umur="",sttsumur="",Nohp="",token="",nomorpasien="",kodetelpon="62",URL="",requestJson;
    private JsonNode response;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    
    

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgJadwalFisioTerapi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
                "P","No.Antrian","Tgl.Penjadwalan","No.RM","Nama Pasien","Tgl.Periksa","Jam Di Layani","Kode Dokter",
                "Nama Dokter","Kode Poli","Nama Poli","No.Telp/HP"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(170);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==8){
                column.setPreferredWidth(170);
            }else if(i==9){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==10){
                column.setPreferredWidth(170);
            }else if(i==11){
                column.setPreferredWidth(170);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        TNoRM.setDocument(new batasInput((byte)17).getKata(TNoRM));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        KdDokter.setDocument(new batasInput((byte)3).getKata(KdDokter));
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
                    isNomer();
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
        
        dokter2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter2.getTable().getSelectedRow()!= -1){                    
                    KdDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),1).toString());
                    if(aktifjadwal.equals("aktif")){
                        kuota=Integer.parseInt(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),13).toString());
                        MaxKuota.setText(""+kuota);
                    }
                    isNomer();                        
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
                    isNomer();
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
        
        poli2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli2.getTable().getSelectedRow()!= -1){                    
                    KdPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),0).toString());
                    NmPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),1).toString());
                    isNomer();
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
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pasien.getTable().getSelectedRow()!= -1){  
                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),7).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),8).toString());
                    NoTelp.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),18).toString());
                }  
                if(pasien.getTable2().getSelectedRow()!= -1){  
                    TNoRM.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),7).toString());
                    TPasien.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),8).toString());
                    NoTelp.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),18).toString());
                }   
                TNoRM.requestFocus();
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });    
        
        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
               
        token = Sequel.cariIsi("SELECT api_key FROM settingwa");
        URL   = Sequel.cariIsi("SELECT urlwa FROM settingwa");
        Nohp  = Sequel.cariIsi("SELECT nohprs FROM settingwa");
        try {
            URUTNOREG=koneksiDB.URUTNOREG();
        } catch (Exception ex) {
            URUTNOREG="";
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
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        ppKirimWA = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnEdit = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel26 = new widget.Label();
        cmbcarijam = new widget.ComboBox();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        TPasien = new widget.TextBox();
        TanggalBooking = new widget.Tanggal();
        jLabel10 = new widget.Label();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalPeriksa = new widget.Tanggal();
        NoReg = new widget.TextBox();
        jLabel18 = new widget.Label();
        BtnPasien = new widget.Button();
        jLabel20 = new widget.Label();
        MaxKuota = new widget.TextBox();
        cmbJam = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jLabel21 = new widget.Label();
        Kuota = new widget.TextBox();
        jLabel19 = new widget.Label();
        NoTelp = new widget.TextBox();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(170, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSemua.setText("Pilih Semua");
        ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSemua.setName("ppSemua"); // NOI18N
        ppSemua.setPreferredSize(new java.awt.Dimension(170, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        ppKirimWA.setBackground(new java.awt.Color(255, 255, 254));
        ppKirimWA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppKirimWA.setForeground(new java.awt.Color(50, 50, 50));
        ppKirimWA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppKirimWA.setText("Kirim WA");
        ppKirimWA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppKirimWA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppKirimWA.setName("ppKirimWA"); // NOI18N
        ppKirimWA.setPreferredSize(new java.awt.Dimension(170, 25));
        ppKirimWA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppKirimWAActionPerformed(evt);
            }
        });
        Popup.add(ppKirimWA);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[Jadwal Kunjungan Pasien Fisioterapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setComponentPopupMenu(Popup);
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
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

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
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

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setSelected(true);
        R2.setText("Tanggal Booking :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(125, 23));
        panelCari.add(R2);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBackground(new java.awt.Color(240, 250, 230));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Tanggal Periksa :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(135, 23));
        panelCari.add(R3);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari3ItemStateChanged(evt);
            }
        });
        DTPCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari3KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari3);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari4ItemStateChanged(evt);
            }
        });
        DTPCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari4KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari4);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Jam :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(30, 23));
        panelCari.add(jLabel26);

        cmbcarijam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00" }));
        cmbcarijam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbcarijam.setName("cmbcarijam"); // NOI18N
        cmbcarijam.setPreferredSize(new java.awt.Dimension(90, 23));
        cmbcarijam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbcarijamItemStateChanged(evt);
            }
        });
        panelCari.add(cmbcarijam);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setSelected(true);
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

        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 55, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(59, 10, 90, 23);

        jLabel9.setText("Dokter :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 40, 55, 23);

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(151, 40, 311, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(151, 10, 311, 23);

        TanggalBooking.setForeground(new java.awt.Color(50, 70, 50));
        TanggalBooking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        TanggalBooking.setDisplayFormat("dd-MM-yyyy");
        TanggalBooking.setName("TanggalBooking"); // NOI18N
        TanggalBooking.setOpaque(false);
        TanggalBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalBookingKeyPressed(evt);
            }
        });
        FormInput.add(TanggalBooking);
        TanggalBooking.setBounds(600, 10, 100, 23);

        jLabel10.setText("Tgl.Booking :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(520, 10, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(59, 40, 90, 23);

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
        BtnDokter.setBounds(464, 40, 28, 23);

        jLabel11.setText("Unit/Poli :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 70, 121, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(125, 70, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(198, 70, 263, 23);

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
        BtnPoli.setBounds(464, 70, 28, 23);

        jLabel14.setText("Jam :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(230, 100, 30, 23);

        TanggalPeriksa.setForeground(new java.awt.Color(50, 70, 50));
        TanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-03-2022" }));
        TanggalPeriksa.setDisplayFormat("dd-MM-yyyy");
        TanggalPeriksa.setName("TanggalPeriksa"); // NOI18N
        TanggalPeriksa.setOpaque(false);
        TanggalPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalPeriksaItemStateChanged(evt);
            }
        });
        TanggalPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalPeriksaKeyPressed(evt);
            }
        });
        FormInput.add(TanggalPeriksa);
        TanggalPeriksa.setBounds(120, 100, 80, 23);

        NoReg.setEditable(false);
        NoReg.setHighlighter(null);
        NoReg.setName("NoReg"); // NOI18N
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });
        FormInput.add(NoReg);
        NoReg.setBounds(610, 40, 90, 23);

        jLabel18.setText("No. Antrian :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(510, 40, 100, 23);

        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('X');
        BtnPasien.setToolTipText("Alt+X");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        BtnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPasienKeyPressed(evt);
            }
        });
        FormInput.add(BtnPasien);
        BtnPasien.setBounds(464, 10, 28, 23);

        jLabel20.setText("Max Kuota :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(510, 70, 100, 23);

        MaxKuota.setEditable(false);
        MaxKuota.setText("10");
        MaxKuota.setHighlighter(null);
        MaxKuota.setName("MaxKuota"); // NOI18N
        MaxKuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MaxKuotaKeyPressed(evt);
            }
        });
        FormInput.add(MaxKuota);
        MaxKuota.setBounds(610, 70, 50, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00" }));
        cmbJam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbJam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJamItemStateChanged(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(270, 100, 90, 23);

        jLabel15.setText("Tgl.Periksa :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(50, 100, 70, 23);

        jLabel21.setText("Sia Kuota :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(360, 100, 60, 23);

        Kuota.setEditable(false);
        Kuota.setHighlighter(null);
        Kuota.setName("Kuota"); // NOI18N
        Kuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KuotaKeyPressed(evt);
            }
        });
        FormInput.add(Kuota);
        Kuota.setBounds(430, 100, 70, 23);

        jLabel19.setText("No. WA Pasien :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(510, 100, 100, 23);

        NoTelp.setHighlighter(null);
        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        FormInput.add(NoTelp);
        NoTelp.setBounds(610, 100, 150, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
       // Valid.pindah(evt,Status,KdDokter);
        
}//GEN-LAST:event_TNoRMKeyPressed

    private void TanggalBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalBookingKeyPressed
        Valid.pindah(evt,BtnPoli,TanggalPeriksa);
}//GEN-LAST:event_TanggalBookingKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();  
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting"));
        if(R2.isSelected()==true){
            status=" jadwalfisioterapi.tanggal_booking between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
        }else if(R3.isSelected()==true){
            status=" jadwalfisioterapi.jam_periksa like '%"+cmbcarijam.getSelectedItem().toString().replaceAll("Semua","")+"%' and jadwalfisioterapi.tanggal_periksa between '"+Valid.SetTgl(DTPCari3.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari4.getSelectedItem()+"")+"' ";           
        }
            Valid.MyReportqry("rptRekapJadwalFisio.jasper","report","::[ Rekap Daftar Booking Registrasi ]::",
                    "SELECT jadwalfisioterapi.no_reg,jadwalfisioterapi.tanggal_booking,jadwalfisioterapi.no_rkm_medis,pasien.nm_pasien,jadwalfisioterapi.tanggal_periksa,jadwalfisioterapi.jam_periksa,jadwalfisioterapi.kd_dokter,dokter.nm_dokter,jadwalfisioterapi.kd_poli,poliklinik.nm_poli,pasien.no_tlp " +
                    "FROM jadwalfisioterapi INNER JOIN pasien ON pasien.no_rkm_medis=jadwalfisioterapi.no_rkm_medis " +
                    "INNER JOIN dokter ON dokter.kd_dokter=jadwalfisioterapi.kd_dokter " +
                    "INNER JOIN poliklinik ON poliklinik.kd_poli=jadwalfisioterapi.kd_poli " + 
                    "where "+status+" order by jadwalfisioterapi.no_reg,jadwalfisioterapi.tanggal_booking",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
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
        kuota();
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
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if(aktifjadwal.equals("aktif")){
        if(akses.getkode().equals("Admin Utama")){
            dokter.isCek();        
            dokter.TCari.requestFocus();
            dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dokter.setLocationRelativeTo(internalFrame1);
            dokter.setVisible(true);
        }else{
            dokter2.setPoli(NmPoli.getText());
            dokter2.isCek();  
            dokter2.SetHari(TanggalPeriksa.getDate());   
            dokter2.tampil();
            dokter2.TCari.requestFocus();
            dokter2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dokter2.setLocationRelativeTo(internalFrame1);
            dokter2.setVisible(true);
        }                
    }else{
        dokter.isCek();        
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
        Valid.pindah(evt,BtnPasien,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed

    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari3KeyPressed

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        KdPoli.setText("IRM");
        NmPoli.setText("Instalasi Rehab Medik");
    }//GEN-LAST:event_formWindowOpened

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari3ItemStateChanged

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void TanggalPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalPeriksaKeyPressed
        Valid.pindah(evt,TanggalBooking,NoReg);
    }//GEN-LAST:event_TanggalPeriksaKeyPressed

    private void TanggalPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalPeriksaItemStateChanged
        try {
            isNomer();
            kuota();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TanggalPeriksaItemStateChanged

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnDokter,BtnPoli);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        if(aktifjadwal.equals("aktif")){
            if(akses.getkode().equals("Admin Utama")){
                poli.isCek();        
                poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                poli.setLocationRelativeTo(internalFrame1);
                poli.setVisible(true);
            }else{
                poli2.isCek();                     
                poli2.SetHari(TanggalPeriksa.getDate());
                poli2.tampil(); 
                poli2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                poli2.setLocationRelativeTo(internalFrame1);
                poli2.setVisible(true);
            }                
        }else{
            poli.isCek();        
            poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            poli.setLocationRelativeTo(internalFrame1);
            poli.setVisible(true);
        }
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        Valid.pindah(evt,TanggalPeriksa,BtnSimpan);
    }//GEN-LAST:event_NoRegKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPasienKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPasienActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }   
    }//GEN-LAST:event_BtnPasienKeyPressed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for(i=0;i<tbObat.getRowCount();i++){
            tbObat.setValueAt(false,i,0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for(i=0;i<tbObat.getRowCount();i++){
            tbObat.setValueAt(true,i,0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void MaxKuotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MaxKuotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaxKuotaKeyPressed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        for(i=0;i<tbObat.getRowCount();i++){
            if(tbObat.getValueAt(i,0).toString().equals("true")){
                Sequel.queryu2("delete from jadwalfisioterapi where no_rkm_medis=? and tanggal_periksa=? and jam_periksa=?",3,new String[]{
                    tbObat.getValueAt(i,3).toString(),tbObat.getValueAt(i,5).toString(),tbObat.getValueAt(i,6).toString()
                });
            }
        }
        tampil();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,NoReg,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
       if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(NmPoli.getText().trim().equals("")||NmPoli.getText().trim().equals("")){
            Valid.textKosong(KdPoli,"Unit/Poliklinik");
        }else if(NoReg.getText().trim().equals("")){
            Valid.textKosong(NoReg,"No.Antri");
        }else if(Kuota.getText().trim().equals("0")||Kuota.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Maaf Kuota Sudah Penuh Silahkan pilih Jam yang lain . . .");
        }else{
            isNomer();
            isBooking();
            emptTeks();
            tampil();
            
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void ppKirimWAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppKirimWAActionPerformed

       for(i=0;i<tbObat.getRowCount();i++){
            if(tbObat.getValueAt(i,0).toString().equals("true")){
              kodetelpon="62";
              nomorpasien=NoTelp.getText();
              NoTelp.setText(kodetelpon+nomorpasien);
           try{   
                headers = new HttpHeaders();
                headers.add("Content-type","application/json");   
                //headers.add("sender",api.getHmac());
                 requestJson ="{\"api_key\":\""+token+"\","+
                              "\"sender\":\""+Nohp+"\","+ 
                              "\"number\":\""+NoTelp.getText()+"\","+ 
                              "\"message\":\"Yang terhormat pasien An. "+tbObat.getValueAt(i,4).toString()+". Sekedar Mengingatkan Jadwal Kontrol Anda, Tanggal : "+tbObat.getValueAt(i,5).toString()+" Jam : "+tbObat.getValueAt(i,6).toString()+" WIB. Nomor Antrian : "+tbObat.getValueAt(i,1).toString()+" di Poli : FisioTerapi/Rehab Medik di RSUD dr.H.Soemarno.S Kapuas dan datanglah 30 menit sebelumnya. Pesan WhatsApp ini dikirim otomatis oleh sistem Rumah sakit.Terima Kasih\""+
                              "}";
                 //System.out.println("Kirim WA VIA JSON : "+requestJson);
                 requestEntity = new HttpEntity(requestJson,headers);
                 requestJson = apiwa.getRest().exchange(URL,HttpMethod.POST,requestEntity, String.class).getBody();
                 //System.out.println("respon WA API SERVER : "+requestJson);
                 
                }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server WA terputus...!");
               }else if(ex.toString().contains("404")){
                    JOptionPane.showMessageDialog(null,"Server Not Found. lelah bang...!");
                }else if(ex.toString().contains("502")){
                    JOptionPane.showMessageDialog(null,"Connection timed out. Hayati lelah bang...!");
                }else{
                    JOptionPane.showMessageDialog(null,response);
                }
            } 
           }     
       }
    }//GEN-LAST:event_ppKirimWAActionPerformed

    private void KuotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KuotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KuotaKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
      if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(NmPoli.getText().trim().equals("")||NmPoli.getText().trim().equals("")){
            Valid.textKosong(KdPoli,"Unit/Poliklinik");
        }else if(NoReg.getText().trim().equals("")){
            Valid.textKosong(NoReg,"No.Antri");
        }else if(Kuota.getText().trim().equals("0")||Kuota.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Maaf Kuota Sudah Penuh Silahkan pilih Jam yang lain . . .");
        }else{
            isNomer();
            Sequel.queryu2("update jadwalfisioterapi set tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"', jam_periksa='"+cmbJam.getSelectedItem().toString()+"' where no_rkm_medis='"+tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()+"' and tanggal_periksa='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"' and jam_periksa='"+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"'");
            Sequel.queryu2("update pasien set no_tlp='"+NoTelp.getText()+"' where no_rkm_medis='"+tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()+"' ");
            emptTeks();
            tampil();
            
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void cmbJamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJamItemStateChanged
       try {
            kuota();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbJamItemStateChanged

    private void cmbcarijamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbcarijamItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcarijamItemStateChanged

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoTelpKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgJadwalFisioTerapi dialog = new DlgJadwalFisioTerapi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPasien;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.TextBox Kuota;
    private widget.Label LCount;
    private widget.TextBox MaxKuota;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPoli;
    private widget.TextBox NoReg;
    private widget.TextBox NoTelp;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalBooking;
    private widget.Tanggal TanggalPeriksa;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbcarijam;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppKirimWA;
    private javax.swing.JMenuItem ppSemua;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {     
        if(R2.isSelected()==true){
            status=" jadwalfisioterapi.jam_periksa like'%"+cmbcarijam.getSelectedItem().toString().replaceAll("Semua","")+"%' and jadwalfisioterapi.tanggal_booking between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
        }else if(R3.isSelected()==true){
            status=" jadwalfisioterapi.jam_periksa like '%"+cmbcarijam.getSelectedItem().toString().replaceAll("Semua","")+"%' and jadwalfisioterapi.tanggal_periksa between '"+Valid.SetTgl(DTPCari3.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari4.getSelectedItem()+"")+"' ";           
        }
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
                    "SELECT jadwalfisioterapi.no_reg,jadwalfisioterapi.tanggal_booking,jadwalfisioterapi.no_rkm_medis,pasien.nm_pasien,jadwalfisioterapi.tanggal_periksa,jadwalfisioterapi.jam_periksa,jadwalfisioterapi.kd_dokter,dokter.nm_dokter,jadwalfisioterapi.kd_poli,poliklinik.nm_poli,pasien.no_tlp " +
                    "FROM jadwalfisioterapi INNER JOIN pasien ON pasien.no_rkm_medis=jadwalfisioterapi.no_rkm_medis " +
                    "INNER JOIN dokter ON dokter.kd_dokter=jadwalfisioterapi.kd_dokter " +
                    "INNER JOIN poliklinik ON poliklinik.kd_poli=jadwalfisioterapi.kd_poli " + 
                    "where "+status+" and jadwalfisioterapi.no_rkm_medis like ? or "+
                    status+" and pasien.nm_pasien like ? or "+
                    status+" and poliklinik.nm_poli like ? or "+
                    status+" and dokter.nm_dokter like ? order by jadwalfisioterapi.no_reg,jadwalfisioterapi.tanggal_booking");
            try {
                ps.setString(1,"%"+TCari.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){                    
                    tabMode.addRow(new Object[]{
                        false,rs.getString("no_reg"),rs.getString("tanggal_booking"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("tanggal_periksa"),rs.getString("jam_periksa"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),
                        rs.getString("kd_poli"),rs.getString("nm_poli"),rs.getString("no_tlp")
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
        TNoRM.setText("");
        TPasien.setText("");
        NoTelp.setText("");
        TanggalBooking.setDate(new Date());
        BtnPasien.requestFocus();
        isNomer();
        kuota();
    }
    
    private void isNomer(){
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from jadwalfisioterapi where kd_poli='"+KdPoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from jadwalfisioterapi where kd_dokter='"+KdDokter.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            case "dokter + poli":             
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from jadwalfisioterapi where kd_dokter='"+KdDokter.getText()+"' and kd_poli='"+KdPoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from jadwalfisioterapi where kd_dokter='"+KdDokter.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"'","",3,NoReg);
                break;
        }
    }
    
    private void kuota() {
            Kuota.setText(Sequel.cariIsi("SELECT "+MaxKuota.getText()+"-COUNT(no_rkm_medis) FROM jadwalfisioterapi WHERE tanggal_periksa='"+Valid.SetTgl(TanggalPeriksa.getSelectedItem()+"")+"' AND jam_periksa='"+cmbJam.getSelectedItem().toString()+"' AND kd_poli='"+KdPoli.getText()+"'"));
            }

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){            
            Valid.SetTgl(TanggalBooking,tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl(TanggalPeriksa,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            cmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NoReg.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoTelp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString().substring(1, 12));
        }
    }
    
    public void setNoRm(String norm,String nama) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }
    
    public void setNoRm(String norm,String nama,String kodepoli,String namapoli,String kodedokter,String namadokter) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        KdPoli.setText(kodepoli);
        NmPoli.setText(namapoli);
        KdDokter.setText(kodedokter);
        NmDokter.setText(namadokter);
        TCari.setText(norm);
        ChkInput.setSelected(true);
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
    
  

    private String jam(){
        int nilai_jam;
        int nilai_menit;
        int nilai_detik;
        String nol_jam = "";
        String nol_menit = "";
        String nol_detik = "";
        Date now = Calendar.getInstance().getTime();
        nilai_jam = now.getHours();
        nilai_menit = now.getMinutes();
        nilai_detik = now.getSeconds();

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
        String jam = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);
        return jam+":"+menit+":"+detik;
    }

    private void isBooking() {
        isNomer();
        if(Sequel.menyimpantf("jadwalfisioterapi","?,?,?,?,?,?,?","Pasien dan Tanggal",7,new String[]{
             NoReg.getText(),Valid.SetTgl(TanggalBooking.getSelectedItem()+""),TNoRM.getText(),
             Valid.SetTgl(TanggalPeriksa.getSelectedItem()+""),cmbJam.getSelectedItem().toString(),KdDokter.getText(),
             KdPoli.getText()
           })==true){
               Sequel.queryu2("update pasien set no_tlp='"+NoTelp.getText()+"' where no_rkm_medis='"+TNoRM.getText()+"' ");
            
            emptTeks();
            tampil();
            Kuota.setText("");
        } 
    }
}

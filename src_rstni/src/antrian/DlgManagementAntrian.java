

package antrian;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import simrskhanza.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import inventory.DlgCariKonversi;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author perpustakaan
 */
public class DlgManagementAntrian extends javax.swing.JDialog {
  private final DefaultTableModel tabMode,tabModeEmbeded;
  private PreparedStatement ps;
  private ResultSet rs;
  private sekuel Sequel=new sekuel();
  private validasi Valid=new validasi();
  private Connection koneksi=koneksiDB.condb();
  public  DlgCariDokter dokter=new DlgCariDokter(null,false);
  public  DlgListKodeAntrian listKodeLayanan=new DlgListKodeAntrian(null,false);
  private int pilihan=0,i=0,kuota=0,jmlparsial=0;
  

    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgManagementAntrian(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setLocation(8,1);
        setSize(885,350);
        
        Object[] row={"Kode Dokter","Nama Dokter","Nama Poli Antrian","Kode Layannan Antrian"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbMapingDokter.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbMapingDokter.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMapingDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbMapingDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(100);
            }
        }
        tbMapingDokter.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        Object[] rowEmbeded={"Kode Dokter","Nama Dokter","Lokasi Antrian","Box Antrian","Audio"};
        tabModeEmbeded=new DefaultTableModel(null,rowEmbeded){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbMapingDokterEmbeded.setModel(tabModeEmbeded);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbMapingDokterEmbeded.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMapingDokterEmbeded.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbMapingDokterEmbeded.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(200);
            }
        }
        tbMapingDokterEmbeded.setDefaultRenderer(Object.class, new WarnaTable()); 
        
                
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(dokter.getTable().getSelectedRow()!= -1){                    
                       if(pilihan==1){
                            kdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                            nmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            nmDokter.requestFocus();
                       }else if(pilihan==2){
                            kdDokterEmbeded.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                            nmDokterEmbeded.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            nmDokterEmbeded.requestFocus();
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
        listKodeLayanan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(listKodeLayanan.getTable().getSelectedRow()!= -1){                    
                       
                            kdAntrian.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),0).toString());
                            nmPelayanan.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),1).toString());
                            kdPelayanan.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),2).toString());
                            
                      
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
       
        tampil();
    }

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        tabPane = new widget.TabPane();
        intMappingDokterIntegrasi = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        jLabel37 = new widget.Label();
        nmDokter = new widget.TextBox();
        BtnDataPasien1 = new widget.Button();
        kdDokter = new widget.TextBox();
        jLabel38 = new widget.Label();
        kdAntrian = new widget.TextBox();
        nmPelayanan = new widget.TextBox();
        BtnDataPasien2 = new widget.Button();
        kdPelayanan = new widget.TextBox();
        intMapingDokter = new widget.ScrollPane();
        tbMapingDokter = new widget.Table();
        intMappingDokterEmbeded = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel39 = new widget.Label();
        nmDokterEmbeded = new widget.TextBox();
        BtnDataPasien3 = new widget.Button();
        kdDokterEmbeded = new widget.TextBox();
        jLabel40 = new widget.Label();
        cmbLokasi = new widget.ComboBox();
        jLabel41 = new widget.Label();
        cmbBox = new widget.ComboBox();
        audio = new widget.TextBox();
        jLabel42 = new widget.Label();
        intMapingDokter1 = new widget.ScrollPane();
        tbMapingDokterEmbeded = new widget.Table();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "::[ Management Antrian ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(875, 200));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        tabPane.setName("tabPane"); // NOI18N
        tabPane.setPreferredSize(new java.awt.Dimension(873, 448));
        tabPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPaneMouseClicked(evt);
            }
        });

        intMappingDokterIntegrasi.setName("intMappingDokterIntegrasi"); // NOI18N
        intMappingDokterIntegrasi.setLayout(new java.awt.BorderLayout());

        panelGlass12.setMinimumSize(new java.awt.Dimension(531, 200));
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(200, 80));
        panelGlass12.setRequestFocusEnabled(false);
        panelGlass12.setLayout(null);

        jLabel37.setText("Dokter SIMRS :");
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(0, 10, 80, 23);

        nmDokter.setEditable(false);
        nmDokter.setName("nmDokter"); // NOI18N
        nmDokter.setPreferredSize(new java.awt.Dimension(300, 23));
        nmDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmDokterKeyPressed(evt);
            }
        });
        panelGlass12.add(nmDokter);
        nmDokter.setBounds(210, 10, 270, 23);

        BtnDataPasien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataPasien1.setMnemonic('X');
        BtnDataPasien1.setToolTipText("Alt+X");
        BtnDataPasien1.setName("BtnDataPasien1"); // NOI18N
        BtnDataPasien1.setPreferredSize(new java.awt.Dimension(30, 20));
        BtnDataPasien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPasien1ActionPerformed(evt);
            }
        });
        BtnDataPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataPasien1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnDataPasien1);
        BtnDataPasien1.setBounds(480, 10, 30, 20);

        kdDokter.setEditable(false);
        kdDokter.setName("kdDokter"); // NOI18N
        kdDokter.setPreferredSize(new java.awt.Dimension(300, 23));
        kdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdDokterKeyPressed(evt);
            }
        });
        panelGlass12.add(kdDokter);
        kdDokter.setBounds(80, 10, 130, 23);

        jLabel38.setText("Kode Antrian :");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel38);
        jLabel38.setBounds(0, 40, 80, 23);

        kdAntrian.setEditable(false);
        kdAntrian.setName("kdAntrian"); // NOI18N
        kdAntrian.setPreferredSize(new java.awt.Dimension(300, 23));
        kdAntrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdAntrianKeyPressed(evt);
            }
        });
        panelGlass12.add(kdAntrian);
        kdAntrian.setBounds(80, 40, 130, 23);

        nmPelayanan.setEditable(false);
        nmPelayanan.setName("nmPelayanan"); // NOI18N
        nmPelayanan.setPreferredSize(new java.awt.Dimension(300, 23));
        nmPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPelayananKeyPressed(evt);
            }
        });
        panelGlass12.add(nmPelayanan);
        nmPelayanan.setBounds(270, 40, 210, 23);

        BtnDataPasien2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataPasien2.setMnemonic('X');
        BtnDataPasien2.setToolTipText("Alt+X");
        BtnDataPasien2.setName("BtnDataPasien2"); // NOI18N
        BtnDataPasien2.setPreferredSize(new java.awt.Dimension(30, 20));
        BtnDataPasien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPasien2ActionPerformed(evt);
            }
        });
        BtnDataPasien2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataPasien2KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnDataPasien2);
        BtnDataPasien2.setBounds(480, 40, 30, 20);

        kdPelayanan.setEditable(false);
        kdPelayanan.setName("kdPelayanan"); // NOI18N
        kdPelayanan.setPreferredSize(new java.awt.Dimension(300, 23));
        kdPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdPelayananKeyPressed(evt);
            }
        });
        panelGlass12.add(kdPelayanan);
        kdPelayanan.setBounds(210, 40, 60, 23);

        intMappingDokterIntegrasi.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        intMapingDokter.setName("intMapingDokter"); // NOI18N
        intMapingDokter.setOpaque(true);

        tbMapingDokter.setAutoCreateRowSorter(true);
        tbMapingDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMapingDokter.setName("tbMapingDokter"); // NOI18N
        tbMapingDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapingDokterMouseClicked(evt);
            }
        });
        intMapingDokter.setViewportView(tbMapingDokter);

        intMappingDokterIntegrasi.add(intMapingDokter, java.awt.BorderLayout.CENTER);

        tabPane.addTab("Mapping Dokter | Antrian Integrasi", intMappingDokterIntegrasi);

        intMappingDokterEmbeded.setName("intMappingDokterEmbeded"); // NOI18N
        intMappingDokterEmbeded.setLayout(new java.awt.BorderLayout());

        panelGlass13.setMinimumSize(new java.awt.Dimension(531, 200));
        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(200, 80));
        panelGlass13.setRequestFocusEnabled(false);
        panelGlass13.setLayout(null);

        jLabel39.setText("Dokter SIMRS :");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(jLabel39);
        jLabel39.setBounds(0, 10, 80, 23);

        nmDokterEmbeded.setEditable(false);
        nmDokterEmbeded.setName("nmDokterEmbeded"); // NOI18N
        nmDokterEmbeded.setPreferredSize(new java.awt.Dimension(300, 23));
        nmDokterEmbeded.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmDokterEmbededKeyPressed(evt);
            }
        });
        panelGlass13.add(nmDokterEmbeded);
        nmDokterEmbeded.setBounds(210, 10, 270, 23);

        BtnDataPasien3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataPasien3.setMnemonic('X');
        BtnDataPasien3.setToolTipText("Alt+X");
        BtnDataPasien3.setName("BtnDataPasien3"); // NOI18N
        BtnDataPasien3.setPreferredSize(new java.awt.Dimension(30, 20));
        BtnDataPasien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPasien3ActionPerformed(evt);
            }
        });
        BtnDataPasien3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataPasien3KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnDataPasien3);
        BtnDataPasien3.setBounds(480, 10, 30, 20);

        kdDokterEmbeded.setEditable(false);
        kdDokterEmbeded.setName("kdDokterEmbeded"); // NOI18N
        kdDokterEmbeded.setPreferredSize(new java.awt.Dimension(300, 23));
        kdDokterEmbeded.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdDokterEmbededKeyPressed(evt);
            }
        });
        panelGlass13.add(kdDokterEmbeded);
        kdDokterEmbeded.setBounds(80, 10, 130, 23);

        jLabel40.setText("Lokasi Antrian :");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(jLabel40);
        jLabel40.setBounds(0, 40, 80, 23);

        cmbLokasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Lokasi Antrian -", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbLokasi.setName("cmbLokasi"); // NOI18N
        cmbLokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLokasiKeyPressed(evt);
            }
        });
        panelGlass13.add(cmbLokasi);
        cmbLokasi.setBounds(90, 40, 155, 23);

        jLabel41.setText("Audio :");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(jLabel41);
        jLabel41.setBounds(520, 40, 70, 23);

        cmbBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Box Antrian -", "1", "2", "3", "4" }));
        cmbBox.setName("cmbBox"); // NOI18N
        cmbBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBoxKeyPressed(evt);
            }
        });
        panelGlass13.add(cmbBox);
        cmbBox.setBounds(350, 40, 155, 23);

        audio.setName("audio"); // NOI18N
        audio.setPreferredSize(new java.awt.Dimension(300, 23));
        audio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                audioKeyPressed(evt);
            }
        });
        panelGlass13.add(audio);
        audio.setBounds(590, 40, 320, 23);

        jLabel42.setText("Lokasi Box :");
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass13.add(jLabel42);
        jLabel42.setBounds(260, 40, 80, 23);

        intMappingDokterEmbeded.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        intMapingDokter1.setName("intMapingDokter1"); // NOI18N
        intMapingDokter1.setOpaque(true);

        tbMapingDokterEmbeded.setAutoCreateRowSorter(true);
        tbMapingDokterEmbeded.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMapingDokterEmbeded.setName("tbMapingDokterEmbeded"); // NOI18N
        tbMapingDokterEmbeded.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapingDokterEmbededMouseClicked(evt);
            }
        });
        intMapingDokter1.setViewportView(tbMapingDokterEmbeded);

        intMappingDokterEmbeded.add(intMapingDokter1, java.awt.BorderLayout.CENTER);

        tabPane.addTab("Mapping Dokter | Antrian Embeded", intMappingDokterEmbeded);

        internalFrame1.add(tabPane, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
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

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
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

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
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

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+T");
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
    
    private void berhasilSimpan() {  
   
} 
    
    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      
    }//GEN-LAST:event_formWindowActivated

    private void tbMapingDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapingDokterMouseClicked
//        getData();
    }//GEN-LAST:event_tbMapingDokterMouseClicked

    private void tabPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPaneMouseClicked
        if(tabPane.getSelectedIndex()==0){
            tampil();
        }else if(tabPane.getSelectedIndex()==1){
            tampilEmbeded();
        }
    }//GEN-LAST:event_tabPaneMouseClicked

    private void nmDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmDokterKeyPressed

    private void BtnDataPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPasien1ActionPerformed
                pilihan=1;
                dokter.isCek();        
                dokter.TCari.requestFocus();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setVisible(true);
    }//GEN-LAST:event_BtnDataPasien1ActionPerformed

    private void BtnDataPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataPasien1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataPasien1KeyPressed

    private void kdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdDokterKeyPressed

    private void kdAntrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdAntrianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdAntrianKeyPressed

    private void nmPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPelayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPelayananKeyPressed

    private void BtnDataPasien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPasien2ActionPerformed
                listKodeLayanan.tampil();        
                listKodeLayanan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                listKodeLayanan.setLocationRelativeTo(internalFrame1);
                listKodeLayanan.setVisible(true);
    }//GEN-LAST:event_BtnDataPasien2ActionPerformed

    private void BtnDataPasien2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataPasien2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataPasien2KeyPressed

    private void kdPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdPelayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdPelayananKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
         if(tabPane.getSelectedIndex()==0){
            if(kdDokter.getText().trim().equals("")){
            Valid.textKosong(kdDokter,"Kode Dokter");
            }else if(kdAntrian.getText().trim().equals("")){
                Valid.textKosong(kdAntrian,"Kode Poli Antrian");
            }else{
                Sequel.menyimpan("mapping_dokterantrian","'"+kdDokter.getText()+"','"+kdAntrian.getText()+"','"+kdPelayanan.getText()+"'");  
            emptTeks();
            tampil();
            }
        }else if(tabPane.getSelectedIndex()==1){
            if(kdDokterEmbeded.getText().trim().equals("")){
            Valid.textKosong(kdDokterEmbeded,"Kode Dokter");
            }else if(cmbLokasi.getSelectedIndex()==0){
                Valid.textKosong(kdDokterEmbeded,"Tentukan Antrian");
            }else if(cmbBox.getSelectedIndex()==0){
                Valid.textKosong(kdDokterEmbeded,"Tentukan Box Antrian");
            }else{
                Sequel.menyimpan("tt_queue_mapping","null,'"+kdDokterEmbeded.getText()+"','"+cmbLokasi.getSelectedItem()+"','"+cmbBox.getSelectedItem()+"','"+audio.getText()+"'");  
            emptTeks();
            tampilEmbeded();
            }
        }
        
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,BtnDokter,NoRujukan);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(kdDokter.getText().trim().equals("")){
            Valid.textKosong(kdDokter,"kode dokter");
        }else if(nmDokter.getText().trim().equals("")){
            Valid.textKosong(nmDokter,"nama dokter");
        }else{
            try {
                Sequel.mengedit("mapping_dokterantrian","kd_dokter='"+kdDokter.getText()+"'",""+
                    "kd_dokter_antrian='"+kdAntrian.getText()+""+
                    "',kd_header='"+kdPelayanan.getText()+"'"+
                    "");
                koneksi.setAutoCommit(true);
                if(tabMode.getRowCount()!=0){tampil();}
                emptTeks();
            } catch (SQLException ex) {
                return;
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
//            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbMapingDokterEmbeded.getSelectedRow()>-1){
            if(Sequel.queryu2tf("delete from tt_queue_mapping where kd_dokter=?",1,new String[]{
                tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),0).toString()
            })==true){
                tampilEmbeded();
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
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

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
//        ChkInput.setSelected(true);
//        isForm();
//        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void nmDokterEmbededKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmDokterEmbededKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmDokterEmbededKeyPressed

    private void BtnDataPasien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPasien3ActionPerformed
                pilihan=2;
                dokter.isCek();        
                dokter.TCari.requestFocus();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setVisible(true);
    }//GEN-LAST:event_BtnDataPasien3ActionPerformed

    private void BtnDataPasien3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataPasien3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataPasien3KeyPressed

    private void kdDokterEmbededKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdDokterEmbededKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdDokterEmbededKeyPressed

    private void tbMapingDokterEmbededMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapingDokterEmbededMouseClicked
        getData();
    }//GEN-LAST:event_tbMapingDokterEmbededMouseClicked

    private void cmbLokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLokasiKeyPressed
        
    }//GEN-LAST:event_cmbLokasiKeyPressed

    private void cmbBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBoxKeyPressed

    private void audioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_audioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_audioKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgManagementAntrian dialog = new DlgManagementAntrian(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnDataPasien1;
    private widget.Button BtnDataPasien2;
    private widget.Button BtnDataPasien3;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.TextBox audio;
    private widget.ComboBox cmbBox;
    private widget.ComboBox cmbLokasi;
    private widget.ScrollPane intMapingDokter;
    private widget.ScrollPane intMapingDokter1;
    private widget.InternalFrame intMappingDokterEmbeded;
    private widget.InternalFrame intMappingDokterIntegrasi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.TextBox kdAntrian;
    private widget.TextBox kdDokter;
    private widget.TextBox kdDokterEmbeded;
    private widget.TextBox kdPelayanan;
    private widget.TextBox nmDokter;
    private widget.TextBox nmDokterEmbeded;
    private widget.TextBox nmPelayanan;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass8;
    private widget.TabPane tabPane;
    private widget.Table tbMapingDokter;
    private widget.Table tbMapingDokterEmbeded;
    // End of variables declaration//GEN-END:variables
    

    private void isPsien() {
//        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }
    private void tampil() {
//        Valid.tabelKosong(tabMode);
//        try {
//            ps=koneksi.prepareStatement("select * "+
//                "from mapping_dokterantrian inner join dokter ON mapping_dokterantrian.kd_dokter=dokter.kd_dokter "+
//                " order by kd_header");
//            try{
//                ps.setString(1,"%"+TCari.getText().trim()+"%");
//                ps.setString(2,"%"+TCari.getText().trim()+"%");
//                ps.setString(3,"%"+TCari.getText().trim()+"%");
//                ps.setString(4,"%"+TCari.getText().trim()+"%");
//                ps.setString(5,"%"+TCari.getText().trim()+"%");
//                ps.setString(6,"%"+TCari.getText().trim()+"%");
//                ps.setString(7,"%"+TCari.getText().trim()+"%");
//                ps.setString(8,"%"+TCari.getText().trim()+"%");
//                ps.setString(9,"%"+TCari.getText().trim()+"%");
//                ps.setString(10,"%"+TCari.getText().trim()+"%");
//                ps.setString(11,"%"+TCari.getText().trim()+"%");
//                ps.setString(12,"%"+TCari.getText().trim()+"%");
//                ps.setString(13,"%"+TCari.getText().trim()+"%");
//                rs=ps.executeQuery();
//                while(rs.next()){
//                    String[] data={rs.getString("kd_dokter"),
//                                   rs.getString("nm_dokter"),
//                                   rs.getString("kd_dokter_antrian"),
//                                   rs.getString("kd_header")};
//                    tabMode.addRow(data);
//                }
//            }catch(SQLException e){
//                System.out.println("Notifikasi : "+e);
//            }finally{
//                if( rs != null ){
//                    rs.close();
//                }
//                
//                if( ps != null ){
//                    ps.close();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        int b=tabMode.getRowCount();
//        LCount.setText(""+b);
    }
    private void tampilEmbeded() {
        Valid.tabelKosong(tabModeEmbeded);
        try {
            ps=koneksi.prepareStatement("select * "+
                "from tt_queue_mapping inner join dokter ON tt_queue_mapping.kd_dokter=dokter.kd_dokter "+
                " order by queue_view asc ");
            try{
                rs=ps.executeQuery();
                while(rs.next()){
                    String[] data={rs.getString("kd_dokter"),
                                   rs.getString("nm_dokter"),
                                   rs.getString("queue_view"),
                                   rs.getString("queue_title"),
                                   rs.getString("audio")};
                    tabModeEmbeded.addRow(data);
                }
            }catch(SQLException e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if( rs != null ){
                    rs.close();
                }
                
                if( ps != null ){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int b=tabMode.getRowCount();
//        LCount.setText(""+b);
    }
    private void getData() {
        if(tbMapingDokterEmbeded.getSelectedRow()!= -1){      
            kdDokterEmbeded.setText(tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),0).toString());
            nmDokterEmbeded.setText(tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),1).toString());
//            kdAntrian.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),2).toString());
//            kdPelayanan.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),3).toString());
            cmbLokasi.setSelectedItem(tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),2).toString());
            cmbBox.setSelectedItem(tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),3).toString());
            audio.setText(tbMapingDokterEmbeded.getValueAt(tbMapingDokterEmbeded.getSelectedRow(),4).toString());
            
        }
    }
    public void emptTeks() {
        kdDokter.setText("");
        nmDokter.setText("");
        kdAntrian.setText("");
        kdPelayanan.setText("");
        nmPelayanan.setText("");
        kdDokterEmbeded.setText("");
        nmDokterEmbeded.setText("");
        cmbLokasi.setSelectedIndex(0);
        cmbBox.setSelectedIndex(0);
       
    }

   

}

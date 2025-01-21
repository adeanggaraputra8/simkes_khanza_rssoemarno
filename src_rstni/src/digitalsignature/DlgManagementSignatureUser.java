/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package digitalsignature;

import antrian.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
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
public class DlgManagementSignatureUser extends javax.swing.JDialog {
  private final DefaultTableModel tabMode;
  private PreparedStatement ps;
  private ResultSet rs;
  private sekuel Sequel=new sekuel();
  private validasi Valid=new validasi();
  private Connection koneksi=koneksiDB.condb();
  public  DlgCariDokter dokter=new DlgCariDokter(null,false);
  public  DlgListKodeAntrian listKodeLayanan=new DlgListKodeAntrian(null,false);
  private String link="",URL="",responseJson="";
  private HttpHeaders headers;
  private HttpEntity requestEntity;
  private ObjectMapper mapper = new ObjectMapper();
  private JsonNode root;
  private TteApi apiTte=new TteApi();
  

    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgManagementSignatureUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setLocation(8,1);
        setSize(885,550);
        
        Object[] row={"Kode Petugas","NIK","Nama Petugas","Status"};
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
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(400);
            }
        }
        tbMapingDokter.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                    if(dokter.getTable().getSelectedRow()!= -1){                    
                       
                            kdPetugas.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                            nmPetugas.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            nmPetugas.requestFocus();
                      
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
                       
//                            kdAntrian.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),0).toString());
                            nik.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),1).toString());
//                            kdPelayanan.setText(listKodeLayanan.getTable().getValueAt(listKodeLayanan.getTable().getSelectedRow(),2).toString());
                            
                      
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
        intMappingDokter = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        jLabel37 = new widget.Label();
        nmPetugas = new widget.TextBox();
        BtnDataPasien1 = new widget.Button();
        kdPetugas = new widget.TextBox();
        jLabel38 = new widget.Label();
        nik = new widget.TextBox();
        intMapingDokter = new widget.ScrollPane();
        tbMapingDokter = new widget.Table();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnCheck = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "::[ Management Signature Petugas ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
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

        intMappingDokter.setName("intMappingDokter"); // NOI18N
        intMappingDokter.setLayout(new java.awt.BorderLayout());

        panelGlass12.setMinimumSize(new java.awt.Dimension(531, 200));
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(200, 80));
        panelGlass12.setRequestFocusEnabled(false);
        panelGlass12.setLayout(null);

        jLabel37.setText("Petugas :");
        jLabel37.setEnabled(false);
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(0, 10, 80, 23);

        nmPetugas.setEditable(false);
        nmPetugas.setEnabled(false);
        nmPetugas.setName("nmPetugas"); // NOI18N
        nmPetugas.setPreferredSize(new java.awt.Dimension(300, 23));
        nmPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPetugasKeyPressed(evt);
            }
        });
        panelGlass12.add(nmPetugas);
        nmPetugas.setBounds(210, 10, 270, 23);

        BtnDataPasien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataPasien1.setMnemonic('X');
        BtnDataPasien1.setToolTipText("Alt+X");
        BtnDataPasien1.setEnabled(false);
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

        kdPetugas.setEditable(false);
        kdPetugas.setEnabled(false);
        kdPetugas.setName("kdPetugas"); // NOI18N
        kdPetugas.setPreferredSize(new java.awt.Dimension(300, 23));
        kdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdPetugasKeyPressed(evt);
            }
        });
        panelGlass12.add(kdPetugas);
        kdPetugas.setBounds(80, 10, 130, 23);

        jLabel38.setText("NIK Signature :");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel38);
        jLabel38.setBounds(0, 40, 80, 23);

        nik.setName("nik"); // NOI18N
        nik.setPreferredSize(new java.awt.Dimension(300, 23));
        nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nikKeyPressed(evt);
            }
        });
        panelGlass12.add(nik);
        nik.setBounds(80, 40, 400, 23);

        intMappingDokter.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

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

        intMappingDokter.add(intMapingDokter, java.awt.BorderLayout.CENTER);

        tabPane.addTab("Status Akun TTE", intMappingDokter);

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

        BtnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCheck.setMnemonic('T');
        BtnCheck.setToolTipText("Alt+T");
        BtnCheck.setName("BtnCheck"); // NOI18N
        BtnCheck.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCheckActionPerformed(evt);
            }
        });
        BtnCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCheckKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnCheck);

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
        getData();
    }//GEN-LAST:event_tbMapingDokterMouseClicked

    private void tabPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPaneMouseClicked
        if(tabPane.getSelectedIndex()==0){

        }else if(tabPane.getSelectedIndex()==1){
//            tampil1();
        }else if(tabPane.getSelectedIndex()==2){
            //  tampil2();
        }else if(tabPane.getSelectedIndex()==3){
//            tampil3();
        }
    }//GEN-LAST:event_tabPaneMouseClicked

    private void nmPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPetugasKeyPressed

    private void BtnDataPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPasien1ActionPerformed
                dokter.isCek();        
                dokter.TCari.requestFocus();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setVisible(true);
    }//GEN-LAST:event_BtnDataPasien1ActionPerformed

    private void BtnDataPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataPasien1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataPasien1KeyPressed

    private void kdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdPetugasKeyPressed

    private void nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nikKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(nik.getText().trim().equals("")){
            Valid.textKosong(kdPetugas,"NIK Petugas");
        }else{
            Sequel.menyimpan("akun_tte","'"+nik.getText()+"'");  
        emptTeks();
        tampil();
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
        if(kdPetugas.getText().trim().equals("")){
            Valid.textKosong(kdPetugas,"kode dokter");
        }else if(nmPetugas.getText().trim().equals("")){
            Valid.textKosong(nmPetugas,"nama dokter");
        }else{
            try {
//                Sequel.mengedit("mapping_dokterantrian","kd_dokter='"+kdPetugas.getText()+"'",""+
//                    "kd_dokter_antrian='"+kdAntrian.getText()+""+
//                    "',kd_header='"+kdPelayanan.getText()+"'"+
//                    "");
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
//        try {
//            Sequel.mengedit("dokter","kd_dokter='"+TKd.getText()+"'","status='0'");
//            Sequel.mengedit("pegawai","nik='"+TKd.getText()+"'","stts_aktif='KELUAR'");
//            tampil();
//            emptTeks();
//        } catch (Exception ex) {
//            System.out.println("Notifikasi : "+ex);
//        }
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

    private void BtnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCheckActionPerformed
        if(tabPane.getSelectedIndex()==0){
           emptTeks();
           tampil();
        }else if(tabPane.getSelectedIndex()==1){
           emptTeks();
//           tampilStatusAkun();
        }
        
    }//GEN-LAST:event_BtnCheckActionPerformed

    private void BtnCheckKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCheckKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCheckKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgManagementSignatureUser dialog = new DlgManagementSignatureUser(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCheck;
    private widget.Button BtnDataPasien1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.ScrollPane intMapingDokter;
    private widget.InternalFrame intMappingDokter;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.TextBox kdPetugas;
    private widget.TextBox nik;
    private widget.TextBox nmPetugas;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.TabPane tabPane;
    private widget.Table tbMapingDokter;
    // End of variables declaration//GEN-END:variables
    

    private void isPsien() {
//        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }
    private void tampil() {
         this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.tabelKosong(tabMode);
//        tabMode.addRow(new Object[]{"coba","User Uji Coba","0803202100007062","Active"});
        try {
            ps=koneksi.prepareStatement("select akun_tte.nik, pegawai.nama,pegawai.nik as idpetugas "+
                "from akun_tte JOIN pegawai ON akun_tte.nik=pegawai.no_ktp");
            try{
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
                rs=ps.executeQuery();
                while(rs.next()){
                    String status,namaPetugas,kodepetugas;
                    if(rs.getString("idpetugas").equals(null)||rs.getString("idpetugas").equals("")){
                        kodepetugas="-";
                    }else{
                        kodepetugas=rs.getString("idpetugas");
                    }
                    if(rs.getString("nama").equals(null)||rs.getString("nama").equals("")){
                        namaPetugas="-";
                    }else{
                        namaPetugas=rs.getString("nama");
                    }
//                    status="-";
                    status=tampilStatusAkun(rs.getString("nik"));
                    String[] data={kodepetugas,rs.getString("nik").substring(0,10)+"******",namaPetugas,status};
                    
                    tabMode.addRow(data);
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
            System.out.println("Notifikasi"+e);
        }
        int b=tabMode.getRowCount();
//        LCount.setText(""+b);
    this.setCursor(Cursor.getDefaultCursor());
    }
    private String tampilStatusAkun(String nik){
        try {
            
            link="http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/webapps/berkastte/";
            URL = link+"cekstatus.php?nik="+nik;
            headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    requestEntity = new HttpEntity(headers);
	    root = mapper.readTree(apiTte.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            responseJson = root.path("response").path("message").asText();
//              System.out.println(nik+" "+root);
        } catch (Exception e) {
            System.out.println("error Search"+e);
        }
        return responseJson;
    }
    private void getData() {
        if(tbMapingDokter.getSelectedRow()!= -1){      
            kdPetugas.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),0).toString());
            nmPetugas.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),1).toString());
//            kdAntrian.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),2).toString());
//            kdPelayanan.setText(tbMapingDokter.getValueAt(tbMapingDokter.getSelectedRow(),3).toString());
            
        }
    }
    public void emptTeks() {
        kdPetugas.setText("");
        nmPetugas.setText("");
        nik.setText("");
       
    }

   

}

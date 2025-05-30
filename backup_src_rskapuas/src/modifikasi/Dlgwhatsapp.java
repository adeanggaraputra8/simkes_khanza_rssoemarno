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

package modifikasi;
//package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.scheme.Scheme;
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
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import java.sql.PreparedStatement;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import bridging.ApiWA;


/**
 *
 * @author perpustakaan
 */
public class Dlgwhatsapp extends javax.swing.JDialog {
    private Connection koneksi=koneksiDB.condb();
    private JsonNode root;
    private JsonNode response;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private RestTemplate rest;
    private ApiWA api=new ApiWA();
    private ObjectMapper mapper = new ObjectMapper();
    private String Nohp="",token="",stringbalik="Hallo, Selamat ",respon="", requestJson, TOKEN="", URL="",catat="",rsk="",poli="",antrian="", tgl="",jamperiksa="",
            isi="Yang terhormat Pasien An. #nama# dengan Nomor Rekam Medik #norm# Sekedar Mengingatkan Pendaftaran/Jadwal Kontrol Anda: Tanggal : #tglperiksa# Jam Periksa #jamperiksa# WIB Nomor Antrian Poli : #antrian# Poliklinik : #poliklinik# Bawalah kartu berobat anda.  Datanglah 30 menit sebelumnya Pesan WhatsApp ini dikirim otomatis oleh #rs#  Terima Kasih";
    
    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    
    
    public Dlgwhatsapp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        token = koneksiDB.TOKENWA();
        URL = koneksiDB.URLAPIWAAPI();
        Nohp= koneksiDB.NOHPRS();
        this.setLocation(8,1);
        setSize(885,674);
        
        //TCatatan.setText(TCatatan); 
        TCatatan.setLineWrap(true);
        TCatatan.setWrapStyleWord(true);
        //prop.loadFromXML(new FileInputStream("setting/database.xml"));
        
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
        FormInput = new widget.PanelBiasa();
        TPasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        TCatatan = new widget.TextArea();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        TNoWA = new widget.TextBox();
        jLabel6 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Kirim Pesan WhatsApp ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 137));
        FormInput.setLayout(null);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(220, 10, 250, 23);

        jLabel9.setText("Pesan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(10, 50, 65, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        TCatatan.setColumns(40);
        TCatatan.setRows(5);
        TCatatan.setName("TCatatan"); // NOI18N
        Scroll3.setViewportView(TCatatan);

        FormInput.add(Scroll3);
        Scroll3.setBounds(80, 50, 580, 260);

        jLabel4.setText("No.R.M. :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(-10, 10, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(80, 10, 130, 23);

        TNoWA.setHighlighter(null);
        TNoWA.setName("TNoWA"); // NOI18N
        FormInput.add(TNoWA);
        TNoWA.setBounds(560, 10, 100, 23);

        jLabel6.setText("No WhatsApp :");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(470, 10, 80, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.CENTER);
        FormInput.getAccessibleContext().setAccessibleName("");
        FormInput.getAccessibleContext().setAccessibleDescription("");

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Kirim WA");
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

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Tutup");
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
    
    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"No.Rekam Medis");
        }else if(TNoWA.getText().trim().equals("")){
            Valid.textKosong(TCatatan,"No Whats App");
        }else if(TCatatan.getText().trim().equals("")){
            Valid.textKosong(TCatatan,"Pesan");
        }else{       
            //------------------------------kirim ke WA API------------------>
             try{   
                headers = new HttpHeaders();
                headers.add("Content-type","application/json");   
                //headers.add("sender",api.getHmac());
                 requestJson =//{\"api_key\":\""+token+"\","+
                              "{\"sender\":\""+api.getHmac()+"\","+
                              //"\"sender\":\""+Nohp+"\","+ 
                              "\"number\":\""+TNoWA.getText()+"\","+ 
                              "\"message\":\""+TCatatan.getText()+"\""+
                              "}";
                 System.out.println("Kirim WA VIA JSON : "+requestJson);
                 requestEntity = new HttpEntity(requestJson,headers);
                 requestJson = api.getRest().exchange(URL,HttpMethod.POST,requestEntity, String.class).getBody();
                 System.out.println("respon WA API SERVER : "+requestJson);
                 
                }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server WA terputus...!");
               }else if(ex.toString().contains("404")){
                    JOptionPane.showMessageDialog(null,"Server Not Found. lelah bang...!");
                }else if(ex.toString().contains("502")){
                    JOptionPane.showMessageDialog(null,"Connection timed out. Hayati lelah bang...!");
                }else{
                    JOptionPane.showMessageDialog(null,respon);
                }
            } 
            //---------------------Akhir Kirim WA ---------------------------------->
            
            //Sequel.menyimpan3("catatan_wa","?,?",2,new String[]{TNoRM.getText(),TCatatan.getText()},"no_rkm_medis=?","catatan=?",2,new String[]{TCatatan.getText(),TNoRM.getText()});
            BtnKeluarActionPerformed(evt);   
           
        }  
        
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TCatatan,BtnKeluar);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
       Sequel.meghapus2("catatan_pasien","no_rkm_medis",TNoRM.getText());
       BtnKeluarActionPerformed(evt);       
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
        Valid.textKosong(TNoRM,"No.Rekam Medis");
    }else if(TCatatan.getText().trim().equals("")){
        Valid.textKosong(TCatatan,"Catatan");
    }else{  
        Sequel.mengedit2("catatan_wa","no_rkm_medis=?","catatan=?",2,new String[]{
            TCatatan.getText(),TNoRM.getText()
        });
        BtnKeluarActionPerformed(evt);
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TCatatan.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Dlgwhatsapp dialog = new Dlgwhatsapp(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private widget.ScrollPane Scroll3;
    private widget.TextArea TCatatan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoWA;
    private widget.TextBox TPasien;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass8;
    // End of variables declaration//GEN-END:variables
    

    private void isPsien() {
        //TCatatan.setText(isi.replace("#norm#",TPasien));
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
        //tgl=Sequel.cariIsi("select DATE_FORMAT(tanggal_periksa,'%d-%m-%Y') from booking_registrasi where no_rkm_medis ='"+TNoRM.getText()+"' AND status='Belum' order by tanggal_booking DESC LIMIT 1");
        //antrian=Sequel.cariIsi("select no_reg from booking_registrasi where no_rkm_medis ='"+TNoRM.getText()+"'  AND status='Belum' order by tanggal_booking DESC LIMIT 1");
        //poli=Sequel.cariIsi("select b.nm_poli,a.kd_poli from booking_registrasi a inner join poliklinik b on a.kd_poli = b.kd_poli where a.no_rkm_medis ='"+TNoRM.getText()+"'AND a.status='Belum' order by a.tanggal_booking DESC LIMIT 1");
        rsk=Sequel.cariIsi("select nama_instansi from setting" );
        TCatatan.setText(isi.replace("#nama#",TPasien.getText()).replace("#norm#",TNoRM.getText()).replace("#tglperiksa#",tgl).replace("#jamperiksa#",jamperiksa).replace("#antrian#",antrian).replace("#poliklinik#",poli).replace("#rs#", rsk));
        //catat= TCatatan.setText(isi.replace("#nama#",TPasien.getText()).replace("#norm#",TNoRM.getText()).replace("#tglperiksa#",tgl).replace("#antrian#",antrian).replace("#poliklinik#",poli).replace("#rs#", rsk));
    }

    public void setNoRm(String norm) {
        //isi=TCatatan.getText();
        TNoRM.setText(norm);  
        isPsien();   
        Sequel.cariIsi("select catatan from catatan_pasien where no_rkm_medis=?",TCatatan,TNoRM.getText());       
    }
    public void setNoWA(String nowa) {
        //isi=TCatatan.getText();
        TNoWA.setText(nowa);  
        isPsien();   
        //Sequel.cariIsi("select catatan from catatan_pasien where no_rkm_medis=?",TCatatan,TNoRM.getText());       
    }
    public  void cek(String tanggal,String jam,String nourut,String poliklinik){
       tgl=(tanggal);
       jamperiksa=(jam);
       antrian=(nourut);
       poli=(poliklinik);
       isPsien();  
    }
    
    
    public void isCek(){
        
        BtnSimpan.setEnabled(true);
        BtnHapus.setEnabled(true);
        BtnEdit.setEnabled(true);
       
        
    }
    


}

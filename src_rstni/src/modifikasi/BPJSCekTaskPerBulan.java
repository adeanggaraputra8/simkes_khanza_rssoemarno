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

import bridging.ApiBPJS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class BPJSCekTaskPerBulan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0,no=0;
    private ApiBPJS api=new ApiBPJS();
    private String URL="",link="",utc="",datajam="";
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  Date parsedDate;

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekTaskPerBulan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new String[]{"No.","Kode Faskes","Nama PPK","TaskID1","TaskID2","TaskID3","TaskID4","TaskID5","TaskID6","AVG Task1","AVG Task2","AVG Task3","AVG Task4","AVG Task5","AVG Task6","Jumlah Antrean","Poli","Kode Poli","InsertDate","Tanggal"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 20; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(40);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(75);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(75);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(75);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(75);
            }else if(i==12){
                column.setPreferredWidth(75);
            }else if(i==13){
                column.setPreferredWidth(75);
            }else if(i==14){
                column.setPreferredWidth(75);
            }else if(i==15){
                column.setPreferredWidth(75);
            }else if(i==16){
                column.setPreferredWidth(75);
            }else if(i==17){
                column.setPreferredWidth(75);
            }else if(i==18){
                column.setPreferredWidth(75);
            }else if(i==19){
                column.setPreferredWidth(75);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
//        diagnosa.setDocument(new batasInput((byte)100).getKata(diagnosa));
        
//        if(koneksiDB.CARICEPAT().equals("aktif")){
//            diagnosa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {
//                    if(diagnosa.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void removeUpdate(DocumentEvent e) {
//                    if(diagnosa.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    if(diagnosa.getText().length()>2){
//                        tampil();
//                    }
//                }
//            });
//        }   
        try {
            link=koneksiDB.URLAPIMOBILEJKN();
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        ThnCari = new widget.ComboBox();
        BlnCari = new widget.ComboBox();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Data Referensi Faskes VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ThnCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        ThnCari.setSelectedIndex(1);
        ThnCari.setName("ThnCari"); // NOI18N
        ThnCari.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(ThnCari);

        BlnCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        BlnCari.setName("BlnCari"); // NOI18N
        BlnCari.setPreferredSize(new java.awt.Dimension(62, 23));
        panelGlass6.add(BlnCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

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
        panelGlass6.add(BtnPrint);

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
        panelGlass6.add(BtnKeluar);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(50, 50, 50));
        jLabel1.setText("Data ditampilkan dalam menit");
        jLabel1.setName("jLabel1"); // NOI18N
        panelGlass6.add(jLabel1);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            Sequel.queryu("truncate table temporary");
            int row=tabMode.getRowCount();
            for(int r=0;r<row;r++){  
                Sequel.menyimpan("temporary","'0','"+
                                tabMode.getValueAt(r,0).toString()+"','"+
                                tabMode.getValueAt(r,1).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,3).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,4).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,5).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,6).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,7).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,8).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,9).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,10).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,11).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,12).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,13).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,14).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,15).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,16).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,17).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,18).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,19).toString().replaceAll("'","`")+"','','','','','','','','','','','','','','','','',''","Rekap TaskID BPJS"); 
            }
            
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            //param.put("peserta","No.Peserta : "+NoKartu.getText()+" Nama Peserta : "+NamaPasien.getText());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReport("rptRekapTaskIDBPJS.jasper","report","[ Pencarian Referensi TASKID ]",param);
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        tampil2();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
//            Valid.pindah(evt,diagnosa,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekTaskPerBulan dialog = new BPJSCekTaskPerBulan(new javax.swing.JFrame(), true);
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
    private widget.ComboBox BlnCari;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.ComboBox ThnCari;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel17;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            datajam=Sequel.cariIsi("select CURRENT_TIMESTAMP ");
            parsedDate = dateFormat.parse(datajam);
            Valid.tabelKosong(tabMode);
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIMOBILEJKN());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
	    requestEntity = new HttpEntity(headers);
            URL = link+"/dashboard/waktutunggu/bulan/"+BlnCari.getSelectedItem()+"/tahun/"+ThnCari.getSelectedItem()+"/waktu/rs";	
//            System.out.println(URL);
//            System.out.println(requestEntity);
//            System.out.println(parsedDate);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
//            System.out.println(nameNode);
            if(nameNode.path("code").asText().equals("200")){ 
//                tabMode.addRow(new Object[]{
//                    "No","Kode Faskes","Nama PPK","TaskID1","TaskID2","TaskID3","TaskID4","TaskID5","TaskID6","AVG Task1","AVG Task2","AVG Task3","AVG Task4","AVG Task5","AVG Task6","Jumlah Antrean","Poli","Kode Poli","InsertDate","Tanggal"
//                });
//                System.out.println(root.path("response"));
//                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
//                System.out.println(response);
                response = root.path("response");
                if(response.path("list").isArray()){
                    i=1;
                    for(JsonNode list:response.path("list")){
                        tabMode.addRow(new Object[]{
                            i+".",list.path("kdppk").asText(),
                            list.path("nmppk").asText(),list.path("waktu_task1").asDouble()/60,list.path("waktu_task2").asDouble()/60,list.path("waktu_task3").asDouble()/60,
                            list.path("waktu_task4").asDouble()/60,
                            list.path("waktu_task5").asDouble()/60,
                            list.path("waktu_task6").asDouble()/60,
                            list.path("avg_waktu_task1").asDouble()/60,
                            list.path("avg_waktu_task2").asDouble()/60,
                            list.path("avg_waktu_task3").asDouble()/60,
                            list.path("avg_waktu_task4").asDouble()/60,
                            list.path("avg_waktu_task5").asDouble()/60,
                            list.path("avg_waktu_task6").asDouble()/60,
                            list.path("jumlah_antrean").asText(),
                            list.path("namapoli").asText(),
                            list.path("kodepoli").asText(),
                            list.path("insertdate").asText(),
                            list.path("tanggal").asText()
                        });
                        i++;
                    }
                }
            }else {
                System.out.println("Notif Dashboard 1 : "+nameNode.path("message").asText());              
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }    
    
    public void tampil2() {        
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIMOBILEJKN());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
	    requestEntity = new HttpEntity(headers);
             URL = link+"/dashboard/waktutunggu/bulan/"+BlnCari.getSelectedItem()+"/tahun/"+ThnCari.getSelectedItem()+"/waktu/"+parsedDate.getTime()+"";		
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if(nameNode.path("message").asText().equals("Sukses")){ 
                tabMode.addRow(new Object[]{
                    "No","Kode Faskes","Nama PPK","TaskID1","TaskID2","TaskID3","TaskID4","TaskID5","TaskID6","AVG Task1","AVG Task2","AVG Task3","AVG Task4","AVG Task5","AVG Task6","Jumlah Antrean","Poli","Kode Poli","InsertDate","Tanggal"
                });
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                //response = root.path("response");
                if(response.path("dashboard").isArray()){
                    i=1;
                    for(JsonNode list:response.path("dashboard")){
                        tabMode.addRow(new Object[]{
                            i+".",list.path("kdppk").asText(),
                            list.path("nmppk").asText(),list.path("waktu_task1").asText(),list.path("waktu_task2").asText(),list.path("waktu_task3").asText(),
                            list.path("waktu_task4").asText(),
                            list.path("waktu_task5").asText(),
                            list.path("waktu_task6").asText(),
                            list.path("avg_waktu_task1").asText(),
                            list.path("avg_waktu_task2").asText(),
                            list.path("avg_waktu_task3").asText(),
                            list.path("avg_waktu_task4").asText(),
                            list.path("avg_waktu_task5").asText(),
                            list.path("avg_waktu_task6").asText(),
                            list.path("jumlah_antrean").asText(),
                            list.path("namapoli").asText(),
                            list.path("kodepoli").asText(),
                            list.path("insertdate").asText(),
                            list.path("tanggal").asText()
                        });
                        i++;
                    }
                }
            }else {
                System.out.println("Notif Dashboard 1 : "+nameNode.path("message").asText());              
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
 
    public JTable getTable(){
        return tbKamar;
    }
}

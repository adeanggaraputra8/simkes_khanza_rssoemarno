/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJnsPerawatanRalan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */

package modifikasi;
import bridging.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class DlgTaskId1 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private  sekuel Sequel=new sekuel();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;    
    private int i=0;
    private ApiMobileJKN api=new ApiMobileJKN();
    private String URL="",link="",utc="",requestJson="",datajam="",hari="",respon="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  Date parsedDate;
    private  Calendar cal = Calendar.getInstance();
    private  int day = cal.get(Calendar.DAY_OF_WEEK);

    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public DlgTaskId1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Booking","Tgl Registrasi","Kd Dokter","Kd Poli","Status Daftar",
                "No. RM","No. Rujukan","No. Kartu","No. Telp","No. KTP",
                "Task 1","Task 2","Task 3","Task 4","Task 5","Task 6","Task 7","No Reg","Jenis Kunjungan"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(110);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(100);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==18){
               column.setPreferredWidth(100);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
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
        
        try {
            link=koneksiDB.URLAPIMOBILEJKN();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        wktadmisi1.setDocument(new batasInput((byte)13).getOnlyAngka(wktadmisi1));
        wktpoli1.setDocument(new batasInput((byte)13).getOnlyAngka(wktpoli1));
        wktfarmasi1.setDocument(new batasInput((byte)13).getOnlyAngka(wktfarmasi1));
        wktadmisi2.setDocument(new batasInput((byte)13).getOnlyAngka(wktadmisi2));
        wktpoli2.setDocument(new batasInput((byte)13).getOnlyAngka(wktpoli2));
        wktfarmasi2.setDocument(new batasInput((byte)13).getOnlyAngka(wktfarmasi2));
        
        emptyteks();
        
           
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
        tbJnsPerawatan = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        addantrian = new widget.Button();
        task1 = new widget.Button();
        task2 = new widget.Button();
        task3 = new widget.Button();
        task4 = new widget.Button();
        task5 = new widget.Button();
        addantrianfrmasi = new widget.Button();
        task6 = new widget.Button();
        task7 = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel4 = new widget.Label();
        wktadmisi1 = new widget.TextBox();
        jLabel5 = new widget.Label();
        wktpoli1 = new widget.TextBox();
        jLabel8 = new widget.Label();
        wktfarmasi1 = new widget.TextBox();
        wktadmisi2 = new widget.TextBox();
        wktpoli2 = new widget.TextBox();
        wktfarmasi2 = new widget.TextBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Task ID Mobile JKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(55, 85));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-08-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnKeluar);

        jPanel1.add(panelGlass9, java.awt.BorderLayout.PAGE_START);
        panelGlass9.getAccessibleContext().setAccessibleName("");

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        addantrian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        addantrian.setMnemonic('S');
        addantrian.setText("Add Antrian");
        addantrian.setToolTipText("Alt+S");
        addantrian.setName("addantrian"); // NOI18N
        addantrian.setPreferredSize(new java.awt.Dimension(120, 30));
        addantrian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addantrianActionPerformed(evt);
            }
        });
        addantrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addantrianKeyPressed(evt);
            }
        });
        panelGlass10.add(addantrian);

        task1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task1.setMnemonic('S');
        task1.setText("Task 1");
        task1.setToolTipText("Alt+S");
        task1.setName("task1"); // NOI18N
        task1.setPreferredSize(new java.awt.Dimension(85, 30));
        task1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task1ActionPerformed(evt);
            }
        });
        task1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task1KeyPressed(evt);
            }
        });
        panelGlass10.add(task1);

        task2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task2.setMnemonic('S');
        task2.setText("Task 2");
        task2.setToolTipText("Alt+S");
        task2.setName("task2"); // NOI18N
        task2.setPreferredSize(new java.awt.Dimension(85, 30));
        task2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task2ActionPerformed(evt);
            }
        });
        task2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task2KeyPressed(evt);
            }
        });
        panelGlass10.add(task2);

        task3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task3.setMnemonic('S');
        task3.setText("Task 3");
        task3.setToolTipText("Alt+S");
        task3.setName("task3"); // NOI18N
        task3.setPreferredSize(new java.awt.Dimension(85, 30));
        task3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task3ActionPerformed(evt);
            }
        });
        task3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task3KeyPressed(evt);
            }
        });
        panelGlass10.add(task3);

        task4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task4.setMnemonic('S');
        task4.setText("Task 4");
        task4.setToolTipText("Alt+S");
        task4.setName("task4"); // NOI18N
        task4.setPreferredSize(new java.awt.Dimension(85, 30));
        task4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task4ActionPerformed(evt);
            }
        });
        task4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task4KeyPressed(evt);
            }
        });
        panelGlass10.add(task4);

        task5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task5.setMnemonic('S');
        task5.setText("Task 5");
        task5.setToolTipText("Alt+S");
        task5.setName("task5"); // NOI18N
        task5.setPreferredSize(new java.awt.Dimension(85, 30));
        task5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task5ActionPerformed(evt);
            }
        });
        task5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task5KeyPressed(evt);
            }
        });
        panelGlass10.add(task5);

        addantrianfrmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        addantrianfrmasi.setMnemonic('S');
        addantrianfrmasi.setText("Add Antrian Farmasi");
        addantrianfrmasi.setToolTipText("Alt+S");
        addantrianfrmasi.setName("addantrianfrmasi"); // NOI18N
        addantrianfrmasi.setPreferredSize(new java.awt.Dimension(170, 30));
        addantrianfrmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addantrianfrmasiActionPerformed(evt);
            }
        });
        addantrianfrmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addantrianfrmasiKeyPressed(evt);
            }
        });
        panelGlass10.add(addantrianfrmasi);

        task6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task6.setMnemonic('S');
        task6.setText("Task 6");
        task6.setToolTipText("Alt+S");
        task6.setName("task6"); // NOI18N
        task6.setPreferredSize(new java.awt.Dimension(85, 30));
        task6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task6ActionPerformed(evt);
            }
        });
        task6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task6KeyPressed(evt);
            }
        });
        panelGlass10.add(task6);

        task7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        task7.setMnemonic('S');
        task7.setText("Task 7");
        task7.setToolTipText("Alt+S");
        task7.setName("task7"); // NOI18N
        task7.setPreferredSize(new java.awt.Dimension(85, 30));
        task7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                task7ActionPerformed(evt);
            }
        });
        task7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                task7KeyPressed(evt);
            }
        });
        panelGlass10.add(task7);

        jPanel1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass7.setLayout(null);

        jLabel4.setText("Waktu Admisi :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(0, 12, 90, 23);

        wktadmisi1.setName("wktadmisi1"); // NOI18N
        wktadmisi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktadmisi1KeyPressed(evt);
            }
        });
        panelGlass7.add(wktadmisi1);
        wktadmisi1.setBounds(95, 12, 50, 23);

        jLabel5.setText("Waktu Poli :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(230, 10, 90, 23);

        wktpoli1.setName("wktpoli1"); // NOI18N
        wktpoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktpoli1KeyPressed(evt);
            }
        });
        panelGlass7.add(wktpoli1);
        wktpoli1.setBounds(330, 10, 50, 23);

        jLabel8.setText("Waktu Farmasi :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(530, 10, 90, 23);

        wktfarmasi1.setName("wktfarmasi1"); // NOI18N
        wktfarmasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktfarmasi1KeyPressed(evt);
            }
        });
        panelGlass7.add(wktfarmasi1);
        wktfarmasi1.setBounds(620, 10, 50, 23);

        wktadmisi2.setName("wktadmisi2"); // NOI18N
        wktadmisi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktadmisi2KeyPressed(evt);
            }
        });
        panelGlass7.add(wktadmisi2);
        wktadmisi2.setBounds(150, 12, 50, 23);

        wktpoli2.setName("wktpoli2"); // NOI18N
        wktpoli2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktpoli2KeyPressed(evt);
            }
        });
        panelGlass7.add(wktpoli2);
        wktpoli2.setBounds(390, 10, 50, 23);

        wktfarmasi2.setName("wktfarmasi2"); // NOI18N
        wktfarmasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wktfarmasi2KeyPressed(evt);
            }
        });
        panelGlass7.add(wktfarmasi2);
        wktfarmasi2.setBounds(680, 10, 50, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,TCari);}
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
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void addantrianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addantrianActionPerformed

        
        for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
                 day=cal.get(Calendar.DAY_OF_WEEK);
                    switch (day) {
                        case 1:
                            hari="AKHAD";
                            break;
                        case 2:
                            hari="SENIN";
                            break;
                        case 3:
                            hari="SELASA";
                            break;
                        case 4:
                            hari="RABU";
                            break;
                        case 5:
                            hari="KAMIS";
                            break;
                        case 6:
                            hari="JUMAT";
                            break;
                        case 7:
                            hari="SABTU";
                            break;
                        default:
                            break;
                    }
                
            ps2=koneksi.prepareStatement("select * from jadwal where hari_kerja=? and kd_dokter=? and kd_poli=?");
             try {
                 ps2.setString(1,hari);
                 ps2.setString(2,tbJnsPerawatan.getValueAt(i,2).toString());
                 ps2.setString(3,tbJnsPerawatan.getValueAt(i,3).toString());
                 rs2=ps2.executeQuery();
                 if(rs2.next()){
                try {  
                     datajam=Sequel.cariIsi("select DATE_ADD(concat('"+tbJnsPerawatan.getValueAt(i,1).toString()+"',' ','"+rs2.getString("jam_mulai")+"'),INTERVAL "+(Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString())*10)+" MINUTE) ");
                     parsedDate = dateFormat.parse(datajam);
                     headers = new HttpHeaders();
                     headers.setContentType(MediaType.APPLICATION_JSON);
                     headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                     utc=String.valueOf(api.GetUTCdatetimeAsString());
                     headers.add("x-timestamp",utc);
                     headers.add("x-signature",api.getHmac(utc));
                     headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                     requestJson ="{" +
                                     "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                     "\"jenispasien\": \"JKN\"," +
                                     "\"nomorkartu\": \""+tbJnsPerawatan.getValueAt(i,7).toString()+"\"," +
                                     "\"nik\": \""+tbJnsPerawatan.getValueAt(i,9).toString()+"\"," +
                                     "\"nohp\": \""+tbJnsPerawatan.getValueAt(i,8).toString()+"\"," +
                                     "\"kodepoli\": \""+Sequel.cariIsi("select maping_poli_bpjs.kd_poli_bpjs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_rs=?",tbJnsPerawatan.getValueAt(i,3).toString())+"\"," +
                                     "\"namapoli\": \""+Sequel.cariIsi("select maping_poli_bpjs.nm_poli_bpjs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_rs=?",tbJnsPerawatan.getValueAt(i,3).toString())+"\"," +
                                     "\"pasienbaru\": "+tbJnsPerawatan.getValueAt(i,4).toString().replaceAll("Baru","1").replaceAll("Lama","0").replaceAll("-","0")+"," +
                                     "\"norm\": \""+tbJnsPerawatan.getValueAt(i,5).toString()+"\"," +
                                     "\"tanggalperiksa\": \""+tbJnsPerawatan.getValueAt(i,1).toString()+"\"," +
                                     "\"kodedokter\": "+Sequel.cariIsi("select maping_dokter_dpjpvclaim.kd_dokter_bpjs from maping_dokter_dpjpvclaim  where maping_dokter_dpjpvclaim.kd_dokter=?",tbJnsPerawatan.getValueAt(i,2).toString())+"," +
                                     "\"namadokter\": \""+Sequel.cariIsi("select maping_dokter_dpjpvclaim.nm_dokter_bpjs from maping_dokter_dpjpvclaim  where maping_dokter_dpjpvclaim.kd_dokter=?",tbJnsPerawatan.getValueAt(i,2).toString())+"\"," +
                                     "\"jampraktek\": \""+rs2.getString("jam_mulai").substring(0,5)+"-"+rs2.getString("jam_selesai").substring(0,5)+"\"," +
                                     "\"jeniskunjungan\": "+tbJnsPerawatan.getValueAt(i,18).toString()+"," +
                                     "\"nomorreferensi\": \""+tbJnsPerawatan.getValueAt(i,6).toString()+"\"," +
                                     "\"nomorantrean\": \""+tbJnsPerawatan.getValueAt(i,3).toString()+"-"+tbJnsPerawatan.getValueAt(i,17).toString()+"\"," +
                                     "\"angkaantrean\": "+Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString())+"," +
                                     "\"estimasidilayani\": "+parsedDate.getTime()+"," +
                                     "\"sisakuotajkn\": "+(rs2.getInt("kuota")-Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString()))+"," +
                                     "\"kuotajkn\": "+rs2.getString("kuota")+"," +
                                     "\"sisakuotanonjkn\": "+(rs2.getInt("kuota")-Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString()))+"," +
                                     "\"kuotanonjkn\": "+rs2.getString("kuota")+"," +
                                     "\"keterangan\": \"Peserta harap 30 menit lebih awal guna pencatatan administrasi.\"" +
                                 "}";
                     requestEntity = new HttpEntity(requestJson,headers);
                     URL = link+"/antrean/add";	
                     System.out.println("URL : "+URL);
                     //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                     root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                     nameNode = root.path("metadata"); 
                     respon=nameNode.path("code").asText();
                     System.out.println("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                 }catch (Exception ex) {
                     System.out.println("Notifikasi Bridging : "+ex);
                 }
                }
               } catch (Exception ex) {
                    System.out.println("Notif : "+ex);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
            
        Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal2=tanggal1 + INTERVAL "+wktadmisi1.getText()+" MINUTE ");
       } 
    }//GEN-LAST:event_addantrianActionPerformed

    private void addantrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addantrianKeyPressed

    }//GEN-LAST:event_addantrianKeyPressed

    private void task1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task1ActionPerformed
        for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal1  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal1 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"1\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                             System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }
                    
                }
                Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal3=tanggal2 + INTERVAL "+wktadmisi2.getText()+" MINUTE ");
                
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        
        
        }
    }//GEN-LAST:event_task1ActionPerformed

    private void task1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task1KeyPressed

    private void task2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task2ActionPerformed
        for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal2  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal2 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"2\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                             System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }
                   Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal4=tanggal3 + INTERVAL "+wktpoli1.getText()+" MINUTE ");
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
 
        
        }
    }//GEN-LAST:event_task2ActionPerformed

    private void task2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task2KeyPressed

    private void task3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task3ActionPerformed
       for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal3  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal3 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"3\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                             System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }
                   Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal5=tanggal4 + INTERVAL "+wktpoli2.getText()+" MINUTE ");
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
       
        
        }
    }//GEN-LAST:event_task3ActionPerformed

    private void task3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task3KeyPressed

    private void task4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task4ActionPerformed
         for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal4  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal4 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"4\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                             System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }
                  Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal6=tanggal5 + INTERVAL "+wktfarmasi1.getText()+" MINUTE ");
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
       
        
        }
    }//GEN-LAST:event_task4ActionPerformed

    private void task4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task4KeyPressed

    private void task5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task5ActionPerformed
      for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal5  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal5 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"5\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"," +
                                             "\"jenisresep\": \"Non Racikan\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                            System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }
                  Sequel.mengedit("antrian_bantu","no_rawat='"+tbJnsPerawatan.getValueAt(i,0).toString()+"'","status='Sudah Valid',tanggal7=tanggal6 + INTERVAL "+wktfarmasi2.getText()+" MINUTE ");    
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
      
        
       }
    }//GEN-LAST:event_task5ActionPerformed

    private void task5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task5KeyPressed

    private void task6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task6ActionPerformed
       for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal6  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal6 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"6\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                            System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }   
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
      
        
       }
    }//GEN-LAST:event_task6ActionPerformed

    private void task6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task6KeyPressed

    private void task7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_task7ActionPerformed
        for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
             datajam=Sequel.cariIsi("SELECT tanggal7  FROM  antrian_bantu ab  WHERE  no_rawat=? AND  tanggal7 <>'0000-00-00 00:00:00'",tbJnsPerawatan.getValueAt(i,0).toString());
                if(!datajam.equals("")){
                        parsedDate = dateFormat.parse(datajam);
                        try {     
                            headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                            headers.add("x-timestamp",utc);
                            headers.add("x-signature",api.getHmac(utc));
                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                            requestJson ="{" +
                                             "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                             "\"taskid\": \"7\"," +
                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                          "}";
                            requestEntity = new HttpEntity(requestJson,headers);
                            URL = link+"/antrean/updatewaktu";	
                            System.out.println("URL : "+URL);
                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                            nameNode = root.path("metadata");
                            System.out.println("code : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                        }   
                }
             }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
      
        
       }
    }//GEN-LAST:event_task7ActionPerformed

    private void task7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_task7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_task7KeyPressed

    private void wktadmisi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktadmisi1KeyPressed

    }//GEN-LAST:event_wktadmisi1KeyPressed

    private void wktpoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktpoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wktpoli1KeyPressed

    private void wktfarmasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktfarmasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wktfarmasi1KeyPressed

    private void addantrianfrmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addantrianfrmasiActionPerformed
       for(i=0;i<tbJnsPerawatan.getRowCount();i++){
            try {
            ps2=koneksi.prepareStatement("select * from jadwal where hari_kerja=? and kd_dokter=? and kd_poli=?");
             try {
                 ps2.setString(1,hari);
                 ps2.setString(2,tbJnsPerawatan.getValueAt(i,2).toString());
                 ps2.setString(3,tbJnsPerawatan.getValueAt(i,3).toString());
                 rs2=ps2.executeQuery();
                 if(rs2.next()){
                try {  
                     datajam=Sequel.cariIsi("select DATE_ADD(concat('"+tbJnsPerawatan.getValueAt(i,1).toString()+"',' ','"+rs2.getString("jam_mulai")+"'),INTERVAL "+(Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString())*10)+" MINUTE) ");
                     parsedDate = dateFormat.parse(datajam);
                     headers = new HttpHeaders();
                     headers.setContentType(MediaType.APPLICATION_JSON);
                     headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                     utc=String.valueOf(api.GetUTCdatetimeAsString());
                     headers.add("x-timestamp",utc);
                     headers.add("x-signature",api.getHmac(utc));
                     headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                     requestJson ="{" +
                                     "\"kodebooking\": \""+tbJnsPerawatan.getValueAt(i,0).toString()+"\"," +
                                     "\"jensiresep\": \"non racikan\"," +
                                     "\"nomorantrean\": "+Integer.parseInt(tbJnsPerawatan.getValueAt(i,17).toString())+"," +
                                     "\"keterangan\": \"-\"" +
                                 "}";
                     requestEntity = new HttpEntity(requestJson,headers);
                     URL = link+"/antrean/farmasi/add";	
                     System.out.println("URL : "+URL);
                     //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                     root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                     nameNode = root.path("metadata");  
                 }catch (Exception ex) {
                     System.out.println("Notifikasi Bridging : "+ex);
                 }
                }
               } catch (Exception ex) {
                    System.out.println("Notif : "+ex);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
       } 
    }//GEN-LAST:event_addantrianfrmasiActionPerformed

    private void addantrianfrmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addantrianfrmasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_addantrianfrmasiKeyPressed

    private void wktadmisi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktadmisi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wktadmisi2KeyPressed

    private void wktpoli2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktpoli2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wktpoli2KeyPressed

    private void wktfarmasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wktfarmasi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wktfarmasi2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgTaskId dialog = new DlgTaskId(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Button addantrian;
    private widget.Button addantrianfrmasi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass9;
    private widget.Button task1;
    private widget.Button task2;
    private widget.Button task3;
    private widget.Button task4;
    private widget.Button task5;
    private widget.Button task6;
    private widget.Button task7;
    private widget.Table tbJnsPerawatan;
    private widget.TextBox wktadmisi1;
    private widget.TextBox wktadmisi2;
    private widget.TextBox wktfarmasi1;
    private widget.TextBox wktfarmasi2;
    private widget.TextBox wktpoli1;
    private widget.TextBox wktpoli2;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                   " select antrian_bantu.no_rawat,reg_periksa.no_reg,reg_periksa.tgl_registrasi,reg_periksa.kd_dokter,reg_periksa.kd_poli,reg_periksa.stts_daftar,reg_periksa.no_rkm_medis,if(bridging_sep.tujuankunjungan='0',bridging_sep.no_rujukan,IF(bridging_sep.tujuankunjungan='2',bridging_sep.noskdp,'')) as no_rujukan,bridging_sep.no_kartu,bridging_sep.notelep,pasien.no_ktp,antrian_bantu.tanggal1,antrian_bantu.tanggal2,antrian_bantu.tanggal3,antrian_bantu.tanggal4,antrian_bantu.tanggal5,antrian_bantu.tanggal6,antrian_bantu.tanggal7,if(bridging_sep.tujuankunjungan='0','1',IF(bridging_sep.tujuankunjungan='2','3','')) as jeniskunjungan " +
                   " from reg_periksa inner JOIN  antrian_bantu on antrian_bantu.no_rawat=reg_periksa.no_rawat INNER  JOIN  bridging_sep ON bridging_sep.no_sep = antrian_bantu.no_sep INNER JOIN pasien on pasien.no_rkm_medis =reg_periksa.no_rkm_medis  where " +
                   " reg_periksa.no_rawat not in (select referensi_mobilejkn_bpjs.no_rawat from referensi_mobilejkn_bpjs) and reg_periksa.kd_poli not in ('IRM','GIG','HDL','IGDK','LAB') "+
                   " and reg_periksa.tgl_registrasi BETWEEN ? AND ? "+(TCari.getText().equals("")?"":
                   " and antrian_bantu.kd_booking LIKE ? OR reg_periksa.no_rkm_medis LIKE ? ") +
                   " order by concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg)");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),rs.getString("tgl_registrasi"),rs.getString("kd_dokter"),
                        rs.getString("kd_poli"),rs.getString("stts_daftar"),rs.getString("no_rkm_medis"),
                        rs.getString("no_rujukan"),rs.getString("no_kartu"),rs.getString("notelep"),
                        rs.getString("no_ktp"),rs.getString("tanggal1"),rs.getString("tanggal2"),
                        rs.getString("tanggal3"),rs.getString("tanggal4"),rs.getString("tanggal5"),
                        rs.getString("tanggal6"),rs.getString("tanggal7"),rs.getString("no_reg"),rs.getString("jeniskunjungan")
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
    
    public void emptyteks(){
        wktadmisi1.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(1-15+1))+15"));
        wktadmisi2.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(2-8+1))+8"));
        wktpoli1.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(15-30+1))+30"));
        wktpoli2.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(1-15+1))+15"));
        wktfarmasi1.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(5-30+1))+30"));
        wktfarmasi2.setText(Sequel.cariIsi("SELECT FLOOR(RAND()*(1-5+1))+5"));
    
    }
}

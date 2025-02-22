/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJnsPerawatanRalan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */

package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgAnggotaMiliterDirawat;
import modifikasi.AntrolPerTanggal;
import modifikasi.BPJSCekTaskPerBulan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import modifikasi.DlgTaskId;
import modifikasi.DlgTaskId1;
import modifikasi.DlgTaskIdNonResep;
import modifikasi.DlgTaskId_belumm;

/**
 *
 * @author dosen
 */
public final class BPJSTaskIDMobileJKN extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;    
    private int i=0;
    private ApiMobileJKN api=new ApiMobileJKN();
    private String URL="",link="",utc="",requestJson="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;

    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public BPJSTaskIDMobileJKN(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Booking","No.RM","Nama Pasien","No.HP","No.Kartu","NIK","Tanggal","Poliklinik","Dokter","Jam Praktek","Jenis Kunjungan","Nomor Referensi","Waktu RS","Waktu","Task Name","Task ID"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(110);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(83);
            }else if(i==4){
                column.setPreferredWidth(90);
            }else if(i==5){
                column.setPreferredWidth(103);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(140);
            }else if(i==8){
                column.setPreferredWidth(160);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(110);
            }else if(i==11){
                column.setPreferredWidth(125);
            }else if(i==12){
                column.setPreferredWidth(115);
            }else if(i==13){
                column.setPreferredWidth(115);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(40);
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
        MnTaskid = new javax.swing.JMenuItem();
        MnTaskid1 = new javax.swing.JMenuItem();
        MnTaskidNonResep = new javax.swing.JMenuItem();
        MnTaskidBelumDilayani = new javax.swing.JMenuItem();
        MnAntrolBpjsPertanggal = new javax.swing.JMenuItem();
        MnDashboardAntrolBpjs = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
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

        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnTaskid.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTaskid.setForeground(new java.awt.Color(70, 70, 70));
        MnTaskid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTaskid.setText("Menu Task ID MJKN");
        MnTaskid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTaskid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTaskid.setIconTextGap(5);
        MnTaskid.setName("MnTaskid"); // NOI18N
        MnTaskid.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTaskid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTaskidActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTaskid);

        MnTaskid1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTaskid1.setForeground(new java.awt.Color(70, 70, 70));
        MnTaskid1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTaskid1.setText("Menu Task ID Resep");
        MnTaskid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTaskid1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTaskid1.setIconTextGap(5);
        MnTaskid1.setName("MnTaskid1"); // NOI18N
        MnTaskid1.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTaskid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTaskid1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTaskid1);

        MnTaskidNonResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTaskidNonResep.setForeground(new java.awt.Color(70, 70, 70));
        MnTaskidNonResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTaskidNonResep.setText(" Menu Task Id Non Resep");
        MnTaskidNonResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTaskidNonResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTaskidNonResep.setIconTextGap(5);
        MnTaskidNonResep.setName("MnTaskidNonResep"); // NOI18N
        MnTaskidNonResep.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTaskidNonResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTaskidNonResepActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTaskidNonResep);

        MnTaskidBelumDilayani.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTaskidBelumDilayani.setForeground(new java.awt.Color(70, 70, 70));
        MnTaskidBelumDilayani.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTaskidBelumDilayani.setText("Menu Task ID Belum Dilayani");
        MnTaskidBelumDilayani.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTaskidBelumDilayani.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTaskidBelumDilayani.setIconTextGap(5);
        MnTaskidBelumDilayani.setName("MnTaskidBelumDilayani"); // NOI18N
        MnTaskidBelumDilayani.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTaskidBelumDilayani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTaskidBelumDilayaniActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTaskidBelumDilayani);

        MnAntrolBpjsPertanggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAntrolBpjsPertanggal.setForeground(new java.awt.Color(70, 70, 70));
        MnAntrolBpjsPertanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAntrolBpjsPertanggal.setText(" Menu Antrol BPJS Pertanggal");
        MnAntrolBpjsPertanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAntrolBpjsPertanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAntrolBpjsPertanggal.setIconTextGap(5);
        MnAntrolBpjsPertanggal.setName("MnAntrolBpjsPertanggal"); // NOI18N
        MnAntrolBpjsPertanggal.setPreferredSize(new java.awt.Dimension(240, 26));
        MnAntrolBpjsPertanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAntrolBpjsPertanggalActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnAntrolBpjsPertanggal);

        MnDashboardAntrolBpjs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDashboardAntrolBpjs.setForeground(new java.awt.Color(70, 70, 70));
        MnDashboardAntrolBpjs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDashboardAntrolBpjs.setText(" Menu Dashborad Antrol BPJS PerBulan");
        MnDashboardAntrolBpjs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDashboardAntrolBpjs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDashboardAntrolBpjs.setIconTextGap(5);
        MnDashboardAntrolBpjs.setName("MnDashboardAntrolBpjs"); // NOI18N
        MnDashboardAntrolBpjs.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDashboardAntrolBpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDashboardAntrolBpjsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDashboardAntrolBpjs);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Task ID Mobile JKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setComponentPopupMenu(jPopupMenu1);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setComponentPopupMenu(jPopupMenu1);
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-07-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-07-2023" }));
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

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

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

    private void MnTaskidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTaskidActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        DlgTaskId sep=new DlgTaskId(null,false);
        sep.emptyteks();
        sep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep.setLocationRelativeTo(internalFrame1);
        sep.setVisible(true);
    }//GEN-LAST:event_MnTaskidActionPerformed

    private void MnTaskidNonResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTaskidNonResepActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        DlgTaskIdNonResep sep1=new DlgTaskIdNonResep(null,false);
        sep1.emptyteks();
        sep1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep1.setLocationRelativeTo(internalFrame1);
        sep1.setVisible(true);
    }//GEN-LAST:event_MnTaskidNonResepActionPerformed

    private void MnAntrolBpjsPertanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAntrolBpjsPertanggalActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        AntrolPerTanggal sep2=new AntrolPerTanggal(null,false);
        sep2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep2.setLocationRelativeTo(internalFrame1);
        sep2.setVisible(true);
    }//GEN-LAST:event_MnAntrolBpjsPertanggalActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void MnDashboardAntrolBpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDashboardAntrolBpjsActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        BPJSCekTaskPerBulan sep2=new BPJSCekTaskPerBulan(null,false);
        sep2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep2.setLocationRelativeTo(internalFrame1);
        sep2.setVisible(true);
    }//GEN-LAST:event_MnDashboardAntrolBpjsActionPerformed

    private void MnTaskid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTaskid1ActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        DlgTaskId1 sep=new DlgTaskId1(null,false);
        sep.emptyteks();
        sep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep.setLocationRelativeTo(internalFrame1);
        sep.setVisible(true);
    }//GEN-LAST:event_MnTaskid1ActionPerformed

    private void MnTaskidBelumDilayaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTaskidBelumDilayaniActionPerformed
        akses.setform("BPJSTaskIDMobileJKN");
        DlgTaskId_belumm sep=new DlgTaskId_belumm(null,false);
        sep.emptyteks();
        sep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep.setLocationRelativeTo(internalFrame1);
        sep.setVisible(true);
    }//GEN-LAST:event_MnTaskidBelumDilayaniActionPerformed

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
    private javax.swing.JMenuItem MnAntrolBpjsPertanggal;
    private javax.swing.JMenuItem MnDashboardAntrolBpjs;
    private javax.swing.JMenuItem MnTaskid;
    private javax.swing.JMenuItem MnTaskid1;
    private javax.swing.JMenuItem MnTaskidBelumDilayani;
    private javax.swing.JMenuItem MnTaskidNonResep;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass9;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                   "SELECT referensi_mobilejkn_bpjs.nobooking,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,"+
                   "referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,"+
                   "referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi "+
                   "FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat "+
                   "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter "+
                   "WHERE referensi_mobilejkn_bpjs.tanggalperiksa BETWEEN ? AND ? "+(TCari.getText().equals("")?"":
                   "and (referensi_mobilejkn_bpjs.nobooking LIKE ? OR reg_periksa.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? OR "+
                   "referensi_mobilejkn_bpjs.nohp LIKE ? OR referensi_mobilejkn_bpjs.nomorkartu LIKE ? OR referensi_mobilejkn_bpjs.nik LIKE ? OR "+
                   "poliklinik.nm_poli LIKE ? OR dokter.nm_dokter LIKE ? OR referensi_mobilejkn_bpjs.jeniskunjungan LIKE ? OR "+
                   "referensi_mobilejkn_bpjs.nomorreferensi LIKE ? OR referensi_mobilejkn_bpjs.status LIKE ?) ")+
                   "order by referensi_mobilejkn_bpjs.tanggalperiksa");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                    ps.setString(10,"%"+TCari.getText()+"%");
                    ps.setString(11,"%"+TCari.getText()+"%");
                    ps.setString(12,"%"+TCari.getText()+"%");
                    ps.setString(13,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                        utc=String.valueOf(api.GetUTCdatetimeAsString());
                        headers.add("x-timestamp",utc);
                        headers.add("x-signature",api.getHmac(utc));
                        headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                        requestJson ="{" +
                                        "\"kodebooking\": \""+rs.getString("nobooking")+"\"" +
                                     "}";
                        requestEntity = new HttpEntity(requestJson,headers);
                        URL = link+"/antrean/getlisttask";	
                        System.out.println("URL : "+URL);
                        System.out.println("JSON : "+requestJson);
                        root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                        nameNode = root.path("metadata");
                        if(nameNode.path("code").asText().equals("200")){
                            Valid.tabelKosong(tabMode);
                            response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                            if(response.isArray()){
                                for(JsonNode list:response){
                                    tabMode.addRow(new Object[]{
                                        rs.getString("nobooking"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                                        rs.getString("nohp"),rs.getString("nomorkartu"),rs.getString("nik"),
                                        rs.getString("tanggalperiksa"),rs.getString("nm_poli"),rs.getString("nm_dokter"),
                                        rs.getString("jampraktek"),rs.getString("jeniskunjungan"),rs.getString("nomorreferensi"),
                                        list.path("wakturs").asText(),list.path("waktu").asText(),list.path("taskname").asText(),
                                        list.path("taskid").asText()
                                    });
                                }
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
                        }   
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : "+ex);
                        if(ex.toString().contains("UnknownHostException")){
                            JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
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
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
}

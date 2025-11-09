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

import bridging.ApiTTE;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



/**
 *
 * @author perpustakaan
 */
public class DlgTTEFile extends javax.swing.JDialog {
    private  ApiTTE apitte=new ApiTTE();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private final DefaultTableModel tabMode;
    private String data="",nomorrawat="",norkmmedis="";
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection koneksi=koneksiDB.condb();
    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgTTEFile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(930,800);
        Object[] row={"Kode Berkas","Jenis Berkas"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar1.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbKamar1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKamar1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(60);
            }else if(i==1){
                column.setPreferredWidth(100);
            }
        }
        tbKamar1.setDefaultRenderer(Object.class, new WarnaTable());
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
         MasterBerkas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(tbKamar1.getSelectedRow()!= -1){
                    kdberkas.setText(tbKamar1.getValueAt(tbKamar1.getSelectedRow(),0).toString());
                    TNmBerkas.setText(tbKamar1.getValueAt(tbKamar1.getSelectedRow(),1).toString());
                    kdberkas.requestFocus();
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
         
         MasterBerkas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                       dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
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

        MasterBerkas = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbKamar1 = new widget.Table();
        panelisi5 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel60 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        jLabel61 = new widget.Label();
        Tpaspras = new widget.PasswordBox();
        jLabel62 = new widget.Label();
        kdberkas = new widget.TextBox();
        TNmBerkas = new widget.TextBox();
        BtnMasterBerkas = new widget.Button();
        panelisi4 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();

        MasterBerkas.setName("MasterBerkas"); // NOI18N

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Unit/Poliklinik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKamar1.setAutoCreateRowSorter(true);
        tbKamar1.setName("tbKamar1"); // NOI18N
        tbKamar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamar1MouseClicked(evt);
            }
        });
        tbKamar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKamar1KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbKamar1);

        internalFrame2.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi5.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi5.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelisi5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("2Alt+2");
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
        panelisi5.add(BtnAll);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi5.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi5.add(BtnKeluar);

        internalFrame2.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        MasterBerkas.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bubuhkan Tanda Tangan Elektronik (TTE) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 100));
        panelisi3.setLayout(null);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Nama DPJP : ");
        jLabel60.setName("jLabel60"); // NOI18N
        panelisi3.add(jLabel60);
        jLabel60.setBounds(0, 10, 100, 23);

        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        panelisi3.add(kddokter);
        kddokter.setBounds(100, 10, 140, 23);

        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        panelisi3.add(TDokter);
        TDokter.setBounds(244, 10, 220, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Passphrase : ");
        jLabel61.setName("jLabel61"); // NOI18N
        panelisi3.add(jLabel61);
        jLabel61.setBounds(0, 38, 100, 23);

        Tpaspras.setForeground(new java.awt.Color(0, 0, 0));
        Tpaspras.setToolTipText("Silahkan masukkan Passphrase");
        Tpaspras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tpaspras.setName("Tpaspras"); // NOI18N
        panelisi3.add(Tpaspras);
        Tpaspras.setBounds(100, 38, 364, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Jenis Berkas : ");
        jLabel62.setName("jLabel62"); // NOI18N
        panelisi3.add(jLabel62);
        jLabel62.setBounds(0, 70, 100, 23);

        kdberkas.setForeground(new java.awt.Color(0, 0, 0));
        kdberkas.setName("kdberkas"); // NOI18N
        panelisi3.add(kdberkas);
        kdberkas.setBounds(100, 70, 90, 23);

        TNmBerkas.setForeground(new java.awt.Color(0, 0, 0));
        TNmBerkas.setName("TNmBerkas"); // NOI18N
        panelisi3.add(TNmBerkas);
        TNmBerkas.setBounds(193, 70, 230, 23);

        BtnMasterBerkas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnMasterBerkas.setMnemonic('2');
        BtnMasterBerkas.setToolTipText("Alt+2");
        BtnMasterBerkas.setName("BtnMasterBerkas"); // NOI18N
        BtnMasterBerkas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnMasterBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasterBerkasActionPerformed(evt);
            }
        });
        panelisi3.add(BtnMasterBerkas);
        BtnMasterBerkas.setBounds(430, 70, 28, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Export.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Submit");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnSimpan1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCloseIn1);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
       if(kdberkas.getText().trim().equals("")||TNmBerkas.getText().trim().equals("")){
            Valid.textKosong(kdberkas,"Kode Berkas");
        }else if(kddokter.getText().trim().equals("")||Tpaspras.getText().trim().equals("")){
            Valid.textKosong(kddokter,"Dokter");
        }else{
            setFile();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbKamar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamar1MouseClicked
        if(tabMode.getRowCount()!=0){
            if(evt.getClickCount()==2){
                MasterBerkas.dispose();
            }
        }
    }//GEN-LAST:event_tbKamar1MouseClicked

    private void tbKamar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamar1KeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                MasterBerkas.dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_tbKamar1KeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbKamar1.requestFocus();
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
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        MasterBerkas.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnMasterBerkasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasterBerkasActionPerformed
         tampil();
         MasterBerkas.setSize(620, 300);
         MasterBerkas.setLocationRelativeTo(internalFrame3);
         MasterBerkas.setVisible(true);
    }//GEN-LAST:event_BtnMasterBerkasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgTTEFile dialog = new DlgTTEFile(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn1;
    private widget.Button BtnKeluar;
    private widget.Button BtnMasterBerkas;
    private widget.Button BtnSimpan1;
    private widget.Label LCount;
    private javax.swing.JDialog MasterBerkas;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TDokter;
    private widget.TextBox TNmBerkas;
    private widget.PasswordBox Tpaspras;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.TextBox kdberkas;
    private widget.TextBox kddokter;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.Table tbKamar1;
    // End of variables declaration//GEN-END:variables
   
    private void tampil() {
          Valid.tabelKosong(tabMode);
          try{
              ps=koneksi.prepareStatement("SELECT * FROM master_berkas_digital where  kode like ? or nama like ? order by nama");
              try {
                  ps.setString(1,"%"+TCari.getText().trim()+"%");
                  ps.setString(2,"%"+TCari.getText().trim()+"%");
                  rs=ps.executeQuery();
                  while(rs.next()){
                      tabMode.addRow(new String[]{
                          rs.getString(1),rs.getString(2)
                      });
                  }
              } catch (Exception e) {
                  System.out.println(e);
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
    
    
    public void isCek(String basecodepdf,String Norm, String NoRawat, String kodekter) {
          kddokter.setText(Sequel.cariIsi("select no_ktp from pegawai where nik = '" + kodekter + "'"));
          TDokter.setText(Sequel.cariIsi("select nama from pegawai where nik = '" + kodekter + "'"));
          data=basecodepdf;
          nomorrawat=NoRawat;
          norkmmedis=Norm;
    }

    public void setFile() {
            try {
                byte[] input_file = Files.readAllBytes(Paths.get(data));
                byte[] encodedBytes = Base64.getEncoder().encode(input_file);
                String encodedString = new String(encodedBytes);

                apitte.unggahKeServer(data, nomorrawat.replaceAll("/", "") + ".pdf");

                apitte.mengunggahFile(nomorrawat.replaceAll("/", "") + ".pdf", encodedString, nomorrawat, kddokter.getText(), kdberkas.getText(), Tpaspras.getText(), Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat = '" + nomorrawat + "'"), akses.getkode(), norkmmedis);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                this.setCursor(Cursor.getDefaultCursor());
                dispose();        
            }

    public JTable getTable(){
        return tbKamar1;
    }
}

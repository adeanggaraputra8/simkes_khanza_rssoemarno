package inventory;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgDaruratStok extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,psstok;
    private ResultSet rs,rsstok;
    private double stok=0;
    private String aktifkanbatch="no";
    private DlgCariPemesanan form=new DlgCariPemesanan(null,false);
    private InventorySuratPemesanan aplikasi=new InventorySuratPemesanan (null,false);
    private int z=0,i=0;
    private boolean[] pilih; 
    private double[] stokminimal,stokk;
    private String[] kodebarang,namabarang,satuan,jenis;

    /** 
     * @param parent
     * @param modal */
    public DlgDaruratStok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"P","Kode Barang", "Nama Barang", "Satuan", "Jenis","Minimal","Saat Ini"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                 boolean a = false;
                 if (colIndex==0) {
                     a=true;
                 }
                 return a;
              }
              Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Double.class, java.lang.Double.class
              };
              @Override
              public Class getColumnClass(int columnIndex) {
                 return types [columnIndex];
              }
         };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else{
                column.setPreferredWidth(80);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());         
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
            });
        }   
        
            form.suplier.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemesanan")){
                    if(form.suplier.getTable().getSelectedRow()!= -1){                   
                        kdsup.setText(form.suplier.getTable().getValueAt(form.suplier.getTable().getSelectedRow(),0).toString());                    
                        nmsup.setText(form.suplier.getTable().getValueAt(form.suplier.getTable().getSelectedRow(),1).toString());
                    } 
                    kdsup.requestFocus();
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
        
        form.suplier.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemesanan")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        form.suplier.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        try {
            aktifkanbatch = koneksiDB.AKTIFKANBATCHOBAT();
        } catch (Exception e) {
            System.out.println("E : "+e);
            aktifkanbatch = "no";
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
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnKirimSP = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        label20 = new widget.Label();
        nmsup = new widget.TextBox();
        BtnSuplier = new widget.Button();
        kdsup = new widget.TextBox();
        ChkFilter = new widget.CekBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Darurat Stok Obat/BHP/Alkes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(69, 23));
        panelisi1.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

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
        panelisi1.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('A');
        BtnAll.setToolTipText("Alt+A");
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
        panelisi1.add(BtnAll);

        label9.setText("Record :");
        label9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 30));
        panelisi1.add(label9);

        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(50, 30));
        panelisi1.add(LTotal);

        BtnKirimSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnKirimSP.setMnemonic('3');
        BtnKirimSP.setText("Kirim SP");
        BtnKirimSP.setToolTipText("Alt+3");
        BtnKirimSP.setName("BtnKirimSP"); // NOI18N
        BtnKirimSP.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimSPActionPerformed(evt);
            }
        });
        panelisi1.add(BtnKirimSP);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+P");
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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 50));
        panelisi4.setLayout(null);

        label20.setText("Suplier :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(40, 23));
        panelisi4.add(label20);
        label20.setBounds(20, 10, 80, 23);

        nmsup.setEditable(false);
        nmsup.setName("nmsup"); // NOI18N
        nmsup.setPreferredSize(new java.awt.Dimension(150, 23));
        nmsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmsupActionPerformed(evt);
            }
        });
        panelisi4.add(nmsup);
        nmsup.setBounds(200, 10, 180, 23);

        BtnSuplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSuplier.setMnemonic('2');
        BtnSuplier.setToolTipText("Alt+2");
        BtnSuplier.setName("BtnSuplier"); // NOI18N
        BtnSuplier.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSuplier.setRequestFocusEnabled(false);
        BtnSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuplierActionPerformed(evt);
            }
        });
        panelisi4.add(BtnSuplier);
        BtnSuplier.setBounds(390, 10, 28, 23);

        kdsup.setName("kdsup"); // NOI18N
        kdsup.setPreferredSize(new java.awt.Dimension(80, 23));
        kdsup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsupKeyPressed(evt);
            }
        });
        panelisi4.add(kdsup);
        kdsup.setBounds(110, 10, 80, 23);

        ChkFilter.setBackground(new java.awt.Color(255, 255, 250));
        ChkFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFilter.setForeground(new java.awt.Color(0, 0, 0));
        ChkFilter.setSelected(true);
        ChkFilter.setText("Filter Aktif ");
        ChkFilter.setBorderPainted(true);
        ChkFilter.setBorderPaintedFlat(true);
        ChkFilter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFilter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFilter.setName("ChkFilter"); // NOI18N
        ChkFilter.setPreferredSize(new java.awt.Dimension(95, 23));
        ChkFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkFilterActionPerformed(evt);
            }
        });
        panelisi4.add(ChkFilter);
        ChkFilter.setBounds(430, 10, 110, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            int row=tabMode.getRowCount();
            for(int i=0;i<row;i++){  
                Sequel.menyimpan("temporary","'"+i+"','"+
                                tabMode.getValueAt(i,1).toString()+"','"+
                                tabMode.getValueAt(i,2).toString()+"','"+
                                tabMode.getValueAt(i,3).toString()+"','"+
                                tabMode.getValueAt(i,4).toString()+"','"+
                                tabMode.getValueAt(i,5).toString()+"','"+
                                tabMode.getValueAt(i,6).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Sirkulasi Barang Keluar Masuk"); 
            }
            
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptDaruratStok.jasper","report","::[ Darurat Stok Obat/BHP/Alkes ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
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
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (ChkFilter.isSelected() == true) {
            prosesCari2();
        } else {
            prosesCari();
        }      
        this.setCursor(Cursor.getDefaultCursor());
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
        kdsup.setText("");
        nmsup.setText("");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        if (ChkFilter.isSelected() == true) {
            prosesCari2();
        } else {
            prosesCari();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void nmsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmsupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmsupActionPerformed

    private void BtnSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuplierActionPerformed
       if (ChkFilter.isSelected() == true) {
        akses.setform("DlgPemesanan");
        form.suplier.emptTeks();
        form.suplier.isCek();
        form.suplier.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.suplier.setLocationRelativeTo(internalFrame1);
        form.suplier.setAlwaysOnTop(false);
        form.suplier.setVisible(true);
       }else {
           JOptionPane.showMessageDialog(null, "Silahkan Di Ceklist untuk mengaktifkan filter..!");
       }
    }//GEN-LAST:event_BtnSuplierActionPerformed

    private void kdsupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsupKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdsupKeyPressed

    private void ChkFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkFilterActionPerformed
        if (ChkFilter.isSelected() == true) {
            ChkFilter.setText("Filter Aktif");
        } else {
            ChkFilter.setText("Filter Non Aktif");
        }
    }//GEN-LAST:event_ChkFilterActionPerformed

    private void BtnKirimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimSPActionPerformed
       if(kdsup.getText().equals("")){
                Valid.textKosong(kdsup,"Supllier");
                BtnSuplier.requestFocus();
            }else{
                int reply = JOptionPane.showConfirmDialog(
                    rootPane,
                    "Eeiiiiiits, udah bener belum data yang mau disimpan..??",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
                );

                if (reply == JOptionPane.YES_OPTION) {
                    boolean ada = false;

                    aplikasi.isCek();
                    aplikasi.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    aplikasi.setLocationRelativeTo(internalFrame1);

                    for(int i=0;i<tbDokter.getRowCount();i++){
                        if(Boolean.parseBoolean(tbDokter.getValueAt(i,0).toString())){
                            ada = true;
                            String kodeobat = tbDokter.getValueAt(i,1).toString();
                            aplikasi.tambahObat(kodeobat, kdsup.getText(), nmsup.getText());
                        }
                    }

                    if(ada){
                        aplikasi.setDariDaruratStok(true);
                        aplikasi.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Belum ada obat yang dipilih");
                    }
                }
            }
    }//GEN-LAST:event_BtnKirimSPActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDaruratStok dialog = new DlgDaruratStok(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKirimSP;
    private widget.Button BtnPrint;
    private widget.Button BtnSuplier;
    public widget.CekBox ChkFilter;
    private widget.Label LTotal;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.TextBox kdsup;
    private widget.Label label10;
    private widget.Label label20;
    private widget.Label label9;
    private widget.TextBox nmsup;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
       z=0;
        for(i=0;i<tbDokter.getRowCount();i++){
            if(!tbDokter.getValueAt(i,0).toString().equals("")){
                z++;
            }
        }    
        
        pilih=null;
        pilih=new boolean[z]; 
        kodebarang=null;
        kodebarang=new String[z];
        namabarang=null;
        namabarang=new String[z];
        satuan=null;
        satuan=new String[z];
        jenis=null;
        jenis=new String[z];
        stokminimal=null;
        stokminimal=new double[z];                   
        stokk=null;
        stokk=new double[z];           

        z=0;
        for(i=0;i<tbDokter.getRowCount();i++){
            if(!tbDokter.getValueAt(i,0).toString().equals("false")){
                 try {
                    pilih[z]=Boolean.parseBoolean(tbDokter.getValueAt(i,0).toString()); 
                } catch (Exception e) {
                    pilih[z]=false;
                }  
                //pilih[z]=Boolean.parseBoolean(tbObat.getValueAt(i,0).toString());                 
                kodebarang[z]=tbDokter.getValueAt(i,1).toString();
                namabarang[z]=tbDokter.getValueAt(i,2).toString();
                satuan[z]=tbDokter.getValueAt(i,3).toString();
                jenis[z]=tbDokter.getValueAt(i,4).toString();
                stokminimal[z]=Double.parseDouble(tbDokter.getValueAt(i,5).toString());
                stokk[z]=Double.parseDouble(tbDokter.getValueAt(i,6).toString());   
                z++;
            }
        }
        
        Valid.tabelKosong(tabMode);             
        
        for(i=0;i<z;i++){
            tabMode.addRow(new Object[] {
                pilih[i],kodebarang[i],namabarang[i],satuan[i],jenis[i],
                stokminimal[i],stokk[i]
            });
        }     
       try{   
            ps=koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng, "
                        + " kodesatuan.satuan,databarang.stokminimal, jenis.nama "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns where databarang.status='1' "
                        + " and (databarang.kode_brng like ? or databarang.nama_brng like ? or jenis.nama like ?) order by databarang.nama_brng");
            try {
                ps.setString(1,"%"+TCari.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();            
                while(rs.next()){ 
                    if(aktifkanbatch.equals("yes")){
                        psstok=koneksi.prepareStatement("select sum(gudangbarang.stok) from gudangbarang inner join bangsal on gudangbarang.kd_bangsal=bangsal.kd_bangsal where bangsal.status='1' and gudangbarang.no_batch<>'' and gudangbarang.no_faktur<>'' and gudangbarang.kode_brng=?"); 
                    }else{
                        psstok=koneksi.prepareStatement("select sum(gudangbarang.stok) from gudangbarang inner join bangsal on gudangbarang.kd_bangsal=bangsal.kd_bangsal where bangsal.status='1' and gudangbarang.no_batch='' and gudangbarang.no_faktur='' and gudangbarang.kode_brng=?"); 
                    }
                               
                    try {
                        psstok.setString(1,rs.getString(1));
                        rsstok=psstok.executeQuery();
                        if(rsstok.next()){
                            stok=rsstok.getDouble(1);
                        }
                    } finally{
                        if(rsstok!=null){
                            rsstok.close();
                        }
                        if(psstok!=null){
                            psstok.close();
                        }
                    }
                    if(stok<=rs.getDouble("stokminimal")){
                        tabMode.addRow(new Object[]{
                            false,rs.getString("kode_brng"),rs.getString("nama_brng"),
                            rs.getString("satuan"),rs.getString("nama"),
                            rs.getDouble("stokminimal"),stok
                        });
                    }
                }                  
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LTotal.setText(""+tabMode.getRowCount());
    }
    
    private void prosesCari2() {
         z=0;
        for(i=0;i<tbDokter.getRowCount();i++){
            if(!tbDokter.getValueAt(i,0).toString().equals("")){
                z++;
            }
        }    
        
        pilih=null;
        pilih=new boolean[z]; 
        kodebarang=null;
        kodebarang=new String[z];
        namabarang=null;
        namabarang=new String[z];
        satuan=null;
        satuan=new String[z];
        jenis=null;
        jenis=new String[z];
        stokminimal=null;
        stokminimal=new double[z];                   
        stokk=null;
        stokk=new double[z];           

        z=0;
        for(i=0;i<tbDokter.getRowCount();i++){
            if(!tbDokter.getValueAt(i,0).toString().equals("false")){
                 try {
                    pilih[z]=Boolean.parseBoolean(tbDokter.getValueAt(i,0).toString()); 
                } catch (Exception e) {
                    pilih[z]=false;
                }  
                //pilih[z]=Boolean.parseBoolean(tbObat.getValueAt(i,0).toString());                 
                kodebarang[z]=tbDokter.getValueAt(i,1).toString();
                namabarang[z]=tbDokter.getValueAt(i,2).toString();
                satuan[z]=tbDokter.getValueAt(i,3).toString();
                jenis[z]=tbDokter.getValueAt(i,4).toString();
                stokminimal[z]=Double.parseDouble(tbDokter.getValueAt(i,5).toString());
                stokk[z]=Double.parseDouble(tbDokter.getValueAt(i,6).toString());   
                z++;
            }
        }
        
        Valid.tabelKosong(tabMode);             
        
        for(i=0;i<z;i++){
            tabMode.addRow(new Object[] {
                pilih[i],kodebarang[i],namabarang[i],satuan[i],jenis[i],
                stokminimal[i],stokk[i]
            });
        }     
       try{   
            ps=koneksi.prepareStatement("SELECT  db.kode_brng,db.nama_brng,ks.satuan,db.stokminimal,j.nama " +
            "FROM databarang db INNER JOIN kodesatuan ks ON db.kode_sat = ks.kode_sat " +
            "INNER JOIN jenis j ON db.kdjns = j.kdjns WHERE db.status = '1' " +
            "AND EXISTS (SELECT 1 FROM detailpesan dp INNER JOIN pemesanan p ON p.no_faktur = dp.no_faktur " +
            "WHERE dp.kode_brng = db.kode_brng AND p.kode_suplier like ?) " +
            "AND NOT EXISTS (SELECT 1 FROM detail_surat_pemesanan_medis spd INNER JOIN surat_pemesanan_medis sp "+
            "ON sp.no_pemesanan = spd.no_pemesanan WHERE spd.kode_brng = db.kode_brng AND sp.status = 'Proses Pesan') "+
            "AND ( db.kode_brng LIKE ? OR db.nama_brng LIKE ? OR j.nama LIKE ?) " +
            "ORDER BY db.nama_brng");
            try {
                ps.setString(1,"%"+kdsup.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();            
                while(rs.next()){ 
                    if(aktifkanbatch.equals("yes")){
                        psstok=koneksi.prepareStatement("select sum(gudangbarang.stok) from gudangbarang inner join bangsal on gudangbarang.kd_bangsal=bangsal.kd_bangsal where bangsal.status='1' and gudangbarang.no_batch<>'' and gudangbarang.no_faktur<>'' and gudangbarang.kode_brng=?"); 
                    }else{
                        psstok=koneksi.prepareStatement("select sum(gudangbarang.stok) from gudangbarang inner join bangsal on gudangbarang.kd_bangsal=bangsal.kd_bangsal where bangsal.status='1' and gudangbarang.no_batch='' and gudangbarang.no_faktur='' and gudangbarang.kode_brng=?"); 
                    }
                               
                    try {
                        psstok.setString(1,rs.getString(1));
                        rsstok=psstok.executeQuery();
                        if(rsstok.next()){
                            stok=rsstok.getDouble(1);
                        }
                    } finally{
                        if(rsstok!=null){
                            rsstok.close();
                        }
                        if(psstok!=null){
                            psstok.close();
                        }
                    }
                    if(stok<=rs.getDouble("stokminimal")){
                        tabMode.addRow(new Object[]{
                            false,rs.getString("kode_brng"),rs.getString("nama_brng"),
                            rs.getString("satuan"),rs.getString("nama"),
                            rs.getDouble("stokminimal"),stok
                        });
                    }
                }                  
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LTotal.setText(""+tabMode.getRowCount());
    }
    
    public void isCek(){
         BtnPrint.setEnabled(akses.getsirkulasi_obat());
    }
    
}

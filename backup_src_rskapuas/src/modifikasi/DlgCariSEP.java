/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAdmin.java
 *
 * Created on 21 Jun 10, 20:53:44
 */

package modifikasi;

import setting.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariPoli;

/**
 *
 * @author perpustakaan
 */
public class DlgCariSEP extends javax.swing.JDialog {
    private final DefaultTableModel tabRalan,tabModeRanap,tabModeDoulbe,tabModekirim,tabModebelumkirim;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;
    private DlgCariBangsal bangsal=new DlgCariBangsal(null,false);    
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private int i, pilihan=0;

    /** Creates new form DlgAdmin
     * @param parent
     * @param modal */
    public DlgCariSEP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,10);
        setSize(457,249);
        tabRalan=new DefaultTableModel(null,new Object[]{"No. M.R","No. SEP","Tgl. SEP","No. Kartu","Nama Peserta","Poliklinik","DPJP"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbRalan.setModel(tabRalan);
        tbRalan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbRalan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }
        }

        tbRalan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRanap=new DefaultTableModel(null,new Object[]{"No. M.R","No. SEP","Tgl. SEP","No. Kartu","Nama Peserta","DPJP"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbRanap.setModel(tabModeRanap);
        tbRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbRanap.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(150);
            }
        }

        tbRanap.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDoulbe=new DefaultTableModel(null,new Object[]{"No. M.R","No. SEP","Tgl. SEP","No. Kartu","Nama Peserta","Poliklinik/Ruangan","Status"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbDouble.setModel(tabModeDoulbe);
        tbDouble.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDouble.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbDouble.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(80);
            }
        }

        tbDouble.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModekirim=new DefaultTableModel(null,new Object[]{"No. M.R","No. SEP","Tgl. SEP","No. Kartu","Nama Peserta","Petugas Entry Klaim"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbkiriminacbg.setModel(tabModekirim);
        tbkiriminacbg.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbkiriminacbg.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbkiriminacbg.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }
        }

        tbkiriminacbg.setDefaultRenderer(Object.class, new WarnaTable());
        
         tabModebelumkirim=new DefaultTableModel(null,new Object[]{"No. M.R","No. SEP","Tgl. SEP","No. Kartu","Nama Peserta","Petugas Entry Klaim"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbkbelumkirimacbg.setModel(tabModebelumkirim);
        tbkbelumkirimacbg.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbkbelumkirimacbg.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbkbelumkirimacbg.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(200);
            }
        }

        tbkbelumkirimacbg.setDefaultRenderer(Object.class, new WarnaTable());

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
        panelGlass5 = new widget.panelisi();
        BtnCetak = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRalan = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbRanap = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbDouble = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbkiriminacbg = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbkbelumkirimacbg = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        jLabel10 = new widget.Label();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        DTPCari2 = new widget.Tanggal();
        BtnAll = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Detail SEP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnCetak.setMnemonic('G');
        BtnCetak.setText("Cetak");
        BtnCetak.setToolTipText("Alt+G");
        BtnCetak.setIconTextGap(3);
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        BtnCetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCetakKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCetak);

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
        panelGlass5.add(BtnKeluar);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass5.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(LCount);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRalan.setAutoCreateRowSorter(true);
        tbRalan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRalan.setName("tbRalan"); // NOI18N
        tbRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRalanMouseClicked(evt);
            }
        });
        tbRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRalanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRalan);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("SEP Ralan", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRanap.setAutoCreateRowSorter(true);
        tbRanap.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRanap.setName("tbRanap"); // NOI18N
        tbRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRanapMouseClicked(evt);
            }
        });
        tbRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRanapKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbRanap);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("SEP Ranap", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbDouble.setAutoCreateRowSorter(true);
        tbDouble.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDouble.setName("tbDouble"); // NOI18N
        tbDouble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDoubleMouseClicked(evt);
            }
        });
        tbDouble.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDoubleKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbDouble);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("SEP RJ & RI", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbkiriminacbg.setAutoCreateRowSorter(true);
        tbkiriminacbg.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbkiriminacbg.setName("tbkiriminacbg"); // NOI18N
        tbkiriminacbg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkiriminacbgMouseClicked(evt);
            }
        });
        tbkiriminacbg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkiriminacbgKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbkiriminacbg);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("SEP Terikirim ke Inacbg", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbkbelumkirimacbg.setAutoCreateRowSorter(true);
        tbkbelumkirimacbg.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbkbelumkirimacbg.setName("tbkbelumkirimacbg"); // NOI18N
        tbkbelumkirimacbg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkbelumkirimacbgMouseClicked(evt);
            }
        });
        tbkbelumkirimacbg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkbelumkirimacbgKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbkbelumkirimacbg);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("SEP Belum Terikirim ke Inacbg", internalFrame6);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 60));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(500, 50));
        panelisi1.setLayout(null);

        jLabel10.setText("s . d");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi1.add(jLabel10);
        jLabel10.setBounds(240, 10, 20, 23);

        jLabel19.setText("Tgl. SEP :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi1.add(jLabel19);
        jLabel19.setBounds(50, 10, 55, 23);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(DTPCari1);
        DTPCari1.setBounds(130, 10, 95, 23);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-06-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(DTPCari2);
        DTPCari2.setBounds(280, 10, 95, 23);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Cari Data");
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
        panelisi1.add(BtnAll);
        BtnAll.setBounds(390, 10, 100, 30);

        jPanel1.add(panelisi1);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();    
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCetak,BtnKeluar);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRalanMouseClicked
  
}//GEN-LAST:event_tbRalanMouseClicked

    private void tbRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRalanKeyPressed
  
}//GEN-LAST:event_tbRalanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilralan();
    }//GEN-LAST:event_formWindowOpened

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed

        if(TabRawat.getSelectedIndex()==0){
            Valid.panggilUrl("billing/seprj.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));
            this.setCursor(Cursor.getDefaultCursor());         
        }else if(TabRawat.getSelectedIndex()==1){
            Valid.panggilUrl("billing/sepri.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));
            this.setCursor(Cursor.getDefaultCursor());   
        }else if(TabRawat.getSelectedIndex()==2){
           if(tabModeDoulbe.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
        }else{
            Valid.panggilUrl("billing/doublesep.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
            }   
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void BtnCetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCetakKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCetakActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
    }//GEN-LAST:event_BtnCetakKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampilralan();
             BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==1){
            tampilranap();
            BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==2){
            tampildouble();
            BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==3){
            tampilkiriminacbg();
            BtnCetak.setVisible(false);
        }else if(TabRawat.getSelectedIndex()==4){
            tampilbelumkiriminacbg();
            BtnCetak.setVisible(false);
        }
        
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRanapMouseClicked
        
    }//GEN-LAST:event_tbRanapMouseClicked

    private void tbRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRanapKeyPressed
              
    }//GEN-LAST:event_tbRanapKeyPressed

    private void tbDoubleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDoubleMouseClicked
        
    }//GEN-LAST:event_tbDoubleMouseClicked

    private void tbDoubleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDoubleKeyPressed
           
    }//GEN-LAST:event_tbDoubleKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
      if(TabRawat.getSelectedIndex()==0){
            tampilralan();
            BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==1){
            tampilranap();
            BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==2){
            tampildouble();
            BtnCetak.setVisible(true);
        }else if(TabRawat.getSelectedIndex()==3){
            tampilkiriminacbg();
            BtnCetak.setVisible(false);
        }else if(TabRawat.getSelectedIndex()==4){
            tampilbelumkiriminacbg();
            BtnCetak.setVisible(false);
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampilralan();
            tampilranap();
            tampildouble();
        }else{
            Valid.pindah(evt, BtnCetak, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void tbkiriminacbgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkiriminacbgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbkiriminacbgMouseClicked

    private void tbkiriminacbgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkiriminacbgKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbkiriminacbgKeyPressed

    private void tbkbelumkirimacbgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkbelumkirimacbgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbkbelumkirimacbgMouseClicked

    private void tbkbelumkirimacbgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkbelumkirimacbgKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbkbelumkirimacbgKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariSEP dialog = new DlgCariSEP(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCetak;
    private widget.Button BtnKeluar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel1;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi1;
    private widget.Table tbDouble;
    private widget.Table tbRalan;
    private widget.Table tbRanap;
    private widget.Table tbkbelumkirimacbg;
    private widget.Table tbkiriminacbg;
    // End of variables declaration//GEN-END:variables

    private void tampilralan() {
        Valid.tabelKosong(tabRalan);
        try{   
            ps=koneksi.prepareStatement("SELECT nomr,no_sep,tglsep,no_kartu,nama_pasien,nmpolitujuan,nmdpdjp FROM bridging_sep where " +
                                        "tglsep BETWEEN ? AND ?  AND jnspelayanan='2' ORDER BY tglsep ASC " );
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    tabRalan.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
                }
            } catch (SQLException e) {
                System.out.println(e);
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
        LCount.setText(""+tabRalan.getRowCount()); 
    }

    

    public void emptTeks() {
        if(TabRawat.getSelectedIndex()==0){
           
        }else if(TabRawat.getSelectedIndex()==1){
            
        }else if(TabRawat.getSelectedIndex()==2){
                  }
            
    }

    private void tampilranap() {
        Valid.tabelKosong(tabModeRanap);
        try{   
            ps=koneksi.prepareStatement(
                    "SELECT nomr,no_sep,tglsep,no_kartu,nama_pasien,nmdpdjp FROM bridging_sep where " +
                    "tglsep BETWEEN ? AND ?  AND jnspelayanan='1' ORDER BY tglsep ASC " );
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModeRanap.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
            } catch (SQLException e) {
                System.out.println(e);
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
         LCount.setText(""+tabModeRanap.getRowCount()); 
    }

    private void tampildouble() {
        Valid.tabelKosong(tabModeDoulbe);
        try{   
            ps=koneksi.prepareStatement(
                "SELECT no_rawat,bridging_sep.no_sep,nama_pasien,nomr,tglsep FROM bridging_sep WHERE tglsep BETWEEN ?  AND ? GROUP BY no_rawat HAVING COUNT(no_rawat)  > 1 ORDER BY tglsep ASC");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    ps1=koneksi.prepareStatement("SELECT nomr,no_sep,tglsep,no_kartu,nama_pasien,nmpolitujuan,if(bridging_sep.jnspelayanan='1','1. Ranap','2. Ralan') FROM bridging_sep WHERE no_rawat=? and tglsep=? ORDER BY tglsep ASC ");
                    try {
                       ps1.setString(1,rs.getString(1));
                       ps1.setString(2,rs.getString(5));
                       rs1=ps1.executeQuery();
                       while(rs1.next()){
                       tabModeDoulbe.addRow(new Object[]{
                       rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7)}); 
                       }
                    } catch (Exception e) {
                         System.out.println(e);
                    }
                  
                }
            } catch (SQLException e) {
                System.out.println(e);
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
        LCount.setText(""+tabModeDoulbe.getRowCount()); 
    }
    
    private void tampilkiriminacbg() {
         Valid.tabelKosong(tabModekirim);
        try{   
            ps=koneksi.prepareStatement(
                    "SELECT a.nomr,a.no_sep,a.tglsep,a.no_kartu,a.nama_pasien,c.nama FROM bridging_sep a INNER JOIN inacbg_data_terkirim2 b ON a.no_sep=b.no_sep INNER JOIN pegawai c ON c.no_ktp=b.nik where " +
                    "a.tglsep BETWEEN ? AND ?  AND a.jnspelayanan='2' ORDER BY a.tglsep ASC,a.no_sep ASC " );
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModekirim.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
            } catch (SQLException e) {
                System.out.println(e);
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
         LCount.setText(""+tabModekirim.getRowCount()); 
    }
    
    private void tampilbelumkiriminacbg() {
         Valid.tabelKosong(tabModebelumkirim);
        try{   
            ps=koneksi.prepareStatement(
                    "SELECT a.nomr,a.no_sep,a.tglsep,a.no_kartu,a.nama_pasien FROM bridging_sep a where " +
                    "a.tglsep BETWEEN ? AND ?  AND a.jnspelayanan='2' and a.no_sep NOT IN (SELECT inacbg_data_terkirim2.no_sep FROM inacbg_data_terkirim2) ORDER BY a.tglsep ASC,a.no_sep ASC " );
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModebelumkirim.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"-"});
                }
            } catch (SQLException e) {
                System.out.println(e);
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
         LCount.setText(""+tabModebelumkirim.getRowCount()); 
    }

}

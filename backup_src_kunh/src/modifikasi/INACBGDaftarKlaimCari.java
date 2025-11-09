package modifikasi;
import bridging.ApiEKLAIM_inacbg;
import com.fasterxml.jackson.databind.JsonNode;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class INACBGDaftarKlaimCari extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps4, ps5, ps6;
    private ResultSet rs, rs1, rs2, rs4, rs5, rs6;
    private Date tgl = new Date();
    private String cekSEP = "", jnsKlaim = "", nik = "", noka = "", tglKunj = "", jnsrwt = "", norm = "", dialog_simpan = "",
            nmpas = "", tgllahir = "", jk = "", tglSep = "", nilaiRWT = "", kd_payor = "", nmibu = "", cekData = "", jnsRawat = "";
    private JsonNode root;
    private int cekKlaim = 0, x = 0, i = 0;
    private ApiEKLAIM_inacbg mbak_eka = new ApiEKLAIM_inacbg();


    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public INACBGDaftarKlaimCari(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

//        this.setLocation(10, 10);
//        setSize(459, 539);

        Object[] row = {"No. Rawat", "Jns. Jaminan", "No. SEP/No.Klaim", "No. RM", "Nama Pasien", "Jns. Rawat", "Nama Unit","Hak Kelas", "Naik Kelas", "Kode INACBG", "Tarif Grouping", 
            "Sub. Tarif Grouping", "Kronik Tarif", "TopUp Tarif", "Tot. Tarif Grouping", "Tarif Naik Kls.", "TopUp Covid-19", "Tarif Real Cost", "Status Final", "Petugas Coder", "Tgl. Input", 
            "Kirim Online", "tglSEP", "kodepayor"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbINACBG.setModel(tabMode);
        tbINACBG.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbINACBG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 24; i++) {
            TableColumn column = tbINACBG.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(125);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(85);
            } else if (i == 17) {
                column.setPreferredWidth(85);
            } else if (i == 18) {
                column.setPreferredWidth(80);
            } else if (i == 19) {
                column.setPreferredWidth(150);
            } else if (i == 20) {
                column.setPreferredWidth(80);
            } else if (i == 21) {
                column.setPreferredWidth(160);
            } else if (i == 22) {
//                column.setPreferredWidth(160);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
//                column.setPreferredWidth(80);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbINACBG.setDefaultRenderer(Object.class, new WarnaTable());
        

        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari1));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbINACBG = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        nosep_klaim = new widget.TextBox();
        jLabel7 = new widget.Label();
        noRawat = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbJnsKlaim = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        panelGlass10 = new widget.panelisi();
        Chktgl = new widget.CekBox();
        tglA = new widget.Tanggal();
        jLabel18 = new widget.Label();
        tglB = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        BtnKeluar1 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbINACBG.setAutoCreateRowSorter(true);
        tbINACBG.setToolTipText("");
        tbINACBG.setName("tbINACBG"); // NOI18N
        tbINACBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbINACBGMouseClicked(evt);
            }
        });
        tbINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbINACBGKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbINACBG);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("No. SEP/No. Klaim : ");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel6);

        nosep_klaim.setEditable(false);
        nosep_klaim.setForeground(new java.awt.Color(0, 0, 0));
        nosep_klaim.setName("nosep_klaim"); // NOI18N
        nosep_klaim.setPreferredSize(new java.awt.Dimension(143, 23));
        panelGlass9.add(nosep_klaim);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. Rawat : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel7);

        noRawat.setEditable(false);
        noRawat.setForeground(new java.awt.Color(0, 0, 0));
        noRawat.setName("noRawat"); // NOI18N
        noRawat.setPreferredSize(new java.awt.Dimension(135, 23));
        panelGlass9.add(noRawat);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns. Klaim : ");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel13);

        cmbJnsKlaim.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsKlaim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "JKN", "JAMINAN COVID-19", "JAMINAN KIPI", "JAMINAN CO-INSIDENSE", "JAMINAN BAYI BARU LAHIR" }));
        cmbJnsKlaim.setName("cmbJnsKlaim"); // NOI18N
        cmbJnsKlaim.setPreferredSize(new java.awt.Dimension(165, 23));
        cmbJnsKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsKlaimActionPerformed(evt);
            }
        });
        panelGlass9.add(cmbJnsKlaim);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jns. Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "JALAN", "INAP" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(cmbJnsRawat);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        Chktgl.setBackground(new java.awt.Color(255, 255, 250));
        Chktgl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        Chktgl.setForeground(new java.awt.Color(0, 0, 0));
        Chktgl.setSelected(true);
        Chktgl.setText("Tgl. Klaim : ");
        Chktgl.setBorderPainted(true);
        Chktgl.setBorderPaintedFlat(true);
        Chktgl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Chktgl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Chktgl.setName("Chktgl"); // NOI18N
        Chktgl.setPreferredSize(new java.awt.Dimension(95, 23));
        Chktgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChktglActionPerformed(evt);
            }
        });
        panelGlass10.add(Chktgl);

        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-11-2025" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglA);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("s.d");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel18);

        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-11-2025" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglB);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Limit Data : ");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel11);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        panelGlass10.add(cmbLimit);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnAll1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('4');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+4");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        }        
    }//GEN-LAST:event_formWindowOpened

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        }        
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        nosep_klaim.setText("");
        noRawat.setText("");
        cmbJnsKlaim.setSelectedIndex(0);
        cmbJnsRawat.setSelectedIndex(0);
        TCari1.setText("");
        
        if (Chktgl.isSelected() == true) {
            tampilKLAIM();
        } else {
            tampilSEP();
        } 
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void ChktglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChktglActionPerformed
        if (Chktgl.isSelected() == true) {
            Chktgl.setText("Tgl. Klaim : ");
        } else {
            Chktgl.setText("Tgl. SEP : ");
        }
    }//GEN-LAST:event_ChktglActionPerformed

    private void tbINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbINACBGKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbINACBGKeyPressed

    private void tbINACBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbINACBGMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbINACBGMouseClicked

    private void cmbJnsKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsKlaimActionPerformed
        if (cekData.equals("")) {
            setData("didalam");
        } else {
            setData("dari luar");
        }
        
        if (cmbJnsKlaim.getSelectedIndex() == 0) {
            jnsKlaim = "";
        } else if (cmbJnsKlaim.getSelectedIndex() == 1) {
            jnsKlaim = "JKN";
        } else if (cmbJnsKlaim.getSelectedIndex() == 2) {
            jnsKlaim = "JAMINAN COVID-19";
        } else if (cmbJnsKlaim.getSelectedIndex() == 3) {
            jnsKlaim = "JAMINAN KIPI";
        } else if (cmbJnsKlaim.getSelectedIndex() == 4) {
            jnsKlaim = "JAMINAN CO-INSIDENSE";
        } else if (cmbJnsKlaim.getSelectedIndex() == 5) {
            jnsKlaim = "JAMINAN BAYI BARU LAHIR";
        }
    }//GEN-LAST:event_cmbJnsKlaimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                INACBGDaftarKlaimCari dialog = new INACBGDaftarKlaimCari(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll1;
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar1;
    public widget.CekBox Chktgl;
    private widget.Label LCount1;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari1;
    public widget.ComboBox cmbJnsKlaim;
    public widget.ComboBox cmbJnsRawat;
    public widget.ComboBox cmbLimit;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel18;
    private widget.Label jLabel23;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.TextBox noRawat;
    private widget.TextBox nosep_klaim;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private widget.Table tbINACBG;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    // End of variables declaration//GEN-END:variables

    private void tampilSEP() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode);
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%ralan%";
        } else {
            nilaiRWT = "%%";
        }
        
        try {
            if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ','Ranap')) unit, IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar'))) like ? "
                        + "order by enc.tglsep desc limit " + cmbLimit.getSelectedItem().toString() + "");
            } else {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ','Ranap')) unit, IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and enc.tglsep BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar'))) like ? "
                        + "order by enc.tglsep desc");
            }
            try {
                 if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");   
                } else {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("payor_cd"), 
                        rs.getString("no_sep"),
                        rs.getString("no_rm"),
                        rs.getString("nm_pasien"),
                        rs.getString("Rawat"),
                        rs.getString("unit"),
                        rs.getString("Kelas"),
                        rs.getString("naik_kelas"),
                        rs.getString("cbg_code"),
                        rs.getDouble("cbg_tarif"),
                        rs.getDouble("sub_acute_tarif"),
                        rs.getDouble("chronic_tarif"),
                        rs.getDouble("special_cmg_tarif"),
                        rs.getDouble("total_tarif_grouping"),
                        rs.getDouble("naik_kelas_tarif"),
                        rs.getDouble("trf_covid"),
                        rs.getDouble("real_tarif"),
                        rs.getString("klaim_final"),
                        rs.getString("nm_petugas"),
                        rs.getString("tglInput"),
                        rs.getString("kirim_online"),
                        rs.getString("tglsep"),
                        rs.getString("payor_id")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }

    public JTable getTable() {
        return tbINACBG;
    }

    
 
    
    public void isCek() {
//      if(akses.getkode().equals("Admin Utama")){
//         BtnData.setEnabled(true);
//         BtnProses.setEnabled(true);                                      
//       }else if (Sequel.cariInteger("SELECT icn.nik  FROM  inacbg_coder_nik icn inner join pegawai p on icn.nik =p.nik  WHERE icn.nik ='"+akses.getkode()+"' AND  p.departemen ='ENTR'")>0){
//         BtnData.setEnabled(true);
//         BtnProses.setEnabled(true); 
//       }else {
//         BtnData.setEnabled(false);
//         BtnProses.setEnabled(false);
//       }
    }
    
    public void emptTeks() {
        noRawat.setText("");
        nosep_klaim.setText("");
        TCari1.setText("");
        tgl.setDate(1);
        tglA.setDate(tgl);
        tglB.setDate(new Date());
        cmbJnsKlaim.setSelectedIndex(0);
        cmbJnsRawat.setSelectedIndex(0);
    }
    
    public void tampilKLAIM() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode);
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%ralan%";
        } else {
            nilaiRWT = "%%";
        }
        
        try {
            if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ','Ranap')) unit, IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar'))) like ? "
                        + "order by enc.tgl_input desc limit " + cmbLimit.getSelectedItem().toString() + "");

            } else {
                ps = koneksi.prepareStatement("SELECT enc.no_rawat,esc.payor_cd,enc.no_sep,if(enc.jnspelayanan='1','Inap','Jalan') Rawat,if(esc.jenis_rawat='1',eg.kelas,'') Kelas, "
                        + "if(esc.upgrade_class_ind='1',esc.upgrade_class_class,'') naik_kelas,eg.cbg_code,eg.cbg_tarif,eg.sub_acute_tarif,eg.chronic_tarif, "
                        + "(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) special_cmg_tarif, "
                        + "eg.cbg_tarif+eg.sub_acute_tarif+eg.chronic_tarif+(SELECT if(SUM(cmg.tarif) IS NULL,0,SUM(cmg.tarif)) FROM eklaim_grouping_spc_cmg cmg WHERE cmg.no_sep=enc.no_sep) total_tarif_grouping, "
                        + "if(esc.upgrade_class_ind='1',eg.add_payment_amt,0) naik_kelas_tarif, round(esc.real_tarif,0) real_tarif,enc.klaim_final,if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') kirim_online, "
                        + "enc.nm_pasien, date_format(enc.tgl_input,'%d-%m-%Y') tglInput, enc.tglsep, p.nama nm_petugas, IFNULL(ecd.top_up_rawat,'0') trf_covid, enc.no_rm, "
                        + "IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ','Ranap')) unit, IFNULL(egc.payor_id,'3') payor_id FROM eklaim_new_claim enc LEFT JOIN eklaim_generate_claim egc ON egc.claim_number=enc.no_sep "
                        + "LEFT JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep LEFT JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep LEFT JOIN eklaim_online_status eos ON eos.no_sep=enc.no_sep "
                        + "LEFT JOIN inacbg_coder_nik koder ON koder.no_ik=esc.coder_nik LEFT JOIN pegawai p ON p.nik=koder.nik LEFT JOIN eklaim_covid19_data ecd ON ecd.no_sep=enc.no_sep "
                        + "LEFT JOIN reg_periksa rp on rp.no_rawat=enc.no_rawat LEFT JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rawat like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and esc.payor_cd like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_sep like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.klaim_final like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and p.nama like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and enc.no_rm like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and if(eos.kemkes_dc_status='sent','Sudah Terkirim ke Kemenkes','Belum Terkirim') like ? or "
                        + "rp.status_lanjut like '" + nilaiRWT + "' and DATE(enc.tgl_input) BETWEEN ? AND ? and IF(rp.status_lanjut='Ralan',CONCAT('Inst./Poli ',pl.nm_poli),CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal WHERE ki.no_rawat=rp.no_rawat AND ki.stts_pulang<>'Pindah Kamar'))) like ? "
                        + "order by enc.tgl_input desc");
            }
            try {
                if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%"); 
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");  
                } else {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");                    
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("payor_cd"), 
                        rs.getString("no_sep"),
                        rs.getString("no_rm"),
                        rs.getString("nm_pasien"),
                        rs.getString("Rawat"),
                        rs.getString("unit"),
                        rs.getString("Kelas"),
                        rs.getString("naik_kelas"),
                        rs.getString("cbg_code"),
                        rs.getDouble("cbg_tarif"),
                        rs.getDouble("sub_acute_tarif"),
                        rs.getDouble("chronic_tarif"),
                        rs.getDouble("special_cmg_tarif"),
                        rs.getDouble("total_tarif_grouping"),
                        rs.getDouble("naik_kelas_tarif"),
                        rs.getDouble("trf_covid"),
                        rs.getDouble("real_tarif"),
                        rs.getString("klaim_final"),
                        rs.getString("nm_petugas"),
                        rs.getString("tglInput"),
                        rs.getString("kirim_online"),
                        rs.getString("tglsep"),
                        rs.getString("payor_id")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }
    
    private void getData() {
        cekData = "";
        tglSep = "";
        kd_payor = "";
        nmibu = "";
        if (tbINACBG.getSelectedRow() != -1) {
            kd_payor = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 23).toString();
            if (kd_payor.equals("3")) {
                cmbJnsKlaim.setSelectedIndex(1);
            } else if (kd_payor.equals("71")) {
                cmbJnsKlaim.setSelectedIndex(2);
            } else if (kd_payor.equals("72")) {
                cmbJnsKlaim.setSelectedIndex(3);
            } else if (kd_payor.equals("75")) {
                cmbJnsKlaim.setSelectedIndex(4);
            } else if (kd_payor.equals("73")) {
                cmbJnsKlaim.setSelectedIndex(5);
            }

            noRawat.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 0).toString());
            nosep_klaim.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 2).toString());
            tglSep = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 22).toString();
            jnsKlaim = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 1).toString();
            
            if (kd_payor.equals("73")) {
                nmibu = Sequel.cariIsi("SELECT p2.nm_pasien FROM reg_periksa rp1 INNER JOIN pasien_bayi pb ON pb.no_rkm_medis = rp1.no_rkm_medis "
                        + "INNER JOIN pasien p1 ON p1.no_rkm_medis = pb.no_rkm_medis LEFT JOIN reg_periksa rp2 ON rp2.no_rawat = pb.no_rawat_ibu "
                        + "LEFT JOIN pasien p2 ON p2.no_rkm_medis = rp2.no_rkm_medis WHERE rp1.no_rawat='" + noRawat.getText() + "'");
            } else {
                nmibu = "-";
            }

            try {
                ps4 = koneksi.prepareStatement("SELECT rp.no_rawat, rp.tgl_registrasi, p.no_ktp, p.no_peserta, p.no_rkm_medis, "
                        + "p.nm_pasien, if(p.jk='L','1','2') jkel, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p.tgl_lahir,' ','00:00:00') tgllhr FROM reg_periksa rp INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "WHERE rp.no_rawat='" + noRawat.getText() + "'");
                try {
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        nik = rs4.getString("no_ktp");
                        tglKunj = rs4.getString("tgl_registrasi");
                        jnsrwt = rs4.getString("jRawat");
                        norm = rs4.getString("no_rkm_medis");
                        nmpas = rs4.getString("nm_pasien");
                        tgllahir = rs4.getString("tgllhr");
                        jk = rs4.getString("jkel");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs4 != null) {
                        rs4.close();
                    }
                    if (ps4 != null) {
                        ps4.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    public void KlaimRAZA(String norw, String noSyaratKlaim, String nmKlaim, String kodepay) {
        if (nmKlaim.equals("JKN")) {
            jnsKlaim = nmKlaim;
            noRawat.setText(norw);
            nosep_klaim.setText(noSyaratKlaim);
            cmbJnsKlaim.setSelectedIndex(1);
            TCari1.setText("");
            tgl.setDate(1);
            tglA.setDate(tgl);
            tglB.setDate(new Date());
            kd_payor = kodepay;
            
            //untuk jaminan kipi belum selesai
        } else if (nmKlaim.equals("JAMINAN COVID-19") || nmKlaim.equals("JAMINAN KIPI")) {            
            try {
                ps5 = koneksi.prepareStatement("SELECT p.no_ktp nik, p.no_peserta noka_bpjs, p.no_rkm_medis, p.nm_pasien, "
                        + "if(p.jk='L','Laki-laki','Perempuan') jk, rp.tgl_registrasi, IF(rp.status_lanjut='Ralan','2','1') jRawat, "
                        + "concat(p.tgl_lahir,' ','00:00:00') tgllhr, if(p.jk='L','1','2') jkel FROM reg_periksa rp "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE rp.no_rawat ='" + norw + "'");
                try {
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        nik = rs5.getString("nik");
                        noka = rs5.getString("noka_bpjs");
                        tglKunj = rs5.getString("tgl_registrasi");
                        jnsrwt = rs5.getString("jRawat");
                        norm = rs5.getString("no_rkm_medis");
                        nmpas = rs5.getString("nm_pasien");
                        tgllahir = rs5.getString("tgllhr");
                        jk = rs5.getString("jkel");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs5 != null) {
                        rs5.close();
                    }
                    if (ps5 != null) {
                        ps5.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            if (nmKlaim.equals("JAMINAN COVID-19")) {
                cmbJnsKlaim.setSelectedIndex(2);
            } else if (nmKlaim.equals("JAMINAN KIPI")) {
                cmbJnsKlaim.setSelectedIndex(3);
            }
            jnsKlaim = nmKlaim;
            noRawat.setText(norw);
            nosep_klaim.setText(noSyaratKlaim);            
            TCari1.setText("");            
            tgl.setDate(1);
            tglA.setDate(tgl);
            tglB.setDate(new Date());
            kd_payor = kodepay;
        }
    }
    
    private void klaimBaruJKN() {
        cekSEP = "";
        cekKlaim = 0;
        cekSEP = Sequel.cariIsi("SELECT no_sep FROM eklaim_new_claim WHERE no_sep='" + nosep_klaim.getText() + "' and tglsep='" + tglSep + "'");
        cekKlaim = Sequel.cariInteger("select count(-1) from eklaim_set_claim where no_sep='" + nosep_klaim.getText() + "' and tgl_masuk='" + tglSep + "'");

        if (cekSEP.equals("")) {
            if (mbak_eka.ngirimJKN(noRawat.getText()) == true) {
                if (cekKlaim == 0) {
                    if (akses.getkode().equals("Admin Utama")) {
//                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                        ajukan.setLocationRelativeTo(internalFrame1);
//                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                        ajukan.tarifRS(noRawat.getText());
//                        ajukan.TabRawat.setEnabled(true);
//                        ajukan.TabRawat.setSelectedIndex(0);
//                        ajukan.TabDiagnosa1.setSelectedIndex(0);
//                        ajukan.TabProsedur1.setSelectedIndex(0);
//                        ajukan.emptTeksLAINNYA();
//                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
//                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                        ajukan.setLocationRelativeTo(internalFrame1);
//                        ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                        ajukan.tarifRS(noRawat.getText());
//                        ajukan.TabRawat.setEnabledAt(1, false);
//                        ajukan.TabRawat.setEnabledAt(0, true);
//                        ajukan.TabRawat.setSelectedIndex(0);
//                        ajukan.TabDiagnosa1.setSelectedIndex(0);
//                        ajukan.TabProsedur1.setSelectedIndex(0);
//                        ajukan.emptTeksLAINNYA();
//                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                } else if (cekKlaim > 0) {
                    if (akses.getkode().equals("Admin Utama")) {
//                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                        ajukan.setLocationRelativeTo(internalFrame1);
//                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                        ajukan.tarifRS(noRawat.getText());
//                        ajukan.TabRawat.setEnabled(true);
//                        ajukan.TabRawat.setSelectedIndex(0);
//                        ajukan.TabDiagnosa1.setSelectedIndex(0);
//                        ajukan.TabProsedur1.setSelectedIndex(0);
//                        ajukan.emptTeksLAINNYA();
//                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    } else {
//                        ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                        ajukan.setLocationRelativeTo(internalFrame1);
//                        ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                        ajukan.tarifRS(noRawat.getText());
//                        ajukan.TabRawat.setEnabledAt(1, false);
//                        ajukan.TabRawat.setEnabledAt(0, true);
//                        ajukan.TabRawat.setSelectedIndex(0);
//                        ajukan.TabDiagnosa1.setSelectedIndex(0);
//                        ajukan.TabProsedur1.setSelectedIndex(0);
//                        ajukan.emptTeksLAINNYA();
//                        ajukan.setVisible(true);
                        if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    }
                }
            }
        } else {
            if (cekKlaim == 0) {
                if (akses.getkode().equals("Admin Utama")) {
//                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                    ajukan.setLocationRelativeTo(internalFrame1);
//                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                    ajukan.tarifRS(noRawat.getText());
//                    ajukan.TabRawat.setEnabled(true);
//                    ajukan.TabRawat.setSelectedIndex(0);
//                    ajukan.TabDiagnosa1.setSelectedIndex(0);
//                    ajukan.TabProsedur1.setSelectedIndex(0);
//                    ajukan.emptTeksLAINNYA();
//                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    
                } else {
//                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                    ajukan.setLocationRelativeTo(internalFrame1);
//                    ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                    ajukan.tarifRS(noRawat.getText());
//                    ajukan.TabRawat.setEnabledAt(1, false);
//                    ajukan.TabRawat.setEnabledAt(0, true);
//                    ajukan.TabRawat.setSelectedIndex(0);
//                    ajukan.TabDiagnosa1.setSelectedIndex(0);
//                    ajukan.TabProsedur1.setSelectedIndex(0);
//                    ajukan.emptTeksLAINNYA();
//                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                   
                }
            } else if (cekKlaim > 0) {
                if (akses.getkode().equals("Admin Utama")) {
//                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                    ajukan.setLocationRelativeTo(internalFrame1);
//                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                    ajukan.tarifRS(noRawat.getText());
//                    ajukan.TabRawat.setEnabled(true);
//                    ajukan.TabRawat.setSelectedIndex(0);
//                    ajukan.TabDiagnosa1.setSelectedIndex(0);
//                    ajukan.TabProsedur1.setSelectedIndex(0);
//                    ajukan.emptTeksLAINNYA();
//                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                    
                } else {
//                    ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                    ajukan.setLocationRelativeTo(internalFrame1);
//                    ajukan.setKlaimAda(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, "3", "-", tglSep);
//                    ajukan.tarifRS(noRawat.getText());
//                    ajukan.TabRawat.setEnabledAt(1, false);
//                    ajukan.TabRawat.setEnabledAt(0, true);
//                    ajukan.TabRawat.setSelectedIndex(0);
//                    ajukan.TabDiagnosa1.setSelectedIndex(0);
//                    ajukan.TabProsedur1.setSelectedIndex(0);
//                    ajukan.emptTeksLAINNYA();
//                    ajukan.setVisible(true);
                    if (Chktgl.isSelected() == true) {
                            tampilKLAIM();
                        } else {
                            tampilSEP();
                        }
                }
            }
        }
    }
    

    


   
    
    private void setData(String datanya) {
        if (datanya.equals("dari luar")) {
        } else if (datanya.equals("didalam")) {
            noRawat.setText("");
            nosep_klaim.setText("");
            jnsKlaim = "";
        }
    }
    
    public void verifData() {
        cekData = "dari luar";
    }
    
}

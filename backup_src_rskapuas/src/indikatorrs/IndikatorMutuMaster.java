/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgJnsPerawatan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */
package indikatorrs;

import perpustakaan.*;
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
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public final class IndikatorMutuMaster extends javax.swing.JDialog {

    private DefaultTableModel tabMode, tabModeUser, tabModeVariabelMutu;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection koneksi = koneksiDB.condb();
    DlgCariAreaMutu areamutu = new DlgCariAreaMutu(null, true);
    DlgCariAreaMutu areamutu1 = new DlgCariAreaMutu(null, true);
    DlgCariPetugas pegawai = new DlgCariPetugas(null, true);
    DlgCariProfilMutu profilmutu = new DlgCariProfilMutu(null, true);

    /**
     * Creates new form DlgJnsPerawatan
     */
    public IndikatorMutuMaster(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "Kode Mutu", "Kategori", "Nama/Judul", "Definisi Oprasional", "Kriteria inklusi", "Kriteria Eksklusi", "Sumber data", "Tipe Indikator", "Kode Area", "Area Monitoring", "Frekuensi", "Standar", "Pengali", "Satuan", "Masa Berlaku"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbProfilMutu.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbProfilMutu.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProfilMutu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbProfilMutu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            }
        }
        tbProfilMutu.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeUser = new DefaultTableModel(null, new Object[]{
            "Kode Pengguna", "Nama Pengguna", "Hak Akses", "Kode Area", "Nama Area"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbUserMutu.setModel(tabModeUser);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbUserMutu.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbUserMutu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbUserMutu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            }
        }
        tbUserMutu.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeVariabelMutu = new DefaultTableModel(null, new Object[]{
            "Kode Variabel", "Profil Mutu", "Numerator", "Denumerator", "Satuan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbVariabelMutu.setModel(tabModeVariabelMutu);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbVariabelMutu.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbVariabelMutu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbVariabelMutu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            }
        }
        tbVariabelMutu.setDefaultRenderer(Object.class, new WarnaTable());

        KodeProfil.setDocument(new batasInput((byte) 10).getKata(KodeProfil));
        Judul.setDocument(new batasInput((int) 200).getKata(Judul));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari.requestFocus();
        ChkInput.setSelected(true);
        isForm();

        if (TabRawat.getSelectedIndex() == 0) {
            if (koneksiDB.CARICEPAT().equals("aktif")) {
                TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampil();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampil();
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampil();
                        }
                    }
                });
            }

        } else if (TabRawat.getSelectedIndex() == 1) {
            if (koneksiDB.CARICEPAT().equals("aktif")) {
                TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampiluser();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampiluser();
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        if (TCari.getText().length() > 2) {
                            tampiluser();
                        }
                    }
                });
            }
        }

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {

                if (pegawai.getTable().getSelectedRow() != -1) {
                    KdUserMutu.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                    NmUserMutu.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());

                }

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        areamutu.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {

                if (areamutu.getTable().getSelectedRow() != -1) {
                    AreaMonitoring.setText(areamutu.getTable().getValueAt(areamutu.getTable().getSelectedRow(), 0).toString());
                    NamaAreaMonitoring.setText(areamutu.getTable().getValueAt(areamutu.getTable().getSelectedRow(), 1).toString());

                }

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        areamutu1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {

                if (areamutu1.getTable().getSelectedRow() != -1) {
                    AreaUser.setText(areamutu1.getTable().getValueAt(areamutu1.getTable().getSelectedRow(), 0).toString());
                    NamaAreaMonitoringUser.setText(areamutu1.getTable().getValueAt(areamutu1.getTable().getSelectedRow(), 1).toString());

                }

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        profilmutu.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {

                if (profilmutu.getTable().getSelectedRow() != -1) {
                    KdProfilMutu.setText(profilmutu.getTable().getValueAt(profilmutu.getTable().getSelectedRow(), 0).toString());
                    NmProfilMutu.setText(profilmutu.getTable().getValueAt(profilmutu.getTable().getSelectedRow(), 2).toString());

                }

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

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
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        label1 = new widget.Label();
        KodeProfil = new widget.TextBox();
        Judul = new widget.TextBox();
        label8 = new widget.Label();
        label10 = new widget.Label();
        cmbKategori = new widget.ComboBox();
        label21 = new widget.Label();
        label11 = new widget.Label();
        label12 = new widget.Label();
        label13 = new widget.Label();
        SumberData = new widget.TextBox();
        label14 = new widget.Label();
        label15 = new widget.Label();
        NamaAreaMonitoring = new widget.TextBox();
        label16 = new widget.Label();
        label17 = new widget.Label();
        StandarProfi = new widget.TextBox();
        Pengali = new widget.TextBox();
        Satuan = new widget.TextBox();
        label19 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        DefOp = new widget.TextArea();
        scrollPane8 = new widget.ScrollPane();
        KritInklusi = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        KritEksl = new widget.TextArea();
        cmbTipeIndikator = new widget.ComboBox();
        cmbFrekuensi = new widget.ComboBox();
        btnPengarang = new widget.Button();
        AreaMonitoring = new widget.TextBox();
        label20 = new widget.Label();
        MasaBerlaku = new widget.Tanggal();
        label22 = new widget.Label();
        ChkInput = new widget.CekBox();
        Scroll = new widget.ScrollPane();
        tbProfilMutu = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        FormInput1 = new widget.PanelBiasa();
        label2 = new widget.Label();
        label18 = new widget.Label();
        cmbHakAksesUser = new widget.ComboBox();
        label23 = new widget.Label();
        NamaAreaMonitoringUser = new widget.TextBox();
        btnPengarang1 = new widget.Button();
        AreaUser = new widget.TextBox();
        KdUserMutu = new widget.TextBox();
        NmUserMutu = new widget.TextBox();
        btnPengarang2 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbUserMutu = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        PanelInput2 = new javax.swing.JPanel();
        FormInput2 = new widget.PanelBiasa();
        label3 = new widget.Label();
        label24 = new widget.Label();
        label25 = new widget.Label();
        btnPengarang3 = new widget.Button();
        KdProfilMutu = new widget.TextBox();
        SatuanVariable = new widget.TextBox();
        label26 = new widget.Label();
        KdVariabel = new widget.TextBox();
        label4 = new widget.Label();
        NmProfilMutu = new widget.TextBox();
        Numerator = new widget.TextBox();
        Denumerator = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbVariabelMutu = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 0), 2, true), "::[ Indikator Mutu Rumah Sakit ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(192, 300));
        FormInput.setLayout(null);

        label1.setText("Kode Profil : ");
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(0, 10, 120, 23);

        KodeProfil.setName("KodeProfil"); // NOI18N
        KodeProfil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeProfilKeyPressed(evt);
            }
        });
        FormInput.add(KodeProfil);
        KodeProfil.setBounds(120, 10, 130, 23);

        Judul.setName("Judul"); // NOI18N
        Judul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JudulKeyPressed(evt);
            }
        });
        FormInput.add(Judul);
        Judul.setBounds(120, 70, 430, 23);

        label8.setText("Definisi Op : ");
        label8.setName("label8"); // NOI18N
        FormInput.add(label8);
        label8.setBounds(10, 100, 100, 23);

        label10.setText("Judul : ");
        label10.setName("label10"); // NOI18N
        FormInput.add(label10);
        label10.setBounds(0, 70, 110, 23);

        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Klinik", "Manajemen", "Medis", "Lainnya" }));
        cmbKategori.setName("cmbKategori"); // NOI18N
        cmbKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKategoriKeyPressed(evt);
            }
        });
        FormInput.add(cmbKategori);
        cmbKategori.setBounds(120, 40, 140, 23);

        label21.setText("Kategori :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label21);
        label21.setBounds(0, 40, 110, 23);

        label11.setText("Kriteria Inklusi : ");
        label11.setName("label11"); // NOI18N
        FormInput.add(label11);
        label11.setBounds(10, 150, 100, 23);

        label12.setText("Kriteria Eksklusi  :");
        label12.setName("label12"); // NOI18N
        FormInput.add(label12);
        label12.setBounds(10, 200, 100, 23);

        label13.setText("Sumber Data : ");
        label13.setName("label13"); // NOI18N
        FormInput.add(label13);
        label13.setBounds(560, 10, 100, 23);

        SumberData.setName("SumberData"); // NOI18N
        SumberData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SumberDataKeyPressed(evt);
            }
        });
        FormInput.add(SumberData);
        SumberData.setBounds(670, 10, 261, 23);

        label14.setText("Tipe Indikator : ");
        label14.setName("label14"); // NOI18N
        FormInput.add(label14);
        label14.setBounds(560, 40, 100, 23);

        label15.setText("Area : ");
        label15.setName("label15"); // NOI18N
        FormInput.add(label15);
        label15.setBounds(560, 70, 100, 23);

        NamaAreaMonitoring.setName("NamaAreaMonitoring"); // NOI18N
        NamaAreaMonitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaAreaMonitoringKeyPressed(evt);
            }
        });
        FormInput.add(NamaAreaMonitoring);
        NamaAreaMonitoring.setBounds(750, 70, 170, 23);

        label16.setText("Frekuensi : ");
        label16.setName("label16"); // NOI18N
        FormInput.add(label16);
        label16.setBounds(560, 100, 100, 23);

        label17.setText("Standar : ");
        label17.setName("label17"); // NOI18N
        FormInput.add(label17);
        label17.setBounds(560, 130, 100, 23);

        StandarProfi.setName("StandarProfi"); // NOI18N
        StandarProfi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StandarProfiKeyPressed(evt);
            }
        });
        FormInput.add(StandarProfi);
        StandarProfi.setBounds(670, 130, 60, 23);

        Pengali.setName("Pengali"); // NOI18N
        Pengali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PengaliKeyPressed(evt);
            }
        });
        FormInput.add(Pengali);
        Pengali.setBounds(670, 160, 60, 23);

        Satuan.setName("Satuan"); // NOI18N
        Satuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SatuanKeyPressed(evt);
            }
        });
        FormInput.add(Satuan);
        Satuan.setBounds(800, 160, 130, 23);

        label19.setText("Satuan : ");
        label19.setName("label19"); // NOI18N
        FormInput.add(label19);
        label19.setBounds(730, 160, 70, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        DefOp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DefOp.setColumns(20);
        DefOp.setRows(5);
        DefOp.setName("DefOp"); // NOI18N
        DefOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DefOpKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(DefOp);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(120, 100, 420, 43);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        KritInklusi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KritInklusi.setColumns(20);
        KritInklusi.setRows(5);
        KritInklusi.setName("KritInklusi"); // NOI18N
        KritInklusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KritInklusiKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(KritInklusi);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(120, 150, 420, 43);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        KritEksl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KritEksl.setColumns(20);
        KritEksl.setRows(5);
        KritEksl.setName("KritEksl"); // NOI18N
        KritEksl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KritEkslKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(KritEksl);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(120, 200, 420, 43);

        cmbTipeIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Input", "Proses", "Outcome", "Outcome and Proses" }));
        cmbTipeIndikator.setName("cmbTipeIndikator"); // NOI18N
        cmbTipeIndikator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipeIndikatorKeyPressed(evt);
            }
        });
        FormInput.add(cmbTipeIndikator);
        cmbTipeIndikator.setBounds(670, 40, 140, 23);

        cmbFrekuensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Harian", "Mingguan", "Bulanan", "Tahunan" }));
        cmbFrekuensi.setName("cmbFrekuensi"); // NOI18N
        cmbFrekuensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbFrekuensiKeyPressed(evt);
            }
        });
        FormInput.add(cmbFrekuensi);
        cmbFrekuensi.setBounds(670, 100, 140, 23);

        btnPengarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPengarang.setMnemonic('1');
        btnPengarang.setToolTipText("Alt+1");
        btnPengarang.setName("btnPengarang"); // NOI18N
        btnPengarang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengarangActionPerformed(evt);
            }
        });
        FormInput.add(btnPengarang);
        btnPengarang.setBounds(920, 70, 25, 23);

        AreaMonitoring.setName("AreaMonitoring"); // NOI18N
        AreaMonitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AreaMonitoringKeyPressed(evt);
            }
        });
        FormInput.add(AreaMonitoring);
        AreaMonitoring.setBounds(671, 70, 80, 23);

        label20.setText("Masa berlaku : ");
        label20.setName("label20"); // NOI18N
        FormInput.add(label20);
        label20.setBounds(560, 190, 100, 23);

        MasaBerlaku.setForeground(new java.awt.Color(50, 70, 50));
        MasaBerlaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-12-2022" }));
        MasaBerlaku.setDisplayFormat("dd-MM-yyyy");
        MasaBerlaku.setName("MasaBerlaku"); // NOI18N
        MasaBerlaku.setOpaque(false);
        MasaBerlaku.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(MasaBerlaku);
        MasaBerlaku.setBounds(670, 190, 110, 23);

        label22.setText("Faktor Pengali : ");
        label22.setName("label22"); // NOI18N
        FormInput.add(label22);
        label22.setBounds(560, 160, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 302));

        tbProfilMutu.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbProfilMutu.setName("tbProfilMutu"); // NOI18N
        tbProfilMutu.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 200));
        tbProfilMutu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProfilMutuMouseClicked(evt);
            }
        });
        tbProfilMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProfilMutuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbProfilMutuKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbProfilMutu);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Profil Mutu", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 120));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(192, 300));
        FormInput1.setLayout(null);

        label2.setText("Pengguna : ");
        label2.setName("label2"); // NOI18N
        FormInput1.add(label2);
        label2.setBounds(-10, 10, 120, 23);

        label18.setText("Area : ");
        label18.setName("label18"); // NOI18N
        FormInput1.add(label18);
        label18.setBounds(0, 70, 110, 23);

        cmbHakAksesUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "Penanggung Jawab", "Koordinator", "Pengguna" }));
        cmbHakAksesUser.setName("cmbHakAksesUser"); // NOI18N
        cmbHakAksesUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHakAksesUserKeyPressed(evt);
            }
        });
        FormInput1.add(cmbHakAksesUser);
        cmbHakAksesUser.setBounds(120, 40, 140, 23);

        label23.setText("Hak Akses : ");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput1.add(label23);
        label23.setBounds(0, 40, 110, 23);

        NamaAreaMonitoringUser.setName("NamaAreaMonitoringUser"); // NOI18N
        NamaAreaMonitoringUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaAreaMonitoringUserKeyPressed(evt);
            }
        });
        FormInput1.add(NamaAreaMonitoringUser);
        NamaAreaMonitoringUser.setBounds(200, 70, 210, 23);

        btnPengarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPengarang1.setMnemonic('1');
        btnPengarang1.setToolTipText("Alt+1");
        btnPengarang1.setName("btnPengarang1"); // NOI18N
        btnPengarang1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPengarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengarang1ActionPerformed(evt);
            }
        });
        FormInput1.add(btnPengarang1);
        btnPengarang1.setBounds(420, 10, 25, 23);

        AreaUser.setName("AreaUser"); // NOI18N
        AreaUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AreaUserKeyPressed(evt);
            }
        });
        FormInput1.add(AreaUser);
        AreaUser.setBounds(120, 70, 80, 23);

        KdUserMutu.setName("KdUserMutu"); // NOI18N
        KdUserMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdUserMutuKeyPressed(evt);
            }
        });
        FormInput1.add(KdUserMutu);
        KdUserMutu.setBounds(120, 10, 80, 23);

        NmUserMutu.setName("NmUserMutu"); // NOI18N
        NmUserMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmUserMutuKeyPressed(evt);
            }
        });
        FormInput1.add(NmUserMutu);
        NmUserMutu.setBounds(200, 10, 210, 23);

        btnPengarang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPengarang2.setMnemonic('1');
        btnPengarang2.setToolTipText("Alt+1");
        btnPengarang2.setName("btnPengarang2"); // NOI18N
        btnPengarang2.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPengarang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengarang2ActionPerformed(evt);
            }
        });
        FormInput1.add(btnPengarang2);
        btnPengarang2.setBounds(420, 70, 25, 23);

        PanelInput1.add(FormInput1, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 302));

        tbUserMutu.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbUserMutu.setName("tbUserMutu"); // NOI18N
        tbUserMutu.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 200));
        tbUserMutu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUserMutuMouseClicked(evt);
            }
        });
        tbUserMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUserMutuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbUserMutuKeyReleased(evt);
            }
        });
        Scroll1.setViewportView(tbUserMutu);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pengguna", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 170));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(192, 300));
        FormInput2.setLayout(null);

        label3.setText("Profil Mutu : ");
        label3.setName("label3"); // NOI18N
        FormInput2.add(label3);
        label3.setBounds(-10, 40, 120, 23);

        label24.setText("Satuan : ");
        label24.setName("label24"); // NOI18N
        FormInput2.add(label24);
        label24.setBounds(0, 130, 110, 23);

        label25.setText("Numerator : ");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput2.add(label25);
        label25.setBounds(0, 70, 110, 23);

        btnPengarang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPengarang3.setMnemonic('1');
        btnPengarang3.setToolTipText("Alt+1");
        btnPengarang3.setName("btnPengarang3"); // NOI18N
        btnPengarang3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPengarang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengarang3ActionPerformed(evt);
            }
        });
        FormInput2.add(btnPengarang3);
        btnPengarang3.setBounds(640, 40, 25, 23);

        KdProfilMutu.setName("KdProfilMutu"); // NOI18N
        KdProfilMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdProfilMutuKeyPressed(evt);
            }
        });
        FormInput2.add(KdProfilMutu);
        KdProfilMutu.setBounds(120, 40, 150, 23);

        SatuanVariable.setName("SatuanVariable"); // NOI18N
        SatuanVariable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SatuanVariableKeyPressed(evt);
            }
        });
        FormInput2.add(SatuanVariable);
        SatuanVariable.setBounds(120, 130, 120, 23);

        label26.setText("Denumerator : ");
        label26.setName("label26"); // NOI18N
        FormInput2.add(label26);
        label26.setBounds(0, 100, 110, 23);

        KdVariabel.setName("KdVariabel"); // NOI18N
        KdVariabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdVariabelKeyPressed(evt);
            }
        });
        FormInput2.add(KdVariabel);
        KdVariabel.setBounds(120, 10, 150, 23);

        label4.setText("Kode Variabel : ");
        label4.setName("label4"); // NOI18N
        FormInput2.add(label4);
        label4.setBounds(-10, 10, 120, 23);

        NmProfilMutu.setName("NmProfilMutu"); // NOI18N
        NmProfilMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmProfilMutuKeyPressed(evt);
            }
        });
        FormInput2.add(NmProfilMutu);
        NmProfilMutu.setBounds(270, 40, 370, 23);

        Numerator.setName("Numerator"); // NOI18N
        Numerator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NumeratorKeyPressed(evt);
            }
        });
        FormInput2.add(Numerator);
        Numerator.setBounds(120, 70, 520, 23);

        Denumerator.setName("Denumerator"); // NOI18N
        Denumerator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DenumeratorKeyPressed(evt);
            }
        });
        FormInput2.add(Denumerator);
        Denumerator.setBounds(120, 100, 520, 23);

        PanelInput2.add(FormInput2, java.awt.BorderLayout.CENTER);

        internalFrame4.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 302));

        tbVariabelMutu.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbVariabelMutu.setName("tbVariabelMutu"); // NOI18N
        tbVariabelMutu.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 200));
        tbVariabelMutu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVariabelMutuMouseClicked(evt);
            }
        });
        tbVariabelMutu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbVariabelMutuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbVariabelMutuKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbVariabelMutu);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Variabel Numerator Denumerator", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
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
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
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
        panelGlass8.add(BtnAll);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (KodeProfil.getText().trim().equals("")) {
                Valid.textKosong(KodeProfil, "Kode Koleksi");
            } else if (Judul.getText().trim().equals("")) {
                Valid.textKosong(Judul, "Judul Profil Mutu");
            } else if (DefOp.getText().trim().equals("")) {
                Valid.textKosong(DefOp, "Definisi Operasional");
            } else if (KritInklusi.getText().trim().equals("") || KritEksl.getText().trim().equals("")) {
                Valid.textKosong(KritInklusi, "Kriteria Inklusi");
            } else if (SumberData.getText().trim().equals("")) {
                Valid.textKosong(SumberData, "Sumber Data");
            } else if (StandarProfi.getText().trim().equals("") || Pengali.getText().trim().equals("") || Satuan.getText().trim().equals("")) {
                Valid.textKosong(StandarProfi, "Standar");
            } else {
                if (Sequel.menyimpantf("indikatormutu_profilmutu", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Kode Profil", 14, new String[]{
                    KodeProfil.getText(),
                    cmbKategori.getSelectedItem().toString(),
                    Judul.getText(),
                    DefOp.getText(),
                    KritInklusi.getText(),
                    KritEksl.getText(),
                    SumberData.getText(),
                    cmbTipeIndikator.getSelectedItem().toString(),
                    AreaMonitoring.getText(),
                    cmbFrekuensi.getSelectedItem().toString(),
                    StandarProfi.getText(),
                    Pengali.getText(), Satuan.getText(), Valid.SetTgl(MasaBerlaku.getSelectedItem().toString())}) == true) {

                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampil();
                }

            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (KdUserMutu.getText().trim().equals("")) {
                Valid.textKosong(KdUserMutu, "Kode Koleksi");
            } else if (cmbHakAksesUser.getSelectedItem().toString().equals("Pilih")) {
                Valid.textKosong(cmbHakAksesUser, "Hak Akses");
            } else if (AreaUser.getText().trim().equals("")) {
                Valid.textKosong(AreaUser, "Area");
            } else {
                if (Sequel.menyimpantf("indikatormutu_koordinator", "?,?,?", "Kode Profil", 3, new String[]{
                    KdUserMutu.getText(),
                    cmbHakAksesUser.getSelectedItem().toString(),
                    AreaUser.getText()}) == true) {
                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampiluser();
                }

            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (KdVariabel.getText().trim().equals("")) {
                Valid.textKosong(KdVariabel, "Kode Variabel");
            } else if (KdProfilMutu.getText().equals("")) {
                Valid.textKosong(KdProfilMutu, "Profil Mutu");
            } else if (Numerator.getText().trim().equals("")) {
                Valid.textKosong(Numerator, "Numerator");
            } else if (Denumerator.getText().trim().equals("")) {
                Valid.textKosong(Denumerator, "Denumerator");
            } else if (SatuanVariable.getText().trim().equals("")) {
                Valid.textKosong(SatuanVariable, "Satuan");
            } else {
                if (Sequel.menyimpantf("indikatormutu_profil_variabel", "?,?,?,?,?", "Kode Variabel", 5, new String[]{
                    KdVariabel.getText(),
                    KdProfilMutu.getText(),
                    Numerator.getText(),
                    Denumerator.getText(),
                    SatuanVariable.getText()}) == true) {
                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampilvariabel();
                }

            }
        }

}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, KodeProfil, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            String kodeprofil = tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 3).toString();
            Valid.hapusTable(tabMode, KodeProfil, "indikatormutu_profilmutu", "no_profil");

        } else if (TabRawat.getSelectedIndex() == 1) {
            String kodeuser = tbUserMutu.getValueAt(tbUserMutu.getSelectedRow(), 0).toString();
            Valid.hapusTable(tabModeUser, KdUserMutu, "indikatormutu_koordinator", "kd_pengguna");
        } else if (TabRawat.getSelectedIndex() == 2) {
            Valid.hapusTable(tabModeVariabelMutu, KdVariabel, "indikatormutu_profil_variabel", "id_variable");
            tampilvariabel();
        }

        BtnCariActionPerformed(evt);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (KodeProfil.getText().trim().equals("")) {
                Valid.textKosong(KodeProfil, "Kode Koleksi");
            } else if (Judul.getText().trim().equals("")) {
                Valid.textKosong(Judul, "Judul Profil Mutu");
            } else if (DefOp.getText().trim().equals("")) {
                Valid.textKosong(DefOp, "Definisi Operasional");
            } else if (KritInklusi.getText().trim().equals("") || KritEksl.getText().trim().equals("")) {
                Valid.textKosong(KritInklusi, "Kriteria Inklusi");
            } else if (SumberData.getText().trim().equals("")) {
                Valid.textKosong(SumberData, "Sumber Data");
            } else if (StandarProfi.getText().trim().equals("") || Pengali.getText().trim().equals("") || Satuan.getText().trim().equals("")) {
                Valid.textKosong(StandarProfi, "Standar");
            } else {
                if (tbProfilMutu.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("indikatormutu_profilmutu", "no_profil=?", "no_profil=?,kategori=?,judul=?,definisiop=?,kriteriainklusi=?,kriteria_ekslusi=?,sumberdata=?,tipe_indikator=?,id_ruang=?,frekuensi=?,standar=?,faktorpengali=?,satuan=?,masaberlaku=?", 15, new String[]{
                        KodeProfil.getText(),
                        cmbKategori.getSelectedItem().toString(),
                        Judul.getText(),
                        DefOp.getText(),
                        KritInklusi.getText(),
                        KritEksl.getText(),
                        SumberData.getText(),
                        cmbTipeIndikator.getSelectedItem().toString(),
                        AreaMonitoring.getText(),
                        cmbFrekuensi.getSelectedItem().toString(),
                        StandarProfi.getText(),
                        Pengali.getText(),
                        Satuan.getText(), Valid.SetTgl(MasaBerlaku.getSelectedItem().toString()), tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 0).toString()
                    }) == true) {
                        tampil();
                        emptTeks();
                        JOptionPane.showMessageDialog(null, "Data berhasil di update");
//                    TabRawat.setSelectedIndex(1);
                    }
                    KodeProfil.requestFocus();
                    tampil();
                    emptTeks();
                }
            }

        } else if (TabRawat.getSelectedIndex() == 1) {
            if (KdUserMutu.getText().trim().equals("")) {
                Valid.textKosong(KdUserMutu, "Kode Koleksi");
            } else if (cmbHakAksesUser.getSelectedItem().toString().equals("Pilih")) {
                Valid.textKosong(cmbHakAksesUser, "Hak Akses");
            } else if (AreaUser.getText().trim().equals("")) {
                Valid.textKosong(AreaUser, "Area");
            } else {
                if (Sequel.mengedittf("indikatormutu_koordinator", "kd_pengguna=?", "kd_pengguna=?,hak_akses=?,kd_area=?", 4, new String[]{
                    KdUserMutu.getText(),
                    cmbHakAksesUser.getSelectedItem().toString(),
                    AreaUser.getText(),
                    tbUserMutu.getValueAt(tbUserMutu.getSelectedRow(), 0).toString()
                }) == true) {
                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampiluser();
                }

            }

        } else if (TabRawat.getSelectedIndex() == 2) {
            if (KdVariabel.getText().trim().equals("")) {
                Valid.textKosong(KdVariabel, "Kode Variabel");
            } else if (KdProfilMutu.getText().equals("")) {
                Valid.textKosong(KdProfilMutu, "Profil Mutu");
            } else if (Numerator.getText().trim().equals("")) {
                Valid.textKosong(Numerator, "Numerator");
            } else if (Denumerator.getText().trim().equals("")) {
                Valid.textKosong(Denumerator, "Denumerator");
            } else if (SatuanVariable.getText().trim().equals("")) {
                Valid.textKosong(SatuanVariable, "Satuan");
            } else {
                if (Sequel.mengedittf("indikatormutu_profil_variabel", "id_variable=?", "no_profil=?,numerator=?,denumerator=?,satuan=?", 5, new String[]{
                    KdProfilMutu.getText(),
                    Numerator.getText(),
                    Denumerator.getText(),
                    SatuanVariable.getText(),
                    tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 0).toString()
                }) == true) {
                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampilvariabel();
                }

            }

        }

}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JOptionPane.showMessageDialog(null, "Maaf, belum dibuat fitur ini");
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProfilMutu.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampiluser();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilvariabel();
        }

}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbProfilMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProfilMutuMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbProfilMutuMouseClicked

    private void tbProfilMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProfilMutuKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbProfilMutuKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void KodeProfilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProfilKeyPressed
        Valid.pindah(evt, KodeProfil, cmbKategori);
}//GEN-LAST:event_KodeProfilKeyPressed

private void cmbKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKategoriKeyPressed
        Valid.pindah(evt, KodeProfil, Judul);
}//GEN-LAST:event_cmbKategoriKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbProfilMutuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProfilMutuKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbProfilMutuKeyReleased

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampiluser();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilvariabel();
        }
//        if (TabRawat.getSelectedIndex() == 1) {
//            tampilObvObat();
//        }
//        if (TabRawat.getSelectedIndex() == 2) {
//            tampilObvNyeri();
//        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void SumberDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SumberDataKeyPressed
        Valid.pindah(evt, KritEksl, cmbTipeIndikator);
    }//GEN-LAST:event_SumberDataKeyPressed

    private void NamaAreaMonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaAreaMonitoringKeyPressed
        Valid.pindah(evt, cmbTipeIndikator, cmbFrekuensi);
    }//GEN-LAST:event_NamaAreaMonitoringKeyPressed

    private void StandarProfiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StandarProfiKeyPressed
        Valid.pindah(evt, cmbFrekuensi, Pengali);
    }//GEN-LAST:event_StandarProfiKeyPressed

    private void PengaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengaliKeyPressed
        Valid.pindah(evt, StandarProfi, Satuan);
    }//GEN-LAST:event_PengaliKeyPressed

    private void SatuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SatuanKeyPressed
        Valid.pindah(evt, Pengali, BtnSimpan);
    }//GEN-LAST:event_SatuanKeyPressed

    private void JudulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JudulKeyPressed
        Valid.pindah(evt, cmbKategori, DefOp);
    }//GEN-LAST:event_JudulKeyPressed

    private void DefOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DefOpKeyPressed
        Valid.pindah2(evt, Judul, KritInklusi);
    }//GEN-LAST:event_DefOpKeyPressed

    private void KritInklusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KritInklusiKeyPressed
        Valid.pindah2(evt, DefOp, KritInklusi);
    }//GEN-LAST:event_KritInklusiKeyPressed

    private void KritEkslKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KritEkslKeyPressed
        Valid.pindah2(evt, KritInklusi, SumberData);
    }//GEN-LAST:event_KritEkslKeyPressed

    private void cmbTipeIndikatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipeIndikatorKeyPressed
        Valid.pindah2(evt, SumberData, NamaAreaMonitoring);
    }//GEN-LAST:event_cmbTipeIndikatorKeyPressed

    private void cmbFrekuensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFrekuensiKeyPressed
        Valid.pindah2(evt, NamaAreaMonitoring, StandarProfi);
    }//GEN-LAST:event_cmbFrekuensiKeyPressed

    private void btnPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengarangActionPerformed
        areamutu.isCek();
        areamutu.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        areamutu.setLocationRelativeTo(null);
        areamutu.setVisible(true);
    }//GEN-LAST:event_btnPengarangActionPerformed

    private void AreaMonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaMonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AreaMonitoringKeyPressed

    private void tbUserMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUserMutuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbUserMutuMouseClicked

    private void tbUserMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUserMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbUserMutuKeyPressed

    private void tbUserMutuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUserMutuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbUserMutuKeyReleased

    private void AreaUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaUserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AreaUserKeyPressed

    private void btnPengarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengarang1ActionPerformed
        pegawai.setSize(internalFrame3.getWidth() - 20, internalFrame3.getHeight() - 20);
        pegawai.setLocationRelativeTo(null);
        pegawai.setVisible(true);
    }//GEN-LAST:event_btnPengarang1ActionPerformed

    private void NamaAreaMonitoringUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaAreaMonitoringUserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaAreaMonitoringUserKeyPressed

    private void cmbHakAksesUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHakAksesUserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHakAksesUserKeyPressed

    private void KdUserMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdUserMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdUserMutuKeyPressed

    private void NmUserMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmUserMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmUserMutuKeyPressed

    private void btnPengarang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengarang2ActionPerformed
        areamutu1.isCek();
        areamutu1.setSize(internalFrame3.getWidth() - 20, internalFrame3.getHeight() - 20);
        areamutu1.setLocationRelativeTo(null);
        areamutu1.setVisible(true);
    }//GEN-LAST:event_btnPengarang2ActionPerformed

    private void btnPengarang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengarang3ActionPerformed
        profilmutu.isCek();
        profilmutu.setSize(internalFrame3.getWidth() - 20, internalFrame3.getHeight() - 20);
        profilmutu.setLocationRelativeTo(null);
        profilmutu.setVisible(true);
    }//GEN-LAST:event_btnPengarang3ActionPerformed

    private void KdProfilMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdProfilMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdProfilMutuKeyPressed

    private void SatuanVariableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SatuanVariableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SatuanVariableKeyPressed

    private void tbVariabelMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVariabelMutuMouseClicked
        if (tabModeVariabelMutu.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbVariabelMutuMouseClicked

    private void tbVariabelMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbVariabelMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVariabelMutuKeyPressed

    private void tbVariabelMutuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbVariabelMutuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVariabelMutuKeyReleased

    private void KdVariabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdVariabelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdVariabelKeyPressed

    private void NmProfilMutuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmProfilMutuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmProfilMutuKeyPressed

    private void NumeratorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeratorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeratorKeyPressed

    private void DenumeratorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DenumeratorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DenumeratorKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            IndikatorMutuMaster dialog = new IndikatorMutuMaster(new javax.swing.JFrame(), true);
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
    private widget.TextBox AreaMonitoring;
    private widget.TextBox AreaUser;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.TextArea DefOp;
    private widget.TextBox Denumerator;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.TextBox Judul;
    private widget.TextBox KdProfilMutu;
    private widget.TextBox KdUserMutu;
    private widget.TextBox KdVariabel;
    private widget.TextBox KodeProfil;
    private widget.TextArea KritEksl;
    private widget.TextArea KritInklusi;
    private widget.Label LCount;
    private widget.Tanggal MasaBerlaku;
    private widget.TextBox NamaAreaMonitoring;
    private widget.TextBox NamaAreaMonitoringUser;
    private widget.TextBox NmProfilMutu;
    private widget.TextBox NmUserMutu;
    private widget.TextBox Numerator;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private widget.TextBox Pengali;
    private widget.TextBox Satuan;
    private widget.TextBox SatuanVariable;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox StandarProfi;
    private widget.TextBox SumberData;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Button btnPengarang;
    private widget.Button btnPengarang1;
    private widget.Button btnPengarang2;
    private widget.Button btnPengarang3;
    private widget.ComboBox cmbFrekuensi;
    private widget.ComboBox cmbHakAksesUser;
    private widget.ComboBox cmbKategori;
    private widget.ComboBox cmbTipeIndikator;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label2;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label3;
    private widget.Label label4;
    private widget.Label label8;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbProfilMutu;
    private widget.Table tbUserMutu;
    private widget.Table tbVariabelMutu;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT\n"
                    + "	indikatormutu_profilmutu.no_profil, \n"
                    + "	indikatormutu_profilmutu.kategori, \n"
                    + "	indikatormutu_profilmutu.judul, \n"
                    + "	indikatormutu_profilmutu.definisiop, \n"
                    + "	indikatormutu_profilmutu.kriteriainklusi, \n"
                    + "	indikatormutu_profilmutu.kriteria_ekslusi, \n"
                    + "	indikatormutu_profilmutu.sumberdata, \n"
                    + "	indikatormutu_profilmutu.tipe_indikator, \n"
                    + "	indikatormutu_profilmutu.frekuensi, \n"
                    + "	indikatormutu_profilmutu.id_ruang, \n"
                    + "	indikatormutu_areamutu.nama_ruang, \n"
                    + "	indikatormutu_profilmutu.standar, \n"
                    + "	indikatormutu_profilmutu.faktorpengali, \n"
                    + "	indikatormutu_profilmutu.satuan,indikatormutu_profilmutu.masaberlaku \n"
                    + "FROM\n"
                    + "	indikatormutu_profilmutu join indikatormutu_areamutu on indikatormutu_profilmutu.id_ruang=indikatormutu_areamutu.id_ruang\n"
                    + "WHERE\n"
                    + "	indikatormutu_profilmutu.judul LIKE ? OR\n"
                    + "	indikatormutu_profilmutu.kategori LIKE ? OR\n"
                    + "	indikatormutu_areamutu.nama_ruang LIKE ? OR\n"
                    + "	indikatormutu_profilmutu.definisiop LIKE ? ORDER BY indikatormutu_profilmutu.no_profil ");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_profil"), rs.getString("kategori"), rs.getString("judul"), rs.getString("definisiop"),
                        rs.getString("kriteriainklusi"), rs.getString("kriteria_ekslusi"), rs.getString("sumberdata"),
                        rs.getString("tipe_indikator"), rs.getString("id_ruang"), rs.getString("nama_ruang"), rs.getString("frekuensi"), rs.getString("standar"), rs.getString("faktorpengali"), rs.getString("satuan"), rs.getString("masaberlaku")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampiluser() {
        Valid.tabelKosong(tabModeUser);
        try {
            ps = koneksi.prepareStatement("SELECT\n"
                    + "	indikatormutu_koordinator.kd_pengguna, \n"
                    + "	indikatormutu_koordinator.hak_akses, \n"
                    + "	indikatormutu_koordinator.kd_area, \n"
                    + "	indikatormutu_areamutu.nama_ruang, \n"
                    + "	petugas.nama \n"
                    + "FROM\n"
                    + "	indikatormutu_koordinator INNER JOIN indikatormutu_areamutu on indikatormutu_koordinator.kd_area=indikatormutu_areamutu.id_ruang JOIN petugas ON indikatormutu_koordinator.kd_pengguna=petugas.nip \n"
                    + "WHERE\n"
                    + "	indikatormutu_koordinator.hak_akses LIKE ? OR\n"
                    + "	petugas.nama LIKE ? OR\n"
                    + "	indikatormutu_areamutu.nama_ruang LIKE ? ORDER BY petugas.nama ");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeUser.addRow(new Object[]{
                        rs.getString("kd_pengguna"), rs.getString("nama"), rs.getString("hak_akses"), rs.getString("kd_area"),
                        rs.getString("nama_ruang")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void tampilvariabel() {
        Valid.tabelKosong(tabModeVariabelMutu);
        try {
            ps = koneksi.prepareStatement("SELECT\n"
                    + "	indikatormutu_profil_variabel.id_variable, \n"
                    + "	indikatormutu_profilmutu.judul, \n"
                    + "	indikatormutu_profil_variabel.numerator, \n"
                    + "	indikatormutu_profil_variabel.denumerator, \n"
                    + "	indikatormutu_profil_variabel.satuan\n"
                    + "FROM\n"
                    + "	indikatormutu_profil_variabel\n"
                    + "	INNER JOIN\n"
                    + "	indikatormutu_profilmutu\n"
                    + "	ON \n"
                    + "		indikatormutu_profil_variabel.no_profil = indikatormutu_profilmutu.no_profil "
                    + "WHERE indikatormutu_profilmutu.judul LIKE ? OR indikatormutu_profil_variabel.numerator LIKE ? OR indikatormutu_profil_variabel.denumerator LIKE ? ORDER BY indikatormutu_profil_variabel.id_variable");

            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeVariabelMutu.addRow(new Object[]{
                        rs.getString("id_variable"), rs.getString("judul"), rs.getString("numerator"), rs.getString("denumerator"),
                        rs.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModeVariabelMutu.getRowCount());
    }

    public void emptTeks() {
        KodeProfil.setText("");
        cmbKategori.setSelectedIndex(0);
        Judul.setText("");
        TCari.setText("");
        KodeProfil.requestFocus();
        cmbKategori.setSelectedIndex(0);
        DefOp.setText("");
        KritInklusi.setText("");
        KritEksl.setText("");
        SumberData.setText("");
        cmbTipeIndikator.setSelectedIndex(0);
        AreaMonitoring.setText("");
        cmbFrekuensi.setSelectedIndex(0);
        StandarProfi.setText("");
        Pengali.setText("");
        Satuan.setText(""); //Valid.autoNomer(" jns_perawatan ","JP",6,TKd);
        KdVariabel.setText("");
        KdProfilMutu.setText("");
        Numerator.setText("");
        Denumerator.setText("");
        SatuanVariable.getText();
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_profil,8),signed)),0) from indikatormutu_profilmutu  ", "IMURS", 8, KodeProfil);
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_profil,8),signed)),0) from indikatormutu_profil_variabel  ", "IMV", 8, KdVariabel);
        KodeProfil.requestFocus();
        KdUserMutu.setText("");
        NmUserMutu.setText("");
        cmbHakAksesUser.setSelectedIndex(0);
        AreaUser.setText("");
        NamaAreaMonitoringUser.setText("");
        NamaAreaMonitoring.setText("");
    }

    private void getData() {

        if (tbProfilMutu.getSelectedRow() != -1) {
            KodeProfil.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 0).toString());
            cmbKategori.setSelectedItem(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 1).toString());
            Judul.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 2).toString());
            KodeProfil.requestFocus();
            DefOp.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 3).toString());
            KritInklusi.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 4).toString());
            KritEksl.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 5).toString());
            SumberData.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 6).toString());
            cmbTipeIndikator.setSelectedItem(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 7).toString());
            AreaMonitoring.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 8).toString());
            NamaAreaMonitoring.setText(Sequel.cariIsi("select indikatormutu_areamutu.nama_ruang from indikatormutu_areamutu where indikatormutu_areamutu.id_ruang='" + AreaMonitoring.getText() + "'"));
            cmbFrekuensi.setSelectedItem(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 10).toString());
            StandarProfi.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 11).toString());
            Pengali.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 12).toString());
            Satuan.setText(tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 13).toString());
            Valid.SetTgl2(MasaBerlaku, tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 14).toString());
        }

        if (tbVariabelMutu.getSelectedRow() != -1) {
            KdVariabel.setText(tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 0).toString());
            KdProfilMutu.setText(Sequel.cariIsi("select indikatormutu_profil_variabel.no_profil from indikatormutu_profil_variabel where indikatormutu_profil_variabel.id_variable='" + tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 0).toString() + "'").toString());
            NmProfilMutu.setText(tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 1).toString());
            Numerator.setText(tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 2).toString());
            Denumerator.setText(tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 3).toString());
            SatuanVariable.setText(tbVariabelMutu.getValueAt(tbVariabelMutu.getSelectedRow(), 4).toString());
        }
    }

    public JTable getTable() {
        return tbProfilMutu;
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 300));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        emptTeks();
        BtnSimpan.setEnabled(akses.getkoleksi_perpustakaan());
        BtnHapus.setEnabled(akses.getkoleksi_perpustakaan());
        BtnEdit.setEnabled(akses.getkoleksi_perpustakaan());
        TCari.requestFocus();
    }

}

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
public final class DlgLaporanDataMutu extends javax.swing.JDialog {

    private DefaultTableModel tabMode, tabModeUser, tabModeVariabelMutu;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int xx = 0;
    private double rerata = 0, ttlrerata = 0;
    private Connection koneksi = koneksiDB.condb();
    DlgCariAreaMutu areamutu = new DlgCariAreaMutu(null, true);
    DlgCariAreaMutu areamutu1 = new DlgCariAreaMutu(null, true);
    DlgCariPetugas pegawai = new DlgCariPetugas(null, true);
    DlgCariProfilMutu profilmutu = new DlgCariProfilMutu(null, true);

    /**
     * Creates new form DlgJnsPerawatan
     */
    public DlgLaporanDataMutu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No Profil", "Id variabel", "Nama/Judul", "Numerator", "Denumerator", "Periode", "Hasil", "Nama Ruang", "Satuan"
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

        for (int i = 0; i < 9; i++) {
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
            }
        }
        tbProfilMutu.setDefaultRenderer(Object.class, new WarnaTable());

        KodeProfil.setDocument(new batasInput((byte) 10).getKata(KodeProfil));
        Judul.setDocument(new batasInput((int) 200).getKata(Judul));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari.requestFocus();

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
                    KodeProfil.setText(profilmutu.getTable().getValueAt(profilmutu.getTable().getSelectedRow(), 0).toString());
                    Judul.setText(profilmutu.getTable().getValueAt(profilmutu.getTable().getSelectedRow(), 2).toString());
                    FaktorPengali.setText(profilmutu.getTable().getValueAt(profilmutu.getTable().getSelectedRow(), 11).toString());
                    KodeVar.setText(Sequel.cariIsi("select indikatormutu_profil_variabel.id_variable from indikatormutu_profil_variabel where indikatormutu_profil_variabel.no_profil='" + KodeProfil.getText() + "'"));
                    labelNumerator.setText(Sequel.cariIsi("select indikatormutu_profil_variabel.numerator from indikatormutu_profil_variabel where indikatormutu_profil_variabel.id_variable='" + KodeVar.getText() + "'"));
                    labelDenumerator.setText(Sequel.cariIsi("select indikatormutu_profil_variabel.denumerator from indikatormutu_profil_variabel where indikatormutu_profil_variabel.id_variable='" + KodeVar.getText() + "'"));
                }

                numerator.requestFocus();

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

        numerator.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isHitungHasil();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isHitungHasil();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isHitungHasil();
            }
        });
        denumerator.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isHitungHasil();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isHitungHasil();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isHitungHasil();
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
        label10 = new widget.Label();
        labelDenumerator = new widget.Label();
        FaktorPengali = new widget.TextBox();
        denumerator = new widget.TextBox();
        PeriodeLaporan = new widget.Tanggal();
        label22 = new widget.Label();
        btnPengarang4 = new widget.Button();
        label27 = new widget.Label();
        label19 = new widget.Label();
        labelNumerator = new widget.Label();
        label5 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        numerator = new widget.TextBox();
        KodeVar = new widget.TextBox();
        hasilhitung = new widget.TextBox();
        label28 = new widget.Label();
        Scroll = new widget.ScrollPane();
        tbProfilMutu = new widget.Table();
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 200));
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
        Judul.setBounds(250, 10, 440, 23);

        label10.setText("Variabel : ");
        label10.setName("label10"); // NOI18N
        FormInput.add(label10);
        label10.setBounds(10, 40, 110, 23);

        labelDenumerator.setForeground(new java.awt.Color(153, 153, 0));
        labelDenumerator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDenumerator.setText("pilih profil mutu dahulu");
        labelDenumerator.setName("labelDenumerator"); // NOI18N
        FormInput.add(labelDenumerator);
        labelDenumerator.setBounds(120, 100, 390, 23);

        FaktorPengali.setName("FaktorPengali"); // NOI18N
        FaktorPengali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorPengaliKeyPressed(evt);
            }
        });
        FormInput.add(FaktorPengali);
        FaktorPengali.setBounds(690, 10, 60, 23);

        denumerator.setName("denumerator"); // NOI18N
        denumerator.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                denumeratorPropertyChange(evt);
            }
        });
        denumerator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                denumeratorKeyPressed(evt);
            }
        });
        FormInput.add(denumerator);
        denumerator.setBounds(530, 100, 60, 23);

        PeriodeLaporan.setForeground(new java.awt.Color(50, 70, 50));
        PeriodeLaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2022" }));
        PeriodeLaporan.setDisplayFormat("dd-MM-yyyy");
        PeriodeLaporan.setName("PeriodeLaporan"); // NOI18N
        PeriodeLaporan.setOpaque(false);
        PeriodeLaporan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(PeriodeLaporan);
        PeriodeLaporan.setBounds(130, 130, 110, 23);

        label22.setText("Periode laporan : ");
        label22.setName("label22"); // NOI18N
        FormInput.add(label22);
        label22.setBounds(20, 130, 100, 23);

        btnPengarang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPengarang4.setMnemonic('1');
        btnPengarang4.setText("Pilih Profil");
        btnPengarang4.setToolTipText("Alt+1");
        btnPengarang4.setName("btnPengarang4"); // NOI18N
        btnPengarang4.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPengarang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengarang4ActionPerformed(evt);
            }
        });
        FormInput.add(btnPengarang4);
        btnPengarang4.setBounds(760, 10, 120, 23);

        label27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label27.setText("Hasil");
        label27.setName("label27"); // NOI18N
        FormInput.add(label27);
        label27.setBounds(610, 80, 90, 10);

        label19.setText("Numerator : ");
        label19.setName("label19"); // NOI18N
        FormInput.add(label19);
        label19.setBounds(20, 70, 100, 23);

        labelNumerator.setForeground(new java.awt.Color(51, 102, 255));
        labelNumerator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNumerator.setText("pilih profil mutu dahulu");
        labelNumerator.setName("labelNumerator"); // NOI18N
        FormInput.add(labelNumerator);
        labelNumerator.setBounds(120, 70, 390, 23);

        label5.setText("Petugas : ");
        label5.setName("label5"); // NOI18N
        FormInput.add(label5);
        label5.setBounds(0, 160, 120, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(120, 160, 130, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        NamaPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPetugasKeyPressed(evt);
            }
        });
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(250, 160, 440, 23);

        numerator.setName("numerator"); // NOI18N
        numerator.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                numeratorPropertyChange(evt);
            }
        });
        numerator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numeratorKeyPressed(evt);
            }
        });
        FormInput.add(numerator);
        numerator.setBounds(530, 70, 60, 23);

        KodeVar.setName("KodeVar"); // NOI18N
        KodeVar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeVarKeyPressed(evt);
            }
        });
        FormInput.add(KodeVar);
        KodeVar.setBounds(120, 40, 130, 23);

        hasilhitung.setEditable(false);
        hasilhitung.setName("hasilhitung"); // NOI18N
        hasilhitung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilhitungKeyPressed(evt);
            }
        });
        FormInput.add(hasilhitung);
        hasilhitung.setBounds(610, 90, 90, 23);

        label28.setText("Denumerator : ");
        label28.setName("label28"); // NOI18N
        FormInput.add(label28);
        label28.setBounds(20, 100, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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

        TabRawat.addTab("Input Laporan Mutu", internalFrame2);

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
            } else if (numerator.getText().trim().equals("")) {
                Valid.textKosong(numerator, "Numerator");
            } else if (denumerator.getText().trim().equals("")) {
                Valid.textKosong(denumerator, "Denumerator");
            } else if (KdPetugas.getText().trim().equals("")) {
                Valid.textKosong(KdPetugas, "Petugas");
            } else {
                if (Sequel.menyimpantf("indikatormutu_laporanhasil", "?,?,?,?,?,?,?,?,?,?", "Kode Profil", 10, new String[]{
                    KodeVar.getText(),
                    KodeProfil.getText(),
                    numerator.getText(),
                    denumerator.getText(),
                    Valid.SetTgl(PeriodeLaporan.getSelectedItem().toString()),
                    Sequel.cariIsi("select now()"),
                    KdPetugas.getText(),
                    Sequel.cariIsi("select now()"),
                    KdPetugas.getText(), hasilhitung.getText()}) == true) {
                    emptTeks();
                    JOptionPane.showMessageDialog(null, "Simpan data berhasil");
                    tampil();
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

        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void isHitungHasil() {
        if ((!numerator.getText().equals("")) && (!denumerator.getText().equals(""))) {
            try {
                double numeratorxx = Valid.SetAngka(numerator.getText().toString());
                double denumeratorxx = Valid.SetAngka(denumerator.getText().toString());
                double pengalixx = Valid.SetAngka(FaktorPengali.getText().toString());
                double hasilhitungxx = (numeratorxx / denumeratorxx);
                double hasilhitungyy = hasilhitungxx * pengalixx;

//                System.out.println(numeratorxx);
//                System.out.println(denumeratorxx);
//                System.out.println(pengalixx);
//                System.out.println(hasilhitungxx);
//                System.out.println(hasilhitungyy);
                hasilhitung.setText(String.valueOf(hasilhitungyy));
            } catch (Exception e) {
                hasilhitung.setText("");
            }
        }
    }
    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            String kodeprofil = tbProfilMutu.getValueAt(tbProfilMutu.getSelectedRow(), 3).toString();
            Valid.hapusTable(tabMode, KodeProfil, "indikatormutu_profilmutu", "no_profil");

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
            } else if (numerator.getText().trim().equals("")) {
                Valid.textKosong(numerator, "Numerator");
            } else if (denumerator.getText().trim().equals("")) {
                Valid.textKosong(denumerator, "Denumerator");
            } else if (KdPetugas.getText().trim().equals("")) {
                Valid.textKosong(KdPetugas, "Petugas");
            } else {
                if (tbProfilMutu.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("indikatormutu_profilmutu", "no_profil=?,id_variable=?", "numerator=?,denumerator=?, tanggalupdate=?,petugas_update=?,hasilhitung=?", 7, new String[]{
                        numerator.getText(),
                        denumerator.getText(),
                        Sequel.cariIsi("select now()"),
                        akses.getkode(),
                        hasilhitung.getText(), KodeProfil.getText(), KodeVar.getText()
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
        JOptionPane.showMessageDialog(null, "Maaf, belum selesai modul print");
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

private void KodeProfilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeProfilKeyPressed

}//GEN-LAST:event_KodeProfilKeyPressed

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
        }
//        if (TabRawat.getSelectedIndex() == 1) {
//            tampilObvObat();
//        }
//        if (TabRawat.getSelectedIndex() == 2) {
//            tampilObvNyeri();
//        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void FaktorPengaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorPengaliKeyPressed

    }//GEN-LAST:event_FaktorPengaliKeyPressed

    private void denumeratorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_denumeratorKeyPressed
        Valid.pindah(evt, numerator, hasilhitung);
//        hasilhitung.setText("" + (Integer.parseInt(numerator.getText()) / Integer.parseInt(denumerator.getText())) * Integer.parseInt(FaktorPengali.getText()) + "");
    }//GEN-LAST:event_denumeratorKeyPressed

    private void JudulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JudulKeyPressed

    }//GEN-LAST:event_JudulKeyPressed

    private void btnPengarang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengarang4ActionPerformed
        profilmutu.isCek();
        profilmutu.setSize(internalFrame2.getWidth() - 20, internalFrame2.getHeight() - 20);
        profilmutu.setLocationRelativeTo(null);
        profilmutu.setVisible(true);
    }//GEN-LAST:event_btnPengarang4ActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void NamaPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPetugasKeyPressed

    private void numeratorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeratorKeyPressed
        Valid.pindah(evt, KodeVar, denumerator);
//        hasilhitung.setText(Valid.SetAngka("" + (Integer.parseInt(numerator.getText()) / Integer.parseInt(denumerator.getText())) * Integer.parseInt(FaktorPengali.getText()) + ""));
    }//GEN-LAST:event_numeratorKeyPressed

    private void KodeVarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeVarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeVarKeyPressed

    private void hasilhitungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilhitungKeyPressed
        Valid.pindah(evt, denumerator, BtnSimpan);
    }//GEN-LAST:event_hasilhitungKeyPressed

    private void numeratorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_numeratorPropertyChange
//        hasilhitung.setText("" + (Integer.parseInt(numerator.getText()) / Integer.parseInt(denumerator.getText())) * Integer.parseInt(FaktorPengali.getText()) + "");
    }//GEN-LAST:event_numeratorPropertyChange

    private void denumeratorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_denumeratorPropertyChange
//        hasilhitung.setText("" + (Integer.parseInt(numerator.getText()) / Integer.parseInt(denumerator.getText())) * Integer.parseInt(FaktorPengali.getText()) + "");
    }//GEN-LAST:event_denumeratorPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanDataMutu dialog = new DlgLaporanDataMutu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox FaktorPengali;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Judul;
    private widget.TextBox KdPetugas;
    private widget.TextBox KodeProfil;
    private widget.TextBox KodeVar;
    private widget.Label LCount;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.Tanggal PeriodeLaporan;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Button btnPengarang4;
    private widget.TextBox denumerator;
    private widget.TextBox hasilhitung;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label19;
    private widget.Label label22;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label5;
    private widget.Label labelDenumerator;
    private widget.Label labelNumerator;
    private widget.TextBox numerator;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbProfilMutu;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT\n"
                    + "	indikatormutu_profilmutu.judul,\n"
                    + "	indikatormutu_laporanhasil.no_profil,\n"
                    + "	indikatormutu_laporanhasil.id_variable,\n"
                    + "	indikatormutu_laporanhasil.numerator,\n"
                    + "	indikatormutu_laporanhasil.denumerator,\n"
                    + "	indikatormutu_laporanhasil.periodehasil,\n"
                    + "	indikatormutu_laporanhasil.hasilhitung,\n"
                    + "	indikatormutu_profil_variabel.satuan \n"
                    + "FROM \n"
                    + "	indikatormutu_laporanhasil\n"
                    + "	INNER JOIN indikatormutu_profilmutu ON indikatormutu_laporanhasil.no_profil = indikatormutu_profilmutu.no_profil\n"
                    + "	INNER JOIN indikatormutu_profil_variabel ON indikatormutu_laporanhasil.id_variable = indikatormutu_profil_variabel.id_variable\n"
                    + " WHERE\n"
                    + "	indikatormutu_profilmutu.judul LIKE ? \n"
                    + " ORDER BY indikatormutu_profilmutu.no_profil ");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                rerata = 0;
                ttlrerata = 0;
                xx = 1;
                while (rs.next()) {
                    rerata = rs.getDouble("hasilhitung");
                    ttlrerata = ttlrerata + rerata;
                    tabMode.addRow(new Object[]{
                        rs.getString("no_profil"), rs.getString("id_variable"), rs.getString("judul"), rs.getString("numerator"),
                        rs.getString("denumerator"), rs.getString("periodehasil"), rs.getDouble("hasilhitung"),
                        "", rs.getString("satuan")
                    });
                    xx++;

                }
                xx = xx - 1;
//                System.out.println(xx);
//                System.out.println(ttlrerata);
//                double rerata = (ttlrerata / xx) * 100;
//                System.out.println(rerata);
                if (xx > 0) {
                    tabMode.addRow(new Object[]{
                        "", "", "", "",
                        "", "Rerata", Valid.SetAngka(ttlrerata / xx),
                        "", ""
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

    public void emptTeks() {
        KodeProfil.setText("");
        Judul.setText("");
        TCari.setText("");
        KodeProfil.requestFocus();
        FaktorPengali.setText("");
        denumerator.setText("");
        numerator.setText("");
        KodeVar.setText("");
        labelNumerator.setText("pilih profil mutu dahulu");
        labelDenumerator.setText("pilih profil mutu dahulu");
        KdPetugas.setText(akses.getkode().toString());
        NamaPetugas.setText(Sequel.cariIsi("select petugas.nama from petugas where petugas.nip='" + akses.getkode().toString() + "'"));
        FaktorPengali.setText("");
    }

    private void getData() {

    }

    public JTable getTable() {
        return tbProfilMutu;
    }

    public void isCek() {
        if(akses.getkode()=="Admin Utama"){
            emptTeks();
            BtnSimpan.setEnabled(true);
            BtnHapus.setEnabled(true);
            BtnEdit.setEnabled(true);
            TCari.requestFocus();
            KdPetugas.setText("");
            NamaPetugas.setText("");
        } else {  
            emptTeks();
            BtnSimpan.setEnabled(akses.getinsiden_keselamatan_pasien());
            BtnHapus.setEnabled(akses.getinsiden_keselamatan_pasien());
            BtnEdit.setEnabled(akses.getinsiden_keselamatan_pasien());
            TCari.requestFocus();
            KdPetugas.setText(akses.getkode().toString());
            NamaPetugas.setText(Sequel.cariIsi("select petugas.nama from petugas where petugas.nip='" + akses.getkode().toString() + "'"));
        }
        
    }

}

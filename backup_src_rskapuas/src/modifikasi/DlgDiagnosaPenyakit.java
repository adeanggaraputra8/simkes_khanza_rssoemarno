package modifikasi;

import laporan.DlgPenyakit;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import laporan.DlgICD9;
import simrskhanza.DlgPasien;

/**
 *
 * @author perpustakaan
 */
public class DlgDiagnosaPenyakit extends javax.swing.JDialog {
    private final DefaultTableModel TabModeDiagnosaPasien, tabModeDiagnosa, tabModeProsedur,
            TabModeTindakanPasien, tabModeDiagnosaSekunder, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgPasien pasien = new DlgPasien(null, false);
    private PreparedStatement pspenyakit, psdiagnosapasien, psprosedur, pstindakanpasien,
            pspenyakitsekunder, pspasien, psdiagnosa, pstindakan, psdokter, psinadrg, psTINinadrg;
    private ResultSet rs, rs1, rspasien, rsdiagnosa, rstindakan, rsdokter;
    private int jml = 0, i = 0, index = 0, jml1 = 0, s = 0, index1 = 0, cek = 0, cekINADRG = 0,
            cekPremier = 0, cekPremierINADRG = 0;
    private String[] kode, nama, ciripny, keterangan, kategori, cirium, kode2, panjang, pendek,
            kode1, nama1, ciripny1, keterangan1, kategori1, cirium1;
    private boolean[] pilih, pilih2, pilih3;
    private String tglklaim = "", drdpjp = "", poli = "", crBayar = "", cekKlaim = "", jlhTindakan = "";

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgDiagnosaPenyakit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        TabModeDiagnosaPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien1.setModel(tabMode1);
        tbDiagnosaPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDiagnosaPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbDiagnosaPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosa = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosa.setModel(tabModeDiagnosa);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosaSekunder = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosa1.setModel(tabModeDiagnosaSekunder);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeProsedur = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Deskripsi Panjang", "Deskripsi Pendek"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbProsedur.setModel(tabModeProsedur);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());

        TabModeTindakanPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status","Jlh."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 8)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien1.setModel(tabMode2);
        tbTindakanPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(40);
            }
        }
        tbTindakanPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        this.setLocation(8, 1);
        setSize(885, 674);

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Diagnosa.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Diagnosa1.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Prosedur.setDocument(new batasInput((byte) 100).getKata(Prosedur));
        TCariPasien.setDocument(new batasInput((byte) 20).getKata(TCariPasien));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            Diagnosa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }
            });
        }

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            Diagnosa1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }
            });
        }
        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                }
                TCariPasien.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSimpanQTYinadrg = new javax.swing.JMenuItem();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel14 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCariPasien = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel17 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        Diagnosa = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        btnTambahPenyakit = new widget.Button();
        btnTambahProsedur = new widget.Button();
        BtnCariProsedur = new widget.Button();
        Prosedur = new widget.TextBox();
        jLabel15 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        Status = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Diagnosa1 = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbDiagnosa1 = new widget.Table();
        BtnCariPenyakit1 = new widget.Button();
        btnTambahPenyakit1 = new widget.Button();
        cmbDiagPro = new widget.ComboBox();
        jLabel20 = new widget.Label();
        ChkInput = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        tbDiagnosaPasien1 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        tbTindakanPasien1 = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSimpanQTYinadrg.setBackground(new java.awt.Color(255, 255, 255));
        MnSimpanQTYinadrg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanQTYinadrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanQTYinadrg.setText("Simpan Jumlah Prosedur INADRG");
        MnSimpanQTYinadrg.setName("MnSimpanQTYinadrg"); // NOI18N
        MnSimpanQTYinadrg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSimpanQTYinadrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanQTYinadrgActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSimpanQTYinadrg);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setHighlighter(null);
        nmpoli.setName("nmpoli"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Diagnosa & Prosedur Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl.Rawat :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(63, 23));
        panelGlass9.add(jLabel14);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-05-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari1);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("s.d");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        panelGlass9.add(jLabel19);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-05-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.RM :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("Alt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(865, 350));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 217));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 68, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(71, 12, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(213, 12, 110, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(325, 12, 330, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Primer :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 42, 97, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Status :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(667, 12, 50, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDiagnosa.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        tbDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDiagnosa);

        FormInput.add(Scroll1);
        Scroll1.setBounds(11, 69, 720, 105);

        Diagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(101, 42, 270, 23);

        BtnCariPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setName("BtnCariPenyakit"); // NOI18N
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(373, 42, 28, 23);

        btnTambahPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit.setMnemonic('2');
        btnTambahPenyakit.setToolTipText("Alt+2");
        btnTambahPenyakit.setEnabled(false);
        btnTambahPenyakit.setName("btnTambahPenyakit"); // NOI18N
        btnTambahPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit);
        btnTambahPenyakit.setBounds(403, 42, 28, 23);

        btnTambahProsedur.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahProsedur.setMnemonic('2');
        btnTambahProsedur.setToolTipText("Alt+2");
        btnTambahProsedur.setEnabled(false);
        btnTambahProsedur.setName("btnTambahProsedur"); // NOI18N
        btnTambahProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProsedurActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahProsedur);
        btnTambahProsedur.setBounds(1100, 42, 28, 23);

        BtnCariProsedur.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setName("BtnCariProsedur"); // NOI18N
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(1065, 42, 28, 23);

        Prosedur.setForeground(new java.awt.Color(0, 0, 0));
        Prosedur.setHighlighter(null);
        Prosedur.setName("Prosedur"); // NOI18N
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormInput.add(Prosedur);
        Prosedur.setBounds(798, 42, 260, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Prosedur :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(735, 42, 60, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbProsedur.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbProsedur.setName("tbProsedur"); // NOI18N
        tbProsedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProsedurKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbProsedur);

        FormInput.add(Scroll2);
        Scroll2.setBounds(738, 67, 620, 253);

        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ralan", "Ranap" }));
        Status.setEnabled(false);
        Status.setName("Status"); // NOI18N
        Status.setOpaque(false);
        Status.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(Status);
        Status.setBounds(720, 12, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnosa Sekunder :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 180, 110, 23);

        Diagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa1.setHighlighter(null);
        Diagnosa1.setName("Diagnosa1"); // NOI18N
        Diagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosa1KeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa1);
        Diagnosa1.setBounds(114, 180, 253, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbDiagnosa1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa1.setName("tbDiagnosa1"); // NOI18N
        tbDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosa1KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbDiagnosa1);

        FormInput.add(Scroll4);
        Scroll4.setBounds(11, 210, 720, 110);

        BtnCariPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit1.setMnemonic('1');
        BtnCariPenyakit1.setToolTipText("Alt+1");
        BtnCariPenyakit1.setName("BtnCariPenyakit1"); // NOI18N
        BtnCariPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit1);
        BtnCariPenyakit1.setBounds(373, 180, 28, 23);

        btnTambahPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit1.setMnemonic('2');
        btnTambahPenyakit1.setToolTipText("Alt+2");
        btnTambahPenyakit1.setEnabled(false);
        btnTambahPenyakit1.setName("btnTambahPenyakit1"); // NOI18N
        btnTambahPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit1);
        btnTambahPenyakit1.setBounds(403, 180, 28, 23);

        cmbDiagPro.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "STATISTIK", "INADRG" }));
        cmbDiagPro.setName("cmbDiagPro"); // NOI18N
        cmbDiagPro.setOpaque(false);
        cmbDiagPro.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(cmbDiagPro);
        cmbDiagPro.setBounds(943, 12, 85, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Diagnosa & Prosedur : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(790, 12, 150, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDiagnosaPasien.setAutoCreateRowSorter(true);
        tbDiagnosaPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien.setName("tbDiagnosaPasien"); // NOI18N
        tbDiagnosaPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasienMouseClicked(evt);
            }
        });
        tbDiagnosaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDiagnosaPasien);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Diagnosa Statistik", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbTindakanPasien.setAutoCreateRowSorter(true);
        tbTindakanPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien.setName("tbTindakanPasien"); // NOI18N
        tbTindakanPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasienMouseClicked(evt);
            }
        });
        tbTindakanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasienKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbTindakanPasien);

        internalFrame3.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Prosedur Statistik", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiagnosaPasien1.setAutoCreateRowSorter(true);
        tbDiagnosaPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien1.setName("tbDiagnosaPasien1"); // NOI18N
        tbDiagnosaPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasien1MouseClicked(evt);
            }
        });
        tbDiagnosaPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasien1KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbDiagnosaPasien1);

        internalFrame4.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Diagnosa INADRG", internalFrame4);

        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbTindakanPasien1.setAutoCreateRowSorter(true);
        tbTindakanPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien1.setComponentPopupMenu(jPopupMenu1);
        tbTindakanPasien1.setName("tbTindakanPasien1"); // NOI18N
        tbTindakanPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasien1MouseClicked(evt);
            }
        });
        tbTindakanPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasien1KeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbTindakanPasien1);

        internalFrame5.add(Scroll6, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Prosedur INADRG", internalFrame5);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil3();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampil4();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt, TCari, Diagnosa);
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekKlaim = "";
        
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat disimpan...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    simpan_diagproStatistik();
                    simpan_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    simpan_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    simpan_diagproINADRG();
                }
           
                BtnCariActionPerformed(null);
                BtnBatalActionPerformed(null);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Diagnosa, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Diagnosa.setText("");
        Diagnosa1.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        Prosedur.setText("");
        TNoRw.requestFocus();
        
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(false, i, 0);
        }
        for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
            tbDiagnosa1.setValueAt(false, s, 0);
        }
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            tbProsedur.setValueAt(false, i, 0);
        }
        
        if (Status.getSelectedIndex() == 0) {
            cmbDiagPro.setSelectedIndex(1);
        } else {
            cmbDiagPro.setSelectedIndex(0);
        }
        
        ChkInput.setSelected(true);
        isForm();        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekKlaim = "";
       
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat dihapus...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    hapus_diagproStatistik();
                    hapus_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    hapus_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    hapus_diagproINADRG();
                }
            }

            BtnCariActionPerformed(null);
            BtnBatalActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        cekPremier = 0;
        cekPremier = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
        
        if (cekPremier == 0 && (!akses.getkode().equals("Admin Utama"))) {
            JOptionPane.showMessageDialog(null, "Diagnosa primer belum tersimpan untuk kunjungan pasien saat ini...");
        } else {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TabRawat.getSelectedIndex() == 0) {
                tampil();
            } else if (TabRawat.getSelectedIndex() == 1) {
                tampil2();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSeek4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            DTPCari2.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void tbDiagnosaPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienMouseClicked
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }

        }
}//GEN-LAST:event_tbDiagnosaPasienMouseClicked

    private void tbDiagnosaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienKeyPressed
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDiagnosaPasienKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosa();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.getpenyakit() == true) {
                btnTambahPenyakitActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosa();
        }
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void btnTambahPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit tariflab = new DlgPenyakit(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakitActionPerformed

    private void tbDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaKeyPressed
        if (tbDiagnosa.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa.getSelectedRow() > -1) {
                            tbDiagnosa.setValueAt(true, tbDiagnosa.getSelectedRow(), 0);
                        }
                        Diagnosa.setText("");
                        Diagnosa.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa.setText("");
                Diagnosa.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampildiagnosa();
        tampildiagnosaSekunder();
        tampilprosedure();
    }//GEN-LAST:event_formWindowOpened

    private void btnTambahProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProsedurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 tariflab = new DlgICD9(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahProsedurActionPerformed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampilprosedure();
        }
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampilprosedure();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.geticd9() == true) {
                btnTambahProsedurActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProsedur.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void tbProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProsedurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProsedurKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTindakanPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasienMouseClicked
        if (TabModeTindakanPasien.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTindakanPasienMouseClicked

    private void tbTindakanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasienKeyPressed
        if (TabModeTindakanPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasienKeyPressed

    private void Diagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosa1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosaSekunder();
            }
        } //        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        //            if (var.getpenyakit1() == true) {
        //                btnTambahPenyakit1ActionPerformed(null);
        //            }
        //        } 
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_Diagnosa1KeyPressed

    private void tbDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosa1KeyPressed
        if (tbDiagnosa1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa1.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa1.getSelectedRow() > -1) {
                            tbDiagnosa1.setValueAt(true, tbDiagnosa1.getSelectedRow(), 0);
                        }
                        Diagnosa1.setText("");
                        Diagnosa1.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa1.setText("");
                Diagnosa1.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosa1KeyPressed

    private void BtnCariPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakit1ActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosaSekunder();
        }
    }//GEN-LAST:event_BtnCariPenyakit1ActionPerformed

    private void btnTambahPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakit1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit diagsekunder = new DlgPenyakit(null, false);
        diagsekunder.emptTeks();
        diagsekunder.isCek();
        diagsekunder.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diagsekunder.setLocationRelativeTo(internalFrame1);
        diagsekunder.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakit1ActionPerformed

    private void tbDiagnosaPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbDiagnosaPasien1MouseClicked

    private void tbDiagnosaPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData3();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosaPasien1KeyPressed

    private void tbTindakanPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasien1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbTindakanPasien1MouseClicked

    private void tbTindakanPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasien1KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData4();
                } catch (java.lang.NullPointerException e) {
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 8).toString().equals("") || Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 0) {
                        tabMode2.setValueAt("1", i, 8);
                    }
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasien1KeyPressed

    private void MnSimpanQTYinadrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanQTYinadrgActionPerformed
        simpanQTYprosedurINADRG();
        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            tbTindakanPasien1.setValueAt(false, i, 0);
        }
        tampil4();
    }//GEN-LAST:event_MnSimpanQTYinadrgActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDiagnosaPenyakit dialog = new DlgDiagnosaPenyakit(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariPenyakit1;
    private widget.Button BtnCariProsedur;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diagnosa1;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnSimpanQTYinadrg;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Prosedur;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Button btnTambahPenyakit;
    private widget.Button btnTambahPenyakit1;
    private widget.Button btnTambahProsedur;
    private widget.ComboBox cmbDiagPro;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel3;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdpoli;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiagnosa;
    private widget.Table tbDiagnosa1;
    private widget.Table tbDiagnosaPasien;
    private widget.Table tbDiagnosaPasien1;
    private widget.Table tbProsedur;
    private widget.Table tbTindakanPasien;
    private widget.Table tbTindakanPasien1;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        try {
            psdiagnosapasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status, if(diagnosa_pasien.prioritas='1','Primer','Sekunder') prior "
                    + "from diagnosa_pasien inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ");
            try {
                psdiagnosapasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(4, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(8, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(12, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(16, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(20, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(24, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasien.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)});
                }
                LCount.setText("" + TabModeDiagnosaPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampil3() {
        Valid.tabelKosong(tabMode1);
        try {
            psinadrg = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "diagnosa_pasien_inadrg.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien_inadrg.status, if(diagnosa_pasien_inadrg.prioritas='1','Primer','Sekunder') prior "
                    + "from diagnosa_pasien_inadrg inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien_inadrg.kd_penyakit=penyakit.kd_penyakit "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas");
            try {
                psinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psinadrg.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)});
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psinadrg != null) {
                    psinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void getData() {
        if (tbDiagnosaPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 7).toString());
        }
    }

    private void getData2() {
        if (tbTindakanPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData3() {
        if (tbDiagnosaPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData4() {
        if (tbTindakanPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 7).toString());
        }
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2, String status) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Status.setSelectedItem(status);
        isRawat();
        isPsien();
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(true);
        isForm();
        
        if (Status.getSelectedIndex() == 0) {
            cmbDiagPro.setSelectedIndex(1);
        } else {
            cmbDiagPro.setSelectedIndex(0);
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 350));
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
        BtnSimpan.setEnabled(akses.getdiagnosa_pasien());
        BtnHapus.setEnabled(akses.getdiagnosa_pasien());
//        btnTambahPenyakit.setEnabled(var.getpenyakit());
    }

    private void tampildiagnosa() {
        try {
            jml = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            ciripny = null;
            ciripny = new String[jml];
            keterangan = null;
            keterangan = new String[jml];
            kategori = null;
            kategori = new String[jml];
            cirium = null;
            cirium = new String[jml];

            index = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbDiagnosa.getValueAt(i, 1).toString();
                    nama[index] = tbDiagnosa.getValueAt(i, 2).toString();
                    ciripny[index] = tbDiagnosa.getValueAt(i, 3).toString();
                    keterangan[index] = tbDiagnosa.getValueAt(i, 4).toString();
                    kategori[index] = tbDiagnosa.getValueAt(i, 5).toString();
                    cirium[index] = tbDiagnosa.getValueAt(i, 6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for (i = 0; i < jml; i++) {
                tabModeDiagnosa.addRow(new Object[]{pilih[i], kode[i], nama[i], ciripny[i], keterangan[i], kategori[i], cirium[i]});
            }

            pspenyakit = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit  LIMIT 100");
            try {
                pspenyakit.setString(1, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(2, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(3, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(4, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(5, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(6, "%" + Diagnosa.getText().trim() + "%");
                rs = pspenyakit.executeQuery();
                while (rs.next()) {
                    tabModeDiagnosa.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspenyakit != null) {
                    pspenyakit.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampildiagnosaSekunder() {
        try {
            jml1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    jml1++;
                }
            }

            pilih3 = null;
            pilih3 = new boolean[jml1];
            kode1 = null;
            kode1 = new String[jml1];
            nama1 = null;
            nama1 = new String[jml1];
            ciripny1 = null;
            ciripny1 = new String[jml1];
            keterangan1 = null;
            keterangan1 = new String[jml1];
            kategori1 = null;
            kategori1 = new String[jml1];
            cirium1 = null;
            cirium1 = new String[jml1];

            index1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    pilih3[index1] = true;
                    kode1[index1] = tbDiagnosa1.getValueAt(s, 1).toString();
                    nama1[index1] = tbDiagnosa1.getValueAt(s, 2).toString();
                    ciripny1[index1] = tbDiagnosa1.getValueAt(s, 3).toString();
                    keterangan1[index1] = tbDiagnosa1.getValueAt(s, 4).toString();
                    kategori1[index1] = tbDiagnosa1.getValueAt(s, 5).toString();
                    cirium1[index1] = tbDiagnosa1.getValueAt(s, 6).toString();
                    index1++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosaSekunder);
            for (s = 0; s < jml1; s++) {
                tabModeDiagnosaSekunder.addRow(new Object[]{pilih3[s], kode1[s], nama1[s], ciripny1[s], keterangan1[s], kategori1[s], cirium1[s]});
            }

            pspenyakitsekunder = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit LIMIT 100");
            try {
                pspenyakitsekunder.setString(1, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(2, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(3, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(4, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(5, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(6, "%" + Diagnosa1.getText().trim() + "%");
                rs1 = pspenyakitsekunder.executeQuery();
                while (rs1.next()) {
                    tabModeDiagnosaSekunder.addRow(new Object[]{false, rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (pspenyakitsekunder != null) {
                    pspenyakitsekunder.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilprosedure() {
        try {
            jml = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode2 = null;
            kode2 = new String[jml];
            panjang = null;
            panjang = new String[jml];
            pendek = null;
            pendek = new String[jml];

            index = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode2[index] = tbProsedur.getValueAt(i, 1).toString();
                    panjang[index] = tbProsedur.getValueAt(i, 2).toString();
                    pendek[index] = tbProsedur.getValueAt(i, 3).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeProsedur);
            for (i = 0; i < jml; i++) {
                tabModeProsedur.addRow(new Object[]{pilih[i], kode2[i], panjang[i], pendek[i]});
            }

            psprosedur = koneksi.prepareStatement("select * from icd9 where kode like ? or "
                    + " deskripsi_panjang like ? or  deskripsi_pendek like ? order by kode");
            try {
                psprosedur.setString(1, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(2, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(3, "%" + Prosedur.getText().trim() + "%");
                rs = psprosedur.executeQuery();
                while (rs.next()) {
                    tabModeProsedur.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3)});
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psprosedur != null) {
                    psprosedur.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil2() {
        Valid.tabelKosong(TabModeTindakanPasien);
        try {
            pstindakanpasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                    + "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "
                    + "on prosedur_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and prosedur_pasien.kode=icd9.kode "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.kode like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and icd9.deskripsi_panjang like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ");
            try {
                pstindakanpasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(4, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(8, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(12, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(16, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(20, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(24, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasien.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)});
                }
                LCount.setText("" + TabModeTindakanPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampil4() {
        Valid.tabelKosong(tabMode2);
        try {
            psTINinadrg = koneksi.prepareStatement("SELECT reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "prosedur_pasien_inadrg.kode,icd9.deskripsi_panjang, prosedur_pasien_inadrg.status, prosedur_pasien_inadrg.qty "
                    + "FROM prosedur_pasien_inadrg INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN icd9 "
                    + "ON prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "AND prosedur_pasien_inadrg.kode=icd9.kode "
                    + "WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.tgl_registrasi LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.no_rawat LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.no_rkm_medis LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND pasien.nm_pasien LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.kode LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND icd9.deskripsi_panjang LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.status LIKE ? "
                    + "ORDER BY reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas");
            try {
                psTINinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psTINinadrg.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psTINinadrg != null) {
                    psTINinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
   
    
    private void simpan_diagproStatistik() {
        try {
            cek = 0;
            cekPremier = 0;
            koneksi.setAutoCommit(false);
            cek = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cek > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien", "?,?,?,?", "Penyakit", 4, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText())
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremier++;
                    }
                }

                if (cekPremier > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?", "Penyakit", 4, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1"
                            });
                        }
                    }
                }

                if (cekPremier == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?", "Penyakit", 4, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText())
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien", "?,?,?,?", "ICD 9", 4, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText())
                    });
                }
            }
            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }        
    }
    
    private void simpan_diagproINADRG() {
        try {
            cekINADRG = 0;
            cekPremierINADRG = 0;
            koneksi.setAutoCommit(false);
            cekINADRG = Sequel.cariInteger("select count(-1) from diagnosa_pasien_inadrg where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cekINADRG > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?", "Penyakit", 4, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText())
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremierINADRG++;
                    }
                }

                if (cekPremierINADRG > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?", "Penyakit", 4, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1"
                            });
                        }
                    }
                }

                if (cekPremierINADRG == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?", "Penyakit", 4, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText())
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer INADRG terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa INADRG yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien_inadrg", "?,?,?,?,?", "ICD 9", 5, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), "1"
                    });
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 INADRG yang sama dimasukkan sebelumnya...!");
        }
    }
    
    private void hapus_diagproStatistik() {
        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien.getValueAt(i, 2).toString(), tbDiagnosaPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    if (tbTindakanPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien.getValueAt(i, 2).toString(), tbTindakanPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }
    
    private void hapus_diagproINADRG() {
        if (TabRawat.getSelectedIndex() == 2) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diagnosa INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    if (tbDiagnosaPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien1.getValueAt(i, 2).toString(), tbDiagnosaPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data prosedur/tindakan/ICD 9 INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien1.getValueAt(i, 2).toString(), tbTindakanPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }   
    
    private void simpanQTYprosedurINADRG() {
        jlhTindakan = ""; 

        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {                
                if (!tbTindakanPasien1.getValueAt(i, 8).toString().equals("") && Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) > 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                } else if (Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                }          
            }
        }
    }
}

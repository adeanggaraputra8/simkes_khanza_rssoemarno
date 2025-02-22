/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgResepObat.java
 *
 * Created on 31 Mei 10, 11:27:40
 */

package keuangan;
import kepegawaian.DlgCariPetugas;
import simrskhanza.*;
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
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author perpustakaan
 */
public final class DlgPemasukanLain extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private PreparedStatement ps,psakun;
    private ResultSet rs;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgKategoriPemasukan kategori=new DlgKategoriPemasukan(null,false);
    private double total = 0, totTagihan = 0, sdhByr = 0, sisaTagihan = 0, byrKe = 0, stlhByr = 0;
    private int cekData = 0;

    /** Creates new form DlgResepObat 
     *@param parent
     *@param modal*/
    public DlgPemasukanLain(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        Object[] row={"Nomor","Tanggal","Kategori","Petugas","Pemasukan","Terima Dari","Keperluan","Kode","NIP",
            "Status Transaksi","No. Transaksi","No. SEP","No. RM","No.Kartu", "No.Rawat","Tgl. Masuk","Tgl. Pulang",
            "Rg. Perawatan","Kode INACBG","Hak Kelas","Naik Ke Kls","Lama Rawat","Persen Tambahan","Rumus Selisih Tarif","Total Bayar"
        };
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, 
                java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbResep.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbResep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 25; i++) {
            TableColumn column = tbResep.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(200);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
            else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(130);
            }else if(i==12){
                column.setPreferredWidth(60);
            }else if(i==13){
                column.setPreferredWidth(95);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(75);
            }else if(i==16){
                column.setPreferredWidth(75);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(70);
            }else if(i==20){
                column.setPreferredWidth(70);
            }else if(i==21){
                column.setPreferredWidth(70);
            }else if(i==22){
                column.setPreferredWidth(95);
            }else if(i==23){
                column.setPreferredWidth(350);
            }else if(i==24){
                column.setPreferredWidth(80);
            }
        }
        tbResep.setDefaultRenderer(Object.class, new WarnaTable());

//        Keperluan.setDocument(new batasInput((byte)70).getKata(Keperluan));
//        Keterangan.setDocument(new batasInput((byte)50).getKata(Keterangan));
        KdPtg.setDocument(new batasInput((byte)20).getKata(KdPtg));
        pemasukan.setDocument(new batasInput((byte)15).getKata(pemasukan));
        Nomor.setDocument(new batasInput((byte)15).getKata(Nomor));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemasukanLain")){
                    if(petugas.getTable().getSelectedRow()!= -1){        
                        KdPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        KdPtg.requestFocus();
                    } 
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
        
        
        kategori.getTabel().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kategori.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kategori.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemasukanLain")){
                    if(kategori.getTabel().getSelectedRow()!= -1){        
                        KdKategori.setText(kategori.getTabel().getValueAt(kategori.getTabel().getSelectedRow(),0).toString());
                        NmKategori.setText(kategori.getTabel().getValueAt(kategori.getTabel().getSelectedRow(),1).toString());
//                        KdKategori.requestFocus();

                        if (KdKategori.getText().equals("SBPJS")) {
                            WindowSelisihTarif.setSize(724, 404);
                            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
                            WindowSelisihTarif.setVisible(true);
                            NoSEP.requestFocus();
                            BtnSimpan6.setEnabled(true);
                            BtnPrint.setEnabled(false);
                            selisihBaru();

                            if (WindowSelisihTarif.isVisible() == true) {
                                BtnSimpan.setVisible(false);
                                BtnBatal.setVisible(false);
                            } else if (WindowSelisihTarif.isVisible() == false) {
                                BtnSimpan.setVisible(true);
                                BtnBatal.setVisible(true);
                            }

                        } else if (!KdKategori.getText().equals("SBPJS")) {
                            pemasukan.requestFocus();
                        }
                    } 
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
        
        ChkInput.setSelected(false);        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        cetakkwitansi = new javax.swing.JMenuItem();
        cetakNotaNaikKls = new javax.swing.JMenuItem();
        lihatSelisihTarif = new javax.swing.JMenuItem();
        RekapPemasukanLain = new javax.swing.JMenuItem();
        RekapSelisihLunas = new javax.swing.JMenuItem();
        RekapSelisihAngsur = new javax.swing.JMenuItem();
        WindowSelisihTarif = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel35 = new widget.Label();
        NoSEP = new widget.TextBox();
        jLabel36 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel37 = new widget.Label();
        nokartu = new widget.TextBox();
        jLabel38 = new widget.Label();
        norawat = new widget.TextBox();
        jLabel39 = new widget.Label();
        tglmsk = new widget.TextBox();
        jLabel40 = new widget.Label();
        tglklr = new widget.TextBox();
        jLabel41 = new widget.Label();
        rginap = new widget.TextBox();
        lunas = new widget.Label();
        hakkelas = new widget.TextBox();
        jLabel43 = new widget.Label();
        kdINACBG = new widget.TextBox();
        deskripsiKD = new widget.TextBox();
        BtnSelisihBaru = new widget.Button();
        jLabel30 = new widget.Label();
        jLabel44 = new widget.Label();
        tarifkls1 = new widget.TextBox();
        jLabel45 = new widget.Label();
        tarifkls2 = new widget.TextBox();
        jLabel46 = new widget.Label();
        tarifkls3 = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        lmrawat = new widget.TextBox();
        Totdibayar = new widget.TextBox();
        jLabel49 = new widget.Label();
        persenSELISIH = new widget.TextBox();
        jLabel50 = new widget.Label();
        labelbyr = new widget.Label();
        naikKLS = new widget.TextBox();
        BtnPrint = new widget.Button();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        Sudahbyr = new widget.TextBox();
        SisaTagihan = new widget.TextBox();
        jlhdibayar = new widget.TextBox();
        ChkSesuaiTagihan = new widget.CekBox();
        jLabel31 = new widget.Label();
        jLabel54 = new widget.Label();
        cekNoSEP = new widget.TextBox();
        nominal1 = new widget.TextBox();
        hasilLM = new widget.TextBox();
        nominal2 = new widget.TextBox();
        rumusbayar = new widget.TextBox();
        statusSELISIH = new widget.TextBox();
        cekNoTransaksi = new widget.TextBox();
        bayarKe = new widget.TextBox();
        jumlhBayar = new widget.TextBox();
        belumDibayar = new widget.TextBox();
        statusTran = new widget.TextBox();
        noTranAngsur = new widget.TextBox();
        dataSelisihTarif = new widget.TextBox();
        byrSimpan = new widget.TextBox();
        kodekamar = new widget.TextBox();
        noTransaksi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbResep = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        Keperluan = new widget.TextBox();
        KdPtg = new widget.TextBox();
        NmPtg = new widget.TextBox();
        jLabel3 = new widget.Label();
        jLabel13 = new widget.Label();
        btnPetugas = new widget.Button();
        jLabel14 = new widget.Label();
        KdKategori = new widget.TextBox();
        NmKategori = new widget.TextBox();
        btnKategori = new widget.Button();
        Tanggal = new widget.Tanggal();
        jLabel8 = new widget.Label();
        jLabel11 = new widget.Label();
        pemasukan = new widget.TextBox();
        Keterangan = new widget.TextBox();
        jLabel12 = new widget.Label();
        Nomor = new widget.TextBox();
        jLabel4 = new widget.Label();
        ChkInput = new widget.CekBox();

        Popup.setName("Popup"); // NOI18N

        cetakkwitansi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cetakkwitansi.setForeground(java.awt.Color.darkGray);
        cetakkwitansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        cetakkwitansi.setText("Cetak Kwitansi Penerimaan");
        cetakkwitansi.setName("cetakkwitansi"); // NOI18N
        cetakkwitansi.setPreferredSize(new java.awt.Dimension(220, 26));
        cetakkwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakkwitansiActionPerformed(evt);
            }
        });
        Popup.add(cetakkwitansi);

        cetakNotaNaikKls.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cetakNotaNaikKls.setForeground(java.awt.Color.darkGray);
        cetakNotaNaikKls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        cetakNotaNaikKls.setText("Nota/Kwitansi Naik Kelas");
        cetakNotaNaikKls.setName("cetakNotaNaikKls"); // NOI18N
        cetakNotaNaikKls.setPreferredSize(new java.awt.Dimension(220, 26));
        cetakNotaNaikKls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakNotaNaikKlsActionPerformed(evt);
            }
        });
        Popup.add(cetakNotaNaikKls);

        lihatSelisihTarif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lihatSelisihTarif.setForeground(java.awt.Color.darkGray);
        lihatSelisihTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        lihatSelisihTarif.setText("Lihat Hitungan Selisih Tarif");
        lihatSelisihTarif.setName("lihatSelisihTarif"); // NOI18N
        lihatSelisihTarif.setPreferredSize(new java.awt.Dimension(220, 26));
        lihatSelisihTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatSelisihTarifActionPerformed(evt);
            }
        });
        Popup.add(lihatSelisihTarif);

        RekapPemasukanLain.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapPemasukanLain.setForeground(java.awt.Color.darkGray);
        RekapPemasukanLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapPemasukanLain.setText("Rekap Pemasukan Lain");
        RekapPemasukanLain.setName("RekapPemasukanLain"); // NOI18N
        RekapPemasukanLain.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapPemasukanLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapPemasukanLainActionPerformed(evt);
            }
        });
        Popup.add(RekapPemasukanLain);

        RekapSelisihLunas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapSelisihLunas.setForeground(java.awt.Color.darkGray);
        RekapSelisihLunas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapSelisihLunas.setText("Rekap Selisih Tarif BPJS (Pelunasan)");
        RekapSelisihLunas.setName("RekapSelisihLunas"); // NOI18N
        RekapSelisihLunas.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapSelisihLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapSelisihLunasActionPerformed(evt);
            }
        });
        Popup.add(RekapSelisihLunas);

        RekapSelisihAngsur.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapSelisihAngsur.setForeground(java.awt.Color.darkGray);
        RekapSelisihAngsur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapSelisihAngsur.setText("Rekap Selisih Tarif BPJS (Angsuran)");
        RekapSelisihAngsur.setName("RekapSelisihAngsur"); // NOI18N
        RekapSelisihAngsur.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapSelisihAngsur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapSelisihAngsurActionPerformed(evt);
            }
        });
        Popup.add(RekapSelisihAngsur);

        WindowSelisihTarif.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSelisihTarif.setName("WindowSelisihTarif"); // NOI18N
        WindowSelisihTarif.setUndecorated(true);
        WindowSelisihTarif.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Selisih Tarif Pasien R. Inap BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(330, 365, 90, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(100, 365, 90, 30);

        jLabel35.setForeground(new java.awt.Color(0, 0, 250));
        jLabel35.setText("No.SEP : ");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame7.add(jLabel35);
        jLabel35.setBounds(0, 25, 102, 23);

        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setHighlighter(null);
        NoSEP.setMaxLenth(19);
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
        });
        internalFrame7.add(NoSEP);
        NoSEP.setBounds(106, 25, 178, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Pasien : ");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame7.add(jLabel36);
        jLabel36.setBounds(280, 25, 50, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        internalFrame7.add(norm);
        norm.setBounds(332, 25, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        internalFrame7.add(nmpasien);
        nmpasien.setBounds(416, 25, 290, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Masuk : ");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame7.add(jLabel37);
        jLabel37.setBounds(0, 78, 102, 23);

        nokartu.setEditable(false);
        nokartu.setForeground(new java.awt.Color(0, 0, 0));
        nokartu.setHighlighter(null);
        nokartu.setName("nokartu"); // NOI18N
        nokartu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nokartuKeyPressed(evt);
            }
        });
        internalFrame7.add(nokartu);
        nokartu.setBounds(106, 52, 120, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("No. Rawat : ");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame7.add(jLabel38);
        jLabel38.setBounds(230, 52, 70, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setHighlighter(null);
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        internalFrame7.add(norawat);
        norawat.setBounds(302, 52, 140, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("No.Kartu : ");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame7.add(jLabel39);
        jLabel39.setBounds(0, 52, 102, 23);

        tglmsk.setForeground(new java.awt.Color(0, 0, 0));
        tglmsk.setHighlighter(null);
        tglmsk.setName("tglmsk"); // NOI18N
        tglmsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglmskKeyPressed(evt);
            }
        });
        internalFrame7.add(tglmsk);
        tglmsk.setBounds(106, 78, 100, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Tgl. Keluar/Pulang : ");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame7.add(jLabel40);
        jLabel40.setBounds(210, 78, 100, 23);

        tglklr.setEditable(false);
        tglklr.setForeground(new java.awt.Color(0, 0, 0));
        tglklr.setHighlighter(null);
        tglklr.setName("tglklr"); // NOI18N
        tglklr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglklrKeyPressed(evt);
            }
        });
        internalFrame7.add(tglklr);
        tglklr.setBounds(310, 78, 120, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Rg. Rwt. Inap : ");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame7.add(jLabel41);
        jLabel41.setBounds(0, 105, 102, 23);

        rginap.setEditable(false);
        rginap.setForeground(new java.awt.Color(0, 0, 0));
        rginap.setHighlighter(null);
        rginap.setName("rginap"); // NOI18N
        rginap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rginapKeyPressed(evt);
            }
        });
        internalFrame7.add(rginap);
        rginap.setBounds(106, 105, 600, 23);

        lunas.setForeground(new java.awt.Color(153, 0, 153));
        lunas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunas.setText("- LUNAS -");
        lunas.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lunas.setName("lunas"); // NOI18N
        internalFrame7.add(lunas);
        lunas.setBounds(230, 264, 270, 50);

        hakkelas.setEditable(false);
        hakkelas.setForeground(new java.awt.Color(0, 0, 0));
        hakkelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hakkelas.setHighlighter(null);
        hakkelas.setName("hakkelas"); // NOI18N
        hakkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hakkelasKeyPressed(evt);
            }
        });
        internalFrame7.add(hakkelas);
        hakkelas.setBounds(106, 208, 40, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Tarif : Kelas 1 Rp.");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame7.add(jLabel43);
        jLabel43.setBounds(0, 160, 102, 23);

        kdINACBG.setEditable(false);
        kdINACBG.setForeground(new java.awt.Color(0, 0, 0));
        kdINACBG.setHighlighter(null);
        kdINACBG.setMaxLenth(15);
        kdINACBG.setName("kdINACBG"); // NOI18N
        kdINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdINACBGKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdINACBGKeyPressed(evt);
            }
        });
        internalFrame7.add(kdINACBG);
        kdINACBG.setBounds(106, 133, 90, 23);

        deskripsiKD.setEditable(false);
        deskripsiKD.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiKD.setHighlighter(null);
        deskripsiKD.setName("deskripsiKD"); // NOI18N
        deskripsiKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskripsiKDKeyPressed(evt);
            }
        });
        internalFrame7.add(deskripsiKD);
        deskripsiKD.setBounds(198, 133, 508, 23);

        BtnSelisihBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnSelisihBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnSelisihBaru.setMnemonic('B');
        BtnSelisihBaru.setText("Baru");
        BtnSelisihBaru.setToolTipText("Alt+B");
        BtnSelisihBaru.setName("BtnSelisihBaru"); // NOI18N
        BtnSelisihBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelisihBaruActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSelisihBaru);
        BtnSelisihBaru.setBounds(10, 365, 80, 30);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame7.add(jLabel30);
        jLabel30.setBounds(0, 345, 850, 14);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Kode INACBG : ");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame7.add(jLabel44);
        jLabel44.setBounds(0, 133, 102, 23);

        tarifkls1.setEditable(false);
        tarifkls1.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls1.setText("0");
        tarifkls1.setHighlighter(null);
        tarifkls1.setName("tarifkls1"); // NOI18N
        tarifkls1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls1KeyPressed(evt);
            }
        });
        internalFrame7.add(tarifkls1);
        tarifkls1.setBounds(106, 160, 120, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Kelas 2 Rp.");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame7.add(jLabel45);
        jLabel45.setBounds(230, 160, 60, 23);

        tarifkls2.setEditable(false);
        tarifkls2.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls2.setText("0");
        tarifkls2.setHighlighter(null);
        tarifkls2.setName("tarifkls2"); // NOI18N
        tarifkls2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls2KeyPressed(evt);
            }
        });
        internalFrame7.add(tarifkls2);
        tarifkls2.setBounds(293, 160, 120, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Kelas 3 Rp.");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame7.add(jLabel46);
        jLabel46.setBounds(420, 160, 60, 23);

        tarifkls3.setEditable(false);
        tarifkls3.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls3.setText("0");
        tarifkls3.setHighlighter(null);
        tarifkls3.setName("tarifkls3"); // NOI18N
        tarifkls3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls3KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls3KeyPressed(evt);
            }
        });
        internalFrame7.add(tarifkls3);
        tarifkls3.setBounds(484, 160, 120, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Naik ke : ");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame7.add(jLabel47);
        jLabel47.setBounds(152, 208, 46, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Lama rawat Rg. VIP : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame7.add(jLabel48);
        jLabel48.setBounds(290, 208, 110, 23);

        lmrawat.setEditable(false);
        lmrawat.setForeground(new java.awt.Color(0, 0, 0));
        lmrawat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lmrawat.setText("0");
        lmrawat.setHighlighter(null);
        lmrawat.setName("lmrawat"); // NOI18N
        lmrawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lmrawatKeyPressed(evt);
            }
        });
        internalFrame7.add(lmrawat);
        lmrawat.setBounds(400, 208, 50, 23);

        Totdibayar.setEditable(false);
        Totdibayar.setForeground(new java.awt.Color(0, 0, 0));
        Totdibayar.setText("0");
        Totdibayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Totdibayar.setHighlighter(null);
        Totdibayar.setName("Totdibayar"); // NOI18N
        Totdibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TotdibayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotdibayarKeyPressed(evt);
            }
        });
        internalFrame7.add(Totdibayar);
        Totdibayar.setBounds(610, 235, 95, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("hari,  Persentase tambahan naik kls. : ");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame7.add(jLabel49);
        jLabel49.setBounds(452, 208, 185, 23);

        persenSELISIH.setEditable(false);
        persenSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        persenSELISIH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        persenSELISIH.setText("0");
        persenSELISIH.setHighlighter(null);
        persenSELISIH.setName("persenSELISIH"); // NOI18N
        persenSELISIH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                persenSELISIHKeyPressed(evt);
            }
        });
        internalFrame7.add(persenSELISIH);
        persenSELISIH.setBounds(639, 208, 45, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("%");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame7.add(jLabel50);
        jLabel50.setBounds(690, 208, 20, 23);

        labelbyr.setForeground(new java.awt.Color(0, 0, 0));
        labelbyr.setText("Total bayar : Rp. ");
        labelbyr.setName("labelbyr"); // NOI18N
        internalFrame7.add(labelbyr);
        labelbyr.setBounds(0, 235, 610, 23);

        naikKLS.setEditable(false);
        naikKLS.setForeground(new java.awt.Color(0, 0, 0));
        naikKLS.setHighlighter(null);
        naikKLS.setName("naikKLS"); // NOI18N
        naikKLS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                naikKLSKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                naikKLSKeyPressed(evt);
            }
        });
        internalFrame7.add(naikKLS);
        naikKLS.setBounds(199, 208, 90, 24);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Tarif");
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
        internalFrame7.add(BtnPrint);
        BtnPrint.setBounds(200, 365, 120, 30);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Sudah bayar : Rp. ");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame7.add(jLabel51);
        jLabel51.setBounds(480, 263, 130, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Sisa tagihan : Rp. ");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame7.add(jLabel52);
        jLabel52.setBounds(480, 290, 130, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Jumlah dibayar : Rp. ");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame7.add(jLabel53);
        jLabel53.setBounds(500, 317, 110, 23);

        Sudahbyr.setEditable(false);
        Sudahbyr.setForeground(new java.awt.Color(0, 0, 0));
        Sudahbyr.setText("0");
        Sudahbyr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Sudahbyr.setHighlighter(null);
        Sudahbyr.setName("Sudahbyr"); // NOI18N
        Sudahbyr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SudahbyrKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SudahbyrKeyPressed(evt);
            }
        });
        internalFrame7.add(Sudahbyr);
        Sudahbyr.setBounds(610, 263, 95, 23);

        SisaTagihan.setEditable(false);
        SisaTagihan.setForeground(new java.awt.Color(0, 0, 0));
        SisaTagihan.setText("0");
        SisaTagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SisaTagihan.setHighlighter(null);
        SisaTagihan.setName("SisaTagihan"); // NOI18N
        SisaTagihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SisaTagihanKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SisaTagihanKeyPressed(evt);
            }
        });
        internalFrame7.add(SisaTagihan);
        SisaTagihan.setBounds(610, 290, 95, 23);

        jlhdibayar.setForeground(new java.awt.Color(0, 0, 0));
        jlhdibayar.setText("0");
        jlhdibayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlhdibayar.setHighlighter(null);
        jlhdibayar.setName("jlhdibayar"); // NOI18N
        jlhdibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jlhdibayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlhdibayarKeyPressed(evt);
            }
        });
        internalFrame7.add(jlhdibayar);
        jlhdibayar.setBounds(610, 317, 95, 23);

        ChkSesuaiTagihan.setBackground(new java.awt.Color(255, 255, 250));
        ChkSesuaiTagihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSesuaiTagihan.setForeground(new java.awt.Color(0, 0, 0));
        ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
        ChkSesuaiTagihan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkSesuaiTagihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSesuaiTagihan.setName("ChkSesuaiTagihan"); // NOI18N
        ChkSesuaiTagihan.setOpaque(false);
        ChkSesuaiTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSesuaiTagihanActionPerformed(evt);
            }
        });
        internalFrame7.add(ChkSesuaiTagihan);
        ChkSesuaiTagihan.setBounds(310, 317, 190, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("--- Penghitungan selisih tarif (Permenkes RI No. 51 Tahun 2018) -------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame7.add(jLabel31);
        jLabel31.setBounds(0, 190, 850, 14);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Hak Kelas BPJS : ");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame7.add(jLabel54);
        jLabel54.setBounds(0, 208, 102, 23);

        WindowSelisihTarif.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        cekNoSEP.setForeground(new java.awt.Color(0, 0, 0));
        cekNoSEP.setHighlighter(null);
        cekNoSEP.setName("cekNoSEP"); // NOI18N
        cekNoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cekNoSEPKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekNoSEPKeyPressed(evt);
            }
        });

        nominal1.setForeground(new java.awt.Color(0, 0, 0));
        nominal1.setHighlighter(null);
        nominal1.setName("nominal1"); // NOI18N
        nominal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nominal1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nominal1KeyPressed(evt);
            }
        });

        hasilLM.setForeground(new java.awt.Color(0, 0, 0));
        hasilLM.setHighlighter(null);
        hasilLM.setName("hasilLM"); // NOI18N
        hasilLM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hasilLMKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilLMKeyPressed(evt);
            }
        });

        nominal2.setForeground(new java.awt.Color(0, 0, 0));
        nominal2.setHighlighter(null);
        nominal2.setName("nominal2"); // NOI18N
        nominal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nominal2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nominal2KeyPressed(evt);
            }
        });

        rumusbayar.setForeground(new java.awt.Color(0, 0, 0));
        rumusbayar.setHighlighter(null);
        rumusbayar.setName("rumusbayar"); // NOI18N
        rumusbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rumusbayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rumusbayarKeyPressed(evt);
            }
        });

        statusSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        statusSELISIH.setHighlighter(null);
        statusSELISIH.setName("statusSELISIH"); // NOI18N
        statusSELISIH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                statusSELISIHKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusSELISIHKeyPressed(evt);
            }
        });

        cekNoTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        cekNoTransaksi.setHighlighter(null);
        cekNoTransaksi.setName("cekNoTransaksi"); // NOI18N
        cekNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cekNoTransaksiKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekNoTransaksiKeyPressed(evt);
            }
        });

        bayarKe.setForeground(new java.awt.Color(0, 0, 0));
        bayarKe.setHighlighter(null);
        bayarKe.setName("bayarKe"); // NOI18N
        bayarKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayarKeKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bayarKeKeyPressed(evt);
            }
        });

        jumlhBayar.setForeground(new java.awt.Color(0, 0, 0));
        jumlhBayar.setHighlighter(null);
        jumlhBayar.setName("jumlhBayar"); // NOI18N
        jumlhBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlhBayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlhBayarKeyPressed(evt);
            }
        });

        belumDibayar.setForeground(new java.awt.Color(0, 0, 0));
        belumDibayar.setHighlighter(null);
        belumDibayar.setName("belumDibayar"); // NOI18N
        belumDibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                belumDibayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                belumDibayarKeyPressed(evt);
            }
        });

        statusTran.setForeground(new java.awt.Color(0, 0, 0));
        statusTran.setHighlighter(null);
        statusTran.setName("statusTran"); // NOI18N
        statusTran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                statusTranKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusTranKeyPressed(evt);
            }
        });

        noTranAngsur.setForeground(new java.awt.Color(0, 0, 0));
        noTranAngsur.setHighlighter(null);
        noTranAngsur.setName("noTranAngsur"); // NOI18N
        noTranAngsur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noTranAngsurKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noTranAngsurKeyPressed(evt);
            }
        });

        dataSelisihTarif.setForeground(new java.awt.Color(0, 0, 0));
        dataSelisihTarif.setHighlighter(null);
        dataSelisihTarif.setName("dataSelisihTarif"); // NOI18N
        dataSelisihTarif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dataSelisihTarifKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dataSelisihTarifKeyPressed(evt);
            }
        });

        byrSimpan.setForeground(new java.awt.Color(0, 0, 0));
        byrSimpan.setHighlighter(null);
        byrSimpan.setName("byrSimpan"); // NOI18N
        byrSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                byrSimpanKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                byrSimpanKeyPressed(evt);
            }
        });

        kodekamar.setForeground(new java.awt.Color(0, 0, 0));
        kodekamar.setHighlighter(null);
        kodekamar.setName("kodekamar"); // NOI18N
        kodekamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kodekamarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodekamarKeyPressed(evt);
            }
        });

        noTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        noTransaksi.setHighlighter(null);
        noTransaksi.setName("noTransaksi"); // NOI18N
        noTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noTransaksiKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noTransaksiKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pemasukan Lain-Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbResep.setAutoCreateRowSorter(true);
        tbResep.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbResep.setComponentPopupMenu(Popup);
        tbResep.setName("tbResep"); // NOI18N
        tbResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepMouseClicked(evt);
            }
        });
        tbResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbResep);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(52, 30));
        panelGlass8.add(LCount);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-05-2019" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-05-2019" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setToolTipText("Alt+4");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(440, 128));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(440, 77));
        FormInput.setLayout(null);

        Keperluan.setHighlighter(null);
        Keperluan.setName("Keperluan"); // NOI18N
        Keperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeperluanKeyPressed(evt);
            }
        });
        FormInput.add(Keperluan);
        Keperluan.setBounds(545, 70, 480, 23);

        KdPtg.setHighlighter(null);
        KdPtg.setName("KdPtg"); // NOI18N
        KdPtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPtgKeyPressed(evt);
            }
        });
        FormInput.add(KdPtg);
        KdPtg.setBounds(83, 42, 100, 23);

        NmPtg.setEditable(false);
        NmPtg.setHighlighter(null);
        NmPtg.setName("NmPtg"); // NOI18N
        FormInput.add(NmPtg);
        NmPtg.setBounds(185, 42, 221, 23);

        jLabel3.setText("Terima Dari :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 72, 80, 23);

        jLabel13.setText("Petugas :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 42, 80, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('3');
        btnPetugas.setToolTipText("Alt+3");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(409, 42, 28, 23);

        jLabel14.setText("Ketegori :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 12, 80, 23);

        KdKategori.setHighlighter(null);
        KdKategori.setName("KdKategori"); // NOI18N
        KdKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKategoriKeyPressed(evt);
            }
        });
        FormInput.add(KdKategori);
        KdKategori.setBounds(83, 12, 70, 23);

        NmKategori.setEditable(false);
        NmKategori.setHighlighter(null);
        NmKategori.setName("NmKategori"); // NOI18N
        FormInput.add(NmKategori);
        NmKategori.setBounds(155, 12, 251, 23);

        btnKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKategori.setMnemonic('3');
        btnKategori.setToolTipText("Alt+3");
        btnKategori.setName("btnKategori"); // NOI18N
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });
        btnKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKategoriKeyPressed(evt);
            }
        });
        FormInput.add(btnKategori);
        btnKategori.setBounds(409, 12, 28, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-05-2019 14:56:11" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.setPreferredSize(new java.awt.Dimension(100, 23));
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(545, 10, 190, 23);

        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(470, 10, 73, 23);

        jLabel11.setText(" Keperluan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(470, 70, 73, 23);

        pemasukan.setText("0");
        pemasukan.setHighlighter(null);
        pemasukan.setName("pemasukan"); // NOI18N
        pemasukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pemasukanKeyPressed(evt);
            }
        });
        FormInput.add(pemasukan);
        pemasukan.setBounds(563, 40, 172, 23);

        Keterangan.setHighlighter(null);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        FormInput.add(Keterangan);
        Keterangan.setBounds(83, 72, 170, 23);

        jLabel12.setText("Pemasukan : Rp.");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(470, 40, 93, 23);

        Nomor.setHighlighter(null);
        Nomor.setName("Nomor"); // NOI18N
        Nomor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NomorKeyPressed(evt);
            }
        });
        FormInput.add(Nomor);
        Nomor.setBounds(317, 72, 120, 23);

        jLabel4.setText("Nomor :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(264, 72, 50, 23);

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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_transaksi=? ", cekNoTransaksi, noTransaksi.getText());
        
        if (Keterangan.getText().trim().equals("")) {
            Valid.textKosong(Keterangan, "Keterangan");
        } else if (Keperluan.getText().trim().equals("")) {
            Valid.textKosong(Keperluan, "Keperluan");
        } else if (Nomor.getText().trim().equals("")) {
            Valid.textKosong(Nomor, "Nomor Transaksi");
        } else if (KdPtg.getText().trim().equals("") || NmPtg.getText().trim().equals("")) {
            Valid.textKosong(KdPtg, "Petugas Keuangan");
        } else if (KdKategori.getText().trim().equals("") || NmKategori.getText().trim().equals("")) {
            Valid.textKosong(KdKategori, "Kategori Pemasukkan");
        } else if (pemasukan.getText().trim().equals("") || pemasukan.getText().trim().equals("0")) {
            Valid.textKosong(pemasukan, "Pengeluaran");
        } else if (!cekNoTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Transaksi tersebut sudah tersimpan dilaporan kasir..!!!!");
            emptTeks();
        } else {
            autoNomorTransaksi();
            Sequel.AutoComitFalse();
            simpanPemasukan();
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,pemasukan,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            Keterangan.requestFocus();
        } else if (Keterangan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada table untuk memilih...!!!!");
        } else if (!(Keterangan.getText().trim().equals(""))) {
            if (tbResep.getSelectedRow() > -1) {
                if (KdKategori.getText().equals("SBPJS")) {
                    JOptionPane.showMessageDialog(null, "Data tdk. bisa dihapus karena accounting sudah tervalidasi utk. transaksi pembayaran biaya selisih tarif naik kls. rawat...!!!!");
                    BtnBatal.requestFocus();
                } else if (!KdKategori.getText().equals("SBPJS")) {
                   hapusTransaksiNonNaikKelas();                
                }
                
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal,BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

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
            Valid.pindah(evt, BtnCari, NmPtg);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbResepMouseClicked

    private void tbResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbResepKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,Keperluan,pemasukan);
    }//GEN-LAST:event_TanggalKeyPressed

    private void pemasukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pemasukanKeyPressed
        Valid.pindah(evt,Tanggal,BtnSimpan);
    }//GEN-LAST:event_pemasukanKeyPressed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,KdPtg,BtnSimpan);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgPemasukanLain");
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.isCek();
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void KdPtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPtgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama from petugas where nip=?",NmPtg,KdPtg.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }else{
            Valid.pindah(evt,KdKategori,Keperluan);
        }
    }//GEN-LAST:event_KdPtgKeyPressed

    private void KeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeperluanKeyPressed
        Valid.pindah(evt,KdPtg,Tanggal);
    }//GEN-LAST:event_KeperluanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        autoNomorTransaksi();
    }//GEN-LAST:event_formWindowOpened

    private void KdKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKategoriKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama_kategori from kategori_pemasukan_lain where kode_kategori=?",NmKategori,KdKategori.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKategoriActionPerformed(null);
        }else{            
            Valid.pindah(evt,TCari,KdPtg);
        }
    }//GEN-LAST:event_KdKategoriKeyPressed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        akses.setform("DlgPemasukanLain");
        kategori.emptTeks();
        kategori.tampil();
        kategori.isCek();
        kategori.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kategori.setLocationRelativeTo(internalFrame1);
        kategori.setVisible(true);
    }//GEN-LAST:event_btnKategoriActionPerformed

    private void btnKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKategoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKategoriKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganKeyPressed

    private void cetakkwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakkwitansiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(Keperluan.getText().trim().equals("")){
            Valid.textKosong(Keperluan,"Keperluan");
        }else if(KdPtg.getText().trim().equals("")||NmPtg.getText().trim().equals("")){
            Valid.textKosong(KdPtg,"Petugas");
        }else if(KdKategori.getText().trim().equals("")||NmKategori.getText().trim().equals("")){
            Valid.textKosong(KdKategori,"Kode Kategori");
        }else if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            tbResep.requestFocus();
        }else {
            
            Sequel.queryu("delete from temporary");
            int row=tabMode.getRowCount();
            for(int i=0;i<row;i++){  
                try {
                    if(Valid.SetAngka(tabMode.getValueAt(i,0).toString())>0){
                           Sequel.menyimpan("temporary","'0','"+
                                   tabMode.getValueAt(i,0).toString()+"','"+
                                   tabMode.getValueAt(i,1).toString()+"','"+
                                   tabMode.getValueAt(i,2).toString()+"','"+
                                   tabMode.getValueAt(i,3).toString()+"','"+
                                   tabMode.getValueAt(i,4).toString()+"','"+
                                   tabMode.getValueAt(i,5).toString()+"','"+
                                   tabMode.getValueAt(i,6).toString()+"','"+
                                   tabMode.getValueAt(i,8).toString()+"','"+
                                   tabMode.getValueAt(i,10).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Penjualan"); 
                    }
                } catch (Exception e) {
                }                
            }
            
            Valid.panggilUrl("billing/LaporanKeuangan.php?kode="+Nomor.getText()+"&Tanggal="+Valid.SetTgl(Tanggal.getSelectedItem()+"")+"&nama="+NmKategori.getText().replaceAll(" ","_")+"&petugas="+NmPtg.getText().replaceAll(" ","_")+"&keperluan="+Keperluan.getText().replaceAll(" ","_")+"&keterangan="+Keterangan.getText().replaceAll(" ","_")+"&nom="+pemasukan.getText().replaceAll(" ","_")+"&kategori="+NmKategori.getText().replaceAll(" ","_"));
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_cetakkwitansiActionPerformed

    private void NomorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NomorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomorKeyPressed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowSelisihTarif.dispose();
        selisihBaru();
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_rawat=? ", cekNoTransaksi, norawat.getText());
        Sequel.cariIsi("select no_transaksi from biaya_naik_kelas_bpjs where no_transaksi=? ", noTranAngsur, cekNoTransaksi.getText());

        if (KdPtg.getText().trim().equals("") || NmPtg.getText().trim().equals("")) {
            Valid.textKosong(KdPtg, "Petugas Kasir");
        } else if (KdKategori.getText().trim().equals("") || NmKategori.getText().trim().equals("")) {
            Valid.textKosong(KdKategori, "Kategori Pemasukan");
        } else if ((norm.getText().trim().equals("")) && (nmpasien.getText().trim().equals(""))
            && (nokartu.getText().trim().equals("")) && (norawat.getText().trim().equals(""))) {
            Valid.textKosong(Keterangan, "Data Pasien");
            NoSEP.requestFocus();
        } else if (tglmsk.getText().trim().equals("")) {
            Valid.textKosong(Keterangan, "Tgl. Masuk");
            tglmsk.requestFocus();
        } else if (tglklr.getText().trim().equals("")) {
            Valid.textKosong(Keterangan, "Tgl. Keluar/Pulang");
            NoSEP.requestFocus();
        } else if ((kdINACBG.getText().trim().equals("")) && (deskripsiKD.getText().trim().equals(""))) {
            Valid.textKosong(Keterangan, "Kode & deskripsi INACBG");
            kdINACBG.requestFocus();
        } else if (jlhdibayar.getText().equals("0") || (jlhdibayar.getText().equals("0.0") || (jlhdibayar.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "Nominal angka pembayaran harus diisi dengan benar,...!!!");
            ChkSesuaiTagihan.requestFocus();
        } else if (!cekNoTransaksi.getText().trim().equals("") && (noTranAngsur.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Ini adalah transaksi lama, data sudah tersimpan dilaporan kasir..!!!!");
            NoSEP.requestFocus();
            selisihBaru();
        } else if (jlhdibayar.getText().trim().equals(byrSimpan.getText()) && (ChkSesuaiTagihan.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Nominal angka pembayaran sdh. sesuai tagihan, conteng dulu jenis pembayaranya..!!!!");
            ChkSesuaiTagihan.requestFocus();
        } else {
            Sequel.cariIsi("select total_byr from pemasukan_lain where no_rawat=? ", statusSELISIH, norawat.getText());

            if (sisaTagihan >= Double.parseDouble(jlhdibayar.getText())) {
                autoNomorTransaksi();
                Sequel.AutoComitFalse();
                try {

                    stlhByr = sisaTagihan - Double.parseDouble(jlhdibayar.getText());

//                    Sequel.menyimpan("pemasukan_lain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemasukan", 27, new String[]{
//                        Valid.SetTgl(Tanggal.getSelectedItem() + ""),
//                        KdKategori.getText(), pemasukan.getText(), KdPtg.getText(), Keterangan.getText(),
//                        CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
//                        noTransaksi.getText(), telahTerima.getText(),
//                        NoSEP.getText(), norm.getText(), nokartu.getText(), norawat.getText(), tglmsk.getText(),
//                        tglklr.getText(), rginap.getText(), kdINACBG.getText(), tarifkls1.getText(),
//                        tarifkls2.getText(), tarifkls3.getText(), hakkelas.getText(), naikKLS.getText(), lmrawat.getText(),
//                        persenSELISIH.getText(), rumusbayar.getText(), jlhdibayar.getText(), nominalPajakSewa.getText(), totalbyrsewa.getText()
//                    });

                    Sequel.menyimpantf("pemasukan_lain","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Pemasukan",26,new String[]{
                    Nomor.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                    KdKategori.getText(),pemasukan.getText(),KdPtg.getText(),Keterangan.getText(),Keperluan.getText(),
                    
                    noTransaksi.getText(),Keterangan.getText(),NoSEP.getText(),norm.getText(),nokartu.getText(),norawat.getText(),
                    tglmsk.getText(),tglklr.getText(),rginap.getText(),kdINACBG.getText(),tarifkls1.getText(),
                        tarifkls2.getText(), tarifkls3.getText(), hakkelas.getText(), naikKLS.getText(), lmrawat.getText(),
                        persenSELISIH.getText(), rumusbayar.getText(), jlhdibayar.getText()
                    });

                    Sequel.menyimpan("biaya_naik_kelas_bpjs", "?,?,?,?,?,?,?,?,?,?,?", "Biaya", 11, new String[]{
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""),norawat.getText(), NoSEP.getText(), byrSimpan.getText(), 
                        (String.valueOf(sdhByr)), (String.valueOf(sisaTagihan)),
                        jlhdibayar.getText(), (String.valueOf(stlhByr)), (String.valueOf(byrKe)), noTransaksi.getText(), statusTran.getText()
                    });

                    Sequel.mengedit("bridging_sep", "no_sep='" + cekNoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");                    
                    Sequel.queryu("delete from tampjurnal");

                    dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(*) total FROM biaya_naik_kelas_bpjs WHERE sisa_setelah_byr='0' AND status_transaksi='dicicil' "));
                    if (!dataSelisihTarif.getText().equals("0")) {
                        Sequel.mengedit("biaya_naik_kelas_bpjs", "no_transaksi='" + noTransaksi.getText() + "'", "status_transaksi='lunas' ");
                    }
                    psakun.setString(1, KdKategori.getText());
                    rs = psakun.executeQuery();
                    if (rs.next()) {
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", pemasukan.getText()});
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), pemasukan.getText(), "0"});
                        jur.simpanJurnal("-", "U", "PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                Sequel.AutoComitTrue();

                WindowSelisihTarif.dispose();
                tampil();
                selisihBaru();
                emptTeks();
                ChkInput.setSelected(true);
                isForm();
            } else {
                JOptionPane.showMessageDialog(null, "Pembayaran lebih besar dari sisa tagihan atau tagihan pasien tersebut sudah lunas...!!!");
                selisihBaru();
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select no_sep from bridging_sep where no_sep=? ", cekNoSEP, NoSEP.getText());

            if (cekNoSEP.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "No. SEP BPJS salah/tidak ditemukan..!!");
                NoSEP.requestFocus();
                selisihBaru();
            } else if (NoSEP.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "No. SEP BPJS belum diisi..!!");
                NoSEP.requestFocus();
                selisihBaru();
            } else if (!cekNoSEP.getText().equals("")) {
                cekSEP();
                Keterangan.setText(nmpasien.getText() + " (" + norm.getText() + ")");
                cekINACBG();
                BtnSimpan6.requestFocus();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah atau belum terisi, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                        cektagihan();
                        pemasukan.setText(byrSimpan.getText());
                        Keperluan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                        cektagihan();
                        pemasukan.setText(byrSimpan.getText());
                        Keperluan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
                    } else if (hakkelas.getText().equals("3")) {
                        hitungSelisih();
                        cektagihan();
                        pemasukan.setText(byrSimpan.getText());
                        Keperluan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
                    }
                }
            }
        }
    }//GEN-LAST:event_NoSEPKeyPressed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void nokartuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nokartuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nokartuKeyPressed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void tglmskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglmskKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglmskKeyPressed

    private void tglklrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglklrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglklrKeyPressed

    private void rginapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rginapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rginapKeyPressed

    private void hakkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hakkelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hakkelasKeyPressed

    private void kdINACBGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdINACBGKeyTyped

    private void kdINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (kdINACBG.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
                kdINACBG.requestFocus();
                selisihBaru();
            } else {
                cekINACBG();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + labelbyr.getText() + "" + Totdibayar.getText());
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + labelbyr.getText() + "" + Totdibayar.getText());
                    } else if (hakkelas.getText().equals("3")) {
                        hitungSelisih();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + labelbyr.getText() + "" + Totdibayar.getText());
                    }
                }
            }
        }
    }//GEN-LAST:event_kdINACBGKeyPressed

    private void deskripsiKDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskripsiKDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deskripsiKDKeyPressed

    private void BtnSelisihBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelisihBaruActionPerformed
        selisihBaru();
    }//GEN-LAST:event_BtnSelisihBaruActionPerformed

    private void tarifkls1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls1KeyTyped

    private void tarifkls1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls1KeyPressed

    private void tarifkls2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls2KeyTyped

    private void tarifkls2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls2KeyPressed

    private void tarifkls3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls3KeyTyped

    private void tarifkls3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls3KeyPressed

    private void lmrawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lmrawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lmrawatKeyPressed

    private void TotdibayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotdibayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TotdibayarKeyTyped

    private void TotdibayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotdibayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotdibayarKeyPressed

    private void persenSELISIHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_persenSELISIHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_persenSELISIHKeyPressed

    private void naikKLSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_naikKLSKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_naikKLSKeyTyped

    private void naikKLSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_naikKLSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_naikKLSKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (kdINACBG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode INACBG masih kosong...!!!!");
            NoSEP.requestFocus();

        } else if (!kdINACBG.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReportqry("rptTarifINACBG.jasper", "report", "::[ Lampiran Kwitansi (Tarif INACBG) ]::",
                " SELECT t.INACBG, k.DESKRIPSI_PMK_59_2014, t.KELAS_RAWAT, convert((t.TARIFF ),int) tarifnya FROM kode_inacbg k "
                + " INNER JOIN tarif_eklaim t on t.INACBG=k.INACBG "
                + " WHERE t.REGIONAL='reg4' AND t.KODE_TARIFF='cp' AND t.JENIS_PELAYANAN='1' "
                + " AND t.INACBG='" + kdINACBG.getText() + "' order by t.KELAS_RAWAT asc", param);
            this.setCursor(Cursor.getDefaultCursor());

            WindowSelisihTarif.dispose();
            tampil();
            selisihBaru();
            emptTeks();
            ChkInput.setSelected(true);
            isForm();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void SudahbyrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SudahbyrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_SudahbyrKeyTyped

    private void SudahbyrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SudahbyrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SudahbyrKeyPressed

    private void SisaTagihanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SisaTagihanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_SisaTagihanKeyTyped

    private void SisaTagihanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SisaTagihanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SisaTagihanKeyPressed

    private void jlhdibayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlhdibayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jlhdibayarKeyTyped

    private void jlhdibayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlhdibayarKeyPressed

    }//GEN-LAST:event_jlhdibayarKeyPressed

    private void ChkSesuaiTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSesuaiTagihanActionPerformed
        cektagihan();
    }//GEN-LAST:event_ChkSesuaiTagihanActionPerformed

    private void cekNoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekNoSEPKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cekNoSEPKeyTyped

    private void cekNoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekNoSEPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekNoSEPKeyPressed

    private void nominal1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal1KeyTyped

    private void nominal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal1KeyPressed

    private void hasilLMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyTyped

    private void hasilLMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyPressed

    private void nominal2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal2KeyTyped

    private void nominal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal2KeyPressed

    private void rumusbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rumusbayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_rumusbayarKeyTyped

    private void rumusbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rumusbayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rumusbayarKeyPressed

    private void statusSELISIHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusSELISIHKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_statusSELISIHKeyTyped

    private void statusSELISIHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusSELISIHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusSELISIHKeyPressed

    private void cekNoTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekNoTransaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cekNoTransaksiKeyTyped

    private void cekNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekNoTransaksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekNoTransaksiKeyPressed

    private void bayarKeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarKeKeyTyped

    private void bayarKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarKeKeyPressed

    private void jumlhBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlhBayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlhBayarKeyTyped

    private void jumlhBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlhBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlhBayarKeyPressed

    private void belumDibayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_belumDibayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_belumDibayarKeyTyped

    private void belumDibayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_belumDibayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_belumDibayarKeyPressed

    private void statusTranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusTranKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTranKeyTyped

    private void statusTranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusTranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTranKeyPressed

    private void noTranAngsurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTranAngsurKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_noTranAngsurKeyTyped

    private void noTranAngsurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTranAngsurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTranAngsurKeyPressed

    private void dataSelisihTarifKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataSelisihTarifKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dataSelisihTarifKeyTyped

    private void dataSelisihTarifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataSelisihTarifKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataSelisihTarifKeyPressed

    private void byrSimpanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_byrSimpanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_byrSimpanKeyTyped

    private void byrSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_byrSimpanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_byrSimpanKeyPressed

    private void kodekamarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodekamarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kodekamarKeyTyped

    private void kodekamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodekamarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodekamarKeyPressed

    private void noTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTransaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_noTransaksiKeyTyped

    private void noTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTransaksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTransaksiKeyPressed

    private void RekapPemasukanLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapPemasukanLainActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReportqry("rptPemasukanLain.jasper", "report", "::[ Data Pemasukan Lain-Lain ]::",
                    "select pemasukan_lain.tanggal, pemasukan_lain.keterangan, pemasukan_lain.keperluan, pemasukan_lain.besar, pemasukan_lain.nip, "
                    + "petugas.nama,pemasukan_lain.kode_kategori,kategori_pemasukan_lain.nama_kategori "
                    + "from pemasukan_lain inner join petugas inner join kategori_pemasukan_lain on pemasukan_lain.nip=petugas.nip "
                    + "and pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori where "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pemasukan_lain.keperluan like '%" + TCari.getText().trim() + "%' or "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pemasukan_lain.keterangan like '%" + TCari.getText().trim() + "%' or "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pemasukan_lain.nip like '%" + TCari.getText().trim() + "%' or "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and petugas.nama like '%" + TCari.getText().trim() + "%' or "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and pemasukan_lain.kode_kategori like '%" + TCari.getText().trim() + "%' or "
                    + "pemasukan_lain.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and kategori_pemasukan_lain.nama_kategori like '%" + TCari.getText().trim() + "%' order by pemasukan_lain.tanggal ", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_RekapPemasukanLainActionPerformed

    private void cetakNotaNaikKlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakNotaNaikKlsActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbResep.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbResep.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbResep.requestFocus();
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("keterangan", Keperluan.getText());
            param.put("judul_kwitansi", "KWITANSI PEMBAYARAN (Angsuran Ke - "+bayarKe.getText()+")");

            if (statusTran.getText().equals("dicicil")) {
                Valid.MyReportqry("rptNotaSelisihTarifCicil.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Angsuran) ]::",
                    " SELECT CONCAT(p.nm_pasien,' (No.RM : ',pl.no_rkm_medis,')') pasien, pl.no_kartu, pl.no_sep, "
                    + " pl.ruang_inap, pl.lm_rawat, DATE_FORMAT(pl.tgl_masuk,'%d-%m-%Y') tgl_msk, DATE_FORMAT(pl.tgl_pulang,'%d-%m-%Y') tgl_plg, "
                    + " pl.hak_kelas, pl.kode_inacbg, ki.DESKRIPSI_PMK_59_2014 deskripsi_inacbg, pl.no_transaksi, "
                    + " pl.rumus_selisih_tarif, pl.besar, pt.nama petugas "
                    + " FROM pemasukan_lain pl INNER JOIN petugas pt ON pt.nip=pl.nip INNER JOIN pasien p ON p.no_rkm_medis=pl.no_rkm_medis "
                    + " INNER JOIN kode_inacbg ki ON ki.INACBG=pl.kode_inacbg WHERE pl.no_transaksi='" + noTransaksi.getText() + "' ", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();

            } else if (statusTran.getText().equals("lunas") || (statusTran.getText().equals(""))) {
                Valid.MyReportqry("rptNotaSelisihTarif.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Pelunasan) ]::",
                    " SELECT CONCAT(p.nm_pasien,' (No.RM : ',pl.no_rkm_medis,')') pasien, pl.no_kartu, pl.no_sep, "
                    + " pl.ruang_inap, pl.lm_rawat, DATE_FORMAT(pl.tgl_masuk,'%d-%m-%Y') tgl_msk, DATE_FORMAT(pl.tgl_pulang,'%d-%m-%Y') tgl_plg, "
                    + " pl.hak_kelas, pl.kode_inacbg, ki.DESKRIPSI_PMK_59_2014 deskripsi_inacbg, pl.no_transaksi, "
                    + " pl.rumus_selisih_tarif, pl.besar, pt.nama petugas "
                    + " FROM pemasukan_lain pl INNER JOIN petugas pt ON pt.nip=pl.nip INNER JOIN pasien p ON p.no_rkm_medis=pl.no_rkm_medis "
                    + " INNER JOIN kode_inacbg ki ON ki.INACBG=pl.kode_inacbg WHERE pl.no_transaksi='" + noTransaksi.getText() + "' ", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
            }
        }
    }//GEN-LAST:event_cetakNotaNaikKlsActionPerformed

    private void lihatSelisihTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatSelisihTarifActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_rawat=? ", cekNoTransaksi, norawat.getText());
        Sequel.cariIsi("select no_transaksi from biaya_naik_kelas_bpjs where no_transaksi=? ", noTranAngsur, cekNoTransaksi.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbResep.requestFocus();
        } else if (NoSEP.getText().equals("-")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pemasukan lain yg. termasuk jenis selisih tarif INACBG dg. mengklik data pada tabel...!!!");
            tbResep.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbResep.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Transaksi pembayaran tidak sesuai...!!!");
            tbResep.requestFocus();
        } else {
            WindowSelisihTarif.setSize(724, 404);
            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
            WindowSelisihTarif.setVisible(true);

            BtnCloseIn6.requestFocus();
            BtnSimpan6.setEnabled(false);
            BtnPrint.setEnabled(true);
            cekSEP();
            cekINACBG();
            hitungSelisih();
            cektagihan();

            if (WindowSelisihTarif.isVisible() == true) {
                BtnSimpan.setVisible(false);
                BtnBatal.setVisible(false);
            } else if (WindowSelisihTarif.isVisible() == false) {
                BtnSimpan.setVisible(true);
                BtnBatal.setVisible(true);
            }

            if (!cekNoTransaksi.getText().trim().equals("") && (noTranAngsur.getText().equals(""))) {
                jLabel51.setVisible(false);
                Sudahbyr.setVisible(false);
                jLabel52.setVisible(false);
                SisaTagihan.setVisible(false);
                jLabel53.setVisible(false);
                ChkSesuaiTagihan.setVisible(false);
                jlhdibayar.setVisible(false);
            } else {
                jLabel51.setVisible(true);
                Sudahbyr.setVisible(true);
                jLabel52.setVisible(true);
                SisaTagihan.setVisible(true);
                jLabel53.setVisible(true);
                ChkSesuaiTagihan.setVisible(true);
                jlhdibayar.setVisible(true);
            }
        }
    }//GEN-LAST:event_lihatSelisihTarifActionPerformed

    private void RekapSelisihLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapSelisihLunasActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(DISTINCT no_sep) cekpasien FROM pemasukan_lain WHERE tanggal BETWEEN "
                    + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' "));

            if (dataSelisihTarif.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Untuk pelunasan selisih tarif naik kls. rwt pasien BPJS Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " datanya tidak ditemukan...!!!");
                emptTeks();
                selisihBaru();
            } else if (!dataSelisihTarif.getText().equals("0")) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReportqry("rptRekapSelisihTarifBPJS.jasper", "report", "::[ Laporan Rekap Selisih Tarif Naik Kelas BPJS (Pelunasan) ]::",
                        "select DISTINCT i.no_rawat, CONCAT(r.no_rkm_medis,' - ',p.nm_pasien) noRM_pasien, DATE_FORMAT(pl.tanggal,'%d/%m/%Y') tgl_byr, "
                        + "concat('Kelas ',pl.hak_kelas) hak_kls, pl.naik_kelas naik_kekls, pl.kode_inacbg, format(IFNULL(sum(l.totalbiaya),''),0) ttl_rincian_RC, "
                        + "case when b.nm_bangsal like '%Melati%' then pl.besar else 0 end Melati, "
                        + "case when b.nm_bangsal like '%Anggrek%' then pl.besar else 0 end Anggrek, "
                        + "case when b.nm_bangsal like '%Cempaka%' then pl.besar else 0 end Cempaka, "
                        + "case when b.nm_bangsal like '%Dahlia%' then pl.besar else 0 end Dahlia, "
                        + "case when b.nm_bangsal like '%Kenanga%' then pl.besar else 0 end Kenanga, "
                        + "case when b.nm_bangsal like '%Mawar%' then pl.besar else 0 end Mawar, "
                        + "case when b.nm_bangsal like '%Teratai%' then pl.besar else 0 end Teratai, "
                        + "case when b.nm_bangsal like '%Kebidanan%' then pl.besar else 0 end Kebidanan, "
                        + "pl.besar total_selisih from pemasukan_lain pl "
                        + "left join kamar_inap i on i.no_rawat = pl.no_rawat "
                        + "left join reg_periksa r on i.no_rawat = r.no_rawat "
                        + "left join kamar k on k.kd_kamar = i.kd_kamar "
                        + "left join bangsal b on b.kd_bangsal = k.kd_bangsal "
                        + "left join billing l on i.no_rawat = l.no_rawat "
                        + "left join pasien p on p.no_rkm_medis=r.no_rkm_medis "
                        + "where pl.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' "
                        + "and pl.kode_kategori='sbpjs' and i.stts_pulang not in ('-','Pindah Kamar') "
                        + "group by l.no_rawat, pl.no_transaksi ORDER BY pl.tanggal", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
            }
        }
    }//GEN-LAST:event_RekapSelisihLunasActionPerformed

    private void RekapSelisihAngsurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapSelisihAngsurActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(DISTINCT no_sep) cekpasien FROM biaya_naik_kelas_bpjs WHERE (total_tagihan <> jumlah_byr) and tgl_transaksi BETWEEN "
                    + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' "));

            if (dataSelisihTarif.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Untuk angsuran selisih tarif naik kls. rwt pasien BPJS Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " datanya tidak ditemukan...!!!");
                emptTeks();
                selisihBaru();
            } else if (!dataSelisihTarif.getText().equals("0")) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReportqry("rptRekapSelisihTarifBPJSAngsur.jasper", "report", "::[ Laporan Rekap Selisih Tarif Naik Kelas BPJS (Angsuran) ]::",
                        " SELECT DISTINCT bn.no_rawat, bn.no_transaksi, p.nm_pasien, rp.no_rkm_medis no_rm, pl.ruang_inap, "
                        + " CONCAT(DATE_FORMAT(pl.tgl_masuk,'%d/%m/%Y'),' s.d ',DATE_FORMAT(pl.tgl_pulang,'%d/%m/%Y') ) tgl_msk_klr, "
                        + " CONCAT(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) alamat, p.no_tlp, format(pl.besar,0) tot_tagihan, "
                        + " CONCAT(DATE_FORMAT(bn.tgl_transaksi,'%d/%m/%Y'),' Angs. Ke ',bn.pembayaran_ke) pembayaran, "
                        + " bn.jumlah_byr, bn.sisa_setelah_byr, UPPER(bn.status_transaksi) stts_tran, "
                        + " (SELECT COUNT(DISTINCT no_rawat) total FROM biaya_naik_kelas_bpjs where (total_tagihan <> jumlah_byr) "
                        + " and tgl_transaksi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') total_px "
                        + " FROM pemasukan_lain pl INNER JOIN reg_periksa rp ON rp.no_rawat=pl.no_rawat "
                        + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + " INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel "
                        + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                        + " INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                        + " INNER JOIN kamar_inap ki ON ki.no_rawat=pl.no_rawat "
                        + " INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + " INNER JOIN biaya_naik_kelas_bpjs bn ON bn.no_transaksi=pl.no_transaksi "
                        + " INNER JOIN bridging_sep bs ON bs.no_rawat=pl.no_rawat "
                        + " WHERE pl.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' and "
                        + " pl.kode_kategori='sbpjs' and ki.stts_pulang not in ('-','Pindah Kamar') and (pl.besar <> bn.jumlah_byr) ORDER BY bn.no_rawat, bn.tgl_transaksi, bn.pembayaran_ke", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
            }
        }
    }//GEN-LAST:event_RekapSelisihAngsurActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemasukanLain dialog = new DlgPemasukanLain(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn6;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSelisihBaru;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan6;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkSesuaiTagihan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdKategori;
    private widget.TextBox KdPtg;
    private widget.TextBox Keperluan;
    private widget.TextBox Keterangan;
    private widget.Label LCount;
    private widget.TextBox NmKategori;
    private widget.TextBox NmPtg;
    private widget.TextBox NoSEP;
    private widget.TextBox Nomor;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JMenuItem RekapPemasukanLain;
    private javax.swing.JMenuItem RekapSelisihAngsur;
    private javax.swing.JMenuItem RekapSelisihLunas;
    private widget.ScrollPane Scroll;
    private widget.TextBox SisaTagihan;
    private widget.TextBox Sudahbyr;
    private widget.TextBox TCari;
    private widget.Tanggal Tanggal;
    private widget.TextBox Totdibayar;
    private javax.swing.JDialog WindowSelisihTarif;
    private widget.TextBox bayarKe;
    private widget.TextBox belumDibayar;
    private widget.Button btnKategori;
    private widget.Button btnPetugas;
    private widget.TextBox byrSimpan;
    private widget.TextBox cekNoSEP;
    private widget.TextBox cekNoTransaksi;
    private javax.swing.JMenuItem cetakNotaNaikKls;
    private javax.swing.JMenuItem cetakkwitansi;
    private widget.TextBox dataSelisihTarif;
    private widget.TextBox deskripsiKD;
    private widget.TextBox hakkelas;
    private widget.TextBox hasilLM;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jlhdibayar;
    private widget.TextBox jumlhBayar;
    private widget.TextBox kdINACBG;
    private widget.TextBox kodekamar;
    private widget.Label labelbyr;
    private javax.swing.JMenuItem lihatSelisihTarif;
    private widget.TextBox lmrawat;
    private widget.Label lunas;
    private widget.TextBox naikKLS;
    private widget.TextBox nmpasien;
    private widget.TextBox noTranAngsur;
    private widget.TextBox noTransaksi;
    private widget.TextBox nokartu;
    private widget.TextBox nominal1;
    private widget.TextBox nominal2;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox pemasukan;
    private widget.TextBox persenSELISIH;
    private widget.TextBox rginap;
    private widget.TextBox rumusbayar;
    private widget.TextBox statusSELISIH;
    private widget.TextBox statusTran;
    private widget.TextBox tarifkls1;
    private widget.TextBox tarifkls2;
    private widget.TextBox tarifkls3;
    private widget.Table tbResep;
    private widget.TextBox tglklr;
    private widget.TextBox tglmsk;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{   
            ps=koneksi.prepareStatement(
                    "SELECT pl.no_masuk, pl.tanggal, pl.keterangan, pl.keperluan, pl.besar, pl.nip, p.nama, pl.kode_kategori, kpl.nama_kategori, "
                    + "CONCAT('Angs. Ke ',bp.pembayaran_ke,' ',UPPER(bp.status_transaksi)) stts_tran, IFNULL(pl.no_transaksi,'-') no_transaksi, IFNULL(pl.no_sep,'-') no_sep, "
                    + "IFNULL(pl.no_rkm_medis,'-') no_rm, IFNULL(pl.no_kartu,'-') no_kartu, "
                    + "IFNULL(pl.no_rawat,'-') no_rwt, IFNULL(pl.tgl_masuk,'-') tgl_msk, IFNULL(pl.tgl_pulang,'-') tgl_plg, IFNULL(pl.ruang_inap,'-') rg_rwt, IFNULL(pl.kode_inacbg,'-') kd_inacbg, "
                    + "IFNULL(pl.hak_kelas,'-') hak_kls, IFNULL(pl.naik_kelas,'-') naik_kls, IFNULL(pl.lm_rawat,'-') lm_rwt, pl.persen_tambahan,  "
                    + "IFNULL(pl.rumus_selisih_tarif,'-') rumus, pl.total_byr "
                    + "FROM pemasukan_lain pl INNER JOIN petugas p on p.nip=pl.nip INNER JOIN kategori_pemasukan_lain kpl on kpl.kode_kategori=pl.kode_kategori "
                    + "INNER JOIN biaya_naik_kelas_bpjs bp on bp.no_transaksi=pl.no_transaksi where "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.keterangan LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.nip LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND p.nama LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.kode_kategori LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.no_transaksi LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.no_sep LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.no_rkm_medis LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.no_kartu LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.no_rawat LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.tgl_masuk LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.tgl_pulang LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.ruang_inap LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.kode_inacbg LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.hak_kelas LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.naik_kelas LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.lm_rawat LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.persen_tambahan LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.rumus_selisih_tarif LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND pl.total_byr LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND bp.status_transaksi LIKE ? OR  "
                    + "pl.tanggal BETWEEN ? AND ? AND kpl.nama_kategori LIKE ?  "
                    + "ORDER BY pl.tanggal");
            
//                "select pemasukan_lain.no_masuk,pemasukan_lain.tanggal, pemasukan_lain.keterangan, pemasukan_lain.keperluan, pemasukan_lain.besar, pemasukan_lain.nip, "+
//                "petugas.nama,pemasukan_lain.kode_kategori,kategori_pemasukan_lain.nama_kategori "+
//                "from pemasukan_lain inner join petugas inner join kategori_pemasukan_lain on pemasukan_lain.nip=petugas.nip "+
//                "and pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori where "+
//                "pemasukan_lain.tanggal between ? and ? and pemasukan_lain.keterangan like ? or "+
//                "pemasukan_lain.tanggal between ? and ? and pemasukan_lain.nip like ? or "+
//                "pemasukan_lain.tanggal between ? and ? and petugas.nama like ? or "+
//                "pemasukan_lain.tanggal between ? and ? and pemasukan_lain.kode_kategori like ? or "+
//                "pemasukan_lain.tanggal between ? and ? and kategori_pemasukan_lain.nama_kategori like ? order by pemasukan_lain.tanggal");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                
                ps.setString(16,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(17,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(18,"%"+TCari.getText().trim()+"%");
                ps.setString(19,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(20,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(21,"%"+TCari.getText().trim()+"%");
                ps.setString(22,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(23,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(24,"%"+TCari.getText().trim()+"%");
                ps.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(27,"%"+TCari.getText().trim()+"%");
                ps.setString(28,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(29,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(30,"%"+TCari.getText().trim()+"%");
                ps.setString(31,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(32,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(33,"%"+TCari.getText().trim()+"%");
                ps.setString(34,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(35,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(36,"%"+TCari.getText().trim()+"%");
                ps.setString(37,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(38,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(39,"%"+TCari.getText().trim()+"%");
                ps.setString(40,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(41,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(42,"%"+TCari.getText().trim()+"%");
                ps.setString(43,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(44,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(45,"%"+TCari.getText().trim()+"%");
                ps.setString(46,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(47,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(48,"%"+TCari.getText().trim()+"%");
                ps.setString(49,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(50,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(51,"%"+TCari.getText().trim()+"%");
                ps.setString(52,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(53,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(54,"%"+TCari.getText().trim()+"%");
                ps.setString(55,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(56,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(57,"%"+TCari.getText().trim()+"%");
                ps.setString(58,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(59,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(60,"%"+TCari.getText().trim()+"%");                
                ps.setString(61,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(62,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(63,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                total=0;
                while(rs.next()){                
                    tabMode.addRow(new Object[]{
                        rs.getString("no_masuk"),rs.getString("tanggal"),rs.getString("kode_kategori")+" "+rs.getString("nama_kategori"),
                        rs.getString("nip")+" "+rs.getString("nama"),rs.getDouble("besar"),rs.getString("keterangan"),rs.getString("keperluan"),
                        rs.getString("kode_kategori"),rs.getString("nip"),
                        
                        rs.getString("stts_tran"),rs.getString("no_transaksi"),rs.getString("no_sep"),rs.getString("no_rm"),rs.getString("no_kartu"),rs.getString("no_rwt"),
                        rs.getString("tgl_msk"),rs.getString("tgl_plg"),rs.getString("rg_rwt"),rs.getString("kd_inacbg"),
                        rs.getString("hak_kls"),rs.getString("naik_kls"),rs.getString("lm_rwt"),rs.getDouble("persen_tambahan"),rs.getString("rumus"),
                        rs.getDouble("total_byr")
                    });
                    total=total+rs.getDouble("besar");
                }
            } catch (Exception e){
                System.out.println(e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
                
            if(total>0){
                tabMode.addRow(new Object[]{"",">>","Jumlah Total Pemasukan :","",total,"","",""}); 
            }        
            LCount.setText((""+(tabMode.getRowCount()-1)).replaceAll("-1","0"));                        
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }        
    }

    public void emptTeks() {
        pemasukan.setText("0");
        Keperluan.setText("");
        KdKategori.setText("");
        NmKategori.setText("");
        Tanggal.setDate(new Date());
        KdKategori.requestFocus();
        autoNomor();
        autoNomorTransaksi();
        
        Keterangan.setText("");        
        BtnSimpan.setVisible(true);
        BtnBatal.setVisible(true);
        cekNoTransaksi.setText("");
        bayarKe.setText("");
        jumlhBayar.setText("");
        belumDibayar.setText("");
        dataSelisihTarif.setText("");
        
        jLabel51.setVisible(true);
        Sudahbyr.setVisible(true);
        jLabel52.setVisible(true);
        SisaTagihan.setVisible(true);
        jLabel53.setVisible(true);
        ChkSesuaiTagihan.setVisible(true);
        jlhdibayar.setVisible(true);
    }

    private void getData() {
        if (tbResep.getSelectedRow() != -1) {
            Nomor.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 0).toString().replaceAll(tbResep.getValueAt(tbResep.getSelectedRow(), 6).toString() + " ", ""));
            NmKategori.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 2).toString().replaceAll(tbResep.getValueAt(tbResep.getSelectedRow(), 6).toString() + " ", ""));
            NmPtg.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 3).toString().replaceAll(tbResep.getValueAt(tbResep.getSelectedRow(), 8).toString() + " ", ""));
            pemasukan.setText(String.valueOf(tbResep.getValueAt(tbResep.getSelectedRow(), 4).toString()));
            Keterangan.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 5).toString());
            Keperluan.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 6).toString());
            KdKategori.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 7).toString());
            KdPtg.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 8).toString());
            Valid.SetTgl(Tanggal, tbResep.getValueAt(tbResep.getSelectedRow(), 1).toString());

            noTransaksi.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 10).toString());
            NoSEP.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 11).toString());
            norawat.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 14).toString());
            Sequel.cariIsi("select no_sep from bridging_sep where no_sep=? ", cekNoSEP, NoSEP.getText());
            kdINACBG.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 18).toString());
            rumusbayar.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 23).toString());
            Sequel.cariIsi("select no_rawat from biaya_naik_kelas_bpjs where no_transaksi=? ", dataSelisihTarif, noTransaksi.getText());

            if (KdKategori.getText().equals("SBPJS") && (!dataSelisihTarif.getText().equals(""))) {
                Sequel.cariIsi("select pembayaran_ke from biaya_naik_kelas_bpjs where no_transaksi=? ", bayarKe, noTransaksi.getText());
                Sequel.cariIsi("select jumlah_byr from biaya_naik_kelas_bpjs where no_transaksi=? ", jumlhBayar, noTransaksi.getText());
                Sequel.cariIsi("select sisa_setelah_byr from biaya_naik_kelas_bpjs where no_transaksi=? ", belumDibayar, noTransaksi.getText());
                Sequel.cariIsi("select status_transaksi from biaya_naik_kelas_bpjs where no_transaksi=? ", statusTran, noTransaksi.getText());
                Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_transaksi=? ", Totdibayar, noTransaksi.getText());

                DecimalFormat df4 = new DecimalFormat("####");
                double ii = Double.parseDouble(jumlhBayar.getText().trim());
                double jj = Double.parseDouble(belumDibayar.getText().trim());
                double kk = Double.parseDouble(Totdibayar.getText().trim());

                if (statusTran.getText().equals("dicicil")) {
                    Keperluan.setText("Jumlah pembayaran angsuran ke " + bayarKe.getText() + " Rp. " + Valid.SetAngka3(ii)
                            + ", dan sisa tagihan yg. belum dibayar adalah Rp. " + Valid.SetAngka3(jj));
                } else if (statusTran.getText().equals("lunas")) {
                    Keperluan.setText("Jumlah pembayaran utk. pelunasan tagihan dgn. kalkulasi penghitungan " + rumusbayar.getText()
                            + " = Rp. " + Valid.SetAngka3(kk));
                }

            } else if (KdKategori.getText().equals("SBPJS") && (dataSelisihTarif.getText().equals(""))) {
                Keperluan.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 6).toString());
            } else {
                Keperluan.setText(tbResep.getValueAt(tbResep.getSelectedRow(), 6).toString());
            }
        }
    }
   
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,128));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        if(akses.getjml2()>=1){
            KdPtg.setEditable(false);
            btnPetugas.setEnabled(false);
            KdPtg.setText(akses.getkode());
            BtnSimpan.setEnabled(akses.getpemasukan_lain());
            BtnHapus.setEnabled(akses.getpemasukan_lain());
            BtnPrint.setEnabled(akses.getpemasukan_lain());
            Sequel.cariIsi("select nama from petugas where nip=?", NmPtg,KdPtg.getText());
        }      
        
    }
    
    private void autoNomor() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_masuk,3),signed)),0) from pemasukan_lain where tanggal like '%"+Valid.SetTgl(Tanggal.getSelectedItem()+"")+"%' ",
                "PL"+Tanggal.getSelectedItem().toString().substring(8,10)+Tanggal.getSelectedItem().toString().substring(3,5)+Tanggal.getSelectedItem().toString().substring(0,2),3,Nomor); 
    }
    
    public void selisihBaru() {
        cekNoSEP.setText("");
        NoSEP.setText("");
        norm.setText("");
        nmpasien.setText("");
        nokartu.setText("");
        norawat.setText("");
        hakkelas.setText("");
        tglmsk.setText("");
        tglklr.setText("");
        rginap.setText("");
        kdINACBG.setText("");
        deskripsiKD.setText("");
        statusTran.setText("");
        noTranAngsur.setText("");

        tarifkls1.setText("0");
        tarifkls2.setText("0");
        tarifkls3.setText("0");
        Totdibayar.setText("0");
        persenSELISIH.setText("0");
        labelbyr.setText("Total bayar : Rp. ");
        nominal1.setText("0");
        nominal2.setText("0");
        hasilLM.setText("");
        lmrawat.setText("0");
        naikKLS.setText("");
        byrSimpan.setText("");
        rumusbayar.setText("");
        statusSELISIH.setText("");
        Sudahbyr.setText("0");
        SisaTagihan.setText("0");   
        jlhdibayar.setText("");
        kodekamar.setText("");
        
        totTagihan = 0;
        sdhByr = 0;
        sisaTagihan = 0;
        byrKe = 0;
        
        lunas.setVisible(false);
        ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
        ChkSesuaiTagihan.setSelected(true);
        ChkSesuaiTagihan.setEnabled(true);        
    }
    
    public void cekSEP() {
        Sequel.cariIsi("select nomr from bridging_sep where no_sep=? ", norm, cekNoSEP.getText());
        Sequel.cariIsi("select kode_inacbg from bridging_sep where jnspelayanan='1' and no_sep=? ", kdINACBG, cekNoSEP.getText());
        Sequel.cariIsi("select nama_pasien from bridging_sep where no_sep=? ", nmpasien, cekNoSEP.getText());
        Sequel.cariIsi("select no_kartu from bridging_sep where no_sep=? ", nokartu, cekNoSEP.getText());
        Sequel.cariIsi("select no_rawat from bridging_sep where no_sep=? ", norawat, cekNoSEP.getText());
        Sequel.cariIsi("select klsrawat from bridging_sep where no_sep=? ", hakkelas, cekNoSEP.getText());

        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=? ", tglmsk, norawat.getText());
        Sequel.cariIsi("select tgl_keluar from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat=? ", tglklr, norawat.getText());
        
        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang not in ('-','Pindah Kamar') "
                + " and ki.no_rawat=? ", rginap, norawat.getText());
        Sequel.cariIsi("SELECT b.kd_bangsal FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang not in ('-','Pindah Kamar') "
                + " and ki.no_rawat=? ", kodekamar, norawat.getText());
        
        Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and no_rawat=? ", naikKLS, norawat.getText());

        Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                + "and b.kd_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, cekNoSEP.getText());
        
        if (kodekamar.getText().equals("Peri") || (kodekamar.getText().equals("R.ICU")) || (kodekamar.getText().equals("R.Iso"))) {
            naikKLS.setText("");
            hasilLM.setText("");
            
            Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE b.kd_bangsal in ('peri','r.icu','r.iso') and "
                    + "ki.no_rawat=? order by ki.tgl_keluar desc limit 1 ", naikKLS, norawat.getText());

            Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                    + "WHERE r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                    + "and b.kd_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());
        }

        if (hasilLM.getText().equals("")) {
            lmrawat.setText("0");
        } else if (!hasilLM.getText().equals("")) {
            lmrawat.setText(hasilLM.getText());
        }

//        if ((Double.parseDouble(lmrawat.getText())) <= 3) {
            persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs1 from set_tarif"));
//        } else if ((Double.parseDouble(lmrawat.getText())) >= 4) {
//            persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs2 from set_tarif"));
//        }

        if (lmrawat.getText().equals("0")) {
            persenSELISIH.setText("0");
        }

//        if ((hakkelas.getText().equals("1")) && (naikKLS.getText().equals("Kelas 1"))
//                || (hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 2"))
//                || (hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 3"))) {
//            JOptionPane.showMessageDialog(null, "Pasien sudah sesuai hak kelasnya...!!!");
//            selisihBaru();
//        }

        if ((hakkelas.getText().equals("1")) && (naikKLS.getText().equals("Kelas 1"))
                || (hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 2"))
                || (hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 3"))) {
            JOptionPane.showMessageDialog(null, "Pasien sudah sesuai hak kelasnya...!!!");
            selisihBaru();
        } else if (kodekamar.getText().equals("Peri") || (kodekamar.getText().equals("R.ICU")) || (kodekamar.getText().equals("R.Iso"))) {
            JOptionPane.showMessageDialog(null, "Karena pasien ini dirawat diruang "+rginap.getText()+", maka tidak dianggap naik kelas rawat...!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
        } else if (naikKLS.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum pulang, petugas ruang inap harus memulangkan dulu...!!!");
            selisihBaru();
        }
    }
    
    public void cekINACBG() {
        Sequel.cariIsi("SELECT DESKRIPSI_PMK_59_2014 FROM kode_inacbg WHERE INACBG=? ", deskripsiKD, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='cp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='cp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='cp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
    }
    
    public void hitungSelisih() {
        totTagihan = 0;
        sdhByr = 0;
        sisaTagihan = 0;
        byrKe = 0;

        DecimalFormat df4 = new DecimalFormat("####");
        double a = Double.parseDouble(tarifkls1.getText().trim());
        double b = Double.parseDouble(tarifkls2.getText().trim());
        double c = Double.parseDouble(tarifkls3.getText().trim());
        double d = Double.parseDouble(persenSELISIH.getText());
        cekData = Sequel.cariInteger("select count(1) from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "'");

        if (cekData <= 0) {
            byrKe += 1;

            if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 2"))) {
                Totdibayar.setText(Valid.SetAngka3(b - c));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 2 Rp. " + Valid.SetAngka3(b) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + "");
                byrSimpan.setText(df4.format(b - c));                
                SisaTagihan.setText(Valid.SetAngka3(b - c));
                sisaTagihan = b - c;

            } else if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 1"))) {
                Totdibayar.setText(Valid.SetAngka3(a - c));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + "");
                byrSimpan.setText(df4.format(a - c));
                SisaTagihan.setText(Valid.SetAngka3(a - c));
                sisaTagihan = a - c;

            } else if ((hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 1"))) {
                Totdibayar.setText(Valid.SetAngka3(a - b));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + "");
                byrSimpan.setText(df4.format(a - b));
                SisaTagihan.setText(Valid.SetAngka3(a - b));
                sisaTagihan = a - b;

            } else if ((hakkelas.getText().equals("3")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                nominal1.setText(df4.format(a - c));
                double e = Double.parseDouble(nominal1.getText());

                nominal2.setText(df4.format(a * d / 100));
                double f = Double.parseDouble(nominal2.getText());
                Totdibayar.setText(Valid.SetAngka3(e + f));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + " + (Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%)");
                byrSimpan.setText(df4.format(e + f));
                SisaTagihan.setText(Valid.SetAngka3(e + f));
                sisaTagihan = e + f;

            } else if ((hakkelas.getText().equals("2")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                nominal1.setText(df4.format(a - b));
                double g = Double.parseDouble(nominal1.getText());

                nominal2.setText(df4.format(a * d / 100));
                double h = Double.parseDouble(nominal2.getText());
                Totdibayar.setText(Valid.SetAngka3(g + h));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + " + (Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%)");
                byrSimpan.setText(df4.format(g + h));
                SisaTagihan.setText(Valid.SetAngka3(g + h));
                sisaTagihan = g + h;

            } else if ((hakkelas.getText().equals("1")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                Totdibayar.setText(Valid.SetAngka3(a * d / 100));
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "% = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%");
                byrSimpan.setText(df4.format(a * d / 100));
                SisaTagihan.setText(Valid.SetAngka3(a * d / 100));
                sisaTagihan = (a * d / 100);
            }

        } else {
            totTagihan = Sequel.cariIsiAngka("select total_tagihan from biaya_naik_kelas_bpjs where no_sep ='" + cekNoSEP.getText() + "' order by tgl_transaksi desc limit 1 ");
            sdhByr = Sequel.cariIsiAngka("select jumlah_byr+sudah_dibayar from biaya_naik_kelas_bpjs where no_sep ='" + cekNoSEP.getText() + "' order by tgl_transaksi desc limit 1 ");
            sisaTagihan = Sequel.cariIsiAngka("select sisa_setelah_byr from biaya_naik_kelas_bpjs where no_sep ='" + cekNoSEP.getText() + "' order by tgl_transaksi desc limit 1 ");
            byrKe = Sequel.cariIsiAngka("select pembayaran_ke from biaya_naik_kelas_bpjs where no_sep ='" + cekNoSEP.getText() + "' order by tgl_transaksi desc limit 1 ") + 1;

            Totdibayar.setText(Valid.SetAngka3(totTagihan));
            Sudahbyr.setText(Valid.SetAngka3(sdhByr));
            SisaTagihan.setText(Valid.SetAngka3(sisaTagihan));
            byrSimpan.setText(String.valueOf(totTagihan));
            
            if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 2"))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 2 Rp. " + Valid.SetAngka3(b) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + "");

            } else if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 1"))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + "");

            } else if ((hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 1"))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + "");

            } else if ((hakkelas.getText().equals("3")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 3 Rp. " + Valid.SetAngka3(c) + " + (Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%)");

            } else if ((hakkelas.getText().equals("2")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + " + (Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%)");

            } else if ((hakkelas.getText().equals("1")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
                labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "% = yang harus dibayar Rp. ");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " X " + persenSELISIH.getText() + "%");
            }

            ChkSesuaiTagihan.setSelected(false);
            ChkSesuaiTagihan.setText("Pembayaran diangsur >>>");
            jlhdibayar.setText((String.valueOf(sisaTagihan)));
            jlhdibayar.setEditable(true);
            jlhdibayar.requestFocus();
        }
    }
    
    public void cektagihan() {
        if (ChkSesuaiTagihan.isSelected() == true) {
            ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
            ChkSesuaiTagihan.setEnabled(true);
            statusTran.setText("lunas");
            jlhdibayar.setText((String.valueOf(sisaTagihan)));
            jlhdibayar.setEditable(false);
            BtnSimpan6.requestFocus();
            
            if (Sudahbyr.getText().equals(Totdibayar.getText())) {
                if (NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (!NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else {
                    lunas.setVisible(true);
                }
            } else {
                lunas.setVisible(false);
            }
        } else if (ChkSesuaiTagihan.isSelected() == false) {
            ChkSesuaiTagihan.setText("Pembayaran diangsur >>>");
            ChkSesuaiTagihan.setEnabled(true);
            statusTran.setText("dicicil");
            jlhdibayar.setText("");
            jlhdibayar.setEditable(true);
            jlhdibayar.requestFocus();
            
            if (Sudahbyr.getText().equals(Totdibayar.getText())) {
                if (NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (!NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else {
                    lunas.setVisible(true);
                }
            } else {
                lunas.setVisible(false);
            }
        }
    }
    
    public void autoNomorTransaksi() {
        Valid.autoNomer8("select ifnull(MAX(CONVERT(LEFT(no_transaksi,4),signed)),0) from pemasukan_lain where "
                + "tanggal like '%" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(0, 7) + "%' ", "/PL/" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(0, 4), 4, noTransaksi);
    }
    
    private void simpanPemasukan() {
        try {
            if (Sequel.menyimpantf("pemasukan_lain","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Pemasukan",26,new String[]{
                    Nomor.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
                    KdKategori.getText(),pemasukan.getText(),KdPtg.getText(),Keterangan.getText(),Keperluan.getText(),
                    
                    noTransaksi.getText(),Keterangan.getText(),"-","-","-","-",
                    "-","-","-","-","0",
                    "0","0","-","-","-",
                    "0","-","0"
                    }) == true) {
            
//            if (Sequel.menyimpantf("pemasukan_lain", "?,?,?,?,?,?,?", "Pemasukan", 7, new String[]{
//                Nomor.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + "") + " " + Tanggal.getSelectedItem().toString().substring(11, 19),
//                KdKategori.getText(), pemasukan.getText(), KdPtg.getText(), Keterangan.getText(), Keperluan.getText()
//            }) == true) {
                Sequel.queryu("delete from tampjurnal");
                psakun = koneksi.prepareStatement(
                        "select kd_rek,'Akun',kd_rek2,'Kontra Akun' from kategori_pemasukan_lain where kode_kategori=?");
                try {
                    psakun.setString(1, KdKategori.getText());
                    rs = psakun.executeQuery();
                    if (rs.next()) {
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", pemasukan.getText()});
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), pemasukan.getText(), "0"});
                        jur.simpanJurnal(Nomor.getText(),"U", "PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Jurnal : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (psakun != null) {
                        psakun.close();
                    }
                }
                Sequel.menyimpan2("tagihan_sadewa", "'" + Nomor.getText() + "','-','" + Keterangan.getText() + "','-',concat('" + Valid.SetTgl(Tanggal.getSelectedItem() + "")
                        + "',' ',CURTIME()),'Pelunasan','" + total + "','" + pemasukan.getText() + "','Sudah','" + akses.getkode() + "'", "No.Transaksi");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusTransaksiNonNaikKelas() {
        try {
            if (Sequel.queryu2tf("delete from pemasukan_lain where no_masuk=?", 1, new String[]{
                tbResep.getValueAt(tbResep.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.queryu("delete from tampjurnal");
                psakun = koneksi.prepareStatement(
                        "select kd_rek,'Akun',kd_rek2,'Kontra Akun' from kategori_pemasukan_lain where kode_kategori=?");
                try {
                    psakun.setString(1, KdKategori.getText());
                    rs = psakun.executeQuery();
                    if (rs.next()) {
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), pemasukan.getText(), "0"});
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), "0", pemasukan.getText()});
                        jur.simpanJurnal("-","U", "PEMBATALAN PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Jurnal : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (psakun != null) {
                        psakun.close();
                    }
                }
                Valid.hapusTable(tabMode, Nomor, "tagihan_sadewa", "no_nota");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusTransaksiNaikKelas() {
        Sequel.AutoComitFalse();
        try {
            Sequel.queryu2("delete from pemasukan_lain where no_transaksi=? ", 1, new String[]{
                noTransaksi.getText()
            });
            Sequel.queryu("delete from tampjurnal");
            psakun.setString(1, KdKategori.getText());
            rs = psakun.executeQuery();
            if (rs.next()) {
                Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), pemasukan.getText(), "0"});
                Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), "0", pemasukan.getText()});
                jur.simpanJurnal("-", "U", "PEMBATALAN PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        Sequel.AutoComitTrue();
    }
}

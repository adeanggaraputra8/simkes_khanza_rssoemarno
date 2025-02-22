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

package inventory;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.event.DocumentEvent;
import keuangan.Jurnal;
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariObatPenyakit;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;
import kepegawaian.DlgCariPetugas;
import modifikasi.DlgBayarObatUmum;
import modifikasi.DlgPasienAmbilObatKronis;

/**
 *
 * @author perpustakaan
 */
public class DlgPemberianObat extends javax.swing.JDialog {
    private final DefaultTableModel tabModePO,tabmodebayar;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    public  DlgCariObat dlgobtjalan=new DlgCariObat(null,false);
    public  DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    public  DlgCariObat2 dlgobt=new DlgCariObat2(null,false);
    public  DlgCariObat3 dlgobt2=new DlgCariObat3(null,false);
    private DlgCariBangsal bangsal2 = new DlgCariBangsal(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private riwayatobat Trackobat=new riwayatobat();
    public  DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    private DlgCariKonversi carikonversi=new DlgCariKonversi(null,false);
    private DlgPasien pasien=new DlgPasien(null,false);
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private String now=dateFormat.format(date),bangsal="",lokasi="",tgl="",pas="",sql="",status="",statussimpan="";
    private PreparedStatement ps,psrekening,psobat;
    private ResultSet rs,rsrekening,rsobat;
    private double embalase=Sequel.cariIsiAngka("select embalase_per_obat from set_embalase"),ttljual,ttlhpp,jumlahtotal=0;
    private double tuslah=Sequel.cariIsiAngka("select tuslah_per_obat from set_embalase");
    private String aktifkanbatch="no",aktifkanparsial="no",kodedokter="",namadokter="",statusberi="",
            Suspen_Piutang_Obat_Ranap="",Obat_Ranap="",HPP_Obat_Rawat_Inap="",Persediaan_Obat_Rawat_Inap="",
            Suspen_Piutang_Obat_Ralan="",Obat_Ralan="",HPP_Obat_Rawat_Jalan="",Persediaan_Obat_Rawat_Jalan="";
    private final Properties prop = new Properties();
    private Jurnal jur=new Jurnal();
    private DlgCariBangsal depo = new DlgCariBangsal(null, false);
    private int jmlparsial=0;
    private boolean sukses=true;
    
    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgPemberianObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        WindowBayarObat.setSize(1103,444);

        Object[] row={"Tgl.Beri","Jam Beri","No.Rawat","No.R.M.",
                      "Nama Pasien","Kode Obat","Nama Obat/Alkes","Embalase",
                      "Tuslah","Jml","Biaya Obat","Total","Harga Beli","Gudang","No.Batch","No.Faktur","No.Resep","Cara Bayar"};
        tabModePO=new DefaultTableModel(null,row){
            @Override 
            public boolean isCellEditable(int rowIndex, int colIndex){return false;}
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemberianObat.setModel(tabModePO);
        //tampilPO("");

        tbPemberianObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemberianObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 18; i++) {
            TableColumn column = tbPemberianObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(60);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(80);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(40);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setPreferredWidth(90);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setPreferredWidth(55);
            }else if(i==14){
                column.setPreferredWidth(80);
            }else if(i==15){
                column.setPreferredWidth(80);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(80);
            }
        }
        tbPemberianObat.setDefaultRenderer(Object.class, new WarnaTable());
        
         tabmodebayar=new DefaultTableModel(null,new Object[]{
            "No.Resep","No.Rawat","No.R.M.","Nama Pasien","Tanggal Bayar","Status","Jaminan","Petugas Kasir",
            "Lokasi Obat","Status Bayar","Keterangan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}{
                
             };
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                 
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbkembali.setModel(tabmodebayar);
        tbkembali.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbkembali.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbkembali.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(70);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(70);
            }else if(i==5){
                column.setPreferredWidth(70);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(50);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(150);
            }
        }
        tbkembali.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TKdOb.setDocument(new batasInput((byte)15).getKata(TKdOb));
        TJumlah.setDocument(new batasInput((byte)25).getKata(TJumlah));
        TEmbalase.setDocument(new batasInput((byte)15).getKata(TEmbalase));
        TTuslah.setDocument(new batasInput((byte)15).getKata(TTuslah));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        TCariPasien.setDocument(new batasInput((byte)20).getKata(TCariPasien));     
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilPO();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilPO();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilPO();}
            });
        } 
        
            dlgobt.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    tampilPO();
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
        
        dlgobt.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(dlgobt.getTable().getSelectedRow()!= -1){                           
                        TKdOb.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(),2).toString());   
                        TNmOb.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(),3).toString());   
                        TSatuan.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(),4).toString());    
                        TBiayaObat.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(),6).toString());   
                        THBeli.setText(dlgobt.getTable().getValueAt(dlgobt.getTable().getSelectedRow(),12).toString());   
                    } 
                    TKdOb.requestFocus();
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
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                     kd_nip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                     Nmpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                Nmpetugas.requestFocus();
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
        
        dlgobt.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        dlgobt.dispose();
                      
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        dlgobtjalan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(dlgobtjalan.getTable().getSelectedRow()!= -1){                           
                        TKdOb.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(),2).toString());   
                        TNmOb.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(),3).toString());   
                        TSatuan.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(),4).toString());    
                        TBiayaObat.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(),6).toString());   
                        THBeli.setText(dlgobtjalan.getTable().getValueAt(dlgobtjalan.getTable().getSelectedRow(),13).toString());   
                    } 
                    TKdOb.requestFocus();
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
        
        dlgobtjalan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        dlgobtjalan.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        dlgobt2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(dlgobt2.getTable().getSelectedRow()!= -1){                           
                        TKdOb.setText(dlgobt2.getTable().getValueAt(dlgobt2.getTable().getSelectedRow(),1).toString());   
                        TNmOb.setText(dlgobt2.getTable().getValueAt(dlgobt2.getTable().getSelectedRow(),2).toString());     
                    } 
                    TKdOb.requestFocus();
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
        
        dlgobt2.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        dlgobt2.dispose();
                       
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(pasien.getTable().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                    } 
                    if(pasien.getTable2().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),1).toString());
                    }
                    if(pasien.getTable3().getSelectedRow()!= -1){                   
                        TCariPasien.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),1).toString());
                    }
                    TCariPasien.requestFocus();
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPemberianObat")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        depo.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
                if (depo.getTable().getSelectedRow() != -1) {
                    lokasi=depo.getTable().getValueAt(depo.getTable().getSelectedRow(),0).toString();
                    tampilPO2();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {
                depo.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        bangsal2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (bangsal2.getTable().getSelectedRow() != -1) {
                        KdGudang.setText(bangsal2.getTable().getValueAt(bangsal2.getTable().getSelectedRow(), 0).toString());
                        NmGudang.setText(bangsal2.getTable().getValueAt(bangsal2.getTable().getSelectedRow(), 1).toString());
                    }
                    KdGudang.requestFocus();
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

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemberianObat")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        kdpoli.requestFocus();
                    }
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
        
        ChkInput.setSelected(false);
        isForm(); 
        jam();   
        
             try {
            psrekening=koneksi.prepareStatement("select * from set_akun_ralan");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Obat_Ralan=rsrekening.getString("Suspen_Piutang_Obat_Ralan");
                    Obat_Ralan=rsrekening.getString("Obat_Ralan");
                    HPP_Obat_Rawat_Jalan=rsrekening.getString("HPP_Obat_Rawat_Jalan");
                    Persediaan_Obat_Rawat_Jalan=rsrekening.getString("Persediaan_Obat_Rawat_Jalan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }
            
            psrekening=koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Obat_Ranap=rsrekening.getString("Suspen_Piutang_Obat_Ranap");
                    Obat_Ranap=rsrekening.getString("Obat_Ranap");
                    HPP_Obat_Rawat_Inap=rsrekening.getString("HPP_Obat_Rawat_Inap");
                    Persediaan_Obat_Rawat_Inap=rsrekening.getString("Persediaan_Obat_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifkanparsial=prop.getProperty("AKTIFKANBILLINGPARSIAL");
            aktifkanbatch = prop.getProperty("AKTIFKANBATCHOBAT");
        } catch (Exception ex) {            
            aktifkanparsial="no";
            aktifkanbatch = "no";
        }
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

        Popup2 = new javax.swing.JPopupMenu();
        ppResepObat = new javax.swing.JMenuItem();
        ppNoRawat = new javax.swing.JMenuItem();
        ppLokasi = new javax.swing.JMenuItem();
        MnBayarObat = new javax.swing.JMenuItem();
        ppResiObatUmum = new javax.swing.JMenuItem();
        MnKirimWa = new javax.swing.JMenuItem();
        THBeli = new widget.TextBox();
        Popup1 = new javax.swing.JPopupMenu();
        jMenuLapJalanAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienPerCB = new javax.swing.JMenuItem();
        jMnRekapDetailResepPasienPerCB = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienSemuaCB = new javax.swing.JMenuItem();
        jMnRekapDetailResepPasienSemuaCB = new javax.swing.JMenuItem();
        jMnRekapDetailResepPasienPerCB1 = new javax.swing.JMenuItem();
        jMenuLapInapAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara3 = new javax.swing.JMenuItem();
        jMenuLapJalanPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan1 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara1 = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienPerCB1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalResepPasienPerCB1 = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienSemuaCB1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalResepPasienSemuaCB1 = new javax.swing.JMenuItem();
        jMenuLapInapPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap1 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara1 = new javax.swing.JMenuItem();
        jMenuLapAllRawatPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan2 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara2 = new javax.swing.JMenuItem();
        jMenuLapSemuaAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap2 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara2 = new javax.swing.JMenuItem();
        jMenuLapResep = new javax.swing.JMenu();
        jMnRekapResepRalan = new javax.swing.JMenuItem();
        jMnRekapResepRanap = new javax.swing.JMenuItem();
        jMenuLapResep1 = new javax.swing.JMenu();
        JMenuRalanAntibiotik = new javax.swing.JMenuItem();
        jMenuRanapAntibiotik = new javax.swing.JMenuItem();
        jMenuRalanGenerik = new javax.swing.JMenuItem();
        JmenuRanapGenerik = new javax.swing.JMenuItem();
        jMenuAntibiotikAll = new javax.swing.JMenuItem();
        jMenuLapObatUmum = new javax.swing.JMenu();
        jLapObatUmum1 = new javax.swing.JMenuItem();
        jLapObatUmumdetail = new javax.swing.JMenuItem();
        jMenuLapDepoIGD = new javax.swing.JMenu();
        jMenuLapStokOpnameDepo = new javax.swing.JMenuItem();
        jLapDepo = new javax.swing.JMenuItem();
        jMenuLapObatPerDokter = new javax.swing.JMenuItem();
        No_rw2 = new widget.TextBox();
        kdpj = new widget.TextBox();
        status1 = new widget.TextBox();
        lokasi1 = new widget.TextBox();
        WindowBayarObat = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        jLabel39 = new widget.Label();
        jLabel41 = new widget.Label();
        Nmpetugas = new widget.TextBox();
        DTPBayar = new widget.Tanggal();
        btncaripetugas = new widget.Button();
        kd_nip = new widget.TextBox();
        scrollPane6 = new widget.ScrollPane();
        tbkembali = new widget.Table();
        panelGlass3 = new widget.panelGlass();
        BtnSimpan6 = new widget.Button();
        BtnGanti = new widget.Button();
        BtnCloseIn6 = new widget.Button();
        BtnPrint1 = new widget.Button();
        DTPtanggalkembali1 = new widget.Tanggal();
        jLabel44 = new widget.Label();
        DTPtanggalkembali2 = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        cari1 = new widget.TextBox();
        jLabel40 = new widget.Label();
        Lcount1 = new widget.Label();
        NoResep1 = new widget.TextBox();
        jLabel45 = new widget.Label();
        Keterangan = new widget.TextBox();
        jLabel46 = new widget.Label();
        Tagihan1 = new widget.TextBox();
        jLabel42 = new widget.Label();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnNotaPx = new javax.swing.JMenuItem();
        Mnrekaplaporan = new javax.swing.JMenuItem();
        Tanggal = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPemberianObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
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
        ChkInput = new widget.CekBox();
        TabInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel7 = new widget.Label();
        TJumlah = new widget.TextBox();
        jLabel9 = new widget.Label();
        DTPBeri = new widget.Tanggal();
        jLabel13 = new widget.Label();
        TKdOb = new widget.TextBox();
        btnObat1 = new widget.Button();
        TNmOb = new widget.TextBox();
        TEmbalase = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        TBiayaObat = new widget.TextBox();
        jLabel17 = new widget.Label();
        TSatuan = new widget.TextBox();
        TTotal = new widget.TextBox();
        jLabel18 = new widget.Label();
        ChkJln = new widget.CekBox();
        btnKonversi = new widget.Button();
        BtnObat2 = new widget.Button();
        BtnObat3 = new widget.Button();
        jLabel20 = new widget.Label();
        TTuslah = new widget.TextBox();
        Ket1 = new widget.ComboBox();
        jLabel24 = new widget.Label();
        NoResep = new widget.TextBox();
        jLabel25 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        jLabel23 = new widget.Label();
        kdpnj = new widget.TextBox();
        jLabel21 = new widget.Label();
        KdGudang = new widget.TextBox();
        label19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        NmGudang = new widget.TextBox();
        nmpnj = new widget.TextBox();
        jLabel22 = new widget.Label();
        BtnRefresLap = new widget.Button();
        btnPenjab = new widget.Button();
        btnBarang1 = new widget.Button();
        BtnUnit = new widget.Button();

        Popup2.setName("Popup2"); // NOI18N

        ppResepObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResepObat.setForeground(new java.awt.Color(130, 100, 100));
        ppResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResepObat.setText("Buat Nomor Resep Obat");
        ppResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResepObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResepObat.setIconTextGap(8);
        ppResepObat.setName("ppResepObat"); // NOI18N
        ppResepObat.setPreferredSize(new java.awt.Dimension(200, 25));
        ppResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResepObatActionPerformed(evt);
            }
        });
        Popup2.add(ppResepObat);

        ppNoRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppNoRawat.setForeground(new java.awt.Color(130, 100, 100));
        ppNoRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppNoRawat.setText("Rekap Per No.Rawat");
        ppNoRawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppNoRawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppNoRawat.setIconTextGap(8);
        ppNoRawat.setName("ppNoRawat"); // NOI18N
        ppNoRawat.setPreferredSize(new java.awt.Dimension(200, 25));
        ppNoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppNoRawatActionPerformed(evt);
            }
        });
        Popup2.add(ppNoRawat);

        ppLokasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLokasi.setForeground(new java.awt.Color(130, 100, 100));
        ppLokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLokasi.setText("Tampilkan Per Asal Stok");
        ppLokasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppLokasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppLokasi.setIconTextGap(8);
        ppLokasi.setName("ppLokasi"); // NOI18N
        ppLokasi.setPreferredSize(new java.awt.Dimension(180, 25));
        ppLokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLokasiBtnPrintActionPerformed(evt);
            }
        });
        Popup2.add(ppLokasi);

        MnBayarObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBayarObat.setForeground(new java.awt.Color(70, 70, 70));
        MnBayarObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBayarObat.setText("Bayar Obat Umum");
        MnBayarObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBayarObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBayarObat.setIconTextGap(8);
        MnBayarObat.setName("MnBayarObat"); // NOI18N
        MnBayarObat.setPreferredSize(new java.awt.Dimension(250, 25));
        MnBayarObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBayarObatActionPerformed(evt);
            }
        });
        Popup2.add(MnBayarObat);

        ppResiObatUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppResiObatUmum.setForeground(new java.awt.Color(130, 100, 100));
        ppResiObatUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppResiObatUmum.setText("Resi Obat");
        ppResiObatUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppResiObatUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppResiObatUmum.setIconTextGap(8);
        ppResiObatUmum.setName("ppResiObatUmum"); // NOI18N
        ppResiObatUmum.setPreferredSize(new java.awt.Dimension(200, 25));
        ppResiObatUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppResiObatUmumActionPerformed(evt);
            }
        });
        Popup2.add(ppResiObatUmum);

        MnKirimWa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimWa.setForeground(new java.awt.Color(70, 70, 70));
        MnKirimWa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimWa.setText("Kirim Wa Ke Pasien");
        MnKirimWa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimWa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimWa.setIconTextGap(5);
        MnKirimWa.setName("MnKirimWa"); // NOI18N
        MnKirimWa.setPreferredSize(new java.awt.Dimension(220, 26));
        MnKirimWa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimWaActionPerformed(evt);
            }
        });
        Popup2.add(MnKirimWa);

        THBeli.setText("0");
        THBeli.setHighlighter(null);
        THBeli.setName("THBeli"); // NOI18N

        Popup1.setBackground(new java.awt.Color(254, 180, 123));
        Popup1.setName("Popup1"); // NOI18N

        jMenuLapJalanAllDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapJalanAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapJalanAllDepo.setText("1) Lap. Rawat Jalan Semua DEPO");
        jMenuLapJalanAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapJalanAllDepo.setName("jMenuLapJalanAllDepo"); // NOI18N
        jMenuLapJalanAllDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienJalan.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan.setName("jMnRekapTotalPerPasienJalan"); // NOI18N
        jMnRekapTotalPerPasienJalan.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalPerPasienJalan);

        jMnRekapDetailTotalPerPasienJalan.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan.setName("jMnRekapDetailTotalPerPasienJalan"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailTotalPerPasienJalan);

        jMnRekapTotalPerPasienJalanCara.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalanCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara.setName("jMnRekapTotalPerPasienJalanCara"); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalanCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCaraActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalPerPasienJalanCara);

        jMnRekapDetailTotalPerPasienJalanCara.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalanCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara.setName("jMnRekapDetailTotalPerPasienJalanCara"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalanCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCaraActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailTotalPerPasienJalanCara);

        jMnRekapTotalResepPasienPerCB.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalResepPasienPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienPerCB.setText("5) Rekap Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapTotalResepPasienPerCB.setToolTipText("");
        jMnRekapTotalResepPasienPerCB.setName("jMnRekapTotalResepPasienPerCB"); // NOI18N
        jMnRekapTotalResepPasienPerCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienPerCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalResepPasienPerCB);

        jMnRekapDetailResepPasienPerCB.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailResepPasienPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailResepPasienPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailResepPasienPerCB.setText("6) Rekap Detail Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapDetailResepPasienPerCB.setName("jMnRekapDetailResepPasienPerCB"); // NOI18N
        jMnRekapDetailResepPasienPerCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailResepPasienPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailResepPasienPerCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailResepPasienPerCB);

        jMnRekapTotalResepPasienSemuaCB.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalResepPasienSemuaCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setText("7) Rekap Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapTotalResepPasienSemuaCB.setToolTipText("");
        jMnRekapTotalResepPasienSemuaCB.setName("jMnRekapTotalResepPasienSemuaCB"); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienSemuaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienSemuaCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalResepPasienSemuaCB);

        jMnRekapDetailResepPasienSemuaCB.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailResepPasienSemuaCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setText("8) Rekap Detail Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapDetailResepPasienSemuaCB.setName("jMnRekapDetailResepPasienSemuaCB"); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailResepPasienSemuaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailResepPasienSemuaCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailResepPasienSemuaCB);

        jMnRekapDetailResepPasienPerCB1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailResepPasienPerCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailResepPasienPerCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailResepPasienPerCB1.setText("9) Rekap Detail Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapDetailResepPasienPerCB1.setName("jMnRekapDetailResepPasienPerCB1"); // NOI18N
        jMnRekapDetailResepPasienPerCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailResepPasienPerCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailResepPasienPerCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailResepPasienPerCB1);

        Popup1.add(jMenuLapJalanAllDepo);

        jMenuLapInapAllDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapInapAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapInapAllDepo.setText("2) Lap. Rawat Inap Semua DEPO");
        jMenuLapInapAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapInapAllDepo.setName("jMenuLapInapAllDepo"); // NOI18N
        jMenuLapInapAllDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienInap.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap.setName("jMnRekapTotalPerPasienInap"); // NOI18N
        jMnRekapTotalPerPasienInap.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapTotalPerPasienInap);

        jMnRekapDetailTotalPerPasienInap.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap.setName("jMnRekapDetailTotalPerPasienInap"); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapDetailTotalPerPasienInap);

        jMnRekapTotalPerPasienInapCara.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInapCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara.setName("jMnRekapTotalPerPasienInapCara"); // NOI18N
        jMnRekapTotalPerPasienInapCara.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCaraActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapTotalPerPasienInapCara);

        jMnRekapDetailTotalPerPasienInapCara.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInapCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara.setName("jMnRekapDetailTotalPerPasienInapCara"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCaraActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapDetailTotalPerPasienInapCara);

        jMnRekapDetailTotalPerPasienInapCara3.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInapCara3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara3.setText("5) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara3.setName("jMnRekapDetailTotalPerPasienInapCara3"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara3.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCara3ActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapDetailTotalPerPasienInapCara3);

        Popup1.add(jMenuLapInapAllDepo);

        jMenuLapJalanPerDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapJalanPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapJalanPerDepo.setText("3) Lap. Rawat Jalan Per DEPO");
        jMenuLapJalanPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapJalanPerDepo.setName("jMenuLapJalanPerDepo"); // NOI18N
        jMenuLapJalanPerDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienJalan1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan1.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan1.setName("jMnRekapTotalPerPasienJalan1"); // NOI18N
        jMnRekapTotalPerPasienJalan1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalan1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalPerPasienJalan1);

        jMnRekapDetailTotalPerPasienJalan1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan1.setName("jMnRekapDetailTotalPerPasienJalan1"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalan1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalPerPasienJalan1);

        jMnRekapTotalPerPasienJalanCara1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalanCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara1.setName("jMnRekapTotalPerPasienJalanCara1"); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalanCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCara1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalPerPasienJalanCara1);

        jMnRekapDetailTotalPerPasienJalanCara1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalanCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara1.setName("jMnRekapDetailTotalPerPasienJalanCara1"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalanCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalPerPasienJalanCara1);

        jMnRekapTotalResepPasienPerCB1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalResepPasienPerCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setText("5) Rekap Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapTotalResepPasienPerCB1.setName("jMnRekapTotalResepPasienPerCB1"); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienPerCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienPerCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalResepPasienPerCB1);

        jMnRekapDetailTotalResepPasienPerCB1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalResepPasienPerCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setText("6) Rekap Detail Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapDetailTotalResepPasienPerCB1.setName("jMnRekapDetailTotalResepPasienPerCB1"); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalResepPasienPerCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalResepPasienPerCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalResepPasienPerCB1);

        jMnRekapTotalResepPasienSemuaCB1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalResepPasienSemuaCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setText("7) Rekap Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapTotalResepPasienSemuaCB1.setName("jMnRekapTotalResepPasienSemuaCB1"); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienSemuaCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienSemuaCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalResepPasienSemuaCB1);

        jMnRekapDetailTotalResepPasienSemuaCB1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalResepPasienSemuaCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setText("8) Rekap Detail Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapDetailTotalResepPasienSemuaCB1.setName("jMnRekapDetailTotalResepPasienSemuaCB1"); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalResepPasienSemuaCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalResepPasienSemuaCB1);

        Popup1.add(jMenuLapJalanPerDepo);

        jMenuLapInapPerDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapInapPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapInapPerDepo.setText("4) Lap. Rawat Inap Per DEPO");
        jMenuLapInapPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapInapPerDepo.setName("jMenuLapInapPerDepo"); // NOI18N
        jMenuLapInapPerDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienInap1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap1.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap1.setName("jMnRekapTotalPerPasienInap1"); // NOI18N
        jMnRekapTotalPerPasienInap1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInap1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapTotalPerPasienInap1);

        jMnRekapDetailTotalPerPasienInap1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap1.setName("jMnRekapDetailTotalPerPasienInap1"); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInap1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapDetailTotalPerPasienInap1);

        jMnRekapTotalPerPasienInapCara1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInapCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara1.setName("jMnRekapTotalPerPasienInapCara1"); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCara1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapTotalPerPasienInapCara1);

        jMnRekapDetailTotalPerPasienInapCara1.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInapCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara1.setName("jMnRekapDetailTotalPerPasienInapCara1"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCara1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapDetailTotalPerPasienInapCara1);

        Popup1.add(jMenuLapInapPerDepo);

        jMenuLapAllRawatPerDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapAllRawatPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapAllRawatPerDepo.setText("5) Lap. Semua Rawat Per DEPO");
        jMenuLapAllRawatPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapAllRawatPerDepo.setName("jMenuLapAllRawatPerDepo"); // NOI18N
        jMenuLapAllRawatPerDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienJalan2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan2.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan2.setName("jMnRekapTotalPerPasienJalan2"); // NOI18N
        jMnRekapTotalPerPasienJalan2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienJalan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalan2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapTotalPerPasienJalan2);

        jMnRekapDetailTotalPerPasienJalan2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan2.setName("jMnRekapDetailTotalPerPasienJalan2"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienJalan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalan2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapDetailTotalPerPasienJalan2);

        jMnRekapTotalPerPasienJalanCara2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienJalanCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara2.setName("jMnRekapTotalPerPasienJalanCara2"); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienJalanCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCara2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapTotalPerPasienJalanCara2);

        jMnRekapDetailTotalPerPasienJalanCara2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienJalanCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara2.setName("jMnRekapDetailTotalPerPasienJalanCara2"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienJalanCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapDetailTotalPerPasienJalanCara2);

        Popup1.add(jMenuLapAllRawatPerDepo);

        jMenuLapSemuaAllDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapSemuaAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapSemuaAllDepo.setText("6) Lap. Semua Rawat Semua DEPO");
        jMenuLapSemuaAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapSemuaAllDepo.setName("jMenuLapSemuaAllDepo"); // NOI18N
        jMenuLapSemuaAllDepo.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapTotalPerPasienInap2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap2.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap2.setName("jMnRekapTotalPerPasienInap2"); // NOI18N
        jMnRekapTotalPerPasienInap2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInap2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapTotalPerPasienInap2);

        jMnRekapDetailTotalPerPasienInap2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap2.setName("jMnRekapDetailTotalPerPasienInap2"); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInap2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapDetailTotalPerPasienInap2);

        jMnRekapTotalPerPasienInapCara2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapTotalPerPasienInapCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara2.setName("jMnRekapTotalPerPasienInapCara2"); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCara2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapTotalPerPasienInapCara2);

        jMnRekapDetailTotalPerPasienInapCara2.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapDetailTotalPerPasienInapCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara2.setToolTipText("");
        jMnRekapDetailTotalPerPasienInapCara2.setName("jMnRekapDetailTotalPerPasienInapCara2"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCara2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapDetailTotalPerPasienInapCara2);

        Popup1.add(jMenuLapSemuaAllDepo);

        jMenuLapResep.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapResep.setText("7) Lap. Penghitungan Obat PerResep");
        jMenuLapResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapResep.setName("jMenuLapResep"); // NOI18N
        jMenuLapResep.setPreferredSize(new java.awt.Dimension(260, 25));

        jMnRekapResepRalan.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapResepRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapResepRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapResepRalan.setText("1) Rekap Lembar & Jumlah Resep Ralan");
        jMnRekapResepRalan.setName("jMnRekapResepRalan"); // NOI18N
        jMnRekapResepRalan.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapResepRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapResepRalanActionPerformed(evt);
            }
        });
        jMenuLapResep.add(jMnRekapResepRalan);

        jMnRekapResepRanap.setBackground(new java.awt.Color(102, 255, 102));
        jMnRekapResepRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapResepRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapResepRanap.setText("2) Rekap Lembar & Jumlah Resep Ranap");
        jMnRekapResepRanap.setName("jMnRekapResepRanap"); // NOI18N
        jMnRekapResepRanap.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapResepRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapResepRanapActionPerformed(evt);
            }
        });
        jMenuLapResep.add(jMnRekapResepRanap);

        Popup1.add(jMenuLapResep);

        jMenuLapResep1.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapResep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapResep1.setText("8) Laporan Antibiotik & Generik");
        jMenuLapResep1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapResep1.setName("jMenuLapResep1"); // NOI18N

        JMenuRalanAntibiotik.setBackground(new java.awt.Color(102, 255, 102));
        JMenuRalanAntibiotik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        JMenuRalanAntibiotik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        JMenuRalanAntibiotik.setText("1) Laporan Antibiotik Ralan");
        JMenuRalanAntibiotik.setName("JMenuRalanAntibiotik"); // NOI18N
        JMenuRalanAntibiotik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuRalanAntibiotikActionPerformed(evt);
            }
        });
        jMenuLapResep1.add(JMenuRalanAntibiotik);

        jMenuRanapAntibiotik.setBackground(new java.awt.Color(102, 255, 102));
        jMenuRanapAntibiotik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuRanapAntibiotik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuRanapAntibiotik.setText("2) Laporan Antibiotik  Ranap");
        jMenuRanapAntibiotik.setName("jMenuRanapAntibiotik"); // NOI18N
        jMenuRanapAntibiotik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRanapAntibiotikActionPerformed(evt);
            }
        });
        jMenuLapResep1.add(jMenuRanapAntibiotik);

        jMenuRalanGenerik.setBackground(new java.awt.Color(102, 255, 102));
        jMenuRalanGenerik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuRalanGenerik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuRalanGenerik.setText("3) Laporan Generik Ralan");
        jMenuRalanGenerik.setName("jMenuRalanGenerik"); // NOI18N
        jMenuRalanGenerik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRalanGenerikActionPerformed(evt);
            }
        });
        jMenuLapResep1.add(jMenuRalanGenerik);

        JmenuRanapGenerik.setBackground(new java.awt.Color(102, 255, 102));
        JmenuRanapGenerik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        JmenuRanapGenerik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        JmenuRanapGenerik.setText("4) Laporan Generik Ranap");
        JmenuRanapGenerik.setName("JmenuRanapGenerik"); // NOI18N
        JmenuRanapGenerik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuRanapGenerikActionPerformed(evt);
            }
        });
        jMenuLapResep1.add(JmenuRanapGenerik);

        jMenuAntibiotikAll.setBackground(new java.awt.Color(102, 255, 102));
        jMenuAntibiotikAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuAntibiotikAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuAntibiotikAll.setText("5) Laporan Antibiotik Ranap & Ralan");
        jMenuAntibiotikAll.setName("jMenuAntibiotikAll"); // NOI18N
        jMenuAntibiotikAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAntibiotikAllActionPerformed(evt);
            }
        });
        jMenuLapResep1.add(jMenuAntibiotikAll);

        Popup1.add(jMenuLapResep1);

        jMenuLapObatUmum.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapObatUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapObatUmum.setText("9) Laporan Obat Umum");
        jMenuLapObatUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapObatUmum.setName("jMenuLapObatUmum"); // NOI18N

        jLapObatUmum1.setBackground(new java.awt.Color(102, 255, 102));
        jLapObatUmum1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLapObatUmum1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jLapObatUmum1.setText("Laporan Setoran Kasir Umum");
        jLapObatUmum1.setName("jLapObatUmum1"); // NOI18N
        jLapObatUmum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLapObatUmum1ActionPerformed(evt);
            }
        });
        jMenuLapObatUmum.add(jLapObatUmum1);

        jLapObatUmumdetail.setBackground(new java.awt.Color(102, 255, 102));
        jLapObatUmumdetail.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLapObatUmumdetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jLapObatUmumdetail.setText("Detail Laporan Obat Umum");
        jLapObatUmumdetail.setName("jLapObatUmumdetail"); // NOI18N
        jLapObatUmumdetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLapObatUmumdetailActionPerformed(evt);
            }
        });
        jMenuLapObatUmum.add(jLapObatUmumdetail);

        Popup1.add(jMenuLapObatUmum);

        jMenuLapDepoIGD.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapDepoIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapDepoIGD.setText("11) Lap. Depo IGD");
        jMenuLapDepoIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapDepoIGD.setName("jMenuLapDepoIGD"); // NOI18N

        jMenuLapStokOpnameDepo.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapStokOpnameDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapStokOpnameDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuLapStokOpnameDepo.setText("Lap. Stok Opname Depo");
        jMenuLapStokOpnameDepo.setName("jMenuLapStokOpnameDepo"); // NOI18N
        jMenuLapStokOpnameDepo.setPreferredSize(new java.awt.Dimension(260, 25));
        jMenuLapStokOpnameDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLapStokOpnameDepoActionPerformed(evt);
            }
        });
        jMenuLapDepoIGD.add(jMenuLapStokOpnameDepo);

        jLapDepo.setBackground(new java.awt.Color(102, 255, 102));
        jLapDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLapDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jLapDepo.setText("Data Pasien & Total Obat");
        jLapDepo.setName("jLapDepo"); // NOI18N
        jLapDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLapDepoActionPerformed(evt);
            }
        });
        jMenuLapDepoIGD.add(jLapDepo);

        Popup1.add(jMenuLapDepoIGD);

        jMenuLapObatPerDokter.setBackground(new java.awt.Color(102, 255, 102));
        jMenuLapObatPerDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapObatPerDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuLapObatPerDokter.setText("10) Lap. Pemakaian Obat Per Dokter");
        jMenuLapObatPerDokter.setName("jMenuLapObatPerDokter"); // NOI18N
        jMenuLapObatPerDokter.setPreferredSize(new java.awt.Dimension(260, 25));
        jMenuLapObatPerDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLapObatPerDokterActionPerformed(evt);
            }
        });
        Popup1.add(jMenuLapObatPerDokter);

        No_rw2.setEditable(false);
        No_rw2.setForeground(new java.awt.Color(255, 255, 255));
        No_rw2.setHighlighter(null);
        No_rw2.setName("No_rw2"); // NOI18N
        No_rw2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                No_rw2KeyPressed(evt);
            }
        });

        kdpj.setEditable(false);
        kdpj.setForeground(new java.awt.Color(255, 255, 255));
        kdpj.setHighlighter(null);
        kdpj.setName("kdpj"); // NOI18N
        kdpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpjKeyPressed(evt);
            }
        });

        status1.setEditable(false);
        status1.setForeground(new java.awt.Color(255, 255, 255));
        status1.setHighlighter(null);
        status1.setName("status1"); // NOI18N
        status1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                status1KeyPressed(evt);
            }
        });

        lokasi1.setEditable(false);
        lokasi1.setForeground(new java.awt.Color(255, 255, 255));
        lokasi1.setHighlighter(null);
        lokasi1.setName("lokasi1"); // NOI18N
        lokasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lokasi1KeyPressed(evt);
            }
        });

        WindowBayarObat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowBayarObat.setName("WindowBayarObat"); // NOI18N
        WindowBayarObat.setUndecorated(true);
        WindowBayarObat.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Bayar Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 15), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(1250, 437));
        internalFrame8.setLayout(null);

        jLabel39.setText("Tagihan :  Rp");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame8.add(jLabel39);
        jLabel39.setBounds(470, 50, 80, 23);

        jLabel41.setText("Tanggal Bayar :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame8.add(jLabel41);
        jLabel41.setBounds(260, 50, 90, 23);

        Nmpetugas.setEditable(false);
        Nmpetugas.setName("Nmpetugas"); // NOI18N
        Nmpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmpetugasActionPerformed(evt);
            }
        });
        internalFrame8.add(Nmpetugas);
        Nmpetugas.setBounds(200, 80, 180, 24);

        DTPBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPBayar.setDisplayFormat("dd-MM-yyyy");
        DTPBayar.setName("DTPBayar"); // NOI18N
        DTPBayar.setOpaque(false);
        DTPBayar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPBayarItemStateChanged(evt);
            }
        });
        DTPBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPBayarKeyPressed(evt);
            }
        });
        internalFrame8.add(DTPBayar);
        DTPBayar.setBounds(360, 50, 110, 24);

        btncaripetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaripetugas.setMnemonic('3');
        btncaripetugas.setToolTipText("Alt+3");
        btncaripetugas.setName("btncaripetugas"); // NOI18N
        btncaripetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btncaripetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugasActionPerformed(evt);
            }
        });
        btncaripetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncaripetugasKeyPressed(evt);
            }
        });
        internalFrame8.add(btncaripetugas);
        btncaripetugas.setBounds(380, 80, 28, 23);

        kd_nip.setEditable(false);
        kd_nip.setName("kd_nip"); // NOI18N
        kd_nip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_nipActionPerformed(evt);
            }
        });
        internalFrame8.add(kd_nip);
        kd_nip.setBounds(150, 80, 50, 24);

        scrollPane6.setBackground(new java.awt.Color(153, 153, 153));
        scrollPane6.setName("scrollPane6"); // NOI18N

        tbkembali.setAutoCreateRowSorter(true);
        tbkembali.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbkembali.setComponentPopupMenu(jPopupMenu2);
        tbkembali.setName("tbkembali"); // NOI18N
        tbkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkembaliMouseClicked(evt);
            }
        });
        tbkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkembaliKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(tbkembali);

        internalFrame8.add(scrollPane6);
        scrollPane6.setBounds(10, 140, 1040, 220);

        panelGlass3.setName("panelGlass3"); // NOI18N

        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan Pembayaran");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        panelGlass3.add(BtnSimpan6);

        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('U');
        BtnGanti.setText("Pembayaran Batal");
        BtnGanti.setToolTipText("Alt+U");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        panelGlass3.add(BtnGanti);

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
        panelGlass3.add(BtnCloseIn6);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelGlass3.add(BtnPrint1);

        DTPtanggalkembali1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPtanggalkembali1.setDisplayFormat("dd-MM-yyyy");
        DTPtanggalkembali1.setName("DTPtanggalkembali1"); // NOI18N
        DTPtanggalkembali1.setOpaque(false);
        DTPtanggalkembali1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPtanggalkembali1ItemStateChanged(evt);
            }
        });
        DTPtanggalkembali1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPtanggalkembali1KeyPressed(evt);
            }
        });
        panelGlass3.add(DTPtanggalkembali1);

        jLabel44.setText("s . d");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass3.add(jLabel44);

        DTPtanggalkembali2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPtanggalkembali2.setDisplayFormat("dd-MM-yyyy");
        DTPtanggalkembali2.setName("DTPtanggalkembali2"); // NOI18N
        DTPtanggalkembali2.setOpaque(false);
        DTPtanggalkembali2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPtanggalkembali2ItemStateChanged(evt);
            }
        });
        DTPtanggalkembali2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPtanggalkembali2KeyPressed(evt);
            }
        });
        panelGlass3.add(DTPtanggalkembali2);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('4');
        BtnCari1.setText("Cari Data");
        BtnCari1.setToolTipText("Alt+4");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(99, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass3.add(BtnCari1);

        cari1.setName("cari1"); // NOI18N
        cari1.setPreferredSize(new java.awt.Dimension(120, 25));
        cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari1ActionPerformed(evt);
            }
        });
        cari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari1KeyPressed(evt);
            }
        });
        panelGlass3.add(cari1);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Record : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass3.add(jLabel40);

        Lcount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lcount1.setName("Lcount1"); // NOI18N
        panelGlass3.add(Lcount1);

        internalFrame8.add(panelGlass3);
        panelGlass3.setBounds(10, 360, 1060, 70);

        NoResep1.setEditable(false);
        NoResep1.setName("NoResep1"); // NOI18N
        NoResep1.setPreferredSize(new java.awt.Dimension(150, 24));
        NoResep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoResep1ActionPerformed(evt);
            }
        });
        internalFrame8.add(NoResep1);
        NoResep1.setBounds(150, 50, 100, 24);

        jLabel45.setText("Keterangan Saat Panjar : ");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame8.add(jLabel45);
        jLabel45.setBounds(0, 110, 140, 23);

        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeteranganActionPerformed(evt);
            }
        });
        internalFrame8.add(Keterangan);
        Keterangan.setBounds(150, 110, 390, 24);

        jLabel46.setText("Petugas Kasir : ");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame8.add(jLabel46);
        jLabel46.setBounds(40, 80, 90, 23);

        Tagihan1.setEditable(false);
        Tagihan1.setBackground(new java.awt.Color(102, 102, 255));
        Tagihan1.setForeground(new java.awt.Color(0, 0, 0));
        Tagihan1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Tagihan1.setName("Tagihan1"); // NOI18N
        Tagihan1.setPreferredSize(new java.awt.Dimension(150, 24));
        Tagihan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tagihan1ActionPerformed(evt);
            }
        });
        internalFrame8.add(Tagihan1);
        Tagihan1.setBounds(560, 50, 130, 24);

        jLabel42.setText("No.Resep :  ");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame8.add(jLabel42);
        jLabel42.setBounds(50, 50, 80, 23);

        WindowBayarObat.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnNotaPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNotaPx.setForeground(new java.awt.Color(70, 70, 70));
        MnNotaPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNotaPx.setText("Cetak Nota");
        MnNotaPx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNotaPx.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNotaPx.setIconTextGap(5);
        MnNotaPx.setName("MnNotaPx"); // NOI18N
        MnNotaPx.setPreferredSize(new java.awt.Dimension(250, 25));
        MnNotaPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNotaPxActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnNotaPx);

        Mnrekaplaporan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnrekaplaporan.setForeground(new java.awt.Color(70, 70, 70));
        Mnrekaplaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnrekaplaporan.setText("Rekapan Setoran");
        Mnrekaplaporan.setToolTipText("");
        Mnrekaplaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnrekaplaporan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnrekaplaporan.setIconTextGap(5);
        Mnrekaplaporan.setName("Mnrekaplaporan"); // NOI18N
        Mnrekaplaporan.setPreferredSize(new java.awt.Dimension(250, 25));
        Mnrekaplaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnrekaplaporanActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Mnrekaplaporan);

        Tanggal.setEditable(false);
        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.setPreferredSize(new java.awt.Dimension(100, 23));
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Detail Pemberian Obat/Barang/Alkes/Perlengkapan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(130, 100, 100))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup2);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPemberianObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemberianObat.setComponentPopupMenu(Popup2);
        tbPemberianObat.setName("tbPemberianObat"); // NOI18N
        tbPemberianObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianObatMouseClicked(evt);
            }
        });
        tbPemberianObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPemberianObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass8.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(45, 23));
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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel14.setText("Tgl.Beri :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass9.add(jLabel14);

        DTPCari1.setEditable(false);
        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari1);

        jLabel19.setText("s.d");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        panelGlass9.add(jLabel19);

        DTPCari2.setEditable(false);
        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setText("No.RM :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
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

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        TabInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabInput.setName("TabInput"); // NOI18N

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(865, 137));
        panelisi1.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelisi1.add(jLabel3);
        jLabel3.setBounds(-2, 12, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        panelisi1.add(TNoRw);
        TNoRw.setBounds(81, 12, 175, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        panelisi1.add(TNoRM);
        TNoRM.setBounds(258, 12, 143, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        panelisi1.add(TPasien);
        TPasien.setBounds(403, 12, 425, 23);

        jLabel7.setText("Tgl.Beri :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelisi1.add(jLabel7);
        jLabel7.setBounds(-2, 42, 80, 23);

        TJumlah.setText("0");
        TJumlah.setHighlighter(null);
        TJumlah.setName("TJumlah"); // NOI18N
        TJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJumlahKeyPressed(evt);
            }
        });
        panelisi1.add(TJumlah);
        TJumlah.setBounds(81, 102, 90, 23);

        jLabel9.setText("Jumlah :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(-2, 102, 80, 23);

        DTPBeri.setEditable(false);
        DTPBeri.setForeground(new java.awt.Color(50, 70, 50));
        DTPBeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-04-2024" }));
        DTPBeri.setDisplayFormat("dd-MM-yyyy");
        DTPBeri.setName("DTPBeri"); // NOI18N
        DTPBeri.setOpaque(false);
        DTPBeri.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPBeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPBeriKeyPressed(evt);
            }
        });
        panelisi1.add(DTPBeri);
        DTPBeri.setBounds(81, 42, 100, 23);

        jLabel13.setText("Obt/Alkes :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(-2, 72, 80, 23);

        TKdOb.setName("TKdOb"); // NOI18N
        TKdOb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdObKeyPressed(evt);
            }
        });
        panelisi1.add(TKdOb);
        TKdOb.setBounds(81, 72, 90, 23);

        btnObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnObat1.setMnemonic('4');
        btnObat1.setToolTipText("Alt+4");
        btnObat1.setName("btnObat1"); // NOI18N
        btnObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObat1ActionPerformed(evt);
            }
        });
        panelisi1.add(btnObat1);
        btnObat1.setBounds(545, 72, 28, 23);

        TNmOb.setEditable(false);
        TNmOb.setName("TNmOb"); // NOI18N
        panelisi1.add(TNmOb);
        TNmOb.setBounds(173, 72, 288, 23);

        TEmbalase.setText("0");
        TEmbalase.setHighlighter(null);
        TEmbalase.setName("TEmbalase"); // NOI18N
        TEmbalase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEmbalaseKeyPressed(evt);
            }
        });
        panelisi1.add(TEmbalase);
        TEmbalase.setBounds(303, 102, 100, 23);

        jLabel15.setText("Embalase : Rp.");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi1.add(jLabel15);
        jLabel15.setBounds(210, 102, 90, 23);

        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(215, 42, 40, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        panelisi1.add(cmbJam);
        cmbJam.setBounds(258, 42, 55, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        panelisi1.add(cmbMnt);
        cmbMnt.setBounds(315, 42, 55, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        panelisi1.add(cmbDtk);
        cmbDtk.setBounds(372, 42, 55, 23);

        TBiayaObat.setText("0");
        TBiayaObat.setHighlighter(null);
        TBiayaObat.setName("TBiayaObat"); // NOI18N
        panelisi1.add(TBiayaObat);
        TBiayaObat.setBounds(710, 70, 118, 23);

        jLabel17.setText("Harga : Rp.");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi1.add(jLabel17);
        jLabel17.setBounds(637, 72, 70, 23);

        TSatuan.setEditable(false);
        TSatuan.setName("TSatuan"); // NOI18N
        panelisi1.add(TSatuan);
        TSatuan.setBounds(463, 72, 80, 23);

        TTotal.setEditable(false);
        TTotal.setText("0");
        TTotal.setHighlighter(null);
        TTotal.setName("TTotal"); // NOI18N
        panelisi1.add(TTotal);
        TTotal.setBounds(698, 102, 130, 23);

        jLabel18.setText("Total Biaya : Rp.");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(605, 102, 90, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        panelisi1.add(ChkJln);
        ChkJln.setBounds(429, 42, 23, 23);

        btnKonversi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        btnKonversi.setMnemonic('2');
        btnKonversi.setToolTipText("Alt+2");
        btnKonversi.setName("btnKonversi"); // NOI18N
        btnKonversi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonversiActionPerformed(evt);
            }
        });
        panelisi1.add(btnKonversi);
        btnKonversi.setBounds(173, 102, 28, 23);

        BtnObat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat2.setMnemonic('4');
        BtnObat2.setToolTipText("Alt+4");
        BtnObat2.setName("BtnObat2"); // NOI18N
        BtnObat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObat2ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnObat2);
        BtnObat2.setBounds(575, 72, 28, 23);

        BtnObat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat3.setMnemonic('4');
        BtnObat3.setToolTipText("Alt+4");
        BtnObat3.setName("BtnObat3"); // NOI18N
        BtnObat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObat3ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnObat3);
        BtnObat3.setBounds(604, 72, 28, 23);

        jLabel20.setText("No.Resep : ");
        jLabel20.setName("jLabel20"); // NOI18N
        panelisi1.add(jLabel20);
        jLabel20.setBounds(650, 40, 70, 23);

        TTuslah.setText("0");
        TTuslah.setHighlighter(null);
        TTuslah.setName("TTuslah"); // NOI18N
        TTuslah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTuslahKeyPressed(evt);
            }
        });
        panelisi1.add(TTuslah);
        TTuslah.setBounds(490, 102, 100, 23);

        Ket1.setForeground(new java.awt.Color(0, 0, 0));
        Ket1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BPJS", "UMUM" }));
        Ket1.setName("Ket1"); // NOI18N
        Ket1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Ket1ItemStateChanged(evt);
            }
        });
        Ket1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ket1MouseClicked(evt);
            }
        });
        Ket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ket1ActionPerformed(evt);
            }
        });
        Ket1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ket1KeyPressed(evt);
            }
        });
        panelisi1.add(Ket1);
        Ket1.setBounds(560, 40, 90, 23);

        jLabel24.setText("Tuslah : Rp.");
        jLabel24.setName("jLabel24"); // NOI18N
        panelisi1.add(jLabel24);
        jLabel24.setBounds(417, 102, 70, 23);

        NoResep.setHighlighter(null);
        NoResep.setName("NoResep"); // NOI18N
        NoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoResepActionPerformed(evt);
            }
        });
        panelisi1.add(NoResep);
        NoResep.setBounds(720, 40, 110, 23);

        jLabel25.setText("Cara Bayar :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelisi1.add(jLabel25);
        jLabel25.setBounds(490, 40, 70, 23);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.CENTER);

        TabInput.addTab(".: Input Pemberian Obat", internalFrame2);

        internalFrame3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi2.setComponentPopupMenu(Popup1);
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(null);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Pilihan Cara Bayar :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(94, 12));
        panelisi2.add(jLabel23);
        jLabel23.setBounds(0, 10, 120, 20);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelisi2.add(kdpnj);
        kdpnj.setBounds(127, 10, 70, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Pilihan Unit Farmasi :");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi2.add(jLabel21);
        jLabel21.setBounds(0, 40, 120, 20);

        KdGudang.setEditable(false);
        KdGudang.setForeground(new java.awt.Color(0, 0, 0));
        KdGudang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        KdGudang.setEnabled(false);
        KdGudang.setName("KdGudang"); // NOI18N
        KdGudang.setPreferredSize(new java.awt.Dimension(80, 23));
        KdGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdGudangKeyPressed(evt);
            }
        });
        panelisi2.add(KdGudang);
        KdGudang.setBounds(127, 40, 70, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Nama Unit/Poliklinik :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi2.add(label19);
        label19.setBounds(0, 70, 120, 20);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi2.add(kdpoli);
        kdpoli.setBounds(127, 70, 70, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(203, 23));
        TPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPoliActionPerformed(evt);
            }
        });
        panelisi2.add(TPoli);
        TPoli.setBounds(200, 70, 240, 23);

        NmGudang.setEditable(false);
        NmGudang.setForeground(new java.awt.Color(0, 0, 0));
        NmGudang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        NmGudang.setEnabled(false);
        NmGudang.setName("NmGudang"); // NOI18N
        NmGudang.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi2.add(NmGudang);
        NmGudang.setBounds(200, 40, 240, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        panelisi2.add(nmpnj);
        nmpnj.setBounds(200, 10, 240, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("- Klik kanan disini utk. lihat LAPORAN -");
        jLabel22.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(94, 12));
        panelisi2.add(jLabel22);
        jLabel22.setBounds(530, 30, 230, 40);

        BtnRefresLap.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefresLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnRefresLap.setMnemonic('1');
        BtnRefresLap.setText("Refresh Laporan");
        BtnRefresLap.setToolTipText("Alt+1");
        BtnRefresLap.setName("BtnRefresLap"); // NOI18N
        BtnRefresLap.setPreferredSize(new java.awt.Dimension(150, 23));
        BtnRefresLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefresLapActionPerformed(evt);
            }
        });
        BtnRefresLap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnRefresLapKeyPressed(evt);
            }
        });
        panelisi2.add(BtnRefresLap);
        BtnRefresLap.setBounds(330, 100, 140, 30);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelisi2.add(btnPenjab);
        btnPenjab.setBounds(444, 10, 28, 23);

        btnBarang1.setForeground(new java.awt.Color(0, 0, 0));
        btnBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang1.setMnemonic('1');
        btnBarang1.setToolTipText("Alt+1");
        btnBarang1.setName("btnBarang1"); // NOI18N
        btnBarang1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarang1ActionPerformed(evt);
            }
        });
        panelisi2.add(btnBarang1);
        btnBarang1.setBounds(444, 40, 28, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi2.add(BtnUnit);
        BtnUnit.setBounds(444, 70, 30, 22);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.CENTER);

        TabInput.addTab(".: Laporan Farmasi", internalFrame3);

        PanelInput.add(TabInput, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        tampilPO();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt,TCari,DTPBeri);
}//GEN-LAST:event_TNoRwKeyPressed

    private void TJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJumlahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
            TKdOb.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TEmbalase.setText(Double.toString(embalase));
            TTuslah.setText(Double.toString(tuslah));
            isjml();
            TEmbalase.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKonversiActionPerformed(null);
        }
}//GEN-LAST:event_TJumlahKeyPressed

    private void DTPBeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPBeriKeyPressed
        Valid.pindah(evt,TNoRw,cmbJam);
}//GEN-LAST:event_DTPBeriKeyPressed

    private void TKdObKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdObKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnObat1ActionPerformed(null);
        }else{            
            Valid.pindah(evt,cmbDtk,TJumlah);
        }
    }//GEN-LAST:event_TKdObKeyPressed

    private void btnObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObat1ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else {
            akses.setform("DlgPemberianObat");
            if(akses.getkode().equals("Admin Utama")){
                if(status.equals("ranap")){
                    dlgobt.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),DTPBeri.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false);
                    dlgobt.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgobt.isCek();
                    dlgobt.emptTeksobat();
                    dlgobt.tampil();
                    dlgobt.setLocationRelativeTo(internalFrame1);
                    dlgobt.setVisible(true);
                }else if(status.equals("ralan")){
                    dlgobtjalan.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='"+TNoRw.getText()+"'"),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='"+TNoRw.getText()+"'"));
                    dlgobtjalan.isCek();
                    if(!namadokter.equals("")){
                        dlgobtjalan.setDokter(kodedokter, namadokter);
                    }
                    dlgobtjalan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgobtjalan.emptTeksobat();
                    dlgobtjalan.tampilobat();
                    dlgobtjalan.setLocationRelativeTo(internalFrame1);
                    dlgobtjalan.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane,"Hanya bisa lewat Kasir Ralan atau Kamar Inap");
                }
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                }else{ 
                    if(status.equals("ranap")){
                        dlgobt.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),DTPBeri.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),cmbDtk.getSelectedItem().toString(),false);
                        dlgobt.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                        dlgobt.isCek();
                        dlgobt.emptTeksobat();
                        dlgobt.tampil();
                        dlgobt.setLocationRelativeTo(internalFrame1);
                        dlgobt.setVisible(true);
                    }else if(status.equals("ralan")){
                        dlgobtjalan.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='"+TNoRw.getText()+"'"),
                                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='"+TNoRw.getText()+"'"));
                        dlgobtjalan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                        dlgobtjalan.isCek();
                        if(!namadokter.equals("")){
                            dlgobtjalan.setDokter(kodedokter, namadokter);
                        }
                        dlgobtjalan.emptTeksobat();
                        dlgobtjalan.tampilobat();
                        dlgobtjalan.setLocationRelativeTo(internalFrame1);
                        dlgobtjalan.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(rootPane,"Hanya bisa lewat Kasir Ralan atau Kamar Inap");
                    }        
                }
            }
        }        
    }//GEN-LAST:event_btnObat1ActionPerformed

    private void TEmbalaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEmbalaseKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isjml();
            TTuslah.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            isjml();
            TJumlah.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isjml();
        }
    }//GEN-LAST:event_TEmbalaseKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        TKdOb.setText("");
        TNmOb.setText("");
        TJumlah.setText("0");
        TTotal.setText("0");
        TEmbalase.setText("0");
        TTuslah.setText("0");
        TBiayaObat.setText("0");
        THBeli.setText("0");
        TNoRw.requestFocus();
        DTPBeri.setDate(new Date());
        TNoRw.requestFocus();
        ChkInput.setSelected(true);
        Ket1.setSelectedIndex(0);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{Valid.pindah(evt, BtnBatal, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbPemberianObat.getSelectedRow()!= -1){
            bangsal=tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),13).toString();
        }
            
        if(tabModePO.getRowCount()==0){
             JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
             TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
             JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(ChkJln.isSelected()==true){
            JOptionPane.showMessageDialog(rootPane,"Matikan dulu jam otomatis sebelum menghapus data..!!");
            ChkJln.requestFocus();
        }else if(!(TPasien.getText().trim().equals(""))){
            try{   
                if(tbPemberianObat.getSelectedRow()!= -1){
                    jmlparsial=0;
                    if(aktifkanparsial.equals("yes")){
                        jmlparsial=Sequel.cariInteger("select count(kd_pj) from set_input_parsial where kd_pj=?",Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",TNoRw.getText()));
                    }
                    Sequel.AutoComitFalse();
                    sukses=true;
                    if(jmlparsial>0){  
                        hapus();
                    }else{
                        if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                            JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        }else{
                            hapus();
                        }
                    } 
                    if(sukses==true){
                        Sequel.Commit();
                    }else{
                        sukses=false;
                        JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                        Sequel.RollBack();
                    }
                    Sequel.AutoComitTrue();
                    if(sukses==true){
                        tampilPO();
                    }
                }                    
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...\n Klik data pada table untuk memilih data...!!!!");
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampilPO();
        if(tabModePO.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabModePO.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            if(!TCariPasien.getText().equals("")){
               pas=" and reg_periksa.no_rkm_medis='"+TCariPasien.getText()+"' "; 
            }
            tgl=" detail_pemberian_obat.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;        
            Valid.MyReportqry("rptBrObt.jasper","report","::[ Rekam Data Pemberian Obat (UMUM) ]::","select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"+
                   "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"+
                   "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total "+
                   "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "+
                   "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and detail_pemberian_obat.kode_brng=databarang.kode_brng "+
                   "where  "+tgl+"and tgl_perawatan like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and detail_pemberian_obat.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and detail_pemberian_obat.kode_brng like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and databarang.nama_brng like '%"+TCari.getText().trim()+"%' "+
                   "order by detail_pemberian_obat.tgl_perawatan",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        tampilPO();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilPO();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnSeek4.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DTPCari2.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        akses.setform("DlgPemberianObat");
        pasien.emptTeks();    
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt,TCariPasien,DTPCari1);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPBeri,cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt,cmbMnt,TKdOb);
}//GEN-LAST:event_cmbDtkKeyPressed

    private void tbPemberianObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianObatMouseClicked
        if(tabModePO.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPemberianObatMouseClicked

    private void tbPemberianObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianObatKeyPressed
        if(tabModePO.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
          } if(evt.getKeyCode()==KeyEvent.VK_R){
                ppResiObatUmumActionPerformed(null);
          } if(evt.getKeyCode()==KeyEvent.VK_O){
                MnBayarObatActionPerformed(null);
          }
}//GEN-LAST:event_tbPemberianObatKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

private void btnKonversiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonversiActionPerformed
    carikonversi.setLocationRelativeTo(internalFrame1);
    carikonversi.setVisible(true);
}//GEN-LAST:event_btnKonversiActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(tbPemberianObat.getSelectedRow()!= -1){
            bangsal=tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),13).toString();
        }
        
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TKdOb.getText().trim().equals("")||TNmOb.getText().trim().equals("")){
            Valid.textKosong(TKdOb,"databarang penyakit");
        }else if(bangsal.equals("")){
            Valid.textKosong(TCari,"Lokasi");
        }else if(TJumlah.getText().trim().equals("")){
            Valid.textKosong(TJumlah,"Jumlah Obat");
        }else if(TEmbalase.getText().trim().equals("")){
            Valid.textKosong(TEmbalase,"Embalase");
        }else if(TTuslah.getText().trim().equals("")){
            Valid.textKosong(TEmbalase,"Tuslah");
        }else if(ChkJln.isSelected()==true){
            JOptionPane.showMessageDialog(rootPane,"Matikan dulu jam otomatis sebelum mengedit data..!!");
            ChkJln.requestFocus();
        }else{       
            if(akses.getkode().equals("Admin Utama")){
                try {  
                    Sequel.AutoComitFalse();
                    statusberi=Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                  "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                  "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                  "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                    ttlhpp=Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                  "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                  "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                  "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                    ttljual=Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                  "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                  "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                  "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");

                    if(Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                  "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                  "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                  "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'")==true){
                        if(statusberi.equals("Ranap")){
                            Sequel.queryu("delete from tampjurnal");    
                            if(ttljual>0){
                                Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ranap+"','Suspen Piutang Obat Ranap','0','"+ttljual+"'","Rekening");    
                                Sequel.menyimpan("tampjurnal","'"+Obat_Ranap+"','Pendapatan Obat Rawat Inap','"+ttljual+"','0'","Rekening");                              
                            }
                            if(ttlhpp>0){
                                Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Inap+"','HPP Persediaan Obat Rawat Inap','0','"+ttlhpp+"'","Rekening");    
                                Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Inap+"','Persediaan Obat Rawat Inap','"+ttlhpp+"','0'","Rekening");                              
                            }
                            jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString(),"U","PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH "+akses.getkode());                                                
                        }

                        Sequel.queryu("delete from aturan_pakai where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                      "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                      "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                      "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                        if(btnObat1.isEnabled()==true){
                             Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                                Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                                0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus",
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString(),
                                 tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                             );
                            Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'", 
                                                "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                                "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                                "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
                                                "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'");
                        }
                    }

                    ttlhpp=0;
                    ttljual=0;
                    if(Sequel.menyimpantf2("detail_pemberian_obat","'"+Valid.SetTgl(DTPBeri.getSelectedItem()+"")+"','"+
                                    cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"','"+
                                    TNoRw.getText()+"','"+TKdOb.getText()+"','"+THBeli.getText()+"','"+TBiayaObat.getText()+"','"+
                                    TJumlah.getText()+"','"+TEmbalase.getText()+"','"+TTuslah.getText()+"',(("+TBiayaObat.getText()+"*"+TJumlah.getText()+")+"+TEmbalase.getText()+"+"+TTuslah.getText()+"),'"+statussimpan+"','"+bangsal+"','"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"','"+NoResep.getText()+"','"+Ket1.getSelectedItem().toString()+"','"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ","data")==true){ 
                        if(statussimpan.equals("Ranap")){
                            ttlhpp=Double.parseDouble(THBeli.getText())*Double.parseDouble(TJumlah.getText());
                            ttljual=(Double.parseDouble(TBiayaObat.getText())*Double.parseDouble(TJumlah.getText()))+Double.parseDouble(TEmbalase.getText())+Double.parseDouble(TTuslah.getText());
                            Sequel.queryu("delete from tampjurnal");    
                            if(ttljual>0){
                                Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ranap+"','Suspen Piutang Obat Ranap','"+ttljual+"','0'","Rekening");    
                                Sequel.menyimpan("tampjurnal","'"+Obat_Ranap+"','Pendapatan Obat Rawat Inap','0','"+ttljual+"'","Rekening");                              
                            }
                            if(ttlhpp>0){
                                Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Inap+"','HPP Persediaan Obat Rawat Inap','"+ttlhpp+"','0'","Rekening");    
                                Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Inap+"','Persediaan Obat Rawat Inap','0','"+ttlhpp+"'","Rekening");                              
                            }
                            jur.simpanJurnal(TNoRw.getText(),"U","PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH "+akses.getkode());                                              
                        }
                         if(Sequel.cariIsi("SELECT detail_pemberian_obat.no_resep FROM detail_pemberian_obat WHERE detail_pemberian_obat.cara_bayar='BPJS' AND detail_pemberian_obat.no_resep=?",NoResep.getText()).isEmpty()){
                                             if(Sequel.menyimpantf2("status_resep","?,?,?,?,?,?,?,?,?","Nomer Resep",9,new String[]{
                                            NoResep.getText(),Valid.SetTgl(DTPBeri.getSelectedItem()+""),
                                            TNoRw.getText(),Sequel.cariIsi("SELECT kd_pj FROM reg_periksa WHERE no_rawat=?",TNoRw.getText()),
                                             Sequel.cariIsi("SELECT status_lanjut FROM reg_periksa WHERE no_rawat=?",TNoRw.getText()),""
                                             ,Sequel.cariIsi("SELECT kd_bangsal FROM detail_pemberian_obat WHERE no_resep=?",NoResep.getText()),"belum bayar",""
                                             })); 

                                            } 

                        if(btnObat1.isEnabled()==true){
                            Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                                Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                                0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus",
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString(),
                                 tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                            );
                            Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'", 
                                                "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                                "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                                "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
                                                "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'");
                        }
                    }
                    Sequel.AutoComitTrue();
    
                    tampilPO();
                    BtnBatalActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
                }
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                }else{
                    try {  
                        Sequel.AutoComitFalse();
                        statusberi=Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                      "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                      "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                      "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                        ttlhpp=Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                      "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                      "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                      "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                        ttljual=Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                      "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                      "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                      "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");

                        if(Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                      "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                      "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                      "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'")==true){
                            if(statusberi.equals("Ranap")){
                                Sequel.queryu("delete from tampjurnal");    
                                if(ttljual>0){
                                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ranap+"','Suspen Piutang Obat Ranap','0','"+ttljual+"'","Rekening");    
                                    Sequel.menyimpan("tampjurnal","'"+Obat_Ranap+"','Pendapatan Obat Rawat Inap','"+ttljual+"','0'","Rekening");                              
                                }
                                if(ttlhpp>0){
                                    Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Inap+"','HPP Persediaan Obat Rawat Inap','0','"+ttlhpp+"'","Rekening");    
                                    Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Inap+"','Persediaan Obat Rawat Inap','"+ttlhpp+"','0'","Rekening");                              
                                }
                                jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString(),"U","PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH "+akses.getkode());                                                
                            }

                            Sequel.queryu("delete from aturan_pakai where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                                          "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                                          "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                                          "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
                            if(btnObat1.isEnabled()==true){
                               Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                                Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                                0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus",
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString(),
                                 tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                               );
                            Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'", 
                                                "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                                "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                                "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
                                                "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'");
                        }
                        }

                        ttlhpp=0;
                        ttljual=0;
                        if(Sequel.menyimpantf2("detail_pemberian_obat","'"+Valid.SetTgl(DTPBeri.getSelectedItem()+"")+"','"+
                                        cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"','"+
                                        TNoRw.getText()+"','"+TKdOb.getText()+"','"+THBeli.getText()+"','"+TBiayaObat.getText()+"','"+
                                        TJumlah.getText()+"','"+TEmbalase.getText()+"','"+TTuslah.getText()+"',(("+TBiayaObat.getText()+"*"+TJumlah.getText()+")+"+TEmbalase.getText()+"+"+TTuslah.getText()+"),'"+statussimpan+"','"+bangsal+"','"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"','"+NoResep.getText()+"','"+Ket1.getSelectedItem().toString()+"','"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ","data")==true){ 
                            if(statussimpan.equals("Ranap")){
                                ttlhpp=Double.parseDouble(THBeli.getText())*Double.parseDouble(TJumlah.getText());
                                ttljual=(Double.parseDouble(TBiayaObat.getText())*Double.parseDouble(TJumlah.getText()))+Double.parseDouble(TEmbalase.getText())+Double.parseDouble(TTuslah.getText());
                                Sequel.queryu("delete from tampjurnal");    
                                if(ttljual>0){
                                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ranap+"','Suspen Piutang Obat Ranap','"+ttljual+"','0'","Rekening");    
                                    Sequel.menyimpan("tampjurnal","'"+Obat_Ranap+"','Pendapatan Obat Rawat Inap','0','"+ttljual+"'","Rekening");                              
                                }
                                if(ttlhpp>0){
                                    Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Inap+"','HPP Persediaan Obat Rawat Inap','"+ttlhpp+"','0'","Rekening");    
                                    Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Inap+"','Persediaan Obat Rawat Inap','0','"+ttlhpp+"'","Rekening");                              
                                }
                                jur.simpanJurnal(TNoRw.getText(),"U","PEMBERIAN OBAT RAWAT INAP PASIEN, DIPOSTING OLEH "+akses.getkode());                                              
                            }
                             if(Sequel.cariIsi("SELECT detail_pemberian_obat.no_resep FROM detail_pemberian_obat WHERE detail_pemberian_obat.cara_bayar='BPJS' AND detail_pemberian_obat.no_resep=?",NoResep.getText()).isEmpty()){
                                            if(Sequel.menyimpantf2("status_resep","?,?,?,?,?,?,?,?,?","Nomer Resep",9,new String[]{
                                            NoResep.getText(),Valid.SetTgl(DTPBeri.getSelectedItem()+""),
                                            TNoRw.getText(),Sequel.cariIsi("SELECT kd_pj FROM reg_periksa WHERE no_rawat=?",TNoRw.getText()),
                                             Sequel.cariIsi("SELECT status_lanjut FROM reg_periksa WHERE no_rawat=?",TNoRw.getText()),""
                                             ,Sequel.cariIsi("SELECT kd_bangsal FROM detail_pemberian_obat WHERE no_resep=?",NoResep.getText()),"belum bayar",""
                                             })); 

                                            }  

                            if(btnObat1.isEnabled()==true){
                                Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                                Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                                0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus",
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                                tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString(),
                                 tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                                );
                            Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"',"+
                                                "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'", 
                                                "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                                "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                                "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
                                                "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'");
                        }
                        }
                        Sequel.AutoComitTrue();
                        tampilPO();
                        Ket1.setSelectedIndex(0);
                        BtnBatalActionPerformed(evt);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan data. Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
                    }
                }
            }   
        }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnObat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObat2ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else {
            if(akses.getkode().equals("Admin Utama")){
                akses.setform("DlgPemberianObat");
                dlgobt2.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),DTPBeri.getDate());
                dlgobt2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgobt2.setLocationRelativeTo(internalFrame1);
                dlgobt2.setVisible(true);
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                }else{ 
                    akses.setform("DlgPemberianObat");
                    dlgobt2.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),DTPBeri.getDate());
                    dlgobt2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgobt2.setLocationRelativeTo(internalFrame1);
                    dlgobt2.setVisible(true);        
                }
            }            
        } 
    }//GEN-LAST:event_BtnObat2ActionPerformed

    private void BtnObat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObat3ActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else {
            if(akses.getkode().equals("Admin Utama")){
                if(status.equals("ralan")){
                    akses.setform("DlgPemberianObat");
                    dlgobtpny.setNoRm(TNoRw.getText(),Sequel.cariIsi("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas limit 1",TNoRw.getText()));
                    dlgobtpny.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgobtpny.setLocationRelativeTo(internalFrame1);
                    dlgobtpny.setVisible(true);
                }
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                }else{ 
                    if(status.equals("ralan")){
                        akses.setform("DlgPemberianObat");
                        dlgobtpny.setNoRm(TNoRw.getText(),Sequel.cariIsi("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas limit 1",TNoRw.getText()));
                        dlgobtpny.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                        dlgobtpny.setLocationRelativeTo(internalFrame1);
                        dlgobtpny.setVisible(true);
                    }        
                }
            }
        } 
    }//GEN-LAST:event_BtnObat3ActionPerformed

    private void TTuslahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTuslahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isjml();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            isjml();
            TEmbalase.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isjml();
        }
    }//GEN-LAST:event_TTuslahKeyPressed

    private void ppResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResepObatActionPerformed
        if(tabModePO.getRowCount()==0){
             JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
             TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
             JOptionPane.showMessageDialog(null,"Maaf, pilih dulu data yang mau dibuatkan Nomor Resep..!!\nKlik data pada table untuk memilih...!!!!");
        }else if(!(TPasien.getText().trim().equals(""))){
            DlgResepObat resep=new DlgResepObat(null,false);
                    resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.emptTeks(); 
                    resep.isCek();
                    resep.setNoRm(TNoRw.getText(),Valid.SetTgl2(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()),Valid.SetTgl2(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()),tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(0,2),tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(3,5),tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(6,8),status);
                    resep.tampil();
                    resep.setVisible(true);
        }
    }//GEN-LAST:event_ppResepObatActionPerformed

    private void ppNoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppNoRawatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampilPO();
        if(tabModePO.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabModePO.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs()); 
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            if(!TCariPasien.getText().equals("")){
               pas=" and reg_periksa.no_rkm_medis='"+TCariPasien.getText()+"' "; 
            }
            tgl=" detail_pemberian_obat.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;        
            Valid.MyReportqry("rptBrObt2.jasper","report","::[ Rekam Data Pemberian Obat (UMUM) ]::","select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"+
                   "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"+
                   "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total "+
                   "from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "+
                   "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and detail_pemberian_obat.kode_brng=databarang.kode_brng "+
                   "where  "+tgl+"and tgl_perawatan like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and detail_pemberian_obat.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and detail_pemberian_obat.kode_brng like '%"+TCari.getText().trim()+"%' or "+
                   tgl+"and databarang.nama_brng like '%"+TCari.getText().trim()+"%' "+
                   "order by detail_pemberian_obat.tgl_perawatan",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppNoRawatActionPerformed

    private void ppLokasiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLokasiBtnPrintActionPerformed
        depo.isCek();
        depo.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        depo.setLocationRelativeTo(internalFrame1);
        depo.setVisible(true);
    }//GEN-LAST:event_ppLokasiBtnPrintActionPerformed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_kdpnjKeyPressed

    private void KdGudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdGudangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdGudangKeyPressed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnRefresLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefresLapActionPerformed
        kdpnj.setText("");
        nmpnj.setText("");
        KdGudang.setText("");
        NmGudang.setText("");
        kdpoli.setText("");
        TPoli.setText("");
    }//GEN-LAST:event_BtnRefresLapActionPerformed

    private void BtnRefresLapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnRefresLapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRefresLapKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgPemberianObat");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void btnBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarang1ActionPerformed
        akses.setform("DlgPemberianObat");
        bangsal2.emptTeks();
        bangsal2.isCek();
        bangsal2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        bangsal2.setLocationRelativeTo(internalFrame1);
        bangsal2.setAlwaysOnTop(false);
        bangsal2.setVisible(true);
        bangsal2.emptTeks();
    }//GEN-LAST:event_btnBarang1ActionPerformed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgPemberianObat");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void jMnRekapTotalPerPasienJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanActionPerformed
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRJRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_barang_medis.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_barang_medis.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT')) AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal in ('APT') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepo1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Semua Depo ]::",
                  " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal in ('APT')) and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanActionPerformed

    private void jMnRekapTotalPerPasienJalanCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCaraActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRJRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal in ('APT') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                + " AND a.kd_bangsal in ('APT') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCaraActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCaraActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepoPerCB1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal in ('APT') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT') AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCaraActionPerformed

    private void jMnRekapTotalResepPasienPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienPerCBActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapTotalResepPerPoliPerCB.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Semua Depo Per Poli Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
            + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
            + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
            + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalResepPasienPerCBActionPerformed

    private void jMnRekapDetailResepPasienPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailResepPasienPerCBActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliPerCB.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Semua Depo Per Poli Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailResepPasienPerCBActionPerformed

    private void jMnRekapTotalResepPasienSemuaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienSemuaCBActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapTotalResepPerPoliSemuaCB.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Semua Depo Per Poli Semua Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
            + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
            + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
            + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalResepPasienSemuaCBActionPerformed

    private void jMnRekapDetailResepPasienSemuaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailResepPasienSemuaCBActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliSemuaCB.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Semua Depo Per Poli Semua Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailResepPasienSemuaCBActionPerformed

    private void jMnRekapTotalPerPasienInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRIRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal in ('APT')) AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal in ('APT') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapActionPerformed

    private void jMnRekapDetailTotalPerPasienInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepo2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal in ('APT')) and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapActionPerformed

    private void jMnRekapTotalPerPasienInapCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCaraActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRIRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal in ('APT') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal in ('APT') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCaraActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCaraActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepoPerCB2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal in ('APT') and penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT') and a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCaraActionPerformed

    private void jMnRekapTotalPerPasienJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalan1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRJRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalan1ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalan1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepo1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalan1ActionPerformed

    private void jMnRekapTotalPerPasienJalanCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCara1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRJRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCara1ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepoPerCB1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed

    private void jMnRekapTotalResepPasienPerCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienPerCB1ActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_unit", NmGudang.getText());
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapTotalResepPerPoliPerCBPerDepo.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Per Depo Per Poli Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
            + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
            + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
            + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalResepPasienPerCB1ActionPerformed

    private void jMnRekapDetailTotalResepPasienPerCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalResepPasienPerCB1ActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_unit", NmGudang.getText());
        param.put("nm_poli", TPoli.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliPerCBPerDepo.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Per Depo Per Poli Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalResepPasienPerCB1ActionPerformed

    private void jMnRekapTotalResepPasienSemuaCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienSemuaCB1ActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("nm_unit", NmGudang.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapTotalResepPerPoliSemuaCBPerDepo.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Per Depo Per Poli Semua Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
            + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
            + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
            + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalResepPasienSemuaCB1ActionPerformed

    private void jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("nm_poli", TPoli.getText());
        param.put("nm_unit", NmGudang.getText());
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliSemuaCBPerDepo.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Per Depo Per Poli Semua Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed

    private void jMnRekapTotalPerPasienInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInap1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRIRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInap1ActionPerformed

    private void jMnRekapDetailTotalPerPasienInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInap1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepo2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInap1ActionPerformed

    private void jMnRekapTotalPerPasienInapCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCara1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptRIRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCara1ActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCara1ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepoPerCB2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCara1ActionPerformed

    private void jMnRekapTotalPerPasienJalan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalan2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptSJRRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalan2ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalan2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepoSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalan2ActionPerformed

    private void jMnRekapTotalPerPasienJalanCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCara2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptSJRRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCara2ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienPerDepoPerCBSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Per Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed

    private void jMnRekapTotalPerPasienInap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInap2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptSJRRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT')) AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInap2ActionPerformed

    private void jMnRekapDetailTotalPerPasienInap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInap2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepoSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT')) and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInap2ActionPerformed

    private void jMnRekapTotalPerPasienInapCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCara2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptSJRRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCara2ActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCara2ActionPerformed
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReportqry("rptDRTPerPasienSemuaDepoPerCBSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Semua Depo ]::",
                " SELECT a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                + " FROM ((SELECT detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_barang_medis ON riwayat_barang_medis.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_barang_medis.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_barang_medis.jam = detail_pemberian_obat.jam "
                + " AND riwayat_barang_medis.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_barang_medis.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT') AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCara2ActionPerformed

    private void jMenuLapObatPerDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuLapObatPerDokterActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptLapRekapObatSemua.jasper", "report", "::[ Laporan Rekap Total Pemakaian Obat Per Dokter ]::",
            " select * from(SELECT databarang.nama_brng, databarang.kode_sat, sum(detail_pemberian_obat.jml) Jumlah, dokter.nm_dokter "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter INNER JOIN penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat "
            + " AND resep_obat.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter "
            + " AND penjab.kd_pj = reg_periksa.kd_pj LEFT JOIN bridging_sep ON bridging_sep.no_rawat = resep_obat.no_rawat "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group BY databarang.kode_brng, dokter.kd_dokter "
            + " union ALL "
            + " select databarang.nama_brng, databarang.kode_sat, sum(detailjual.jumlah) jumlah, ifnull(dokter.nm_dokter,'-') dokter from penjualan "
            + " inner join detailjual on penjualan.nota_jual = detailjual.nota_jual inner join databarang  on databarang.kode_brng = detailjual.kode_brng "
            + " left join dokter  on dokter.kd_dokter = penjualan.kd_dokter where penjualan.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " group by databarang.kode_brng, dokter.kd_dokter) as a order by a.nama_brng, a.Jumlah", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMenuLapObatPerDokterActionPerformed

    private void jMnRekapResepRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapResepRalanActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptrekapresepralan.jasper", "report", "::[ Laporan Rekap Lembar & Jumlah Resep Ralan ]::",
                " select distinct a.nm_poli, ifnull(b.total,0) jlh_obat,ifnull(c.total,0) byknya_RESEP, IFNULL(d.total,0) Generik from "
                + " ((select nm_poli from poliklinik) as a left JOIN "
                + " (select k.nm_poli, count(d.kode_brng) total from reg_periksa p "
                + " inner join poliklinik k on k.kd_poli = p.kd_poli "
                + " inner join detail_pemberian_obat d on d.no_rawat = p.no_rawat "
                + " where  p.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and p.status_lanjut='Ralan' "
                + " GROUP BY k.nm_poli)as b on b.nm_poli = a.nm_poli "
                + " left JOIN (select k.nm_poli, count(d.no_resep) total from reg_periksa p "
                + " inner join poliklinik k on k.kd_poli = p.kd_poli "
                + " inner join resep_obat d on d.no_rawat = p.no_rawat "
                + " where p.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and p.status_lanjut='Ralan' "
                + " GROUP BY k.nm_poli)as c on c.nm_poli = a.nm_poli) "
                + " left JOIN (SELECT poliklinik.nm_poli, COUNT(detail_pemberian_obat.kode_brng) total " 
                + " FROM reg_periksa "
                + " INNER JOIN detail_pemberian_obat ON detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli=reg_periksa.kd_poli " 
                + " INNER JOIN databarang ON databarang.kode_brng=detail_pemberian_obat.kode_brng "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' "
                + " AND databarang.kode_golongan='G01' "
                + " GROUP BY poliklinik.nm_poli)AS d on d.nm_poli=a.nm_poli "
                + "where ifnull(b.total,0) > 0 or ifnull(c.total,0) > 0 OR IFNULL(d.total,0) > 0", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapResepRalanActionPerformed

    private void jMnRekapResepRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapResepRanapActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptrekapresepranap.jasper", "report", "::[ Laporan Rekap Lembar & Jumlah Resep Ranap ]::",
                " select a.nm_bangsal, ifnull(b.total,0) Jumlah_R,ifnull(c.total,0) Lembar_R, IFNULL(d.total, 0) Generik from "
                + " ((select nm_bangsal from bangsal) as a left JOIN "
                + " (select b.nm_bangsal, count(d.kode_brng) total from kamar_inap p "
                + " inner join kamar k on k.kd_kamar = p.kd_kamar "
                + " inner join bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " inner join detail_pemberian_obat d on d.no_rawat = p.no_rawat "
                + " where p.tgl_keluar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " GROUP BY b.nm_bangsal) as b on b.nm_bangsal = a.nm_bangsal left JOIN "
                + " (select b.nm_bangsal, count(d.no_resep) total from kamar_inap p "
                + " inner join kamar k on k.kd_kamar = p.kd_kamar "
                + " inner join bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " inner join resep_obat d on d.no_rawat = p.no_rawat "
                + " where p.tgl_keluar BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " GROUP BY b.nm_bangsal) as c on c.nm_bangsal = a.nm_bangsal) "
                + " LEFT JOIN (SELECT b.nm_bangsal, COUNT(dpo.kode_brng) total "
                + " FROM detail_pemberian_obat dpo "
                + " INNER JOIN kamar_inap ki ON dpo.no_rawat=ki.no_rawat "
                + " INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + " INNER JOIN databarang db ON db.kode_brng=dpo.kode_brng "
                + " WHERE dpo.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'  "
                + " AND db.kode_golongan='G01' GROUP BY b.nm_bangsal) AS d on d.nm_bangsal = a.nm_bangsal "
                + " where ifnull(b.total,0) > 0 or ifnull(c.total,0) > 0 or ifnull(d.total,0) > 0", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapResepRanapActionPerformed

    private void jMenuRanapAntibiotikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRanapAntibiotikActionPerformed
    Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptantibiotik.jasper", "report", "::[ Laporan Antibiotik Ranap ]::",
                " SELECT b.nm_bangsal, CONCAT(db.nama_brng,' ',db.kode_sat) Antibiotik, SUM(dpo.jml) jumlah "
                + " FROM detail_pemberian_obat dpo INNER JOIN kamar_inap ki ON dpo.no_rawat=ki.no_rawat "
                + " INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + " INNER JOIN databarang db ON db.kode_brng=dpo.kode_brng "
                + " WHERE dpo.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND db.kdjns='J010' GROUP BY db.nama_brng, b.nm_bangsal "
                + " ORDER BY b.nm_bangsal ASC, db.nama_brng ASC", param);
   this.setCursor(Cursor.getDefaultCursor());      
    }//GEN-LAST:event_jMenuRanapAntibiotikActionPerformed
 
    private void JMenuRalanAntibiotikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuRalanAntibiotikActionPerformed
         Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptantibiotik1.jasper", "report", "::[ Laporan Antibiotik Ralan ]::",
          " SELECT poliklinik.nm_poli, CONCAT(databarang.nama_brng,' ',databarang.kode_sat) Antibiotik, SUM(detail_pemberian_obat.jml) jumlah "
        + " FROM reg_periksa "
        + " INNER JOIN detail_pemberian_obat ON detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
        + " INNER JOIN poliklinik ON poliklinik.kd_poli=reg_periksa.kd_poli "
        + " INNER JOIN databarang ON databarang.kode_brng=detail_pemberian_obat.kode_brng "
        + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
        + " AND databarang.kdjns='J010' GROUP BY databarang.nama_brng, poliklinik.nm_poli "
        + " ORDER BY poliklinik.nm_poli ASC, databarang.nama_brng ASC ", param);
    this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_JMenuRalanAntibiotikActionPerformed

    private void JmenuRanapGenerikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmenuRanapGenerikActionPerformed
      Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptgenerik1.jasper", "report", "::[ Laporan Generik Ranap ]::",
                " SELECT CONCAT(db.nama_brng,' ',db.kode_sat) Generik, SUM(dpo.jml) jumlah "
                + " FROM detail_pemberian_obat dpo INNER JOIN kamar_inap ki ON dpo.no_rawat=ki.no_rawat "
                + " INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                + " INNER JOIN databarang db ON db.kode_brng=dpo.kode_brng "
                + " WHERE dpo.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND db.kode_golongan='G01' GROUP BY db.nama_brng "
                + " ORDER BY db.nama_brng ASC", param);
   this.setCursor(Cursor.getDefaultCursor()); 
    }//GEN-LAST:event_JmenuRanapGenerikActionPerformed

    private void jMenuRalanGenerikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRalanGenerikActionPerformed
     Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptgenerik2.jasper", "report", "::[ Laporan Generik Ralan ]::",
          " SELECT CONCAT(databarang.nama_brng,' ',databarang.kode_sat) Generik, SUM(detail_pemberian_obat.jml) jumlah "
        + " FROM reg_periksa "
        + " INNER JOIN detail_pemberian_obat ON detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
        + " INNER JOIN databarang ON databarang.kode_brng=detail_pemberian_obat.kode_brng "
        + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
        + " AND databarang.kode_golongan='G01' GROUP BY databarang.nama_brng "
        + " ORDER BY  databarang.nama_brng ASC ", param);
    this.setCursor(Cursor.getDefaultCursor());    
    }//GEN-LAST:event_jMenuRalanGenerikActionPerformed

    private void jMenuAntibiotikAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAntibiotikAllActionPerformed
       Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptantibiotikall.jasper", "report", "::[ Laporan Antibiotik Ralan & Ralan ]::",
        " select CONCAT(databarang.nama_brng,' ',databarang.kode_sat) Antibiotik, detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam, "
      + " detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,diagnosa_pasien.kd_penyakit, penyakit.nm_penyakit, " 
      + " detail_pemberian_obat.jml,detail_pemberian_obat.status from detail_pemberian_obat inner join "
      + " reg_periksa inner join pasien inner join databarang INNER JOIN diagnosa_pasien INNER JOIN penyakit " 
      + " on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat " 
      + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis " 
      + " and detail_pemberian_obat.kode_brng=databarang.kode_brng " 
      + " and detail_pemberian_obat.no_rawat=diagnosa_pasien.no_rawat " 
      + " and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit " 
      + " where detail_pemberian_obat.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " 
      + " and databarang.kdjns='J010' and diagnosa_pasien.prioritas='1' order by databarang.nama_brng ASC, detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam ", param);
    this.setCursor(Cursor.getDefaultCursor());          
    }//GEN-LAST:event_jMenuAntibiotikAllActionPerformed

    private void jLapObatUmum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLapObatUmum1ActionPerformed
        HashMap<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReportqry("rptPenjualan3.jasper", "report", "::[ Laporan Setoran Obat Kasir Umum ]::",
            "select detail_pemberian_obat.tgl_perawatan, " +
            "SUM(IF(DAY(detail_pemberian_obat.tgl_perawatan),embalase,0)) as totalembalase, " +
            "(SUM(IF(DAY(detail_pemberian_obat.tgl_perawatan),total,0))-SUM(IF(DAY(detail_pemberian_obat.tgl_perawatan),embalase,0))) as totalobat, " +
            "SUM(IF(DAY(detail_pemberian_obat.tgl_perawatan),total,0)) as grandtotal from " +
            "detail_pemberian_obat inner join databarang INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN penjab " +
            "on detail_pemberian_obat.kode_brng=databarang.kode_brng " +
            "AND detail_pemberian_obat.no_rawat=reg_periksa.no_rawat " +
            "AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
            "AND reg_periksa.kd_pj=penjab.kd_pj " +
            "where detail_pemberian_obat.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "  +
            "and databarang.kode_brng not in (select kode_brng from detail_obat_racikan where tgl_perawatan and jam and no_rawat) AND penjab.kd_pj IN('-','002','009')", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jLapObatUmum1ActionPerformed

    private void jLapObatUmumdetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLapObatUmumdetailActionPerformed
        HashMap<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptdetailobatumum.jasper", "report", "::[ Detail Laporan Obat Kasir Umum ]::",
            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,databarang.nama_brng,detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.embalase,detail_pemberian_obat.total,detail_pemberian_obat.no_resep,bangsal.nm_bangsal, " +
            "penjab.png_jawab from " +
            "detail_pemberian_obat inner join databarang INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN penjab INNER JOIN status_resep INNER JOIN bangsal " +
            "on detail_pemberian_obat.kode_brng=databarang.kode_brng " +
            "AND detail_pemberian_obat.no_rawat=reg_periksa.no_rawat " +
            "AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
            "AND reg_periksa.kd_pj=penjab.kd_pj " +
            "AND detail_pemberian_obat.no_resep=status_resep.no_resep "+
            "AND detail_pemberian_obat.kd_bangsal=bangsal.kd_bangsal "+
            "where detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " +
            "and databarang.kode_brng not in (select kode_brng from detail_obat_racikan where tgl_perawatan and jam and no_rawat) AND detail_pemberian_obat.cara_bayar='UMUM' " +
            "AND status_resep.keterangan='sudah bayar' " +
            "ORDER BY reg_periksa.no_rawat ASC ",param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jLapObatUmumdetailActionPerformed

    private void Ket1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Ket1ItemStateChanged

    }//GEN-LAST:event_Ket1ItemStateChanged

    private void Ket1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ket1MouseClicked
        Ket1.setEditable(false);
    }//GEN-LAST:event_Ket1MouseClicked

    private void Ket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ket1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ket1ActionPerformed

    private void Ket1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ket1KeyPressed

    }//GEN-LAST:event_Ket1KeyPressed

    private void NoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoResepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoResepActionPerformed

    private void No_rw2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_No_rw2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_rw2KeyPressed

    private void kdpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpjKeyPressed

    private void status1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_status1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_status1KeyPressed

    private void lokasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lokasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lokasi1KeyPressed

    private void NmpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmpetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmpetugasActionPerformed

    private void DTPBayarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPBayarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPBayarItemStateChanged

    private void DTPBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPBayarKeyPressed

    private void btncaripetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugasActionPerformed
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.isCek();
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btncaripetugasActionPerformed

    private void btncaripetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncaripetugasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{Valid.pindah(evt,DTPBayar,BtnSimpan6);
        }
    }//GEN-LAST:event_btncaripetugasKeyPressed

    private void kd_nipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_nipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_nipActionPerformed

    private void tbkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkembaliMouseClicked
        if(tabmodebayar.getRowCount()!=0){
            try {
                getbayar();
            } catch (java.lang.NullPointerException e){

            }
        }
    }//GEN-LAST:event_tbkembaliMouseClicked

    private void tbkembaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkembaliKeyPressed
        if(tabmodebayar.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getbayar();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();

            }
        } else if(evt.getKeyCode()==KeyEvent.VK_O){
                MnBayarObatActionPerformed(null);
            }
    }//GEN-LAST:event_tbkembaliKeyPressed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
             if(NoResep1.getText().trim().equals("")){
            Valid.textKosong(NoResep1,"No.Resep");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{

            Sequel.mengedit("status_resep","no_resep=?","tgl_resep=?,no_rawat=?,kd_pj=?,status=?,petugas=?,lokasi=?,keterangan=?,ket1=?",9,new String[]{
                Valid.SetTgl(DTPBayar.getSelectedItem()+""),No_rw2.getText(),
                kdpj.getText(),status1.getText(),
                kd_nip.getText(),lokasi1.getText(),"sudah bayar",Keterangan.getText(),NoResep1.getText()});
        {
            JOptionPane.showMessageDialog(null,"Pembayaran Disimpan...!!!");
        }
        MnNotaPxActionPerformed(null);
        tampilbayar();
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if(NoResep1.getText().trim().equals("")){
            Valid.textKosong(NoResep1,"No.Resep");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu petugas...!!!");
        }else{

            Sequel.mengedit("status_resep","no_resep=?","tgl_resep=?,no_rawat=?,kd_pj=?,status=?,petugas=?,lokasi=?,keterangan=?,ket1=?",9,new String[]{
                Valid.SetTgl(DTPBayar.getSelectedItem()+""),No_rw2.getText(),
                kdpj.getText(),status1.getText(),
                kd_nip.getText(),lokasi1.getText(),"belum bayar",Keterangan.getText(),NoResep1.getText()});
        {
            JOptionPane.showMessageDialog(null,"Pembayaran Dibatalkan...!!!");
        }
        tampilbayar();

        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowBayarObat.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars",akses.getnamars());
        param.put("alamatrs",akses.getalamatrs());
        param.put("kotars",akses.getkabupatenrs());
        param.put("propinsirs",akses.getpropinsirs());
        param.put("kontakrs",akses.getkontakrs());
        param.put("emailrs",akses.getemailrs());
        param.put("logo",Sequel.cariGambar("select logo from setting"));
        Valid.MyReportqry("rptkembaliberkas.jasper","report","::[ Data Pengembalian Berkas Rawat Inap ]::",
            "SELECT asembling.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,asembling.sttus_berkas,asembling.tgl_kembali,petugas.nama,asembling.ket " +
            "FROM asembling INNER JOIN kamar_inap INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas ON " +
            "asembling.no_rawat=kamar_inap.no_rawat AND reg_periksa.no_rawat=kamar_inap.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
            "AND petugas.nip=asembling.nip and kamar_inap.stts_pulang not in ('Pindah Kamar') " +
            "where asembling.tgl_kembali between '"+Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+"")+"' and asembling.no_rawat like '%"+cari1.getText()+"%' or " +
            "asembling.tgl_kembali between '"+Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+"")+"' and pasien.no_rkm_medis like '%"+cari1.getText()+"%' or " +
            "asembling.tgl_kembali between '"+Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+"")+"' and pasien.nm_pasien like '%"+cari1.getText()+"%' or " +
            "asembling.tgl_kembali between '"+Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+"")+"' and asembling.sttus_berkas like '%"+cari1.getText()+"%' " ,param);

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void DTPtanggalkembali1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPtanggalkembali1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembali1ItemStateChanged

    private void DTPtanggalkembali1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPtanggalkembali1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembali1KeyPressed

    private void DTPtanggalkembali2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPtanggalkembali2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembali2ItemStateChanged

    private void DTPtanggalkembali2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPtanggalkembali2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembali2KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilbayar();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cari1ActionPerformed

    private void cari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari1ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            cari1.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnCloseIn6.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbkembali.requestFocus();
        }
    }//GEN-LAST:event_cari1KeyPressed

    private void NoResep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoResep1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoResep1ActionPerformed

    private void KeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeteranganActionPerformed

    private void Tagihan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tagihan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tagihan1ActionPerformed

    private void MnNotaPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNotaPxActionPerformed
        if(tabmodebayar.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
        }else if(kd_nip.getText().trim().equals("")||(Nmpetugas.getText().trim().equals(""))){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan Pilih Dulu Petugas Kasir...!!!");
            btncaripetugas.requestFocus();
        }else{
            Valid.panggilUrl("billing/NotaApotek8.php?noresep="+NoResep1.getText()+"&tanggal="+Valid.SetTgl(DTPBayar.getSelectedItem()+"")+"&keterangan="+Keterangan.getText().replaceAll(" ","_")+"&petugas="+Nmpetugas.getText().replaceAll(" ","_")+"&norawat="+No_rw2.getText().replaceAll(" ","_"));
        }
    }//GEN-LAST:event_MnNotaPxActionPerformed

    private void MnrekaplaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnrekaplaporanActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPtanggalkembali1.getSelectedItem() + " s.d " + DTPtanggalkembali2.getSelectedItem());
        Valid.MyReportqry("rptPenjualan3.jasper", "report", "::[ Laporan Setoran Obat dari Kasir ]::",
            "SELECT ((SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') " +
            "-(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar')) as ttlapotek" +
            ",((SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') " +
            "-(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar')) as ttldpo " +
            ",(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='DPO' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as ttlembadpo " +
            ",(SELECT SUM(detail_pemberian_obat.embalase)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE kd_bangsal='APT' AND cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as ttlembaapt  " +
            ",(SELECT SUM(detail_pemberian_obat.total)FROM detail_pemberian_obat INNER JOIN status_resep " +
            "ON status_resep.no_resep=detail_pemberian_obat.no_resep WHERE  cara_bayar=NAMA.cara_bayar AND tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' AND status_resep.keterangan='sudah bayar') as grandtotal " +
            "from  " +
            "(SELECT * FROM detail_pemberian_obat) AS NAMA INNER JOIN status_resep " +
            "ON status_resep.no_resep=NAMA.no_resep " +
            "where NAMA.tgl_perawatan between '" + Valid.SetTgl(DTPtanggalkembali1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPtanggalkembali2.getSelectedItem() + "") + "' " +
            "AND NAMA.cara_bayar='UMUM'  " +
            "AND status_resep.keterangan='sudah bayar '", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnrekaplaporanActionPerformed

    private void MnBayarObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBayarObatActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        }else{
            DlgBayarObatUmum bayar=new DlgBayarObatUmum(null,false);
            bayar.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            bayar.setLocationRelativeTo(internalFrame1);
            bayar.setNoRm(TNoRw.getText(),TNoRM.getText(), TPasien.getText());
            bayar.setAlwaysOnTop(false);
            bayar.setVisible(true);
        }
    }//GEN-LAST:event_MnBayarObatActionPerformed

    private void jMenuLapStokOpnameDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuLapStokOpnameDepoActionPerformed
     
            Valid.panggilUrl("billing/stokopnamedepo.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        
    }//GEN-LAST:event_jMenuLapStokOpnameDepoActionPerformed

    private void ppResiObatUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppResiObatUmumActionPerformed
     if(tabModePO.getRowCount()==0){
             JOptionPane.showMessageDialog(null,"Maaf, tampilkan data dulu...!!!!");
             BtnCari.requestFocus();
        }else if(NoResep.getText().trim().equals("")){
             JOptionPane.showMessageDialog(null,"Maaf, No.Resep belu ada periksa data lagi...!!!!");            
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();          
            param.put("pasien",TPasien.getText());
            param.put("norm",TNoRM.getText());
            param.put("noresep",NoResep.getText());
            Valid.MyReport("rptResiObat.jasper",param,"::[ Resi Obat ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppResiObatUmumActionPerformed

    private void jLapDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLapDepoActionPerformed
         Valid.panggilUrl("billing/laporandepoigd.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));             
    }//GEN-LAST:event_jLapDepoActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKeyPressed

    private void TPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPoliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPoliActionPerformed

    private void jMnRekapDetailResepPasienPerCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailResepPasienPerCB1ActionPerformed
       Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliPerCB1.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Semua Depo Per Poli Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total,poliklinik.nm_poli "
            + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
            + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
            + " inner JOIN poliklinik on poliklinik.kd_poli = reg_periksa.kd_poli "
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.status_lanjut = 'Ralan' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailResepPasienPerCB1ActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCara3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCara3ActionPerformed
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
        Valid.MyReportqry("rptRekapDetailResepPerPoliPerCB2.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Inap Semua Depo Per Bangsal Per Cara Bayar ]::",
            " SELECT IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
            + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
            + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total,bangsal.nm_bangsal "
            + " FROM resep_obat INNER JOIN kamar_inap INNER JOIN detail_pemberian_obat " 
            + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = kamar_inap.no_rawat " 
            + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat " 
            + " inner JOIN reg_periksa  on kamar_inap.no_rawat  = reg_periksa.no_rawat " 
            + " INNER JOIN kamar on kamar.kd_kamar=kamar_inap.kd_kamar " 
            + " INNER JOIN bangsal on bangsal.kd_bangsal = kamar.kd_bangsal " 
            + " INNER JOIN pasien on reg_periksa.no_rkm_medis = pasien.no_rkm_medis " 
            + " INNER JOIN penjab on penjab.kd_pj = reg_periksa.kd_pj "
            + " INNER JOIN dpjp_ranap on kamar_inap.no_rawat=dpjp_ranap.no_rawat "
            + " INNER JOIN dokter on dokter.kd_dokter =dpjp_ranap.kd_dokter " 
            + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
            + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
            + " AND reg_periksa.status_lanjut = 'Ranap' "
            + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCara3ActionPerformed

    private void MnKirimWaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimWaActionPerformed
        akses.setform("DlgPemberianObat");
        DlgPasienAmbilObatKronis sep=new DlgPasienAmbilObatKronis(null,false);
        sep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        sep.setLocationRelativeTo(internalFrame1);
        sep.setVisible(true);
    }//GEN-LAST:event_MnKirimWaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemberianObat dialog = new DlgPemberianObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnObat2;
    private widget.Button BtnObat3;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnRefresLap;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan6;
    private widget.Button BtnUnit;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPBayar;
    private widget.Tanggal DTPBeri;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPtanggalkembali1;
    private widget.Tanggal DTPtanggalkembali2;
    private javax.swing.JMenuItem JMenuRalanAntibiotik;
    private javax.swing.JMenuItem JmenuRanapGenerik;
    private widget.TextBox KdGudang;
    private widget.ComboBox Ket1;
    private widget.TextBox Keterangan;
    private widget.Label LCount;
    private widget.Label Lcount1;
    private javax.swing.JMenuItem MnBayarObat;
    private javax.swing.JMenuItem MnKirimWa;
    private javax.swing.JMenuItem MnNotaPx;
    private javax.swing.JMenuItem Mnrekaplaporan;
    private widget.TextBox NmGudang;
    private widget.TextBox Nmpetugas;
    private widget.TextBox NoResep;
    private widget.TextBox NoResep1;
    private widget.TextBox No_rw2;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup1;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll;
    private widget.TextBox TBiayaObat;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TEmbalase;
    private widget.TextBox THBeli;
    private widget.TextBox TJumlah;
    private widget.TextBox TKdOb;
    private widget.TextBox TNmOb;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.TextBox TSatuan;
    private widget.TextBox TTotal;
    private widget.TextBox TTuslah;
    private widget.TabPane TabInput;
    private widget.TextBox Tagihan1;
    private widget.Tanggal Tanggal;
    private javax.swing.JDialog WindowBayarObat;
    private widget.Button btnBarang1;
    private widget.Button btnKonversi;
    private widget.Button btnObat1;
    private widget.Button btnPenjab;
    private widget.Button btncaripetugas;
    private widget.TextBox cari1;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel3;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JMenuItem jLapDepo;
    private javax.swing.JMenuItem jLapObatUmum1;
    private javax.swing.JMenuItem jLapObatUmumdetail;
    private javax.swing.JMenuItem jMenuAntibiotikAll;
    private javax.swing.JMenu jMenuLapAllRawatPerDepo;
    private javax.swing.JMenu jMenuLapDepoIGD;
    private javax.swing.JMenu jMenuLapInapAllDepo;
    private javax.swing.JMenu jMenuLapInapPerDepo;
    private javax.swing.JMenu jMenuLapJalanAllDepo;
    private javax.swing.JMenu jMenuLapJalanPerDepo;
    private javax.swing.JMenuItem jMenuLapObatPerDokter;
    private javax.swing.JMenu jMenuLapObatUmum;
    private javax.swing.JMenu jMenuLapResep;
    private javax.swing.JMenu jMenuLapResep1;
    private javax.swing.JMenu jMenuLapSemuaAllDepo;
    private javax.swing.JMenuItem jMenuLapStokOpnameDepo;
    private javax.swing.JMenuItem jMenuRalanGenerik;
    private javax.swing.JMenuItem jMenuRanapAntibiotik;
    private javax.swing.JMenuItem jMnRekapDetailResepPasienPerCB;
    private javax.swing.JMenuItem jMnRekapDetailResepPasienPerCB1;
    private javax.swing.JMenuItem jMnRekapDetailResepPasienSemuaCB;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara3;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara2;
    private javax.swing.JMenuItem jMnRekapDetailTotalResepPasienPerCB1;
    private javax.swing.JMenuItem jMnRekapDetailTotalResepPasienSemuaCB1;
    private javax.swing.JMenuItem jMnRekapResepRalan;
    private javax.swing.JMenuItem jMnRekapResepRanap;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara2;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienPerCB;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienPerCB1;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienSemuaCB;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienSemuaCB1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kd_nip;
    private widget.TextBox kdpj;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.Label label19;
    private widget.TextBox lokasi1;
    private widget.TextBox nmpnj;
    private widget.panelGlass panelGlass3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppLokasi;
    private javax.swing.JMenuItem ppNoRawat;
    private javax.swing.JMenuItem ppResepObat;
    private javax.swing.JMenuItem ppResiObatUmum;
    private widget.ScrollPane scrollPane6;
    private widget.TextBox status1;
    private widget.Table tbPemberianObat;
    private widget.Table tbkembali;
    // End of variables declaration//GEN-END:variables

    public void tampilPO() {
        pas="";
        if(!TCariPasien.getText().equals("")){
           pas=" and reg_periksa.no_rkm_medis='"+TCariPasien.getText()+"' "; 
        }
        tgl=" detail_pemberian_obat.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;        
        sql="select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"+
                   "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"+
                   "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.h_beli,detail_pemberian_obat.kd_bangsal, "+
                   "detail_pemberian_obat.no_batch,detail_pemberian_obat.no_faktur,detail_pemberian_obat.no_resep,detail_pemberian_obat.cara_bayar from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "+
                   "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and detail_pemberian_obat.kode_brng=databarang.kode_brng "+
                   "where "+tgl+" and tgl_perawatan like ? or "+
                   tgl+"and detail_pemberian_obat.no_rawat like ? or "+
                   tgl+"and reg_periksa.no_rkm_medis like ? or "+
                   tgl+"and pasien.nm_pasien like ? or "+
                   tgl+"and detail_pemberian_obat.kode_brng like ? or "+
                   tgl+"and databarang.nama_brng like ? or "+
                   tgl+"and detail_pemberian_obat.no_resep like ? or "+
                   tgl+"and detail_pemberian_obat.cara_bayar like ? "+
                   "order by detail_pemberian_obat.tgl_perawatan";
        
        Valid.tabelKosong(tabModePO);
        try{
            ps=koneksi.prepareStatement(sql);
            try {
                ps.setString(1,"%"+TCari.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                ps.setString(5,"%"+TCari.getText().trim()+"%");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,"%"+TCari.getText().trim()+"%");
                ps.setString(8,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModePO.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getDouble(8),rs.getDouble(9),
                        rs.getDouble(10),rs.getDouble(11),rs.getDouble(12),
                        rs.getDouble(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18)
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
        LCount.setText(""+tabModePO.getRowCount());
    }
    
    public void tampilPO2() {
        pas="";
        if(!TCariPasien.getText().equals("")){
           pas=" and reg_periksa.no_rkm_medis='"+TCariPasien.getText()+"' "; 
        }
        tgl=" detail_pemberian_obat.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and detail_pemberian_obat.kd_bangsal='"+lokasi+"' "+pas;        
        sql="select detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,"+
                   "detail_pemberian_obat.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                   "detail_pemberian_obat.kode_brng,databarang.nama_brng,detail_pemberian_obat.embalase,detail_pemberian_obat.tuslah,"+
                   "detail_pemberian_obat.jml,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.h_beli,detail_pemberian_obat.kd_bangsal, "+
                   "detail_pemberian_obat.no_batch,detail_pemberian_obat.no_faktur,detail_pemberian_obat.no_resep,detail_pemberian_obat.cara_bayar from detail_pemberian_obat inner join reg_periksa inner join pasien inner join databarang "+
                   "on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and detail_pemberian_obat.kode_brng=databarang.kode_brng "+
                   "where "+tgl+" and tgl_perawatan like ? or "+
                   tgl+"and detail_pemberian_obat.no_rawat like ? or "+
                   tgl+"and reg_periksa.no_rkm_medis like ? or "+
                   tgl+"and pasien.nm_pasien like ? or "+
                   tgl+"and detail_pemberian_obat.kode_brng like ? or "+
                   tgl+"and databarang.nama_brng like ? or "+
                   tgl+"and detail_pemberian_obat.no_resep like ? or "+
                   tgl+"and detail_pemberian_obat.cara_bayar like ? "+
                   "order by detail_pemberian_obat.tgl_perawatan";
        
        Valid.tabelKosong(tabModePO);
        try{
            ps=koneksi.prepareStatement(sql);
            try {
                ps.setString(1,"%"+TCari.getText().trim()+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                ps.setString(5,"%"+TCari.getText().trim()+"%");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,"%"+TCari.getText().trim()+"%");
                ps.setString(8,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModePO.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getDouble(8),rs.getDouble(9),
                        rs.getDouble(10),rs.getDouble(11),rs.getDouble(12),
                        rs.getDouble(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18)
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
        LCount.setText(""+tabModePO.getRowCount());
    }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ",TNoRM,TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

    

    private void getData() {
        if(tbPemberianObat.getSelectedRow()!= -1){
            cmbJam.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString().substring(6,8));
            TNoRw.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString());
            isRawat();
            isPsien();
            TKdOb.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString());
            TNmOb.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),6).toString());
            TEmbalase.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),7).toString());
            TTuslah.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),8).toString());
            TJumlah.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString());
            TBiayaObat.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),10).toString());
            THBeli.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),12).toString());
            Valid.SetTgl(DTPBeri,tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString());
            Ket1.setSelectedItem(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),17).toString());
            NoResep.setText(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),16).toString());
            isjml();
        }
    }
    
    private void tampilbayar() {
        Valid.tabelKosong(tabmodebayar);
        try{
         psobat=koneksi.prepareStatement("SELECT status_resep.no_resep,status_resep.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,status_resep.tgl_resep,status_resep.status,penjab.png_jawab,status_resep.petugas,status_resep.lokasi,status_resep.keterangan,status_resep.ket1 " +
                                        "FROM status_resep INNER JOIN reg_periksa on reg_periksa.no_rawat=status_resep.no_rawat " +
                                        "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                                        "INNER JOIN penjab ON penjab.kd_pj=status_resep.kd_pj " +
                                        "WHERE status_resep.tgl_resep BETWEEN ? and ? and status_resep.no_resep like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.no_rawat like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and reg_periksa.no_rkm_medis like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and pasien.nm_pasien like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and penjab.png_jawab like ? or  "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.lokasi like ? or "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.status like ? or "+
                                        "status_resep.tgl_resep BETWEEN ? and ? and status_resep.keterangan like ? ORDER BY status_resep.no_resep DESC ");
                                        
            try { 
                psobat.setString(1,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(2,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(3,"%"+cari1.getText()+"%");
                psobat.setString(4,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(5,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(6,"%"+cari1.getText()+"%");
                psobat.setString(7,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(8,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(9,"%"+cari1.getText()+"%");
                psobat.setString(10,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(11,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(12,"%"+cari1.getText()+"%");
                psobat.setString(13,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(14,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(15,"%"+cari1.getText()+"%");
                psobat.setString(16,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(17,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(18,"%"+cari1.getText()+"%");
                psobat.setString(19,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(20,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(21,"%"+cari1.getText()+"%");
                psobat.setString(22,Valid.SetTgl(DTPtanggalkembali1.getSelectedItem()+""));
                psobat.setString(23,Valid.SetTgl(DTPtanggalkembali2.getSelectedItem()+""));
                psobat.setString(24,"%"+cari1.getText()+"%");
              
                rsobat=psobat.executeQuery();
                while(rsobat.next()){
                    tabmodebayar.addRow(new Object[]{
                                   rsobat.getString(1),
                                   rsobat.getString(2),
                                   rsobat.getString(3),
                                   rsobat.getString(4),
                                   rsobat.getString(5),
                                   rsobat.getString(6),
                                   rsobat.getString(7),
                                   rsobat.getString(8),
                                   rsobat.getString(9),
                                   rsobat.getString(10),
                                   rsobat.getString(11)});
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rsobat!=null){
                    rsobat.close();
                }
                if(psobat!=null){
                    psobat.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        Lcount1.setText(""+tabmodebayar.getRowCount());
    }
    
    
     private void getbayar() {
        if(tbkembali.getSelectedRow()!= -1){
            NoResep1.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),0).toString());
            No_rw2.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),1).toString());
            Sequel.cariIsi("SELECT kd_pj FROM status_resep WHERE no_resep=?",kdpj,NoResep1.getText());
            status1.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),5).toString());
            lokasi1.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),8).toString());
            Sequel.cariIsi("SELECT nama FROM petugas WHERE nip=?",Nmpetugas,kd_nip.getText());
            kd_nip.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(),7).toString());
            Keterangan.setText(tbkembali.getValueAt(tbkembali.getSelectedRow(), 10).toString());
            Valid.SetTgl(DTPBayar,tbkembali.getValueAt(tbkembali.getSelectedRow(),4).toString());
            Sequel.cariIsi("SELECT SUM( total) FROM detail_pemberian_obat WHERE cara_bayar='UMUM' AND no_rawat='"+No_rw2.getText()+"' AND tgl_perawatan='"+Valid.SetTgl(DTPBayar.getSelectedItem() + "") +"' AND no_resep=?",Tagihan1,NoResep1.getText());
                 
        }
    }
     public void emptyteks(){
         NoResep1.setText("");
          kd_nip.setText(akses.getkode());
          Sequel.cariIsi("select nama from petugas where nip=?", Nmpetugas,kd_nip.getText());
          Keterangan.setText("");
          Tagihan1.setText("");
     }
            
    
    public void setNoRm(String norwt, Date tgl1, Date tgl2,String statuspasien) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        isPsien();   
        status=statuspasien;
        if(statuspasien.equals("ranap")){
            statussimpan="Ranap";
            BtnObat2.setEnabled(true);
            BtnObat3.setEnabled(false);
            if(Sequel.cariInteger("select count(no_rawat) from stok_obat_pasien where no_rawat=? ",norwt)>0){
                btnObat1.setEnabled(false);
            }else{
                btnObat1.setEnabled(true);
            }
        }else if(statuspasien.equals("ralan")){
            statussimpan="Ralan";
            btnObat1.setEnabled(true);
            BtnObat2.setEnabled(false);
            BtnObat3.setEnabled(true);
        }
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(true);
        BtnBatalActionPerformed(null);
        isForm();
    }
    
    public void setNoRm2(String norwt, Date tgl1, Date tgl2,String statuspasien) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        isPsien();   
        status=statuspasien;
        if(statuspasien.equals("ranap")){
            BtnObat2.setEnabled(true);
            BtnObat3.setEnabled(false);
            if(Sequel.cariInteger("select count(no_rawat) from stok_obat_pasien where no_rawat=? ",norwt)>0){
                btnObat1.setEnabled(false);
            }else{
                btnObat1.setEnabled(true);
            }
        }else if(statuspasien.equals("ralan")){
            btnObat1.setEnabled(true);
            BtnObat2.setEnabled(false);
            BtnObat3.setEnabled(true);
        }
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(false);
        isForm();
    }
    
    public void setNoRm3(String norwt, Date tgl1, Date tgl2,String statuspasien) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        isPsien();   
        status=statuspasien;
        if(statuspasien.equals("ranap")){
            BtnObat2.setEnabled(true);
            BtnObat3.setEnabled(false);
            if(Sequel.cariInteger("select count(stok_obat_pasien.no_rawat) from stok_obat_pasien where stok_obat_pasien.no_rawat=? ",norwt)>0){
                btnObat1.setEnabled(false);
            }else{
                btnObat1.setEnabled(true);
            }
        }else if(statuspasien.equals("ralan")){
            btnObat1.setEnabled(true);
            BtnObat2.setEnabled(false);
            BtnObat3.setEnabled(true);
        }
        ChkJln.setSelected(false);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,192));
            TabInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            TabInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        //BtnSimpan.setEnabled(akses.getberi_obat());
        BtnHapus.setEnabled(akses.getberi_obat());
        BtnEdit.setEnabled(akses.getberi_obat());
        BtnPrint.setEnabled(akses.getberi_obat());
        ppResepObat.setEnabled(akses.getresep_obat());
        
    }
    
    private void isjml(){        
        if((!TBiayaObat.getText().equals(""))&&(!TEmbalase.getText().equals(""))&&(!TTuslah.getText().equals(""))&&(!TJumlah.getText().equals(""))){
            TTotal.setText(Double.toString((Double.parseDouble(TBiayaObat.getText())*Double.parseDouble(TJumlah.getText()))+Double.parseDouble(TEmbalase.getText())+Double.parseDouble(TTuslah.getText())));        
        }
        
    }
    
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =cmbJam.getSelectedIndex();
                    nilai_menit =cmbMnt.getSelectedIndex();
                    nilai_detik =cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    public void setDokter(String kodedokter,String namadokter){
        this.kodedokter=kodedokter;
        this.namadokter=namadokter;
    }
    
    public  void bayar(){
            WindowBayarObat.setLocationRelativeTo(internalFrame1);
            WindowBayarObat.setVisible(true);
            NoResep.setText("");
            kd_nip.setText("");
            Nmpetugas.setText("");
            cari1.setText(TNoRM.getText());
            BtnCari1ActionPerformed(null);
            emptyteks();
    }
  private void hapus() {
         statusberi=Sequel.cariIsi("select status from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
              "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
              "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
              "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"' "+
              "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
              "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ");
        ttlhpp=Sequel.cariIsiAngka("select sum(h_beli*jml) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
              "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
              "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
              "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"' "+
              "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
              "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ");
        ttljual=Sequel.cariIsiAngka("select sum(total) from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
              "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
              "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
              "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"' "+
              "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
              "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ");
        if(Sequel.queryutf("delete from detail_pemberian_obat where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
              "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
              "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
              "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"' "+
              "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
              "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"' ")==true){
            if(statusberi.equals("Ranap")){
                Sequel.queryu("delete from tampjurnal");    
                if(ttljual>0){
                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ranap+"','Suspen Piutang Obat Ranap','0','"+ttljual+"'","Rekening");    
                    Sequel.menyimpan("tampjurnal","'"+Obat_Ranap+"','Pendapatan Obat Rawat Inap','"+ttljual+"','0'","Rekening");                              
                }
                if(ttlhpp>0){
                    Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Inap+"','HPP Persediaan Obat Rawat Inap','0','"+ttlhpp+"'","Rekening");    
                    Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Inap+"','Persediaan Obat Rawat Inap','"+ttlhpp+"','0'","Rekening");                              
                }
                sukses=jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString(),"U","PEMBATALAN PEMBERIAN OBAT RAWAT INAP PASIEN, OLEH "+akses.getkode());                                                
            }else if(statusberi.equals("Ralan")){
                Sequel.queryu("delete from tampjurnal");    
                if(ttljual>0){
                    Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Obat_Ralan+"','Suspen Piutang Obat Ralan','0','"+ttljual+"'","Rekening");    
                    Sequel.menyimpan("tampjurnal","'"+Obat_Ralan+"','Pendapatan Obat Rawat Jalan','"+ttljual+"','0'","Rekening");                              
                }
                if(ttlhpp>0){
                    Sequel.menyimpan("tampjurnal","'"+HPP_Obat_Rawat_Jalan+"','HPP Persediaan Obat Rawat Jalan','0','"+ttlhpp+"'","Rekening");    
                    Sequel.menyimpan("tampjurnal","'"+Persediaan_Obat_Rawat_Jalan+"','Persediaan Obat Rawat Jalan','"+ttlhpp+"','0'","Rekening");                              
                }
                sukses=jur.simpanJurnal(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString(),"U","PEMBATALAN PEMBERIAN OBAT RAWAT JALAN PASIEN, OLEH "+akses.getkode());                                                
            }

            Sequel.queryu("delete from aturan_pakai where no_rawat='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+"' "+
                          "and kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' "+
                          "and tgl_perawatan='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),0).toString()+"' "+
                          "and jam='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),1).toString()+"'");
            if(btnObat1.isEnabled()==true){
                if(aktifkanbatch.equals("yes")){
                    Sequel.mengedit("data_batch","no_batch=? and kode_brng=? and no_faktur=?","sisa=sisa+?",4,new String[]{
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString(),
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()
                    });
                    Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                        Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                        0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus",
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString(),
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString(), 
                        tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                    );
                    Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                        "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"',"+
                                        "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"',"+
                                        "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'", 
                                        "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                        "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                        "and no_batch='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),14).toString()+"' "+
                                        "and no_faktur='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),15).toString()+"'");   
                }else{
                    Trackobat.catatRiwayat(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString(),
                        Valid.SetAngka(tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()),
                        0,"Pemberian Obat",akses.getkode(),bangsal,"Hapus","","",
                         tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),2).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),3).toString()+" "+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),4).toString()
                    );
                    Sequel.menyimpan("gudangbarang","'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"','"+bangsal+"',"+
                                        "'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"','',''", 
                                        "stok=stok+'"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),9).toString()+"'",
                                        "kode_brng='"+tbPemberianObat.getValueAt(tbPemberianObat.getSelectedRow(),5).toString()+"' and kd_bangsal='"+bangsal+"'  "+
                                        "and no_batch='' and no_faktur=''");   
                }
            }
        }else{
            sukses=false;
        }
    }
    

}

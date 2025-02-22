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
package modifikasi;

import simrskhanza.*;
import rekammedis.RMRiwayatPerawatan;
import bridging.BPJSCekNIK;
import bridging.BPJSCekNoKartu;
import bridging.BPJSNik;
import bridging.BPJSPeserta;
import bridging.CoronaPasien;
import bridging.DUKCAPILCekNIK;
import bridging.DUKCAPILJakartaCekNik;
import bridging.PCareNIK;
import bridging.PCarePeserta;
import bridging.YaskiReferensiKabupaten;
import bridging.YaskiReferensiKecamatan;
import bridging.YaskiReferensiKelurahan;
import bridging.YaskiReferensiPropinsi;
import fungsi.WarnaTable;
import fungsi.batasInput;
import grafikanalisa.grafikjkel;
import grafikanalisa.grafikpasienperagama;
import grafikanalisa.grafikpasienperpekerjaaan;
import grafikanalisa.grafiksql;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


/**
 *
 * @author igos
 */
public class DlgPasienPRB extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    public  DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    public  DlgKabupaten kab=new DlgKabupaten(null,false);
    public  DlgPropinsi prop=new DlgPropinsi(null,false);
    public  DlgKecamatan kec=new DlgKecamatan(null,false);
    public  DlgKelurahan kel=new DlgKelurahan(null,false);
    public  DlgCariPerusahaan perusahaan=new DlgCariPerusahaan(null,false);
    public  DlgCariBahasa bahasa=new DlgCariBahasa(null,false);
    public  DlgCariCacatFisik cacat=new DlgCariCacatFisik(null,false);
    public  DlgCariSuku suku=new DlgCariSuku(null,false);
    public  DlgGolonganTNI golongantni=new DlgGolonganTNI(null,false);
    public  DlgSatuanTNI satuantni=new DlgSatuanTNI(null,false);
    public  DlgPangkatTNI pangkattni=new DlgPangkatTNI(null,false);
    public  DlgJabatanTNI jabatantni=new DlgJabatanTNI(null,false);
    public  DlgGolonganPolri golonganpolri=new DlgGolonganPolri(null,false);
    public  DlgSatuanPolri satuanpolri=new DlgSatuanPolri(null,false);
    public  DlgPangkatPolri pangkatpolri=new DlgPangkatPolri(null,false);
    public  DlgJabatanPolri jabatanpolri=new DlgJabatanPolri(null,false);
    private YaskiReferensiPropinsi propinsiref=new YaskiReferensiPropinsi(null,false);
    private YaskiReferensiKabupaten kabupatenref=new YaskiReferensiKabupaten(null,false);
    private YaskiReferensiKecamatan kecamatanref=new YaskiReferensiKecamatan(null,false);
    private YaskiReferensiKelurahan kelurahanref=new YaskiReferensiKelurahan(null,false);
    private int pilih=0,z=0,j=0,p_no_ktp=0,p_tmp_lahir=0,p_nm_ibu=0,p_alamat=0,
            p_pekerjaan=0,p_no_tlp=0,p_umur=0,p_namakeluarga=0,p_no_peserta=0,
            p_kelurahan=0,p_kecamatan=0,p_kabupaten=0,p_pekerjaanpj=0,
            p_alamatpj=0,p_kelurahanpj=0,p_kecamatanpj=0,p_kabupatenpj=0,
            p_propinsi=0,p_propinsipj=0;
    private double x=0,i=0;
    private String kdkel="",kdkec="",kdkab="",kdprop="",klg="SAUDARA",pengurutan="",asalform="",bulan="",tahun="",awalantahun="",awalanbulan="",posisitahun="",
            no_ktp="",tmp_lahir="",nm_ibu="",alamat="",pekerjaan="",no_tlp="",
            umur="",namakeluarga="",no_peserta="",kelurahan="",kecamatan="",
            kabupaten="",pekerjaanpj="",alamatpj="",kelurahanpj="",kecamatanpj="",
            kabupatenpj="",propinsi="",propinsipj="",tampilkantni=Sequel.cariIsi("select set_tni_polri.tampilkan_tni_polri from set_tni_polri");
    private PreparedStatement ps,pscariwilayah,pssetalamat,pskelengkapan;
    private ResultSet rs;
    private BPJSCekNIK cekViaBPJS=new BPJSCekNIK();
    private DUKCAPILJakartaCekNik cekViaDukcapilJakarta=new DUKCAPILJakartaCekNik();
    private DUKCAPILCekNIK cekViaDukcapilAceh=new DUKCAPILCekNIK();
    private BPJSCekNoKartu cekViaBPJSKartu=new BPJSCekNoKartu();
    private Date lahir;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
    private Period p;

    /** Creates new form DlgPas
     * @param parent
     * @param modal*/
    public DlgPasienPRB(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
              "P","No.R.M","Nama Pasien","No.SIM/KTP","J.K.","Tmp.Lahir","Tgl.Lahir","Nama Ibu","Alamat",
              "G.D.","Pekerjaan","Stts.Nikah","Agama","Tgl.Daftar","No.Telp/HP","Umur","Pendidikan",
              "Keluarga","Nama Keluarga","Asuransi/Askes","No.Peserta","Daftar","Pekerjaan P.J.","Alamat P.J.",
              "ID Suku","Suku/Bangsa","ID Bahasa","Bahasa","ID Peru","Instansi/Perusahaan","NIP/NRP","Email",
              "Id Cacat","Cacat Fisik","kd_pj","alamat","nm_kel","nm_kec","nm_kab","nm_prop","alamatpj","kelurahanpj",
              "kecamatanpj","kabupatenpj","propinsipj"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPasien.setModel(tabMode);

        //tbPetugas.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPetugas.getBackground()));
        tbPasien.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (z = 0; z < 45; z++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(z);
            if(z==0){
                column.setPreferredWidth(20);
            }else if(z==1){
                column.setPreferredWidth(85);
            }else if(z==2){
                column.setPreferredWidth(190);
            }else if(z==3){
                column.setPreferredWidth(100);
            }else if(z==4){
                column.setPreferredWidth(25);
            }else if(z==5){
                column.setPreferredWidth(100);
            }else if(z==6){
                column.setPreferredWidth(70);
            }else if(z==7){
                column.setPreferredWidth(150);
            }else if(z==8){
                column.setPreferredWidth(190);
            }else if(z==9){
                column.setPreferredWidth(30);
            }else if(z==10){
                column.setPreferredWidth(100);
            }else if(z==11){
                column.setPreferredWidth(75);
            }else if(z==12){
                column.setPreferredWidth(75);
            }else if(z==13){
                column.setPreferredWidth(75);
            }else if(z==14){
                column.setPreferredWidth(80);
            }else if(z==15){
                column.setPreferredWidth(90);
            }else if(z==16){
                column.setPreferredWidth(80);
            }else if(z==17){
                column.setPreferredWidth(80);
            }else if(z==18){
                column.setPreferredWidth(150);
            }else if(z==19){
                column.setPreferredWidth(120);
            }else if(z==20){
                column.setPreferredWidth(100);
            }else if(z==21){
                column.setPreferredWidth(60);
            }else if(z==22){
                column.setPreferredWidth(85);
            }else if(z==23){
                column.setPreferredWidth(160);
            }else if(z==25){
                column.setPreferredWidth(100);
            }else if(z==27){
                column.setPreferredWidth(100);
            }else if(z==29){
                column.setPreferredWidth(140);
            }else if(z==30){
                column.setPreferredWidth(90);
            }else if(z==31){
                column.setPreferredWidth(120);
            }else if(z==33){
                column.setPreferredWidth(120);
            }else if((z==24)||(z==26)||(z==28)||(z==32)||(z==34)||(z==35)||(z==36)||(z==37)||(z==38)||(z==39)||(z==40)||(z==41)||(z==42)||(z==43)||(z==44)){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());


        
      
        
        TNo.setDocument(new batasInput((byte)15).getKata(TNo));
        TNm.setDocument(new batasInput((byte)40).getKata(TNm));
        NmIbu.setDocument(new batasInput((byte)40).getKata(NmIbu));
        TKtp.setDocument(new batasInput((byte)20).getKata(TKtp));
        Kdpnj.setDocument(new batasInput((byte)3).getKata(Kdpnj));
        TTlp.setDocument(new batasInput((int)40).getKata(TTlp));
        TTmp.setDocument(new batasInput((byte)15).getKata(TTmp));
        Alamat.setDocument(new batasInput((int)200).getFilter(Alamat));
        Pekerjaan.setDocument(new batasInput((byte)15).getKata(Pekerjaan));
        TUmurTh.setDocument(new batasInput((byte)5).getOnlyAngka(TUmurTh));
        Kabupaten.setDocument(new batasInput((byte)60).getFilter(Kabupaten));
        Kecamatan.setDocument(new batasInput((byte)60).getFilter(Kecamatan));
        Kelurahan.setDocument(new batasInput((byte)60).getFilter(Kelurahan));
        Kabupaten2.setDocument(new batasInput((byte)60).getFilter(Kabupaten2));
        Kecamatan2.setDocument(new batasInput((byte)60).getFilter(Kecamatan2));
        Kelurahan2.setDocument(new batasInput((byte)60).getFilter(Kelurahan2));
        Propinsi.setDocument(new batasInput((byte)30).getFilter(Propinsi));
        EMail.setDocument(new batasInput((byte)50).getKata(EMail));
        TNoPeserta.setDocument(new batasInput((byte)25).getKata(TNoPeserta));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        pilihantampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        pilihantampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        pilihantampil();
                    }
                }
            });
        } 
        
        pengurutan=Sequel.cariIsi("select urutan from set_urut_no_rkm_medis");
        tahun=Sequel.cariIsi("select tahun from set_urut_no_rkm_medis");
        bulan=Sequel.cariIsi("select bulan from set_urut_no_rkm_medis");
        posisitahun=Sequel.cariIsi("select posisi_tahun_bulan from set_urut_no_rkm_medis");
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(penjab.getTable().getSelectedRow()!= -1){
                        Kdpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),1).toString());
                        nmpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),2).toString());

                    }  
                    Kdpnj.requestFocus();
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
        
        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        penjab.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
       
        
        prop.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(prop.getTable().getSelectedRow()!= -1){
                        switch (pilih) {
                            case 1:
                                Propinsi.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(),0).toString());
                                kdprop=prop.getTable().getValueAt(prop.getTable().getSelectedRow(),1).toString();
                                Propinsi.requestFocus();
                                break;
                            case 2:
                                Propinsi2.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(),0).toString());
                                Propinsi2.requestFocus();
                                break;
                            default:
                                break;
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
        
        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(kab.getTable().getSelectedRow()!= -1){
                        switch (pilih) {
                            case 1:
                                Kabupaten.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(),0).toString());
                                kdkab=kab.getTable().getValueAt(kab.getTable().getSelectedRow(),1).toString();
                                Kabupaten.requestFocus();
                                break;
                            case 2:
                                Kabupaten2.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(),0).toString());
                                Kabupaten2.requestFocus();
                                break;
                            default:
                                break;
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
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(kec.getTable().getSelectedRow()!= -1){
                        switch (pilih) {
                            case 1:
                                Kecamatan.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(),0).toString());
                                kdkec=kec.getTable().getValueAt(kec.getTable().getSelectedRow(),1).toString();
                                Kecamatan.requestFocus();
                                break;
                            case 2:
                                Kecamatan2.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(),0).toString());
                                Kecamatan2.requestFocus();
                                break;
                            default:
                                break;
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
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPasien")){
                    if(kel.getTable().getSelectedRow()!= -1){
                        switch (pilih) {
                            case 1:
                                Kelurahan.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(),0).toString());
                                kdkel=kel.getTable().getValueAt(kel.getTable().getSelectedRow(),1).toString();
                                Kelurahan.requestFocus();
                                break;
                            case 2:
                                Kelurahan2.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(),0).toString());
                                Kelurahan2.requestFocus();
                                break;
                            default:
                                break;
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
        
        
        propinsiref.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(propinsiref.getTable().getSelectedRow()!= -1){   
                    KdProp.setText(propinsiref.getTable().getValueAt(propinsiref.getTable().getSelectedRow(),1).toString());
                    kdprop="";
                    switch (pilih) {
                        case 1:
                            Propinsi.setText(propinsiref.getTable().getValueAt(propinsiref.getTable().getSelectedRow(),2).toString().toUpperCase());
                            break;
                        default:
                            break;
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
        
        propinsiref.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    propinsiref.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kabupatenref.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kabupatenref.getTable().getSelectedRow()!= -1){   
                    KdKab.setText(kabupatenref.getTable().getValueAt(kabupatenref.getTable().getSelectedRow(),1).toString());
                    kdkab="";
                    switch (pilih) {
                        case 1:
                            Kabupaten.setText(kabupatenref.getTable().getValueAt(kabupatenref.getTable().getSelectedRow(),2).toString().toUpperCase());
                            break;
                        default:
                            break;
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
        
        kabupatenref.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kabupatenref.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kecamatanref.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kecamatanref.getTable().getSelectedRow()!= -1){   
                    KdKec.setText(kecamatanref.getTable().getValueAt(kecamatanref.getTable().getSelectedRow(),1).toString());
                    kdkec="";
                    switch (pilih) {
                        case 1:
                            Kecamatan.setText(kecamatanref.getTable().getValueAt(kecamatanref.getTable().getSelectedRow(),2).toString().toUpperCase());
                            break;
                        default:
                            break;
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
        
        kecamatanref.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kecamatanref.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kelurahanref.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kelurahanref.getTable().getSelectedRow()!= -1){    
                    KdKel.setText(kelurahanref.getTable().getValueAt(kelurahanref.getTable().getSelectedRow(),1).toString());
                    kdkel="";
                    switch (pilih) {
                        case 1:
                            Kelurahan.setText(kelurahanref.getTable().getValueAt(kelurahanref.getTable().getSelectedRow(),2).toString().toUpperCase());
                            break;
                        default:
                            break;
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
        
        kelurahanref.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kelurahanref.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        try {
            pssetalamat=koneksi.prepareStatement("select * from set_alamat_pasien");
            try {
                rs=pssetalamat.executeQuery();
                while(rs.next()){
                    Kelurahan.setEditable(rs.getBoolean("kelurahan"));
                    Kecamatan.setEditable(rs.getBoolean("kecamatan"));                    
                    Kabupaten.setEditable(rs.getBoolean("kabupaten"));                   
                    Propinsi.setEditable(rs.getBoolean("propinsi"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pssetalamat!=null){
                    pssetalamat.close();
                }
            }
            
            pskelengkapan=koneksi.prepareStatement("select * from set_kelengkapan_data_pasien");
            try {
                rs=pskelengkapan.executeQuery();
                while(rs.next()){
                    no_ktp=rs.getString("no_ktp");
                    p_no_ktp=rs.getInt("p_no_ktp");
                    tmp_lahir=rs.getString("tmp_lahir");
                    p_tmp_lahir=rs.getInt("p_tmp_lahir");
                    nm_ibu=rs.getString("nm_ibu");
                    p_nm_ibu=rs.getInt("p_nm_ibu");
                    alamat=rs.getString("alamat");
                    p_alamat=rs.getInt("p_alamat");
                    pekerjaan=rs.getString("pekerjaan");
                    p_pekerjaan=rs.getInt("p_pekerjaan");
                    no_tlp=rs.getString("no_tlp");
                    p_no_tlp=rs.getInt("p_no_tlp");
                    umur=rs.getString("umur");
                    p_umur=rs.getInt("p_umur");
                    namakeluarga=rs.getString("namakeluarga");
                    p_namakeluarga=rs.getInt("p_namakeluarga");
                    no_peserta=rs.getString("no_peserta");
                    p_no_peserta=rs.getInt("p_no_peserta");
                    kelurahan=rs.getString("kelurahan");
                    p_kelurahan=rs.getInt("p_kelurahan");
                    kecamatan=rs.getString("kecamatan");
                    p_kecamatan=rs.getInt("p_kecamatan");
                    kabupaten=rs.getString("kabupaten");
                    p_kabupaten=rs.getInt("p_kabupaten");
                    pekerjaanpj=rs.getString("pekerjaanpj");
                    p_pekerjaanpj=rs.getInt("p_pekerjaanpj");
                    alamatpj=rs.getString("alamatpj");
                    p_alamatpj=rs.getInt("p_alamatpj");
                    kelurahanpj=rs.getString("kelurahanpj");
                    p_kelurahanpj=rs.getInt("p_kelurahanpj");
                    kecamatanpj=rs.getString("kecamatanpj");
                    p_kecamatanpj=rs.getInt("p_kecamatanpj");
                    kabupatenpj=rs.getString("kabupatenpj");
                    p_kabupatenpj=rs.getInt("p_kabupatenpj");
                    propinsi=rs.getString("propinsi");
                    p_propinsi=rs.getInt("p_propinsi");
                    propinsipj=rs.getString("propinsipj");
                    p_propinsipj=rs.getInt("p_propinsipj");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pskelengkapan!=null){
                    pskelengkapan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        } 
        
    }    
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        KartuPasien = new javax.swing.JMenu();
        MnKartu1 = new javax.swing.JMenuItem();
        MnKartu2 = new javax.swing.JMenuItem();
        MnKartu3 = new javax.swing.JMenuItem();
        MnKartu4 = new javax.swing.JMenuItem();
        MnKartu5 = new javax.swing.JMenuItem();
        MnKartu6 = new javax.swing.JMenuItem();
        Barcode = new javax.swing.JMenu();
        MnBarcodeRM = new javax.swing.JMenuItem();
        MnBarcodeRM1 = new javax.swing.JMenuItem();
        MnBarcodeRM2 = new javax.swing.JMenuItem();
        MnBarcodeRM3 = new javax.swing.JMenuItem();
        MnBarcodeRM4 = new javax.swing.JMenuItem();
        MnBarcodeRM5 = new javax.swing.JMenuItem();
        MnBarcodeRM6 = new javax.swing.JMenuItem();
        MnBarcodeRM7 = new javax.swing.JMenuItem();
        MnBarcodeRM8 = new javax.swing.JMenuItem();
        MnBarcodeRM9 = new javax.swing.JMenuItem();
        MenuIdentitas = new javax.swing.JMenu();
        MnIdentitas = new javax.swing.JMenuItem();
        MnIdentitas2 = new javax.swing.JMenuItem();
        MnIdentitas3 = new javax.swing.JMenuItem();
        MnIdentitas4 = new javax.swing.JMenuItem();
        MnKartuStatus = new javax.swing.JMenuItem();
        MenuBPJS = new javax.swing.JMenu();
        MnCekKepesertaan = new javax.swing.JMenuItem();
        MnCekNIK = new javax.swing.JMenuItem();
        MnCekKepesertaan1 = new javax.swing.JMenuItem();
        MnCekNIK1 = new javax.swing.JMenuItem();
        ppKelahiranBayi = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MnLaporanRM = new javax.swing.JMenuItem();
        MnLaporanRM1 = new javax.swing.JMenuItem();
        MnLaporanRM2 = new javax.swing.JMenuItem();
        MnLaporanRM3 = new javax.swing.JMenuItem();
        MnFormulirPendaftaran = new javax.swing.JMenuItem();
        MnSCreening = new javax.swing.JMenuItem();
        MnCopyResep = new javax.swing.JMenuItem();
        MnLembarKeluarMasuk = new javax.swing.JMenuItem();
        MnLembarKeluarMasuk2 = new javax.swing.JMenuItem();
        MnLaporanIGD = new javax.swing.JMenuItem();
        MnLembarAnamNesa = new javax.swing.JMenuItem();
        MnLembarGrafik = new javax.swing.JMenuItem();
        MnLembarCatatanPerkembangan = new javax.swing.JMenuItem();
        MnLembarCatatanKeperawatan = new javax.swing.JMenuItem();
        MnLaporanAnestesia = new javax.swing.JMenuItem();
        MnPengantarHemodalisa = new javax.swing.JMenuItem();
        MnCover = new javax.swing.JMenuItem();
        MnCover1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikPerAgama = new javax.swing.JMenuItem();
        ppGrafikPerPekerjaan = new javax.swing.JMenuItem();
        ppGrafikjkbayi = new javax.swing.JMenuItem();
        ppGrafikDemografi = new javax.swing.JMenuItem();
        ppRegistrasi = new javax.swing.JMenuItem();
        ppRegistrasi1 = new javax.swing.JMenuItem();
        ppRegistrasi2 = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        ppGabungRM = new javax.swing.JMenuItem();
        ppPasienCorona = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        Kd2 = new widget.TextBox();
        DlgDemografi = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        BtnPrint2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        Kelurahan2 = new widget.TextBox();
        BtnSeek8 = new widget.Button();
        Kecamatan2 = new widget.TextBox();
        BtnSeek9 = new widget.Button();
        Kabupaten2 = new widget.TextBox();
        BtnSeek10 = new widget.Button();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        BtnPrint3 = new widget.Button();
        BtnSeek11 = new widget.Button();
        Propinsi2 = new widget.TextBox();
        jLabel41 = new widget.Label();
        NoRm = new widget.TextBox();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnViaBPJSNik = new javax.swing.JMenuItem();
        MnViaBPJSNoKartu = new javax.swing.JMenuItem();
        MnViaDukcapilNikDKI = new javax.swing.JMenuItem();
        MnViaDukcapilNikAceh = new javax.swing.JMenuItem();
        WindowGabungRM = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        label40 = new widget.Label();
        NoRmTujuan = new widget.TextBox();
        NmPasienTujuan = new widget.TextBox();
        label41 = new widget.Label();
        BtnCari1 = new widget.Button();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        kdgolongantni = new widget.TextBox();
        kdsatuantni = new widget.TextBox();
        kdpangkattni = new widget.TextBox();
        kdjabatantni = new widget.TextBox();
        kdgolonganpolri = new widget.TextBox();
        kdsatuanpolri = new widget.TextBox();
        kdpangkatpolri = new widget.TextBox();
        kdjabatanpolri = new widget.TextBox();
        kdperusahaan = new widget.TextBox();
        kdcacat = new widget.TextBox();
        KdKec = new widget.TextBox();
        KdProp = new widget.TextBox();
        KdKel = new widget.TextBox();
        KdKab = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel11 = new widget.Label();
        Carialamat = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel6 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TTmp = new widget.TextBox();
        CmbJk = new widget.ComboBox();
        TNm = new widget.TextBox();
        jLabel8 = new widget.Label();
        CMbGd = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel13 = new widget.Label();
        DTPLahir = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbAgama = new widget.ComboBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        Pekerjaan = new widget.TextBox();
        jLabel12 = new widget.Label();
        Alamat = new widget.TextBox();
        TTlp = new widget.TextBox();
        TNo = new widget.TextBox();
        jLabel15 = new widget.Label();
        TKtp = new widget.TextBox();
        DTPDaftar = new widget.Tanggal();
        jLabel22 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel23 = new widget.Label();
        CMbPnd = new widget.ComboBox();
        jLabel24 = new widget.Label();
        Kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        BtnPenjab = new widget.Button();
        Kelurahan = new widget.TextBox();
        Kecamatan = new widget.TextBox();
        Kabupaten = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        ChkDaftar = new widget.CekBox();
        jLabel14 = new widget.Label();
        NmIbu = new widget.TextBox();
        jLabel28 = new widget.Label();
        TNoPeserta = new widget.TextBox();
        ChkRM = new widget.CekBox();
        TUmurTh = new widget.TextBox();
        jLabel31 = new widget.Label();
        TUmurBl = new widget.TextBox();
        jLabel29 = new widget.Label();
        TUmurHr = new widget.TextBox();
        jLabel30 = new widget.Label();
        BtnPropinsi = new widget.Button();
        Propinsi = new widget.TextBox();
        jLabel39 = new widget.Label();
        EMail = new widget.TextBox();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        KartuPasien.setBackground(new java.awt.Color(250, 255, 245));
        KartuPasien.setForeground(new java.awt.Color(50, 50, 50));
        KartuPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        KartuPasien.setText("Kartu Pasien");
        KartuPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        KartuPasien.setName("KartuPasien"); // NOI18N
        KartuPasien.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKartu1.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu1.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu1.setText("Kartu Pasien 1");
        MnKartu1.setName("MnKartu1"); // NOI18N
        MnKartu1.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu1ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu1);

        MnKartu2.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu2.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu2.setText("Kartu Pasien 2");
        MnKartu2.setName("MnKartu2"); // NOI18N
        MnKartu2.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu2ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu2);

        MnKartu3.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu3.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu3.setText("Kartu Pasien 3");
        MnKartu3.setName("MnKartu3"); // NOI18N
        MnKartu3.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu3ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu3);

        MnKartu4.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu4.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu4.setText("Kartu Pasien 4");
        MnKartu4.setName("MnKartu4"); // NOI18N
        MnKartu4.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu4ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu4);

        MnKartu5.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu5.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu5.setText("Kartu Pasien 5");
        MnKartu5.setName("MnKartu5"); // NOI18N
        MnKartu5.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu5ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu5);

        MnKartu6.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu6.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu6.setText("Kartu Pasien 6");
        MnKartu6.setName("MnKartu6"); // NOI18N
        MnKartu6.setPreferredSize(new java.awt.Dimension(150, 26));
        MnKartu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartu6ActionPerformed(evt);
            }
        });
        KartuPasien.add(MnKartu6);

        jPopupMenu1.add(KartuPasien);

        Barcode.setBackground(new java.awt.Color(250, 255, 245));
        Barcode.setForeground(new java.awt.Color(50, 50, 50));
        Barcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Barcode.setText("Label Rekam Medis");
        Barcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Barcode.setName("Barcode"); // NOI18N
        Barcode.setPreferredSize(new java.awt.Dimension(220, 26));

        MnBarcodeRM.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM.setText("Label Rekam Medis 1");
        MnBarcodeRM.setName("MnBarcodeRM"); // NOI18N
        MnBarcodeRM.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRMActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM);

        MnBarcodeRM1.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM1.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM1.setText("Label Rekam Medis 2");
        MnBarcodeRM1.setName("MnBarcodeRM1"); // NOI18N
        MnBarcodeRM1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM1ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM1);

        MnBarcodeRM2.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM2.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM2.setText("Label Rekam Medis 3");
        MnBarcodeRM2.setName("MnBarcodeRM2"); // NOI18N
        MnBarcodeRM2.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM2ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM2);

        MnBarcodeRM3.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM3.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM3.setText("Label Rekam Medis 4");
        MnBarcodeRM3.setName("MnBarcodeRM3"); // NOI18N
        MnBarcodeRM3.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM3ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM3);

        MnBarcodeRM4.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM4.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM4.setText("Label Rekam Medis 5");
        MnBarcodeRM4.setName("MnBarcodeRM4"); // NOI18N
        MnBarcodeRM4.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM4ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM4);

        MnBarcodeRM5.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM5.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM5.setText("Label Rekam Medis 6");
        MnBarcodeRM5.setName("MnBarcodeRM5"); // NOI18N
        MnBarcodeRM5.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM5ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM5);

        MnBarcodeRM6.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM6.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM6.setText("Label Rekam Medis 7");
        MnBarcodeRM6.setName("MnBarcodeRM6"); // NOI18N
        MnBarcodeRM6.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM6ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM6);

        MnBarcodeRM7.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM7.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM7.setText("Label Rekam Medis 8");
        MnBarcodeRM7.setToolTipText("");
        MnBarcodeRM7.setName("MnBarcodeRM7"); // NOI18N
        MnBarcodeRM7.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM7ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM7);

        MnBarcodeRM8.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM8.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM8.setText("Label Rekam Medis 9");
        MnBarcodeRM8.setName("MnBarcodeRM8"); // NOI18N
        MnBarcodeRM8.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM8ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM8);

        MnBarcodeRM9.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM9.setForeground(new java.awt.Color(50, 50, 50));
        MnBarcodeRM9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM9.setText("Label Rekam Medis 10");
        MnBarcodeRM9.setName("MnBarcodeRM9"); // NOI18N
        MnBarcodeRM9.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM9ActionPerformed(evt);
            }
        });
        Barcode.add(MnBarcodeRM9);

        jPopupMenu1.add(Barcode);

        MenuIdentitas.setBackground(new java.awt.Color(250, 255, 245));
        MenuIdentitas.setForeground(new java.awt.Color(50, 50, 50));
        MenuIdentitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuIdentitas.setText("Identitas Pasien");
        MenuIdentitas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuIdentitas.setName("MenuIdentitas"); // NOI18N
        MenuIdentitas.setPreferredSize(new java.awt.Dimension(220, 26));

        MnIdentitas.setBackground(new java.awt.Color(255, 255, 254));
        MnIdentitas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas.setForeground(new java.awt.Color(50, 50, 50));
        MnIdentitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas.setText("Identitas Pasien");
        MnIdentitas.setName("MnIdentitas"); // NOI18N
        MnIdentitas.setPreferredSize(new java.awt.Dimension(200, 26));
        MnIdentitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitasActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas);

        MnIdentitas2.setBackground(new java.awt.Color(255, 255, 254));
        MnIdentitas2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas2.setForeground(new java.awt.Color(50, 50, 50));
        MnIdentitas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas2.setText("Identitas Pasien 2");
        MnIdentitas2.setName("MnIdentitas2"); // NOI18N
        MnIdentitas2.setPreferredSize(new java.awt.Dimension(200, 26));
        MnIdentitas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas2ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas2);

        MnIdentitas3.setBackground(new java.awt.Color(255, 255, 254));
        MnIdentitas3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas3.setForeground(new java.awt.Color(50, 50, 50));
        MnIdentitas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas3.setText("Identitas Pasien 3");
        MnIdentitas3.setName("MnIdentitas3"); // NOI18N
        MnIdentitas3.setPreferredSize(new java.awt.Dimension(200, 26));
        MnIdentitas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas3ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas3);

        MnIdentitas4.setBackground(new java.awt.Color(255, 255, 254));
        MnIdentitas4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas4.setForeground(new java.awt.Color(50, 50, 50));
        MnIdentitas4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas4.setText("Identitas Pasien 4");
        MnIdentitas4.setName("MnIdentitas4"); // NOI18N
        MnIdentitas4.setPreferredSize(new java.awt.Dimension(200, 26));
        MnIdentitas4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas4ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas4);

        jPopupMenu1.add(MenuIdentitas);

        MnKartuStatus.setBackground(new java.awt.Color(255, 255, 254));
        MnKartuStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuStatus.setForeground(new java.awt.Color(50, 50, 50));
        MnKartuStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuStatus.setText("Kartu Indeks Pasien Yang Dipilih");
        MnKartuStatus.setName("MnKartuStatus"); // NOI18N
        MnKartuStatus.setPreferredSize(new java.awt.Dimension(220, 26));
        MnKartuStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuStatusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKartuStatus);

        MenuBPJS.setBackground(new java.awt.Color(250, 255, 245));
        MenuBPJS.setForeground(new java.awt.Color(50, 50, 50));
        MenuBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuBPJS.setText("BPJS");
        MenuBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuBPJS.setName("MenuBPJS"); // NOI18N
        MenuBPJS.setPreferredSize(new java.awt.Dimension(220, 26));

        MnCekKepesertaan.setBackground(new java.awt.Color(255, 255, 254));
        MnCekKepesertaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekKepesertaan.setForeground(new java.awt.Color(50, 50, 50));
        MnCekKepesertaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekKepesertaan.setText("Pencarian Peserta Berdasarkan Nomor Kepesertaan VClaim");
        MnCekKepesertaan.setName("MnCekKepesertaan"); // NOI18N
        MnCekKepesertaan.setPreferredSize(new java.awt.Dimension(350, 26));
        MnCekKepesertaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekKepesertaanActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekKepesertaan);

        MnCekNIK.setBackground(new java.awt.Color(255, 255, 254));
        MnCekNIK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNIK.setForeground(new java.awt.Color(50, 50, 50));
        MnCekNIK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekNIK.setText("Pencarian Peserta Berdasarkan NIK/No.KTP VClaim");
        MnCekNIK.setName("MnCekNIK"); // NOI18N
        MnCekNIK.setPreferredSize(new java.awt.Dimension(350, 26));
        MnCekNIK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNIKActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekNIK);

        MnCekKepesertaan1.setBackground(new java.awt.Color(255, 255, 254));
        MnCekKepesertaan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekKepesertaan1.setForeground(new java.awt.Color(50, 50, 50));
        MnCekKepesertaan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekKepesertaan1.setText("Pencarian Peserta Berdasarkan Nomor Kepesertaan PCare");
        MnCekKepesertaan1.setName("MnCekKepesertaan1"); // NOI18N
        MnCekKepesertaan1.setPreferredSize(new java.awt.Dimension(350, 26));
        MnCekKepesertaan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekKepesertaan1ActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekKepesertaan1);

        MnCekNIK1.setBackground(new java.awt.Color(255, 255, 254));
        MnCekNIK1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNIK1.setForeground(new java.awt.Color(50, 50, 50));
        MnCekNIK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekNIK1.setText("Pencarian Peserta Berdasarkan NIK/No.KTP PCare");
        MnCekNIK1.setName("MnCekNIK1"); // NOI18N
        MnCekNIK1.setPreferredSize(new java.awt.Dimension(350, 26));
        MnCekNIK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNIK1ActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekNIK1);

        jPopupMenu1.add(MenuBPJS);

        ppKelahiranBayi.setBackground(new java.awt.Color(255, 255, 254));
        ppKelahiranBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppKelahiranBayi.setForeground(new java.awt.Color(50, 50, 50));
        ppKelahiranBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppKelahiranBayi.setText("Data Kelahiran Bayi");
        ppKelahiranBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppKelahiranBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppKelahiranBayi.setName("ppKelahiranBayi"); // NOI18N
        ppKelahiranBayi.setPreferredSize(new java.awt.Dimension(220, 26));
        ppKelahiranBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppKelahiranBayiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppKelahiranBayi);

        jMenu1.setBackground(new java.awt.Color(250, 255, 245));
        jMenu1.setForeground(new java.awt.Color(50, 50, 50));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Berkas Rekam Medis");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(220, 26));

        MnLaporanRM.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM.setText("Lembar Rawat Jalan Model 1");
        MnLaporanRM.setName("MnLaporanRM"); // NOI18N
        MnLaporanRM.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRMActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM);

        MnLaporanRM1.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM1.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM1.setText("Lembar Rawat Jalan Model 2");
        MnLaporanRM1.setName("MnLaporanRM1"); // NOI18N
        MnLaporanRM1.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRM1ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM1);

        MnLaporanRM2.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM2.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM2.setText("Lembar Rawat Jalan Model 3");
        MnLaporanRM2.setName("MnLaporanRM2"); // NOI18N
        MnLaporanRM2.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRM2ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM2);

        MnLaporanRM3.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRM3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM3.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanRM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM3.setText("Lembar Rawat Jalan Model 4");
        MnLaporanRM3.setName("MnLaporanRM3"); // NOI18N
        MnLaporanRM3.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanRM3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRM3ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM3);

        MnFormulirPendaftaran.setBackground(new java.awt.Color(255, 255, 254));
        MnFormulirPendaftaran.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirPendaftaran.setForeground(new java.awt.Color(50, 50, 50));
        MnFormulirPendaftaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirPendaftaran.setText("Formulir Pendaftaran Pasien");
        MnFormulirPendaftaran.setName("MnFormulirPendaftaran"); // NOI18N
        MnFormulirPendaftaran.setPreferredSize(new java.awt.Dimension(300, 26));
        MnFormulirPendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirPendaftaranActionPerformed(evt);
            }
        });
        jMenu1.add(MnFormulirPendaftaran);

        MnSCreening.setBackground(new java.awt.Color(255, 255, 254));
        MnSCreening.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSCreening.setForeground(new java.awt.Color(50, 50, 50));
        MnSCreening.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSCreening.setText("Lembar Screening Awal Pasien Masuk Rawat Jalan");
        MnSCreening.setName("MnSCreening"); // NOI18N
        MnSCreening.setPreferredSize(new java.awt.Dimension(300, 26));
        MnSCreening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSCreeningActionPerformed(evt);
            }
        });
        jMenu1.add(MnSCreening);

        MnCopyResep.setBackground(new java.awt.Color(255, 255, 254));
        MnCopyResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyResep.setForeground(new java.awt.Color(50, 50, 50));
        MnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyResep.setText("Formulir Penempelan Copy Resep");
        MnCopyResep.setName("MnCopyResep"); // NOI18N
        MnCopyResep.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyResepActionPerformed(evt);
            }
        });
        jMenu1.add(MnCopyResep);

        MnLembarKeluarMasuk.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarKeluarMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarKeluarMasuk.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarKeluarMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarKeluarMasuk.setText("Lembar Masuk Keluar Model 1");
        MnLembarKeluarMasuk.setName("MnLembarKeluarMasuk"); // NOI18N
        MnLembarKeluarMasuk.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarKeluarMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarKeluarMasukActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarKeluarMasuk);

        MnLembarKeluarMasuk2.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarKeluarMasuk2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarKeluarMasuk2.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarKeluarMasuk2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarKeluarMasuk2.setText("Lembar Masuk Keluar Model 2");
        MnLembarKeluarMasuk2.setName("MnLembarKeluarMasuk2"); // NOI18N
        MnLembarKeluarMasuk2.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarKeluarMasuk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarKeluarMasuk2ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarKeluarMasuk2);

        MnLaporanIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanIGD.setText("Laporan IGD");
        MnLaporanIGD.setName("MnLaporanIGD"); // NOI18N
        MnLaporanIGD.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanIGDActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanIGD);

        MnLembarAnamNesa.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarAnamNesa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarAnamNesa.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarAnamNesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarAnamNesa.setText("Lembar Anamnesa");
        MnLembarAnamNesa.setName("MnLembarAnamNesa"); // NOI18N
        MnLembarAnamNesa.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarAnamNesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarAnamNesaActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarAnamNesa);

        MnLembarGrafik.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarGrafik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarGrafik.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarGrafik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarGrafik.setText("Lembar Grafik");
        MnLembarGrafik.setName("MnLembarGrafik"); // NOI18N
        MnLembarGrafik.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarGrafik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarGrafikActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarGrafik);

        MnLembarCatatanPerkembangan.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarCatatanPerkembangan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCatatanPerkembangan.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarCatatanPerkembangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCatatanPerkembangan.setText("Lembar Catatan Perkembangan");
        MnLembarCatatanPerkembangan.setName("MnLembarCatatanPerkembangan"); // NOI18N
        MnLembarCatatanPerkembangan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarCatatanPerkembangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCatatanPerkembanganActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarCatatanPerkembangan);

        MnLembarCatatanKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarCatatanKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCatatanKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarCatatanKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCatatanKeperawatan.setText("Lembar Catatan Keperawatan");
        MnLembarCatatanKeperawatan.setName("MnLembarCatatanKeperawatan"); // NOI18N
        MnLembarCatatanKeperawatan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLembarCatatanKeperawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCatatanKeperawatanActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarCatatanKeperawatan);

        MnLaporanAnestesia.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanAnestesia.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanAnestesia.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanAnestesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanAnestesia.setText("Lembar Laporan Anastesia");
        MnLaporanAnestesia.setName("MnLaporanAnestesia"); // NOI18N
        MnLaporanAnestesia.setPreferredSize(new java.awt.Dimension(300, 26));
        MnLaporanAnestesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanAnestesiaActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanAnestesia);

        MnPengantarHemodalisa.setBackground(new java.awt.Color(255, 255, 254));
        MnPengantarHemodalisa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengantarHemodalisa.setForeground(new java.awt.Color(50, 50, 50));
        MnPengantarHemodalisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPengantarHemodalisa.setText("Pengantar Hemodialisa");
        MnPengantarHemodalisa.setName("MnPengantarHemodalisa"); // NOI18N
        MnPengantarHemodalisa.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPengantarHemodalisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengantarHemodalisaActionPerformed(evt);
            }
        });
        jMenu1.add(MnPengantarHemodalisa);

        MnCover.setBackground(new java.awt.Color(255, 255, 254));
        MnCover.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCover.setForeground(new java.awt.Color(50, 50, 50));
        MnCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCover.setText("Cover Rekam Medis");
        MnCover.setName("MnCover"); // NOI18N
        MnCover.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCoverActionPerformed(evt);
            }
        });
        jMenu1.add(MnCover);

        MnCover1.setBackground(new java.awt.Color(255, 255, 254));
        MnCover1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCover1.setForeground(new java.awt.Color(50, 50, 50));
        MnCover1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCover1.setText("Cover Rekam Medis 2");
        MnCover1.setName("MnCover1"); // NOI18N
        MnCover1.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCover1ActionPerformed(evt);
            }
        });
        jMenu1.add(MnCover1);

        jPopupMenu1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(250, 255, 245));
        jMenu2.setForeground(new java.awt.Color(50, 50, 50));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Analisa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(220, 26));

        ppGrafikPerAgama.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerAgama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerAgama.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikPerAgama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerAgama.setText("Grafik Pasien Per Agama");
        ppGrafikPerAgama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerAgama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerAgama.setName("ppGrafikPerAgama"); // NOI18N
        ppGrafikPerAgama.setPreferredSize(new java.awt.Dimension(230, 26));
        ppGrafikPerAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerAgamaActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerAgama);

        ppGrafikPerPekerjaan.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerPekerjaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerPekerjaan.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerPekerjaan.setText("Grafik Pasien Per Pekerjaan");
        ppGrafikPerPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerPekerjaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerPekerjaan.setName("ppGrafikPerPekerjaan"); // NOI18N
        ppGrafikPerPekerjaan.setPreferredSize(new java.awt.Dimension(230, 26));
        ppGrafikPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerPekerjaanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerPekerjaan);

        ppGrafikjkbayi.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikjkbayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikjkbayi.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikjkbayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikjkbayi.setText("Grafik Jenis Kelamin Pasien");
        ppGrafikjkbayi.setActionCommand("Grafik Pasien Per Jenis Kelamin");
        ppGrafikjkbayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikjkbayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikjkbayi.setName("ppGrafikjkbayi"); // NOI18N
        ppGrafikjkbayi.setPreferredSize(new java.awt.Dimension(230, 26));
        ppGrafikjkbayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikjkbayiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikjkbayi);

        ppGrafikDemografi.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikDemografi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikDemografi.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikDemografi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikDemografi.setText("Demografi Pasien");
        ppGrafikDemografi.setActionCommand("Grafik Demografi Pasien");
        ppGrafikDemografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikDemografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikDemografi.setName("ppGrafikDemografi"); // NOI18N
        ppGrafikDemografi.setPreferredSize(new java.awt.Dimension(230, 26));
        ppGrafikDemografi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikDemografiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikDemografi);

        jPopupMenu1.add(jMenu2);

        ppRegistrasi.setBackground(new java.awt.Color(255, 255, 254));
        ppRegistrasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRegistrasi.setForeground(new java.awt.Color(50, 50, 50));
        ppRegistrasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRegistrasi.setText("Tampilkan Banyak Daftar");
        ppRegistrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRegistrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRegistrasi.setName("ppRegistrasi"); // NOI18N
        ppRegistrasi.setPreferredSize(new java.awt.Dimension(220, 26));
        ppRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRegistrasiBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRegistrasi);

        ppRegistrasi1.setBackground(new java.awt.Color(255, 255, 254));
        ppRegistrasi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRegistrasi1.setForeground(new java.awt.Color(50, 50, 50));
        ppRegistrasi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRegistrasi1.setText("Tampilkan Tidak Aktif > 5 Tahun");
        ppRegistrasi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRegistrasi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRegistrasi1.setName("ppRegistrasi1"); // NOI18N
        ppRegistrasi1.setPreferredSize(new java.awt.Dimension(220, 26));
        ppRegistrasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRegistrasi1BtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRegistrasi1);

        ppRegistrasi2.setBackground(new java.awt.Color(255, 255, 254));
        ppRegistrasi2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRegistrasi2.setForeground(new java.awt.Color(50, 50, 50));
        ppRegistrasi2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRegistrasi2.setText("Tampilkan Sudah Diretensi");
        ppRegistrasi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRegistrasi2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRegistrasi2.setName("ppRegistrasi2"); // NOI18N
        ppRegistrasi2.setPreferredSize(new java.awt.Dimension(220, 26));
        ppRegistrasi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRegistrasi2BtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRegistrasi2);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 254));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setForeground(new java.awt.Color(50, 50, 50));
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(220, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRiwayat);

        ppCatatanPasien.setBackground(new java.awt.Color(255, 255, 254));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setForeground(new java.awt.Color(50, 50, 50));
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCatatanPasien);

        ppGabungRM.setBackground(new java.awt.Color(255, 255, 254));
        ppGabungRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGabungRM.setForeground(new java.awt.Color(50, 50, 50));
        ppGabungRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGabungRM.setText("Gabungkan Data RM");
        ppGabungRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGabungRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGabungRM.setName("ppGabungRM"); // NOI18N
        ppGabungRM.setPreferredSize(new java.awt.Dimension(220, 26));
        ppGabungRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGabungRMBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGabungRM);

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Bridging Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(220, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPasienCorona);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        DlgDemografi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgDemografi.setName("DlgDemografi"); // NOI18N
        DlgDemografi.setUndecorated(true);
        DlgDemografi.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Demografi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Grafik");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint2);
        BtnPrint2.setBounds(110, 130, 100, 30);

        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(430, 130, 100, 30);

        Kelurahan2.setHighlighter(null);
        Kelurahan2.setName("Kelurahan2"); // NOI18N
        panelBiasa2.add(Kelurahan2);
        Kelurahan2.setBounds(105, 100, 350, 23);

        BtnSeek8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek8.setMnemonic('1');
        BtnSeek8.setToolTipText("ALt+1");
        BtnSeek8.setName("BtnSeek8"); // NOI18N
        BtnSeek8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek8ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek8);
        BtnSeek8.setBounds(460, 100, 28, 23);

        Kecamatan2.setHighlighter(null);
        Kecamatan2.setName("Kecamatan2"); // NOI18N
        panelBiasa2.add(Kecamatan2);
        Kecamatan2.setBounds(105, 70, 350, 23);

        BtnSeek9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek9.setMnemonic('1');
        BtnSeek9.setToolTipText("ALt+1");
        BtnSeek9.setName("BtnSeek9"); // NOI18N
        BtnSeek9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek9ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek9);
        BtnSeek9.setBounds(460, 70, 28, 23);

        Kabupaten2.setHighlighter(null);
        Kabupaten2.setName("Kabupaten2"); // NOI18N
        panelBiasa2.add(Kabupaten2);
        Kabupaten2.setBounds(105, 40, 350, 23);

        BtnSeek10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek10.setMnemonic('1');
        BtnSeek10.setToolTipText("ALt+1");
        BtnSeek10.setName("BtnSeek10"); // NOI18N
        BtnSeek10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek10ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek10);
        BtnSeek10.setBounds(460, 40, 28, 23);

        jLabel33.setText("Kelurahan :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa2.add(jLabel33);
        jLabel33.setBounds(0, 100, 100, 23);

        jLabel34.setText("Kabupaten :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa2.add(jLabel34);
        jLabel34.setBounds(0, 40, 100, 23);

        jLabel35.setText("Kecamatan :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa2.add(jLabel35);
        jLabel35.setBounds(0, 70, 100, 23);

        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint3.setMnemonic('T');
        BtnPrint3.setText("Cetak");
        BtnPrint3.setToolTipText("Alt+T");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint3);
        BtnPrint3.setBounds(10, 130, 100, 30);

        BtnSeek11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek11.setMnemonic('1');
        BtnSeek11.setToolTipText("ALt+1");
        BtnSeek11.setName("BtnSeek11"); // NOI18N
        BtnSeek11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek11ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek11);
        BtnSeek11.setBounds(460, 10, 28, 23);

        Propinsi2.setHighlighter(null);
        Propinsi2.setName("Propinsi2"); // NOI18N
        panelBiasa2.add(Propinsi2);
        Propinsi2.setBounds(105, 10, 350, 23);

        jLabel41.setText("Propinsi :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelBiasa2.add(jLabel41);
        jLabel41.setBounds(0, 10, 100, 23);

        internalFrame3.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgDemografi.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        NoRm.setHighlighter(null);
        NoRm.setName("NoRm"); // NOI18N
        NoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmKeyPressed(evt);
            }
        });

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnViaBPJSNik.setBackground(new java.awt.Color(255, 255, 254));
        MnViaBPJSNik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaBPJSNik.setForeground(new java.awt.Color(50, 50, 50));
        MnViaBPJSNik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaBPJSNik.setText("Cek Via NIK Web Servis BPJS VClaim");
        MnViaBPJSNik.setName("MnViaBPJSNik"); // NOI18N
        MnViaBPJSNik.setPreferredSize(new java.awt.Dimension(290, 26));
        MnViaBPJSNik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaBPJSNikActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaBPJSNik);

        MnViaBPJSNoKartu.setBackground(new java.awt.Color(255, 255, 254));
        MnViaBPJSNoKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaBPJSNoKartu.setForeground(new java.awt.Color(50, 50, 50));
        MnViaBPJSNoKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaBPJSNoKartu.setText("Cek Via No Kartu Web Servis BPJS VClaim");
        MnViaBPJSNoKartu.setName("MnViaBPJSNoKartu"); // NOI18N
        MnViaBPJSNoKartu.setPreferredSize(new java.awt.Dimension(290, 26));
        MnViaBPJSNoKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaBPJSNoKartuActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaBPJSNoKartu);

        MnViaDukcapilNikDKI.setBackground(new java.awt.Color(255, 255, 254));
        MnViaDukcapilNikDKI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaDukcapilNikDKI.setForeground(new java.awt.Color(50, 50, 50));
        MnViaDukcapilNikDKI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaDukcapilNikDKI.setText("Cek Via NIK Web Servis DUKCAPIL Jakarta");
        MnViaDukcapilNikDKI.setName("MnViaDukcapilNikDKI"); // NOI18N
        MnViaDukcapilNikDKI.setPreferredSize(new java.awt.Dimension(290, 26));
        MnViaDukcapilNikDKI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaDukcapilNikDKIActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaDukcapilNikDKI);

        MnViaDukcapilNikAceh.setBackground(new java.awt.Color(255, 255, 254));
        MnViaDukcapilNikAceh.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaDukcapilNikAceh.setForeground(new java.awt.Color(50, 50, 50));
        MnViaDukcapilNikAceh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaDukcapilNikAceh.setText("Cek Via NIK Web Servis DUKCAPIL");
        MnViaDukcapilNikAceh.setName("MnViaDukcapilNikAceh"); // NOI18N
        MnViaDukcapilNikAceh.setPreferredSize(new java.awt.Dimension(290, 26));
        MnViaDukcapilNikAceh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaDukcapilNikAcehActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaDukcapilNikAceh);

        WindowGabungRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGabungRM.setModal(true);
        WindowGabungRM.setName("WindowGabungRM"); // NOI18N
        WindowGabungRM.setUndecorated(true);
        WindowGabungRM.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Gabungkan Ke Nomor RM ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(null);

        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('P');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+P");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(130, 87, 100, 30);

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
        internalFrame8.add(BtnSimpan6);
        BtnSimpan6.setBounds(20, 87, 100, 30);

        label40.setText("No.Rekam Medik :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(35, 23));
        internalFrame8.add(label40);
        label40.setBounds(0, 22, 105, 23);

        NoRmTujuan.setHighlighter(null);
        NoRmTujuan.setName("NoRmTujuan"); // NOI18N
        NoRmTujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmTujuanKeyPressed(evt);
            }
        });
        internalFrame8.add(NoRmTujuan);
        NoRmTujuan.setBounds(108, 22, 100, 23);

        NmPasienTujuan.setEditable(false);
        NmPasienTujuan.setHighlighter(null);
        NmPasienTujuan.setName("NmPasienTujuan"); // NOI18N
        internalFrame8.add(NmPasienTujuan);
        NmPasienTujuan.setBounds(108, 52, 300, 23);

        label41.setText("Nama Pasien :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(35, 23));
        internalFrame8.add(label41);
        label41.setBounds(0, 52, 105, 23);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCari1);
        BtnCari1.setBounds(210, 22, 28, 23);

        WindowGabungRM.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        kdsuku.setName("kdsuku"); // NOI18N
        kdsuku.setPreferredSize(new java.awt.Dimension(207, 23));

        kdbahasa.setName("kdbahasa"); // NOI18N
        kdbahasa.setPreferredSize(new java.awt.Dimension(207, 23));

        kdgolongantni.setName("kdgolongantni"); // NOI18N
        kdgolongantni.setPreferredSize(new java.awt.Dimension(207, 23));

        kdsatuantni.setName("kdsatuantni"); // NOI18N
        kdsatuantni.setPreferredSize(new java.awt.Dimension(207, 23));

        kdpangkattni.setName("kdpangkattni"); // NOI18N
        kdpangkattni.setPreferredSize(new java.awt.Dimension(207, 23));

        kdjabatantni.setName("kdjabatantni"); // NOI18N
        kdjabatantni.setPreferredSize(new java.awt.Dimension(207, 23));

        kdgolonganpolri.setName("kdgolonganpolri"); // NOI18N
        kdgolonganpolri.setPreferredSize(new java.awt.Dimension(207, 23));

        kdsatuanpolri.setName("kdsatuanpolri"); // NOI18N
        kdsatuanpolri.setPreferredSize(new java.awt.Dimension(207, 23));

        kdpangkatpolri.setName("kdpangkatpolri"); // NOI18N
        kdpangkatpolri.setPreferredSize(new java.awt.Dimension(207, 23));

        kdjabatanpolri.setName("kdjabatanpolri"); // NOI18N
        kdjabatanpolri.setPreferredSize(new java.awt.Dimension(207, 23));

        kdperusahaan.setEditable(false);
        kdperusahaan.setHighlighter(null);
        kdperusahaan.setName("kdperusahaan"); // NOI18N
        kdperusahaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdperusahaanKeyPressed(evt);
            }
        });

        kdcacat.setEditable(false);
        kdcacat.setHighlighter(null);
        kdcacat.setName("kdcacat"); // NOI18N
        kdcacat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdcacatKeyPressed(evt);
            }
        });

        KdKec.setEditable(false);
        KdKec.setHighlighter(null);
        KdKec.setName("KdKec"); // NOI18N

        KdProp.setEditable(false);
        KdProp.setHighlighter(null);
        KdProp.setName("KdProp"); // NOI18N

        KdKel.setEditable(false);
        KdKel.setHighlighter(null);
        KdKel.setName("KdKel"); // NOI18N

        KdKab.setEditable(false);
        KdKab.setHighlighter(null);
        KdKab.setName("KdKab"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass8.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
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

        jLabel11.setText("Alamat :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass9.add(jLabel11);

        Carialamat.setName("Carialamat"); // NOI18N
        Carialamat.setPreferredSize(new java.awt.Dimension(335, 23));
        Carialamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CarialamatKeyPressed(evt);
            }
        });
        panelGlass9.add(Carialamat);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel7.setText("Key Word :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(68, 23));
        panelGlass9.add(jLabel7);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(180, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
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

        jLabel6.setText("Limit Data :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(cmbHlm);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(254, 254, 254));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(560, 168));
        FormInput.setLayout(null);

        jLabel3.setText("No.Rekam Medis :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(4, 12, 95, 23);

        jLabel4.setText("Nama Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(4, 42, 95, 23);

        TTmp.setName("TTmp"); // NOI18N
        TTmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTmpKeyPressed(evt);
            }
        });
        FormInput.add(TTmp);
        TTmp.setBounds(102, 102, 187, 23);

        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        CmbJk.setName("CmbJk"); // NOI18N
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput.add(CmbJk);
        CmbJk.setBounds(102, 72, 110, 23);

        TNm.setHighlighter(null);
        TNm.setName("TNm"); // NOI18N
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        FormInput.add(TNm);
        TNm.setBounds(102, 42, 290, 23);

        jLabel8.setText("J.K. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(4, 72, 95, 23);

        CMbGd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "A", "B", "AB", "O" }));
        CMbGd.setName("CMbGd"); // NOI18N
        CMbGd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CMbGdKeyPressed(evt);
            }
        });
        FormInput.add(CMbGd);
        CMbGd.setBounds(322, 72, 70, 23);

        jLabel9.setText("Gol. Darah :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(247, 72, 72, 23);

        jLabel13.setText("Tmp/Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(4, 102, 95, 23);

        DTPLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-04-2023" }));
        DTPLahir.setDisplayFormat("dd-MM-yyyy");
        DTPLahir.setName("DTPLahir"); // NOI18N
        DTPLahir.setOpaque(false);
        DTPLahir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPLahirItemStateChanged(evt);
            }
        });
        DTPLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPLahirKeyPressed(evt);
            }
        });
        FormInput.add(DTPLahir);
        DTPLahir.setBounds(292, 102, 100, 23);

        jLabel18.setText("Agama :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 190, 90, 23);

        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU", "-" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        cmbAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAgamaKeyPressed(evt);
            }
        });
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(100, 195, 130, 23);

        jLabel20.setText("Alamat Pasien :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(10, 350, 90, 23);

        jLabel21.setText("No.Telp :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(10, 290, 90, 23);

        Pekerjaan.setName("Pekerjaan"); // NOI18N
        Pekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(Pekerjaan);
        Pekerjaan.setBounds(100, 320, 150, 23);

        jLabel12.setText("Pekerjaan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 320, 90, 23);

        Alamat.setText("ALAMAT");
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        Alamat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatMouseMoved(evt);
            }
        });
        Alamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatMouseExited(evt);
            }
        });
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(100, 350, 377, 23);

        TTlp.setHighlighter(null);
        TTlp.setName("TTlp"); // NOI18N
        TTlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTlpKeyPressed(evt);
            }
        });
        FormInput.add(TTlp);
        TTlp.setBounds(100, 290, 150, 23);

        TNo.setEditable(false);
        TNo.setBackground(new java.awt.Color(245, 250, 240));
        TNo.setHighlighter(null);
        TNo.setName("TNo"); // NOI18N
        TNo.setOpaque(true);
        TNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoKeyPressed(evt);
            }
        });
        FormInput.add(TNo);
        TNo.setBounds(102, 12, 160, 23);

        jLabel15.setText("No.KTP/SIM :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(260, 320, 80, 23);

        TKtp.setComponentPopupMenu(jPopupMenu2);
        TKtp.setName("TKtp"); // NOI18N
        TKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKtpKeyPressed(evt);
            }
        });
        FormInput.add(TKtp);
        TKtp.setBounds(350, 320, 130, 23);

        DTPDaftar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-04-2023" }));
        DTPDaftar.setDisplayFormat("dd-MM-yyyy");
        DTPDaftar.setName("DTPDaftar"); // NOI18N
        DTPDaftar.setOpaque(false);
        DTPDaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPDaftarKeyPressed(evt);
            }
        });
        FormInput.add(DTPDaftar);
        DTPDaftar.setBounds(360, 290, 93, 23);

        jLabel22.setText("Pertama Daftar :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(250, 290, 100, 23);

        jLabel17.setText("Umur :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(4, 132, 95, 23);

        jLabel23.setText("Pendidikan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(247, 132, 72, 23);

        CMbPnd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA", "SLTA/SEDERAJAT", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        CMbPnd.setName("CMbPnd"); // NOI18N
        CMbPnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CMbPndKeyPressed(evt);
            }
        });
        FormInput.add(CMbPnd);
        CMbPnd.setBounds(322, 132, 70, 23);

        jLabel24.setText("Askes/Asuransi :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(10, 230, 90, 23);

        Kdpnj.setText("-");
        Kdpnj.setHighlighter(null);
        Kdpnj.setName("Kdpnj"); // NOI18N
        Kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdpnjKeyPressed(evt);
            }
        });
        FormInput.add(Kdpnj);
        Kdpnj.setBounds(100, 230, 80, 23);

        nmpnj.setEditable(false);
        nmpnj.setText("-");
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(180, 230, 265, 23);

        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("ALt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenjab);
        BtnPenjab.setBounds(450, 230, 28, 23);

        Kelurahan.setText("KELURAHAN");
        Kelurahan.setHighlighter(null);
        Kelurahan.setName("Kelurahan"); // NOI18N
        Kelurahan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KelurahanMouseMoved(evt);
            }
        });
        Kelurahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KelurahanMouseExited(evt);
            }
        });
        Kelurahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanKeyPressed(evt);
            }
        });
        FormInput.add(Kelurahan);
        Kelurahan.setBounds(100, 380, 152, 23);

        Kecamatan.setText("KECAMATAN");
        Kecamatan.setHighlighter(null);
        Kecamatan.setName("Kecamatan"); // NOI18N
        Kecamatan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KecamatanMouseMoved(evt);
            }
        });
        Kecamatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KecamatanMouseExited(evt);
            }
        });
        Kecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanKeyPressed(evt);
            }
        });
        FormInput.add(Kecamatan);
        Kecamatan.setBounds(290, 380, 152, 23);

        Kabupaten.setText("KABUPATEN");
        Kabupaten.setHighlighter(null);
        Kabupaten.setName("Kabupaten"); // NOI18N
        Kabupaten.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KabupatenMouseMoved(evt);
            }
        });
        Kabupaten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KabupatenMouseExited(evt);
            }
        });
        Kabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenKeyPressed(evt);
            }
        });
        FormInput.add(Kabupaten);
        Kabupaten.setBounds(100, 410, 152, 23);

        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(260, 380, 28, 23);

        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(450, 380, 28, 23);

        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(260, 410, 28, 23);

        ChkDaftar.setBorder(null);
        ChkDaftar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkDaftar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkDaftar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkDaftar.setName("ChkDaftar"); // NOI18N
        ChkDaftar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkDaftarItemStateChanged(evt);
            }
        });
        FormInput.add(ChkDaftar);
        ChkDaftar.setBounds(450, 290, 23, 23);

        jLabel14.setText("Nama Ibu :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(4, 162, 95, 23);

        NmIbu.setName("NmIbu"); // NOI18N
        NmIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmIbuKeyPressed(evt);
            }
        });
        FormInput.add(NmIbu);
        NmIbu.setBounds(102, 162, 290, 23);

        jLabel28.setText("No.Peserta :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 260, 100, 23);

        TNoPeserta.setComponentPopupMenu(jPopupMenu2);
        TNoPeserta.setHighlighter(null);
        TNoPeserta.setName("TNoPeserta"); // NOI18N
        TNoPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoPesertaKeyPressed(evt);
            }
        });
        FormInput.add(TNoPeserta);
        TNoPeserta.setBounds(100, 260, 150, 23);

        ChkRM.setBorder(null);
        ChkRM.setSelected(true);
        ChkRM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRM.setName("ChkRM"); // NOI18N
        ChkRM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRMItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRM);
        ChkRM.setBounds(266, 12, 23, 23);

        TUmurTh.setName("TUmurTh"); // NOI18N
        TUmurTh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurThKeyPressed(evt);
            }
        });
        FormInput.add(TUmurTh);
        TUmurTh.setBounds(102, 132, 35, 23);

        jLabel31.setText("Th");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(131, 132, 20, 23);

        TUmurBl.setName("TUmurBl"); // NOI18N
        TUmurBl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurBlKeyPressed(evt);
            }
        });
        FormInput.add(TUmurBl);
        TUmurBl.setBounds(153, 132, 35, 23);

        jLabel29.setText("Bl");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(178, 132, 20, 23);

        TUmurHr.setName("TUmurHr"); // NOI18N
        TUmurHr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurHrKeyPressed(evt);
            }
        });
        FormInput.add(TUmurHr);
        TUmurHr.setBounds(200, 132, 35, 23);

        jLabel30.setText("Hr");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(229, 132, 20, 23);

        BtnPropinsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPropinsi.setMnemonic('4');
        BtnPropinsi.setToolTipText("ALt+4");
        BtnPropinsi.setName("BtnPropinsi"); // NOI18N
        BtnPropinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPropinsiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPropinsi);
        BtnPropinsi.setBounds(450, 410, 28, 23);

        Propinsi.setText("PROPINSI");
        Propinsi.setHighlighter(null);
        Propinsi.setName("Propinsi"); // NOI18N
        Propinsi.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                PropinsiMouseMoved(evt);
            }
        });
        Propinsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropinsiMouseExited(evt);
            }
        });
        Propinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PropinsiKeyPressed(evt);
            }
        });
        FormInput.add(Propinsi);
        Propinsi.setBounds(290, 410, 152, 23);

        jLabel39.setText("Email :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(250, 260, 60, 23);

        EMail.setHighlighter(null);
        EMail.setName("EMail"); // NOI18N
        EMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EMailKeyPressed(evt);
            }
        });
        FormInput.add(EMail);
        EMail.setBounds(320, 260, 161, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame2.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Pasien", internalFrame2);

        internalFrame4.setBackground(new java.awt.Color(254, 254, 254));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPasien.setComponentPopupMenu(jPopupMenu1);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPasienKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Data Pasien", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbPasien.getSelectedColumn()==1)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbPasienMouseClicked

private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
       if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                akses.setform(asalform);
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }else if(evt.getKeyCode()==KeyEvent.VK_A){                
                for(z=0;z<tbPasien.getRowCount();z++){ 
                    tbPasien.setValueAt(true,z,0);
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_G){ 
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbPasienKeyPressed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNo.getText().trim().equals("")){
            Valid.textKosong(TNo,"No.Rekam Medis");
        }else if(TNm.getText().trim().equals("")){
            Valid.textKosong(TNm,"nama pasien");
        }else if(nmpnj.getText().trim().equals("")||Kdpnj.getText().trim().equals("")){
            Valid.textKosong(Kdpnj,"Asuransi/Askes/Png.Jawab");
        }else if(no_ktp.equals("Yes")&&(TKtp.getText().trim().length()<p_no_ktp)){
            Valid.textKosong(TKtp,"No.KTP/SIM minimal "+p_no_ktp+" karakter dan ");            
        }else if(tmp_lahir.equals("Yes")&&(TTmp.getText().trim().length()<p_tmp_lahir)){
            Valid.textKosong(TTmp,"Tempat Lahir minimal "+p_tmp_lahir+" karakter dan ");            
        }else if(nm_ibu.equals("Yes")&&(NmIbu.getText().trim().length()<p_nm_ibu)){
            Valid.textKosong(NmIbu,"Nama Ibu minimal "+p_nm_ibu+" karakter dan ");            
        }else if(alamat.equals("Yes")&&(Alamat.getText().trim().length()<p_alamat)){
            Valid.textKosong(Alamat,"Alamat Pasien minimal "+p_alamat+" karakter dan ");            
        }else if(pekerjaan.equals("Yes")&&(Pekerjaan.getText().trim().length()<p_pekerjaan)){
            Valid.textKosong(Pekerjaan,"Pekerjaan Pasien minimal "+p_pekerjaan+" karakter dan ");            
        }else if(no_tlp.equals("Yes")&&(TTlp.getText().trim().length()<p_no_tlp)){
            Valid.textKosong(TTlp,"Telp Pasien minimal "+p_no_tlp+" karakter dan ");            
        }else if(umur.equals("Yes")&&(TUmurTh.getText().trim().length()<p_umur)){
            Valid.textKosong(TUmurTh,"Umur Pasien minimal "+p_umur+" karakter dan ");            
        }else if(no_peserta.equals("Yes")&&(TNoPeserta.getText().trim().length()<p_no_peserta)){
            Valid.textKosong(TNoPeserta,"No.Peserta Pasien minimal "+p_no_peserta+" karakter dan ");            
        }else if(kelurahan.equals("Yes")&&(Kelurahan.getText().trim().length()<p_kelurahan)){
            Valid.textKosong(Kelurahan,"Kelurahan minimal "+p_kelurahan+" karakter dan ");            
        }else if(kecamatan.equals("Yes")&&(Kecamatan.getText().trim().length()<p_kecamatan)){
            Valid.textKosong(Kecamatan,"Kecamatan minimal "+p_kecamatan+" karakter dan ");            
        }else if(kabupaten.equals("Yes")&&(Kabupaten.getText().trim().length()<p_kabupaten)){
            Valid.textKosong(Kabupaten,"Kabupaten minimal "+p_kabupaten+" karakter dan ");            
        }else if(propinsi.equals("Yes")&&(Propinsi.getText().trim().length()<p_propinsi)){
            Valid.textKosong(Propinsi,"Propinsi minimal "+p_propinsi+" karakter dan ");            
        }else{
  
            
            if(Kelurahan.isEditable()==true){
                Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0",Kelurahan.getText()});
                kdkel=Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?",Kelurahan.getText());
            }else if(Kelurahan.isEditable()==false){
                if(kdkel.equals("")){
                    Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0",Kelurahan.getText()});
                    kdkel=Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?",Kelurahan.getText());
                }
            }
            
            if(Kecamatan.isEditable()==true){
                Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0",Kecamatan.getText()});
                kdkec=Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?",Kecamatan.getText());
            }else if(Kecamatan.isEditable()==false){
                if(kdkec.equals("")){
                    Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0",Kecamatan.getText()});
                    kdkec=Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?",Kecamatan.getText());
                }
            }
            
            if(Kabupaten.isEditable()==true){
                Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0",Kabupaten.getText()});
                kdkab=Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?",Kabupaten.getText());
            }else if(Kabupaten.isEditable()==false){
                if(kdkab.equals("")){
                    Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0",Kabupaten.getText()});
                    kdkab=Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?",Kabupaten.getText());
                }
            }
            
            if(Propinsi.isEditable()==true){
               Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0",Propinsi.getText()}); 
               kdprop=Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?",Propinsi.getText());
            }else if(Propinsi.isEditable()==false){
                if(kdprop.equals("")){
                    Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0",Propinsi.getText()}); 
                    kdprop=Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?",Propinsi.getText());
                }
            }
            
              
            if(Sequel.menyimpantf2("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                    TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),NmIbu.getText(),
                    Alamat.getText().replaceAll("ALAMAT",""),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                    kdkel,kdkec,kdkab,"-","-","-","-","-","-",
                    "1","1","1",EMail.getText(),"0","1","-"
                })==true){
                if(akses.getform().equals("DlgReg")){
                    TCari.setText(TNo.getText());
                }
                if(ChkRM.isSelected()==true){
                    Sequel.queryu2("delete from set_no_rkm_medis");
                    Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{TNo.getText()});            
                }                
                emptTeks(); 
            }else{
                autoNomor();
                if(Sequel.menyimpantf2("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                    TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),NmIbu.getText(),
                    Alamat.getText().replaceAll("ALAMAT",""),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                    kdkel,kdkec,kdkab,"-","-","-","-","-","-",
                   "1","1","1",EMail.getText(),"0","1","-"
                    })==true){
                    if(akses.getform().equals("DlgReg")){
                        TCari.setText(TNo.getText());
                    }
                    
                    if(ChkRM.isSelected()==true){
                        Sequel.queryu2("delete from set_no_rkm_medis");
                        Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{TNo.getText()});            
                    }                
                    emptTeks(); 
                }else{
                    autoNomor();
                    if(Sequel.menyimpantf2("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                    TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),NmIbu.getText(),
                    Alamat.getText().replaceAll("ALAMAT",""),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                    kdkel,kdkec,kdkab,"-","-","-","-","-","-",
                   "1","1","1",EMail.getText(),"0","1","-"
                        })==true){
                        if(akses.getform().equals("DlgReg")){
                            TCari.setText(TNo.getText());
                        }
                       
                        if(ChkRM.isSelected()==true){
                            Sequel.queryu2("delete from set_no_rkm_medis");
                            Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{TNo.getText()});            
                        }                
                        emptTeks(); 
                    }else{
                        autoNomor();
                        if(Sequel.menyimpantf2("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                    TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),NmIbu.getText(),
                    Alamat.getText().replaceAll("ALAMAT",""),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                    kdkel,kdkec,kdkab,"-","-","-","-","-","-",
                    "1","1","1",EMail.getText(),"0","1","-"
                            })==true){
                            if(akses.getform().equals("DlgReg")){
                                TCari.setText(TNo.getText());
                            }

                            if(ChkRM.isSelected()==true){
                                Sequel.queryu2("delete from set_no_rkm_medis");
                                Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{TNo.getText()});            
                            }                
                            emptTeks(); 
                        }else{
                            autoNomor();
                            if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                                    TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),NmIbu.getText(),
                                    Alamat.getText().replaceAll("ALAMAT",""),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                                    kdkel,kdkec,kdkab,"-","-","-","-","-","-",
                                    "1","1","1",EMail.getText(),"0","1","-"
                                })==true){
                                if(akses.getform().equals("DlgReg")){
                                    TCari.setText(TNo.getText());
                              
                                if(ChkRM.isSelected()==true){
                                    Sequel.queryu2("delete from set_no_rkm_medis");
                                    Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{TNo.getText()});            
                                }                
                                emptTeks(); 
                            }else{
                                TNm.requestFocus();
                                autoNomor();
                            }
                        }
                    }
                }
            }
        }
     }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnBatal.requestFocus();
        }
        
}//GEN-LAST:event_BtnSimpanKeyPressed

private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
    TabRawat.setSelectedIndex(0);
    emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TabRawat.setSelectedIndex(0);
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    switch (TabRawat.getSelectedIndex()) {
        case 1:
            for(z=0;z<tbPasien.getRowCount();z++){ 
                 if(tbPasien.getValueAt(z,0).toString().equals("true")){
                     Sequel.meghapus("pasien","no_rkm_medis",tbPasien.getValueAt(z,1).toString());
                 }
            } 
            tampil();
            break;
        default:
            break;
    }
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNo.getText().trim().equals("")){
            Valid.textKosong(TNo,"No.Rekam Medis");
        }else if(TNm.getText().trim().equals("")){
            Valid.textKosong(TNm,"nama pasien");
        }else if(nmpnj.getText().trim().equals("")||Kdpnj.getText().trim().equals("")){
            Valid.textKosong(Kdpnj,"Asuransi/Askes/Png.Jawab");
        }else if(no_ktp.equals("Yes")&&(TKtp.getText().trim().length()<p_no_ktp)){
            Valid.textKosong(TKtp,"No.KTP/SIM minimal "+p_no_ktp+" karakter dan ");            
        }else if(tmp_lahir.equals("Yes")&&(TTmp.getText().trim().length()<p_tmp_lahir)){
            Valid.textKosong(TTmp,"Tempat Lahir minimal "+p_tmp_lahir+" karakter dan ");            
        }else if(nm_ibu.equals("Yes")&&(NmIbu.getText().trim().length()<p_nm_ibu)){
            Valid.textKosong(NmIbu,"Nama Ibu minimal "+p_nm_ibu+" karakter dan ");            
        }else if(alamat.equals("Yes")&&(Alamat.getText().trim().length()<p_alamat)){
            Valid.textKosong(Alamat,"Alamat Pasien minimal "+p_alamat+" karakter dan ");            
        }else if(pekerjaan.equals("Yes")&&(Pekerjaan.getText().trim().length()<p_pekerjaan)){
            Valid.textKosong(Pekerjaan,"Pekerjaan Pasien minimal "+p_pekerjaan+" karakter dan ");            
        }else if(no_tlp.equals("Yes")&&(TTlp.getText().trim().length()<p_no_tlp)){
            Valid.textKosong(TTlp,"Telp Pasien minimal "+p_no_tlp+" karakter dan ");            
        }else if(umur.equals("Yes")&&(TUmurTh.getText().trim().length()<p_umur)){
            Valid.textKosong(TUmurTh,"Umur Pasien minimal "+p_umur+" karakter dan ");                       
        }else if(no_peserta.equals("Yes")&&(TNoPeserta.getText().trim().length()<p_no_peserta)){
            Valid.textKosong(TNoPeserta,"No.Peserta Pasien minimal "+p_no_peserta+" karakter dan ");            
        }else if(kelurahan.equals("Yes")&&(Kelurahan.getText().trim().length()<p_kelurahan)){
            Valid.textKosong(Kelurahan,"Kelurahan minimal "+p_kelurahan+" karakter dan ");            
        }else if(kecamatan.equals("Yes")&&(Kecamatan.getText().trim().length()<p_kecamatan)){
            Valid.textKosong(Kecamatan,"Kecamatan minimal "+p_kecamatan+" karakter dan ");            
        }else if(kabupaten.equals("Yes")&&(Kabupaten.getText().trim().length()<p_kabupaten)){
            Valid.textKosong(Kabupaten,"Kabupaten minimal "+p_kabupaten+" karakter dan ");            
        }else if(propinsi.equals("Yes")&&(Propinsi.getText().trim().length()<p_propinsi)){
            Valid.textKosong(Propinsi,"Propinsi minimal "+p_propinsi+" karakter dan ");            
        }else{
  
            
            if(Kelurahan.isEditable()==true){
                Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0",Kelurahan.getText()});
                kdkel=Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?",Kelurahan.getText());
            }else if(Kelurahan.isEditable()==false){
                if(kdkel.equals("")){
                    Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0",Kelurahan.getText()});
                    kdkel=Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?",Kelurahan.getText());
                }
            }
            
            if(Kecamatan.isEditable()==true){
                Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0",Kecamatan.getText()});
                kdkec=Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?",Kecamatan.getText());
            }else if(Kecamatan.isEditable()==false){
                if(kdkec.equals("")){
                    Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0",Kecamatan.getText()});
                    kdkec=Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?",Kecamatan.getText());
                }
            }
            
            if(Kabupaten.isEditable()==true){
                Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0",Kabupaten.getText()});
                kdkab=Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?",Kabupaten.getText());
            }else if(Kabupaten.isEditable()==false){
                if(kdkab.equals("")){
                    Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0",Kabupaten.getText()});
                    kdkab=Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?",Kabupaten.getText());
                }
            }
            
            if(Propinsi.isEditable()==true){
               Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0",Propinsi.getText()}); 
               kdprop=Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?",Propinsi.getText());
            }else if(Propinsi.isEditable()==false){
                if(kdprop.equals("")){
                    Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0",Propinsi.getText()}); 
                    kdprop=Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?",Propinsi.getText());
                }
            }
            
            if(Sequel.mengedittf("pasien","no_rkm_medis=?","no_rkm_medis=?,nm_pasien=?,no_ktp=?,jk=?,tmp_lahir=?,"+
                "tgl_lahir=?,alamat=?,gol_darah=?,pekerjaan=?,stts_nikah=?,agama=?,tgl_daftar=?,no_tlp=?,umur=?"+
                ",pnd=?,keluarga=?,namakeluarga=?,kd_pj=?,no_peserta=?,kd_kel=?,kd_kec=?,kd_kab=?,nm_ibu=?,pekerjaanpj=?,"+
                "alamatpj=?,kelurahanpj=?,kecamatanpj=?,kabupatenpj=?,perusahaan_pasien=?,suku_bangsa=?,bahasa_pasien=?,"+
                "cacat_fisik=?,email=?,nip=?,kd_prop=?,propinsipj=?",37,
                new String[]{TNo.getText(),TNm.getText(),TKtp.getText(),CmbJk.getSelectedItem().toString().substring(0,1),TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem()+""),
                    Alamat.getText(),CMbGd.getSelectedItem().toString(),Pekerjaan.getText(),"MENIKAH",cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6,10)+"-"+DTPDaftar.getSelectedItem().toString().substring(3,5)+"-"+DTPDaftar.getSelectedItem().toString().substring(0,2),
                    TTlp.getText(),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",CMbPnd.getSelectedItem().toString(),"SAUDARA","-",Kdpnj.getText(),TNoPeserta.getText(),
                    kdkel,kdkec,kdkab,NmIbu.getText(),"-","-","-","-",
                    "-","-","1","1","1",EMail.getText(),"0",
                    "1","-",Kd2.getText()
            })==true){
                

                emptTeks();
                TabRawat.setSelectedIndex(1);
                tampil();
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

private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();    
        param.put("namars",akses.getnamars());
        param.put("alamatrs",akses.getalamatrs());
        param.put("kotars",akses.getkabupatenrs());
        param.put("propinsirs",akses.getpropinsirs());
        param.put("kontakrs",akses.getkontakrs());
        param.put("emailrs",akses.getemailrs());   
        param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 

        switch (TabRawat.getSelectedIndex()) {
            case 1:
                if(tabMode.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabMode.getRowCount()!=0){
                    if(!cmbHlm.getSelectedItem().toString().equals("Semua")){
                        if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){
                            Valid.MyReportqry("rptPasien.jasper","report","::[ Data Pasien Umum ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
                                "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
                                "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                                "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
                                "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                                "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                                "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                                "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                                "inner join penjab on pasien.kd_pj=penjab.kd_pj order by pasien.no_rkm_medis desc"+" LIMIT "+cmbHlm.getSelectedItem(),param);
                        }else{
                            Valid.MyReportqry("rptPasien.jasper","report","::[ Data Pasien Umum ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
                                "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
                                "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                                "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
                                "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                                "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                                "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                                "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                                "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
                                 "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%' and pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_ktp like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_peserta like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tmp_lahir like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tgl_lahir like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and penjab.png_jawab like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.alamat like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.gol_darah like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.pekerjaan like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.stts_nikah like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nip like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and cacat_fisik.nama_cacat like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.namakeluarga like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and perusahaan_pasien.nama_perusahaan like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and bahasa_pasien.nama_bahasa like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and suku_bangsa.nama_suku_bangsa like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.agama like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nm_ibu like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tgl_daftar like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_tlp like '%"+TCari.getText().trim()+"%'  order by pasien.no_rkm_medis desc"+" LIMIT "+cmbHlm.getSelectedItem(),param);
                        }
                            
                    }else{
                        if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){
                            Valid.MyReportqry("rptPasien.jasper","report","::[ Data Pasien Umum ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
                                "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
                                "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                                "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
                                "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                                "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                                "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                                "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                                "inner join penjab on pasien.kd_pj=penjab.kd_pj order by pasien.no_rkm_medis desc ",param);
                        }else{
                            Valid.MyReportqry("rptPasien.jasper","report","::[ Data Pasien Umum ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
                                "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
                                "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                                "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
                                "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                                "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                                "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                                "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                                "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
                                 "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%' and pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_ktp like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_peserta like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tmp_lahir like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tgl_lahir like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and penjab.png_jawab like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.alamat like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.gol_darah like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.pekerjaan like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.stts_nikah like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nip like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and cacat_fisik.nama_cacat like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.namakeluarga like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and perusahaan_pasien.nama_perusahaan like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and bahasa_pasien.nama_bahasa like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and suku_bangsa.nama_suku_bangsa like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.agama like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.nm_ibu like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.tgl_daftar like '%"+TCari.getText().trim()+"%' "+
                                 "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like '%"+Carialamat.getText().trim()+"%'  and pasien.no_tlp like '%"+TCari.getText().trim()+"%'   order by pasien.no_rkm_medis desc ",param);
                        }   
                    }
                }
                break;
            default:
                break;
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

private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        Carialamat.setText("");
        pilihantampil();
}//GEN-LAST:event_BtnAllActionPerformed

private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        kab.dispose();
        kec.dispose();
        kel.dispose();
        penjab.dispose();
        perusahaan.dispose();
        bahasa.dispose();
        cacat.dispose();
        suku.dispose();
        golongantni.dispose();
        satuantni.dispose();
        pangkattni.dispose();
        jabatantni.dispose();
        golonganpolri.dispose();
        satuanpolri.dispose();
        pangkatpolri.dispose();
        jabatanpolri.dispose();
        DlgDemografi.dispose();
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
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbPasien.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    pilihantampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

private void TTmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTmpKeyPressed
   Valid.pindah(evt,CMbGd,DTPLahir);
}//GEN-LAST:event_TTmpKeyPressed

private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
   Valid.pindah(evt,TNm,CMbGd);
}//GEN-LAST:event_CmbJkKeyPressed

private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TCari.setText(TNm.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TCari.setText(TNm.getText());
            if(TNm.getText().toLowerCase().contains("tn.")){
                CmbJk.setSelectedItem("LAKI-LAKI");
            }else if(TNm.getText().toLowerCase().contains("sdr.")){
                CmbJk.setSelectedItem("LAKI-LAKI");
            }else if(TNm.getText().toLowerCase().contains("ny.")){
                CmbJk.setSelectedItem("PEREMPUAN");
            }else if(TNm.getText().toLowerCase().contains("nn.")){
                CmbJk.setSelectedItem("PEREMPUAN");
            }
            CmbJk.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){          
            TNo.requestFocus();  
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){   
            TCari.requestFocus();  
        }
}//GEN-LAST:event_TNmKeyPressed

private void CMbGdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CMbGdKeyPressed
   Valid.pindah(evt,CmbJk,TTmp);
}//GEN-LAST:event_CMbGdKeyPressed

private void cmbAgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgamaKeyPressed
    Valid.pindah(evt,NmIbu,BtnPenjab);
}//GEN-LAST:event_cmbAgamaKeyPressed

private void PekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       TKtp.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       TTlp.requestFocus();
   }
}//GEN-LAST:event_PekerjaanKeyPressed

private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       if(Alamat.getText().equals("")){
           Alamat.setText("ALAMAT");
       }
       if(Kelurahan.getText().equals("KELURAHAN")){
           Kelurahan.setText("");
       }
       Kelurahan.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       if(Alamat.getText().equals("")){
           Alamat.setText("ALAMAT");
       }
       TKtp.requestFocus();
   }
}//GEN-LAST:event_AlamatKeyPressed

private void TTlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTlpKeyPressed
   Valid.pindah(evt,EMail,Pekerjaan);
}//GEN-LAST:event_TTlpKeyPressed

private void TNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TCari.setText(TNo.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TNm.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){   
            TCari.requestFocus();  
        }
}//GEN-LAST:event_TNoKeyPressed

private void TKtpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKtpKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(Alamat.getText().equals("ALAMAT")){
                Alamat.setText("");
            }
            Alamat.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Pekerjaan.requestFocus();
        }
}//GEN-LAST:event_TKtpKeyPressed

private void DTPDaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPDaftarKeyPressed
   Valid.pindah2(evt,Pekerjaan,BtnSimpan);
}//GEN-LAST:event_DTPDaftarKeyPressed

private void CMbPndKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CMbPndKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       NmIbu.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       TUmurTh.requestFocus();
   }
}//GEN-LAST:event_CMbPndKeyPressed

private void MnKartuStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuStatusActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptKartuPasien.jasper","report","::[ Kartu Periksa Pasien(Umum) ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_MnKartuStatusActionPerformed

private void DTPLahirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPLahirItemStateChanged
    lahir = DTPLahir.getDate();    
    birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    p = Period.between(birthday,today);
    TUmurTh.setText(String.valueOf(p.getYears()));
    TUmurBl.setText(String.valueOf(p.getMonths()));
    TUmurHr.setText(String.valueOf(p.getDays()));
}//GEN-LAST:event_DTPLahirItemStateChanged

private void KdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdpnjKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
           Sequel.cariIsi("select png_jawab from penjab where kd_pj=?",nmpnj,Kdpnj.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnPenjabActionPerformed(null);
        }else{
            Valid.pindah(evt,cmbAgama,TNoPeserta);
        }
}//GEN-LAST:event_KdpnjKeyPressed

private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
        akses.setform("DlgPasien");
        penjab.isCek();
        penjab.onCari();
        penjab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
}//GEN-LAST:event_BtnPenjabActionPerformed

private void MnKartu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu2ActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuBerobat.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_MnKartu2ActionPerformed

private void ppGrafikjkbayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikjkbayiActionPerformed
       grafikjkel kas=new grafikjkel("Grafik Jenis Kelamin Pasien "," ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikjkbayiActionPerformed

private void MnLaporanRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRMActionPerformed
      if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM2.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLaporanRMActionPerformed

private void MnLaporanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanIGDActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuIgd.jasper","report","::[ Laporan Rekam Medik ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLaporanIGDActionPerformed

private void ppKelahiranBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppKelahiranBayiActionPerformed
    if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgIKBBayi resume=new DlgIKBBayi(null,false);
            resume.setNoRM(TNo.getText(),TNm.getText(),NmIbu.getText(),Alamat.getText()+", "+Kelurahan.getText()+", "+Kecamatan.getText()+", "+Kabupaten.getText(),CmbJk.getSelectedItem().toString(),
                    TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",DTPLahir.getDate(),DTPDaftar.getDate());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_ppKelahiranBayiActionPerformed

private void MnLembarKeluarMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarKeluarMasukActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptLembarKeluarMasuk.jasper","report","::[ Ringkasan Masuk Keluar ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_MnLembarKeluarMasukActionPerformed

private void MnLembarAnamNesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarAnamNesaActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptLembarAnamnesi.jasper","report","::[ Lembar Anamnesa ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLembarAnamNesaActionPerformed

private void MnLembarGrafikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarGrafikActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptLembarGrafik.jasper","report","::[ Lembar Grafik ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "on pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLembarGrafikActionPerformed

private void MnLembarCatatanPerkembanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCatatanPerkembanganActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptLembarPerkembangan.jasper","report","::[ Lembar Catatan Perkembangan ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and  pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLembarCatatanPerkembanganActionPerformed

private void MnLembarCatatanKeperawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCatatanKeperawatanActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptLembarPerkembangan.jasper","report","::[ Lembar Catatan Keperawatan ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and  pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLembarCatatanKeperawatanActionPerformed

private void MnLaporanAnestesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanAnestesiaActionPerformed
     if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptLaporanAnestesia.jasper","report","::[ Lembar Catatan Keperawatan ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and  pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
}//GEN-LAST:event_MnLaporanAnestesiaActionPerformed

private void CarialamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CarialamatKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
            TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){          
            BtnCariActionPerformed(null);
            TNo.requestFocus();
        }
}//GEN-LAST:event_CarialamatKeyPressed

private void MnKartu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu3ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuBerobat2.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_MnKartu3ActionPerformed

private void KelurahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       if(Kelurahan.getText().equals("")){
           Kelurahan.setText("KELURAHAN");
       }
       if(Kecamatan.getText().equals("KECAMATAN")){
           Kecamatan.setText("");
       }
       Kecamatan.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       if(Kelurahan.getText().equals("")){
           Kelurahan.setText("KELURAHAN");
       }
       if(Alamat.getText().equals("ALAMAT")){
          Alamat.setText("");
       }     
       Alamat.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_UP){
       if(KdKec.getText().trim().equals("")||Kecamatan.getText().trim().equals("")||Kecamatan.getText().trim().equals("KECAMATAN")){
            JOptionPane.showMessageDialog(null,"Silahkan pilih kecamatan dulu..!!");
            BtnKelurahan.requestFocus();
        }else{
            pilih=1;
            kelurahanref.setPropinsi(KdKec.getText(),Kecamatan.getText());
            kelurahanref.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            kelurahanref.setLocationRelativeTo(internalFrame1);
            kelurahanref.setVisible(true);
        }
   }
}//GEN-LAST:event_KelurahanKeyPressed

private void KecamatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       if(Kecamatan.getText().equals("")){
           Kecamatan.setText("KECAMATAN");
       }
       if(Kabupaten.getText().equals("KABUPATEN")){
           Kabupaten.setText("");
       }
       Kabupaten.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       if(Kecamatan.getText().equals("")){
           Kecamatan.setText("KECAMATAN");
       }
       if(Kelurahan.getText().equals("KELURAHAN")){
          Kelurahan.setText("");
       }     
       Kelurahan.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_UP){
       if(KdKab.getText().trim().equals("")||Kabupaten.getText().trim().equals("")||Kabupaten.getText().trim().equals("KABUPATEN")){
            JOptionPane.showMessageDialog(null,"Silahkan pilih kabupaten dulu..!!");
            BtnKabupaten.requestFocus();
        }else{
            pilih=1;
            kecamatanref.setPropinsi(KdKab.getText(),Kabupaten.getText());
            kecamatanref.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            kecamatanref.setLocationRelativeTo(internalFrame1);
            kecamatanref.setVisible(true);
        }
   }
}//GEN-LAST:event_KecamatanKeyPressed

private void KabupatenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       if(Kabupaten.getText().equals("")){
           Kabupaten.setText("KABUPATEN");
       }
       if(Propinsi.getText().equals("PROPINSI")){
           Propinsi.setText("");
       }
       Propinsi.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
       if(Kabupaten.getText().equals("")){
           Kabupaten.setText("KABUPATEN");
       }
       if(Kecamatan.getText().equals("KECAMATAN")){
          Kecamatan.setText("");
       }     
       Kecamatan.requestFocus();
   }else if(evt.getKeyCode()==KeyEvent.VK_UP){
        if(KdProp.getText().trim().equals("")||Propinsi.getText().trim().equals("")||Propinsi.getText().trim().equals("PROPINSI")){
            JOptionPane.showMessageDialog(null,"Silahkan pilih propinsi dulu..!!");
            BtnPropinsi.requestFocus();
        }else{
            pilih=1;
            kabupatenref.setPropinsi(KdProp.getText(),Propinsi.getText());
            kabupatenref.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            kabupatenref.setLocationRelativeTo(internalFrame1);
            kabupatenref.setVisible(true);
        }
   }
}//GEN-LAST:event_KabupatenKeyPressed

private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
       akses.setform("DlgPasien");
       pilih=1;
        kel.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
}//GEN-LAST:event_BtnKelurahanActionPerformed

private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        akses.setform("DlgPasien");
        pilih=1;
        kec.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
}//GEN-LAST:event_BtnKecamatanActionPerformed

private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        akses.setform("DlgPasien");
        pilih=1;
        kab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
}//GEN-LAST:event_BtnKabupatenActionPerformed

private void ppGrafikDemografiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikDemografiActionPerformed
        DlgDemografi.setSize(550,210);
        DlgDemografi.setLocationRelativeTo(internalFrame1);
        DlgDemografi.setVisible(true);
}//GEN-LAST:event_ppGrafikDemografiActionPerformed

private void ppGrafikPerAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerAgamaActionPerformed
       grafikpasienperagama kas=new grafikpasienperagama("Grafik Pasien Per Agama "," ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerAgamaActionPerformed

private void ppGrafikPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerPekerjaanActionPerformed
       grafikpasienperpekerjaaan kas=new grafikpasienperpekerjaaan("Grafik Pasien Per Pekerjaan "," ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerPekerjaanActionPerformed

private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
            if(!Kelurahan2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText()+" ]::",
                        " pasien inner join kabupaten inner join kecamatan inner join kelurahan inner join propinsi on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                        " and pasien.kd_prop=propinsi.kd_prop where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' and kelurahan.nm_kel='"+Kelurahan2.getText()+"' and propinsi.nm_prop='"+Propinsi2.getText()+"' ", 
                        "pasien.alamat","Area");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(!Kecamatan2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+" Kabupaten "+Kabupaten2.getText()+" ]::",
                        " pasien inner join kabupaten inner join kecamatan inner join kelurahan inner join propinsi on pasien.kd_kab=kabupaten.kd_kab "+
                         "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                         "and propinsi.kd_prop=pasien.kd_prop where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' and propinsi.nm_prop='"+Propinsi2.getText()+"' ", 
                         "kelurahan.nm_kel","Kelurahan");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(!Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Per Kecamatan Kabupaten "+Kabupaten2.getText()+" ]::",
                         " pasien inner join kabupaten inner join kecamatan inner join propinsi on pasien.kd_kab=kabupaten.kd_kab "+
                         "and pasien.kd_kec=kecamatan.kd_kec and propinsi.kd_prop=pasien.kd_prop where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and propinsi.nm_prop='"+Propinsi2.getText()+"' ", 
                         "kecamatan.nm_kec","Kecamatan");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(!Propinsi2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Propinsi ]::",
                         " pasien inner join propinsi on pasien.kd_prop=propinsi.kd_prop where propinsi.nm_prop='"+Propinsi2.getText()+"' ", 
                          "propinsi.nm_prop","Propinsi");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(Propinsi2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Propinsi ]::",
                         " pasien inner join propinsi on pasien.kd_prop=propinsi.kd_prop ", 
                          "propinsi.nm_prop","Propinsi");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }  
}//GEN-LAST:event_BtnPrint2ActionPerformed

private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
   DlgDemografi.dispose();
}//GEN-LAST:event_BtnKeluar2ActionPerformed

private void BtnSeek8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek8ActionPerformed
   if(Kecamatan2.getText().equals("")){
       Valid.textKosong(Kecamatan2,"Kecamatan");
   }else{
       akses.setform("DlgPasien");
       pilih=2;
        kel.emptTeks();
        kel.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
   }       
}//GEN-LAST:event_BtnSeek8ActionPerformed

private void BtnSeek9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek9ActionPerformed
   if(Kabupaten2.getText().equals("")){
       Valid.textKosong(Kabupaten2,"Kabupaten");
   }else{
       akses.setform("DlgPasien");
       pilih=2;
        kec.emptTeks();
        kec.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
   }       
}//GEN-LAST:event_BtnSeek9ActionPerformed

private void BtnSeek10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek10ActionPerformed
    if(Propinsi2.getText().equals("")){
       Valid.textKosong(Propinsi2,"Propinsi");
    }else{    
        akses.setform("DlgPasien");
        pilih=2;
        kab.emptTeks();
        kab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
    }           
}//GEN-LAST:event_BtnSeek10ActionPerformed

private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            if(!Kelurahan2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText());
                data.put("area","Area");
                data.put("namars",akses.getnamars());
                data.put("alamatrs",akses.getalamatrs());
                data.put("kotars",akses.getkabupatenrs());
                data.put("propinsirs",akses.getpropinsirs());
                data.put("kontakrs",akses.getkontakrs());
                data.put("emailrs",akses.getemailrs());
                data.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDemografi.jasper","report","::[ Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select  pasien.alamat as area,count(pasien.alamat) as jumlah from pasien "+
                   "inner join kabupaten inner join kecamatan inner join kelurahan inner join propinsi "+
                   "on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_prop=propinsi.kd_prop "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                   "where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' "+
                   "and kelurahan.nm_kel='"+Kelurahan2.getText()+"' and propinsi.nm_prop='"+Propinsi2.getText()+"' "+
                   "group by pasien.alamat order by pasien.alamat",data);
            }else if(!Kecamatan2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText());
                data.put("area","Kelurahan");
                data.put("namars",akses.getnamars());
                data.put("alamatrs",akses.getalamatrs());
                data.put("kotars",akses.getkabupatenrs());
                data.put("propinsirs",akses.getpropinsirs());
                data.put("kontakrs",akses.getkontakrs());
                data.put("emailrs",akses.getemailrs());
                data.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDemografi.jasper","report","::[ Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+" Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select kelurahan.nm_kel as area,count(kelurahan.nm_kel) as jumlah from pasien "+
                   "inner join kabupaten inner join kecamatan inner join kelurahan inner join propinsi "+
                   "on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_prop=propinsi.kd_prop "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                   "where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' "+
                   "and propinsi.nm_prop='"+Propinsi2.getText()+"' group by kelurahan.nm_kel order by kelurahan.nm_kel",data);
            }else if(!Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Kecamatan Kabupaten "+Kabupaten2.getText());
                data.put("area","Kecamatan");
                data.put("namars",akses.getnamars());
                data.put("alamatrs",akses.getalamatrs());
                data.put("kotars",akses.getkabupatenrs());
                data.put("propinsirs",akses.getpropinsirs());
                data.put("kontakrs",akses.getkontakrs());
                data.put("emailrs",akses.getemailrs());
                data.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDemografi.jasper","report","::[ Data Per Kecamatan Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select kecamatan.nm_kec as area,count(kecamatan.nm_kec) as jumlah from pasien "+
                   "inner join kabupaten inner join kecamatan inner join propinsi "+
                   "on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_prop=propinsi.kd_prop "+
                   "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='"+Kabupaten2.getText()+"' "+
                   "and propinsi.nm_prop='"+Propinsi2.getText()+"' group by kecamatan.nm_kec order by kecamatan.nm_kec",data);
            }else if(!Propinsi2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Propinsi");
                data.put("area","Propinsi");
                data.put("namars",akses.getnamars());
                data.put("alamatrs",akses.getalamatrs());
                data.put("kotars",akses.getkabupatenrs());
                data.put("propinsirs",akses.getpropinsirs());
                data.put("kontakrs",akses.getkontakrs());
                data.put("emailrs",akses.getemailrs());
                data.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDemografi.jasper","report","::[ Data Demografi Per Propinsi ]::","select propinsi.nm_prop as area,count(propinsi.nm_prop) as jumlah from pasien "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where propinsi.nm_prop='"+Propinsi2.getText()+"' group by propinsi.nm_prop order by propinsi.nm_prop",data);
            }else if(Propinsi2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Propinsi");
                data.put("area","Propinsi");
                data.put("namars",akses.getnamars());
                data.put("alamatrs",akses.getalamatrs());
                data.put("kotars",akses.getkabupatenrs());
                data.put("propinsirs",akses.getpropinsirs());
                data.put("kontakrs",akses.getkontakrs());
                data.put("emailrs",akses.getemailrs());
                data.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptDemografi.jasper","report","::[ Data Demografi Per Propinsi ]::","select propinsi.nm_prop as area,count(propinsi.nm_prop) as jumlah from pasien "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop group by propinsi.nm_prop order by propinsi.nm_prop",data);
            }                         
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrint3ActionPerformed

private void ppRegistrasiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRegistrasiBtnPrintActionPerformed
     prosesCari2();
}//GEN-LAST:event_ppRegistrasiBtnPrintActionPerformed

private void AlamatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseExited
        if(Alamat.getText().equals("")){
            Alamat.setText("ALAMAT");
        }
}//GEN-LAST:event_AlamatMouseExited

private void KelurahanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanMouseExited
        if(Kelurahan.getText().equals("")){
            Kelurahan.setText("KELURAHAN");
        }
}//GEN-LAST:event_KelurahanMouseExited

private void KecamatanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanMouseExited
        if(Kecamatan.getText().equals("")){
            Kecamatan.setText("KECAMATAN");
        }
}//GEN-LAST:event_KecamatanMouseExited

private void KabupatenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenMouseExited
       if(Kabupaten.getText().equals("")){
            Kabupaten.setText("KABUPATEN");
        }
}//GEN-LAST:event_KabupatenMouseExited

private void AlamatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseMoved
        if(Alamat.getText().equals("ALAMAT")){
            Alamat.setText("");
        }
}//GEN-LAST:event_AlamatMouseMoved

private void KelurahanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanMouseMoved
        if(Kelurahan.getText().equals("KELURAHAN")){
            Kelurahan.setText("");
        }
}//GEN-LAST:event_KelurahanMouseMoved

private void KecamatanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanMouseMoved
        if(Kecamatan.getText().equals("KECAMATAN")){
            Kecamatan.setText("");
        }
}//GEN-LAST:event_KecamatanMouseMoved

private void KabupatenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenMouseMoved
        if(Kabupaten.getText().equals("KABUPATEN")){
            Kabupaten.setText("");
        }
}//GEN-LAST:event_KabupatenMouseMoved

    private void ChkDaftarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkDaftarItemStateChanged
        if(ChkDaftar.isSelected()==true){
            DTPDaftar.setEditable(true);
        }else if(ChkDaftar.isSelected()==false){
            DTPDaftar.setEditable(false);            
        }
        DTPDaftar.requestFocus();
    }//GEN-LAST:event_ChkDaftarItemStateChanged

    private void MnIdentitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitasActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{     
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM1.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitasActionPerformed

    private void NmIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmIbuKeyPressed
        Valid.pindah(evt,CMbPnd,cmbAgama);
    }//GEN-LAST:event_NmIbuKeyPressed

    private void NoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRmKeyPressed

    private void MnPengantarHemodalisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengantarHemodalisaActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            String nip=Sequel.cariIsi("select kd_dokterhemodialisa from set_pjlab");
            param.put("dokter",Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='"+nip+"'"));   
            param.put("nipdokter",nip);   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM3.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and  pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
    }//GEN-LAST:event_MnPengantarHemodalisaActionPerformed

    private void TNoPesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoPesertaKeyPressed
        Valid.pindah(evt,Kdpnj,EMail);
    }//GEN-LAST:event_TNoPesertaKeyPressed

    private void MnBarcodeRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRMActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRMActionPerformed

    private void MnBarcodeRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM2.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM2ActionPerformed

    private void ChkRMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRMItemStateChanged
        if(ChkRM.isSelected()==true){
            TNo.setEditable(false);
            TNo.setBackground(new Color(245,250,240));  
            autoNomor();
        }else if(ChkRM.isSelected()==false){
            TNo.setEditable(true);
            TNo.setBackground(new Color(250,255,245));
        }
    }//GEN-LAST:event_ChkRMItemStateChanged

    private void MnCekKepesertaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekKepesertaanActionPerformed
        if(!TNoPeserta.getText().equals("")){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSPeserta form=new BPJSPeserta(null, true);
            form.tampil(TNoPeserta.getText());
            form.setSize(640,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, Nomor kepesertaan kosong...!!!");
        }
            
    }//GEN-LAST:event_MnCekKepesertaanActionPerformed

    private void MnCekNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNIKActionPerformed
        if(!TKtp.getText().equals("")){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSNik form=new BPJSNik(null, true);
            form.tampil(TKtp.getText());
            form.setSize(640,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, NIK KTP kosong...!!!");
        }
    }//GEN-LAST:event_MnCekNIKActionPerformed

    private void MnBarcodeRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM3.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM1ActionPerformed

    private void MnBarcodeRM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM3ActionPerformed
                if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM9.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM3ActionPerformed

    private void MnKartu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuBerobat5.jasper","report","::[ Kartu Periksa Pasien ]::","select * from pasien where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartu1ActionPerformed

    private void MnBarcodeRM4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM4ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM11.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM4ActionPerformed

    private void MnBarcodeRM5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM5ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM12.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM5ActionPerformed

    private void MnBarcodeRM6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM6ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM13.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM6ActionPerformed

    private void MnViaBPJSNikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaBPJSNikActionPerformed
        if(TKtp.getText().trim().equals("")){
            TKtp.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK/No.KTP..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaBPJS.tampil(TKtp.getText());
            TNm.setText(cekViaBPJS.nama);
            CmbJk.setSelectedItem(cekViaBPJS.sex);
            TNoPeserta.setText(cekViaBPJS.noKartu);
            Pekerjaan.setText(cekViaBPJS.jenisPesertaketerangan);
            TUmurTh.setText(cekViaBPJS.umurumurSekarang);
            Valid.SetTgl(DTPLahir,cekViaBPJS.tglLahir);
            this.setCursor(Cursor.getDefaultCursor());
        }
            
    }//GEN-LAST:event_MnViaBPJSNikActionPerformed

    private void MnViaBPJSNoKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaBPJSNoKartuActionPerformed
        if(TNoPeserta.getText().trim().equals("")){
            TNoPeserta.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu No.Peserta..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaBPJSKartu.tampil(TNoPeserta.getText());
            TNm.setText(cekViaBPJSKartu.nama);
            CmbJk.setSelectedItem(cekViaBPJSKartu.sex);
            TKtp.setText(cekViaBPJSKartu.nik);
            Pekerjaan.setText(cekViaBPJSKartu.jenisPesertaketerangan);
            TUmurTh.setText(cekViaBPJSKartu.umurumurSekarang);
            Valid.SetTgl(DTPLahir,cekViaBPJSKartu.tglLahir);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnViaBPJSNoKartuActionPerformed

    private void MnLaporanRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRM1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM4.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanRM1ActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMRiwayatPerawatan resume=new RMRiwayatPerawatan(null,true);
            resume.setNoRm(TNo.getText(),TNm.getText());
            resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void MnIdentitas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{     
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM5.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj,pasien.no_peserta from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas2ActionPerformed

    private void MnLaporanRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRM2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM6.jasper","report","::[ Lembar Rawat Jalan ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanRM2ActionPerformed

    private void MnFormulirPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirPendaftaranActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM7.jasper","report","::[ Formulir Pendaftaran ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnFormulirPendaftaranActionPerformed

    private void MnSCreeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSCreeningActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM8.jasper","report","::[ Screening Awal ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSCreeningActionPerformed

    private void MnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyResepActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM9.jasper","report","::[ Copy Resep ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCopyResepActionPerformed

    private void MnKartu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu4ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuBerobat6.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartu4ActionPerformed

    private void MnLembarKeluarMasuk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarKeluarMasuk2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptLembarKeluarMasuk2.jasper","report","::[ Ringkasan Masuk Keluar ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarKeluarMasuk2ActionPerformed

    private void MnIdentitas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas3ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{     
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM10.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas3ActionPerformed

    private void MnBarcodeRM7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM7ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM15.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM7ActionPerformed

    private void TUmurThKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurThKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //       CmbUmur.requestFocus();
            try {
                Valid.SetTgl(DTPLahir,Sequel.cariIsi("select DATE_SUB('"+Valid.SetTgl(DTPLahir.getSelectedItem()+"")+"', interval "+TUmurTh.getText()+" year)"));
            } catch (Exception e) {
                System.out.println(e);
            }
            TUmurBl.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DTPLahir.requestFocus();
        }
    }//GEN-LAST:event_TUmurThKeyPressed

    private void TUmurBlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurBlKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TUmurHr.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TUmurTh.requestFocus();
        }
    }//GEN-LAST:event_TUmurBlKeyPressed

    private void TUmurHrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurHrKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           CMbPnd.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TUmurBl.requestFocus();
        }
    }//GEN-LAST:event_TUmurHrKeyPressed

    private void DTPLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPLahirKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){            
             TUmurTh.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
             TTmp.requestFocus();
        }
    }//GEN-LAST:event_DTPLahirKeyPressed

    private void MnKartu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu5ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs()); 
            Valid.MyReportqry("rptKartuBerobat7.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,pasien.nm_ibu,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartu5ActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan=new DlgCatatan(null,true);
            catatan.setNoRm(TNo.getText());
            catatan.setSize(720,330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void MnCoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCoverActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptCoverMap.jasper","report","::[ Cover Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,pasien.no_peserta,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
    }//GEN-LAST:event_MnCoverActionPerformed

    private void ppGabungRMBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGabungRMBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else if(TNo.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien yang mau digabung data rekam medisnya...!!!");
            TCari.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien yang mau digabung data rekam medisnya...!!!");
            TCari.requestFocus();
        }else{
            NoRmTujuan.requestFocus();
            NoRmTujuan.setText("");
            NmPasienTujuan.setText("");
            WindowGabungRM.setSize(430,130);
            WindowGabungRM.setLocationRelativeTo(internalFrame1);
            WindowGabungRM.setAlwaysOnTop(false);
            WindowGabungRM.setVisible(true);
        }
    }//GEN-LAST:event_ppGabungRMBtnPrintActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowGabungRM.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if(NoRmTujuan.getText().trim().equals("")){
            Valid.textKosong(NoRmTujuan,"No.R.M Tujuan masih kosong");
        }else if(NmPasienTujuan.getText().trim().equals("")){
            Valid.textKosong(NmPasienTujuan,"Nama Pasien Tujuan");
        }else if(NoRmTujuan.getText().trim().equals(TNo.getText().trim())){
            JOptionPane.showMessageDialog(rootPane,"No.R.M dan No.R.M. tujuan tidak boleh sama...!!");
        }else{
            Sequel.mengedit("bayar_piutang","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("booking_periksa_diterima","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("booking_registrasi","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("bridging_dukcapil","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("catatan_pasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("diagnosa_corona","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pasien_bayi","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pasien_corona","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pasien_mati","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pasien_polri","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pasien_tni","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pcare_peserta_kegiatan_kelompok","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("peminjaman_berkas","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("pengaduan","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("penjualan","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("personal_pasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("piutang","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("piutang_pasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("referensi_mobilejkn_bpjs_batal","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("reg_periksa","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("retensi_pasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("returjual","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("riwayat_imunisasi","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("riwayat_persalinan_pasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("returpiutang","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("rujukanranap_dokter_rs","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("sidikjaripasien","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("skdp_bpjs","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("skrining_rawat_jalan","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.mengedit("tagihan_bpd_jateng","no_rkm_medis=?","no_rkm_medis=?",2,new String[]{
                NoRmTujuan.getText(),TNo.getText()
            });
            Sequel.meghapus("pasien","no_rkm_medis",TNo.getText());
            tampil();
            emptTeks();
            WindowGabungRM.dispose();
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoRmTujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmTujuanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(!NoRmTujuan.getText().trim().equals("")){
                Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=?",NmPasienTujuan,NoRmTujuan.getText());
                if(NmPasienTujuan.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(rootPane,"Data pasien tidak ditemukan..!!");
                }
                NoRmTujuan.requestFocus();
            }
        }
    }//GEN-LAST:event_NoRmTujuanKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=?",NmPasienTujuan,NoRmTujuan.getText());
        if(NmPasienTujuan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Data pasien tidak ditemukan..!!");
        }
        NoRmTujuan.requestFocus();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void MnViaDukcapilNikDKIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaDukcapilNikDKIActionPerformed
        if(TKtp.getText().trim().equals("")){
            TKtp.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK/No.KTP..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(TKtp.getText());
            Pekerjaan.setText(cekViaDukcapilJakarta.DSC_JENIS_PKRJN);
            TNm.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            TTmp.setText(cekViaDukcapilJakarta.TMPT_LHR);
            Kecamatan.setText(cekViaDukcapilJakarta.NM_KEC);
            Kabupaten.setText(cekViaDukcapilJakarta.NM_KAB);
            Alamat.setText(cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW);
            Kelurahan.setText(cekViaDukcapilJakarta.NM_KEL);
            CmbJk.setSelectedItem(cekViaDukcapilJakarta.JENIS_KLMIN);
            CMbGd.setSelectedItem(cekViaDukcapilJakarta.DSC_GOL_DRH); 
            Valid.SetTgl(DTPLahir,cekViaDukcapilJakarta.TGL_LHR);   
            DTPLahirItemStateChanged(null);  
            this.setCursor(Cursor.getDefaultCursor());
            if((Kelurahan.isEditable()==false)||(Kecamatan.isEditable()==false)||(Kabupaten.isEditable()==false)||(Propinsi.isEditable()==false)){
                JOptionPane.showMessageDialog(null,"Pengaturan Kelurahan, Kecamatan, Kabupaten dan Propinsi harus diaktifkan terlebih dahulu di Set RM agar data mau disimpan..");
            }
        }
            
    }//GEN-LAST:event_MnViaDukcapilNikDKIActionPerformed

    private void MnBarcodeRM8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM8ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM17.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM8ActionPerformed

    private void MnBarcodeRM9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM9ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptBarcodeRM18.jasper","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM9ActionPerformed

    private void MnViaDukcapilNikAcehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaDukcapilNikAcehActionPerformed
        if(TKtp.getText().trim().equals("")){
            TKtp.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK/No.KTP..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilAceh.tampil(TKtp.getText());
            //System.out.println("Nama : "+cekViaDukcapilAceh.NAMA_LGKP);
            TNm.setText(cekViaDukcapilAceh.NAMA_LGKP);
            Kabupaten.setText(cekViaDukcapilAceh.KAB_NAME);
            cmbAgama.setSelectedItem(cekViaDukcapilAceh.AGAMA);
            Propinsi.setText(cekViaDukcapilAceh.PROP_NAME);
            Kecamatan.setText(cekViaDukcapilAceh.KEC_NAME);
            Pekerjaan.setText(cekViaDukcapilAceh.JENIS_PKRJN);
            Alamat.setText(cekViaDukcapilAceh.ALAMAT+" RT "+cekViaDukcapilAceh.NO_RT+" RW "+cekViaDukcapilAceh.NO_RW);
            TTmp.setText(cekViaDukcapilAceh.TMPT_LHR);
            CMbGd.setSelectedItem(cekViaDukcapilAceh.GOL_DARAH);
            CMbPnd.setSelectedItem(cekViaDukcapilAceh.PDDK_AKH);
            NmIbu.setText(cekViaDukcapilAceh.NAMA_LGKP_IBU);
            Kelurahan.setText(cekViaDukcapilAceh.KEL_NAME);
            CmbJk.setSelectedItem(cekViaDukcapilAceh.JENIS_KLMIN.toUpperCase());
            Valid.SetTgl(DTPLahir,cekViaDukcapilAceh.TGL_LHR);   
            DTPLahirItemStateChanged(null);    
            this.setCursor(Cursor.getDefaultCursor());
            if((Kelurahan.isEditable()==false)||(Kecamatan.isEditable()==false)||(Kabupaten.isEditable()==false)||(Propinsi.isEditable()==false)){
                JOptionPane.showMessageDialog(null,"Pengaturan Kelurahan, Kecamatan, Kabupaten dan Propinsi harus diaktifkan terlebih dahulu di Set RM agar data mau disimpan..");
            }
        }
    }//GEN-LAST:event_MnViaDukcapilNikAcehActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(tampilkantni.equals("Yes")){
            if(this.getHeight()<670){   
                Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                FormInput.setPreferredSize(new Dimension(FormInput.WIDTH,560));
                if(this.getWidth()<900){
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
                    FormInput.setPreferredSize(new Dimension(890,560));
                }else{
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
                }
            }else{
                Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);            
                if(this.getWidth()<900){
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
                    FormInput.setPreferredSize(new Dimension(890,FormInput.HEIGHT));
                }else{
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
                }
            }
        }else{
            if(this.getHeight()<560){   
                Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                FormInput.setPreferredSize(new Dimension(FormInput.WIDTH,410));
                if(this.getWidth()<900){
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
                    FormInput.setPreferredSize(new Dimension(890,410));
                }else{
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
                }
            }else{
                Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);            
                if(this.getWidth()<900){
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
                    FormInput.setPreferredSize(new Dimension(890,FormInput.HEIGHT));
                }else{
                    Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
                }
            }
        }            
    }//GEN-LAST:event_formWindowActivated

    private void kdperusahaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdperusahaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdperusahaanKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
//        if(TabRawat.getSelectedIndex()==0){
//            PanelAccor.setVisible(false);
//        }else{
//            PanelAccor.setVisible(true);
//            ChkAccor.setSelected(false);
//            isPhoto();
//        }
//        if(!TCari.getText().equals("")){
//            pilihantampil();
//        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnPropinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPropinsiActionPerformed
        akses.setform("DlgPasien");
        pilih=1;
        prop.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        prop.setLocationRelativeTo(internalFrame1);
        prop.setVisible(true);
    }//GEN-LAST:event_BtnPropinsiActionPerformed

    private void PropinsiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropinsiMouseMoved
        if(Propinsi.getText().equals("PROPINSI")){
            Propinsi.setText("");
        }
    }//GEN-LAST:event_PropinsiMouseMoved

    private void PropinsiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropinsiMouseExited
        if(Propinsi.getText().equals("")){
            Propinsi.setText("PROPINSI");
        }
    }//GEN-LAST:event_PropinsiMouseExited

    private void PropinsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PropinsiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(Propinsi.getText().equals("")){
                Propinsi.setText("PROPINSI");
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            if(Propinsi.getText().equals("")){
                Propinsi.setText("PROPINSI");
            }
            if(Kabupaten.getText().equals("KABUPATEN")){
               Kabupaten.setText("");
            }     
            Kabupaten.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            pilih=1;
            propinsiref.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            propinsiref.setLocationRelativeTo(internalFrame1);
            propinsiref.setVisible(true);
        }
    }//GEN-LAST:event_PropinsiKeyPressed

    private void EMailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EMailKeyPressed
        Valid.pindah(evt,TNoPeserta,TTlp);
    }//GEN-LAST:event_EMailKeyPressed

    private void kdcacatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdcacatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdcacatKeyPressed

    private void BtnSeek11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek11ActionPerformed
        akses.setform("DlgPasien");
        pilih=2;
        prop.emptTeks();
        prop.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        prop.setLocationRelativeTo(internalFrame1);
        prop.setVisible(true); 
    }//GEN-LAST:event_BtnSeek11ActionPerformed

    private void MnCekKepesertaan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekKepesertaan1ActionPerformed
        if(!TNoPeserta.getText().equals("")){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            PCarePeserta form=new PCarePeserta(null, true);
            form.tampil(TNoPeserta.getText());
            form.setSize(640,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, Nomor kepesertaan kosong...!!!");
        }
    }//GEN-LAST:event_MnCekKepesertaan1ActionPerformed

    private void MnCekNIK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNIK1ActionPerformed
        if(!TKtp.getText().equals("")){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            PCareNIK form=new PCareNIK(null, true);
            form.tampil(TKtp.getText());
            form.setSize(640,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, NIK KTP kosong...!!!");
        }
    }//GEN-LAST:event_MnCekNIK1ActionPerformed

    private void tbPasienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienKeyReleased

    private void MnIdentitas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas4ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{     
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());  
            param.put("petugas",akses.getnamauser());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptIdentitasPasien.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas4ActionPerformed

    private void MnLaporanRM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRM3ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
//            param.put("suku",nmsukubangsa.getText());
//            param.put("bahasa",nmbahasa.getText());
//            param.put("cacat",nmcacat.getText());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptRM13.jasper","report","::[ Identitas Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanRM3ActionPerformed

    private void MnCover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCover1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptCoverMap2.jasper","report","::[ Cover Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,pasien.no_peserta,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten inner join suku_bangsa "+
                   "inner join penjab inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
        }
    }//GEN-LAST:event_MnCover1ActionPerformed

    private void MnKartu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartu6ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs()); 
            Valid.MyReportqry("rptKartuBerobat8.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,pasien.nm_ibu,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga, pasien.no_peserta from pasien inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join propinsi on pasien.kd_prop=propinsi.kd_prop and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='"+TNo.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartu6ActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        }else if(TNm.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form=new CoronaPasien(null,false);
            form.setPasien(TNo.getText());
            form.isCek();
            form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void ppRegistrasi1BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRegistrasi1BtnPrintActionPerformed
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
               "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
               "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
               "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
               "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
               "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
               "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
               "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
               "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
               "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
               "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
               "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
               "where no_rkm_medis not in(select no_rkm_medis from reg_periksa where tgl_registrasi between DATE_SUB(current_date(), INTERVAL 5 YEAR) and current_date()) "+
               "order by pasien.no_rkm_medis desc");  
            try{
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20),"Klik Kanan, Tampilkan Banyak Daftar",rs.getString(21),rs.getString(22),
                        rs.getString("suku_bangsa"),rs.getString("nama_suku_bangsa"),rs.getString("bahasa_pasien"),
                        rs.getString("nama_bahasa"),rs.getString("kode_perusahaan"),rs.getString("nama_perusahaan"),
                        rs.getString("nip"),rs.getString("email"),rs.getString("cacat_fisik"),rs.getString("nama_cacat")
                    });
                }          
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
            
        LCount.setText(""+tabMode.getRowCount());
    }//GEN-LAST:event_ppRegistrasi1BtnPrintActionPerformed

    private void ppRegistrasi2BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRegistrasi2BtnPrintActionPerformed
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
               "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,"+
               "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
               "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
               "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj,', ',pasien.propinsipj),"+
               "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
               "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik from pasien "+
               "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
               "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
               "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
               "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
               "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
               "where no_rkm_medis in(select no_rkm_medis from retensi_pasien) "+
               "order by pasien.no_rkm_medis desc");  
            try{
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20),"Klik Kanan, Tampilkan Banyak Daftar",rs.getString(21),rs.getString(22),
                        rs.getString("suku_bangsa"),rs.getString("nama_suku_bangsa"),rs.getString("bahasa_pasien"),
                        rs.getString("nama_bahasa"),rs.getString("kode_perusahaan"),rs.getString("nama_perusahaan"),
                        rs.getString("nip"),rs.getString("email"),rs.getString("cacat_fisik"),rs.getString("nama_cacat")
                    });
                }          
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
            
        LCount.setText(""+tabMode.getRowCount());
    }//GEN-LAST:event_ppRegistrasi2BtnPrintActionPerformed

    /**
     * @data args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgPasienPRB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgPasienPRB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgPasienPRB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgPasienPRB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DlgPasienPRB dialog = new DlgPasienPRB(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private javax.swing.JMenu Barcode;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPenjab;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint2;
    private widget.Button BtnPrint3;
    private widget.Button BtnPropinsi;
    private widget.Button BtnSeek10;
    private widget.Button BtnSeek11;
    private widget.Button BtnSeek8;
    private widget.Button BtnSeek9;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan6;
    private widget.ComboBox CMbGd;
    private widget.ComboBox CMbPnd;
    private widget.TextBox Carialamat;
    private widget.CekBox ChkDaftar;
    private widget.CekBox ChkRM;
    private widget.ComboBox CmbJk;
    private widget.Tanggal DTPDaftar;
    private widget.Tanggal DTPLahir;
    private javax.swing.JDialog DlgDemografi;
    private widget.TextBox EMail;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Kabupaten;
    private widget.TextBox Kabupaten2;
    private javax.swing.JMenu KartuPasien;
    private widget.TextBox Kd2;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdKel;
    private widget.TextBox KdProp;
    private widget.TextBox Kdpnj;
    private widget.TextBox Kecamatan;
    private widget.TextBox Kecamatan2;
    private widget.TextBox Kelurahan;
    private widget.TextBox Kelurahan2;
    private widget.Label LCount;
    private javax.swing.JMenu MenuBPJS;
    private javax.swing.JMenu MenuIdentitas;
    private javax.swing.JMenuItem MnBarcodeRM;
    private javax.swing.JMenuItem MnBarcodeRM1;
    private javax.swing.JMenuItem MnBarcodeRM2;
    private javax.swing.JMenuItem MnBarcodeRM3;
    private javax.swing.JMenuItem MnBarcodeRM4;
    private javax.swing.JMenuItem MnBarcodeRM5;
    private javax.swing.JMenuItem MnBarcodeRM6;
    private javax.swing.JMenuItem MnBarcodeRM7;
    private javax.swing.JMenuItem MnBarcodeRM8;
    private javax.swing.JMenuItem MnBarcodeRM9;
    private javax.swing.JMenuItem MnCekKepesertaan;
    private javax.swing.JMenuItem MnCekKepesertaan1;
    private javax.swing.JMenuItem MnCekNIK;
    private javax.swing.JMenuItem MnCekNIK1;
    private javax.swing.JMenuItem MnCopyResep;
    private javax.swing.JMenuItem MnCover;
    private javax.swing.JMenuItem MnCover1;
    private javax.swing.JMenuItem MnFormulirPendaftaran;
    private javax.swing.JMenuItem MnIdentitas;
    private javax.swing.JMenuItem MnIdentitas2;
    private javax.swing.JMenuItem MnIdentitas3;
    private javax.swing.JMenuItem MnIdentitas4;
    private javax.swing.JMenuItem MnKartu1;
    private javax.swing.JMenuItem MnKartu2;
    private javax.swing.JMenuItem MnKartu3;
    private javax.swing.JMenuItem MnKartu4;
    private javax.swing.JMenuItem MnKartu5;
    private javax.swing.JMenuItem MnKartu6;
    private javax.swing.JMenuItem MnKartuStatus;
    private javax.swing.JMenuItem MnLaporanAnestesia;
    private javax.swing.JMenuItem MnLaporanIGD;
    private javax.swing.JMenuItem MnLaporanRM;
    private javax.swing.JMenuItem MnLaporanRM1;
    private javax.swing.JMenuItem MnLaporanRM2;
    private javax.swing.JMenuItem MnLaporanRM3;
    private javax.swing.JMenuItem MnLembarAnamNesa;
    private javax.swing.JMenuItem MnLembarCatatanKeperawatan;
    private javax.swing.JMenuItem MnLembarCatatanPerkembangan;
    private javax.swing.JMenuItem MnLembarGrafik;
    private javax.swing.JMenuItem MnLembarKeluarMasuk;
    private javax.swing.JMenuItem MnLembarKeluarMasuk2;
    private javax.swing.JMenuItem MnPengantarHemodalisa;
    private javax.swing.JMenuItem MnSCreening;
    private javax.swing.JMenuItem MnViaBPJSNik;
    private javax.swing.JMenuItem MnViaBPJSNoKartu;
    private javax.swing.JMenuItem MnViaDukcapilNikAceh;
    private javax.swing.JMenuItem MnViaDukcapilNikDKI;
    private widget.TextBox NmIbu;
    private widget.TextBox NmPasienTujuan;
    private widget.TextBox NoRm;
    private widget.TextBox NoRmTujuan;
    private widget.TextBox Pekerjaan;
    private widget.TextBox Propinsi;
    private widget.TextBox Propinsi2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TKtp;
    private widget.TextBox TNm;
    private widget.TextBox TNo;
    private widget.TextBox TNoPeserta;
    private widget.TextBox TTlp;
    private widget.TextBox TTmp;
    private widget.TextBox TUmurBl;
    private widget.TextBox TUmurHr;
    private widget.TextBox TUmurTh;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JDialog WindowGabungRM;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbHlm;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdbahasa;
    private widget.TextBox kdcacat;
    private widget.TextBox kdgolonganpolri;
    private widget.TextBox kdgolongantni;
    private widget.TextBox kdjabatanpolri;
    private widget.TextBox kdjabatantni;
    private widget.TextBox kdpangkatpolri;
    private widget.TextBox kdpangkattni;
    private widget.TextBox kdperusahaan;
    private widget.TextBox kdsatuanpolri;
    private widget.TextBox kdsatuantni;
    private widget.TextBox kdsuku;
    private widget.Label label40;
    private widget.Label label41;
    private widget.TextBox nmpnj;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppGabungRM;
    private javax.swing.JMenuItem ppGrafikDemografi;
    private javax.swing.JMenuItem ppGrafikPerAgama;
    private javax.swing.JMenuItem ppGrafikPerPekerjaan;
    private javax.swing.JMenuItem ppGrafikjkbayi;
    private javax.swing.JMenuItem ppKelahiranBayi;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppRegistrasi;
    private javax.swing.JMenuItem ppRegistrasi1;
    private javax.swing.JMenuItem ppRegistrasi2;
    private javax.swing.JMenuItem ppRiwayat;
    private widget.Table tbPasien;
    // End of variables declaration//GEN-END:variables
    
    private void tampil() {    
        Valid.tabelKosong(tabMode);
        try{
            if(cmbHlm.getSelectedItem().toString().equals("Semua")){
                 if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){
                      ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                           "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop,"+
                           "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                           "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                           "pasien.alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.kabupatenpj,pasien.propinsipj,"+
                           "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                           "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik,pasien.kd_pj from pasien "+
                           "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                           "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                           "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                           "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                           "inner join penjab on pasien.kd_pj=penjab.kd_pj order by pasien.no_rkm_medis desc");  
                 }else{
                      ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                           "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop,"+
                           "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                           "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                           "pasien.alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.kabupatenpj,pasien.propinsipj,"+
                           "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                           "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik,pasien.kd_pj from pasien "+
                           "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                           "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                           "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                           "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                           "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
                           "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like ? "+
                           "and (pasien.no_rkm_medis like ? or  pasien.nm_pasien like ? or pasien.no_ktp like ? or pasien.no_peserta like ? or pasien.tmp_lahir like ? "+
                           "or pasien.tgl_lahir like ? or penjab.png_jawab like ? or pasien.alamat like ? or pasien.gol_darah like ? or pasien.pekerjaan like ? "+
                           "or pasien.stts_nikah like ? or pasien.nip like ? or cacat_fisik.nama_cacat like ? or pasien.namakeluarga like ? or perusahaan_pasien.nama_perusahaan like ? "+
                           "or bahasa_pasien.nama_bahasa like ? or suku_bangsa.nama_suku_bangsa like ? or pasien.agama like ? or pasien.nm_ibu like ? or pasien.tgl_daftar like ? "+
                           "or pasien.no_tlp like ?) order by pasien.no_rkm_medis desc");  
                 }    
            }else{
                if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                           "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop,"+
                           "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                           "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                           "pasien.alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.kabupatenpj,pasien.propinsipj,"+
                           "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                           "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik,pasien.kd_pj from pasien "+
                           "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                           "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                           "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                           "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                           "inner join penjab on pasien.kd_pj=penjab.kd_pj order by pasien.no_rkm_medis desc LIMIT ? ");    
                }else{
                    ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                           "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop,"+
                           "pasien.gol_darah, pasien.pekerjaan,pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                           "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta,pasien.pekerjaanpj,"+
                           "pasien.alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.kabupatenpj,pasien.propinsipj,"+
                           "perusahaan_pasien.kode_perusahaan,perusahaan_pasien.nama_perusahaan,pasien.bahasa_pasien,"+
                           "bahasa_pasien.nama_bahasa,pasien.suku_bangsa,suku_bangsa.nama_suku_bangsa,pasien.nip,pasien.email,cacat_fisik.nama_cacat,pasien.cacat_fisik,pasien.kd_pj from pasien "+
                           "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                           "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab inner join perusahaan_pasien on perusahaan_pasien.kode_perusahaan=pasien.perusahaan_pasien "+
                           "inner join cacat_fisik on pasien.cacat_fisik=cacat_fisik.id inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                           "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien inner join suku_bangsa on suku_bangsa.id=pasien.suku_bangsa "+
                           "inner join penjab on pasien.kd_pj=penjab.kd_pj "+
                           "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) like ? "+
                           "and (pasien.no_rkm_medis like ? or  pasien.nm_pasien like ? or pasien.no_ktp like ? or pasien.no_peserta like ? or pasien.tmp_lahir like ? "+
                           "or pasien.tgl_lahir like ? or penjab.png_jawab like ? or pasien.alamat like ? or pasien.gol_darah like ? or pasien.pekerjaan like ? "+
                           "or pasien.stts_nikah like ? or pasien.nip like ? or cacat_fisik.nama_cacat like ? or pasien.namakeluarga like ? or perusahaan_pasien.nama_perusahaan like ? "+
                           "or bahasa_pasien.nama_bahasa like ? or suku_bangsa.nama_suku_bangsa like ? or pasien.agama like ? or pasien.nm_ibu like ? or pasien.tgl_daftar like ? "+
                           "or pasien.no_tlp like ?)  order by pasien.no_rkm_medis desc LIMIT ? ");    
                }       
            }          
            try{
                if(cmbHlm.getSelectedItem().toString().equals("Semua")){
                    if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){}else{
                        ps.setString(1,"%"+Carialamat.getText().trim()+"%");
                        ps.setString(2,"%"+TCari.getText().trim()+"%");
                        ps.setString(3, "%"+TCari.getText().trim()+"%");
                        ps.setString(4, "%"+TCari.getText().trim()+"%");
                        ps.setString(5, "%"+TCari.getText().trim()+"%");
                        ps.setString(6, "%"+TCari.getText().trim()+"%");
                        ps.setString(7, "%"+TCari.getText().trim()+"%");
                        ps.setString(8, "%"+TCari.getText().trim()+"%");
                        ps.setString(9, "%"+TCari.getText().trim()+"%");
                        ps.setString(10, "%"+TCari.getText().trim()+"%");
                        ps.setString(11, "%"+TCari.getText().trim()+"%");
                        ps.setString(12, "%"+TCari.getText().trim()+"%");
                        ps.setString(13, "%"+TCari.getText().trim()+"%");
                        ps.setString(14, "%"+TCari.getText().trim()+"%");
                        ps.setString(15, "%"+TCari.getText().trim()+"%");
                        ps.setString(16, "%"+TCari.getText().trim()+"%");
                        ps.setString(17, "%"+TCari.getText().trim()+"%");
                        ps.setString(18, "%"+TCari.getText().trim()+"%");
                        ps.setString(19, "%"+TCari.getText().trim()+"%");
                        ps.setString(20, "%"+TCari.getText().trim()+"%");
                        ps.setString(21, "%"+TCari.getText().trim()+"%");
                        ps.setString(22, "%"+TCari.getText().trim()+"%");
                    }   
                }else{
                    if(Carialamat.getText().trim().equals("")&&TCari.getText().trim().equals("")){
                        ps.setInt(1,Integer.parseInt(cmbHlm.getSelectedItem().toString()));
                    }else{
                        ps.setString(1,"%"+Carialamat.getText().trim()+"%");
                        ps.setString(2,"%"+TCari.getText().trim()+"%");
                        ps.setString(3, "%"+TCari.getText().trim()+"%");
                        ps.setString(4, "%"+TCari.getText().trim()+"%");
                        ps.setString(5, "%"+TCari.getText().trim()+"%");
                        ps.setString(6, "%"+TCari.getText().trim()+"%");
                        ps.setString(7, "%"+TCari.getText().trim()+"%");
                        ps.setString(8, "%"+TCari.getText().trim()+"%");
                        ps.setString(9, "%"+TCari.getText().trim()+"%");
                        ps.setString(10, "%"+TCari.getText().trim()+"%");
                        ps.setString(11, "%"+TCari.getText().trim()+"%");
                        ps.setString(12, "%"+TCari.getText().trim()+"%");
                        ps.setString(13, "%"+TCari.getText().trim()+"%");
                        ps.setString(14, "%"+TCari.getText().trim()+"%");
                        ps.setString(15, "%"+TCari.getText().trim()+"%");
                        ps.setString(16, "%"+TCari.getText().trim()+"%");
                        ps.setString(17, "%"+TCari.getText().trim()+"%");
                        ps.setString(18, "%"+TCari.getText().trim()+"%");
                        ps.setString(19, "%"+TCari.getText().trim()+"%");
                        ps.setString(20, "%"+TCari.getText().trim()+"%");
                        ps.setString(21, "%"+TCari.getText().trim()+"%");
                        ps.setString(22, "%"+TCari.getText().trim()+"%");
                        ps.setInt(23,Integer.parseInt(cmbHlm.getSelectedItem().toString()));
                    }
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        false,rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("no_ktp"),rs.getString("jk"),rs.getString("tmp_lahir"),rs.getString("tgl_lahir"),rs.getString("nm_ibu"),
                        rs.getString("alamat")+", "+rs.getString("nm_kel")+", "+rs.getString("nm_kec")+", "+rs.getString("nm_kab")+", "+rs.getString("nm_prop"),rs.getString("gol_darah"),rs.getString("pekerjaan"),
                        rs.getString("stts_nikah"),rs.getString("agama"),rs.getString("tgl_daftar"),rs.getString("no_tlp"),rs.getString("umur"),rs.getString("pnd"),rs.getString("keluarga"),
                        rs.getString("namakeluarga"),rs.getString("png_jawab"),rs.getString("no_peserta"),"Klik Kanan, Tampilkan Banyak Daftar",rs.getString("pekerjaanpj"),
                        rs.getString("alamatpj")+", "+rs.getString("kelurahanpj")+", "+rs.getString("kecamatanpj")+", "+rs.getString("kabupatenpj")+", "+rs.getString("propinsipj"),
                        rs.getString("suku_bangsa"),rs.getString("nama_suku_bangsa"),rs.getString("bahasa_pasien"),rs.getString("nama_bahasa"),
                        rs.getString("kode_perusahaan"),rs.getString("nama_perusahaan"),rs.getString("nip"),rs.getString("email"),rs.getString("cacat_fisik"),
                        rs.getString("nama_cacat"),rs.getString("kd_pj"),rs.getString("alamat"),rs.getString("nm_kel"),rs.getString("nm_kec"),
                        rs.getString("nm_kab"),rs.getString("nm_prop"),rs.getString("alamatpj"),rs.getString("kelurahanpj"),
                        rs.getString("kecamatanpj"),rs.getString("kabupatenpj"),rs.getString("propinsipj")
                    });
                }          
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
            
        LCount.setText(""+tabMode.getRowCount());
    }
    



    public void emptTeks() {
        TNo.setText("");
        Kd2.setText("");
        TNm.setText("");
        CmbJk.setSelectedIndex(0);
        CMbGd.setSelectedIndex(0);
        TTmp.setText("");
        cmbAgama.setSelectedIndex(0);
        Alamat.setText("ALAMAT");
        TKtp.setText("");
        TNoPeserta.setText("");
        Pekerjaan.setText("");
        TTlp.setText("");
        TUmurTh.setText("");     
        NmIbu.setText("");
        Kelurahan.setText("KELURAHAN");      
        Kecamatan.setText("KECAMATAN");      
        Kabupaten.setText("KABUPATEN"); 
        Propinsi.setText("PROPINSI");
        kdcacat.setText("");
        EMail.setText("");
        DTPLahir.setDate(new Date());
        DTPDaftar.setDate(new Date());
        kdsuku.setText("");
        kdbahasa.setText("");
        kdperusahaan.setText("");
        kdgolongantni.setText("");
        kdsatuantni.setText("");
        kdpangkattni.setText("");
        kdjabatantni.setText("");
        kdgolonganpolri.setText("");
        kdsatuanpolri.setText("");
        kdpangkatpolri.setText("");
        kdjabatanpolri.setText("");
        kdkel="";
        kdkec="";
        kdkab="";
        kdprop="";
        autoNomor();
        TNm.requestFocus();
    }

    private void getData() {
        if(tbPasien.getSelectedRow()!= -1){                
            try {                
                TNo.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),1).toString());
                Kd2.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),1).toString());
                TNm.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),2).toString());
                TKtp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),3).toString());

                switch (tbPasien.getValueAt(tbPasien.getSelectedRow(),4).toString()) {
                    case "L":
                        CmbJk.setSelectedItem("LAKI-LAKI");
                        break;
                    case "P":
                        CmbJk.setSelectedItem("PEREMPUAN");
                        break;
                }

                TTmp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),5).toString());
                CMbGd.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(),9).toString());
                Pekerjaan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),10).toString());
                cmbAgama.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(),12).toString());
                TTlp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),14).toString());
                nmpnj.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),19).toString());
                TNoPeserta.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),20).toString());
                NmIbu.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),7).toString());
                CMbPnd.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(),16).toString());          
                kdsuku.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),24).toString());
                kdbahasa.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),26).toString());
                kdperusahaan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),28).toString());
                EMail.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),31).toString());
                kdcacat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),32).toString());
                Kdpnj.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),34).toString());
                Alamat.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),35).toString());
                Kelurahan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),36).toString());
                Kecamatan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),37).toString());
                Kabupaten.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),38).toString());
                Propinsi.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(),39).toString());


 
                kdgolongantni.setText("");
                kdsatuantni.setText("");
                kdpangkattni.setText("");
                kdjabatantni.setText(""); 
                kdgolonganpolri.setText("");
                kdsatuanpolri.setText("");
                kdpangkatpolri.setText("");
                kdjabatanpolri.setText("");

                Valid.SetTgl(DTPLahir,tbPasien.getValueAt(tbPasien.getSelectedRow(),6).toString());
                Valid.SetTgl(DTPDaftar,tbPasien.getValueAt(tbPasien.getSelectedRow(),13).toString());  
            } catch (Exception ex) {
            }   
        }
    }
    
 
    
    public JTable getTable(){
        return tbPasien;
    }
        
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpasien());
        BtnHapus.setEnabled(akses.getpasien());
        BtnEdit.setEnabled(akses.getpasien());
        BtnPrint.setEnabled(akses.getpasien());
        ppGabungRM.setEnabled(akses.getgabung_rm());
        ppRiwayat.setEnabled(akses.getresume_pasien());
        ppCatatanPasien.setEnabled(akses.getcatatan_pasien());
        ppPasienCorona.setEnabled(akses.getpasien_corona());
        asalform=akses.getform();
        TabRawat.setSelectedIndex(1);
    }

    private void prosesCari2() {
        switch (TabRawat.getSelectedIndex()) {
            case 1:
                for(z=0;z<tbPasien.getRowCount();z++){ 
                    tbPasien.setValueAt(Sequel.cariIsi("select count(reg_periksa.no_rkm_medis) from reg_periksa where reg_periksa.no_rkm_medis=?",tbPasien.getValueAt(z,1).toString())+" X",z,21);
                } 
                break;
            default:
                break;
        }        
    }
    
    private void pilihantampil(){
        switch (TabRawat.getSelectedIndex()) {
            case 1:
                tampil();
                break;
            default:
                break;
        }
    }

    private void autoNomor() {  
        if(Kd2.getText().equals("")){
            if(ChkRM.isSelected()==true){
                if(tahun.equals("Yes")){
                    awalantahun=DTPDaftar.getSelectedItem().toString().substring(8,10);
                }else{
                    awalantahun="";
                }

                if(bulan.equals("Yes")){
                    awalanbulan=DTPDaftar.getSelectedItem().toString().substring(3,5);
                }else{
                    awalanbulan="";
                }

                if(posisitahun.equals("Depan")){
                    switch (pengurutan) {
                        case "Straight":
                            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(set_no_rkm_medis.no_rkm_medis,6),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                        case "Terminal":
                            Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),5,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                        case "Middle":
                            Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),1,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                    }
                }else if(posisitahun.equals("Belakang")){
                    switch (pengurutan) {
                        case "Straight":
                            Valid.autoNomer3("select ifnull(MAX(CONVERT(LEFT(set_no_rkm_medis.no_rkm_medis,6),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                        case "Terminal":
                            Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),5,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                        case "Middle":
                            Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),1,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                            break;
                    }            
                }

                if(posisitahun.equals("Depan")){
                    TNo.setText(awalantahun+awalanbulan+NoRm.getText());
                }else if(posisitahun.equals("Belakang")){
                    if(!(awalanbulan+awalantahun).equals("")){
                        TNo.setText(NoRm.getText()+"-"+awalanbulan+awalantahun);
                    }else{
                        TNo.setText(NoRm.getText());
                    }            
                }
            }
        }
    }
    
    public void setPasien(String NamaPasien,String Kontak,String Alamat,String TempatLahir,String TglLahir,String JK,String NoKartuJKN,String NIK){
        this.TNm.setText(NamaPasien);
        this.TTlp.setText(Kontak);
        this.Alamat.setText(Alamat);
        this.TTmp.setText(TempatLahir);
        Valid.SetTgl(this.DTPLahir,TglLahir);
        if(JK.equals("L")){
            this.CmbJk.setSelectedItem("LAKI-LAKI");
        }else{            
            this.CmbJk.setSelectedItem("PEREMPUAN");
        }
        this.TNoPeserta.setText(NoKartuJKN);
        this.TKtp.setText(NIK);
    }
    
}

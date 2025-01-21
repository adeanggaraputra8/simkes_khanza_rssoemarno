
package modifikasi;

import bridging.DlgSKDPBPJS;
import keuangan.DlgCariPerawatanRanap;
import keuangan.DlgCariPerawatanRanap2;
import inventory.DlgPemberianObat;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCariObat2;
import inventory.DlgCopyResep;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.JobState;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import keuangan.Jurnal;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
import rekammedis.RMHemodialisa;
import kepegawaian.DlgCariPegawai;
import rekammedis.RMRiwayatPerawatan;
import simrskhanza.DlgCatatan;
import simrskhanza.DlgPasien;
import simrskhanza.DlgRujuk;



/**
 *
 * @author perpustakaan
 */
public final class DlgRawatInaprs extends javax.swing.JDialog {
    private DefaultTableModel tabModePemeriksaan,tabModeAwal,tabModeNeonatal,tabModecppt;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private Connection koneksi=koneksiDB.condb();
    public  DlgCariPerawatanRanap perawatan=new DlgCariPerawatanRanap(null,false);
    public  DlgCariPerawatanRanap2 perawatan2=new DlgCariPerawatanRanap2(null,false);
    private DlgPasien pasien=new DlgPasien(null,false);
    private PreparedStatement ps,ps2,ps3,ps4,ps5,psrekening,ps6,ps7,ps8,ps9,psotomatis,psotomatis2,pskasir;
    private ResultSet rs,rsrekening,rskasir;
    private final Properties prop = new Properties();
    private int i=0;
    private double ttljmdokter=0,ttljmperawat=0,ttlkso=0,ttlpendapatan=0;
    private String Suspen_Piutang_Tindakan_Ranap="",Tindakan_Ranap="",Beban_Jasa_Medik_Dokter_Tindakan_Ranap="",
            Utang_Jasa_Medik_Dokter_Tindakan_Ranap="",Beban_Jasa_Medik_Paramedis_Tindakan_Ranap="",
            Utang_Jasa_Medik_Paramedis_Tindakan_Ranap="",Beban_KSO_Tindakan_Ranap="",
            Utang_KSO_Tindakan_Ranap="",kode_poli="",
            sqlpsotomatis2="insert into rawat_inap_dr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2petugas="insert into rawat_inap_pr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2dokterpetugas="insert into rawat_inap_drpr values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
             sqlpsotomatisdokterpetugas=
                "select jns_perawatan_inap.kd_jenis_prw,jns_perawatan_inap.material,jns_perawatan_inap.bhp,"+
                "jns_perawatan_inap.tarif_tindakandr,jns_perawatan_inap.tarif_tindakanpr,jns_perawatan_inap.total_byrdrpr,"+
                "jns_perawatan_inap.kso,jns_perawatan_inap.menejemen from set_otomatis_tindakan_ranap_dokterpetugas "+
                "inner join jns_perawatan_inap on set_otomatis_tindakan_ranap_dokterpetugas.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                "where set_otomatis_tindakan_ranap_dokterpetugas.kd_bangsal=? ",
            sqlpsotomatispetugas=
                "select jns_perawatan_inap.kd_jenis_prw,jns_perawatan_inap.material,jns_perawatan_inap.bhp,"+
                "jns_perawatan_inap.tarif_tindakanpr,jns_perawatan_inap.total_byrpr,jns_perawatan_inap.kso,jns_perawatan_inap.menejemen from set_otomatis_tindakan_ranap_petugas "+
                "inner join jns_perawatan_inap on set_otomatis_tindakan_ranap_petugas.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                "where set_otomatis_tindakan_ranap_petugas.kd_bangsal=? ",
            sqlpsotomatis=
                "select jns_perawatan_inap.kd_jenis_prw,jns_perawatan_inap.material,jns_perawatan_inap.bhp,"+
                "jns_perawatan_inap.tarif_tindakandr,jns_perawatan_inap.total_byrdr,jns_perawatan_inap.kso,jns_perawatan_inap.menejemen from set_otomatis_tindakan_ranap "+
                "inner join jns_perawatan_inap on set_otomatis_tindakan_ranap.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
                "where set_otomatis_tindakan_ranap.kd_bangsal=? ";
    private Date lahir;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
     private Period p;
    private long p2;
    private DlgCariPegawai pegawai=new DlgCariPegawai(null,false);
    private DlgCariPegawai pegawai1=new DlgCariPegawai(null,false);
    private DlgCariPegawai pegawai2=new DlgCariPegawai(null,false);
    private DlgCariPegawai pegawai3=new DlgCariPegawai(null,false);
    /** Creates new form DlgRawatInap
     * @param parent
     * @param modal */
    public DlgRawatInaprs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);
        
        
        tabModePemeriksaan=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam Rawat","Suhu(C)","Tensi","Nadi(/menit)",
            "Respirasi(/menit)","Tinggi(Cm)","Berat(Kg)","GCS(E,V,M)","Keluhan","Pemeriksaan","Alergi","Diagnosa Masuk","Terapi Pengobatan Di RS","Hasil Konsultasi","Hasil Labor (Pending)","Pengobatan Dilanjutkan Ke","Terapi Pulang","Prosedur ICD 9","Follow UP"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPemeriksaan.setModel(tabModePemeriksaan);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(75);
            }else if(i==8){
                column.setPreferredWidth(75);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(75);
            }else if(i==11){
                column.setPreferredWidth(75);
            }else if(i==12){
                column.setPreferredWidth(75);
            }else if(i==13){
                column.setPreferredWidth(180);
            }else if(i==14){
                column.setPreferredWidth(180);
            }else if(i==15){
                column.setPreferredWidth(130);
            }else if(i==16){
                column.setPreferredWidth(150);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(150);
            }else if(i==19){
                column.setPreferredWidth(150);
            }else if(i==20){
                column.setPreferredWidth(130);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(130);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        
         tabModeAwal=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam Rawat","Riwayat Alergi","Keluhan",
            "Riwayat Penyakit Sekarang","Riwayat Penyakit Dahulu","Riwayat Penyakit Keluarga","Diagnosa Kerja","Diagnosa Banding",
            "Prognosis"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbawal.setModel(tabModeAwal);
        tbawal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbawal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbawal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(60);
            }else if(i==6){
                column.setPreferredWidth(250);
            }else if(i==7){
                column.setPreferredWidth(250);
            }else if(i==8){
                column.setPreferredWidth(250);
            }else if(i==9){
                column.setPreferredWidth(250);
            }else if(i==10){
                column.setPreferredWidth(250);
            }else if(i==11){
                column.setPreferredWidth(250);
            }else if(i==12){
                column.setPreferredWidth(250);
            }else if(i==13){
                column.setPreferredWidth(200);
            }
        }
        tbawal.setDefaultRenderer(Object.class, new WarnaTable());
        
        
         tabModeNeonatal=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M","Nama Pasien","Tgl.Rawat","Jam Rawat",
            "Nama Ibu","Umur Ibu", "Nama Ayah","Umur Ayah","Alamat","Jenis Kelamin","Berat Badan","Panjang Badan",
            "Lingkar Kepala", "Proses Lahir","Anakke","Jam Lahir","Lain lain",
            "Diaknosa","Penyulit Kehamilan","Ketuban","Lingkar Perut","Lingkar Dada","Penolong DPJP",
            "Penolong Kelahiran","Usia Kehamilan","Gpa","Frekuensi Jantung 1'","Usaha Nafas 1'","Tonus Otot 1'","Refleks 1'","Warna Kulit 1'","Frekuensi Jantung 5'","Usaha Nafas 5'","Tonus Otot 5'","Refleks 5'","Warna Kulit 5'","Frekuensi Jantung 10'","Usaha Nafas 10'","Tonus Otot 10'","Refleks 10'","Warna Kulit 10'","Ajumlah","Bjumlah","Cjumlah","Resositasi","Obat Diberi","Mikasi","Mekunium","Suhu","Nadi","Respirasi","SpO2","Catatan Keperawatan","Tanggal Lahir"   }) {
             @Override public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
            }
            Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        
        tbNeonatal.setModel(tabModeNeonatal);
        tbNeonatal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNeonatal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 56; i++) {
            TableColumn column = tbNeonatal.getColumnModel().getColumn(i);
            if(i==0) {
                column.setPreferredWidth(20);
            }else if(i==1) {
                column.setPreferredWidth(105);
            }else if(i==2) {
                column.setPreferredWidth(70);
            }else if(i==3) {
                column.setPreferredWidth(180);
            }else if(i==4) {
                column.setPreferredWidth(80);
            }else if(i==5) {
                column.setPreferredWidth(70);
            }else if(i==6) {
                column.setPreferredWidth(100);
            }else if(i==7) {
                column.setPreferredWidth(60);
            }else if(i==8) {
                column.setPreferredWidth(100);
            }else if(i==9) {
                column.setPreferredWidth(60);
            }else if(i==10) {
                column.setPreferredWidth(130);
            }else if(i==11) {
                column.setPreferredWidth(80);
            }else if(i==12) {
                column.setPreferredWidth(60);
            }else if(i==13) {
                column.setPreferredWidth(60);
            }else if(i==14) {
                column.setPreferredWidth(60);
            }else if(i==15) {
                column.setPreferredWidth(130);
            }else if(i==16) {
                column.setPreferredWidth(60);
            }else if(i==17) {
                column.setPreferredWidth(60);
            }else if(i==18) {
                column.setPreferredWidth(200);
            }else if(i==19) {
                column.setPreferredWidth(150);
            }else if(i==20) {
                column.setPreferredWidth(200);
            }else if(i==21) {
                column.setPreferredWidth(150);
            }else if(i==22) {
                column.setPreferredWidth(60);
            }else if(i==23) {
                column.setPreferredWidth(60);
            }else if(i==24) {
                column.setPreferredWidth(80);
            }else if(i==25) {
                column.setPreferredWidth(80);
            }else if(i==26) {
                column.setPreferredWidth(80);
            }else if(i==27) {
                column.setPreferredWidth(130);
            }else if(i==28) {
                column.setPreferredWidth(80);
            }else if(i==29) {
                column.setPreferredWidth(80);
            }else if(i==30) {
                column.setPreferredWidth(80);
            }else if(i==31) {
                column.setPreferredWidth(80);
            }else if(i==32) {
                column.setPreferredWidth(80);
            }else if(i==33) {
                column.setPreferredWidth(80);
            }else if(i==34) {
                column.setPreferredWidth(80);
            }else if(i==35) {
                column.setPreferredWidth(80);
            }else if(i==36) {
                column.setPreferredWidth(80);
            }else if(i==37) {
                column.setPreferredWidth(80);
            }else if(i==38) {
                column.setPreferredWidth(80);
            }else if(i==39) {
                column.setPreferredWidth(80);
            }else if(i==40) {
                column.setPreferredWidth(80);
            }else if(i==41) {
                column.setPreferredWidth(80);
            }else if(i==42) {
                column.setPreferredWidth(80);
            }else if(i==43) {
                column.setPreferredWidth(100);
            }else if(i==44) {
                column.setPreferredWidth(100);
            }else if(i==45) {
                column.setPreferredWidth(100);
            }else if(i==46) {
                column.setPreferredWidth(150);
            }else if(i==47) {
                column.setPreferredWidth(150);
            }else if(i==48) {
                column.setPreferredWidth(100);
            }else if(i==49) {
                column.setPreferredWidth(100);
            }else if(i==50) {
                column.setPreferredWidth(60);
            }else if(i==51) {
                column.setPreferredWidth(60);
            }else if(i==52) {
                column.setPreferredWidth(60);
            }else if(i==53) {
                column.setPreferredWidth(60);
            }else if(i==54) {
                column.setPreferredWidth(200);
            }else if(i==55) {
                column.setPreferredWidth(80);
            }
        }
        tbNeonatal.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        
         tabModecppt=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","No.R.M.","Nama Pasien","Tgl.Rawat","Jam Rawat","PPJP",
            "PPA","Hasil Asessmen Pasien & Pemberian Pelayanan","Instruksi PPA","Verifikasi"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbcppt.setModel(tabModecppt);
        tbcppt.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbcppt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbcppt.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(60);
            }else if(i==6){
                column.setPreferredWidth(80);
            }else if(i==7){
                column.setPreferredWidth(80);
            }else if(i==8){
                column.setPreferredWidth(350);
            }else if(i==9){
                column.setPreferredWidth(350);
            }else if(i==10){
                column.setPreferredWidth(200);
            }
        }
        tbcppt.setDefaultRenderer(Object.class, new WarnaTable());

      
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));        
        TSuhu.setDocument(new batasInput((byte)5).getKata(TSuhu));
        TTensi.setDocument(new batasInput((byte)7).getKata(TTensi));
        TCariPasien.setDocument(new batasInput((byte)20).getKata(TCariPasien));
        TKeluhan.setDocument(new batasInput((int)300).getKata(TKeluhan));        
        TTinggi.setDocument(new batasInput((byte)5).getKata(TTinggi));
        TBerat.setDocument(new batasInput((byte)5).getKata(TBerat));
        TNadi.setDocument(new batasInput((byte)3).getOnlyAngka(TNadi));
        TRespirasi.setDocument(new batasInput((byte)3).getOnlyAngka(TRespirasi));      
        TGCS.setDocument(new batasInput((byte)10).getKata(TGCS)); 
        TAlergi.setDocument(new batasInput((int)50).getKata(TAlergi));        
        TPemeriksaan.setDocument(new batasInput((int)300).getKata(TPemeriksaan));
        Diagnosaawal.setDocument(new batasInput((int)50).getKata(Diagnosaawal));
        Tterapi.setDocument(new batasInput((int)300).getKata(Tterapi));
        Konsul.setDocument(new batasInput((int)300).getKata(Konsul));
        labor.setDocument(new batasInput((int)300).getKata(labor));
        Tterapipulang.setDocument(new batasInput((int)300).getKata(Tterapipulang));
        Tprosedur.setDocument(new batasInput((int)300).getKata(Tprosedur));
        Tfollowup.setDocument(new batasInput((int)300).getKata(Tfollowup));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));       
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        if(TabRawat.getSelectedIndex()==0){
                            tampilAsesmenAwal();
                        }else if(TabRawat.getSelectedIndex()==1){
                             tampilAsesmenNeonatal();
                        }else if(TabRawat.getSelectedIndex()==2){
                            tampilPemeriksaan();
                        }
                    }                        
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        if(TabRawat.getSelectedIndex()==0){
                            tampilAsesmenAwal();
                        }else if(TabRawat.getSelectedIndex()==1){
                              tampilAsesmenNeonatal();
                        }else if(TabRawat.getSelectedIndex()==2){
                              tampilPemeriksaan();
                        }
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TabRawat.getSelectedIndex()==0){
                            tampilAsesmenAwal();
                        }else if(TabRawat.getSelectedIndex()==1){
                            tampilAsesmenNeonatal();
                        }else if(TabRawat.getSelectedIndex()==2){
                              tampilPemeriksaan();
                        }
                    }
            });
        } 
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgRawatInap")){
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
                if(akses.getform().equals("DlgRawatInap")){
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
                if(akses.getform().equals("DlgRawatInap")){
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
                if(akses.getform().equals("DlgRawatInap")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
     
        
        
       pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai.getTable().getSelectedRow()!= -1){                   
                    KdPenolong.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),0).toString());
                    NmPenolong.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),1).toString());
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
        
        pegawai1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai1.getTable().getSelectedRow()!= -1){                   
                    KdPenolong1.setText(pegawai1.tbKamar.getValueAt(pegawai1.tbKamar.getSelectedRow(),0).toString());
                    NmPenolong1.setText(pegawai1.tbKamar.getValueAt(pegawai1.tbKamar.getSelectedRow(),1).toString());
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
        
        pegawai2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai2.getTable().getSelectedRow()!= -1){                   
                    kdppjp.setText(pegawai2.tbKamar.getValueAt(pegawai2.tbKamar.getSelectedRow(),0).toString());
                    ppjp.setText(pegawai2.tbKamar.getValueAt(pegawai2.tbKamar.getSelectedRow(),1).toString());
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
        
         pegawai3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai3.getTable().getSelectedRow()!= -1){                   
                    kdppa.setText(pegawai3.tbKamar.getValueAt(pegawai3.tbKamar.getSelectedRow(),0).toString());
                    ppa.setText(pegawai3.tbKamar.getValueAt(pegawai3.tbKamar.getSelectedRow(),1).toString());
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
            
            
        
        ChkInput3.setSelected(false);
        isForm4(); 
        ChkInput.setSelected(false);
        isForm(); 
        ChkInput4.setSelected(false);
        isForm5();
        ChkAccor.setSelected(false);
        isMenu();
        jam();
      
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception ex) {
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

        BagianRS = new javax.swing.JTextField();
        Bhp = new javax.swing.JTextField();
        JmDokter = new javax.swing.JTextField();
        JmPerawat = new javax.swing.JTextField();
        TTnd = new javax.swing.JTextField();
        KSO = new javax.swing.JTextField();
        Menejemen = new javax.swing.JTextField();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnRingksanKeluar = new javax.swing.JMenuItem();
        WindowCPPT = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        jLabel84 = new widget.Label();
        jLabel86 = new widget.Label();
        cmbrevieiw = new widget.ComboBox();
        jLabel87 = new widget.Label();
        dpjp = new widget.TextBox();
        DTPtanggalkembali = new widget.Tanggal();
        scrollPane17 = new widget.ScrollPane();
        tbcppt = new widget.Table();
        panelGlass1 = new widget.panelGlass();
        BtnSimpan5 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnCloseIn5 = new widget.Button();
        BtnPrint1 = new widget.Button();
        tglcppt1 = new widget.Tanggal();
        jLabel88 = new widget.Label();
        tglcppt2 = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        jLabel95 = new widget.Label();
        Tnorw2 = new widget.TextBox();
        jLabel98 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        soap = new widget.TextArea();
        scrollPane19 = new widget.ScrollPane();
        instruksi = new widget.TextArea();
        Tglmasuk = new widget.TextBox();
        jLabel100 = new widget.Label();
        kdppjp = new widget.TextBox();
        ppjp = new widget.TextBox();
        btncaripetugas1 = new widget.Button();
        Tglmasuk1 = new widget.TextBox();
        jLabel101 = new widget.Label();
        Tnorw3 = new widget.TextBox();
        jLabel104 = new widget.Label();
        kdppa = new widget.TextBox();
        ppa = new widget.TextBox();
        btncaripetugas2 = new widget.Button();
        jLabel105 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        LCount = new widget.Label();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel20 = new widget.Label();
        TCariPasien = new widget.TextBox();
        btnPasien = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        tbawal = new widget.Table();
        PanelInput4 = new javax.swing.JPanel();
        ChkInput3 = new widget.CekBox();
        panelGlass16 = new widget.panelisi();
        jLabel85 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Triwayat_alergi = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        Tkel_utama = new widget.TextArea();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        Triwayat = new widget.TextArea();
        jLabel96 = new widget.Label();
        jLabel102 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Triwayat_dulu = new widget.TextArea();
        jLabel97 = new widget.Label();
        jLabel94 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Tdx_kerja = new widget.TextArea();
        scrollPane13 = new widget.ScrollPane();
        Triwayat_keluarga = new widget.TextArea();
        scrollPane14 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        scrollPane15 = new widget.ScrollPane();
        Tdx_banding = new widget.TextArea();
        jLabel99 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        tbNeonatal = new widget.Table();
        PanelInput5 = new javax.swing.JPanel();
        ChkInput4 = new widget.CekBox();
        panelGlass17 = new widget.panelisi();
        label18 = new widget.Label();
        label22 = new widget.Label();
        label24 = new widget.Label();
        Proses = new widget.TextBox();
        Anakke = new widget.TextBox();
        label25 = new widget.Label();
        LingkarKepala = new widget.TextBox();
        label27 = new widget.Label();
        JKel = new widget.ComboBox();
        label23 = new widget.Label();
        label30 = new widget.Label();
        Lahir = new widget.Tanggal();
        label31 = new widget.Label();
        Diagnosa = new widget.TextBox();
        Nmibu = new widget.TextBox();
        label26 = new widget.Label();
        label19 = new widget.Label();
        NmAyah = new widget.TextBox();
        label20 = new widget.Label();
        UmurIbu = new widget.TextBox();
        label21 = new widget.Label();
        AlamatIbu = new widget.TextBox();
        label32 = new widget.Label();
        jam = new widget.ComboBox();
        menit = new widget.ComboBox();
        detik = new widget.ComboBox();
        label34 = new widget.Label();
        Berat = new widget.TextBox();
        Panjang = new widget.TextBox();
        scrollPane9 = new widget.ScrollPane();
        keterangan = new widget.TextArea();
        label35 = new widget.Label();
        UmurAyah = new widget.TextBox();
        label36 = new widget.Label();
        UmurBayi = new widget.TextBox();
        label37 = new widget.Label();
        PenyulitKehamilan = new widget.TextBox();
        label38 = new widget.Label();
        Ketuban = new widget.TextBox();
        label39 = new widget.Label();
        LingkarDada = new widget.TextBox();
        LingkarPerut = new widget.TextBox();
        label40 = new widget.Label();
        jLabel113 = new widget.Label();
        KdPenolong = new widget.TextBox();
        NmPenolong = new widget.TextBox();
        BtnPenjab = new widget.Button();
        label29 = new widget.Label();
        label41 = new widget.Label();
        label42 = new widget.Label();
        usia_kehamilan = new widget.TextBox();
        GPA = new widget.TextBox();
        BtnPenjab1 = new widget.Button();
        NmPenolong1 = new widget.TextBox();
        KdPenolong1 = new widget.TextBox();
        jLabel114 = new widget.Label();
        label28 = new widget.Label();
        label33 = new widget.Label();
        label43 = new widget.Label();
        label44 = new widget.Label();
        label45 = new widget.Label();
        label46 = new widget.Label();
        label47 = new widget.Label();
        label48 = new widget.Label();
        label49 = new widget.Label();
        label50 = new widget.Label();
        label51 = new widget.Label();
        label52 = new widget.Label();
        label53 = new widget.Label();
        label54 = new widget.Label();
        label55 = new widget.Label();
        label56 = new widget.Label();
        label57 = new widget.Label();
        label58 = new widget.Label();
        label59 = new widget.Label();
        label60 = new widget.Label();
        label61 = new widget.Label();
        label62 = new widget.Label();
        label63 = new widget.Label();
        label64 = new widget.Label();
        label65 = new widget.Label();
        label66 = new widget.Label();
        label67 = new widget.Label();
        label68 = new widget.Label();
        label69 = new widget.Label();
        label70 = new widget.Label();
        c1 = new widget.ComboBox();
        a1 = new widget.ComboBox();
        b1 = new widget.ComboBox();
        c2 = new widget.ComboBox();
        a2 = new widget.ComboBox();
        b2 = new widget.ComboBox();
        c3 = new widget.ComboBox();
        a3 = new widget.ComboBox();
        b3 = new widget.ComboBox();
        c4 = new widget.ComboBox();
        a4 = new widget.ComboBox();
        b4 = new widget.ComboBox();
        c5 = new widget.ComboBox();
        a5 = new widget.ComboBox();
        b5 = new widget.ComboBox();
        scrollPane10 = new widget.ScrollPane();
        resusitasi = new widget.TextArea();
        label71 = new widget.Label();
        label72 = new widget.Label();
        mikasi = new widget.TextBox();
        label73 = new widget.Label();
        obatdiberi = new widget.TextBox();
        label74 = new widget.Label();
        mekonium = new widget.TextBox();
        label75 = new widget.Label();
        cjumlah = new widget.TextBox();
        ajumlah = new widget.TextBox();
        bjumlah = new widget.TextBox();
        label76 = new widget.Label();
        jLabel66 = new widget.Label();
        TSuhu1 = new widget.TextBox();
        jLabel68 = new widget.Label();
        TNadi1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        TRespirasi1 = new widget.TextBox();
        jLabel70 = new widget.Label();
        spo2 = new widget.TextBox();
        label77 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        cttnkeperawatan = new widget.TextArea();
        internalFrame4 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        jLabel7 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel4 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel25 = new widget.Label();
        TBerat = new widget.TextBox();
        TNadi = new widget.TextBox();
        jLabel24 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel15 = new widget.Label();
        TAlergi = new widget.TextBox();
        jLabel23 = new widget.Label();
        TRespirasi = new widget.TextBox();
        jLabel22 = new widget.Label();
        TGCS = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        scrollPane2 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel26 = new widget.Label();
        Diagnosaawal = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Tterapi = new widget.TextArea();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Konsul = new widget.TextBox();
        labor = new widget.TextBox();
        CbLanjut = new widget.ComboBox();
        scrollPane5 = new widget.ScrollPane();
        Tterapipulang = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        Tprosedur = new widget.TextArea();
        jLabel61 = new widget.Label();
        Tfollowup = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel65 = new widget.Label();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        BtnCatatan1 = new widget.Button();
        BtnCPPT = new widget.Button();
        BtnPermintaanLab = new widget.Button();
        BtnPermintaanRad = new widget.Button();
        BtnUTD = new widget.Button();
        BtnIRM = new widget.Button();
        BtnGizi = new widget.Button();
        BtnHD = new widget.Button();
        BtnInputObat = new widget.Button();
        BtnCopyResep = new widget.Button();
        BtnObatBhp = new widget.Button();
        BtnResepObat = new widget.Button();
        BtnBerkasDigital = new widget.Button();
        BtnRujukKeluar = new widget.Button();
        BtnSKDP = new widget.Button();
        BtnDiagnosa = new widget.Button();
        BtnRiwayat = new widget.Button();
        BtnCatatan = new widget.Button();

        BagianRS.setEditable(false);
        BagianRS.setText("0");
        BagianRS.setName("BagianRS"); // NOI18N

        Bhp.setEditable(false);
        Bhp.setText("0");
        Bhp.setName("Bhp"); // NOI18N

        JmDokter.setEditable(false);
        JmDokter.setText("0");
        JmDokter.setName("JmDokter"); // NOI18N

        JmPerawat.setEditable(false);
        JmPerawat.setText("0");
        JmPerawat.setName("JmPerawat"); // NOI18N

        TTnd.setEditable(false);
        TTnd.setText("0");
        TTnd.setName("TTnd"); // NOI18N

        KSO.setEditable(false);
        KSO.setText("0");
        KSO.setName("KSO"); // NOI18N

        Menejemen.setEditable(false);
        Menejemen.setText("0");
        Menejemen.setName("Menejemen"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRingksanKeluar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRingksanKeluar.setForeground(new java.awt.Color(70, 70, 70));
        MnRingksanKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRingksanKeluar.setText("Ringksan Keluar Pasien");
        MnRingksanKeluar.setToolTipText("");
        MnRingksanKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRingksanKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRingksanKeluar.setName("MnRingksanKeluar"); // NOI18N
        MnRingksanKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRingksanKeluarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRingksanKeluar);

        WindowCPPT.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCPPT.setName("WindowCPPT"); // NOI18N
        WindowCPPT.setUndecorated(true);
        WindowCPPT.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Perkembangan Pasien Terintegrasi/CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(null);

        jLabel84.setText("Pasien :");
        jLabel84.setName("jLabel84"); // NOI18N
        internalFrame10.add(jLabel84);
        jLabel84.setBounds(10, 30, 70, 23);

        jLabel86.setText("Tanggal :");
        jLabel86.setName("jLabel86"); // NOI18N
        internalFrame10.add(jLabel86);
        jLabel86.setBounds(360, 30, 90, 23);

        cmbrevieiw.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum Terverifikasi", "Sudah Terverifikasi" }));
        cmbrevieiw.setName("cmbrevieiw"); // NOI18N
        internalFrame10.add(cmbrevieiw);
        cmbrevieiw.setBounds(830, 100, 120, 23);

        jLabel87.setText("Verifikasi :");
        jLabel87.setName("jLabel87"); // NOI18N
        internalFrame10.add(jLabel87);
        jLabel87.setBounds(750, 100, 77, 23);

        dpjp.setEditable(false);
        dpjp.setName("dpjp"); // NOI18N
        dpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpjpActionPerformed(evt);
            }
        });
        internalFrame10.add(dpjp);
        dpjp.setBounds(90, 70, 260, 24);

        DTPtanggalkembali.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPtanggalkembali.setDisplayFormat("dd-MM-yyyy");
        DTPtanggalkembali.setName("DTPtanggalkembali"); // NOI18N
        DTPtanggalkembali.setOpaque(false);
        DTPtanggalkembali.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPtanggalkembaliItemStateChanged(evt);
            }
        });
        DTPtanggalkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPtanggalkembaliKeyPressed(evt);
            }
        });
        internalFrame10.add(DTPtanggalkembali);
        DTPtanggalkembali.setBounds(460, 30, 109, 23);

        scrollPane17.setBackground(new java.awt.Color(153, 153, 153));
        scrollPane17.setName("scrollPane17"); // NOI18N

        tbcppt.setAutoCreateRowSorter(true);
        tbcppt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbcppt.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbcppt.setName("tbcppt"); // NOI18N
        tbcppt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcpptMouseClicked(evt);
            }
        });
        tbcppt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbcpptKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(tbcppt);

        internalFrame10.add(scrollPane17);
        scrollPane17.setBounds(30, 370, 920, 220);

        panelGlass1.setName("panelGlass1"); // NOI18N

        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnSimpan5);

        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setIconTextGap(3);
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass1.add(BtnBatal1);

        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('U');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+U");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnGanti);

        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setIconTextGap(3);
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass1.add(BtnHapus1);

        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnCloseIn5);

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
        panelGlass1.add(BtnPrint1);

        tglcppt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        tglcppt1.setDisplayFormat("dd-MM-yyyy");
        tglcppt1.setName("tglcppt1"); // NOI18N
        tglcppt1.setOpaque(false);
        tglcppt1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglcppt1ItemStateChanged(evt);
            }
        });
        tglcppt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglcppt1KeyPressed(evt);
            }
        });
        panelGlass1.add(tglcppt1);

        jLabel88.setText("s . d");
        jLabel88.setName("jLabel88"); // NOI18N
        panelGlass1.add(jLabel88);

        tglcppt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        tglcppt2.setDisplayFormat("dd-MM-yyyy");
        tglcppt2.setName("tglcppt2"); // NOI18N
        tglcppt2.setOpaque(false);
        tglcppt2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglcppt2ItemStateChanged(evt);
            }
        });
        tglcppt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglcppt2KeyPressed(evt);
            }
        });
        panelGlass1.add(tglcppt2);

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
        panelGlass1.add(BtnCari1);

        internalFrame10.add(panelGlass1);
        panelGlass1.setBounds(30, 600, 920, 40);

        jLabel95.setText(" Pelayanan : ");
        jLabel95.setName("jLabel95"); // NOI18N
        internalFrame10.add(jLabel95);
        jLabel95.setBounds(10, 190, 80, 30);

        Tnorw2.setEditable(false);
        Tnorw2.setName("Tnorw2"); // NOI18N
        Tnorw2.setPreferredSize(new java.awt.Dimension(150, 24));
        Tnorw2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tnorw2ActionPerformed(evt);
            }
        });
        internalFrame10.add(Tnorw2);
        Tnorw2.setBounds(90, 30, 90, 24);

        jLabel98.setText("DPJP :");
        jLabel98.setName("jLabel98"); // NOI18N
        internalFrame10.add(jLabel98);
        jLabel98.setBounds(0, 70, 77, 23);

        scrollPane18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane18.setName("scrollPane18"); // NOI18N

        soap.setColumns(20);
        soap.setRows(5);
        soap.setName("soap"); // NOI18N
        soap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                soapKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(soap);

        internalFrame10.add(scrollPane18);
        scrollPane18.setBounds(90, 130, 300, 210);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        instruksi.setColumns(20);
        instruksi.setRows(5);
        instruksi.setName("instruksi"); // NOI18N
        instruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                instruksiKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(instruksi);

        internalFrame10.add(scrollPane19);
        scrollPane19.setBounds(470, 130, 300, 210);

        Tglmasuk.setEditable(false);
        Tglmasuk.setName("Tglmasuk"); // NOI18N
        Tglmasuk.setPreferredSize(new java.awt.Dimension(150, 24));
        Tglmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TglmasukActionPerformed(evt);
            }
        });
        internalFrame10.add(Tglmasuk);
        Tglmasuk.setBounds(460, 70, 120, 24);

        jLabel100.setText("Tgl.Masuk Pasien :");
        jLabel100.setName("jLabel100"); // NOI18N
        internalFrame10.add(jLabel100);
        jLabel100.setBounds(360, 70, 90, 23);

        kdppjp.setEditable(false);
        kdppjp.setName("kdppjp"); // NOI18N
        kdppjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdppjpActionPerformed(evt);
            }
        });
        internalFrame10.add(kdppjp);
        kdppjp.setBounds(90, 100, 50, 24);

        ppjp.setEditable(false);
        ppjp.setName("ppjp"); // NOI18N
        ppjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppjpActionPerformed(evt);
            }
        });
        internalFrame10.add(ppjp);
        ppjp.setBounds(140, 100, 210, 24);

        btncaripetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaripetugas1.setMnemonic('3');
        btncaripetugas1.setToolTipText("Alt+3");
        btncaripetugas1.setName("btncaripetugas1"); // NOI18N
        btncaripetugas1.setPreferredSize(new java.awt.Dimension(28, 23));
        btncaripetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugas1ActionPerformed(evt);
            }
        });
        btncaripetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncaripetugas1KeyPressed(evt);
            }
        });
        internalFrame10.add(btncaripetugas1);
        btncaripetugas1.setBounds(360, 100, 28, 23);

        Tglmasuk1.setEditable(false);
        Tglmasuk1.setName("Tglmasuk1"); // NOI18N
        Tglmasuk1.setPreferredSize(new java.awt.Dimension(150, 24));
        Tglmasuk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tglmasuk1ActionPerformed(evt);
            }
        });
        internalFrame10.add(Tglmasuk1);
        Tglmasuk1.setBounds(630, 30, 120, 24);

        jLabel101.setText("Jam :");
        jLabel101.setName("jLabel101"); // NOI18N
        internalFrame10.add(jLabel101);
        jLabel101.setBounds(560, 30, 60, 23);

        Tnorw3.setEditable(false);
        Tnorw3.setName("Tnorw3"); // NOI18N
        Tnorw3.setPreferredSize(new java.awt.Dimension(150, 24));
        Tnorw3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tnorw3ActionPerformed(evt);
            }
        });
        internalFrame10.add(Tnorw3);
        Tnorw3.setBounds(190, 30, 160, 24);

        jLabel104.setText("PPA :");
        jLabel104.setName("jLabel104"); // NOI18N
        internalFrame10.add(jLabel104);
        jLabel104.setBounds(370, 100, 77, 23);

        kdppa.setEditable(false);
        kdppa.setName("kdppa"); // NOI18N
        kdppa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdppaActionPerformed(evt);
            }
        });
        internalFrame10.add(kdppa);
        kdppa.setBounds(460, 100, 50, 24);

        ppa.setEditable(false);
        ppa.setName("ppa"); // NOI18N
        ppa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppaActionPerformed(evt);
            }
        });
        internalFrame10.add(ppa);
        ppa.setBounds(510, 100, 210, 24);

        btncaripetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btncaripetugas2.setMnemonic('3');
        btncaripetugas2.setToolTipText("Alt+3");
        btncaripetugas2.setName("btncaripetugas2"); // NOI18N
        btncaripetugas2.setPreferredSize(new java.awt.Dimension(28, 23));
        btncaripetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugas2ActionPerformed(evt);
            }
        });
        btncaripetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncaripetugas2KeyPressed(evt);
            }
        });
        internalFrame10.add(btncaripetugas2);
        btncaripetugas2.setBounds(730, 100, 28, 23);

        jLabel105.setText("PPJP :");
        jLabel105.setName("jLabel105"); // NOI18N
        internalFrame10.add(jLabel105);
        jLabel105.setBounds(0, 100, 77, 23);

        jLabel108.setText("Instruksi PPA :");
        jLabel108.setName("jLabel108"); // NOI18N
        internalFrame10.add(jLabel108);
        jLabel108.setBounds(390, 130, 80, 30);

        jLabel109.setText("Pasien & ");
        jLabel109.setName("jLabel109"); // NOI18N
        internalFrame10.add(jLabel109);
        jLabel109.setBounds(10, 150, 80, 30);

        jLabel110.setText("Pemberian ");
        jLabel110.setName("jLabel110"); // NOI18N
        internalFrame10.add(jLabel110);
        jLabel110.setBounds(10, 170, 80, 30);

        jLabel111.setText("Hasil Asessmen");
        jLabel111.setName("jLabel111"); // NOI18N
        internalFrame10.add(jLabel111);
        jLabel111.setBounds(10, 130, 80, 30);

        WindowCPPT.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Perawatan/Tindakan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setBackground(new java.awt.Color(215, 225, 215));
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
        BtnSimpan.setIconTextGap(3);
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
        BtnBatal.setIconTextGap(3);
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
        BtnHapus.setIconTextGap(3);
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
        BtnEdit.setIconTextGap(3);
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
        BtnPrint.setIconTextGap(3);
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

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(87, 30));
        panelGlass8.add(LCount);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setIconTextGap(3);
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
        jLabel10.setPreferredSize(new java.awt.Dimension(95, 30));
        panelGlass8.add(jLabel10);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setIconTextGap(3);
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

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

        jLabel20.setText("No.RM :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel20);

        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass10.add(TCariPasien);

        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('6');
        btnPasien.setToolTipText("Alt+6");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        btnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPasienKeyPressed(evt);
            }
        });
        panelGlass10.add(btnPasien);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass10.add(jSeparator5);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(273, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setToolTipText("Alt+7");
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
        panelGlass10.add(BtnCari);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabRawat.setDoubleBuffered(true);
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(500, 800));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbawal.setAutoCreateRowSorter(true);
        tbawal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbawal.setComponentPopupMenu(jPopupMenu1);
        tbawal.setName("tbawal"); // NOI18N
        tbawal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbawalMouseClicked(evt);
            }
        });
        tbawal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbawalKeyReleased(evt);
            }
        });
        Scroll6.setViewportView(tbawal);

        internalFrame2.add(Scroll6, java.awt.BorderLayout.CENTER);

        PanelInput4.setName("PanelInput4"); // NOI18N
        PanelInput4.setOpaque(false);
        PanelInput4.setPreferredSize(new java.awt.Dimension(190, 190));
        PanelInput4.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setMnemonic('I');
        ChkInput3.setText(".: Input Data");
        ChkInput3.setToolTipText("Alt+I");
        ChkInput3.setBorderPainted(true);
        ChkInput3.setBorderPaintedFlat(true);
        ChkInput3.setFocusable(false);
        ChkInput3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput3.setName("ChkInput3"); // NOI18N
        ChkInput3.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkInput3ItemStateChanged(evt);
            }
        });
        ChkInput3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput3ActionPerformed(evt);
            }
        });
        PanelInput4.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(452, 402));
        panelGlass16.setLayout(null);

        jLabel85.setText("Asesment Awal Medis ");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        panelGlass16.add(jLabel85);
        jLabel85.setBounds(420, 0, 140, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Triwayat_alergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triwayat_alergi.setColumns(20);
        Triwayat_alergi.setRows(5);
        Triwayat_alergi.setName("Triwayat_alergi"); // NOI18N
        Triwayat_alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triwayat_alergiKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Triwayat_alergi);

        panelGlass16.add(scrollPane4);
        scrollPane4.setBounds(90, 320, 360, 60);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Tkel_utama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkel_utama.setColumns(20);
        Tkel_utama.setRows(5);
        Tkel_utama.setName("Tkel_utama"); // NOI18N
        Tkel_utama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkel_utamaKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Tkel_utama);

        panelGlass16.add(scrollPane7);
        scrollPane7.setBounds(90, 30, 360, 80);

        jLabel89.setText(" Utama :");
        jLabel89.setName("jLabel89"); // NOI18N
        panelGlass16.add(jLabel89);
        jLabel89.setBounds(0, 50, 85, 23);

        jLabel90.setText("Keluhan  ");
        jLabel90.setName("jLabel90"); // NOI18N
        panelGlass16.add(jLabel90);
        jLabel90.setBounds(0, 30, 85, 23);

        jLabel92.setText("Riwayat");
        jLabel92.setName("jLabel92"); // NOI18N
        panelGlass16.add(jLabel92);
        jLabel92.setBounds(0, 320, 85, 23);

        jLabel93.setText("Sekarang :");
        jLabel93.setName("jLabel93"); // NOI18N
        panelGlass16.add(jLabel93);
        jLabel93.setBounds(10, 140, 110, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Triwayat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triwayat.setColumns(20);
        Triwayat.setRows(5);
        Triwayat.setName("Triwayat"); // NOI18N
        Triwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwayatKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Triwayat);

        panelGlass16.add(scrollPane8);
        scrollPane8.setBounds(120, 120, 330, 90);

        jLabel96.setText(" Riwayat Penyakit ");
        jLabel96.setName("jLabel96"); // NOI18N
        panelGlass16.add(jLabel96);
        jLabel96.setBounds(10, 120, 110, 23);

        jLabel102.setText("Alergi : ");
        jLabel102.setName("jLabel102"); // NOI18N
        panelGlass16.add(jLabel102);
        jLabel102.setBounds(0, 340, 85, 20);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        Triwayat_dulu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triwayat_dulu.setColumns(20);
        Triwayat_dulu.setRows(5);
        Triwayat_dulu.setName("Triwayat_dulu"); // NOI18N
        Triwayat_dulu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triwayat_duluKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Triwayat_dulu);

        panelGlass16.add(scrollPane11);
        scrollPane11.setBounds(120, 220, 330, 90);

        jLabel97.setText(" Riwayat Penyakit ");
        jLabel97.setName("jLabel97"); // NOI18N
        panelGlass16.add(jLabel97);
        jLabel97.setBounds(10, 220, 110, 23);

        jLabel94.setText("Dahulu :");
        jLabel94.setName("jLabel94"); // NOI18N
        panelGlass16.add(jLabel94);
        jLabel94.setBounds(10, 240, 110, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Tdx_kerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdx_kerja.setColumns(20);
        Tdx_kerja.setRows(5);
        Tdx_kerja.setName("Tdx_kerja"); // NOI18N
        Tdx_kerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdx_kerjaKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Tdx_kerja);

        panelGlass16.add(scrollPane12);
        scrollPane12.setBounds(550, 110, 360, 80);

        scrollPane13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane13.setName("scrollPane13"); // NOI18N

        Triwayat_keluarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triwayat_keluarga.setColumns(20);
        Triwayat_keluarga.setRows(5);
        Triwayat_keluarga.setName("Triwayat_keluarga"); // NOI18N
        Triwayat_keluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triwayat_keluargaKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Triwayat_keluarga);

        panelGlass16.add(scrollPane13);
        scrollPane13.setBounds(550, 30, 360, 60);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Tprognosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprognosisKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tprognosis);

        panelGlass16.add(scrollPane14);
        scrollPane14.setBounds(550, 300, 330, 90);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Tdx_banding.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdx_banding.setColumns(20);
        Tdx_banding.setRows(5);
        Tdx_banding.setName("Tdx_banding"); // NOI18N
        Tdx_banding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdx_bandingKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tdx_banding);

        panelGlass16.add(scrollPane15);
        scrollPane15.setBounds(550, 200, 330, 90);

        jLabel99.setText("Riwayat Penaykit");
        jLabel99.setName("jLabel99"); // NOI18N
        panelGlass16.add(jLabel99);
        jLabel99.setBounds(460, 30, 85, 23);

        jLabel103.setText("Keluarga : ");
        jLabel103.setName("jLabel103"); // NOI18N
        panelGlass16.add(jLabel103);
        jLabel103.setBounds(460, 50, 85, 20);

        jLabel91.setText("Diagnosis Kerja :");
        jLabel91.setName("jLabel91"); // NOI18N
        panelGlass16.add(jLabel91);
        jLabel91.setBounds(445, 110, 100, 23);

        jLabel106.setText(" Diagnosis Banding  :");
        jLabel106.setName("jLabel106"); // NOI18N
        panelGlass16.add(jLabel106);
        jLabel106.setBounds(440, 200, 110, 23);

        jLabel107.setText(" Prognosis : ");
        jLabel107.setName("jLabel107"); // NOI18N
        panelGlass16.add(jLabel107);
        jLabel107.setBounds(440, 300, 110, 23);

        PanelInput4.add(panelGlass16, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput4, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Asesment Awal Medis", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Scroll7KeyPressed(evt);
            }
        });

        tbNeonatal.setAutoCreateRowSorter(true);
        tbNeonatal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbNeonatal.setName("tbNeonatal"); // NOI18N
        tbNeonatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNeonatalMouseClicked(evt);
            }
        });
        tbNeonatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNeonatalKeyReleased(evt);
            }
        });
        Scroll7.setViewportView(tbNeonatal);

        internalFrame3.add(Scroll7, java.awt.BorderLayout.CENTER);

        PanelInput5.setName("PanelInput5"); // NOI18N
        PanelInput5.setOpaque(false);
        PanelInput5.setPreferredSize(new java.awt.Dimension(192, 245));
        PanelInput5.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setMnemonic('I');
        ChkInput4.setText(".: Input Data");
        ChkInput4.setToolTipText("Alt+I");
        ChkInput4.setBorderPainted(true);
        ChkInput4.setBorderPaintedFlat(true);
        ChkInput4.setFocusable(false);
        ChkInput4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput4.setName("ChkInput4"); // NOI18N
        ChkInput4.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkInput4ItemStateChanged(evt);
            }
        });
        ChkInput4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput4ActionPerformed(evt);
            }
        });
        PanelInput5.add(ChkInput4, java.awt.BorderLayout.PAGE_END);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 134));
        panelGlass17.setLayout(null);

        label18.setText("Ibu Bayi :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label18);
        label18.setBounds(0, 42, 85, 23);

        label22.setText("J.K.Bayi :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label22);
        label22.setBounds(0, 132, 85, 23);

        label24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label24.setText("Asesmen Keperawatan Neonatus");
        label24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label24);
        label24.setBounds(440, 10, 220, 23);

        Proses.setName("Proses"); // NOI18N
        Proses.setPreferredSize(new java.awt.Dimension(207, 23));
        Proses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsesKeyPressed(evt);
            }
        });
        panelGlass17.add(Proses);
        Proses.setBounds(577, 222, 300, 23);

        Anakke.setName("Anakke"); // NOI18N
        Anakke.setPreferredSize(new java.awt.Dimension(207, 23));
        Anakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnakkeKeyPressed(evt);
            }
        });
        panelGlass17.add(Anakke);
        Anakke.setBounds(370, 300, 95, 23);

        label25.setText("Kelahiran Ke :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label25);
        label25.setBounds(270, 300, 100, 23);

        LingkarKepala.setName("LingkarKepala"); // NOI18N
        LingkarKepala.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarKepalaKeyPressed(evt);
            }
        });
        panelGlass17.add(LingkarKepala);
        LingkarKepala.setBounds(370, 260, 95, 23);

        label27.setText("Lain-Lain :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label27);
        label27.setBounds(475, 102, 98, 23);

        JKel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        JKel.setName("JKel"); // NOI18N
        JKel.setPreferredSize(new java.awt.Dimension(100, 23));
        JKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKelKeyPressed(evt);
            }
        });
        panelGlass17.add(JKel);
        JKel.setBounds(89, 132, 130, 23);

        label23.setText("Berat Bayi(gram) :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label23);
        label23.setBounds(245, 132, 117, 23);

        label30.setText("Tgl. Lahir :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label30);
        label30.setBounds(0, 162, 85, 23);

        Lahir.setDisplayFormat("dd-MM-yyyy");
        Lahir.setName("Lahir"); // NOI18N
        Lahir.setVerifyInputWhenFocusTarget(false);
        Lahir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                LahirItemStateChanged(evt);
            }
        });
        Lahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LahirKeyPressed(evt);
            }
        });
        panelGlass17.add(Lahir);
        Lahir.setBounds(89, 162, 95, 23);

        label31.setText("Diagnosa :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label31);
        label31.setBounds(475, 162, 98, 23);

        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.setPreferredSize(new java.awt.Dimension(207, 23));
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        panelGlass17.add(Diagnosa);
        Diagnosa.setBounds(577, 162, 120, 23);

        Nmibu.setName("Nmibu"); // NOI18N
        Nmibu.setPreferredSize(new java.awt.Dimension(207, 23));
        Nmibu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmibuKeyPressed(evt);
            }
        });
        panelGlass17.add(Nmibu);
        Nmibu.setBounds(89, 40, 230, 23);

        label26.setText("Lingkar Kepala :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label26);
        label26.setBounds(270, 260, 100, 23);

        label19.setText("Nama Ayah :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label19);
        label19.setBounds(0, 72, 85, 23);

        NmAyah.setName("NmAyah"); // NOI18N
        NmAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        NmAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmAyahKeyPressed(evt);
            }
        });
        panelGlass17.add(NmAyah);
        NmAyah.setBounds(89, 72, 230, 23);

        label20.setText("Umur Ibu :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label20);
        label20.setBounds(292, 42, 100, 23);

        UmurIbu.setName("UmurIbu"); // NOI18N
        UmurIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurIbuKeyPressed(evt);
            }
        });
        panelGlass17.add(UmurIbu);
        UmurIbu.setBounds(395, 42, 70, 23);

        label21.setText("Alamat Ibu :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label21);
        label21.setBounds(0, 102, 85, 23);

        AlamatIbu.setName("AlamatIbu"); // NOI18N
        AlamatIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        AlamatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatIbuKeyPressed(evt);
            }
        });
        panelGlass17.add(AlamatIbu);
        AlamatIbu.setBounds(89, 102, 376, 23);

        label32.setText("Jam Lahir :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label32);
        label32.setBounds(189, 162, 80, 23);

        jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jam.setName("jam"); // NOI18N
        jam.setPreferredSize(new java.awt.Dimension(100, 23));
        jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamKeyPressed(evt);
            }
        });
        panelGlass17.add(jam);
        jam.setBounds(273, 162, 62, 23);

        menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        menit.setName("menit"); // NOI18N
        menit.setPreferredSize(new java.awt.Dimension(100, 23));
        menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menitKeyPressed(evt);
            }
        });
        panelGlass17.add(menit);
        menit.setBounds(338, 162, 62, 23);

        detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        detik.setName("detik"); // NOI18N
        detik.setPreferredSize(new java.awt.Dimension(100, 23));
        detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                detikKeyPressed(evt);
            }
        });
        panelGlass17.add(detik);
        detik.setBounds(403, 162, 62, 23);

        label34.setText("Proses Kelahiran :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label34);
        label34.setBounds(475, 222, 98, 23);

        Berat.setName("Berat"); // NOI18N
        Berat.setPreferredSize(new java.awt.Dimension(207, 23));
        Berat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BeratKeyPressed(evt);
            }
        });
        panelGlass17.add(Berat);
        Berat.setBounds(365, 132, 100, 23);

        Panjang.setName("Panjang"); // NOI18N
        Panjang.setPreferredSize(new java.awt.Dimension(207, 23));
        Panjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanjangKeyPressed(evt);
            }
        });
        panelGlass17.add(Panjang);
        Panjang.setBounds(180, 222, 95, 23);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        keterangan.setColumns(20);
        keterangan.setRows(5);
        keterangan.setName("keterangan"); // NOI18N
        keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keteranganKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(keterangan);

        panelGlass17.add(scrollPane9);
        scrollPane9.setBounds(577, 102, 300, 52);

        label35.setText("Umur Ayah :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label35);
        label35.setBounds(292, 72, 100, 23);

        UmurAyah.setName("UmurAyah"); // NOI18N
        UmurAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurAyahKeyPressed(evt);
            }
        });
        panelGlass17.add(UmurAyah);
        UmurAyah.setBounds(395, 72, 70, 23);

        label36.setText("Umur Bayi :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label36);
        label36.setBounds(70, 260, 98, 23);

        UmurBayi.setName("UmurBayi"); // NOI18N
        UmurBayi.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurBayiKeyPressed(evt);
            }
        });
        panelGlass17.add(UmurBayi);
        UmurBayi.setBounds(180, 260, 95, 23);

        label37.setText("Penyulit Kehamilan :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label37);
        label37.setBounds(473, 192, 100, 23);

        PenyulitKehamilan.setName("PenyulitKehamilan"); // NOI18N
        PenyulitKehamilan.setPreferredSize(new java.awt.Dimension(207, 23));
        PenyulitKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyulitKehamilanKeyPressed(evt);
            }
        });
        panelGlass17.add(PenyulitKehamilan);
        PenyulitKehamilan.setBounds(577, 192, 300, 23);

        label38.setText("Ketuban :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label38);
        label38.setBounds(697, 162, 57, 23);

        Ketuban.setName("Ketuban"); // NOI18N
        Ketuban.setPreferredSize(new java.awt.Dimension(207, 23));
        Ketuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanKeyPressed(evt);
            }
        });
        panelGlass17.add(Ketuban);
        Ketuban.setBounds(757, 162, 120, 23);

        label39.setText("Lingkar Dada :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label39);
        label39.setBounds(80, 300, 100, 23);

        LingkarDada.setName("LingkarDada"); // NOI18N
        LingkarDada.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarDadaKeyPressed(evt);
            }
        });
        panelGlass17.add(LingkarDada);
        LingkarDada.setBounds(180, 300, 95, 23);

        LingkarPerut.setName("LingkarPerut"); // NOI18N
        LingkarPerut.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarPerutKeyPressed(evt);
            }
        });
        panelGlass17.add(LingkarPerut);
        LingkarPerut.setBounds(370, 222, 95, 23);

        label40.setText("Lingkar Perut :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label40);
        label40.setBounds(280, 222, 85, 23);

        jLabel113.setText("Penolong Kelahiran :");
        jLabel113.setName("jLabel113"); // NOI18N
        panelGlass17.add(jLabel113);
        jLabel113.setBounds(475, 40, 100, 23);

        KdPenolong.setEditable(false);
        KdPenolong.setHighlighter(null);
        KdPenolong.setName("KdPenolong"); // NOI18N
        KdPenolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPenolongKeyPressed(evt);
            }
        });
        panelGlass17.add(KdPenolong);
        KdPenolong.setBounds(90, 192, 100, 23);

        NmPenolong.setEditable(false);
        NmPenolong.setName("NmPenolong"); // NOI18N
        panelGlass17.add(NmPenolong);
        NmPenolong.setBounds(192, 192, 241, 23);

        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("ALt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnPenjab);
        BtnPenjab.setBounds(437, 192, 28, 23);

        label29.setText("Panjang Badan :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label29);
        label29.setBounds(80, 222, 98, 23);

        label41.setText("Usia Kehamilan :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label41);
        label41.setBounds(475, 70, 100, 23);

        label42.setText("GPA :");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label42);
        label42.setBounds(700, 70, 40, 23);

        usia_kehamilan.setName("usia_kehamilan"); // NOI18N
        usia_kehamilan.setPreferredSize(new java.awt.Dimension(207, 23));
        usia_kehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usia_kehamilanKeyPressed(evt);
            }
        });
        panelGlass17.add(usia_kehamilan);
        usia_kehamilan.setBounds(580, 70, 120, 23);

        GPA.setName("GPA"); // NOI18N
        GPA.setPreferredSize(new java.awt.Dimension(207, 23));
        GPA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GPAKeyPressed(evt);
            }
        });
        panelGlass17.add(GPA);
        GPA.setBounds(750, 70, 120, 23);

        BtnPenjab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab1.setMnemonic('1');
        BtnPenjab1.setToolTipText("ALt+1");
        BtnPenjab1.setName("BtnPenjab1"); // NOI18N
        BtnPenjab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjab1ActionPerformed(evt);
            }
        });
        panelGlass17.add(BtnPenjab1);
        BtnPenjab1.setBounds(920, 40, 28, 23);

        NmPenolong1.setEditable(false);
        NmPenolong1.setName("NmPenolong1"); // NOI18N
        panelGlass17.add(NmPenolong1);
        NmPenolong1.setBounds(682, 40, 241, 23);

        KdPenolong1.setEditable(false);
        KdPenolong1.setHighlighter(null);
        KdPenolong1.setName("KdPenolong1"); // NOI18N
        KdPenolong1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPenolong1KeyPressed(evt);
            }
        });
        panelGlass17.add(KdPenolong1);
        KdPenolong1.setBounds(580, 40, 100, 23);

        jLabel114.setText("Penolong/DPJP :");
        jLabel114.setName("jLabel114"); // NOI18N
        panelGlass17.add(jLabel114);
        jLabel114.setBounds(0, 192, 85, 23);

        label28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label28.setText("2");
        label28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label28);
        label28.setBounds(490, 375, 120, 23);

        label33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label33.setText("II. Tanda Vital");
        label33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label33);
        label33.setBounds(960, 20, 100, 23);

        label43.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label43.setText("Nilai Apgar");
        label43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label43);
        label43.setBounds(20, 350, 590, 23);

        label44.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label44.setText("10'");
        label44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label44);
        label44.setBounds(740, 375, 60, 23);

        label45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label45.setText("Tanda");
        label45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label45);
        label45.setBounds(20, 375, 160, 23);

        label46.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label46.setText("0");
        label46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label46);
        label46.setBounds(180, 375, 140, 23);

        label47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label47.setText("1");
        label47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label47);
        label47.setBounds(320, 375, 170, 23);

        label48.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label48.setText("Frekuensi Jantung");
        label48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label48);
        label48.setBounds(20, 400, 160, 23);

        label49.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label49.setText("Tidak Ada");
        label49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label49);
        label49.setBounds(180, 400, 140, 23);

        label50.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label50.setText("< 100");
        label50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label50.setName("label50"); // NOI18N
        label50.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label50);
        label50.setBounds(320, 400, 170, 23);

        label51.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label51.setText("> 100");
        label51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label51.setName("label51"); // NOI18N
        label51.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label51);
        label51.setBounds(490, 400, 120, 23);

        label52.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label52.setText("Usaha Nafas");
        label52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label52.setName("label52"); // NOI18N
        label52.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label52);
        label52.setBounds(20, 420, 160, 23);

        label53.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label53.setText("Tidak Ada");
        label53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label53.setName("label53"); // NOI18N
        label53.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label53);
        label53.setBounds(180, 420, 140, 23);

        label54.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label54.setText("Lambat Tidak Teratur");
        label54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label54);
        label54.setBounds(320, 420, 170, 23);

        label55.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label55.setText("Menangis Kuat");
        label55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label55);
        label55.setBounds(490, 420, 120, 23);

        label56.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label56.setText("Tonus Otot");
        label56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label56.setName("label56"); // NOI18N
        label56.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label56);
        label56.setBounds(20, 440, 160, 23);

        label57.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label57.setText("Lumpuh");
        label57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label57.setName("label57"); // NOI18N
        label57.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label57);
        label57.setBounds(180, 440, 140, 23);

        label58.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label58.setText("Ext. Fleksi Dikit");
        label58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label58.setName("label58"); // NOI18N
        label58.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label58);
        label58.setBounds(320, 440, 170, 23);

        label59.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label59.setText("Gerakan Aktif");
        label59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label59.setName("label59"); // NOI18N
        label59.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label59);
        label59.setBounds(490, 440, 120, 23);

        label60.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label60.setText("Refleks");
        label60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label60.setName("label60"); // NOI18N
        label60.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label60);
        label60.setBounds(20, 460, 160, 23);

        label61.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label61.setText("Tidak Ada Respon");
        label61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label61.setName("label61"); // NOI18N
        label61.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label61);
        label61.setBounds(180, 460, 140, 23);

        label62.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label62.setText("Pergerakan Sedikit");
        label62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label62.setName("label62"); // NOI18N
        label62.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label62);
        label62.setBounds(320, 460, 170, 23);

        label63.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label63.setText("Menangis");
        label63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label63.setName("label63"); // NOI18N
        label63.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label63);
        label63.setBounds(490, 460, 120, 23);

        label64.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label64.setText("Warna Kulit");
        label64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label64.setName("label64"); // NOI18N
        label64.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label64);
        label64.setBounds(20, 480, 160, 23);

        label65.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label65.setText("Biru Pucat");
        label65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label65.setName("label65"); // NOI18N
        label65.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label65);
        label65.setBounds(180, 480, 140, 23);

        label66.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label66.setText("Tubuh Kemerahan Tangan dan Kaki Biru");
        label66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label66.setName("label66"); // NOI18N
        label66.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label66);
        label66.setBounds(320, 480, 170, 23);

        label67.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label67.setText("Kemerahan");
        label67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label67.setName("label67"); // NOI18N
        label67.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label67);
        label67.setBounds(490, 480, 120, 23);

        label68.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label68.setText("Nilai");
        label68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label68.setName("label68"); // NOI18N
        label68.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label68);
        label68.setBounds(610, 350, 190, 23);

        label69.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label69.setText("1'");
        label69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label69.setName("label69"); // NOI18N
        label69.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label69);
        label69.setBounds(610, 375, 70, 23);

        label70.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        label70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label70.setText("5'");
        label70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label70.setName("label70"); // NOI18N
        label70.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label70);
        label70.setBounds(680, 375, 60, 23);

        c1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        c1.setName("c1"); // NOI18N
        c1.setPreferredSize(new java.awt.Dimension(100, 23));
        c1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c1ItemStateChanged(evt);
            }
        });
        c1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c1KeyPressed(evt);
            }
        });
        panelGlass17.add(c1);
        c1.setBounds(740, 400, 60, 23);

        a1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        a1.setName("a1"); // NOI18N
        a1.setPreferredSize(new java.awt.Dimension(100, 23));
        a1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                a1ItemStateChanged(evt);
            }
        });
        a1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a1KeyPressed(evt);
            }
        });
        panelGlass17.add(a1);
        a1.setBounds(610, 400, 70, 23);

        b1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        b1.setName("b1"); // NOI18N
        b1.setPreferredSize(new java.awt.Dimension(100, 23));
        b1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                b1ItemStateChanged(evt);
            }
        });
        b1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b1KeyPressed(evt);
            }
        });
        panelGlass17.add(b1);
        b1.setBounds(680, 400, 60, 23);

        c2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        c2.setName("c2"); // NOI18N
        c2.setPreferredSize(new java.awt.Dimension(100, 23));
        c2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c2ItemStateChanged(evt);
            }
        });
        c2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c2KeyPressed(evt);
            }
        });
        panelGlass17.add(c2);
        c2.setBounds(740, 420, 60, 23);

        a2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        a2.setName("a2"); // NOI18N
        a2.setPreferredSize(new java.awt.Dimension(100, 23));
        a2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                a2ItemStateChanged(evt);
            }
        });
        a2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a2KeyPressed(evt);
            }
        });
        panelGlass17.add(a2);
        a2.setBounds(610, 420, 70, 23);

        b2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        b2.setName("b2"); // NOI18N
        b2.setPreferredSize(new java.awt.Dimension(100, 23));
        b2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                b2ItemStateChanged(evt);
            }
        });
        b2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b2KeyPressed(evt);
            }
        });
        panelGlass17.add(b2);
        b2.setBounds(680, 420, 60, 23);

        c3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        c3.setName("c3"); // NOI18N
        c3.setPreferredSize(new java.awt.Dimension(100, 23));
        c3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c3ItemStateChanged(evt);
            }
        });
        c3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c3KeyPressed(evt);
            }
        });
        panelGlass17.add(c3);
        c3.setBounds(740, 440, 60, 23);

        a3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        a3.setName("a3"); // NOI18N
        a3.setPreferredSize(new java.awt.Dimension(100, 23));
        a3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                a3ItemStateChanged(evt);
            }
        });
        a3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a3KeyPressed(evt);
            }
        });
        panelGlass17.add(a3);
        a3.setBounds(610, 440, 70, 23);

        b3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        b3.setName("b3"); // NOI18N
        b3.setPreferredSize(new java.awt.Dimension(100, 23));
        b3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                b3ItemStateChanged(evt);
            }
        });
        b3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b3KeyPressed(evt);
            }
        });
        panelGlass17.add(b3);
        b3.setBounds(680, 440, 60, 23);

        c4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        c4.setName("c4"); // NOI18N
        c4.setPreferredSize(new java.awt.Dimension(100, 23));
        c4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c4ItemStateChanged(evt);
            }
        });
        c4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c4KeyPressed(evt);
            }
        });
        panelGlass17.add(c4);
        c4.setBounds(740, 460, 60, 23);

        a4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        a4.setName("a4"); // NOI18N
        a4.setPreferredSize(new java.awt.Dimension(100, 23));
        a4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                a4ItemStateChanged(evt);
            }
        });
        a4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a4KeyPressed(evt);
            }
        });
        panelGlass17.add(a4);
        a4.setBounds(610, 460, 70, 23);

        b4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        b4.setName("b4"); // NOI18N
        b4.setPreferredSize(new java.awt.Dimension(100, 23));
        b4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                b4ItemStateChanged(evt);
            }
        });
        b4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b4KeyPressed(evt);
            }
        });
        panelGlass17.add(b4);
        b4.setBounds(680, 460, 60, 23);

        c5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        c5.setName("c5"); // NOI18N
        c5.setPreferredSize(new java.awt.Dimension(100, 23));
        c5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c5ItemStateChanged(evt);
            }
        });
        c5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c5KeyPressed(evt);
            }
        });
        panelGlass17.add(c5);
        c5.setBounds(740, 480, 60, 23);

        a5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        a5.setName("a5"); // NOI18N
        a5.setPreferredSize(new java.awt.Dimension(100, 23));
        a5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                a5ItemStateChanged(evt);
            }
        });
        a5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a5KeyPressed(evt);
            }
        });
        panelGlass17.add(a5);
        a5.setBounds(610, 480, 70, 23);

        b5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        b5.setName("b5"); // NOI18N
        b5.setPreferredSize(new java.awt.Dimension(100, 23));
        b5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                b5ItemStateChanged(evt);
            }
        });
        b5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b5KeyPressed(evt);
            }
        });
        panelGlass17.add(b5);
        b5.setBounds(680, 480, 60, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        resusitasi.setColumns(20);
        resusitasi.setRows(5);
        resusitasi.setName("resusitasi"); // NOI18N
        resusitasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                resusitasiKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(resusitasi);

        panelGlass17.add(scrollPane10);
        scrollPane10.setBounds(930, 360, 300, 60);

        label71.setText("Obat yg Diberi :");
        label71.setName("label71"); // NOI18N
        label71.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label71);
        label71.setBounds(830, 430, 98, 23);

        label72.setText("Mikasi :");
        label72.setName("label72"); // NOI18N
        label72.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label72);
        label72.setBounds(830, 460, 100, 23);

        mikasi.setName("mikasi"); // NOI18N
        mikasi.setPreferredSize(new java.awt.Dimension(207, 23));
        mikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mikasiKeyPressed(evt);
            }
        });
        panelGlass17.add(mikasi);
        mikasi.setBounds(940, 460, 300, 23);

        label73.setText("Resusitasi :");
        label73.setName("label73"); // NOI18N
        label73.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label73);
        label73.setBounds(830, 360, 98, 23);

        obatdiberi.setName("obatdiberi"); // NOI18N
        obatdiberi.setPreferredSize(new java.awt.Dimension(207, 23));
        obatdiberi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                obatdiberiKeyPressed(evt);
            }
        });
        panelGlass17.add(obatdiberi);
        obatdiberi.setBounds(940, 430, 300, 23);

        label74.setText("Mekonium :");
        label74.setName("label74"); // NOI18N
        label74.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label74);
        label74.setBounds(830, 490, 100, 23);

        mekonium.setName("mekonium"); // NOI18N
        mekonium.setPreferredSize(new java.awt.Dimension(207, 23));
        mekonium.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mekoniumKeyPressed(evt);
            }
        });
        panelGlass17.add(mekonium);
        mekonium.setBounds(940, 490, 300, 23);

        label75.setText("Jumah Nilai :");
        label75.setName("label75"); // NOI18N
        label75.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label75);
        label75.setBounds(487, 510, 100, 23);

        cjumlah.setEditable(false);
        cjumlah.setName("cjumlah"); // NOI18N
        cjumlah.setPreferredSize(new java.awt.Dimension(207, 23));
        cjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cjumlahKeyPressed(evt);
            }
        });
        panelGlass17.add(cjumlah);
        cjumlah.setBounds(740, 510, 70, 23);

        ajumlah.setEditable(false);
        ajumlah.setName("ajumlah"); // NOI18N
        ajumlah.setPreferredSize(new java.awt.Dimension(207, 23));
        ajumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ajumlahKeyPressed(evt);
            }
        });
        panelGlass17.add(ajumlah);
        ajumlah.setBounds(600, 510, 70, 23);

        bjumlah.setEditable(false);
        bjumlah.setName("bjumlah"); // NOI18N
        bjumlah.setPreferredSize(new java.awt.Dimension(207, 23));
        bjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bjumlahKeyPressed(evt);
            }
        });
        panelGlass17.add(bjumlah);
        bjumlah.setBounds(670, 510, 70, 23);

        label76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label76.setText("I. Identitas Pasien");
        label76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label76.setName("label76"); // NOI18N
        label76.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label76);
        label76.setBounds(10, 20, 220, 23);

        jLabel66.setText("Suhu Badan(C) :");
        jLabel66.setName("jLabel66"); // NOI18N
        panelGlass17.add(jLabel66);
        jLabel66.setBounds(960, 40, 100, 23);

        TSuhu1.setFocusTraversalPolicyProvider(true);
        TSuhu1.setName("TSuhu1"); // NOI18N
        TSuhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhu1KeyPressed(evt);
            }
        });
        panelGlass17.add(TSuhu1);
        TSuhu1.setBounds(1060, 40, 60, 23);

        jLabel68.setText("Nadi(/menit) :");
        jLabel68.setName("jLabel68"); // NOI18N
        panelGlass17.add(jLabel68);
        jLabel68.setBounds(1120, 40, 79, 23);

        TNadi1.setFocusTraversalPolicyProvider(true);
        TNadi1.setName("TNadi1"); // NOI18N
        TNadi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNadi1ActionPerformed(evt);
            }
        });
        TNadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadi1KeyPressed(evt);
            }
        });
        panelGlass17.add(TNadi1);
        TNadi1.setBounds(1200, 40, 60, 23);

        jLabel69.setText("Respirasi(/menit) :");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass17.add(jLabel69);
        jLabel69.setBounds(960, 70, 100, 23);

        TRespirasi1.setHighlighter(null);
        TRespirasi1.setName("TRespirasi1"); // NOI18N
        TRespirasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasi1KeyPressed(evt);
            }
        });
        panelGlass17.add(TRespirasi1);
        TRespirasi1.setBounds(1060, 70, 60, 23);

        jLabel70.setText("Sp02 :");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass17.add(jLabel70);
        jLabel70.setBounds(1120, 70, 79, 23);

        spo2.setFocusTraversalPolicyProvider(true);
        spo2.setName("spo2"); // NOI18N
        spo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spo2ActionPerformed(evt);
            }
        });
        spo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spo2KeyPressed(evt);
            }
        });
        panelGlass17.add(spo2);
        spo2.setBounds(1200, 70, 60, 23);

        label77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label77.setText("II. Catatan Keperawatan");
        label77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label77.setName("label77"); // NOI18N
        label77.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass17.add(label77);
        label77.setBounds(960, 120, 170, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        cttnkeperawatan.setColumns(20);
        cttnkeperawatan.setRows(5);
        cttnkeperawatan.setName("cttnkeperawatan"); // NOI18N
        cttnkeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cttnkeperawatanKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(cttnkeperawatan);

        panelGlass17.add(scrollPane16);
        scrollPane16.setBounds(960, 140, 310, 120);

        PanelInput5.add(panelGlass17, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput5, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemeriksaan Neonatal", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(500, 800));
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPemeriksaan.setAutoCreateRowSorter(true);
        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setComponentPopupMenu(jPopupMenu1);
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyReleased(evt);
            }
        });
        Scroll3.setViewportView(tbPemeriksaan);

        internalFrame4.add(Scroll3, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(190, 190));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

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
        ChkInput.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkInputItemStateChanged(evt);
            }
        });
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(452, 402));
        panelGlass12.setLayout(null);

        jLabel7.setText("Suhu Badan(C) :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(480, 60, 100, 23);

        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass12.add(TSuhu);
        TSuhu.setBounds(580, 60, 60, 23);

        jLabel4.setText("TD : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass12.add(jLabel4);
        jLabel4.setBounds(640, 60, 60, 23);

        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTensi);
        TTensi.setBounds(700, 60, 60, 23);

        jLabel25.setText("Berat(Kg) :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass12.add(jLabel25);
        jLabel25.setBounds(750, 60, 79, 23);

        TBerat.setHighlighter(null);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        panelGlass12.add(TBerat);
        TBerat.setBounds(830, 60, 60, 23);

        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNadiActionPerformed(evt);
            }
        });
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelGlass12.add(TNadi);
        TNadi.setBounds(830, 90, 60, 23);

        jLabel24.setText("Nadi(/menit) :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass12.add(jLabel24);
        jLabel24.setBounds(750, 90, 79, 23);

        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTinggi);
        TTinggi.setBounds(580, 90, 60, 23);

        jLabel16.setText("Tinggi Badan(Cm) :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(480, 90, 100, 23);

        jLabel15.setText("Pemeriksaan Fisik ");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass12.add(jLabel15);
        jLabel15.setBounds(540, 0, 110, 23);

        TAlergi.setHighlighter(null);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        panelGlass12.add(TAlergi);
        TAlergi.setBounds(580, 30, 308, 23);

        jLabel23.setText("Respirasi(/menit) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(480, 120, 100, 23);

        TRespirasi.setHighlighter(null);
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        panelGlass12.add(TRespirasi);
        TRespirasi.setBounds(580, 120, 60, 23);

        jLabel22.setText("GCS(E,V,M) :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(650, 120, 70, 23);

        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        panelGlass12.add(TGCS);
        TGCS.setBounds(730, 120, 165, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        TKeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKeluhan);

        panelGlass12.add(scrollPane1);
        scrollPane1.setBounds(90, 40, 360, 40);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        TPemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPemeriksaan.setColumns(20);
        TPemeriksaan.setRows(5);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(TPemeriksaan);

        panelGlass12.add(scrollPane2);
        scrollPane2.setBounds(90, 100, 360, 40);

        jLabel8.setText("Penyakit");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass12.add(jLabel8);
        jLabel8.setBounds(0, 80, 85, 20);

        jLabel9.setText(" Penunjang   :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(0, 120, 85, 23);

        jLabel17.setText("Pemeriksaan  ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass12.add(jLabel17);
        jLabel17.setBounds(0, 100, 85, 23);

        jLabel26.setText("Diagnosa Masuk :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass12.add(jLabel26);
        jLabel26.setBounds(0, 10, 85, 23);

        Diagnosaawal.setName("Diagnosaawal"); // NOI18N
        Diagnosaawal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiagnosaawalActionPerformed(evt);
            }
        });
        panelGlass12.add(Diagnosaawal);
        Diagnosaawal.setBounds(90, 10, 320, 24);

        jLabel37.setText("Ringkasan ");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass12.add(jLabel37);
        jLabel37.setBounds(0, 40, 85, 23);

        jLabel41.setText("Selama Di RS :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass12.add(jLabel41);
        jLabel41.setBounds(460, 180, 110, 23);

        jLabel53.setText("Alergi (Reaksi Obat) :");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass12.add(jLabel53);
        jLabel53.setBounds(470, 30, 110, 23);

        jLabel54.setText("Follow Up : ");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass12.add(jLabel54);
        jLabel54.setBounds(950, 150, 80, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Tterapi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tterapi.setColumns(20);
        Tterapi.setRows(5);
        Tterapi.setName("Tterapi"); // NOI18N
        Tterapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TterapiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tterapi);

        panelGlass12.add(scrollPane3);
        scrollPane3.setBounds(570, 160, 330, 40);

        jLabel55.setText(" Terapi Pengobatan ");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass12.add(jLabel55);
        jLabel55.setBounds(460, 160, 110, 23);

        jLabel56.setText("Hasil Konsul :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass12.add(jLabel56);
        jLabel56.setBounds(80, 150, 110, 23);

        jLabel58.setText("Prosedur ( kode ICD 9) :");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass12.add(jLabel58);
        jLabel58.setBounds(910, 90, 120, 50);

        jLabel59.setText("Hasil Labor (Pending) :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass12.add(jLabel59);
        jLabel59.setBounds(80, 180, 110, 23);

        Konsul.setName("Konsul"); // NOI18N
        panelGlass12.add(Konsul);
        Konsul.setBounds(190, 150, 260, 24);

        labor.setName("labor"); // NOI18N
        panelGlass12.add(labor);
        labor.setBounds(190, 180, 260, 24);

        CbLanjut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Poliklinik RS", "RS Lain", "Puskesmas", "Dokter Luar", "Lain-Lain" }));
        CbLanjut.setName("CbLanjut"); // NOI18N
        panelGlass12.add(CbLanjut);
        CbLanjut.setBounds(1150, 180, 100, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Tterapipulang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tterapipulang.setColumns(20);
        Tterapipulang.setRows(5);
        Tterapipulang.setName("Tterapipulang"); // NOI18N
        Tterapipulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TterapipulangKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Tterapipulang);

        panelGlass12.add(scrollPane5);
        scrollPane5.setBounds(990, 30, 260, 50);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tprosedur.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tprosedur.setColumns(20);
        Tprosedur.setRows(5);
        Tprosedur.setName("Tprosedur"); // NOI18N
        Tprosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprosedurKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tprosedur);

        panelGlass12.add(scrollPane6);
        scrollPane6.setBounds(1030, 90, 220, 50);

        jLabel61.setText("Terapi Pulang :");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass12.add(jLabel61);
        jLabel61.setBounds(910, 40, 80, 23);

        Tfollowup.setHighlighter(null);
        Tfollowup.setName("Tfollowup"); // NOI18N
        Tfollowup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfollowupKeyPressed(evt);
            }
        });
        panelGlass12.add(Tfollowup);
        Tfollowup.setBounds(1028, 146, 230, 24);

        jLabel63.setText("Pengobatan Lanjutan Ke :");
        jLabel63.setName("jLabel63"); // NOI18N
        panelGlass12.add(jLabel63);
        jLabel63.setBounds(1000, 180, 140, 23);

        jLabel65.setText("Penyakit");
        jLabel65.setName("jLabel65"); // NOI18N
        panelGlass12.add(jLabel65);
        jLabel65.setBounds(0, 60, 85, 20);

        PanelInput1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame4.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Resume Akhir", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 43));
        FormInput.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(null);
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 125, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(201, 10, 80, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(283, 10, 260, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-03-2021" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(617, 10, 90, 23);

        jLabel18.setText("Tanggal :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(554, 10, 60, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(711, 10, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(776, 10, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(841, 10, 62, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(906, 10, 23, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(135, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setBorderPainted(true);
        ChkAccor.setBorderPaintedFlat(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.EAST);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setToolTipText("");
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

        BtnCatatan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/menu.png"))); // NOI18N
        BtnCatatan1.setText("Menu Order");
        BtnCatatan1.setFocusPainted(false);
        BtnCatatan1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BtnCatatan1.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCatatan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCatatan1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCatatan1.setName("BtnCatatan1"); // NOI18N
        BtnCatatan1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCatatan1.setRoundRect(false);
        BtnCatatan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatan1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCatatan1);

        BtnCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnCPPT.setText("CPPT");
        BtnCPPT.setFocusPainted(false);
        BtnCPPT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCPPT.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCPPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCPPT.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCPPT.setName("BtnCPPT"); // NOI18N
        BtnCPPT.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCPPT.setRoundRect(false);
        BtnCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCPPTActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCPPT);

        BtnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnPermintaanLab.setText("Permintaan Lab");
        BtnPermintaanLab.setFocusPainted(false);
        BtnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPermintaanLab.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPermintaanLab.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPermintaanLab.setName("BtnPermintaanLab"); // NOI18N
        BtnPermintaanLab.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnPermintaanLab.setRoundRect(false);
        BtnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPermintaanLabActionPerformed(evt);
            }
        });
        BtnPermintaanLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPermintaanLabKeyPressed(evt);
            }
        });
        FormMenu.add(BtnPermintaanLab);

        BtnPermintaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnPermintaanRad.setText("Permintaan Rad");
        BtnPermintaanRad.setFocusPainted(false);
        BtnPermintaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPermintaanRad.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnPermintaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPermintaanRad.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPermintaanRad.setName("BtnPermintaanRad"); // NOI18N
        BtnPermintaanRad.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnPermintaanRad.setRoundRect(false);
        BtnPermintaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPermintaanRadActionPerformed(evt);
            }
        });
        BtnPermintaanRad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPermintaanRadKeyPressed(evt);
            }
        });
        FormMenu.add(BtnPermintaanRad);

        BtnUTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnUTD.setText("Permintaan UTD");
        BtnUTD.setFocusPainted(false);
        BtnUTD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnUTD.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnUTD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnUTD.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnUTD.setName("BtnUTD"); // NOI18N
        BtnUTD.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnUTD.setRoundRect(false);
        BtnUTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUTDActionPerformed(evt);
            }
        });
        FormMenu.add(BtnUTD);

        BtnIRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnIRM.setText("Permintaan IRM");
        BtnIRM.setFocusPainted(false);
        BtnIRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnIRM.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnIRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnIRM.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnIRM.setName("BtnIRM"); // NOI18N
        BtnIRM.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnIRM.setRoundRect(false);
        BtnIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIRMActionPerformed(evt);
            }
        });
        FormMenu.add(BtnIRM);

        BtnGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnGizi.setText("Asuhan GIzi");
        BtnGizi.setFocusPainted(false);
        BtnGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnGizi.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnGizi.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnGizi.setName("BtnGizi"); // NOI18N
        BtnGizi.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnGizi.setRoundRect(false);
        BtnGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGiziActionPerformed(evt);
            }
        });
        FormMenu.add(BtnGizi);

        BtnHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnHD.setText("Hemodialisa");
        BtnHD.setFocusPainted(false);
        BtnHD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnHD.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnHD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHD.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnHD.setName("BtnHD"); // NOI18N
        BtnHD.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnHD.setRoundRect(false);
        BtnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHDActionPerformed(evt);
            }
        });
        FormMenu.add(BtnHD);

        BtnInputObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnInputObat.setText("Input Obat & BHP");
        BtnInputObat.setFocusPainted(false);
        BtnInputObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnInputObat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnInputObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnInputObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnInputObat.setName("BtnInputObat"); // NOI18N
        BtnInputObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnInputObat.setRoundRect(false);
        BtnInputObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputObatActionPerformed(evt);
            }
        });
        BtnInputObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnInputObatKeyPressed(evt);
            }
        });
        FormMenu.add(BtnInputObat);

        BtnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnCopyResep.setText("Copy Resep");
        BtnCopyResep.setFocusPainted(false);
        BtnCopyResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCopyResep.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCopyResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCopyResep.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCopyResep.setName("BtnCopyResep"); // NOI18N
        BtnCopyResep.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCopyResep.setRoundRect(false);
        BtnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepActionPerformed(evt);
            }
        });
        BtnCopyResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCopyResepKeyPressed(evt);
            }
        });
        FormMenu.add(BtnCopyResep);

        BtnObatBhp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnObatBhp.setText("Data Obat & BHP");
        BtnObatBhp.setFocusPainted(false);
        BtnObatBhp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnObatBhp.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnObatBhp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnObatBhp.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnObatBhp.setName("BtnObatBhp"); // NOI18N
        BtnObatBhp.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnObatBhp.setRoundRect(false);
        BtnObatBhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatBhpActionPerformed(evt);
            }
        });
        BtnObatBhp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnObatBhpKeyPressed(evt);
            }
        });
        FormMenu.add(BtnObatBhp);

        BtnResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnResepObat.setText("Input Resep");
        BtnResepObat.setFocusPainted(false);
        BtnResepObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnResepObat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnResepObat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnResepObat.setName("BtnResepObat"); // NOI18N
        BtnResepObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnResepObat.setRoundRect(false);
        BtnResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepObatActionPerformed(evt);
            }
        });
        BtnResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnResepObatKeyPressed(evt);
            }
        });
        FormMenu.add(BtnResepObat);

        BtnBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnBerkasDigital.setText("Berkas Digital");
        BtnBerkasDigital.setFocusPainted(false);
        BtnBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnBerkasDigital.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnBerkasDigital.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnBerkasDigital.setName("BtnBerkasDigital"); // NOI18N
        BtnBerkasDigital.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnBerkasDigital.setRoundRect(false);
        BtnBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBerkasDigitalActionPerformed(evt);
            }
        });
        BtnBerkasDigital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBerkasDigitalKeyPressed(evt);
            }
        });
        FormMenu.add(BtnBerkasDigital);

        BtnRujukKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnRujukKeluar.setText("Rujuk Keluar");
        BtnRujukKeluar.setFocusPainted(false);
        BtnRujukKeluar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnRujukKeluar.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnRujukKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRujukKeluar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRujukKeluar.setName("BtnRujukKeluar"); // NOI18N
        BtnRujukKeluar.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnRujukKeluar.setRoundRect(false);
        BtnRujukKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRujukKeluarActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRujukKeluar);

        BtnSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnSKDP.setText("Data SKDP");
        BtnSKDP.setFocusPainted(false);
        BtnSKDP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnSKDP.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnSKDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnSKDP.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnSKDP.setName("BtnSKDP"); // NOI18N
        BtnSKDP.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnSKDP.setRoundRect(false);
        BtnSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSKDPActionPerformed(evt);
            }
        });
        FormMenu.add(BtnSKDP);

        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnDiagnosa.setText("Diagnosa");
        BtnDiagnosa.setFocusPainted(false);
        BtnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnDiagnosa.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnDiagnosa.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnDiagnosa.setRoundRect(false);
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormMenu.add(BtnDiagnosa);

        BtnRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnRiwayat.setText("Riwayat Pasien");
        BtnRiwayat.setFocusPainted(false);
        BtnRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnRiwayat.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRiwayat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRiwayat.setName("BtnRiwayat"); // NOI18N
        BtnRiwayat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnRiwayat.setRoundRect(false);
        BtnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwayatActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRiwayat);

        BtnCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnCatatan.setText("Catatan Pasien");
        BtnCatatan.setFocusPainted(false);
        BtnCatatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCatatan.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCatatan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCatatan.setName("BtnCatatan"); // NOI18N
        BtnCatatan.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCatatan.setRoundRect(false);
        BtnCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCatatan);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.WEST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{         
            if(TabRawat.getSelectedIndex()==0){
                Valid.pindah(evt,DTPTgl,Tkel_utama);
            }else if(TabRawat.getSelectedIndex()==1){
                Valid.pindah(evt,DTPTgl,Nmibu);
            }else if(TabRawat.getSelectedIndex()==2){
                Valid.pindah(evt,DTPTgl,Diagnosaawal);
            }
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt,TAlergi,TTensi);
}//GEN-LAST:event_TSuhuKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt,TSuhu,TBerat);
}//GEN-LAST:event_TTensiKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{            
            switch (TabRawat.getSelectedIndex()) {
                case 2:
                    if((!TKeluhan.getText().trim().equals(""))||(!TPemeriksaan.getText().trim().equals(""))||
                            (!TSuhu.getText().trim().equals(""))||(!TTensi.getText().trim().equals(""))||
                            (!TAlergi.getText().trim().equals(""))||(!TTinggi.getText().trim().equals(""))||
                            (!TBerat.getText().trim().equals(""))||(!TRespirasi.getText().trim().equals(""))||
                            (!TNadi.getText().trim().equals(""))||(!TGCS.getText().trim().equals(""))||
                            (!Diagnosaawal.getText().trim().equals(""))||(!Tterapi.getText().trim().equals(""))||
                            (!Konsul.getText().trim().equals(""))||(!labor.getText().trim().equals(""))||
                            (!Tterapipulang.getText().trim().equals(""))||(!Tprosedur.getText().trim().equals(""))||
                            (!Tfollowup.getText().trim().equals(""))){
                        Sequel.menyimpan("pemeriksaan_ranap_modif","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",21,new String[]{
                            TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),                      
                            TSuhu.getText(),TTensi.getText(),TNadi.getText(),TRespirasi.getText(),TTinggi.getText(),
                            TBerat.getText(),TGCS.getText(),TKeluhan.getText(),TPemeriksaan.getText(),TAlergi.getText(),
                            Diagnosaawal.getText(),Tterapi.getText(),Konsul.getText(),labor.getText(),CbLanjut.getSelectedItem().toString(),
                            Tterapipulang.getText(),Tprosedur.getText(),Tfollowup.getText()
                        });
                        tampilPemeriksaan();
                        BtnBatalActionPerformed(evt);
                    }   break;
                  
                case 1:
                    if(Nmibu.getText().trim().equals("")||NmAyah.getText().trim().equals("")){
                        Valid.textKosong(Nmibu,"Orang Tua");
                    }else if(UmurIbu.getText().trim().equals("")||UmurAyah.getText().trim().equals("")){
                        Valid.textKosong(UmurIbu,"umur ibu dan ayah");
                    }else if(Berat.getText().trim().equals("")){
                        Valid.textKosong(Berat,"Berat Badan");
                    }else if(Panjang.getText().trim().equals("")){
                        Valid.textKosong(Panjang,"Panjang Badan");
                    }else if(LingkarKepala.getText().trim().equals("")){
                        Valid.textKosong(LingkarKepala,"Lingkar Kepala");
                    }else if(Proses.getText().trim().equals("")){
                        Valid.textKosong(Proses,"Proses Lahir");
                    }else if(PenyulitKehamilan.getText().trim().equals("")){
                        Valid.textKosong(PenyulitKehamilan,"penyul kehamilan");
                    }else if(Ketuban.getText().trim().equals("")){
                        Valid.textKosong(Ketuban,"Ketuban");
                    }else if(LingkarPerut.getText().trim().equals("")){
                        Valid.textKosong(LingkarPerut,"lingkar perut");
                    }else if(LingkarDada.getText().trim().equals("")){
                        Valid.textKosong(LingkarDada,"Lingkar dada");
                    }else if(KdPenolong.getText().trim().equals("")){
                        Valid.textKosong(KdPenolong,"Penolong/DPJP");
                    }else if(KdPenolong1.getText().trim().equals("")){
                        Valid.textKosong(KdPenolong1,"Penolong Kelahiran");
                    }else if(TSuhu1.getText().trim().equals("")){
                        Valid.textKosong(TSuhu1,"Suhu");
                    }else if(TNadi1.getText().trim().equals("")){
                        Valid.textKosong(TNadi1,"Nadi");
                    }else if(TRespirasi1.getText().trim().equals("")){
                        Valid.textKosong(TRespirasi1,"Respirasi");
                    }else if(spo2.getText().trim().equals("")){
                        Valid.textKosong(spo2,"Sp02");
                    }else{  
                        Sequel.menyimpan("asesmen_neonatal","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",53, new String[] {
                            TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                            Nmibu.getText(),UmurIbu.getText(),NmAyah.getText(),UmurAyah.getText(),AlamatIbu.getText(),JKel.getSelectedItem().toString(),Berat.getText(),Panjang.getText(),LingkarKepala.getText(),Proses.getText(),Anakke.getText(),jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem(),
                            keterangan.getText(),Diagnosa.getText(),PenyulitKehamilan.getText(),Ketuban.getText(),LingkarPerut.getText(),LingkarDada.getText(),KdPenolong.getText(),KdPenolong1.getText(),usia_kehamilan.getText(),GPA.getText(),
                            a1.getSelectedItem().toString(),a2.getSelectedItem().toString(),a3.getSelectedItem().toString(),a4.getSelectedItem().toString(),a5.getSelectedItem().toString(),b1.getSelectedItem().toString(),b2.getSelectedItem().toString(),b3.getSelectedItem().toString(),b4.getSelectedItem().toString(),
                            b5.getSelectedItem().toString(),c1.getSelectedItem().toString(),c2.getSelectedItem().toString(),c3.getSelectedItem().toString(),c4.getSelectedItem().toString(),c5.getSelectedItem().toString(),ajumlah.getText(),bjumlah.getText(),cjumlah.getText(),resusitasi.getText(),obatdiberi.getText(),
                            mikasi.getText(),mekonium.getText(),TSuhu1.getText(),TNadi1.getText(),TRespirasi1.getText(),spo2.getText(),cttnkeperawatan.getText(),Valid.SetTgl(Lahir.getSelectedItem()+"")
                          });
                    }
                        tampilAsesmenNeonatal();
                    break;
                case 0:
                    if((!Triwayat_alergi.getText().trim().equals(""))||(!Tkel_utama.getText().trim().equals(""))||
                            (!Triwayat.getText().trim().equals(""))||(!Triwayat_dulu.getText().trim().equals(""))||
                            (!Triwayat_keluarga.getText().trim().equals(""))||(!Tdx_kerja.getText().trim().equals(""))||
                            (!Tdx_banding.getText().trim().equals(""))||(!Tprognosis.getText().trim().equals(""))){
                        Sequel.menyimpan("asesmen_awal","?,?,?,?,?,?,?,?,?,?,?","Data",11,new String[]{
                            TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                            Triwayat_alergi.getText(),Tkel_utama.getText(),Triwayat.getText(),Triwayat_dulu.getText(),Triwayat_keluarga.getText(),
                            Tdx_kerja.getText(),Tdx_banding.getText(),Tprognosis.getText()
                        });
                        tampilAsesmenAwal();
                        BtnBatalActionPerformed(evt);
                    }   break; 
                default:
                    break;
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            if(TabRawat.getSelectedIndex()==0){
                Valid.pindah(evt,BtnSimpan,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==1){
                Valid.pindah(evt,BtnSimpan,BtnBatal);
            }else if(TabRawat.getSelectedIndex()==2){
                Valid.pindah(evt,BtnSimpan,BtnBatal);
            }
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        ChkInput3.setSelected(true);
        ChkInput4.setSelected(true);
        isForm(); 
        isForm4();
        isForm5();
        TSuhu.setText("");
        TTensi.setText("");
        TKeluhan.setText("");
        TPemeriksaan.setText("");
        TBerat.setText("");
        TTinggi.setText("");
        TNadi.setText("");
        TRespirasi.setText("");
        TGCS.setText("");
        TAlergi.setText("");
        Diagnosaawal.setText("");
        Tterapi.setText("");
        Konsul.setText("");
        labor.setText("");
        Tterapipulang.setText("");
        Tprosedur.setText("");
        Tfollowup.setText("");
        TTnd.setText("0");
        BagianRS.setText("0");
        Bhp.setText("0");
        JmDokter.setText("0");
        JmPerawat.setText("0");
        DTPTgl.setDate(new Date());
        Triwayat_alergi.setText("");
        Tkel_utama.setText("");
        Triwayat.setText("");
        Triwayat_dulu.setText("");
        Triwayat_keluarga.setText("");
        Tdx_kerja.setText("");
        Tdx_banding.setText("");
        Tprognosis.setText("");
        TNoRw.requestFocus();
        
            Nmibu.setText("");
            UmurIbu.setText("");
            NmAyah.setText("");
            UmurAyah.setText("");
            AlamatIbu.setText("");
            JKel.setSelectedIndex(0);
            Berat.setText("");
            Panjang.setText("");
            LingkarKepala.setText("");
            Proses.setText("");
            Anakke.setText("");
            jam.setSelectedIndex(0);
            menit.setSelectedIndex(0);
            detik.setSelectedIndex(0);
            keterangan.setText("");
            Diagnosa.setText("");
            PenyulitKehamilan.setText("");
            Ketuban.setText("");
            LingkarPerut.setText("");
            LingkarDada.setText("");
            KdPenolong.setText("");
            KdPenolong1.setText("");
            usia_kehamilan.setText("");
            GPA.setText("");
            a1.setSelectedIndex(0);
            a2.setSelectedIndex(0);
            a3.setSelectedIndex(0);
            a4.setSelectedIndex(0);
            a5.setSelectedIndex(0);
            b1.setSelectedIndex(0);
            b2.setSelectedIndex(0);
            b3.setSelectedIndex(0);
            b4.setSelectedIndex(0);
            b5.setSelectedIndex(0);
            c1.setSelectedIndex(0);
            c2.setSelectedIndex(0);
            c3.setSelectedIndex(0);
            c4.setSelectedIndex(0);
            c5.setSelectedIndex(0);
            ajumlah.setText("0");
            bjumlah.setText("0");
            cjumlah.setText("0");
            resusitasi.setText("");
            obatdiberi.setText("");
            mikasi.setText("");
            mekonium.setText("");
            TSuhu1.setText("");
            TNadi1.setText("");
            TRespirasi1.setText("");
            spo2.setText("");
            cttnkeperawatan.setText("");
            Lahir.setDate(new Date());
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        switch (TabRawat.getSelectedIndex()) {
           
            case 3:
                if(tabModePemeriksaan.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                }else{
                    for(i=0;i<tbPemeriksaan.getRowCount();i++){
                        if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){
                            Sequel.queryu("delete from pemeriksaan_ranap_modif where no_rawat='"+tbPemeriksaan.getValueAt(i,1).toString()+
                                    "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(i,4).toString()+
                                    "' and jam_rawat='"+tbPemeriksaan.getValueAt(i,5).toString()+"' ");
                        }
                    }
                    tampilPemeriksaan();
                }   break;
           
             case 0:
                if(tabModeAwal.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    TNoRw.requestFocus();
                    
                }else {
                    for(i=0;i<tbawal.getRowCount();i++){
                        if(tbawal.getValueAt(i,0).toString().equals("true")){
                            Sequel.queryu("delete from asesmen_awal where no_rawat='"+tbawal.getValueAt(i,1).toString()+
                                    "' and tgl_perawatan='"+tbawal.getValueAt(i,4).toString()+
                                    "' and jam_rawat='"+tbawal.getValueAt(i,5).toString()+"' ");
                        }
                    }
                    tampilAsesmenAwal();
                }   break;
              case 1:
                if(tabModeNeonatal.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    TNoRw.requestFocus();
                    
                }else {
                    for(i=0;i<tbNeonatal.getRowCount();i++){
                        if(tbNeonatal.getValueAt(i,0).toString().equals("true")){
                            Sequel.queryu("delete from asesmen_neonatal where no_rawat='"+tbNeonatal.getValueAt(i,1).toString()+
                                    "' and tgl_perawatan='"+tbNeonatal.getValueAt(i,4).toString()+
                                    "' and jam_rawat='"+tbNeonatal.getValueAt(i,5).toString()+"' ");
                        }
                    }
                    tampilAsesmenNeonatal();
                }   break;
            default:
                break;
        }

        BtnBatalActionPerformed(evt);
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
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        switch (TabRawat.getSelectedIndex()) {
            case 3:
                if(tabModePemeriksaan.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModePemeriksaan.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    String pas=" and reg_periksa.no_rkm_medis like '%"+TCariPasien.getText()+"%' ";
                    
                    String tgl=" pemeriksaan_ranap_modif.tgl_perawatan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+pas;
                    Valid.MyReportqry("rptInapPemeriksaan.jasper","report","::[ Data Pemeriksaan Rawat Inap ]::",
                            "select pemeriksaan_ranap_modif.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                                    "pemeriksaan_ranap_modif.tgl_perawatan,pemeriksaan_ranap_modif.jam_rawat,pemeriksaan_ranap_modif.suhu_tubuh,pemeriksaan_ranap_modif.tensi, " +
                                    "pemeriksaan_ranap_modif.nadi,pemeriksaan_ranap_modif.respirasi,pemeriksaan_ranap_modif.tinggi, " +
                                    "pemeriksaan_ranap_modif.berat,pemeriksaan_ranap_modif.gcs,pemeriksaan_ranap_modif.keluhan, " +
                                    "pemeriksaan_ranap_modif.pemeriksaan,pemeriksaan_ranap_modif.alergi,pemeriksaan_ranap_modif.dxawal,pemeriksaan_ranap_modif.terapi,pemeriksaan_ranap_modif.hasilkonsul,pemeriksaan_ranap_modif.hasillabor,pemeriksaan_ranap_modif.lanjut,pemeriksaan_ranap_modif.obatpulang " +
                                    "from pasien inner join reg_periksa inner join pemeriksaan_ranap_modif "+
                                    "on pemeriksaan_ranap_modif.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "+
                                    tgl+"and pemeriksaan_ranap_modif.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap_modif.alergi like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap_modif.keluhan like '%"+TCari.getText().trim()+"%' or "+
                                    tgl+"and pemeriksaan_ranap_modif.pemeriksaan like '%"+TCari.getText().trim()+"%' "+
                                    "order by pemeriksaan_ranap_modif.no_rawat desc",param);
                }   break;
            
            case 0:
                if(tabModeAwal.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModeAwal.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    param.put("logo1",Sequel.cariGambar("select pemda from gambar"));
                    Valid.MyReportqry("rptasesmenawal.jasper","report","::[ ASESMENT AWAL MEDIS RAWAT INAP ]::",
                            "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, " +
                            "pasien.tgl_lahir,pasien.umur,bangsal.nm_bangsal, dokter.nm_dokter,asesmen_awal.tgl_perawatan,asesmen_awal.jam_rawat,asesmen_awal.riwayat_alergi,asesmen_awal.keluhan_utama," +
                            "asesmen_awal.riwayat,asesmen_awal.riwayat_dulu,asesmen_awal.riwayat_keluarga,asesmen_awal.diagnosa_banding,asesmen_awal.diagnosa_kerja,asesmen_awal.prognosis " +
                            "from asesmen_awal INNER JOIN kamar_inap ON kamar_inap.no_rawat=asesmen_awal.no_rawat " +
                            "INNER JOIN reg_periksa ON reg_periksa.no_rawat=kamar_inap.no_rawat " +
                            "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                            "INNER JOIN dpjp_ranap ON dpjp_ranap.no_rawat=kamar_inap.no_rawat " +
                            "INNER JOIN dokter ON dokter.kd_dokter=dpjp_ranap.kd_dokter " +
                            "INNER JOIN kamar ON kamar.kd_kamar=kamar_inap.kd_kamar " +
                            "INNER JOIN bangsal ON bangsal.kd_bangsal=kamar.kd_bangsal " +
                            "WHERE asesmen_awal.no_rawat='"+TNoRw.getText()+"'" ,param);
                }   break;
            case 1:
                if(tabModeNeonatal.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                }else if(tabModeNeonatal.getRowCount()!=0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select logo from setting"));
                    param.put("logo1",Sequel.cariGambar("select pemda from gambar"));
                    Valid.MyReportqry("rptasesmenneonatal.jasper","report","::[ ASESMENT NEONATUS]::",
                            "SELECT asesmen_neonatal.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk," +
                            "pasien.tgl_lahir,pasien.umur,pasien.alamat,asesmen_neonatal.tgl_perawatan,asesmen_neonatal.jam_rawat,kamar_inap.tgl_masuk,kamar.kelas,asesmen_neonatal.nm_ibu,asesmen_neonatal.umur_ibu,asesmen_neonatal.nama_ayah,asesmen_neonatal.umur_ayah,asesmen_neonatal.alamat,asesmen_neonatal.jkel,asesmen_neonatal.berat_badan,asesmen_neonatal.panjang_badan,asesmen_neonatal.lingkar_kepala,asesmen_neonatal.proses_lahir,asesmen_neonatal.anakke," +
                            "asesmen_neonatal.jam_lahir,asesmen_neonatal.lain_lain,asesmen_neonatal.diagnosa,asesmen_neonatal.penyulit_kehamilan,asesmen_neonatal.ketuban,asesmen_neonatal.lingkar_perut,asesmen_neonatal.lingkar_dada,dokter.nm_dokter,pegawai.nama,asesmen_neonatal.usia_kehamilan,asesmen_neonatal.gpa,asesmen_neonatal.a1,asesmen_neonatal.a2,asesmen_neonatal.a3,asesmen_neonatal.a4,asesmen_neonatal.a5," +
                            "asesmen_neonatal.b1,asesmen_neonatal.b2,asesmen_neonatal.b3,asesmen_neonatal.b4,asesmen_neonatal.b5,asesmen_neonatal.c1,asesmen_neonatal.c2,asesmen_neonatal.c3,asesmen_neonatal.c4,asesmen_neonatal.c5,asesmen_neonatal.ajumlah,asesmen_neonatal.bjumlah,asesmen_neonatal.cjumlah," +
                            "asesmen_neonatal.resosistasi,asesmen_neonatal.obat_diberi,asesmen_neonatal.mikasi,asesmen_neonatal.mekunium,asesmen_neonatal.suhu,asesmen_neonatal.nadi,asesmen_neonatal.respirasi,asesmen_neonatal.spo2,asesmen_neonatal.catatan_keperawatan FROM asesmen_neonatal " +
                            "INNER JOIN reg_periksa on reg_periksa.no_rawat=asesmen_neonatal.no_rawat " +
                            "INNER JOIN kamar_inap ON kamar_inap.no_rawat=asesmen_neonatal.no_rawat " +
                            "INNER JOIN kamar ON kamar_inap.kd_kamar=kamar.kd_kamar " +
                            "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                            "INNER JOIN dokter ON dokter.kd_dokter=asesmen_neonatal.penolong_dpjp " +
                            "INNER JOIN pegawai ON pegawai.nik=asesmen_neonatal.penolong_kelahiran WHERE asesmen_neonatal.no_rawat='"+TNoRw.getText()+"'" ,param);
                }   break;
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
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

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
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampilAsesmenAwal();
                break;
            case 1:
                tampilAsesmenNeonatal();
                break;
            case 2:
                tampilPemeriksaan();
                ;
                break;
            default:
                break;
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
   
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampilAsesmenAwal();
                break;
            case 1:
                 tampilAsesmenNeonatal();
                break;
            case 2:
                tampilPemeriksaan();
                ;
                break;
            default:
                break;
        }
}//GEN-LAST:event_TabRawatMouseClicked

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
       Valid.pindah(evt,Nmibu,cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
}//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
}//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt,cmbMnt,TKeluhan);
}//GEN-LAST:event_cmbDtkKeyPressed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgRawatInap");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(rootPaneCheckingEnabled);
}//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt,TCariPasien,DTPCari1);
}//GEN-LAST:event_btnPasienKeyPressed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            switch (TabRawat.getSelectedIndex()) {
              
                case 4:
                    if(TKeluhan.getText().trim().equals("")&&TPemeriksaan.getText().trim().equals("")&&TSuhu.getText().trim().equals("")&&TTensi.getText().trim().equals("")){
                        Valid.textKosong(TKeluhan,"Hasil Periksa/Perkambangan/Suhu Badan/Tensi");                            
                    }else{
                        if(tbPemeriksaan.getSelectedRow()>-1){
                            Sequel.mengedit("pemeriksaan_ranap_modif","no_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1)+
                                "' and tgl_perawatan='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4)+
                                "' and jam_rawat='"+tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5)+"'",
                                "no_rawat='"+TNoRw.getText()+"',suhu_tubuh='"+TSuhu.getText()+"',tensi='"+TTensi.getText()+"',"+
                                "keluhan='"+TKeluhan.getText()+"',pemeriksaan='"+TPemeriksaan.getText()+"',"+
                                "nadi='"+TNadi.getText()+"',respirasi='"+TRespirasi.getText()+"',"+
                                "tinggi='"+TTinggi.getText()+"',berat='"+TBerat.getText()+"',"+
                                "gcs='"+TGCS.getText()+"',alergi='"+TAlergi.getText()+"',"+
                                "dxawal='"+Diagnosaawal.getText()+"',terapi='"+Tterapi.getText()+"',"+
                                "hasilkonsul='"+Konsul.getText()+"',hasillabor='"+labor.getText()+"',"+
                                "lanjut='"+CbLanjut.getSelectedItem()+"',obatpulang='"+Tterapipulang.getText()+"',"+
                                "prosedur='"+Tprosedur.getText()+"',followup='"+Tfollowup.getText()+"',"+
                                "tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',"+
                                "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"'");
                            tampilPemeriksaan();
                            BtnBatalActionPerformed(evt);
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
               
                 case 0:
                   if((!Triwayat_alergi.getText().trim().equals(""))||(!Tkel_utama.getText().trim().equals(""))||
                            (!Triwayat.getText().trim().equals(""))||(!Triwayat_dulu.getText().trim().equals(""))||
                            (!Triwayat_keluarga.getText().trim().equals(""))||(!Tdx_kerja.getText().trim().equals(""))||
                            (!Tdx_banding.getText().trim().equals(""))||(!Tprognosis.getText().trim().equals(""))){
                        if(tbawal.getSelectedRow()>-1){
                            Sequel.mengedit("asesmen_awal","no_rawat='"+tbawal.getValueAt(tbawal.getSelectedRow(),1)+
                                    "' and tgl_perawatan='"+tbawal.getValueAt(tbawal.getSelectedRow(),4)+
                                    "' and jam_rawat='"+tbawal.getValueAt(tbawal.getSelectedRow(),5)+"'",
                                    "no_rawat='"+TNoRw.getText()+"', tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"', "+
                                    "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"', "+
                                    "riwayat_alergi='"+Triwayat_alergi.getText()+"', keluhan_utama='"+Tkel_utama.getText()+"', riwayat='"+Triwayat.getText()+"', "+
                                    "riwayat_dulu='"+Triwayat_dulu.getText()+"', riwayat_keluarga='"+Triwayat_keluarga.getText()+"', "+
                                    "diagnosa_kerja='"+Tdx_kerja.getText()+"', diagnosa_banding='"+Tdx_kerja.getText()+"', prognosis='"+Tprognosis.getText()+"'");
                            tampilAsesmenAwal();
                            BtnBatalActionPerformed(evt);
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
                 case 1:
                   if(Nmibu.getText().trim().equals("")||NmAyah.getText().trim().equals("")){
                        Valid.textKosong(Nmibu,"Orang Tua");
                    }else if(UmurIbu.getText().trim().equals("")||UmurAyah.getText().trim().equals("")){
                        Valid.textKosong(Nmibu,"umur ibu dan ayah");
                    }else if(Berat.getText().trim().equals("")){
                        Valid.textKosong(Berat,"Berat Badan");
                    }else if(Panjang.getText().trim().equals("")){
                        Valid.textKosong(Panjang,"Panjang Badan");
                    }else if(LingkarKepala.getText().trim().equals("")){
                        Valid.textKosong(LingkarKepala,"Lingkar Kepala");
                    }else if(Proses.getText().trim().equals("")){
                        Valid.textKosong(Proses,"Proses Lahir");
                    }else if(PenyulitKehamilan.getText().trim().equals("")){
                        Valid.textKosong(PenyulitKehamilan,"penyul kehamilan");
                    }else if(Ketuban.getText().trim().equals("")){
                        Valid.textKosong(Ketuban,"Ketuban");
                    }else if(LingkarPerut.getText().trim().equals("")){
                        Valid.textKosong(LingkarPerut,"lingkar perut");
                    }else if(LingkarDada.getText().trim().equals("")){
                        Valid.textKosong(LingkarDada,"Lingkar dada");
                    }else if(KdPenolong.getText().trim().equals("")){
                        Valid.textKosong(KdPenolong,"Penolong/DPJP");
                    }else if(KdPenolong1.getText().trim().equals("")){
                        Valid.textKosong(KdPenolong1,"Penolong Kelahiran");
                    }else if(TSuhu1.getText().trim().equals("")){
                        Valid.textKosong(TSuhu1,"Suhu");
                    }else if(TNadi1.getText().trim().equals("")){
                        Valid.textKosong(TNadi1,"Nadi");
                    }else if(TRespirasi1.getText().trim().equals("")){
                        Valid.textKosong(TRespirasi1,"Respirasi");
                    }else if(spo2.getText().trim().equals("")){
                        Valid.textKosong(spo2,"Sp02");
                    }else{  
                        if(tbNeonatal.getSelectedRow()>-1){
                            Sequel.mengedit("asesmen_neonatal","no_rawat='"+tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),1)+
                                    "' and tgl_perawatan='"+tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),4)+
                                    "' and jam_rawat='"+tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),5)+"'",
                                    "no_rawat='"+TNoRw.getText()+"', tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"', "+
                                    "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"', "+
                                    "nm_ibu='"+Nmibu.getText()+"', umur_ibu='"+UmurIbu.getText()+"', nama_ayah='"+NmAyah.getText()+"', "+
                                    "umur_ayah='"+UmurAyah.getText()+"', alamat='"+AlamatIbu.getText()+"', "+
                                    "jkel='"+JKel.getSelectedItem().toString()+"', berat_badan='"+Berat.getText()+"', panjang_badan='"+Panjang.getText()+"', "+
                                    "lingkar_kepala='"+LingkarKepala.getText()+"', proses_lahir='"+Proses.getText()+"', anakke='"+Anakke.getText()+"', "+
                                    "jam_lahir='"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"', lain_lain='"+keterangan.getText()+"', diagnosa='"+Diagnosa.getText()+"',"+
                                    "penyulit_kehamilan='"+PenyulitKehamilan.getText()+"', ketuban='"+Ketuban.getText()+"', lingkar_perut='"+LingkarPerut.getText()+"', "+
                                    "lingkar_dada='"+LingkarDada.getText()+"', penolong_dpjp='"+KdPenolong.getText()+"', penolong_kelahiran='"+KdPenolong1.getText()+"', "+
                                    "gpa='"+GPA.getText()+"', a1='"+a1.getSelectedItem().toString()+"', a2='"+a2.getSelectedItem().toString()+"', "+
                                    "a3='"+a3.getSelectedItem().toString()+"', a4='"+a4.getSelectedItem().toString()+"', a5='"+a5.getSelectedItem().toString()+"', "+
                                    "b1='"+b1.getSelectedItem().toString()+"', b2='"+b2.getSelectedItem().toString()+"', b3='"+b3.getSelectedItem().toString()+"', "+
                                    "b4='"+b4.getSelectedItem().toString()+"', b5='"+b5.getSelectedItem().toString()+"', c1='"+c1.getSelectedItem().toString()+"', "+
                                    "c2='"+c2.getSelectedItem().toString()+"', c3='"+c3.getSelectedItem().toString()+"', c4='"+c4.getSelectedItem().toString()+"', "+
                                    "c5='"+c5.getSelectedItem().toString()+"', ajumlah='"+ajumlah.getText()+"', bjumlah='"+bjumlah.getText()+"', "+
                                    "cjumlah='"+cjumlah.getText()+"', resosistasi='"+resusitasi.getText()+"', obat_diberi='"+obatdiberi.getText()+"', "+
                                    "mikasi='"+mikasi.getText()+"', mekunium='"+mekonium.getText()+"', suhu='"+TSuhu1.getText()+"', "+
                                    "nadi='"+TNadi1.getText()+"',respirasi='"+TRespirasi1.getText()+"',spo2='"+spo2.getText()+"',catatan_keperawatan='"+cttnkeperawatan.getText()+ "',tgl_lahir='"+Valid.SetTgl(Lahir.getSelectedItem()+"")+"'");
                            tampilAsesmenNeonatal();
                            BtnBatalActionPerformed(evt);
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }   break;
                default:
                    break;                
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if(tabModePemeriksaan.getRowCount()!=0){
            try {
                getDataPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }           
            
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        Valid.pindah(evt,TPemeriksaan,TSuhu);
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        Valid.pindah(evt,TRespirasi,BtnSimpan);
    }//GEN-LAST:event_TGCSKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        Valid.pindah(evt,TNadi,TGCS);
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt,TTinggi,TRespirasi);
    }//GEN-LAST:event_TNadiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        Valid.pindah(evt,TTensi,TTinggi);
    }//GEN-LAST:event_TBeratKeyPressed

    private void TNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNadiActionPerformed
        
    }//GEN-LAST:event_TNadiActionPerformed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        Valid.pindah(evt,TBerat,TNadi);
    }//GEN-LAST:event_TTinggiKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ChkInputItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkInputItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInputItemStateChanged

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        Valid.pindah(evt,TNoRw,TPemeriksaan);
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        Valid.pindah(evt,TKeluhan,TAlergi);
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void tbPemeriksaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyReleased
        if(tabModePemeriksaan.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }
                       
        }
    }//GEN-LAST:event_tbPemeriksaanKeyReleased

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepObatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                inputResep();
            }
        }
    }//GEN-LAST:event_BtnResepObatActionPerformed

    private void BtnResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnResepObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResepObatKeyPressed

    private void BtnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCopyResep daftar=new DlgCopyResep(null,false);
            daftar.isCek();
            daftar.setRM(TNoRw.getText(),TNoRM.getText(),Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?",TNoRw.getText()),Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",TNoRw.getText()),"ranap");
            daftar.tampil();
            daftar.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            daftar.setLocationRelativeTo(internalFrame1);
            daftar.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCopyResepActionPerformed

    private void BtnCopyResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCopyResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCopyResepKeyPressed

    private void BtnInputObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputObatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                inputObat();
            }
        }
    }//GEN-LAST:event_BtnInputObatActionPerformed

    private void BtnInputObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnInputObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInputObatKeyPressed

    private void BtnObatBhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatBhpActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPemberianObat dlgrwinap=new DlgPemberianObat(null,false);
            dlgrwinap.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgrwinap.setLocationRelativeTo(internalFrame1);
            dlgrwinap.isCek();
            dlgrwinap.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"ranap");
            dlgrwinap.tampilPO();
            dlgrwinap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnObatBhpActionPerformed

    private void BtnObatBhpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnObatBhpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnObatBhpKeyPressed

    private void BtnBerkasDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBerkasDigitalActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgBerkasRawat berkas=new DlgBerkasRawat(null,true);
            berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
            try {
                berkas.loadURL("http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/login2.php?act=login&usere=admin&passwordte=akusayangsamakamu&no_rawat="+TNoRw.getText());
            } catch (Exception ex) {
                System.out.println("Notifikasi : "+ex);
            }

            berkas.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            berkas.setLocationRelativeTo(internalFrame1);
            berkas.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnBerkasDigitalActionPerformed

    private void BtnBerkasDigitalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBerkasDigitalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBerkasDigitalKeyPressed

    private void BtnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPermintaanLabActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPermintaanLaboratorium dlgro=new DlgPermintaanLaboratorium(null,false);
            dlgro.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.emptTeks();
            dlgro.isCek();
            dlgro.setNoRm(TNoRw.getText(),"Ranap");
            dlgro.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPermintaanLabActionPerformed

    private void BtnPermintaanLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPermintaanLabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPermintaanLabKeyPressed

    private void BtnPermintaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPermintaanRadActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPermintaanRadiologi dlgro=new DlgPermintaanRadiologi(null,false);
            dlgro.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.emptTeks();
            dlgro.isCek();
            dlgro.setNoRm(TNoRw.getText(),"Ranap");
            dlgro.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPermintaanRadActionPerformed

    private void BtnPermintaanRadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPermintaanRadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPermintaanRadKeyPressed

    private void BtnSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSKDPActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            DlgSKDPBPJS form=new DlgSKDPBPJS(null,false);
            form.isCek();
            form.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            kode_poli=Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?",TNoRw.getText());
            form.setNoRm(TNoRM.getText(),TPasien.getText(), kode_poli,Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?",kode_poli),Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?",TNoRw.getText()),Sequel.cariIsi("select nm_dokter from dokter inner join reg_periksa on reg_periksa.kd_dokter=dokter.kd_dokter where no_rawat=?",TNoRw.getText()));
            form.setVisible(true);
        }
    }//GEN-LAST:event_BtnSKDPActionPerformed

    private void BtnRujukKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRujukKeluarActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRujuk dlgrjk=new DlgRujuk(null,false);
                dlgrjk.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.emptTeks();
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate());
                dlgrjk.tampil();
                dlgrjk.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }            
        }
    }//GEN-LAST:event_BtnRujukKeluarActionPerformed

    private void BtnCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
            }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgCatatan catatan=new DlgCatatan(null,true);
                catatan.setNoRm(TNoRM.getText());
                catatan.setSize(720,330);
                catatan.setLocationRelativeTo(internalFrame1);
                catatan.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }            
        }
    }//GEN-LAST:event_BtnCatatanActionPerformed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{            
            DlgDiagnosaPenyakit resep=new DlgDiagnosaPenyakit(null,false);
            resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"Ranap");
            resep.panelDiagnosa1.tampil();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwayatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMRiwayatPerawatan resume=new RMRiwayatPerawatan(null,true);
            resume.setNoRm(TNoRM.getText(),TPasien.getText());
            resume.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnRiwayatActionPerformed

    private void DiagnosaawalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiagnosaawalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaawalActionPerformed

    private void TterapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TterapiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TterapiKeyPressed

    private void MnRingksanKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRingksanKeluarActionPerformed
      Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReportqry("rptringkasan.jasper","report","::[ Ringksanan Keluar ]::",
            " SELECT pemeriksaan_ranap_modif.suhu_tubuh,pemeriksaan_ranap_modif.tensi,pemeriksaan_ranap_modif.nadi,pemeriksaan_ranap_modif.respirasi, " +
            " pemeriksaan_ranap_modif.tinggi,pemeriksaan_ranap_modif.berat,pemeriksaan_ranap_modif.gcs,pemeriksaan_ranap_modif.keluhan, " +
            " pemeriksaan_ranap_modif.pemeriksaan,pemeriksaan_ranap_modif.alergi,pemeriksaan_ranap_modif.dxawal, " +
            " pemeriksaan_ranap_modif.terapi,pemeriksaan_ranap_modif.hasilkonsul,pemeriksaan_ranap_modif.hasillabor,pemeriksaan_ranap_modif.lanjut, " +
            " pemeriksaan_ranap_modif.obatpulang,pemeriksaan_ranap_modif.prosedur,pemeriksaan_ranap_modif.followup,pasien.nm_pasien,pasien.tgl_lahir, " +
            " pasien.jk,pasien.agama,reg_periksa.no_rkm_medis,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,ifnull(kamar_inap.tgl_keluar,0) as tg_keluar, " +
            " kamar_inap.stts_pulang,penjab.png_jawab,dokter.nm_dokter, " +
            " CONCAT(kamar.kd_kamar,' ',kamar.kelas) as kamar,bangsal.nm_bangsal,CONCAT(penyakit.kd_penyakit,' ',penyakit.nm_penyakit) AS diagnosa, " +
            " IFNULL(booking_registrasi.tanggal_periksa,0) as tgl_kontrol, " +
            " IFNULL(poliklinik.nm_poli,'-') AS poli_kontrol,IFNULL(diet.nama_diet,'-') as dietp " +
            " FROM kamar_inap INNER JOIN pasien INNER JOIN reg_periksa INNER JOIN penjab INNER JOIN pemeriksaan_ranap_modif " +
            " INNER JOIN dokter INNER JOIN dpjp_ranap INNER JOIN bangsal INNER JOIN kamar INNER JOIN diagnosa_pasien INNER JOIN penyakit " +
            " on pemeriksaan_ranap_modif.no_rawat=kamar_inap.no_rawat " +
            " AND reg_periksa.no_rawat=kamar_inap.no_rawat " +
            " AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
            " AND reg_periksa.kd_pj=penjab.kd_pj " +
            " AND reg_periksa.no_rawat=dpjp_ranap.no_rawat " +
            " AND dokter.kd_dokter=dpjp_ranap.kd_dokter " +
            " AND kamar_inap.kd_kamar=kamar.kd_kamar " +
            " AND bangsal.kd_bangsal=kamar.kd_bangsal " +
            " AND kamar_inap.no_rawat=diagnosa_pasien.no_rawat " +
            " AND penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit " +       
            " LEFT JOIN booking_registrasi ON booking_registrasi.no_rkm_medis=pasien.no_rkm_medis " +
            " LEFT JOIN poliklinik ON poliklinik.kd_poli=booking_registrasi.kd_poli " +
            " LEFT JOIN detail_beri_diet on kamar_inap.no_rawat=detail_beri_diet.no_rawat " +
            " LEFT JOIN diet ON diet.kd_diet=detail_beri_diet.kd_diet " +
            " WHERE kamar_inap.no_rawat ='"+TNoRw.getText()+"' " +
            " GROUP BY penyakit.nm_penyakit ",param);
         this.setCursor(Cursor.getDefaultCursor());   
    }//GEN-LAST:event_MnRingksanKeluarActionPerformed

    private void TterapipulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TterapipulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TterapipulangKeyPressed

    private void TprosedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprosedurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TprosedurKeyPressed

    private void TfollowupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfollowupKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfollowupKeyPressed

    private void BtnUTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUTDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnUTDActionPerformed

    private void BtnIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnIRMActionPerformed

    private void BtnGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGiziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGiziActionPerformed

    private void BtnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHDActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMHemodialisa dlghd=new RMHemodialisa(null,false);
            dlghd.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            dlghd.setLocationRelativeTo(internalFrame1);
            dlghd.emptTeks();
            dlghd.isCek();
            dlghd.setNoRm(TNoRw.getText());
            dlghd.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnHDActionPerformed

    private void tbawalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbawalMouseClicked
       if(tabModeAwal.getRowCount()!=0){
            try {
                getDataAsesmenAwal();
            } catch (java.lang.NullPointerException e) {
            }
            
        }
    }//GEN-LAST:event_tbawalMouseClicked

    private void tbawalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbawalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbawalKeyReleased

    private void ChkInput3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkInput3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkInput3ItemStateChanged

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
       isForm4();
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void TriwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwayatKeyPressed
         Valid.pindah(evt,Tkel_utama,Triwayat_dulu);
    }//GEN-LAST:event_TriwayatKeyPressed

    private void Tkel_utamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkel_utamaKeyPressed
      Valid.pindah(evt,Triwayat_alergi,Triwayat);
    }//GEN-LAST:event_Tkel_utamaKeyPressed

    private void Triwayat_alergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triwayat_alergiKeyPressed
       Valid.pindah(evt,DTPCari1,Tkel_utama);
    }//GEN-LAST:event_Triwayat_alergiKeyPressed

    private void Triwayat_duluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triwayat_duluKeyPressed
     Valid.pindah(evt,Triwayat,Triwayat_keluarga);
    }//GEN-LAST:event_Triwayat_duluKeyPressed

    private void Tdx_kerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdx_kerjaKeyPressed
        Valid.pindah(evt,Triwayat_keluarga,Tdx_banding);
    }//GEN-LAST:event_Tdx_kerjaKeyPressed

    private void Triwayat_keluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triwayat_keluargaKeyPressed
         Valid.pindah(evt,Triwayat_dulu,Tdx_kerja);
    }//GEN-LAST:event_Triwayat_keluargaKeyPressed

    private void TprognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprognosisKeyPressed
       Valid.pindah(evt,Tdx_banding,BtnSimpan);
    }//GEN-LAST:event_TprognosisKeyPressed

    private void Tdx_bandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdx_bandingKeyPressed
        Valid.pindah(evt,Tdx_kerja,Tprognosis);
    }//GEN-LAST:event_Tdx_bandingKeyPressed

    private void BtnCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCPPTActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Tnorw2.setText(TNoRM.getText());
            Tnorw3.setText(TPasien.getText());
            Sequel.cariIsi("SELECT IFNULL(pegawai.nama,'-') FROM pegawai INNER JOIN dpjp_ranap ON dpjp_ranap.kd_dokter=pegawai.nik INNER JOIN kamar_inap ON kamar_inap.no_rawat=dpjp_ranap.no_rawat WHERE kamar_inap.no_rawat=?",dpjp,TNoRw.getText());
            Sequel.cariIsi("SELECT tgl_masuk FROM kamar_inap WHERE kamar_inap.no_rawat=?",Tglmasuk,TNoRw.getText());
            WindowCPPT.setSize(980,674);
            WindowCPPT.setLocationRelativeTo(internalFrame1);
            WindowCPPT.setVisible(true);
            emptyteks();
            tampilcppt();
            if(akses.getkode().equals("Admin Utama")){
            BtnHapus1.setEnabled(true);
           }else{
            BtnHapus1.setEnabled(false);
            }   
            this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_BtnCPPTActionPerformed

    private void tbNeonatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNeonatalMouseClicked
  if(tabModeNeonatal.getRowCount()!=0) {
            try {
                getDataNeonatal();

            } catch (java.lang.NullPointerException e) {

            }
        }
    }//GEN-LAST:event_tbNeonatalMouseClicked

    private void tbNeonatalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNeonatalKeyReleased
       if(tabModeNeonatal.getRowCount()!=0) {
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)) {
                try {
                    getDataNeonatal();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNeonatalKeyReleased

    private void Scroll7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Scroll7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll7KeyPressed

    private void ChkInput4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput4ActionPerformed
        isForm5();
    }//GEN-LAST:event_ChkInput4ActionPerformed

    private void ProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsesKeyPressed
        Valid.pindah(evt,PenyulitKehamilan,BtnSimpan);
    }//GEN-LAST:event_ProsesKeyPressed

    private void AnakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnakkeKeyPressed
        Valid.pindah(evt,LingkarDada,BtnPenjab1);
    }//GEN-LAST:event_AnakkeKeyPressed

    private void LingkarKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarKepalaKeyPressed
        Valid.pindah(evt,Panjang,UmurBayi);
    }//GEN-LAST:event_LingkarKepalaKeyPressed

    private void JKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKelKeyPressed
        Valid.pindah(evt,AlamatIbu,Berat);
    }//GEN-LAST:event_JKelKeyPressed

    private void LahirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LahirItemStateChanged
        lahir = Lahir.getDate();
        birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        p = Period.between(birthday,today);
        p2 =ChronoUnit.DAYS.between(birthday,today);
        UmurBayi.setText(String.valueOf(p.getYears())+" Th "+String.valueOf(p.getMonths())+" Bl "+String.valueOf(p.getDays())+" Hr");
    }//GEN-LAST:event_LahirItemStateChanged

    private void LahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LahirKeyPressed
        Valid.pindah(evt,Berat,jam);
    }//GEN-LAST:event_LahirKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah(evt,keterangan,Ketuban);
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void NmibuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmibuKeyPressed
        
    }//GEN-LAST:event_NmibuKeyPressed

    private void NmAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmAyahKeyPressed
        Valid.pindah(evt, Nmibu,UmurAyah);
    }//GEN-LAST:event_NmAyahKeyPressed

    private void UmurIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurIbuKeyPressed
        Valid.pindah(evt, Nmibu,NmAyah);
    }//GEN-LAST:event_UmurIbuKeyPressed

    private void AlamatIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatIbuKeyPressed
        Valid.pindah(evt, UmurAyah,JKel);
    }//GEN-LAST:event_AlamatIbuKeyPressed

    private void jamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyPressed
        Valid.pindah(evt,Lahir, menit);
    }//GEN-LAST:event_jamKeyPressed

    private void menitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menitKeyPressed
        Valid.pindah(evt,jam,detik);
    }//GEN-LAST:event_menitKeyPressed

    private void detikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detikKeyPressed
        Valid.pindah(evt,menit,KdPenolong);
    }//GEN-LAST:event_detikKeyPressed

    private void BeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BeratKeyPressed
        Valid.pindah(evt,JKel,Lahir);
    }//GEN-LAST:event_BeratKeyPressed

    private void PanjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanjangKeyPressed
        Valid.pindah(evt,LingkarPerut,LingkarKepala);
    }//GEN-LAST:event_PanjangKeyPressed

    private void keteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keteranganKeyPressed
        Valid.pindah(evt,Anakke,Diagnosa);
    }//GEN-LAST:event_keteranganKeyPressed

    private void UmurAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurAyahKeyPressed
        Valid.pindah(evt, NmAyah,AlamatIbu);
    }//GEN-LAST:event_UmurAyahKeyPressed

    private void UmurBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurBayiKeyPressed
        Valid.pindah(evt,LingkarKepala,LingkarDada);
    }//GEN-LAST:event_UmurBayiKeyPressed

    private void PenyulitKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyulitKehamilanKeyPressed
        Valid.pindah(evt,Ketuban,Proses);
    }//GEN-LAST:event_PenyulitKehamilanKeyPressed

    private void KetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanKeyPressed
        Valid.pindah(evt,Diagnosa,PenyulitKehamilan);
    }//GEN-LAST:event_KetubanKeyPressed

    private void LingkarDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarDadaKeyPressed
        Valid.pindah(evt,UmurBayi,Anakke);
    }//GEN-LAST:event_LingkarDadaKeyPressed

    private void LingkarPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarPerutKeyPressed
        Valid.pindah(evt,BtnPenjab,Panjang);
    }//GEN-LAST:event_LingkarPerutKeyPressed

    private void KdPenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPenolongKeyPressed
        Valid.pindah(evt,detik,LingkarPerut);
    }//GEN-LAST:event_KdPenolongKeyPressed

    private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPenjabActionPerformed

    private void usia_kehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usia_kehamilanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_usia_kehamilanKeyPressed

    private void GPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GPAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GPAKeyPressed

    private void BtnPenjab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjab1ActionPerformed
        pegawai1.emptTeks();
        pegawai1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai1.setLocationRelativeTo(internalFrame1);
        pegawai1.setAlwaysOnTop(false);
        pegawai1.setVisible(true);
                                
    }//GEN-LAST:event_BtnPenjab1ActionPerformed

    private void KdPenolong1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPenolong1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPenolong1KeyPressed

    private void c1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c1KeyPressed
        Valid.pindah(evt,AlamatIbu,Berat);
    }//GEN-LAST:event_c1KeyPressed

    private void a1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_a1KeyPressed

    private void b1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b1KeyPressed

    private void c2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_c2KeyPressed

    private void a2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_a2KeyPressed

    private void b2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b2KeyPressed

    private void c3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_c3KeyPressed

    private void a3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_a3KeyPressed

    private void b3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b3KeyPressed

    private void c4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_c4KeyPressed

    private void a4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_a4KeyPressed

    private void b4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b4KeyPressed

    private void c5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_c5KeyPressed

    private void a5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_a5KeyPressed

    private void b5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b5KeyPressed

    private void resusitasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resusitasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_resusitasiKeyPressed

    private void mikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mikasiKeyPressed

    private void obatdiberiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obatdiberiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_obatdiberiKeyPressed

    private void mekoniumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mekoniumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mekoniumKeyPressed

    private void cjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cjumlahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cjumlahKeyPressed

    private void ajumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ajumlahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajumlahKeyPressed

    private void bjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bjumlahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bjumlahKeyPressed

    private void TSuhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhu1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSuhu1KeyPressed

    private void TNadi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNadi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadi1ActionPerformed

    private void TNadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNadi1KeyPressed

    private void TRespirasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRespirasi1KeyPressed

    private void spo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spo2ActionPerformed

    private void spo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_spo2KeyPressed

    private void cttnkeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cttnkeperawatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cttnkeperawatanKeyPressed

    private void a1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_a1ItemStateChanged
    ajumlah.setText(""+(Integer.parseInt(a1.getSelectedItem().toString())+Integer.parseInt(a2.getSelectedItem().toString())+Integer.parseInt(a3.getSelectedItem().toString())+Integer.parseInt(a4.getSelectedItem().toString()
    )+Integer.parseInt(a5.getSelectedItem().toString())));
    }//GEN-LAST:event_a1ItemStateChanged

    private void a2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_a2ItemStateChanged
      ajumlah.setText(""+(Integer.parseInt(a1.getSelectedItem().toString())+Integer.parseInt(a2.getSelectedItem().toString())+Integer.parseInt(a3.getSelectedItem().toString())+Integer.parseInt(a4.getSelectedItem().toString()
    )+Integer.parseInt(a5.getSelectedItem().toString())));
    }//GEN-LAST:event_a2ItemStateChanged

    private void a3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_a3ItemStateChanged
       ajumlah.setText(""+(Integer.parseInt(a1.getSelectedItem().toString())+Integer.parseInt(a2.getSelectedItem().toString())+Integer.parseInt(a3.getSelectedItem().toString())+Integer.parseInt(a4.getSelectedItem().toString()
    )+Integer.parseInt(a5.getSelectedItem().toString())));
    }//GEN-LAST:event_a3ItemStateChanged

    private void a4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_a4ItemStateChanged
      ajumlah.setText(""+(Integer.parseInt(a1.getSelectedItem().toString())+Integer.parseInt(a2.getSelectedItem().toString())+Integer.parseInt(a3.getSelectedItem().toString())+Integer.parseInt(a4.getSelectedItem().toString()
    )+Integer.parseInt(a5.getSelectedItem().toString())));
    }//GEN-LAST:event_a4ItemStateChanged

    private void a5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_a5ItemStateChanged
      ajumlah.setText(""+(Integer.parseInt(a1.getSelectedItem().toString())+Integer.parseInt(a2.getSelectedItem().toString())+Integer.parseInt(a3.getSelectedItem().toString())+Integer.parseInt(a4.getSelectedItem().toString()
    )+Integer.parseInt(a5.getSelectedItem().toString())));
    }//GEN-LAST:event_a5ItemStateChanged

    private void b1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_b1ItemStateChanged
     bjumlah.setText(""+(Integer.parseInt(b1.getSelectedItem().toString())+Integer.parseInt(b2.getSelectedItem().toString())+Integer.parseInt(b3.getSelectedItem().toString())+Integer.parseInt(b4.getSelectedItem().toString()
    )+Integer.parseInt(b5.getSelectedItem().toString())));
    }//GEN-LAST:event_b1ItemStateChanged

    private void b2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_b2ItemStateChanged
        bjumlah.setText(""+(Integer.parseInt(b1.getSelectedItem().toString())+Integer.parseInt(b2.getSelectedItem().toString())+Integer.parseInt(b3.getSelectedItem().toString())+Integer.parseInt(b4.getSelectedItem().toString()
    )+Integer.parseInt(b5.getSelectedItem().toString())));
    }//GEN-LAST:event_b2ItemStateChanged

    private void b3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_b3ItemStateChanged
 bjumlah.setText(""+(Integer.parseInt(b1.getSelectedItem().toString())+Integer.parseInt(b2.getSelectedItem().toString())+Integer.parseInt(b3.getSelectedItem().toString())+Integer.parseInt(b4.getSelectedItem().toString()
    )+Integer.parseInt(b5.getSelectedItem().toString())));
    }//GEN-LAST:event_b3ItemStateChanged

    private void b4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_b4ItemStateChanged
       bjumlah.setText(""+(Integer.parseInt(b1.getSelectedItem().toString())+Integer.parseInt(b2.getSelectedItem().toString())+Integer.parseInt(b3.getSelectedItem().toString())+Integer.parseInt(b4.getSelectedItem().toString()
    )+Integer.parseInt(b5.getSelectedItem().toString())));
    }//GEN-LAST:event_b4ItemStateChanged

    private void b5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_b5ItemStateChanged
   bjumlah.setText(""+(Integer.parseInt(b1.getSelectedItem().toString())+Integer.parseInt(b2.getSelectedItem().toString())+Integer.parseInt(b3.getSelectedItem().toString())+Integer.parseInt(b4.getSelectedItem().toString()
    )+Integer.parseInt(b5.getSelectedItem().toString())));
    }//GEN-LAST:event_b5ItemStateChanged

    private void c1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c1ItemStateChanged
        cjumlah.setText(""+(Integer.parseInt(c1.getSelectedItem().toString())+Integer.parseInt(c2.getSelectedItem().toString())+Integer.parseInt(c3.getSelectedItem().toString())+Integer.parseInt(c4.getSelectedItem().toString()
    )+Integer.parseInt(c5.getSelectedItem().toString())));
        
    }//GEN-LAST:event_c1ItemStateChanged

    private void c2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c2ItemStateChanged
      cjumlah.setText(""+(Integer.parseInt(c1.getSelectedItem().toString())+Integer.parseInt(c2.getSelectedItem().toString())+Integer.parseInt(c3.getSelectedItem().toString())+Integer.parseInt(c4.getSelectedItem().toString()
    )+Integer.parseInt(c5.getSelectedItem().toString())));
    }//GEN-LAST:event_c2ItemStateChanged

    private void c3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c3ItemStateChanged
       cjumlah.setText(""+(Integer.parseInt(c1.getSelectedItem().toString())+Integer.parseInt(c2.getSelectedItem().toString())+Integer.parseInt(c3.getSelectedItem().toString())+Integer.parseInt(c4.getSelectedItem().toString()
    )+Integer.parseInt(c5.getSelectedItem().toString())));
    }//GEN-LAST:event_c3ItemStateChanged

    private void c4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c4ItemStateChanged
      cjumlah.setText(""+(Integer.parseInt(c1.getSelectedItem().toString())+Integer.parseInt(c2.getSelectedItem().toString())+Integer.parseInt(c3.getSelectedItem().toString())+Integer.parseInt(c4.getSelectedItem().toString()
    )+Integer.parseInt(c5.getSelectedItem().toString())));
    }//GEN-LAST:event_c4ItemStateChanged

    private void c5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c5ItemStateChanged
       cjumlah.setText(""+(Integer.parseInt(c1.getSelectedItem().toString())+Integer.parseInt(c2.getSelectedItem().toString())+Integer.parseInt(c3.getSelectedItem().toString())+Integer.parseInt(c4.getSelectedItem().toString()
    )+Integer.parseInt(c5.getSelectedItem().toString())));
    }//GEN-LAST:event_c5ItemStateChanged

    private void ChkInput4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkInput4ItemStateChanged
     
    }//GEN-LAST:event_ChkInput4ItemStateChanged

    private void dpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpjpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpjpActionPerformed

    private void DTPtanggalkembaliItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPtanggalkembaliItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembaliItemStateChanged

    private void DTPtanggalkembaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPtanggalkembaliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPtanggalkembaliKeyPressed

    private void tbcpptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcpptMouseClicked
        if(tbcppt.getRowCount()!=0){
            try {
                getDatacppt();
            } catch (java.lang.NullPointerException e){

            }
        }
    }//GEN-LAST:event_tbcpptMouseClicked

    private void tbcpptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbcpptKeyPressed
        if(tbcppt.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDatacppt();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();

            }
        }
    }//GEN-LAST:event_tbcpptKeyPressed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if(Tnorw2.getText().trim().equals("")){
            Valid.textKosong(Tnorw2,"No.Rawat");
        }else if(kdppjp.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu PPJP...!!!");
        }else{
            //String kdpj=Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",norawat.getText());

            //Sequel.meghapus("ubah_penjab","no_rawat",norawat.getText());
            Sequel.menyimpan("cppt","?,?,?,?,?,?,?,?",8,new String[]{TNoRw.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),
                            kdppjp.getText(),kdppa.getText(),cmbrevieiw.getSelectedItem().toString(),soap.getText(),instruksi.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()});

            tampilcppt();
            emptyteks();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(Tnorw2,"No.Rawat");
        }else if(kdppjp.getText().trim().equals("")||ppjp.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih PPJP...!!!");
        }else{
         if(tbcppt.getSelectedRow()>-1){
                            Sequel.mengedit("cppt","no_rawat='"+tbcppt.getValueAt(tbcppt.getSelectedRow(),1)+
                                    "' and tgl_perawatan='"+tbcppt.getValueAt(tbcppt.getSelectedRow(),4)+
                                    "' and jam_rawat='"+tbcppt.getValueAt(tbcppt.getSelectedRow(),5)+"'",
                                    "no_rawat='"+TNoRw.getText()+"', tgl_perawatan='"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"', "+
                                    "jam_rawat='"+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"', "+
                                    "kdppjp='"+kdppjp.getText()+"', kdppa='"+kdppa.getText()+"', soap='"+soap.getText()+"', "+
                                    "instruksi='"+instruksi.getText()+"', verifikasi='"+cmbrevieiw.getSelectedItem().toString()+"'");
                            tampilcppt();
                            emptyteks();
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang mau diganti..!!");
                            tglcppt1.requestFocus();
                        }
            tampilcppt();
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowCPPT.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

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
        param.put("logo1",Sequel.cariGambar("select pemda from gambar"));
        Valid.MyReportqry("rptcppt.jasper","report","::[ CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (CPPT) ]::",
            "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk," +
                "pasien.tgl_lahir,pasien.umur,bangsal.nm_bangsal,kamar_inap.tgl_masuk, dokter.nm_dokter,petugas.nama as ppjp,cppt.tgl_perawatan,cppt.jam_rawat,cppt.soap,cppt.instruksi,cppt.verifikasi,pegawai.nama as ppa " +
                "from cppt INNER JOIN kamar_inap ON kamar_inap.no_rawat=cppt.no_rawat " +
                "INNER JOIN reg_periksa ON reg_periksa.no_rawat=kamar_inap.no_rawat " +
                "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                "INNER JOIN dpjp_ranap ON dpjp_ranap.no_rawat=kamar_inap.no_rawat " +
                "INNER JOIN dokter ON dokter.kd_dokter=dpjp_ranap.kd_dokter " +
                "INNER JOIN petugas ON petugas.nip=cppt.kdppjp " +
                "INNER JOIN pegawai ON pegawai.nik=cppt.kdppa " +
                "INNER JOIN kamar ON kamar.kd_kamar=kamar_inap.kd_kamar " +
                "INNER JOIN bangsal ON bangsal.kd_bangsal=kamar.kd_bangsal " +
                "WHERE cppt.no_rawat='"+TNoRw.getText()+"'" ,param);

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void tglcppt1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglcppt1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tglcppt1ItemStateChanged

    private void tglcppt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglcppt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglcppt1KeyPressed

    private void tglcppt2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglcppt2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tglcppt2ItemStateChanged

    private void tglcppt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglcppt2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglcppt2KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilcppt();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void Tnorw2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tnorw2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tnorw2ActionPerformed

    private void soapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_soapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_soapKeyPressed

    private void instruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_instruksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_instruksiKeyPressed

    private void TglmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglmasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglmasukActionPerformed

    private void kdppjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdppjpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdppjpActionPerformed

    private void ppjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppjpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppjpActionPerformed

    private void btncaripetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugas1ActionPerformed
        pegawai2.emptTeks();
        pegawai2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai2.setLocationRelativeTo(internalFrame1);
        pegawai2.setAlwaysOnTop(false);
        pegawai2.setVisible(true);
    }//GEN-LAST:event_btncaripetugas1ActionPerformed

    private void btncaripetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncaripetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncaripetugas1KeyPressed

    private void Tglmasuk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tglmasuk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tglmasuk1ActionPerformed

    private void Tnorw3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tnorw3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tnorw3ActionPerformed

    private void kdppaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdppaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdppaActionPerformed

    private void ppaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppaActionPerformed

    private void btncaripetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugas2ActionPerformed
        pegawai3.emptTeks();
        pegawai3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai3.setLocationRelativeTo(internalFrame1);
        pegawai3.setAlwaysOnTop(false);
        pegawai3.setVisible(true);
    }//GEN-LAST:event_btncaripetugas2ActionPerformed

    private void btncaripetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncaripetugas2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncaripetugas2KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptyteks();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
      if(tabModecppt.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
                    tglcppt1.requestFocus();
                    
                }else {
                    for(i=0;i<tbcppt.getRowCount();i++){
                        if(tbcppt.getValueAt(i,0).toString().equals("true")){
                            Sequel.queryu("delete from cppt where no_rawat='"+tbcppt.getValueAt(i,1).toString()+
                                    "' and tgl_perawatan='"+tbcppt.getValueAt(i,4).toString()+
                                    "' and jam_rawat='"+tbcppt.getValueAt(i,5).toString()+"' ");
                        }
                    }
                    tampilcppt();
                    emptyteks();
                } 
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnCatatan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCatatan1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRawatInaprs dialog = new DlgRawatInaprs(new javax.swing.JFrame(), true);
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
    private widget.TextBox AlamatIbu;
    private widget.TextBox Anakke;
    private javax.swing.JTextField BagianRS;
    private widget.TextBox Berat;
    private javax.swing.JTextField Bhp;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnBerkasDigital;
    private widget.Button BtnCPPT;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCatatan;
    private widget.Button BtnCatatan1;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCopyResep;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti;
    private widget.Button BtnGizi;
    private widget.Button BtnHD;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnIRM;
    private widget.Button BtnInputObat;
    private widget.Button BtnKeluar;
    private widget.Button BtnObatBhp;
    private widget.Button BtnPenjab;
    private widget.Button BtnPenjab1;
    private widget.Button BtnPermintaanLab;
    private widget.Button BtnPermintaanRad;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnResepObat;
    private widget.Button BtnRiwayat;
    private widget.Button BtnRujukKeluar;
    private widget.Button BtnSKDP;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan5;
    private widget.Button BtnUTD;
    private widget.ComboBox CbLanjut;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkInput4;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.Tanggal DTPtanggalkembali;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diagnosaawal;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox GPA;
    private widget.ComboBox JKel;
    private javax.swing.JTextField JmDokter;
    private javax.swing.JTextField JmPerawat;
    private javax.swing.JTextField KSO;
    private widget.TextBox KdPenolong;
    private widget.TextBox KdPenolong1;
    private widget.TextBox Ketuban;
    private widget.TextBox Konsul;
    private widget.Label LCount;
    private widget.Tanggal Lahir;
    private widget.TextBox LingkarDada;
    private widget.TextBox LingkarKepala;
    private widget.TextBox LingkarPerut;
    private javax.swing.JTextField Menejemen;
    private javax.swing.JMenuItem MnRingksanKeluar;
    private widget.TextBox NmAyah;
    private widget.TextBox NmPenolong;
    private widget.TextBox NmPenolong1;
    private widget.TextBox Nmibu;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput4;
    private javax.swing.JPanel PanelInput5;
    private widget.TextBox Panjang;
    private widget.TextBox PenyulitKehamilan;
    private widget.TextBox Proses;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TAlergi;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TGCS;
    private widget.TextArea TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNadi1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TPemeriksaan;
    private widget.TextBox TRespirasi;
    private widget.TextBox TRespirasi1;
    private widget.TextBox TSuhu;
    private widget.TextBox TSuhu1;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private javax.swing.JTextField TTnd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Tdx_banding;
    private widget.TextArea Tdx_kerja;
    private widget.TextBox Tfollowup;
    private widget.TextBox Tglmasuk;
    private widget.TextBox Tglmasuk1;
    private widget.TextArea Tkel_utama;
    private widget.TextBox Tnorw2;
    private widget.TextBox Tnorw3;
    private widget.TextArea Tprognosis;
    private widget.TextArea Tprosedur;
    private widget.TextArea Triwayat;
    private widget.TextArea Triwayat_alergi;
    private widget.TextArea Triwayat_dulu;
    private widget.TextArea Triwayat_keluarga;
    private widget.TextArea Tterapi;
    private widget.TextArea Tterapipulang;
    private widget.TextBox UmurAyah;
    private widget.TextBox UmurBayi;
    private widget.TextBox UmurIbu;
    private javax.swing.JDialog WindowCPPT;
    private widget.ComboBox a1;
    private widget.ComboBox a2;
    private widget.ComboBox a3;
    private widget.ComboBox a4;
    private widget.ComboBox a5;
    private widget.TextBox ajumlah;
    private widget.ComboBox b1;
    private widget.ComboBox b2;
    private widget.ComboBox b3;
    private widget.ComboBox b4;
    private widget.ComboBox b5;
    private widget.TextBox bjumlah;
    private widget.Button btnPasien;
    private widget.Button btncaripetugas1;
    private widget.Button btncaripetugas2;
    private widget.ComboBox c1;
    private widget.ComboBox c2;
    private widget.ComboBox c3;
    private widget.ComboBox c4;
    private widget.ComboBox c5;
    private widget.TextBox cjumlah;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbrevieiw;
    private widget.TextArea cttnkeperawatan;
    private widget.ComboBox detik;
    private widget.TextBox dpjp;
    private widget.TextArea instruksi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
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
    private widget.Label jLabel26;
    private widget.Label jLabel3;
    private widget.Label jLabel37;
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel61;
    private widget.Label jLabel63;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel8;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator5;
    private widget.ComboBox jam;
    private widget.TextBox kdppa;
    private widget.TextBox kdppjp;
    private widget.TextArea keterangan;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label50;
    private widget.Label label51;
    private widget.Label label52;
    private widget.Label label53;
    private widget.Label label54;
    private widget.Label label55;
    private widget.Label label56;
    private widget.Label label57;
    private widget.Label label58;
    private widget.Label label59;
    private widget.Label label60;
    private widget.Label label61;
    private widget.Label label62;
    private widget.Label label63;
    private widget.Label label64;
    private widget.Label label65;
    private widget.Label label66;
    private widget.Label label67;
    private widget.Label label68;
    private widget.Label label69;
    private widget.Label label70;
    private widget.Label label71;
    private widget.Label label72;
    private widget.Label label73;
    private widget.Label label74;
    private widget.Label label75;
    private widget.Label label76;
    private widget.Label label77;
    private widget.TextBox labor;
    private widget.TextBox mekonium;
    private widget.ComboBox menit;
    private widget.TextBox mikasi;
    private widget.TextBox obatdiberi;
    private widget.panelGlass panelGlass1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass8;
    private widget.TextBox ppa;
    private widget.TextBox ppjp;
    private widget.TextArea resusitasi;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.TextArea soap;
    private widget.TextBox spo2;
    private widget.Table tbNeonatal;
    private widget.Table tbPemeriksaan;
    private widget.Table tbawal;
    private widget.Table tbcppt;
    private widget.Tanggal tglcppt1;
    private widget.Tanggal tglcppt2;
    private widget.TextBox usia_kehamilan;
    // End of variables declaration//GEN-END:variables


    private void isRawat(){
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ",TNoRM,TNoRw.getText());
    }

    private void isPsien(){
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

   
    
    public void setNoRm(String norwt,Date awal,Date akhir) {
        TNoRw.setText(norwt);
        
        isRawat();
        isPsien();
        DTPCari1.setDate(awal);
        DTPCari2.setDate(akhir);
        TCari.setText(norwt);
        ChkInput.setSelected(true);
        isForm();
        TabRawatMouseClicked(null);
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH,250));
            panelGlass12.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput1.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass12.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.gettindakan_ranap());
        BtnHapus.setEnabled(akses.gettindakan_ranap());
        BtnEdit.setEnabled(akses.gettindakan_ranap());
        BtnDiagnosa.setEnabled(akses.getdiagnosa_pasien());         
        BtnRiwayat.setEnabled(akses.getresume_pasien());    
        BtnResepObat.setEnabled(akses.getresep_dokter());
        BtnCopyResep.setEnabled(akses.getresep_dokter());
        BtnObatBhp.setEnabled(akses.getberi_obat());  
        BtnInputObat.setEnabled(akses.getberi_obat());   
        BtnPermintaanLab.setEnabled(akses.getpermintaan_lab());     
        BtnBerkasDigital.setEnabled(akses.getberkas_digital_perawatan());    
        BtnPermintaanRad.setEnabled(akses.getpermintaan_radiologi());  
        BtnRujukKeluar.setEnabled(akses.getrujukan_keluar());
        BtnSKDP.setEnabled(akses.getskdp_bpjs());     
        BtnCatatan.setEnabled(akses.getcatatan_pasien());  
        
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

    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabModePemeriksaan);
        try{  
            ps4=koneksi.prepareStatement("select pemeriksaan_ranap_modif.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"+
                    "pemeriksaan_ranap_modif.tgl_perawatan,pemeriksaan_ranap_modif.jam_rawat,pemeriksaan_ranap_modif.suhu_tubuh,pemeriksaan_ranap_modif.tensi, " +
                    "pemeriksaan_ranap_modif.nadi,pemeriksaan_ranap_modif.respirasi,pemeriksaan_ranap_modif.tinggi, " +
                    "pemeriksaan_ranap_modif.berat,pemeriksaan_ranap_modif.gcs,pemeriksaan_ranap_modif.keluhan, " +
                    "pemeriksaan_ranap_modif.pemeriksaan,pemeriksaan_ranap_modif.alergi,pemeriksaan_ranap_modif.dxawal,pemeriksaan_ranap_modif.terapi,pemeriksaan_ranap_modif.hasilkonsul,pemeriksaan_ranap_modif.hasillabor,pemeriksaan_ranap_modif.lanjut,pemeriksaan_ranap_modif.obatpulang,pemeriksaan_ranap_modif.prosedur,pemeriksaan_ranap_modif.followup " +
                    "from pasien inner join reg_periksa inner join pemeriksaan_ranap_modif "+
                    "on pemeriksaan_ranap_modif.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap_modif.no_rawat like ? or "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap_modif.alergi like ? or "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap_modif.keluhan like ? or "+
                    "pemeriksaan_ranap_modif.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap_modif.pemeriksaan like ? "+
                   "order by pemeriksaan_ranap_modif.no_rawat desc"); 
            try{
                ps4.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(3,"%"+TCariPasien.getText()+"%");
                ps4.setString(4,"%"+TCari.getText().trim()+"%");
                ps4.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(7,"%"+TCariPasien.getText()+"%");
                ps4.setString(8,"%"+TCari.getText().trim()+"%");
                ps4.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(11,"%"+TCariPasien.getText()+"%");
                ps4.setString(12,"%"+TCari.getText().trim()+"%");
                ps4.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(15,"%"+TCariPasien.getText()+"%");
                ps4.setString(16,"%"+TCari.getText().trim()+"%");
                ps4.setString(17,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(18,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(19,"%"+TCariPasien.getText()+"%");
                ps4.setString(20,"%"+TCari.getText().trim()+"%");
                ps4.setString(21,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps4.setString(22,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps4.setString(23,"%"+TCariPasien.getText()+"%");
                ps4.setString(24,"%"+TCari.getText().trim()+"%");
                rs=ps4.executeQuery();
                while(rs.next()){
                    tabModePemeriksaan.addRow(new Object[]{false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7),
                                   rs.getString(8),
                                   rs.getString(9),
                                   rs.getString(10),
                                   rs.getString(11),
                                   rs.getString(12),
                                   rs.getString(13),
                                   rs.getString(14),
                                   rs.getString(15),
                                   rs.getString(16),
                                   rs.getString(17),
                                   rs.getString(18),
                                   rs.getString(19),
                                   rs.getString(20),
                                   rs.getString(21),
                                   rs.getString(22),
                                   rs.getString(23)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps4!=null){
                    ps4.close();
                }
            }                  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabModePemeriksaan.getRowCount());
    }

    private void getDataPemeriksaan() {
        if(tbPemeriksaan.getSelectedRow()!= -1){
            TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),1).toString());
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),2).toString());
            TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),3).toString());             
            TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),6).toString()); 
            TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),7).toString()); 
            TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),8).toString()); 
            TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),9).toString()); 
            TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),10).toString()); 
            TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),11).toString());  
            TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),12).toString()); 
            TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),13).toString()); 
            TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),14).toString()); 
            TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),15).toString());
            Diagnosaawal.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),16).toString());
            Tterapi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),17).toString());
            Konsul.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),18).toString());
            labor.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),19).toString());
            CbLanjut.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),20).toString());
            Tterapipulang.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),21).toString());
            Tprosedur.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),22).toString());
            Tfollowup.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),23).toString());
            cmbJam.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),5).toString().substring(6,8));
            Valid.SetTgl(DTPTgl,tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(),4).toString());
        }
    }
    
    
    
      private void tampilAsesmenAwal() {
        Valid.tabelKosong(tabModeAwal);
        try{
            ps7=koneksi.prepareStatement("SELECT asesmen_awal.no_rawat,pasien.nm_pasien,pasien.no_rkm_medis,asesmen_awal.tgl_perawatan,asesmen_awal.jam_rawat,asesmen_awal.riwayat_alergi,asesmen_awal.keluhan_utama,asesmen_awal.riwayat,asesmen_awal.riwayat_dulu," +
                    "asesmen_awal.riwayat_keluarga,asesmen_awal.diagnosa_kerja,asesmen_awal.diagnosa_banding,asesmen_awal.prognosis FROM asesmen_awal "+
                    "INNER JOIN reg_periksa ON reg_periksa.no_rawat=asesmen_awal.no_rawat "+
                    "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis where  "+
                    "asesmen_awal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and asesmen_awal.no_rawat like ? or "+
                    "asesmen_awal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "asesmen_awal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ?  "+
                    "order by asesmen_awal.no_rawat desc");
            try {
                ps7.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps7.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps7.setString(3,"%"+TCariPasien.getText()+"%");
                ps7.setString(4,"%"+TCari.getText().trim()+"%");
                ps7.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps7.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps7.setString(7,"%"+TCariPasien.getText()+"%");
                ps7.setString(8,"%"+TCari.getText().trim()+"%");
                ps7.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps7.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps7.setString(11,"%"+TCariPasien.getText()+"%");
                ps7.setString(12,"%"+TCari.getText().trim()+"%");
                rs=ps7.executeQuery();
                while(rs.next()) {
                    tabModeAwal.addRow(new Object[] {
                        false, rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("tgl_perawatan"),rs.getString("jam_rawat"),rs.getString("riwayat_alergi"),
                        rs.getString("keluhan_utama"),rs.getString("riwayat"),rs.getString("riwayat_dulu"),
                        rs.getString("riwayat_keluarga"),rs.getString("diagnosa_kerja"),rs.getString("diagnosa_banding"),
                        rs.getString("prognosis")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);    
            } finally {
                if(rs!=null) {
                    rs.close();
                }
                if(ps7!=null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi :" +e);
        }
        LCount.setText(""+tabModeAwal.getRowCount());
    }     
        
    private void getDataAsesmenAwal() {
        if(tbawal.getSelectedRow()!= -1) {
            TNoRw.setText(tbawal.getValueAt(tbawal.getSelectedRow(),1).toString());
            TNoRM.setText(tbawal.getValueAt(tbawal.getSelectedRow(),2).toString());
            TPasien.setText(tbawal.getValueAt(tbawal.getSelectedRow(),3).toString());
            Valid.SetTgl(DTPTgl,tbawal.getValueAt(tbawal.getSelectedRow(),4).toString());
            cmbJam.setSelectedItem(tbawal.getValueAt(tbawal.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbawal.getValueAt(tbawal.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbawal.getValueAt(tbawal.getSelectedRow(),5).toString().substring(6,8));
            Triwayat_alergi.setText(tbawal.getValueAt(tbawal.getSelectedRow(),6).toString());
            Tkel_utama.setText(tbawal.getValueAt(tbawal.getSelectedRow(),7).toString());
            Triwayat.setText(tbawal.getValueAt(tbawal.getSelectedRow(),8).toString());
            Triwayat_dulu.setText(tbawal.getValueAt(tbawal.getSelectedRow(),9).toString());
            Triwayat_keluarga.setText(tbawal.getValueAt(tbawal.getSelectedRow(),10).toString());
            Tdx_kerja.setText(tbawal.getValueAt(tbawal.getSelectedRow(),11).toString());
            Tdx_banding.setText(tbawal.getValueAt(tbawal.getSelectedRow(),12).toString());
            Tprognosis.setText(tbawal.getValueAt(tbawal.getSelectedRow(),13).toString());
        }
    }
    
    
    

    
   
    
   
    
    private void isForm4(){
        if(ChkInput3.isSelected()==true){
            ChkInput3.setVisible(false);
            PanelInput4.setPreferredSize(new Dimension(WIDTH,420));
            panelGlass16.setVisible(true);      
            ChkInput3.setVisible(true);
        }else if(ChkInput3.isSelected()==false){           
            ChkInput3.setVisible(false);            
            PanelInput4.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass16.setVisible(false);      
            ChkInput3.setVisible(true);
        }
    }
    
    private void isForm5(){
        if(ChkInput4.isSelected()==true){
            ChkInput4.setVisible(false);
            PanelInput5.setPreferredSize(new Dimension(WIDTH,600));
            panelGlass17.setVisible(true);      
            ChkInput4.setVisible(true);
        }else if(ChkInput4.isSelected()==false){           
            ChkInput4.setVisible(false);            
            PanelInput5.setPreferredSize(new Dimension(WIDTH,20));
            panelGlass17.setVisible(false);      
            ChkInput4.setVisible(true);
        }
    }
    
    
     private void tampilAsesmenNeonatal() {
        Valid.tabelKosong(tabModeNeonatal);
        try{
            ps8=koneksi.prepareStatement("SELECT asesmen_neonatal.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,asesmen_neonatal.tgl_perawatan,asesmen_neonatal.jam_rawat,asesmen_neonatal.nm_ibu,asesmen_neonatal.umur_ibu,asesmen_neonatal.nama_ayah,asesmen_neonatal.umur_ayah,asesmen_neonatal.alamat,asesmen_neonatal.jkel,asesmen_neonatal.berat_badan,asesmen_neonatal.panjang_badan,asesmen_neonatal.lingkar_kepala,asesmen_neonatal.proses_lahir,asesmen_neonatal.anakke," +
                    "asesmen_neonatal.jam_lahir,asesmen_neonatal.lain_lain,asesmen_neonatal.diagnosa,asesmen_neonatal.penyulit_kehamilan,asesmen_neonatal.ketuban,asesmen_neonatal.lingkar_perut,asesmen_neonatal.lingkar_dada,asesmen_neonatal.penolong_dpjp,asesmen_neonatal.penolong_kelahiran,asesmen_neonatal.usia_kehamilan,asesmen_neonatal.gpa,asesmen_neonatal.a1,asesmen_neonatal.a2,asesmen_neonatal.a3,asesmen_neonatal.a4,asesmen_neonatal.a5," +
                    "asesmen_neonatal.b1,asesmen_neonatal.b2,asesmen_neonatal.b3,asesmen_neonatal.b4,asesmen_neonatal.b5,asesmen_neonatal.c1,asesmen_neonatal.c2,asesmen_neonatal.c3,asesmen_neonatal.c4,asesmen_neonatal.c5,asesmen_neonatal.ajumlah,asesmen_neonatal.bjumlah,asesmen_neonatal.cjumlah," +
                    "asesmen_neonatal.resosistasi,asesmen_neonatal.obat_diberi,asesmen_neonatal.mikasi,asesmen_neonatal.mekunium,asesmen_neonatal.suhu,asesmen_neonatal.nadi,asesmen_neonatal.respirasi,asesmen_neonatal.spo2,asesmen_neonatal.catatan_keperawatan,asesmen_neonatal.tgl_lahir FROM asesmen_neonatal " +
                    "INNER JOIN reg_periksa on reg_periksa.no_rawat=asesmen_neonatal.no_rawat " +
                    "INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis " +
                    "INNER JOIN pegawai ON pegawai.nik=asesmen_neonatal.penolong_dpjp where "+
                    "asesmen_neonatal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and asesmen_neonatal.no_rawat like ? or "+
                    "asesmen_neonatal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "+
                    "asesmen_neonatal.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ?  "+
                    "order by asesmen_neonatal.no_rawat desc");
            try {
                ps8.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps8.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps8.setString(3,"%"+TCariPasien.getText()+"%");
                ps8.setString(4,"%"+TCari.getText().trim()+"%");
                ps8.setString(5,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps8.setString(6,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps8.setString(7,"%"+TCariPasien.getText()+"%");
                ps8.setString(8,"%"+TCari.getText().trim()+"%");
                ps8.setString(9,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps8.setString(10,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps8.setString(11,"%"+TCariPasien.getText()+"%");
                ps8.setString(12,"%"+TCari.getText().trim()+"%");
                rs=ps8.executeQuery();
                while(rs.next()) {
                    tabModeNeonatal.addRow(new Object[] {
                        false,rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
                        rs.getString(16),rs.getString(17),rs.getString(18),
                        rs.getString(19),rs.getString(20),rs.getString(21),
                        rs.getString(22),rs.getString(23),rs.getString(24),
                        rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
                        rs.getString(29),rs.getString(30),rs.getString(31),
                        rs.getString(32),rs.getString(33),rs.getString(34),
                        rs.getString(35),rs.getString(36),rs.getString(37),
                        rs.getString(38),rs.getString(39),rs.getString(40),rs.getString(41),
                        rs.getString(42),rs.getString(43),rs.getString(44),rs.getString(45),
                        rs.getString(46),rs.getString(47),rs.getString(48),
                        rs.getString(49),rs.getString(50),rs.getString(51),
                        rs.getString(52),rs.getString(53),rs.getString(54),rs.getString(55)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);    
            } finally {
                if(rs!=null) {
                    rs.close();
                }
                if(ps8!=null) {
                    ps8.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi :" +e);
        }
        LCount.setText(""+tabModeNeonatal.getRowCount());
    } 
     
      private void getDataNeonatal() {
         if(tbNeonatal.getSelectedRow()!= -1) {
            TNoRw.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),1).toString());
            TNoRM.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),2).toString());
            TPasien.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),3).toString());
            Valid.SetTgl(DTPTgl,tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),4).toString());
            cmbJam.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),5).toString().substring(0,2));
            cmbMnt.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),5).toString().substring(3,5));
            cmbDtk.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),5).toString().substring(6,8));
            Nmibu.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),6).toString());
            UmurIbu.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),7).toString());
            NmAyah.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),8).toString());
            UmurAyah.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),9).toString());
            AlamatIbu.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),10).toString());
            JKel.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),11).toString());
            Berat.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),12).toString());
            Panjang.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),13).toString());
            LingkarKepala.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),14).toString());
            Proses.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),15).toString());
            Anakke.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),16).toString());
            jam.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),17).toString().substring(0,2));
            menit.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),17).toString().substring(3,5));
            detik.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),17).toString().substring(6,8));
            keterangan.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),18).toString());
            Diagnosa.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),19).toString());
            PenyulitKehamilan.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),20).toString());
            Ketuban.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),21).toString());
            LingkarPerut.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),22).toString());
            LingkarDada.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),23).toString());
            KdPenolong.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),24).toString());
            KdPenolong1.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),25).toString());
            Sequel.cariIsi("SELECT nama FROM pegawai WHERE nik=?",NmPenolong,tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),24).toString());
            Sequel.cariIsi("SELECT nama FROM pegawai WHERE nik=?",NmPenolong1,tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),25).toString());
            usia_kehamilan.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),26).toString());
            GPA.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),27).toString());
            a1.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),28).toString());
            a2.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),29).toString());
            a3.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),30).toString());
            a4.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),31).toString());
            a5.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),32).toString());
            b1.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),33).toString());
            b2.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),34).toString());
            b3.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),35).toString());
            b4.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),36).toString());
            b5.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),37).toString());
            c1.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),38).toString());
            c2.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),39).toString());
            c3.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),40).toString());
            c4.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),41).toString());
            c5.setSelectedItem(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),42).toString());
            ajumlah.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),43).toString());
            bjumlah.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),44).toString());
            cjumlah.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),45).toString());
            resusitasi.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(), 46).toString());
            obatdiberi.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),47).toString());
            mikasi.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),48).toString());
            mekonium.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),49).toString());
            TSuhu1.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),50).toString());
            TNadi1.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),51).toString());
            TRespirasi1.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),52).toString());
            spo2.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),53).toString());
            cttnkeperawatan.setText(tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),54).toString());
            Valid.SetTgl(Lahir,tbNeonatal.getValueAt(tbNeonatal.getSelectedRow(),55).toString());   
            
        }
    }
      
      
   private void tampilcppt() {
        Valid.tabelKosong(tabModecppt);
        try{
            ps9=koneksi.prepareStatement("SELECT cppt.no_rawat,pasien.nm_pasien,pasien.no_rkm_medis,cppt.tgl_perawatan,cppt.jam_rawat,cppt.kdppjp,cppt.kdppa,cppt.soap,cppt.instruksi,cppt.verifikasi FROM cppt "+
                    "INNER JOIN reg_periksa ON reg_periksa.no_rawat=cppt.no_rawat "+
                    "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "WHERE "+
                    "cppt.tgl_perawatan between ? and ? and cppt.no_rawat like ? "+
                    "order by cppt.jam_rawat desc");
            try {
                ps9.setString(1,Valid.SetTgl(tglcppt1.getSelectedItem()+""));
                ps9.setString(2,Valid.SetTgl(tglcppt2.getSelectedItem()+""));
                ps9.setString(3,"%"+TNoRw.getText()+"%");
               
                rs=ps9.executeQuery();
                while(rs.next()) {
                    tabModecppt.addRow(new Object[] {
                        false, rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("tgl_perawatan"),rs.getString("jam_rawat"),rs.getString("kdppjp"),
                        rs.getString("kdppa"),rs.getString("soap"),rs.getString("instruksi"),
                        rs.getString("verifikasi")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);    
            } finally {
                if(rs!=null) {
                    rs.close();
                }
                if(ps7!=null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi :" +e);
        }
       
    } 
   
       private void getDatacppt() {
        if(tbcppt.getSelectedRow()!= -1) {
            Valid.SetTgl(DTPtanggalkembali,tbcppt.getValueAt(tbcppt.getSelectedRow(),4).toString());
            kdppjp.setText(tbcppt.getValueAt(tbcppt.getSelectedRow(),6).toString());
            kdppa.setText(tbcppt.getValueAt(tbcppt.getSelectedRow(),7).toString());
            soap.setText(tbcppt.getValueAt(tbcppt.getSelectedRow(),8).toString());
            instruksi.setText(tbcppt.getValueAt(tbcppt.getSelectedRow(),9).toString());
            cmbrevieiw.setSelectedItem(tbcppt.getValueAt(tbcppt.getSelectedRow(),10).toString());
            Tglmasuk1.setText(tbcppt.getValueAt(tbcppt.getSelectedRow(),5).toString());
            Sequel.cariIsi("SELECT nama FROM pegawai WHERE nik=?",ppjp,tbcppt.getValueAt(tbcppt.getSelectedRow(),6).toString());
            Sequel.cariIsi("SELECT nama FROM pegawai WHERE nik=?",ppa,tbcppt.getValueAt(tbcppt.getSelectedRow(),7).toString());
        }
    }
      
        private void emptyteks() {
            kdppjp.setText("");
            kdppa.setText("");
            soap.setText("");
            instruksi.setText("");
            Tglmasuk1.setText("");
            ppjp.setText("");
            ppa.setText("");
            DTPtanggalkembali.setDate(new Date());
            tglcppt1.setDate(new Date());
            tglcppt2.setDate(new Date());
            cmbrevieiw.setSelectedIndex(0);
        }           
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(135,HEIGHT));
            FormMenu.setVisible(true);      
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){           
            ChkAccor.setVisible(false);            
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);      
            ChkAccor.setVisible(true);
        }
    }
    
    private void inputResep() {
        DlgPeresepanDokter resep=new DlgPeresepanDokter(null,false);
        resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        resep.setLocationRelativeTo(internalFrame1);
        resep.setNoRm(TNoRw.getText(),DTPTgl.getDate(),cmbJam.getSelectedItem().toString(),cmbMnt.getSelectedItem().toString(),
                cmbDtk.getSelectedItem().toString(),Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?",TNoRw.getText()),Sequel.cariIsi("select nm_dokter from dokter inner join reg_periksa on reg_periksa.kd_dokter=dokter.kd_dokter where no_rawat=?",TNoRw.getText()),"ranap");
        resep.isCek();
        resep.tampilobat();
        resep.setVisible(true);
    }
    
    private void inputObat() {
        DlgCariObat2 dlgobt=new DlgCariObat2(null,false);
//        dlgobt.setNoRm(TNoRw.getText(),DTPTgl.getDate());
        dlgobt.isCek();
        dlgobt.tampil();
        dlgobt.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        dlgobt.setLocationRelativeTo(internalFrame1);
        dlgobt.setVisible(true);
    }
    
}

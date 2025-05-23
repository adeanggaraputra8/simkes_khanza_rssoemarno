package modifikasi;

import fungsi.WarnaTable;
import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCariObat;
import inventory.DlgPenjualan;
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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import simrskhanza.DlgCariPeriksaLab;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgCariPoli;
import inventory.DlgPemberianObat;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import keuangan.DlgBilingRalan;
import keuangan.DlgLhtBiaya;
import keuangan.DlgLhtPiutang;
import keuangan.Jurnal;
import org.eclipse.jdt.internal.compiler.ast.NormalAnnotation;
import simrskhanza.DlgPenanggungJawab;
import simrskhanza.DlgPeriksaLaboratorium;
import simrskhanza.DlgPeriksaRadiologi;
import simrskhanza.DlgRawatJalan;
import simrskhanza.DlgTagihanOperasi;

/**
 *
 * @author perpustakaan
 */
public class DlgBilingRalan12 extends javax.swing.JDialog {

    private final DefaultTableModel tabModeRwJlDr;
    private DefaultTableModel tabModeTambahan, tabModePotongan, tabModeAkunBayar, tabModeAkunPiutang;

    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgCariPoli poli = new DlgCariPoli(null, false);
    public DlgCariObat dlgobt = new DlgCariObat(null, false);
    public DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    public DlgPenjualan jual = new DlgPenjualan(null, false);
    private double ttl = 0, y = 0, subttl = 0, ralanparamedis = 0, piutang = 0, itembayar = 0, itempiutang = 0,
            bayar = 0, total = 0, tamkur = 0, detailjs = 0, detailbhp = 0, ppn = 0, besarppn = 0, tagihanppn = 0,
            ttlLaborat = 0, ttlRadiologi = 0, ttlObat = 0, ttlRalan_Dokter = 0, ttlRalan_Paramedis = 0,
            ttlTambahan = 0, ttlPotongan = 0, ttlRegistrasi = 0, ttlRalan_Dokter_Param = 0, ppnobat = 0, ttlOperasi = 0,
            Jasa_Medik_Dokter_Tindakan_Ralan = 0, Jasa_Medik_Paramedis_Tindakan_Ralan = 0, KSO_Tindakan_Ralan = 0,
            Jasa_Medik_Dokter_Laborat_Ralan = 0, Jasa_Medik_Petugas_Laborat_Ralan = 0, Kso_Laborat_Ralan = 0,
            Persediaan_Laborat_Rawat_Jalan = 0, Jasa_Medik_Dokter_Radiologi_Ralan = 0, Jasa_Medik_Petugas_Radiologi_Ralan = 0,
            Kso_Radiologi_Ralan = 0, Persediaan_Radiologi_Rawat_Jalan = 0, Obat_Rawat_Jalan = 0,
            Jasa_Medik_Dokter_Operasi_Ralan = 0, Jasa_Medik_Paramedis_Operasi_Ralan = 0, Obat_Operasi_Ralan = 0, kekurangan = 0, lab;
    private int i, r, cek, row2, countbayar = 0, z = 0, jml = 0, cekObat = 0, cekSimpan = 0, diagnosa_cek = 0, jmlNota = 0;
    private String status = "", biaya = "", tambahan = "", totals = "", kdptg = "", nmptg = "", kd_pj = "", notaralan = "", centangdokterralan = "",
            rinciandokterralan = "", Tindakan_Ralan = "", Laborat_Ralan = "", Radiologi_Ralan = "",
            Obat_Ralan = "", Registrasi_Ralan = "", Tambahan_Ralan = "", Potongan_Ralan = "",
            Beban_Jasa_Medik_Dokter_Tindakan_Ralan = "", Utang_Jasa_Medik_Dokter_Tindakan_Ralan = "",
            Beban_Jasa_Medik_Paramedis_Tindakan_Ralan = "", Utang_Jasa_Medik_Paramedis_Tindakan_Ralan = "",
            Beban_KSO_Tindakan_Ralan = "", Utang_KSO_Tindakan_Ralan = "", Beban_Jasa_Medik_Dokter_Laborat_Ralan = "",
            Utang_Jasa_Medik_Dokter_Laborat_Ralan = "", Beban_Jasa_Medik_Petugas_Laborat_Ralan = "",
            Utang_Jasa_Medik_Petugas_Laborat_Ralan = "", Beban_Kso_Laborat_Ralan = "", Utang_Kso_Laborat_Ralan = "",
            HPP_Persediaan_Laborat_Rawat_Jalan = "", Persediaan_BHP_Laborat_Rawat_Jalan = "",
            Beban_Jasa_Medik_Dokter_Radiologi_Ralan = "", Utang_Jasa_Medik_Dokter_Radiologi_Ralan = "",
            Beban_Jasa_Medik_Petugas_Radiologi_Ralan = "", Utang_Jasa_Medik_Petugas_Radiologi_Ralan = "",
            Beban_Kso_Radiologi_Ralan = "", Utang_Kso_Radiologi_Ralan = "", HPP_Persediaan_Radiologi_Rawat_Jalan = "",
            Persediaan_BHP_Radiologi_Rawat_Jalan = "", HPP_Obat_Rawat_Jalan = "", Persediaan_Obat_Rawat_Jalan = "",
            Beban_Jasa_Medik_Dokter_Operasi_Ralan = "", Utang_Jasa_Medik_Dokter_Operasi_Ralan = "",
            Beban_Jasa_Medik_Paramedis_Operasi_Ralan = "", Utang_Jasa_Medik_Paramedis_Operasi_Ralan = "",
            HPP_Obat_Operasi_Ralan = "", Persediaan_Obat_Kamar_Operasi_Ralan = "", diagnosa_ok = "", cekdokter = "",
            Operasi_Ralan = "", tampilkan_ppnobat_ralan = "", rincianoperasi = "", centangobatralan = "No",
            sqlpscekbilling = "select count(billing.no_rawat) from billing where billing.no_rawat=?",
            sqlpscarirm = "select no_rkm_medis from reg_periksa where no_rawat=?",
            sqlpscaripasien = "select p.nm_pasien, concat(r.umurdaftar,' ',r.sttsumur) umur from pasien p "
            + "inner join reg_periksa r on r.no_rkm_medis=p.no_rkm_medis where p.no_rkm_medis=? ",
            sqlpsreg = "select reg_periksa.no_rkm_medis,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,"
            + "reg_periksa.no_rkm_medis,reg_periksa.kd_poli,reg_periksa.no_rawat,reg_periksa.biaya_reg,current_time() as jam "
            + "from reg_periksa where reg_periksa.no_rawat=?",
            sqlpscaripoli = "select nm_poli from poliklinik where kd_poli=?",
            sqlpscarialamat = "select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) from pasien "
            + "inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel "
            + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
            + "where pasien.no_rkm_medis=?",
            sqlpsrekening = "select * from set_akun_ralan",
            sqlpsdokterralan = "select dokter.nm_dokter from reg_periksa "
            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by reg_periksa.kd_dokter",
            sqlpscariralandokter = "select jns_perawatan.nm_perawatan,rawat_jl_dr.biaya_rawat as total_byrdr,"
            + "count(rawat_jl_dr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_dr.biaya_rawat) as biaya,"
            + "sum(rawat_jl_dr.bhp) as totalbhp,"
            + "sum(rawat_jl_dr.material) as totalmaterial,"
            + "rawat_jl_dr.tarif_tindakandr,"
            + "sum(rawat_jl_dr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_jl_dr inner join jns_perawatan "
            + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where "
            + "rawat_jl_dr.no_rawat=? group by jns_perawatan.nm_perawatan",
            sqlpscariralanperawat = "select jns_perawatan.nm_perawatan,rawat_jl_pr.biaya_rawat as total_byrpr,"
            + "count(rawat_jl_pr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_pr.biaya_rawat) as biaya, "
            + "sum(rawat_jl_pr.bhp) as totalbhp,"
            + "sum(rawat_jl_pr.material) as totalmaterial,"
            + "sum(rawat_jl_pr.tarif_tindakanpr) as totaltarif_tindakanpr "
            + "from rawat_jl_pr inner join jns_perawatan "
            + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where "
            + "rawat_jl_pr.no_rawat=? group by jns_perawatan.nm_perawatan ",
            //            sqlpscariralandrpr = "select jns_perawatan.nm_perawatan,rawat_jl_drpr.biaya_rawat as total_byrdrpr,"
            //            + "count(rawat_jl_drpr.kd_jenis_prw) as jml, "
            //            + "sum(rawat_jl_drpr.biaya_rawat) as biaya,"
            //            + "sum(rawat_jl_drpr.bhp) as totalbhp,"
            //            + "sum(rawat_jl_drpr.material) as totalmaterial,"
            //            + "rawat_jl_drpr.tarif_tindakandr,"
            //            + "sum(rawat_jl_drpr.tarif_tindakanpr) as totaltarif_tindakanpr, "
            //            + "sum(rawat_jl_drpr.tarif_tindakandr) as totaltarif_tindakandr "
            //            + "from rawat_jl_drpr inner join jns_perawatan "
            //            + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where "
            //            + "rawat_jl_drpr.no_rawat=? group by jns_perawatan.nm_perawatan",
            sqlpscariralandrpr = "select jns_perawatan.nm_perawatan,rawat_jl_drpr.biaya_rawat as total_byrdrpr,"
            + "count(rawat_jl_drpr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_drpr.biaya_rawat) as biaya,"
            + "sum(rawat_jl_drpr.bhp) as totalbhp,"
            + "sum(rawat_jl_drpr.material) as totalmaterial,"
            + "rawat_jl_drpr.tarif_tindakandr,"
            + "sum(rawat_jl_drpr.tarif_tindakanpr) as totaltarif_tindakanpr, "
            + "sum(rawat_jl_drpr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_jl_drpr inner join jns_perawatan "
            + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where rawat_jl_drpr.stts_bayar = 'Belum' and "
            + "rawat_jl_drpr.no_rawat=? group by jns_perawatan.nm_perawatan",
            //            sqlpscarilab = "select jns_perawatan_lab.nm_perawatan, count(periksa_lab.kd_jenis_prw) as jml,periksa_lab.biaya as biaya, "
            //            + "sum(periksa_lab.biaya) as total,jns_perawatan_lab.kd_jenis_prw,sum(periksa_lab.tarif_perujuk+periksa_lab.tarif_tindakan_dokter) as totaldokter, "
            //            + "sum(periksa_lab.tarif_tindakan_petugas) as totalpetugas,sum(periksa_lab.kso) as totalkso,sum(periksa_lab.bhp) as totalbhp "
            //            + " from periksa_lab inner join jns_perawatan_lab on jns_perawatan_lab.kd_jenis_prw=periksa_lab.kd_jenis_prw where "
            //            + " periksa_lab.no_rawat=? group by periksa_lab.kd_jenis_prw  ",
            sqlpscarilab = "select jns_perawatan_lab.nm_perawatan, count(periksa_lab.kd_jenis_prw) as jml,periksa_lab.biaya as biaya, "
            + "sum(periksa_lab.biaya) as total,jns_perawatan_lab.kd_jenis_prw,sum(periksa_lab.tarif_perujuk+periksa_lab.tarif_tindakan_dokter) as totaldokter, "
            + "sum(periksa_lab.tarif_tindakan_petugas) as totalpetugas,sum(periksa_lab.kso) as totalkso,sum(periksa_lab.bhp) as totalbhp "
            + " from periksa_lab inner join jns_perawatan_lab on jns_perawatan_lab.kd_jenis_prw=periksa_lab.kd_jenis_prw where periksa_lab.stts_bayar='Belum' and"
            + " periksa_lab.no_rawat=? group by periksa_lab.kd_jenis_prw  ",
            //            sqlpscariobat = "select databarang.nama_brng,detail_pemberian_obat.biaya_obat,"
            //            + "sum(detail_pemberian_obat.jml) as jml,sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah) as tambahan,"
            //            + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as total, "
            //            + "sum((detail_pemberian_obat.h_beli*detail_pemberian_obat.jml)) as totalbeli "
            //            + "from detail_pemberian_obat inner join databarang "
            //            + "on detail_pemberian_obat.kode_brng=databarang.kode_brng where "
            //            + "detail_pemberian_obat.no_rawat=? group by databarang.nama_brng",
            sqlpscariobat = "select databarang.nama_brng,detail_pemberian_obat.biaya_obat,"
            + "sum(detail_pemberian_obat.jml) as jml,sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah) as tambahan,"
            + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as total, "
            + "sum((detail_pemberian_obat.h_beli*detail_pemberian_obat.jml)) as totalbeli "
            + "from detail_pemberian_obat inner join databarang "
            + "on detail_pemberian_obat.kode_brng=databarang.kode_brng where stts_bayar = 'Belum' "
            + "and detail_pemberian_obat.no_rawat=? group by databarang.nama_brng",
            sqlpsdetaillab = "select sum(detail_periksa_lab.biaya_item) as total,sum(detail_periksa_lab.bagian_perujuk+detail_periksa_lab.bagian_dokter) as totaldokter, "
            + "sum(detail_periksa_lab.bagian_laborat) as totalpetugas,sum(detail_periksa_lab.kso) as totalkso,sum(detail_periksa_lab.bhp) as totalbhp "
            + "from detail_periksa_lab where detail_periksa_lab.no_rawat=? "
            + "and detail_periksa_lab.kd_jenis_prw=? and detail_periksa_lab.no_nota='-' ",
            sqlpsobatlangsung = "select besar_tagihan from tagihan_obat_langsung where "
            + "no_rawat=? ",
            sqlpsreturobat = "select databarang.nama_brng,detreturjual.h_retur, "
            + "(detreturjual.jml_retur * -1) as jml, "
            + "(detreturjual.subtotal * -1) as ttl from detreturjual inner join databarang inner join returjual "
            + "on detreturjual.kode_brng=databarang.kode_brng "
            + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual=? group by databarang.nama_brng",
            sqlpstambahan = "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat=?  ",
            //            sqlpsbiling = "insert into billing values('0',?,?,?,?,?,?,?,?,?,?)",
            sqlpsbiling = "insert into billing values('0',?,?,?,?,?,?,?,?,?,?,?)",
            sqlpstemporary = "insert into temporary_bayar_ralan values('0',?,?,?,?,?,?,?,?,'','','','','','','','','')",
            sqlpspotongan = "select nama_pengurangan,besar_pengurangan from pengurangan_biaya where no_rawat=?",
            //            sqlpsbilling = "select no,nm_perawatan, if(biaya<>0,biaya,null) as satu, if(jumlah<>0,jumlah,null) as dua,"
            //            + "if(tambahan<>0,tambahan,null) as tiga, if(totalbiaya<>0,totalbiaya,null) as empat,pemisah,status "
            //            + "from billing where no_rawat=? order by noindex",
            //            sqlpscariradiologi = "select jns_perawatan_radiologi.nm_perawatan, count(periksa_radiologi.kd_jenis_prw) as jml,periksa_radiologi.biaya as biaya, "
            //            + "sum(periksa_radiologi.biaya) as total,jns_perawatan_radiologi.kd_jenis_prw,sum(periksa_radiologi.tarif_perujuk+periksa_radiologi.tarif_tindakan_dokter) as totaldokter, "
            //            + "sum(periksa_radiologi.tarif_tindakan_petugas) as totalpetugas,sum(periksa_radiologi.kso) as totalkso,sum(periksa_radiologi.bhp) as totalbhp "
            //            + " from periksa_radiologi inner join jns_perawatan_radiologi on jns_perawatan_radiologi.kd_jenis_prw=periksa_radiologi.kd_jenis_prw where "
            //            + " periksa_radiologi.no_rawat=? group by periksa_radiologi.kd_jenis_prw  ",
            sqlpscariradiologi = "select jns_perawatan_radiologi.nm_perawatan, count(periksa_radiologi.kd_jenis_prw) as jml,periksa_radiologi.biaya as biaya, "
            + "sum(periksa_radiologi.biaya) as total,jns_perawatan_radiologi.kd_jenis_prw,sum(periksa_radiologi.tarif_perujuk+periksa_radiologi.tarif_tindakan_dokter) as totaldokter, "
            + "sum(periksa_radiologi.tarif_tindakan_petugas) as totalpetugas,sum(periksa_radiologi.kso) as totalkso,sum(periksa_radiologi.bhp) as totalbhp "
            + " from periksa_radiologi inner join jns_perawatan_radiologi on jns_perawatan_radiologi.kd_jenis_prw=periksa_radiologi.kd_jenis_prw where periksa_radiologi.stts_bayar = 'Belum' and "
            + " periksa_radiologi.no_rawat=? group by periksa_radiologi.kd_jenis_prw  ",
            sqlpsnota = "insert into nota_jalan values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
            sqlcarinota = "select * from nota_jalan where no_rawat=?",
            //            sqlpsoperasi = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
            //            + "operasi.biayaoperator3+operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+"
            //            + "operasi.biayaasisten_operator3+operasi.biayainstrumen+operasi.biayadokter_anak+"
            //            + "operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+operasi.biayaasisten_anestesi+"
            //            + "operasi.biayaasisten_anestesi2+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+"
            //            + "operasi.biayaperawat_luar+operasi.biayaalat+operasi.biayasewaok+operasi.akomodasi+"
            //            + "operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
            //            + "operasi.biaya_omloop4+operasi.biaya_omloop5+operasi.biayasarpras+operasi.biaya_dokter_pjanak+"
            //            + "operasi.biaya_dokter_umum) as biaya,operasi.biayaoperator1,"
            //            + "operasi.biayaoperator2,operasi.biayaoperator3,operasi.biayaasisten_operator1,operasi.biayaasisten_operator2,operasi.biayaasisten_operator3,"
            //            + "operasi.biayainstrumen,operasi.biayadokter_anak,operasi.biayaperawaat_resusitas,"
            //            + "operasi.biayadokter_anestesi,operasi.biayaasisten_anestesi,operasi.biayaasisten_anestesi2,operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3,operasi.biayaperawat_luar,"
            //            + "operasi.biayaalat,operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,operasi.biaya_omloop4,operasi.biaya_omloop5,"
            //            + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum "
            //            + "from operasi inner join paket_operasi "
            //            + "on operasi.kode_paket=paket_operasi.kode_paket where "
            //            + "operasi.no_rawat=?",
            //            sqlpsoperasi = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
            //            + "operasi.biayaoperator3+operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+"
            //            + "operasi.biayaasisten_operator3+operasi.biayainstrumen+operasi.biayadokter_anak+"
            //            + "operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+operasi.biayaasisten_anestesi+"
            //            + "operasi.biayaasisten_anestesi2+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+"
            //            + "operasi.biayaperawat_luar+operasi.biayaalat+operasi.biayasewaok+operasi.akomodasi+"
            //            + "operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
            //            + "operasi.biaya_omloop4+operasi.biaya_omloop5+operasi.biayasarpras+operasi.biaya_dokter_pjanak+"
            //            + "operasi.biaya_dokter_umum) as biaya,operasi.biayaoperator1,"
            //            + "operasi.biayaoperator2,operasi.biayaoperator3,operasi.biayaasisten_operator1,operasi.biayaasisten_operator2,operasi.biayaasisten_operator3,"
            //            + "operasi.biayainstrumen,operasi.biayadokter_anak,operasi.biayaperawaat_resusitas,"
            //            + "operasi.biayadokter_anestesi,operasi.biayaasisten_anestesi,operasi.biayaasisten_anestesi2,operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3,operasi.biayaperawat_luar,"
            //            + "operasi.biayaalat,operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,operasi.biaya_omloop4,operasi.biaya_omloop5,"
            //            + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
            //            + " ifnull(d1.nm_dokter,'-') dokter_operator1,"
            //            + " ifnull(d2.nm_dokter,'-') dokter_operator2,"
            //            + " ifnull(d3.nm_dokter,'-') dokter_operator3,"
            //            + " ifnull(p1.nama,'-') asisten_operator1,"
            //            + " ifnull(p2.nama,'-') asisten_operator2,"
            //            + " ifnull(d8.nm_dokter,'-') asisten_operator3,"
            //            + " ifnull(p3.nama,'-') instrumen,"
            //            + " ifnull(d4.nm_dokter,'-') dokter_anak,"
            //            + " ifnull(p4.nama,'-') perawaat_resusitas,"
            //            + " ifnull(d5.nm_dokter,'-') dokter_anestesi,"
            //            + " ifnull(p5.nama,'-') asisten_anestesi,"
            //            + " ifnull(d9.nm_dokter,'-') asisten_anestesi2,"
            //            + " ifnull(p6.nama,'-') bidan,"
            //            + " ifnull(p7.nama,'-') bidan2,"
            //            + " ifnull(p8.nama,'-') bidan3,"
            //            + " ifnull(p9.nama,'-') perawat_luar,"
            //            + " ifnull(p10.nama,'-') omloop,"
            //            + " ifnull(p11.nama,'-') omloop2,"
            //            + " ifnull(p12.nama,'-') omloop3,"
            //            + " ifnull(d10.nm_dokter,'-') omloop4,"
            //            + " ifnull(d11.nm_dokter,'-') omloop5 "
            //            + "from operasi inner join paket_operasi "
            //            + "on operasi.kode_paket=paket_operasi.kode_paket"
            //            + " LEFT JOIN dokter d1 on d1.kd_dokter = operasi.operator1"
            //            + " LEFT JOIN dokter d2 on d2.kd_dokter = operasi.operator2"
            //            + " LEFT JOIN dokter d3 on d3.kd_dokter = operasi.operator3"
            //            + " LEFT JOIN dokter d4 on d4.kd_dokter = operasi.dokter_anak"
            //            + " LEFT JOIN dokter d5 on d5.kd_dokter = operasi.dokter_anestesi"
            //            + " LEFT JOIN dokter d6 on d6.kd_dokter = operasi.dokter_pjanak"
            //            + " LEFT JOIN dokter d7 on d7.kd_dokter = operasi.dokter_umum"
            //            + " LEFT JOIN dokter d8 on d8.kd_dokter = operasi.asisten_operator3"
            //            + " LEFT JOIN dokter d9 on d9.kd_dokter = operasi.asisten_anestesi2"
            //            + " LEFT JOIN dokter d10 on d10.kd_dokter = operasi.omloop4"
            //            + " LEFT JOIN dokter d11 on d11.kd_dokter = operasi.omloop5"
            //            + " LEFT JOIN petugas p1 on p1.nip = operasi.asisten_operator1"
            //            + " LEFT JOIN petugas p2 on p2.nip = operasi.asisten_operator2"
            //            + " LEFT JOIN petugas p3 on p3.nip = operasi.instrumen"
            //            + " LEFT JOIN petugas p4 on p4.nip = operasi.perawaat_resusitas"
            //            + " LEFT JOIN petugas p5 on p5.nip = operasi.asisten_anestesi"
            //            + " LEFT JOIN petugas p6 on p6.nip = operasi.bidan"
            //            + " LEFT JOIN petugas p7 on p7.nip = operasi.bidan2"
            //            + " LEFT JOIN petugas p8 on p8.nip = operasi.bidan3"
            //            + " LEFT JOIN petugas p9 on p9.nip = operasi.perawat_luar"
            //            + " LEFT JOIN petugas p10 on p10.nip = operasi.omloop"
            //            + " LEFT JOIN petugas p11 on p11.nip = operasi.omloop2"
            //            + " LEFT JOIN petugas p12 on p12.nip = operasi.omloop3"
            //            + " where operasi.no_rawat=?",
            sqlpsoperasi = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
            + "operasi.biayaoperator3+operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+"
            + "operasi.biayaasisten_operator3+operasi.biayainstrumen+operasi.biayadokter_anak+"
            + "operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+operasi.biayaasisten_anestesi+"
            + "operasi.biayaasisten_anestesi2+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+"
            + "operasi.biayaperawat_luar+operasi.biayaalat+operasi.biayasewaok+operasi.akomodasi+"
            + "operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
            + "operasi.biaya_omloop4+operasi.biaya_omloop5+operasi.biayasarpras+operasi.biaya_dokter_pjanak+"
            + "operasi.biaya_dokter_umum) as biaya,operasi.biayaoperator1,"
            + "operasi.biayaoperator2,operasi.biayaoperator3,operasi.biayaasisten_operator1,operasi.biayaasisten_operator2,operasi.biayaasisten_operator3,"
            + "operasi.biayainstrumen,operasi.biayadokter_anak,operasi.biayaperawaat_resusitas,"
            + "operasi.biayadokter_anestesi,operasi.biayaasisten_anestesi,operasi.biayaasisten_anestesi2,operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3,operasi.biayaperawat_luar,"
            + "operasi.biayaalat,operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,operasi.biaya_omloop4,operasi.biaya_omloop5,"
            + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
            + " ifnull(d1.nm_dokter,'-') dokter_operator1,"
            + " ifnull(d2.nm_dokter,'-') dokter_operator2,"
            + " ifnull(d3.nm_dokter,'-') dokter_operator3,"
            + " ifnull(p1.nama,'-') asisten_operator1,"
            + " ifnull(p2.nama,'-') asisten_operator2,"
            + " ifnull(d8.nm_dokter,'-') asisten_operator3,"
            + " ifnull(p3.nama,'-') instrumen,"
            + " ifnull(d4.nm_dokter,'-') dokter_anak,"
            + " ifnull(p4.nama,'-') perawaat_resusitas,"
            + " ifnull(d5.nm_dokter,'-') dokter_anestesi,"
            + " ifnull(p5.nama,'-') asisten_anestesi,"
            + " ifnull(d9.nm_dokter,'-') asisten_anestesi2,"
            + " ifnull(p6.nama,'-') bidan,"
            + " ifnull(p7.nama,'-') bidan2,"
            + " ifnull(p8.nama,'-') bidan3,"
            + " ifnull(p9.nama,'-') perawat_luar,"
            + " ifnull(p10.nama,'-') omloop,"
            + " ifnull(p11.nama,'-') omloop2,"
            + " ifnull(p12.nama,'-') omloop3,"
            + " ifnull(d10.nm_dokter,'-') omloop4,"
            + " ifnull(d11.nm_dokter,'-') omloop5 "
            + "from operasi inner join paket_operasi "
            + "on operasi.kode_paket=paket_operasi.kode_paket"
            + " LEFT JOIN dokter d1 on d1.kd_dokter = operasi.operator1"
            + " LEFT JOIN dokter d2 on d2.kd_dokter = operasi.operator2"
            + " LEFT JOIN dokter d3 on d3.kd_dokter = operasi.operator3"
            + " LEFT JOIN dokter d4 on d4.kd_dokter = operasi.dokter_anak"
            + " LEFT JOIN dokter d5 on d5.kd_dokter = operasi.dokter_anestesi"
            + " LEFT JOIN dokter d6 on d6.kd_dokter = operasi.dokter_pjanak"
            + " LEFT JOIN dokter d7 on d7.kd_dokter = operasi.dokter_umum"
            + " LEFT JOIN dokter d8 on d8.kd_dokter = operasi.asisten_operator3"
            + " LEFT JOIN dokter d9 on d9.kd_dokter = operasi.asisten_anestesi2"
            + " LEFT JOIN dokter d10 on d10.kd_dokter = operasi.omloop4"
            + " LEFT JOIN dokter d11 on d11.kd_dokter = operasi.omloop5"
            + " LEFT JOIN petugas p1 on p1.nip = operasi.asisten_operator1"
            + " LEFT JOIN petugas p2 on p2.nip = operasi.asisten_operator2"
            + " LEFT JOIN petugas p3 on p3.nip = operasi.instrumen"
            + " LEFT JOIN petugas p4 on p4.nip = operasi.perawaat_resusitas"
            + " LEFT JOIN petugas p5 on p5.nip = operasi.asisten_anestesi"
            + " LEFT JOIN petugas p6 on p6.nip = operasi.bidan"
            + " LEFT JOIN petugas p7 on p7.nip = operasi.bidan2"
            + " LEFT JOIN petugas p8 on p8.nip = operasi.bidan3"
            + " LEFT JOIN petugas p9 on p9.nip = operasi.perawat_luar"
            + " LEFT JOIN petugas p10 on p10.nip = operasi.omloop"
            + " LEFT JOIN petugas p11 on p11.nip = operasi.omloop2"
            + " LEFT JOIN petugas p12 on p12.nip = operasi.omloop3"
            + " where operasi.stts_bayar = 'Belum' and operasi.no_rawat=?",
            sqlpsobatoperasi = "select obatbhp_ok.nm_obat,beri_obat_operasi.hargasatuan,beri_obat_operasi.jumlah, "
            + "(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
            + "from obatbhp_ok inner join beri_obat_operasi "
            + "on beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat where "
            + "beri_obat_operasi.no_rawat=? group by obatbhp_ok.nm_obat",
            sqlpstamkur = "select biaya from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", no_nota = "", cekNota = "", nmPoli = "";
    private String[] Nama_Akun_Piutang, Kode_Rek_Piutang, Kd_PJ, Besar_Piutang, Jatuh_Tempo,
            Nama_Akun_Bayar, Kode_Rek_Bayar, Bayar, PPN_Persen, PPN_Besar;

    private PreparedStatement pscekbilling, pscarirm, pscaripasien, psreg, pscaripoli, pscarialamat, psrekening,
            psdokterralan, pscariralandokter, pscariralanperawat, pscariralandrpr, pscarilab, pscariobat, psdetaillab,
            psobatlangsung, psreturobat, pstambahan, psbiling, pstemporary, pspotongan, psbilling, pscariradiologi,
            pstamkur, psnota, psoperasi, psobatoperasi, psceknota, psakunbayar, psakunpiutang, psRujuk;
    private ResultSet rscekbilling, rscarirm, rscaripasien, rsreg, rscaripoli, rscarialamat, rsrekening, rsobatoperasi,
            rsdokterralan, rscariralandokter, rscariralanperawat, rscariralandrpr, rscarilab, rscariobat, rsdetaillab,
            rsobatlangsung, rsreturobat, rstambahan, rspotongan, rsbilling, rscariradiologi, rstamkur, rsoperasi, rsceknota,
            rsakunbayar, rsakunpiutang, rsRujuk;
    private WarnaTable2 warna = new WarnaTable2();
    private WarnaTable2 warna2 = new WarnaTable2();

    /**
     * Creates new form DlgBiling
     *
     * @param parent
     * @param modal
     */
    public DlgBilingRalan12(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Object[] rowRwJlDr = {"Pilih", "Keterangan", "Tagihan/Tindakan/Terapi", "", "Biaya", "Jml", "Tambahan", "Total Biaya", ""};
        tabModeRwJlDr = new DefaultTableModel(null, rowRwJlDr) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 6) || (colIndex == 0)) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbBilling.setModel(tabModeRwJlDr);

        //tbPetugas.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPetugas.getBackground()));
        tbBilling.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBilling.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbBilling.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(160);
            } else if (i == 2) {
                column.setPreferredWidth(420);
            } else if (i == 3) {
                column.setPreferredWidth(10);
            } else if (i == 4) {
                column.setPreferredWidth(95);
            } else if (i == 5) {
                column.setPreferredWidth(30);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbBilling.setDefaultRenderer(Object.class, new WarnaTable());

        //tambahan biaya
        tabModeTambahan = new DefaultTableModel(null, new Object[]{"Tambahan Biaya", "Besar Biaya"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return true;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTambahan.setModel(tabModeTambahan);

        tbTambahan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbTambahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbTambahan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            }
        }
        tbTambahan.setDefaultRenderer(Object.class, new WarnaTable());

        //potongan biaya
        Object[] rowPotongan = {"Potongan Biaya", "Besar Potongan"};
        tabModePotongan = new DefaultTableModel(null, rowPotongan) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return true;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPotongan.setModel(tabModePotongan);

        tbPotongan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPotongan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbPotongan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            }
        }
        tbPotongan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeAkunBayar = new DefaultTableModel(null, new Object[]{"Nama Akun", "Kode Rek", "Bayar", "PPN(%)", "PPN(Rp)"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAkunBayar.setModel(tabModeAkunBayar);

        tbAkunBayar.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbAkunBayar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbAkunBayar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(345);
            } else if (i == 1) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(112);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            }
        }
        warna.kolom = 2;
        tbAkunBayar.setDefaultRenderer(Object.class, warna);

        tabModeAkunPiutang = new DefaultTableModel(null, new Object[]{"Nama Akun", "Kode Rek", "Kd PJ", "Piutang", "Jatuh Tempo"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 3) || (colIndex == 4)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAkunPiutang.setModel(tabModeAkunPiutang);

        tbAkunPiutang.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbAkunPiutang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbAkunPiutang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(405);
            } else if (i == 1) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(112);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            }
        }
        warna2.kolom = 3;
        tbAkunPiutang.setDefaultRenderer(Object.class, warna2);

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        kdpoli.setDocument(new batasInput((byte) 5).getKata(kdpoli));
        kddokter.setDocument(new batasInput((byte) 20).getKata(kddokter));

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBilingRalan")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    btnCariDokter.requestFocus();
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
                if (akses.getform().equals("DlgBilingRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        nmpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                    }
                    btnCariPoli.requestFocus();
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

        dlgrwjl.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBilingRalan")) {
                    isRawat();
                    isKembali();
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

        dlgobt.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBilingRalan")) {
                    isRawat();
                    isKembali();
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

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBilingRalan")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpenjab.requestFocus();
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

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgBilingRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            notaralan = Sequel.cariIsi("select cetaknotasimpanralan from set_nota");
            centangdokterralan = Sequel.cariIsi("select centangdokterralan from set_nota");
            rinciandokterralan = Sequel.cariIsi("select rinciandokterralan from set_nota");
            rincianoperasi = Sequel.cariIsi("select rincianoperasi from set_nota");
            tampilkan_ppnobat_ralan = Sequel.cariIsi("select tampilkan_ppnobat_ralan from set_nota");
            centangobatralan = Sequel.cariIsi("select centangobatralan from set_nota");
        } catch (Exception e) {
            notaralan = "No";
            centangdokterralan = "No";
            rinciandokterralan = "No";
            rincianoperasi = "No";
            tampilkan_ppnobat_ralan = "No";
            centangobatralan = "No";
        }

        try {
            psrekening = koneksi.prepareStatement(sqlpsrekening);
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Tindakan_Ralan = rsrekening.getString("Tindakan_Ralan");
                    Laborat_Ralan = rsrekening.getString("Laborat_Ralan");
                    Radiologi_Ralan = rsrekening.getString("Radiologi_Ralan");
                    Obat_Ralan = rsrekening.getString("Obat_Ralan");
                    Registrasi_Ralan = rsrekening.getString("Registrasi_Ralan");
                    Tambahan_Ralan = rsrekening.getString("Tambahan_Ralan");
                    Potongan_Ralan = rsrekening.getString("Potongan_Ralan");
                    Operasi_Ralan = rsrekening.getString("Operasi_Ralan");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ralan = rsrekening.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ralan = rsrekening.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Beban_Jasa_Medik_Paramedis_Tindakan_Ralan = rsrekening.getString("Beban_Jasa_Medik_Paramedis_Tindakan_Ralan");
                    Utang_Jasa_Medik_Paramedis_Tindakan_Ralan = rsrekening.getString("Utang_Jasa_Medik_Paramedis_Tindakan_Ralan");
                    Beban_KSO_Tindakan_Ralan = rsrekening.getString("Beban_KSO_Tindakan_Ralan");
                    Utang_KSO_Tindakan_Ralan = rsrekening.getString("Utang_KSO_Tindakan_Ralan");
                    Beban_Jasa_Medik_Dokter_Laborat_Ralan = rsrekening.getString("Beban_Jasa_Medik_Dokter_Laborat_Ralan");
                    Utang_Jasa_Medik_Dokter_Laborat_Ralan = rsrekening.getString("Utang_Jasa_Medik_Dokter_Laborat_Ralan");
                    Beban_Jasa_Medik_Petugas_Laborat_Ralan = rsrekening.getString("Beban_Jasa_Medik_Petugas_Laborat_Ralan");
                    Utang_Jasa_Medik_Petugas_Laborat_Ralan = rsrekening.getString("Utang_Jasa_Medik_Petugas_Laborat_Ralan");
                    Beban_Kso_Laborat_Ralan = rsrekening.getString("Beban_Kso_Laborat_Ralan");
                    Utang_Kso_Laborat_Ralan = rsrekening.getString("Utang_Kso_Laborat_Ralan");
                    HPP_Persediaan_Laborat_Rawat_Jalan = rsrekening.getString("HPP_Persediaan_Laborat_Rawat_Jalan");
                    Persediaan_BHP_Laborat_Rawat_Jalan = rsrekening.getString("Persediaan_BHP_Laborat_Rawat_Jalan");
                    Beban_Jasa_Medik_Dokter_Radiologi_Ralan = rsrekening.getString("Beban_Jasa_Medik_Dokter_Radiologi_Ralan");
                    Utang_Jasa_Medik_Dokter_Radiologi_Ralan = rsrekening.getString("Utang_Jasa_Medik_Dokter_Radiologi_Ralan");
                    Beban_Jasa_Medik_Petugas_Radiologi_Ralan = rsrekening.getString("Beban_Jasa_Medik_Petugas_Radiologi_Ralan");
                    Utang_Jasa_Medik_Petugas_Radiologi_Ralan = rsrekening.getString("Utang_Jasa_Medik_Petugas_Radiologi_Ralan");
                    Beban_Kso_Radiologi_Ralan = rsrekening.getString("Beban_Kso_Radiologi_Ralan");
                    Utang_Kso_Radiologi_Ralan = rsrekening.getString("Utang_Kso_Radiologi_Ralan");
                    HPP_Persediaan_Radiologi_Rawat_Jalan = rsrekening.getString("HPP_Persediaan_Radiologi_Rawat_Jalan");
                    Persediaan_BHP_Radiologi_Rawat_Jalan = rsrekening.getString("Persediaan_BHP_Radiologi_Rawat_Jalan");
                    HPP_Obat_Rawat_Jalan = rsrekening.getString("HPP_Obat_Rawat_Jalan");
                    Persediaan_Obat_Rawat_Jalan = rsrekening.getString("Persediaan_Obat_Rawat_Jalan");
                    Beban_Jasa_Medik_Dokter_Operasi_Ralan = rsrekening.getString("Beban_Jasa_Medik_Dokter_Operasi_Ralan");
                    Utang_Jasa_Medik_Dokter_Operasi_Ralan = rsrekening.getString("Utang_Jasa_Medik_Dokter_Operasi_Ralan");
                    Beban_Jasa_Medik_Paramedis_Operasi_Ralan = rsrekening.getString("Beban_Jasa_Medik_Paramedis_Operasi_Ralan");
                    Utang_Jasa_Medik_Paramedis_Operasi_Ralan = rsrekening.getString("Utang_Jasa_Medik_Paramedis_Operasi_Ralan");
                    HPP_Obat_Operasi_Ralan = rsrekening.getString("HPP_Obat_Operasi_Ralan");
                    Persediaan_Obat_Kamar_Operasi_Ralan = rsrekening.getString("Persediaan_Obat_Kamar_Operasi_Ralan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnRawatJalan = new javax.swing.JMenuItem();
        MnInputObat = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnTambahan = new javax.swing.JMenuItem();
        MnPotongan = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MnObatLangsung = new javax.swing.JMenuItem();
        MnGanti = new javax.swing.JMenu();
        MnNamaTelahTerima = new javax.swing.JMenuItem();
        MnPoli = new javax.swing.JMenuItem();
        MnDokter = new javax.swing.JMenuItem();
        MnPenjab = new javax.swing.JMenuItem();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnCariPeriksaLab = new javax.swing.JMenuItem();
        MnCariRadiologi = new javax.swing.JMenuItem();
        MnPenjualan = new javax.swing.JMenuItem();
        MnHapusTagihan = new javax.swing.JMenuItem();
        WindowGantiDokterPoli = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel13 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnCariDokter = new widget.Button();
        WindowObatLangsung = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        TotalObat = new widget.TextBox();
        jLabel8 = new widget.Label();
        BtnCloseIn = new widget.Button();
        BtnSimpan2 = new widget.Button();
        BtnBatal1 = new widget.Button();
        WindowTambahanBiaya = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbTambahan = new widget.Table();
        panelisi1 = new widget.panelisi();
        label15 = new widget.Label();
        norawat = new widget.TextBox();
        BtnTambah = new widget.Button();
        BtnSimpan3 = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar1 = new widget.Button();
        WindowGantiPoli = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel14 = new widget.Label();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        btnCariPoli = new widget.Button();
        WindowPotonganBiaya = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbPotongan = new widget.Table();
        panelisi2 = new widget.panelisi();
        label16 = new widget.Label();
        norawatpotongan = new widget.TextBox();
        BtnTambahPotongan = new widget.Button();
        BtnSimpanPotongan = new widget.Button();
        BtnHapusPotongan = new widget.Button();
        BtnKeluarPotongan = new widget.Button();
        WindowGantiPenjab = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel17 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnPenjab = new widget.Button();
        WindowTelahTerimaKwitansi = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel19 = new widget.Label();
        nm_telah_terima = new widget.TextBox();
        PopupBayar = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        PopupPiutang = new javax.swing.JPopupMenu();
        ppBersihkan1 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass1 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel7 = new widget.Label();
        TNoNota = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel4 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        chkBayar = new widget.CekBox();
        TCekNota = new widget.TextBox();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbBilling = new widget.Table();
        panelBayar = new widget.panelisi();
        TtlSemua = new widget.TextBox();
        TKembali = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel9 = new widget.Label();
        chkPotongan = new widget.CekBox();
        chkLaborat = new widget.CekBox();
        chkTarifDokter = new widget.CekBox();
        chkTarifPrm = new widget.CekBox();
        chkTambahan = new widget.CekBox();
        chkObat = new widget.CekBox();
        chkRadiologi = new widget.CekBox();
        jLabel12 = new widget.Label();
        TagihanPPn = new widget.TextBox();
        chkAdministrasi = new widget.CekBox();
        chkSarpras = new widget.CekBox();
        scrollPane3 = new widget.ScrollPane();
        tbAkunBayar = new widget.Table();
        jLabel6 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        tbAkunPiutang = new widget.Table();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCariBayar = new widget.Button();
        TCari1 = new widget.TextBox();
        btnCariPiutang = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnNota = new widget.Button();
        BtnView = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRawatJalan.setBackground(new java.awt.Color(255, 255, 255));
        MnRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan.setText("Data Tagihan/Tindakan Rawat Jalan");
        MnRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan.setIconTextGap(5);
        MnRawatJalan.setName("MnRawatJalan"); // NOI18N
        MnRawatJalan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnRawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRawatJalan);

        MnInputObat.setBackground(new java.awt.Color(255, 255, 255));
        MnInputObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputObat.setText("Input Obat/Barang/Alkes");
        MnInputObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputObat.setIconTextGap(5);
        MnInputObat.setName("MnInputObat"); // NOI18N
        MnInputObat.setPreferredSize(new java.awt.Dimension(250, 25));
        MnInputObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputObat);

        MnPeriksaLab.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Input Periksa Lab");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPeriksaLab);

        MnPeriksaRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Input Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPeriksaRadiologi);

        MnTambahan.setBackground(new java.awt.Color(255, 255, 255));
        MnTambahan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTambahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTambahan.setText("Tambahan Biaya");
        MnTambahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTambahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTambahan.setIconTextGap(5);
        MnTambahan.setName("MnTambahan"); // NOI18N
        MnTambahan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnTambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTambahanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTambahan);

        MnPotongan.setBackground(new java.awt.Color(255, 255, 255));
        MnPotongan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPotongan.setText("Potongan Biaya");
        MnPotongan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPotongan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPotongan.setIconTextGap(5);
        MnPotongan.setName("MnPotongan"); // NOI18N
        MnPotongan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPotonganActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPotongan);

        MnOperasi.setBackground(new java.awt.Color(255, 255, 255));
        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi.setText("Tagihan Operasi/VK");
        MnOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi.setIconTextGap(5);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnOperasi);

        MnObatLangsung.setBackground(new java.awt.Color(255, 255, 255));
        MnObatLangsung.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObatLangsung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObatLangsung.setText("Tagihan BHP & Obat");
        MnObatLangsung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObatLangsung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObatLangsung.setIconTextGap(5);
        MnObatLangsung.setName("MnObatLangsung"); // NOI18N
        MnObatLangsung.setPreferredSize(new java.awt.Dimension(250, 25));
        MnObatLangsung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObatLangsungActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnObatLangsung);

        MnGanti.setBackground(new java.awt.Color(255, 255, 255));
        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGanti.setText("Ganti Data");
        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGanti.setIconTextGap(5);
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setPreferredSize(new java.awt.Dimension(250, 28));

        MnNamaTelahTerima.setBackground(new java.awt.Color(255, 255, 255));
        MnNamaTelahTerima.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNamaTelahTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNamaTelahTerima.setText("Nama Org. Telah Terima Di Kwitansi");
        MnNamaTelahTerima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNamaTelahTerima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNamaTelahTerima.setIconTextGap(5);
        MnNamaTelahTerima.setName("MnNamaTelahTerima"); // NOI18N
        MnNamaTelahTerima.setPreferredSize(new java.awt.Dimension(230, 28));
        MnNamaTelahTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNamaTelahTerimaActionPerformed(evt);
            }
        });
        MnGanti.add(MnNamaTelahTerima);

        MnPoli.setBackground(new java.awt.Color(255, 255, 255));
        MnPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoli.setText("Ganti Poliklinik");
        MnPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoli.setIconTextGap(5);
        MnPoli.setName("MnPoli"); // NOI18N
        MnPoli.setPreferredSize(new java.awt.Dimension(230, 28));
        MnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliActionPerformed(evt);
            }
        });
        MnGanti.add(MnPoli);

        MnDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokter.setText("Ganti Dokter Poli");
        MnDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokter.setIconTextGap(5);
        MnDokter.setName("MnDokter"); // NOI18N
        MnDokter.setPreferredSize(new java.awt.Dimension(230, 28));
        MnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterActionPerformed(evt);
            }
        });
        MnGanti.add(MnDokter);

        MnPenjab.setBackground(new java.awt.Color(255, 255, 255));
        MnPenjab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjab.setText("Ganti Jenis Bayar");
        MnPenjab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjab.setIconTextGap(5);
        MnPenjab.setName("MnPenjab"); // NOI18N
        MnPenjab.setPreferredSize(new java.awt.Dimension(230, 28));
        MnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjabActionPerformed(evt);
            }
        });
        MnGanti.add(MnPenjab);

        jPopupMenu1.add(MnGanti);

        MnPemberianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Data Pemberian Obat");
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(250, 25));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPemberianObat);

        MnCariPeriksaLab.setBackground(new java.awt.Color(255, 255, 255));
        MnCariPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPeriksaLab.setText("Data Pemeriksaan Lab");
        MnCariPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPeriksaLab.setIconTextGap(5);
        MnCariPeriksaLab.setName("MnCariPeriksaLab"); // NOI18N
        MnCariPeriksaLab.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCariPeriksaLab);

        MnCariRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnCariRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariRadiologi.setText("Data Pemeriksaan Radiologi");
        MnCariRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariRadiologi.setIconTextGap(5);
        MnCariRadiologi.setName("MnCariRadiologi"); // NOI18N
        MnCariRadiologi.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariRadiologiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCariRadiologi);

        MnPenjualan.setBackground(new java.awt.Color(255, 255, 255));
        MnPenjualan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjualan.setText("Penjualan Obat/Alkes/Barang");
        MnPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjualan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjualan.setIconTextGap(5);
        MnPenjualan.setName("MnPenjualan"); // NOI18N
        MnPenjualan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjualanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenjualan);

        MnHapusTagihan.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusTagihan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusTagihan.setText("Hapus Nota Salah");
        MnHapusTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTagihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTagihan.setIconTextGap(5);
        MnHapusTagihan.setName("MnHapusTagihan"); // NOI18N
        MnHapusTagihan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnHapusTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTagihanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTagihan);

        WindowGantiDokterPoli.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokterPoli.setName("WindowGantiDokterPoli"); // NOI18N
        WindowGantiDokterPoli.setUndecorated(true);
        WindowGantiDokterPoli.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Poli ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame3.setLayout(null);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        BtnCloseIn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn1KeyPressed(evt);
            }
        });
        internalFrame3.add(BtnCloseIn1);
        BtnCloseIn1.setBounds(510, 30, 100, 30);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        internalFrame3.add(BtnSimpan1);
        BtnSimpan1.setBounds(405, 30, 100, 30);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dr Dituju :");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame3.add(jLabel13);
        jLabel13.setBounds(0, 32, 77, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        internalFrame3.add(kddokter);
        kddokter.setBounds(81, 32, 80, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        TDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDokterKeyPressed(evt);
            }
        });
        internalFrame3.add(TDokter);
        TDokter.setBounds(164, 32, 200, 23);

        btnCariDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariDokter.setMnemonic('7');
        btnCariDokter.setToolTipText("ALt+7");
        btnCariDokter.setName("btnCariDokter"); // NOI18N
        btnCariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokterActionPerformed(evt);
            }
        });
        internalFrame3.add(btnCariDokter);
        btnCariDokter.setBounds(366, 32, 28, 23);

        WindowGantiDokterPoli.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowObatLangsung.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowObatLangsung.setName("WindowObatLangsung"); // NOI18N
        WindowObatLangsung.setUndecorated(true);
        WindowObatLangsung.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Total BHP & Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame2.setLayout(null);

        TotalObat.setForeground(new java.awt.Color(0, 0, 0));
        TotalObat.setHighlighter(null);
        TotalObat.setName("TotalObat"); // NOI18N
        TotalObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalObatKeyPressed(evt);
            }
        });
        internalFrame2.add(TotalObat);
        TotalObat.setBounds(60, 30, 180, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Total :");
        jLabel8.setName("jLabel8"); // NOI18N
        internalFrame2.add(jLabel8);
        jLabel8.setBounds(0, 30, 57, 23);

        BtnCloseIn.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn.setMnemonic('U');
        BtnCloseIn.setText("Tutup");
        BtnCloseIn.setToolTipText("Alt+U");
        BtnCloseIn.setName("BtnCloseIn"); // NOI18N
        BtnCloseIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInActionPerformed(evt);
            }
        });
        BtnCloseIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseInKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnCloseIn);
        BtnCloseIn.setBounds(465, 30, 100, 30);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        internalFrame2.add(BtnSimpan2);
        BtnSimpan2.setBounds(255, 30, 100, 30);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnBatal1.setMnemonic('H');
        BtnBatal1.setText("Hapus");
        BtnBatal1.setToolTipText("Alt+H");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
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
        internalFrame2.add(BtnBatal1);
        BtnBatal1.setBounds(360, 30, 100, 30);

        WindowObatLangsung.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        WindowTambahanBiaya.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTambahanBiaya.setName("WindowTambahanBiaya"); // NOI18N
        WindowTambahanBiaya.setUndecorated(true);
        WindowTambahanBiaya.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Tambah Biaya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbTambahan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTambahan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTambahan.setName("tbTambahan"); // NOI18N
        scrollPane1.setViewportView(tbTambahan);

        internalFrame4.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("No.Rawat :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(label15);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        norawat.setPreferredSize(new java.awt.Dimension(150, 23));
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        panelisi1.add(norawat);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnTambah.setMnemonic('T');
        BtnTambah.setText("Tambah");
        BtnTambah.setToolTipText("Alt+T");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi1.add(BtnTambah);

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnSimpan3);

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
        panelisi1.add(BtnHapus);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar1.setMnemonic('U');
        BtnKeluar1.setText("Tutup");
        BtnKeluar1.setToolTipText("Alt+U");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);

        internalFrame4.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        WindowTambahanBiaya.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowGantiPoli.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPoli.setName("WindowGantiPoli"); // NOI18N
        WindowGantiPoli.setUndecorated(true);
        WindowGantiPoli.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Poliklinik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(510, 30, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(405, 30, 100, 30);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Poli Dituju :");
        jLabel14.setName("jLabel14"); // NOI18N
        internalFrame5.add(jLabel14);
        jLabel14.setBounds(0, 32, 77, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        internalFrame5.add(kdpoli);
        kdpoli.setBounds(81, 32, 60, 23);

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setName("nmpoli"); // NOI18N
        internalFrame5.add(nmpoli);
        nmpoli.setBounds(144, 32, 220, 23);

        btnCariPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariPoli.setMnemonic('7');
        btnCariPoli.setToolTipText("ALt+7");
        btnCariPoli.setName("btnCariPoli"); // NOI18N
        btnCariPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPoliActionPerformed(evt);
            }
        });
        internalFrame5.add(btnCariPoli);
        btnCariPoli.setBounds(366, 32, 28, 23);

        WindowGantiPoli.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowPotonganBiaya.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPotonganBiaya.setName("WindowPotonganBiaya"); // NOI18N
        WindowPotonganBiaya.setUndecorated(true);
        WindowPotonganBiaya.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Potongan Biaya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbPotongan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPotongan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPotongan.setName("tbPotongan"); // NOI18N
        scrollPane2.setViewportView(tbPotongan);

        internalFrame6.add(scrollPane2, java.awt.BorderLayout.CENTER);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("No.Rawat :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(label16);

        norawatpotongan.setEditable(false);
        norawatpotongan.setForeground(new java.awt.Color(0, 0, 0));
        norawatpotongan.setName("norawatpotongan"); // NOI18N
        norawatpotongan.setPreferredSize(new java.awt.Dimension(150, 23));
        norawatpotongan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatpotonganKeyPressed(evt);
            }
        });
        panelisi2.add(norawatpotongan);

        BtnTambahPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnTambahPotongan.setMnemonic('T');
        BtnTambahPotongan.setText("Tambah");
        BtnTambahPotongan.setToolTipText("Alt+T");
        BtnTambahPotongan.setName("BtnTambahPotongan"); // NOI18N
        BtnTambahPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTambahPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnTambahPotongan);

        BtnSimpanPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanPotongan.setMnemonic('S');
        BtnSimpanPotongan.setText("Simpan");
        BtnSimpanPotongan.setToolTipText("Alt+S");
        BtnSimpanPotongan.setName("BtnSimpanPotongan"); // NOI18N
        BtnSimpanPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnSimpanPotongan);

        BtnHapusPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusPotongan.setMnemonic('H');
        BtnHapusPotongan.setText("Hapus");
        BtnHapusPotongan.setToolTipText("Alt+H");
        BtnHapusPotongan.setName("BtnHapusPotongan"); // NOI18N
        BtnHapusPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnHapusPotongan);

        BtnKeluarPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluarPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluarPotongan.setMnemonic('U');
        BtnKeluarPotongan.setText("Tutup");
        BtnKeluarPotongan.setToolTipText("Alt+U");
        BtnKeluarPotongan.setName("BtnKeluarPotongan"); // NOI18N
        BtnKeluarPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnKeluarPotongan);

        internalFrame6.add(panelisi2, java.awt.BorderLayout.PAGE_END);

        WindowPotonganBiaya.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowGantiPenjab.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPenjab.setName("WindowGantiPenjab"); // NOI18N
        WindowGantiPenjab.setUndecorated(true);
        WindowGantiPenjab.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jenis Bayar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('P');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+P");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(510, 30, 100, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan5);
        BtnSimpan5.setBounds(405, 30, 100, 30);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bayar :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame7.add(jLabel17);
        jLabel17.setBounds(0, 32, 77, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        internalFrame7.add(kdpenjab);
        kdpenjab.setBounds(81, 32, 80, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        internalFrame7.add(nmpenjab);
        nmpenjab.setBounds(164, 32, 200, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('7');
        btnPenjab.setToolTipText("ALt+7");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        internalFrame7.add(btnPenjab);
        btnPenjab.setBounds(366, 32, 28, 23);

        WindowGantiPenjab.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowTelahTerimaKwitansi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTelahTerimaKwitansi.setName("WindowTelahTerimaKwitansi"); // NOI18N
        WindowTelahTerimaKwitansi.setUndecorated(true);
        WindowTelahTerimaKwitansi.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Nama Orang Telah Terima Di Kwitansi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame8.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
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
        BtnCloseIn6.setBounds(620, 30, 100, 30);

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
        internalFrame8.add(BtnSimpan6);
        BtnSimpan6.setBounds(510, 30, 100, 30);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Nama Org. Sebagai Telah Terima : ");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame8.add(jLabel19);
        jLabel19.setBounds(0, 32, 180, 23);

        nm_telah_terima.setForeground(new java.awt.Color(0, 0, 0));
        nm_telah_terima.setName("nm_telah_terima"); // NOI18N
        internalFrame8.add(nm_telah_terima);
        nm_telah_terima.setBounds(183, 32, 320, 23);

        WindowTelahTerimaKwitansi.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        PopupBayar.setName("PopupBayar"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 255));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pembayaran");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setIconTextGap(8);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        PopupBayar.add(ppBersihkan);

        PopupPiutang.setName("PopupPiutang"); // NOI18N

        ppBersihkan1.setBackground(new java.awt.Color(255, 255, 255));
        ppBersihkan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan1.setText("Bersihkan Piutang");
        ppBersihkan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan1.setIconTextGap(8);
        ppBersihkan1.setName("ppBersihkan1"); // NOI18N
        ppBersihkan1.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkan1ActionPerformed(evt);
            }
        });
        PopupPiutang.add(ppBersihkan1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Billing/Pembayaran Ralan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass1.setPreferredSize(new java.awt.Dimension(100, 45));
        panelGlass1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 10));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass1.add(jLabel3);

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(135, 23));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        panelGlass1.add(TNoRw);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass1.add(TNoRM);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(320, 23));
        panelGlass1.add(TPasien);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. Nota :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass1.add(jLabel7);

        TNoNota.setForeground(new java.awt.Color(0, 0, 0));
        TNoNota.setHighlighter(null);
        TNoNota.setName("TNoNota"); // NOI18N
        TNoNota.setPreferredSize(new java.awt.Dimension(150, 23));
        TNoNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoNotaKeyPressed(evt);
            }
        });
        panelGlass1.add(TNoNota);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('R');
        BtnCari.setToolTipText("Alt+R");
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
        panelGlass1.add(BtnCari);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass1.add(jLabel4);

        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-03-2021 01:13:00" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(140, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        panelGlass1.add(DTPTgl);

        chkBayar.setForeground(new java.awt.Color(0, 0, 0));
        chkBayar.setText("Sudah Bayar");
        chkBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayar.setName("chkBayar"); // NOI18N
        chkBayar.setOpaque(false);
        chkBayar.setPreferredSize(new java.awt.Dimension(200, 16));
        chkBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBayarActionPerformed(evt);
            }
        });
        panelGlass1.add(chkBayar);

        TCekNota.setForeground(new java.awt.Color(0, 0, 0));
        TCekNota.setHighlighter(null);
        TCekNota.setName("TCekNota"); // NOI18N
        TCekNota.setPreferredSize(new java.awt.Dimension(0, 0));
        TCekNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCekNotaActionPerformed(evt);
            }
        });
        TCekNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCekNotaKeyPressed(evt);
            }
        });
        panelGlass1.add(TCekNota);

        internalFrame1.add(panelGlass1, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBilling.setToolTipText("");
        tbBilling.setComponentPopupMenu(jPopupMenu1);
        tbBilling.setName("tbBilling"); // NOI18N
        tbBilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBillingMouseClicked(evt);
            }
        });
        tbBilling.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBillingKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBilling);

        TabRawat.addTab(".: Data Tagihan ", Scroll);

        panelBayar.setForeground(new java.awt.Color(153, 0, 51));
        panelBayar.setPreferredSize(new java.awt.Dimension(100, 137));
        panelBayar.setLayout(null);

        TtlSemua.setEditable(false);
        TtlSemua.setForeground(new java.awt.Color(0, 0, 0));
        TtlSemua.setText("0");
        TtlSemua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TtlSemua.setHighlighter(null);
        TtlSemua.setName("TtlSemua"); // NOI18N
        TtlSemua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtlSemuaKeyPressed(evt);
            }
        });
        panelBayar.add(TtlSemua);
        TtlSemua.setBounds(110, 37, 230, 23);

        TKembali.setEditable(false);
        TKembali.setForeground(new java.awt.Color(0, 0, 0));
        TKembali.setText("0");
        TKembali.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TKembali.setHighlighter(null);
        TKembali.setName("TKembali"); // NOI18N
        panelBayar.add(TKembali);
        TKembali.setBounds(110, 377, 230, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Bayar : Rp.");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel5);
        jLabel5.setBounds(20, 67, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Total Tagihan : Rp.");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel9);
        jLabel9.setBounds(0, 37, 110, 23);

        chkPotongan.setForeground(new java.awt.Color(0, 0, 0));
        chkPotongan.setSelected(true);
        chkPotongan.setText("Potongan");
        chkPotongan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPotongan.setName("chkPotongan"); // NOI18N
        chkPotongan.setOpaque(false);
        chkPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPotonganActionPerformed(evt);
            }
        });
        panelBayar.add(chkPotongan);
        chkPotongan.setBounds(395, 8, 90, 23);

        chkLaborat.setForeground(new java.awt.Color(0, 0, 0));
        chkLaborat.setSelected(true);
        chkLaborat.setText("Laboratorium");
        chkLaborat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLaborat.setName("chkLaborat"); // NOI18N
        chkLaborat.setOpaque(false);
        chkLaborat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLaboratActionPerformed(evt);
            }
        });
        panelBayar.add(chkLaborat);
        chkLaborat.setBounds(15, 8, 90, 23);

        chkTarifDokter.setForeground(new java.awt.Color(0, 0, 0));
        chkTarifDokter.setSelected(true);
        chkTarifDokter.setText("Tarif Dokter");
        chkTarifDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTarifDokter.setName("chkTarifDokter"); // NOI18N
        chkTarifDokter.setOpaque(false);
        chkTarifDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarifDokterActionPerformed(evt);
            }
        });
        panelBayar.add(chkTarifDokter);
        chkTarifDokter.setBounds(205, 8, 90, 23);

        chkTarifPrm.setForeground(new java.awt.Color(0, 0, 0));
        chkTarifPrm.setSelected(true);
        chkTarifPrm.setText("Tarif Paramedis");
        chkTarifPrm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTarifPrm.setName("chkTarifPrm"); // NOI18N
        chkTarifPrm.setOpaque(false);
        chkTarifPrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTarifPrmActionPerformed(evt);
            }
        });
        panelBayar.add(chkTarifPrm);
        chkTarifPrm.setBounds(585, 8, 120, 23);

        chkTambahan.setForeground(new java.awt.Color(0, 0, 0));
        chkTambahan.setSelected(true);
        chkTambahan.setText("Tambahan");
        chkTambahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTambahan.setName("chkTambahan"); // NOI18N
        chkTambahan.setOpaque(false);
        chkTambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTambahanActionPerformed(evt);
            }
        });
        panelBayar.add(chkTambahan);
        chkTambahan.setBounds(300, 8, 90, 23);

        chkObat.setForeground(new java.awt.Color(0, 0, 0));
        chkObat.setSelected(true);
        chkObat.setText("Obat");
        chkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObat.setName("chkObat"); // NOI18N
        chkObat.setOpaque(false);
        chkObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkObatActionPerformed(evt);
            }
        });
        panelBayar.add(chkObat);
        chkObat.setBounds(490, 8, 90, 23);

        chkRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        chkRadiologi.setSelected(true);
        chkRadiologi.setText("Radiologi");
        chkRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRadiologi.setName("chkRadiologi"); // NOI18N
        chkRadiologi.setOpaque(false);
        chkRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRadiologiActionPerformed(evt);
            }
        });
        panelBayar.add(chkRadiologi);
        chkRadiologi.setBounds(110, 8, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tagihan + PPN : Rp.");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel12);
        jLabel12.setBounds(532, 37, 110, 23);

        TagihanPPn.setEditable(false);
        TagihanPPn.setForeground(new java.awt.Color(0, 0, 0));
        TagihanPPn.setText("0");
        TagihanPPn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TagihanPPn.setHighlighter(null);
        TagihanPPn.setName("TagihanPPn"); // NOI18N
        panelBayar.add(TagihanPPn);
        TagihanPPn.setBounds(642, 37, 230, 23);

        chkAdministrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkAdministrasi.setSelected(true);
        chkAdministrasi.setText("Administrasi");
        chkAdministrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAdministrasi.setName("chkAdministrasi"); // NOI18N
        chkAdministrasi.setOpaque(false);
        chkAdministrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdministrasiActionPerformed(evt);
            }
        });
        panelBayar.add(chkAdministrasi);
        chkAdministrasi.setBounds(710, 8, 90, 23);

        chkSarpras.setForeground(new java.awt.Color(0, 0, 0));
        chkSarpras.setSelected(true);
        chkSarpras.setText("Sarpras");
        chkSarpras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSarpras.setName("chkSarpras"); // NOI18N
        chkSarpras.setOpaque(false);
        chkSarpras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSarprasActionPerformed(evt);
            }
        });
        panelBayar.add(chkSarpras);
        chkSarpras.setBounds(805, 8, 90, 23);

        scrollPane3.setComponentPopupMenu(PopupBayar);
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbAkunBayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAkunBayar.setToolTipText("");
        tbAkunBayar.setComponentPopupMenu(PopupBayar);
        tbAkunBayar.setName("tbAkunBayar"); // NOI18N
        tbAkunBayar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbAkunBayarPropertyChange(evt);
            }
        });
        tbAkunBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAkunBayarKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(tbAkunBayar);

        panelBayar.add(scrollPane3);
        scrollPane3.setBounds(110, 92, 790, 125);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kembali : Rp.");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel6);
        jLabel6.setBounds(20, 377, 90, 23);

        scrollPane4.setComponentPopupMenu(PopupPiutang);
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbAkunPiutang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAkunPiutang.setToolTipText("");
        tbAkunPiutang.setComponentPopupMenu(PopupPiutang);
        tbAkunPiutang.setName("tbAkunPiutang"); // NOI18N
        tbAkunPiutang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbAkunPiutangPropertyChange(evt);
            }
        });
        tbAkunPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAkunPiutangKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(tbAkunPiutang);

        panelBayar.add(scrollPane4);
        scrollPane4.setBounds(110, 247, 790, 125);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Piutang : Rp.");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel16);
        jLabel16.setBounds(20, 222, 90, 23);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelBayar.add(TCari);
        TCari.setBounds(110, 67, 762, 23);

        BtnCariBayar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariBayar.setMnemonic('3');
        BtnCariBayar.setToolTipText("Alt+3");
        BtnCariBayar.setName("BtnCariBayar"); // NOI18N
        BtnCariBayar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariBayarActionPerformed(evt);
            }
        });
        BtnCariBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariBayarKeyPressed(evt);
            }
        });
        panelBayar.add(BtnCariBayar);
        BtnCariBayar.setBounds(875, 67, 25, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelBayar.add(TCari1);
        TCari1.setBounds(110, 222, 762, 23);

        btnCariPiutang.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariPiutang.setMnemonic('3');
        btnCariPiutang.setToolTipText("Alt+3");
        btnCariPiutang.setName("btnCariPiutang"); // NOI18N
        btnCariPiutang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPiutangActionPerformed(evt);
            }
        });
        btnCariPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariPiutangKeyPressed(evt);
            }
        });
        panelBayar.add(btnCariPiutang);
        btnCariPiutang.setBounds(875, 222, 25, 23);

        TabRawat.addTab(".: Pembayaran ", panelBayar);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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

        BtnNota.setForeground(new java.awt.Color(0, 0, 0));
        BtnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png"))); // NOI18N
        BtnNota.setMnemonic('N');
        BtnNota.setText(" Nota");
        BtnNota.setToolTipText("Alt+N");
        BtnNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnNota.setName("BtnNota"); // NOI18N
        BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotaActionPerformed(evt);
            }
        });
        BtnNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNotaKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnNota);

        BtnView.setForeground(new java.awt.Color(0, 0, 0));
        BtnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnView.setMnemonic('L');
        BtnView.setText("Lihat");
        BtnView.setToolTipText("Alt+L");
        BtnView.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnView.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnView.setName("BtnView"); // NOI18N
        BtnView.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnViewActionPerformed(evt);
            }
        });
        BtnView.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnViewKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnView);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isRawat();
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
//        if (akses.getbilling_ralan() == true) {
//            try {
//                pscekbilling=koneksi.prepareStatement(sqlpscekbilling);
//                try {
//                    pscekbilling.setString(1,TNoRw.getText());
//                    rscekbilling=pscekbilling.executeQuery();
//                    if(rscekbilling.next()){
//                        i=rscekbilling.getInt(1);
//                    }
//                } catch (Exception e) {
//                    i=0;
//                    System.out.println("Notifikasi : "+e);
//                } finally{
//                    if(rscekbilling != null){
//                        rscekbilling.close();
//                    }
//                    if(pscekbilling != null){
//                        pscekbilling.close();
//                    }
//                }
//
//                if(i<=0){
//                    int jawab=JOptionPane.showConfirmDialog(null, "Data pembayaran belum tersimpan, apa anda mau menyimpannya...????","Konfirmasi",JOptionPane.YES_NO_OPTION);
//                    if(jawab==JOptionPane.YES_OPTION){
//                        chkLaborat.setSelected(true);
//                        chkRadiologi.setSelected(true);
//                        isRawat();
//                        BtnSimpanActionPerformed(evt);
//                        dispose();
//                    }else{
//                        WindowObatLangsung.dispose();
//                        WindowGantiDokterPoli.dispose();
//                        WindowTambahanBiaya.dispose();
//                        WindowGantiPoli.dispose();
//                        WindowPotonganBiaya.dispose();
//                        dispose();    
//                    }                
//                }else if(i>0){
//                    WindowObatLangsung.dispose();
//                    WindowGantiDokterPoli.dispose();
//                    WindowTambahanBiaya.dispose();
//                    WindowGantiPoli.dispose();
//                    WindowPotonganBiaya.dispose();
//                    dispose();                
//                }
//            }catch(Exception e){
//                System.out.println(e);
//            }
//        }else{
        WindowObatLangsung.dispose();
        WindowGantiDokterPoli.dispose();
        WindowTambahanBiaya.dispose();
        WindowGantiPoli.dispose();
        WindowPotonganBiaya.dispose();
        dispose();
//        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnView, BtnNota);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TtlSemuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtlSemuaKeyPressed
        Valid.pindah(evt, BtnKeluar, BtnNota);
    }//GEN-LAST:event_TtlSemuaKeyPressed

    private void BtnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotaActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (tabModeRwJlDr.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabModeRwJlDr.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                koneksi.setAutoCommit(false);
                Sequel.queryu2("delete from temporary_bayar_ralan");
                for (i = 0; i < tabModeRwJlDr.getRowCount(); i++) {
                    if (tabModeRwJlDr.getValueAt(i, 0).toString().equals("true")) {
                        biaya = "";
                        try {
                            biaya = Valid.SetAngka(Double.parseDouble(tabModeRwJlDr.getValueAt(i, 4).toString()));
                        } catch (Exception e) {
                            biaya = "";
                        }
                        tambahan = "";
                        try {
                            tambahan = Valid.SetAngka(Double.parseDouble(tabModeRwJlDr.getValueAt(i, 6).toString()));
                        } catch (Exception e) {
                            tambahan = "";
                        }
                        totals = "";
                        try {
                            totals = Valid.SetAngka(Double.parseDouble(tabModeRwJlDr.getValueAt(i, 7).toString()));
                        } catch (Exception e) {
                            totals = "";
                        }

                        pstemporary = koneksi.prepareStatement(sqlpstemporary);
                        try {
                            pstemporary.setString(1, tabModeRwJlDr.getValueAt(i, 1).toString().replaceAll("'", "`"));
                            pstemporary.setString(2, tabModeRwJlDr.getValueAt(i, 2).toString().replaceAll("'", "`"));
                            pstemporary.setString(3, tabModeRwJlDr.getValueAt(i, 3).toString().replaceAll("'", "`"));
                            pstemporary.setString(4, biaya);
                            try {
                                pstemporary.setString(5, tabModeRwJlDr.getValueAt(i, 5).toString().replaceAll("'", "`"));
                            } catch (Exception e) {
                                pstemporary.setString(5, "");
                            }
                            pstemporary.setString(6, tambahan);
                            pstemporary.setString(7, totals);
                            try {
                                pstemporary.setString(8, tabModeRwJlDr.getValueAt(i, 8).toString().replaceAll("'", "`"));
                            } catch (Exception e) {
                                pstemporary.setString(8, "");
                            }
                            pstemporary.executeUpdate();
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (pstemporary != null) {
                                pstemporary.close();
                            }
                        }
                    }
                }

                if (piutang <= 0) {
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','TOTAL TAGIHAN',':','','','','','" + TtlSemua.getText() + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','PPN',':','','','','','" + Valid.SetAngka(besarppn) + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','TOTAL BAYAR',':','','','','','" + TagihanPPn.getText() + "','Tagihan','','','','','','','','',''", "Tagihan");
                } else if (piutang > 0) {
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','TOTAL TAGIHAN',':','','','','','" + TtlSemua.getText() + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','PPN',':','','','','','" + Valid.SetAngka(besarppn) + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','TAGIHAN + PPN',':','','','','','" + TagihanPPn.getText() + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','UANG MUKA',':','','','','','" + Valid.SetAngka(bayar) + "','Tagihan','','','','','','','','',''", "Tagihan");
                    Sequel.menyimpan("temporary_bayar_ralan", "'0','SISA PIUTANG',':','','','','','" + Valid.SetAngka(piutang) + "','Tagihan','','','','','','','','',''", "Tagihan");
                }

                i = 0;
                try {
                    biaya = (String) JOptionPane.showInputDialog(null, "Silahkan pilih nota/kwitansi yang mau dicetak..!", "Nota",
                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Nota", "Kwitansi"}, "Nota");
                    switch (biaya) {
                        case "Nota":
                            i = 1;
                            break;
                        case "Kwitansi":
                            i = 2;
                            break;
//                        case "Nota & Kwitansi":
//                            i = 3;
//                            break;
                    }
                } catch (Exception e) {
                    i = 0;
                }

                if (i > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText());
                    if (i == 1) {
                        cetakNota();
//                        Valid.panggilUrl("billing/LaporanBilling.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
                    } else if (i == 2) {
                        if (piutang > 0) {
                            cetakKwitansiPIUTANG();

//                            Valid.panggilUrl("billing/LaporanBilling7.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa "
//                                    + "where reg_periksa.kd_pj='" + kd_pj + "' and reg_periksa.tgl_registrasi like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RJ/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
                        } else if (piutang <= 0) {
                            cetakKwitansiLUNAS();

//                            Valid.panggilUrl("billing/LaporanBilling5.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa "
//                                    + "where reg_periksa.kd_pj='" + kd_pj + "' and reg_periksa.tgl_registrasi like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RJ/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
                        }
                    }
//                    else if (i == 3) {
//                        Valid.panggilUrl("billing/LaporanBilling.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
//                        if (piutang > 0) {
//                            Valid.panggilUrl("billing/LaporanBilling7.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa "
//                                    + "where reg_periksa.kd_pj='" + kd_pj + "' and reg_periksa.tgl_registrasi like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RJ/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
//                        } else if (piutang <= 0) {
//                            Valid.panggilUrl("billing/LaporanBilling5.php?petugas=" + akses.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(reg_periksa.no_rawat) from reg_periksa "
//                                    + "where reg_periksa.kd_pj='" + kd_pj + "' and reg_periksa.tgl_registrasi like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RJ/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
//                        }
//                    }
                    this.setCursor(Cursor.getDefaultCursor());
                }

                koneksi.setAutoCommit(true);
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
}//GEN-LAST:event_BtnNotaActionPerformed

    private void BtnNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnView);
        }
}//GEN-LAST:event_BtnNotaKeyPressed

    private void BtnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnViewActionPerformed
        Object[] options = {"Tagihan Masuk", "Piutang Pasien"};

        String input;
        i = 0;
        try {
            input = (String) JOptionPane.showInputDialog(null, "Silahkan pilih yang mau ditampilkan!", "Keuangan", JOptionPane.QUESTION_MESSAGE, null, options, "Nota 1");
            switch (input) {
                case "Tagihan Masuk":
                    i = 1;
                    break;
                case "Piutang Pasien":
                    i = 2;
                    break;
            }
        } catch (Exception e) {
            i = 0;
        }

        if (i > 0) {
            if (i == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgLhtBiaya billing = new DlgLhtBiaya(null, false);
                billing.setSize(this.getWidth(), this.getHeight());
                billing.setLocationRelativeTo(this);
                billing.setAlwaysOnTop(false);
                billing.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (i == 2) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgLhtPiutang billing = new DlgLhtPiutang(null, false);
                billing.tampil();
                billing.setSize(this.getWidth(), this.getHeight());
                billing.setLocationRelativeTo(this);
                billing.setAlwaysOnTop(false);
                billing.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnViewActionPerformed

    private void BtnViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnViewKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnViewActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnNota, BtnKeluar);
        }
    }//GEN-LAST:event_BtnViewKeyPressed

private void tbBillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBillingMouseClicked
    if (tabModeRwJlDr.getRowCount() != 0) {
        if (evt.getClickCount() == 1) {
            i = tbBilling.getSelectedColumn();
            if (i == 1) {
                try {
                    akses.setform("DlgBilingRalan");
                    switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), i).toString()) {
                        case "Tindakan":
                            if (akses.gettindakan_ralan() == true) {
//                                kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
//                                nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                                dlgrwjl.setNoRm(TNoRw.getText(), kdptg, nmptg, "rawat_jl_dr", "", "", "", "", "", "", "-", "-", "", "", "", "", "", "");
//                                //perawatan.setModal(true);
//                                dlgrwjl.perawatan.isCek();
//                                dlgrwjl.perawatan.tampil();
//                                dlgrwjl.perawatan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                                dlgrwjl.perawatan.setLocationRelativeTo(internalFrame1);
//                                dlgrwjl.perawatan.setAlwaysOnTop(false);
//                                dlgrwjl.perawatan.setVisible(true);
                            }
                            break;
                        case "Obat & BHP":
                            if (akses.getberi_obat() == true) {
                                dlgobt.emptTeksobat();
                                //dlgobt.setModal(true);
                                dlgobt.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                                dlgobt.isCek();
                                dlgobt.tampilobat();
                                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                dlgobt.setLocationRelativeTo(internalFrame1);
                                dlgobt.setAlwaysOnTop(false);
                                dlgobt.setVisible(true);
                            }
                            //dispose();
                            break;
                        case "Tambahan Biaya":
                            if (akses.gettambahan_biaya() == true) {
                                MnTambahanActionPerformed(null);
                            }
                            break;
                        case "Potongan Biaya":
                            if (akses.getpotongan_biaya()== true) {
                                MnPotonganActionPerformed(null);
                            }
                            break;
                        case "No. Nota":
                            Sequel.cariIsi("select no_nota from billing where no_nota = (replace('" + tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString() + "',': ',''))", TCekNota);
                            TNoNota.setText(TCekNota.getText());
                            isRawat();
                            break;
                    }
                } catch (Exception e) {
                    akses.setform("DlgBilingRalan");
                    switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), i).toString()) {
                        case "Tindakan":
                            if (akses.gettindakan_ralan() == true) {
                                kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
                                nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                                dlgrwjl.perawatan.setNoRm(TNoRw.getText(), kdptg, nmptg, "rawat_jl_dr", "", "", "", "", "", "", "-", "-", "", "", "", "", "", "");
//                                //perawatan.setModal(true);
//                                dlgrwjl.perawatan.isCek();
//                                dlgrwjl.perawatan.tampil();
//                                dlgrwjl.perawatan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                                dlgrwjl.perawatan.setLocationRelativeTo(internalFrame1);
//                                dlgrwjl.perawatan.setAlwaysOnTop(false);
//                                dlgrwjl.perawatan.setVisible(true);
                            }
                            break;
                        case "Obat & BHP":
                            if (akses.getberi_obat() == true) {
                                dlgobt.emptTeksobat();
                                //dlgobt.setModal(true);
                                dlgobt.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                                dlgobt.isCek();
                                dlgobt.tampilobat();
                                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                dlgobt.setLocationRelativeTo(internalFrame1);
                                dlgobt.setAlwaysOnTop(false);
                                dlgobt.setVisible(true);
                            }
                            //dispose();
                            break;
                        case "Tambahan Biaya":
                            if (akses.gettambahan_biaya() == true) {
                                MnTambahanActionPerformed(null);
                            }
                            break;
                        case "Potongan Biaya":
                            if (akses.getpotongan_biaya() == true) {
                                MnPotonganActionPerformed(null);
                            }
                            break;
                        case "No. Nota":
                            Sequel.cariIsi("select no_nota from billing where no_nota = (replace('" + tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString() + "',': ',''))", TCekNota);
                            TNoNota.setText(TCekNota.getText());
                            isRawat();
                            break;
                    }
                }
            }
        }
    }
}//GEN-LAST:event_tbBillingMouseClicked

private void tbBillingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBillingKeyPressed
    if (tbBilling.getRowCount() != 0) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tbBilling.getSelectedColumn();
            if (i == 6) {
                if (akses.getbilling_ralan() == true) {
                    try {
                        switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()) {
                            case "Laborat":
                                JOptionPane.showMessageDialog(rootPane, "Maaf, untuk tambahan/potongan laborat gunakan pada Tambahan/Potongan Biaya");
                                isRawat();
                                break;
                            case "Obat":
                                JOptionPane.showMessageDialog(rootPane, "Maaf, untuk tambahan potongan obat hanya bisa diisi embalase.\nGunakan Tambahan Biaya jika ingin tambahan lain");
                                isRawat();
                                break;
                            default:
                                try {
                                    if (Double.parseDouble(tbBilling.getValueAt(tbBilling.getSelectedRow(), 6).toString()) != 0) {
                                        Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                            TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                        });
                                        Sequel.menyimpan("temporary_tambahan_potongan", "?,?,?,?", 4, new String[]{
                                            TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 6).toString(),
                                            tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                        });
                                    } else {
                                        Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                            TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                        });
                                        tbBilling.setValueAt(0, tbBilling.getSelectedRow(), 0);
                                    }
                                } catch (Exception e) {
                                    Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                        TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                    });
                                    tbBilling.setValueAt(0, tbBilling.getSelectedRow(), 0);
                                }

                                isRawat();
                                break;
                        }
                    } catch (Exception e) {
                        isRawat();
                    }
                }
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            i = tbBilling.getSelectedColumn();
            if (i == 1) {
                try {
                    akses.setform("DlgBilingRalan");
                    switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), i).toString()) {
                        case "Tindakan":
                            if (akses.gettindakan_ralan() == true) {
                                kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
                                nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                                dlgrwjl.perawatan.setNoRm(TNoRw.getText(), kdptg, nmptg, "rawat_jl_dr", "", "", "", "", "", "", "-", "-", "", "", "", "", "", "");
//                                //perawatan.setModal(true);
//                                dlgrwjl.perawatan.isCek();
//                                dlgrwjl.perawatan.tampil();
//                                dlgrwjl.perawatan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                                dlgrwjl.perawatan.setLocationRelativeTo(internalFrame1);
//                                dlgrwjl.perawatan.setAlwaysOnTop(false);
//                                dlgrwjl.perawatan.setVisible(true);
                            }
                            break;
                        case "Obat & BHP":
                            if (akses.getberi_obat() == true) {
                                dlgobt.emptTeksobat();
                                //dlgobt.setModal(true);
                                dlgobt.isCek();
                                dlgobt.setNoRm(TNoRw.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?"), TNoRw.getText(),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?"), TNoRw.getText());
                                dlgobt.tampilobat();
                                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                dlgobt.setLocationRelativeTo(internalFrame1);
                                dlgobt.setAlwaysOnTop(false);
                                dlgobt.setVisible(true);
                            }
                            //dispose();
                            break;
                        case "Tambahan Biaya":
                            if (akses.gettambahan_biaya() == true) {
                                MnTambahanActionPerformed(null);
                            }
                            break;
                        case "Potongan Biaya":
                            if (akses.getpotongan_biaya() == true) {
                                MnPotonganActionPerformed(null);
                            }
                            break;
                    }
                } catch (Exception e) {
                    akses.setform("DlgBilingRalan");
                    switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), i).toString()) {
                        case "Tindakan":
                            if (akses.gettindakan_ralan() == true) {
                                kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
                                nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                                dlgrwjl.perawatan.setNoRm(TNoRw.getText(), kdptg, nmptg, "rawat_jl_dr", "", "", "", "", "", "", "-", "-", "", "", "", "", "", "");
//                                //perawatan.setModal(true);
//                                dlgrwjl.perawatan.isCek();
//                                dlgrwjl.perawatan.tampil();
//                                dlgrwjl.perawatan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                                dlgrwjl.perawatan.setLocationRelativeTo(internalFrame1);
//                                dlgrwjl.perawatan.setAlwaysOnTop(false);
//                                dlgrwjl.perawatan.setVisible(true);
                            }
                            break;
                        case "Obat & BHP":
                            if (akses.getberi_obat() == true) {
                                dlgobt.emptTeksobat();
                                //dlgobt.setModal(true);
                                dlgobt.isCek();
                                dlgobt.setNoRm(TNoRw.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?"), TNoRw.getText(),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?"), TNoRw.getText());
                                dlgobt.tampilobat();
                                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                dlgobt.setLocationRelativeTo(internalFrame1);
                                dlgobt.setAlwaysOnTop(false);
                                dlgobt.setVisible(true);
                            }
                            //dispose();
                            break;
                        case "Tambahan Biaya":
                            if (akses.gettambahan_biaya() == true) {
                                MnTambahanActionPerformed(null);
                            }
                            break;
                        case "Potongan Biaya":
                            if (akses.getpotongan_biaya() == true) {
                                MnPotonganActionPerformed(null);
                            }
                            break;
                    }
                }
            }
        }
    }
}//GEN-LAST:event_tbBillingKeyPressed

private void MnRawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalanActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        Sequel.cariIsi("select d.nm_dokter from reg_periksa r inner join dokter d where r.no_rawat=? ", TDokter, TNoRw.getText());
        Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=? ", kddokter, TNoRw.getText());

        akses.setform("DlgBilingRalan");
        dlgrwjl.isCek();
        dlgrwjl.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        dlgrwjl.setLocationRelativeTo(internalFrame1);

//        dlgrwjl.setNoRm(TNoRw.getText(), DTPTgl.getDate(), new Date());
//        dlgrwjl.tampilDrPr();
//        dlgrwjl.setVisible(true);
//        dlgrwjl.fokus();
//        dlgrwjl.petugas(kddokter.getText(), akses.getkode());
    }
}//GEN-LAST:event_MnRawatJalanActionPerformed

private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        DlgPemberianObat dlgrwinap = new DlgPemberianObat(null, false);
        dlgrwinap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        dlgrwinap.setLocationRelativeTo(internalFrame1);
        dlgrwinap.isCek();
        dlgrwinap.setNoRm(TNoRw.getText(), DTPTgl.getDate(), new Date(), "ralan");
        dlgrwinap.tampilPO();
        dlgrwinap.setAlwaysOnTop(false);
        dlgrwinap.setVisible(true);
    }
}//GEN-LAST:event_MnPemberianObatActionPerformed

private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
    Valid.pindah(evt, TNoRw, BtnNota);
}//GEN-LAST:event_DTPTglKeyPressed

private void MnHapusTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTagihanActionPerformed
    double a = Sequel.cariIsiAngka("select count(1) from billing where no_nota = (replace('" + tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString() + "',': ',''))");
    if (a <= 0) {
        JOptionPane.showMessageDialog(null, "Silakan Pilih No. Nota");
    } else {
//        cekNota = Sequel.cariIsi("select no_nota from billing where no_nota = (replace('" + tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString() + "',': ',''))");
//        JOptionPane.showMessageDialog(null, cekNota);
        Sequel.cariIsi("select no_nota from billing where no_nota = (replace('" + tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString() + "',': ',''))", TCekNota);
        try {

            i = 0;
            pscekbilling = koneksi.prepareStatement("select count(billing.no_rawat) from billing where billing.no_rawat='" + TNoRw.getText() + "' and no_nota = '" + TCekNota.getText() + "'");
            try {
//                pscekbilling.setString(1, TNoRw.getText());
                rscekbilling = pscekbilling.executeQuery();
                if (rscekbilling.next()) {
                    i = rscekbilling.getInt(1);
                }
            } catch (Exception e) {
                i = 0;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscekbilling != null) {
                    rscekbilling.close();
                }
                if (pscekbilling != null) {
                    pscekbilling.close();
                }
            }
            if (i > 0) {
                Sequel.AutoComitFalse();
//                Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah'");
                Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, "stts='Belum'");

                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                Sequel.queryu2("delete from tampjurnal");
                if ((-1 * ttlPotongan) > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Potongan_Ralan + "','Potongan_Ralan','0','" + (-1 * ttlPotongan) + "'", "Rekening");
                }

                if ((ttlRalan_Dokter + ttlRalan_Dokter_Param + ttlRalan_Paramedis) > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ralan + "','Tindakan Ralan','" + (ttlRalan_Dokter + ttlRalan_Dokter_Param + ttlRalan_Paramedis) + "','0'", "Rekening");
                }

                if (ttlLaborat > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Laborat_Ralan + "','Laborat Ralan','" + ttlLaborat + "','0'", "Rekening");
                }

                if (ttlRadiologi > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ralan + "','Radiologi Ralan','" + ttlRadiologi + "','0'", "Rekening");
                }

                if (ttlObat > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Obat_Ralan + "','Obat Ralan','" + ttlObat + "','0'", "Rekening");
                }

                if (ttlRegistrasi > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Registrasi_Ralan + "','Registrasi Ralan','" + ttlRegistrasi + "','0'", "Rekening");
                }

                if (ttlTambahan > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Tambahan_Ralan + "','Tambahan Ralan','" + ttlTambahan + "','0'", "Rekening");
                }

                if (ttlOperasi > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Operasi_Ralan + "','Operasi Ralan','" + ttlOperasi + "','0'", "Rekening");
                }

                psceknota = koneksi.prepareStatement("select * from nota_jalan where no_rawat='" + TNoRw.getText() + "' and no_nota = '" + TCekNota.getText() + "'");
                try {
//                    psceknota.setString(1, TNoRw.getText());
                    rsceknota = psceknota.executeQuery();
                    if (rsceknota.next()) {
                        if (rsceknota.getDouble("Jasa_Medik_Dokter_Tindakan_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Dokter_Tindakan_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Dokter_Tindakan_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Paramedis_Tindakan_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Paramedis_Tindakan_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Paramedis_Tindakan_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("KSO_Tindakan_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("KSO_Tindakan_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("KSO_Tindakan_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Dokter_Laborat_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Laborat_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Dokter_Laborat_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Laborat_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Dokter_Laborat_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Petugas_Laborat_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Laborat_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Petugas_Laborat_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Laborat_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Petugas_Laborat_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Kso_Laborat_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Laborat_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Kso_Laborat_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Laborat_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Kso_Laborat_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Persediaan_Laborat_Rawat_Jalan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Laborat_Rawat_Jalan + "','Operasi Ralan','" + rsceknota.getDouble("Persediaan_Laborat_Rawat_Jalan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Laborat_Rawat_Jalan + "','Operasi Ralan','0','" + rsceknota.getDouble("Persediaan_Laborat_Rawat_Jalan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Dokter_Radiologi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Dokter_Radiologi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Dokter_Radiologi_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Petugas_Radiologi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Petugas_Radiologi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Petugas_Radiologi_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Kso_Radiologi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Radiologi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Kso_Radiologi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Radiologi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Kso_Radiologi_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Persediaan_Radiologi_Rawat_Jalan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Jalan + "','Operasi Ralan','" + rsceknota.getDouble("Persediaan_Radiologi_Rawat_Jalan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Jalan + "','Operasi Ralan','0','" + rsceknota.getDouble("Persediaan_Radiologi_Rawat_Jalan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Obat_Rawat_Jalan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Rawat_Jalan + "','Operasi Ralan','" + rsceknota.getDouble("Obat_Rawat_Jalan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Rawat_Jalan + "','Operasi Ralan','0','" + rsceknota.getDouble("Obat_Rawat_Jalan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Dokter_Operasi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Operasi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Dokter_Operasi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Operasi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Dokter_Operasi_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Jasa_Medik_Paramedis_Operasi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Operasi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Jasa_Medik_Paramedis_Operasi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Operasi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Jasa_Medik_Paramedis_Operasi_Ralan") + "'", "Rekening");
                        }

                        if (rsceknota.getDouble("Obat_Operasi_Ralan") > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_Obat_Kamar_Operasi_Ralan + "','Operasi Ralan','" + rsceknota.getDouble("Obat_Operasi_Ralan") + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Obat_Operasi_Ralan + "','Operasi Ralan','0','" + rsceknota.getDouble("Obat_Operasi_Ralan") + "'", "Rekening");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notif Cek Nota : " + e);
                } finally {
                    if (rsceknota != null) {
                        rsceknota.close();
                    }
                    if (psceknota != null) {
                        psceknota.close();
                    }
                }

                psakunbayar = koneksi.prepareStatement(
                        "select akun_bayar.nama_bayar,akun_bayar.kd_rek,detail_nota_jalan.besar_bayar,"
                        + "akun_bayar.ppn,detail_nota_jalan.besarppn from akun_bayar inner join detail_nota_jalan "
                        + "on akun_bayar.nama_bayar=detail_nota_jalan.nama_bayar where detail_nota_jalan.no_rawat='" + TNoRw.getText() + "' and detail_nota_jalan.no_nota='" + TCekNota.getText() + "' order by nama_bayar");
                try {
//                    psakunbayar.setString(1, TNoRw.getText());
                    rsakunbayar = psakunbayar.executeQuery();
                    while (rsakunbayar.next()) {
                        Sequel.menyimpan("tampjurnal", "'" + rsakunbayar.getString("kd_rek") + "','" + rsakunbayar.getString("nama_bayar") + "','0','" + rsakunbayar.getString("besar_bayar") + "'", "Rekening");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi Akun Bayar Tersimpan : " + e);
                } finally {
                    if (rsakunbayar != null) {
                        rsakunbayar.close();
                    }
                    if (psakunbayar != null) {
                        psakunbayar.close();
                    }
                }

                psakunpiutang = koneksi.prepareStatement(
                        "select akun_piutang.nama_bayar,akun_piutang.kd_rek,akun_piutang.kd_pj, "
                        + "detail_piutang_pasien.totalpiutang,date_format(detail_piutang_pasien.tgltempo,'%d/%m/%Y') from "
                        + "akun_piutang inner join detail_piutang_pasien on akun_piutang.nama_bayar=detail_piutang_pasien.nama_bayar "
                        + "where detail_piutang_pasien.no_rawat='" + TNoRw.getText() + "' and detail_piutang_pasien.no_nota = '" + TCekNota.getText() + "' order by nama_bayar");
                try {
//                    psakunpiutang.setString(1, TNoRw.getText());
                    rsakunpiutang = psakunpiutang.executeQuery();
                    while (rsakunpiutang.next()) {
                        Sequel.menyimpan("tampjurnal", "'" + rsakunpiutang.getString("kd_rek") + "','" + rsakunpiutang.getString("nama_bayar") + "','0','" + rsakunpiutang.getString("totalpiutang") + "'", "Rekening");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi Akun Piutang Tersimpan : " + e);
                } finally {
                    if (rsakunpiutang != null) {
                        rsakunpiutang.close();
                    }
                    if (psakunpiutang != null) {
                        psakunpiutang.close();
                    }
                }

                if (piutang > 0) {
                    jur.simpanJurnal(TNoRw.getText(),"U", "PEMBATALAN PIUTANG PASIEN RAWAT JALAN, DIBATALKAN OLEH " + akses.getkode());
                } else if (piutang <= 0) {
                    jur.simpanJurnal(TNoRw.getText(),"U", "PEMBATALAN PEMBAYARAN PASIEN RAWAT JALAN, DIBATALKAN OLEH " + akses.getkode());
                }

//                Sequel.queryu2("delete from piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
//                Sequel.queryu2("delete from detail_piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
//                Sequel.queryu2("delete from nota_jalan where no_rawat='" + TNoRw.getText() + "'");
//                Sequel.queryu2("delete from detail_nota_jalan where no_rawat='" + TNoRw.getText() + "'");
//                Valid.hapusTable(tabModeRwJlDr, TNoRw, "billing", "no_rawat");
//                Valid.hapusTable(tabModeRwJlDr, TNoRw, "tagihan_sadewa", "no_nota");
                Sequel.queryu2("delete from piutang_pasien where no_nota='" + TCekNota.getText() + "'");
                Sequel.queryu2("delete from detail_piutang_pasien where no_nota='" + TCekNota.getText() + "'");
                Sequel.queryu2("delete from nota_jalan where no_nota='" + TCekNota.getText() + "'");
                Sequel.queryu2("delete from detail_nota_jalan where no_nota='" + TCekNota.getText() + "'");
                Sequel.mengedittf("detail_pemberian_obat", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Bayar' and no_nota = '" + TCekNota.getText() + "' ", "stts_bayar = 'Belum',no_nota='-'");
                Sequel.mengedittf("rawat_jl_drpr", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Bayar' and no_nota = '" + TCekNota.getText() + "' ", "stts_bayar = 'Belum',no_nota='-'");
                Sequel.mengedittf("periksa_lab", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Bayar' and no_nota = '" + TCekNota.getText() + "' ", "stts_bayar = 'Belum',no_nota='-'");
                Sequel.mengedittf("detail_periksa_lab", "no_rawat = '" + TNoRw.getText() + "' and no_nota = '" + TCekNota.getText() + "' ", "no_nota='-'");
                Sequel.mengedittf("periksa_radiologi", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Bayar' and no_nota = '" + TCekNota.getText() + "' ", "stts_bayar = 'Belum',no_nota='-'");
                Sequel.mengedittf("operasi", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Bayar' and no_nota = '" + TCekNota.getText() + "' ", "stts_bayar = 'Bayar',no_nota='-'");
                Valid.hapusTable(tabModeRwJlDr, TCekNota, "billing", "no_nota");
                Valid.hapusTable(tabModeRwJlDr, TCekNota, "tagihan_sadewa", "no_nota2");
                this.setCursor(Cursor.getDefaultCursor());
                Sequel.AutoComitTrue();
                JOptionPane.showMessageDialog(rootPane, "Proses hapus data Nota Salah selesai..!!");
                Valid.tabelKosong(tabModeAkunBayar);
                Valid.tabelKosong(tabModeAkunPiutang);
                isRawat();
            } else if (i <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Data belum pernah disimpan/diverifikasi."
                        + "Tidak perlu ada penghapusan data salah..!!");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}//GEN-LAST:event_MnHapusTagihanActionPerformed

private void MnPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjualanActionPerformed
    if (tabModeRwJlDr.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
    } else {
        DlgPenjualan penjualan = new DlgPenjualan(null, false);
        penjualan.setPasien(Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
        kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
        nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//        penjualan.isRw(TNoRw.getText(), kdptg, nmptg);
        penjualan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        penjualan.setLocationRelativeTo(internalFrame1);
        penjualan.setVisible(true);
        penjualan.isCek();
    }
}//GEN-LAST:event_MnPenjualanActionPerformed

private void MnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterActionPerformed
    Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=? ", kddokter, TNoRw.getText());
    Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=? ", TDokter, kddokter.getText());

    if (akses.getkode().equals("Admin Utama")) {
        WindowGantiDokterPoli.setSize(630, 80);
        WindowGantiDokterPoli.setLocationRelativeTo(internalFrame1);
        WindowGantiDokterPoli.setAlwaysOnTop(false);
        WindowGantiDokterPoli.setVisible(true);
    } else {
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
        } else {
            WindowGantiDokterPoli.setSize(630, 80);
            WindowGantiDokterPoli.setLocationRelativeTo(internalFrame1);
            WindowGantiDokterPoli.setAlwaysOnTop(false);
            WindowGantiDokterPoli.setVisible(true);
        }
    }
}//GEN-LAST:event_MnDokterActionPerformed

private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
    WindowGantiDokterPoli.dispose();
}//GEN-LAST:event_BtnCloseIn1ActionPerformed

private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnCloseIn1KeyPressed

private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    }
    if (kddokter.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
        Valid.textKosong(kddokter, "Dokter");
    } else {
        Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        Valid.editTable(tabModeRwJlDr, "rawat_jl_dr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        Valid.editTable(tabModeRwJlDr, "rawat_jl_drpr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        isRawat();
        WindowGantiDokterPoli.dispose();
    }
}//GEN-LAST:event_BtnSimpan1ActionPerformed

private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnSimpan1KeyPressed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
//    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
//        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
//    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
//        btnCariDokterActionPerformed(null);
//    } else {
//        Valid.pindah(evt, BtnCloseIn1, BtnSimpan1);
//    }
}//GEN-LAST:event_kddokterKeyPressed

private void TDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDokterKeyPressed

}//GEN-LAST:event_TDokterKeyPressed

private void btnCariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokterActionPerformed
    akses.setform("DlgBilingRalan");
    dokter.isCek();
    dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setAlwaysOnTop(false);
    dokter.setVisible(true);
}//GEN-LAST:event_btnCariDokterActionPerformed

private void MnObatLangsungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObatLangsungActionPerformed
    TotalObat.setText(Sequel.cariIsi("select besar_tagihan from tagihan_obat_langsung where no_rawat=?", TNoRw.getText()));
    WindowObatLangsung.setSize(590, 80);
    WindowObatLangsung.setLocationRelativeTo(internalFrame1);
    TotalObat.requestFocus();
    WindowObatLangsung.setAlwaysOnTop(false);
    WindowObatLangsung.setVisible(true);
}//GEN-LAST:event_MnObatLangsungActionPerformed

private void TotalObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalObatKeyPressed
    Valid.pindah(evt, BtnCloseIn, BtnNota);
}//GEN-LAST:event_TotalObatKeyPressed

private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
    WindowObatLangsung.dispose();
}//GEN-LAST:event_BtnCloseInActionPerformed

private void BtnCloseInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseInKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        WindowObatLangsung.dispose();
    } else {
        Valid.pindah(evt, BtnNota, TotalObat);
    }
}//GEN-LAST:event_BtnCloseInKeyPressed

private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        Sequel.menyimpan("tagihan_obat_langsung", "'" + TNoRw.getText() + "','" + TotalObat.getText() + "'", "No.Rawat");
        WindowObatLangsung.setVisible(false);
        isRawat();
        isKembali();
    }
}//GEN-LAST:event_BtnSimpan2ActionPerformed

private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
    Valid.pindah(evt, TotalObat, BtnBatal1);
}//GEN-LAST:event_BtnSimpan2KeyPressed

private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        Sequel.queryu("delete from tagihan_obat_langsung where no_rawat=? ", TNoRw.getText());
        WindowObatLangsung.setVisible(false);
        isRawat();
        isKembali();
    }
}//GEN-LAST:event_BtnBatal1ActionPerformed

private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnBatal1ActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnNota, BtnCloseIn);
    }
}//GEN-LAST:event_BtnBatal1KeyPressed

private void MnTambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTambahanActionPerformed
    if (akses.getkode().equals("Admin Utama")) {
        norawat.setText(TNoRw.getText());
        tampilTambahan(norawat.getText());
        WindowTambahanBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        WindowTambahanBiaya.setLocationRelativeTo(internalFrame1);
        WindowTambahanBiaya.setAlwaysOnTop(false);
        WindowTambahanBiaya.setVisible(true);
    } else {
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
        } else {
            norawat.setText(TNoRw.getText());
            tampilTambahan(norawat.getText());
            WindowTambahanBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            WindowTambahanBiaya.setLocationRelativeTo(internalFrame1);
            WindowTambahanBiaya.setAlwaysOnTop(false);
            WindowTambahanBiaya.setVisible(true);
        }
    }

}//GEN-LAST:event_MnTambahanActionPerformed

private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
    Valid.pindah(evt, BtnKeluar, BtnNota);
}//GEN-LAST:event_norawatKeyPressed

private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
    tabModeTambahan.addRow(new Object[]{"", ""});
}//GEN-LAST:event_BtnTambahActionPerformed

private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
    if (norawat.getText().trim().equals("") || (tbTambahan.getRowCount() <= 0)) {
        Valid.textKosong(norawat, "Data");
    } else {
        for (i = 0; i < tbTambahan.getRowCount(); i++) {
            if (Valid.SetAngka(tbTambahan.getValueAt(i, 1).toString()) > 0) {
                Sequel.menyimpan("tambahan_biaya", "'" + norawat.getText() + "','" + tbTambahan.getValueAt(i, 0).toString()
                        + "','" + tbTambahan.getValueAt(i, 1).toString() + "','" + akses.getkode() + "',"
                        + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Tambahan Biaya");
            }
        }
        isRawat();
        isKembali();
        WindowTambahanBiaya.dispose();
    }
}//GEN-LAST:event_BtnSimpan3ActionPerformed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    Sequel.queryu("delete from tambahan_biaya where no_rawat='" + norawat.getText()
            + "' and nama_biaya='" + tbTambahan.getValueAt(tbTambahan.getSelectedRow(), 0).toString() + "'");
    tabModeTambahan.removeRow(tbTambahan.getSelectedRow());
    isRawat();
    isKembali();
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
    WindowTambahanBiaya.dispose();
}//GEN-LAST:event_BtnKeluar1ActionPerformed

private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
    WindowGantiPoli.dispose();
}//GEN-LAST:event_BtnCloseIn4ActionPerformed

private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    }
    if (kdpoli.getText().trim().equals("") || nmpoli.getText().trim().equals("")) {
        Valid.textKosong(kdpoli, "Poli");
    } else {
        Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, " kd_poli='" + kdpoli.getText() + "'");
        isRawat();
        WindowGantiPoli.dispose();
    }
}//GEN-LAST:event_BtnSimpan4ActionPerformed

private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
//    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
//        Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
//    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
//        btnCariPoliActionPerformed(null);
//    } else {
//        Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
//    }
}//GEN-LAST:event_kdpoliKeyPressed

private void btnCariPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPoliActionPerformed
    akses.setform("DlgBilingRalan");
    poli.isCek();
    poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    poli.setLocationRelativeTo(internalFrame1);
    poli.setAlwaysOnTop(false);
    poli.setVisible(true);
}//GEN-LAST:event_btnCariPoliActionPerformed

private void MnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliActionPerformed
    Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=? ", kdpoli, TNoRw.getText());
    Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=? ", nmpoli, kdpoli.getText());

    WindowGantiPoli.setSize(630, 80);
    WindowGantiPoli.setLocationRelativeTo(internalFrame1);
    WindowGantiPoli.setAlwaysOnTop(false);
    WindowGantiPoli.setVisible(true);
}//GEN-LAST:event_MnPoliActionPerformed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    isRawat();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, TNoRw, DTPTgl);
    }
}//GEN-LAST:event_BtnCariKeyPressed

private void norawatpotonganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatpotonganKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_norawatpotonganKeyPressed

private void BtnTambahPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahPotonganActionPerformed
    tabModePotongan.addRow(new Object[]{"", ""});
}//GEN-LAST:event_BtnTambahPotonganActionPerformed

private void BtnSimpanPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPotonganActionPerformed
    if (norawatpotongan.getText().trim().equals("") || (tbPotongan.getRowCount() <= 0)) {
        Valid.textKosong(norawat, "Data");
    } else {
        for (r = 0; r < tbPotongan.getRowCount(); r++) {
            if (Valid.SetAngka(tbPotongan.getValueAt(r, 1).toString()) > 0) {
                Sequel.menyimpan("pengurangan_biaya", "'" + norawatpotongan.getText() + "','" + tbPotongan.getValueAt(r, 0).toString()
                        + "','" + tbPotongan.getValueAt(r, 1).toString() + "'", "Potongan Biaya");
            }
        }
        isRawat();
        isKembali();
        WindowPotonganBiaya.dispose();
    }
}//GEN-LAST:event_BtnSimpanPotonganActionPerformed

private void BtnHapusPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPotonganActionPerformed
    Sequel.queryu("delete from pengurangan_biaya where no_rawat='" + norawatpotongan.getText()
            + "' and nama_pengurangan='" + tbPotongan.getValueAt(tbPotongan.getSelectedRow(), 0).toString() + "'");
    tabModePotongan.removeRow(tbPotongan.getSelectedRow());
    isRawat();
    isKembali();
}//GEN-LAST:event_BtnHapusPotonganActionPerformed

private void BtnKeluarPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarPotonganActionPerformed
    WindowPotonganBiaya.dispose();
}//GEN-LAST:event_BtnKeluarPotonganActionPerformed

private void MnPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPotonganActionPerformed
    if (akses.getkode().equals("Admin Utama")) {
        norawatpotongan.setText(TNoRw.getText());
        tampilPotongan(norawatpotongan.getText());
        WindowPotonganBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        WindowPotonganBiaya.setLocationRelativeTo(internalFrame1);
        WindowPotonganBiaya.setAlwaysOnTop(false);
        WindowPotonganBiaya.setVisible(true);
    } else {
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
        } else {
            norawatpotongan.setText(TNoRw.getText());
            tampilPotongan(norawatpotongan.getText());
            WindowPotonganBiaya.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            WindowPotonganBiaya.setLocationRelativeTo(internalFrame1);
            WindowPotonganBiaya.setAlwaysOnTop(false);
            WindowPotonganBiaya.setVisible(true);
        }
    }
}//GEN-LAST:event_MnPotonganActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    cekdokter = "";
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
    cekdokter = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
    }

    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
    } else {
        if (akses.getkode().equals("Admin Utama")) {
            DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
            periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            periksalab.setLocationRelativeTo(internalFrame1);
            periksalab.emptTeks();
//            periksalab.setNoRm(TNoRw.getText(), "Ralan", diagnosa_ok, "-", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));;
//            periksalab.KodePerujuk.setText(cekdokter);
//            periksalab.tampiltarif();
//            periksalab.tampil();
//            periksalab.tampilMintaPeriksa();
//            periksalab.isCek();
//            periksalab.setAlwaysOnTop(false);
//            periksalab.setVisible(true);
//            periksalab.fokus();
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi ..!!");
            } else {
                DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
                periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                periksalab.setLocationRelativeTo(internalFrame1);
                periksalab.emptTeks();
//                periksalab.setNoRm(TNoRw.getText(), "Ralan", diagnosa_ok, "-", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
//                periksalab.KodePerujuk.setText(cekdokter);
//                periksalab.tampiltarif();
//                periksalab.tampil();
//                periksalab.tampilMintaPeriksa();
//                periksalab.isCek();
//                periksalab.setAlwaysOnTop(false);
//                periksalab.setVisible(true);
//                periksalab.fokus();
            }
        }
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        cekObat = 0;
//        cekPoli();
//        if (kdpoli.getText().equals("-") || kdpoli.getText().equals("LAA") || kdpoli.getText().equals("LAB") || kdpoli.getText().equals("RAD") || kdpoli.getText().equals("UMUM")) {
//            isCekSimpan();
//        } else {
//            isCekObat();
//            if (cekObat == 0) {
//                JOptionPane.showMessageDialog(null, "Obat Belum Diinput, Silakan Konfirmasi ke Pasien dan Apotek !!!");
//            } else {
//                isCekSimpan();
//            }
//        }
        if (chkBayar.isSelected() == true) {
            chkBayar.setText("Transaksi SUDAH DIBAYAR");
            JOptionPane.showMessageDialog(null, "Transaksi Sudah Terverifikasi Semua, silakan hilangkan tanda centang pada tulisan Sudah DiBayar....");
        } else {
            if ((TtlSemua.getText()).equals("0") || TtlSemua.getText().equals("") || TtlSemua.getText().equals(null)) {
                JOptionPane.showMessageDialog(null, "Tidak ada transaksi yang akan disimpan, silakan periksa kembali");
            } else {
                cekSimpan = 1;
                isRawat();
                isCekSimpan();
            }

        }


    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void chkPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPotonganActionPerformed
        isRawat();
    }//GEN-LAST:event_chkPotonganActionPerformed

    private void chkLaboratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLaboratActionPerformed
        isRawat();
    }//GEN-LAST:event_chkLaboratActionPerformed

    private void chkTarifDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarifDokterActionPerformed
        isRawat();
    }//GEN-LAST:event_chkTarifDokterActionPerformed

    private void chkTarifPrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTarifPrmActionPerformed
        isRawat();
    }//GEN-LAST:event_chkTarifPrmActionPerformed

    private void chkTambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTambahanActionPerformed
        isRawat();
    }//GEN-LAST:event_chkTambahanActionPerformed

    private void chkObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkObatActionPerformed
        isRawat();
    }//GEN-LAST:event_chkObatActionPerformed

    private void MnInputObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputObatActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                dlgobt.emptTeksobat();
                dlgobt.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                dlgobt.isCek();
                dlgobt.tampilobat();
//                dlgobt.tampil_resep();
//                dlgobt.isPsien();
                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgobt.setLocationRelativeTo(internalFrame1);
                dlgobt.setAlwaysOnTop(false);
                dlgobt.setVisible(true);
            } else {
//                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
////                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi ..!!");
//                    int jawab = JOptionPane.showConfirmDialog(null, "Data billing sudah terverifikasi, apa anda mau input pada penjualan langsung...????", "Konfirmasi", JOptionPane.YES_NO_OPTION);
//                    if (jawab == JOptionPane.YES_OPTION) {
//                        jual.setPasien(Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
//                        kdptg = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText());
//                        nmptg = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", kdptg);
//                        jual.isRw(TNoRw.getText(), kdptg, nmptg);
//                        jual.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                        jual.setLocationRelativeTo(internalFrame1);
//                        jual.setVisible(true);
//                        jual.isCek();
//                    } else {
////                        TCari.requestFocus();
//                    }
//                } else {
                dlgobt.emptTeksobat();
                dlgobt.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                dlgobt.isCek();
                dlgobt.tampilobat();
//                dlgobt.isPsien();
                dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgobt.setLocationRelativeTo(internalFrame1);
                dlgobt.setAlwaysOnTop(false);
                dlgobt.setVisible(true);
//                }
            }

        }
    }//GEN-LAST:event_MnInputObatActionPerformed

    private void MnCariPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPeriksaLabActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaLab periksalab = new DlgCariPeriksaLab(null, false);
            periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            periksalab.setLocationRelativeTo(internalFrame1);
            periksalab.SetNoRw(TNoRw.getText());
            periksalab.setAlwaysOnTop(false);
            periksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariPeriksaLabActionPerformed

    private void chkRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRadiologiActionPerformed
        isRawat();
    }//GEN-LAST:event_chkRadiologiActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                DlgPeriksaRadiologi periksalab = new DlgPeriksaRadiologi(null, false);
                periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                periksalab.setLocationRelativeTo(internalFrame1);
                periksalab.emptTeks();
                periksalab.setNoRm(TNoRw.getText(), "Ralan");
                periksalab.tampil();
                periksalab.isCek();
                periksalab.setAlwaysOnTop(false);
                periksalab.setVisible(true);
            } else {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
                } else {
                    DlgPeriksaRadiologi periksalab = new DlgPeriksaRadiologi(null, false);
                    periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    periksalab.setLocationRelativeTo(internalFrame1);
                    periksalab.emptTeks();
                    periksalab.setNoRm(TNoRw.getText(), "Ralan");
                    periksalab.tampil();
                    periksalab.isCek();
                    periksalab.setAlwaysOnTop(false);
                    periksalab.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void MnCariRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariRadiologiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaRadiologi periksalab = new DlgCariPeriksaRadiologi(null, false);
            periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            periksalab.setLocationRelativeTo(internalFrame1);
            periksalab.SetNoRw(TNoRw.getText());
            periksalab.setAlwaysOnTop(false);
            periksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariRadiologiActionPerformed

    private void chkAdministrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdministrasiActionPerformed
        isRawat();
    }//GEN-LAST:event_chkAdministrasiActionPerformed

    private void chkSarprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSarprasActionPerformed
        isRawat();
    }//GEN-LAST:event_chkSarprasActionPerformed

    private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
            dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.setNoRm(TNoRw.getText(), TNoRM.getText() + ", " + TPasien.getText(), "Ralan");
            dlgro.setVisible(true);
        }
    }//GEN-LAST:event_MnOperasiActionPerformed

    private void MnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjabActionPerformed
        Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", kdpenjab, TNoRw.getText());
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());

        WindowGantiPenjab.setSize(630, 80);
        WindowGantiPenjab.setLocationRelativeTo(internalFrame1);
        WindowGantiPenjab.setAlwaysOnTop(false);
        WindowGantiPenjab.setVisible(true);
    }//GEN-LAST:event_MnPenjabActionPerformed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgBilingRalan");
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (kdpenjab.getText().trim().equals("") || nmpenjab.getText().trim().equals("")) {
            Valid.textKosong(kdpenjab, "Jenis Bayar");
        } else {
            Sequel.mengedit("reg_periksa", "no_rawat=?", " kd_pj=?", 2, new String[]{kdpenjab.getText(), TNoRw.getText()});
            isRawat();
            WindowGantiPenjab.dispose();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowGantiPenjab.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void tbAkunBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAkunBayarKeyPressed
        if (tabModeAkunBayar.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (tbAkunBayar.getRowCount() != 0) {
                    if ((tbAkunBayar.getSelectedColumn() == 2) || (tbAkunBayar.getSelectedColumn() == 3) || (tbAkunBayar.getSelectedColumn() == 4)) {
                        if (!tabModeAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 2).toString().equals("")) {
                            tbAkunBayar.setValueAt(
                                    Valid.roundUp((Valid.SetAngka(tbAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 3).toString()) / 100)
                                            * Valid.SetAngka(tbAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 2).toString()), 100), tbAkunBayar.getSelectedRow(), 4);
                        } else {
                            tbAkunBayar.setValueAt("", tbAkunBayar.getSelectedRow(), 4);
                        }
                    }
                }
                isKembali();
            }
        }
    }//GEN-LAST:event_tbAkunBayarKeyPressed

    private void tbAkunPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAkunPiutangKeyPressed
        if (tabModeAkunBayar.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                isKembali();
            }
        }
    }//GEN-LAST:event_tbAkunPiutangKeyPressed

    private void tbAkunBayarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbAkunBayarPropertyChange
        if (this.isVisible() == true) {
            isKembali();
        }
    }//GEN-LAST:event_tbAkunBayarPropertyChange

    private void tbAkunPiutangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbAkunPiutangPropertyChange
        if (this.isVisible() == true) {
            isKembali();
        }
    }//GEN-LAST:event_tbAkunPiutangPropertyChange

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariBayarActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariBayarActionPerformed
        if (status.equals("belum")) {
            tampilAkunBayar();
        } else if (status.equals("sudah")) {
            tampilAkunBayarTersimpan();
        }
        isHitung();
        isKembali();
    }//GEN-LAST:event_BtnCariBayarActionPerformed

    private void BtnCariBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariBayarKeyPressed

    }//GEN-LAST:event_BtnCariBayarKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariPiutangActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void btnCariPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPiutangActionPerformed
        if (status.equals("belum")) {
            tampilAkunPiutang();
        } else if (status.equals("sudah")) {
            tampilAkunPiutangTersimpan();
        }
        isHitung();
        isKembali();
    }//GEN-LAST:event_btnCariPiutangActionPerformed

    private void btnCariPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariPiutangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPiutangKeyPressed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        Valid.tabelKosong(tabModeAkunBayar);
        if (status.equals("belum")) {
            tampilAkunBayar();
        } else if (status.equals("sudah")) {
            tampilAkunBayarTersimpan();
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppBersihkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkan1ActionPerformed
        Valid.tabelKosong(tabModeAkunPiutang);
        if (status.equals("belum")) {
            tampilAkunPiutang();
        } else if (status.equals("sudah")) {
            tampilAkunPiutangTersimpan();
        }
    }//GEN-LAST:event_ppBersihkan1ActionPerformed

    private void chkBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBayarActionPerformed
        isRawat();

        if (chkBayar.isSelected() == false) {
            chkBayar.setText("Transaksi BELUM DIBAYAR");
        } else if (chkBayar.isSelected() == true) {
            chkBayar.setText("Transaksi SUDAH DIBAYAR");
        }
    }//GEN-LAST:event_chkBayarActionPerformed

    private void TNoNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoNotaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isRawat();
        }
    }//GEN-LAST:event_TNoNotaKeyPressed

    private void TCekNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCekNotaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCekNotaKeyPressed

    private void MnNamaTelahTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNamaTelahTerimaActionPerformed
        Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat=? ", nm_telah_terima, TNoRw.getText());

        WindowTelahTerimaKwitansi.setSize(731, 77);
        WindowTelahTerimaKwitansi.setLocationRelativeTo(internalFrame1);
        WindowTelahTerimaKwitansi.setAlwaysOnTop(false);
        WindowTelahTerimaKwitansi.setVisible(true);
    }//GEN-LAST:event_MnNamaTelahTerimaActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowTelahTerimaKwitansi.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "p_jawab='" + nm_telah_terima.getText() + "' ");
        WindowTelahTerimaKwitansi.dispose();
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void TCekNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCekNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCekNotaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBilingRalan dialog = new DlgBilingRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCariBayar;
    private widget.Button BtnCloseIn;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusPotongan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluarPotongan;
    private widget.Button BtnNota;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpanPotongan;
    private widget.Button BtnTambah;
    private widget.Button BtnTambahPotongan;
    private widget.Button BtnView;
    private widget.Tanggal DTPTgl;
    private javax.swing.JMenuItem MnCariPeriksaLab;
    private javax.swing.JMenuItem MnCariRadiologi;
    private javax.swing.JMenuItem MnDokter;
    private javax.swing.JMenu MnGanti;
    private javax.swing.JMenuItem MnHapusTagihan;
    private javax.swing.JMenuItem MnInputObat;
    private javax.swing.JMenuItem MnNamaTelahTerima;
    private javax.swing.JMenuItem MnObatLangsung;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenuItem MnPenjab;
    private javax.swing.JMenuItem MnPenjualan;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenuItem MnPoli;
    private javax.swing.JMenuItem MnPotongan;
    private javax.swing.JMenuItem MnRawatJalan;
    private javax.swing.JMenuItem MnTambahan;
    private javax.swing.JPopupMenu PopupBayar;
    private javax.swing.JPopupMenu PopupPiutang;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    public widget.TextBox TCekNota;
    private widget.TextBox TDokter;
    public widget.TextBox TKembali;
    public widget.TextBox TNoNota;
    private widget.TextBox TNoRM;
    public widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TagihanPPn;
    private widget.TextBox TotalObat;
    private widget.TextBox TtlSemua;
    private javax.swing.JDialog WindowGantiDokterPoli;
    private javax.swing.JDialog WindowGantiPenjab;
    private javax.swing.JDialog WindowGantiPoli;
    private javax.swing.JDialog WindowObatLangsung;
    private javax.swing.JDialog WindowPotonganBiaya;
    private javax.swing.JDialog WindowTambahanBiaya;
    private javax.swing.JDialog WindowTelahTerimaKwitansi;
    private widget.Button btnCariDokter;
    private widget.Button btnCariPiutang;
    private widget.Button btnCariPoli;
    private widget.Button btnPenjab;
    private widget.CekBox chkAdministrasi;
    private widget.CekBox chkBayar;
    private widget.CekBox chkLaborat;
    private widget.CekBox chkObat;
    private widget.CekBox chkPotongan;
    private widget.CekBox chkRadiologi;
    private widget.CekBox chkSarpras;
    private widget.CekBox chkTambahan;
    private widget.CekBox chkTarifDokter;
    private widget.CekBox chkTarifPrm;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kddokter;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpoli;
    private widget.Label label15;
    private widget.Label label16;
    private widget.TextBox nm_telah_terima;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpoli;
    private widget.TextBox norawat;
    private widget.TextBox norawatpotongan;
    private widget.panelisi panelBayar;
    private widget.panelisi panelGlass1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppBersihkan1;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbAkunBayar;
    private widget.Table tbAkunPiutang;
    private widget.Table tbBilling;
    private widget.Table tbPotongan;
    private widget.Table tbTambahan;
    // End of variables declaration//GEN-END:variables

    public void isRawat() {
        try {
            pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
            try {
                pscekbilling.setString(1, TNoRw.getText());
                rscekbilling = pscekbilling.executeQuery();
                if (rscekbilling.next()) {
                    i = rscekbilling.getInt(1);
                }
            } catch (Exception e) {
                i = 0;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscekbilling != null) {
                    rscekbilling.close();
                }
                if (pscekbilling != null) {
                    pscekbilling.close();
                }
            }

            pscarirm = koneksi.prepareStatement(sqlpscarirm);
            try {
                pscarirm.setString(1, TNoRw.getText());
                rscarirm = pscarirm.executeQuery();
                if (rscarirm.next()) {
                    TNoRM.setText(rscarirm.getString(1));
                }
            } catch (Exception e) {
                TNoRM.setText("");
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscarirm != null) {
                    rscarirm.close();
                }
                if (pscarirm != null) {
                    pscarirm.close();
                }
            }

            pscaripasien = koneksi.prepareStatement(sqlpscaripasien);
            try {
                pscaripasien.setString(1, TNoRM.getText());
                rscaripasien = pscaripasien.executeQuery();
                if (rscaripasien.next()) {
                    TPasien.setText(rscaripasien.getString(1) + " (" + rscaripasien.getString(2) + ".)");
                }
            } catch (Exception e) {
                TPasien.setText("");
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscaripasien != null) {
                    rscaripasien.close();
                }
                if (pscaripasien != null) {
                    pscaripasien.close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        Jasa_Medik_Dokter_Tindakan_Ralan = 0;
        Jasa_Medik_Paramedis_Tindakan_Ralan = 0;
        KSO_Tindakan_Ralan = 0;
        Jasa_Medik_Dokter_Laborat_Ralan = 0;
        Jasa_Medik_Petugas_Laborat_Ralan = 0;
        Kso_Laborat_Ralan = 0;
        Persediaan_Laborat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Radiologi_Ralan = 0;
        Jasa_Medik_Petugas_Radiologi_Ralan = 0;
        Kso_Radiologi_Ralan = 0;
        Persediaan_Radiologi_Rawat_Jalan = 0;
        Obat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Operasi_Ralan = 0;
        Jasa_Medik_Paramedis_Operasi_Ralan = 0;
        Obat_Operasi_Ralan = 0;

//        if (i <= 0) {
        if (chkBayar.isSelected() == false) {
            chkBayar.setText("Transaksi BELUM DIBAYAR");
            prosesCariReg();
            if ((chkLaborat.isSelected() == true) || (chkTarifDokter.isSelected() == true) || (chkTarifPrm.isSelected() == true) || (chkRadiologi.isSelected() == true)) {
                tabModeRwJlDr.addRow(new Object[]{true, "Tindakan", ":", "", null, null, null, null, "Ralan Dokter"});
            }
            if (chkTarifDokter.isSelected() == true) {
                prosesCariRwJlDr();
                prosesCariRwJlDrPr();
            }
            if (chkTarifPrm.isSelected() == true) {
                prosesCariRwJlPr();
            }
            if (chkLaborat.isSelected() == true) {
                prosesCariPeriksaLab();
            }
            if (chkRadiologi.isSelected() == true) {
                prosesCariRadiologi();
            }
            prosesCariOperasi();
            if (chkSarpras.isSelected() == true) {
                if (detailjs > 0) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Jasa Sarana dan Prasarana", ":", null, null, null, detailjs, "Ralan Dokter"});
                }
            }
            if (chkObat.isSelected() == true) {
                tabModeRwJlDr.addRow(new Object[]{true, "Obat & BHP", ":", "", null, null, null, null, "Obat"});
                prosesCariObat();
                if (detailbhp > 0) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Paket Obat/BHP", ":", null, null, null, detailbhp, "Ralan Dokter"});
                }
            }
            if (chkTambahan.isSelected() == true) {
                try {
                    pstambahan = koneksi.prepareStatement(sqlpstambahan);
                    try {
                        pstambahan.setString(1, TNoRw.getText());
                        rstambahan = pstambahan.executeQuery();
                        rstambahan.last();
                        if (rstambahan.getRow() > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                        } else {
                            tabModeRwJlDr.addRow(new Object[]{false, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                        }
                        rstambahan.beforeFirst();
                        while (rstambahan.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "", rstambahan.getString("nama_biaya"), ":",
                                rstambahan.getDouble("besar_biaya"), 1, null, rstambahan.getDouble("besar_biaya"), "Tambahan"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstambahan != null) {
                            rstambahan.close();
                        }
                        if (pstambahan != null) {
                            pstambahan.close();
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Notifikasi : " + ex);
                }
            }
            if (chkPotongan.isSelected() == true) {
                try {
                    pspotongan = koneksi.prepareStatement(sqlpspotongan);
                    try {
                        pspotongan.setString(1, TNoRw.getText());
                        rspotongan = pspotongan.executeQuery();
                        rspotongan.last();
                        if (rspotongan.getRow() > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                        } else {
                            tabModeRwJlDr.addRow(new Object[]{false, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                        }
                        rspotongan.beforeFirst();
                        while (rspotongan.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "", rspotongan.getString("nama_pengurangan"), ":",
                                rspotongan.getDouble("besar_pengurangan"), 1, null, (-1 * rspotongan.getDouble("besar_pengurangan")), "Potongan"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rspotongan != null) {
                            rspotongan.close();
                        }
                        if (pspotongan != null) {
                            pspotongan.close();
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Notifikasi : " + ex);
                }
            }
            TCari.setText("");
            TCari1.setText("");
            TNoNota.setText("");

            if (cekSimpan == 0) {
                isHitung();
                Valid.tabelKosong(tabModeAkunBayar);
                tampilAkunBayar();
                tampilAkunPiutang();
            }
            cekSimpan = 0;
            status = "belum";

//        } else if (i > 0) {
        } else if (chkBayar.isSelected() == true) {
            chkBayar.setText("Transaksi SUDAH DIBAYAR");
            Valid.SetTgl2(DTPTgl, Sequel.cariIsi("select concat(tanggal,' ',jam) from nota_jalan where no_rawat='" + TNoRw.getText() + "'"));
            Valid.tabelKosong(tabModeRwJlDr);
            try {
//                psbilling = koneksi.prepareStatement(sqlpsbilling);
                psbilling = koneksi.prepareStatement("select no,nm_perawatan, if(biaya<>0,biaya,null) as satu, if(jumlah<>0,jumlah,null) as dua,"
                        + "if(tambahan<>0,tambahan,null) as tiga, if(totalbiaya<>0,totalbiaya,null) as empat,pemisah,status "
                        + "from billing where no_rawat='" + TNoRw.getText() + "' and no_nota like '%" + TNoNota.getText() + "%' order by noindex");
                try {
//                    psbilling.setString(1, TNoRw.getText());                    
                    rsbilling = psbilling.executeQuery();
                    while (rsbilling.next()) {
                        if (!rsbilling.getString("status").equals("Tagihan")) {
                            tabModeRwJlDr.addRow(new Object[]{true, rsbilling.getString("no"),
                                rsbilling.getString("nm_perawatan"),
                                rsbilling.getString("pemisah"),
                                rsbilling.getObject("satu"),
                                rsbilling.getObject("dua"),
                                rsbilling.getObject("tiga"),
                                rsbilling.getObject("empat"),
                                rsbilling.getString("status")});
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsbilling != null) {
                        rsbilling.close();
                    }
                    if (psbilling != null) {
                        psbilling.close();
                    }
                }

                TCari.setText("");
                TCari1.setText("");
                tampilAkunBayarTersimpan();
                tampilAkunPiutangTersimpan();
                isHitung();
                status = "sudah";
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
        isKembali();
    }

    public void isRawat2() {
        Jasa_Medik_Dokter_Tindakan_Ralan = 0;
        Jasa_Medik_Paramedis_Tindakan_Ralan = 0;
        KSO_Tindakan_Ralan = 0;
        Jasa_Medik_Dokter_Laborat_Ralan = 0;
        Jasa_Medik_Petugas_Laborat_Ralan = 0;
        Kso_Laborat_Ralan = 0;
        Persediaan_Laborat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Radiologi_Ralan = 0;
        Jasa_Medik_Petugas_Radiologi_Ralan = 0;
        Kso_Radiologi_Ralan = 0;
        Persediaan_Radiologi_Rawat_Jalan = 0;
        Obat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Operasi_Ralan = 0;
        Jasa_Medik_Paramedis_Operasi_Ralan = 0;
        Obat_Operasi_Ralan = 0;

        prosesCariReg();
        if ((chkLaborat.isSelected() == true) || (chkTarifDokter.isSelected() == true) || (chkTarifPrm.isSelected() == true) || (chkRadiologi.isSelected() == true)) {
            tabModeRwJlDr.addRow(new Object[]{true, "Tindakan", ":", "", null, null, null, null, "Ralan Dokter"});
        }
        if (chkTarifDokter.isSelected() == true) {
            prosesCariRwJlDr();
            prosesCariRwJlDrPr();
        }
        if (chkTarifPrm.isSelected() == true) {
            prosesCariRwJlPr();
        }
        prosesCariOperasi();
        if (chkLaborat.isSelected() == true) {
            prosesCariPeriksaLab();
        }
        if (chkRadiologi.isSelected() == true) {
            prosesCariRadiologi();
        }
        if (chkSarpras.isSelected() == true) {
            if (detailjs > 0) {
                tabModeRwJlDr.addRow(new Object[]{true, "", "Jasa Sarana dan Prasarana", ":", null, null, null, detailjs, "Ralan Dokter"});
            }
        }
        if (chkObat.isSelected() == true) {
            tabModeRwJlDr.addRow(new Object[]{true, "Obat & BHP", ":", "", null, null, null, null, "Obat"});
            prosesCariObat();
            if (detailbhp > 0) {
                tabModeRwJlDr.addRow(new Object[]{true, "", "Paket Obat/BHP", ":", null, null, null, detailbhp, "Ralan Dokter"});
            }
        }
        if (chkTambahan.isSelected() == true) {
            try {
                pstambahan = koneksi.prepareStatement(sqlpstambahan);
                try {
                    pstambahan.setString(1, TNoRw.getText());
                    rstambahan = pstambahan.executeQuery();
                    rstambahan.last();
                    if (rstambahan.getRow() > 0) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                    }
                    rstambahan.beforeFirst();
                    while (rstambahan.next()) {
                        tabModeRwJlDr.addRow(new Object[]{true, "", rstambahan.getString("nama_biaya"), ":",
                            rstambahan.getDouble("besar_biaya"), 1, null, rstambahan.getDouble("besar_biaya"), "Tambahan"});
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rstambahan != null) {
                        rstambahan.close();
                    }
                    if (pstambahan != null) {
                        pstambahan.close();
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Notifikasi : " + ex);
            }
        }
        if (chkPotongan.isSelected() == true) {
            try {
                pspotongan = koneksi.prepareStatement(sqlpspotongan);
                try {
                    pspotongan.setString(1, TNoRw.getText());
                    rspotongan = pspotongan.executeQuery();
                    rspotongan.last();
                    if (rspotongan.getRow() > 0) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                    }
                    rspotongan.beforeFirst();
                    while (rspotongan.next()) {
                        tabModeRwJlDr.addRow(new Object[]{true, "", rspotongan.getString("nama_pengurangan"), ":",
                            rspotongan.getDouble("besar_pengurangan"), 1, null, (-1 * rspotongan.getDouble("besar_pengurangan")), "Potongan"});
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rspotongan != null) {
                        rspotongan.close();
                    }
                    if (pspotongan != null) {
                        pspotongan.close();
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Notifikasi : " + ex);
            }
        }

        isHitung();
        isKembali();
    }

    private void prosesCariReg() {
        Valid.tabelKosong(tabModeRwJlDr);
        nmPoli = "";
        try {
            psreg = koneksi.prepareStatement(sqlpsreg);
            try {
                psreg.setString(1, TNoRw.getText());
                rsreg = psreg.executeQuery();
                if (rsreg.next()) {
                    psRujuk = koneksi.prepareStatement("select p.nm_poli from rujukan_internal_poli r "
                            + "inner join poliklinik p on p.kd_poli = r.kd_poli where r.no_rawat = '" + TNoRw.getText() + "'");
                    try {
                        rsRujuk = psRujuk.executeQuery();
                        if (rsRujuk.next()) {
                            nmPoli = nmPoli + rsRujuk.getString("nm_poli") + ", ";
                        }

                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    //DTPTgl.setDate(rsreg.getDate("tgl_registrasi"));
                    tabModeRwJlDr.addRow(new Object[]{true, "No. Nota", ": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6), "", null, null, null, null, "-"});
                    pscaripoli = koneksi.prepareStatement(sqlpscaripoli);
                    try {
                        pscaripoli.setString(1, rsreg.getString("kd_poli"));
                        rscaripoli = pscaripoli.executeQuery();
                        if (rscaripoli.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Poliklinik/Inst.", ": " + rscaripoli.getString(1) + " (" + nmPoli + ")", "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Poliklinik/Inst.", ": ", "", null, null, null, null, "-"});
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscaripoli != null) {
                            rscaripoli.close();
                        }
                        if (pscaripoli != null) {
                            pscaripoli.close();
                        }
                    }

                    tabModeRwJlDr.addRow(new Object[]{true, "Tanggal & Jam", ": " + rsreg.getString("tgl_registrasi") + " " + rsreg.getString("jam"), "", null, null, null, null, "-"});
                    tabModeRwJlDr.addRow(new Object[]{true, "Pasien", ": " + TPasien.getText() + " No. RM. " + TNoRM.getText(), "", null, null, null, null, "-"});
                    pscarialamat = koneksi.prepareStatement(sqlpscarialamat);
                    try {
                        pscarialamat.setString(1, TNoRM.getText());
                        rscarialamat = pscarialamat.executeQuery();
                        if (rscarialamat.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Alamat Pasien", ": " + rscarialamat.getString(1), "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Alamat Pasien", ": ", "", null, null, null, null, "-"});
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscarialamat != null) {
                            rscarialamat.close();
                        }
                        if (pscarialamat != null) {
                            pscarialamat.close();
                        }
                    }

                    //cari dokter yang menangani 
                    if (centangdokterralan.equals("Yes")) {
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterralan);
                        try {
                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();
                            if (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, "Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, "", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    } else {
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterralan);
                        try {
                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();
                            if (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{false, "Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{false, "", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    }

                    if (chkAdministrasi.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Registrasi", ":", "", null, null, null, rsreg.getDouble("biaya_reg"), "Registrasi"});
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsreg != null) {
                    rsreg.close();
                }
                if (psreg != null) {
                    psreg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    private void prosesCariRwJlDr() {
        try {
            pscariralandokter = koneksi.prepareStatement(sqlpscariralandokter);
            try {
                pscariralandokter.setString(1, TNoRw.getText());
                rscariralandokter = pscariralandokter.executeQuery();
                subttl = 0;
                detailbhp = 0;
                detailjs = 0;
                while (rscariralandokter.next()) {
                    Jasa_Medik_Dokter_Tindakan_Ralan = Jasa_Medik_Dokter_Tindakan_Ralan + rscariralandokter.getDouble("totaltarif_tindakandr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralandokter.getString("nm_perawatan"));
                        pstamkur.setString(3, "Ralan Dokter");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }
                    if (rinciandokterralan.equals("Yes")) {
                        detailbhp = detailbhp + rscariralandokter.getDouble("totalbhp");
                        detailjs = detailjs + rscariralandokter.getDouble("totalmaterial");
                        tabModeRwJlDr.addRow(new Object[]{true, "", rscariralandokter.getString("nm_perawatan"), ":",
                            rscariralandokter.getDouble("tarif_tindakandr"), rscariralandokter.getDouble("jml"), tamkur, (rscariralandokter.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter"});
                        subttl = subttl + rscariralandokter.getDouble("totaltarif_tindakandr") + tamkur;
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{true, "", rscariralandokter.getString("nm_perawatan"), ":",
                            rscariralandokter.getDouble("total_byrdr"), rscariralandokter.getDouble("jml"), tamkur, (rscariralandokter.getDouble("biaya") + tamkur), "Ralan Dokter"});
                        subttl = subttl + rscariralandokter.getDouble("biaya") + tamkur;
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralandokter != null) {
                    rscariralandokter.close();
                }
                if (pscariralandokter != null) {
                    pscariralandokter.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void prosesCariRwJlDrPr() {
        try {
            pscariralandrpr = koneksi.prepareStatement(sqlpscariralandrpr);
            try {
                pscariralandrpr.setString(1, TNoRw.getText());
                rscariralandrpr = pscariralandrpr.executeQuery();
                subttl = 0;
                while (rscariralandrpr.next()) {
                    Jasa_Medik_Dokter_Tindakan_Ralan = Jasa_Medik_Dokter_Tindakan_Ralan + rscariralandrpr.getDouble("totaltarif_tindakandr");
                    Jasa_Medik_Paramedis_Tindakan_Ralan = Jasa_Medik_Paramedis_Tindakan_Ralan + rscariralandrpr.getDouble("totaltarif_tindakanpr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralandrpr.getString("nm_perawatan"));
                        pstamkur.setString(3, "Ralan Dokter Paramedis");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }

                    if (rinciandokterralan.equals("Yes")) {
                        detailbhp = detailbhp + rscariralandrpr.getDouble("totalbhp");
                        detailjs = detailjs + rscariralandrpr.getDouble("totalmaterial") + rscariralandrpr.getDouble("totaltarif_tindakanpr");
                        tabModeRwJlDr.addRow(new Object[]{true, "", rscariralandrpr.getString("nm_perawatan"), ":",
                            rscariralandrpr.getDouble("tarif_tindakandr"), rscariralandrpr.getDouble("jml"), tamkur, (rscariralandrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter Paramedis"});
                        subttl = subttl + rscariralandrpr.getDouble("totaltarif_tindakandr") + tamkur;
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{true, "", rscariralandrpr.getString("nm_perawatan"), ":",
                            rscariralandrpr.getDouble("total_byrdrpr"), rscariralandrpr.getDouble("jml"), tamkur, (rscariralandrpr.getDouble("biaya") + tamkur), "Ralan Dokter Paramedis"});
                        subttl = subttl + rscariralandrpr.getDouble("biaya") + tamkur;
                    }

                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralandrpr != null) {
                    rscariralandrpr.close();
                }
                if (pscariralandrpr != null) {
                    pscariralandrpr.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void prosesCariRwJlPr() {
        try {
            pscariralanperawat = koneksi.prepareStatement(sqlpscariralanperawat);
            try {
                pscariralanperawat.setString(1, TNoRw.getText());
                rscariralanperawat = pscariralanperawat.executeQuery();
                subttl = 0;
                while (rscariralanperawat.next()) {
                    Jasa_Medik_Paramedis_Tindakan_Ralan = Jasa_Medik_Paramedis_Tindakan_Ralan + rscariralanperawat.getDouble("totaltarif_tindakanpr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralanperawat.getString("nm_perawatan"));
                        pstamkur.setString(3, "Ralan Paramedis");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }

                    tabModeRwJlDr.addRow(new Object[]{true, "", rscariralanperawat.getString("nm_perawatan"), ":",
                        rscariralanperawat.getDouble("total_byrpr"), rscariralanperawat.getDouble("jml"), tamkur, (rscariralanperawat.getDouble("biaya") + tamkur), "Ralan Paramedis"});
                    subttl = subttl + rscariralanperawat.getDouble("biaya") + tamkur;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralanperawat != null) {
                    rscariralanperawat.close();
                }
                if (pscariralanperawat != null) {
                    pscariralanperawat.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void prosesCariPeriksaLab() {
        try {
            pscarilab = koneksi.prepareStatement(sqlpscarilab);
            try {
                pscarilab.setString(1, TNoRw.getText());
                rscarilab = pscarilab.executeQuery();
                subttl = 0;
                while (rscarilab.next()) {
                    Jasa_Medik_Dokter_Laborat_Ralan = Jasa_Medik_Dokter_Laborat_Ralan + rscarilab.getDouble("totaldokter");
                    Jasa_Medik_Petugas_Laborat_Ralan = Jasa_Medik_Petugas_Laborat_Ralan + rscarilab.getDouble("totalpetugas");
                    Kso_Laborat_Ralan = Kso_Laborat_Ralan + rscarilab.getDouble("totalkso");
                    Persediaan_Laborat_Rawat_Jalan = Persediaan_Laborat_Rawat_Jalan + rscarilab.getDouble("totalbhp");
                    psdetaillab = koneksi.prepareStatement(sqlpsdetaillab);
                    try {
                        psdetaillab.setString(1, TNoRw.getText());
                        psdetaillab.setString(2, rscarilab.getString("kd_jenis_prw"));
                        rsdetaillab = psdetaillab.executeQuery();
                        ralanparamedis = 0;
                        while (rsdetaillab.next()) {
                            Jasa_Medik_Dokter_Laborat_Ralan = Jasa_Medik_Dokter_Laborat_Ralan + rsdetaillab.getDouble("totaldokter");
                            Jasa_Medik_Petugas_Laborat_Ralan = Jasa_Medik_Petugas_Laborat_Ralan + rsdetaillab.getDouble("totalpetugas");
                            Kso_Laborat_Ralan = Kso_Laborat_Ralan + rsdetaillab.getDouble("totalkso");
                            Persediaan_Laborat_Rawat_Jalan = Persediaan_Laborat_Rawat_Jalan + rsdetaillab.getDouble("totalbhp");
                            ralanparamedis = rsdetaillab.getDouble("total");
                        }
                    } catch (Exception e) {
                        ralanparamedis = 0;
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsdetaillab != null) {
                            rsdetaillab.close();
                        }
                        if (psdetaillab != null) {
                            psdetaillab.close();
                        }
                    }
//                    tabModeRwJlDr.addRow(new Object[]{true, "", rscarilab.getString("nm_perawatan"), ":",
//                        rscarilab.getDouble("biaya"), rscarilab.getDouble("jml"), ralanparamedis, (rscarilab.getDouble("total") + ralanparamedis), "Laborat"});
                    subttl = subttl + rscarilab.getDouble("total") + ralanparamedis;
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscarilab != null) {
                    rscarilab.close();
                }
                if (pscarilab != null) {
                    pscarilab.close();
                }
            }

            pscarilab = koneksi.prepareStatement(sqlpscarilab);
            pscarilab.setString(1, TNoRw.getText());
            rscarilab = pscarilab.executeQuery();
            if (rscarilab.next()) {
                psdetaillab = koneksi.prepareStatement(
                        "SELECT tl.Pemeriksaan,dpl.biaya_item,count(tl.Pemeriksaan) jumlah,sum(dpl.biaya_item) total "
                        + "FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template = dpl.id_template "
                        + "WHERE dpl.no_rawat = ? group by tl.Pemeriksaan ");
                try {
                    psdetaillab.setString(1, TNoRw.getText());
//                        psdetaillab.setString(2, rsperiksalab.getString("kd_jenis_prw"));
                    rsdetaillab = psdetaillab.executeQuery();
                    lab = 0;
                    while (rsdetaillab.next()) {
                        lab = 0;
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdetaillab.getString("Pemeriksaan"), ":",
                            rsdetaillab.getDouble("biaya_item"), rsdetaillab.getDouble("jumlah"), lab, (rsdetaillab.getDouble("total") + lab), "Laborat"});
//                        subttl = subttl + rsdetaillab.getDouble("total") + lab;
                    }

                    if (subttl > 1) {
                        tabModeRwJlDr.addRow(new Object[]{true, "", "Total Periksa Lab : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlLaborat"});
                    }
                } catch (Exception e) {
                    System.out.println("Notif Detail Lab : " + e);
                } finally {
                    if (rsdetaillab != null) {
                        rsdetaillab.close();
                    }
                    if (psdetaillab != null) {
                        psdetaillab.close();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void prosesCariRadiologi() {
        try {
            pscariradiologi = koneksi.prepareStatement(sqlpscariradiologi);
            try {
                pscariradiologi.setString(1, TNoRw.getText());
                rscariradiologi = pscariradiologi.executeQuery();
                subttl = 0;
                while (rscariradiologi.next()) {
                    Jasa_Medik_Dokter_Radiologi_Ralan = Jasa_Medik_Dokter_Radiologi_Ralan + rscariradiologi.getDouble("totaldokter");
                    Jasa_Medik_Petugas_Radiologi_Ralan = Jasa_Medik_Petugas_Radiologi_Ralan + rscariradiologi.getDouble("totalpetugas");
                    Kso_Radiologi_Ralan = Kso_Radiologi_Ralan + rscariradiologi.getDouble("totalkso");
                    Persediaan_Radiologi_Rawat_Jalan = Persediaan_Radiologi_Rawat_Jalan + rscariradiologi.getDouble("totalbhp");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariradiologi.getString("nm_perawatan"));
                        pstamkur.setString(3, "Radiologi");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }

                    tabModeRwJlDr.addRow(new Object[]{true, "", rscariradiologi.getString("nm_perawatan"), ":",
                        rscariradiologi.getDouble("biaya"), rscariradiologi.getDouble("jml"), tamkur, (rscariradiologi.getDouble("total") + tamkur), "Radiologi"});
                    subttl = subttl + rscariradiologi.getDouble("total") + tamkur;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariradiologi != null) {
                    rscariradiologi.close();
                }
                if (pscariradiologi != null) {
                    pscariradiologi.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void prosesCariObat() {
        subttl = 0;
        try {
            psobatlangsung = koneksi.prepareStatement(sqlpsobatlangsung);
            try {
                psobatlangsung.setString(1, TNoRw.getText());
                rsobatlangsung = psobatlangsung.executeQuery();
                if (rsobatlangsung.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Obat & BHP ", ":", rsobatlangsung.getDouble("besar_tagihan"), 1, 0, rsobatlangsung.getDouble("besar_tagihan"), "Obat"});
                    subttl = subttl + rsobatlangsung.getDouble("besar_tagihan");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobatlangsung != null) {
                    rsobatlangsung.close();
                }
                if (psobatlangsung != null) {
                    psobatlangsung.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            pscariobat = koneksi.prepareStatement(sqlpscariobat);
            try {
                pscariobat.setString(1, TNoRw.getText());
                rscariobat = pscariobat.executeQuery();
                //embalase=0;
                if (centangobatralan.equals("Yes")) {
                    while (rscariobat.next()) {
                        Obat_Rawat_Jalan = Obat_Rawat_Jalan + rscariobat.getDouble("totalbeli");
                        tabModeRwJlDr.addRow(new Object[]{true, "", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                    }
                } else {
                    while (rscariobat.next()) {
                        Obat_Rawat_Jalan = Obat_Rawat_Jalan + rscariobat.getDouble("totalbeli");
                        tabModeRwJlDr.addRow(new Object[]{false, "", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariobat != null) {
                    rscariobat.close();
                }
                if (pscariobat != null) {
                    pscariobat.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psobatoperasi = koneksi.prepareStatement(sqlpsobatoperasi);
            try {
                psobatoperasi.setString(1, TNoRw.getText());
                rsobatoperasi = psobatoperasi.executeQuery();
                if (centangobatralan.equals("Yes")) {
                    while (rsobatoperasi.next()) {
                        Obat_Operasi_Ralan = Obat_Operasi_Ralan + rsobatoperasi.getDouble("total");
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsobatoperasi.getString("nm_obat"), ":",
                            rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                            rsobatoperasi.getDouble("total"), "Obat"});
                        subttl = subttl + rsobatoperasi.getDouble("total");
                    }
                } else {
                    while (rsobatoperasi.next()) {
                        Obat_Operasi_Ralan = Obat_Operasi_Ralan + rsobatoperasi.getDouble("total");
                        tabModeRwJlDr.addRow(new Object[]{false, "                           ", rsobatoperasi.getString("nm_obat"), ":",
                            rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                            rsobatoperasi.getDouble("total"), "Obat"});
                        subttl = subttl + rsobatoperasi.getDouble("total");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobatoperasi != null) {
                    rsobatoperasi.close();
                }
                if (psobatoperasi != null) {
                    psobatoperasi.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psreturobat = koneksi.prepareStatement(sqlpsreturobat);
            try {
                psreturobat.setString(1, TNoRw.getText());

                rsreturobat = psreturobat.executeQuery();
                if (rsreturobat.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Retur Obat :", "", null, null, null, null, "Retur Obat"});
                }
                rsreturobat.beforeFirst();
                while (rsreturobat.next()) {
                    Object[] data = {true, "", rsreturobat.getString("nama_brng"), ":",
                        rsreturobat.getDouble("h_retur"), rsreturobat.getDouble("jml"), null,
                        rsreturobat.getDouble("ttl"), "Retur Obat"};
                    tabModeRwJlDr.addRow(data);
                    subttl = subttl + rsreturobat.getDouble("ttl");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsreturobat != null) {
                    rsreturobat.close();
                }
                if (psreturobat != null) {
                    psreturobat.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        if (subttl > 0) {
            if (tampilkan_ppnobat_ralan.equals("Yes")) {
                ppnobat = Valid.roundUp(subttl * 0.1, 100);
                if (centangobatralan.equals("Yes")) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                } else {
                    tabModeRwJlDr.addRow(new Object[]{false, "", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                }
                tabModeRwJlDr.addRow(new Object[]{true, "", "" + Valid.SetAngka3(subttl + ppnobat), "", null, null, null, null, "TtlObat"});
            } else {
                tabModeRwJlDr.addRow(new Object[]{true, "Tot. Biaya Farm.", ": " + Valid.SetAngka3(subttl), "", null, null, null, null, "TtlObat"});
            }
        }
    }

    private void isHitung() {
        ttl = 0;
        y = 0;
        ttlLaborat = 0;
        ttlRadiologi = 0;
        ttlObat = 0;
        ttlRalan_Dokter = 0;
        ttlRalan_Paramedis = 0;
        ttlTambahan = 0;
        ttlPotongan = 0;
        ttlRegistrasi = 0;
        ttlRalan_Dokter_Param = 0;
        ttlOperasi = 0;
        int a = tbBilling.getRowCount();
        for (r = 0; r < a; r++) {
            try {
                y = Double.parseDouble(tabModeRwJlDr.getValueAt(r, 7).toString());
            } catch (Exception e) {
                y = 0;
            }
            switch (tabModeRwJlDr.getValueAt(r, 8).toString()) {
                case "Laborat":
                    ttlLaborat = ttlLaborat + y;
                    break;
                case "Radiologi":
                    ttlRadiologi = ttlRadiologi + y;
                    break;
                case "Obat":
                    ttlObat = ttlObat + y;
                    break;
                case "Ralan Dokter":
                    ttlRalan_Dokter = ttlRalan_Dokter + y;
                    break;
                case "Ralan Dokter Paramedis":
                    ttlRalan_Dokter_Param = ttlRalan_Dokter_Param + y;
                    break;
                case "Ralan Paramedis":
                    ttlRalan_Paramedis = ttlRalan_Paramedis + y;
                    break;
                case "Tambahan":
                    ttlTambahan = ttlTambahan + y;
                    break;
                case "Potongan":
                    ttlPotongan = ttlPotongan + y;
                    break;
                case "Registrasi":
                    ttlRegistrasi = ttlRegistrasi + y;
                    break;
                case "Operasi":
                    ttlOperasi = ttlOperasi + y;
                    break;
            }
            ttl = ttl + y;
        }
        //TtlSemua.setText(Valid.SetAngka3(ttl));
        TtlSemua.setText(Valid.SetAngka3(Valid.roundUp(ttl, 0)));
        ttl = Valid.roundUp(ttl, 0);
    }

    public void isCek() {
        Valid.tabelKosong(tabModeAkunBayar);
        Valid.tabelKosong(tabModeAkunPiutang);
        DTPTgl.setDate(new Date());
        BtnNota.setEnabled(akses.getbilling_ralan());
        BtnSimpan.setEnabled(akses.getbilling_ralan());
        BtnView.setEnabled(akses.getbilling_ralan());
        MnRawatJalan.setEnabled(akses.gettindakan_ralan());
        MnPemberianObat.setEnabled(akses.getberi_obat());
        MnInputObat.setEnabled(akses.getberi_obat());
        MnOperasi.setEnabled(akses.getoperasi());
        MnObatLangsung.setEnabled(akses.getberi_obat());
        MnTambahan.setEnabled(akses.gettambahan_biaya());
        MnPotongan.setEnabled(akses.getpotongan_biaya());
        MnPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnCariPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnCariRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnPenjualan.setEnabled(akses.getpenjualan_obat());
        MnHapusTagihan.setEnabled(akses.getbilling_ralan());
        MnPoli.setEnabled(akses.getbilling_ralan());
        MnDokter.setEnabled(akses.getbilling_ralan());
        MnPenjab.setEnabled(akses.getbilling_ralan());
        MnNamaTelahTerima.setEnabled(akses.getbilling_ralan());
        if (Sequel.cariIsi("select tampilkan_tombol_nota_ralan from set_nota").equals("Yes")) {
            BtnNota.setVisible(true);
            BtnNota.setEnabled(true);
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                BtnNota.setVisible(true);
                BtnNota.setEnabled(true);
            } else {
                BtnNota.setVisible(false);
                BtnNota.setEnabled(false);
            }
        }
    }

    public void isKembali() {
        bayar = 0;
        total = 0;
        ppn = 0;
        besarppn = 0;
        tagihanppn = 0;
        y = 0;
        piutang = 0;
        kekurangan = 0;
        countbayar = 0;

        row2 = tabModeAkunBayar.getRowCount();
        for (r = 0; r < row2; r++) {
            if (!tabModeAkunBayar.getValueAt(r, 2).toString().equals("")) {
                countbayar++;
                try {
                    bayar = bayar + Double.parseDouble(tabModeAkunBayar.getValueAt(r, 2).toString());
                } catch (Exception e) {
                    bayar = bayar + 0;
                }
            }

            if (!tabModeAkunBayar.getValueAt(r, 4).toString().equals("")) {
                try {
                    besarppn = besarppn + Valid.roundUp(Double.parseDouble(tabModeAkunBayar.getValueAt(r, 4).toString()), 100);
                } catch (Exception e) {
                    besarppn = besarppn + 0;
                }
            }
        }

        row2 = tabModeAkunPiutang.getRowCount();
        for (r = 0; r < row2; r++) {
            if (!tabModeAkunPiutang.getValueAt(r, 3).toString().equals("")) {
                try {
                    piutang = piutang + Double.parseDouble(tabModeAkunPiutang.getValueAt(r, 3).toString());
                } catch (Exception e) {
                    piutang = piutang + 0;
                }
            }
        }

        if (ttl > 0) {
            total = ttl;
        }

        tagihanppn = besarppn + total;
        TagihanPPn.setText(Valid.SetAngka3(Valid.roundUp(tagihanppn, 0)));
        tagihanppn = Valid.roundUp(tagihanppn, 0);

        if (piutang <= 0) {
            kekurangan = (bayar + besarppn) - tagihanppn;
            jLabel5.setText("Bayar : Rp.");
            if (kekurangan < 0) {
                jLabel6.setText("Kekurangan : Rp.");
            } else {
                jLabel6.setText("Kembali : Rp.");
            }

            TKembali.setText(Valid.SetAngka3(Valid.roundUp(kekurangan, 0)));
            kekurangan = Valid.roundUp(kekurangan, 0);
        } else {
            kekurangan = (tagihanppn - (bayar + besarppn) - piutang) * -1;
            jLabel5.setText("Uang Muka : Rp.");
            if (kekurangan > 0) {
                jLabel6.setText("Kelebihan : Rp.");
            } else {
                jLabel6.setText("Kekurangan : Rp.");
            }

            TKembali.setText(Valid.SetAngka3(Valid.roundUp(kekurangan, 0)));
            kekurangan = Valid.roundUp(kekurangan, 0);
        }

    }

    public void tampilTambahan(String NoRawat) {
        norawat.setText(NoRawat);
        Valid.tabelKosong(tabModeTambahan);
        try {
            pstambahan = koneksi.prepareStatement(sqlpstambahan);
            try {
                pstambahan.setString(1, norawat.getText());
                rstambahan = pstambahan.executeQuery();
                while (rstambahan.next()) {
                    tabModeTambahan.addRow(new Object[]{rstambahan.getString(1), rstambahan.getString(2)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rstambahan != null) {
                    rstambahan.close();
                }
                if (pstambahan != null) {
                    pstambahan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilPotongan(String NoRawat) {
        norawatpotongan.setText(NoRawat);
        Valid.tabelKosong(tabModePotongan);
        try {
            pspotongan = koneksi.prepareStatement(sqlpspotongan);
            try {
                pspotongan.setString(1, TNoRw.getText());
                rspotongan = pspotongan.executeQuery();
                while (rspotongan.next()) {
                    tabModePotongan.addRow(new Object[]{rspotongan.getString(1), rspotongan.getString(2)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rspotongan != null) {
                    rspotongan.close();
                }
                if (pspotongan != null) {
                    pspotongan.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void prosesCariOperasi() {
        try {
            subttl = 0;
            psoperasi = koneksi.prepareStatement(sqlpsoperasi);
            try {
                psoperasi.setString(1, TNoRw.getText());
                rsoperasi = psoperasi.executeQuery();
                if (rsoperasi.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "Operasi", ":", "", null, null, null, null, "Operasi"});
                }
                rsoperasi.beforeFirst();
                if (rincianoperasi.equals("Yes")) {
                    while (rsoperasi.next()) {
                        Jasa_Medik_Dokter_Operasi_Ralan = Jasa_Medik_Dokter_Operasi_Ralan + rsoperasi.getDouble("biayaoperator1")
                                + rsoperasi.getDouble("biayaoperator2") + rsoperasi.getDouble("biayaoperator3") + rsoperasi.getDouble("biayadokter_anak")
                                + rsoperasi.getDouble("biayadokter_anestesi") + rsoperasi.getDouble("biaya_dokter_pjanak") + rsoperasi.getDouble("biaya_dokter_umum");
                        Jasa_Medik_Paramedis_Operasi_Ralan = Jasa_Medik_Paramedis_Operasi_Ralan + rsoperasi.getDouble("biayaasisten_operator1")
                                + rsoperasi.getDouble("biayaasisten_operator2") + rsoperasi.getDouble("biayaasisten_operator3") + rsoperasi.getDouble("biayainstrumen") + rsoperasi.getDouble("biayaperawaat_resusitas")
                                + rsoperasi.getDouble("biayaasisten_anestesi") + rsoperasi.getDouble("biayaasisten_anestesi2") + rsoperasi.getDouble("biayabidan") + rsoperasi.getDouble("biayabidan2")
                                + rsoperasi.getDouble("biayabidan3") + rsoperasi.getDouble("biayaperawat_luar") + rsoperasi.getDouble("biaya_omloop")
                                + rsoperasi.getDouble("biaya_omloop2") + rsoperasi.getDouble("biaya_omloop3") + rsoperasi.getDouble("biaya_omloop4") + rsoperasi.getDouble("biaya_omloop5");

                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsoperasi.getString("nm_perawatan"), ":", null, null, null, null, "Operasi"});
//                        if (rsoperasi.getDouble("biayaoperator1") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 1", ":", rsoperasi.getDouble("biayaoperator1"), 1, 0, rsoperasi.getDouble("biayaoperator1"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaoperator2") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 2", ":", rsoperasi.getDouble("biayaoperator2"), 1, 0, rsoperasi.getDouble("biayaoperator2"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaoperator3") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 3", ":", rsoperasi.getDouble("biayaoperator3"), 1, 0, rsoperasi.getDouble("biayaoperator3"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaasisten_operator1") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 1", ":", rsoperasi.getDouble("biayaasisten_operator1"), 1, 0, rsoperasi.getDouble("biayaasisten_operator1"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaasisten_operator2") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 2", ":", rsoperasi.getDouble("biayaasisten_operator2"), 1, 0, rsoperasi.getDouble("biayaasisten_operator2"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaasisten_operator3") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 3", ":", rsoperasi.getDouble("biayaasisten_operator3"), 1, 0, rsoperasi.getDouble("biayaasisten_operator3"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayainstrumen") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Instrumen", ":", rsoperasi.getDouble("biayainstrumen"), 1, 0, rsoperasi.getDouble("biayainstrumen"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayadokter_anak") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anak", ":", rsoperasi.getDouble("biayadokter_anak"), 1, 0, rsoperasi.getDouble("biayadokter_anak"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaperawaat_resusitas") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Resusitas", ":", rsoperasi.getDouble("biayaperawaat_resusitas"), 1, 0, rsoperasi.getDouble("biayaperawaat_resusitas"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayadokter_anestesi") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anastesi", ":", rsoperasi.getDouble("biayadokter_anestesi"), 1, 0, rsoperasi.getDouble("biayadokter_anestesi"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaasisten_anestesi") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 1", ":", rsoperasi.getDouble("biayaasisten_anestesi"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaasisten_anestesi2") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 2", ":", rsoperasi.getDouble("biayaasisten_anestesi2"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi2"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayabidan") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 1", ":", rsoperasi.getDouble("biayabidan"), 1, 0, rsoperasi.getDouble("biayabidan"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayabidan2") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 2", ":", rsoperasi.getDouble("biayabidan2"), 1, 0, rsoperasi.getDouble("biayabidan2"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayabidan3") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 3", ":", rsoperasi.getDouble("biayabidan3"), 1, 0, rsoperasi.getDouble("biayabidan3"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaperawat_luar") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Luar", ":", rsoperasi.getDouble("biayaperawat_luar"), 1, 0, rsoperasi.getDouble("biayaperawat_luar"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayaalat") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Alat", ":", rsoperasi.getDouble("biayaalat"), 1, 0, rsoperasi.getDouble("biayaalat"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayasewaok") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Sewa OK/VK", ":", rsoperasi.getDouble("biayasewaok"), 1, 0, rsoperasi.getDouble("biayasewaok"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("akomodasi") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Akomodasi", ":", rsoperasi.getDouble("akomodasi"), 1, 0, rsoperasi.getDouble("akomodasi"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_omloop") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 1", ":", rsoperasi.getDouble("biaya_omloop"), 1, 0, rsoperasi.getDouble("biaya_omloop"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_omloop2") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 2", ":", rsoperasi.getDouble("biaya_omloop2"), 1, 0, rsoperasi.getDouble("biaya_omloop2"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_omloop3") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 3", ":", rsoperasi.getDouble("biaya_omloop3"), 1, 0, rsoperasi.getDouble("biaya_omloop3"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_omloop4") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 4", ":", rsoperasi.getDouble("biaya_omloop4"), 1, 0, rsoperasi.getDouble("biaya_omloop4"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_omloop5") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 5", ":", rsoperasi.getDouble("biaya_omloop5"), 1, 0, rsoperasi.getDouble("biaya_omloop5"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("bagian_rs") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  N.M.S.", ":", rsoperasi.getDouble("bagian_rs"), 1, 0, rsoperasi.getDouble("bagian_rs"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biayasarpras") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Sarpras", ":", rsoperasi.getDouble("biayasarpras"), 1, 0, rsoperasi.getDouble("biayasarpras"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_dokter_pjanak") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter PJ Anak", ":", rsoperasi.getDouble("biaya_dokter_pjanak"), 1, 0, rsoperasi.getDouble("biaya_dokter_pjanak"), "Operasi"});
//                        }
//
//                        if (rsoperasi.getDouble("biaya_dokter_umum") > 0) {
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Umum", ":", rsoperasi.getDouble("biaya_dokter_umum"), 1, 0, rsoperasi.getDouble("biaya_dokter_umum"), "Operasi"});
//                        }
                        if (rsoperasi.getDouble("biayaoperator1") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 1 (" + rsoperasi.getString("dokter_operator1") + ")", ":", rsoperasi.getDouble("biayaoperator1"), 1, 0, rsoperasi.getDouble("biayaoperator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 2 (" + rsoperasi.getString("dokter_operator2") + ")", ":", rsoperasi.getDouble("biayaoperator2"), 1, 0, rsoperasi.getDouble("biayaoperator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 3 (" + rsoperasi.getString("dokter_operator3") + ")", ":", rsoperasi.getDouble("biayaoperator3"), 1, 0, rsoperasi.getDouble("biayaoperator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator1") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 1 (" + rsoperasi.getString("asisten_operator1") + ")", ":", rsoperasi.getDouble("biayaasisten_operator1"), 1, 0, rsoperasi.getDouble("biayaasisten_operator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 2 (" + rsoperasi.getString("asisten_operator2") + ")", ":", rsoperasi.getDouble("biayaasisten_operator2"), 1, 0, rsoperasi.getDouble("biayaasisten_operator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 3 (" + rsoperasi.getString("asisten_operator3") + ")", ":", rsoperasi.getDouble("biayaasisten_operator3"), 1, 0, rsoperasi.getDouble("biayaasisten_operator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayainstrumen") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Instrumen (" + rsoperasi.getString("instrumen") + ")", ":", rsoperasi.getDouble("biayainstrumen"), 1, 0, rsoperasi.getDouble("biayainstrumen"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anak") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anak (" + rsoperasi.getString("dokter_anak") + ")", ":", rsoperasi.getDouble("biayadokter_anak"), 1, 0, rsoperasi.getDouble("biayadokter_anak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawaat_resusitas") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Resusitas (" + rsoperasi.getString("perawaat_resusitas") + ")", ":", rsoperasi.getDouble("biayaperawaat_resusitas"), 1, 0, rsoperasi.getDouble("biayaperawaat_resusitas"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anestesi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anastesi (" + rsoperasi.getString("dokter_anestesi") + ")", ":", rsoperasi.getDouble("biayadokter_anestesi"), 1, 0, rsoperasi.getDouble("biayadokter_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 1 (" + rsoperasi.getString("asisten_anestesi") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 2 (" + rsoperasi.getString("asisten_anestesi2") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi2"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 1 (" + rsoperasi.getString("bidan") + ")", ":", rsoperasi.getDouble("biayabidan"), 1, 0, rsoperasi.getDouble("biayabidan"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 2 (" + rsoperasi.getString("bidan2") + ")", ":", rsoperasi.getDouble("biayabidan2"), 1, 0, rsoperasi.getDouble("biayabidan2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 3 (" + rsoperasi.getString("bidan3") + ")", ":", rsoperasi.getDouble("biayabidan3"), 1, 0, rsoperasi.getDouble("biayabidan3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawat_luar") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Luar (" + rsoperasi.getString("perawat_luar") + ")", ":", rsoperasi.getDouble("biayaperawat_luar"), 1, 0, rsoperasi.getDouble("biayaperawat_luar"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaalat") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Alat", ":", rsoperasi.getDouble("biayaalat"), 1, 0, rsoperasi.getDouble("biayaalat"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasewaok") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Sewa OK/VK", ":", rsoperasi.getDouble("biayasewaok"), 1, 0, rsoperasi.getDouble("biayasewaok"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("akomodasi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Akomodasi", ":", rsoperasi.getDouble("akomodasi"), 1, 0, rsoperasi.getDouble("akomodasi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 1 (" + rsoperasi.getString("omloop") + ")", ":", rsoperasi.getDouble("biaya_omloop"), 1, 0, rsoperasi.getDouble("biaya_omloop"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 2 (" + rsoperasi.getString("omloop2") + ")", ":", rsoperasi.getDouble("biaya_omloop2"), 1, 0, rsoperasi.getDouble("biaya_omloop2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 3 (" + rsoperasi.getString("omloop3") + ")", ":", rsoperasi.getDouble("biaya_omloop3"), 1, 0, rsoperasi.getDouble("biaya_omloop3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop4") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 4 (" + rsoperasi.getString("omloop4") + ")", ":", rsoperasi.getDouble("biaya_omloop4"), 1, 0, rsoperasi.getDouble("biaya_omloop4"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop5") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 5 (" + rsoperasi.getString("omloop5") + ")", ":", rsoperasi.getDouble("biaya_omloop5"), 1, 0, rsoperasi.getDouble("biaya_omloop5"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("bagian_rs") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  N.M.S.", ":", rsoperasi.getDouble("bagian_rs"), 1, 0, rsoperasi.getDouble("bagian_rs"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasarpras") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Sarpras", ":", rsoperasi.getDouble("biayasarpras"), 1, 0, rsoperasi.getDouble("biayasarpras"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_pjanak") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter PJ Anak", ":", rsoperasi.getDouble("biaya_dokter_pjanak"), 1, 0, rsoperasi.getDouble("biaya_dokter_pjanak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_umum") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Umum", ":", rsoperasi.getDouble("biaya_dokter_umum"), 1, 0, rsoperasi.getDouble("biaya_dokter_umum"), "Operasi"});
                        }
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                } else {
                    while (rsoperasi.next()) {
                        Jasa_Medik_Dokter_Operasi_Ralan = Jasa_Medik_Dokter_Operasi_Ralan + rsoperasi.getDouble("biayaoperator1")
                                + rsoperasi.getDouble("biayaoperator2") + rsoperasi.getDouble("biayaoperator3") + rsoperasi.getDouble("biayadokter_anak")
                                + rsoperasi.getDouble("biayadokter_anestesi") + rsoperasi.getDouble("biaya_dokter_pjanak") + rsoperasi.getDouble("biaya_dokter_umum");
                        Jasa_Medik_Paramedis_Operasi_Ralan = Jasa_Medik_Paramedis_Operasi_Ralan + rsoperasi.getDouble("biayaasisten_operator1")
                                + rsoperasi.getDouble("biayaasisten_operator2") + rsoperasi.getDouble("biayaasisten_operator3") + rsoperasi.getDouble("biayainstrumen") + rsoperasi.getDouble("biayaperawaat_resusitas")
                                + rsoperasi.getDouble("biayaasisten_anestesi") + rsoperasi.getDouble("biayaasisten_anestesi2") + rsoperasi.getDouble("biayabidan") + rsoperasi.getDouble("biayabidan2")
                                + rsoperasi.getDouble("biayabidan3") + rsoperasi.getDouble("biayaperawat_luar") + rsoperasi.getDouble("biaya_omloop")
                                + rsoperasi.getDouble("biaya_omloop2") + rsoperasi.getDouble("biaya_omloop3") + rsoperasi.getDouble("biaya_omloop4") + rsoperasi.getDouble("biaya_omloop5");
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsoperasi.getString("nm_perawatan"), ":", rsoperasi.getDouble("biaya"), 1, 0, rsoperasi.getDouble("biaya"), "Operasi"});
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsoperasi != null) {
                    rsoperasi.close();
                }
                if (psoperasi != null) {
                    psoperasi.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setPiutang() {
        chkRadiologi.setSelected(true);
        chkLaborat.setSelected(true);
    }

    private void tampilAkunBayar() {
        try {
            jml = 0;
            for (z = 0; z < tbAkunBayar.getRowCount(); z++) {
                if (!tbAkunBayar.getValueAt(z, 2).toString().equals("")) {
                    jml++;
                }
            }
            Nama_Akun_Bayar = null;
            Kode_Rek_Bayar = null;
            Bayar = null;
            PPN_Persen = null;
            PPN_Besar = null;
            Nama_Akun_Bayar = new String[jml];
            Kode_Rek_Bayar = new String[jml];
            Bayar = new String[jml];
            PPN_Persen = new String[jml];
            PPN_Besar = new String[jml];

            jml = 0;
            for (z = 0; z < tbAkunBayar.getRowCount(); z++) {
                if (!tbAkunBayar.getValueAt(z, 2).toString().equals("")) {
                    Nama_Akun_Bayar[jml] = tbAkunBayar.getValueAt(z, 0).toString();
                    Kode_Rek_Bayar[jml] = tbAkunBayar.getValueAt(z, 1).toString();
                    Bayar[jml] = tbAkunBayar.getValueAt(z, 2).toString();
                    PPN_Persen[jml] = tbAkunBayar.getValueAt(z, 3).toString();
                    PPN_Besar[jml] = tbAkunBayar.getValueAt(z, 4).toString();
                    jml++;
                }
            }

            Valid.tabelKosong(tabModeAkunBayar);

            for (z = 0; z < jml; z++) {
                tabModeAkunBayar.addRow(new Object[]{
                    Nama_Akun_Bayar[z], Kode_Rek_Bayar[z], Bayar[z], PPN_Persen[z], PPN_Besar[z]
                });
            }

            psakunbayar = koneksi.prepareStatement("select * from akun_bayar where nama_bayar like ? order by nama_bayar");
            try {
                psakunbayar.setString(1, "%" + TCari.getText() + "%");
                rsakunbayar = psakunbayar.executeQuery();
                while (rsakunbayar.next()) {
                    tabModeAkunBayar.addRow(new Object[]{rsakunbayar.getString(1), rsakunbayar.getString(2), "", rsakunbayar.getDouble(3), ""});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsakunbayar != null) {
                    rsakunbayar.close();
                }
                if (psakunbayar != null) {
                    psakunbayar.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunBayarTersimpan() {
        try {
            Valid.tabelKosong(tabModeAkunBayar);

            psakunbayar = koneksi.prepareStatement(
                    "select akun_bayar.nama_bayar,akun_bayar.kd_rek,detail_nota_jalan.besar_bayar,"
                    + "akun_bayar.ppn,detail_nota_jalan.besarppn from akun_bayar inner join detail_nota_jalan "
                    + "on akun_bayar.nama_bayar=detail_nota_jalan.nama_bayar where detail_nota_jalan.no_rawat=? and akun_bayar.nama_bayar like ? order by nama_bayar");
            try {
                psakunbayar.setString(1, TNoRw.getText());
                psakunbayar.setString(2, "%" + TCari.getText() + "%");
                rsakunbayar = psakunbayar.executeQuery();
                while (rsakunbayar.next()) {
                    tabModeAkunBayar.addRow(new Object[]{rsakunbayar.getString(1), rsakunbayar.getString(2), rsakunbayar.getString(3), rsakunbayar.getString(4), rsakunbayar.getString(5)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Bayar Tersimpan : " + e);
            } finally {
                if (rsakunbayar != null) {
                    rsakunbayar.close();
                }
                if (psakunbayar != null) {
                    psakunbayar.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunPiutang() {
        try {
            jml = 0;
            for (z = 0; z < tbAkunPiutang.getRowCount(); z++) {
                if (!tbAkunPiutang.getValueAt(z, 3).toString().equals("")) {
                    jml++;
                }
            }

            Nama_Akun_Piutang = null;
            Nama_Akun_Piutang = new String[jml];
            Kode_Rek_Piutang = null;
            Kode_Rek_Piutang = new String[jml];
            Kd_PJ = null;
            Kd_PJ = new String[jml];
            Besar_Piutang = null;
            Besar_Piutang = new String[jml];
            Jatuh_Tempo = null;
            Jatuh_Tempo = new String[jml];

            jml = 0;
            for (z = 0; z < tbAkunPiutang.getRowCount(); z++) {
                if (!tbAkunPiutang.getValueAt(z, 3).toString().equals("")) {
                    Nama_Akun_Piutang[jml] = tbAkunPiutang.getValueAt(z, 0).toString();
                    Kode_Rek_Piutang[jml] = tbAkunPiutang.getValueAt(z, 1).toString();
                    Kd_PJ[jml] = tbAkunPiutang.getValueAt(z, 2).toString();
                    Besar_Piutang[jml] = tbAkunPiutang.getValueAt(z, 3).toString();
                    Jatuh_Tempo[jml] = tbAkunPiutang.getValueAt(z, 4).toString();
                    jml++;
                }
            }

            Valid.tabelKosong(tabModeAkunPiutang);

            for (z = 0; z < jml; z++) {
                tabModeAkunPiutang.addRow(new Object[]{
                    Nama_Akun_Piutang[z], Kode_Rek_Piutang[z], Kd_PJ[z], Besar_Piutang[z], Jatuh_Tempo[z]
                });
            }

            psakunpiutang = koneksi.prepareStatement("select * from akun_piutang where nama_bayar like ? order by nama_bayar");
            try {
                psakunpiutang.setString(1, "%" + TCari1.getText() + "%");
                rsakunpiutang = psakunpiutang.executeQuery();
                while (rsakunpiutang.next()) {
                    tabModeAkunPiutang.addRow(new Object[]{rsakunpiutang.getString(1), rsakunpiutang.getString(2), rsakunpiutang.getString(3), "", DTPTgl.getSelectedItem().toString().substring(0, 10)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsakunpiutang != null) {
                    rsakunpiutang.close();
                }
                if (psakunpiutang != null) {
                    psakunpiutang.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunPiutangTersimpan() {
        try {
            Valid.tabelKosong(tabModeAkunPiutang);
            psakunpiutang = koneksi.prepareStatement(
                    "select akun_piutang.nama_bayar,akun_piutang.kd_rek,akun_piutang.kd_pj, "
                    + "detail_piutang_pasien.totalpiutang,date_format(detail_piutang_pasien.tgltempo,'%d/%m/%Y') from "
                    + "akun_piutang inner join detail_piutang_pasien on akun_piutang.nama_bayar=detail_piutang_pasien.nama_bayar "
                    + "where detail_piutang_pasien.no_rawat=? and akun_piutang.nama_bayar like ? order by nama_bayar");
            try {
                psakunpiutang.setString(1, TNoRw.getText());
                psakunpiutang.setString(2, "%" + TCari1.getText() + "%");
                rsakunpiutang = psakunpiutang.executeQuery();
                while (rsakunpiutang.next()) {
                    tabModeAkunPiutang.addRow(new Object[]{rsakunpiutang.getString(1), rsakunpiutang.getString(2), rsakunpiutang.getString(3), rsakunpiutang.getString(4), rsakunpiutang.getString(5)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Piutang Tersimpan : " + e);
            } finally {
                if (rsakunpiutang != null) {
                    rsakunpiutang.close();
                }
                if (psakunpiutang != null) {
                    psakunpiutang.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void isSimpan() {
        if (notaralan.equals("Yes")) {
            BtnNotaActionPerformed(null);
            chkLaborat.setSelected(true);
            chkRadiologi.setSelected(true);
            chkPotongan.setSelected(true);
            chkTambahan.setSelected(true);
            chkObat.setSelected(true);
            chkAdministrasi.setSelected(true);
            chkSarpras.setSelected(true);
            chkTarifDokter.setSelected(true);
            chkTarifPrm.setSelected(true);
            isRawat2();
        }

        if ((chkLaborat.isSelected() == false) || (chkRadiologi.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan tampilkan semua pilihan tagihan...!!!");
        } else {
            try {
                psnota = koneksi.prepareStatement(sqlpsnota);
                no_nota = Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6);
                try {
                    psnota.setString(1, TNoRw.getText());
                    psnota.setString(2, Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6));
                    psnota.setString(3, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                    psnota.setString(4, DTPTgl.getSelectedItem().toString().substring(11, 19));
                    psnota.setDouble(5, Jasa_Medik_Dokter_Tindakan_Ralan);
                    psnota.setDouble(6, Jasa_Medik_Paramedis_Tindakan_Ralan);
                    psnota.setDouble(7, KSO_Tindakan_Ralan);
                    psnota.setDouble(8, Jasa_Medik_Dokter_Laborat_Ralan);
                    psnota.setDouble(9, Jasa_Medik_Petugas_Laborat_Ralan);
                    psnota.setDouble(10, Kso_Laborat_Ralan);
                    psnota.setDouble(11, Persediaan_Laborat_Rawat_Jalan);
                    psnota.setDouble(12, Jasa_Medik_Dokter_Radiologi_Ralan);
                    psnota.setDouble(13, Jasa_Medik_Petugas_Radiologi_Ralan);
                    psnota.setDouble(14, Kso_Radiologi_Ralan);
                    psnota.setDouble(15, Persediaan_Radiologi_Rawat_Jalan);
                    psnota.setDouble(16, Obat_Rawat_Jalan);
                    psnota.setDouble(17, Jasa_Medik_Dokter_Operasi_Ralan);
                    psnota.setDouble(18, Jasa_Medik_Paramedis_Operasi_Ralan);
                    psnota.setDouble(19, Obat_Operasi_Ralan);
                    psnota.executeUpdate();
                } catch (Exception e) {
                    Sequel.meghapus("nota_jalan", "no_rawat", TNoRw.getText());
                    tbBilling.setValueAt(": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6), 0, 2);
                    psnota.setString(1, TNoRw.getText());
                    psnota.setString(2, Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6));
                    psnota.setString(3, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                    psnota.setString(4, DTPTgl.getSelectedItem().toString().substring(11, 19));
                    psnota.setDouble(5, Jasa_Medik_Dokter_Tindakan_Ralan);
                    psnota.setDouble(6, Jasa_Medik_Paramedis_Tindakan_Ralan);
                    psnota.setDouble(7, KSO_Tindakan_Ralan);
                    psnota.setDouble(8, Jasa_Medik_Dokter_Laborat_Ralan);
                    psnota.setDouble(9, Jasa_Medik_Petugas_Laborat_Ralan);
                    psnota.setDouble(10, Kso_Laborat_Ralan);
                    psnota.setDouble(11, Persediaan_Laborat_Rawat_Jalan);
                    psnota.setDouble(12, Jasa_Medik_Dokter_Radiologi_Ralan);
                    psnota.setDouble(13, Jasa_Medik_Petugas_Radiologi_Ralan);
                    psnota.setDouble(14, Kso_Radiologi_Ralan);
                    psnota.setDouble(15, Persediaan_Radiologi_Rawat_Jalan);
                    psnota.setDouble(16, Obat_Rawat_Jalan);
                    psnota.setDouble(17, Jasa_Medik_Dokter_Operasi_Ralan);
                    psnota.setDouble(18, Jasa_Medik_Paramedis_Operasi_Ralan);
                    psnota.setDouble(19, Obat_Operasi_Ralan);
                    psnota.executeUpdate();
                } finally {
                    if (psnota != null) {
                        psnota.close();
                    }
                }

                koneksi.setAutoCommit(false);
                //simpan billing
                for (i = 0; i < tbBilling.getRowCount(); i++) {
                    psbiling = koneksi.prepareStatement(sqlpsbiling);
                    try {
                        psbiling.setString(1, TNoRw.getText());
                        psbiling.setString(2, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                        psbiling.setString(3, tbBilling.getValueAt(i, 1).toString());
                        psbiling.setString(4, tbBilling.getValueAt(i, 2).toString().replaceAll("'", "`"));
                        psbiling.setString(5, tbBilling.getValueAt(i, 3).toString());
                        try {
                            psbiling.setDouble(6, Valid.SetAngka(tbBilling.getValueAt(i, 4).toString()));
                        } catch (Exception e) {
                            psbiling.setDouble(6, 0);
                        }
                        try {
                            psbiling.setDouble(7, Valid.SetAngka(tbBilling.getValueAt(i, 5).toString()));
                        } catch (Exception e) {
                            psbiling.setDouble(7, 0);
                        }
                        subttl = 0;
                        try {
                            if ((!tbBilling.getValueAt(i, 8).toString().equals("Laborat")) && (!tbBilling.getValueAt(i, 8).toString().equals("Obat"))) {
                                subttl = Valid.SetAngka(tbBilling.getValueAt(i, 6).toString());
                            }
                            psbiling.setDouble(8, Valid.SetAngka(tbBilling.getValueAt(i, 6).toString()));
                        } catch (Exception e) {
                            subttl = 0;
                            psbiling.setDouble(8, 0);
                        }
                        if (subttl > 0) {
                            Sequel.queryu2("delete from tambahan_biaya where no_rawat=? and nama_biaya=?", 2, new String[]{
                                TNoRw.getText(), "Tambahan " + tbBilling.getValueAt(i, 2).toString()
                            });
                            Sequel.menyimpan("tambahan_biaya", "'" + TNoRw.getText() + "','Tambahan " + tbBilling.getValueAt(i, 2).toString()
                                    + "','" + tbBilling.getValueAt(i, 6).toString() + "','" + akses.getkode() + "',"
                                    + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Tambahan Biaya");
                        }
                        if (subttl < 0) {
                            Sequel.queryu2("delete from pengurangan_biaya where no_rawat=? and nama_pengurangan=?", 2, new String[]{
                                TNoRw.getText(), "Potongan " + tbBilling.getValueAt(i, 2).toString()
                            });
                            Sequel.menyimpan("pengurangan_biaya", "'" + TNoRw.getText() + "','Potongan " + tbBilling.getValueAt(i, 2).toString()
                                    + "','" + tbBilling.getValueAt(i, 6).toString() + "'", "Potongan Biaya");
                        }
                        try {
                            psbiling.setDouble(9, Valid.SetAngka(tbBilling.getValueAt(i, 7).toString()));
                        } catch (Exception e) {
                            psbiling.setDouble(9, 0);
                        }
                        psbiling.setString(10, tbBilling.getValueAt(i, 8).toString());
                        psbiling.setString(11, no_nota);
                        psbiling.executeUpdate();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (psbiling != null) {
                            psbiling.close();
                        }
                    }
                }

                String alamat = Sequel.cariIsi("select almt_pj from reg_periksa where no_rawat=? ", TNoRw.getText());

                //lakukan jurnal
                Sequel.queryu2("delete from tampjurnal");

                itembayar = 0;
                besarppn = 0;
                row2 = tbAkunBayar.getRowCount();
                for (r = 0; r < row2; r++) {
                    if (Valid.SetAngka(tbAkunBayar.getValueAt(r, 2).toString()) > 0) {
                        try {
                            itembayar = Double.parseDouble(tbAkunBayar.getValueAt(r, 2).toString());
                        } catch (Exception e) {
                            itembayar = 0;
                        }

                        if (!tbAkunBayar.getValueAt(r, 4).toString().equals("")) {
                            try {
                                besarppn = Valid.roundUp(Double.parseDouble(tbAkunBayar.getValueAt(r, 4).toString()), 100);
                            } catch (Exception e) {
                                besarppn = 0;
                            }
                        }

                        if (countbayar > 1) {
                            if (Sequel.menyimpantf2("detail_nota_jalan", "?,?,?,?,?", "Akun bayar", 5, new String[]{
                                TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(itembayar), no_nota
                            }) == true) {
                                Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(itembayar) + "','0'", "Rekening");
                            }
                        } else if (countbayar == 1) {
                            if (piutang <= 0) {
                                if (Sequel.menyimpantf2("detail_nota_jalan", "?,?,?,?,?", "Akun bayar", 5, new String[]{
                                    TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(total), no_nota
                                }) == true) {
                                    Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(total) + "','0'", "Rekening");
                                }
                            } else {
                                if (Sequel.menyimpantf2("detail_nota_jalan", "?,?,?,?,?", "Akun bayar", 5, new String[]{
                                    TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(itembayar), no_nota
                                }) == true) {
                                    Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(itembayar) + "','0'", "Rekening");
                                }
                            }
                        }
                    }

                }

                itempiutang = 0;
                row2 = tabModeAkunPiutang.getRowCount();
                for (r = 0; r < row2; r++) {
                    if (!tabModeAkunPiutang.getValueAt(r, 3).toString().equals("")) {
                        try {
                            itempiutang = Double.parseDouble(tabModeAkunPiutang.getValueAt(r, 3).toString());
                        } catch (Exception e) {
                            itempiutang = 0;
                        }

                        if (Sequel.menyimpantf2("detail_piutang_pasien", "?,?,?,?,?,?,?", "Akun Piutang", 7, new String[]{
                            TNoRw.getText(), tabModeAkunPiutang.getValueAt(r, 0).toString(), tabModeAkunPiutang.getValueAt(r, 2).toString(),
                            Double.toString(itempiutang), Double.toString(itempiutang), Valid.SetTgl(tabModeAkunPiutang.getValueAt(r, 4).toString()), no_nota
                        }) == true) {
                            Sequel.menyimpan("tampjurnal", "'" + tabModeAkunPiutang.getValueAt(r, 1).toString() + "','" + tabModeAkunPiutang.getValueAt(r, 0).toString() + "','" + Double.toString(itempiutang) + "','0'", "Rekening");
                        }
                    }
                }

                if ((-1 * ttlPotongan) > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Potongan_Ralan + "','Potongan_Ralan','" + (-1 * ttlPotongan) + "','0'", "Rekening");
                }

                if ((ttlRalan_Dokter + ttlRalan_Dokter_Param + ttlRalan_Paramedis) > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Tindakan_Ralan + "','Tindakan Ralan','0','" + (ttlRalan_Dokter + ttlRalan_Dokter_Param + ttlRalan_Paramedis) + "'", "Rekening");
                }

                if (ttlLaborat > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Laborat_Ralan + "','Laborat Ralan','0','" + ttlLaborat + "'", "Rekening");
                }

                if (ttlRadiologi > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Radiologi_Ralan + "','Radiologi Ralan','0','" + ttlRadiologi + "'", "Rekening");
                }

                if (ttlObat > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Obat_Ralan + "','Obat Ralan','0','" + ttlObat + "'", "Rekening");
                }

                if (ttlRegistrasi > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Registrasi_Ralan + "','Registrasi Ralan','0','" + ttlRegistrasi + "'", "Rekening");
                }

                if (ttlTambahan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Tambahan_Ralan + "','Tambahan Ralan','0','" + ttlTambahan + "'", "Rekening");
                }

                if (ttlOperasi > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Operasi_Ralan + "','Operasi Ralan','0','" + ttlOperasi + "'", "Rekening");
                }

                if (Jasa_Medik_Dokter_Tindakan_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ralan + "','Operasi Ralan','" + Jasa_Medik_Dokter_Tindakan_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Dokter_Tindakan_Ralan + "'", "Rekening");
                }

                if (Jasa_Medik_Paramedis_Tindakan_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ralan + "','Operasi Ralan','" + Jasa_Medik_Paramedis_Tindakan_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Paramedis_Tindakan_Ralan + "'", "Rekening");
                }

                if (KSO_Tindakan_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_KSO_Tindakan_Ralan + "','Operasi Ralan','" + KSO_Tindakan_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_KSO_Tindakan_Ralan + "','Operasi Ralan','0','" + KSO_Tindakan_Ralan + "'", "Rekening");
                }

                if (Jasa_Medik_Dokter_Laborat_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Laborat_Ralan + "','Operasi Ralan','" + Jasa_Medik_Dokter_Laborat_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Laborat_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Dokter_Laborat_Ralan + "'", "Rekening");
                }

                if (Jasa_Medik_Petugas_Laborat_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Laborat_Ralan + "','Operasi Ralan','" + Jasa_Medik_Petugas_Laborat_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Laborat_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Petugas_Laborat_Ralan + "'", "Rekening");
                }

                if (Kso_Laborat_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Kso_Laborat_Ralan + "','Operasi Ralan','" + Kso_Laborat_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Kso_Laborat_Ralan + "','Operasi Ralan','0','" + Kso_Laborat_Ralan + "'", "Rekening");
                }

                if (Persediaan_Laborat_Rawat_Jalan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + HPP_Persediaan_Laborat_Rawat_Jalan + "','Operasi Ralan','" + Persediaan_Laborat_Rawat_Jalan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Persediaan_BHP_Laborat_Rawat_Jalan + "','Operasi Ralan','0','" + Persediaan_Laborat_Rawat_Jalan + "'", "Rekening");
                }

                if (Jasa_Medik_Dokter_Radiologi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ralan + "','Operasi Ralan','" + Jasa_Medik_Dokter_Radiologi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Dokter_Radiologi_Ralan + "'", "Rekening");
                }

                if (Jasa_Medik_Petugas_Radiologi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ralan + "','Operasi Ralan','" + Jasa_Medik_Petugas_Radiologi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Petugas_Radiologi_Ralan + "'", "Rekening");
                }

                if (Kso_Radiologi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Kso_Radiologi_Ralan + "','Operasi Ralan','" + Kso_Radiologi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Kso_Radiologi_Ralan + "','Operasi Ralan','0','" + Kso_Radiologi_Ralan + "'", "Rekening");
                }

                if (Persediaan_Radiologi_Rawat_Jalan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Jalan + "','Operasi Ralan','" + Persediaan_Radiologi_Rawat_Jalan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Jalan + "','Operasi Ralan','0','" + Persediaan_Radiologi_Rawat_Jalan + "'", "Rekening");
                }

                if (Obat_Rawat_Jalan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + HPP_Obat_Rawat_Jalan + "','Operasi Ralan','" + Obat_Rawat_Jalan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Persediaan_Obat_Rawat_Jalan + "','Operasi Ralan','0','" + Obat_Rawat_Jalan + "'", "Rekening");
                }

                if (Jasa_Medik_Dokter_Operasi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Operasi_Ralan + "','Operasi Ralan','" + Jasa_Medik_Dokter_Operasi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Operasi_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Dokter_Operasi_Ralan + "'", "Rekening");
                }

                if (Jasa_Medik_Paramedis_Operasi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Operasi_Ralan + "','Operasi Ralan','" + Jasa_Medik_Paramedis_Operasi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Operasi_Ralan + "','Operasi Ralan','0','" + Jasa_Medik_Paramedis_Operasi_Ralan + "'", "Rekening");
                }

                if (Obat_Operasi_Ralan > 0) {
                    Sequel.menyimpan2("tampjurnal", "'" + HPP_Obat_Operasi_Ralan + "','Operasi Ralan','" + Obat_Operasi_Ralan + "','0'", "Rekening");
                    Sequel.menyimpan2("tampjurnal", "'" + Persediaan_Obat_Kamar_Operasi_Ralan + "','Operasi Ralan','0','" + Obat_Operasi_Ralan + "'", "Rekening");
                }

                if (piutang > 0) {
                    jur.simpanJurnal(TNoRw.getText(),"U", "PIUTANG PASIEN RAWAT JALAN, DIPOSTING OLEH " + akses.getkode());
                    Sequel.menyimpan2("tagihan_sadewa", "'" + TNoRw.getText() + "','" + TNoRM.getText() + "','" + TPasien.getText() + "','" + alamat + "',concat('" + Valid.SetTgl(DTPTgl.getSelectedItem() + "")
                            + "',' ',CURTIME()),'Uang Muka','" + total + "','" + bayar + "','Belum','" + akses.getkode() + "','" + no_nota + "'", "No.Rawat");
                    Sequel.queryu2("insert into piutang_pasien values ('" + TNoRw.getText() + "','" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','"
                            + TNoRM.getText() + "','Belum Lunas','" + total + "','" + bayar + "','" + piutang + "','" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','" + no_nota + "')");
                } else if (piutang <= 0) {
                    jur.simpanJurnal(TNoRw.getText(),"U", "PEMBAYARAN PASIEN RAWAT JALAN, DIPOSTING OLEH " + akses.getkode());
                    Sequel.menyimpan2("tagihan_sadewa", "'" + TNoRw.getText() + "','" + TNoRM.getText() + "','" + TPasien.getText() + "','" + alamat + "',concat('" + Valid.SetTgl(DTPTgl.getSelectedItem() + "")
                            + "',' ',CURTIME()),'Pelunasan','" + total + "','" + total + "','Sudah','" + akses.getkode() + "','" + no_nota + "'", "No.Rawat");
                }

                Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, "stts='Bayar'");
                Sequel.meghapus("temporary_tambahan_potongan", "no_rawat", TNoRw.getText());
                Sequel.mengedittf("detail_pemberian_obat", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Belum' and no_nota = '-' ", "stts_bayar = 'Bayar',no_nota='" + no_nota + "'");
                Sequel.mengedittf("rawat_jl_drpr", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Belum' and no_nota = '-' ", "stts_bayar = 'Bayar',no_nota='" + no_nota + "'");
                Sequel.mengedittf("periksa_lab", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Belum' and no_nota = '-' ", "stts_bayar = 'Bayar',no_nota='" + no_nota + "'");
                Sequel.mengedittf("detail_periksa_lab", "no_rawat = '" + TNoRw.getText() + "' and no_nota = '-' ", "no_nota='" + no_nota + "'");
                Sequel.mengedittf("periksa_radiologi", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Belum' and no_nota = '-' ", "stts_bayar = 'Bayar',no_nota='" + no_nota + "'");
                Sequel.mengedittf("operasi", "no_rawat = '" + TNoRw.getText() + "' and stts_bayar = 'Belum' and no_nota = '-' ", "stts_bayar = 'Bayar',no_nota='" + no_nota + "'");
                koneksi.setAutoCommit(true);
                JOptionPane.showMessageDialog(null, "Proses simpan selesai...!");
                if (notaralan.equals("Yes")) {
                    this.dispose();
                }
                Valid.tabelKosong(tabModeAkunBayar);
                chkBayar.setSelected(true);
                chkBayar.setText("Transaksi SUDAH DIBAYAR");
                isKembali();

            } catch (SQLException ex) {
                System.out.println("Notifikasi : " + ex);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Data yang sama dimasukkan sebelumnya...!");
            }
        }
    }

    public void isCekObat() {
//        cekObat = Sequel.cariObat(TNoRw.getText());
    }

    public void isCekSimpan() {
        try {
            pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
            try {
                pscekbilling.setString(1, TNoRw.getText());
                rscekbilling = pscekbilling.executeQuery();
                if (rscekbilling.next()) {
                    cek = rscekbilling.getInt(1);
                }
            } catch (Exception e) {
                cek = 0;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscekbilling != null) {
                    rscekbilling.close();
                }
                if (pscekbilling != null) {
                    pscekbilling.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
//            Valid.textKosong(TNoRw, "Pasien");
//        } else if ((chkObat.isSelected() == false) || (chkPotongan.isSelected() == false)
//                || (chkTambahan.isSelected() == false) || (chkTarifDokter.isSelected() == false) || (chkTarifPrm.isSelected() == false)) {
//            JOptionPane.showMessageDialog(null, "Maaf, Silahkan tampilkan semua pilihan tagihan...!!!");
//        } else if (cek > 0) {
//            JOptionPane.showMessageDialog(null, "Maaf, data tagihan pasien dengan No.Rawat tersebut sudah pernah disimpan...!!!");
//        } else if (cek == 0) {
//            if (piutang <= 0) {
//                if (kekurangan < 0) {
//                    JOptionPane.showMessageDialog(null, "Maaf, pembayaran pasien masih kurang ...!!!");
//                } else if (kekurangan > 0) {
//                    if (countbayar > 1) {
//                        JOptionPane.showMessageDialog(null, "Maaf, kembali harus bernilai 0 untuk cara bayar lebih dari 1...!!!");
//                    } else {
//                        isSimpan();
//                    }
//                } else if (kekurangan == 0) {
//                    isSimpan();
//                }
//            } else if (piutang >= 1) {
//                if (kekurangan < 0) {
//                    JOptionPane.showMessageDialog(null, "Maaf, piutang belum genap. Silahkan isi di jumlah piutang ...!!!");
//                } else if (kekurangan > 0) {
//                    JOptionPane.showMessageDialog(null, "Maaf, terjadi kelebihan piutang ...!!!");
//                } else {
//                    isSimpan();
//                }
//            }
//        }
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if ((chkObat.isSelected() == false) || (chkPotongan.isSelected() == false)
                || (chkTambahan.isSelected() == false) || (chkTarifDokter.isSelected() == false) || (chkTarifPrm.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan tampilkan semua pilihan tagihan...!!!");
        } else if (cek > 0) {
//            JOptionPane.showMessageDialog(null, "Maaf, data tagihan pasien dengan No.Rawat tersebut sudah pernah disimpan...!!!");
            int jawab = JOptionPane.showConfirmDialog(null, "Data billing sudah terverifikasi, apa anda mau input data baru...????", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == JOptionPane.YES_OPTION) {
                if (piutang <= 0) {
                    if (kekurangan < 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, pembayaran pasien masih kurang ...!!!");
                    } else if (kekurangan > 0) {
                        if (countbayar > 1) {
                            JOptionPane.showMessageDialog(null, "Maaf, kembali harus bernilai 0 untuk cara bayar lebih dari 1...!!!");
                        } else {
                            isSimpan();
                        }
                    } else if (kekurangan == 0) {
                        isSimpan();
                    }
                } else if (piutang >= 1) {
                    if (kekurangan < 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, piutang belum genap. Silahkan isi di jumlah piutang ...!!!");
                    } else if (kekurangan > 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, terjadi kelebihan piutang ...!!!");
                    } else {
                        isSimpan();
                    }
                }
            } else {
            }
        } else if (cek == 0) {
            if (piutang <= 0) {
                if (kekurangan < 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, pembayaran pasien masih kurang ...!!!");
                } else if (kekurangan > 0) {
                    if (countbayar > 1) {
                        JOptionPane.showMessageDialog(null, "Maaf, kembali harus bernilai 0 untuk cara bayar lebih dari 1...!!!");
                    } else {
                        isSimpan();
                    }
                } else if (kekurangan == 0) {
                    isSimpan();
                }
            } else if (piutang >= 1) {
                if (kekurangan < 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, piutang belum genap. Silahkan isi di jumlah piutang ...!!!");
                } else if (kekurangan > 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, terjadi kelebihan piutang ...!!!");
                } else {
                    isSimpan();
                }
            }
        }
    }

    public void cekPoli() {
        Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?", kdpoli, TNoRw.getText());
    }

    private void cetakNota() {
        jmlNota = 0;
        jmlNota = Sequel.cariInteger("SELECT count(-1) cek FROM temporary_bayar_ralan WHERE temp1='No. Nota'");
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + TNoRw.getText() + "'"));
        param.put("tot_bayar", Sequel.cariIsi("select REPLACE(REPLACE(temp7,'.','.'),',','.') from temporary_bayar_ralan where temp1='TOTAL BAYAR'"));
            if (akses.getkode().equals("Admin Utama")) {
                param.put("petugas_ksr", "( ................... )");
            } else {
                param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
            }

        if (jmlNota > 1) {
            Valid.MyReportqry("rptNotaRalanBanyak.jrxml", "report", "::[ Nota Pembayaran Banyak - LUNAS (Rawat Jalan) ]::",
                    " SELECT temp1, temp2, temp5, temp7 FROM temporary_bayar_ralan WHERE temp1 <> 'TOTAL BAYAR'", param);
        } else if (jmlNota <= 1) {
            Valid.MyReportqry("rptNotaRalan.jrxml", "report", "::[ Nota Pembayaran - LUNAS (Rawat Jalan) ]::",
                    " SELECT temp1, temp2, temp5, temp7 , "
                    + "(SELECT REPLACE(temp2,': ','') FROM temporary_bayar_ralan WHERE temp1='No. Nota') no_nota,"
                    + "(SELECT REPLACE(temp2,': ','') FROM temporary_bayar_ralan WHERE temp1='Poliklinik/Inst.') poli,"
                    + "(SELECT REPLACE(temp2,': ','') FROM temporary_bayar_ralan WHERE temp1='Tanggal & Jam') tgl_jam,"
                    + "(SELECT REPLACE(temp2,': ','') FROM temporary_bayar_ralan WHERE temp1='Pasien') pasien,"
                    + "(SELECT REPLACE(temp2,': ','') FROM temporary_bayar_ralan WHERE temp1='Alamat Pasien') alamat "
                    + "FROM temporary_bayar_ralan WHERE temp1 not in ('No. Nota','Poliklinik/Inst.','Tanggal & Jam','Pasien','Alamat Pasien') ", param);
        }        
    }

    private void cetakKwitansiLUNAS() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("no_kwitansi", Sequel.cariIsi("SELECT REPLACE(temp2,': ','') no_nota FROM temporary_bayar_ralan WHERE temp1='No. Nota'"));
        param.put("telah_terima", Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
//        param.put("uang_sebanyak", Sequel.(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp7,'.',''),',','') tot_bayar FROM temporary_bayar_ralan WHERE temp1='TOTAL BAYAR'")) + " Rupiah.");
        param.put("untuk_byr", "Pelayanan Kesehatan Rawat Jalan di " + Sequel.cariIsi("select nama_instansi from setting") + " a/n "
                + Sequel.cariIsi("SELECT REPLACE(temp2,': ','') pasien FROM temporary_bayar_ralan WHERE temp1='Nama Pasien'") + ", No. RM : " + TNoRM.getText());
        param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp7,'.','.'),',','.')) terbilang FROM temporary_bayar_ralan WHERE temp1='TOTAL BAYAR'"));

        if (akses.getkode().equals("Admin Utama")) {
            param.put("petugas_ksr", "( ____________________ )");
        } else {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReportqry("rptKwitansiRalan.jrxml", "report", "::[ Kwitansi Pembayaran - LUNAS (Rawat Jalan) ]::",
                " SELECT * FROM temporary_bayar_ralan ", param);
    }

    private void cetakKwitansiPIUTANG() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("no_kwitansi", Sequel.cariIsi("SELECT REPLACE(temp2,': ','') no_nota FROM temporary_bayar_ralan WHERE temp1='No. Nota'"));
        param.put("telah_terima", Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
//        param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp7,'.',''),',','') tot_bayar FROM temporary_bayar_ralan WHERE temp1='UANG MUKA'")) + " Rupiah.");
        param.put("untuk_byr", "Pelayanan Kesehatan Rawat Jalan di " + Sequel.cariIsi("select nama_instansi from setting") + " a/n "
                + Sequel.cariIsi("SELECT REPLACE(temp2,': ','') pasien FROM temporary_bayar_ralan WHERE temp1='Nama Pasien'") + ", No. RM : " + TNoRM.getText()+" sebagai uang muka.");
        param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp7,'.','.'),',','.')) terbilang FROM temporary_bayar_ralan WHERE temp1='UANG MUKA'"));

        if (akses.getkode().equals("Admin Utama")) {
            param.put("petugas_ksr", "( ____________________ )");
        } else {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReportqry("rptKwitansiRalanPiutang.jrxml", "report", "::[ Kwitansi Pembayaran - PIUTANG (Rawat Jalan) ]::",
                " SELECT * FROM temporary_bayar_ralan ", param);
    }
}

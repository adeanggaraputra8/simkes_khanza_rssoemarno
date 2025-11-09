package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author khanzasoft
 */
public class ApiEKLAIM_inacbg {
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String URL = "",requestJsonA = "", requestJsonB = "", requestJson1 = "", requestJson2 = "", requestJson3 = "", 
            requestJson4 = "",requestJson5 = "", requestJson6 = "", requestJson7 = "", requestJson8 = "", requestJson9 = "",
            requestJson10 = "", requestJson11 = "", requestJson12 = "", requestJson13 = "", requestJson14 = "",
            stringbalik = "", requestJson15 = "", requestJson16 = "", requestJson17 = "",requestJson18 = "",requestJson19 = "" , reqJsondxidrg="",reqJsonpsidrg,
            reqJsongrouperidrg = "", reqJsonFinalidrg = "", reqJsoneditidrg = "",reqJsoneditinacbg = "",reqJsondxinacbg = "",reqJsonpsinacbg = "", reqJsonImportInacbg = "", reqJsonCetakklaim ="",
            reqJsonAmbilDxPs ="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private sekuel Sequel = new sekuel();
    private boolean x;
    private int i;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();
    private static final Properties prop = new Properties(); 

    public ApiEKLAIM_inacbg() {
        super();
        try {
            URL = koneksiDB.URL_EKLAIM_INACBG();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    public boolean ngirimJKN(String noRawat) {
        try {
            ps = koneksi.prepareStatement("select bs.no_kartu, bs.no_sep, bs.nomr, bs.nama_pasien, "
                    + "concat(bs.tanggal_lahir,' ','00:00:00') tgl_lhr, if(p.jk='L','1','2') jk, bs.tglsep, "
                    + "bs.jnspelayanan from bridging_sep bs INNER JOIN pasien p ON p.no_rkm_medis=bs.nomr where no_rawat='" + noRawat + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Content-Type", "application/json;charset=UTF-8");
                    requestJson1
                            = "{"
                            + "\"metadata\": {"
                            + "\"method\": \"new_claim\""
                            + "},"
                            + "\"data\": {"
                            + "\"no_rawat\": \"" + noRawat + "\","
                            + "\"nomor_kartu\": \"" + rs.getString("no_kartu") + "\","
                            + "\"nomor_sep\": \"" + rs.getString("no_sep") + "\","
                            + "\"tglsep\": \"" + rs.getString("tglsep") + "\","
                            + "\"jnspelayanan\": \"" + rs.getString("jnspelayanan") + "\","
                            + "\"nomor_rm\": \"" + rs.getString("nomr") + "\","
                            + "\"nama_pasien\": \"" + rs.getString("nama_pasien") + "\","
                            + "\"tgl_lahir\": \"" + rs.getString("tgl_lhr") + "\","
                            + "\"gender\": \"" + rs.getString("jk") + "\""
                            + "}"
                            + "}";

                    System.out.println("JSON : " + requestJson1);
                    requestEntity = new HttpEntity(requestJson1, headers);
                    stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                    System.out.println("Output : " + stringbalik);
                    root = mapper.readTree(stringbalik);

                    if (root.path("metadata").path("code").asText().equals("200")) {
                        JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                        x = true;
                    } else {
                        JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                        x = false;
                    }

                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                if (e.toString().contains("UnknownHostException") || e.toString().contains("false")) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return x;
    }

    public boolean ngirimLAINYA(String norawat, String noIdentitas, String tglReg, String jnsrwt, String norm, String nmPas, String tglLhr, String jk, String payor) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson10
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"generate_claim_number\""
                    + "},"
                    + "\"data\": {"
                    + "\"no_rawat\": \"" + norawat + "\","
                    + "\"payor_id\": \"" + payor + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson10);
            requestEntity = new HttpEntity(requestJson10, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                //-------------------------------------------------------
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");
                requestJson11
                        = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"new_claim\""
                        + "},"
                        + "\"data\": {"
                        + "\"no_rawat\": \"" + norawat + "\","
                        + "\"nomor_kartu\": \"" + noIdentitas + "\","
                        + "\"nomor_sep\": \"" + root.path("metadata").path("claim_number").asText() + "\","
                        + "\"tglsep\": \"" + tglReg + "\","
                        + "\"jnspelayanan\": \"" + jnsrwt + "\","
                        + "\"nomor_rm\": \"" + norm + "\","
                        + "\"nama_pasien\": \"" + nmPas + "\","
                        + "\"tgl_lahir\": \"" + tglLhr + "\","
                        + "\"gender\": \"" + jk + "\""
                        + "}"
                        + "}";

                System.out.println("JSON : " + requestJson11);
                requestEntity = new HttpEntity(requestJson11, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);
                root = mapper.readTree(stringbalik);

                if (root.path("metadata").path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                    x = true;
                } else {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                    x = false;
                }
                //-------------------------------------------------------
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                x = false;
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }

    public void menggrouper(String noSep, String noKartu, String tglMsk, String tglPulang, String jnsRawt, String klsRwt, String adlSub, String adlKro,
            String icuIndi, String icuLos, String venti, String classInd, String classClass, String classLos, String addPaymen, String birthWe, String dischargeStts,
            String diagnosa, String prosedur, Double pnb, Double pb, Double kon, Double ta, Double kep, Double pen, Double rad, Double lab, Double pd, Double reh,
            Double kam, Double ri, Double obat, String okr, String oke, String alkes, Double bmhp, Double sa, String pj, String kj, String petiJen, String plastikErat,
            String dj, String mj, String dmj, String covidStatus, String noKT, String episod, String ccInd, String rsDarurat, String coInside, String labAL,
            String labPro, String labCRP, String labKul, String labDim, String labPT, String labAPTT, String labWP, String labAnti, String labAnaGas, String labAlbu,
            String radTho, String tarifPE, String nmDokter, String payorI, String payorC, String cob, String codNik, String konvalesen, String naat, String isoman,
            String bayi_baru_lhr, String prosedur_inadrg, String diagnosa_inadrg, String caraMsk, String classPayor, String sistol, String diastol, String nilaiVenti,
            String tglIntub, String tglEkstub, String dialiser, String Kantng_darah, String mnt1APP, String mnt1PUL, String mnt1GRI, String mnt1ACT, String mnt1RES,
            String mnt5APP, String mnt5PUL, String mnt5GRI, String mnt5ACT, String mnt5RES, String usiaHamil, String gravida, String partus, String abortus, 
            String onset, String cekdeliveri, String Json) {
       
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json;charset=UTF-8");

        //--------------------------------------------------------------------------------------------------------
        requestJsonA = "";
        requestJsonB = "";
        if (cekdeliveri.equals("tabel_kosong")) {
            requestJsonA = "\"\"";
            requestJsonB = "";

        } else if (cekdeliveri.equals("ada_datanya")) {
            requestJsonA = "[";
            requestJsonB = Json;
        }
        
        
        try {
            requestJson2
                     = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"set_claim_data\","
                    + "\"nomor_sep\": \"" + noSep + "\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + noSep + "\","
                    + "\"nomor_kartu\": \"" + noKartu + "\","
                    + "\"tgl_masuk\": \"" + tglMsk + "\","
                    + "\"tgl_pulang\": \"" + tglPulang + "\","
                    + "\"cara_masuk\": \"" + caraMsk + "\","
                    + "\"jenis_rawat\": \"" + jnsRawt + "\","
                    + "\"kelas_rawat\": \"" + klsRwt + "\","
                    + "\"adl_sub_acute\": \"" + adlSub + "\","
                    + "\"adl_chronic\": \"" + adlKro + "\","
                    + "\"icu_indikator\": \"" + icuIndi + "\","
                    + "\"icu_los\": \"" + icuLos + "\","
                    + "\"ventilator_hour\": \"" + venti + "\","
                    + "\"ventilator\": {"
                    + "\"use_ind\": \"" + nilaiVenti + "\","
                    + "\"start_dttm\": \"" + tglIntub + "\","
                    + "\"stop_dttm\": \"" + tglEkstub + "\""
                    + "},"                    
                    + "\"upgrade_class_ind\": \"" + classInd + "\","
                    + "\"upgrade_class_class\": \"" + classClass + "\","
                    + "\"upgrade_class_los\": \"" + classLos + "\","
                    + "\"upgrade_class_payor\": \"" + classPayor + "\","                    
                    + "\"add_payment_pct\": \"" + addPaymen + "\","
                    + "\"birth_weight\": \"" + birthWe + "\","
                    + "\"sistole\": \"" + sistol + "\","
                    + "\"diastole\": \"" + diastol + "\","                    
                    + "\"discharge_status\": \"" + dischargeStts + "\","
//                    + "\"diagnosa\": \"" + diagnosa + "\","
//                    + "\"procedure\": \"" + prosedur + "\","
//                    + "\"diagnosa_inagrouper\": \"" + diagnosa_inadrg + "\","
//                    + "\"procedure_inagrouper\": \"" + prosedur_inadrg + "\","
                    + "\"tarif_rs\": {"
                    + "\"prosedur_non_bedah\": \"" + pnb + "\","
                    + "\"prosedur_bedah\": \"" + pb + "\","
                    + "\"konsultasi\": \"" + kon + "\","
                    + "\"tenaga_ahli\": \"" + ta + "\","
                    + "\"keperawatan\": \"" + kep + "\","
                    + "\"penunjang\": \"" + pen + "\","
                    + "\"radiologi\": \"" + rad + "\","
                    + "\"laboratorium\": \"" + lab + "\","
                    + "\"pelayanan_darah\": \"" + pd + "\","
                    + "\"rehabilitasi\": \"" + reh + "\","
                    + "\"kamar\": \"" + kam + "\","
                    + "\"rawat_intensif\": \"" + ri + "\","
                    + "\"obat\": \"" + obat + "\","
                    + "\"obat_kronis\": \"" + okr + "\","
                    + "\"obat_kemoterapi\": \"" + oke + "\","
                    + "\"alkes\": \"" + alkes + "\","
                    + "\"bmhp\": \"" + bmhp + "\","
                    + "\"sewa_alat\": \"" + sa + "\""
                    + "},"
                    + "\"pemulasaraan_jenazah\": \"" + pj + "\","
                    + "\"kantong_jenazah\": \"" + kj + "\","
                    + "\"peti_jenazah\": \"" + petiJen + "\","
                    + "\"plastik_erat\": \"" + plastikErat + "\","
                    + "\"desinfektan_jenazah\": \"" + dj + "\","
                    + "\"mobil_jenazah\": \"" + mj + "\","
                    + "\"desinfektan_mobil_jenazah\": \"" + dmj + "\","
                    + "\"covid19_status_cd\": \"" + covidStatus + "\","
                    + "\"nomor_kartu_t\": \"" + noKT + "\","
                    + "\"episodes\": \"" + episod + "\","
                    + "\"covid19_cc_ind\": \"" + ccInd + "\","
                    + "\"covid19_rs_darurat_ind\": \"" + rsDarurat + "\","
                    + "\"covid19_co_insidense_ind\": \"" + coInside + "\","
                    + "\"covid19_penunjang_pengurang\": {"
                    + "\"lab_asam_laktat\": \"" + labAL + "\","
                    + "\"lab_procalcitonin\": \"" + labPro + "\","
                    + "\"lab_crp\": \"" + labCRP + "\","
                    + "\"lab_kultur\": \"" + labKul + "\","
                    + "\"lab_d_dimer\": \"" + labDim + "\","
                    + "\"lab_pt\": \"" + labPT + "\","
                    + "\"lab_aptt\": \"" + labAPTT + "\","
                    + "\"lab_waktu_pendarahan\": \"" + labWP + "\","
                    + "\"lab_anti_hiv\": \"" + labAnti + "\","
                    + "\"lab_analisa_gas\": \"" + labAnaGas + "\","
                    + "\"lab_albumin\": \"" + labAlbu + "\","
                    + "\"rad_thorax_ap_pa\": \"" + radTho + "\""
                    + "},"
                    + "\"terapi_konvalesen\": \"" + konvalesen + "\","
                    + "\"akses_naat\": \"" + naat + "\","
                    + "\"isoman_ind\": \"" + isoman + "\","
                    + "\"bayi_lahir_status_cd\": \"" + bayi_baru_lhr + "\","
                    + "\"dializer_single_use\": \"" + dialiser + "\","
                    + "\"kantong_darah\": \"" + Kantng_darah + "\","
                    + "\"apgar\": {"
                    + "\"menit_1\": {"
                    + "\"appearance\": \"" + mnt1APP + "\","
                    + "\"pulse\": \"" + mnt1PUL + "\","
                    + "\"grimace\": \"" + mnt1GRI + "\","
                    + "\"activity\": \"" + mnt1ACT + "\","
                    + "\"respiration\": \"" + mnt1RES + "\""
                    + "},"
                    + "\"menit_5\": {"
                    + "\"appearance\": \"" + mnt5APP + "\","
                    + "\"pulse\": \"" + mnt5PUL + "\","
                    + "\"grimace\": \"" + mnt5GRI + "\","
                    + "\"activity\": \"" + mnt5ACT + "\","
                    + "\"respiration\": \"" + mnt5RES + "\""
                    + "}"
                    + "},"
                    + "\"persalinan\": {"
                    + "\"usia_kehamilan\": \"" + usiaHamil + "\","
                    + "\"gravida\": \"" + gravida + "\","
                    + "\"partus\": \"" + partus + "\","
                    + "\"abortus\": \"" + abortus + "\","
                    + "\"onset_kontraksi\": \"" + onset + "\","
                    + "\"delivery\": " + requestJsonA + ""
                    + requestJsonB    
                    + "},"
                    + "\"tarif_poli_eks\": \"" + tarifPE + "\","
                    + "\"nama_dokter\": \"" + nmDokter + "\","
                    + "\"kode_tarif\": \"DS\","
                    + "\"payor_id\": \"" + payorI + "\","
                    + "\"payor_cd\": \"" + payorC + "\","
                    + "\"cob_cd\": \"" + cob + "\","
                    + "\"coder_nik\": \"" + codNik + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson2);
            requestEntity = new HttpEntity(requestJson2, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Data Klaim Berhasil Terkirim. .");
                     Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Simpan Data'",1,new String[]{noSep});
                }else{
                      JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                }
            } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, ex);
            }else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        }
    }//-------------------------------------------
         public boolean menggrouperPertama(String nosep_pengajuan) {
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Content-Type", "application/json;charset=UTF-8");
                        requestJson3
                                = "{"
                                + "\"metadata\": {"
                                + "\"method\": \"grouper\","
                                + "\"stage\": \"1\","
                                + "\"grouper\": \"inacbg\""
                                + "},"
                                + "\"data\": {"
                                + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                                + "}"
                                + "}";

                        System.out.println("JSON : " + requestJson3);
                        requestEntity = new HttpEntity(requestJson3, headers);
                        stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Output : " + stringbalik);
                        root = mapper.readTree(stringbalik);

                        if (root.path("metadata").path("code").asText().equals("200")) {
                            Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Grouper INACBG'",1,new String[]{nosep_pengajuan});
                            JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                       
                            x=true;
                        } else {
                            JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                            x = false;
                        }
                    } catch (Exception erornya) {
                        System.out.println("Notifikasi : " + erornya);
                        if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                            JOptionPane.showMessageDialog(null, erornya);
                        }
                    }
                    
                    return x;
                
            } 

      

    public void menggrouperKedua(String nosep_pengajuan, String kodeTopUP) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson4
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"grouper\","
                    + "\"stage\": \"2\","
                    + "\"grouper\": \"inacbg\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"special_cmg\": \"" + kodeTopUP + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson4);
            requestEntity = new HttpEntity(requestJson4, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Grouper INACBG s2'",1,new String[]{nosep_pengajuan});
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

    public void mempinal(String nosep_pengajuan, String nikKoder) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson5
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"claim_final\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"coder_nik\": \"" + nikKoder + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson5);
            requestEntity = new HttpEntity(requestJson5, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Final'",1,new String[]{nosep_pengajuan});
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

    public void mengedit(String nosep_pengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson6
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"reedit_claim\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson6);
            requestEntity = new HttpEntity(requestJson6, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Edit Klaim'",1,new String[]{nosep_pengajuan});
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void validasiNomorTB(String nosep, String nomorTB) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson16
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"sitb_validate\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep + "\","
                    + "\"nomor_register_sitb\": \"" + nomorTB + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson16);
            requestEntity = new HttpEntity(requestJson16, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void BatalvalidasiNomorTB(String nosep, String nomorTB) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson17
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"sitb_invalidate\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep + "\","
                    + "\"nomor_register_sitb\": \"" + nomorTB + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson17);
            requestEntity = new HttpEntity(requestJson17, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {                
                if (root.path("metadata").path("message").asText().equals("null")) {
                    JOptionPane.showMessageDialog(null, "Data belum divalidasi sebelumnya,..!!!");
                } else {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                }

            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

    public void menghapus(String nosep_pengajuan, String nikKoder) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson7
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"delete_claim\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"coder_nik\": \"" + nikKoder + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson7);
            requestEntity = new HttpEntity(requestJson7, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

    public void mengirimOnline(String nosep_pengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson8
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"send_claim_individual\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson8);
            requestEntity = new HttpEntity(requestJson8, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

   public boolean ngecekDiagnosa(String kodeICD) {
        boolean x = false; // hasil akhir
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");

            // Buat JSON request
            String requestJson9 = "{"
                    + "\"metadata\": {\"method\": \"search_diagnosis\"},"
                    + "\"data\": {\"keyword\": \"" + kodeICD + "\"}"
                    + "}";

            System.out.println("JSON : " + requestJson9);

            // Kirim request ke API
            requestEntity = new HttpEntity(requestJson9, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);

            // Parse hasil JSON
            root = mapper.readTree(stringbalik);
            JsonNode responseNode = root.path("response");

            // Jika count > 0 dan data berbentuk array → berarti valid
            if (responseNode.path("count").asInt() > 0 && responseNode.path("data").isArray()) {
                for (JsonNode arr : responseNode.path("data")) {
                    if (arr.isArray() && arr.size() >= 2) {
                        String kode = arr.get(1).asText().trim();
                        if (kodeICD.equalsIgnoreCase(kode)) {
                            x = true;
                            break;
                        }
                    }
                }
            } else {
                // Jika count == 0 atau data == "EMPTY"
                x = false;
            }

        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }


    public String ngecekDiagnosaINADRG(String kodeICD) {
//        try {
//            headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.add("Content-Type", "application/json;charset=UTF-8");
//            requestJson18
//                    = "{"
//                    + "\"metadata\": {"
//                    + "\"method\": \"search_diagnosis_inagrouper\""
//                    + "},"
//                    + "\"data\": {"
//                    + "\"keyword\": \"" + kodeICD + "\""
//                    + "}"
//                    + "}";
//
//            System.out.println("JSON : " + requestJson18);
//            requestEntity = new HttpEntity(requestJson18, headers);
//            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
//            System.out.println("Output : " + stringbalik);
//            root = mapper.readTree(stringbalik);
//
//            if (root.path("response").path("data").isArray()) {
//                for (JsonNode list : root.path("response").path("data")) {
//                    if (list.path("code").asText().toLowerCase().equals(kodeICD.toLowerCase()) && list.path("validcode").asText().toLowerCase().equals("1")) {
//                        x = true;
//                        break;
//                    } else {
//                        x = false;
//                    }
//                }
//            }
//
//        } catch (Exception erornya) {
//            System.out.println("Notifikasi : " + erornya);
//            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
//                JOptionPane.showMessageDialog(null, erornya);
//            }
//        }
//        return x;
         try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");

                requestJson18 = "{"
                    + "\"metadata\": {\"method\": \"search_diagnosis_inagrouper\"},"
                    + "\"data\": {\"keyword\": \"" + kodeICD + "\"}"
                    + "}";

                requestEntity = new HttpEntity(requestJson18, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                root = mapper.readTree(stringbalik);

                if (root.path("response").path("data").isArray()) {
                    for (JsonNode list : root.path("response").path("data")) {
                        if (list.path("code").asText().equalsIgnoreCase(kodeICD)) {
                            String valid = list.path("validcode").asText();
                            String accpdx = list.path("accpdx").asText();
                            String codeAsterisk = list.path("code_asterisk").asText();

                            if ("1".equals(valid)) {
                                if ("N".equalsIgnoreCase(accpdx)&&codeAsterisk.equals(kodeICD) ) {
                                    return "tidak boleh jadi primer";
                                } else if (codeAsterisk.endsWith("*") && "N".equalsIgnoreCase(accpdx)) {
                                    return "wajib ada diagnosa tambahan";
                                } else {
                                    return "Sesuai";
                                }
                                 
                            } else {
                                return "Belum Sesuai";
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            return "Belum Sesuai (error cek API)";
    }
    
    public boolean ngecekProsedurINADRG(String kodeIC9) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson19
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"search_procedures_inagrouper\""
                    + "},"
                    + "\"data\": {"
                    + "\"keyword\": \"" + kodeIC9 + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson19);
            requestEntity = new HttpEntity(requestJson19, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("response").path("data").isArray()) {
                for (JsonNode list : root.path("response").path("data")) {
                    if (list.path("code").asText().toLowerCase().equals(kodeIC9.toLowerCase()) && list.path("validcode").asText().toLowerCase().equals("1")) {
                        x = true;
                        break;
                    } else {
                        x = false;
                    }
                }
            }

        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
    
    public boolean ngecekProsedur(String ProskodeIC9) {
            boolean x = false;
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");

                requestJson15 = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"search_procedures\""
                        + "},"
                        + "\"data\": {"
                        + "\"keyword\": \"" + ProskodeIC9 + "\""
                        + "}"
                        + "}";

                System.out.println("JSON : " + requestJson15);
                requestEntity = new HttpEntity(requestJson15, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);

                root = mapper.readTree(stringbalik);
                JsonNode responseNode = root.path("response");

                // validasi cepat
                // Jika count > 0 dan data berbentuk array → berarti valid
                if (responseNode.path("count").asInt() > 0 && responseNode.path("data").isArray()) {
                    for (JsonNode arr : responseNode.path("data")) {
                        if (arr.isArray() && arr.size() >= 2) {
                            String kode = arr.get(1).asText().trim();
                            if (ProskodeIC9.equalsIgnoreCase(kode)) {
                                x = true;
                                break;
                            }
                        }
                    }
                } else {
                    // Jika count == 0 atau data == "EMPTY"
                    x = false;
                }

            } catch (Exception erornya) {
                System.out.println("Notifikasi : " + erornya);
                if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                    JOptionPane.showMessageDialog(null, erornya);
                }
            }
            return x;
        }

    public void mengunggahFile(String noPengajuan, String jnsDokumen, String filenya, String enkodePDF) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson12
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"file_upload\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\","
                    + "\"file_class\": \"" + jnsDokumen + "\","
                    + "\"file_name\": \"" + filenya + "\""
                    + "},"
                    + "\"data\":\"" + enkodePDF + "\""
                    + "}";

            System.out.println("JSON : " + requestJson12);
            requestEntity = new HttpEntity(requestJson12, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
//            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

//            if (root.path("metadata").path("code").asText().equals("200")) {
//                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
//            } else {
//                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
//            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
   

    public void mengambilData(String norawt, String nosep_pengajuan, String tglSEP) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson13
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"get_claim_data\""
                    + "},"
                    + "\"data\": {"
                    + "\"no_rawat\": \"" + norawt + "\","
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"tglsep\": \"" + tglSEP + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson13);
            requestEntity = new HttpEntity(requestJson13, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }

    public void menghapusFileUpload(String nosep_pengajuan, String kodeFile) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson14
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"file_delete\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"file_id\": \"" + kodeFile + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson14);
            requestEntity = new HttpEntity(requestJson14, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    //new api eklaim v5.10--------------------------------
    
     public void kirimdiagnosaidrg(String noPengajuan, String Dxidrg) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsondxidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"idrg_diagnosa_set\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "},"
                     + "\"data\": {"
                    + "\"diagnosa\": \"" + Dxidrg + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsondxidrg);
            requestEntity = new HttpEntity(reqJsondxidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
               System.out.println(root.path("metadata").path("message").asText()+" Kirim Diagnosa IDRG"); 
            } else {
                 System.out.println(root.path("metadata").path("message").asText()+" Gagal Kirim Diagnosa IDRG"); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
     
       public void kirimproseduridrg(String noPengajuan, String Psidrg) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonpsidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"idrg_procedure_set\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "},"
                     + "\"data\": {"
                    + "\"procedure\": \"" + Psidrg + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsonpsidrg);
            requestEntity = new HttpEntity(reqJsonpsidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                System.out.println(root.path("metadata").path("message").asText()+" Kirim Prosedur IDRG"); 
            } else {
               System.out.println(root.path("metadata").path("message").asText()+" Gagal Kirim Prosedur IDRG");
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
       
    
       
    public void kirimgrouperidrg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsongrouperidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"grouper\","
                    + "\"stage\": \"1\","
                    + "\"grouper\": \"idrg\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsongrouperidrg);
            requestEntity = new HttpEntity(reqJsongrouperidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null,"Sukses Grouper IDRG. . .");
                 Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Grouper IDRG'",1,new String[]{noPengajuan});
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
      
       public void kirimfinalidrg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonFinalidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"idrg_grouper_final\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsonFinalidrg);
            requestEntity = new HttpEntity(reqJsonFinalidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null,"Sukses Final IDRG. . .");
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Final IDRG'",1,new String[]{noPengajuan});
                Sequel.mengedit("eklaim_response_inagrouper","no_sep=?","status_cd='final'",1,new String[]{noPengajuan});
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
     
      public boolean importidrgtoinacbg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonImportInacbg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"idrg_to_inacbg_import\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " +  reqJsonImportInacbg);
            requestEntity = new HttpEntity( reqJsonImportInacbg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null,"Sukses Import INACBG. . .");
                 x = true;
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
                x = false;
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
       
    public void reeditidrg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsoneditidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"idrg_grouper_reedit\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsoneditidrg);
            requestEntity = new HttpEntity(reqJsoneditidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null,"Silahklan Edit Ulang IDRG");
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Edit IDRG'",1,new String[]{noPengajuan});
            }else{
                 JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
      public boolean kirimdiagnosainacbg(String noPengajuan, String Dxinacbg) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsondxinacbg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"inacbg_diagnosa_set\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "},"
                     + "\"data\": {"
                    + "\"diagnosa\": \"" + Dxinacbg + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsondxinacbg);
            requestEntity = new HttpEntity(reqJsondxinacbg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

               if (root.path("metadata").path("code").asText().equals("200")) {
                        // request sukses
                        JsonNode expanded = root.path("data").path("expanded");
                        if (expanded.isArray()) {
                            for (JsonNode dx : expanded) {
                                if ("1".equals(dx.path("validcode").asText())) {
                                    x = true;
                                } else {
                                    x = false;
                                }
                            }
                        }
                }

        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
     
       public boolean kirimprosedurinacbg(String noPengajuan, String Psinacbg) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonpsinacbg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"inacbg_procedure_set\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "},"
                     + "\"data\": {"
                    + "\"procedure\": \"" + Psinacbg + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsonpsinacbg);
            requestEntity = new HttpEntity(reqJsonpsinacbg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                        // request sukses
                        JsonNode expanded = root.path("data").path("expanded");
                        if (expanded.isArray()) {
                            for (JsonNode dx : expanded) {
                                if ("1".equals(dx.path("validcode").asText())) {
                                    x = true;
                                } else {
                                    x = false;
                                }
                            }
                        }
                }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
       
     public void kirimfinalinacbg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonFinalidrg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"inacbg_grouper_final\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsonFinalidrg);
            requestEntity = new HttpEntity(reqJsonFinalidrg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Final INACBG'",1,new String[]{noPengajuan});
                JOptionPane.showMessageDialog(null,"Sukses Final IDRG. . ."); 
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
     
    
    public void reeditinacbg(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsoneditinacbg
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"inacbg_grouper_reedit\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsoneditinacbg);
            requestEntity = new HttpEntity(reqJsoneditinacbg, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null,"Silahklan Edit Ulang INACBG"); 
                 Sequel.mengedit("eklaim_new_claim","no_sep=?","klaim_final='Edit INACBG'",1,new String[]{noPengajuan});
            } 
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void printklaim(String noPengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            reqJsonCetakklaim
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"claim_print\""
                    + "},"
                     + "\"data\": {"
                    + "\"nomor_sep\": \"" + noPengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + reqJsonCetakklaim);
            requestEntity = new HttpEntity(reqJsonCetakklaim, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
            // ambil base64 dari field data
            String base64Pdf = root.path("data").asText();
             prop.loadFromXML(new FileInputStream("setting/database.xml"));
             String saveDir = prop.getProperty("FOLDERPDF");

                // jika folder tidak ada, buat folder
               File dir = new File(saveDir);
               if (!dir.exists()) {
                   dir.mkdirs();
               }
               // decode base64
               byte[] pdfBytes = java.util.Base64.getDecoder().decode(base64Pdf);
               // simpan file pdf ke folder tujuan
               File file = new File(dir, "klaim_" + noPengajuan + ".pdf");
               try (FileOutputStream fos = new FileOutputStream(file)) {
                   fos.write(pdfBytes);
               }

               // buka file otomatis
               if (Desktop.isDesktopSupported()) {
                   Desktop.getDesktop().open(file);
               }

            JOptionPane.showMessageDialog(null, "Klaim berhasil dicetak: " + file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Gagal cetak klaim: " + root.path("metadata").path("message").asText());
        }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void ambilDataDiagnosaDanProsedur(String nosep_pengajuan, String noRawat, String noRM) {
            try {
                // --- Siapkan Header dan JSON Request ---
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");

                reqJsonAmbilDxPs = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"get_claim_datadxps\""
                        + "},"
                        + "\"data\": {"
                        + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                        + "}"
                        + "}";

                requestEntity = new HttpEntity(reqJsonAmbilDxPs, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);
                root = mapper.readTree(stringbalik);

                if (!root.path("metadata").path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                    return;
                }

                // --- Ambil Data dari Response JSON ---
                JsonNode data = root.path("response").path("data");
                if (data.isMissingNode()) {
                    JOptionPane.showMessageDialog(null, "Response tidak memiliki node data!");
                    return;
                }

                // Tentukan status Ranap / Ralan
                int jenis_rawat = data.path("jenis_rawat").asInt();
                String status = (jenis_rawat == 1) ? "Ranap" : "Ralan";

                // Ambil string diagnosa & prosedur
                String diagnosaStr = data.path("diagnosa").asText();
                String prosedurStr = data.path("procedure").asText();

                if (diagnosaStr.equals("") && prosedurStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Tidak ada data diagnosa atau prosedur yang dikembalikan API.");
                    return;
                }

                // --- HAPUS DATA LAMA ---
                Sequel.queryu("DELETE FROM diagnosa_pasien WHERE no_rawat='" + noRawat + "' AND status='" + status + "'");
                Sequel.queryu("DELETE FROM prosedur_pasien WHERE no_rawat='" + noRawat + "' AND status='" + status + "'");

                // --- PROSES DIAGNOSA ---
                if (!diagnosaStr.isEmpty()) {
                    String[] diagnosaList = diagnosaStr.split("#");
                    int prioritas = 1;

                    for (String kodeDx : diagnosaList) {
                        kodeDx = kodeDx.trim();
                        if (kodeDx.equals("")) continue;

                        String statusPenyakit = "Baru";
                        // Cek apakah pernah ada di rekam medis sebelumnya
                        if (Sequel.cariInteger(
                                "SELECT COUNT(diagnosa_pasien.kd_penyakit) "
                                + "FROM diagnosa_pasien "
                                + "INNER JOIN reg_periksa ON diagnosa_pasien.no_rawat=reg_periksa.no_rawat "
                                + "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "WHERE pasien.no_rkm_medis='" + noRM + "' "
                                + "AND diagnosa_pasien.kd_penyakit='" + kodeDx + "'"
                        ) > 0) {
                            statusPenyakit = "Lama";
                        }

                        Sequel.menyimpantf(
                                "diagnosa_pasien",
                                "?,?,?,?,?",
                                "Diagnosa",
                                5,
                                new String[]{
                                    noRawat,
                                    kodeDx,
                                    status,
                                    String.valueOf(prioritas),
                                    statusPenyakit
                                }
                        );
                        prioritas++;
                    }
                }

                // --- PROSES PROSEDUR ---
                if (!prosedurStr.isEmpty()) {
                    String[] prosedurList = prosedurStr.split("#");
                    int prioritas = 1;

                    for (String kodePr : prosedurList) {
                        kodePr = kodePr.trim();
                        if (kodePr.equals("")) continue;

                        Sequel.menyimpantf(
                                "prosedur_pasien",
                                "?,?,?,?",
                                "Prosedur",
                                4,
                                new String[]{
                                    noRawat,
                                    kodePr,
                                    status,
                                    String.valueOf(prioritas)
                                }
                        );
                        prioritas++;
                    }
                }

                JOptionPane.showMessageDialog(null, "Data diagnosa dan prosedur berhasil diperbarui dari e-Klaim untuk SEP: " + nosep_pengajuan);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            }
     }

       
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
}

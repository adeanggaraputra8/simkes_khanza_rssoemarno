package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author windiartonugroho
 */
public class ApiOrthanc {
    private HttpHeaders headers ;
    private JsonNode root;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private SSLContext sslContext;
    private SSLSocketFactory sslFactory;
    private Scheme scheme;
    private HttpComponentsClientHttpRequestFactory factory;
    private String auth,authEncrypt,requestJson,requestJson1,stringbalik="";
    private byte[] encodedBytes;
    private int i=1;
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection koneksi=koneksiDB.condb();
    
    public ApiOrthanc(){
        try {
            auth=koneksiDB.USERORTHANC()+":"+koneksiDB.PASSORTHANC();
            encodedBytes = Base64.encodeBase64(auth.getBytes());
            authEncrypt= new String(encodedBytes);
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }
    
    public String Auth(){
        return authEncrypt;
    }
    
    public JsonNode AmbilSeries(String Norm,String Tanggal1,String Tanggal2){
        System.out.println("Percobaan Mengambil Photo Pasien : "+Norm);
        try{
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);
            requestJson = "{"+
                              "\"Level\": \"Study\","+
                              "\"Expand\": true,"+
                              "\"Query\": {"+
                                   "\"StudyDate\": \""+Tanggal1+"-"+Tanggal2+"\","+
                                   "\"PatientID\": \""+Norm+"\""+
                              "}"+
                          "}";
            System.out.println("Request JSON : "+requestJson);
            requestEntity = new HttpEntity(requestJson,headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/tools/find");
            requestJson=getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/tools/find", HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Result JSON : "+requestJson);
            root = mapper.readTree(requestJson);
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Gagal mengambil data dari Orthanc, silahkan hubungi administrator ..!!");
        }
        return root;
    }
    
    public JsonNode AmbilPng(String NoRawat,String Series){
        System.out.println("Percobaan Mengambil Gambar PNG : "+NoRawat+", Series : "+Series);
        try{
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);
            requestEntity = new HttpEntity(headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series);
            requestJson=getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series, HttpMethod.GET, requestEntity, String.class).getBody();
            System.out.println("Result JSON : "+requestJson);
            root = mapper.readTree(requestJson);
            i=1;
            for(JsonNode list:root.path("Instances")){
                 System.out.println("Mengambil Gambar PNG "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview");
                 headers = new HttpHeaders();
                 headers.add("Authorization", "Basic "+authEncrypt);
                 headers.add("Accept","image/png");
                 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
                 headers.setAccept(Collections.singletonList(MediaType.IMAGE_JPEG));
                 HttpEntity<String> entity = new HttpEntity<>(headers);
                 ResponseEntity<byte[]> response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview", HttpMethod.GET, entity, byte[].class);
                 Files.write(Paths.get("./gambarradiologi/"+NoRawat+i+".png"),response.getBody());
                 i++;
            }
            JOptionPane.showMessageDialog(null,"Pengambilan Gambar PNG dari Orthanc berhasil, silahkan lihat di dalam folder Aplikasi..!!");
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Gagal mengambil Gambar PNG dari Orthanc, silahkan hubungi administrator ..!!");
        }
        return root;
    }
    
    public JsonNode AmbilJpg(String NoRawat,String Series){
        System.out.println("Percobaan Mengambil Gambar JPG : "+NoRawat+", Series : "+Series);
        try{
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);
            requestEntity = new HttpEntity(headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series);
            requestJson=getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series, HttpMethod.GET, requestEntity, String.class).getBody();
            System.out.println("Result JSON : "+requestJson);
            root = mapper.readTree(requestJson);
            i=1;
            for(JsonNode list:root.path("Instances")){
                 System.out.println("Mengambil Gambar JPG "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview");
                 headers = new HttpHeaders();
                 headers.add("Authorization", "Basic "+authEncrypt);
                 headers.add("Accept","image/jpeg");
                 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
                 headers.setAccept(Collections.singletonList(MediaType.IMAGE_JPEG));
                 HttpEntity<String> entity = new HttpEntity<>(headers);
                 ResponseEntity<byte[]> response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview", HttpMethod.GET, entity, byte[].class);
                 Files.write(Paths.get("./gambarradiologi/"+NoRawat+i+".jpg"),response.getBody());
                 i++;
            }
            JOptionPane.showMessageDialog(null,"Pengambilan Gambar JPG dari Orthanc berhasil, silahkan lihat di dalam folder Aplikasi..!!");
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Gagal mengambil Gambar JPG dari Orthanc, silahkan hubungi administrator ..!!");
        }
        return root;
    }
    
    public JsonNode AmbilBmp(String NoRawat,String Series){
        System.out.println("Percobaan Mengambil Gambar BMP : "+NoRawat+", Series : "+Series);
        try{
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);
            requestEntity = new HttpEntity(headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series);
            requestJson=getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series, HttpMethod.GET, requestEntity, String.class).getBody();
            System.out.println("Result JSON : "+requestJson);
            root = mapper.readTree(requestJson);
            i=1;
            for(JsonNode list:root.path("Instances")){
                 System.out.println("Mengambil Gambar BMP "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview");
                 headers = new HttpHeaders();
                 headers.add("Authorization", "Basic "+authEncrypt);
                 headers.add("Accept","image/bmp");
                 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
                 headers.setAccept(Collections.singletonList(MediaType.IMAGE_JPEG));
                 HttpEntity<String> entity = new HttpEntity<>(headers);
                 ResponseEntity<byte[]> response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/preview", HttpMethod.GET, entity, byte[].class);
                 Files.write(Paths.get("./gambarradiologi/"+NoRawat+i+".bmp"),response.getBody());
                 i++;
            }
            JOptionPane.showMessageDialog(null,"Pengambilan Gambar BMP dari Orthanc berhasil, silahkan lihat di dalam folder Aplikasi..!!");
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Gagal mengambil Gambar BMP dari Orthanc, silahkan hubungi administrator ..!!");
        }
        return root;
    }
    
    public JsonNode AmbilDcm(String NoRawat,String Series){
        System.out.println("Percobaan Mengambil Gambar DCM : "+NoRawat+", Series : "+Series);
        try{
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);
            requestEntity = new HttpEntity(headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series);
            requestJson=getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/series/"+Series, HttpMethod.GET, requestEntity, String.class).getBody();
            System.out.println("Result JSON : "+requestJson);
            root = mapper.readTree(requestJson);
            i=1;
            for(JsonNode list:root.path("Instances")){
                 System.out.println("Mengambil Gambar DCM "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/file");
                 headers = new HttpHeaders();
                 headers.add("Authorization", "Basic "+authEncrypt);
                 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
                 headers.setAccept(Collections.singletonList(MediaType.IMAGE_JPEG));
                 HttpEntity<String> entity = new HttpEntity<>(headers);
                 ResponseEntity<byte[]> response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/instances/"+list.asText()+"/file", HttpMethod.GET, entity, byte[].class);
                 Files.write(Paths.get("./gambarradiologi/"+NoRawat+i+".dcm"),response.getBody());
                 i++;
            }
            JOptionPane.showMessageDialog(null,"Pengambilan Gambar DCM dari Orthanc berhasil, silahkan lihat di dalam folder Aplikasi..!!");
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
            JOptionPane.showMessageDialog(null,"Gagal mengambil Gambar DCM dari Orthanc, silahkan hubungi administrator ..!!");
        }
        return root;
    }
    
    public boolean UbahAccession(String studyId, String accessionBaru){
        System.out.println("Modify AccessionNumber Study : " + studyId);
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + authEncrypt);
            headers.setContentType(MediaType.APPLICATION_JSON);
            requestJson = "{" +
                                "\"Replace\": {" +
                                    "\"AccessionNumber\": \""+accessionBaru+"\"" +
                                "}," +
                                "\"KeepSource\": false"+
                            "}";
            System.out.println("Request JSON : " + requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            System.out.println("URL : "+koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/studies/"+studyId+"/modify");
            String response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/studies/"+studyId+"/modify",HttpMethod.POST,requestEntity, String.class).getBody();
            System.out.println("Response : " + response);
            return true;
        }catch(Exception e){
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null,"Gagal mengubah Accession Number di Orthanc..!!");
            return false;
        }
    }
    
    public void kirimRalanDX(String nopermintaan) {
        try {
        ps = koneksi.prepareStatement(
            "SELECT permintaan_radiologi.noorder, permintaan_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, " +
            "permintaan_radiologi.tgl_permintaan, IF(permintaan_radiologi.jam_permintaan='00:00:00','',permintaan_radiologi.jam_permintaan) AS jam_permintaan, " +
            "pasien.tgl_lahir, pasien.jk, pasien.alamat, " +
            "IF(permintaan_radiologi.tgl_sampel='0000-00-00','',permintaan_radiologi.tgl_sampel) AS tgl_sampel, " +
            "IF(permintaan_radiologi.jam_sampel='00:00:00','',permintaan_radiologi.jam_sampel) AS jam_sampel, " +
            "IF(permintaan_radiologi.tgl_hasil='0000-00-00','',permintaan_radiologi.tgl_hasil) AS tgl_hasil, " +
            "IF(permintaan_radiologi.jam_hasil='00:00:00','',permintaan_radiologi.jam_hasil) AS jam_hasil, " +
            "permintaan_radiologi.dokter_perujuk, dokter.nm_dokter, poliklinik.nm_poli, pasien.no_tlp, penjab.png_jawab, " +
            "permintaan_pemeriksaan_radiologi.kd_jenis_prw, jns_perawatan_radiologi.nm_perawatan, " +
            "permintaan_radiologi.diagnosa_klinis, permintaan_radiologi.informasi_tambahan " +
            "FROM permintaan_radiologi " +
            "INNER JOIN reg_periksa ON permintaan_radiologi.no_rawat = reg_periksa.no_rawat " +
            "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
            "INNER JOIN dokter ON permintaan_radiologi.dokter_perujuk = dokter.kd_dokter " +
            "INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli " +
            "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj " +
            "INNER JOIN permintaan_pemeriksaan_radiologi ON permintaan_pemeriksaan_radiologi.noorder = permintaan_radiologi.noorder " +
            "INNER JOIN jns_perawatan_radiologi ON permintaan_pemeriksaan_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw " +
            "WHERE permintaan_radiologi.noorder=?"
        );

        ps.setString(1, nopermintaan); // txtNoOrder diisi nomor order dari form
        rs = ps.executeQuery();

        while (rs.next()) {
            headers = new HttpHeaders();
            System.out.println("Auth : "+authEncrypt);
            headers.add("Authorization", "Basic "+authEncrypt);

            // Bentuk JSON untuk API RIS /order/add
              requestJson = "{"
                            + "\"AccessionNumber\":\"" + rs.getString("noorder") + "\","
                            + "\"PatientName\":\"" + rs.getString("nm_pasien").toUpperCase() + "\","
                            + "\"PatientID\":\"" + rs.getString("no_rkm_medis") + "\","
                            + "\"PatientBirthDate\":\"" + formatTanggal(rs.getString("tgl_lahir")) + "\","
                            + "\"PatientSex\":\"" + formatJK(rs.getString("jk")) + "\","
                            + "\"RequestedProcedureDescription\":\"" + rs.getString("nm_perawatan").toUpperCase() + "\","
                            + "\"Modality\":\"DR\","
                            + "\"ScheduledStationAETitle\":\"ROMEXIS_1\","
                            + "\"ScheduledProcedureStepStartDate\":\"" + formatTanggal(rs.getString("tgl_permintaan")) + "\","
                            + "\"ScheduledProcedureStepStartTime\":\"" + formatJam(rs.getString("jam_permintaan")) + "\","
                            + "\"StudyInstanceUID\":\"" + generateUID(rs.getString("noorder")) + "\","
                            + "\"ReferringPhysicianName\":\"" + rs.getString("nm_dokter") + "\","
                            + "\"ScheduledPerformingPhysicianName\":\"\""
                            + "}";


            System.out.println("JSON : " + requestJson);
            System.out.println("URL : http://192.168.10.25:8042/api/ris-worklist");
            requestEntity = new HttpEntity(requestJson, headers);
            stringbalik = getRest().exchange("http://192.168.10.251:8042/api/ris-worklist", HttpMethod.POST, requestEntity, String.class).getBody();
            JOptionPane.showMessageDialog(null, stringbalik);
        }
    } catch (Exception e) {
        System.out.println("Notif : " + e);
        if (e.toString().contains("UnknownHostException") || e.toString().contains("404")) {
            JOptionPane.showMessageDialog(null, "Koneksi ke server Orthanc terputus...!");
        }
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
        }
    }
    
     public void kirimRanapDX(String nopermintaan) {
        try {
             ps=koneksi.prepareStatement(
                    "select permintaan_radiologi.noorder,permintaan_radiologi.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,permintaan_radiologi.tgl_permintaan,"+
                    "if(permintaan_radiologi.jam_permintaan='00:00:00','',permintaan_radiologi.jam_permintaan) as jam_permintaan,pasien.jk,pasien.alamat,"+
                    "if(permintaan_radiologi.tgl_sampel='0000-00-00','',permintaan_radiologi.tgl_sampel) as tgl_sampel,if(permintaan_radiologi.jam_sampel='00:00:00','',permintaan_radiologi.jam_sampel) as jam_sampel,"+
                    "if(permintaan_radiologi.tgl_hasil='0000-00-00','',permintaan_radiologi.tgl_hasil) as tgl_hasil,if(permintaan_radiologi.jam_hasil='00:00:00','',permintaan_radiologi.jam_hasil) as jam_hasil,"+
                    "permintaan_radiologi.dokter_perujuk,dokter.nm_dokter,bangsal.nm_bangsal,pasien.no_tlp,penjab.png_jawab,pasien.tgl_lahir,permintaan_pemeriksaan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan, "+
                    "permintaan_radiologi.diagnosa_klinis,permintaan_radiologi.informasi_tambahan from permintaan_radiologi "+
                    "inner join reg_periksa on permintaan_radiologi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on permintaan_radiologi.dokter_perujuk=dokter.kd_dokter "+
                    "inner join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                    "inner join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                    "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                    "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "+
                    "inner join permintaan_pemeriksaan_radiologi on permintaan_pemeriksaan_radiologi.noorder=permintaan_radiologi.noorder "+
                    "inner join jns_perawatan_radiologi on permintaan_pemeriksaan_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw "+
                    "where permintaan_radiologi.noorder=?");
             try {
                ps.setString(1,nopermintaan);
                rs=ps.executeQuery();
                while(rs.next()){
                    headers = new HttpHeaders();
                    System.out.println("Auth : "+authEncrypt);
                     headers.add("Authorization", "Basic "+authEncrypt);
                     requestJson = "{"
                            + "\"AccessionNumber\":\"" + rs.getString("noorder") + "\","
                            + "\"PatientName\":\"" + rs.getString("nm_pasien").toUpperCase() + "\","
                            + "\"PatientID\":\"" + rs.getString("no_rkm_medis") + "\","
                            + "\"PatientBirthDate\":\"" + formatTanggal(rs.getString("tgl_lahir")) + "\","
                            + "\"PatientSex\":\"" + formatJK(rs.getString("jk")) + "\","
                            + "\"RequestedProcedureDescription\":\"" + rs.getString("nm_perawatan").toUpperCase() + "\","
                            + "\"Modality\":\"DR\","
                            + "\"ScheduledStationAETitle\":\"ROMEXIS_1\","
                             + "\"ScheduledProcedureStepStartDate\":\"" + formatTanggal(rs.getString("tgl_permintaan")) + "\","
                            + "\"ScheduledProcedureStepStartTime\":\"" + formatJam(rs.getString("jam_permintaan")) + "\","
                            + "\"StudyInstanceUID\":\"" + generateUID(rs.getString("noorder")) + "\","
                            + "\"ReferringPhysicianName\":\"" + rs.getString("nm_dokter") + "\","
                            + "\"ScheduledPerformingPhysicianName\":\"\""
                            + "}"; 
                    System.out.println("JSON : "+requestJson);
                    System.out.println("URL : http://192.168.10.251:8042/api/ris-worklist");
                    requestEntity = new HttpEntity(requestJson,headers);      
                    stringbalik=getRest().exchange("http://192.168.10.251:8042/api/ris-worklist", HttpMethod.POST, requestEntity, String.class).getBody();
                    JOptionPane.showMessageDialog(null,stringbalik);
                }
             } catch (Exception e) {
                 System.out.println("Notif : "+e);
                 if(e.toString().contains("UnknownHostException")||e.toString().contains("404")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server Orthanc terputus...!");
                 }
             } finally{
                 if(rs!=null){
                     rs.close();
                 }
                 if(ps!=null){
                     ps.close();
                 }
             }
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")||ex.toString().contains("404")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server Orthanc terputus...!");
            }
        }
    }
     
     public void kirimKeUSGOBG(String norawat) {
        try {
             ps=koneksi.prepareStatement(
                    "select permintaan_radiologi.noorder,permintaan_radiologi.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,permintaan_radiologi.tgl_permintaan,"+
                    "if(permintaan_radiologi.jam_permintaan='00:00:00','',permintaan_radiologi.jam_permintaan) as jam_permintaan,pasien.jk,pasien.alamat,"+
                    "if(permintaan_radiologi.tgl_sampel='0000-00-00','',permintaan_radiologi.tgl_sampel) as tgl_sampel,if(permintaan_radiologi.jam_sampel='00:00:00','',permintaan_radiologi.jam_sampel) as jam_sampel,"+
                    "if(permintaan_radiologi.tgl_hasil='0000-00-00','',permintaan_radiologi.tgl_hasil) as tgl_hasil,if(permintaan_radiologi.jam_hasil='00:00:00','',permintaan_radiologi.jam_hasil) as jam_hasil,"+
                    "permintaan_radiologi.dokter_perujuk,dokter.nm_dokter,bangsal.nm_bangsal,pasien.no_tlp,penjab.png_jawab,pasien.tgl_lahir,permintaan_pemeriksaan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan, "+
                    "permintaan_radiologi.diagnosa_klinis,permintaan_radiologi.informasi_tambahan from permintaan_radiologi "+
                    "inner join reg_periksa on permintaan_radiologi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on permintaan_radiologi.dokter_perujuk=dokter.kd_dokter "+
                    "inner join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                    "inner join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                    "inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                    "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "+
                    "inner join permintaan_pemeriksaan_radiologi on permintaan_pemeriksaan_radiologi.noorder=permintaan_radiologi.noorder "+
                    "inner join jns_perawatan_radiologi on permintaan_pemeriksaan_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw "+
                    "where permintaan_radiologi.noorder=?");
             try {
                ps.setString(1,norawat);
                rs=ps.executeQuery();
                while(rs.next()){
                    headers = new HttpHeaders();
                    System.out.println("Auth : "+authEncrypt);
                     headers.add("Authorization", "Basic "+authEncrypt);
                     requestJson = "{"
                            + "\"AccessionNumber\":\"" + rs.getString("noorder") + "\","
                            + "\"PatientName\":\"" + rs.getString("nm_pasien").toUpperCase() + "\","
                            + "\"PatientID\":\"" + rs.getString("no_rkm_medis") + "\","
                            + "\"PatientBirthDate\":\"" + formatTanggal(rs.getString("tgl_lahir")) + "\","
                            + "\"PatientSex\":\"" + formatJK(rs.getString("jk")) + "\","
                            + "\"RequestedProcedureDescription\":\"" + rs.getString("nm_perawatan").toUpperCase() + "\","
                            + "\"Modality\":\"US\","
                            + "\"ScheduledStationAETitle\":\"ROMEXIS_1\","
                             + "\"ScheduledProcedureStepStartDate\":\"" + formatTanggal(rs.getString("tgl_permintaan")) + "\","
                            + "\"ScheduledProcedureStepStartTime\":\"" + formatJam(rs.getString("jam_permintaan")) + "\","
                            + "\"StudyInstanceUID\":\"" + generateUID(rs.getString("noorder")) + "\","
                            + "\"ReferringPhysicianName\":\"" + rs.getString("nm_dokter") + "\","
                            + "\"ScheduledPerformingPhysicianName\":\"\""
                            + "}"; 
                    System.out.println("JSON : "+requestJson);
                    System.out.println("URL : http://192.168.10.251:8042/api/ris-worklist");
                    requestEntity = new HttpEntity(requestJson,headers);      
                    stringbalik=getRest().exchange("http://192.168.10.251:8042/api/ris-worklist", HttpMethod.POST, requestEntity, String.class).getBody();
                    JOptionPane.showMessageDialog(null,stringbalik);
                }
             } catch (Exception e) {
                 System.out.println("Notif : "+e);
                 if(e.toString().contains("UnknownHostException")||e.toString().contains("404")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server Orthanc terputus...!");
                 }
             } finally{
                 if(rs!=null){
                     rs.close();
                 }
                 if(ps!=null){
                     ps.close();
                 }
             }
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")||ex.toString().contains("404")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server Orthanc terputus...!");
            }
        }
    }
    
    public boolean kirimKeModality(String studyId){
        System.out.println("Kirim Study ke Modality : " + studyId);
        try{
            headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + authEncrypt);
            headers.setContentType(MediaType.APPLICATION_JSON);
            requestJson = "[\"" + studyId + "\"]";
            requestEntity = new HttpEntity(requestJson, headers);
            System.out.println("URL : " + koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/DICOMROUTER/store");
            System.out.println("Request JSON : " + requestJson);
            String response = getRest().exchange(koneksiDB.URLORTHANC()+":"+koneksiDB.PORTORTHANC()+"/modalities/DICOMROUTER/store",HttpMethod.POST,requestEntity,String.class).getBody();
            System.out.println("Response : " + response);
            JOptionPane.showMessageDialog(null,"Proses kirim ke Modality selesai..!!");
            return true;
        }catch(Exception e){
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null,"Gagal kirim ke Modality..!!");
            return false;
        }
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        scheme=new Scheme("https",443,sslFactory);
        factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
    
    
    private String formatTanggal(String tgl) {
            return tgl.replace("-", ""); // 2026-04-29 → 20260429
        }

    private String formatJam(String jam) {
            if(jam == null || jam.equals("") || jam.equals("00:00:00")) {
                return "000000";
            }
            return jam.replace(":", ""); // 12:30:00 → 123000
     }

        private String formatJK(String jk) {
            return jk.equals("L") ? "M" : (jk.equals("P") ? "F" : "O");
        }
        
        private String generateUID(String noorder) {
            return "2.25." + Math.abs(noorder.hashCode());
        }
}

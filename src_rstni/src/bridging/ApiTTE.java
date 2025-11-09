package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.Component;
import java.awt.Cursor;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
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
public class ApiTTE {
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String URL = "",  host_port = "", requestJson12 = "", stringbalik = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private sekuel Sequel = new sekuel();
    private boolean x;
    private int i;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();

  
            public void mengunggahFile(String namaFile, String enkodePDF, String noRawat, String nik, String kode,
                        String pwd, String stts, String ptgs, String rm) {
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Content-Type", "application/json;charset=UTF-8");
                        requestJson12
                                = "{"
                                + "\"metadata\": {"
                                + "\"method\": \"kirim_berkas\"},"
                                + "\"data\": {"
                                + "\"no_rawat\": \"" + noRawat + "\","
                                + "\"nik\": \"" + nik + "\","
                                + "\"password\": \"" + pwd + "\","
                                + "\"jns_dokumen\": \"" + kode + "\","
                                + "\"nama_file\": \"" + namaFile + "\","
                                + "\"status_rawat\": \"" + stts + "\","
                                + "\"petugas\": \"" + ptgs + "\","
                                + "\"nomr\": \"" + rm + "\","
                                + "\"file_base64\": \"" + enkodePDF + "\""
                                + "}}";

                        Properties prop = new Properties();
                        prop.loadFromXML(new FileInputStream("setting/database.xml"));
                        host_port = prop.getProperty("HOSTport");
                        //System.out.println("JSON : " + requestJson12);
                        requestEntity = new HttpEntity(requestJson12, headers);
                        stringbalik = getRest().exchange("http://" + host_port + "/ws-tte-simrs/kirim.php", HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Output : " + stringbalik);
                        root = mapper.readTree(stringbalik);
                       // JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                        if (root.path("status").asBoolean() == true) {
                            JOptionPane.showMessageDialog(null, root.path("msg").asText());
                          // System.out.println("Pesan Status Unggah File : " + root.path("msg").asText());
                        } else {
                            JOptionPane.showMessageDialog(null, root.path("msg").asText());
                            System.out.println("Pesan Error : " + root.path("msg").asText());
                        }
                        Sequel.menyimpan("log_tte", "'" + noRawat + "',CURRENT_TIMESTAMP,'" + kode + "','" + root.path("msg").asText() + "'");
                    } catch (Exception erornya) {
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan (" + erornya + "), silakan ulangi lagi,..!!");
                       System.out.println("Notifikasi : " + erornya);
                        Sequel.menyimpan("log_tte", "'" + noRawat + "',CURRENT_TIMESTAMP,'" + kode + "','" + erornya + "'");
            //            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
            //                JOptionPane.showMessageDialog(null, erornya);                
            //            }
                    }
                }
                 
                 
             public void unggahKeServer(String pathLocalPDF, String namaFile) {
                    try {
                        Properties prop = new Properties();
                        prop.loadFromXML(new FileInputStream("setting/database.xml"));
                        String uploadURL =  prop.getProperty("UPLOADTTEPDF");
                        
                        // Baca file PDF lokal
                        Path path = Paths.get(pathLocalPDF);
                        byte[] data = Files.readAllBytes(path);

                        // Encode Base64
                        String encoded = Base64.getEncoder().encodeToString(data);

                        // Buat JSON body
                        String json = "{"
                                + "\"nama_file\": \"" + namaFile + "\","
                                + "\"file_base64\": \"" + encoded + "\""
                                + "}";

                        // Kirim POST
                        URL url = new URL(uploadURL);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        conn.setDoOutput(true);

                        try (OutputStream os = conn.getOutputStream()) {
                            os.write(json.getBytes(StandardCharsets.UTF_8));
                        }

                        // Cek respon dari server
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            //System.out.println("Upload sukses ke " + uploadURL);
                        } else {
                            System.out.println("Upload gagal. HTTP " + code);
                        }

                        conn.disconnect();

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Gagal upload: " + e.getMessage());
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

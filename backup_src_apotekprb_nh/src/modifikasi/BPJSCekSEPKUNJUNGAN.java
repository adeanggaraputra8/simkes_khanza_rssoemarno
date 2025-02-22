
package modifikasi;

import bridging.ApiApotekBPJS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author khanzasoft
 */
public class BPJSCekSEPKUNJUNGAN {
    public String noSep="",faskesasalresep="",nmfaskesasalresep="",nokartu="",
            namapeserta="",jnskelamin="",tgllhr="",pisat="",
            kdjenispeserta="",nmjenispeserta="",kodebu="",
            namabu="",tglsep="",tglplgsep="",jnspelayanan="",nmdiag="",poli="",
            flagprb="",namaprb="",kodedokter="",namadokter="", link="",utc="",URL="",informasi="";
   
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    private ApiApotekBPJS api=new ApiApotekBPJS();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();   
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
        
    public BPJSCekSEPKUNJUNGAN(){
        super();
        try {
            link=koneksiDB.URLAPIAPOTEKBPJS();  
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
    }
    
    public void tampil(String kunjungan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIAPOTEKBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIAPOTEKBPJS());
	    requestEntity = new HttpEntity(headers);
            URL = link+"/sep/"+kunjungan;	
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText());
            informasi="";
            if(nameNode.path("code").asText().equals("200")){
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                //response = root.path("response");
                this.noSep=response.path("noSep").asText();
                namapeserta=response.path("namapeserta").asText();
                faskesasalresep=response.path("faskesasalresep").asText();
                nokartu=response.path("nokartu").asText();
                jnskelamin=response.path("jnskelamin").asText();
                tgllhr=response.path("tgllhr").asText();
                pisat=response.path("pisat").asText();
                kdjenispeserta=response.path("kdjenispeserta").asText();
                nmjenispeserta=response.path("nmjenispeserta").asText();
                kodebu=response.path("kodebu").asText();
                namabu=response.path("namabu").asText();
                tglsep=response.path("tglsep").asText();
                jnspelayanan=response.path("jnspelayanan").asText();
                nmdiag=response.path("nmdiag").asText();
                poli=response.path("poli").asText();
                flagprb=response.path("flagprb").asText();
                namaprb=response.path("namaprb").asText();
                kodedokter=response.path("kodedokter").asText();
                namadokter=response.path("namadokter").asText();
                informasi="OK";
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
}

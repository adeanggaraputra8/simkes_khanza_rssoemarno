/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.print.DocFlavor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.ncipro.calendar.JDateTimePicker;
import widget.Button;
import widget.ComboBox;
import widget.Tanggal;
import widget.TextArea;
import widget.TextBox;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;


/**
 *
 * @author Owner
 */
public final class validasi {
    private int a,j,i,result=0;
    private String s,s1,z,auto,PEMBULATANHARGAOBAT=koneksiDB.PEMBULATANHARGAOBAT();
    private final Connection connect=koneksiDB.condb();
    private final sekuel sek=new sekuel();
    private final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    private final DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");  
    private final DecimalFormat df4 = new DecimalFormat("###,###,###,###,###,###,###.#################");  
    private final DecimalFormat df5 = new DecimalFormat("###,###,###,###,###,###,###.##");  
    private final DecimalFormat df3 = new DecimalFormat("######"); 
    private final DecimalFormat df6 = new DecimalFormat("######.###"); 
    private final DecimalFormat df7 = new DecimalFormat("######.#"); 
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private File file;
    private boolean status=true;
    private final Calendar now = Calendar.getInstance();
    private final int year=(now.get(Calendar.YEAR));
    private static final Properties prop = new Properties();  
	 private String[] nomina={"","satu","dua","tiga","empat","lima","enam",
                         "tujuh","delapan","sembilan","sepuluh","sebelas"};
    
    public validasi(){
        super();
    };

    public void autoNomer(DefaultTableModel tabMode,String strAwal,Integer pnj,javax.swing.JTextField teks){        
        s=Integer.toString(tabMode.getRowCount()+1);
        j=s.length();
        s1="";
        for(i = 1;i<=pnj-j;i++){
            s1=s1+"0";
        }
        teks.setText(strAwal+s1+s);
    }

    public void autoNomer(String tabel,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement("select * from "+tabel);
            try{
               rs=ps.executeQuery();
               rs.last();
               s=Integer.toString(rs.getRow()+1);
               j=s.length();
               s1="";
               for(i = 1;i<=pnj-j;i++){
                   s1=s1+"0";
               }
               teks.setText(strAwal+s1+s);      
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
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer2(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{
                rs=ps.executeQuery();
                rs.last();
                s=Integer.toString(rs.getRow()+1);
                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(strAwal+s1+s);
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
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer3(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(strAwal+s1+s);
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer4(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText((strAwal+s1+s).substring(4,6)+(strAwal+s1+s).substring(2,4)+(strAwal+s1+s).substring(0,2));
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer5(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText((strAwal+s1+s).substring(2,4)+(strAwal+s1+s).substring(0,2)+(strAwal+s1+s).substring(4,6));
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer6(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(s1+s+strAwal);
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer10(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                z="867/";
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(z+s1+s+strAwal);
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public String autoNomer(String tabel,String strAwal,Integer pnj){
        try {
            auto="";
            ps=connect.prepareStatement("select * from "+tabel);
            try{        
                rs=ps.executeQuery();
                rs.last();
                s=Integer.toString(rs.getRow()+1);
                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                auto=strAwal+s1+s;             
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
            
        return auto;        
    }
    
    public String autoNomer3(String sql,String strAwal,Integer pnj){
        try {
            auto="";
            ps=connect.prepareStatement(sql);
            try{
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                auto=strAwal+s1+s;
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
            
        return auto;
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JTextField nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getText()+"'", update);                 
        }
    }
    
    public void editTable(String table,String field_acuan,JTextField nilai_field,String update) {
        if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getText()+"'", update);                 
        }
    }
    
    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,String nilai_field,String update,int i, String[] a) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
        }else if(nilai_field.trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.trim().equals("")){            
            sek.mengedit(table,field_acuan+"="+nilai_field, update,i,a);                 
        }
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JComboBox nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(nilai_field.getSelectedItem()!=""){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getSelectedItem()+"'", update);            
            
        }
    }

    public void editTable(DefaultTableModel tabMode,String table,String field_acuan,JLabel nilai_field,String update) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.mengedit(table,field_acuan+"='"+nilai_field.getText()+"'", update);            
            
        }
    }
    
    public boolean editTabletf(DefaultTableModel tabMode,String table,String field_acuan,JTextField nilai_field,String update) {
        status=true;
        if(tabMode.getRowCount()==0){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            status=sek.mengedittf(table,field_acuan+"='"+nilai_field.getText()+"'", update);                 
        }
        return status;
    }
    
    public boolean editTabletf(DefaultTableModel tabMode,String table,String field_acuan,String nilai_field,String update,int i, String[] a) {
        status=true;
        if(tabMode.getRowCount()==0){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
        }else if(nilai_field.trim().equals("")){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.trim().equals("")){            
            status=sek.mengedittf(table,field_acuan+"="+nilai_field, update,i,a);                 
        }
        return status;
    }
    
    public void fillData(DefaultTableModel model,JTable table, File file) {
        try {
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            model = (DefaultTableModel) table.getModel();

            for (i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            for (i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1, 
                            model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (IOException | WriteException ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }

    public void hapusTable(DefaultTableModel tabMode,JTextField nilai_field,String table,String field) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            sek.meghapus(table,field,nilai_field.getText());            
            
        }
    }

    public void hapusTable(DefaultTableModel tabMode,JComboBox nilai_field,String table,String field) {
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(nilai_field.getSelectedItem()!=""){            
            String data=nilai_field.getSelectedItem().toString();
            sek.meghapus(table,field,data);     
        }
    }
    
    public boolean hapusTabletf(DefaultTableModel tabMode,JTextField nilai_field,String table,String field) {
        status=true;
        if(tabMode.getRowCount()==0){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        }else if(nilai_field.getText().trim().equals("")){
            status=false;
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(! nilai_field.getText().trim().equals("")){            
            status=sek.meghapustf(table,field,nilai_field.getText());   
        }
        return status;
    }

    public void loadCombo(JComboBox cmb,String field,String table){
        cmb.removeAllItems();
        try {
            ps=connect.prepareStatement("select "+field+" from "+table+" order by "+field);
            try{
                rs=ps.executeQuery();
                while(rs.next()){
                    String item=rs.getString(1);
                    cmb.addItem(item);
                    a++;
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
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void loadCombo(JComboBox cmb,String query){
        cmb.removeAllItems();
        try {
            ps=connect.prepareStatement(query);
            try{
                rs=ps.executeQuery();
                while(rs.next()){
                    String item=rs.getString(1);
                    cmb.addItem(item);
                    a++;
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
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public int hariAkhad(int month,int year){ 
        j=0; 
        Calendar calendar=Calendar.getInstance(); 
        calendar.set(year, month-1,1); 
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
        
        for(i=1;i<=days;i++) { 
            calendar.set(year, month-1, i); 
            int day=calendar.get(Calendar.DAY_OF_WEEK); 
            if(day==1){
                j++ ;
            }  
        } 
        
        return j; 
    } 
    
    public int jumlahHari(int month,int year){ 
        Calendar calendar=Calendar.getInstance(); 
        calendar.set(year, month-1,1); 
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
        return days; 
    } 

    public void LoadTahun(JComboBox cmb){        
        cmb.removeAllItems();
        for(i =(year+1);i>=1980;i--){
            cmb.addItem(i);
        }
        cmb.setSelectedItem(year);
    }

    public void LoadTahunAkd(JComboBox cmb){
        cmb.removeAllItems();
        for(i = 1950;i<=year;i++){
            cmb.addItem(i+"1");
            cmb.addItem(i+"2");
        }
        cmb.setSelectedItem(year+"1");
    }

    @SuppressWarnings("empty-statement")
    public void MyReport(String reportName,String reportDirName,String judul,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            try (Statement stm = connect.createStatement()) {
                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void MyReportqry(String reportName,String reportDirName,String judul,String qry){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();

                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    File reportfile=new File(namafile);
                    
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(qry);
                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                    jasperDesign.setQuery(newQuery);
                    
                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                    JasperReport JasperSubReport = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
     @SuppressWarnings("empty-statement")
    public void MyReportPDF(String reportName,String reportDirName,String judul,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            try (Statement stm = connect.createStatement()) {
                try {
                    File f = new File("./"+reportDirName+"/"+reportName.replaceAll("jasper","pdf")); 
                    String namafile="./"+reportDirName+"/"+reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);
                    JasperExportManager.exportReportToPdfFile(jasperPrint,"./"+reportDirName+"/"+reportName.replaceAll("jasper","pdf"));
                    Desktop.getDesktop().open(f);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void MyReport2(String reportName,String reportDirName,String judul,String qry){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();
                
                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    File reportfile=new File(namafile);
                    
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(qry);
                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                    jasperDesign.setQuery(newQuery);
                    
                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void MyReport2(String reportName,String reportDirName,String judul,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            try (Statement stm = connect.createStatement()) {
                try {
                    
                    String namafile="./"+reportDirName+"/"+reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);
                    
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width-50,screen.height-50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReportqry(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


         try {
            ps=connect.prepareStatement(qry);
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                rs=ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters,rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width-50,screen.height-50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReport2(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } 
            } 
        } 

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                File reportfile=new File(namafile);

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(qry);
                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                jasperDesign.setQuery(newQuery);

                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width-50,screen.height-50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReportqrypdf(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            ps=connect.prepareStatement(qry);
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                File f = new File("./"+reportDirName+"/"+reportName.replaceAll("jasper","pdf")); 
                rs=ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);
                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters,rsdt);
                JasperExportManager.exportReportToPdfFile(jasperPrint,"./"+reportDirName+"/"+reportName.replaceAll("jasper","pdf"));
                Desktop.getDesktop().open(f);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReport5(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


        try {
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                File reportfile=new File(namafile);

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(qry);
                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                jasperDesign.setQuery(newQuery);

                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width-50,screen.height-50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void MyReportqry(String reportName,Map parameters,String title){
        try {
                JasperViewer jasperViewer =new JasperViewer(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect), false);
                jasperViewer.setTitle(title);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
                
                //JasperViewer.viewReport(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect),false);
        } catch (Exception ex) {
           System.out.println("Notifikasi : "+ex);
        } 
    }
    
     public void MyReport(String reportName,Map parameters,String title){
        try {
                JasperViewer jasperViewer =new JasperViewer(JasperFillManager.fillReport("./report/"+reportName,parameters,connect), false);
                jasperViewer.setTitle(title);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
                //JasperViewer.viewReport(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect),false);
        } catch (Exception ex) {
           System.out.println("Notifikasi : "+ex);
        } 
    }

   public void MyReportqrynoview(String reportName,String reportDirName,String judul,String qry,Map parameters){
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jrxml ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jrxml berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];


         try {
            ps=connect.prepareStatement(qry);
            try {
                String namafile="./"+reportDirName+"/"+reportName;
                rs=ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters,rsdt);
                
                JasperPrintManager.printReport(jasperPrint, false);

//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle(judul);
//                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
//                jasperViewer.setSize(screen.width-50,screen.height-50);
//                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                jasperViewer.setLocationRelativeTo(null);
//                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null,"Report Can't view because : "+ rptexcpt);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JCheckBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan,JTextField bawah){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            bawah.requestFocus();
        }
    }
    
 public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JButton kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah2(KeyEvent evt, TextArea kiri, ComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(KeyEvent evt, Tanggal kiri, Button kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah2(KeyEvent evt, Button kiri, ComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(KeyEvent evt, ComboBox kiri, Button kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
        public void pindah2(KeyEvent evt, TextArea kiri, Tanggal kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
      public void pindah2(KeyEvent evt, Button kiri, TextBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
      
        
    public void pindah2(KeyEvent evt, Button kiri, Button kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JTextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JComboBox kiri, JTextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JButton kanan,JTextField bawah){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            bawah.requestFocus();
        }
    }
    
   

    public void pindah(java.awt.event.KeyEvent evt,JButton kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JButton kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextField kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JTextArea kiri,JComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JDateTimePicker kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(java.awt.event.KeyEvent evt,JCheckBox kiri,JDateTimePicker kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JDateTimePicker kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt,JComboBox kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JButton kiri, JComboBox kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JButton kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JTextField kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(KeyEvent evt, JTextArea kiri, JTextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah(KeyEvent evt, Button kiri, TextArea kanan) {
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void panggilUrl(String url){
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();                                
        try{ 
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            if(os.contains("win")) {
                rt.exec( "rundll32 url.dll,FileProtocolHandler " + "http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+url);
            }else if (os.contains("mac")) {
                rt.exec( "open " + "http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+url);
            }else if (os.contains("nix") || os.contains("nux")) {
                String[] browsers = {"x-www-browser","epiphany", "firefox", "mozilla", "konqueror","chrome","chromium","netscape","opera","links","lynx","midori"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for(i=0; i<browsers.length; i++) cmd.append(i==0  ? "" : " || ").append(browsers[i]).append(" \"").append("http://").append(koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")).append("/").append(prop.getProperty("HYBRIDWEB")).append("/").append(url).append( "\" ");
                rt.exec(new String[] { "sh", "-c", cmd.toString() });
            } 
        }catch (Exception e){
            System.out.println("Notif Browser : "+e);
        } 
    }
    
    public void panggilUrl2(String url){
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();                                
        try{ 
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            if(os.contains("win")) {
                rt.exec( "rundll32 url.dll,FileProtocolHandler "+url);
            }else if (os.contains("mac")) {
                rt.exec( "open " +url);
            }else if (os.contains("nix") || os.contains("nux")) {
                String[] browsers = {"x-www-browser","epiphany", "firefox", "mozilla", "konqueror","chrome","chromium","netscape","opera","links","lynx","midori"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for(i=0; i<browsers.length; i++) cmd.append(i==0  ? "" : " || ").append(browsers[i]).append(" \"").append(url).append( "\" ");
                rt.exec(new String[] { "sh", "-c", cmd.toString() });
            } 
        }catch (Exception e){
            System.out.println("Notif Browser : "+e);
        } 
    }
    
    public void printUrl(String url) throws URISyntaxException{
        try{
           Properties prop = new Properties();
           prop.loadFromXML(new FileInputStream("setting/database.xml"));            
           desktop.print(new File(new java.net.URI("http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+url)));  
        }catch (Exception e) {
           System.out.println(e);
        }
    }

    public void SetTgl(DefaultTableModel tabMode,JTable table,JDateTimePicker dtp,int i){
        j=table.getSelectedRow();
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tabMode.getValueAt(j,i).toString());
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
      public void SetTglSITB(DefaultTableModel tabMode,JTable table,JDateTimePicker dtp,int i){
        j=table.getSelectedRow();
        try {
           Date dtpa = new SimpleDateFormat("yyyyMMdd").parse(tabMode.getValueAt(j,i).toString().replaceAll("'",""));
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
    
    public String SetTgl(String original){
        s = "";
        try {
            s=original.substring(6,10)+"-"+original.substring(3,5)+"-"+original.substring(0,2);
        }catch (Exception e) {
        }   
        return s;
    }
    
    public String SetTglSITB(String original){
        original=original.replaceAll("'","");
        s = "";
        try {
            s=original.substring(6,10)+""+original.substring(3,5)+""+original.substring(0,2);
        }catch (Exception e) {
        }   
        return s;
    }
    
    public String SetTgl3(String original){
        s = "";
        try {
            s=original.substring(8,10)+"-"+original.substring(5,7)+"-"+original.substring(0,4);
        }catch (Exception e) {
        }   
        return s;
    }
    
    public String MaxTeks(String original,int max){
        if(original.length()>=max){
            s=original.substring(0,(max-1));
        }else{
            s=original;
        }
        return original;
    }
    
    public void SetTgl(JDateTimePicker dtp,String tgl){            
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl.replaceAll("'",""));
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
     public void SetTglSITB(JDateTimePicker dtp,String tgl){            
        try {
           Date dtpa = new SimpleDateFormat("yyyyMMdd").parse(tgl.replaceAll("'",""));
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    

    public void SetTgl2(JDateTimePicker dtp,String tgl){            
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tgl);
           dtp.setDate(dtpa);
        } catch (ParseException ex) {
           dtp.setDate(new Date());
        }
    }
    
    public Date SetTgl2(String tgl){
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
           return dtpa;
        } catch (ParseException ex) {
           return new Date();
        }
    }
    
    public Date SetTgl4(String tgl){
        
        try {
           Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
           return dtpa;
        } catch (ParseException ex) {
           return new Date();
        }
    }
    
    public String SetTglJam(String original){
        original=original.replaceAll("'","");
        s = "";
        try {
            s=original.substring(6,10)+"-"+original.substring(3,5)+"-"+original.substring(0,2)+" "+original.substring(11,19);
        }catch (Exception e) {
        }   
        return s;
    }
    
    
    
    public void textKosong(JTextField teks,String pesan){
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void textKosong(JTextArea teks,String pesan){
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }
    
    public void textKosong(JButton teks,String pesan){
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void tabelKosong(DefaultTableModel tabMode) {
        j=tabMode.getRowCount();
        for(i=0;i<j;i++){
            tabMode.removeRow(0);
        }
    }

    public void textKosong(JComboBox teks, String pesan) {
        JOptionPane.showMessageDialog(null,"Maaf, "+pesan+" tidak boleh kosong...!!!");
        teks.requestFocus();
    }
    
    public String SetAngka(double nilai){        
       return df2.format(nilai);
    }
    
    public String SetAngka3(double nilai){        
       return df4.format(nilai);
    }
    
    public String SetAngka4(double nilai){        
       return df5.format(nilai);
    }
    
    public String SetAngka2(double nilai){        
       return df3.format(nilai);
    }
    
    public String SetAngka5(double nilai){        
       return df6.format(nilai);
    }
    
    public String SetAngka6(double nilai){        
       return df7.format(nilai);
    }
    
    public double SetAngka7(double nilai){        
       return Double.parseDouble(df7.format(nilai));
    }
    
    public double SetAngka8(double value,int places){      
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
    
    public double SetAngka(String txt){
        double x;   
        try {
            if(txt.equals("")){
                x=0;
            }else{
                x=Double.parseDouble(txt);
            }
        } catch (Exception e) {
            x=0;
        }
            
        return x;
    }
    
      public int SetInteger(String txt){
        int x;   
        try {
            if(txt.equals("")){
                x=0;
            }else{
                x=Integer.parseInt(txt);
            }
        } catch (Exception e) {
            x=0;
        }
            
        return x;
    }
    
    public double roundUp(double number, int multiple) {
        if(PEMBULATANHARGAOBAT.equals("yes")){
            result = multiple;
            if (number % multiple == 0) {
                return (int) number;
            }

            if (number % multiple != 0) {
                int division = (int) ((number / multiple) + 1);
                result = division * multiple;
            }
            return result;
        }else{
            return Math.round(number);
        }
    }
    
    public void autoNomer7(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(s1+s+strAwal);
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void autoNomer8(String sql,String strAwal,Integer pnj,javax.swing.JTextField teks){
        try {
            ps=connect.prepareStatement(sql);
            try{   
                rs=ps.executeQuery();
                s="1";
                while(rs.next()){
                    s=Integer.toString(Integer.parseInt(rs.getString(1))+1);
                }            

                j=s.length();
                s1="";
                for(i = 1;i<=pnj-j;i++){
                    s1=s1+"0";
                }
                teks.setText(s1+s+strAwal);
             }catch(Exception e){
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, Query tidak bisa dijalankan...!!!!");
             }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    }

 public String terbilang(double angka){
        if(angka<12)
        {
          return nomina[(int)angka];
        }
        
        if(angka>=12 && angka <=19)
        {
            return nomina[(int)angka%10] +" belas ";
        }
        
        if(angka>=20 && angka <=99)
        {
            return nomina[(int)angka/10] +" puluh "+nomina[(int)angka%10];
        }
        
        if(angka>=100 && angka <=199)
        {
            return "seratus "+ terbilang(angka%100);
        }
        
        if(angka>=200 && angka <=999)
        {
            return nomina[(int)angka/100]+" ratus "+terbilang(angka%100);
        }
        
        if(angka>=1000 && angka <=1999)
        {
            return "seribu "+ terbilang(angka%1000);
        }
        
        if(angka >= 2000 && angka <=999999)
        {
            return terbilang((int)angka/1000)+" ribu "+ terbilang(angka%1000);
        }
        
        if(angka >= 1000000 && angka <=999999999)
        {
            return terbilang((int)angka/1000000)+" juta "+ terbilang(angka%1000000);
        }
        
        return "";
    }
 
     public void MyReportToExcel(String qry1, String nm_filenya) {
        try {
            ps1 = connect.prepareStatement(qry1);
            rs1 = ps1.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs1, sheet);

            writeDataLines(rs1, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
    }
 
  private void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        // exclude the first column which is the ID field
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnLabel(i);
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(columnName);
        }
    }
    
 private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet)
            throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        int rowCount = 1;

        while (result.next()) {
            Row row = sheet.createRow(rowCount++);

            for (int i = 1; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);

                Cell cell = row.createCell(i - 1);

                if (valueObject instanceof Boolean) {
                    cell.setCellValue((Boolean) valueObject);
                } else if (valueObject instanceof Double) {
                    cell.setCellValue((double) valueObject);
                } else if (valueObject instanceof Float) {
                    cell.setCellValue((float) valueObject);
                } else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatDateCell(workbook, cell);
                } else {
                    cell.setCellValue((String) valueObject);
                }

            }

        }
    }
 
     private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }
     
       public boolean MyReportToExcelBoolean(String qry1, String nm_filenya) {
        try {
            ps1 = connect.prepareStatement(qry1);
            rs1 = ps1.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs1, sheet);

            writeDataLines(rs1, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
     
     
     public boolean MyReportToExcelBooleanRs(ResultSet rs2, String nm_filenya) {
        try {            

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs2, sheet);

            writeDataLines(rs2, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
     
      public String openDialog(){
        String x;
        x = "";
        JFileChooser c = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        c.setMultiSelectionEnabled(true);
        int r = c.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // get the selelcted files 
            File files[] = c.getSelectedFiles();            
            
            x = c.getSelectedFile().getAbsolutePath();
           
        } else {
            x = "the user cancelled the operation";
        }
        return x ;
    }
       
    public int daysOld(String path) {
        file=new File(path);
        if (file.lastModified() < 1) return 0;
        return milliToDay(Calendar.getInstance().getTimeInMillis() - file.lastModified());
    }

    /**
     * Converts milliseconds to days
     */
    public static int milliToDay(long milli) {
        return (int) ((double) milli / (1000 * 24 * 60 * 60));
    }
    
    public void printReportQuerySmc(String reportName, String reportDirName, String title, Map params, String namaPrinter, int jumlah, String sql, String... values) {
        String currentDir = System.getProperties().getProperty("user.dir");
        File dir = new File(currentDir);
        File report = null;
        if (dir.isDirectory()) {
            for (String file: dir.list()) {
                report = new File(currentDir + File.separatorChar + file + File.separatorChar + reportName);                
                if (report.isFile()) {
                    System.out.println("Found report file at: " + report.toString());
                    break;
                }
            }
        }
        if (report == null) {
            JOptionPane.showMessageDialog(null, "File tidak ditemukan!");
            return;
        }
        
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                ps.setString(i + 1, values[i]);
            }            
            PrintService printService = null;
            for (PrintService currentPrintService: PrintServiceLookup.lookupPrintServices(null, null)) {
                if (currentPrintService.getName().equals(namaPrinter)) {
                    System.out.println("Printer ditemukan: " + currentPrintService.getName());
                    printService = currentPrintService;
                    break;
                }
            }
            JasperPrint jp = JasperFillManager.fillReport((JasperReport) JRLoader.loadObject(report), params, new JRResultSetDataSource(ps.executeQuery()));
            if (printService == null) {
                JOptionPane.showMessageDialog(null, "Printer tidak ditemukan!");
                JasperViewer jv = new JasperViewer(jp, false);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jv.setTitle(title);                
                jv.setSize(screen.width - 50, screen.height - 50);
                jv.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jv.setLocationRelativeTo(null);
                jv.setVisible(true);
            } else {
                PrintRequestAttributeSet pra = new HashPrintRequestAttributeSet();
                pra.add(new Copies(jumlah));
                SimplePrintServiceExporterConfiguration config = new SimplePrintServiceExporterConfiguration();
                config.setPrintService(printService);
                config.setPrintRequestAttributeSet(pra);
                config.setPrintServiceAttributeSet(printService.getAttributes());
                config.setDisplayPageDialog(false);
                config.setDisplayPrintDialog(false);
                JRPrintServiceExporter exporter = new JRPrintServiceExporter();            
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setConfiguration(config);
                exporter.exportReport();
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            JOptionPane.showMessageDialog(null, "Tidak dapat menemukan printer untuk mencetak!");
        }
    }
    
    public void reportQuerySmc(String filename, String directory, String title, Map params, String sql, String... values) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                for (int i = 0; i < values.length; i++) {
                    ps.setString(i + 1, values[i]);
                }
                JRResultSetDataSource rsdt = new JRResultSetDataSource(ps.executeQuery());
                JasperPrint jasperPrint = JasperFillManager.fillReport("./" + directory + "/" + filename, params, rsdt);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(title);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    
//    contoh 
//    Map<String, Object> param = new HashMap<>();
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());
//            Valid.printReportQuerySmc("rptAntriLoket.jasper", "report", "::[ Antrian Loket ]::", param, koneksiDB.PRINTERCETAKANTREAN(), 1,
//                "select date_format(tanggal, '%d-%m-%Y') as tanggal, lpad(nomor, greatest(length(nomor), 3), '0') as nomor, jam from antriloketcetak_smc where nomor = ? and tanggal = current_date()",
//                String.valueOf(Integer.parseInt(LabelNomor.getText()))
//            );

        public String saveToPDF(String reportName, String reportDirName, String judul, Map parameters) {
        String a = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String saveDir = prop.getProperty("FOLDERPDF");

            File d = new File(saveDir);
            boolean cek = d.isDirectory();

            if (cek == false) {
                Files.createDirectories(Paths.get(saveDir));
            }

            try (Statement stm = connect.createStatement()) {
                try {
                    File f = new File("./" + reportDirName + "/" + reportName.replaceAll("jasper", "pdf"));
                    String namafile = "./" + reportDirName + "/" + reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);
//                    JasperExportManager.exportReportToPdfFile(jasperPrint, saveDir + "/" + reportName.replaceAll("jasper", "pdf"));
                    JasperExportManager.exportReportToPdfFile(jasperPrint, saveDir + "/" + judul.replaceAll("/", "") + "-rsum.pdf");
                    
                    a = saveDir + "/" + judul.replaceAll("/", "") + "-rsum.pdf";
//                    Desktop.getDesktop().open(f);
                    //JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data . . .");
                } catch (Exception rptexcpt) {

                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
        
     
  
     
    public void convert2pdf( String URL,String namafile) throws IOException {
        String a="";  
        Properties prop = new Properties();
        prop.loadFromXML(new FileInputStream("setting/database.xml"));
        String phpUrl = "http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+URL; // URL file PHP
        String outputPdfPath = prop.getProperty("FOLDERPDF");

      
        File d = new File(outputPdfPath);
            boolean cek = d.isDirectory();

            if (cek == false) {
                Files.createDirectories(Paths.get(outputPdfPath));
            }   
            try {
                a = outputPdfPath+namafile.replaceAll("/", "")+"-bill.pdf";
                // Baca konten dari PHP
                String htmlContent = getHtmlFromPhp(phpUrl);
                //System.out.println("HTML dari PHP: \n" + htmlContent); // Debugging

                // Konversi ke PDF
                HtmlConvertoPDF(htmlContent,a);
            }  catch (Exception e) {
               System.out.println(e);
            }
      
    }

    // Fungsi untuk membaca output dari file PHP
    public static String getHtmlFromPhp(String phpUrl) throws IOException {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        BufferedReader rd = null;

        try {
            URL url = new URL(phpUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (rd != null) rd.close();
            if (conn != null) conn.disconnect();
        }
        return result.toString();
    }

    public void HtmlConvertoPDF(String htmlContent,String namefile){        
        try {
            PdfWriter pdf = new PdfWriter(namefile);
                HtmlConverter.convertToPdf(
                   htmlContent,pdf);
                  System.out.println("Berhasil Membuat fil PDF billing. . .!");
                File f = new File(namefile);   
        }  catch (Exception e) {
               System.out.println(e);
        }
        
    }
    
   
      public void gabungfile(String NameFile) {
 
        try {
              Properties prop = new Properties();
              prop.loadFromXML(new FileInputStream("setting/database.xml"));
              String PdfPath = prop.getProperty("FOLDERPDF");
                File folder = new File(PdfPath+"/");
            
            if (folder.isDirectory()) {
                 File[] files = folder.listFiles((dir, name) -> name.startsWith(NameFile) && name.endsWith(".pdf"));

                 // Membuat array untuk file yang akan digabungkan
                    if (files != null && files.length >= 2) {
                        String[] filesToMerge = new String[files.length];
                        for (int i = 0; i < files.length; i++) {
                            filesToMerge[i] = files[i].getAbsolutePath();
                        }

                        // Membuat objek PDFMergerUtility untuk menggabungkan file
                        String outputPDF = PdfPath + "/" + NameFile + "-marge.pdf";
                        PDFMergerUtility ut = new PDFMergerUtility();

                        // Menambahkan file-file ke dalam PDFMergerUtility
                        for (String file : filesToMerge) {
                            ut.addSource(file);
                        }

                        // Menetapkan file tujuan untuk hasil gabungan
                        ut.setDestinationFileName(outputPDF);

                        // Menggabungkan dokumen PDF
                        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

                        // Menampilkan pesan sukses
                        JOptionPane.showMessageDialog(null, "Proses gabung file selesai..!");
                    } else {
                    JOptionPane.showMessageDialog(null, "File tidak ditemukan. Pastikan file ada di folder yang benar.");
                }
            }
        } catch (IOException e) {
                System.out.println("Notif : "+e);
                JOptionPane.showMessageDialog(null,"Gagal menggabungkan file, cek kembali file apakah sudah dalam bentuk PDF.\nAtau cek kembali hak akses file di server dokumen..!!");
        }
    }
      
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import fungsi.koneksiDB;

/**
 *
 * @author Owner
 */
public class WarnaTablePasien extends DefaultTableCellRenderer {
    private sekuel Sequel=new sekuel();
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1){
            component.setBackground(new Color(255,246,244));
            component.setForeground(new Color(50,50,50));
        }else{
           component.setBackground(new Color(255,255,255));
           component.setForeground(new Color(50,50,50));
        }
        
        if(table.getValueAt(row,45).toString().trim().equals("Prolanis")){
            component.setBackground(Color.GREEN);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,45).toString().trim().equals("PRB")){
           component.setBackground(Color.YELLOW);
           component.setForeground(Color.BLACK);
        }else if (Sequel.cariInteger("SELECT count(p.no_rkm_medis) FROM pasien p INNER JOIN status_pasien sp ON sp.no_rm=p.no_rkm_medis WHERE p.no_rkm_medis IN (SELECT rp.no_rkm_medis FROM reg_periksa rp INNER JOIN pemeriksaan_ralan pr ON rp.no_rawat=pr.no_rawat WHERE SUBSTRING(pr.tensi,1,3) > 159 ) AND sp.status = '-' and p.no_rkm_medis='"+table.getValueAt(row,1).toString()+"' ")>0) {
           component.setBackground(Color.RED);
           component.setForeground(Color.BLACK);    
        } else{
           component.setBackground(new Color(255,255,255));
           component.setForeground(new Color(70,70,70));
        }
        
        return component;
    }

}

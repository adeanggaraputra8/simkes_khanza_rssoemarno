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
public class WarnaTableDaftarResep extends DefaultTableCellRenderer {
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
        if(Sequel.cariInteger("SELECT count(rd.kode_brng) FROM resep_dokter rd inner join databarang d on d.kode_brng = rd.kode_brng  WHERE d.kode_golongan='G008' and rd.no_resep ='"+table.getValueAt(row,0).toString()+"' ")>0){
           component.setBackground(Color.BLUE);
           component.setForeground(Color.WHITE);
        }else if(Sequel.cariInteger("SELECT count(rd.kode_brng) FROM resep_dokter rd inner join databarang d on d.kode_brng = rd.kode_brng  WHERE d.kode_golongan='G013' and rd.no_resep ='"+table.getValueAt(row,0).toString()+"' ")>0){
           component.setBackground(Color.RED);
           component.setForeground(Color.WHITE);
        }else{
           component.setBackground(new Color(255,255,255));
           component.setForeground(new Color(70,70,70));
        }
        return component;
    }

}

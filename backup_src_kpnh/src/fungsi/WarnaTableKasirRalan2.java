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
public class WarnaTableKasirRalan2 extends DefaultTableCellRenderer {
      private sekuel Sequel=new sekuel();
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       if (row % 2 == 1){
            component.setBackground(new Color(255,246,244));
        }else{
            component.setBackground(new Color(255,255,255));
        } 
        if(Sequel.cariInteger("select pr.no_rawat from pemeriksaan_ralan pr inner join reg_periksa rp on rp.no_rawat= pr.no_rawat  where pr.no_rawat='"+table.getValueAt(row,11).toString()+"' and rp.stts='Belum' ")>1){
            component.setBackground(new Color(200,0,0));
            component.setForeground(new Color(255,230,230));
        }else if(table.getValueAt(row,10).toString().equals("Sudah")){
            component.setBackground(new Color(255,255,255));
            component.setForeground(new Color(70,70,70));
        }
        return component;
    }

}

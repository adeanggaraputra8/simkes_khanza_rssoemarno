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
public class WarnaTablePemeriksaanInap extends DefaultTableCellRenderer {
      private sekuel Sequel=new sekuel();
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       if (row % 2 == 1){
            component.setBackground(new Color(247,255,243));
        }else{
           component.setBackground(new Color(255,255,255));
        } 
        if(Sequel.cariInteger("select count(kd_dokter) from dokter where kd_dokter= '"+table.getValueAt(row,22).toString()+"' ")>0){
           component.setBackground(Color.GREEN);
           component.setForeground(Color.BLACK);
        }else{
          component.setBackground(new Color(255,255,255));
        }
        return component;
    }

}

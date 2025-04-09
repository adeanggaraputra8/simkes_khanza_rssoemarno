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
public class WarnaTableVedika extends DefaultTableCellRenderer {
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
        if(table.getValueAt(row,8).toString().trim().equals("InputInacbg") ){
           component.setBackground(Color.BLUE);
           component.setForeground(Color.BLACK);
        }else if (table.getValueAt(row,8).toString().trim().equals("Verif")){
            component.setBackground(Color.GREEN);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,8).toString().trim().equals("Pending")){
           component.setBackground(Color.YELLOW);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,8).toString().trim().equals("Final Klaim")){
           component.setBackground(Color.MAGENTA);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,8).toString().trim().equals("Revisi")){
           component.setBackground(Color.RED);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,8).toString().trim().equals("Validasi")){
           component.setBackground(Color.gray);
           component.setForeground(Color.BLACK);
        }else if(table.getValueAt(row,8).toString().trim().equals("Sudah Revisi")){
           component.setBackground(Color.CYAN);
           component.setForeground(Color.BLACK);
        }else{
           component.setBackground(new Color(255,255,255));
           component.setForeground(new Color(70,70,70));
        }
        return component;
    }

}

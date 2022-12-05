package app;

import Enums.Shift;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class StatusColumnCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        Color text = new Color(0,0,0);
        Color day = new Color(10,220,220);
        Color swing = new Color(100,100,150);
        Color night = new Color(200,50,120);

        //Get the status for the current row.
        if (value.equals("Day")) {
            l.setBackground(day);
            l.setForeground(text);
        } else if(value.equals("Swing")){
            l.setBackground(swing);
            l.setForeground(text);
        }else if(value.equals("Night")){
            l.setBackground(night);
            l.setForeground(text);
        }else{
            l.setBackground(Color.LIGHT_GRAY);
        }

        //Return the JLabel which renders the cell.
        return l;

    }
}
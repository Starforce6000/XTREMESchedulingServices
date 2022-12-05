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

        //Get the status for the current row.
        if (value.equals(Shift.Day)) {
            l.setBackground(new Color(61,133,198));
        } else if(value.equals(Shift.Swing)){
            l.setBackground(new Color(11, 83, 148));
        }else{
            l.setBackground(new Color(7, 55, 99));
        }

        //Return the JLabel which renders the cell.
        return l;

    }
}
package app;

import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {
    private final TemplateFrame lab7;
    private final MainFrame mainFrame;

    private String[] columnNames = {"Shift Name","Shift Time","Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"};
    private String[] nul = {"Day","(8:00am - 5:00pm)"," ", " ", " ", " ", " ", " ", " "};
    private String[] nul2 = {"Swing","(4:00pm - 12:00am)"," ", " ", " ", " ", " ", " ", " "};
    private String[] nul3 = {"Night","(11:00pm - 9:00am)"," ", " ", " ", " ", " ", " ", " "};
    private String[][] data = {nul,nul2,nul3};

    public MyTableModel(TemplateFrame lab7) {
        this.lab7 = lab7;
        mainFrame = null;
    }
    public MyTableModel(MainFrame mainFrame) {
        lab7 = null;
        this.mainFrame = mainFrame;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public String getValueAt(int row, int col) {
        return data[row][col];
    }

    public String[][] getData() {
        return data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setData(int row, int length, String option, String[] line){
        String[][] tmp = data;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                tmp[i][j] = data[i][j];
            }
        }
        data = new String[row+1][length];
        for(int i = 0; i < tmp.length; i++){
            for(int j = 0; j < tmp[i].length; j++){
                data[i][j] = tmp[i][j];
            }
        }
        data[row][0] = line[0];
        data[row][1] = line[1];
        data[row][2] = line[2];
        data[row][3] = line[3];
        data[row][4] = line[4];
        data[row][5] = line[5];
        data[row][6] = line[6];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(String value, int row, int col) {
        /*
        if (lab7.isDEBUG()) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

         */

        data[row][col] = value;
        fireTableCellUpdated(row, col);
/*
        if (lab7.isDEBUG()) {
            System.out.println("New value of data:");
            printDebugData();
        }

 */
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}

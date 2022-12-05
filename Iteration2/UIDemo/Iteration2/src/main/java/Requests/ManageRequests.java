package Requests;

import Enums.RequestStatus;
import Enums.RequestType;
import Models.Employee;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ManageRequests extends JPanel {
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    ArrayList<Request> requests = new ArrayList<>();

    public ManageRequests(ArrayList<Request> reqs) {
        super();
        JFrame manage = new JFrame("Manage Requests");
        manage.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columNames = {"Status", "Employee", "Type", "Day",
                                "Shift", "Reason", "Manage"};
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class,
                String.class, String.class, String.class, JButton.class
        };
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column < 6) {return false;}
                return true;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        model.setColumnIdentifiers(columNames);

        sorter = new TableRowSorter<>(model);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        for(int i = 0; i < model.getColumnCount(); i++) {
            sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
        }
        sorter.setSortKeys(sortKeys);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);

        Action view = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                //JPanel listPane = new JPanel();
                JFrame manageWindow = new JFrame();
                manageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                manageWindow.setLayout(new BoxLayout(manageWindow,BoxLayout.Y_AXIS));
                manageWindow.setSize(500,200);

                JLabel reason = new JLabel((String) model.getValueAt(modelRow, 5));

                JButton deny = new JButton("Deny Request");
                deny.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.setValueAt("DENIED", modelRow, 0);
                        //TODO: make the change reflected in the database
                    }
                });

                JButton approve = new JButton("Approve Request");
                approve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.setValueAt("APPROVED", modelRow, 0);
                    }
                });
                manageWindow.add(reason);
                manageWindow.add(deny);
                manageWindow.add(approve);
                manageWindow.pack();
                manageWindow.setVisible(true);
            }
        };
        //ButtonCol

        scrollPane.add(filterHeader);
        add(scrollPane);
        this.requests.addAll(reqs);
        fillTable();
    }

    public void init(ArrayList<Request> reqs) {
        JFrame frame = new JFrame("Manage Requests");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ManageRequests newContentPane = new ManageRequests(reqs);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public void fillTable() {
        for(Request r : requests) {
            ((DefaultTableModel)table.getModel()).addRow(new Object[]
                    {r.getStatus().toString(), r.getEmp().getName(),
                            r.getType().toString(), r.getDay().toString(),
                            r.getStatus().toString(), r.getReason(),"View"});
        }
    }
}

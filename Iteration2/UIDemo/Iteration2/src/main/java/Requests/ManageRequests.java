package Requests;

import DAO.RequestDAO;
import Enums.RequestStatus;
import Enums.RequestType;
import Models.Employee;
import app.ButtonColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManageRequests extends JPanel {
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    ArrayList<Request> requests = new ArrayList<>();

    ArrayList<Employee> employees = new ArrayList<>();

    RequestDAO dao;

    public ManageRequests() {
        super();
        JFrame manage = new JFrame("Manage Requests");
        manage.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columNames = {"Status", "Employee", "Type", "Day",
                                "Shift", "Reason", "ID", "Manage"};
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class,
                String.class, String.class, Integer.class, JButton.class
        };
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column < 7) {return false;}
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
                //manageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                manageWindow.setLayout(new GridLayout(2,2));
                manageWindow.setSize(500,200);

                JLabel label = new JLabel("Reason for Request:");
                JLabel reason = new JLabel((String) model.getValueAt(modelRow, 5));

                JButton deny = new JButton("Deny Request");
                deny.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.setValueAt("DENIED", modelRow, 0);
                        //TODO: make the change reflected in the database
                        for(Request r : requests) {
                            if(r.getId() == (Integer)model.getValueAt(modelRow, 6)) {
                                r.setStatus(RequestStatus.DENIED);
                            }
                        }
                        manageWindow.setVisible(false);
                        manageWindow.dispose();
                    }
                });

                JButton approve = new JButton("Approve Request");
                approve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.setValueAt("APPROVED", modelRow, 0);
                        //TODO: make the change reflected in the database
                        for(Request r : requests) {
                            if(r.getId() == (Integer)model.getValueAt(modelRow, 6)) {
                                r.setStatus(RequestStatus.APPROVED);
                            }
                        }
                        manageWindow.setVisible(false);
                        manageWindow.dispose();
                    }
                });
                manageWindow.add(label);
                manageWindow.add(reason);
                manageWindow.add(deny);
                manageWindow.add(approve);
                manageWindow.pack();
                manageWindow.setVisible(true);
            }
        };
        ButtonColumn viewButton = new ButtonColumn(table, view, 7);
        viewButton.setMnemonic(KeyEvent.VK_D);

        scrollPane.add(filterHeader);
        add(scrollPane);
        //this.requests.addAll(reqs);
        dao = new RequestDAO(employees);
        try {
            requests = dao.loadRequestsFromFile(new File("requests.csv"));
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        fillTable();
    }

    public void init(ArrayList<Employee> emps) throws IOException {
        JFrame frame = new JFrame("Manage Requests");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        employees.addAll(emps);

        ManageRequests newContentPane = new ManageRequests();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        JButton done = new JButton("Finished");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        frame.add(done);

        frame.pack();
        frame.setVisible(true);
    }

    public void fillTable() {
        for(Request r : requests) {
            ((DefaultTableModel)table.getModel()).addRow(new Object[]
                    {r.getStatus().toString(), r.getEmp().getName(),
                            r.getType().toString(), r.getDay().toString(),
                            r.getDay().getShift().toString(), r.getReason(),
                            r.getId(), "View"});
        }
    }
}

package app;

import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import DAO.RequestDAO;
import Enums.*;
import Models.Department;
import Models.Employee;
import Requests.MakeRequest;
import Requests.ManageRequests;
import Requests.Request;
import javazoom.jl.player.Player;
import Schedule.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class InitFrame extends JFrame{
    JFrame frame;
    JTable theTable;
    SpringLayout layout;
    Boolean logged, admin;
    JButton left = new JButton("<");
    JButton right = new JButton(">");
    JTextField week = new JTextField("Week of: 12/04 - 12/10");
    JComboBox<String> depCB;
    JComboBox<String> userList;
    //JButton conf = new JButton("Find Employee");
    MyTableModel tableModel;

    ArrayList<Request> requests;
    ArrayList<Department> departments;
    ArrayList<Employee> employees;

    Employee user;

    public InitFrame(JFrame frame,
                     JTable theTable,
                     SpringLayout layout,
                     Boolean logged,
                     Boolean admin,
                     ArrayList<Request> requests,
                     ArrayList<Department> departments,
                     ArrayList<Employee> employees,
                     MyTableModel tableModel){
        this.frame = frame;
        this.theTable = theTable;
        this.layout = layout;
        this.logged = logged;
        this.admin = admin;
        this.requests = requests;
        this.departments = departments;
        this.employees = employees;
        this.tableModel = tableModel;
    }
    void initFrame(Employee user){
        this.user = user;

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLayout(layout);
        theTable.setRowHeight(50);
        theTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        for (int i = 2; i < theTable.getColumnCount(); i++) {
            theTable.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer());
        }


        // Set the Main Menu
        if(logged) {
            frame.setJMenuBar(initHead());
            userBar();
            initCalendar();
        }
        else{
            frame.setVisible(false);
            goodbye();
        }
    }
    JMenuBar initHead(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logout = new JMenuItem("Logout");
        JButton addSchedule = new JButton("Add Schedule");
        JButton request = new JButton("Pending Requests");
        JButton makeReq = new JButton("Make Request");
        JMenuItem saveAll = new JMenuItem("Save All");
        JMenuItem makeActive = new JMenuItem("Set Active");
        //JButton addEmployee = new JButton("Add Employee");

        if(!admin){
            request.setEnabled(false);
            saveAll.setEnabled(false);
            addSchedule.setEnabled(false);
            makeActive.setEnabled(false);
            //addEmployee.setEnabled(false);
        }



        makeActive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame scheduleActiveFrame = new JFrame("Set active schedule for departments");
                scheduleActiveFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                scheduleActiveFrame.setLayout(new FlowLayout());
                JButton activate = new JButton("Set Selected as Active");
                ArrayList<String> deptList = new ArrayList<>();
                for(Department d : departments) {
                    deptList.add(d.getName());
                }
                ArrayList<String> scheduleList = new ArrayList<>();
                for(Schedule s : departments.get(0).getSchedules()) {
                    scheduleList.add(s.getName());
                }

                JComboBox<String> departmentBox = new JComboBox<>(deptList.toArray(new String[0]));
                JComboBox<String> scheduleBox = new JComboBox<>(scheduleList.toArray(new String[0]));

                scheduleActiveFrame.add(departmentBox);
                scheduleActiveFrame.add(scheduleBox);
                scheduleActiveFrame.add(activate);

                scheduleActiveFrame.setSize(500,100);
                scheduleActiveFrame.setVisible(true);

                activate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String deptName = departmentBox.getSelectedItem().toString();
                        String scheduleName = scheduleBox.getSelectedItem().toString();

                        Department target = null;
                        for(Department d : departments) {
                            if(d.getName().equals(deptName)) {
                                target = d;
                            }
                        }
                        for(Schedule s : target.getSchedules()) {
                            if(s.getName().equals(scheduleName)) {
                                target.setActiveSchedule(s);
                            }
                        }

                        updateScheduleTable();
                    }
                });

                departmentBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scheduleList.removeAll(scheduleList);
                        String deptName = departmentBox.getSelectedItem().toString();
                        Department target = null;
                        for(Department d : departments) {
                            if(d.getName().equals(deptName)) {
                                target = d;
                            }
                        }
                        for(Schedule s : target.getSchedules()) {
                            scheduleList.add(s.getName());
                        }

                        ComboBoxModel<String> temp = new DefaultComboBoxModel<>(scheduleList.toArray(new String[0]));
                        scheduleBox.setModel(temp);
                    }
                });
            }
        });

        // Logout
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame confirm = new JFrame("Confirm Logout");
                confirm.setLayout(new GridLayout(1,2));
                confirm.setSize(600,100);
                JButton yes = new JButton("Yes");
                JButton no = new JButton("No");
                confirm.add(no);
                confirm.add(yes);

                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        confirm.setVisible(false);
                        goodbye();
                    }
                });

                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirm.setVisible(false);
                    }
                });
                confirm.setVisible(true);
            }
        });

        saveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(InitFrame.this,
                        "Do you really want to Save all changes?",
                        "Confirm Save",
                        JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    DepartmentDAO departmentDAO = new DepartmentDAO(employees);
                    RequestDAO requestDAO = new RequestDAO(employees);
                    try {
                        employeeDAO.saveEmployeesToFile(employees);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        departmentDAO.saveDepartmentToFile(departments);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        requestDAO.saveRequestsToFile(new File("requests.csv"), requests);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Department selected = null;

                for(Department d : departments) {
                    if(d.getName().equals(depCB.getSelectedItem().toString())) {
                        selected = d;
                    }
                }
                GeneratorDialog generatorDialog = new GeneratorDialog(selected);
            }
        });

        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageRequests manage = new ManageRequests();
                try {
                    manage.init(employees);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        makeReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeRequest makeRequest = new MakeRequest();
                try {
                    makeRequest.initRequest(employees, user);
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        //menu.add(print);
        menu.add(logout);
        menu.addSeparator();
        menu.add(saveAll);
        menu.add(makeActive);

        menuBar.add(menu);
        //menuBar.add(addEmployee);
        menuBar.add(addSchedule);
        menuBar.add(request);
        menuBar.add(makeReq);

        return menuBar;
    }
    void userBar(){
        // CHANGE TO REFLECT REAL USERS
        ArrayList<String> initEmployees = new ArrayList<>();
        for(Employee e : departments.get(0).getEmployees()) {
            initEmployees.add(e.getName());
        }
        userList = new JComboBox<>(initEmployees.toArray(new String[0]));
        ArrayList<String> deptNames = new ArrayList<>();
        for(Department d : departments) {
            deptNames.add(d.getName());
        }
        depCB = new JComboBox<>(deptNames.toArray(new String[0]));
        //conf.setSize(30,40);
        depCB.setSize(50, 40);
        userList.setSize(50,40);

        //conf.setVisible(true);
        //conf.setEnabled(false);
        depCB.setVisible(true);
        layout.putConstraint(SpringLayout.EAST, userList, -5, SpringLayout.EAST, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, depCB, -5, SpringLayout.WEST, userList);
        //layout.putConstraint(SpringLayout.EAST, conf, -10, SpringLayout.WEST, depCB);

        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScheduleTable();
            }
        });

        depCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) depCB.getSelectedItem();
                Department department = new Department();
                for(Department d: departments){
                    if(d.getName().equalsIgnoreCase(str)){
                        department = d;
                        break;
                    }
                }
                String[] strings = new String[department.getEmployees().size()];
                int count = 0;
                for(Employee employee:department.getEmployees()){
                    strings[count] = employee.getName();
                    count++;
                }

                ComboBoxModel<String> temp = new DefaultComboBoxModel<>(strings);
                userList.setModel(temp);

                updateScheduleTable();
            }
        });

        /*conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScheduleTable();
            }
        });*/

        frame.add(userList);
        frame.add(depCB);
        //frame.add(conf);
    }
    void goodbye(){
        JFrame goodbye = new JFrame();
        JLabel bye = new JLabel("Thank you for using XTREME Scheduling Services.");

        goodbye.add(bye);
        goodbye.setVisible(true);
        goodbye.setSize(450,300);
        new Thread(){
            @Override
            public void run() {
                try(InputStream in = TemplateFrame.class.getResourceAsStream("/ByeByey.mp3")){
                    new Player(in).play();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }.start();
        goodbye.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
    }
    void initCalendar(){
        theTable.setSize(500,100);
        theTable.setVisible(true);
        left.setSize(15,15);
        left.setVisible(true);
        right.setSize(15,15);
        right.setVisible(true);
        week.setSize(320,30);
        week.setHorizontalAlignment(JTextField.CENTER);
        week.setVisible(true);
        JScrollPane scrolly = new JScrollPane(theTable);

        layout.putConstraint(SpringLayout.NORTH, left, 40, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, left, 15, SpringLayout.WEST, frame.getContentPane());

        layout.putConstraint(SpringLayout.NORTH, right, 40, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, right, -15, SpringLayout.EAST, frame.getContentPane());

        layout.putConstraint(SpringLayout.NORTH,week, 40, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, week, 10, SpringLayout.EAST, left);
        layout.putConstraint(SpringLayout.EAST, week, -10, SpringLayout.WEST, right);

        layout.putConstraint(SpringLayout.NORTH, scrolly, 10, SpringLayout.SOUTH, week);
        layout.putConstraint(SpringLayout.WEST, scrolly, 15, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, scrolly, -15, SpringLayout.EAST, frame.getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, scrolly, -10, SpringLayout.SOUTH, frame.getContentPane());

        frame.add(left);
        frame.add(week);
        frame.add(right);
        frame.add(scrolly);


        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd");
                Calendar d = new GregorianCalendar();
                String[] dates = new String[2];
                String text = week.getText();
                String newLine = "Week of: ";
                text = text.replace("Week of: ", "");
                text = text.replace(" - ", ",");
                dates = text.split(",");
                try {
                    d.setTime(format.parse(dates[0]));
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                d.add(Calendar.DATE, -7);
                newLine = newLine.concat(format.format(d.getTime()));
                newLine = newLine.concat(" - ");
                try{
                    d.setTime(format.parse(dates[1]));
                }catch (ParseException ex){
                    throw new RuntimeException(ex);
                }
                d.add(Calendar.DATE, -7);
                newLine = newLine.concat(format.format(d.getTime()));

                week.setText(newLine);
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd");
                Calendar d = new GregorianCalendar();
                String[] dates = new String[2];
                String text = week.getText();
                String newLine = "Week of: ";
                text = text.replace("Week of: ", "");
                text = text.replace(" - ", ",");
                dates = text.split(",");
                try {
                    d.setTime(format.parse(dates[0]));
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                d.add(Calendar.DATE, 7);
                newLine = newLine.concat(format.format(d.getTime()));
                newLine = newLine.concat(" - ");
                try{
                    d.setTime(format.parse(dates[1]));
                }catch (ParseException ex){
                    throw new RuntimeException(ex);
                }
                d.add(Calendar.DATE, 7);
                newLine = newLine.concat(format.format(d.getTime()));

                week.setText(newLine);
            }
        });
        week.setEditable(false);
    }

    void updateScheduleTable() {
        String activeDept = depCB.getSelectedItem().toString();
        String activeEmployee = userList.getSelectedItem().toString();

        Department targetDepartment = null;
        Employee targetEmployee = null;
        for(Department d : departments) {
            if(d.getName().equals(activeDept)) {
                targetDepartment = d;
            }
        }
        for(Employee e : targetDepartment.getEmployees()) {
            if(e.getName().equals(activeEmployee)) {
                targetEmployee = e;
            }
        }

        if(targetEmployee != null) {
            ArrayList<String> dayShift = new ArrayList<>();
            Schedule activeSchedule = targetDepartment.getActiveSchedule();
            ArrayList<Day> allDays = new ArrayList<>();
            Collections.addAll(allDays, Day.values());
            allDays.remove(Day.SELECT);
            int counter = 1;
            for (Day day : allDays) {
                dayShift.add("");
            }
            if(activeSchedule != null) {
                EmployeeSchedule targetSchedule = activeSchedule.getSchedule(targetEmployee);
                if (targetSchedule != null) {
                    Shift employeeShift = targetSchedule.getShift();
                    List<Day> scheduleDays = targetSchedule.getDays();
                    for (Day day : allDays) {
                        if (scheduleDays.contains(day)) {
                            dayShift.set(counter, employeeShift.toString());
                        }
                        counter += 1;
                        counter = counter % 7;
                    }

                    counter = 0;
                    for (String str : dayShift) {
                        tableModel.setValueAt(str, 0, counter);
                        counter++;
                    }
                } else {
                    counter = 0;
                    for (String str : dayShift) {
                        tableModel.setValueAt(str, 0, counter);
                        counter++;
                    }
                }
            } else {
                counter = 0;
                for (String str : dayShift) {
                    tableModel.setValueAt(str, 0, counter);
                    counter++;
                }
            }
        }
    }
}

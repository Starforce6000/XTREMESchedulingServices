package app;

import Requests.MakeRequest;
import Requests.ManageRequests;
import Requests.Request;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InitFrame extends JFrame{
    JFrame frame;
    JTable theTable;
    SpringLayout layout;
    Boolean logged, admin;
    JButton left = new JButton("<");
    JButton right = new JButton(">");
    JTextField week = new JTextField("Week of: 12/04 - 12/10");

    ArrayList<Request> requests = new ArrayList<>();
    public InitFrame(JFrame frame,
                     JTable theTable,
                     SpringLayout layout,
                     Boolean logged,
                     Boolean admin,
                     ArrayList<Request> requests){
        this.frame = frame;
        this.theTable = theTable;
        this.layout = layout;
        this.logged = logged;
        this.admin = admin;
        this.requests.addAll(requests);
    }
    void initFrame(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(layout);
        theTable.setRowHeight(400);


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
        JMenuItem print = new JMenuItem("Print");
        JButton addSchedule = new JButton("Add Schedule");
        JButton request = new JButton("Pending Requests");
        JButton makeReq = new JButton("Make Request");
        JMenuItem saveAll = new JMenuItem("Save All");

        if(!admin){
            request.setEnabled(false);
            saveAll.setEnabled(false);
        }


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
                    System.exit(0);
                }

            }
        });


        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFrame addSched = new JFrame("Generate Schedule");
//                addSched.setLayout(new GridLayout(10,2));
//                addSched.setSize(500,450);
//                JLabel l1 = new JLabel("User");
//                JLabel l2 = new JLabel("ID");
//                JLabel l3 = new JLabel("Sunday");
//                JLabel l4 = new JLabel("Monday");
//                JLabel l5 = new JLabel("Tuesday");
//                JLabel l6 = new JLabel("Wednesday");
//                JLabel l7 = new JLabel("Thursday");
//                JLabel l8 = new JLabel("Friday");
//                JLabel l9 = new JLabel("Saturday");
//                JTextField f1 = new JTextField();
//                JTextField f2 = new JTextField();
//                JTextField f3 = new JTextField();
//                JTextField f4 = new JTextField();
//                JTextField f5 = new JTextField();
//                JTextField f6 = new JTextField();
//                JTextField f7 = new JTextField();
//                JTextField f8 = new JTextField();
//                JTextField f9 = new JTextField();
//                addSched.add(l1);
//                addSched.add(f1);
//                addSched.add(l2);
//                addSched.add(f2);
//                addSched.add(l3);
//                addSched.add(f3);
//                addSched.add(l4);
//                addSched.add(f4);
//                addSched.add(l5);
//                addSched.add(f5);
//                addSched.add(l6);
//                addSched.add(f6);
//                addSched.add(l7);
//                addSched.add(f7);
//                addSched.add(l8);
//                addSched.add(f8);
//                addSched.add(l9);
//                addSched.add(f9);
//                addSched.setVisible(true);
//                JButton canc = new JButton("Cancel");
//                JButton conf = new JButton("Confirm");

//                canc.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        addSched.setVisible(false);
//                    }
//                });
//
//                conf.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        addSched.setVisible(false);
//                    }
//                });
//
//                addSched.add(canc);
//                addSched.add(conf);
            }
        });

        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageRequests manage = new ManageRequests(requests);
                manage.init(requests);
            }
        });

        makeReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeRequest makeRequest = new MakeRequest();
                makeRequest.initRequest();
            }
        });


        menu.add(print);
        menu.add(logout);
        menu.addSeparator();
        menu.add(saveAll);

        menuBar.add(menu);
        menuBar.add(addSchedule);
        menuBar.add(request);
        menuBar.add(makeReq);

        return menuBar;
    }
    void userBar(){
        // CHANGE TO REFLECT REAL USERS
        String[] list = {"Employee","Suzzie", "Mike", "John", "Rebecca"};
        String[] def = {"Employee"};
        String[] newList = {"Employee","Cerny", "Fry", "Booth", "Donahoo"};
        String[] departments = {"Department", "Cashiers", "Back-of-House", "Dream Killers"};
        JComboBox<String> userList = new JComboBox<>(def);
        JComboBox<String> depList = new JComboBox<>(departments);
        JButton conf = new JButton("Find Employee");
        conf.setSize(30,40);
        depList.setSize(50, 40);
        userList.setSize(50,40);

        conf.setVisible(true);
        conf.setEnabled(false);
        depList.setVisible(true);
        layout.putConstraint(SpringLayout.EAST, userList, -5, SpringLayout.EAST, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, depList, -5, SpringLayout.WEST, userList);
        layout.putConstraint(SpringLayout.EAST, conf, -10, SpringLayout.WEST, depList);

        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userList.getSelectedItem().toString().equals("Models.Employee")){
                    conf.setEnabled(false);
                }
                else {
                    conf.setEnabled(true);
                }
                // NEED TO FIX
            }
        });

        depList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // NEED TO FIX
                switch(depList.getSelectedItem().toString()){
                    case "Dream Killers":
                        ComboBoxModel<String> temp = new DefaultComboBoxModel<>(newList);
                        userList.setModel(temp);
                        break;
                    case "Models.Department":
                        ComboBoxModel<String> tmp = new DefaultComboBoxModel<>(def);
                        userList.setModel(tmp);
                        conf.setEnabled(false);
                        break;
                    default:
                        ComboBoxModel<String> temp1 = new DefaultComboBoxModel<>(list);
                        userList.setModel(temp1);
                }
            }
        });

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // NEED TO ADD FUNCTION
            }
        });

        frame.add(userList);
        frame.add(depList);
        frame.add(conf);
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
}

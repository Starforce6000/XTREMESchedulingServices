import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TemplateFrame extends JFrame {
    String[] adList = {"John", "Rebecca", "Gabe"};
    ArrayList<String> adminList = new ArrayList<>(List.of(adList));

    JFrame frame = new JFrame("XTreme Schedule Processing");
    Boolean logged = true, admin = false;
    SpringLayout layout = new SpringLayout();
    void run(){
        login();
    }

    void initFrame(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(layout);


        // Set the Main Menu
        if(logged) {
            frame.setJMenuBar(initHead());
            userBar();

            //Set the Main Content
            //frame.add(initContent());
        }


    }

    JMenuBar initHead(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem print = new JMenuItem("Print");
        JButton addSchedule = new JButton("Add Schedule");
        JButton request = new JButton("Pending Requests");
        JButton makeReq = new JButton("Make PTO Request");

        if(!admin){
            request.setEnabled(false);
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


        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addSched = new JFrame("Adding Schedule");
                addSched.setLayout(new GridLayout(10,2));
                addSched.setSize(500,450);
                JLabel l1 = new JLabel("User");
                JLabel l2 = new JLabel("ID");
                JLabel l3 = new JLabel("Sunday");
                JLabel l4 = new JLabel("Monday");
                JLabel l5 = new JLabel("Tuesday");
                JLabel l6 = new JLabel("Wednesday");
                JLabel l7 = new JLabel("Thursday");
                JLabel l8 = new JLabel("Friday");
                JLabel l9 = new JLabel("Saturday");


                JButton can = new JButton("Cancel");
                JButton add = new JButton("Add");

                JTextField f1 = new JTextField();
                JTextField f2 = new JTextField();
                JTextField f3 = new JTextField();
                JTextField f4 = new JTextField();
                JTextField f5 = new JTextField();
                JTextField f6 = new JTextField();
                JTextField f7 = new JTextField();
                JTextField f8 = new JTextField();
                JTextField f9 = new JTextField();

                addSched.add(l1);
                addSched.add(f1);

                addSched.add(l2);
                addSched.add(f2);

                addSched.add(l3);
                addSched.add(f3);

                addSched.add(l4);
                addSched.add(f4);

                addSched.add(l5);
                addSched.add(f5);

                addSched.add(l6);
                addSched.add(f6);

                addSched.add(l7);
                addSched.add(f7);

                addSched.add(l8);
                addSched.add(f8);

                addSched.add(l9);
                addSched.add(f9);

                addSched.setVisible(true);

                JButton canc = new JButton("Cancel");
                JButton conf = new JButton("Confirm");

                canc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addSched.setVisible(false);
                    }
                });

                conf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addSched.setVisible(false);
                    }
                });

                addSched.add(canc);
                addSched.add(conf);
            }
        });

        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // NEED TO ADD
            }
        });

        makeReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // NEED TO ADD
            }
        });


        menu.add(print);
        menu.add(logout);

        menuBar.add(menu);
        menuBar.add(addSchedule);
        menuBar.add(request);
        menuBar.add(makeReq);

        return menuBar;
    }

    /*
    void initContent(){

    }
     */

    void login(){
        JFrame loginForm = new JFrame("XTREME Scheduling Services Login");
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setVisible(true);
        loginForm.setLayout(new GridLayout(3,3));

        JLabel userL = new JLabel("Username: ");
        JLabel passL = new JLabel("Password: ");

        JTextField user = new JTextField("Username");
        JPasswordField pass = new JPasswordField("Password");
        JButton canButton = new JButton("Cancel");
        JButton logButton = new JButton("Login");

        loginForm.add(userL);
        loginForm.add(user);

        loginForm.add(passL);
        loginForm.add(pass);

        loginForm.add(canButton);
        loginForm.add(logButton);

        canButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logged = false;
                loginForm.setVisible(false);
            }
        });

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                Add info to check login information
                 */
                logged = true;
                if(adminList.contains(user.getText())){
                    admin = true;
                }
                loginForm.setVisible(false);
                initFrame();
            }
        });

        loginForm.setSize(300,300);
        loginForm.setAlwaysOnTop(true);

    }

    JFrame userBar(){
        // CHANGE TO REFLECT REAL USERS
        String[] list = {"Employee","Suzzie", "Mike", "John", "Rebecca"};
        JComboBox<String> userList = new JComboBox<>(list);
        JButton conf = new JButton("Find Employee");
        conf.setSize(30,40);
        userList.setSize(50,40);

        conf.setVisible(true);
        conf.setEnabled(false);
        layout.putConstraint(SpringLayout.EAST, userList, -5, SpringLayout.EAST, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, conf, -10, SpringLayout.WEST, userList);

        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userList.getSelectedItem().toString().equals("Employee")){
                    conf.setEnabled(false);
                }
                else {
                    conf.setEnabled(true);
                }
                // NEED TO FIX
            }
        });

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // NEED TO ADD FUNCTION
            }
        });

        frame.add(userList);
        frame.add(conf);

        return frame;
    }

    void goodbye(){
        JFrame goodbye = new JFrame();
        JLabel bye = new JLabel("Thank you for using XTREME Scheduling Services.");

        goodbye.add(bye);
        goodbye.setVisible(true);
        goodbye.setSize(450,300);
        goodbye.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
    }


    public static void main(String args[]){
        TemplateFrame tmp = new TemplateFrame();
        tmp.run();
    }
}

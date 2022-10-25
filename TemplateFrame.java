import javax.swing.*;
import java.awt.*;

public class TemplateFrame extends JFrame {
    JFrame frame = new JFrame("XTreme Schedule Processing");
    void run(){
        initFrame();
    }

    void initFrame(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);

        frame.getContentPane().add(login());

        // Set the Main Menu
        frame.setJMenuBar(initHead());

        //Set the Main Content
        //frame.add(initContent());



        frame.pack();
    }

    JMenuBar initHead(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logout = new JMenuItem("Logout");
        logout.setEnabled(false);
        JMenuItem print = new JMenuItem("Print");

        menu.add(print);
        menu.add(logout);

        menuBar.add(menu);
        return menuBar;
    }

    /*
    void initContent(){

    }
     */

    JFrame login(){
        JFrame loginForm = new JFrame();
        loginForm.setName("XTREME Scheduling Services Login");
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

        loginForm.setSize(300,300);
        loginForm.setAlwaysOnTop(true);


        return loginForm;
    }

    public static void main(String args[]){
        TemplateFrame tmp = new TemplateFrame();
        tmp.run();
    }
}

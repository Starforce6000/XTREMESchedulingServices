package app;

import Models.Employee;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;

public class LoginFrame {
    Boolean logged;
    Boolean admin;
    ArrayList<Employee> employees;
    ArrayList<String> adminList;
    InitFrame initFrame;
    JFrame loginForm;
    JLabel userL;
    JLabel passL;
    JTextField user;
    JPasswordField pass;
    JButton canButton;
    JButton logButton;
    public LoginFrame(Boolean logged, Boolean admin,
                      ArrayList<Employee> employees,
                      ArrayList<String> adminList,
                      InitFrame initFrame) {
        this.logged = logged;
        this.admin = admin;
        this.employees = employees;
        this.adminList = adminList;
        this.initFrame = initFrame;
        login();
        loginForm.setVisible(false);
    }
    void login(){
        loginForm = new JFrame("XTREME Scheduling Services Login");
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setVisible(true);
        loginForm.setLayout(new GridLayout(3,3));

        userL = new JLabel("Username: ");
        passL = new JLabel("Password: ");

        user = new JTextField("");
        pass = new JPasswordField("");
        canButton = new JButton("Cancel");
        logButton = new JButton("Login");

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
                initFrame.goodbye();
            }
        });

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Add info to check login information
                 */
                logged = false;
                for(Employee employee:employees){
                    if(employee.getEmail().equals(user.getText())){
                        if(employee.getPassword().equals(pass.getText())){
                            logged = true;
                        }
                    }
                }
                if(adminList.contains(user.getText())){
                    admin = true;
                }
                loginForm.setVisible(false);

                new Thread(){
                    @Override
                    public void run() {
                        try(InputStream in = TemplateFrame.class.getResourceAsStream("/Explosion.mp3")){
                            new Player(in).play();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                }.start();
            }
        });

        loginForm.setSize(300,300);
        loginForm.setAlwaysOnTop(true);

    }
}

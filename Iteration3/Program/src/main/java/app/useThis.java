package app;

import Models.*;

import javax.swing.*;
import java.util.ArrayList;

public class useThis {

    public static void main(String[] args){
        ArrayList<Employee> empList = new ArrayList<>();
        SpringLayout spLayout = new SpringLayout();
        JFrame employeeFrame = new JFrame("Employees Working");
        employeeFrame.setVisible(true);
        employeeFrame.setSize(400,400);
        employeeFrame.setLayout(spLayout);
        JTextField dayShift = new JTextField("Day Shift");
        JTextField swingShift = new JTextField("Swing Shift");
        JTextField nightShift = new JTextField("Night Shift");


        employeeFrame.add(dayShift);
        employeeFrame.add(swingShift);
        employeeFrame.add(nightShift);


    }
}

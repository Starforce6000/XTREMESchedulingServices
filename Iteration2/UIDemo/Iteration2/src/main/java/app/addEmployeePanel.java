package app;

import Models.Department;

import javax.swing.*;
import java.util.ArrayList;

public class addEmployeePanel extends JPanel {
//    int id;
//    String name;
//    Department department;
//    String email;
//    Availability availability;
//
//    Boolean isManager = false;
//
//    String password;
    JLabel id, name, department, email, day, shift, password;
    JTextField id_field, name_field, email_field, password_field;
    JComboBox department_box, day_box, shift_box;
    ArrayList<String> departments = new ArrayList<>();
    public addEmployeePanel(ArrayList<String> departments){
        id = new JLabel("ID: ");
        name = new JLabel("Name: ");
        department = new JLabel("Department: ");
        email = new JLabel("Email: ");
        day = new JLabel("Day: ");
        shift = new JLabel("Shift: ");
        password = new JLabel("Password: ");
        id_field = new JTextField(10);
        name_field= new JTextField(10);
        email_field= new JTextField(10);
        password_field= new JTextField(10);

    }

}

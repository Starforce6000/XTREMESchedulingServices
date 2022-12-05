package app;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import DAO.RequestDAO;
import Models.Department;
import Models.Employee;
import Requests.Request;


public class MainFrame extends JFrame {
    // Gets all Employee data
    EmployeeDAO employeeDAO = new EmployeeDAO();
    ArrayList<Employee> employees = new ArrayList<>();
    DepartmentDAO departmentDAO;
    ArrayList<Department> departments;

    RequestDAO requestDAO = new RequestDAO(employees);
    ArrayList<Request> requests = requestDAO.loadRequestsFromFile(new File("requests.csv"));

    String[] adList = {"tcerny@example.com", "ghamerly@example.com", "cfry@example.com", "dbooth@example.com"};
    ArrayList<String> adminList = new ArrayList<>(List.of(adList));

    MyTableModel model = new MyTableModel(this);
    JTable theTable = new JTable(model);

    JFrame frame = new JFrame("XTREME Schedule.Schedule Processing");
    Boolean logged = true, admin = false;
    SpringLayout layout = new SpringLayout();

    public MainFrame() throws IOException {
        employees.addAll(employeeDAO.loadEmployeesFromFile(new File("employee.csv")));
        departmentDAO = new DepartmentDAO(employees);
        requestDAO = new RequestDAO(employees);

        departments = new ArrayList<>();
        departments.addAll(departmentDAO.loadDepartmentFromFile(new File("department.csv")));

        requests.addAll(requestDAO.loadRequestsFromFile(new File("requests.csv")));
    }

    void run(){
        InitFrame initFrame = new InitFrame(frame, theTable, layout, logged, admin, requests);
        LoginFrame loginFrame = new LoginFrame(logged, admin, employees, adminList, initFrame);
        loginFrame.login();
    }

    public static void main(String[] args) throws IOException {
        MainFrame tmp = new MainFrame();
        tmp.run();

    }
}
package DAO;

import Enums.Day;
import Enums.Shift;
import Models.Department;
import Models.Employee;
import app.Availability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    public ArrayList<Department> loadDepartmentFromFile(File file) throws IOException {
        ArrayList<Department> departments = new ArrayList<Department> ();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            // Skip header line
            line = reader.readLine();
            while((line = reader.readLine()) != null){
                Department department = new Department();
                String[] line_data = line.split(",");
                department.setId(Integer.parseInt(line_data[0]));
                department.setName(line_data[1]);
                department.setManagerId(Integer.parseInt(line_data[2]));
                // Gets all Employee data
                EmployeeDAO temp = new EmployeeDAO();
                ArrayList<Employee> employees = temp.loadEmployeesFromFile(new File("employee.csv"));
                // If the employee id is in the department keep them in the list
                ArrayList<String> employee_ids = new ArrayList(List.of(line_data[4].split(" ")));
                employees.removeIf(e -> !employee_ids.contains(String.valueOf(e.getId())));
                department.setEmployeeList(employees);
                departments.add(department);
            }

        }catch (Exception e){
            throw e;
        }
        return departments;
    }


}

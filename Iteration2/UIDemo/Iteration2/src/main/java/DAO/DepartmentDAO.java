package DAO;

import Models.Department;
import Models.Employee;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    // Gets all Employee data
    private ArrayList<Employee> employees;
    public DepartmentDAO(ArrayList<Employee> employees){
        this.employees = employees;
    }
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
                // If the employee id is in the department keep them in the list
                ArrayList<String> employee_ids = new ArrayList(List.of(line_data[3].split(" ")));
                employees.removeIf(e -> !employee_ids.contains(String.valueOf(e.getId())));
                department.setEmployeeList(employees);
//                for(Employee e : department.getEmployees()){
//                    e.setDepartment(department);
//                }
                departments.add(department);
            }

        }catch (Exception e){
            throw e;
        }
        return departments;
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println("Hello World");
//        DepartmentDAO dao = new DepartmentDAO();
//        ArrayList<Department> temp = dao.loadDepartmentFromFile(new File("department.csv"));
//        for(Department t : temp){
//            t.printData();
//        }
//    }
}

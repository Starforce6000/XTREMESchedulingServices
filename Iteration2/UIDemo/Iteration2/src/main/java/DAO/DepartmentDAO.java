package DAO;

import Models.Department;
import Models.Employee;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    // Gets all Employee data
    private ArrayList<Employee> employees = new ArrayList<>();
    public DepartmentDAO(ArrayList<Employee> employees){
        this.employees.addAll(employees);
    }
    public ArrayList<Department> loadDepartmentFromFile(File file) throws IOException {
        ArrayList<Department> departments = new ArrayList<Department> ();
        ArrayList<Employee> temp = new ArrayList<>();
        //temp.addAll(employees);
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
                temp.addAll(employees);
                temp.removeIf(e -> !employee_ids.contains(String.valueOf(e.getId())));
                department.setEmployeeList(temp);
//                for(Employee e : department.getEmployees()){
//                    e.setDepartment(department);
//                }
                departments.add(department);
            }

        }catch (Exception e){
            throw e;
        }
        reader.close();
        return departments;
    }
    public void saveDepartmentToFile(ArrayList<Department> departments) throws IOException {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File("/outdept.csv")));
            if(departments.size() == 0){
                return;
            }
            writer.write("department_id, name, manger_id, employee_ids\n");
            for(Department department : departments){
                writer.write(department.printData());
                writer.write("\n");
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }

}

import Enums.Day;
import Enums.Shift;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
package DAO;

public class EmployeeDAO {
    public ArrayList<Employee> loadEmployeesFromFile(File file) throws IOException {
        ArrayList<Employee> employeeList = new ArrayList<Employee> ();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            // Skip header line
            line = reader.readLine();
            while((line = reader.readLine()) != null){
                Employee e = new Employee();
                String[] data = line.split(",");
                e.setId(Integer.parseInt(data[0]));
                e.setName(data[1]);
                e.setEmail(data[2]);
                Shift shift = Shift.Day;
                if (data[3].equalsIgnoreCase("night")){
                    shift = Shift.Night;
                } else if (data[3].equalsIgnoreCase("swing")){
                    shift = Shift.Swing;
                }
                List<Availability> availabilityList = e.getAvailability();
                String[] shiftData = data[4].split(" ");
                for(String str : shiftData){
                    Day day = Day.FRIDAY;
                    if(str.equalsIgnoreCase("M")){
                        day = Day.MONDAY;
                    }else if(str.equalsIgnoreCase("T")){
                        day = Day.TUESDAY;
                    }else if(str.equalsIgnoreCase("W")){
                        day = Day.WEDNESDAY;
                    }else if(str.equalsIgnoreCase("TR")){
                        day = Day.THURSDAY;
                    }
                    availabilityList.add(new Availability(day, shift));
                }
                e.setAvailabilityList(availabilityList);
                employeeList.add(e);
            }

            }catch (Exception e){
            throw e;
        }
        return employeeList;
    }
//    public static void main(String[] args) throws IOException {
//        System.out.println("Hello World");
//        EmployeeDAO dao = new EmployeeDAO();
//        ArrayList<Employee> temp = dao.loadEmployeesFromFile(new File("/Users/bradbuckingham/Desktop/Baylor-Semester-Courses/FA-22/SWE-1/Git-Repo/Group-Project/XTREMESchedulingServices/Iteration2/UIDemo/Iteration2/employee.csv"));
//        for(Employee t : temp){
//            System.out.println(t.getId());
//            System.out.println(t.getName());
//            System.out.println(t.getEmail());
//        }
//    }
}


package DAO;

import Enums.Day;
import Enums.Shift;
import Models.Employee;
import app.Availability;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
                } else if (data[3].equalsIgnoreCase("swing")) {
                    shift = Shift.Swing;
                }
                String[] shiftData = data[4].split(" ");
                List<Day> days = new LinkedList<>();
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
                    days.add(day);
                }
                Availability availability = new Availability(days, shift);
                e.setAvailability(availability);
                if(data[5].equalsIgnoreCase("Y")){
                    e.setManager(true);
                }else{
                    e.setManager(false);
                }
                e.setPassword(data[6]);
                employeeList.add(e);
            }

            }catch (Exception e){
            throw e;
        }
        reader.close();
        return employeeList;
    }
    public void saveEmployeesToFile(ArrayList<Employee> employees) throws IOException {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(new File("src/main/resources/outemp.csv")));
            if(employees.size() == 0){
                return;
            }
            writer.write("id,username,email,shift type,Working Day,Manager,Password\n");
            for(Employee employee : employees){
                writer.write(employee.printData());
                writer.write("\n");
            }


        }catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println("Hello World");
//        EmployeeDAO dao = new EmployeeDAO();
//        ArrayList<Models.Employee> temp = dao.loadEmployeesFromFile(new File("employee.csv"));
//        for(Models.Employee t : temp){
//            System.out.println(t.getId());
//            System.out.println(t.getName());
//            System.out.println(t.getEmail());
//            t.getAvailability().printAvailability();
//        }
//    }
}


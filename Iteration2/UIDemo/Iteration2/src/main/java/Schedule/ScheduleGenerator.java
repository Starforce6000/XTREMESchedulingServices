package Schedule;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import Models.Department;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import Models.*;
import Enums.*;

public class ScheduleGenerator {

    Department department;
    Schedule schedule;
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();

    public ScheduleGenerator() {
        schedule = new Schedule();
    }

    public Schedule generateSchedule(Department d, int perShift, List<Day> days, List<Shift> shifts, List<Employee> exclude) {
        List<Employee> employeeList = d.getEmployees();
        List<List<List<Employee>>> scheduleList = new ArrayList<List<List<Employee>>>();

        Map<Day, Integer> dayLocation = new HashMap<>();
        Map<Shift, Integer> shiftLocation = new HashMap<>();

        int dayCounter = 0;
        int shiftCounter = 0;

        for(Shift shift : shifts) {
            shiftLocation.put(shift, shiftCounter);
            System.out.println(shift +  "," + shiftCounter);
            shiftCounter++;
        }

        for(Day day : days) {
            shiftCounter = 0;
            dayLocation.put(day, dayCounter);
            scheduleList.add(new ArrayList<List<Employee>>());
            dayCounter++;
            for(Shift shift : shifts) {
                scheduleList.get(dayLocation.get(day)).add(new ArrayList<Employee>());
                shiftCounter++;
            }
        }

        for(Employee employee : employeeList) {
            System.out.println(employee.getName());
            Shift s = employee.getAvailability().getShift();
            int sl = shiftLocation.get(s);
            for(Day day : employee.getAvailability().getDays()) {
                int dl = dayLocation.get(day);
                scheduleList.get(dl).get(sl).add(employee);
            }
        }

        return schedule;
    }

    public static void main(String[] args) throws IOException {
        ScheduleGenerator sg = new ScheduleGenerator();

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Employee> employees = employeeDAO.loadEmployeesFromFile(new File("employee.csv"));

        DepartmentDAO departmentDAO = new DepartmentDAO(employees);
        ArrayList<Department> departments = departmentDAO.loadDepartmentFromFile(new File("department.csv"));

        List<Day> days = Arrays.asList(Day.values());
        ArrayList<Shift> shifts = new ArrayList<>(Arrays.asList(Shift.values()));
        shifts.remove(0);
        List<Employee> exclude = new LinkedList<>();
        sg.generateSchedule(departments.get(0), 0, days, shifts, exclude);
    }
}


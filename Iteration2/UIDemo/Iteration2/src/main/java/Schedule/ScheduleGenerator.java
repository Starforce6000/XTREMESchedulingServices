package Schedule;
import Models.Department;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        List<Employee> employeeList = department.getEmployees();
        Map<Shift, Integer> employeesShift = new HashMap<Shift, Integer>();

        employeesShift.replaceAll((k,v)->v=0);

        for(Employee employee : employeeList) {
            if(perShift > 0) {
                int inShift = employeesShift.get(employee.getAvailability().getShift());
            }
        }

        /*for(Employee employee : department.getEmployees()) {
            EmployeeSchedule eSchedule = new EmployeeSchedule(employee, schedule);
        }*/

        return schedule;
    }
}

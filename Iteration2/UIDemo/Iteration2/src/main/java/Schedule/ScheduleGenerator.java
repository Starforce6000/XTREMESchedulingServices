package Schedule;
import Models.Department;

import java.util.LinkedList;
import java.util.List;

public class ScheduleGenerator {
    Department department;
    Schedule schedule;
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();

    public ScheduleGenerator(Department d) {
        Schedule newSchedule = new Schedule();

        for(Employee employee : department.getEmployees()) {
            EmployeeSchedule eSchedule = new EmployeeSchedule(employee, newSchedule);
        }
    }
}

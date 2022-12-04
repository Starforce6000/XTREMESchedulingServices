package Schedule;

import Models.*;
import Enums.*;

import java.util.List;

public class EmployeeSchedule {

    Employee employee;
    Schedule schedule;
    List<Day> workdays;
    Shift shift;

    public EmployeeSchedule() {}

    public EmployeeSchedule(Employee e, Schedule s) {
        employee = e;
        schedule = s;
    }

    public Employee getEmployee() {
        return employee;
    }
    public Schedule getSchedule() {
        return schedule;
    }
}

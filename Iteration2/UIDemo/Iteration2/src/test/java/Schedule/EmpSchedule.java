package Schedule;

import DAO.EmployeeDAO;
import Enums.Day;
import Enums.Shift;
import Models.Department;
import Models.Employee;
import app.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;



public class EmpSchedule{
    String name;
    Employee e = new Employee();
    Department d = new Department();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Day> days = new ArrayList<>();
    Availability a;
    ArrayList<Shift> s = new ArrayList<>();
    Schedule schedule = new Schedule();
    ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
    EmployeeSchedule employeeSchedule;

    @BeforeEach
    void init(){
        name = "temp";
        days.add(Day.MONDAY);
        a = new Availability(days, Shift.Night);
        d.setId(1);
        d.setName("Meats");
        d.setManagerId(5);
        e.setDepartment(d);
        e.setPassword("1234");
        e.setEmail("ex@email.com");
        e.setId(5);
        e.setManager(true);
        e.setName("Bob");
        e.setAvailability(a);
        d.setEmployeeList(employees);
        s.add(Shift.Day);
        schedule = scheduleGenerator.generateSchedule(name, d, 1, days, s, employees);
        employeeSchedule = new EmployeeSchedule(e, schedule);
    }

    @Test
    void somethingBroke() {
        EmployeeSchedule test = new EmployeeSchedule(new Employee(), new Schedule());
        Assertions.assertNotEquals(test.schedule, employeeSchedule.schedule);
        Assertions.assertNotEquals(null, employeeSchedule.schedule);
    }

    @Test
    void somethingProper() {
        EmployeeSchedule test = new EmployeeSchedule(e, schedule);
        Assertions.assertEquals(test.schedule, employeeSchedule.schedule);
        Assertions.assertEquals(test.employee, employeeSchedule.employee);
        Assertions.assertEquals(test.shift, employeeSchedule.shift);
        Assertions.assertEquals(test.workdays, employeeSchedule.workdays);
    }

}

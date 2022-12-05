package Schedule;


import Enums.Day;
import Enums.Shift;
import Models.Department;
import Models.Employee;
import app.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Generator {
    String name;
    Employee e = new Employee();
    Department d = new Department();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Day> days = new ArrayList<>();
    Availability a;
    ArrayList<Shift> s = new ArrayList<>();

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
    }

    @Test
    void somethingBroke(){
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        Assertions.assertNotEquals(null, scheduleGenerator);
    }

    @Test
    void somethingProper() {
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        Schedule schedule = scheduleGenerator.generateSchedule(name, d, 1, days, s, employees);
        Assertions.assertNotEquals(null, schedule);
        Assertions.assertEquals(schedule, scheduleGenerator.generateSchedule(name, d, 1, days, s, employees));
    }

}


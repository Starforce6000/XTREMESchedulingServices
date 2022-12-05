package Schedule;

import DAO.EmployeeDAO;
import Enums.Day;
import Enums.Shift;
import Models.Department;
import Models.Employee;
import app.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EmpSchedule{
    Employee e = new Employee();
    Department d = new Department();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Day> days = new ArrayList<>();
    Availability a;

    @BeforeEach
    void init(){
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
    }

    @Test
    void somethingBroke() {}

    @Test
    void somethingProper() {}

}

package Models;

import Enums.Day;
import Enums.Shift;
import app.Availability;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class getSetProper {

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
    void testEmployee(){
        assertEquals(a, e.getAvailability());
        assertEquals("Bob", e.getName());
        assertEquals(5,e.getId());
        assertEquals("ex@email.com", e.getEmail());
        assertEquals(d, e.getDepartment());
        assertEquals(true, e.getManager());
        assertEquals("1234", e.getPassword());
    }

    @Test
    void testDepartment(){
        assertEquals("Meats", d.getName());
        assertEquals(employees, d.getEmployees());
        assertEquals(1,d.getId());
        assertEquals(5,d.getManagerId());
    }
}

package IOTesting;

import DAO.*;
import Enums.*;
import Models.*;
import app.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileOutput {
    EmployeeDAO dao;
    ArrayList<Employee> yaMan = new ArrayList<>();
    ArrayList<Day> days = new ArrayList<>();

    @BeforeEach
    void init(){
        dao = new EmployeeDAO();
        days.add(Day.MONDAY);
        Employee e = new Employee();
        Availability a = new Availability(days,Shift.Day);
        e.setEmail("j@j.com");
        e.setName("e");
        e.setAvailability(a);
        e.setManager(false);
        e.setId(0);
        e.setPassword("1234");
        e.setDepartment(new Department());
    }

    @Test
    void somethingBroke(){
        try {
            dao.saveEmployeesToFile(null);
        }catch (NullPointerException e){
            assertEquals(true, true);
        }catch (IOException ex){

        }
    }

    @Test
    void somethingProper(){
        try {
            dao.saveEmployeesToFile(yaMan);
        }catch (IOException e){
            assertEquals(false, true);
        }
    }
}

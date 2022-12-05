package IOTesting;

import DAO.*;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.Assert.*;

public class fileInput {
    EmployeeDAO dao;

    @BeforeEach
    void init(){
        dao = new EmployeeDAO();
    }

    @Test
    void somethingBroke(){
        try {
            dao.loadEmployeesFromFile(new File("noWorky.csv"));
        }catch (IOException e){
            assertEquals(true, true);
        }
    }

    @Test
    void somethingProper(){
        try {
            dao.loadEmployeesFromFile(new File("employee.csv"));
        }catch (IOException e){
            assertEquals(false, true);
        }
    }

}

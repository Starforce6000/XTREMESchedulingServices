import java.util.LinkedList;
import java.util.List;

public class Department {
    String name;
    List<Employee> employeeList = new LinkedList<Employee>();

    public List<Employee> getEmployees() {
        return employeeList;
    }

    public String getName() {
        return name;
    }
}

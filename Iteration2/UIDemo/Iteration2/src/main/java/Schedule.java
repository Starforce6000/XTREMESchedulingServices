import java.util.LinkedList;
import java.util.List;

public class Schedule {
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();

    public EmployeeSchedule getSchedule(Employee employee) {
        int id = employee.getId();

        EmployeeSchedule employeeSchedule = new EmployeeSchedule();

        for(EmployeeSchedule eSchedule : employeeSchedules) {
            Employee e = eSchedule.getEmployee();

            
        }

        return employeeSchedule;
    }
}

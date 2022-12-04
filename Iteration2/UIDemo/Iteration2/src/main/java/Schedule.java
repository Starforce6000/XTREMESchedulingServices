import java.util.LinkedList;
import java.util.List;

public class Schedule {
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();

    public EmployeeSchedule getSchedule(Employee employee) {
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();

        for(EmployeeSchedule eSchedule : employeeSchedules) {
            Employee e = eSchedule.getEmployee();
            if(e.getId() == employee.getId()) {
                employeeSchedule = eSchedule;
            }
        }

        return employeeSchedule;
    }

    public void addEmployeeSchedule(EmployeeSchedule eSchedule) {
        employeeSchedules.add(eSchedule);
    }
}

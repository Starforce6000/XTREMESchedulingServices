package Schedule;

import Models.*;
import java.util.LinkedList;
import java.util.List;

public class Schedule {

    String name;
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();
    Boolean active = false;

    public EmployeeSchedule getSchedule(Employee employee) {
        EmployeeSchedule employeeSchedule = null;

        for(EmployeeSchedule eSchedule : employeeSchedules) {
            Employee e = eSchedule.getEmployee();
            if(e.getId() == employee.getId()) {
                employeeSchedule = eSchedule;
            }
        }

        return employeeSchedule;
    }

    public List<EmployeeSchedule> getFullSchedule() {
        return employeeSchedules;
    }

    public void addEmployeeSchedule(EmployeeSchedule eSchedule) {
        employeeSchedules.add(eSchedule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeActivity() {
        active = !active;
    }

    public boolean getActive() {
        return active;
    }
}
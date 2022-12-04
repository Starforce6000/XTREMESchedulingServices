<<<<<<< HEAD:Iteration2/UIDemo/Iteration2/src/main/java/app/ScheduleGenerator.java
package app;
=======
import java.util.LinkedList;
import java.util.List;
>>>>>>> 9a0c738a7cc338bb7fe48ec071dd4d57598aaf4b:Iteration2/UIDemo/Iteration2/src/main/java/ScheduleGenerator.java

public class ScheduleGenerator {
    Department department;
    Schedule schedule;
    List<EmployeeSchedule> employeeSchedules = new LinkedList<EmployeeSchedule>();

    public ScheduleGenerator(Department d) {
        Schedule newSchedule = new Schedule();

        for(Employee employee : department.getEmployees()) {
            EmployeeSchedule eSchedule = new EmployeeSchedule(employee, newSchedule);
        }
    }
}

public class EmployeeSchedule {
    Employee employee;
    Schedule schedule;

    public EmployeeSchedule() {}

    public EmployeeSchedule(Employee e, Schedule s) {
        employee = e;
        schedule = s;
    }

    public Employee getEmployee() {
        return employee;
    }
    public Schedule getSchedule() {
        return schedule;
    }
}

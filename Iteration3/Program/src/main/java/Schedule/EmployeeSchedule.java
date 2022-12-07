package Schedule;

import Models.*;
import Enums.*;

import java.util.LinkedList;
import java.util.List;

public class EmployeeSchedule {

    Employee employee;
    Schedule schedule;
    List<Day> workdays;
    Shift shift;

    public EmployeeSchedule(Employee e, Schedule s) {
        employee = e;
        schedule = s;
        workdays = new LinkedList<>();
    }

    public Employee getEmployee() {
        return employee;
    }
    public Schedule getSchedule() {
        return schedule;
    }

    public void addDay(Day day) {
        if(!workdays.contains(day)) {
            workdays.add(day);
        }
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public void printData() {
        System.out.println(employee.getName());
        System.out.println(schedule.getName());
        for(Day day : workdays) {
            System.out.print(day + ",");
        }
        System.out.println("\n" + shift);
    }

    public List<Day> getDays() {
        return workdays;
    }

    public Shift getShift() {
        return shift;
    }
}

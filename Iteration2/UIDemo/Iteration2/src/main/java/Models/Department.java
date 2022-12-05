package Models;

import Models.*;
import Schedule.*;

import java.util.LinkedList;
import java.util.List;

public class Department {
    int id;
    int managerId;

    String name;
    List<Employee> employeeList = new LinkedList<Employee>();
    List<Schedule> scheduleList = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList.addAll(employeeList);
    }

    public String printData(){
        String temp = "";
        //temp += id + "," + name + "," + getManagerId();
        temp += id + "," + name + "," + managerId + ",";
        for(Employee e: getEmployees()){
            temp += e.getId() + " ";
        }
        return temp;
    }

    public void addSchedule(Schedule s) {
        scheduleList.add(s);
    }
}

package Models;

import Models.Employee;

import java.util.LinkedList;
import java.util.List;

public class Department {
    int id;
    int managerId;

    String name;
    List<Employee> employeeList = new LinkedList<Employee>();

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
        temp += getId() + "," + getName() + "," + getManagerId() + ",";
        for(Employee e: getEmployees()){
            temp += e.getId() + "," + e.name;
        }
        return temp;
    }
}

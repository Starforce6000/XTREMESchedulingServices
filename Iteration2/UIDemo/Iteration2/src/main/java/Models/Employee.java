package Models;

import Enums.Day;
import app.Availability;

import java.util.LinkedList;
import java.util.List;

public class Employee {

    int id;
    String name;
    Department department;
    String email;
    Availability availability;

    Boolean isManager = false;

    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    public Availability getAvailability() {
        return availability;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }


    public String printData() {
        String temp = id + "," + name + "," + email + ",";
        temp+= availability.getShift().shift + ",";// + availability.getDays().toString();
        for(Day d : availability.getDays()) {
            temp += d.toString() + " ";
        }
        temp+= ",";
        if(isManager) {
            temp += "Y,";
        } else {
            temp += "N,";
        }
        temp+=password;
        return temp;
    }
}
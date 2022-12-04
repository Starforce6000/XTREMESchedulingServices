package Models;

import app.Availability;

import java.util.LinkedList;
import java.util.List;

public class Employee {

    int id;
    String name;
    Department department;
    String email;
    Availability availability;

    Boolean isManager;

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
}
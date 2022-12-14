package Requests;

import Enums.Day;
import Enums.RequestStatus;
import Enums.RequestType;
import Models.Employee;

public class Request {

    RequestStatus status;
    RequestType type;
    String reason;
    RequestDay day = new RequestDay();

    Employee emp;
    int id;

    public Request() {
        status = RequestStatus.PENDING;
        type = RequestType.SELECT;
        reason = "";
        day.setDay(Day.SELECT);
        emp = new Employee();
    }

    public void approve() {
        status = RequestStatus.APPROVED;
    }

    public void deny() {
        status = RequestStatus.DENIED;
    }

    public String viewStatus() {
        return status.toString();
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RequestDay getDay() {
        return day;
    }

    public void setDay(RequestDay day) {
        this.day = day;
    }

    public void setEmp(Employee emp) { this.emp = emp; }

    public Employee getEmp() { return emp; }

    public void setId(int id) { this.id = id; }

    public int getId() { return this.id; }

    public String printRequest() {
        String temp = "";

        temp += emp.getName() + ",";
        temp += status.toString().toUpperCase() + ",";
        temp += type.toString() + ",";
        temp += day.getDay().toString() + ",";
        temp += day.getShift().toString() + ",";
        temp += reason + "," + id;

        return temp;
    }
}

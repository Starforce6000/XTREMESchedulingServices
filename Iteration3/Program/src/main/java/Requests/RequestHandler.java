package Requests;

import Enums.Day;
import Enums.RequestType;
import Enums.Shift;
import Models.Employee;

public class RequestHandler {
    Request request;

    public RequestHandler() {
        request = new Request();
    }

    public void setRequestType(RequestType type) {
        request.setType(type);
    }

    public void setWorkdays(Day day) {
        request.getDay().setDay(day);
    }

    public void setHours(Shift shift) {
        request.getDay().setShift(shift);
    }

    public void setReason(String reason) {
        request.setReason(reason);
    }

    public void setEmployee(Employee employee) { request.setEmp(employee);}

    public Employee getEmployee() { return request.getEmp();}
}

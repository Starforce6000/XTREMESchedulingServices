package Requests;

import Enums.Day;
import Enums.RequestStatus;
import Enums.RequestType;

public class Request {

    RequestStatus status;
    RequestType type;
    String reason;
    RequestDay day = new RequestDay();

    //Schedule.EmployeeSchedule sched;

    public Request() {
        status = RequestStatus.PENDING;
        type = RequestType.SELECT;
        reason = "";
        day.setDay(Day.SELECT);
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
}

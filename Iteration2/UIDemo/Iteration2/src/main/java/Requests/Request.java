package Requests;

import Enums.RequestStatus;
import Enums.RequestType;

public class Request {

    RequestStatus status;
    RequestType type;
    String reason;
    RequestDay day;

    //Schedule.EmployeeSchedule sched;

    public Request() {
        status = RequestStatus.PENDING;
        type = RequestType.PTO;
        reason = "";
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

    //TODO: implement setDays() and setHours()
}

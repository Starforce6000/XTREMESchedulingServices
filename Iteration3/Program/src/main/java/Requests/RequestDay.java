package Requests;

import Enums.Day;
import Enums.Shift;

public class RequestDay {

    Day day;
    Shift shift;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

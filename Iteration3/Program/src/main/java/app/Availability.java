package app;

import Enums.Day;
import Enums.Shift;

import java.util.List;

public class Availability {

    List<Day> days;
    Shift shift;

    public Availability(List<Day> days, Shift shift){
        this.days = days;
        this.shift = shift;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public List<Day> getDays() {
        return days;
    }
    
    public Shift getShift() {
        return shift;
    }

    public void addDay(Day day) {
        days.add(day);
    }

    public void printAvailability() {
        for(Day day : days) {
            System.out.println(day);
        }
        System.out.println(shift);
    }
}

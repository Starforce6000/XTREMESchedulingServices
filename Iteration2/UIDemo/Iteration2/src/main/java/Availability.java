import Enums.Day;
import Enums.Shift;

public class Availability {
    Day day;
    Shift shift;
    Availability(Day day, Shift shift){
        this.day = day;
        this.shift = shift;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Day getDay() {
        return day;
    }
    
    public Shift getShift() {
        return shift;
    }
}

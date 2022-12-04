package Enums;

public enum Day {

    MONDAY("M"), TUESDAY("T"), WEDNESDAY("W"),
    THURSDAY("TR"), FRIDAY("F"), SATURDAY("Sat"), SUNDAY("Sun");

    public final String day;

    private Day(String day) {
        this.day = day;
    }
}

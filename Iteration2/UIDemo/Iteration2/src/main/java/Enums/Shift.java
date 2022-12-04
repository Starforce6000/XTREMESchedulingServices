package Enums;

public enum Shift {

    SELECT("---"), Day("Day"), Night("Night"), Swing("Swing");

    public final String shift;

    private Shift(String shift) {
        this.shift = shift;
    }
}

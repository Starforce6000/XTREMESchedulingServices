package Enums;

public enum RequestType {

    RESCHEDULE("re"), SWAP("swp"), OVERTIME("ot"), PTO("pto");

    public final String type;

    private RequestType(String type) {
        this.type = type;
    }
}

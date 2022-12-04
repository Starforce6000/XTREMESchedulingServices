package Enums;

public enum RequestStatus {
    APPROVED("Approved"), DENIED("Denied"), PENDING("Pending");

    public final String state;

    private RequestStatus(String state) {
        this.state = state;
    }

    public String toString() {
        return this.state;
    }
}

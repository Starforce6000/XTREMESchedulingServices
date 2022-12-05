package Requests;

import Enums.Day;
import Enums.RequestType;
import Enums.Shift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RequestHandlerTest {
    RequestHandler rh;

    @BeforeEach
    void init() {
        rh = new RequestHandler();
    }

    @Test
    void testSetType() {
        rh.setRequestType(RequestType.OVERTIME);
        assertEquals(rh.request.getStatus(), RequestType.OVERTIME);
    }

    @Test
    void testSetWorkdays() {
        rh.setWorkdays(Day.TUESDAY);
        assertEquals(rh.request.getDay().getDay(), Day.TUESDAY);
    }

    @Test
    void testSetHours() {
        rh.setHours(Shift.Swing);
        assertEquals(rh.request.getDay().getShift(), Shift.Swing);
    }

    @Test
    void setReason() {
        rh.setReason("Family emergency");
        assertEquals(rh.request.getReason(), "Family emergency");
    }
}

package group3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.dal.FlightDAL;

public class TestBooking {
    @Test
    public void flightIdIsExist() {
        boolean result = FlightDAL.chooseFlight(1);
        assertTrue(result);
    }

    @Test
    public void flightIsNotExit() {
        boolean result = FlightDAL.chooseFlight(100);
        assertFalse(result);
    }

    @Test
    public void checkRemainingESeat() {
        final int result = FlightDAL.checkERemain(3);
        final int expected = 0;
        assertFalse(result == expected );
    }

    @Test
    public void checkRemainingPSeat() {
        final int result = FlightDAL.checkERemain(3);
        final int expected = 0;
        assertFalse(result == expected );
    }

    @Test
    public void checkRemainingBSeat() {
        final int result = FlightDAL.checkERemain(3);
        final int expected = 0;
        assertFalse(result == expected );
    }

    @Test
    public void checkESeatSoldOut() {
        final int result = FlightDAL.checkERemain(1);
        final int expected = 0;
        assertTrue(result == expected );
    }

    @Test
    public void checkPSeatSoldOut() {
        final int result = FlightDAL.checkERemain(1);
        final int expected = 0;
        assertTrue(result == expected );
    }

    @Test
    public void checkBSeatSoldOut() {
        final int result = FlightDAL.checkERemain(1);
        final int expected = 0;
        assertTrue(result == expected );
    }
}

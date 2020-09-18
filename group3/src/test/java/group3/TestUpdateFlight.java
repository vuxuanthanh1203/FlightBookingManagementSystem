package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.persistance.Flight;

public class TestUpdateFlight {
    @Test
    public void flightDateIsEmpty() {
        final String result = Flight.flightDate("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void flightDateIsNotEmpty() {
        final String result = Flight.flightDate("abcd");
        final String expected = "abcd";
        assertEquals(expected, result);
    }

    @Test
    public void departureTimeIsEmpty() {
        final String result = Flight.takeOff("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void departureTimeIsNotEmpty() {
        final String result = Flight.takeOff("abcd");
        final String expected = "abcd";
        assertEquals(expected, result);
    }

    @Test
    public void arrivalTimeIsEmpty() {
        final String result = Flight.landing("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void arrivalTimeIsNotEmpty() {
        final String result = Flight.landing("abcd");
        final String expected = "abcd";
        assertEquals(expected, result);
    }

    // Test validate

    @Test
    public void dateValidate() {
        boolean result = Flight.isDateValid("2020/09/10");
        assertTrue(result);
    }

    @Test
    public void dateInvalidate() {
        boolean result = Flight.isDateValid("123456");
        assertFalse(result);
    }

    @Test
    public void TimeValidate() {
        boolean result = Flight.isTimeValid("01:00:00");
        assertTrue(result);
    }

    @Test
    public void TimeInvalidate() {
        boolean result = Flight.isTimeValid("123456");
        assertFalse(result);
    }
}

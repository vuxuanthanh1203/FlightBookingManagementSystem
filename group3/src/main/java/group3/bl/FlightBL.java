package group3.bl;

import group3.dal.FlightDAL;

public class FlightBL {
    private FlightDAL fdal = new FlightDAL();

    public void displayFlight(int route, String flightDate) {
        fdal.displayFlight(route, flightDate);
    }
}

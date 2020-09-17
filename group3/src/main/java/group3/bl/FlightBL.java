package group3.bl;

import group3.dal.FlightDAL;

public class FlightBL {
    private FlightDAL fDal = new FlightDAL();

    public void displayFlight(int route, String flightDate) {
        fDal.displayFlight(route, flightDate);
    }
}

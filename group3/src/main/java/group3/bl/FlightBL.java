package group3.bl;

import group3.dal.FlightDAL;

public class FlightBL {
    private FlightDAL fDal = new FlightDAL();

    public void displayFlight() {
        fDal.displayFlight();
    }

    public void flightDetails(int flightID) {
        fDal.flightDetails(flightID);
    }

    public void addFlight(String flight_num, int route, int fleet, int fare, String flightDate, String flightTime,
            String departureTime, String arrivalTime) {
        fDal.addFlight(flight_num, route, fleet, fare, flightDate, flightTime, departureTime, arrivalTime);
    }

    public void modifyFlight(int flightID, String flightDate, String departureTime, String arrivalTime) {
        fDal.modifyFlight(flightID, flightDate, departureTime, arrivalTime);
    }
}

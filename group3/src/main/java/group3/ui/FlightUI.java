package group3.ui;

import group3.bl.FlightBL;
import group3.persistance.ClearScreen;

public class FlightUI {
    private FlightBL fbl = new FlightBL();

    public void displayFlight(int route, String flightDate) {
        // ClearScreen.clear();
        System.out.println(
                "================================================================================================================================================");
        System.out.printf("|| %2s | %12s %1s| %10s %-2s| %10s %-2s | %16s %-1s | %13s %-6s| %13s %-6s| %15s %-6s||\n", "ID",
                "Flight Date", " ", "Departs", " ", "Arrives", " ", "Flight Detail", " ", "Economy",
                " ", "Premium", " ", "Business", " ");
        System.out.println(
                "================================================================================================================================================");
        fbl.displayFlight(route, flightDate);
    }
}

package group3;

import group3.dal.FlightDAL;
import group3.ui.AdminUI;
import group3.ui.MenuUI;

public class App {

    public static void main(String[] args) {
        // MenuUI.menu();
        // AdminUI.manageFlight();
        FlightDAL.flightDetails(1);
    }
}

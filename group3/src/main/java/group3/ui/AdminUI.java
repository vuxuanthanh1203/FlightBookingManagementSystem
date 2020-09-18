package group3.ui;

import java.util.Scanner;

import group3.dal.FlightDAL;
import group3.persistance.ClearScreen;

public class AdminUI {
    static String flight_num = "";
    static int flightID = 0;
    static int route = 0;
    static int fleet = 0;
    static int fare = 0;
    static String flightDate = "";
    static String flightTime = "";
    static String departureTime = "";
    static String arrivalTime = "";

    static FlightUI flightUI = new FlightUI();

    public static void manageFlight() {
        int choice = 0;
        while (true) {
            ClearScreen.clear();
            Header.header();
            System.out.println("\n=====================================================================");
            System.out.println("|                             MANAGE FLIGHT                         |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. Add New Flight                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Modify Flight                                                  |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Back                                                           |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    flightUI.addFlight(flight_num, route, fleet, fare, flightDate, flightTime, departureTime,
                            arrivalTime);
                    break;
                case 2:
                    flightUI.modifyFlight(FlightDAL.flight_id, flightDate, departureTime, arrivalTime);
                    break;
                case 3:
                    ClearScreen.clear();
                    MenuUI.adminScreen();
                    break;
                default:
                    System.out.println("Function does not exist !");
                    break;
            }
        }
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}

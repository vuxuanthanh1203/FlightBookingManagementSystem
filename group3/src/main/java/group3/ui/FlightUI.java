package group3.ui;

import java.util.Scanner;

import group3.bl.FlightBL;
import group3.dal.BookingDAL;
import group3.persistance.ClearScreen;
import group3.persistance.Flight;

public class FlightUI {
        private static FlightBL fbl = new FlightBL();
        public static int route = 0;
        public static String date = "";
        public static int flight_id = 0;

        public static void displayFlight() {
                ClearScreen.clear();
                Header.header();
                System.out.println(
                                "\n================================================================================================================================================");
                System.out.println(
                                "|                                                               LIST OF FLIGHT                                                                 |");
                System.out.println(
                                "================================================================================================================================================\n");
                System.out.println(
                                "================================================================================================================================================");
                System.out.printf(
                                "|| %2s | %12s %1s| %10s %-2s| %10s %-2s | %16s %-1s | %13s %-6s| %13s %-6s| %15s %-6s||\n",
                                "ID", "Flight Date", " ", "Departs", " ", "Arrives", " ", "Flight Detail", " ",
                                "Economy", " ", "Premium", " ", "Business", " ");
                System.out.println(
                                "================================================================================================================================================");
                fbl.displayFlight();
        }

        public static void chooseSchedule() {
                ClearScreen.clear();
                Header.header();
                System.out.println("+-------------------------------------------------------------------+");
                System.out.println("|                           -- The Schedule --                      |");
                System.out.println("+-------------------------------------------------------------------+");
                System.out.println("| 1. Ha Noi --> TP.Ho Chi Minh                                      |");
                System.out.println("| 2. Ha Noi --> Da Nang                                             |");
                System.out.println("| 3. TP.Ho Chi Minh --> Ha Noi                                      |");
                System.out.println("| 4. TP.Ho Chi Minh --> Da Nang                                     |");
                System.out.println("| 5. Da Nang --> Ha Noi                                             |");
                System.out.println("| 6. Da Nang --> TP.Ho Chi Minh                                     |");
                System.out.println("+-------------------------------------------------------------------+");

                System.out.print("\n- Choose The Schedule: ");
                route = getScanner().nextInt();
                BookingDAL.selectRoute(route);
                System.out.print("\n- Departure Date (yyyy/MM/dd): ");
                date = Flight.getDate(date);
                displayFlight();

        }

        public static Scanner getScanner() {
                return new Scanner(System.in);
        }

        public void addFlight(String flight_num, int route, int fleet, int fare, String flightDate, String flightTime,
                        String departureTime, String arrivalTime) {
                ClearScreen.clear();
                Header.header();
                System.out.println("=====================================================================");
                System.out.println("|                             ADD NEW FLIGHT                        |");
                System.out.println("+-------------------------------------------------------------------+");
                flight_num = Flight.getFNum(flight_num);
                route = Flight.getRoute(route);
                fleet = Flight.getFleet(fleet);
                fare = Flight.getFare(fare);
                flightDate = Flight.getFlightDate(flightDate);
                flightTime = Flight.getFlightTime(flightTime);
                departureTime = Flight.getDeTime(departureTime);
                arrivalTime = Flight.getArrTime(arrivalTime);
                fbl.addFlight(flight_num, route, fleet, fare, flightDate, flightTime, departureTime, arrivalTime);
        }

        public void modifyFlight(int flightID, String flightDate, String departureTime, String arrivalTime) {
                selectFlight(flightID);
                System.out.print("\n- Confirm Modification (Y/N): ");
                String choice = getScanner().nextLine().toLowerCase();
                switch (choice) {
                        case "y":
                                System.out.println(
                                                "\n=====================================================================");
                                System.out.println(
                                                "|                            MODIFY FLIGHT                          |");
                                System.out.println(
                                                "+-------------------------------------------------------------------+");
                                flightDate = Flight.getFlightDate(flightDate);
                                departureTime = Flight.getDeTime(departureTime);
                                arrivalTime = Flight.getArrTime(arrivalTime);
                                fbl.modifyFlight(flightID, flightDate, departureTime, arrivalTime);
                                break;
                        case "n":
                                ClearScreen.clear();
                                Header.header();
                                System.out.println("\n-- Unsuccessful Modification !!! --");
                                System.out.println("\n-- Enter To Be Back ! --");
                                getScanner().nextLine();
                                break;
                        default:
                                break;
                }

        }

        public static void selectFlight(int flightID) {
                ClearScreen.clear();
                Header.header();
                searchFlight(flightID);
                System.out.println(
                                "\n================================================================================================================================================");
                System.out.println(
                                "|                                                             FLIGHT INFORMATION                                                               |");
                System.out.println(
                                "================================================================================================================================================\n");
                System.out.println(
                                "================================================================================================================================================");
                System.out.printf(
                                "|| %2s | %14s %1s| %19s %1s| %18s %-1s | %13s %-1s | %12s %-1s| %16s %-2s| %13s %-1s||\n",
                                "ID", "Flight Number", " ", "Departure Location", " ", "Arrival Location", " ",
                                "Flight Date", " ", "Flight Time", " ", "Departure Time", " ", "Arrival Time", " ");
                System.out.println(
                                "================================================================================================================================================");
                fbl.flightDetails(flight_id);
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        public static int searchFlight(int flightID) {
                ClearScreen.clear();
                Header.header();
                System.out.println("=====================================================================");
                System.out.println("|                             SEARCH FLIGHT                         |");
                System.out.println("+-------------------------------------------------------------------+");
                System.out.print("\n- Input the Flight ID: ");
                flight_id = getScanner().nextInt();
                return flight_id;
        }
}

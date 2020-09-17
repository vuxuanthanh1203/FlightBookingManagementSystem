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
}

package group3.ui;

import java.util.Scanner;

public class AdminUI {

    public void manageFlight() {
        int choice = 0;
        while (true) {
            System.out.println("\n=====================================================================");
            System.out.println("|                             MANAGE FLIGHT                         |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. Add New Flight                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Update Flight                                                  |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Back                                                           |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    // fbl.addFlight(flight);
                    break;
                case 2:
                    // fbl.updateFlight(flight);
                    break;
                case 3:
                    System.out.println("Back");
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

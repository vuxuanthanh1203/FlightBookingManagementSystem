package group3.persistance;

import java.util.Scanner;
import java.util.regex.Pattern;

import group3.ui.MenuUI;
import group3.ui.UserUI;

public class Flight {

    private String departureTime;
    private String arrivalTime;
    private String flightTime;
    private String flightDate;
    private int route = 0;
    private int fleet = 0;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public static String getDate(String Date) {
        while (true) {
            Date = getScanner().nextLine();
            if (!isDateValid(Date)) {
                System.out.println("\n- Invalid date !!!");
                continue;
            }
            break;
        }
        return Date;
    }

    public static String getFlightDate(String flightDate) {
        while (true) {
            System.out.print("\n- Flight Date (yyy/MM/dd): ");
            flightDate = getScanner().nextLine();
            if (flightDate.isEmpty()) {
                UserUI.fieldBlank("|               -- Flight Date is not blank --        |");
                continue;
            } else if (!isDateValid(flightDate)) {
                System.out.println("\n- Invalid Date !!!");
                continue;
            }
            break;
        }
        return flightDate;
    }

    public static String getFlightTime(String flightTime) {
        while (true) {
            System.out.print("\n- Flight Time (hh:mm:ss): ");
            flightTime = getScanner().nextLine();
            if (flightTime.isEmpty()) {
                UserUI.fieldBlank("|               -- Flight Time is not blank --        |");
                continue;
            } else if (!isTimeValid(flightTime)) {
                System.out.println("\n- Invalid Time !!!");
                continue;
            }
            break;
        }
        return flightTime;
    }

    public static String getDeTime(String DeTime) {
        while (true) {
            System.out.print("\n- Departure Time (hh:mm:ss): ");
            DeTime = getScanner().nextLine();
            if (DeTime.isEmpty()) {
                UserUI.fieldBlank("|           -- Departure Time is not blank --         |");
                continue;
            } else if (!isTimeValid(DeTime)) {
                System.out.println("\n- Invalid Time !!!");
                continue;
            }
            break;
        }
        return DeTime;
    }

    public static String getArrTime(String ArrTime) {
        while (true) {
            System.out.print("\n- Arrival Time (hh:mm:ss): ");
            ArrTime = getScanner().nextLine();
            if (ArrTime.isEmpty()) {
                UserUI.fieldBlank("|            -- Arrival Time is not blank --          |");
                continue;
            } else if (!isTimeValid(ArrTime)) {
                System.out.println("\n- Invalid Time !!!");
                continue;
            }
            break;
        }
        return ArrTime;
    }

    public static String getFNum(String flightNum) {
        while (true) {
            System.out.println("\n  (Input 'back' to Back)");
            System.out.print("\n- Input Flight Number(VD: VN 111): ");
            flightNum = getScanner().nextLine().toUpperCase();
            if (flightNum.isEmpty()) {
                UserUI.fieldBlank("|              -- Flight number is not blank --       |");
                continue;
            } else if (flightNum.equalsIgnoreCase("back")) {
                ClearScreen.clear();
                MenuUI.adminScreen();
            } else if (!isFNumValid(flightNum)) {
                System.out.println("\n- Invalid Flight Number !");
                continue;
            }
            break;
        }
        return flightNum;
    }

    public static int getRoute(int route) {
        while (true) {
            System.out.println("\n===================================================================================");
            System.out.println("|                                       ROUTE ID                                  |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.println("| 1: HN - HCM | 2: HN - DN | 3: HCM - HN | 4: HCM - DN | 5: DN - HN | 6: DN - HCM |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.print("\n- Input Route ID: ");
            route = getScanner().nextInt();
            if (route == 0) {
                UserUI.fieldBlank("|                  -- Route is not blank --           |");
                continue;
            } else if (route > 6) {
                System.out.println("\n- Route ID Does Not Exist !!!");
                continue;
            }
            break;
        }
        return route;
    }

    public static int getFleet(int fleet) {
        while (true) {
            System.out.println("\n===================================================================================");
            System.out.println("|                                       FLEET ID                                  |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.println("| 1: Economy = 42 / Premium = 60 / Business = 72                                  |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.print("\n- Input Fleet ID: ");
            fleet = getScanner().nextInt();
            if (fleet == 0) {
                UserUI.fieldBlank("|                  -- Fleet is not blank --           |");
                continue;
            } else if (fleet != 1) {
                System.out.println("\n- Fleet ID Does Not Exist !!!");
                continue;
            }
            break;
        }
        return fleet;
    }

    public static int getFare(int fare) {
        while (true) {
            System.out.println("\n===================================================================================");
            System.out.println("|                                       FARE ID                                   |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.println("| 1: Economy = 1.500k / Premium = 3.500k / Business = 5.100k                      |");
            System.out.println("+---------------------------------------------------------------------------------+");
            System.out.print("\n- Input Fare ID: ");
            fare = getScanner().nextInt();
            if (fare == 0) {
                UserUI.fieldBlank("|                   -- Fare is not blank --           |");
                continue;
            } else if (fare != 1) {
                System.out.println("\n- Fare ID Does Not Exist !!!");
                continue;
            }
            break;
        }
        return fare;
    }

    public static boolean isFNumValid(String flightNum) {
        final Pattern FNUM_REGEX = Pattern.compile("VN\s[0-9]{3}", Pattern.CASE_INSENSITIVE);
        return FNUM_REGEX.matcher(flightNum).matches();
    }

    public static boolean isDateValid(String date) {
        Pattern DATE_PATTERN = Pattern.compile("^\\d{4}(-|/)\\d{2}(-|)/\\d{2}$");
        return DATE_PATTERN.matcher(date).matches();
    }

    public static boolean isTimeValid(String time) {
        Pattern TIME24HOURS_PATTERN = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
        return TIME24HOURS_PATTERN.matcher(time).matches();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public int getFleet() {
        return fleet;
    }

    public void setFleet(int fleet) {
        this.fleet = fleet;
    }
}

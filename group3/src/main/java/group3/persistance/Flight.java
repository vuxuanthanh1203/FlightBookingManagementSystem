package group3.persistance;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Flight {
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;
    private String flightTime;
    private String flightDate;

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

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
}

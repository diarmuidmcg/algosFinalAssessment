package com.company;

import com.company.HelperClasses.EdgeWeightedDigraph;
import com.company.HelperClasses.TST;
import com.company.HelperClasses.Trip;
import com.sun.jdi.event.ExceptionEvent;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchStopTimes {
    public static void runFindStopTimes(EdgeWeightedDigraph graphOfStops) {
        // create scanner
        Scanner input = new Scanner(System.in);
        Boolean engagingInApp = true;
        while (engagingInApp) {
            System.out.println("\nEnter the time you'd like to search for in hh:mm:ss format\n ");
            // get stop name
            String searchTime = input.nextLine();
            // check if they quit
            // check if input is quit
            if (searchTime.toLowerCase().contains("quit")) {
                // quit app
                engagingInApp = false;
                break;
            }
            else {
                // error check
                while (!isValidTime(searchTime)) {
                    System.out.println("\nThe time must be in hh:mm:ss format\n ");
                    // get stop name
                    searchTime = input.nextLine();
                }
                ArrayList<Trip> validTrips = new ArrayList<Trip>();
                for (int i = 0; i < graphOfStops.trips.size(); i++) {
                    String time = graphOfStops.trips.get(i).time;
                    if (time.charAt(0) == ' ') {
                        time = time.substring(1);
                    }
                    if (searchTime.charAt(0) == ' ') {
                        searchTime = searchTime.substring(1);
                    }
                    if (searchTime.equals(time)) {
                        validTrips.add(graphOfStops.trips.get(i));
                    }
                }
                for (int i = 0; i < validTrips.size(); i++) {
                    System.out.println("////////////////////////////////////////////////////////////\n");
                    System.out.println("Arrival Time:" + validTrips.get(i).time + "\n"
                            + "Stop Number:" + validTrips.get(i).stop.stopNumber + "\n"
                            + "Stop Name:" + validTrips.get(i).stop.stopName + "\n"
                            + "id: " + validTrips.get(i).id + "\n" );
                }
            }
        }
    }

    private static boolean isValidTime(String time) {
        String regex = "(([0-9]:[0-5][0-9]:[0-5][0-9])|([2][0-3]:[0-5][0-9]:[0-5][0-9])|([0-1][0-9]:[0-5][0-9]:[0-5][0-9]))";
        Pattern p = Pattern.compile(regex);
        if (time == null) {
            return false;
        }
        Matcher m = p.matcher(time);
        return m.matches();
    }
}

package com.company;

import com.company.HelperClasses.EdgeWeightedDigraph;
import com.company.HelperClasses.TST;

import java.util.Scanner;

public class SearchStops {
    public static void runFindStop(TST busStops) {
        // create scanner
        Scanner input = new Scanner(System.in);
        Boolean engagingInApp = true;
        while (engagingInApp) {
            System.out.println("\nEnter the stop name or enter 'quit' to quit. ");
            // get stop name
            String getStopName = input.nextLine();
            // check if they quit
            // check if input is quit
            if (getStopName.toLowerCase().contains("quit")) {
                // quit app
                engagingInApp = false;
                break;
            }
            else {
                Iterable<String> potStops = busStops.keysWithPrefix(getStopName);
                // search for bus routes w desc
            }

            engagingInApp = false;

        }
    }
}

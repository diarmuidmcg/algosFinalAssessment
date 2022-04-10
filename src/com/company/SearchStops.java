package com.company;

import com.company.HelperClasses.EdgeWeightedDigraph;

import java.util.Scanner;

public class SearchStops {
    public static void RunFindStop() {
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

                // search for bus routes w desc
            }

            engagingInApp = false;

        }
    }
}

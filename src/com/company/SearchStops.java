package com.company;

import com.company.HelperClasses.EdgeWeightedDigraph;
import com.company.HelperClasses.Stop;
import com.company.HelperClasses.TST;

import java.util.Iterator;
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
                Iterable<String> potStops = busStops.keysWithPrefix(getStopName.toUpperCase());
                String returnStops = "Stop Id ---- Stop Name ---- Stop Desc\n";
                // for each loop bc its an iterable
                int lengthOfPotStops = 0;
                for (String stop : potStops ) {

                    if (stop == null || stop == "") {
                        System.out.println("There were no stops that meet that search term.");
                        break;
                    }
                    else {
                        lengthOfPotStops++;
                        Stop stopValue = busStops.getStopValue(stop);
                        returnStops += stopValue.stopNumber + " " + stopValue.stopName + " " + stopValue.stopDescription + "\n";
                    }
                }
                if (lengthOfPotStops == 0) {
                    System.out.println("There were no stops that meet that search term.");
                }
                else {
                    System.out.println(returnStops);
                }

            }
            System.out.println("Those are your results. press any key to continue.");
            input.nextLine();
            engagingInApp = false;

        }
    }
}

package com.company;

import com.company.HelperClasses.DijkstraSP;
import com.company.HelperClasses.EdgeWeightedDigraph;
import com.company.HelperClasses.Stop;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathBetweenTwoStops {

    public static void RunShortestPath(EdgeWeightedDigraph graphOfStops) {

        Boolean engagingInApp = true;
        while (engagingInApp) {
            // get first stop number
            int stopOneNumber = getUserInputForStopNumber(1);
            // check if they quit
            if (stopOneNumber == -1) {
                engagingInApp = false;
                break;
            }

            // find stop
            int stopOne = graphOfStops.findStop(stopOneNumber);
            while (stopOne == -1) {
                System.out.println("This is not a valid stop. Try again");
                // find stop again
                stopOneNumber = getUserInputForStopNumber(1);
                stopOne = graphOfStops.findStop(stopOneNumber);
            }

            // get second stop number
            int stopTwoNumber = getUserInputForStopNumber(2);
            // check if they quit
            if (stopTwoNumber == -1) {
                engagingInApp = false;
                break;
            }

            // find stop
            int stopTwo = graphOfStops.findStop(stopTwoNumber);
            if (stopTwo == -1) {
                System.out.println("This is not a valid stop. Try again");
                // find stop again
                stopTwoNumber = getUserInputForStopNumber(2);
                stopTwo = graphOfStops.findStop(stopTwoNumber);
            }

            // find the shortest path
            System.out.println("stop 1 is " + stopOneNumber + " and stop 2 is " + stopTwoNumber);
            findShortestPath(stopOneNumber, stopTwoNumber, graphOfStops);
            engagingInApp = false;

        }
    }

    // Finding shortest paths between 2 bus stops (as input by the user), returning
    // the list of stops en route as well as the associated “cost”.
    private static void findShortestPath(int stop1, int stop2, EdgeWeightedDigraph graphOfStops) {

        DijkstraSP shortestPath = new DijkstraSP(graphOfStops, stop1, stop2);
        // ensure shortestPath was successful & not the same stop
        if (shortestPath.fail == false && stop1 != stop2) {
            ArrayList<Stop> route = shortestPath.shortestRoute;
            System.out.println("To get from " + stop1 + " to stop " + stop2 + " is:");
            for (int i = 0; i < route.size(); i++) {
                Stop s = route.get(i);
                System.out.println(s.stopNumber + " -> " + s.stopName);
            }
        }
        // if they're the same stop,
        else if (stop1 == stop2) {
            System.out.println("Stop " + stop1 + " and stop " + stop2 + " are the same stop! ");
        } else {
            System.out.println("The shortest path calculation failed. Please check your inputs ");
        }
    }

    public static int getUserInputForStopNumber(int stopNumber) {
        // create scanner
        Scanner input = new Scanner(System.in);
        if (stopNumber == 1) {
            System.out.println("\nWhere is your starting point (stop number)?");
        }
        else System.out.println("\nWhere is your ending point (stop number)?");

        String firstStop = input.nextLine();

        // check if input is quit
        if (firstStop.toLowerCase().contains("q")) {
            // quit app
            return -1;
        }
        else {
            // try to make input into string
            Boolean convertToInt = true;
            int userInputInt = 0;
            while (convertToInt) {
                try {
                    userInputInt = Integer.parseInt(firstStop);
                    convertToInt = false;
                }
                catch (Exception e) {
                    System.out.println("Your input must be a number.");
                    firstStop = input.nextLine();
                }
            }
            return userInputInt;
        }
    }

}

package com.company;

import java.util.Scanner;

public class ShortestPathBetweenTwoStops {

    public static void RunShortestPath() {
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
            int stopOne = 0;
            while (stopOne == -1) {
                System.out.println("This is not a valid stop. Try again");
                // find stop again
            }

            // get second stop number
            int stopTwoNumber = getUserInputForStopNumber(2);
            // check if they quit
            if (stopTwoNumber == -1) {
                engagingInApp = false;
                break;
            }

            // find stop
            int stopTwo = 0;
            if (stopTwo == -1) {
                System.out.println("This is not a valid stop. Try again");
                // find stop again
            }

            // find the shortest path

            engagingInApp = false;

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

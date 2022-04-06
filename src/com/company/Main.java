package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Boolean engagingInApp = true;
        while (engagingInApp) {
            // welcome
            System.out.println("Hello & welcome to the Vancouver Bus Management App!\n" +
                    "I hope you find what you're looking for :)");
            // give options
            System.out.println("What would you like to do?\n\n" +
                    "1. Find a route between two points?\n" +
                    "2. Search for a bus stop?\n" +
                    "3. Search for an arrival time?\n\n" +
                    "Choose 1,2, or 3 for your choice. Or enter 'q' or 'quit' to stop the app.\n");
            // scan for input

            // create scanner
            Scanner input = new Scanner(System.in);
            String userJobChoice = input.nextLine();

            // check if input is quit
            if (userJobChoice.contains("q")) {
                // quit app
                engagingInApp = false;
            }
            else {
                // try to make input into string
                Boolean convertToInt = true;
                int userInputInt = 0;
                while (convertToInt) {
                    try {
                        userInputInt = Integer.parseInt(userJobChoice);
                        convertToInt = false;
                    }
                    catch (Exception e) {
                        System.out.println("Your input must be a number.");
                        userJobChoice = input.nextLine();
                    }
                }


                // ensure input is a number
                while ((userInputInt < 1 || userInputInt > 3)) {
                    // tell them to repeat
                    System.out.println("Your entry must be either the number 1, 2, or 3.");
                }

                if (userInputInt == 1) {
                    // run shortest path;
                    System.out.println("shortest path");
                } else if (userInputInt == 2) {
                    // run search bus stops
                    System.out.println("search buses");
                } else if (userInputInt == 3) {
                    // run search for arrival time
                    System.out.println("search arrival");
                } else {
                    System.out.println("You broke me... restart");
                }


            }

        }
    }
}

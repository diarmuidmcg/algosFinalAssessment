package com.company.HelperClasses;

public class Stop {
    public int stopNumber; // Stop id
    public String stopName;
    public String stopDescription;

    public Stop(int stopNumber, String stopName) {
        this.stopNumber = stopNumber;
        // this removes any prefixes & makes them a postfix
        if (stopName.charAt(0) == 'W' && stopName.charAt(1) == 'e') {
            String sub = stopName.substring(9);
            sub += " WB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'E' && stopName.charAt(1) == 'a') {
            String sub = stopName.substring(9);
            sub += " EB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'N' && stopName.charAt(1) == 'o') {
            String sub = stopName.substring(10);
            sub += " NB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'S' && stopName.charAt(1) == 'o') {
            String sub = stopName.substring(10);
            sub += " SB";
            this.stopName = sub;
        } else {
            this.stopName = stopName;
        }
    }

    public Stop(int stopNumber, String stopName, String stopDescription) {
        this.stopNumber = stopNumber;
        this.stopDescription = stopDescription;
        // this removes any prefixes & makes them a postfix
        if (stopName.charAt(0) == 'W') {
            String sub = stopName.substring(3);
            sub += " WB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'E') {
            String sub = stopName.substring(3);
            sub += " EB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'N') {
            String sub = stopName.substring(3);
            sub += " NB";
            this.stopName = sub;
        } else if (stopName.charAt(0) == 'S') {
            String sub = stopName.substring(3);
            sub += " SB";
            this.stopName = sub;
        } else {
            this.stopName = stopName;
        }
    }

}
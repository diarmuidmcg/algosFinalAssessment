package com.company.HelperClasses;

public class Stop {
    public int stopNumber; // Stop id
    public String stopName;
    public String stopDescription;

    public Stop(int stopNumber, String stopName) {
        this.stopNumber = stopNumber;

    }

    public Stop(int stopNumber, String stopName, String stopDescription) {
        this.stopNumber = stopNumber;
        this.stopDescription = stopDescription;

    }

    public String printStop() {
        String output = "";
        output += "Stop Number:\n\t" + stopNumber + "\nStop Name:\n\t" + stopName + "\nStop Description:\n\t"
                + stopDescription + "";
        return output;
    }

    public void printStopSingleLine() {
        String output = "";
        output += stopNumber + "\t" + stopDescription;
        String format = "%-40s%s%n";
        System.out.printf(format, stopName, output);
    }
}
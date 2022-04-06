package com.company.HelperClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html

public class EdgeWeightedDigraph {
    public int numberOfVerts;                        // number of vertices in this digraph
    public ArrayList<Stop> stops;                     // list of edges (stops) in this digraph
    public ArrayList<ArrayList<DirectedEdge>> adjacentEdges;    // adj[v] = adjacency list for vertex v
    public ArrayList<Trip> trips;                              // indegree[v] = indegree of vertex v

    public EdgeWeightedDigraph() {
        stops = new ArrayList<>();
        initStops();
        this.numberOfVerts = stops.size();
        adjacentEdges = new ArrayList<>(stops.size());
        for (int i = 0; i < stops.size(); i++) {
            adjacentEdges.add(new ArrayList<>());
        }
        initStopEdges();
        initTransferEdges();
        initTripTimes();
    }

    private void initStops() {
        try {
            String filename = "algosFinalAssessment/src/BusFiles/stops.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int stopNum = scanner.nextInt();
                    scanner.next();
                    String stopName = scanner.next();
                    stops.add(new Stop(stopNum, stopName));
                    scanner.nextLine();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            stops = null;
        }
    }

    private void initStopEdges() {
        try {
            int lastRoute, currRoute, lastStop, currStop;
            String filename = "algosFinalAssessment/src/BusFiles/stop_times.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            lastRoute = scanner.nextInt();
            scanner.next();
            scanner.next();
            lastStop = scanner.nextInt();
            scanner.nextLine();
            while (scanner.hasNext()) {
                currRoute = scanner.nextInt();
                scanner.next();
                scanner.next();
                if (lastRoute == currRoute) {
                    currStop = scanner.nextInt();
                    adjacentEdges.get(findStop(lastStop)).add(new DirectedEdge(lastStop, currStop, 1));
                    lastStop = currStop;
                    lastRoute = currRoute;
                } else {
                    lastRoute = currRoute;
                    lastStop = scanner.nextInt();
                }
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            adjacentEdges = null;
        }
    }

    private void initTripTimes() {
        try {
            trips = new ArrayList<>();
            String route, time;
            int stopNumber;
            Stop stop;
            String filename = "algosFinalAssessment/src/BusFiles/stop_times.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            while (scanner.hasNext()) {
                route = scanner.next();
                time = scanner.next();
                scanner.next();
                stopNumber = scanner.nextInt();
                stop = stops.get(findStop(stopNumber));
                trips.add(new Trip(time, stop, route));
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
    }

    private void initTransferEdges() {
        try {
            int initStop, destStop;
            double weight;
            String filename = "algosFinalAssessment/src/BusFiles/transfers.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",|\\n");
            scanner.nextLine();
            while (scanner.hasNext()) {
                initStop = scanner.nextInt();
                destStop = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    scanner.nextInt();
                    String min = scanner.next();
                    min = min.substring(0, 3);
                    double minimum = Double.parseDouble(min);
                    weight = minimum / 100;
                } else {
                    weight = 2;
                    scanner.nextLine();
                }
                adjacentEdges.get(findStop(initStop)).add(new DirectedEdge(initStop, destStop, weight));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            adjacentEdges = null;
        }
    }

    public int findStop(int stopNum) {
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i).stopNumber == stopNum) {
                return i;
            }
        }
        return -1;
    }
}
package com.company.HelperClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html

public class EdgeWeightedDigraph {
    private int numberOfVerts;                        // number of vertices in this digraph
    private ArrayList<Stop> stops;                     // list of edges (stops) in this digraph
    private ArrayList<ArrayList<DirectedEdge>> adjentEdges;    // adj[v] = adjacency list for vertex v
    private ArrayList<Trip> trips;                              // indegree[v] = indegree of vertex v

    public EdgeWeightedDigraph() {
        stops = new ArrayList<>();
        initStops();
        this.numberOfVerts = stops.size();
        adjentEdges = new ArrayList<>(stops.size());
        for (int i = 0; i < stops.size(); i++) {
            adjentEdges.add(new ArrayList<>());
        }
        initStopEdges();
        initTransferEdges();
        initTripTimes();
    }

    private void initStops() {

    }

    private void initStopEdges() {

    }

    private void initTripTimes() {

    }

    private void initTransferEdges() {

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
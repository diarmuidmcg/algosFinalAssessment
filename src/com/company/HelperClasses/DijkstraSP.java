package com.company.HelperClasses;
import java.util.ArrayList;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html

public class DijkstraSP {
    public double[] distTo;
    public boolean[] visited;
    public int[] edgedTo;
    public ArrayList<Stop> shortestRoute;
    public boolean fail = false;

    public DijkstraSP(EdgeWeightedDigraph graphOfStops, int startingPoint, int endPoint) {
        // error check
        if (graphOfStops.findStop(startingPoint) != -1 && graphOfStops.findStop(endPoint) != -1) {
            // distance to that stores all distances from current
            distTo = new double[graphOfStops.numberOfVerts];
            // graph of boolean vals of visited distances
            visited = new boolean[graphOfStops.numberOfVerts];
            // graph of edges
            edgedTo = new int[graphOfStops.numberOfVerts];
            edgedTo[graphOfStops.findStop(startingPoint)] = -1;
            // set distTo graph & visted graph
            for (int i = 0; i < distTo.length; i++) {
                distTo[i] = Double.POSITIVE_INFINITY;
                visited[i] = false;
            }
            // set starting point to 0
            distTo[graphOfStops.findStop(startingPoint)] = 0;
            // iterate thru each stop
            for (int i = 0; i < graphOfStops.numberOfVerts - 1; i++) {
                // get the min distance
                int vertex = minimumDistance(distTo, visited);
                System.out.println("vertex is " + vertex);
                // if the vertex is greater than 0
                if (vertex >= 0) {
                    // mark as visited
                    visited[vertex] = true;
                    // iterate thru each adj edge
                    for (int j=0; j < graphOfStops.adjacentEdges.get(vertex).size(); j++) {
                        DirectedEdge edge = graphOfStops.adjacentEdges.get(vertex).get(j);
                        // relax or calculate cost of reaching that vertex
                        relax(edge, graphOfStops);
                    }
                }
//                if (vertex < 0)
//                    continue;
//                visited[vertex] = true;
//                for (DirectedEdge edge : graphOfStops.adjacentEdges.get(vertex))
//                    relax(edge, graphOfStops);
            }
            shortestRoute = new ArrayList<Stop>();
            // find time to distance
            int lastStop = graphOfStops.findStop(endPoint);
            // recursively check each stop to see if destination
            shortestRoute = findShortestPath(edgedTo, lastStop, graphOfStops);
        } else {
            fail = true;
        }
    }

    // gets min distance from two points based on visited vertexes
    public int minimumDistance(double[] distTo, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && distTo[i] <= min) {
                min = distTo[i];
                index = i;
            }
        }
        return index;
    }

    // calculates the 'cost of reach' to reach a certain point based on the first point
    public void relax(DirectedEdge edge, EdgeWeightedDigraph graph) {
        int initVert = graph.findStop(edge.startVertex);
        int destVert = graph.findStop(edge.endVertex);
        System.out.println("initVert is " + initVert);
        System.out.println("destVert is " + destVert);
        System.out.println("edge weight is " + edge.weight);
        if (distTo[destVert] > (distTo[initVert] + edge.weight)) {
            distTo[destVert] = distTo[initVert] + edge.weight;
            edgedTo[destVert] = initVert;
        }
    }

    // checks if at destination, other wise recursively calls itself to find next stop
    public ArrayList<Stop> findShortestPath(int[] edgedTo, int lastStop, EdgeWeightedDigraph graph) {
        if (edgedTo[lastStop] == -1) {
            shortestRoute.add(graph.stops.get(lastStop));
            return shortestRoute;
        }
        findShortestPath(edgedTo, edgedTo[lastStop], graph);
        shortestRoute.add(graph.stops.get(lastStop));
        return shortestRoute;
    }
}
package com.company.HelperClasses;

import java.util.ArrayList;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html

public class EdgeWeightedDigraph {
    private int numberOfVerts;                        // number of vertices in this digraph
    private int numberOfEdges;                        // number of edges in this digraph
    private ArrayList<DirectedEdge>[] adjentEdges;    // adj[v] = adjacency list for vertex v
    private int[] trips                 ;             // indegree[v] = indegree of vertex v
}
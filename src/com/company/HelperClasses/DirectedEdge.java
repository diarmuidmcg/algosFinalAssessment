package com.company.HelperClasses;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html

public class DirectedEdge {
    private int startVertex;
    private int endVertex;
    private double weight;

    public DirectedEdge(int initVert, int destVertex, double weight) {
        this.startVertex = initVert;
        this.endVertex = destVertex;
        this.weight = weight;
    }
}
package com.company.HelperClasses;

// used Sedgewick & Wayne
// https://algs4.cs.princeton.edu/52trie/TST.java.html

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TST {
    private int size;
    private Node rootNode;

    private static class Node {
        private char currentChar;
        private Node leftTree, midTree, rightTree;
        private Stop stopValue;
    }

    public TST() {
    }

    public TST(String filename) {
        populateTree(filename);
    }

        private void populateTree(String filename) {
        int lineCount = 0;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            scanner.nextLine();
            lineCount++;
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int stopNum = Integer.parseInt(scanner.next());
                    scanner.next();
                    String stopName = scanner.next();
                    String stopDesc = scanner.next();
                    Stop currStop = new Stop(stopNum, stopName, stopDesc);
                    System.out.println("stop is " + stopName + " " + stopDesc + " " + stopNum);
                    put(currStop.stopName, currStop);
                    scanner.nextLine();
                    lineCount++;
                }
            }
            // System.out.println(lineCount);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            rootNode = null;
            size = 0;
        } catch (NumberFormatException e) {
            System.out.println("there was an error " + e + " on line " + lineCount);
        }

    }

    // ----------------------- HELPER METHODS ---------------------------------------

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int getSize() {
        return size;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return getStopValue(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Stop getStopValue(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0)
            throw new IllegalArgumentException("key must have length >= 1");
        Node x = getTree(rootNode, key, 0);
        if (x == null)
            return null;
        return x.stopValue;
    }

    // return subtrie corresponding to given key
    private Node getTree(Node x, String key, int d) {
        if (x == null)
            return null;
        if (key.length() == 0)
            throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if (c < x.currentChar)
            return getTree(x.leftTree, key, d);
        else if (c > x.currentChar)
            return getTree(x.rightTree, key, d);
        else if (d < key.length() - 1)
            return getTree(x.midTree, key, d + 1);
        else
            return x;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(String key, Stop val) {
        if (key == null) {
            System.out.println("current stop is " + val.stopName);
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (!contains(key))
            size++;
        else if (val == null)
            size--; // delete existing key
        rootNode = put(rootNode, key, val, 0);
    }

    private Node put(Node x, String key, Stop val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.currentChar = c;
        }
        if (c < x.currentChar)
            x.leftTree = put(x.leftTree, key, val, d);
        else if (c > x.currentChar)
            x.rightTree = put(x.rightTree, key, val, d);
        else if (d < key.length() - 1)
            x.midTree = put(x.midTree, key, val, d + 1);
        else
            x.stopValue = val;
        return x;
    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(rootNode, new StringBuilder(), queue);
        return queue;
    }
    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     *     as an iterable
     * @throws IllegalArgumentException if {@code prefix} is {@code null}
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new Queue<String>();
        Node x = getTree(rootNode, prefix, 0);
        if (x == null) return queue;
        if (x.stopValue != null) queue.enqueue(prefix);
        collect(x.midTree, new StringBuilder(prefix), queue);
        return queue;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.leftTree,  prefix, queue);
        if (x.stopValue != null) queue.enqueue(prefix.toString() + x.currentChar);
        collect(x.midTree,   prefix.append(x.currentChar), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.rightTree, prefix, queue);
    }


}

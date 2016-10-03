/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel
 */
public class Path {

    private BaseUnit start;
    private BaseUnit end;
    private ArrayList<BaseUnit> nodes; //All locations and junctions
    private ArrayList<BaseUnit> visited; //Contains start
    private ArrayList<BaseUnit> unvisited; //Contains every other node
    private BaseUnit current;

    public Path(BaseUnit start, BaseUnit end, ArrayList<BaseUnit> nodes) {
        this.start = start;
        this.end = end;
        this.nodes = nodes;
        visited = new ArrayList<>();
        unvisited = new ArrayList<>();
    }

    public void setStart(BaseUnit start) {
        this.start = start;
    }

    public void setEnd(BaseUnit end) {
        this.end = end;
    }

    public BaseUnit getEnd() {
        return end;
    }

    public BaseUnit getStart() {
        return start;
    }
    
    public ArrayList<BaseUnit> findShortestPath() {
        //Dijkstra's Algorithm
        //Using this algorithm as the nodes are static.
        //Vertices are Junctions and Locations
        //Edges are DirectPaths
        initializeSearch();
        try {
            //4. While there are still unvisited nodes, explore the neighbours of the lowest node(current).
            while (!unvisited.isEmpty()) {
                 exploreUnvisited();
                 getNewCurrentNode();
                //If the end node is found. May not be needed;
                if (current == end) {
                    visited.add(current);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Couldnt find shortest path:");
            System.out.println(e);
        }
        return visited;
    }

    private void getNewCurrentNode() {
        //6. Add the current node to visited,
        visited.add(current);
        unvisited.remove(current);
        System.out.println("closest neighbour is " + getLowestTentativeDistance(unvisited).getName());

        getLowestTentativeDistance(unvisited).setPredecessor(current);
        //Set the current explored node to be the lowest neighbour.
        current = getLowestTentativeDistance(unvisited);
    }

    /**
     * Explore all unvisited neighbors of the current node.
     * @return ArrayList<BaseUnit> which contains all unvisited neighbors.
     */
    private void exploreUnvisited() {
        System.out.println("Exploring " + current.getName() + " node.");
        //Explore every neighbour of current node, that has not been visited.
        for (BaseUnit neighbour : current.getNeighbours()) {
            if (unvisited.contains(neighbour)) {
                System.out.println("Neighbour: " + neighbour.getName());
                System.out.println("Distance to neighbour: " + current.getDirectPath(neighbour).getDistance());
                System.out.println("Tentative distance to neighbour: " + neighbour.getTentativeDistance());
                //5.If the neighbours tentative distance is greater than, the current nodes tentative distance + the edge(directpath)
                //Then set that neighbours new tentative distance to (current node tentative + the edge distance)
                if (neighbour.getTentativeDistance() > (current.getTentativeDistance() + current.getDirectPath(neighbour).getDistance())) {
                    neighbour.setTentativeDistance(current.getTentativeDistance() + current.getDirectPath(neighbour).getDistance());
                }
            }
        }
    }

    /**
     * Initialize the search, set the start node to have 0 tentative distance, 
     * set the current node to be start, add all nodes to unvisited.
     */
    private void initializeSearch() {
        //1. Set all tentative distances to infinity.
        // (Completed in BaseUnit's Constructor)
        //2. Set tentative distance of start node to 0.
        //3. Start has the lowest cost (0) which means it is the current node.
        //Add all nodes to unvisited, includes start(but its fine).
        start.setTentativeDistance(0);
        current = start;
        unvisited.addAll(nodes);   
    }

    /**
     * Given an arrayList of BaseUnits, determine which has the lowest tentative
     * distance and return it.
     * @param list
     * @return BaseUnit
     */
    private BaseUnit getLowestTentativeDistance(ArrayList<BaseUnit> list) {
        BaseUnit result = list.get(0);
        try {
            for (BaseUnit b : list) {
                if (result.getTentativeDistance() >= b.getTentativeDistance()) {
                    result = b;
                }
            }
        } catch (Exception e) {
            System.out.println("Null lowest tentative distance");
            System.out.println(e);
        }
        return result;
    }



}

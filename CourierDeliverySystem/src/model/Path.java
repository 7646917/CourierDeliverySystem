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
    
    
    public Path(BaseUnit start, BaseUnit end, ArrayList<BaseUnit> nodes){
        this.start = start;
        this.end = end;
        this.nodes = nodes;
    }
    
    public void setStart(BaseUnit start){
        this.start = start;
    }
    
    public void setEnd(BaseUnit end){
        this.end = end;
    }
    
    public ArrayList<BaseUnit> findShortestPath(){
        //Dijkstra's Algorithm
        //Using this algorithm as the nodes do not change position and are static.
        //Vertices are Junctions and Locations
        //Edges are DirectPaths
        
        ArrayList<BaseUnit> visited = new ArrayList<>(); //Contains start
        ArrayList<BaseUnit> unvisited = new ArrayList<>(); //Contains every other node
        
        BaseUnit current = start;
        start.setTentativeDistance(0); //By default in BaseUnit's constructer it is set to Integer.MaxValue
        visited.add(start);
        unvisited.addAll(nodes);
        
        //For every neighbour of the current node
        try{
        while(!unvisited.isEmpty()){
        ArrayList<BaseUnit> unvisitedNeighbours = new ArrayList<>();
        System.out.println("Exploring " + current.getName());

        for(BaseUnit neighbour : current.getNeighbours()){
            //if they are unvisited
            if(unvisited.contains(neighbour)){
                System.out.println("Neighbour: " + neighbour.getName());
                unvisitedNeighbours.add(neighbour);
                System.out.println("Distance to neighbour: " + current.getDirectPath(neighbour).getDistance());
                System.out.println("Tentative distance to neighbour: " + neighbour.getTentativeDistance());
                
                
                if(neighbour.getTentativeDistance() > (current.getTentativeDistance() + current.getDirectPath(neighbour).getDistance())){
                    //If the real distance is less then the tentative distance, set tentative to real.
                    //Will always change the values, since they are initially infinity (or Integer.MaxValue)
                    neighbour.setTentativeDistance(current.getTentativeDistance() + current.getDirectPath(neighbour).getDistance());
                    
                }
                
            }

            //Start again
            }
           
            visited.add(current);
            unvisited.remove(current);
            System.out.println("closest neighbour is " + getLowestTentativeDistance(unvisitedNeighbours).getName());
            
            current = getLowestTentativeDistance(unvisitedNeighbours);
            
            if(current == end){
                break;
            }
        }
        }catch(Exception e){
            System.out.println("Couldnt find shortest path:");
            System.out.println(e);
        }
        
        
        
        return visited;
        //System.out.println("The path from " + start.getName() + " to " + end.getName() + " is ");
    }
    
    
    private BaseUnit getLowestTentativeDistance( ArrayList<BaseUnit> list){
        BaseUnit result = list.get(0);
        try{
            for(BaseUnit b : list){
                if(result.getTentativeDistance() >= b.getTentativeDistance()){
                    result = b;
                }
            }
        }catch(Exception e){
            //System.out.println()
            System.out.println(e);
        }
        return result;
    }
    
    
    public BaseUnit getEnd(){
        return end;
    }
    
    public BaseUnit getStart(){
        return start;
    }
   
}

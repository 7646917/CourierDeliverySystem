/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.BaseUnit;
import model.Path;

/**
 *
 * @author Daniel
 */
public class RoundTrip {

    private BaseUnit start;
    private ArrayList<BaseUnit> locationsAndJunctions;
    private ArrayList<BaseUnit> destinations;
    private ArrayList<Path> paths;

    public RoundTrip(BaseUnit start, ArrayList<BaseUnit> destinations, ArrayList<BaseUnit> locationsAndJunctions) {
        this.start = start;
        this.destinations = destinations;
        this.locationsAndJunctions = locationsAndJunctions;
    }

    public ArrayList<Path> findallPaths() {
        ArrayList<Path> result = new ArrayList<>();
        result.add(createInitialPath());
        result.get(0).findShortestPath();
        for (BaseUnit nextLoc : destinations) {
            int iteration = 0;
            Path p = new Path(result.get(iteration).getEnd(), nextLoc, locationsAndJunctions);
            p.findShortestPath();
            result.add(p);
            iteration++;
        }
        setPaths(result);
        return result ;
    }

private Path createInitialPath(){
        return new Path(start,destinations.get(0),locationsAndJunctions);
    }
    

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public void printPaths() {
        System.out.println("There are " + paths.size() + " paths");
        for (Path p : paths) {
            p.printPath();
            System.out.println(" ");
        }
    }

}

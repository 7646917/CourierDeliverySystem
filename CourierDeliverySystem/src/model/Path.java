/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */

public class Path {
    
    private BaseUnit start;
    private BaseUnit end;
    private ArrayList<DirectPath> path;
    
    
    public Path(BaseUnit start, BaseUnit end){
        this.start = start;
        this.end = end;
    }
    
    public void setStart(BaseUnit start){
        this.start = start;
    }
    
    public void setEnd(BaseUnit end){
        this.end = end;
    }
    
    public void findPath(){
        //Create two arraylists, open and closed
        //create an set goal to be the endstate
        BaseUnit goal = this.end;
        ArrayList<BaseUnit> open = new ArrayList<BaseUnit>();
        ArrayList<BaseUnit> closed = new ArrayList<BaseUnit>();
        //set open to the default state
        open.add(start);
        //Set closed to empty, no explored nodes yet.
        closed.clear();
    }
    
    
    public BaseUnit getEnd(){
        return end;
    }
    
    public BaseUnit getStart(){
        return start;
    }
    
}

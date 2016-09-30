/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Daniel
 */
public class DirectPath {
    private BaseUnit start, end;
    private int distance;
    
    public DirectPath(BaseUnit start, BaseUnit end){
        this.start = start;
        this.end = end;
        calculateDistance();
    }
    
    public void setStart(BaseUnit start){
        this.start = start;
    }
    
    public void setEnd(BaseUnit end){
        this.end = end;
    }
    
    public BaseUnit getEnd(){
        return end;
    }
    
    public BaseUnit getStart(){
        return start;
    }
    
    public int getDistance(){
        return distance;
    }
    
    public void calculateDistance(){
        int x1 = this.start.getXPos();
        int y1= this.start.getYPos();
        int x2 = this.end.getXPos();
        int y2 = this.end.getYPos();
        int distance;
        
        distance = (int) Math.sqrt(
                Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        //The distance of two points on a 2d plane.
        
        this.distance = distance;
    }
}

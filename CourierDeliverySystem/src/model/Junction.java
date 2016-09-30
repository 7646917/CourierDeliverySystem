/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Princy
 */
public class Junction extends BaseUnit {   
    private ArrayList<DirectPath> directPaths;
    
    public Junction(String jctName, int xPos, int yPos){
        //myJunctionList = new ArrayList<Junction>();
        directPaths = new ArrayList<DirectPath>();
        setName(jctName);
        setXPos(xPos);
        setYPos(yPos);
    }  
   
    public Junction(String name, int xPos, int yPos, List<BaseUnit> directPaths){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        addDirectPathsTo(directPaths);
    }
    public ArrayList<DirectPath> getDirectPaths(){
        return directPaths;
    }
    
    public void addDirectPath(DirectPath directPath){
        directPaths.add(directPath);
    }
    
    public void addDirectPathTo(BaseUnit end){
        DirectPath newPath = new DirectPath(this,end);
        directPaths.add(newPath);
    }
    
    public void addDirectPathsTo(List<BaseUnit> endPoints){
        for (BaseUnit endPoint : endPoints) {
            DirectPath newPath = new DirectPath(this,endPoint);
            directPaths.add(newPath);
        }
    }
    
}

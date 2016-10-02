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
    
    public Junction(String jctName, int xPos, int yPos){
        //myJunctionList = new ArrayList<Junction>();
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
    
    
   
    
}

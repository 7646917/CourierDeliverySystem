/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Daniel Mastrowicz 21/09/16
 */

 public class BaseUnit {
    private String name;
    private String jctName;
    private String imgName;
    private int xPos;
    private int yPos;
    private int xSize;
    private int ySize;
    private ArrayList<DirectPath> directPaths;

    
    public String getName() {
        return name;
    }
    
    public BaseUnit(){
        directPaths = new ArrayList<DirectPath>();
    }
    public BaseUnit(String name, int xPos, int yPos, int xSize, int ySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.name = name;
        directPaths = new ArrayList<DirectPath>();
    }
    
    public BaseUnit(String name,String imgName, int xPos, int yPos, int xSize, int ySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.imgName = imgName;
        this.name = name;
        directPaths = new ArrayList<DirectPath>();
    }
    
    public BaseUnit(String jctName, int xPos, int yPos){
        this.jctName = jctName;
        this.xPos = xPos;
        this.yPos = yPos;
        directPaths = new ArrayList<DirectPath>();
    }
    
    public void setXPos(int x){
        xPos = x;
    }
    
    public void setYPos(int y){
        yPos = y;
    }
    
    public void setXSize(int x){
        xSize = x;
    }
    
    public void setYSize(int y){
        ySize = y;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public int getXPos() {
        //System.out.println("xPos " + xPos);
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public ArrayList<DirectPath> getDirectPaths(){
        return directPaths;
    }
    
    public DirectPath getDirectPath(BaseUnit endPoint){
        DirectPath result = null;
        if(directPathTo(endPoint)){
            
            //result = new DirectPath(this,directPaths.get);
        }
        return result;
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
    
    public Boolean directPathTo(BaseUnit endPoint){
        return endPointExists(endPoint);
    }
    
    private Boolean endPointExists(BaseUnit endPoint){
        Boolean result = false;
         for(int i=0;i<directPaths.size();i++){
            if(directPaths.get(i).getEnd() == endPoint){
                result = true;
            }
        }
        return result;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author Daniel Mastrowicz 21/09/16
 */

 public class BaseUnit {
    private String name;
    private int xPos;
    private int yPos;
    private int xSize;
    private int ySize;
    
    public String getName() {
        return name;
    }
    
    public BaseUnit(){
        
    }
    public BaseUnit(String name, int xPos, int yPos, int xSize, int ySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.name = name;
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
    
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}

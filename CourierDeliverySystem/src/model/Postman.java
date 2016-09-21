/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Daniel Mastrowicz 21/9/16
 */
public class Postman extends BaseUnit {
    
    private int moveSpeed;
    
    public Postman(){
        
    }
    public Postman(String name, int xPos,int yPos, int xSize, int ySize){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setXSize(xSize);
        setYSize(ySize);
    }
    
    public void MoveTo(Location loc){
        
    } 
    
}

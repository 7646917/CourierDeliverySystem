package model;

/**
 * Created by Dave on 20/09/2016.
 */
public class Location extends BaseUnit {

   public Location(String name, int xPos,int yPos, int xSize, int ySize){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setXSize(xSize);
        setYSize(ySize);
    }
    
}

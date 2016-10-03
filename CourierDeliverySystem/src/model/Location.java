package model;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by Dave on 20/09/2016.
 */
public class Location extends BaseUnit {
    private ArrayList<DirectPath> directPaths;
    public Location(String name,String imgName, int xPos,int yPos, int xSize, int ySize){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setXSize(xSize);
        setYSize(ySize);
        setImgName(imgName);
        setTentativeDistance(Integer.MAX_VALUE);

    }
}

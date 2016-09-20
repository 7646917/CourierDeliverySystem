package model;

/**
 * Created by Dave on 20/09/2016.
 */
public class Location {

    private String name;
    private int xPos;
    private int yPos;
    private int xSize;
    private int ySize;



    public String getName() {
        return name;
    }

    public Location(String name){
        this.name = name;
    }

    public Location(String name, int xPos, int yPos, int xSize, int ySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.name = name;
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

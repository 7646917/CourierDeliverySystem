package model;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dave on 20/09/2016.
 */
public class Location extends BaseUnit {
    private ArrayList<DirectPath> directPaths;
    private Image img;

    public Location(String name,String imgName, int xPos,int yPos, int xSize, int ySize){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setXSize(xSize);
        setYSize(ySize);
        setImgName(imgName);
        setTentativeDistance(Integer.MAX_VALUE);

    }
    public Image getImg(){
        if (img == null) {
            try {
                img = ImageIO.read(new File(getImgName()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return img;
    }
}

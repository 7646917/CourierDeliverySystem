/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Daniel Mastrowicz 21/9/16
 */
public class Postman extends BaseUnit {
    
    private int moveSpeed;
    private Image img;
    private BufferedImage postImage;
    private boolean isVisible;
    
    public Postman(String name, int xPos,int yPos, int xSize, int ySize){
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        setXSize(xSize);
        setYSize(ySize);
        isVisible = false;

    }
    
    public void moveTo(Location loc){

    } 
    
    public void moveTo(int x, int y){
        setXPos(x);
        setYPos(y);
    }
    
    public Point getPoint(){
        return new Point(getXPos(),getYPos());
    }
    
    public void setImg(String imgName){
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Image getImg(){
        return img;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setLocation(Location loc) {
        setXPos(loc.getXPos());
        setYPos(loc.getYPos());
    }

    public BufferedImage getPostImage() {
        if (postImage == null) {
            try {
                postImage = ImageIO.read(new File(getImgName()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return postImage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dave
 */
public class Location {
    private int x;
    private int y;
    private String name;
          
    Location(int aX, int aY, String aName) {
        x = aX;
        y = aY;
        name = aName;
    }

    public Location() { }
}

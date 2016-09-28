package model;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dave on 14/09/2016.
 */
public class Model {

    private List<Location> locationList;
    private ArrayList<Junction> junctionList;    
    private Postman postman;
    //private Junction  j = new Junction();
   
    public Model() {
        this.locationList = new ArrayList<>();
        this.junctionList = new ArrayList<Junction>();
        //this.postman = new Postman();
        locationData();
        junctionData(); 
        this.postman = new Postman("Pat",20,280,0,0);

    }
    
    public void setPostman(Postman newPostman){
        postman = newPostman;
    }
    
    public Postman getPostman(){
        System.out.println("getPostman() " + postman.getName()+", " + postman.getXPos());
        return postman;
    }
    public void addLocation(Location location) {
        this.locationList.add(location);        
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
    
    public Location getLocation(String name){
        Location result = new Location("null","null",0,0,0,0);
        try{
            for (Location l : locationList) {
                if(l.getName().equals(name)){
                    result = l;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public List<Location> getLocationList() {
        return locationList;
    }
    
    public void addJunction(Junction junction){        
        this.junctionList.add(junction);        
    }   

    public void setJunctionList(ArrayList<Junction> junctionList) {
        this.junctionList = junctionList;
    }
        
    public ArrayList<Junction> getJunctionList() {   
        return junctionList;
    }
    
    public void junctionData(){
        addJunction(new Junction("j1", 0, 58));
        addJunction(new Junction("j2", 344, 45));
        addJunction(new Junction("j3", 395, 305));
        addJunction(new Junction("j4", 235, 330));
        addJunction(new Junction("j5", 113, 361));
       
        addJunction(new Junction("j6", 195, 195));
        addJunction(new Junction("j7", 30, 145));
        
        addJunction(new Junction("j8", 520, 275));
    }
    
    public void locationData(){
       addLocation(new Location("Shoe Shop","src/resources/images/house.jpg", 480, 56, 60, 14));
       addLocation(new Location("Pet Store","src/resources/images/house.jpg", 450, 320, 90, 14));
       addLocation(new Location("Airport","src/resources/images/house.jpg",200, 170, 60, 14));
       addLocation(new Location("House","src/resources/images/house.jpg", 328, 237, 60, 14));
    }


}
package model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dave on 14/09/2016.
 */
public class Model {

    private List<Location> locationList;
    private Postman postman;
    
    public Model() {
        this.locationList = new ArrayList<>();
        this.postman = new Postman("Pat",0,0,0,0);
    }
    
    public void setPostman(Postman newPostman){
        postman = newPostman;
    }
    
    public Postman getPostman(){
        return postman;
    }
    public void addLocation(Location location) {
        this.locationList.add(location);
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
    
    public Location getLocation(String name){
        Location result = new Location("null",0,0,0,0);
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


}
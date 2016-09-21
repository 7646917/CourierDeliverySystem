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
        this.postman = new Postman();
    }
    
    public void setPostman(Postman newPostman){
        postman = newPostman;
    }
    
    public void addLocation(Location location) {
        this.locationList.add(location);
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }


}
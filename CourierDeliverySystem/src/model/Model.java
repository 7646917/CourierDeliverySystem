package model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dave on 14/09/2016.
 */
public class Model {

    private List<Location> locationList;

    public Model() {
        this.locationList = new ArrayList<>();
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
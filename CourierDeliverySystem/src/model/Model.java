package model;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dave on 14/09/2016.
 */
public class Model {

    private ArrayList<Location> locationList;
    private ArrayList<Junction> junctionList;    
    private Postman postman;
    //private Junction  j = new Junction();
   
    public Model() {
        this.locationList = new ArrayList<>();
        this.junctionList = new ArrayList<Junction>();
        //this.postman = new Postman();
        locationData();
        junctionData();

        this.postman = new Postman("Pat",20,280,50,50);
        this.postman.setImgName("resources/images/postman.png");
        this.postman.setLocation(locationList.get(4));

        this.postman.setyPosDrawOffset(-25);
        //this.postman.setxPosDrawOffset(25);

    }
    
    public void setPostman(Postman newPostman){
        postman = newPostman;
    }
    
    public Postman getPostman(){

        //System.out.println("getPostman() xPos: " + postman.getXPos()+", yPos: " + postman.getXPos());
        return postman;
    }

    public void addLocation(Location location) {
        this.locationList.add(location);        
    }

    public void setLocationList(ArrayList<Location> locationList) {
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

    public ArrayList<Location> getLocationList() {
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
    
    public Junction getJunction(String name){
        Junction result = null;
        try{
            for (Junction j : junctionList) {
                if(j.getName().equals(name)){
                    result = j;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
    
    public void junctionData(){
        Junction j1,j2,j3,j4,j5,j6,j7,j8;
        Location shoeShop = locationList.get(0);
        Location petStore = locationList.get(1);
        Location airport = locationList.get(2);
        Location house = locationList.get(3);
        Location start = locationList.get(4);
        //Initialize all the junctions
        j1 = new Junction("j1",0,58);
        j2 = new Junction("j2", 344, 45);
        j3 = new Junction("j3", 395, 305);
        j4 = new Junction("j4", 235, 330);
        j5 = new Junction("j5", 113, 361);
        j6 = new Junction("j6", 195, 195);
        j7 = new Junction("j7", 30, 145);
        j8 = new Junction("j8", 520, 275);
        
        //Create all the direct paths between junctions
        List<BaseUnit> list = Arrays.asList(j2,j7); //Paths from j1 are (j2,j7)
        j1.addDirectPathsTo(list);
        
        list = Arrays.asList(j1,j3,j8,shoeShop,house); 
        j2.addDirectPathsTo(list);
        
        list = Arrays.asList(j2,j4,j8,house,petStore);
        j3.addDirectPathsTo(list);
        
        list = Arrays.asList(j3,j5,j6);
        j4.addDirectPathsTo(list);
        
        list = Arrays.asList(j4,j7,start);
        j5.addDirectPathsTo(list);
        
        list = Arrays.asList(j4,j7,airport);
        j6.addDirectPathsTo(list);
        
        list = Arrays.asList(j1,j5,j6);
        j7.addDirectPathsTo(list);
        
        list = Arrays.asList(j2,j3,petStore);
        j8.addDirectPathsTo(list);
        
        //Add direct paths to locations, some only have one.
        list = Arrays.asList(j3,j8);
        petStore.addDirectPathsTo(list);
        
        list = Arrays.asList(j2,j3);
        house.addDirectPathsTo(list);
        
        airport.addDirectPathTo(j6);
        shoeShop.addDirectPathTo(j2);
        start.addDirectPathTo(j5);
        
        
        //Add all junctions, with their direct paths to ArrayList
        addJunction(j1);
        addJunction(j2);
        addJunction(j3);
        addJunction(j4);
        addJunction(j5);
        addJunction(j6);
        addJunction(j7);
        addJunction(j8);
        
        /*Test commands to get distance between directPaths
        System.out.println("Distance between j1 and j2 is " + j1.getDirectPath(j2).getDistance());
        System.out.println("Distance between j5 and j7 is " + j5.getDirectPath(j7).getDistance());
        System.out.println("Distance between j2 and Shoeshop is " + j2.getDirectPath(shoeShop).getDistance());
        System.out.println("Distance between j1 and j8 is (SHOULDNT WORK) " + j1.getDirectPath(j8).getDistance()); Causes exception
        */

    }
    
    public void locationData() {
        /*
        addLocation(new Location("Shoe Shop", "resources/images/shoeshop.png", 480, 56, 60, 60));
        addLocation(new Location("Pet Store", "resources/images/petstore.png", 450, 320, 40, 40));
        //addLocation(new Location("Airport", "resources/images/airport.png", 200, 170, 40, 40));
        //addLocation(new Location("Start", "resources/images/postoffice.jpg", 110, 300, 70, 70));
        */
        Location shoeshop = new Location("Shoe Shop", "resources/images/shoeshop.png", 480, 56, 60, 60);
        addLocation(shoeshop);

        Location petstore = new Location("Pet Store", "resources/images/petstore.png", 450, 320, 60, 60);
        addLocation(petstore);


        Location airport = new Location("Airport", "resources/images/airport.png", 200, 170, 80, 80);
        airport.setyPosDrawOffset(-20);
        addLocation(airport);

        Location house = new Location("House", "resources/images/house.png", 328, 237, 60, 60);
        house.setxPosDrawOffset(-10);
        house.setyPosDrawOffset(-10);
        addLocation(house);


        Location start = new Location("Start", "resources/images/postoffice.jpg", 110, 330, 70, 70);
        start.setyPosDrawOffset(-40);
        start.setxPosDrawOffset(10);
        addLocation(start);



    }
}
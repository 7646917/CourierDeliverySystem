package controller;
/**
 * Created by Dave on 14/09/2016.
 */


import java.util.ArrayList;
import model.BaseUnit;
import model.Model;
import model.Path;
import view.Listener;
import view.View;


public class Controller implements Listener {
    private Model model;
    private View view;    

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        //postmanValue = false;
        //model.getPostman().moveTo(model.getLocation("Start"));  uncomment it 
        //model.getPostman().moveTo(100,100);

    }

    @Override
    public void sendActionPerformed() {
        System.out.println("Send!");
        if (view.getListDeliveryQueue().getItemCount() >= 3) {
            //Don't allow adding. Put feedback here.
      
        } else {
            view.getListDeliveryQueue().add(view.getBtnGroup().getSelection().getActionCommand());
            if (view.getListDeliveryQueue().getItemCount() == 3) {
                //Path newPath = new Path();
                for (int i = 0; i < 3; i++) {
                    view.getCurrentDeliveryList().add(view.getListDeliveryQueue().getItem(i));                     
                    view.showPostMan();
                    System.out.println(model.getLocation("Airport").directPaths());
                    
                    //calculateShortestPath();
                }
                
                
                //Test path
                ArrayList<model.BaseUnit> locAndJunc = new ArrayList<>();
                
               // locAndJunc.addAll(model.getLocationList());
                locAndJunc.addAll(model.getJunctionList());
                //Missing start location???
                
                //new path from j1 to 
                Path testPath = new Path(model.getJunction("j2"),model.getJunction("j8"),locAndJunc);
                ArrayList<BaseUnit> shortestPath = testPath.findShortestPath();
                System.out.println("Shortest path route from j2 to j8 is " );
                int totalDistance = 0;
                for(BaseUnit b : shortestPath){
                    System.out.println(b.getName());
                    totalDistance += b.getTentativeDistance();
                }
                System.out.println("Total distance being: ");
                System.out.println(totalDistance);
                view.getListDeliveryQueue().removeAll();
            }
        }
    }
    
    
    
    @Override
    public void cancelActionPerformed() {
        view.getListDeliveryQueue().removeAll();
        System.out.println("Cancel...");

    }
}   
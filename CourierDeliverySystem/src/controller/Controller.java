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
                    //System.out.println(model.getLocation("Airport").directPaths());

                    //calculateShortestPath();
                }

                //Test path
                ArrayList<model.BaseUnit> locAndJunc = new ArrayList<>();

                locAndJunc.addAll(model.getLocationList());
                locAndJunc.addAll(model.getJunctionList());
                //Missing start location PAT'S HOME??????
                //model.getLocation(view.getListDeliveryQueue().getItem(0))
                //new path from start to last item added.

                BaseUnit start,loc1, loc2, loc3;
                start = model.getLocation("Start");
                loc1 = model.getLocation(view.getListDeliveryQueue().getItem(0));
                loc2 = model.getLocation(view.getListDeliveryQueue().getItem(1));
                loc3 = model.getLocation(view.getListDeliveryQueue().getItem(2));
                Path path1 = new Path(start, loc1, locAndJunc);
                Path path2 = new Path(loc1, loc2, locAndJunc);
                Path path3 = new Path(loc2, loc3, locAndJunc);
                Path path4 = new Path(loc3, start, locAndJunc);

                System.out.println("Total delivery path: ");
                ArrayList<BaseUnit> shortestPath1 = path1.findShortestPath();
                System.out.println("Path 1: start to " + loc1.getName());
                for (BaseUnit b : shortestPath1) {
                    System.out.print(b.getName());
                    System.out.print(",");
                }
                //Remove packaage
                System.out.println(" ");
                view.getCurrentDeliveryList().remove(view.getListDeliveryQueue().getItem(0));

                ArrayList<BaseUnit> shortestPath2 = path2.findShortestPath();
                System.out.println("Path 2: " + loc1.getName() + " to " + loc2.getName());
                for (BaseUnit b : shortestPath2) {
                    System.out.print(b.getName());
                    System.out.print(",");
                }
                //Remove packaage
                System.out.println(" ");

                view.getCurrentDeliveryList().remove(view.getListDeliveryQueue().getItem(1));
                
                ArrayList<BaseUnit> shortestPath3 = path3.findShortestPath();
                System.out.println("Path 3: " + loc2.getName() + " to " + loc3.getName());
                for (BaseUnit b : shortestPath3) {
                    System.out.print(b.getName());
                    System.out.print(",");
                }
                //Remove packaage
                System.out.println(" ");

                view.getCurrentDeliveryList().remove(view.getListDeliveryQueue().getItem(2));
                
                System.out.println("Delivered all packages, time to go home");

                ArrayList<BaseUnit> shortestPath4 = path4.findShortestPath();
                System.out.println("Path 4: " + loc3.getName() + " to start");
                for (BaseUnit b : shortestPath4) {
                    System.out.print(b.getName());
                    System.out.print(",");
                }
                
                System.out.println(" ");

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

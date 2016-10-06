package controller;

/**
 * Created by Dave on 14/09/2016.
 */

import java.util.ArrayList;
import java.util.Arrays;
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
                BaseUnit start;
                locAndJunc.addAll(model.getLocationList());
                locAndJunc.addAll(model.getJunctionList());
                start = model.getLocation("Start");
                //RoundTrip newRoundTrip = new RoundTrip(start,locAndJunc);
                //model.getLocation(view.getListDeliveryQueue().getItem(0))
                //new path from start to last item added.

                BaseUnit loc1,loc2,loc3;
                loc1 = model.getLocation(view.getListDeliveryQueue().getItem(0));
                loc2 = model.getLocation(view.getListDeliveryQueue().getItem(1));
                loc3 = model.getLocation(view.getListDeliveryQueue().getItem(2));
                
                ArrayList<BaseUnit> destinations = new ArrayList<>();

                destinations.add(loc1);
                destinations.add(loc2);
                destinations.add(loc3);
                RoundTrip newRoundTrip = new RoundTrip(start,destinations,locAndJunc);
                
                ArrayList<Path> paths = newRoundTrip.findallPaths();
                newRoundTrip.printPaths();
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

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

<<<<<<< HEAD
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


=======
>>>>>>> 2198f2d04bed9b82037791d556abb40239f7cc96
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
<<<<<<< HEAD

                    DeployPostman();
=======
                    view.showPostMan();
                    //System.out.println(model.getLocation("Airport").directPaths());
>>>>>>> 2198f2d04bed9b82037791d556abb40239f7cc96

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

<<<<<<< HEAD
    private void DeployPostman() {
        //Update the postman at the model
        ArrayList<Point> pointList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 2 + rand.nextInt(10); i++) {
            pointList.add(new Point(rand.nextInt( view.getMapPanel().getWidth()),
                    rand.nextInt( view.getMapPanel().getHeight())));
        }

        //Point start  = pointList.get(0);

        Point start = new Point();
        start.setLocation(model.getPostman().getXPos(),model.getPostman().getYPos());

        Point end = pointList.get(1);

        //MoveBetween2Points(pointList.get(0), pointList.get(1),  view.getMapPanel());

        for (double i = 0.0; i <= 1.0; i += 0.005) {
            //Point p = interpolate(pointList.get(0), pointList.get(1), i);
            Point p = interpolate(start, end, i);
            model.getPostman().setXPos(p.x);
            model.getPostman().setYPos(p.y);

            view.getMapPanel().repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //Redraw the panel which will get the visibility flag from the model


        //view.getPostman();

    }

    public Point interpolate(Point start, Point end, double fraction) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;

        int newX = (int) (start.x + dx * fraction);
        int newY = (int) (start.y + dy * fraction);

        return new Point(newX, newY);
    }


=======
>>>>>>> 2198f2d04bed9b82037791d556abb40239f7cc96
    @Override
    public void cancelActionPerformed() {
        view.getListDeliveryQueue().removeAll();
        System.out.println("Cancel...");

    }
}

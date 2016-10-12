package controller;

/**
 * Created by Dave on 14/09/2016.
 */

import java.awt.event.ActionEvent;

import model.*;
import view.Listener;
import view.View;
import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;


public class Controller implements Listener {

    private Model model;
    private View view;
    private Timer animationLoop;
    private Postman postman;

    private double i; //Interpolation factor

    private Point end;
    private Point start;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.postman = model.getPostman();

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
                }

                //HACK: end point to be implemented properly
                //DeployPostman(new Point(200,200));
                DeployPostman( new Point(model.getLocation(view.getListDeliveryQueue().getItem(0)).getXPos(),
                        model.getLocation(view.getListDeliveryQueue().getItem(0)).getYPos()));

                //calculateShortestPath();

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
        view.getCurrentDeliveryList().removeAll();
        System.out.println("Cancel...");

    }

    private void DeployPostman(Point destination) {
        System.out.println("Deploying postman");

        postman.setVisible(true);
        view.getBtnPost().setEnabled(false);

        //Set the start and end destinations
        start = new Point(postman.getXPos(), postman.getYPos());
        end = destination;

        animationLoop = new Timer(10, this);
        //animationLoop.setRepeats(true);
        animationLoop.setCoalesce(true);
        animationLoop.start();

    }

    private Point interpolate(Point start, Point end, double fraction) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;

        int newX = (int) (start.x + dx * fraction);
        int newY = (int) (start.y + dy * fraction);

        System.out.println("NewX: " + newX + " NewY: "+ newY);

        //Catch all when the end has gone past start
        if (dx > 0 && newX > end.x)
            return end;
        else if (dx < 0 && newX < end.x)
            return end;
        else
            return new Point(newX, newY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(postman.getXPos() == end.x && postman.getYPos() == end.y) {
            animationLoop.stop();
            view.getBtnPost().setEnabled(true);
        } else {
            i += 0.005;
            Point p = interpolate(start, end, i);
            postman.setXPos(p.x);
            postman.setYPos(p.y);

            view.getMapPanel().repaint();

        }
    }
}

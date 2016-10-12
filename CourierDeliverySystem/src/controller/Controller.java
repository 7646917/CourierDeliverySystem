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

    public static final double INTERPOLATION_FACTOR = 0.005;
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
               
                ArrayList<Path> allPaths = new ArrayList<>();
                Path p1 = new Path(start, loc1, locAndJunc);
                Path p2 = new Path(loc1, loc2, locAndJunc);
                Path p3 = new Path(loc2, loc3, locAndJunc);
                Path p4 = new Path(loc3, start, locAndJunc);
                
                System.out.println("before loop");
                ArrayList<BaseUnit> shortest = p1.findShortestPath();
                System.out.println("after shortest");

                //Deploy to each baseunit of path1,path2,path3,path4.
                for(BaseUnit b : p1.findShortestPath()){
                    if(i != 0){
                        System.out.println("TEST");
                        Point point = new Point(b.getXPos(),b.getYPos());
                        DeployPostman(point);
                    }
                }
                //DeployPostman( new Point(model.getLocation(view.getListDeliveryQueue().getItem(0)).getXPos(),
                  //      model.getLocation(view.getListDeliveryQueue().getItem(0)).getYPos()));
                //ArrayList<BaseUnit> destinations = new ArrayList<>();
                
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
    
    //x=0,y=0
    //new point x=1,y=1
    //While not this new point
    //wait
    //

    private void DeployPostman(Point destination) {
        System.out.println("Deploying postman to " + destination.getX() + destination.getY());

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

        //System.out.println("NewX: " + newX + " NewY: "+ newY + "| dx: " + dx + " dy: " + dy + "| endX: " +end.x);

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
            i=0;

        } else {
            i += INTERPOLATION_FACTOR;
            Point p = interpolate(start, end, i);
            postman.setXPos(p.x);
            postman.setYPos(p.y);

            view.getMapPanel().repaint();

        }
    }
}

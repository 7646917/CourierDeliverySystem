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
    private ArrayList<BaseUnit> shortest;
    private double i; //Interpolation factor

    private Point end;
    private Point start;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.postman = model.getPostman();
        this.animationLoop = new Timer(0, this);
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
                BaseUnit startB = model.getLocation("Start");
                locAndJunc.addAll(model.getLocationList());
                locAndJunc.addAll(model.getJunctionList());

                BaseUnit loc1, loc2, loc3;
                loc1 = model.getLocation(view.getListDeliveryQueue().getItem(0));
                loc2 = model.getLocation(view.getListDeliveryQueue().getItem(1));
                loc3 = model.getLocation(view.getListDeliveryQueue().getItem(2));

                Path p1 = new Path(startB, loc1, locAndJunc);
                Path p2 = new Path(loc1, loc2, locAndJunc);
                Path p3 = new Path(loc2, loc3, locAndJunc);
                Path p4 = new Path(loc3, startB, locAndJunc);

                shortest = p1.findShortestPath();
                shortest.addAll(p2.findShortestPath());
                shortest.addAll(p3.findShortestPath());
                shortest.addAll(p4.findShortestPath());

                System.out.println("after shortest");

                Point point = new Point(shortest.get(0).getXPos(), shortest.get(0).getYPos());
                DeployPostman(point);


                view.getListDeliveryQueue().removeAll();
            }
        }
    }

    @Override
    public void cancelActionPerformed() {
        view.getListDeliveryQueue().removeAll();
        //view.getCurrentDeliveryList().removeAll();
        System.out.println("Cancel...");

    }

    //x=0,y=0
    //new point x=1,y=1
    //While not this new point
    //wait
    //
    private void DeployPostman(Point destination) {
        System.out.println("Deploying postman to " + "{ X:" + destination.getX() + " ||| Y:" + destination.getY() + "}");

        postman.setVisible(true);
        view.getBtnPost().setEnabled(false);

        //Set the start and end destinations
        start = new Point(postman.getXPos(), postman.getYPos());
        end = destination;

        animationLoop = new Timer(10, this);
        //animationLoop.setRepeats(true);
        animationLoop.setCoalesce(true);
        animationLoop.start();
        shortest.remove(0);
    }

    private Point interpolate(Point start, Point end, double fraction) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;

        int newX = (int) (start.x + dx * fraction);
        int newY = (int) (start.y + dy * fraction);

        //System.out.println("NewX: " + newX + " NewY: "+ newY + "| dx: " + dx + " dy: " + dy + "| endX: " +end.x);
        //Catch all when the end has gone past start
        if (dx > 0 && newX > end.x) {
            return end;
        } else if (dx < 0 && newX < end.x) {
            return end;
        } else {
            return new Point(newX, newY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (postman.getXPos() == end.x && postman.getYPos() == end.y) {
            animationLoop.stop();
            view.getBtnPost().setEnabled(true);
            i = 0;
            //GO TO NEXT POINT
            if (!shortest.isEmpty()) {
                Point point2 = new Point(shortest.get(0).getXPos(), shortest.get(0).getYPos());
                DeployPostman(point2);
            }

        } else {
            i += INTERPOLATION_FACTOR;
            Point p = interpolate(start, end, i);
            postman.setXPos(p.x);
            postman.setYPos(p.y);

            view.getMapPanel().repaint();

        }
    }
}

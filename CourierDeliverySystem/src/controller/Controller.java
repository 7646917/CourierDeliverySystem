package controller;
/**
 * Created by Dave on 14/09/2016.
 */


import model.Model;
import view.Listener;
import view.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


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
                for (int i = 0; i < 3; i++) {
                    view.getCurrentDeliveryList().add(view.getListDeliveryQueue().getItem(i));

                    DeployPostman();

                    //calculateShortestPath();
                }
                view.getListDeliveryQueue().removeAll();
            }
        }
    }

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


    @Override
    public void cancelActionPerformed() {
        view.getListDeliveryQueue().removeAll();
        System.out.println("Cancel...");

    }
}   
package controller;
/**
 * Created by Dave on 14/09/2016.
 */


import model.Model;
import view.Listener;
import view.View;

import view.Postman_View;

public class Controller implements Listener {
    private Model model;
    private View view;
    private Postman_View p;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

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
                    p = new Postman_View(true);
//                    p.setVisible(true);   
                    p.repaint();
                }
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
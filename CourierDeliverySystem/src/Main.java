/**
 * Created by Dave on 14/09/2016.
 */

import controller.Controller;
import model.Location;
import model.Model;
import view.View;

import javax.swing.SwingUtilities;

public class Main
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
    }

    public static void start() {
        Model model = new Model();

        //Hardcoding the data for the model for now.
        model.addLocation(new Location("Shoe Shop", 480, 56, 60, 14));
        model.addLocation(new Location("Pet Store", 450, 320, 90, 14));
        model.addLocation(new Location("Airport", 200, 170, 60, 14));
        model.addLocation(new Location("House", 328, 237, 60, 14));


        View view = new View(model);  //Pass a reference of the model to the view
        Controller controller = new Controller(model, view); //Controller needs reference to model and view to do its work
        view.setListener(controller); //Pass a reference of the controller that acts as the action listener to the view
    }


}



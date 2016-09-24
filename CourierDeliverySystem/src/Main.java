/**
 * Created by Dave on 14/09/2016. testing
 */

import controller.Controller;
import model.Location;
import model.Model;
import view.View;
import model.Postman;
import javax.swing.SwingUtilities;
import model.Junction;

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
        //Postman postman = new Postman("Pat",0,0,10,10);
        /*postman.setImg("src/pat.jpg");
        model.setPostman(postman);*/
        //Hardcoding the data for the model for now.
        /*model.addLocation(new Location("Shoe Shop", 480, 56, 60, 14));
        model.addLocation(new Location("Pet Store", 450, 320, 90, 14));
        model.addLocation(new Location("Airport", 200, 170, 60, 14));
        model.addLocation(new Location("House", 328, 237, 60, 14));*/
        
        
        /*model.addJunction(new Junction("j1", 0, 58));
        model.addJunction(new Junction("j2", 344, 45));
        model.addJunction(new Junction("j3", 395, 305));
        model.addJunction(new Junction("j4", 235, 330));
        model.addJunction(new Junction("j5", 113, 361));
       
        model.addJunction(new Junction("j6", 195, 195));
        model.addJunction(new Junction("j7", 30, 145));*/
       
        View view = new View(model);  //Pass a reference of the model to the view
        
        Controller controller = new Controller(model, view); //Controller needs reference to model and view to do its work
        view.setListener(controller); //Pass a reference of the controller that acts as the action listener to the view
        
        
    }


}



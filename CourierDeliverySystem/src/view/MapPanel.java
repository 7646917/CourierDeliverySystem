package view;

import model.Model;
import model.Postman;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

import static com.apple.eio.FileManager.getResource;

/**
 * Created by Dave on 2/10/2016.
 */
public class MapPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Model model;
    private Postman postman;
    private Image postmanImage;

    public MapPanel(LayoutManager layout, boolean isDoubleBuffered, Model model) {
        super(layout, isDoubleBuffered);
        this.model = model;
        this.postman = model.getPostman();
        setBackground(Color.white);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //HACK: This works, but will need to get the details from the model.
        /*
        Image spiral = Toolkit.getDefaultToolkit().getImage("resources/images/postman.png");
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawImage(spiral, XPos, YPos, 25,25,this);
        g2.drawImage(spiral, 50, 50, 25,25,this); */

        Image pat = Toolkit.getDefaultToolkit().getImage(postman.getImgName());
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(pat, postman.getXPos(), postman.getYPos(), postman.getXSize(),postman.getYSize(),this);

        //setBounds(32, 11, 580, 380);

        //setLayout(null);

        //HACK: Removing roads for now
        //adding roads
        //Roads c = new Roads(model);
        //add(c);

        //HACK setting Pat to visible for now
        //model.getPostman().setVisible(true);


        //adding postman
//        JLabel postmanLabel = new JLabel(model.getPostman().getName());
//        postmanLabel.setIcon(new ImageIcon(model.getPostman().getImgName()));
//        postmanLabel.setBounds(model.getPostman().getXPos(),model.getPostman().getYPos(),
//                model.getPostman().getXSize(), model.getPostman().getYSize());
//        postmanLabel.setVisible(model.getPostman().isVisible());
//        //add(postmanLabel);
//        Graphics2D g2 = (Graphics2D) g;
//        Image img = null;
//
//        try {
//            URL imageURL = MapPanel.class.getResource(model.getPostman().getImgName());
//            img = Toolkit.getDefaultToolkit().getImage(imageURL);
//        } catch (Exception e) {
//            //Error!
//        }
//
//        g2.drawImage(img, model.getPostman().getXPos(),model.getPostman().getYPos(),
//                model.getPostman().getXSize(), model.getPostman().getYSize(), this);



        //Loop through and add locations
        /*model.getLocationList().forEach(m -> {
            JLabel jLabel = new JLabel(m.getName());
            //System.out.println(m.getImgName());
            ImageIcon myImg = new ImageIcon(m.getImgName());
            jLabel.setIcon(myImg);
            jLabel.setBounds(m.getXPos(), m.getYPos(), m.getXSize()+30, m.getYSize()+10);
            add(jLabel);
        });
        */

    }

}



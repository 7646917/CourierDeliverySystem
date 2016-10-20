package view;

import model.Model;
import model.Postman;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Created by Dave on 2/10/2016.
 */
public class MapPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Model model;
    private Postman postman;
    private BufferedImage postImage;


    public MapPanel(LayoutManager layout, boolean isDoubleBuffered, Model model) {
        super(layout, isDoubleBuffered);
        this.model = model;
        this.postman = model.getPostman();
        setBackground(Color.white);
        postImage = postman.getPostImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBounds(32, 11, 580, 380);
        setLayout(null);

        //Adding roads
        drawRoads(g);

        //Add the locations
        drawLocations(g);

        //Draw postman
        if (postman.isVisible()) {
            g.drawImage(postImage, postman.getXPos(), postman.getYPos(),
                    postman.getXSize(), postman.getYSize(), this);
        }

    }

    private void drawLocations(Graphics g) {
        //Loop through and add locations
        model.getLocationList().forEach(m -> {
            g.setColor(Color.BLACK);
            g.drawString(m.getName(),m.getXPos(), m.getYPos());
            g.drawImage(m.getImg(), m.getXPos(), m.getYPos(), 30, 30, this);
        });
    }

    private void drawRoads(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        GeneralPath path;
        GeneralPath path1;
        GeneralPath path2;
        GeneralPath path3;

        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(20.0f));
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path1 = new GeneralPath(GeneralPath.WIND_NON_ZERO);

        ///trial out the road
        path.moveTo(0, 60);
        path.lineTo(600, 45);
        path.lineTo(700, 250);
        path.lineTo(120, 370);

        path.closePath();
        g2.draw(path);

        path1.moveTo(350, 60);
        path1.lineTo(450, 550);
        g2.draw(path1);

        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(20.0f));
        path2 = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path2.moveTo(35, 150);
        path2.lineTo(200, 200);
        path2.lineTo(300, 500);
        g2.draw(path2);

        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(20.0f));
        path3 = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path3.moveTo(350, 55);
        path3.lineTo(525, 280);
        g2.draw(path3);

        //DEBUG: Uncomment for visible junctions
        /*
        g.setColor(Color.green);
        model.getJunctionList().forEach(m -> {
            g.fillOval(m.getXPos(),m.getYPos(),20,20);
            g.drawString(m.getName(), m.getXPos(), m.getYPos());
        });*/
    }
}



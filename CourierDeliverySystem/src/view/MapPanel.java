package view;

import model.Model;
import model.Postman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.apple.eio.FileManager.getResource;

/**
 * Created by Dave on 2/10/2016.
 */
public class MapPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Model model;
    private Postman postman;
    private BufferedImage postImage;
    private Timer timer;

    private Point end;
    private double i;

    public MapPanel(LayoutManager layout, boolean isDoubleBuffered, Model model) {
        super(layout, isDoubleBuffered);
        this.model = model;
        this.postman = model.getPostman();
        setBackground(Color.white);

        end = new Point(150,150);
        timer = new Timer(100, this);

        try {
            postImage = ImageIO.read(new File(postman.getImgName()));


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        timer.setRepeats(true);
        timer.setCoalesce(true);
        //timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBounds(32, 11, 580, 380);
        setLayout(null);

        //adding roads
        Roads c = new Roads(model);
        add(c);

        //Loop through and add locations
        model.getLocationList().forEach(m -> {
            JLabel jLabel = new JLabel(m.getName());
            //System.out.println(m.getImgName());
            ImageIcon myImg = new ImageIcon(m.getImgName());
            jLabel.setIcon(myImg);
            jLabel.setBounds(m.getXPos(), m.getYPos(), m.getXSize()+30, m.getYSize()+10);
            add(jLabel);
        });


        //Draw postman
        g.drawImage(postImage, postman.getXPos(), postman.getYPos(),
                postman.getXSize(),postman.getYSize(), this);

    }

    public void actionPerformed(ActionEvent e) {

        //Calls controller
        if(postman.getXPos() == end.x && postman.getYPos() == end.y)
            timer.stop();
        else {
            i += 0.005;
            Point p = interpolate(new Point(postman.getXPos(), postman.getYPos()), end, i);
            postman.setXPos(p.x);
            postman.setYPos(p.y);
            repaint();
        }

    }

    private Point interpolate(Point start, Point end, double fraction) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;

        int newX = (int) (start.x + dx * fraction);
        int newY = (int) (start.y + dy * fraction);

        return new Point(newX, newY);
    }
}



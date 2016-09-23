/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;
import javax.swing.JComponent;
import model.Model;

/**
 *
 * @author Princy
 */
public class Roads extends JComponent {
private Model model;
        public Roads(Model model) {
            this.model = model;
            //setPreferredSize(new Dimension(100, 100));
            setBounds(0, 0, 600, 400);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

                      
            Graphics2D g2 = (Graphics2D) g;
            Graphics2D gPath2 = (Graphics2D) g;
            Graphics2D gPath3 = (Graphics2D) g;
             Graphics2D gImg = (Graphics2D) g;
             
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

            gPath2.setPaint(Color.GRAY);
            gPath2.setStroke(new BasicStroke(20.0f));
            path2 = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            path2.moveTo(35, 150);
            path2.lineTo(200, 200);
            path2.lineTo(300, 500);
            gPath2.draw(path2);
            
            gPath3.setPaint(Color.GRAY);
            gPath3.setStroke(new BasicStroke(20.0f));
            path3 = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            path3.moveTo(350, 55);
            path3.lineTo(525, 280);            
            gPath3.draw(path3);
            
            g.setColor(Color.green);
           // g.fillOval(50,50,20,20);
                        
            model.getJunctionList().forEach(m -> {
                g.fillOval(m.getxPos(),m.getyPos(),20,20);  
                g.drawString(m.getName(), m.getxPos(), m.getyPos());
            });
                       
//            Image img1 = Toolkit.getDefaultToolkit().getImage("src/resources/images/postman.png");           
//            gImg.drawImage(img1, 80, 250, this);
        }
}
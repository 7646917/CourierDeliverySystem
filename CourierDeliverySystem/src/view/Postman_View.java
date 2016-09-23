/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author Princy
 */
public class Postman_View extends JComponent{
    private boolean b;
    public Postman_View(boolean b){
        this.b=b;
        //Postman_View p = new Postman_View();
        setVisible(b);
        setBounds(0, 0, 600, 400);
    }
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("testing postman view");
        super.paintComponent(g);
        Graphics2D gImg = (Graphics2D) g;
        Image img1 = Toolkit.getDefaultToolkit().getImage("src/resources/images/postman.png");
        gImg.drawImage(img1, 20, 280, this);
    }
    
}

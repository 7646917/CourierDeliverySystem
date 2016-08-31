/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Princy
 */

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class SwingFrame extends JFrame {

    private static final long serialVersionUID = -5209228424053569693L;

    private JRadioButton radioButtonShoeShop, radioButtonHouse1, radioButtonHouse2, radioButtonAirport;
    private ButtonGroup btnGroup;
    private JButton btnPost, btnCancel;

    private List listDeliveryQueue, currentDeliveryList;

    private JPanel uiPanel;
    private JLabel lblStart, lblAirport, lblShoeShop, lblHouse, lblHouse_1, lblLocations, lblWaiting, lblDelivering;

    public SwingFrame() {
        setTitle("Courier Delivery");
        getContentPane().setLayout(null);
        createGUI();

    }

    public void createGUI() {
        
        createUIPanel();
        addLocationToWaiting();
        addAllLists();
        
         lblLocations = new JLabel("Locations");
        lblLocations.setBounds(20, 338, 71, 14);
        getContentPane().add(lblLocations);

        lblWaiting = new JLabel("Waiting for Delivery");
        lblWaiting.setBounds(283, 338, 98, 14);
        getContentPane().add(lblWaiting);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(297, 444, 84, 23);
        getContentPane().add(btnCancel);

        btnPost = new JButton("Send");
        btnPost.setBounds(135, 388, 89, 23);
        getContentPane().add(btnPost);  
        
        btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (listDeliveryQueue.getItemCount() >= 3) {
                    //Don't allow adding. Put feedback here.
                } else {
                    listDeliveryQueue.add(btnGroup.getSelection().getActionCommand());
                    if(listDeliveryQueue.getItemCount() == 3){                        
                        for(int i=0; i<3; i++){
                        currentDeliveryList.add(listDeliveryQueue.getItem(i));
                        }
                        listDeliveryQueue.removeAll();
                    }
                }
            }
        });
       
        JCheckBox chckbxDeliverThroughShortest = new JCheckBox("Deliver Through Shortest Path");
        chckbxDeliverThroughShortest.setBounds(407, 350, 171, 23);
        getContentPane().add(chckbxDeliverThroughShortest);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listDeliveryQueue.removeAll();
            }
        });

    }
    
    // grouping the locations, more location can be added here
    public ButtonGroup addLocationToWaiting(){
   
         radioButtonShoeShop = new JRadioButton("Shoe Shop");
        radioButtonShoeShop.setBounds(20, 376, 109, 23);
        radioButtonShoeShop.setActionCommand("Shoe Shop");
        getContentPane().add(radioButtonShoeShop);

        radioButtonHouse1 = new JRadioButton("House 1");
        radioButtonHouse1.setBounds(20, 402, 109, 23);
        radioButtonHouse1.setActionCommand("House 1");
        getContentPane().add(radioButtonHouse1);

        radioButtonHouse2 = new JRadioButton("House 2");
        radioButtonHouse2.setBounds(20, 428, 109, 23);
        radioButtonHouse2.setActionCommand("House 2");
        getContentPane().add(radioButtonHouse2);

        radioButtonAirport = new JRadioButton("Airport");
        radioButtonAirport.setSelected(true);
        radioButtonAirport.setBounds(20, 350, 109, 23);
        radioButtonAirport.setActionCommand("Airport");
        getContentPane().add(radioButtonAirport);

        btnGroup = new ButtonGroup();
        btnGroup.add(radioButtonHouse1);
        btnGroup.add(radioButtonHouse2);
        btnGroup.add(radioButtonAirport);
        btnGroup.add(radioButtonShoeShop);
        
        return btnGroup;
    }
    
    
    //cretaing UI map panel
    public JPanel createUIPanel(){
        uiPanel = new JPanel();
        uiPanel.setBackground(Color.WHITE);
        uiPanel.setBounds(32, 11, 501, 316);
        getContentPane().add(uiPanel);
        uiPanel.setLayout(null);

        lblStart = new JLabel("Start");
        lblStart.setBounds(21, 291, 46, 14);
        uiPanel.add(lblStart);

        lblAirport = new JLabel("Airport");
        lblAirport.setBounds(411, 46, 46, 14);
        uiPanel.add(lblAirport);

        lblShoeShop = new JLabel("Shoe Shop");
        lblShoeShop.setBounds(213, 46, 75, 14);
        uiPanel.add(lblShoeShop);

        lblHouse = new JLabel("House 1");
        lblHouse.setBounds(205, 279, 46, 14);
        uiPanel.add(lblHouse);

        lblHouse_1 = new JLabel("House 2");
        lblHouse_1.setBounds(328, 237, 46, 14);
        uiPanel.add(lblHouse_1);
        
        return uiPanel;
    }
    
    //adding both waiting and currently delivering list    
    public void addAllLists(){
        listDeliveryQueue = new List();
        listDeliveryQueue.setBounds(271, 358, 130, 80);
        getContentPane().add(listDeliveryQueue);
        
         currentDeliveryList = new List();
        currentDeliveryList.setBounds(557, 131, 110, 60);
        getContentPane().add(currentDeliveryList);

        lblDelivering = new JLabel("Currently Delivering");
        lblDelivering.setBounds(557, 111, 110, 14);
        getContentPane().add(lblDelivering);
    }

    public void addInCurrentlyDeliverList() {

    }

    public static void main(String[] args) {
        SwingFrame swingFrame = new SwingFrame();
        swingFrame.setSize(800, 600);
        swingFrame.setVisible(true);

    }
}

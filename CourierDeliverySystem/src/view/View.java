package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by Dave on 14/09/2016.
 */

public class View extends JFrame implements ActionListener {

    private Model model;
    private Listener listener;
    private ButtonGroup btnGroup;
    private JButton btnPost, btnCancel;
    private List listDeliveryQueue;
    private List currentDeliveryList;
    private MapPanel mapPanel;
    private JCheckBox chckbxDeliverThroughShortest;

    public View(Model model) {
        super("Courier Delivery"); //Calls JFrame super constructor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = model;

        getContentPane().setLayout(null);
        createGUI();
        setActionListeners();

        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createGUI() {

        mapPanel = new MapPanel(null, true, this.model);
        mapPanel.setBounds(32, 11, 580, 380);
        mapPanel.setBackground(new Color(51,255,51));
        getContentPane().add(mapPanel);

        listDeliveryQueue = new List();
        listDeliveryQueue.setBounds(271, 428, 130, 80);
        getContentPane().add(listDeliveryQueue);

        currentDeliveryList = new List();
        currentDeliveryList.setBounds(640, 131, 110, 60);
        getContentPane().add(currentDeliveryList);

        JLabel lblLocations, lblWaiting, lblDelivering;

        lblDelivering = new JLabel("Currently Delivering");
        lblDelivering.setBounds(640, 111, 200, 14);
        getContentPane().add(lblDelivering);

        btnGroup = new ButtonGroup();

        //JPanel to contain the radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));

        //Loop through locations in the model and add them as radio buttons
        model.getLocationList().forEach(m -> {
            if (!"Start".equals(m.getName())) {
                JRadioButton radioButton = new JRadioButton(m.getName());
                radioButton.setActionCommand(m.getName()); //Use the name for the action
                btnGroup.add(radioButton);
                radioPanel.add(radioButton);
                //radioButton.setSelected(true);
            }
        });

        //Add the radio panel to the main UI
        radioPanel.setLocation(20, 428);
        radioPanel.setSize(109, 80);
        getContentPane().add(radioPanel);

        lblLocations = new JLabel("Locations");
        lblLocations.setBounds(20, 400, 71, 14);
        getContentPane().add(lblLocations);

        lblWaiting = new JLabel("Waiting for Delivery");
        lblWaiting.setBounds(283, 400, 150, 14);
        getContentPane().add(lblWaiting);

        btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand("Cancel");
        btnCancel.setBounds(297, 515, 84, 23);
        getContentPane().add(btnCancel);

        btnPost = new JButton("Send");
        btnPost.setActionCommand("Send");
        btnPost.setBounds(135, 450, 89, 23);
        getContentPane().add(btnPost);

        chckbxDeliverThroughShortest = new JCheckBox("Deliver Through Shortest Path");
        chckbxDeliverThroughShortest.setBounds(410, 420, 300, 23);
        getContentPane().add(chckbxDeliverThroughShortest);
        chckbxDeliverThroughShortest.setSelected(true);

    }

    private void setActionListeners() {
        btnPost.addActionListener(this);
        btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            switch (e.getActionCommand()) {
                case "Send":
                    listener.sendActionPerformed();
                    break;
                case "Cancel":
                    listener.cancelActionPerformed();
                    break;
                default:
                    System.out.println("No action found");
            }
        }

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public JButton getBtnPost() {
        return btnPost;
    }

    public ButtonGroup getBtnGroup() {
        return btnGroup;
    }

    public List getCurrentDeliveryList() {
        return currentDeliveryList;
    }

    public List getListDeliveryQueue() {
        return listDeliveryQueue;
    }


}

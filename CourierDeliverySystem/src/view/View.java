package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

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
    private JPanel uiPanel;
    private JLabel lblStart, lblLocations, lblWaiting, lblDelivering;
    private boolean showPostmanValue;
    private Postman_View p;

    public ButtonGroup getBtnGroup() {
        return btnGroup;
    }

    public List getCurrentDeliveryList() {
        return currentDeliveryList;
    }

    public View(Model model) {
        super("Courier Delivery"); //Calls JFrame super constructor
        this.model = model;
        this.showPostmanValue = false;
        p = new Postman_View(false, model);
        createGUI();

        setActionListeners();

        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(100, 100);
        setVisible(true);
    }

    public void createGUI() {

        createUIPanel();
        addAllLists();
        addLocationToWaiting();

        lblLocations = new JLabel("Locations");
        lblLocations.setBounds(20, 400, 71, 14);
        getContentPane().add(lblLocations);

        lblWaiting = new JLabel("Waiting for Delivery");
        lblWaiting.setBounds(283, 400, 98, 14);
        getContentPane().add(lblWaiting);

        btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand("Cancel");
        btnCancel.setBounds(297, 506, 84, 23);
        getContentPane().add(btnCancel);

        btnPost = new JButton("Send");
        btnPost.setActionCommand("Send");
        btnPost.setBounds(135, 450, 89, 23);
        getContentPane().add(btnPost);

        JCheckBox chckbxDeliverThroughShortest = new JCheckBox("Deliver Through Shortest Path");
        chckbxDeliverThroughShortest.setBounds(410, 420, 171, 23);
        getContentPane().add(chckbxDeliverThroughShortest);

    }

    private void setActionListeners() {
        btnPost.addActionListener(this);
        btnCancel.addActionListener(this);

    }

    public List getListDeliveryQueue() {
        return listDeliveryQueue;
    }

    //adding both waiting and currently delivering list
    public void addAllLists() {
        listDeliveryQueue = new List();
        listDeliveryQueue.setBounds(271, 420, 130, 80);
        getContentPane().add(listDeliveryQueue);

        currentDeliveryList = new List();
        currentDeliveryList.setBounds(640, 131, 110, 60);
        getContentPane().add(currentDeliveryList);

        lblDelivering = new JLabel("Currently Delivering");
        lblDelivering.setBounds(640, 111, 110, 14);
        getContentPane().add(lblDelivering);
    }

    public ButtonGroup addLocationToWaiting() {

        btnGroup = new ButtonGroup();

        //JPanel to contain the radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));

        //Loop through locations in the model and add them as radio buttons
        model.getLocationList().forEach(m -> {
            if(!"Start".equals(m.getName())){
                JRadioButton radioButton = new JRadioButton(m.getName());
                radioButton.setActionCommand(m.getName()); //Use the name for the action
                btnGroup.add(radioButton);
                radioPanel.add(radioButton);
            }
        });

        //Add the radio panel to the main UI
        radioPanel.setLocation(20, 428);
        radioPanel.setSize(109, 80);
        getContentPane().add(radioPanel);

        return btnGroup;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
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

    //cretaing UI map panel
    public JPanel createUIPanel() {
        uiPanel = new JPanel();
        uiPanel.setBackground(Color.WHITE);
        uiPanel.setBounds(32, 11, 580, 380);
        getContentPane().add(uiPanel);
        uiPanel.setLayout(null);

        //adding roads
        Roads c = new Roads(model);
        uiPanel.add(c);        
        
        //adding postman
        uiPanel.add(p);

        //Loop through and add locations
        model.getLocationList().forEach(m -> {
            JLabel jLabel = new JLabel(m.getName());
            jLabel.setBounds(m.getXPos(), m.getYPos(), m.getXSize(), m.getYSize());
            uiPanel.add(jLabel);
        });  
        return uiPanel;
    }
    
    //make postman visible automatic after three locations added in the list
    public void showPMan(){
        p.setVisible(true);
        
    }
    
        public String test() {
            System.out.println("testing string");
            return "TestTest";
        }
    }

//}



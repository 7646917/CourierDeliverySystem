package swingjframe;

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
	public SwingFrame() {
		setTitle("Courier Delivery");
		getContentPane().setLayout(null);
		
		JRadioButton radioButtonShoeShop = new JRadioButton("Shoe Shop");
		radioButtonShoeShop.setBounds(20, 376, 109, 23);
		radioButtonShoeShop.setActionCommand("Shoe Shop");
		getContentPane().add(radioButtonShoeShop);
		
		JRadioButton radioButtonHouse1 = new JRadioButton("House 1");
		radioButtonHouse1.setBounds(20, 402, 109, 23);
		radioButtonHouse1.setActionCommand("House 1");
		getContentPane().add(radioButtonHouse1);
		
		JRadioButton radioButtonHouse2 = new JRadioButton("House 2");
		radioButtonHouse2.setBounds(20, 428, 109, 23);
		radioButtonHouse2.setActionCommand("House 2");
		getContentPane().add(radioButtonHouse2);
		
		JRadioButton radioButtonAirport = new JRadioButton("Airport");
		radioButtonAirport.setSelected(true);
		radioButtonAirport.setBounds(20, 350, 109, 23);
		radioButtonAirport.setActionCommand("Airport");
		getContentPane().add(radioButtonAirport);
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(radioButtonHouse1);
	    group.add(radioButtonHouse2);
	    group.add(radioButtonAirport);
	    group.add(radioButtonShoeShop);
	    
	    List listDeliveryQueue = new List();
	    listDeliveryQueue.setBounds(271, 358, 130, 80);
	    getContentPane().add(listDeliveryQueue);
		    
		JButton btnPost = new JButton("Send");
		btnPost.setBounds(135, 388, 89, 23);
		
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if(listDeliveryQueue.getItemCount() >=3){
				    	//Don't allow adding. Put feedback here.
				    }else{
						listDeliveryQueue.add(group.getSelection().getActionCommand());	
				    }
			}
		});
		
		getContentPane().add(btnPost);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(32, 11, 501, 316);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(21, 291, 46, 14);
		panel.add(lblStart);
		
		JLabel lblAirport = new JLabel("Airport");
		lblAirport.setBounds(411, 46, 46, 14);
		panel.add(lblAirport);
		
		JLabel lblShoeShop = new JLabel("Shoe Shop");
		lblShoeShop.setBounds(213, 46, 75, 14);
		panel.add(lblShoeShop);
		
		JLabel lblHouse = new JLabel("House 1");
		lblHouse.setBounds(205, 279, 46, 14);
		panel.add(lblHouse);
		
		JLabel lblHouse_1 = new JLabel("House 2");
		lblHouse_1.setBounds(328, 237, 46, 14);
		panel.add(lblHouse_1);
		
	 
	    JLabel lblLocations = new JLabel("Locations");
	    lblLocations.setBounds(20, 338, 71, 14);
	    getContentPane().add(lblLocations);
	    
	    JLabel lblNewLabel = new JLabel("Waiting for Delivery");
	    lblNewLabel.setBounds(283, 338, 98, 14);
	    getContentPane().add(lblNewLabel);
	    JButton btnCancel = new JButton("Cancel");
	    btnCancel.setBounds(297, 444, 84, 23);
	    getContentPane().add(btnCancel);
	    
	    List list = new List();
	    list.setBounds(557, 131, 110, 60);
	    getContentPane().add(list);
	    
	    JLabel lblDelivering = new JLabel("Currently Delivering");
	    lblDelivering.setBounds(557, 111, 110, 14);
	    getContentPane().add(lblDelivering);
	    
	    JCheckBox chckbxDeliverThroughShortest = new JCheckBox("Deliver Through Shortest Path");
	    chckbxDeliverThroughShortest.setBounds(407, 350, 171, 23);
	    getContentPane().add(chckbxDeliverThroughShortest);
	    btnCancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		listDeliveryQueue.removeAll();
	    	}
	    });
	
	}
	
	public static void main(String[] args){
		SwingFrame swingFrame = new SwingFrame();
	    swingFrame.setSize(800, 600);
		swingFrame.setVisible(true);

	}
}

package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Car_Owner_Delete extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldCarOwnerID;
	private Car_Owner_Details carOwnerDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Car_Owner_Delete frame = new Car_Owner_Delete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Car_Owner_Delete(Car_Owner_Details carOwnerDetails) {
		this.carOwnerDetails = carOwnerDetails;
		setTitle("Delete Car Owner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 434, 262);
		contentPane.add(contentPane_1);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelEnterCarOwner = new JLabel("Enter Car Owner ID that you want to be deleted");
		JLabelEnterCarOwner.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelEnterCarOwner.setBounds(34, 33, 345, 26);
		contentPane_1.add(JLabelEnterCarOwner);
		
		JtextFieldCarOwnerID = new JTextField();
		JtextFieldCarOwnerID.setColumns(10);
		JtextFieldCarOwnerID.setBounds(73, 80, 306, 26);
		contentPane_1.add(JtextFieldCarOwnerID);
		
		JButton JbtnCancel = new JButton("Cancel");
		JbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int showConfirmDialog=JOptionPane.showConfirmDialog(null, "Are you sure want to cancel this?","Yes",JOptionPane.OK_CANCEL_OPTION);
				if(showConfirmDialog==0)
				{
					dispose();
				}
				else
				{
					setVisible(true);
				}
			}
		});
		JbtnCancel.setBounds(290, 208, 89, 23);
		contentPane_1.add(JbtnCancel);
		
		JButton JbtnOK = new JButton("OK");
		JbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String id = JtextFieldCarOwnerID.getText().trim();
                 if (CarOwner.isIdValid(id)) {
                     CarOwner carOwner = CarOwner.search(Integer.parseInt(id));
                     if (carOwner != null) {
                         int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Car Owner.\n"+carOwner.toString()+"\nAll the data including Cars and Balance for this car owner will also be deleted  !"
                                 + "\n Are you sure you want to continue ??", "Remove Car Owner", JOptionPane.OK_CANCEL_OPTION);
                         if (showConfirmDialog == 0) {
                             ArrayList<Car> cars = carOwner.getAllCars();
                             System.out.println("Deleting all cars for this car owner !");
                             for (int i = 0; i < cars.size(); i++) {
                                 cars.get(i).delete();
                             }
                             System.out.println("All cars deleted !");
                             carOwner.delete();
                             System.out.println("Car owner deleted !");
                             
                             JOptionPane.showMessageDialog(null, "Record successfully deleted !");
                             dispose();
                             carOwnerDetails.dispose();
							 Car_Owner_Details detailsFrame = new Car_Owner_Details();
							 detailsFrame.setVisible(true);
                         } else {
                            setEnabled(true);
                         }

                     } else {
                         JOptionPane.showMessageDialog(null, "This ID does not exists !");
                     }
                 } else {
                     JOptionPane.showMessageDialog(null, "Enter a valid ID !\n(A valid ID is an integer number greater than 0)");
                 }
			}
		});
		JbtnOK.setBounds(73, 208, 89, 23);
		contentPane_1.add(JbtnOK);
	}

}

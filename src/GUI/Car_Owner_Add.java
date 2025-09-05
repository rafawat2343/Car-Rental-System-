package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Car_Owner_Add extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldNID;
	private JTextField JtextFieldName;
	private JTextField JtextFieldContact;
	private Car_Owner_Details carOwnerDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Car_Owner_Add frame = new Car_Owner_Add();
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
	public Car_Owner_Add(Car_Owner_Details carOwnerDetails) {
		this.carOwnerDetails = carOwnerDetails;
		setTitle("Add Car Owner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 462, 311);
		contentPane.add(contentPane_1);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelEnterNID = new JLabel("Enter NID(without dashes)");
		JLabelEnterNID.setBounds(25, 44, 149, 14);
		contentPane_1.add(JLabelEnterNID);
		
		JLabel JLabelEnterName = new JLabel("Enter Name");
		JLabelEnterName.setBounds(25, 87, 149, 14);
		contentPane_1.add(JLabelEnterName);
		
		JLabel JLabelEnterContact = new JLabel("Enter Contact no.");
		JLabelEnterContact.setBounds(25, 133, 149, 14);
		contentPane_1.add(JLabelEnterContact);
		
		JtextFieldNID = new JTextField();
		JtextFieldNID.setColumns(10);
		JtextFieldNID.setBounds(184, 41, 236, 20);
		contentPane_1.add(JtextFieldNID);
		
		JtextFieldName = new JTextField();
		JtextFieldName.setColumns(10);
		JtextFieldName.setBounds(184, 84, 236, 20);
		contentPane_1.add(JtextFieldName);
		
		JtextFieldContact = new JTextField();
		JtextFieldContact.setColumns(10);
		JtextFieldContact.setBounds(184, 130, 236, 20);
		contentPane_1.add(JtextFieldContact);
		
		JButton JbtnAdd = new JButton("Add");
		JbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NID = JtextFieldNID.getText().trim();
				String name = JtextFieldName.getText().trim();
				String contact = JtextFieldContact.getText().trim();
				
				if(CarOwner.isNIDValid(NID))
				{
					CarOwner carOwner =CarOwner.searchByNID(NID);
					if(carOwner==null)
					{
						if(CarOwner.isNameVald(name))
						{
							if(CarOwner.isContactNoValid(contact))
							{
								new CarOwner(0,NID,name,contact,0).add();
								JOptionPane.showMessageDialog(null, "Car Owner added successfully !");
								dispose();
								carOwnerDetails.dispose();
								Car_Owner_Details detailsFrame = new Car_Owner_Details();
								detailsFrame.setVisible(true);
								
							}
							else {
                                JOptionPane.showMessageDialog(null, "Invalid contact no. !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Name !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This NID is already registered !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid NID");
                }	
				
			}
		});
		JbtnAdd.setBounds(88, 237, 89, 23);
		contentPane_1.add(JbtnAdd);
		
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
		JbtnCancel.setBounds(285, 237, 89, 23);
		contentPane_1.add(JbtnCancel);
	}

}

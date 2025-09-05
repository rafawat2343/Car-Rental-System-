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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Car_Owner_Update extends JFrame {

	private JPanel contentPane;
	private static String id;
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
					Car_Owner_Update frame = new Car_Owner_Update(id, carOwnerDetails);
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
	public Car_Owner_Update(String id , Car_Owner_Details carOwnerDetails) {
		this.id=id;
		this.carOwnerDetails = carOwnerDetails;
		setTitle("Update Car Owner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 435, 217);
		contentPane.add(contentPane_1);
		
		JPanel contentPane_1_1 = new JPanel();
		contentPane_1_1.setLayout(null);
		contentPane_1_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1_1.setBounds(0, 0, 462, 297);
		contentPane_1.add(contentPane_1_1);
		
		JLabel JLabelEnterNID = new JLabel("Enter NID(without dashes)");
		JLabelEnterNID.setBounds(25, 44, 149, 14);
		contentPane_1_1.add(JLabelEnterNID);
		
		JLabel JLabelEnterName = new JLabel("Enter Name");
		JLabelEnterName.setBounds(25, 87, 149, 14);
		contentPane_1_1.add(JLabelEnterName);
		
		JLabel JLabelEnterContact = new JLabel("Enter Contact no.");
		JLabelEnterContact.setBounds(25, 133, 149, 14);
		contentPane_1_1.add(JLabelEnterContact);
		
		CarOwner co =CarOwner.search(Integer.parseInt(Car_Owner_Update.id));
		co.setId(Integer.parseInt(Car_Owner_Update.id));
		
		JtextFieldNID = new JTextField(co.getNID());
		JtextFieldNID.setColumns(10);
		JtextFieldNID.setBounds(184, 41, 236, 20);
		contentPane_1_1.add(JtextFieldNID);
		
		JtextFieldName = new JTextField(co.getName());
		JtextFieldName.setColumns(10);
		JtextFieldName.setBounds(184, 84, 236, 20);
		contentPane_1_1.add(JtextFieldName);
		
		JtextFieldContact = new JTextField(co.getContact_no());
		JtextFieldContact.setColumns(10);
		JtextFieldContact.setBounds(184, 130, 236, 20);
		contentPane_1_1.add(JtextFieldContact);
		
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
		JbtnCancel.setBounds(309, 228, 89, 23);
		contentPane.add(JbtnCancel);
		
		JButton JbtnUpdate = new JButton("Update");
		JbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NID = JtextFieldNID.getText().trim();
				String name = JtextFieldName.getText().trim();
				String contactNo = JtextFieldContact.getText().trim();
				if(!NID.isEmpty())
				{
					if(CarOwner.isNIDValid(NID))
					{
						if(co!=null)
						{
							if(NID.equals(co.getNID()))
							{
								System.out.println("No change in NID");
							}
							else
							{
								NID=null;
								JOptionPane.showMessageDialog(null, "NID is already registered!!!");
							}
						}
					}
					else
					{
						NID=null;
						JOptionPane.showMessageDialog(null, "Invalid NID!!");
					}
				}
				else
				{
					NID=null;
					JOptionPane.showMessageDialog(null, "Enter NID!!");
				}
				
				if (!name.isEmpty()) {
                    if (CarOwner.isNameVald(name)) {
                    	System.out.println("Valid Name!!");
                    } else {
                        name = null;
                        JOptionPane.showMessageDialog(null,"Invalid Name !");
                    }
                } else {
                    name = null;
                    JOptionPane.showMessageDialog(null,"Enter Name !");
                }
				
				if (!contactNo.isEmpty()) {
                    if (CarOwner.isContactNoValid(contactNo)) {
                    	System.out.println("Valid Contact no!!");
                    } else {
                        contactNo = null;
                        JOptionPane.showMessageDialog(null,"Invalid Contact Number!");
                    }
                } else {
                    contactNo = null;
                    JOptionPane.showMessageDialog(null,"Enter Contact Number !");
                }
				
				if (NID != null && name != null && contactNo != null) {
                    CarOwner carOwner = new CarOwner( co.getId(),NID, name, contactNo,co.getBalance());
                    System.out.println(carOwner.toString());
                    carOwner.update();
                    JOptionPane.showMessageDialog(null, "Record Successfully Updated !");
                    dispose();
                    carOwnerDetails.dispose();
					Car_Owner_Details detailsFrame = new Car_Owner_Details();
					detailsFrame.setVisible(true);
                }
				
			}
		});
		JbtnUpdate.setBounds(90, 228, 89, 23);
		contentPane.add(JbtnUpdate);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
	}
}

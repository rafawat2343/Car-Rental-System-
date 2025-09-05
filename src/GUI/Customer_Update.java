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
import java.awt.BorderLayout;

public class Customer_Update extends JFrame {
	private JTextField JtextFieldNID;
	private JTextField JtextFieldName;
	private JTextField JtextFieldContact;
	private static String id;
	private Customer_Details customerDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Update frame = new Customer_Update();
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
	public Customer_Update(String id , Customer_Details customerDetails) {
		this.id=id;
		this.customerDetails= customerDetails;
		setTitle("Update Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 436);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		JLabel JLabelEnterNID = new JLabel("Enter NID(without dashes)");
		JLabelEnterNID.setBounds(25, 44, 149, 14);
		contentPane.add(JLabelEnterNID);
		
		JLabel JLabelEnterName = new JLabel("Enter Name");
		JLabelEnterName.setBounds(25, 87, 149, 14);
		contentPane.add(JLabelEnterName);
		
		JLabel JLabelEnterContact = new JLabel("Enter Contact no.");
		JLabelEnterContact.setBounds(25, 133, 149, 14);
		contentPane.add(JLabelEnterContact);
		
		Customer c =Customer.search(Integer.parseInt(Customer_Update.id));
		c.setId(Integer.parseInt(Customer_Update.id));
		
		JtextFieldNID = new JTextField(c.getNID());
		JtextFieldNID.setColumns(10);
		JtextFieldNID.setBounds(184, 41, 236, 20);
		contentPane.add(JtextFieldNID);
		
		JtextFieldName = new JTextField(c.getName());
		JtextFieldName.setColumns(10);
		JtextFieldName.setBounds(184, 84, 236, 20);
		contentPane.add(JtextFieldName);
		
		JtextFieldContact = new JTextField(c.getContact_no());
		JtextFieldContact.setColumns(10);
		JtextFieldContact.setBounds(184, 130, 236, 20);
		contentPane.add(JtextFieldContact);
		
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
		contentPane.add(JbtnCancel);
		
		JButton JbtnUpdate = new JButton("Update");
		JbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NID = JtextFieldNID.getText().trim();
				String name = JtextFieldName.getText().trim();
				String contactNo = JtextFieldContact.getText().trim();
				if(!NID.isEmpty())
				{
					if(Customer.isNIDValid(NID))
					{
						if(c!=null)
						{
							if(NID.equals(c.getNID()))
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
                    if (Customer.isNameVald(name)) {
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
                    if (Customer.isContactNoValid(contactNo)) {
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
					Customer customer = new Customer( c.getId(),NID, name, contactNo,c.getBill());
                    System.out.println(customer.toString());
                    customer.update();
                    JOptionPane.showMessageDialog(null, "Record Successfully Updated !");
                    dispose();
                    customerDetails.dispose();
                    Customer_Details detailsFrame = new Customer_Details();
                    detailsFrame.setVisible(true);
                }
				
			}
		});
		JbtnUpdate.setBounds(98, 237, 89, 23);
		contentPane.add(JbtnUpdate);
	}
}

package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer_Add extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldEnterNID;
	private JTextField JtextFieldEnterName;
	private JTextField JtextFieldEnterContact;
	private Customer_Details customerDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Add frame = new Customer_Add();
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
	public Customer_Add(Customer_Details customerDetails) {
		this.customerDetails= customerDetails;
		setTitle("Add Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelEnterNID = new JLabel("Enter NID(without dashes)");
		JLabelEnterNID.setBounds(25, 44, 149, 14);
		contentPane.add(JLabelEnterNID);
		
		JLabel JLabelEnterName = new JLabel("Enter Name");
		JLabelEnterName.setBounds(25, 87, 149, 14);
		contentPane.add(JLabelEnterName);
		
		JLabel JLabelEnterContact = new JLabel("Enter Contact no.");
		JLabelEnterContact.setBounds(25, 133, 149, 14);
		contentPane.add(JLabelEnterContact);
		
		JtextFieldEnterNID = new JTextField();
		JtextFieldEnterNID.setBounds(184, 41, 236, 20);
		contentPane.add(JtextFieldEnterNID);
		JtextFieldEnterNID.setColumns(10);
		
		JtextFieldEnterName = new JTextField();
		JtextFieldEnterName.setColumns(10);
		JtextFieldEnterName.setBounds(184, 84, 236, 20);
		contentPane.add(JtextFieldEnterName);
		
		JtextFieldEnterContact = new JTextField();
		JtextFieldEnterContact.setColumns(10);
		JtextFieldEnterContact.setBounds(184, 130, 236, 20);
		contentPane.add(JtextFieldEnterContact);
		
		JButton JbtnAdd = new JButton("Add");
		JbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NID = JtextFieldEnterNID.getText().trim();
                String name =JtextFieldEnterName.getText().trim();
                String contact = JtextFieldEnterContact.getText().trim();
                
                if (Customer.isNIDValid(NID)) {
                    Customer customer = Customer.searchByNID(NID);
                    if (customer == null) {
                        if (Customer.isNameVald(name)) {
                            if (Customer.isContactNoValid(contact)) {
                                new Customer(0, NID, name, contact,0).add(); 
                                JOptionPane.showMessageDialog(null, "Customer added successfully !");
                                dispose();
                                customerDetails.dispose();
                                Customer_Details detailsFrame = new Customer_Details();
                                detailsFrame.setVisible(true);
                            } else {
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
		contentPane.add(JbtnAdd);
		
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
	}
}

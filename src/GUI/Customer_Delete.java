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

public class Customer_Delete extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldEnterID;
	private Customer_Details customerDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Delete frame = new Customer_Delete();
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
	public Customer_Delete(Customer_Details customerDetails) {
		this.customerDetails= customerDetails;
		setTitle("Delete Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JlabelCustomerID = new JLabel("Enter Customer ID that you want to be deleted");
		JlabelCustomerID.setHorizontalAlignment(SwingConstants.CENTER);
		JlabelCustomerID.setBounds(34, 33, 345, 26);
		contentPane.add(JlabelCustomerID);
		
		JtextFieldEnterID = new JTextField();
		JtextFieldEnterID.setBounds(73, 70, 306, 26);
		contentPane.add(JtextFieldEnterID);
		JtextFieldEnterID.setColumns(10);
		
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
		contentPane.add(JbtnCancel);
		
		JButton JbtnOK = new JButton("OK");
		JbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JtextFieldEnterID.getText().trim();
                if (Customer.isIdValid(id)) {
                    Customer customer = Customer.search(Integer.parseInt(id));
                    if (customer != null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Customer.\n"
                                + customer.toString() + " \nAll the data including Booked Cars and Balance for this Customer will also be deleted  !"
                                + "\n Are you sure you want to continue ??", "Remove Customer", JOptionPane.OK_CANCEL_OPTION);
                        if (showConfirmDialog == 0) {
                            ArrayList<Booking> bookings = Booking.view();
                            for (int i = 0; i < bookings.size(); i++) {
                                if (customer.getId() == bookings.get(i).getCustomer().getId()) {
                                    bookings.get(i).delete();
                                }
                            }
                         
                            customer.delete();

                            System.out.println("Customer deleted !");
                            JOptionPane.showMessageDialog(null, "Record successfully deleted !");
                            dispose();
                            customerDetails.dispose();
                            Customer_Details detailsFrame = new Customer_Details();
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
		contentPane.add(JbtnOK);
	}
}

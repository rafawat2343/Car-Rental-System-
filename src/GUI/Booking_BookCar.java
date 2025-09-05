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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Booking_BookCar extends JFrame {

	private JPanel contentPane;
	private static JTextField JtextFieldCarID;
	private static JTextField JtextFieldCustomerID;
	private Booking_Details bookingDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking_BookCar frame = new Booking_BookCar();
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
	public Booking_BookCar(Booking_Details bookingDetails) {
		this.bookingDetails = bookingDetails;
		setTitle("Book Car");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelEnterCarID = new JLabel("Enter Car ID to be booked");
		JLabelEnterCarID.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelEnterCarID.setBounds(43, 28, 191, 22);
		contentPane.add(JLabelEnterCarID);
		
		JLabel JLabelEnterCustomerID = new JLabel("Enter Customer ID");
		JLabelEnterCustomerID.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelEnterCustomerID.setBounds(43, 105, 191, 29);
		contentPane.add(JLabelEnterCustomerID);
		
		JtextFieldCarID = new JTextField();
		JtextFieldCarID.setBounds(43, 62, 197, 20);
		contentPane.add(JtextFieldCarID);
		JtextFieldCarID.setColumns(10);
		
		JtextFieldCustomerID = new JTextField();
		JtextFieldCustomerID.setColumns(10);
		JtextFieldCustomerID.setBounds(43, 145, 197, 20);
		contentPane.add(JtextFieldCustomerID);
		
		JButton JbtnBook = new JButton("Book");
		JbtnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CarID = JtextFieldCarID.getText().trim();
				String CustomerID =JtextFieldCustomerID.getText().toString();
				
				if(!CarID.isEmpty()&&!CustomerID.isEmpty())
				{
					if(Integer.parseInt(CarID)>0 && Integer.parseInt(CustomerID)>0)
					{
						Car car = Car.search(Integer.parseInt(CarID));
						Customer customer= Customer.search(Integer.parseInt(CustomerID));
						if(car!=null && customer!=null)
						{
							if(car.isRented())
							{
								CarID=null;
								CustomerID=null;
								JOptionPane.showMessageDialog(null, "The Car is rented !!!");
							}
							else {
							setEnabled(false);
							int showConfirmDialog = JOptionPane.showConfirmDialog(null,
		                            "You are about to Book the Car: \n" + car.toString() + "\n against the Customer: \n"
		                            + customer.toString() + "\n Are you sure you want to continue??",
		                            "Book Confirmation", JOptionPane.OK_CANCEL_OPTION);
							
							if(showConfirmDialog==0)
							{
								Booking booking = new Booking(0, customer, car, System.currentTimeMillis(), 0);
		                        booking.add();
		                        dispose();
		                        bookingDetails.dispose();
		                        Booking_Details detailsFrame = new Booking_Details();
		                        detailsFrame.setVisible(true);
		                        
							}
							}
						}
						else
						{
							CarID=null;
							CustomerID = null;
							JOptionPane.showMessageDialog(null, "Car ID and Customer ID does not exists !");
						}
					}
					else
					{
						CarID=null;
						CustomerID = null;
					}
				}
				else
				{
					CarID=null;
					CustomerID = null;
				}
			}
		});
		JbtnBook.setBounds(115, 234, 89, 23);
		contentPane.add(JbtnBook);
		
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
		JbtnCancel.setBounds(247, 234, 89, 23);
		contentPane.add(JbtnCancel);
		
		JLabel JLabelOR = new JLabel("Or");
		JLabelOR.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLabelOR.setBounds(251, 65, 46, 14);
		contentPane.add(JLabelOR);
		
		JLabel JLabelOR_1 = new JLabel("Or");
		JLabelOR_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLabelOR_1.setBounds(251, 148, 46, 14);
		contentPane.add(JLabelOR_1);
		
		JButton JbtnSelectCar = new JButton("Select Car");
		JbtnSelectCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cars_Database carDatabase= new Cars_Database();
				carDatabase.setVisible(true);
			}
		});
		JbtnSelectCar.setBounds(295, 61, 130, 23);
		contentPane.add(JbtnSelectCar);
		
		JButton JbtnSelectCustomer = new JButton("Select Customer");
		JbtnSelectCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer_Database customerDatabase= new Customer_Database();
				customerDatabase.setVisible(true);
			}
		});
		JbtnSelectCustomer.setBounds(295, 144, 130, 23);
		contentPane.add(JbtnSelectCustomer);
	}
	
	public static void setCarID(String SelectedCarID)
	{
		JtextFieldCarID.setText(SelectedCarID);
	}
	public static void setCustomerID(String SelectedCustomerID)
	{
		JtextFieldCustomerID.setText(SelectedCustomerID);
	}
}

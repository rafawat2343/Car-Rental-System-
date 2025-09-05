package GUI;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

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

public class Booking_unbookCar extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldUnbook;
	private Booking_Details bookingDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking_unbookCar frame = new Booking_unbookCar();
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
	public Booking_unbookCar(Booking_Details bookingDetails) {
		this.bookingDetails = bookingDetails;
		setTitle("Unbook Car");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelUnbook = new JLabel("Enter Car ID to be unbooked");
		JLabelUnbook.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelUnbook.setBounds(98, 40, 224, 29);
		contentPane.add(JLabelUnbook);
		
		JtextFieldUnbook = new JTextField();
		JtextFieldUnbook.setBounds(98, 80, 224, 20);
		contentPane.add(JtextFieldUnbook);
		JtextFieldUnbook.setColumns(10);
		
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
		JbtnCancel.setBounds(281, 138, 89, 23);
		contentPane.add(JbtnCancel);
		
		JButton JbtnUnbook = new JButton("Unbook");
		JbtnUnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CarID = JtextFieldUnbook.getText().trim();
				{
					if(!CarID.isEmpty())
					{
						if(Integer.parseInt(CarID)>0)
						{
							Car car = Car.search(Integer.parseInt(CarID));
							if(car!=null)
							{
								if(!car.isRented())
								{
									car=null;
									JOptionPane.showMessageDialog(null, "This car is not booked !");
								}
								else {
								setEnabled(false);
								int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to UnBook this Car\n" + car.toString()
	                            + "\n Are you sure you want to continue ??", "UnBook Confirmation", OK_CANCEL_OPTION);
								if(showConfirmDialog==0)
								{
									 ArrayList<Booking> booking = Booking.searchByCarID(Integer.parseInt(CarID));
				                        Booking last = booking.get((booking.size() - 1));
				                        last.setReturnTime(System.currentTimeMillis());
				                        last.update();
				                        
				                        double CarOwnerBill = last.calculateBill()*.8; 
				                        double StaffBill = last.calculateBill()*.2;
				                        
				                        CarOwner carOwner = last.getCar().getCarOwner();
				                        carOwner.setBalance((carOwner.getBalance() + CarOwnerBill));
				                        carOwner.update();
				                        
				                        Staff staff= last.getCar().getStaff();
				                        staff.setSalary((staff.getSalary()+StaffBill));
				                        staff.update();

				                        Customer customer = last.getCustomer();
				                        customer.setBill(customer.getBill()+last.calculateBill());
				                        customer.update();
				                        
				                        bookingDetails.dispose();
				                        Booking_Details detailsFrame = new Booking_Details();
				                        detailsFrame.setVisible(true);
								}
								else
								{
									setEnabled(true);
								}
							  }
							}
							else
							{
								CarID=null;
								JOptionPane.showMessageDialog(null, "Car ID does not exists !");
							}
						}
						else
						{
							CarID=null;						
						}
					}
					else
					{
						CarID=null;
					}
				}
			}
		});
		JbtnUnbook.setBounds(59, 138, 89, 23);
		contentPane.add(JbtnUnbook);
	}
}

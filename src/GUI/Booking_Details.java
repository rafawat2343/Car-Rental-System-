package GUI;

import backendCode.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

import java.awt.Color;
import javax.swing.UIManager;

public class Booking_Details extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldRegNo;
	private JTextField JtextFieldCustomerID;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking_Details frame = new Booking_Details();
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
	public Booking_Details() {
		setTitle("Booking Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton JbtnSearchByCarREgNo = new JButton("Search by Car RegNo");
		JbtnSearchByCarREgNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String carRegNo = JtextFieldRegNo.getText().trim();
                if (!carRegNo.isEmpty()) {
                    if (Car.isRegNoValid(carRegNo)) {
                        Car car = Car.searchByRegNo(carRegNo);
                        if (car != null) {
                            ArrayList<Booking> bookings = Booking.searchByCarRegNo(carRegNo);
                            if (!bookings.isEmpty()) {
                                JOptionPane.showMessageDialog(null, bookings.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "This Car is not booked yet !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Registeration no. not found !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Registeration no !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter Car Registeration No first !");
                }
                JtextFieldCustomerID .setText("");
            }
		});
		JbtnSearchByCarREgNo.setBounds(33, 11, 185, 23);
		contentPane.add(JbtnSearchByCarREgNo);
		
		JButton JbtnSearchByCustomerId = new JButton("Search by Customer ID");
		JbtnSearchByCustomerId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   String customerID = JtextFieldCustomerID.getText().trim();
                   if (!customerID.isEmpty()) {
                       if (Customer.isIdValid(customerID)) {
                           Customer customer = Customer.search(Integer.parseInt(customerID));
                           if (customer != null) {
                               ArrayList<Booking> bookings = Booking.searchByCustomerID(Integer.parseInt(customerID));
                               if (!bookings.isEmpty()) {
                                   JOptionPane.showMessageDialog(null, bookings.toString());
                               } else {
                                   JOptionPane.showMessageDialog(null, "This Customer has not booked any cars yet !");
                               }
                           } else {
                               JOptionPane.showMessageDialog(null, "Customer ID not found !");
                           }
                       } else {
                           JOptionPane.showMessageDialog(null, "Invalid Customer ID !");
                       }
                   } else {
                       JOptionPane.showMessageDialog(null, "Enter Customer ID first !");
                   }
                   JtextFieldCustomerID.setText("");
			}
		});
		JbtnSearchByCustomerId.setBounds(472, 11, 192, 23);
		contentPane.add(JbtnSearchByCustomerId);
		
		JtextFieldRegNo = new JTextField();
		JtextFieldRegNo.setBounds(228, 12, 169, 20);
		contentPane.add(JtextFieldRegNo);
		JtextFieldRegNo.setColumns(10);
		
		JtextFieldCustomerID = new JTextField();
		JtextFieldCustomerID.setColumns(10);
		JtextFieldCustomerID.setBounds(674, 14, 169, 20);
		contentPane.add(JtextFieldCustomerID);
		
		model =new DefaultTableModel();
		Object[] column = {"Sr#", "ID", "Customer ID+Name", "Car ID+Name", "Rent Time", "Return Time"};
		model.setColumnIdentifiers(column);
		
		 ArrayList<Booking> Booking_objects = Booking.view();
	        for (int i = 0; i < Booking_objects.size(); i++) {
	            int ID = Booking_objects.get(i).getId();
	            String customer_ID_Name = Booking_objects.get(i).getCustomer().getId()
	                    + ": " + Booking_objects.get(i).getCustomer().getName();
	            String carName = Booking_objects.get(i).getCar().getName();
	            String carID = Booking_objects.get(i).getCar().getId()+"";
	            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a dd-MM-yyyy");
	            Date rentime = new Date(Booking_objects.get(i).getRentTime());
	            String rentTime = dateFormat.format(rentime);

	            long returnTime_ = Booking_objects.get(i).getReturnTime();
	            String returnTime;
	            if (returnTime_ != 0) {
	                Date returntime = new Date(returnTime_);
	                returnTime = dateFormat.format(returntime);
	            } else {
	                returnTime = "Not returned yet !";
	            }

	            String[] one_s_Record = {((i + 1) + ""), "" + ID, customer_ID_Name, carID+": "+carName, rentTime, returnTime};
	            model.addRow(one_s_Record);
	        }
		
		JButton JbtnBook = new JButton("Book");
		JbtnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (!Booking.getUnbookedCars().isEmpty()) {
					 Booking_BookCar bookCar = new Booking_BookCar(Booking_Details.this);
					 bookCar.setVisible(true);
                 } else {
                     JOptionPane.showMessageDialog(null, "No UnBooked Cars are available !");
                 }
				
			}
		});
		JbtnBook.setBounds(33, 407, 89, 23);
		contentPane.add(JbtnBook);
		
		JButton JbtnUnbook = new JButton("Unbook");
		JbtnUnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedCarID_CarName =(String) table.getValueAt(selectedRow, 3);
					String[] selectedCarID = selectedCarID_CarName.split("[:]");		
					Car car = Car.search(Integer.parseInt(selectedCarID[0]));
					if(car!=null)
					{
						if(!car.isRented())
						{
							car=null;
							JOptionPane.showMessageDialog(null, "This car is not booked !");
						}
						else {
						int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to UnBook this Car:\n\n" + car.toString()
                        + "\n Are you sure you want to continue ??", "UnBook Confirmation", OK_CANCEL_OPTION);
						if(showConfirmDialog==0)
						{
							 ArrayList<Booking> booking = Booking.searchByCarID(Integer.parseInt(selectedCarID[0]));
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
		                        
		                        dispose();
		                        Booking_Details detailsFrame = new Booking_Details();
		                        detailsFrame.setVisible(true);
						}
						
					  }
					}
					else
					{
						selectedCarID[0]=null;
						JOptionPane.showMessageDialog(null, "Car ID does not exists !");
					}
				}
				else
				{
					if (!Booking.getBookedCars().isEmpty()) {
						Booking_unbookCar unbookCar = new Booking_unbookCar(Booking_Details.this);
						unbookCar.setVisible(true);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No Booked Cars found !");
	                }
				}
				
			}
		});
		JbtnUnbook.setBounds(160, 407, 89, 23);
		contentPane.add(JbtnUnbook);
		
		JButton JbtnBack = new JButton("Back");
		JbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m =new MainMenu();
				m.show();
				dispose();
			}
		});
		JbtnBack.setBounds(731, 407, 89, 23);
		contentPane.add(JbtnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 887, 337);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		table.setBackground(UIManager.getColor("Table.selectionBackground"));
		scrollPane.setViewportView(table);
	}
}

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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Customer_Database extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldSearchName;
	private JTextField JtextFieldSearchID;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Details frame = new Customer_Details();
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
	public Customer_Database() {
		setTitle("Select Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 818, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton JbtnSearchName = new JButton("Search Name");
		JbtnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JtextFieldSearchName.getText().trim();
                if (!name.isEmpty()) {
                    if (Customer.isNameVald(name)) {
                        ArrayList<Customer> customerArrayList = Customer.search(name);
                        String record = "";

                        if (!customerArrayList.isEmpty()) {
                            for (int i = 0; i < customerArrayList.size(); i++) {
                                record =record+ customerArrayList.get(i).toString() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, record);
                            JtextFieldSearchName.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required person not found");
                            JtextFieldSearchName.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Name !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Name first !");
                }
			}
		});
		JbtnSearchName.setBounds(48, 11, 143, 23);
		contentPane.add(JbtnSearchName);
		
		JtextFieldSearchName = new JTextField();
		JtextFieldSearchName.setColumns(10);
		JtextFieldSearchName.setBounds(209, 12, 169, 20);
		contentPane.add(JtextFieldSearchName);
		
		JButton JbtnSearchId = new JButton("Search ID");
		JbtnSearchId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JtextFieldSearchID.getText().trim();
                if (!id.isEmpty()) {
                    if (Customer.isIdValid(id)) {
                    	Customer co = Customer.search(Integer.parseInt(id));
                        if (co != null) {
                            JOptionPane.showMessageDialog(null, co.toString());
                            JtextFieldSearchID.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required person not found");
                            JtextFieldSearchID.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter ID first !");
                }
			}
		});
		JbtnSearchId.setBounds(400, 11, 143, 23);
		contentPane.add(JbtnSearchId);
		
		JtextFieldSearchID = new JTextField();
		JtextFieldSearchID.setColumns(10);
		JtextFieldSearchID.setBounds(567, 12, 169, 20);
		contentPane.add(JtextFieldSearchID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 782, 365);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
		Object[] column = {"Sr#", "ID", "NID", "Name", "Contact Number", "Car Rented", "Bill"};
		model.setColumnIdentifiers(column);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		ArrayList<Customer> Customer_objects = Customer.view();
        for (int i = 0; i < Customer_objects.size(); i++) {

            int ID = Customer_objects.get(i).getId();
            String NID = Customer_objects.get(i).getNID();
            String Name = Customer_objects.get(i).getName();
            String ContactNo = Customer_objects.get(i).getContact_no();
            int Bill = Customer_objects.get(i).getBill();
            
            ArrayList<Booking> bookings = Booking.searchByCustomerID(ID);
            String bookedCars = "";
            boolean isAnyCarBooked = false;
            if (!bookings.isEmpty()) {
                for (int j = 0; j < bookings.size(); j++) {
                    if (bookings.get(j).getReturnTime() == 0) {
                        bookedCars =bookedCars+ bookings.get(j).getCar().getId() + ": " + bookings.get(j).getCar().getMaker()+" "+ bookings.get(j).getCar().getName()+ "\n";
                        isAnyCarBooked = true;
                    } else {
                    	isAnyCarBooked = false;
                    }
                }
                
                if(!isAnyCarBooked)
                {
                	bookedCars = "No Cars Booked !";
                }
            } else {
                bookedCars = "No Cars Booked !";
            }
            
            String[] one_s_Record = {(i + 1) + "", "" + ID, NID, Name, ContactNo, bookedCars, Bill + ""};
           model.addRow(one_s_Record);
        }
		
		JButton JbtnCancel = new JButton("Cancel");
		JbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JbtnCancel.setBounds(679, 488, 89, 23);
		contentPane.add(JbtnCancel);
		
		JButton JbtnSelect = new JButton("Select");
		JbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow= table.getSelectedRow();
					String selectedCustomerID= (String) table.getValueAt(selectedRow, 1);
					Booking_BookCar.setCustomerID(selectedCustomerID);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a Customer from the list!!!!");
				}
			}
		});
		JbtnSelect.setBounds(48, 488, 89, 23);
		contentPane.add(JbtnSelect);
		
		JLabel JLabelSelectCustomer = new JLabel("Please Select a Customer from the list");
		JLabelSelectCustomer.setForeground(new Color(204, 51, 204));
		JLabelSelectCustomer.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabelSelectCustomer.setBounds(233, 43, 375, 50);
		contentPane.add(JLabelSelectCustomer);
		
		
		
		
	}
}

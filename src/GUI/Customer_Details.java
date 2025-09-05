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

public class Customer_Details extends JFrame {

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
	public Customer_Details() {
		setTitle("Customer Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 508);
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
		scrollPane.setBounds(10, 43, 782, 365);
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
		
		JButton JbtnClearBill = new JButton("Clear Bill");
		JbtnClearBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Customer> View = Customer.view();
                if (!View.isEmpty()) {
                    ArrayList<String> IDsArray = new ArrayList<>(0);
                    for (int i = 0; i < View.size(); i++) { 
                        if (View.get(i).getBill() != 0) {
                            IDsArray.add(View.get(i).getId() + "");
                        }
                    }

                    Object showInputDialog = JOptionPane.showInputDialog(null, "Select ID for Customer whose bill you want to clear.",
                            "Clear Bill", JOptionPane.PLAIN_MESSAGE, null, IDsArray.toArray(), null);
                    System.out.println(showInputDialog);

                    if (showInputDialog != null) {
                    	Customer customer = Customer.search(Integer.parseInt(showInputDialog + ""));

                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to clear the bill for the following Customer\n"
                                + customer + "\nAre you sure you want to continue ?", "Clear bill Confirmation",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                        if (showConfirmDialog == 0) {
                        	customer.setBill(0);
                        	customer.update();                            
                            JOptionPane.showMessageDialog(null, "Bill Successfully Cleared !");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Customer is registered !");
                }
			}
		});
		JbtnClearBill.setBounds(34, 423, 143, 23);
		contentPane.add(JbtnClearBill);
		
		JButton JbtnAdd = new JButton("Add");
		JbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer_Add customer =new Customer_Add(Customer_Details.this);
				customer.setVisible(true);
			}
		});
		JbtnAdd.setBounds(264, 423, 89, 23);
		contentPane.add(JbtnAdd);
				
		JButton JbtnUpdate = new JButton("Update");
		JbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedID = (String) table.getValueAt(selectedRow, 1);
					Customer_Update customer = new Customer_Update(selectedID , Customer_Details.this);
					customer.setVisible(true);
				}
				else
				{
					String id=JOptionPane.showInputDialog("Enter ID to be updated");
	            	if(!id.isEmpty()) {
		            	if(Customer.isIdValid(id))
		            	{
		            		Customer_Update customer = new Customer_Update(id , Customer_Details.this);
		            		customer.setVisible(true);
		            	}
		            	else
		            	{
		            		JOptionPane.showMessageDialog(null, "Inavlid ID!!!");
		            	}
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "Please enter ID!");
	            	}
				}
				
			}
		});
		JbtnUpdate.setBounds(363, 423, 89, 23);
		contentPane.add(JbtnUpdate);
		
		JButton JbtnDelete = new JButton("Delete");
		JbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedID = (String) table.getValueAt(selectedRow, 1);
					 if (Customer.isIdValid(selectedID)) {
		                	Customer customer = Customer.search(Integer.parseInt(selectedID));
		                    if (customer != null) {
		                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Customer.\n"
		                                + customer.toString() + " \nAll the data including Booked Cars and Salary for this Customer will also be deleted  !"
		                                + "\n Are you sure you want to continue ??", "Delete Customer", JOptionPane.OK_CANCEL_OPTION);
		                        if (showConfirmDialog == 0) {
		                                                     
		                        	customer.delete();                        	
		                        	
		                            System.out.println("Customer deleted !");
		                            JOptionPane.showMessageDialog(null, "Record successfully deleted !");
		                            dispose();
		                            Customer_Details detailsFrame = new Customer_Details();
		                            detailsFrame.setVisible(true);
		                        } else {

		                            setEnabled(true);
		                        }

		                    } else {
		                        JOptionPane.showMessageDialog(null, "This ID does not exists !");
		                    }
					 }
					 else {
		                    JOptionPane.showMessageDialog(null, "Enter a valid ID !\n(A valid ID is an integer number greater than 0)");
		                }
				}
				else
				{
					Customer_Delete customer =new Customer_Delete(Customer_Details.this);
					customer.setVisible(true);
				}
				
			}
		});
		JbtnDelete.setBounds(472, 423, 89, 23);
		contentPane.add(JbtnDelete);
		
		JButton JbtnBack = new JButton("Back");
		JbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m =new MainMenu();
				m.show();
				dispose();
			}
		});
		JbtnBack.setBounds(703, 423, 89, 23);
		contentPane.add(JbtnBack);
		
		
		
		
	}
}

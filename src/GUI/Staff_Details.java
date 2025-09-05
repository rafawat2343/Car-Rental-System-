package GUI;

import backendCode.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Staff_Details extends JFrame {
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
					Staff_Details frame = new Staff_Details();
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
	public Staff_Details() {
		setTitle("Staff Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 498);
		getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 806, 460);
		getContentPane().add(contentPane);
		
		JButton JbtnSearchName = new JButton("Search Name");
		JbtnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JtextFieldSearchName.getText().trim();
                if (!name.isEmpty()) {
                    if (Staff.isNameVald(name)) {
                        ArrayList<Staff> staffArrayList = Staff.search(name);
                        String record = "";

                        if (!staffArrayList.isEmpty()) {
                            for (int i = 0; i < staffArrayList.size(); i++) {
                                record += staffArrayList.get(i).toString() + "\n";
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
		JbtnSearchName.setBounds(54, 11, 143, 23);
		contentPane.add(JbtnSearchName);
		
		JtextFieldSearchName = new JTextField();
		JtextFieldSearchName.setColumns(10);
		JtextFieldSearchName.setBounds(215, 12, 169, 20);
		contentPane.add(JtextFieldSearchName);
		
		JButton JbtnSearchId = new JButton("Search ID");
		JbtnSearchId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String id = JtextFieldSearchID.getText().trim();
                if (!id.isEmpty()) {
                    if (Staff.isIdValid(id)) {
                    	Staff st = Staff.search(Integer.parseInt(id));
                        if (st!= null) {
                            JOptionPane.showMessageDialog(null, st.toString());
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
		JbtnSearchId.setBounds(406, 11, 143, 23);
		contentPane.add(JbtnSearchId);
		
		JtextFieldSearchID = new JTextField();
		JtextFieldSearchID.setColumns(10);
		JtextFieldSearchID.setBounds(573, 12, 169, 20);
		contentPane.add(JtextFieldSearchID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 49, 796, 337);
		contentPane.add(scrollPane);
		
		model=new DefaultTableModel();
		Object[] column = {"Sr#", "ID", "NID", "Name", "Contact Number", "Salary"};
		model.setColumnIdentifiers(column);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		ArrayList<Staff> Staff_Objects = Staff.view();
        for (int i = 0; i <Staff_Objects.size(); i++) {

            int ID = Staff_Objects.get(i).getId();
            String NID = Staff_Objects.get(i).getNID();
            String Name = Staff_Objects.get(i).getName();
            String ContactNo = Staff_Objects.get(i).getContact_no();
            double Salary = Staff_Objects.get(i).getSalary();
            
            
            
           String[] one_s_Record = {(i + 1) + "", "" + ID, NID, Name, ContactNo, Salary + ""};
           model.addRow(one_s_Record);
        }
		
		JButton JbtnClearSalary = new JButton("Clear Salary");
		JbtnClearSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Staff> View = Staff.view();
                if (!View.isEmpty()) {
                    ArrayList<String> IDsArray = new ArrayList<>(0);
                    for (int i = 0; i < View.size(); i++) { 
                        if (View.get(i).getSalary() != 0) {
                            IDsArray.add(View.get(i).getId() + "");
                        }
                    }

                    Object showInputDialog = JOptionPane.showInputDialog(null, "Select ID for Staff whose salary you want to clear.",
                            "Clear Salary", JOptionPane.PLAIN_MESSAGE, null, IDsArray.toArray(), null);
                    System.out.println(showInputDialog);

                    if (showInputDialog != null) {
                        Staff staff = Staff.search(Integer.parseInt(showInputDialog + ""));

                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to clear the salary for the following Staff\n"
                                + staff + "\nAre you sure you want to continue ?", "Clear salary Confirmation",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                        if (showConfirmDialog == 0) {
                        	staff.setSalary(0);
                        	staff.update();                            
                            JOptionPane.showMessageDialog(null, "Salary Successfully Cleared !");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Staff is registered !");
                }
			}
		});
		JbtnClearSalary.setBounds(38, 408, 143, 23);
		contentPane.add(JbtnClearSalary);
		
		JButton JbtnAdd = new JButton("Add");
		JbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_Add staff =new Staff_Add(Staff_Details.this);
				staff.setVisible(true);
			}
		});
		JbtnAdd.setBounds(268, 408, 89, 23);
		contentPane.add(JbtnAdd);
		
		JButton JbtnUpdate = new JButton("Update");
		JbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedID = (String) table.getValueAt(selectedRow, 1);
					Staff_Update staff = new Staff_Update(selectedID , Staff_Details.this);
					staff.setVisible(true);
				}
				else {
					String id=JOptionPane.showInputDialog("Enter ID to be updated");
	            	if(!id.isEmpty()) {
		            	if(Staff.isIdValid(id))
		            	{
		            		Staff_Update staff = new Staff_Update(id , Staff_Details.this);
		            		staff.setVisible(true);
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
		JbtnUpdate.setBounds(367, 408, 89, 23);
		contentPane.add(JbtnUpdate);
		
		JButton JbtnDelete = new JButton("Delete");
		JbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedID = (String) table.getValueAt(selectedRow, 1);
					 if (Staff.isIdValid(selectedID)) {
		                	Staff staff = Staff.search(Integer.parseInt(selectedID));
		                    if (staff != null) {
		                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Staff.\n"
		                                + staff.toString() + " \nAll the data including Booked Cars and Salary for this Staff will also be deleted  !"
		                                + "\n Are you sure you want to continue ??", "Delete Staff", JOptionPane.OK_CANCEL_OPTION);
		                        if (showConfirmDialog == 0) {
		                                                     
		                        	staff.delete();                        	
		                        	
		                            System.out.println("Staff deleted !");
		                            JOptionPane.showMessageDialog(null, "Record successfully deleted !");
		                            dispose();
		                            Staff_Details detailsFrame = new Staff_Details();
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
					Staff_Delete staff =new Staff_Delete(Staff_Details.this);
					staff.setVisible(true);
				}
				
			}
		});
		JbtnDelete.setBounds(476, 408, 89, 23);
		contentPane.add(JbtnDelete);
		
		JButton JbtnBack = new JButton("Back");
		JbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m =new MainMenu();
				m.show();
				dispose();
			}
		});
		JbtnBack.setBounds(707, 408, 89, 23);
		contentPane.add(JbtnBack);
	}
	
}

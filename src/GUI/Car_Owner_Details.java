package GUI;

import backendCode.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Car_Owner_Details extends JFrame {

    private JPanel contentPane;
    private JTextField JtextFieldSearchName;
    private JTextField JtextFieldSearchID;
    private JTable table;
    private DefaultTableModel model;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Car_Owner_Details frame = new Car_Owner_Details();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public Car_Owner_Details() {
        setTitle("Car Owner Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 822, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        model = new DefaultTableModel();
        Object[] column = {"Sr#", "ID", "NID", "Name", "Contact Number", "Car Given for rent", "Balance"};
        model.setColumnIdentifiers(column);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 38, 806, 359);
        contentPane.add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);

        JButton JbtnSearchName = new JButton("Search Name");
        JbtnSearchName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = JtextFieldSearchName.getText().trim();
                if (!name.isEmpty()) {
                    if (CarOwner.isNameVald(name)) {
                        ArrayList<CarOwner> carOwnerArrayList = CarOwner.search(name);
                        String record = "";

                        if (!carOwnerArrayList.isEmpty()) {
                            for (int i = 0; i < carOwnerArrayList.size(); i++) {
                                record =record+ carOwnerArrayList.get(i).toString() + "\n";
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
                    if (CarOwner.isIdValid(id)) {
                        CarOwner co = CarOwner.search(Integer.parseInt(id));
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
        JbtnSearchId.setBounds(406, 11, 143, 23);
        contentPane.add(JbtnSearchId);

        JtextFieldSearchID = new JTextField();
        JtextFieldSearchID.setColumns(10);
        JtextFieldSearchID.setBounds(573, 12, 169, 20);
        contentPane.add(JtextFieldSearchID);

        JButton JbtnClearBalance = new JButton("Clear Balance");
        JbtnClearBalance.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<CarOwner> View = CarOwner.view();
                if (!View.isEmpty()) {
                    ArrayList<String> IDsArray = new ArrayList<>(0);
                    for (int i = 0; i < View.size(); i++) { 
                        if (View.get(i).getBalance() != 0) {
                            IDsArray.add(View.get(i).getId() + "");
                        }
                    }

                    Object showInputDialog = JOptionPane.showInputDialog(null, "Select ID for Car Owner whose balance you want to clear.",
                            "Clear Balance", JOptionPane.PLAIN_MESSAGE, null, IDsArray.toArray(), null);
                    System.out.println(showInputDialog);

                    if (showInputDialog != null) {
                        CarOwner carOwner = CarOwner.search(Integer.parseInt(showInputDialog + ""));

                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to clear the balance for the following Car Owner\n"
                                + carOwner + "\nAre you sure you want to continue ?", "Clear Balance Confirmation",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                        if (showConfirmDialog == 0) {
                            carOwner.setBalance(0);
                            carOwner.update();                            
                            JOptionPane.showMessageDialog(null, "Balance Successfully Cleared !");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Car Owner is registered !");
                }
        	}
        });
        JbtnClearBalance.setBounds(38, 408, 143, 23);
        contentPane.add(JbtnClearBalance);

        JButton JbtnAdd = new JButton("Add");
        JbtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Car_Owner_Add carOwner = new Car_Owner_Add(Car_Owner_Details.this);
                carOwner.setVisible(true);
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
					Car_Owner_Update carOwner = new Car_Owner_Update(selectedID , Car_Owner_Details.this);
					carOwner.setVisible(true);
				}
				else
				{
					String id=JOptionPane.showInputDialog("Enter ID to be updated");
					if(!id.isEmpty()) {
		            	if(CarOwner.isIdValid(id))
		            	{
		            		Car_Owner_Update carOwner = new Car_Owner_Update(id , Car_Owner_Details.this);
		                    carOwner.setVisible(true);
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
					 if (CarOwner.isIdValid(selectedID)) {
						 CarOwner carOwner = CarOwner.search(Integer.parseInt(selectedID));
		                    if (carOwner != null) {
		                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Car Owner.\n"
		                                + carOwner.toString() + " \nAll the data including Booked Cars and Salary for this Car Owner will also be deleted  !"
		                                + "\n Are you sure you want to continue ??", "Delete Car Owner", JOptionPane.OK_CANCEL_OPTION);
		                        if (showConfirmDialog == 0) {
		                                                     
		                        	carOwner.delete();                        	
		                        	
		                            System.out.println("Car Owner deleted !");
		                            JOptionPane.showMessageDialog(null, "Record successfully deleted !");
		                            dispose();
		                            Car_Owner_Details detailsFrame = new Car_Owner_Details();
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
            		 Car_Owner_Delete carOwner = new Car_Owner_Delete(Car_Owner_Details.this);
                     carOwner.setVisible(true);
            	}
               
            }
        });
        JbtnDelete.setBounds(476, 408, 89, 23);
        contentPane.add(JbtnDelete);

        JButton JbtnBack = new JButton("Back");
        JbtnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu m = new MainMenu();
                m.show();
                dispose();
            }
        });
        JbtnBack.setBounds(707, 408, 89, 23);
        contentPane.add(JbtnBack);

        ArrayList<CarOwner> CarOwner_objects = CarOwner.view();
        for (int i = 0; i < CarOwner_objects.size(); i++) {
            int ID = CarOwner_objects.get(i).getId();
            String NID = CarOwner_objects.get(i).getNID();
            String Name = CarOwner_objects.get(i).getName();
            String ContactNo = CarOwner_objects.get(i).getContact_no();
            double Balance = CarOwner_objects.get(i).getBalance();

            String carGivenOnRent = "";
            ArrayList<Car> cars = Car.view();

            for (int j = 0; j < cars.size(); j++) {
                if (cars.get(j).getCarOwner().getId() == ID) {
                    carGivenOnRent = carGivenOnRent+cars.get(j).getId() + ": " + cars.get(j).getName() + "  ";
                }
            }
            if (carGivenOnRent.isEmpty()) {
                carGivenOnRent = "No Cars given for Rent !";
            }

            String[] one_s_Record = {(i + 1) + "", ID + "", NID, Name, ContactNo, carGivenOnRent, Balance + ""};
            model.addRow(one_s_Record);
        }
    }
}

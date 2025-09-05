package GUI;

import backendCode.Car;
import backendCode.CarOwner;
import backendCode.Staff;

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
import java.awt.Color;

public class Cars_Details extends JFrame {

    private JPanel contentPane;
    private JTextField JtextFieldSearchName;
    private JTextField JtextFieldSearchRegNo;
    private JTable table;
    private DefaultTableModel tablemodel;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cars_Details frame = new Cars_Details();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public Cars_Details() {
        setTitle("Cars Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 905, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contentPane_1 = new JPanel();
        contentPane_1.setLayout(null);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBounds(0, 0, 889, 464);
        contentPane.add(contentPane_1);

        JButton JbtnSearchName = new JButton("Search Name");
        JbtnSearchName.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name = JtextFieldSearchName.getText().trim();
                if (!name.isEmpty()) {
                    if (Car.isNameValid(name)) {

                        ArrayList<Car> car = Car.search(name);

                        if (!car.isEmpty()) {
                            JOptionPane.showMessageDialog(null, car.toString());
                            JtextFieldSearchName.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required Car not found");
                            JtextFieldSearchName.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Name !");
                        JtextFieldSearchName.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Car Name first !");
                }
        	}
        });
        JbtnSearchName.setBounds(54, 11, 143, 23);
        contentPane_1.add(JbtnSearchName);

        JtextFieldSearchName = new JTextField();
        JtextFieldSearchName.setColumns(10);
        JtextFieldSearchName.setBounds(215, 12, 169, 20);
        contentPane_1.add(JtextFieldSearchName);

        JButton JbtnSearchRegNo = new JButton("Search Reg No");
        JbtnSearchRegNo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String regNo = JtextFieldSearchRegNo.getText().trim();
                if (!regNo.isEmpty()) {
                    if (Car.isRegNoValid(regNo)) {
                        Car car = Car.searchByRegNo(regNo);
                        if (car != null) {
                            JOptionPane.showMessageDialog(null, car.toString());
                            JtextFieldSearchRegNo.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required Car not found");
                            JtextFieldSearchRegNo.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Reg No.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Car Reg no first !");
                }
        	}
        });
        JbtnSearchRegNo.setBounds(406, 11, 143, 23);
        contentPane_1.add(JbtnSearchRegNo);

        JtextFieldSearchRegNo = new JTextField();
        JtextFieldSearchRegNo.setColumns(10);
        JtextFieldSearchRegNo.setBounds(573, 12, 169, 20);
        contentPane_1.add(JtextFieldSearchRegNo);

        tablemodel = new DefaultTableModel();
        Object[] column = {"Sr#", "ID", "Maker", "Name", "Colour", "Type", "Seats", "Model", "Condition",
                "Reg No.", "Rent/hour", "Car Owner ID+Name", "Staff ID+Name"};
        tablemodel.setColumnIdentifiers(column);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 41, 889, 354);
        contentPane_1.add(scrollPane);

        table = new JTable(tablemodel);
        table.setBackground(Color.PINK);
        scrollPane.setViewportView(table);

        ArrayList<Car> Car_objects = Car.view();
        for (int i = 0; i < Car_objects.size(); i++) {
            int ID = Car_objects.get(i).getId();
            String maker = Car_objects.get(i).getMaker();
            String Name = Car_objects.get(i).getName();
            String color = Car_objects.get(i).getColour();
            String type = Car_objects.get(i).getType();
            int seatingCapacity = Car_objects.get(i).getSeatingCapacity();
            String model = Car_objects.get(i).getModel();
            String condition = Car_objects.get(i).getCondition();
            String regNo = Car_objects.get(i).getRegNo();
            int rentPerHour = Car_objects.get(i).getRentPerHour();
            CarOwner carOwner = Car_objects.get(i).getCarOwner();

            Staff staff = Car_objects.get(i).getStaff();
            String[] one_s_Record = {((i + 1) + ""), "" + ID, maker, Name, color, type, seatingCapacity + "",
                    model, condition, regNo, rentPerHour + "", carOwner.getId() + ": " + carOwner.getName(), staff.getId()+":"+staff.getName()};
            tablemodel.addRow(one_s_Record);
        }

        JButton JbtnAdd = new JButton("Add");
        JbtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cars_Add car = new Cars_Add(Cars_Details.this);
                car.setVisible(true);
            }
        });
        JbtnAdd.setBounds(221, 408, 89, 23);
        contentPane_1.add(JbtnAdd);

        JButton JbtnUpdate = new JButton("Update");
        JbtnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {       
            	if(table.getSelectedRowCount()==1)
            	{
            		int selectedRow = table.getSelectedRow();
            		String selectedID = (String) table.getValueAt(selectedRow, 1);
            		Cars_Update staff = new Cars_Update(selectedID , Cars_Details.this);
            		staff.setVisible(true);
            	}
            	else
            	{
            		String id=JOptionPane.showInputDialog("Enter Car ID to be updated");
                	if(!id.isEmpty()) {
    	            	if(Staff.isIdValid(id))
    	            	{
    	            		Cars_Update staff = new Cars_Update(id , Cars_Details.this);
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
        JbtnUpdate.setBounds(320, 408, 89, 23);
        contentPane_1.add(JbtnUpdate);

        JButton JbtnDelete = new JButton("Delete");
        JbtnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if(table.getSelectedRowCount()==1)
				{
					int selectedRow = table.getSelectedRow();
					String selectedID = (String) table.getValueAt(selectedRow, 1);
					Car car = Car.search(Integer.parseInt(selectedID));
                    if (car != null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove this car \n "
                                + car.toString() + "\n Are you sure you want to continue ??", "Confirmation",
                                JOptionPane.OK_CANCEL_OPTION);
                        if (showConfirmDialog == 0) {
                            car.delete();
                            dispose();
							Cars_Details detailsFrame = new Cars_Details();
							detailsFrame.setVisible(true);
                        }
                        else
                        {
                        	setVisible(true);
                        }
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Car ID not found !");
                    }
				}
            	else
            	{
            		Cars_Delete car = new Cars_Delete(Cars_Details.this);
                    car.setVisible(true);
            	}
                
            }
        });
        JbtnDelete.setBounds(429, 408, 89, 23);
        contentPane_1.add(JbtnDelete);

        JButton JbtnBack = new JButton("Back");
        JbtnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu m = new MainMenu();
                m.show();
                dispose();
            }
        });
        JbtnBack.setBounds(681, 408, 89, 23);
        contentPane_1.add(JbtnBack);
    }
}

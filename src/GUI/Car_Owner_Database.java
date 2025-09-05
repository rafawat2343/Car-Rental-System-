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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Car_Owner_Database extends JFrame {

    private JPanel contentPane;
    private JTextField JtextFieldSearchName;
    private JTextField JtextFieldSearchID;
    private JTable table;
    private DefaultTableModel model;
    private JButton JbtnSelect;
    private JLabel JLabelSelectCarOwner;

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

    public Car_Owner_Database() {
        setTitle("Select Car Owner");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 822, 565);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        model = new DefaultTableModel();
        Object[] column = {"Sr#", "ID", "NID", "Name", "Contact Number", "Car Given for rent", "Balance"};
        model.setColumnIdentifiers(column);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 93, 806, 359);
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

        JButton JbtnBack = new JButton("Cancel");
        JbtnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JbtnBack.setBounds(707, 481, 89, 23);
        contentPane.add(JbtnBack);
        
        JbtnSelect = new JButton("Select");
        JbtnSelect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(table.getSelectedRowCount()==1)
        		{
        			int selectedRow = table.getSelectedRow();
        			String selectedCarOwnerID = (String) table.getValueAt(selectedRow, 1);
        			Cars_Add.setCarOwnerID(selectedCarOwnerID);
        			dispose();        			
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "Please select a Car owner!!!!");
        		}
        	}
        });
        JbtnSelect.setBounds(49, 481, 89, 23);
        contentPane.add(JbtnSelect);
        
        JLabelSelectCarOwner = new JLabel("Please Select a Car Owner from the list");
        JLabelSelectCarOwner.setForeground(new Color(204, 51, 204));
        JLabelSelectCarOwner.setFont(new Font("Tahoma", Font.PLAIN, 21));
        JLabelSelectCarOwner.setBounds(204, 43, 375, 50);
        contentPane.add(JLabelSelectCarOwner);

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

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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Cars_Database extends JFrame {

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

    public Cars_Database() {
        setTitle("Select Car");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 905, 568);
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
        scrollPane.setBounds(0, 99, 889, 354);
        contentPane_1.add(scrollPane);

        table = new JTable(tablemodel);
        table.setBackground(Color.PINK);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("Please Select a Car From the list!!!");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblNewLabel.setForeground(new Color(204, 51, 255));
        lblNewLabel.setBounds(198, 45, 351, 43);
        contentPane_1.add(lblNewLabel);

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
                    model, condition, regNo, rentPerHour + "", carOwner.getId() + ": " + carOwner.getName(),
                    staff.getId() + ": " + staff.getName()};
            tablemodel.addRow(one_s_Record);
        }
        
        
                JButton JbtnCancel = new JButton("Cancel");
                JbtnCancel.setBounds(696, 475, 89, 23);
                contentPane.add(JbtnCancel);
                
                JButton JbtnSelect = new JButton("Select");
                JbtnSelect.setBounds(82, 475, 89, 23);
                contentPane.add(JbtnSelect);
                JbtnSelect.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		if(table.getSelectedRowCount()==1)
                		{
                			int selectedRow= table.getSelectedRow();
                			String selectedCarID= (String)table.getValueAt(selectedRow, 1);
                			Booking_BookCar.setCarID(selectedCarID);
                			dispose();
                		}
                		else
                		{
                			JOptionPane.showMessageDialog(null, "Please select a car!!!");
                		}
                	}
                });
                JbtnCancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
    }
}

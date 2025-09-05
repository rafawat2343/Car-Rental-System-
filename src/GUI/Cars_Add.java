package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cars_Add extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldMaker;
	private JTextField JtextFieldName;
	private JTextField JtextFieldRegNo;
	private static JTextField JtextFieldOwnerID;
	private JTextField JtextFieldRentPerHour;
	private static JTextField JtextFieldStaffID;
	private Cars_Details carsDetails;
	
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cars_Add frame = new Cars_Add();
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
	public Cars_Add(Cars_Details carsDetails) {
		this.carsDetails = carsDetails;
		setTitle("Add Car");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelMaker = new JLabel("Maker");
		JLabelMaker.setBounds(10, 26, 103, 14);
		contentPane.add(JLabelMaker);
		
		JLabel JLabelName = new JLabel("Name");
		JLabelName.setBounds(10, 64, 103, 14);
		contentPane.add(JLabelName);
		
		JLabel JLabelRegNo = new JLabel("Reg no(ABC-0123)");
		JLabelRegNo.setBounds(10, 101, 120, 14);
		contentPane.add(JLabelRegNo);
		
		JLabel JLabelOwnerID = new JLabel("Owner ID");
		JLabelOwnerID.setBounds(10, 135, 120, 14);
		contentPane.add(JLabelOwnerID);
		
		JLabel JLabelRentPerHour = new JLabel("Rent Per Hour(RPH)");
		JLabelRentPerHour.setBounds(10, 174, 120, 14);
		contentPane.add(JLabelRentPerHour);
		
		JtextFieldMaker = new JTextField();
		JtextFieldMaker.setBounds(123, 23, 317, 20);
		contentPane.add(JtextFieldMaker);
		JtextFieldMaker.setColumns(10);
		
		JtextFieldName = new JTextField();
		JtextFieldName.setColumns(10);
		JtextFieldName.setBounds(123, 61, 317, 20);
		contentPane.add(JtextFieldName);
		
		JtextFieldRegNo = new JTextField();
		JtextFieldRegNo.setColumns(10);
		JtextFieldRegNo.setBounds(123, 98, 317, 20);
		contentPane.add(JtextFieldRegNo);
		
		JtextFieldOwnerID = new JTextField();
		JtextFieldOwnerID.setColumns(10);
		JtextFieldOwnerID.setBounds(123, 132, 317, 20);
		contentPane.add(JtextFieldOwnerID);
		
		JtextFieldRentPerHour = new JTextField();
		JtextFieldRentPerHour.setColumns(10);
		JtextFieldRentPerHour.setBounds(123, 171, 317, 20);
		contentPane.add(JtextFieldRentPerHour);
		
		JLabel JLabelModel = new JLabel("Model");
		JLabelModel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelModel.setBounds(10, 268, 46, 14);
		contentPane.add(JLabelModel);
		
		JComboBox JcomboBoxModel = new JComboBox();
		JcomboBoxModel.setBounds(67, 264, 77, 22);
		contentPane.add(JcomboBoxModel);
		int TodaysYear = new Date().getYear() + 1900;
        int noOfYears = TodaysYear - 1949;
        String[] Years = new String[noOfYears];
        for (int i = 0; i < noOfYears; i++) {
            Years[i] = TodaysYear - i + "";
            JcomboBoxModel.addItem(Years[i]);
        }
        
		
		
		JLabel JLabelCarType = new JLabel("Car Type");
		JLabelCarType.setBounds(187, 268, 53, 14);
		contentPane.add(JLabelCarType);
		
		JComboBox JcomboBoxCarType = new JComboBox();
		JcomboBoxCarType.setBounds(250, 264, 132, 22);
		contentPane.add(JcomboBoxCarType);
		
		 JcomboBoxCarType.addItem("Familycar");
		 JcomboBoxCarType.addItem("Comercial");
		 JcomboBoxCarType.addItem("Microcar");
		 JcomboBoxCarType.addItem("Compact car");
		 JcomboBoxCarType.addItem("Mid-size car"); 
		 JcomboBoxCarType.addItem("Microbus");
		 JcomboBoxCarType.addItem("Convertible");
		 JcomboBoxCarType.addItem("Sports cars");
		
		JLabel JLabelSeatingCapacity = new JLabel("Seating Capacity");
		JLabelSeatingCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelSeatingCapacity.setBounds(392, 268, 119, 14);
		contentPane.add(JLabelSeatingCapacity);
		
		JComboBox JcomboBoxSeatingCapacity = new JComboBox();
		JcomboBoxSeatingCapacity.setBounds(521, 264, 46, 22);
		contentPane.add(JcomboBoxSeatingCapacity);
		JcomboBoxSeatingCapacity.addItem("2");
		JcomboBoxSeatingCapacity.addItem("4");
		JcomboBoxSeatingCapacity.addItem("6");
		JcomboBoxSeatingCapacity.addItem("8");
		JcomboBoxSeatingCapacity.addItem("10");
		JcomboBoxSeatingCapacity.addItem("12");
		
		JLabel JLabelColor = new JLabel("Color");
		JLabelColor.setBounds(43, 303, 46, 14);
		contentPane.add(JLabelColor);
		
		JLabel JLabelCondition = new JLabel("Condition");
		JLabelCondition.setBounds(205, 303, 69, 14);
		contentPane.add(JLabelCondition);
		
		JComboBox JcomboBoxColor = new JComboBox();
		JcomboBoxColor.setBounds(102, 299, 93, 22);
		contentPane.add(JcomboBoxColor);
		JcomboBoxColor.addItem("White");
		JcomboBoxColor.addItem("Red");
		JcomboBoxColor.addItem("Silver");
		JcomboBoxColor.addItem("Blue");
		JcomboBoxColor.addItem("Black");
		
		JComboBox JcomboBoxCondition = new JComboBox();
		JcomboBoxCondition.setBounds(284, 299, 93, 22);
		contentPane.add(JcomboBoxCondition);
		
		JcomboBoxCondition.addItem("Excellent");
		JcomboBoxCondition.addItem("Good");
		JcomboBoxCondition.addItem("Average");
		JcomboBoxCondition.addItem("Bad");
		
		
		
		
		JButton JbtnAdd = new JButton("Add");
		JbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maker = JtextFieldMaker.getText().trim();
				String name = JtextFieldName.getText().trim();
				String regNo = JtextFieldRegNo.getText().trim();
				String ownerID = JtextFieldOwnerID.getText().trim();
				String StaffID =JtextFieldStaffID.getText().trim();
				String rentPerHour = JtextFieldRentPerHour.getText().trim();

				if(Car.isRegNoValid(regNo))
				{
					Car car =Car.searchByRegNo(regNo);
					CarOwner carOwner = CarOwner.search(Integer.parseInt(ownerID));
					Staff staff =Staff.search(Integer.parseInt(StaffID));
					if(carOwner!=null && staff!=null) {
						if(car==null)
						{
							if(Car.isNameValid(name))
							{
								
									car = new Car(0, maker, name, JcomboBoxColor.getSelectedItem() + "",
										JcomboBoxCarType.getSelectedItem() + "",
										Integer.parseInt(JcomboBoxSeatingCapacity.getSelectedItem().toString()),
                                        JcomboBoxModel.getSelectedItem() + "",
                                        JcomboBoxCondition.getSelectedItem() + "",
                                        regNo, Integer.parseInt(rentPerHour), carOwner,staff);
									car.add();
									JOptionPane.showMessageDialog(null, "Car added successfully !");
									dispose();
									carsDetails.dispose();
									Cars_Details detailsFrame = new Cars_Details();
									detailsFrame.setVisible(true);
							}
								else {
	                                JOptionPane.showMessageDialog(null, "Invalid Car name !!");
	                            }
	                        } else {
	                            JOptionPane.showMessageDialog(null, "The Car is already added !!");
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Car Owner and Staff do not exist !!");
	                    }
	                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Reg no");
                }
			}
		});
		JbtnAdd.setBounds(437, 299, 89, 23);
		contentPane.add(JbtnAdd);
		
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
		JbtnCancel.setBounds(216, 350, 89, 23);
		contentPane.add(JbtnCancel);
		
		JLabel JLabelStaffID = new JLabel("Staff ID");
		JLabelStaffID.setBounds(10, 202, 120, 14);
		contentPane.add(JLabelStaffID);
		
		JtextFieldStaffID = new JTextField();
		JtextFieldStaffID.setColumns(10);
		JtextFieldStaffID.setBounds(123, 199, 317, 20);
		contentPane.add(JtextFieldStaffID);
		
		JButton JbtnSelectCarOwner = new JButton("Select a Car Owner");
		JbtnSelectCarOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Car_Owner_Database carOwnerDatabase = new Car_Owner_Database();
				carOwnerDatabase.setVisible(true);
			}
		});
		JbtnSelectCarOwner.setBounds(488, 131, 170, 23);
		contentPane.add(JbtnSelectCarOwner);
		
		JButton JbtnSelectStaff = new JButton("Select a Staff");
		JbtnSelectStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_Database staffDatabase = new Staff_Database();
				staffDatabase.setVisible(true);
			}
		});
		JbtnSelectStaff.setBounds(488, 198, 170, 23);
		contentPane.add(JbtnSelectStaff);
		
		JLabel JLabelOr = new JLabel("Or");
		JLabelOr.setBounds(459, 135, 32, 14);
		contentPane.add(JLabelOr);
		
		JLabel JLabelOr_1 = new JLabel("Or");
		JLabelOr_1.setBounds(459, 202, 32, 14);
		contentPane.add(JLabelOr_1);
	}
	public static void setCarOwnerID(String ID)
    {
		JtextFieldOwnerID.setText(ID);
    }
    public static void setStaffID(String ID)
    {
    	JtextFieldStaffID.setText(ID);
    }
}

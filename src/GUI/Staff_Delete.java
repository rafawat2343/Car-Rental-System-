package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backendCode.Booking;
import backendCode.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Staff_Delete extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldStaffID;
	private Staff_Details staffDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_Delete frame = new Staff_Delete();
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
	public Staff_Delete(Staff_Details staffDetails) {
		this.staffDetails = staffDetails;
		setTitle("Delete Staff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 434, 262);
		contentPane.add(contentPane_1);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JlabelEnterStaffID = new JLabel("Enter Staff ID that you want to be deleted");
		JlabelEnterStaffID.setHorizontalAlignment(SwingConstants.CENTER);
		JlabelEnterStaffID.setBounds(34, 33, 345, 26);
		contentPane_1.add(JlabelEnterStaffID);
		
		JtextFieldStaffID = new JTextField();
		JtextFieldStaffID.setColumns(10);
		JtextFieldStaffID.setBounds(73, 70, 306, 26);
		contentPane_1.add(JtextFieldStaffID);
		
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
		JbtnCancel.setBounds(290, 208, 89, 23);
		contentPane_1.add(JbtnCancel);
		
		JButton JbtnOK = new JButton("OK");
		JbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JtextFieldStaffID.getText().trim();
                if (Staff.isIdValid(id)) {
                	Staff staff = Staff.search(Integer.parseInt(id));
                    if (staff != null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove the following Staff.\n"
                                + staff.toString() + " \nAll the data including Booked Cars and Salary for this Staff will also be deleted  !"
                                + "\n Are you sure you want to continue ??", "Delete Staff", JOptionPane.OK_CANCEL_OPTION);
                        if (showConfirmDialog == 0) {
                                                     
                        	staff.delete();                        	
                        	
                            System.out.println("Staff deleted !");
                            JOptionPane.showMessageDialog(null, "Record successfully deleted !");
                            dispose();
                            staffDetails.dispose();
                            Staff_Details detailsFrame = new Staff_Details();
                            detailsFrame.setVisible(true);
                        } else {

                            setEnabled(true);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "This ID does not exists !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a valid ID !\n(A valid ID is an integer number greater than 0)");
                }
			}
		});
		JbtnOK.setBounds(73, 208, 89, 23);
		contentPane_1.add(JbtnOK);
	}
		
}

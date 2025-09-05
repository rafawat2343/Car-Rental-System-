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
import javax.swing.JLabel;

public class Staff_Database_for_Update extends JFrame {
	private JTextField JtextFieldSearchName;
	private JTextField JtextFieldSearchID;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public Staff_Database_for_Update() {
		setTitle("Select Staff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 558);
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
		scrollPane.setBounds(0, 112, 796, 337);
		contentPane.add(scrollPane);
		
		model=new DefaultTableModel();
		Object[] column = {"Sr#", "ID", "NID", "Name", "Contact Number", "Salary"};
		model.setColumnIdentifiers(column);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel JLabelSelectStaff = new JLabel("Please Select a Staff from the list");
		JLabelSelectStaff.setForeground(new Color(204, 51, 204));
		JLabelSelectStaff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabelSelectStaff.setBounds(239, 43, 375, 50);
		contentPane.add(JLabelSelectStaff);
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
		
		JButton JbtnSelect = new JButton("Select");
		JbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1)
        		{
        			int selectedRow = table.getSelectedRow();
        			String selectedStaffID = (String) table.getValueAt(selectedRow, 1);
        			Cars_Update.setStaffID(selectedStaffID);
        			dispose();
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "Please select a Staff!!!!");
        		}
			}
		});
		JbtnSelect.setBounds(46, 471, 89, 23);
		getContentPane().add(JbtnSelect);
		
		JButton JbtnBack = new JButton("Cancel");
		JbtnBack.setBounds(699, 471, 89, 23);
		getContentPane().add(JbtnBack);
		JbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}

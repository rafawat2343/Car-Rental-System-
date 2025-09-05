package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 495);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Menu.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton JbtnBookingDetails = new JButton("Booking Details");
		JbtnBookingDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking_Details bd =new Booking_Details();
				bd.show();
				dispose();
			}
		});
		JbtnBookingDetails.setBounds(328, 55, 157, 48);
		contentPane.add(JbtnBookingDetails);
		
		JButton JbtnStaff = new JButton("Staff");
		JbtnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_Details sd =new Staff_Details();
				sd.show();
				dispose();
			}
		});
		JbtnStaff.setBounds(328, 134, 157, 48);
		contentPane.add(JbtnStaff);
		
		JButton JbtnCustomer = new JButton("Customer");
		JbtnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer_Details cusd =new Customer_Details();
				cusd.show();
				dispose();
			}
		});
		JbtnCustomer.setBounds(328, 208, 157, 48);
		contentPane.add(JbtnCustomer);
		
		JButton JbtnCarOwner = new JButton("Car Owner");
		JbtnCarOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Car_Owner_Details cod =new Car_Owner_Details();
				cod.show();
				dispose();
			}
		});
		JbtnCarOwner.setBounds(328, 281, 157, 48);
		contentPane.add(JbtnCarOwner);
		
		JButton JbtnCars = new JButton("Cars");
		JbtnCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cars_Details cd =new Cars_Details();
				cd.show();
				dispose();
			}
		});
		JbtnCars.setBounds(328, 352, 157, 48);
		contentPane.add(JbtnCars);
		
		JButton JbtnLogOut = new JButton("Log Out");
		JbtnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int showConfirmDialog=JOptionPane.showConfirmDialog(null, "Are you sure want to Log Out from the sysytem?", "Yes", JOptionPane.YES_NO_OPTION);
				if(showConfirmDialog==0)
				{
					LoginGUI log = new LoginGUI();
					dispose();
					JOptionPane.showMessageDialog(null, "Successfully Logged Out!!!!!!");
					log.main(null);
				}
				else
				{
					setVisible(true);
				}
				}			
		});
		JbtnLogOut.setBounds(694, 55, 89, 23);
		contentPane.add(JbtnLogOut);
	}
}

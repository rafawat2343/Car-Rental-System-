package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import backendCode.LoginInfo;

public class LoginGUI {

	private JFrame JLoginFrame;
	private JTextField JTextFieldName;
	private JPasswordField JpasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.JLoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JLoginFrame = new JFrame();
		JLoginFrame.getContentPane().setBackground(SystemColor.activeCaption);
		JLoginFrame.setTitle("Login Portal");
		JLoginFrame.setBounds(100, 100, 683, 427);
		JLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLoginFrame.getContentPane().setLayout(null);
		
		JLabel JLabelWelcome = new JLabel("Welcome To Car Charter System");
		JLabelWelcome.setForeground(Color.DARK_GRAY);
		JLabelWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		JLabelWelcome.setBackground(SystemColor.activeCaption);
		JLabelWelcome.setBounds(171, 11, 335, 37);
		JLoginFrame.getContentPane().add(JLabelWelcome);
		
		JLabel JLabelPortal = new JLabel("Login Portal");
		JLabelPortal.setFont(new Font("Tahoma", Font.BOLD, 16));
		JLabelPortal.setForeground(Color.DARK_GRAY);
		JLabelPortal.setBounds(262, 59, 167, 25);
		JLoginFrame.getContentPane().add(JLabelPortal);
		
		JLabel JLabelUserName = new JLabel("Username:");
		JLabelUserName.setBounds(98, 120, 92, 25);
		JLoginFrame.getContentPane().add(JLabelUserName);
		
		JLabel JLabelPassword = new JLabel("Password:");
		JLabelPassword.setBounds(98, 171, 92, 25);
		JLoginFrame.getContentPane().add(JLabelPassword);
		
		JTextFieldName = new JTextField();
		JTextFieldName.setBounds(200, 122, 264, 20);
		JLoginFrame.getContentPane().add(JTextFieldName);
		JTextFieldName.setColumns(10);
		
		JButton JbtnLogin = new JButton("Login");
		JbtnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!JTextFieldName.getText().equals("admin") && !JpasswordField.getText().equals("123"))
				{
					LoginInfo login = LoginInfo.view();
					if(login != null)
					{
							if(JTextFieldName.getText().equals(login.getUserName() )&& JpasswordField.getText().equals(login.getPassword()))
							{

								MainMenu m=new MainMenu();
								JTextFieldName.setText("");
								JpasswordField.setText("");
								m.show();
								JLoginFrame.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Wrong input");
							}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Wrong input");
					}
				}
				else if(JTextFieldName.getText().equals("admin")|| JpasswordField.getText().equals("123"))
				{
					LoginInfo logInfo =LoginInfo.view();
					if(JTextFieldName.getText().equals("admin")&& JpasswordField.getText().equals("123"))
					{	
						if(logInfo==null)
						{
							MainMenu m=new MainMenu();
							JTextFieldName.setText("");
							JpasswordField.setText("");
							m.show();
							JLoginFrame.dispose();
						}
						else
						{
							if(JTextFieldName.getText().equals(logInfo.getUserName() )&& JpasswordField.getText().equals(logInfo.getPassword()))
							{	
								MainMenu m=new MainMenu();
								JTextFieldName.setText("");
								JpasswordField.setText("");
								m.show();
								JLoginFrame.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Wrong input");
							}
						}
					}
					else
					{
						if(logInfo!=null)
						{
							if(JTextFieldName.getText().equals(logInfo.getUserName() )&& JpasswordField.getText().equals(logInfo.getPassword()))
							{	
								MainMenu m=new MainMenu();
								JTextFieldName.setText("");
								JpasswordField.setText("");
								m.show();
								JLoginFrame.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Wrong input");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Wrong input");
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong input");
				}
			}
				
		});
		JbtnLogin.setBounds(224, 234, 89, 23);
		JLoginFrame.getContentPane().add(JbtnLogin);
		
		JButton JbtnReset = new JButton("Reset");
		JbtnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextFieldName.setText("");
				JpasswordField.setText("");
			}
		});
		JbtnReset.setBounds(355, 234, 89, 23);
		JLoginFrame.getContentPane().add(JbtnReset);
		
		JpasswordField = new JPasswordField();
		JpasswordField.setBounds(200, 173, 264, 20);
		JLoginFrame.getContentPane().add(JpasswordField);
		
		JLabel JLabelChangePassword = new JLabel("Change Login Info? ");
		JLabelChangePassword.setForeground(Color.BLACK);
		JLabelChangePassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLabelChangePassword.setBounds(305, 278, 141, 31);
		JLoginFrame.getContentPane().add(JLabelChangePassword);
		
		JButton JbtnChangePassword = new JButton("Click Here");
		JbtnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmUserInfo cu  = new ConfirmUserInfo();
				cu.setVisible(true);
			}
		});
		JbtnChangePassword.setBounds(458, 283, 112, 23);
		JLoginFrame.getContentPane().add(JbtnChangePassword);
	}
}

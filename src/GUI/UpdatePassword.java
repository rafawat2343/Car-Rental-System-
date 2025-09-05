package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import backendCode.*;
import javax.swing.JPasswordField;

public class UpdatePassword extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldOldPass;
	private JTextField textFieldNewUser;
	private JTextField textFieldOldUser;
	private JPasswordField JpasswordField;
	private JPasswordField JpasswordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePassword frame = new UpdatePassword();
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
	public UpdatePassword() {
		setTitle("Update Username & Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOldUsername = new JLabel("Old Username:");
		lblOldUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOldUsername.setBounds(50, 47, 124, 22);
		contentPane.add(lblOldUsername);
		
		JLabel lblNewUsername = new JLabel("New Username:");
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewUsername.setBounds(50, 80, 124, 22);
		contentPane.add(lblNewUsername);
		
		JLabel lblOldPassword = new JLabel("Old password:");
		lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOldPassword.setBounds(50, 113, 124, 22);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewPassword.setBounds(50, 146, 124, 22);
		contentPane.add(lblNewPassword);
		
		JLabel lblTypeNewPassword = new JLabel("Type new password again:");
		lblTypeNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTypeNewPassword.setBounds(50, 179, 175, 22);
		contentPane.add(lblTypeNewPassword);
		
		LoginInfo l = LoginInfo.view();
		String oldUsername,oldPassword;
		if(LoginInfo.isLoginInfoEmpty())
		{
			oldUsername ="admin";
			oldPassword = "123";
		}
		else if(l!=null)
		{
			oldUsername =l.getUserName();
			oldPassword = l.getPassword();
		}
		else
		{
			oldUsername ="admin";
			oldPassword = "123";
		}
		
		textFieldOldPass = new JTextField(oldPassword);
		textFieldOldPass.setColumns(10);
		textFieldOldPass.setBounds(235, 115, 345, 20);
		contentPane.add(textFieldOldPass);
		
		textFieldNewUser = new JTextField();
		textFieldNewUser.setColumns(10);
		textFieldNewUser.setBounds(235, 82, 345, 20);
		contentPane.add(textFieldNewUser);
		
		textFieldOldUser = new JTextField(oldUsername);
		textFieldOldUser.setColumns(10);
		textFieldOldUser.setBounds(235, 49, 345, 20);
		contentPane.add(textFieldOldUser);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Keep Old Userename ");
		chckbxNewCheckBox.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					textFieldNewUser.setText(oldUsername);
				}
				else
				{
					textFieldNewUser.setText("");
				}
			}
			
		});
		chckbxNewCheckBox.setBounds(600, 81, 177, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton JbtnChange = new JButton("Change username & password");
		JbtnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 LoginInfo l = LoginInfo.view();
				String userName = textFieldNewUser.getText().trim();
				String password= JpasswordField.getText().trim();
				
				if(password.equals(JpasswordField_1.getText().trim()))
				{
					if(!userName.isEmpty()&& !password.isEmpty())
					{
						if(l != null && userName.equals(l.getUserName()) || userName.equals("admin"))
						{
							LoginInfo log = new LoginInfo(userName,password);
							log.updateLoginInfo();
							JOptionPane.showMessageDialog(null, "No changes in username !!!");
							dispose();
						}
						else
						{
							LoginInfo log = new LoginInfo(userName,password);
							log.updateLoginInfo();
							JOptionPane.showMessageDialog(null, "Changes successfull !!!");
							dispose();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username and password fields are empty!!!");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "New password mismatched");
					setVisible(true);
				}
			}
		});
		JbtnChange.setBounds(135, 269, 215, 23);
		contentPane.add(JbtnChange);
		
		JButton JbtnCancel = new JButton("Cancel");
		JbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure want to cancel?","yes",JOptionPane.OK_CANCEL_OPTION);
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
		JbtnCancel.setBounds(515, 269, 161, 23);
		contentPane.add(JbtnCancel);
		
		JCheckBox chckbxShowPassword_2 = new JCheckBox("Show password");
		chckbxShowPassword_2.setBounds(552, 208, 124, 23);
		chckbxShowPassword_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chckbxShowPassword_2.isSelected())
				{
					JpasswordField.setEchoChar('*');
					JpasswordField_1.setEchoChar('*');
				}
				else
				{
					JpasswordField.setEchoChar((char)0);
					JpasswordField_1.setEchoChar((char)0);
				}
			}
			
			
		});
		contentPane.add(chckbxShowPassword_2);
		
		JButton JbtnChangeToDefault = new JButton("Change to default username & password");
		JbtnChangeToDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure want to use the deafault username and password?","Yes", JOptionPane.OK_CANCEL_OPTION);
				if(showConfirmDialog==0)
				{
					LoginInfo logNew = new LoginInfo("admin","123");
					logNew.updateLoginInfo();
					dispose();
				}
				else
				{
					setVisible(true);
				}
				
			}
		});
		JbtnChangeToDefault.setBounds(267, 330, 285, 23);
		contentPane.add(JbtnChangeToDefault);
		
		JpasswordField = new JPasswordField();
		JpasswordField.setBounds(235, 148, 345, 20);
		contentPane.add(JpasswordField);
		
		JpasswordField_1 = new JPasswordField();
		JpasswordField_1.setBounds(235, 179, 345, 20);
		contentPane.add(JpasswordField_1);
	}
}

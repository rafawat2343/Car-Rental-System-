package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import backendCode.*;

public class ConfirmUserInfo extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldUsername;
	private JPasswordField JpasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmUserInfo frame = new ConfirmUserInfo();
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
	public ConfirmUserInfo() {
		setTitle("Confirm Username & Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabelUserName = new JLabel("Username:");
		JLabelUserName.setBounds(84, 89, 92, 25);
		contentPane.add(JLabelUserName);
		
		JtextFieldUsername = new JTextField();
		JtextFieldUsername.setColumns(10);
		JtextFieldUsername.setBounds(186, 91, 264, 20);
		contentPane.add(JtextFieldUsername);
		
		JLabel JLabelPassword = new JLabel("Password:");
		JLabelPassword.setBounds(84, 144, 92, 25);
		contentPane.add(JLabelPassword);
		
		JpasswordField = new JPasswordField();
		JpasswordField.setBounds(186, 146, 264, 20);
		contentPane.add(JpasswordField);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!JtextFieldUsername.getText().equals("admin") && !JpasswordField.getText().equals("123"))
				{
					LoginInfo login = LoginInfo.view();
					if(login != null)
					{
							if(JtextFieldUsername.getText().equals(login.getUserName() )&& JpasswordField.getText().equals(login.getPassword()))
							{

								UpdatePassword up=new UpdatePassword();
								JtextFieldUsername.setText("");
								JpasswordField.setText("");
								up.show();
								dispose();
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
				else if(JtextFieldUsername.getText().equals("admin")|| JpasswordField.getText().equals("123"))
				{
					LoginInfo logInfo =LoginInfo.view();
					if(JtextFieldUsername.getText().equals("admin")&& JpasswordField.getText().equals("123"))
					{	
						if(logInfo==null)
						{
							UpdatePassword up=new UpdatePassword();
							JtextFieldUsername.setText("");
							JpasswordField.setText("");
							up.show();
							dispose();
						}
						else
						{
							if(JtextFieldUsername.getText().equals(logInfo.getUserName() )&& JpasswordField.getText().equals(logInfo.getPassword()))
							{	
								UpdatePassword up=new UpdatePassword();
								JtextFieldUsername.setText("");
								JpasswordField.setText("");
								up.show();
								dispose();
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
							if(JtextFieldUsername.getText().equals(logInfo.getUserName() )&& JpasswordField.getText().equals(logInfo.getPassword()))
							{	
								UpdatePassword up=new UpdatePassword();
								JtextFieldUsername.setText("");
								JpasswordField.setText("");
								up.show();
								dispose();
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
		btnConfirm.setBounds(147, 225, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton JbtnCancel = new JButton("Cancel");
		JbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int showConfirmDialog=JOptionPane.showConfirmDialog(null, "Are you sure want to cancel this?","Yes",JOptionPane.YES_NO_OPTION);
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
		JbtnCancel.setBounds(382, 225, 89, 23);
		contentPane.add(JbtnCancel);
	}
}

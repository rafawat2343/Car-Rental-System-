package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteConfirmation extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteConfirmation frame = new DeleteConfirmation();
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
	public DeleteConfirmation() {
		setTitle("Delete Confirmation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabelConfirmation1 = new JLabel("Do you want to delete the selected record? ");
		JLabelConfirmation1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelConfirmation1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabelConfirmation1.setBounds(28, 24, 532, 44);
		contentPane.add(JLabelConfirmation1);
		
		JLabel lblOr = new JLabel("Or");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOr.setBounds(10, 64, 532, 44);
		contentPane.add(lblOr);
		
		JLabel JLabelConfirmation2 = new JLabel("Enter an ID to be deleted");
		JLabelConfirmation2.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelConfirmation2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabelConfirmation2.setBounds(20, 106, 532, 44);
		contentPane.add(JLabelConfirmation2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(51, 185, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(441, 185, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

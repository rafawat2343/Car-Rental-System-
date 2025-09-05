package GUI;

import backendCode.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cars_Delete extends JFrame {

	private JPanel contentPane;
	private JTextField JtextFieldCarID;
	private Cars_Details carsDetails;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cars_Delete frame = new Cars_Delete();
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
	public Cars_Delete(Cars_Details carsDetails) {
		this.carsDetails = carsDetails;
		setTitle("Delete Car");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 434, 151);
		contentPane.add(contentPane_1);
		
		Toolkit toolKit = getToolkit();
		Dimension size =toolKit.getScreenSize();
		setLocation(size.width/2- getWidth()/2,size.height/2-getHeight()/2);
		
		JLabel JLabelDeleteCar = new JLabel("Enter Car ID to be deleted");
		JLabelDeleteCar.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelDeleteCar.setBounds(59, 24, 318, 14);
		contentPane_1.add(JLabelDeleteCar);
		
		JtextFieldCarID = new JTextField();
		JtextFieldCarID.setColumns(10);
		JtextFieldCarID.setBounds(59, 49, 318, 20);
		contentPane_1.add(JtextFieldCarID);
		
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
		JbtnCancel.setBounds(255, 108, 89, 23);
		contentPane_1.add(JbtnCancel);
		
		JButton JbtnDelete = new JButton("Delete");
		JbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String carID = JtextFieldCarID.getText().trim();
                if (!carID.isEmpty()) {
                        if (Integer.parseInt(carID) > 0) {
                            Car car = Car.search(Integer.parseInt(carID));
                            if (car != null) {
                                int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove this car \n "
                                        + car.toString() + "\n Are you sure you want to continue ??", "Confirmation",
                                        JOptionPane.OK_CANCEL_OPTION);
                                if (showConfirmDialog == 0) {
                                    car.delete();
                                    dispose();
                                    carsDetails.dispose();
									Cars_Details detailsFrame = new Cars_Details();
									detailsFrame.setVisible(true);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Car ID not found !");
                            }
                        } else {
                            carID = null;
                            JOptionPane.showMessageDialog(null, "ID cannot be '0' or negative !");
                        }
                    } 
                } 	
		});
		JbtnDelete.setBounds(88, 108, 89, 23);
		contentPane_1.add(JbtnDelete);
	}
}

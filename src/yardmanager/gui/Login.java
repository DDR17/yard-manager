package yardmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame frmYardManagerLogin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYardManagerLogin = new JFrame();
		frmYardManagerLogin.setResizable(false);
		frmYardManagerLogin.setTitle("Yard Manager Login");
		frmYardManagerLogin.setBounds(100, 100, 317, 149);
		frmYardManagerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYardManagerLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmYardManagerLogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//check the username and password in the database
			}
		});
		btnContinue.setBounds(103, 88, 105, 23);
		panel.add(btnContinue);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(62, 11, 186, 66);
		panel.add(panel_1);
		
		JLabel lblUsername = new JLabel("Username");
		panel_1.add(lblUsername);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel_1.add(lblPassword);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		frmYardManagerLogin.setVisible(true);
	}
}

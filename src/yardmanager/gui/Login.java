package yardmanager.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JPasswordField;

import yardmanager.User;
import yardmanager.dao.UserDAO;

public class Login {

	private JFrame frmYardManagerLogin;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private UserDAO userDAO;
	private Connection conn;

	/**
	 * Create the application.
	 */
	public Login(UserDAO userDAO, Connection connect) {
		conn = connect;
		this.userDAO = userDAO;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYardManagerLogin = new JFrame();
		frmYardManagerLogin.setResizable(false);
		frmYardManagerLogin.setTitle("Yard Manager Login");
		frmYardManagerLogin.setBounds(100, 100, 319, 175);
		frmYardManagerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYardManagerLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmYardManagerLogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ContinuePressed();
			}
		});
		btnContinue.setBounds(104, 99, 105, 23);
		panel.add(btnContinue);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(53, 11, 207, 77);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(26, 8, 48, 14);
		panel_1.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(79, 5, 86, 20);
		panel_1.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 33, 46, 14);
		panel_1.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(78, 30, 87, 20);
		panel_1.add(textPassword);
		textPassword.setColumns(10);
		
		frmYardManagerLogin.setVisible(true);
	}
	
	public void ContinuePressed(){
		String username;
		String password;
		
		username = textUsername.getText();
		password = new String(textPassword.getPassword());
		User user =  userDAO.authenticate(username, password);
		if(user != null) {
			frmYardManagerLogin.setVisible(false);
			frmYardManagerLogin.dispose();
			new MainFrame(user, conn);
		} 
		else {
			JOptionPane.showMessageDialog(null,"Incorrect username or password.", "Attention", JOptionPane.OK_OPTION);
		}
	}
}

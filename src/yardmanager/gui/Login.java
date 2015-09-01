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
import java.awt.Font;

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
		btnContinue.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel.add(btnContinue);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(62, 11, 188, 77);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblUsername.setBounds(10, 5, 72, 14);
		panel_1.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textUsername.setBounds(92, 2, 86, 20);
		panel_1.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPassword.setBounds(10, 32, 72, 14);
		panel_1.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(92, 30, 87, 20);
		textPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
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

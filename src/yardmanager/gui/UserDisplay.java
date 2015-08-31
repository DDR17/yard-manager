package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import yardmanager.User;
import yardmanager.dao.UserDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class UserDisplay extends JDialog {

	private JPanel contentPane;
	private JTextField txtFirst;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPass2;
	private JComboBox cbClearance;
	private String tempUser;
	private Connection conn;
	private UserDAO userDAO;
	private JTextField txtLast;
	private boolean newUser;
	
	public UserDisplay( Connection connect, boolean userIsNew) {
		conn = connect;
		newUser = userIsNew;
		userDAO = new UserDAO(conn);
		
		setModal(true);
		setResizable(false);
		setTitle("User Display");
		setBounds(100, 100, 242, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setBounds(10, 14, 99, 14);
		contentPane.add(lblFirst);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(10, 64, 70, 14);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(10, 89, 86, 14);
		contentPane.add(lblPass);
		
		JLabel lblPass2 = new JLabel("Retype password");
		lblPass2.setBounds(10, 114, 110, 14);
		contentPane.add(lblPass2);
		
		txtFirst = new JTextField();
		txtFirst.setBounds(120, 14, 86, 20);
		contentPane.add(txtFirst);
		txtFirst.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(120, 64, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(120, 89, 86, 20);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		txtPass2 = new JPasswordField();
		txtPass2.setBounds(120, 114, 86, 20);
		contentPane.add(txtPass2);
		txtPass2.setColumns(10);
		
		JLabel lblClearance = new JLabel("Clearance");
		lblClearance.setBounds(10, 139, 70, 14);
		contentPane.add(lblClearance);
		
		String[] cblist = {"gold", "silver", "bronze"};
		cbClearance = new JComboBox(cblist);
		cbClearance.setBounds(120, 142, 86, 20);
		contentPane.add(cbClearance);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newUser) {
					tempUser = txtUser.getText();
					if(userDAO.find(txtUser.getText()) != null) {
						JOptionPane.showMessageDialog(null,"Username already exists. Please select another one.", "Username Invalid", JOptionPane.OK_OPTION);
					}
					else if (String.valueOf(txtPass.getPassword()) != String.valueOf(txtPass2.getPassword())) {
						JOptionPane.showMessageDialog(null,"Your passwords do not match. Please input matching passwords", "Passwords Invalid", JOptionPane.OK_OPTION);
					}
					else {
						User user = new User(txtUser.getText(), String.valueOf(txtPass.getPassword()), String.valueOf(cbClearance.getSelectedItem()), txtFirst.getText(), txtLast.getText() );
						userDAO.create(user);
						dispose();
					}
				}
				else {
				
					
				}
			}
		});
		btnAdd.setBounds(73, 168, 89, 23);
		contentPane.add(btnAdd);
		
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(10, 39, 99, 14);
		contentPane.add(lblLast);
		
		txtLast = new JTextField();
		txtLast.setColumns(10);
		txtLast.setBounds(120, 39, 86, 20);
		contentPane.add(txtLast);
		
		setVisible(true);
	}
}

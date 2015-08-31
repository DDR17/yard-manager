package yardmanager.gui;

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
	private Connection conn;
	private UserDAO userDAO;
	private JTextField txtLast;
	private boolean createNewUser;
	private User oldUser;
	
	public UserDisplay( Connection connect, User userOld) {
		conn = connect;
		oldUser = userOld;
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
		
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(10, 39, 99, 14);
		contentPane.add(lblLast);
		
		txtLast = new JTextField();
		txtLast.setColumns(10);
		txtLast.setBounds(120, 39, 86, 20);
		contentPane.add(txtLast);
		
		String[] cblist = {"gold", "silver", "bronze"};
		cbClearance = new JComboBox(cblist);
		cbClearance.setBounds(120, 142, 86, 20);
		contentPane.add(cbClearance);
		
		if (oldUser == null) {
			createNewUser = true;
		}
		else {
			createNewUser = false;
			txtUser.setText(oldUser.getUsername());
			txtFirst.setText(oldUser.getFirstName());
			txtLast.setText(oldUser.getLastName());
			txtPass.setText(oldUser.getPassword());
			txtPass2.setText(oldUser.getPassword());
			cbClearance.setSelectedItem(oldUser.getClearance());
		}
		
		JButton btnAdd = new JButton(createNewUser?"Add":"Edit");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(createNewUser) {
					if(userDAO.find(txtUser.getText()) != null) {
						JOptionPane.showMessageDialog(null,"Username already exists. Please select another one.", "Username Invalid", JOptionPane.OK_OPTION);
					}
					else if (!String.valueOf(txtPass.getPassword()).equals(String.valueOf(txtPass2.getPassword()))) {
						JOptionPane.showMessageDialog(null,"Your passwords do not match. Please input matching passwords.", "Passwords Invalid", JOptionPane.OK_OPTION);
					}
					else {
						User newUser = new User(txtUser.getText(), String.valueOf(txtPass.getPassword()), String.valueOf(cbClearance.getSelectedItem()), txtFirst.getText(), txtLast.getText() );
						userDAO.create(newUser);
						dispose();
					}
				}
				else {
					User newUser = new User(txtUser.getText(), String.valueOf(txtPass.getPassword()), String.valueOf(cbClearance.getSelectedItem()), txtFirst.getText(), txtLast.getText() );
					userDAO.update(newUser, oldUser);
					dispose();
				}
			}
		});
		btnAdd.setBounds(73, 168, 89, 23);
		contentPane.add(btnAdd);		
		
		setVisible(true);
	}
}

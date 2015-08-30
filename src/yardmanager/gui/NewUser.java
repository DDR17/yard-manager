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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewUser extends JDialog {

	JPanel contentPane;
	JTextField txtEmployee;
	JTextField txtUser;
	JTextField txtPass;
	JTextField txtPass2;
	JComboBox cbClearance;
	boolean refresh;
	boolean replace;
	String tempuser;
	Database db;
	
	public NewUser( boolean x, boolean y) {
		setModal(true);
		refresh = x;
		replace = y;
		
		setResizable(false);
		setTitle("New User");
		setBounds(100, 100, 240, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployee = new JLabel("Employee name");
		lblEmployee.setBounds(10, 14, 99, 14);
		contentPane.add(lblEmployee);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(10, 39, 70, 14);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(10, 64, 86, 14);
		contentPane.add(lblPass);
		
		JLabel lblPass2 = new JLabel("Retype password");
		lblPass2.setBounds(10, 89, 110, 14);
		contentPane.add(lblPass2);
		
		txtEmployee = new JTextField();
		txtEmployee.setBounds(120, 14, 86, 20);
		contentPane.add(txtEmployee);
		txtEmployee.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(120, 39, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(120, 64, 86, 20);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		txtPass2 = new JTextField();
		txtPass2.setBounds(120, 89, 86, 20);
		contentPane.add(txtPass2);
		txtPass2.setColumns(10);
		
		JLabel lblClearance = new JLabel("Clearance");
		lblClearance.setBounds(10, 114, 70, 14);
		contentPane.add(lblClearance);
		
		String[] cblist = {"gold", "silver", "bronze"};
		cbClearance = new JComboBox(cblist);
		cbClearance.setBounds(120, 117, 86, 20);
		contentPane.add(cbClearance);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db = new Database();
				if(db.userCheck(txtUser.getText(), txtPass.getText()).equals("bad credentials")) {
					if(txtPass.getText().equals(txtPass2.getText())){
						db = new Database();
						if(replace) db.deleteUser(tempuser);
						db = new Database();
						db.addUser(txtEmployee.getText(), txtUser.getText(), txtPass.getText(), String.valueOf(cbClearance.getSelectedItem()));
						if(refresh) new EditUser();
						dispose();
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "The two passwords are not equivalent", "", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "That username is already in use.", "", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(72, 143, 89, 23);
		contentPane.add(btnAdd);
		
		setVisible(true);
	}
	
	public void Edit(String employee, String user) {
		
		txtEmployee.setText(employee);
		txtUser.setText(user);
		tempuser = user;
	}
	
}

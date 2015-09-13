package yardmanager.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import yardmanager.Address;
import yardmanager.Company;
import yardmanager.User;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.UserDAO;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JPasswordField;

import java.awt.Font;

@SuppressWarnings("serial")
public class CompanyDisplay extends JDialog {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPostal;
	private JTextField txtCountry;
	private JTextField txtCity;
	private JComboBox<String> cbType;
	private Connection conn;
	private CompanyDAO companyDAO;
	private JTextField txtName;
	private boolean createNewUser;
	private Company oldCompany;
	private JTextField txtStreet;
	private JTextField txtStreetNum;
	
	public CompanyDisplay( Connection connect, Company companyOld) {
		conn = connect;
		oldCompany = companyOld;
		companyDAO = new CompanyDAO(conn);
		
		setModal(true);
		setResizable(false);
		setTitle("User Display");
		setBounds(100, 100, 244, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Company ID");
		lblID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblID.setBounds(10, 14, 99, 14);
		contentPane.add(lblID);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblType.setBounds(10, 64, 70, 14);
		contentPane.add(lblType);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblCountry.setBounds(10, 89, 86, 14);
		contentPane.add(lblCountry);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblCity.setBounds(10, 114, 110, 14);
		contentPane.add(lblCity);
		
		txtID = new JTextField();
		txtID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtID.setBounds(120, 14, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPostal = new JTextField();
		txtPostal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPostal.setBounds(120, 139, 86, 20);
		contentPane.add(txtPostal);
		txtPostal.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCountry.setBounds(120, 89, 86, 20);
		contentPane.add(txtCountry);
		txtCountry.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCity.setBounds(120, 114, 86, 20);
		contentPane.add(txtCity);
		txtCity.setColumns(10);
		
		JLabel lblPostal = new JLabel("Postal Code");
		lblPostal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPostal.setBounds(10, 139, 70, 14);
		contentPane.add(lblPostal);
		
		JLabel lblName = new JLabel("Company Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblName.setBounds(10, 39, 99, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtName.setColumns(10);
		txtName.setBounds(120, 39, 86, 20);
		contentPane.add(txtName);
		
		String[] cblist = {"Customer", "Trucker"};
		cbType = new JComboBox<>(cblist);
		cbType.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbType.setBounds(120, 64, 86, 20);
		contentPane.add(cbType);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblStreet.setBounds(10, 164, 70, 14);
		contentPane.add(lblStreet);
		
		txtStreet = new JTextField();
		txtStreet.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtStreet.setColumns(10);
		txtStreet.setBounds(120, 164, 86, 20);
		contentPane.add(txtStreet);
		
		JLabel lblStreetNum = new JLabel("Civic Number");
		lblStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblStreetNum.setBounds(10, 189, 99, 14);
		contentPane.add(lblStreetNum);
		
		txtStreetNum = new JTextField();
		txtStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtStreetNum.setColumns(10);
		txtStreetNum.setBounds(120, 189, 86, 20);
		contentPane.add(txtStreetNum);
		if (oldCompany == null) {
			createNewUser = true;
		}
		else {
			createNewUser = false;
			txtPostal.setText(oldCompany.getAddress().getPostalCode());
			txtID.setText(oldCompany.getId());
			txtName.setText(oldCompany.getName());
			txtCountry.setText(oldCompany.getAddress().getCountry());
			txtCity.setText(oldCompany.getAddress().getCity());
			cbType.setSelectedItem(oldCompany.getType());
			txtStreet.setText(oldCompany.getAddress().getStreet());
			txtStreetNum.setText(oldCompany.getAddress().getStreetNumber());
		}
		
		JButton btnAdd = new JButton(createNewUser?"Add":"Edit");
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(createNewUser) {
					if(companyDAO.find(txtID.getText()) != null) {
						JOptionPane.showMessageDialog(null,"Company ID already exists. Please select another one.", "Attention", JOptionPane.OK_OPTION);
					}
					else {
						Address address = new Address(txtCity.getText(),txtCountry.getText(), txtPostal.getText(), txtStreet.getText(), txtStreetNum.getText());
						Company newCompany = new Company(txtID.getText(), txtName.getText(), String.valueOf(cbType.getSelectedItem()), address);
						companyDAO.create(newCompany);
						dispose();
					}
				}
				else {
					Address address = new Address(txtCity.getText(),txtCountry.getText(), txtPostal.getText(), txtStreet.getText(), txtStreetNum.getText());
					Company newCompany = new Company(txtID.getText(), txtName.getText(), String.valueOf(cbType.getSelectedItem()), address);
					companyDAO.update(newCompany, oldCompany);
					dispose();
				}
			}
		});
		btnAdd.setBounds(64, 218, 110, 23);
		contentPane.add(btnAdd);		
		
		
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		setVisible(true);
	}
}

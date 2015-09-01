package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import yardmanager.Company;
import yardmanager.User;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.UserDAO;

import java.sql.Connection;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class EditCompany extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private CompanyDAO companyDAO;
	private Connection conn;
	private List<Company> companies;
	
	public EditCompany(Connection connect) {
		conn = connect;
		setModal(true);
		companyDAO = new CompanyDAO(conn);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Company List");
		setBounds(100, 100, 795, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 637, 384);
		contentPane.add(scrollPane_1);
		
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table();
		
		JButton btnEditCompany = new JButton("Edit Company");
		btnEditCompany.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEditCompany.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null,"Please select a user by clicking a row in the table.", "Attention", JOptionPane.OK_OPTION);
				}
				else {
					//new UserDisplay(conn, users.get(1)); TODO
					table();
				}
			}
		});
		btnEditCompany.setBounds(657, 11, 122, 23);
		contentPane.add(btnEditCompany);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,"Please select a company by clicking a row in the table.", "Attention", JOptionPane.OK_OPTION);
					return;
				}
				companyDAO.delete(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				table();
			}
		});
		btnDelete.setBounds(657, 44, 122, 23);
		contentPane.add(btnDelete);
		
		JButton btnNewUser = new JButton("New Company");
		btnNewUser.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new UserDisplay(conn , null);
				table();
			}
		});
		btnNewUser.setBounds(657, 78, 122, 23);
		contentPane.add(btnNewUser);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}
	
	public void table() {
		
		companies = companyDAO.list();
		Object[][] companyData = new Object[companies.size()][8];
		
		for(int i = 0; i < companies.size(); i++) {
			companyData[i][0] = companies.get(i).getId();
			companyData[i][1] = companies.get(i).getName();
			companyData[i][2] = companies.get(i).getType();
			companyData[i][3] = companies.get(i).getAddress().getCountry();
			companyData[i][4] = companies.get(i).getAddress().getCity();
			companyData[i][5] = companies.get(i).getAddress().getPostalCode();
			companyData[i][6] = companies.get(i).getAddress().getStreet();
			companyData[i][7] = companies.get(i).getAddress().getStreetNumber();
		}
		table.setModel(new DefaultTableModel(
			companyData, new String[] {"Company ID", "Name", "Type", "Country", "City", "Postal Code", "Street", "Civic Number"}) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
	}
}

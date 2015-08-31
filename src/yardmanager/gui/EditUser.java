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

import yardmanager.User;
import yardmanager.dao.UserDAO;

import java.sql.Connection;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class EditUser extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private UserDAO userDAO;
	private Connection conn;
	private List<User> users;
	
	public EditUser(Connection connect) {
		conn = connect;
		setModal(true);
		userDAO = new UserDAO(conn);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("User List");
		setBounds(100, 100, 471, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 334, 262);
		contentPane.add(scrollPane_1);
		
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table();
		
		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null,"Please select a user by clicking a row in the table.", "No User Selected", JOptionPane.OK_OPTION);
				}
				else {
					UserDisplay userDisplay = new UserDisplay(conn, users.get(1));
					table();
				}
			}
		});
		btnEditUser.setBounds(354, 87, 89, 23);
		contentPane.add(btnEditUser);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				userDAO.delete((String)table.getValueAt(table.getSelectedRow(), 1));
				table();
			}
		});
		btnDelete.setBounds(354, 120, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserDisplay userDisplay = new UserDisplay(conn , null);
				table();
			}
		});
		btnNewUser.setBounds(354, 154, 89, 23);
		contentPane.add(btnNewUser);
		
		this.setVisible(true);
	}
	
	public void table() {
		users = userDAO.list();
		Object[][] userData = new Object[users.size()][4];
		
		for(int i = 0; i < users.size(); i++) {
			userData[i][0] = users.get(i).getFirstName() + " " + users.get(i).getLastName();
			userData[i][1] = users.get(i).getUsername();
			userData[i][2] = users.get(i).getPassword();
			userData[i][3] = users.get(i).getClearance();
		}
		table.setModel(new DefaultTableModel(
			userData,new String[] {"Employee", "Username", "Password", "Clearance"}) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
	}
}

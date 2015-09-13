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
import yardmanager.Container;
import yardmanager.User;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.ContainerDAO;
import yardmanager.dao.UserDAO;
import yardmanager.dao.YardDAO;

import java.sql.Connection;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ContainerSearch extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private ContainerDAO containerDAO;
	private YardDAO yardDAO;
	private CompanyDAO companyDAO;
	private Connection conn;
	private List<Container> containers;
	private JTextField txtSearch;
	
	public ContainerSearch(Connection connect) {
		conn = connect;
		setModal(true);
		yardDAO = new YardDAO(conn);
		companyDAO = new CompanyDAO(conn);
		containerDAO = new ContainerDAO(conn, companyDAO, yardDAO);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Container Search");
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Location", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(657, 11, 122, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnHistory = new JRadioButton("History");
		rdbtnHistory.setBounds(6, 17, 63, 25);
		rdbtnHistory.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnHistory.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel.add(rdbtnHistory);
		
		JRadioButton rdbtnInventory = new JRadioButton("Inventory");
		rdbtnInventory.setBounds(6, 45, 73, 25);
		rdbtnInventory.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel.add(rdbtnInventory);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearch.setBounds(657, 117, 122, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id contains:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setBounds(657, 100, 122, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtSearch.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "The search field is blank. Please enter a portion of the container code that you are searching for.", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				else {
					table();
				}
			}
		});
		btnSearch.setBounds(657, 148, 122, 23);
		contentPane.add(btnSearch);
		
		table.setModel(new DefaultTableModel(null, new String[] {"Container", "Yard", "Type", "Customer Id", "Seal Number", "Full", "Colour"}) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}
	
	public void table() {
		
		containers= containerDAO.list();
		Object[][] containerData = new Object[containers.size()][8];
		
		for(int i = 0; i < containers.size(); i++) {
			containerData[i][0] = containers.get(i).getId();
			containerData[i][1] = containers.get(i).getYard().getId();
			containerData[i][2] = containers.get(i).getType();
			containerData[i][3] = containers.get(i).getCustomer().getId();
			containerData[i][4] = containers.get(i).getSealNumber();
			containerData[i][5] = containers.get(i).isFull();
			containerData[i][6] = containers.get(i).getColour();
		}
		table.setModel(new DefaultTableModel(
			containerData, new String[] {"Container", "Yard", "Type", "Customer Id", "Seal Number", "Full", "Colour"}) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
	}
}

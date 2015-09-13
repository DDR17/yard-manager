package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerListModel;

import yardmanager.User;
import yardmanager.Yard;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.ContainerDAO;
import yardmanager.dao.YardDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Canvas;

import javax.swing.JTabbedPane;

import java.awt.Font;

public class MainFrame {

	private JFrame frmYardManager;
	private Connection conn;
	private User user;
	private YardDAO yardDAO;
	private CompanyDAO companyDAO;
	private ContainerDAO containerDAO;

	/**
	 * Create the application.
	 */
	public MainFrame(User user, Connection conn) {
		this.conn = conn;
		this.user = user;
		yardDAO = new YardDAO(conn);
		companyDAO = new CompanyDAO(conn);
		containerDAO = new ContainerDAO(conn, companyDAO, yardDAO);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYardManager = new JFrame();
		frmYardManager.setTitle("Yard Manager");
		frmYardManager.setBounds(100, 100, 865, 619);
		frmYardManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYardManager.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JMenuBar menuBar = new JMenuBar();
		frmYardManager.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUsers = new JMenuItem("Users");
		mnEdit.add(mntmUsers);
		
		JMenuItem mntmEditCompanies = new JMenuItem("Companies");
		mntmEditCompanies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditCompany(conn);
			}
		});
		mnEdit.add(mntmEditCompanies);
		mntmUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (user.getClearance().equals("Gold")) {
					new EditUser(conn);
				}
				else {
					JOptionPane.showMessageDialog(null,"You do not have the authorization for that action.", "Attention", JOptionPane.OK_OPTION);
				}
			}
		});
		
		JMenu mnNewObject = new JMenu("New Object");
		menuBar.add(mnNewObject);
		
		JMenuItem mntmNewUser = new JMenuItem("User");
		mntmNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserDisplay(conn, null);
			}
		});
		mnNewObject.add(mntmNewUser);
		
		JMenuItem mntmCompany = new JMenuItem("Company");
		mnNewObject.add(mntmCompany);
		
		JMenu mnLogs = new JMenu("Logs");
		menuBar.add(mnLogs);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem mntmContainer = new JMenuItem("Container");
		mnSearch.add(mntmContainer);
		
		JMenuItem mntmInterchange = new JMenuItem("Interchange");
		mnSearch.add(mntmInterchange);
		frmYardManager.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		frmYardManager.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		
		JButton btnInGate = new JButton("In-Gate");
		btnInGate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO: Pass current yard.
				new Ingate(conn, yardDAO.list().get(0));
			}
		});
		panel_1.add(btnInGate);
		
		JButton btnOutGate = new JButton("Out-Gate");
		panel_1.add(btnOutGate);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		
		JButton btnLocateContainer = new JButton("Locate Container");
		panel_3.add(btnLocateContainer);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblLevel = new JLabel("Level");
		panel_4.add(lblLevel);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spinner.setModel(new SpinnerListModel(new String[] {"Ground", "1 High", "2 High"}));
		((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		panel_4.add(spinner);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		for (Yard yard : yardDAO.list()) {
			tabbedPane.addTab(yard.getId(), null, new YardPane(conn, yard, containerDAO), "Yard View");
		}
		
		frmYardManager.setVisible(true);
	}
}

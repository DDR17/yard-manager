package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MainFrame {

	private JFrame frmYardManager;
	private Connection conn;
	private User user;

	/**
	 * Create the application.
	 */
	public MainFrame(User currentUser, Connection connect) {
		conn = connect;
		user = currentUser;
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
		
		JMenuBar menuBar = new JMenuBar();
		frmYardManager.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUsers = new JMenuItem("Users");
		mnEdit.add(mntmUsers);
		mntmUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (user.getClearance().equals("Gold")) {
					EditUser editUser = new EditUser(conn);
				}
				else {
					JOptionPane.showMessageDialog(null,"You do not have the authorization for that action.", "Attention", JOptionPane.OK_OPTION);
				}
			}
		});
		
		JMenu mnNewObject = new JMenu("New Object");
		menuBar.add(mnNewObject);
		
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
		spinner.setModel(new SpinnerListModel(new String[] {"Ground", "1 High", "2 High"}));
		panel_4.add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		frmYardManager.setVisible(true);
	}
}

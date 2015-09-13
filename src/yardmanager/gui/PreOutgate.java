package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import yardmanager.Container;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.ContainerDAO;
import yardmanager.dao.YardDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;

@SuppressWarnings("serial")
public class PreOutgate extends JDialog {

	private JPanel contentPane;
	private JTextField txtContainer;
	private Connection conn;
	private YardDAO yardDAO;
	private ContainerDAO containerDAO;
	private CompanyDAO companyDAO;
	
	
	public PreOutgate(Connection connect) {
		conn = connect;
		yardDAO = new YardDAO(conn);
		companyDAO = new CompanyDAO(conn);
		containerDAO = new ContainerDAO(conn, companyDAO, yardDAO);
		
		setTitle("Out-gate");
		setResizable(false);
		setBounds(100, 100, 221, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContainer = new JLabel("Container");
		lblContainer.setHorizontalAlignment(SwingConstants.CENTER);
		lblContainer.setBounds(70, 11, 74, 14);
		contentPane.add(lblContainer);
		
		txtContainer = new JFormattedTextField(createFormatter("UUUU#######"));
		txtContainer.setBounds(64, 36, 86, 20);
		contentPane.add(txtContainer);
		txtContainer.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container container = containerDAO.find(txtContainer.getText());
				if(container == null) {
					int n = JOptionPane.showConfirmDialog(new JFrame(), "Container is not in database. Would you like to continue?", "", JOptionPane.YES_NO_OPTION);
					if(n==1) return;
				}
				new Outgate(container);
				dispose();
			}
		});
		btnContinue.setBounds(63, 67, 89, 23);
		contentPane.add(btnContinue);
		
		setVisible(true);
	}
	
	public	MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {}
		return formatter;
	}
}

package yardmanager.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import yardmanager.Company;
import yardmanager.Container;
import yardmanager.Yard;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.ContainerDAO;
import yardmanager.dao.InterchangeDAO;
import yardmanager.dao.UserDAO;
import yardmanager.dao.YardDAO;

import java.awt.Font;

@SuppressWarnings("serial")
public class Ingate extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField txtContainer;
	private JTextField txtIso;
	private JPanel customer;
	private JLabel lblCustID;
	private JComboBox<String> cbCustCode;
	private JPanel container;
	private JTextField txtMass;
	private JTextField txtAccept;
	private JComboBox<String> cbTruckCode;
	private JTextField txtLiscence;
	private JPanel inspector;
	private JLabel lblInspInit;
	private JTextField txtInspName;
	private JLabel lblDate;
	private JTextField txtDate;
	private JPanel comment;
	private JTextPane txtComment;
	private JButton btnSavePrint;
	private Date currentDate;
	private SimpleDateFormat df;
	private JTextField txtSeal;
	private JTextField txtDom;
	private Connection conn;
	private ContainerDAO containerDAO;
	private CompanyDAO companyDAO;
	private YardDAO yardDAO;
	private InterchangeDAO interchangeDAO;
	String[] info = new String[20];
	private JButton btnNewCustomer;
	private JButton btnNewTrucker;
	private Yard yard;
	private JLabel lblColour;
	private JComboBox cbColour;
	private JRadioButton full;
	
	public Ingate(Connection connect, Yard currentYard) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);    
		
		
		yard = currentYard;
		conn = connect;
		yardDAO = new YardDAO(conn);
		setModal(true);
		companyDAO = new CompanyDAO(conn);
		setResizable(false);
		//--------------------------------------------------frame
		setTitle("In-Gate");
		setBounds(100, 100, 552, 461);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------customer
		customer = new JPanel();
		customer.setBounds(285, 119, 250, 74);
		customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		contentPane.add(customer);
		customer.setLayout(null);
		
		lblCustID = new JLabel("ID");
		lblCustID.setBounds(10, 24, 70, 14);
		lblCustID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		customer.add(lblCustID);
		
		List<Company> CustomerCompanies = companyDAO.list("Customer");
		List<String> CustomerCompanyIds = new ArrayList<String>();
		
		for (Company CustomerCompany : CustomerCompanies) {
			CustomerCompanyIds.add(CustomerCompany.getId());
		}
		
		String[] CustomerCompanyStrings = new String[CustomerCompanyIds.size()];
		CustomerCompanyStrings = CustomerCompanyIds.toArray(CustomerCompanyStrings);
		
		cbCustCode = new JComboBox<>(); //***********************************************************
		cbCustCode.setBounds(10, 38, 93, 20);
		cbCustCode.setFont(new Font("SansSerif", Font.PLAIN, 12));
		customer.add(cbCustCode);
		
		btnNewCustomer = new JButton("New Company");
		btnNewCustomer.setBounds(113, 37, 127, 23);
		btnNewCustomer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		customer.add(btnNewCustomer);
		//-----------------------------------------------------------------------container
		container = new JPanel();
		container.setBounds(10, 11, 250, 251);
		container.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Container", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		contentPane.add(container);
		container.setLayout(null);
		
		txtContainer = new JFormattedTextField(createFormatter("UUUU#######"));
		txtContainer.setBounds(10, 36, 122, 20);
		txtContainer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtContainer);
		txtContainer.setColumns(10);
		
		JLabel lblContainer = new JLabel("Container #");
		lblContainer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblContainer.setBounds(10, 22, 88, 14);
		lblContainer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblContainer);
		
		JLabel lblIso = new JLabel("ISO code");
		lblIso.setBounds(142, 22, 58, 14);
		lblIso.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblIso);
		
		txtIso = new JTextField();
		txtIso.setBounds(142, 36, 98, 20);
		txtIso.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtIso);
		txtIso.setColumns(10);
		
		JLabel lblMass = new JLabel("Mass (kg)");
		lblMass.setBounds(142, 85, 64, 14);
		lblMass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblMass);
		
		txtMass = new JTextField("55000");
		txtMass.setBounds(142, 99, 98, 20);
		txtMass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtMass);
		txtMass.setColumns(10);
		
		JLabel lblAccept = new JLabel("Acceptance #");
		lblAccept.setBounds(10, 85, 98, 14);
		lblAccept.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblAccept);
		
		txtAccept = new JTextField();
		txtAccept.setBounds(10, 99, 122, 20);
		txtAccept.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtAccept);
		txtAccept.setColumns(10);
		
		full = new JRadioButton("FULL");
		full.setBounds(10, 209, 72, 23);
		full.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(full);
		
		JLabel lblSeal = new JLabel("Seal #");
		lblSeal.setBounds(10, 145, 46, 14);
		lblSeal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblSeal);
		
		txtSeal = new JTextField();
		txtSeal.setBounds(10, 159, 122, 20);
		txtSeal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtSeal);
		txtSeal.setColumns(10);
		
		JLabel lblDom = new JLabel("D.O.M.");
		lblDom.setBounds(142, 145, 58, 14);
		lblDom.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setBounds(142, 159, 98, 20);
		txtDom.setFont(new Font("SansSerif", Font.PLAIN, 12));
		container.add(txtDom);
		txtDom.setColumns(10);
		
		lblColour = new JLabel("Colour");
		lblColour.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblColour.setBounds(142, 198, 46, 14);
		container.add(lblColour);
		
		String[] cbCoulourlist = {"Green", "Red", "Blue", "Black", "Orange", "Yellow", "Brown", "White"}; 
		cbColour = new JComboBox(cbCoulourlist);
		cbColour.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbColour.setBounds(142, 211, 98, 20);
		container.add(cbColour);
		
		//---------------------------------------------------------------------truck
		JPanel truckPanel = new JPanel();
		truckPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Truck Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		truckPanel.setBounds(285, 217, 250, 115);
		contentPane.add(truckPanel);
		truckPanel.setLayout(null);
		
		List<Company> truckCompanies = companyDAO.list("Trucking");
		List<String> truckCompanyIds = new ArrayList<String>();
		
		for (Company truckCompany : truckCompanies) {
			truckCompanyIds.add(truckCompany.getId());
		}
		
		String[] truckCompanyStrings = new String[truckCompanyIds.size()];
		truckCompanyStrings = truckCompanyIds.toArray(truckCompanyStrings);
		
		cbTruckCode = new JComboBox<>(); //******************************************************
		cbTruckCode.setBounds(10, 37, 93, 20);
		cbTruckCode.setFont(new Font("SansSerif", Font.PLAIN, 12));
		truckPanel.add(cbTruckCode);
		
		JLabel lblTruckID = new JLabel("ID");
		lblTruckID.setBounds(10, 23, 46, 14);
		lblTruckID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		truckPanel.add(lblTruckID);
		
		JLabel lblLiscence = new JLabel("Truck Liscence");
		lblLiscence.setBounds(10, 68, 113, 14);
		lblLiscence.setFont(new Font("SansSerif", Font.PLAIN, 12));
		truckPanel.add(lblLiscence);
		
		txtLiscence = new JTextField();
		txtLiscence.setBounds(11, 82, 92, 20);
		txtLiscence.setFont(new Font("SansSerif", Font.PLAIN, 12));
		truckPanel.add(txtLiscence);
		txtLiscence.setColumns(10);
		
		btnNewTrucker = new JButton("New Company");
		btnNewTrucker.setBounds(113, 36, 127, 23);
		btnNewTrucker.setFont(new Font("SansSerif", Font.PLAIN, 12));
		truckPanel.add(btnNewTrucker);
		//------------------------------------------------------------inspector
		inspector = new JPanel();
		inspector.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inspector", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		inspector.setBounds(285, 11, 250, 74);
		inspector.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(inspector);
		inspector.setLayout(null);
		
		txtInspName = new JTextField();
		txtInspName.setBounds(10, 36, 88, 20);
		txtInspName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inspector.add(txtInspName);
		txtInspName.setColumns(10);
		
		lblInspInit = new JLabel("Initials");
		lblInspInit.setBounds(11, 22, 46, 14);
		lblInspInit.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inspector.add(lblInspInit);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(108, 22, 46, 14);
		lblDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inspector.add(lblDate);
		
		currentDate = new Date();
		
		txtDate = new JTextField();
		txtDate.setBounds(108, 36, 132, 20);
		txtDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		df = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
		txtDate.setText(df.format(currentDate));
		inspector.add(txtDate);
		txtDate.setColumns(10);
		//--------------------------------------------------------------------comment
		comment = new JPanel();
		comment.setBorder(new TitledBorder(null, "Comments", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		comment.setBounds(10, 273, 250, 149);
		contentPane.add(comment);
		comment.setLayout(null);
		
		txtComment = new JTextPane();
		txtComment.setBounds(10, 20, 189, 115);
		txtComment.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comment.add(txtComment);
		//---------------------------------------------------------------extra
		btnSavePrint = new JButton("Save and Print Interchange");
		btnSavePrint.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnSavePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CheckDigit(txtContainer.getText())) {
					int n = JOptionPane.showConfirmDialog(new JFrame(), "the check digit is incorrect. Would you like to continue?", "", JOptionPane.YES_NO_OPTION);
					if(n==1) return;
				}
				Company customer = companyDAO.find(String.valueOf(cbCustCode.getSelectedItem()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm a");
				
				for(int i=0; i < 13; i++) {
					if(info[i].equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), "At least one textfield is blank.", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				try {
					Date date = sdf.parse(txtDom.getText());
					
					Container container = new Container(
							txtContainer.getText(), 
							yard,
							companyDAO.find(String.valueOf(cbCustCode.getSelectedItem())),
							"ST", //TODO fix
							txtIso.getText(),
							txtSeal.getText(),
							String.valueOf(cbColour.getSelectedItem()),
							40, //TODO fix
							Integer.parseInt(txtMass.getText()),
							0,
							0,
							0,
							0,
							date,
							full.isSelected());
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnSavePrint.setBounds(285, 354, 250, 23);
		contentPane.add(btnSavePrint);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		setVisible(true);
		
	}
	
	public boolean CheckDigit(String container) {
		char[] c = container.toCharArray();
		int sum;
		
		sum = cN(c[0])*1 + cN(c[1])*2 + cN(c[2])*4 + cN(c[3])*8 + ((int)c[4]-48)*16 + ((int)c[5]-48)*32 + ((int)c[6]-48)*64 + ((int)c[7]-48)*128 + ((int)c[8]-48)*256 + ((int)c[9]-48)*512; 
		if(sum%11 != (int)c[10]-48) return true;
		return false;
	}
	
	public int cN(char x) {
		int y = 0;
		int[] value = {10,12,13,14,15,16,17,18,19,20,21,23,24,25,26,27,28,29,30,31,32,34,35,36,37,38};
		int index = ((int)x) - 65;
		y = value[index];
		
		return y;
	}
	
	public	MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {}
		return formatter;
	}
}



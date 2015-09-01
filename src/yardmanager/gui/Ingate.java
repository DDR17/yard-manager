package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;

import java.awt.Color;
import java.sql.Connection;
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
import yardmanager.Yard;
import yardmanager.dao.CompanyDAO;
import yardmanager.dao.ContainerDAO;
import yardmanager.dao.InterchangeDAO;
import yardmanager.dao.UserDAO;
import yardmanager.dao.YardDAO;

@SuppressWarnings("serial")
public class Ingate extends JFrame {

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
	private JButton button;
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
	int lgth=0;
	private JButton btnNewCompany;
	private JButton button_1;
	
	public Ingate(Connection connect) {
		conn = connect;
		yardDAO = new YardDAO(conn);
		companyDAO = new CompanyDAO(conn);
		setResizable(false);
		//--------------------------------------------------frame
		setVisible(true);
		setTitle("In-Gate");
		setBounds(100, 100, 479, 406);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//---------------------------------------------------------------------------customer
		customer = new JPanel();
		customer.setBounds(229, 96, 233, 74);
		customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		contentPane.add(customer);
		customer.setLayout(null);
		
		lblCustID = new JLabel("ID");
		lblCustID.setBounds(10, 24, 70, 14);
		customer.add(lblCustID);
		
		cbCustCode = new JComboBox();
		cbCustCode.setBounds(10, 38, 93, 20);
		customer.add(cbCustCode);
		
		btnNewCompany = new JButton("New Company");
		btnNewCompany.setBounds(113, 37, 110, 23);
		customer.add(btnNewCompany);
		//-----------------------------------------------------------------------container
		container = new JPanel();
		container.setBounds(10, 11, 209, 182);
		container.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Container", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		contentPane.add(container);
		container.setLayout(null);
		
		txtContainer = new JFormattedTextField(createFormatter("UUUU#######"));
		txtContainer.setBounds(10, 36, 96, 20);
		container.add(txtContainer);
		txtContainer.setColumns(10);
		
		JLabel lblContainer = new JLabel("Container #");
		lblContainer.setBounds(10, 22, 72, 14);
		container.add(lblContainer);
		
		JLabel lblIso = new JLabel("ISO code");
		lblIso.setBounds(116, 22, 58, 14);
		container.add(lblIso);
		
		txtIso = new JTextField();
		txtIso.setBounds(116, 36, 83, 20);
		container.add(txtIso);
		txtIso.setColumns(10);
		
		JLabel lblMass = new JLabel("Mass (kg)");
		lblMass.setBounds(116, 67, 64, 14);
		container.add(lblMass);
		
		txtMass = new JTextField("55000");
		txtMass.setBounds(116, 81, 83, 20);
		container.add(txtMass);
		txtMass.setColumns(10);
		
		JLabel lblAccept = new JLabel("Acceptance #");
		lblAccept.setBounds(10, 67, 78, 14);
		container.add(lblAccept);
		
		txtAccept = new JTextField();
		txtAccept.setBounds(10, 81, 96, 20);
		container.add(txtAccept);
		txtAccept.setColumns(10);
		
		JRadioButton full = new JRadioButton("FULL");
		full.setBounds(10, 153, 53, 23);
		container.add(full);
		
		JLabel lblSeal = new JLabel("Seal #");
		lblSeal.setBounds(10, 112, 46, 14);
		container.add(lblSeal);
		
		txtSeal = new JTextField();
		txtSeal.setBounds(10, 126, 96, 20);
		container.add(txtSeal);
		txtSeal.setColumns(10);
		
		JLabel lblDom = new JLabel("D.O.M.");
		lblDom.setBounds(116, 112, 58, 14);
		container.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setBounds(116, 126, 83, 20);
		container.add(txtDom);
		txtDom.setColumns(10);
		
		
		
		
		//---------------------------------------------------------------------truck
		JPanel truckPanel = new JPanel();
		truckPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Truck Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		truckPanel.setBounds(229, 181, 233, 115);
		contentPane.add(truckPanel);
		truckPanel.setLayout(null);
		
		List<Company> truckCompanies = companyDAO.list("Truck");
		List<String> truckCompanyIds = new ArrayList<String>();
		
		for (Company truckCompany : truckCompanies) {
			truckCompanyIds.add(truckCompany.getId());
		}
		
		String[] truckCompanyStrings = new String[truckCompanyIds.size()];
		truckCompanyStrings = truckCompanyIds.toArray(truckCompanyStrings);
		
		cbTruckCode = new JComboBox<>(truckCompanyStrings);
		cbTruckCode.setBounds(10, 37, 93, 20);
		truckPanel.add(cbTruckCode);
		
		JLabel lblTruckID = new JLabel("ID");
		lblTruckID.setBounds(10, 23, 46, 14);
		truckPanel.add(lblTruckID);
		
		JLabel lblLiscence = new JLabel("Truck Liscence");
		lblLiscence.setBounds(10, 68, 84, 14);
		truckPanel.add(lblLiscence);
		
		txtLiscence = new JTextField();
		txtLiscence.setBounds(11, 82, 92, 20);
		truckPanel.add(txtLiscence);
		txtLiscence.setColumns(10);
		
		button_1 = new JButton("New Company");
		button_1.setBounds(113, 36, 110, 23);
		truckPanel.add(button_1);
		//------------------------------------------------------------inspector
		inspector = new JPanel();
		inspector.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inspector", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		inspector.setBounds(229, 11, 233, 74);
		contentPane.add(inspector);
		inspector.setLayout(null);
		
		txtInspName = new JTextField();
		txtInspName.setBounds(10, 36, 71, 20);
		inspector.add(txtInspName);
		txtInspName.setColumns(10);
		
		lblInspInit = new JLabel("Initials");
		lblInspInit.setBounds(11, 22, 46, 14);
		inspector.add(lblInspInit);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(91, 22, 46, 14);
		inspector.add(lblDate);
		
		currentDate = new Date();
		
		txtDate = new JTextField();
		txtDate.setBounds(91, 36, 132, 20);
		df = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
		txtDate.setText(df.format(currentDate));
		inspector.add(txtDate);
		txtDate.setColumns(10);
		//--------------------------------------------------------------------comment
		comment = new JPanel();
		comment.setBorder(new TitledBorder(null, "Comments", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		comment.setBounds(10, 204, 209, 149);
		contentPane.add(comment);
		comment.setLayout(null);
		
		txtComment = new JTextPane();
		txtComment.setBounds(10, 20, 189, 115);
		comment.add(txtComment);
		//---------------------------------------------------------------extra
		button = new JButton("Save and Print Interchange");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CheckDigit(txtContainer.getText())) {
					int n = JOptionPane.showConfirmDialog(new JFrame(), "the check digit is incorrect. Would you like to continue?", "", JOptionPane.YES_NO_OPTION);
					if(n==1) return;
				}
				
				
				//containerDAO()
				
//				info[0] = txtContainer.getText();
//				info[1] = txtIso.getText();
//				info[2] = txtAccept.getText();
//				info[3] = txtMass.getText();
//				info[4] = txtSeal.getText();
//				info[5] = txtDom.getText();
//				info[6] = cbCustCode.getText();
//				info[7] = txtCustName.getText();
//				info[8] = cbTruckCode.getText();
//				info[9] = txtTruckName.getText();
//				info[10] = txtLiscence.getText();
//				info[11] = txtInspName.getText();
//				info[12] = txtDate.getText();
//				info[13] = txtComment.getText();
				
				for(int i=0; i < 13; i++) {
					if(info[i].equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), "At least one textfield is blank.", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				dispose();
			}
		});
		button.setBounds(229, 330, 234, 23);
		contentPane.add(button);
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



package yardmanager.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JButton;

import yardmanager.Container;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Outgate extends JFrame {

	private JPanel contentPane;
	private JTextField txtContainer;
	private JTextField txtIso;
	private JPanel customer;
	private JLabel lblCustCode;
	private JTextField txtCustCode;
	private JLabel lblCustName;
	private JTextField txtCustName;
	private JPanel panelContainer;
	private JTextField txtMass;
	private JTextField txtRelease;
	private JTextField txtTruckCode;
	private JTextField txtTruckName;
	private JTextField txtLiscence;
	private JPanel inspector;
	private JLabel lblInspName;
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
	String[] info = new String[20];
	
	public Outgate(Container container) {		
		//--------------------------------------------------frame
		setResizable(false);
		setVisible(true);
		setTitle("Out-Gate");
		setBounds(100, 100, 479, 406);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//---------------------------------------------------------------------------customer
		customer = new JPanel();
		customer.setBounds(229, 108, 234, 74);
		customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		contentPane.add(customer);
		customer.setLayout(null);
		
		lblCustCode = new JLabel("Code");
		lblCustCode.setBounds(10, 24, 30, 14);
		customer.add(lblCustCode);
		
		txtCustCode = new JTextField();
		txtCustCode.setBounds(10, 38, 70, 20);
		customer.add(txtCustCode);
		txtCustCode.setColumns(10);
		
		lblCustName = new JLabel("Name");
		lblCustName.setBounds(90, 24, 46, 14);
		customer.add(lblCustName);
		
		txtCustName = new JTextField();
		txtCustName.setBounds(90, 38, 86, 20);
		customer.add(txtCustName);
		txtCustName.setColumns(10);
		//-----------------------------------------------------------------------container
		panelContainer = new JPanel();
		panelContainer.setBounds(10, 11, 209, 185);
		panelContainer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Container", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		contentPane.add(panelContainer);
		panelContainer.setLayout(null);
		
		txtContainer = new JTextField();
		txtContainer.setBounds(10, 36, 96, 20);
		panelContainer.add(txtContainer);
		txtContainer.setColumns(10);
		
		JLabel lblContainer = new JLabel("Container #");
		lblContainer.setBounds(10, 22, 72, 14);
		panelContainer.add(lblContainer);
		
		JLabel lblIso = new JLabel("ISO code");
		lblIso.setBounds(116, 22, 58, 14);
		panelContainer.add(lblIso);
		
		txtIso = new JTextField();
		txtIso.setBounds(116, 36, 58, 20);
		panelContainer.add(txtIso);
		txtIso.setColumns(10);
		
		JLabel lblMass = new JLabel("Mass (kg)");
		lblMass.setBounds(116, 67, 64, 14);
		panelContainer.add(lblMass);
		
		txtMass = new JTextField("55000");
		txtMass.setBounds(116, 81, 58, 20);
		panelContainer.add(txtMass);
		txtMass.setColumns(10);
		
		JLabel lblRelease = new JLabel("Release #");
		lblRelease.setBounds(10, 67, 78, 14);
		panelContainer.add(lblRelease);
		
		txtRelease = new JTextField();
		txtRelease.setBounds(10, 81, 86, 20);
		panelContainer.add(txtRelease);
		txtRelease.setColumns(10);
		
		JRadioButton full = new JRadioButton("FULL");
		full.setBounds(10, 153, 53, 23);
		panelContainer.add(full);
		
		JLabel lblSeal = new JLabel("Seal #");
		lblSeal.setBounds(10, 112, 46, 14);
		panelContainer.add(lblSeal);
		
		txtSeal = new JTextField();
		txtSeal.setBounds(10, 126, 86, 20);
		panelContainer.add(txtSeal);
		txtSeal.setColumns(10);
		
		JLabel lblDom = new JLabel("D.O.M.");
		lblDom.setBounds(116, 112, 58, 14);
		panelContainer.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setBounds(106, 126, 86, 20);
		panelContainer.add(txtDom);
		txtDom.setColumns(10);
		//---------------------------------------------------------------------truck
		JPanel truck = new JPanel();
		truck.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Truck Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		truck.setBounds(229, 193, 234, 74);
		contentPane.add(truck);
		truck.setLayout(null);
		
		txtTruckCode = new JTextField();
		txtTruckCode.setBounds(10, 37, 46, 20);
		truck.add(txtTruckCode);
		txtTruckCode.setColumns(10);
		
		JLabel lblTruckCode = new JLabel("Code");
		lblTruckCode.setBounds(10, 23, 46, 14);
		truck.add(lblTruckCode);
		
		JLabel lblTruckName = new JLabel("Name");
		lblTruckName.setBounds(66, 23, 38, 14);
		truck.add(lblTruckName);
		
		txtTruckName = new JTextField();
		txtTruckName.setBounds(66, 37, 52, 20);
		truck.add(txtTruckName);
		txtTruckName.setColumns(10);
		
		JLabel lblLiscence = new JLabel("Truck #");
		lblLiscence.setBounds(127, 23, 46, 14);
		truck.add(lblLiscence);
		
		txtLiscence = new JTextField();
		txtLiscence.setBounds(128, 37, 56, 20);
		truck.add(txtLiscence);
		txtLiscence.setColumns(10);
		//------------------------------------------------------------inspector
		inspector = new JPanel();
		inspector.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inspector", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		inspector.setBounds(229, 11, 233, 86);
		contentPane.add(inspector);
		inspector.setLayout(null);
		
		txtInspName = new JTextField();
		txtInspName.setBounds(10, 36, 71, 20);
		inspector.add(txtInspName);
		txtInspName.setColumns(10);
		
		lblInspName = new JLabel("Name");
		lblInspName.setBounds(11, 22, 46, 14);
		inspector.add(lblInspName);
		
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
		comment.setBounds(10, 207, 209, 146);
		contentPane.add(comment);
		comment.setLayout(null);
		
		txtComment = new JTextPane();
		txtComment.setBounds(10, 20, 189, 115);
		comment.add(txtComment);
		//---------------------------------------------------------------extra
		button = new JButton("Save and Print Interchange");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info[0] = txtContainer.getText();
				info[1] = txtIso.getText();
				info[2] = txtRelease.getText();
				info[3] = txtMass.getText();
				info[4] = txtSeal.getText();
				info[5] = txtDom.getText();
				info[6] = txtCustCode.getText();
				info[7] = txtCustName.getText();
				info[8] = txtTruckCode.getText();
				info[9] = txtTruckName.getText();
				info[10] = txtLiscence.getText();
				info[11] = txtInspName.getText();
				info[12] = txtDate.getText();
				info[13] = txtComment.getText();
				
				for(int i=0; i < 13; i++) {
					if(info[i].equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), "At least one textfield is blank.", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				db = new Database();
				db.outgate(info);
				Main.mainframe.map.update = true;	
				dispose();
			}
		});
		button.setBounds(241, 288, 209, 23);
		contentPane.add(button);
		//-------------------------------------------------------------initialize
		
		if(!data[1].equals("bad")){
			txtContainer.setText(data[0]);
			txtIso.setText(data[1]);
			txtMass.setText(data[2]);
			txtSeal.setText(data[3]);
			txtDom.setText(data[4]);
		}
	}
}


package uiLayer;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import controllerLayer.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateEmp extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtAdd;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtPho;
	private JTextField txtSalery;
	private JTextField txtBank;
	private JRadioButton rdbtnIsLeader;
	
	private boolean leader;
	
	private EmployeeController empCon = new EmployeeController();

	public CreateEmp() {
		getContentPane().setLayout(null);
		
		System.out.println(leader);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtName.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligst!");
				} else if(txtAdd.getText().equalsIgnoreCase("")) {
					txtAdd.setText("Udfyld venligst!");
				} else if(txtZip.getText().equalsIgnoreCase("")) {
					txtZip.setText("Udfyld venligst!");
				} else if(txtCity.getText().equalsIgnoreCase("")) {
					txtCity.setText("Udfyld venligst!");
				} else if(txtPho.getText().equalsIgnoreCase("")) {
					txtPho.setText("Udfyld venligst!");
				} else if(txtSalery.getText().equalsIgnoreCase("")) {
					txtPho.setText("Udfyld venligst!");
				} else if(txtBank.getText().equalsIgnoreCase("")) {
					txtPho.setText("Udfyld venligst!");
				} else if(!txtName.getText().equals("Udfyld venligst!") && !txtAdd.getText().equals("Udfyld venligst!") && !txtZip.getText().equals("Udfyld venligst!") && !txtCity.getText().equals("Udfyld venligst!") && !txtPho.getText().equals("Udfyld venligst!") && !txtSalery.getText().equals("Udfyld venligst!") && !txtBank.getText().equals("Udfyld venligst!")) {
				empCon.addEmployee(txtName.getText(), txtAdd.getText(), txtZip.getText(), txtCity.getText(), txtPho.getText(), txtBank.getText(), Double.parseDouble(txtSalery.getText()), leader);
				EmployeeGUI.updateTable();
				txtName.setText("");
				txtAdd.setText("");
				txtZip.setText("");
				txtCity.setText("");
				txtPho.setText("");
				txtSalery.setText("");
				txtBank.setText("");
				}
			}
		});
		btnCreate.setBounds(10, 283, 89, 23);
		getContentPane().add(btnCreate);
		
		JButton btnAnnul = new JButton("Annuller");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(134, 283, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setBounds(66, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAdd = new JTextField();
		txtAdd.setBounds(66, 48, 157, 20);
		getContentPane().add(txtAdd);
		txtAdd.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblAdd = new JLabel("Adresse:");
		lblAdd.setBounds(10, 50, 46, 14);
		getContentPane().add(lblAdd);
		
		JLabel lblZip = new JLabel("Postnr:");
		lblZip.setBounds(10, 85, 46, 14);
		getContentPane().add(lblZip);
		
		JLabel lblCity = new JLabel("By:");
		lblCity.setBounds(10, 120, 46, 14);
		getContentPane().add(lblCity);
		
		JLabel lblTlf = new JLabel("Telefonnr:");
		lblTlf.setBounds(10, 155, 59, 14);
		getContentPane().add(lblTlf);
		
		txtZip = new JTextField();
		txtZip.setBounds(66, 82, 157, 20);
		getContentPane().add(txtZip);
		txtZip.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(66, 117, 157, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		txtPho = new JTextField();
		txtPho.setBounds(66, 152, 157, 20);
		getContentPane().add(txtPho);
		txtPho.setColumns(10);
		
		JLabel lblSalery = new JLabel("Timeløn:");
		lblSalery.setBounds(10, 189, 46, 14);
		getContentPane().add(lblSalery);
		
		txtSalery = new JTextField();
		txtSalery.setBounds(66, 186, 157, 20);
		getContentPane().add(txtSalery);
		txtSalery.setColumns(10);
		
		JLabel lblBankInfo = new JLabel("Bank:");
		lblBankInfo.setBounds(10, 223, 46, 14);
		getContentPane().add(lblBankInfo);
		
		txtBank = new JTextField();
		txtBank.setBounds(66, 220, 157, 20);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		JRadioButton rdbtnIsLeader = new JRadioButton("");
		rdbtnIsLeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!leader) {
					leader = true;
				} else {
					leader = false;
				}
				
			}
		});
		rdbtnIsLeader.setBounds(63, 253, 109, 23);
		getContentPane().add(rdbtnIsLeader);
		
		JLabel lblIsLeader = new JLabel("Leder:");
		lblIsLeader.setBounds(10, 257, 46, 14);
		getContentPane().add(lblIsLeader);
		
		}
}

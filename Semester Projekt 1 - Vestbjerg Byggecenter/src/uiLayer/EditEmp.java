package uiLayer;

import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import controllerLayer.*;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class EditEmp extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtAdd;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtPho;
	private JTextField txtBank;
	private JTextField txtSalery;
	private JRadioButton rdbtnIsLeader;
	
	private boolean leader;

	private EmployeeController empCon = new EmployeeController();

	public EditEmp(int id) {
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				String add;
				String zip;
				String city;
				String pho;
				String bank;
				Double sal = 0.0d;
				boolean isLeader;
				
				if(txtName.getText().equals("")) {
					name = empCon.getEmployee(id).getName();
				} else {
					name = txtName.getText();
				}
				if(txtAdd.getText().equals("")) {
					add = empCon.getEmployee(id).getAddress();
				} else {
					add = txtAdd.getText();
				}
				if(txtZip.getText().equals("")) {
					zip = empCon.getEmployee(id).getZip();
				} else {
					zip = txtZip.getText();
				}
				if(txtCity.getText().equals("")) {
					city = empCon.getEmployee(id).getCity();
				} else {
					city = txtCity.getText();
				}
				if(txtPho.getText().equals("")) {
					pho = empCon.getEmployee(id).getPhone();
				} else {
					pho = txtPho.getText();
				}
				if(txtBank.getText().equals("")) {
					bank = empCon.getEmployee(id).getBankInfo();
				} else {
					bank = txtBank.getText();
				}
				if(Double.valueOf(txtSalery.getText()) == sal) {
					sal = empCon.getEmployee(id).getSalery();
				} else {
					sal = Double.parseDouble(txtSalery.getText());
				}
					isLeader = leader;

				empCon.updateEmployee(id, name, add, zip, city, pho, bank, sal, isLeader);
				CustomerGUI.updateTable();
				dispose();
			}
		});
		btnSave.setBounds(10, 284, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnAnnul = new JButton("Annuller");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(134, 284, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(empCon.getEmployee(id).getName());
		txtName.setBounds(66, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAdd = new JTextField();
		txtAdd.setText(empCon.getEmployee(id).getAddress());
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
		txtZip.setText(empCon.getEmployee(id).getZip());
		txtZip.setBounds(66, 82, 157, 20);
		getContentPane().add(txtZip);
		txtZip.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setText(empCon.getEmployee(id).getCity());
		txtCity.setBounds(66, 117, 157, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		txtPho = new JTextField();
		txtPho.setText(empCon.getEmployee(id).getPhone());
		txtPho.setBounds(66, 152, 157, 20);
		getContentPane().add(txtPho);
		txtPho.setColumns(10);
		
		rdbtnIsLeader = new JRadioButton("");
		if(empCon.getEmployee(id).getIsLeader()) {
			rdbtnIsLeader.setSelected(true);
		} else {
			rdbtnIsLeader.setSelected(false);
		}
		
		rdbtnIsLeader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnIsLeader.isSelected()) {
					leader = true;
				} else {
					leader = false;
				}
			}
		});

		rdbtnIsLeader.setBounds(63, 254, 109, 23);
		getContentPane().add(rdbtnIsLeader);
		
		JLabel lblIsLeader = new JLabel("Leder:");
		lblIsLeader.setBounds(10, 258, 46, 14);
		getContentPane().add(lblIsLeader);
		
		txtBank = new JTextField();
		txtBank.setText(empCon.getEmployee(id).getBankInfo());
		txtBank.setColumns(10);
		txtBank.setBounds(66, 221, 157, 20);
		getContentPane().add(txtBank);
		
		JLabel lblBank = new JLabel("Bank:");
		lblBank.setBounds(10, 224, 46, 14);
		getContentPane().add(lblBank);
		
		txtSalery = new JTextField();
		txtSalery.setText(Double.toString(empCon.getEmployee(id).getSalery()));
		txtSalery.setColumns(10);
		txtSalery.setBounds(66, 187, 157, 20);
		getContentPane().add(txtSalery);
		
		JLabel lblSalery = new JLabel("Timeløn:");
		lblSalery.setBounds(10, 190, 46, 14);
		getContentPane().add(lblSalery);
	}
}

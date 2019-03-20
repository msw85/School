package uiLayer;

import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import controllerLayer.*;

import javax.swing.JLabel;

public class EditCust extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtAdd;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtPho;

	private CustomerController cusCon = new CustomerController();

	public EditCust(int id) {
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				String add;
				String zip;
				String city;
				String pho;
				
				if(txtName.getText().equals("")) {
					name = cusCon.getCustomer(id).getName();
				} else {
					name = txtName.getText();
				}
				if(txtAdd.getText().equals("")) {
					add = cusCon.getCustomer(id).getAddress();
				} else {
					add = txtAdd.getText();
				}
				if(txtZip.getText().equals("")) {
					zip = cusCon.getCustomer(id).getZip();
				} else {
					zip = txtZip.getText();
				}
				if(txtCity.getText().equals("")) {
					city = cusCon.getCustomer(id).getCity();
				} else {
					city = txtCity.getText();
				}
				if(txtPho.getText().equals("")) {
					pho = cusCon.getCustomer(id).getPhone();
				} else {
					pho = txtPho.getText();
				}
				cusCon.updateCustomer(id, name, add, zip, city, pho);
				CustomerGUI.updateTable();
				dispose();
			}
		});
		btnSave.setBounds(10, 201, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnAnnul = new JButton("Annuller");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(134, 201, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(cusCon.getCustomer(id).getName());
		txtName.setBounds(66, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAdd = new JTextField();
		txtAdd.setText(cusCon.getCustomer(id).getAddress());
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
		txtZip.setText(cusCon.getCustomer(id).getZip());
		txtZip.setBounds(66, 82, 157, 20);
		getContentPane().add(txtZip);
		txtZip.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setText(cusCon.getCustomer(id).getCity());
		txtCity.setBounds(66, 117, 157, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		txtPho = new JTextField();
		txtPho.setText(cusCon.getCustomer(id).getPhone());
		txtPho.setBounds(66, 152, 157, 20);
		getContentPane().add(txtPho);
		txtPho.setColumns(10);
	}
}

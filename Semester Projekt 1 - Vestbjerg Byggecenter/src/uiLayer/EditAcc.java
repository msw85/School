package uiLayer;

import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import controllerLayer.*;
import modelLayer.Account;
import modelLayer.Customer;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EditAcc extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPay;
	private JTextField txtCraft;
	private JTextField txtAmm;
	private JTextField txtPick;
	private JTextField txtBal;
	private JTextField txtCust;
	private Customer custToAdd;
	
	private AccountController accCon = new AccountController();
	private CustomerController cusCon = new CustomerController();

	public EditAcc(int id) {
		getContentPane().setLayout(null);

		
		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pay;
				String craft;
				String amm;
				String pick;
				String bal;
				
				if(txtPay.getText().equals("")) {
					pay = accCon.getAccount(id).getPaymentAgreement();
				} else {
					pay = txtPay.getText();
				}
				if(txtCraft.getText().equals("")) {
					craft = Integer.toString(accCon.getAccount(id).getCraftmansDiscount());
				} else {
					craft = txtCraft.getText();
				}
				if(txtAmm.getText().equals("")) {
					amm = Integer.toString(accCon.getAccount(id).getAmountDiscount());
				} else {
					amm = txtAmm.getText();
				}
				if(txtPick.getText().equals("")) {
					pick = Integer.toString(accCon.getAccount(id).getPickupDiscount());
				} else {
					pick = txtPick.getText();
				}
				if(txtBal.getText().equals("")) {
					bal = Double.toString(accCon.getAccount(id).getBalance());
				} else {
					bal = txtBal.getText();
				}
				accCon.updateAccount(id, pay, Integer.parseInt(craft), Integer.parseInt(amm), Integer.parseInt(pick), Double.parseDouble(bal));
				accCon.addCustomer(id, custToAdd.getID());
				AccountGUI.update();
				dispose();
			}
		});
		btnSave.setBounds(10, 227, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnAnnul = new JButton("Annuller");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(134, 227, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtPay = new JTextField();
		txtPay.setText(accCon.getAccount(id).getPaymentAgreement());
		txtPay.setBounds(66, 11, 157, 20);
		getContentPane().add(txtPay);
		txtPay.setColumns(10);
		
		txtCraft = new JTextField();
		txtCraft.setText(Integer.toString(accCon.getAccount(id).getCraftmansDiscount()));
		txtCraft.setBounds(66, 48, 157, 20);
		getContentPane().add(txtCraft);
		txtCraft.setColumns(10);
		
		JLabel lblPay = new JLabel("Betalingbetingelser:");
		lblPay.setBounds(10, 15, 46, 14);
		getContentPane().add(lblPay);
		
		JLabel lblCraft = new JLabel("Håndværkerrabat:");
		lblCraft.setBounds(10, 50, 46, 14);
		getContentPane().add(lblCraft);
		
		JLabel lblAmm = new JLabel("Mængderabat:");
		lblAmm.setBounds(10, 85, 46, 14);
		getContentPane().add(lblAmm);
		
		JLabel lblPick = new JLabel("Afhentningsrabat:");
		lblPick.setBounds(10, 120, 46, 14);
		getContentPane().add(lblPick);
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setBounds(10, 155, 59, 14);
		getContentPane().add(lblBal);
		
		txtAmm = new JTextField();
		txtAmm.setText(Integer.toString(accCon.getAccount(id).getAmountDiscount()));
		txtAmm.setBounds(66, 82, 157, 20);
		getContentPane().add(txtAmm);
		txtAmm.setColumns(10);
		
		txtPick = new JTextField();
		txtPick.setText(Integer.toString(accCon.getAccount(id).getPickupDiscount()));
		txtPick.setBounds(66, 117, 157, 20);
		getContentPane().add(txtPick);
		txtPick.setColumns(10);
		
		txtBal = new JTextField();
		txtBal.setText(Double.toString(accCon.getAccount(id).getBalance()));
		txtBal.setBounds(66, 152, 157, 20);
		getContentPane().add(txtBal);
		txtBal.setColumns(10);
		
		JLabel lblCust = new JLabel("Kunde:");
		lblCust.setBounds(10, 191, 46, 14);
		getContentPane().add(lblCust);
		if(accCon.getAccount(id).getCustomer() != null) {
			txtCust = new JTextField();
			txtCust.setEnabled(true);
			txtCust.setText(Integer.toString(accCon.getAccount(id).getCustomer().getID()) + " " + accCon.getAccount(id).getCustomer().getName());
			txtCust.setBounds(66, 188, 157, 20);
			getContentPane().add(txtCust);
			txtCust.setColumns(10);
		} else {
			JComboBox<Customer> comboCust = new JComboBox<Customer>();
			comboCust.setEnabled(true);
			for (int i = 0; i < cusCon.size(); i++) {
				Customer c = cusCon.getCustomer(i);
				comboCust.addItem(c);
			}
			comboCust.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					custToAdd = (Customer) comboCust.getSelectedItem();
				}
			});
			comboCust.setBounds(66, 188, 157, 20);
			getContentPane().add(comboCust);
		}
	}
}

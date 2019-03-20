package uiLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllerLayer.AccountController;
import controllerLayer.CustomerController;
import modelLayer.Product;

public class ShowAcc extends JDialog {
	
	private JTextField txtPay;
	private JTextField txtCraft;
	private JTextField txtAmm;
	private JTextField txtPick;
	private JTextField txtBal;
	private JTextField txtCust;
	
	private AccountController accCon = new AccountController();

	public ShowAcc(int id) {
		getContentPane().setLayout(null);
		
		JButton btnAnnul = new JButton("Luk");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(187, 227, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtPay = new JTextField();
		txtPay.setText(accCon.getAccount(id).getPaymentAgreement());
		txtPay.setEditable(false);
		txtPay.setBounds(119, 11, 157, 20);
		getContentPane().add(txtPay);
		txtPay.setColumns(10);
		
		txtCraft = new JTextField();
		txtCraft.setText(Integer.toString(accCon.getAccount(id).getCraftmansDiscount()));
		txtCraft.setEditable(false);
		txtCraft.setBounds(119, 48, 157, 20);
		getContentPane().add(txtCraft);
		txtCraft.setColumns(10);
		
		JLabel lblPay = new JLabel("Betalingsbetingelser:");
		lblPay.setBounds(10, 15, 109, 14);
		getContentPane().add(lblPay);
		
		JLabel lblCraft = new JLabel("Håndværkerrabat:");
		lblCraft.setBounds(10, 50, 99, 14);
		getContentPane().add(lblCraft);
		
		JLabel lblPick = new JLabel("Afhentningsrabat:");
		lblPick.setBounds(10, 85, 99, 14);
		getContentPane().add(lblPick);
		
		JLabel lblAmm = new JLabel("Mængderabat:");
		lblAmm.setBounds(10, 120, 99, 14);
		getContentPane().add(lblAmm);
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setBounds(10, 155, 59, 14);
		getContentPane().add(lblBal);
		
		txtAmm = new JTextField();
		txtAmm.setText(Integer.toString(accCon.getAccount(id).getAmountDiscount()));
		txtAmm.setEditable(false);
		txtAmm.setBounds(119, 82, 157, 20);
		getContentPane().add(txtAmm);
		txtAmm.setColumns(10);
		
		txtPick = new JTextField();
		txtPick.setText(Integer.toString(accCon.getAccount(id).getPickupDiscount()));
		txtPick.setEditable(false);
		txtPick.setBounds(119, 117, 157, 20);
		getContentPane().add(txtPick);
		txtPick.setColumns(10);
		
		txtBal = new JTextField();
		txtBal.setText(Double.toString(accCon.getAccount(id).getBalance()));
		txtBal.setEditable(false);
		txtBal.setBounds(119, 152, 157, 20);
		getContentPane().add(txtBal);
		txtBal.setColumns(10);
		
		txtCust = new JTextField();
		if(accCon.getAccount(id).getCustomer() == null) {
			txtCust.setText("Ingen kunde tilknyttet");
		} else {
			txtCust.setText(Integer.toString(accCon.getAccount(id).getCustomer().getID()) + " " + accCon.getAccount(id).getCustomer().getName());
		}
		txtCust.setEditable(false);
		txtCust.setBounds(118, 186, 158, 20);
		getContentPane().add(txtCust);
		txtCust.setColumns(10);
		
		JLabel lblCust = new JLabel("Kunde:");
		lblCust.setBounds(10, 189, 46, 14);
		getContentPane().add(lblCust);

	}

}

package uiLayer;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import controllerLayer.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAcc extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtPay;
	private JTextField txtCraft;
	private JTextField txtAmm;
	private JTextField txtPick;
	private JTextField txtBal;

	private AccountController accCon = new AccountController();

	public CreateAcc() {
		getContentPane().setLayout(null);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPay.getText().equalsIgnoreCase("")) {
					txtPay.setText("Udfyld venligst!");
				} else if(txtCraft.getText().equalsIgnoreCase("")) {
					txtCraft.setText("Udfyld venligst!");
				} else if(txtAmm.getText().equalsIgnoreCase("")) {
					txtAmm.setText("Udfyld venligst!");
				} else if(txtPick.getText().equalsIgnoreCase("")) {
					txtPick.setText("Udfyld venligst!");
				} else if(txtBal.getText().equalsIgnoreCase("")) {
					txtBal.setText("Udfyld venligst!");
				} else if(!txtPay.getText().equals("Udfyld venligst!") && !txtCraft.getText().equals("Udfyld venligst!") && !txtAmm.getText().equals("Udfyld venligst!") && !txtPick.getText().equals("Udfyld venligst!") && !txtBal.getText().equals("Udfyld venligst!")) {
				accCon.addAccount(txtPay.getText(), Integer.valueOf(txtCraft.getText()), Integer.valueOf(txtAmm.getText()), Integer.valueOf(txtPick.getText()), Double.parseDouble(txtBal.getText()));
				AccountGUI.update();
				txtPay.setText("");
				txtCraft.setText("");
				txtAmm.setText("");
				txtPick.setText("");
				txtBal.setText("");
				}
			}
		});
		btnCreate.setBounds(10, 201, 89, 23);
		getContentPane().add(btnCreate);
		
		JButton btnAnnul = new JButton("Annuller");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(258, 201, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtPay = new JTextField();
		txtPay.setBounds(112, 12, 235, 20);
		getContentPane().add(txtPay);
		txtPay.setColumns(10);
		
		txtCraft = new JTextField();
		txtCraft.setBounds(112, 48, 235, 20);
		getContentPane().add(txtCraft);
		txtCraft.setColumns(10);
		
		JLabel lblPayment = new JLabel("Betalingsaftale:");
		lblPayment.setBounds(10, 15, 89, 14);
		getContentPane().add(lblPayment);
		
		JLabel lblCraftD = new JLabel("Håndværkerrabat:");
		lblCraftD.setBounds(10, 50, 102, 14);
		getContentPane().add(lblCraftD);
		
		JLabel lblAmmD = new JLabel("Mængderabat:");
		lblAmmD.setBounds(10, 85, 102, 14);
		getContentPane().add(lblAmmD);
		
		JLabel lblPickD = new JLabel("Afhentningsrabat:");
		lblPickD.setBounds(10, 120, 102, 14);
		getContentPane().add(lblPickD);
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setBounds(10, 155, 59, 14);
		getContentPane().add(lblBal);
		
		txtAmm = new JTextField();
		txtAmm.setBounds(112, 82, 235, 20);
		getContentPane().add(txtAmm);
		txtAmm.setColumns(10);
		
		txtPick = new JTextField();
		txtPick.setBounds(112, 117, 235, 20);
		getContentPane().add(txtPick);
		txtPick.setColumns(10);
		
		txtBal = new JTextField();
		txtBal.setBounds(112, 152, 235, 20);
		getContentPane().add(txtBal);
		txtBal.setColumns(10);
	}
}

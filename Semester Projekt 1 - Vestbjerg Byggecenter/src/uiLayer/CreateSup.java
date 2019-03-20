package uiLayer;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import controllerLayer.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CreateSup extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextPane txtDesc;
	private JTextField txtBank;
	
	private SupplierController supCon = new SupplierController();

	public CreateSup() {
		getContentPane().setLayout(null);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligst!");
				} else if(txtDesc.getText().equalsIgnoreCase("")) {
					txtDesc.setText("Udfyld venligst!");
				} else if(txtBank.getText().equalsIgnoreCase("")) {
					txtBank.setText("Udfyld venligst!");
				} else if(!txtName.getText().equals("Udfyld venligst!") && !txtBank.getText().equals("Udfyld venligst!")) {
				supCon.createSupplier(txtName.getText(), txtDesc.getText(), txtBank.getText());
				SupplierGUI.updateTable();
				txtName.setText("");
				txtDesc.setText("");
				txtBank.setText("");
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
		btnAnnul.setBounds(186, 201, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setBounds(118, 11, 161, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtBank = new JTextField();
		txtBank.setBounds(118, 170, 161, 20);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblBank = new JLabel("Bankinformation:");
		lblBank.setBounds(10, 172, 108, 14);
		getContentPane().add(lblBank);
		
		JLabel lblDesc = new JLabel("Beskrivelse:");
		lblDesc.setBounds(10, 40, 68, 14);
		getContentPane().add(lblDesc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(118, 40, 161, 115);
		getContentPane().add(scrollPane);
		
		txtDesc = new JTextPane();
		scrollPane.setViewportView(txtDesc);

	}
}

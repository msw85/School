package uiLayer;

import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import controllerLayer.*;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class EditSup extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextPane txtDesc;
	private JTextField txtBank;

	private SupplierController supCon = new SupplierController();

	public EditSup(int id) {
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				String desc;
				String bank;
				
				if(txtName.getText().equals("")) {
					name = supCon.getSupplier(id).getName();
				} else {
					name = txtName.getText();
				}
				if(txtDesc.getText().equals("")) {
					desc = supCon.getSupplier(id).getDescription();
				} else {
					desc = txtDesc.getText();
				}
				if(txtBank.getText().equals("")) {
					bank = supCon.getSupplier(id).getBankInfo();
				} else {
					bank = txtBank.getText();
				}
				supCon.updateSupplier(id, name, desc, bank);
				SupplierGUI.updateTable();
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
		btnAnnul.setBounds(190, 201, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(supCon.getSupplier(id).getName());
		txtName.setBounds(114, 11, 165, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblDesc = new JLabel("Beskrivelse:");
		lblDesc.setBounds(10, 50, 62, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblBank = new JLabel("Bankinformation:");
		lblBank.setBounds(10, 173, 102, 14);
		getContentPane().add(lblBank);
		
		txtBank = new JTextField();
		txtBank.setText(supCon.getSupplier(id).getBankInfo());
		txtBank.setBounds(114, 170, 165, 20);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 42, 169, 121);
		getContentPane().add(scrollPane);
		
		txtDesc = new JTextPane();
		scrollPane.setViewportView(txtDesc);
		txtDesc.setText(supCon.getSupplier(id).getDescription());
	}
}

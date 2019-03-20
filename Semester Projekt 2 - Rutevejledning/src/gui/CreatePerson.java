package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PersonController;

public class CreatePerson extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JCheckBox chkbxValuedCust;
	private PersonController personCtr;

	public CreatePerson() {
		getContentPane().setLayout(null);
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}

		txtPhoneNo = new JTextField();
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(175, 50, 450, 40);
		getContentPane().add(txtPhoneNo);

		JLabel lblPhoneNo = new JLabel("Telefonnummer:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNo.setBounds(10, 60, 155, 20);
		getContentPane().add(lblPhoneNo);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 120, 109, 20);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(175, 110, 450, 40);
		getContentPane().add(txtName);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 180, 109, 20);
		getContentPane().add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 170, 450, 40);
		getContentPane().add(txtAddress);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 240, 155, 20);
		getContentPane().add(lblZip);

		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(175, 230, 450, 40);
		getContentPane().add(txtZip);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 300, 109, 20);
		getContentPane().add(lblCity);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(175, 290, 450, 40);
		getContentPane().add(txtCity);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 360, 109, 20);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 350, 450, 40);
		getContentPane().add(txtEmail);

		JLabel lblCreateCustomer = new JLabel("Opret Kunde");
		lblCreateCustomer.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCreateCustomer.setBounds(10, 11, 190, 33);
		getContentPane().add(lblCreateCustomer);

		JButton btnSaveCust = new JButton("Gem");
		btnSaveCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if the different textFields is empty.
				// If they are empty then a text will be shown and asks the user to fill in the
				// blank spots.
				if (txtName.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligst!");
				} else if (txtAddress.getText().equalsIgnoreCase("")) {
					txtAddress.setText("Udfyld venligst!");
				} else if (txtZip.getText().equalsIgnoreCase("")) {
					txtZip.setText("Udfyld venligst!");
				} else if (txtCity.getText().equalsIgnoreCase("")) {
					txtCity.setText("Udfyld venligst!");
				} else if (txtPhoneNo.getText().equalsIgnoreCase("")) {
					txtPhoneNo.setText("Udfyld venligst!");
				} else if (txtEmail.getText().equalsIgnoreCase("")) {
					txtEmail.setText("Udfyld venligst!");
				} 
				// Checks if the textfields is not empty or equal to the filled in text.
				if (!txtName.getText().equals("Udfyld venligst!")
						&& !txtAddress.getText().equals("Udfyld venligst!")
						&& !txtZip.getText().equals("Udfyld venligst!") && !txtCity.getText().equals("Udfyld venligst!")
						&& !txtPhoneNo.getText().equals("Udfyld venligst!")
						&& !txtEmail.getText().equals("Udfyld venligst!")) {
					// Checks if the user has put in a valid email and phoneNo.
					UiUtil.validEmailInput(txtEmail.getText());
					UiUtil.validPhoneInput(txtPhoneNo.getText());
					try {
						// Grabs the text from textFields and Creates the Customer.
						personCtr.createCustomer(txtName.getText(), txtAddress.getText(), txtZip.getText(),
								txtCity.getText(), txtPhoneNo.getText(), txtEmail.getText(), "Customer",
								chkbxValuedCust.isSelected());
						// Calls PersonGUI updateTable and updates it.
						PersonGUI.updateTable();
						// Dispose closes the dialogbox.
						dispose();
						// Uncheck the selected box.
						chkbxValuedCust.setSelected(false);
					} catch (SQLException ea) {
						ConfirmationDialog.showMessageDialog(chkbxValuedCust, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnSaveCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSaveCust.setBounds(432, 455, 193, 35);
		getContentPane().add(btnSaveCust);

		JButton btnClearFields = new JButton("Ryd felter");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear buttons sets every textField to empty
				txtName.setText("");
				txtAddress.setText("");
				txtZip.setText("");
				txtCity.setText("");
				txtPhoneNo.setText("");
				txtEmail.setText("");
				chkbxValuedCust.setSelected(false);
			}
		});
		btnClearFields.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClearFields.setBounds(175, 455, 193, 35);
		getContentPane().add(btnClearFields);

		chkbxValuedCust = new JCheckBox("Værdifuld kunde");
		chkbxValuedCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chkbxValuedCust.setBounds(175, 410, 174, 25);
		getContentPane().add(chkbxValuedCust);

	}
}

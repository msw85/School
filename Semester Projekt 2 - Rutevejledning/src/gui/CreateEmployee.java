package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PersonController;

public class CreateEmployee extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTextField txtSalary;
	private JTextField txtAccountInfo;
	private JTextField txtTitle;
	private PersonController personCtr;

	public CreateEmployee() {
		getContentPane().setLayout(null);
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}

		txtPhoneNo = new JTextField();
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(175, 50, 450, 40);
		getContentPane().add(txtPhoneNo);

		JLabel lblCreateEmployee = new JLabel("Opret medarbejder");
		lblCreateEmployee.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCreateEmployee.setBounds(10, 11, 307, 33);
		getContentPane().add(lblCreateEmployee);

		JLabel lblPhoneNo = new JLabel("Telefonnummer:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNo.setBounds(10, 60, 155, 20);
		getContentPane().add(lblPhoneNo);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(175, 110, 450, 40);
		getContentPane().add(txtName);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 170, 450, 40);
		getContentPane().add(txtAddress);

		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(175, 230, 450, 40);
		getContentPane().add(txtZip);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(175, 290, 450, 40);
		getContentPane().add(txtCity);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 350, 450, 40);
		getContentPane().add(txtEmail);

		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(175, 410, 450, 40);
		getContentPane().add(txtSalary);

		txtAccountInfo = new JTextField();
		txtAccountInfo.setColumns(10);
		txtAccountInfo.setBounds(175, 470, 450, 40);
		getContentPane().add(txtAccountInfo);

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(175, 530, 450, 40);
		getContentPane().add(txtTitle);

		JLabel lblTitle = new JLabel("Titel:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(10, 540, 109, 20);
		getContentPane().add(lblTitle);

		JLabel lblAccountInfo = new JLabel("Bankinfo:");
		lblAccountInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccountInfo.setBounds(10, 480, 109, 20);
		getContentPane().add(lblAccountInfo);

		JLabel lblSalary = new JLabel("Løn:");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSalary.setBounds(10, 420, 109, 20);
		getContentPane().add(lblSalary);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 360, 109, 20);
		getContentPane().add(lblEmail);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 300, 109, 20);
		getContentPane().add(lblCity);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 240, 155, 20);
		getContentPane().add(lblZip);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 180, 109, 20);
		getContentPane().add(lblAddress);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 120, 109, 20);
		getContentPane().add(lblName);

		JButton btnRydFelter = new JButton("Ryd felter");
		btnRydFelter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Clear button sets every textField to empty.
				txtPhoneNo.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtZip.setText("");
				txtCity.setText("");
				txtEmail.setText("");
				txtSalary.setText("");
				txtAccountInfo.setText("");
				txtTitle.setText("");
			}
		});
		btnRydFelter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRydFelter.setBounds(175, 585, 193, 35);
		getContentPane().add(btnRydFelter);

		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
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
				} else if (txtSalary.getText().equalsIgnoreCase("")) {
					txtSalary.setText("Udfyld venligst!");
				} else if (txtAccountInfo.getText().equalsIgnoreCase("")) {
					txtAccountInfo.setText("Udfyld venligst!");
				} else if (txtTitle.getText().equalsIgnoreCase("")) {
					txtTitle.setText("Udfyld venligst!");
				}
				// Checks if the textfields is not empty or equal to the filled in text.
				if (!txtName.getText().equals("Udfyld venligst!") && !txtAddress.getText().equals("Udfyld venligst!")
						&& !txtZip.getText().equals("Udfyld venligst!") && !txtCity.getText().equals("Udfyld venligst!")
						&& !txtPhoneNo.getText().equals("Udfyld venligst!")
						&& !txtEmail.getText().equals("Udfyld venligst!")
						&& !txtSalary.getText().equals("Udfyld venligst!")
						&& !txtAccountInfo.getText().equals("Udfyld venligst!")
						&& !txtTitle.getText().equals("Udfyld venligst!")) {
					try {
						// Grabs the text from textFields and updates the Employee.
						personCtr.createEmployee(txtName.getText(), txtAddress.getText(), txtZip.getText(),
								txtCity.getText(), txtPhoneNo.getText(), txtEmail.getText(), "Employee",
								Double.parseDouble(txtSalary.getText()), txtAccountInfo.getText(), txtTitle.getText());
						// Calls EmployGUI updateTable and updates it.
						EmployeeGUI.updateTable();
						// Dispose closes the dialogbox.
						dispose();
					} catch (SQLException ea) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(432, 585, 193, 35);
		getContentPane().add(btnSave);
	}
}

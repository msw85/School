package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PersonController;
import model.Customer;
import model.Person;

public class EditPerson extends JDialog {

	private static final long serialVersionUID = 1L;
	private PersonController personCtr;
	private JTextField txtName;
	private JTextField txtPhoneNo;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtEmail;
	private static List<Person> allCustomers;
	
	public EditPerson(Customer cc) {
		getContentPane().setLayout(null);
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
			allCustomers = personCtr.findAllCustomers();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		txtName = new JTextField(cc.getName());
		txtName.setColumns(10);
		txtName.setBounds(175, 110, 450, 40);
		getContentPane().add(txtName);

		JLabel lblUpdateCustomer = new JLabel("Opdater kunde");
		lblUpdateCustomer.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblUpdateCustomer.setBounds(10, 11, 225, 33);
		getContentPane().add(lblUpdateCustomer);

		JLabel lblPhoneNo = new JLabel("Telefonnummer:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNo.setBounds(10, 60, 155, 20);
		getContentPane().add(lblPhoneNo);

		txtPhoneNo = new JTextField(cc.getPhoneNo());
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(175, 50, 450, 40);
		getContentPane().add(txtPhoneNo);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 120, 109, 20);
		getContentPane().add(lblName);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 180, 109, 20);
		getContentPane().add(lblAddress);

		txtAddress = new JTextField(cc.getAddress());
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 170, 450, 40);
		getContentPane().add(txtAddress);

		txtZip = new JTextField(cc.getZip());
		txtZip.setColumns(10);
		txtZip.setBounds(175, 230, 450, 40);
		getContentPane().add(txtZip);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 240, 155, 20);
		getContentPane().add(lblZip);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 300, 109, 20);
		getContentPane().add(lblCity);

		txtCity = new JTextField(cc.getCity());
		txtCity.setColumns(10);
		txtCity.setBounds(175, 290, 450, 40);
		getContentPane().add(txtCity);

		txtEmail = new JTextField(cc.getEmail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 350, 450, 40);
		getContentPane().add(txtEmail);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 360, 109, 20);
		getContentPane().add(lblEmail);

		JCheckBox checkBox = new JCheckBox("Værdifuld kunde");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		checkBox.setBounds(175, 410, 174, 25);
		getContentPane().add(checkBox);

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
				}
				// Checks if the textfields is not empty or equal to the filled in text.
				if (!txtName.getText().equals("Udfyld venligst!") && !txtAddress.getText().equals("Udfyld venligst!")
						&& !txtZip.getText().equals("Udfyld venligst!") && !txtCity.getText().equals("Udfyld venligst!")
						&& !txtPhoneNo.getText().equals("Udfyld venligst!")
						&& !txtEmail.getText().equals("Udfyld venligst!")) {
					// Checks if the user has put in a valid email and phoneNo.
					UiUtil.validEmailInput(txtEmail.getText());
					UiUtil.validPhoneInput(txtPhoneNo.getText());
					try {
						// Grabs the text from textFields and updates the Customer.
						personCtr.updateCustomer(cc.getId(), txtName.getText(), txtAddress.getText(), txtZip.getText(),
								txtCity.getText(), txtPhoneNo.getText(), txtEmail.getText(), "Customer",
								checkBox.isSelected());
						// Calls PersonGUI updateTable and updates it.
						PersonGUI.updateTable();
						// Dispose closes the dialogbox.
						dispose();
					} catch (SQLException ea) {
						// Prints stacktrace if an error occured.
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		// Deleting an Customer
		JButton btnDeleteCust = new JButton("Slet kunde");
		btnDeleteCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Checks if the textField with numbers it not equal to null.
				if (txtPhoneNo.getText() != null) {
					// If succeeded, then it loops through the list with employees
					for (int i = 0; i < allCustomers.size(); i++) {
						// Gets the person with the PhoneNo and typcastes it to a customer.
						Person per = allCustomers.get(i);
						// Checks if the phoneNo is equal to the textField phoneNo.
						if (per.getPhoneNo().equals(txtPhoneNo.getText())) {
							try {
								// If the customer exists, then a prompt box will come up, and ask the user
								// if they want to delete the customer.
								if (confirmations()) {
									//Checks if person is on an order
									if(personCtr.inUse(per)) {
										errorMessage();
									}
									else {
									// Calls personController and deletes the person.
									personCtr.deletePerson(per);
									// updates the table
									PersonGUI.updateTable();
									dispose();
									}
								}
							} catch (SQLException e) {
								ConfirmationDialog.showMessageDialog(txtPhoneNo, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
			}
		});

		btnDeleteCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteCust.setBounds(175, 455, 193, 35);
		getContentPane().add(btnDeleteCust);

		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(432, 455, 193, 35);
		getContentPane().add(btnSave);
	}
	
	// Creates a diablogBox to promt the user with a yes and no option, when
	// deleting an person.
	private boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(this, "Er du sikker på at du vil slette denne kunde?",
				"Validering", optionType);
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	public void errorMessage() {
		JOptionPane.showMessageDialog(this, "Den valgte customer har order, så ordrelinje og ordre skal slettes først", "Information",
				JOptionPane.WARNING_MESSAGE);
	}
}

package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PersonController;
import model.Employee;
import model.Person;

public class EditEmployee extends JDialog {

	private static final long serialVersionUID = 1L;
	private PersonController personCtr;
	private JTextField txtTitle;
	private JTextField txtAccountInfo;
	private JTextField txtSalary;
	private JTextField txtEmail;
	private JTextField txtCity;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZip;
	private static List<Person> allEmployee;

	public EditEmployee(Employee emp) {
		getContentPane().setLayout(null);
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
			allEmployee = personCtr.findAllEmployees();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

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
						personCtr.updateEmployee(emp.getId(), txtName.getText(), txtAddress.getText(), txtZip.getText(),
								txtCity.getText(), txtPhoneNo.getText(), txtEmail.getText(), "Employee",
								Double.parseDouble(txtSalary.getText()), txtAccountInfo.getText(), txtTitle.getText());
						// Calls EmployGUI updateTable and updates it.
						EmployeeGUI.updateTable();
						// Dispose closes the dialogbox.
						dispose();
					} catch (SQLException ea) {
						ConfirmationDialog.showMessageDialog(txtCity, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(432, 585, 193, 35);
		getContentPane().add(btnSave);

		txtTitle = new JTextField(emp.getTitle());
		txtTitle.setColumns(10);
		txtTitle.setBounds(175, 530, 450, 40);
		getContentPane().add(txtTitle);

		JLabel lblTitle = new JLabel("Titel:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(10, 540, 109, 20);
		getContentPane().add(lblTitle);

		txtAccountInfo = new JTextField(emp.getAccountInfo());
		txtAccountInfo.setColumns(10);
		txtAccountInfo.setBounds(175, 470, 450, 40);
		getContentPane().add(txtAccountInfo);

		JLabel lblAccountInfo = new JLabel("Bankinfo:");
		lblAccountInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAccountInfo.setBounds(10, 480, 109, 20);
		getContentPane().add(lblAccountInfo);

		txtSalary = new JTextField(emp.getSalary().toString());
		txtSalary.setColumns(10);
		txtSalary.setBounds(175, 410, 450, 40);
		getContentPane().add(txtSalary);

		JLabel lblSalary = new JLabel("Løn:");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSalary.setBounds(10, 420, 109, 20);
		getContentPane().add(lblSalary);

		txtEmail = new JTextField(emp.getEmail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 350, 450, 40);
		getContentPane().add(txtEmail);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 360, 109, 20);
		getContentPane().add(lblEmail);

		txtCity = new JTextField(emp.getCity());
		txtCity.setColumns(10);
		txtCity.setBounds(175, 290, 450, 40);
		getContentPane().add(txtCity);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 300, 109, 20);
		getContentPane().add(lblCity);

		JLabel lblCreateEmployee = new JLabel("Opdater medarbejder");
		lblCreateEmployee.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCreateEmployee.setBounds(10, 11, 307, 33);
		getContentPane().add(lblCreateEmployee);

		JLabel lblPhoneNo = new JLabel("Telefonnummer:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNo.setBounds(10, 60, 155, 20);
		getContentPane().add(lblPhoneNo);

		txtPhoneNo = new JTextField(emp.getPhoneNo());
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(175, 50, 450, 40);
		getContentPane().add(txtPhoneNo);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 120, 109, 20);
		getContentPane().add(lblName);

		txtName = new JTextField(emp.getName());
		txtName.setColumns(10);
		txtName.setBounds(175, 110, 450, 40);
		getContentPane().add(txtName);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 180, 109, 20);
		getContentPane().add(lblAddress);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 240, 155, 20);
		getContentPane().add(lblZip);

		txtAddress = new JTextField(emp.getAddress());
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 170, 450, 40);
		getContentPane().add(txtAddress);

		txtZip = new JTextField(emp.getZip());
		txtZip.setColumns(10);
		txtZip.setBounds(175, 230, 450, 40);
		getContentPane().add(txtZip);
	
	
	// Deleting an employee
	JButton btnLetGo = new JButton("Fyr medarbejder");
	btnLetGo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			// Checks if the textField with numbers it not equal to null.
			if (txtPhoneNo.getText() != null) {
				// If succeeded, then it loops through the list with employees.
				for (int i = 0; i < allEmployee.size(); i++) {
					// Gets the person with the PhoneNo and typcastes it to a employee.
					Person per = (Employee) allEmployee.get(i);
					// Checks if the phoneNo is equal to the textField phoneNo.
					if (per.getPhoneNo().equals(txtPhoneNo.getText())) {
						try {
							// If the employee exists, then a prompt box will come up, and ask the user
							// if they want to delete the employee.
							if (confirmations()) {
								if(personCtr.inUse(per)) {
									errorMessage();
								}
								else {								
									// Calls personController and deletes the person.
									personCtr.deletePerson(per);
									// updates the table
									EmployeeGUI.updateTable();
									dispose();
								}
							}
						} catch (SQLException e) {
							ConfirmationDialog.showMessageDialog(txtCity, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			}
		}
	});
	
	btnLetGo.setFont(new Font("Tahoma", Font.PLAIN, 20));
	btnLetGo.setBounds(175, 585, 193, 35);
	getContentPane().add(btnLetGo);
	}
	// Creates a diablogBox to promt the user with a yes and no option, when
	// deleting an person.
	private boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(this, "Er du sikker på at du vil fyre denne medarbejder?",
				"Validering", optionType);
		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
	
	public void errorMessage() {
		JOptionPane.showMessageDialog(this, "Den valgte empolyee har order, så ordrelinje og ordre skal slettes først", "Information",
				JOptionPane.WARNING_MESSAGE);
	}
}

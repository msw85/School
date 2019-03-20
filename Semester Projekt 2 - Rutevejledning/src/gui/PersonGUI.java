package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.PersonController;
import model.Customer;
import model.Person;

public class PersonGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtPhone;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JScrollPane scrollPane;
	private JCheckBox chkbxValuedCust;
	private static DefaultTableModel d;
	private JTable table;
	private static PersonController personCtr;
	private static List<Person> allCustomers;
	private static Object thisClass;

	public PersonGUI() {
		thisClass = this;
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
			// Getting all Customers and saves them into A List.
			allCustomers = personCtr.findAllCustomers();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		// Creating a new instance of UiUtil and gets the defaulttablemodel
		d = UiUtil.getTable();

		// Creating an Array with strings.
		String[] columNames = { "Telefonnummer", "Navn", "Adresse", "Postnummer", "By", "E-Mail", "Værdifuld" };

		// loops through the array of strings and adds it to columns.
		for (String string : columNames) {
			d.addColumn(string);
		}

		setSize(1346, 718);
		setLayout(null);

		JLabel lblCust = new JLabel("Kunder");
		lblCust.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCust.setBounds(10, 11, 109, 25);
		add(lblCust);

		JLabel lblPhone = new JLabel("Telefonnummer:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhone.setBounds(10, 55, 155, 20);
		add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(175, 45, 450, 40);
		add(txtPhone);

		// Clear the textField
		JButton btnClear = new JButton("Ryd felter");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Makes the textField empty.
				txtPhone.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtZip.setText("");
				txtCity.setText("");
				txtEmail.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(175, 96, 150, 35);
		add(btnClear);

		// Find customer.
		JButton btnFindCust = new JButton("Find kunde");
		btnFindCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Checks if the textField with numbers it not equal to null.
				if (txtPhone.getText() != null) {
					// If succeeded, then it loops through the list with Customers.
					for (int i = 0; i < allCustomers.size(); i++) {
						// Gets the customer with the PhoneNo and typcastes it to a customer.
						Customer cust = (Customer) allCustomers.get(i);
						if (cust.getPhoneNo().equals(txtPhone.getText())) {
							// Fills in the text matching with the person found.
							txtName.setText(cust.getName());
							txtAddress.setText(cust.getAddress());
							txtZip.setText(cust.getZip());
							txtCity.setText(cust.getCity());
							txtEmail.setText(cust.getEmail());
							chkbxValuedCust.setSelected(cust.getIsValuedCust());
						}
					}
				}
			}
		});
		btnFindCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFindCust.setBounds(477, 96, 150, 35);
		add(btnFindCust);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(175, 160, 450, 40);
		txtName.setEnabled(false);
		add(txtName);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 240, 450, 40);
		txtAddress.setEnabled(false);
		add(txtAddress);

		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(175, 320, 450, 40);
		txtZip.setEnabled(false);
		add(txtZip);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(175, 400, 450, 40);
		txtCity.setEnabled(false);
		add(txtCity);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 480, 450, 40);
		txtEmail.setEnabled(false);
		add(txtEmail);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 490, 109, 20);
		add(lblEmail);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 410, 109, 20);
		add(lblCity);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 330, 155, 20);
		add(lblZip);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 250, 109, 20);
		add(lblAddress);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 170, 109, 20);
		add(lblName);

		chkbxValuedCust = new JCheckBox("V\u00E6rdifuld kunde");
		chkbxValuedCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chkbxValuedCust.setBounds(175, 560, 174, 25);
		chkbxValuedCust.setEnabled(false);
		add(chkbxValuedCust);

		// Creates a customer.
		JButton btnCreateCust = new JButton("Opret kunde");
		btnCreateCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Creates a new instance of CreatePersonGUI.
				CreatePerson createPerson = new CreatePerson();
				createPerson.setSize(655, 545);
				createPerson.setVisible(true);
			}
		});

		btnCreateCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateCust.setBounds(475, 672, 150, 35);
		add(btnCreateCust);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(673, 0, 8, 718);
		add(separator);

		JLabel lblCustList = new JLabel("Kundeliste");
		lblCustList.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCustList.setBounds(691, 11, 155, 25);
		add(lblCustList);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(691, 45, 645, 662);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(d);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Grabs the information at the table and puts it into the matching textFields.
				int selectedRow = table.getSelectedRow();
				txtPhone.setText(table.getValueAt(selectedRow, 0).toString());
				txtName.setText(table.getValueAt(selectedRow, 1).toString());
				txtAddress.setText(table.getValueAt(selectedRow, 2).toString());
				txtZip.setText(table.getValueAt(selectedRow, 3).toString());
				txtCity.setText(table.getValueAt(selectedRow, 4).toString());
				txtEmail.setText(table.getValueAt(selectedRow, 5).toString());
			}
		});

		for (int i = 0; i < allCustomers.size(); i++) {
			Customer cust = (Customer) allCustomers.get(i);
			String[] allCust = { cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getZip(), cust.getCity(),
					cust.getEmail(), cust.getIsValuedCust().toString() };
			d.addRow(allCust);
		}

		// Checks if the tablerow has been clicked twice.
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					table.updateUI();
					// Grabs the value at the selected row.
					String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
					Customer cc;
					EditPerson c;
					// Loops through all the customers to see if the right customer is found.
					for (int i = 0; i < allCustomers.size(); i++) {
						cc = (Customer) allCustomers.get(i);
						if (cc.getPhoneNo().equals(value)) {
							// If the right customer is found a dialoxbox will pop-up and promt the user to
							// update customers information
							c = new EditPerson(cc);
							c.setSize(665, 545);
							c.setVisible(true);
						}
					}
				}
			}
		});
	}

	// UpdateTable calls populate.
	public static void updateTable() {
		populate(d);
	}

	// Populate gets all the employees and put them into the table.
	private static void populate(DefaultTableModel d) {
		// Sets the rowcount to zero, to avoid multiple employees with the same data.
		d.setRowCount(0);
		try {
			allCustomers = personCtr.findAllCustomers();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog((Component) thisClass, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer cust = (Customer) allCustomers.get(i);
			String[] allCust = { cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getZip(), cust.getCity(),
					cust.getEmail(), cust.getIsValuedCust().toString() };
			d.addRow(allCust);
		}
	}
	
}

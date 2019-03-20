package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
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
import model.Employee;
import model.Person;

public class EmployeeGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTextField txtSalary;
	private JTextField txtBankInfo;
	private JTable table;
	private static DefaultTableModel d;
	private static PersonController personCtr;
	private static List<Person> allEmployee;
	private JTextField txtTitle;

	public EmployeeGUI() {
		try {
			// Creating a new instance of PersonController.
			personCtr = new PersonController();
			// Getting all employees and saves them into A List.
			allEmployee = personCtr.findAllEmployees();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		setSize(1346, 718);
		setLayout(null);

		// Creating a new instance of UiUtil and gets the defaulttablemodel
		d = UiUtil.getTable();

		// Creating an Array with strings.
		String[] columNames = { "Telefonnummer", "Navn", "Adresse", "Postnummer", "By", "E-Mail", "Løn", "Bankinfo",
				"Titel" };

		// loops through the array of strings and adds it to columns.
		for (String string : columNames) {
			d.addColumn(string);
		}

		JLabel lblMedarbejder = new JLabel("Medarbejder");
		lblMedarbejder.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMedarbejder.setBounds(10, 11, 190, 33);
		add(lblMedarbejder);

		JLabel lblPhoneNo = new JLabel("Telefonnummer:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNo.setBounds(10, 60, 155, 20);
		add(lblPhoneNo);

		txtPhoneNo = new JTextField();
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBounds(175, 50, 450, 40);
		txtPhoneNo.setEnabled(true);
		add(txtPhoneNo);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(175, 110, 450, 40);
		txtName.setEnabled(false);
		add(txtName);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 120, 109, 20);
		add(lblName);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 180, 109, 20);
		add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 170, 450, 40);
		txtAddress.setEnabled(false);
		add(txtAddress);

		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(175, 230, 450, 40);
		txtZip.setEnabled(false);
		add(txtZip);

		JLabel lblZip = new JLabel("Postnummer:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(10, 240, 155, 20);
		add(lblZip);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 300, 109, 20);
		add(lblCity);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(175, 290, 450, 40);
		txtCity.setEnabled(false);
		;
		add(txtCity);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 350, 450, 40);
		txtEmail.setEnabled(false);
		add(txtEmail);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 360, 109, 20);
		add(lblEmail);

		// Creates a Employee.
		JButton btnCreateEmployee = new JButton("Opret");
		btnCreateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creates a new instance of createEmployee class.
				CreateEmployee createEmployee = new CreateEmployee();
				createEmployee.setSize(655, 670);
				createEmployee.setVisible(true);
			}
		});
		btnCreateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateEmployee.setBounds(432, 672, 193, 35);
		add(btnCreateEmployee);

		JLabel lblSalary = new JLabel("Løn:");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSalary.setBounds(10, 420, 109, 20);
		add(lblSalary);

		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(175, 410, 450, 40);
		txtSalary.setEnabled(false);
		add(txtSalary);

		JLabel lblBankinfo = new JLabel("Bankinfo:");
		lblBankinfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBankinfo.setBounds(10, 480, 109, 20);
		add(lblBankinfo);

		txtBankInfo = new JTextField();
		txtBankInfo.setColumns(10);
		txtBankInfo.setBounds(175, 470, 450, 40);
		txtBankInfo.setEnabled(false);
		add(txtBankInfo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(653, 50, 683, 350);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(d);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Grabs the information at the table and puts it into the matching textFields.
				int selectedRow = table.getSelectedRow();
				txtPhoneNo.setText(table.getValueAt(selectedRow, 0).toString());
				txtName.setText(table.getValueAt(selectedRow, 1).toString());
				txtAddress.setText(table.getValueAt(selectedRow, 2).toString());
				txtZip.setText(table.getValueAt(selectedRow, 3).toString());
				txtCity.setText(table.getValueAt(selectedRow, 4).toString());
				txtEmail.setText(table.getValueAt(selectedRow, 5).toString());
				txtSalary.setText(table.getValueAt(selectedRow, 6).toString());
				txtBankInfo.setText(table.getValueAt(selectedRow, 7).toString());
				txtTitle.setText(table.getValueAt(selectedRow, 8).toString());
			}
		});

		// Checks if the tablerow has been clicked twice.
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					table.updateUI();
					// Grabs the value at the selected row.
					String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
					Employee e;
					EditEmployee c;
					// Loops through all the employees to see if the right employee is found.
					for (int i = 0; i < allEmployee.size(); i++) {
						e = (Employee) allEmployee.get(i);
						if (e.getPhoneNo().equals(value)) {
							// If the right employee is found a dialoxbox will pop-up and promt the user to
							// update employees information.
							c = new EditEmployee(e);
							c.setSize(665, 675);
							c.setVisible(true);
						}
					}
				}
			}
		});

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(635, 0, 8, 718);
		add(separator);

		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitel.setBounds(10, 540, 109, 20);
		add(lblTitel);

		JLabel lblMedarbejderliste = new JLabel("Medarbejderliste");
		lblMedarbejderliste.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMedarbejderliste.setBounds(653, 11, 262, 33);
		add(lblMedarbejderliste);

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(175, 530, 450, 40);
		txtTitle.setEnabled(false);
		add(txtTitle);

		// Loops through employees and put them into the table.
		for (int i = 0; i < allEmployee.size(); i++) {
			Employee empl = (Employee) allEmployee.get(i);
			String[] allEmpl = { empl.getPhoneNo(), empl.getName(), empl.getAddress(), empl.getZip(), empl.getCity(),
					empl.getEmail(), empl.getSalary().toString(), empl.getAccountInfo(), empl.getTitle() };
			d.addRow(allEmpl);
		}

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
			allEmployee = personCtr.findAllEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < allEmployee.size(); i++) {
			Employee empl = (Employee) allEmployee.get(i);
			String[] allEmpl = { empl.getPhoneNo(), empl.getName(), empl.getAddress(), empl.getZip(), empl.getCity(),
					empl.getEmail(), empl.getSalary().toString(), empl.getAccountInfo(), empl.getTitle() };
			d.addRow(allEmpl);
		}
	}
}

package gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import controller.FoodController;
import controller.OrderController;
import controller.PersonController;
import model.Customer;
import model.DbObserver;
import model.Employee;
import model.Food;
import model.OrderLine;
import model.Person;
import gui.ConfirmationDialog;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class SaleGUI extends SwingWorker {

	private static JPanel panel;
	private JTextField txtPhoneNumber;
	private JTextField txtName;
	private JTextField txtFoodName;
	private JTextField txtAddress;
	private JTextField txtZipCode;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtTotalPrice;
	private static DefaultTableModel t;

	private OrderController oCtr;
	private PersonController pCtr;
	private static FoodController fCtr;
	private Customer cust;
	private Employee employee;
	private Person per;
	private String input = "";
	private int index = 0;
	private JTextField txtMenuNo;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private static DbObserver observer;
	private static JPanel pnlConnection;

	public SaleGUI() {
		panel = new JPanel();
		observer = new DbObserver();
		MainGUI.observable.addObserver(observer);	
		try {
			oCtr = new OrderController();
			pCtr = new PersonController();
			fCtr = new FoodController();

		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		panel.setSize(1346, 718);
		panel.setLayout(null);

		String[] columnNames = {
				"ID",
				"Nummer",
				"Navn",
				"Antal",
				"Pris",};

		String[] tempOrderLine = {"", "", "", "", "", ""};	

		t = UiUtil.getTable();
		for (String string : columnNames) {
			t.addColumn(string);
		}

		JLabel lblCustomer = new JLabel("Kunde");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCustomer.setBounds(10, 11, 109, 25);
		panel.add(lblCustomer);

		JLabel lblOrdre = new JLabel("Ordre");
		lblOrdre.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblOrdre.setBounds(691, 98, 100, 25);
		panel.add(lblOrdre);

		JLabel lblPhoneNumber = new JLabel("Telefonnummer:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(10, 55, 155, 20);
		panel.add(lblPhoneNumber);

		JLabel lblName = new JLabel("Navn:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 170, 109, 20);
		panel.add(lblName);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(10, 250, 109, 20);
		panel.add(lblAddress);

		JLabel lblZipCode = new JLabel("Postnummer:");
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZipCode.setBounds(10, 330, 155, 20);
		panel.add(lblZipCode);

		JLabel lblCity = new JLabel("By:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(10, 410, 109, 20);
		panel.add(lblCity);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 490, 109, 20);
		panel.add(lblEmail);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(175, 45, 450, 40);
		panel.add(txtPhoneNumber);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(175, 160, 450, 40);
		txtName.setEnabled(false);
		panel.add(txtName);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(175, 240, 450, 40);
		txtAddress.setEnabled(false);
		panel.add(txtAddress);

		txtZipCode = new JTextField();
		txtZipCode.setColumns(10);
		txtZipCode.setBounds(175, 320, 450, 40);
		txtZipCode.setEnabled(false);
		panel.add(txtZipCode);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(175, 400, 450, 40);
		txtCity.setEnabled(false);
		panel.add(txtCity);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 480, 450, 40);
		txtEmail.setEnabled(false);
		panel.add(txtEmail);

		JCheckBox chckbxWorker = new JCheckBox("Medarbejder");
		chckbxWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxWorker.setBounds(488, 560, 137, 25);
		chckbxWorker.setEnabled(false);
		panel.add(chckbxWorker);

		JCheckBox chckbxValuedCustomer = new JCheckBox("Værdifuld kunde");
		chckbxValuedCustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxValuedCustomer.setBounds(175, 560, 174, 25);
		chckbxValuedCustomer.setEnabled(false);
		panel.add(chckbxValuedCustomer);

		JButton btnClear = new JButton("Ryd felter");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(175, 96, 150, 35);
		btnClear.addActionListener(ae -> {
			txtPhoneNumber.setText("");
			txtName.setText("");
			txtAddress.setText("");
			txtZipCode.setText("");
			txtCity.setText("");
			txtEmail.setText("");
			chckbxValuedCustomer.setSelected(false);
			chckbxWorker.setSelected(false);
			input = "";
			index = 0;
		});
		panel.add(btnClear);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(673, 0, 8, 718);
		panel.add(separator);


		JLabel lblTotalPrice = new JLabel("Total pris:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalPrice.setBounds(1070, 298, 100, 20);
		panel.add(lblTotalPrice);

		txtTotalPrice = new JTextField();
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(1176, 297, 160, 25);		
		panel.add(txtTotalPrice);



		JButton btn9 = new JButton("9");
		btn9.setBounds(1026, 350, 75, 75);
		btn9.addActionListener(ae -> {
			input = input + btn9.getText();
			getFocus(index);
		});
		panel.add(btn9);

		JButton btn8 = new JButton("8");
		btn8.setBounds(941, 350, 75, 75);
		btn8.addActionListener(ae -> {
			input = input + btn8.getText();
			getFocus(index);
		});
		panel.add(btn8);

		JButton btn7 = new JButton("7");
		btn7.setBounds(856, 350, 75, 75);
		btn7.addActionListener(ae -> {
			input = input + btn7.getText();
			getFocus(index);
		});
		panel.add(btn7);

		JButton btn6 = new JButton("6");
		btn6.setBounds(1026, 436, 75, 75);
		btn6.addActionListener(ae -> {
			input = input + btn6.getText();
			getFocus(index);
		});
		panel.add(btn6);

		JButton btn5 = new JButton("5");
		btn5.setBounds(941, 436, 75, 75);
		btn5.addActionListener(ae -> {
			input = input + btn5.getText();
			getFocus(index);
		});
		panel.add(btn5);

		JButton btn4 = new JButton("4");
		btn4.setBounds(856, 436, 75, 75);
		btn4.addActionListener(ae -> {
			input = input + btn4.getText();
			getFocus(index);
		});
		panel.add(btn4);

		JButton btn3 = new JButton("3");
		btn3.setBounds(1026, 522, 75, 75);
		btn3.addActionListener(ae -> {
			input = input + btn3.getText();
			getFocus(index);
		});
		panel.add(btn3);

		JButton btn2 = new JButton("2");
		btn2.setBounds(941, 522, 75, 75);
		btn2.addActionListener(ae -> {
			input = input + btn2.getText();
			getFocus(index);
		});
		panel.add(btn2);

		JButton btn1 = new JButton("1");
		btn1.setBounds(856, 522, 75, 75);
		btn1.addActionListener(ae -> {
			input = input + btn1.getText();
			getFocus(index);
		});
		panel.add(btn1);

		JButton btn0 = new JButton("0");
		btn0.setBounds(941, 608, 160, 75);
		btn0.addActionListener(ae -> {
			input = input + btn0.getText();
			getFocus(index);
		});
		panel.add(btn0);

		JButton btnCancel = new JButton("Annuller");
		btnCancel.setBounds(1111, 350, 75, 75);
		btnCancel.addActionListener(ae -> {
			txtPhoneNumber.grabFocus();
			t.setRowCount(0);
			txtMenuNo.setText("");
			txtQuantity.setText("");
			txtFoodName.setText("");
			txtPrice.setText("");
			txtPhoneNumber.setText("");
			txtName.setText("");
			txtAddress.setText("");
			txtZipCode.setText("");
			txtCity.setText("");
			txtEmail.setText("");
			txtTotalPrice.setText("");
			input = "";
			index = 0;

			try {
				oCtr.deleteTempOrder();
			} catch (SQLException e1) {
				ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}

		});
		panel.add(btnCancel);

		JButton btnAdd = new JButton("Tilføj");
		btnAdd.setBounds(1111, 608, 75, 74);
		btnAdd.addActionListener(ae -> {
			if(!txtMenuNo.getText().equals("") && !txtQuantity.getText().equals("")) {

				try {
					OrderLine ol = oCtr.createOrderLine(Integer.parseInt(tempOrderLine[3]), Double.parseDouble(tempOrderLine[4]), Integer.parseInt(tempOrderLine[5]));
					tempOrderLine[0] = Integer.toString(ol.getId());
					t.addRow(tempOrderLine);	
					t.fireTableDataChanged();

					if(txtTotalPrice.getText().equals("")) {
						try {
							txtTotalPrice.setText(oCtr.discountPrice().toString());
						} catch (SQLException e1) {
							ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						try {
							oCtr.discountPrice();
							txtTotalPrice.setText(oCtr.discountPrice().toString());
						} catch (SQLException e1) {
							ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (NumberFormatException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nMalformateret nummer!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen?", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}			
			else {

				index = 2;
				input = "";
			}
			txtMenuNo.setText("");
			txtQuantity.setText("");
			txtFoodName.setText("");
			txtPrice.setText("");
			btnAdd.setEnabled(false);
			index = 1;
			input = "";
		});
		panel.add(btnAdd);

		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(1111, 522, 75, 75);
		btnEnter.addActionListener(ae -> {
			if(!txtMenuNo.getText().equals("") && txtQuantity.getText().equals("")) {
				txtQuantity.grabFocus();
				try {
					btnAdd.setEnabled(false);
					Food food = fCtr.findOnMenuNo(Integer.parseInt(txtMenuNo.getText()));
					txtFoodName.setText(food.getName());
					tempOrderLine[1] = txtMenuNo.getText();
					tempOrderLine[2] = food.getName();
					tempOrderLine[5] = food.getId().toString();
				} catch (NumberFormatException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nMalformateret nummer!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}else if(!txtMenuNo.getText().equals("") && !txtQuantity.getText().equals("")) {
				try {
					btnAdd.setEnabled(true);
					txtPrice.grabFocus();
					Food food = fCtr.findOnMenuNo(Integer.parseInt(txtMenuNo.getText()));
					tempOrderLine[3] = txtQuantity.getText();
					Double price = food.getPrice();
					int quantity = Integer.parseInt(txtQuantity.getText());
					Double linePrice = oCtr.calcPrice(quantity, price, Integer.parseInt(tempOrderLine[5]));
					txtPrice.setText(linePrice.toString());
					tempOrderLine[4] = linePrice.toString();
				} catch (NumberFormatException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nMalformateret nummer!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}				
			}
			index = 2;
			input = "";
		});
		panel.add(btnEnter);

		JButton btnAccessories = new JButton("Tilbehør");
		btnAccessories.setBounds(856, 608, 75, 75);
		btnAccessories.setEnabled(false);
		panel.add(btnAccessories);

		txtMenuNo = new JTextField();
		txtMenuNo.setBounds(689, 45, 86, 40);
		txtMenuNo.setColumns(10);
		panel.add(txtMenuNo);


		txtFoodName = new JTextField();
		txtFoodName.setBounds(787, 45, 357, 40);
		panel.add(txtFoodName);
		txtName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setText("");
		txtPrice.setBounds(1250, 45, 86, 40);
		txtPrice.setColumns(10);
		panel.add(txtPrice);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(1154, 45, 86, 40);
		panel.add(txtQuantity);
		txtQuantity.setColumns(10);

		JLabel label = new JLabel("");
		label.setBounds(822, 23, 46, 14);
		panel.add(label);

		JLabel lblmenuNo = new JLabel("Menu nummer");
		lblmenuNo.setBounds(691, 34, 86, 14);
		panel.add(lblmenuNo);

		JLabel lblFoodName = new JLabel("Navn");
		lblFoodName.setBounds(788, 34, 46, 14);
		panel.add(lblFoodName);

		JLabel lblAntal = new JLabel("Antal");
		lblAntal.setBounds(1154, 34, 46, 14);
		panel.add(lblAntal);

		JLabel lblPrice = new JLabel("Pris");
		lblPrice.setBounds(1250, 34, 46, 14);
		panel.add(lblPrice);

		JCheckBox chckbxToDeliver = new JCheckBox("Til levering");
		chckbxToDeliver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxToDeliver.setBounds(691, 301, 143, 35);
		chckbxToDeliver.addActionListener(ae -> {
			if(chckbxToDeliver.isSelected()) {
				chckbxToDeliver.setSelected(true);;
			} 
			else {
				chckbxToDeliver.setSelected(false);
			}
		});
		panel.add(chckbxToDeliver);

		JButton btnComplete = new JButton("Afslut");
		btnComplete.setBounds(1111, 436, 75, 75);
		btnComplete.addActionListener(ae -> {
			txtPhoneNumber.grabFocus();
			try {			
				oCtr.makeSale(chckbxToDeliver.isSelected(), Double.parseDouble(txtTotalPrice.getText()));
				chckbxToDeliver.setSelected(false);
				OrderGUI.update();
			} catch (NumberFormatException e1) {
				ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nMalformateret nummer!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e1) {
				ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
			txtPhoneNumber.grabFocus();
			t.setRowCount(0);
			txtMenuNo.setText("");
			txtQuantity.setText("");
			txtFoodName.setText("");
			txtPrice.setText("");
			txtPhoneNumber.setText("");
			txtName.setText("");
			txtAddress.setText("");
			txtZipCode.setText("");
			txtCity.setText("");
			txtEmail.setText("");
			txtTotalPrice.setText("");
			index = 0;
			input = "";
		});
		panel.add(btnComplete);

		JButton btnFindCustomer = new JButton("Find kunde");
		btnFindCustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFindCustomer.setBounds(477, 96, 150, 35);
		btnFindCustomer.addActionListener(ae -> {
			txtMenuNo.grabFocus();
			btnAdd.setEnabled(false);
			UiUtil.validPhoneInput(txtPhoneNumber.getText());
			try {				
				List<Person> customers = pCtr.findAllCustomers();
				List<Person> employees = pCtr.findAllEmployees();
				per = null;

				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getPhoneNo().equals(txtPhoneNumber.getText())) {
						per = customers.get(i);
						break;
					}
				}
				if(per == null) {
					for(int i = 0; i < employees.size(); i++) {
						if(employees.get(i).getPhoneNo().equals(txtPhoneNumber.getText())) {
							per = employees.get(i);
							break;
						}
					}
				}

				if(per.getType().equals("Customer")) {
					cust = (Customer) per;

					txtName.setText(cust.getName());
					txtAddress.setText(cust.getAddress());
					txtZipCode.setText(cust.getZip());
					txtCity.setText(cust.getCity());
					txtEmail.setText(cust.getEmail());

					if(cust.getIsValuedCust()) {
						chckbxValuedCustomer.setSelected(true);
					}
					else {
						chckbxValuedCustomer.setEnabled(false);
					}
					chckbxWorker.setSelected(false);
				}
				else {
					employee = (Employee) per;

					txtName.setText(employee.getName());	
					txtAddress.setText(employee.getAddress());
					txtZipCode.setText(employee.getZip());
					txtCity.setText(employee.getCity());
					txtEmail.setText(employee.getEmail());

					chckbxWorker.setSelected(true);
					chckbxValuedCustomer.setSelected(false);
				}
				oCtr.createTempOrder(per.getId());
			} catch (SQLException e) {
				ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
			index = 1;
			input = "";
		});
		panel.add(btnFindCustomer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(691, 137, 645, 157);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.updateUI();

				if(table.getRowCount()>0) {				
					int index = table.getSelectedRow();
					int value = Integer.parseInt(table.getModel().getValueAt(index, 0).toString());

					try {
						if(confirmations()) {
							oCtr.deleteOrderLine(value);
							((DefaultTableModel)table.getModel()).removeRow(index);
						}
					} catch (SQLException e1) {
						ConfirmationDialog.showMessageDialog(panel, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					update();
				}
			}
		});
		table.setModel(t);
		
		JButton btnCreateCust = new JButton("Opret kunde");
		btnCreateCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatePerson createPerson = new CreatePerson();
				createPerson.setSize(655, 545);
				createPerson.setVisible(true);
			}
		});
		btnCreateCust.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateCust.setBounds(175, 608, 165, 40);
		panel.add(btnCreateCust);
		
		pnlConnection = new JPanel();
		pnlConnection.setBackground(Color.MAGENTA);
		pnlConnection.setBounds(10, 682, 25, 25);
		panel.add(pnlConnection);
		
		JLabel lblNewLabel = new JLabel("DB");
		pnlConnection.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}

	private static void updateTable(DefaultTableModel t) {
		t.setRowCount(0);
	}

	public static void update() {
		updateTable(t);
	}

	public boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(panel, "Er du sikker på at du vil fjerne dette fra ordren?", "Validering", optionType);
		if(result == JOptionPane.YES_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}

	public void getFocus(int index) {
		if(index == 0) {
			txtPhoneNumber.grabFocus();
			txtPhoneNumber.setText(input);
		} 
		else if(index == 1) {
			txtMenuNo.grabFocus();
			txtMenuNo.setText(input);
		} 
		else if(index == 2) {
			txtQuantity.grabFocus();
			txtQuantity.setText(input);
		}
	}

	public Component getPanel() {
		return panel;
	}

	@Override
	protected Object doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public static void changeColor(boolean state) {
		if(state)
			pnlConnection.setBackground(Color.GREEN);
		else
			pnlConnection.setBackground(Color.RED);
	}
}

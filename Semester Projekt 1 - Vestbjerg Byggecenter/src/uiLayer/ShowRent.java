package uiLayer;

import javax.swing.JDialog;
import javax.swing.JTextField;

import controllerLayer.CustomerController;
import controllerLayer.ProductController;
import controllerLayer.RentController;
import modelLayer.Customer;
import modelLayer.Product;
import modelLayer.Rent;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JScrollPane;

public class ShowRent extends JDialog {
	private JTextField txtSerial;
	private CustomerController cusCon;
	private ProductController proCon;
	private Product[] products;
	private RentController renCon;
	private JTextField txtCust;
	
	public ShowRent(int id) {

		renCon = new RentController();
		proCon = new ProductController();
		products = proCon.toArr();
		cusCon = new CustomerController();
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Kunde:");
		lblNewLabel.setBounds(10, 14, 64, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Produkt:");
		lblNewLabel_1.setBounds(10, 45, 64, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Dagspris:");
		lblNewLabel_2.setBounds(10, 76, 64, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Depositum:");
		lblNewLabel_3.setBounds(10, 107, 64, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Antal dage:");
		lblNewLabel_4.setBounds(10, 138, 64, 14);
		getContentPane().add(lblNewLabel_4);

		JFormattedTextField txtCustomer = new JFormattedTextField();
		txtCustomer.setText(Integer.toString(renCon.getRent(id).getCustomer().getID()) + " " + renCon.getRent(id).getCustomer().getID());
		txtCustomer.setEditable(false);
		txtCustomer.setBounds(84, 11, 205, 20);
		getContentPane().add(txtCustomer);

		JFormattedTextField txtPrice = new JFormattedTextField();
		txtPrice.setText(Double.toString(renCon.getRent(id).getPrice()));
		txtPrice.setEditable(false);
		txtPrice.setBounds(84, 73, 205, 20);
		getContentPane().add(txtPrice);

		JFormattedTextField txtDeposit = new JFormattedTextField();
		txtDeposit.setText(Double.toString(renCon.getRent(id).getDeposit()));
		txtDeposit.setEditable(false);
		txtDeposit.setBounds(84, 104, 205, 20);
		getContentPane().add(txtDeposit);

		JFormattedTextField txtDays = new JFormattedTextField();
		txtDays.setText(renCon.getRent(id).getDays());
		txtDays.setEditable(false);
		txtDays.setBounds(84, 135, 205, 20);
		getContentPane().add(txtDays);

		
		

		JButton btnCls = new JButton("Luk");
		btnCls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCls.setBounds(155, 375, 135, 23);
		getContentPane().add(btnCls);
		
		JLabel lblNewLabel_5 = new JLabel("Note:");
		lblNewLabel_5.setBounds(10, 205, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Serienummer:");
		lblNewLabel_6.setBounds(10, 169, 77, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtSerial = new JTextField();
		txtSerial.setText(renCon.getRent(id).getSerialNumber());
		txtSerial.setEditable(false);
		txtSerial.setBounds(84, 166, 205, 20);
		getContentPane().add(txtSerial);
		txtSerial.setColumns(10);
		
		txtCust = new JTextField();
		txtCust.setText(Integer.toString(renCon.getRent(id).getCustomer().getID()) + " " + renCon.getRent(id).getCustomer().getName());
		txtCust.setEditable(false);
		txtCust.setBounds(84, 42, 205, 20);
		getContentPane().add(txtCust);
		txtCust.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 279, 134);
		getContentPane().add(scrollPane);
		
		JEditorPane txtNote = new JEditorPane();
		scrollPane.setViewportView(txtNote);
		txtNote.setText(renCon.getRent(id).getNote());
		txtNote.setEditable(false);
		txtNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}
}

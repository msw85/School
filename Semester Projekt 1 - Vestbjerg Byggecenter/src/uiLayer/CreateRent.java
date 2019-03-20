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
import java.util.Calendar;
import java.util.Date;

import javax.swing.JEditorPane;
import java.awt.Font;

public class CreateRent extends JDialog {
	private JTextField txtSerial;
	private CustomerController cusCon;
	private ProductController proCon;
	private Product[] products;
	private RentController renCon;
	private Customer custToAdd;
	private Product prodToAdd;
	
	public CreateRent() {

		renCon = new RentController();
		proCon = new ProductController();
		products = proCon.toArr();
		cusCon = new CustomerController();
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Kunde");
		lblNewLabel.setBounds(10, 14, 64, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Produkt");
		lblNewLabel_1.setBounds(10, 45, 64, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Dagspris");
		lblNewLabel_2.setBounds(10, 76, 64, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Depositum");
		lblNewLabel_3.setBounds(10, 107, 64, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Antal dage");
		lblNewLabel_4.setBounds(10, 138, 64, 14);
		getContentPane().add(lblNewLabel_4);

		JComboBox<Product> comboBox = new JComboBox<>(products);
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(84, 42, 205, 20);
		getContentPane().add(comboBox);
		
		JFormattedTextField txtPrice = new JFormattedTextField();
		txtPrice.setBounds(84, 73, 205, 20);
		getContentPane().add(txtPrice);

		JFormattedTextField txtDeposit = new JFormattedTextField();
		txtDeposit.setBounds(84, 104, 205, 20);
		getContentPane().add(txtDeposit);

		JFormattedTextField txtDays = new JFormattedTextField();
		txtDays.setBounds(84, 135, 205, 20);
		getContentPane().add(txtDays);

		JButton btnNewButton_1 = new JButton("Annuller");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(155, 417, 135, 23);
		getContentPane().add(btnNewButton_1);
		
		JEditorPane txtNote = new JEditorPane();
		txtNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNote.setBounds(10, 272, 279, 134);
		getContentPane().add(txtNote);
		
		JLabel lblNewLabel_5 = new JLabel("Note");
		lblNewLabel_5.setBounds(10, 247, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Serienummer");
		lblNewLabel_6.setBounds(10, 169, 64, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtSerial = new JTextField();
		txtSerial.setBounds(84, 166, 205, 20);
		getContentPane().add(txtSerial);
		txtSerial.setColumns(10);
		
		JComboBox<Customer> comboCust = new JComboBox<Customer>();
		for (int i = 0; i < cusCon.size(); i++) {
			Customer c = cusCon.getCustomer(i);
			comboCust.addItem(c);
		}
		comboCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				custToAdd = (Customer) comboCust.getSelectedItem();
			}
		});
		comboCust.setSelectedIndex(0);
		comboCust.setBounds(84, 11, 205, 20);
		getContentPane().add(comboCust);
		
		JButton btnNewButton = new JButton("Opret");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Customer c = custToAdd;
				Calendar calToday = Calendar.getInstance();
				Date today = calToday.getTime();
				Calendar calReturn = Calendar.getInstance();
				calReturn.add(Calendar.DATE, Integer.parseInt(txtDays.getText()));
				Date toReturn = calReturn.getTime();
				Rent r = new Rent(txtSerial.getText(), txtNote.getText(), Double.parseDouble(txtPrice.getText()), Double.parseDouble(txtDeposit.getText()), today, toReturn,  custToAdd, prodToAdd);
				r.setDays(txtDays.getText());
				renCon.addRent(r);
				RentGUI.update();
				dispose();
			}
		});
		btnNewButton.setBounds(10, 417, 135, 23);
		getContentPane().add(btnNewButton);
		

	}
}

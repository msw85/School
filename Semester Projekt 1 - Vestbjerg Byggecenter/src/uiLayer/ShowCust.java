package uiLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllerLayer.CustomerController;
import modelLayer.Product;

public class ShowCust extends JDialog {
	
	private JTextField txtName;
	private JTextField txtAdd;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtPho;
	
	private CustomerController cusCon = new CustomerController();

	public ShowCust(int id) {
		getContentPane().setLayout(null);
		
		JButton btnAnnul = new JButton("Luk");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(134, 201, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(cusCon.getCustomer(id).getName());
		txtName.setEditable(false);
		txtName.setBounds(66, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAdd = new JTextField();
		txtAdd.setText(cusCon.getCustomer(id).getAddress());
		txtAdd.setEditable(false);
		txtAdd.setBounds(66, 48, 157, 20);
		getContentPane().add(txtAdd);
		txtAdd.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblAdd = new JLabel("Adresse:");
		lblAdd.setBounds(10, 50, 46, 14);
		getContentPane().add(lblAdd);
		
		JLabel lblZip = new JLabel("Postnr:");
		lblZip.setBounds(10, 85, 46, 14);
		getContentPane().add(lblZip);
		
		JLabel lblCity = new JLabel("By:");
		lblCity.setBounds(10, 120, 46, 14);
		getContentPane().add(lblCity);
		
		JLabel lblTlf = new JLabel("Telefonnr:");
		lblTlf.setBounds(10, 155, 59, 14);
		getContentPane().add(lblTlf);
		
		txtZip = new JTextField();
		txtZip.setText(cusCon.getCustomer(id).getZip());
		txtZip.setEditable(false);
		txtZip.setBounds(66, 82, 157, 20);
		getContentPane().add(txtZip);
		txtZip.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setText(cusCon.getCustomer(id).getCity());
		txtCity.setEditable(false);
		txtCity.setBounds(66, 117, 157, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		txtPho = new JTextField();
		txtPho.setText(cusCon.getCustomer(id).getPhone());
		txtPho.setEditable(false);
		txtPho.setBounds(66, 152, 157, 20);
		getContentPane().add(txtPho);
		txtPho.setColumns(10);
		
	}

}

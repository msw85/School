package uiLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllerLayer.ProductController;
import controllerLayer.SupplierController;
import modelLayer.Customer;
import modelLayer.Product;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ShowSup extends JDialog {
	
	private JTextField txtName;
	private JTextPane txtDesc;
	private JTextField txtBank;

	
	private SupplierController supCon = new SupplierController();
	private ProductController proCon = new ProductController();

	public ShowSup(int id) {
		getContentPane().setLayout(null);
		
		JButton btnAnnul = new JButton("Luk");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(190, 227, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(supCon.getSupplier(id).getName());
		txtName.setEditable(false);
		txtName.setBounds(122, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblAdd = new JLabel("Beskrivelse:");
		lblAdd.setBounds(10, 50, 89, 14);
		getContentPane().add(lblAdd);
		
		JLabel lblZip = new JLabel("Bankinformation:");
		lblZip.setBounds(10, 161, 105, 14);
		getContentPane().add(lblZip);
		
		txtBank = new JTextField();
		txtBank.setText(supCon.getSupplier(id).getBankInfo());
		txtBank.setEditable(false);
		txtBank.setBounds(122, 158, 157, 20);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 49, 157, 98);
		getContentPane().add(scrollPane);
		
		txtDesc = new JTextPane();
		scrollPane.setViewportView(txtDesc);
		txtDesc.setText(supCon.getSupplier(id).getDescription());
		txtDesc.setEditable(false);
		
		JComboBox<String> comboProd = new JComboBox<String>();
		comboProd.setEnabled(true);
		for (int i = 0; i < proCon.size(); i++) {
			Product p = proCon.getProduct(i);
			if(p.getSup() == supCon.getSupplier(id)) {
			comboProd.addItem(Integer.toString(p.getID()) + " " + p.getName());
			}
		}
		comboProd.setBounds(122, 189, 157, 20);
		getContentPane().add(comboProd);
		
		JLabel lblProd = new JLabel("Produkter:");
		lblProd.setBounds(10, 192, 64, 14);
		getContentPane().add(lblProd);

		
	}
}

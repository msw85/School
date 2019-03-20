package uiLayer;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelLayer.Order;
import modelLayer.Product;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowOrder extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8500637376058323499L;
	private JTable table;

	public ShowOrder(Order order, int customerID) {
		Product[] products = order.toArr();
		setResizable(false);
		getContentPane().setLayout(null);
		DefaultTableModel d = UiUtil.getTable();
		d.addColumn("Produkt ID");
		d.addColumn("Produkt");
		d.addColumn("Pris");
		d.addColumn("T & T");
		d.addColumn("Afsendt");

		
		JLabel lblCus = new JLabel("Kunde ID:");
		lblCus.setBounds(10, 11, 90, 14);
		getContentPane().add(lblCus);
		
		JLabel lblOdr = new JLabel("Ordre ID: ");
		lblOdr.setBounds(110, 11, 90, 14);
		getContentPane().add(lblOdr);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(210, 11, 90, 14);
		getContentPane().add(lblTotal);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setBounds(304, 10, 117, 16);
		getContentPane().add(lblDate);
		
		table = new JTable();
		table.setBounds(10, 36, 524, 324);
		getContentPane().add(table);
		table.setModel(d);
		Integer j = customerID;
		String sent;
		if(order.getTimeSent() == null) {
			sent = "nej";
		}
		else {
			sent = order.getTimeSent().toString();
		}
		
		lblCus.setText("Kunde ID: " + j.toString());
		lblOdr.setText("Odre ID: " + order.getID());
		lblDate.setText("Afsendt: " + sent);
		lblTotal.setText("Total: " + Double.toString(order.getPrice()));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ikke slettet");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				order.toggleActive();
				OrderGUI.update();
			}
		});
		
		rdbtnNewRadioButton.setSelected(order.getState());
		
		rdbtnNewRadioButton.setBounds(429, 7, 109, 23);
		getContentPane().add(rdbtnNewRadioButton);
		
		for (Product product : products) {
			Integer y = product.getID();
			String[] a = {y.toString(), product.getName(), Double.toString(product.getPrice()), order.getTrackAndTrace()};
			d.addRow(a);
		}
	}
}

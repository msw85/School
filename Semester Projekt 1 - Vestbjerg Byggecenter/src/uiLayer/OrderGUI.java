package uiLayer;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import controllerLayer.CustomerController;
import controllerLayer.OrderController;
import modelLayer.Customer;
import modelLayer.Order;
import java.awt.Point;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class OrderGUI extends JPanel {

	private static CustomerController cusCon;
	private static DefaultTableModel d;
	private static OrderController odrCon;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private static boolean showActive;

	public OrderGUI() {
		cusCon = new CustomerController();
		odrCon = new OrderController();
		setLayout(null);
		setSize(640, 420);
		d = UiUtil.getTable();
		d.addColumn("Kunde ID");
		d.addColumn("Ordre ID");
		d.addColumn("Antal produkter");
		d.addColumn("Total");
		JFormattedTextField txtOdr;
		JFormattedTextField txtCus;
		NumberFormatter formatter = UiUtil.initFormatter();
		showActive = false;

		txtOdr = new JFormattedTextField(formatter);
		txtCus = new JFormattedTextField(formatter);

		txtCus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					txtOdr.setText("");
					d.setRowCount(0);
					int cusId = Integer.parseInt(txtCus.getText());
					Customer c = cusCon.getCustomer(cusId);
					for (int i = 0; i < c.getOrderSize(); i++) {
						Order o = c.getOrder(i);
						Integer j = cusId;
						String[] s = {j.toString(), o.getID(), o.getSize().toString(), String.valueOf(o.getPrice())};
						d.addRow(s);					
					}

				} catch (Exception e) {
					update();
				}

			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtOdr.setValue(null);
				if(txtCus.getText().length() == 1 && arg0.getKeyChar() == '\b'){
					txtCus.setValue(null);
				}
			}
		});
		txtCus.setBounds(10, 27, 109, 20);
		add(txtCus);

		txtOdr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					txtCus.setText(null);
					d.setRowCount(0);
					int ordId = Integer.parseInt(txtOdr.getText());
					try {
						Order o = odrCon.getOrder(ordId);
						String[] s = {txtOdr.getText(), o.getID(), o.getSize().toString(), String.valueOf(o.getPrice())};
						d.addRow(s);
					} catch (Exception e2) {
						d.setRowCount(0);
					}
				} catch (Exception e2) {
					update();
				}


			}
			@Override
			public void keyPressed(KeyEvent e) {
				txtCus.setValue(null);
				if(txtOdr.getText().length() == 1 && e.getKeyChar() == '\b'){
					txtOdr.setValue(null);
				}
			}
		});
		txtOdr.setBounds(10, 68, 109, 20);
		add(txtOdr);

		JLabel lblSgKunde = new JLabel("Søg kunde");
		lblSgKunde.setBounds(10, 10, 70, 14);
		add(lblSgKunde);

		JLabel lblSgOrdre = new JLabel("Søg ordre");
		lblSgOrdre.setBounds(10, 53, 70, 14);
		add(lblSgOrdre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 9, 474, 399);
		add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(d);

		JRadioButton bntActive = new JRadioButton("Vis slettede");
		bntActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showActive = bntActive.isSelected();
				if(showActive) {
					updateNonAc();
				} else {
					update();
				}

			}
		});	
		bntActive.setBounds(10, 109, 99, 23);
		add(bntActive);

		updateTable(d);	

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2) {
					int j = Integer.parseInt(table.getValueAt(row, 0).toString());
					int k = Integer.parseInt(table.getValueAt(row, 1).toString());
					ShowOrder a =  new ShowOrder(odrCon.getOrder(k), j);
					a.setSize(550, 400);
					a.setVisible(true);
				}
			}
		});
	}

	/**
	 * @param d
	 */
	private static void updateTable(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < cusCon.size(); i++) {
			Customer c = cusCon.getCustomer(i);
			Integer j = i;
			Order[] a = c.getOrders();
			if(a != null) {
				for (Order order : a) {
					if(showActive) {
						String[] s = {j.toString(),  order.getID() , order.getSize().toString(), String.valueOf(order.getPrice())};
						d.addRow(s);
					}
					else {
						if(order.getState()) {
							String[] s = {j.toString(),  order.getID() , order.getSize().toString(), String.valueOf(order.getPrice())};
							d.addRow(s);
						}
					}

				}			
			}
		}
	}
	
	private static void updateTableNonAc(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < cusCon.size(); i++) {
			Customer c = cusCon.getCustomer(i);
			Integer j = i;
			Order[] a = c.getOrders();
			if(a != null) {
				for (Order order : a) {
					if(showActive && !order.getState()) {
						String[] s = {j.toString(),  order.getID() , order.toString(), String.valueOf(order.getPrice())};
						d.addRow(s);
					}
				}			
			}
		}
	}
	
	
	public static void update() {
		updateTable(d);
	}
	
	public static void updateNonAc() {
		updateTableNonAc(d);
	}

}

package gui;

import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import model.Order;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class OrderGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static DefaultTableModel t;
	private JTable table;
	private static OrderController orderCtr;
	private static List<Order> listOrder;
	private static Object thisClass;
	public OrderGUI() {
		thisClass = this;
		try {
			orderCtr = new OrderController();
			listOrder = orderCtr.findAll();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		setSize(1346, 718);
		setLayout(null);
		t = UiUtil.getTable();
		JLabel lblOrdrer = new JLabel("Ordrer");
		lblOrdrer.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblOrdrer.setBounds(10, 11, 100, 25);
		add(lblOrdrer);

		JButton btnOrdreKlar = new JButton("Ordre klar");
		btnOrdreKlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				int value = Integer.parseInt(table.getModel().getValueAt(index, 7).toString());
				Order o;
				try {
					o = orderCtr.findByID(value);
					o.setIsReady(true);
					orderCtr.updateOrder(o);
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(btnOrdreKlar, "Der skete en fejl.\nEr der forbindelse til databasen?", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				update();
			}
		});
		btnOrdreKlar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrdreKlar.setBounds(1186, 672, 150, 35);
		add(btnOrdreKlar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 1316, 614);
		add(scrollPane);

		String[] columnNames = {"Antal produkter",
				"Tidspunkt",
				"Til levering",
				"Er klar",
				"Er leveret",
				"Pris",
				"Kunde", 
		"id"};

		t = UiUtil.getTable();
		for (String string : columnNames) {
			t.addColumn(string);
		}

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(t);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					try {
						table.updateUI();
						String value = table.getModel().getValueAt(table.getSelectedRow(), 7).toString();
						Order o;
						o = orderCtr.findByID(Integer.parseInt(value));
						OrderLineDetails old;
						old = new OrderLineDetails(o);
						old.setSize(665, 230);
						old.setVisible(true);
					} catch (NumberFormatException | SQLException e) {
						ConfirmationDialog.showMessageDialog(table, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}			
		});

		scrollPane.setViewportView(table);
		update();
	}

	private static void updateTable(DefaultTableModel t) {
		try {
			listOrder = orderCtr.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConfirmationDialog.showMessageDialog((Component) thisClass, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		t.setRowCount(0);

		for (int i = 0; i < listOrder.size(); i++) {
			Order or = listOrder.get(i);
			Integer orderLineCount = or.getOrderL().size();
			if(!or.getOrderL().isEmpty()) {
				String[] s = {orderLineCount.toString(), or.getDueTime().toString(), or.getToDeliver() ? "Ja" : "Nej", or.getIsReady() ? "Ja" : "Nej", or.getIsDelivered() ? "Ja" : "Nej", String.valueOf(or.getTotalPrice()), or.getPers().getName(), Integer.toString(or.getId())};
				t.addRow(s);
			}
		}
	}
	public static void update() {
		updateTable(t);
	}
}

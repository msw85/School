package gui;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import model.Order;
import model.OrderLine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class OrderLineDetails extends JDialog{
	private JTable table;
	private DefaultTableModel tableModel;
	private OrderController orderCtr;
	
	public OrderLineDetails(Order o) {
		try {
			orderCtr = new OrderController();
		} catch (Exception e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl under sletning.\nEr der en ordre der indeholder det valgte?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}
		tableModel = UiUtil.getTable();
		
		String[] columNames = { "Id", "Produkt Navn", "Antal", "Pris" };

		for (String string : columNames) {
			tableModel.addColumn(string);
		}
		
		populate(tableModel, o);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order indhold:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 121, 25);
		getContentPane().add(lblNewLabel);	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 625, 92);
		getContentPane().add(scrollPane);
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("SLET");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(confirmations()) {
					try {
						orderCtr.deleteOrder(o);
						OrderGUI.update();
						dispose();
					} catch (SQLException e) {
						ConfirmationDialog.showMessageDialog(btnDelete, "Der skete en fejl under sletning.\nEr der en ordre der indeholder det valgte?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.setBounds(544, 144, 89, 23);
		getContentPane().add(btnDelete);
	}
	
	public static void populate(DefaultTableModel tableModel, Order o) {
		tableModel.setRowCount(0);
		List<OrderLine> allOrderLines = o.getOrderL();
		Integer quantity;
		Double price;
		
		for (int i = 0; i < allOrderLines.size(); i++) {
			OrderLine orderLine = allOrderLines.get(i);
			quantity = orderLine.getQuantity();
			price = orderLine.getPrice();
			String[] allOl = { orderLine.getId().toString(), orderLine.getFood().getName(), quantity.toString(), price.toString() };
			tableModel.addRow(allOl);
		}
	}
	public boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(this, "Er du sikker på at du vil fjerne dette fra menu?", "Validering", optionType);
		if(result == JOptionPane.YES_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}

	public void errorMessage() {
		JOptionPane.showMessageDialog(this, "Den valgte menu er på en ordrelinje, ordrelinjen og ordre skal slettes først", "Information",
				JOptionPane.WARNING_MESSAGE);
	}
}

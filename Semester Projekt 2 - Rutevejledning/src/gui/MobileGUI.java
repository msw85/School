package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.RouteController;
import model.DbObserver;
import model.Order;
import model.Route;
import model.Vertex;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MobileGUI extends SwingWorker<String, Object> {

	private JPanel contentPane;
	private JFrame frame;
	private static JTable tblRoute;
	private static DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private static RouteController rCtr;
	private static Route route;
	private static Object thisClass;
	private static JPanel pnlConnection;
	private static DbObserver observer;

	public MobileGUI() {
		thisClass = this.frame;

		frame = new JFrame();
		observer = new DbObserver();
		MainGUI.observable.addObserver(observer);

		String[] columnNames = { "Handling", "Navn", "Kontakt" };

		tableModel = UiUtil.getTable();
		for (String string : columnNames) {
			tableModel.addColumn(string);
		}

		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGetRoute = new JButton("Hent rutevejledning");
		btnGetRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		btnGetRoute.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGetRoute.setBounds(0, 504, 161, 42);
		contentPane.add(btnGetRoute);

		JButton btnCancelRoute = new JButton("Annuller rute");
		btnCancelRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.setRowCount(0);
				try {
					rCtr.deleteRoute(route);
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(btnCancelRoute, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCancelRoute.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelRoute.setBounds(235, 504, 161, 42);
		contentPane.add(btnCancelRoute);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 396, 504);
		contentPane.add(scrollPane);

		tblRoute = new JTable(tableModel);
		scrollPane.setViewportView(tblRoute);

		pnlConnection = new JPanel();
		pnlConnection.setBackground(Color.CYAN);
		pnlConnection.setBounds(184, 510, 25, 25);
		contentPane.add(pnlConnection);

		JLabel lblNewLabel = new JLabel("DB");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlConnection.add(lblNewLabel);
		tblRoute.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 1) {
					String vertName = table.getValueAt(row, 1).toString();
					tableModel.setValueAt("Leveret!", row, 0);
					tableModel.fireTableCellUpdated(row, 0);
					rCtr.markOrderDelivered(vertName);
				}
			}
		});
		MobileGUI.this.execute();
	}

	public static void changeColor(boolean state) {
		if (state)
			pnlConnection.setBackground(Color.GREEN);
		else
			pnlConnection.setBackground(Color.RED);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);

	}

	public void setSize(int i, int j) {
		frame.setSize(i, j);

	}

	public static void update() {
		updateTable(tableModel);
	}

	private static void updateTable(DefaultTableModel tableModel) {
		tableModel.setRowCount(0);

		try {
			rCtr = new RouteController();
			route = rCtr.getRoute();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog((Component)thisClass, "Der skete en fejl.\nEr der forbindelse til databasen?", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		populateTable(tableModel);

	}

	private static void populateTable(DefaultTableModel tableModel) {
		tableModel.setRowCount(0);
		List<Order> listOrder = null;
		try {
			listOrder = rCtr.getReadyOrders();
			if (listOrder == null) {
				throw new NullPointerException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (route.getVertices().isEmpty()) {
			noOrdersPopUp();
		} else {
			for (Vertex vert : route.getVertices()) {
				for (Order order : listOrder) {
					if (order.getPers().getAddress().equals(vert.getName())) {
						String[] a = { "Levering:", vert.getName(), order.getPers().getPhoneNo() };
						if (tableModel.getValueAt(tableModel.getRowCount() - 1, 1).equals(vert.getName())) {
							tableModel.removeRow(tableModel.getRowCount() - 1);
						}
						tableModel.addRow(a);

					} else {
						String[] a = { " -> ", vert.getName(), "" };
						if (tableModel.getRowCount() == 0)
							tableModel.addRow(a);
						else if (!tableModel.getValueAt(tableModel.getRowCount() - 1, 1).equals(vert.getName())) {
							tableModel.addRow(a);
						}
					}
				}
			}
		}
	}

	private static void noOrdersPopUp() {
		ConfirmationDialog.showMessageDialog((Component) thisClass, "Der er ingen færdige ordrer til udbringning.");
	}

	@Override
	protected String doInBackground() throws Exception {
		MainGUI.observable.run();
		return "Doing";
	}

}

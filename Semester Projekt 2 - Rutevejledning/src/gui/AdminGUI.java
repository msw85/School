package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.RouteController;
import model.Discount;
import model.Edge;
import model.Vertex;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JPanel {
	private JTable tblEdges;
	private JTable tblVerts;
	private JTextField txtNewVertName;
	private JTextField txtNewEdgeSource;
	private JTextField txtNewEdgeDest;
	private JTextField txtNewEdgeWeight;
	private JTextField txtNewEdgeDescript;
	private static DefaultTableModel defaultModelVerts;
	private static DefaultTableModel defaultModelEdges;
	private static RouteController rCtr;
	private OrderController oCtr;
	private JTextField txtEmpDis;
	private JTextField txtCustDis;
	private JTextField txtLunchDisc;
	List<Discount> allDiscounts;
	private static Object thisClass;
	public AdminGUI() {
		thisClass = this;
		try {
			oCtr = new OrderController();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		defaultModelVerts = UiUtil.getTable();
		defaultModelEdges = UiUtil.getTable();

		String[] columNamesVerts = { "Id", "Navn" };
		String[] columNamesEdges = { "Id", "Beskrivelse", "Start", "Slut", "Vægt" };

		for (String string : columNamesVerts) {
			defaultModelVerts.addColumn(string);
		}
		for (String string : columNamesEdges) {
			defaultModelEdges.addColumn(string);
		}

		setSize(1346, 718);
		setLayout(null);

		JScrollPane scrollPane_edge = new JScrollPane();
		scrollPane_edge.setBounds(791, 293, 545, 299);
		add(scrollPane_edge);

		tblEdges = new JTable(defaultModelEdges);
		scrollPane_edge.setViewportView(tblEdges);

		JScrollPane scrollPane_vert = new JScrollPane();
		scrollPane_vert.setBounds(517, 293, 264, 299);
		add(scrollPane_vert);

		tblVerts = new JTable(defaultModelVerts);
		scrollPane_vert.setViewportView(tblVerts);

		JButton btnNewVert = new JButton("Opret Vertex");
		btnNewVert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vertex newVert = rCtr.createVertex(txtNewVertName.getText());
					txtNewVertName.setText("Skriv navn her");
					Integer newVertId = newVert.getId();
					String[] newVertToTable = { newVertId.toString(), newVert.getName() };
					defaultModelVerts.addRow(newVertToTable);
					defaultModelVerts.fireTableDataChanged();
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(tblVerts, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewVert.setBounds(681, 684, 100, 23);
		add(btnNewVert);

		JLabel lblNewVert = new JLabel("Ny vertex");
		lblNewVert.setBounds(642, 603, 60, 14);
		add(lblNewVert);

		txtNewVertName = new JTextField();
		txtNewVertName.setText("Skriv navn her");
		txtNewVertName.setBounds(642, 617, 139, 20);
		add(txtNewVertName);
		txtNewVertName.setColumns(10);

		JButton btnNewEdge = new JButton("Opret Edge");
		btnNewEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Edge newEdge = rCtr.createEdge(txtNewEdgeDescript.getText(),
							Integer.parseInt(txtNewEdgeSource.getText()), Integer.parseInt(txtNewEdgeDest.getText()),
							Integer.parseInt(txtNewEdgeWeight.getText()));
					txtNewVertName.setText("Skriv navn her");
					Integer newEdgeId = newEdge.getId();
					String[] newEdgeToTable = { newEdgeId.toString(), newEdge.getDescription(),
							newEdge.getSource().getName(), newEdge.getDestination().getName() };
					defaultModelEdges.addRow(newEdgeToTable);
					defaultModelEdges.fireTableDataChanged();
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(tblVerts, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewEdge.setBounds(1247, 684, 89, 23);
		add(btnNewEdge);

		txtNewEdgeSource = new JTextField();
		txtNewEdgeSource.setBounds(1094, 617, 89, 20);
		txtNewEdgeSource.setText("Start vertex id");
		add(txtNewEdgeSource);
		txtNewEdgeSource.setColumns(10);

		txtNewEdgeDest = new JTextField();
		txtNewEdgeDest.setBounds(1193, 617, 89, 20);
		txtNewEdgeDest.setText("Slut vertex id");
		add(txtNewEdgeDest);
		txtNewEdgeDest.setColumns(10);

		txtNewEdgeWeight = new JTextField();
		txtNewEdgeWeight.setBounds(1292, 617, 44, 20);
		txtNewEdgeWeight.setText("Vægt");
		add(txtNewEdgeWeight);
		txtNewEdgeWeight.setColumns(10);

		txtNewEdgeDescript = new JTextField();
		txtNewEdgeDescript.setBounds(1094, 648, 242, 20);
		txtNewEdgeDescript.setText("Beskrivelse");
		add(txtNewEdgeDescript);
		txtNewEdgeDescript.setColumns(10);

		JLabel lblNewEdge = new JLabel("Ny edge");
		lblNewEdge.setBounds(1094, 603, 46, 14);
		add(lblNewEdge);

		JLabel lblGraphAdmin = new JLabel("Graph administration:");
		lblGraphAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGraphAdmin.setBounds(517, 258, 170, 23);
		add(lblGraphAdmin);

		JSeparator separator_topGraph = new JSeparator();
		separator_topGraph.setBounds(517, 258, 819, 2);
		add(separator_topGraph);

		JSeparator separator_sideGraph = new JSeparator();
		separator_sideGraph.setOrientation(SwingConstants.VERTICAL);
		separator_sideGraph.setBounds(506, 259, 1, 448);
		add(separator_sideGraph);

		JButton btnDeleteVert = new JButton("Slet markeret vertex");
		btnDeleteVert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tblVerts.getSelectedRow();
				int vertId = Integer.parseInt(tblVerts.getModel().getValueAt(row, 0).toString());
				try {
					rCtr.deleteVertex(vertId);
				} catch (SQLException ee) {
					ConfirmationDialog.showMessageDialog(tblVerts, "Der skete en fejl under sletning.\nEr der forbindelse til databasen?", "Information",
							JOptionPane.WARNING_MESSAGE);
				}
				updateVertTable();
			}
		});
		btnDeleteVert.setBounds(517, 684, 131, 23);
		add(btnDeleteVert);

		JButton btnDeleteEdge = new JButton("Slet markeret edge");
		btnDeleteEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tblEdges.getSelectedRow();
				int edgeId = Integer.parseInt(tblEdges.getModel().getValueAt(row, 0).toString());
				try {
					rCtr.deleteEdge(edgeId);
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
				updateEdgeTable();
			}
		});
		btnDeleteEdge.setBounds(955, 684, 139, 23);
		add(btnDeleteEdge);

		JButton btnRedigerMarkeretEdge = new JButton("Rediger markeret edge");
		btnRedigerMarkeretEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblEdges.getSelectedRow();
				int edgeId = Integer.parseInt(tblEdges.getModel().getValueAt(row, 0).toString());
				EditEdge editEdge = new EditEdge(edgeId);
				editEdge.setSize(366, 210);
				editEdge.setVisible(true);
			}
		});
		btnRedigerMarkeretEdge.setBounds(791, 684, 154, 23);
		add(btnRedigerMarkeretEdge);

		txtEmpDis = new JTextField();
		txtEmpDis.setBounds(1250, 133, 86, 20);
		add(txtEmpDis);
		txtEmpDis.setColumns(10);

		txtCustDis = new JTextField();
		txtCustDis.setBounds(1250, 164, 86, 20);
		add(txtCustDis);
		txtCustDis.setColumns(10);

		txtLunchDisc = new JTextField();
		txtLunchDisc.setBounds(1250, 195, 86, 20);
		add(txtLunchDisc);
		txtLunchDisc.setColumns(10);

		allDiscounts = new LinkedList<Discount>();
		try {
			allDiscounts = oCtr.findAllDiscounts();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(tblVerts, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}

		for (int i = 0; i < allDiscounts.size(); i++) {
			Integer percentage = allDiscounts.get(i).getPercentage();
			if (allDiscounts.get(i).getType().equalsIgnoreCase("Employee")) {
				txtEmpDis.setText(percentage.toString());
			}
			if (allDiscounts.get(i).getType().equalsIgnoreCase("Valued Customers")) {
				txtCustDis.setText(percentage.toString());
			}
			if (allDiscounts.get(i).getType().equalsIgnoreCase("Lunch offer")) {
				txtLunchDisc.setText(percentage.toString());
			}

		}

		JLabel lblEmpDisc = new JLabel("Medarbejder:");
		lblEmpDisc.setBounds(1175, 136, 65, 14);
		add(lblEmpDisc);

		JLabel lblNewLabel = new JLabel("Stamkunde:");
		lblNewLabel.setBounds(1183, 167, 57, 14);
		add(lblNewLabel);

		JLabel lblFrokosttilbud = new JLabel("Frokosttilbud:");
		lblFrokosttilbud.setBounds(1175, 198, 69, 14);
		add(lblFrokosttilbud);

		JLabel lblDiscount = new JLabel("Rabatter:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiscount.setBounds(1175, 102, 65, 23);
		add(lblDiscount);

		JSeparator separator = new JSeparator();
		separator.setBounds(1166, 101, 170, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1164, 117, 1, 130);
		add(separator_1);

		JButton btnSaveDiscounts = new JButton("Gem ændringer");
		btnSaveDiscounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Nedenstående hardcoding af indexes er lavet ud fra
				// opfattelsen af at man i et pizzaria ikke skifter, eller
				// tilføjer rabattyper tit.
				try {
					if (!allDiscounts.get(0).getPercentage().toString().equalsIgnoreCase(txtEmpDis.getText())) {
						allDiscounts.get(0).setPercentage(Integer.parseInt(txtEmpDis.getText()));
						oCtr.updateDiscount(allDiscounts.get(0));
						txtEmpDis.setText(allDiscounts.get(0).getPercentage().toString());
					}
					if (!allDiscounts.get(1).getPercentage().toString().equalsIgnoreCase(txtCustDis.getText())) {
						allDiscounts.get(1).setPercentage(Integer.parseInt(txtCustDis.getText()));
						oCtr.updateDiscount(allDiscounts.get(1));
						txtCustDis.setText(allDiscounts.get(1).getPercentage().toString());
					}
					if (!allDiscounts.get(2).getPercentage().toString().equalsIgnoreCase(txtLunchDisc.getText())) {
						allDiscounts.get(2).setPercentage(Integer.parseInt(txtLunchDisc.getText()));
						oCtr.updateDiscount(allDiscounts.get(2));
						txtLunchDisc.setText(allDiscounts.get(2).getPercentage().toString());
					}
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(tblVerts, "Der skete en fejl.\nDine ændringer er ikke gemt!", "Advarsel",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSaveDiscounts.setBounds(1175, 224, 161, 23);
		add(btnSaveDiscounts);

		updateVertTable();
		updateEdgeTable();
	}

	public static void updateVertTable() {
		populateVertTable(defaultModelVerts);

	}

	public static void updateEdgeTable() {
		populateEdgeTable(defaultModelEdges);
	}

	private static void populateVertTable(DefaultTableModel defaultModelVerts) {
		defaultModelVerts.setRowCount(0);
		List<Vertex> allVertices = new LinkedList<Vertex>();

		try {
			rCtr = new RouteController();
			allVertices = rCtr.findAllVertices();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < allVertices.size(); i++) {
			Vertex vert = allVertices.get(i);
			Integer vertId = vert.getId();
			String[] toAddToTable = { vertId.toString(), vert.getName() };
			defaultModelVerts.addRow(toAddToTable);
		}
	}

	private static void populateEdgeTable(DefaultTableModel defaultModelEdges) {
		defaultModelEdges.setRowCount(0);
		List<Edge> allEdges = new LinkedList<Edge>();

		try {
			rCtr = new RouteController();
			allEdges = rCtr.findAllEdge();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog((Component)thisClass, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}
		for (int i = 0; i < allEdges.size(); i++) {
			Edge edge = allEdges.get(i);
			Integer edgeId = edge.getId();
			Integer edgeWeight = edge.getWeightVal();
			String[] toAddToTable = { edgeId.toString(), edge.getDescription(), edge.getSource().getName(),
					edge.getDestination().getName(), edgeWeight.toString() };
			defaultModelEdges.addRow(toAddToTable);
		}
	}
}

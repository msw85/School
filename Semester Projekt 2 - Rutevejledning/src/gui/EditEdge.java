package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.RouteController;
import model.Edge;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditEdge extends JFrame {
	private JTextField txtStartVert;
	private JTextField txtEndVert;
	private JTextField txtWeight;
	private JTextField txtDescription;
	private RouteController rCtr;
	private Edge edge;
	
	public EditEdge(int id) {

		try {
			rCtr = new RouteController();
			edge = rCtr.findEdgeById(id);
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}
		
		getContentPane().setLayout(null);
		
		txtStartVert = new JTextField();
		txtStartVert.setBounds(94, 11, 86, 20);
		Integer startVertId = edge.getSource().getId();
		txtStartVert.setText(startVertId.toString());
		getContentPane().add(txtStartVert);
		txtStartVert.setColumns(10);
		
		txtEndVert = new JTextField();
		txtEndVert.setBounds(94, 42, 86, 20);
		Integer endVertId = edge.getDestination().getId();
		txtEndVert.setText(endVertId.toString());
		getContentPane().add(txtEndVert);
		txtEndVert.setColumns(10);
		
		txtWeight = new JTextField();
		txtWeight.setBounds(94, 73, 86, 20);
		Integer weight = edge.getWeightVal();
		txtWeight.setText(weight.toString());
		getContentPane().add(txtWeight);
		txtWeight.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(94, 104, 244, 20);
		txtDescription.setText(edge.getDescription());
		getContentPane().add(txtDescription);
		txtDescription.setColumns(10);
		
		JLabel lblStartVertexId = new JLabel("Start vertex id:");
		lblStartVertexId.setBounds(10, 14, 74, 14);
		getContentPane().add(lblStartVertexId);
		
		JLabel lblEndVertexId = new JLabel("Slut vertex id:");
		lblEndVertexId.setBounds(14, 45, 70, 14);
		getContentPane().add(lblEndVertexId);
		
		JLabel lblWeight = new JLabel("Vægt:");
		lblWeight.setBounds(54, 76, 30, 14);
		getContentPane().add(lblWeight);
		
		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(27, 107, 57, 14);
		getContentPane().add(lblDescription);
		
		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rCtr.updateEdge(edge.getId(), txtDescription.getText(), Integer.parseInt(txtWeight.getText()), Integer.parseInt(txtEndVert.getText()), Integer.parseInt(txtStartVert.getText()));
				} catch (NumberFormatException e) {
					ConfirmationDialog.showMessageDialog(btnSave, "Der skete en fejl.\nMalformateret nummer indtastet!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e) {
					ConfirmationDialog.showMessageDialog(btnSave, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
							JOptionPane.WARNING_MESSAGE);
				}
				AdminGUI.updateEdgeTable();
				dispose();
			}
		});
		btnSave.setBounds(249, 135, 89, 23);
		getContentPane().add(btnSave);
	}
}

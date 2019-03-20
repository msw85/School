package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EditOrder extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtTotalPris;

	
		
		

	/**
	 * Create the dialog.
	 */
	public EditOrder() {
		try {
			EditOrder dialog = new EditOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		setBounds(100, 100, 685, 750);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblOrder = new JLabel("Ordre");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblOrder.setBounds(10, 11, 100, 25);
		contentPanel.add(lblOrder);
		
		table = new JTable();
		table.setBounds(10, 45, 645, 197);
		contentPanel.add(table);
		
		txtTotalPris = new JTextField();
		txtTotalPris.setColumns(10);
		txtTotalPris.setBounds(175, 275, 480, 40);
		contentPanel.add(txtTotalPris);
		
		JLabel lblTotalPris = new JLabel("Total pris:");
		lblTotalPris.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalPris.setBounds(10, 285, 155, 20);
		contentPanel.add(lblTotalPris);
		
		JButton btn_7 = new JButton("7");
		btn_7.setBounds(186, 357, 75, 75);
		contentPanel.add(btn_7);
		
		JButton btn_8 = new JButton("8");
		btn_8.setBounds(271, 357, 75, 75);
		contentPanel.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.setBounds(356, 357, 75, 75);
		contentPanel.add(btn_9);
		
		JButton btnCancel = new JButton("Anuller");
		btnCancel.setBounds(441, 357, 75, 75);
		contentPanel.add(btnCancel);
		
		JButton btnFinish = new JButton("Afslut");
		btnFinish.setBounds(441, 443, 75, 75);
		contentPanel.add(btnFinish);
		
		JButton btn_6 = new JButton("6");
		btn_6.setBounds(356, 443, 75, 75);
		contentPanel.add(btn_6);
		
		JButton btn_5 = new JButton("5");
		btn_5.setBounds(271, 443, 75, 75);
		contentPanel.add(btn_5);
		
		JButton btn_4 = new JButton("4");
		btn_4.setBounds(186, 443, 75, 75);
		contentPanel.add(btn_4);
		
		JButton btn_1 = new JButton("1");
		btn_1.setBounds(186, 529, 75, 75);
		contentPanel.add(btn_1);
		
		JButton btn_2 = new JButton("2");
		btn_2.setBounds(271, 529, 75, 75);
		contentPanel.add(btn_2);
		
		JButton btn_3 = new JButton("3");
		btn_3.setBounds(356, 529, 75, 75);
		contentPanel.add(btn_3);
		
		JButton btnAdd = new JButton("Tilføj");
		btnAdd.setBounds(441, 529, 75, 160);
		contentPanel.add(btnAdd);
		
		JButton btn_0 = new JButton("0");
		btn_0.setBounds(271, 615, 160, 75);
		contentPanel.add(btn_0);
		
		JButton btnExtras = new JButton("Tilbehør");
		btnExtras.setBounds(186, 615, 75, 75);
		contentPanel.add(btnExtras);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}

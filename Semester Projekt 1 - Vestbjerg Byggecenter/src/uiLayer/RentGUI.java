package uiLayer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllerLayer.RentController;
import modelLayer.Rent;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.ActionEvent;

public class RentGUI extends javax.swing.JPanel
{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private static DefaultTableModel d;
	private static RentController renCon;
	private static Rent[] rents;
	private int rid;
	private static boolean showNonActive;
	public RentGUI()
	{
		setSize(640, 420);
		setLayout(null);

		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Kunde");
		d.addColumn("Pris");
		d.addColumn("Start");
		d.addColumn("Slut");

		renCon = new RentController();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 11, 474, 399);
		add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 1) {
					rid = Integer.parseInt(table.getValueAt(row, 0).toString());
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2) {
					rid = Integer.parseInt(table.getValueAt(row, 0).toString());
					ShowRent a =  new ShowRent(rid);
					a.setSize(315, 450);
					a.setVisible(true);
				}
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(d);

		JButton btnNewButton = new JButton("Opret");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateRent create = new CreateRent();
				create.setSize(310, 490);
				create.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 10, 136, 23);
		add(btnNewButton);

		JButton button = new JButton("Aflevere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmations()) {
					renCon.toggleActive(rid);
					update();
				}
			}
		});
		button.setBounds(10, 45, 136, 23);
		add(button);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Vis Afleverede");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showNonActive = rdbtnNewRadioButton.isSelected();
				if(showNonActive) {
					updateNonAc();
					button.setEnabled(false);
				} else {
					update();
					button.setEnabled(true);
				}
			}
		});
		rdbtnNewRadioButton.setBounds(10, 80, 100, 23);
		add(rdbtnNewRadioButton);

		update();
	}
	/**
	 * 
	 */
	public static void updateNonAc() {
		rents = renCon.toArr();
		d.setRowCount(0);
		for (Rent rent : rents) {
			if(showNonActive && !rent.getState()) {
			String[] s = {Integer.toString(rent.getID()),  rent.getCustomer().getID().toString(), Double.toString(rent.getRentPrice()), rent.getStartDate().toString(), rent.getEndDate().toString() };
			d.addRow(s);
		}
		}
	} 

	public static void update() {
		rents = renCon.toArr();
		d.setRowCount(0);
		for (Rent rent : rents) {
			if(showNonActive) {
				String[] s = {Integer.toString(rent.getID()), rent.getCustomer().getID().toString(), Double.toString(rent.getRentPrice()), rent.getStartDate().toString(), rent.getEndDate().toString() };
				d.addRow(s);
			}
			else if(rent.getState()) {
				String[] s = {Integer.toString(rent.getID()), rent.getCustomer().getID().toString(), Double.toString(rent.getRentPrice()), rent.getStartDate().toString(), rent.getEndDate().toString() };
				d.addRow(s);			
			}
		}
	}

	public boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(this, "Er du sikker på at du vil udføre denne handling?", "Validering", optionType);
		if(result == JOptionPane.YES_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}
}
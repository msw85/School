package uiLayer;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllerLayer.SupplierController;
import modelLayer.Supplier;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupplierGUI extends JPanel {
	
	private static SupplierController supCon;

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSearchID;
	private JScrollPane scrollPane;
	private JTextField txtSearchName;
	private JButton btnEditSup;
	private JButton btnDelSup;
	private JSeparator separator;
	private static DefaultTableModel d;
	private static boolean showNonActive;
	private int sid;

	public SupplierGUI() {
		supCon = new SupplierController();
		
		setSize(640, 420);
		setLayout(null);		
		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Navn");
		d.addColumn("Beskrivelse");
		d.addColumn("Betalingsbetingelser");
		
		txtSearchID = new JTextField();
		txtSearchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtSearchID.getText().equals("")){
					d.setRowCount(0);
					populate(d);
				}else {
					d.setRowCount(0);
					int supID = Integer.parseInt(txtSearchID.getText());
					Supplier s = supCon.getSupplier(supID);
					if(s != null) {
						String[] arr = {Integer.toString(s.getID()), s.getName(), s.getDescription(), s.getBankInfo()};
						d.addRow(arr);
					}
				}
			}
		});
		
		txtSearchID.setBounds(10, 50, 109, 20);
		add(txtSearchID);
		txtSearchID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("På ID:");
		lblNewLabel.setBounds(10, 35, 70, 14);
		add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 9, 474, 400);
		add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table = (JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 1) {
		        	sid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		        	sid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        	ShowSup a =  new ShowSup(sid);
		        	a.setSize(305, 300);
		        	a.setVisible(true);
		        }
		    }
		});
		scrollPane.setViewportView(table);
		table.setModel(d);
		
		JLabel label = new JLabel("Søg leverandør");
		label.setBounds(10, 10, 88, 14);
		add(label);
		
		txtSearchName = new JTextField();
		txtSearchName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtSearchName.getText().equals("")){
					d.setRowCount(0);
					populate(d);
				} else {
					d.setRowCount(0);
					String supName = txtSearchName.getText();
					Supplier[] c = supCon.getSupplier(supName);
					for (Supplier supplier : c) {
						if(c != null && supplier.getState()) {
							String[] s = {Integer.toString(supplier.getID()), supplier.getName(), supplier.getDescription(), supplier.getBankInfo()};
							d.addRow(s);
						} else if(c != null && showNonActive) {
							String[] s = {Integer.toString(supplier.getID()), supplier.getName(), supplier.getDescription(), supplier.getBankInfo()};
							d.addRow(s);
						}
					}
					
				}
			}
		});
		txtSearchName.setColumns(10);
		txtSearchName.setBounds(10, 90, 109, 20);
		add(txtSearchName);
		
		JLabel lblPNavn = new JLabel("På navn:");
		lblPNavn.setBounds(10, 75, 70, 14);
		add(lblPNavn);
		
		JButton openCreDia = new JButton("Opret leverandør");
		openCreDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				           CreateSup a =  new CreateSup();
				           a.setSize(305, 275);
				           a.setVisible(true);
				    }
		});
		openCreDia.setBounds(10, 166, 136, 23);
		add(openCreDia);
		
		btnEditSup = new JButton("Rediger leverandør");
		btnEditSup.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditSup a =  new EditSup(sid);
					a.setSize(305, 275);
					a.setVisible(true);
			}
		});
		btnEditSup.setBounds(10, 201, 136, 23);
		add(btnEditSup);
		
		btnDelSup = new JButton("Slet leverandør");
		btnDelSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmations()) {
				supCon.toggleActive(sid);
				updateTable();
				}
			}
		});
		btnDelSup.setBounds(10, 235, 136, 23);
		add(btnDelSup);
		
		JRadioButton rdbtnVisSlettede = new JRadioButton("Vis Slettede");
		rdbtnVisSlettede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showNonActive = rdbtnVisSlettede.isSelected();
				if(showNonActive) {
					updateNonAc();
					btnDelSup.setEnabled(false);
				} else {
					updateTable();
					btnDelSup.setEnabled(true);
				}
			}
		});
		rdbtnVisSlettede.setBounds(10, 120, 109, 23);
		add(rdbtnVisSlettede);
		
		separator = new JSeparator();
		separator.setBounds(10, 150, 136, 2);
		add(separator);
		
		populate(d);
		
	}

	public static void updateTable() {
		populate(d);
	}
	
	public static void updateNonAc() {
		populateNonAc(d);
	}
	/**
	 * @param d
	 */
	private static void populate(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < supCon.size(); i++) {
			Supplier s = supCon.getSupplier(i);
			if(showNonActive) {
				String[] toTable = {Integer.toString(s.getID()), s.getName(), s.getDescription(), s.getBankInfo()};
				d.addRow(toTable);
			} else if(s.getState()) {
				String[] toTable = {Integer.toString(s.getID()), s.getName(), s.getDescription(), s.getBankInfo()};
				d.addRow(toTable);
			}
		}
	}
	
	private static void populateNonAc(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < supCon.size(); i++) {
			Supplier s = supCon.getSupplier(i);
			if(showNonActive && !s.getState()) {
				String[] toTable = {Integer.toString(s.getID()), s.getName(), s.getDescription(), s.getBankInfo()};
				d.addRow(toTable);
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

package uiLayer;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllerLayer.CustomerController;
import modelLayer.Customer;
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

public class CustomerGUI extends JPanel {
	
	static CustomerController cusCon;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSearchID;
	private JScrollPane scrollPane;
	private JTextField txtSearchName;
	private JButton btnRedigerKunde;
	private JButton btnSletKunde;
	private JSeparator separator;
	private static DefaultTableModel d;
	private static boolean showNonActive;
	
	private int cid;

	public CustomerGUI() {
		cusCon = new CustomerController();

		setSize(640, 420);
		setLayout(null);		
		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Navn");
		d.addColumn("Telefon");
		d.addColumn("Antal Ordrer");
		
		txtSearchID = new JTextField();
		txtSearchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtSearchID.getText().equals("")){
					d.setRowCount(0);
					populate(d);
				}else {
					d.setRowCount(0);
					int custID = Integer.parseInt(txtSearchID.getText());
					Customer c = cusCon.getCustomer(custID);
					if(c != null) {
						String[] arr = {Integer.toString(c.getID()), c.getName(), c.getPhone(), Integer.toString(c.getOrderSize())};
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
		scrollPane.setBounds(156, 10, 474, 399);
		add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table = (JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 1) {
		        	cid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		        	cid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        	ShowCust a =  new ShowCust(cid);
		        	a.setSize(250, 280);
		        	a.setVisible(true);
		        }
		    }
		});
		scrollPane.setViewportView(table);
		table.setModel(d);
		
		JLabel label = new JLabel("Søg kunde");
		label.setBounds(10, 10, 70, 14);
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
					String custName = txtSearchName.getText();
					Customer[] c = cusCon.getCustomer(custName);
					for (Customer customer : c) {
						if(c != null && customer.getState()) {
							String[] s = {Integer.toString(customer.getID()), customer.getName(), customer.getPhone(), Integer.toString(customer.getOrderSize())};
							d.addRow(s);
						} else if(c != null && showNonActive) {
							String[] s = {Integer.toString(customer.getID()), customer.getName(), customer.getPhone(), Integer.toString(customer.getOrderSize())};
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
		
		JButton openCreDia = new JButton("Opret Kunde");
		openCreDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				           CreateCust a =  new CreateCust();
				           a.setSize(250, 280);
				           a.setVisible(true);
				    }
		});
		openCreDia.setBounds(10, 166, 136, 23);
		add(openCreDia);
		
		btnRedigerKunde = new JButton("Rediger Kunde");
		btnRedigerKunde.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditCust a =  new EditCust(cid);
		           a.setSize(250, 280);
		           a.setVisible(true);
			}
		});
		btnRedigerKunde.setBounds(10, 201, 136, 23);
		add(btnRedigerKunde);
		
		btnSletKunde = new JButton("Slet Kunde");
		btnSletKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmations()) {
				cusCon.toggleActive(cid);
				updateTable();
				}
			}
		});
		btnSletKunde.setBounds(10, 235, 136, 23);
		add(btnSletKunde);
		
		JRadioButton rdbtnVisSlettede = new JRadioButton("Vis Slettede");
		rdbtnVisSlettede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showNonActive = rdbtnVisSlettede.isSelected();
				if(showNonActive) {
					updateNonAc();
					btnSletKunde.setEnabled(false);
				} else {
					updateTable();
					btnSletKunde.setEnabled(true);
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
		for (int i = 0; i < cusCon.size(); i++) {
			Customer c = cusCon.getCustomer(i);
			if(showNonActive) {
				String[] toTable = {Integer.toString(i), c.getName(), c.getPhone(), Integer.toString(c.getOrderSize())};
				d.addRow(toTable);
			} else if(c.getState()) {
				String[] toTable = {Integer.toString(i), c.getName(), c.getPhone(), Integer.toString(c.getOrderSize())};
				d.addRow(toTable);
			}
		}
	}
	
	private static void populateNonAc(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < cusCon.size(); i++) {
			Customer c = cusCon.getCustomer(i);
			if(showNonActive && !c.getState()) {
				String[] toTable = {Integer.toString(i), c.getName(), c.getPhone(), Integer.toString(c.getOrderSize())};
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

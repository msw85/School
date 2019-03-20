package uiLayer;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import controllerLayer.AccountController;
import controllerLayer.CustomerController;
import modelLayer.Account;
import modelLayer.Customer;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class AccountGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AccountController accCon;
	private static DefaultTableModel d;
	private JTable table;
	private JTextField txtCust;
	private JFormattedTextField txtAcc;
	private NumberFormatter formatter;
	private static boolean showNonActive;
	
	private CustomerController cusCon;
	
	private int aid;

	public AccountGUI() {
		accCon = new AccountController();
		cusCon = new CustomerController();
		formatter = UiUtil.initFormatter();

		setSize(644, 420);
		setLayout(null);

		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Betalingsaftale");
		d.addColumn("Håndværkerrabat");
		d.addColumn("Mængderabat");
		d.addColumn("Afhentningsrabat");
		d.addColumn("Balance");

		txtAcc = new JFormattedTextField(formatter);
		txtAcc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtAcc.getText().equals("")){
					d.setRowCount(0);
					update();
				} else {
					d.setRowCount(0);
					int accID = Integer.parseInt(txtAcc.getText());
					Account a = accCon.getAccount(accID);
					if(a != null) {
						String[] arr = {Integer.toString(a.getID()), a.getPaymentAgreement(), Double.toString(a.getCraftmansDiscount()), Double.toString(a.getAmountDiscount()),
								Double.toString(a.getPickupDiscount()), Double.toString(a.getBalance())};
						d.addRow(arr);
					}
				}
			}
			public void keyTyped(KeyEvent e) {
				if(txtAcc.getText().length() == 1 && e.getKeyChar() == '\b'){
					txtAcc.setValue(null);
					}
			}
		});
		txtAcc.setBounds(10, 50, 109, 20);
		add(txtAcc);
		txtAcc.setColumns(10);

		JLabel lblSchAcc = new JLabel("Søg konto");
		lblSchAcc.setBounds(10, 10, 78, 14);
		add(lblSchAcc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 10, 478, 399);
		add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table = (JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 1) {
		        	aid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        aid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        if (mouseEvent.getClickCount() == 2) {
		        	ShowAcc a =  new ShowAcc(aid);
		        	a.setSize(300, 300);
		        	a.setVisible(true);
		        }
		        
		    }
		});
		scrollPane.setViewportView(table);
		table.setModel(d);
		
		JLabel lblID = new JLabel("På ID:");
		lblID.setBounds(10, 35, 46, 14);
		add(lblID);
		
		JLabel lblCust = new JLabel("På kundeID:");
		lblCust.setBounds(10, 75, 78, 14);
		add(lblCust);
		
		txtCust = new JTextField();
		txtCust.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
					if(txtCust.getText().equals("")){
						d.setRowCount(0);
						update();
					} else {
						d.setRowCount(0);
						Customer cust = cusCon.getCustomer(Integer.parseInt(txtCust.getText()));
						if(cust != null) {
							Account[] a = accCon.toArr();
							for(Account acc : a) {
								if(acc.getCustomer().getID().equals(cust.getID())) {
									String[] s = {Integer.toString(acc.getID()), acc.getPaymentAgreement(), Double.toString(acc.getCraftmansDiscount()), Double.toString(acc.getAmountDiscount()),
											Double.toString(acc.getPickupDiscount()), Double.toString(acc.getBalance()), Boolean.toString(acc.getState())};
									d.addRow(s);
								}
							}
						}
					}
			}
		});
		txtCust.setBounds(10, 90, 109, 20);
		add(txtCust);
		txtCust.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 150, 136, 2);
		add(separator);
		
		JButton btnCreAcc = new JButton("Opret Konto");
		btnCreAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				           CreateAcc a =  new CreateAcc();
				           a.setSize(373, 273);
				           a.setVisible(true);
				    }
		});
		btnCreAcc.setBounds(10, 166, 136, 23);
		add(btnCreAcc);
		
		JButton btnREditAcc = new JButton("Rediger Konto");
		btnREditAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditAcc a =  new EditAcc(aid);
				a.setSize(250, 300);
				a.setVisible(true);
			}
		});
		btnREditAcc.setBounds(10, 201, 136, 23);
		add(btnREditAcc);
		
		JButton btnDelAcc = new JButton("Slet Konto");
		btnDelAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmations()) {
				accCon.toggleActive(aid);
				update();
				}
			}
		});
		btnDelAcc.setBounds(10, 235, 136, 23);
		add(btnDelAcc);
		

		JRadioButton rdbtnVisSlettede = new JRadioButton("Vis slettede");
		rdbtnVisSlettede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showNonActive = rdbtnVisSlettede.isSelected();
				if(showNonActive) {
					updateNonAc();
					btnDelAcc.setEnabled(false);
				} else {
					update();
					btnDelAcc.setEnabled(true);
				}

			}
		});
		rdbtnVisSlettede.setBounds(10, 120, 109, 23);
		add(rdbtnVisSlettede);

		updateTable(d);

	}
	
	

	private static void updateTable(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < accCon.size(); i++) {
			Account acc = accCon.getAccount(i);
			if(showNonActive) {
				String[] s = {Integer.toString(acc.getID()), acc.getPaymentAgreement(), Double.toString(acc.getCraftmansDiscount()), Double.toString(acc.getAmountDiscount()),
						Double.toString(acc.getPickupDiscount()), Double.toString(acc.getBalance()), Boolean.toString(acc.getState())};
				d.addRow(s);
			} else if(acc.getState()) {
				String[] s = {Integer.toString(acc.getID()), acc.getPaymentAgreement(), Double.toString(acc.getCraftmansDiscount()), Double.toString(acc.getAmountDiscount()),
						Double.toString(acc.getPickupDiscount()), Double.toString(acc.getBalance()), Boolean.toString(acc.getState())};
				d.addRow(s);
			}
		}
	}
	
	
	private static void updateTableNonAc(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < accCon.size(); i++) {
			Account acc = accCon.getAccount(i);
			if(showNonActive && !acc.getState()) {
				String[] s = {Integer.toString(acc.getID()), acc.getPaymentAgreement(), Double.toString(acc.getCraftmansDiscount()), Double.toString(acc.getAmountDiscount()),
						Double.toString(acc.getPickupDiscount()), Double.toString(acc.getBalance()), Boolean.toString(acc.getState())};
				d.addRow(s);
			} 
		}
	}
	
	public static void updateNonAc() {
		updateTableNonAc(d);
	}
	
	public static void update() {
		updateTable(d);
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

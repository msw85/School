package uiLayer;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllerLayer.CustomerController;
import controllerLayer.EmployeeController;
import modelLayer.Customer;
import modelLayer.Employee;

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

public class EmployeeGUI extends JPanel {
	
	static EmployeeController empCon;

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
	
	private int eid;

	public EmployeeGUI() {
		empCon = new EmployeeController();

		setSize(640, 420);
		setLayout(null);		
		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Navn");
		d.addColumn("Telefon");
		d.addColumn("Timeløn");
		
		txtSearchID = new JTextField();
		txtSearchID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtSearchID.getText().equals("")){
					d.setRowCount(0);
					populate(d);
				}else {
					d.setRowCount(0);
					int empID = Integer.parseInt(txtSearchID.getText());
					Employee e = empCon.getEmployee(empID);
					if(e != null) {
						String[] arr = {Integer.toString(e.getID()), e.getName(), e.getPhone(), Double.toString(e.getSalery())};
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
		        	eid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		        	eid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        	ShowEmp a =  new ShowEmp(eid);
		        	a.setSize(250, 350);
		        	a.setVisible(true);
		        }
		    }
		});
		scrollPane.setViewportView(table);
		table.setModel(d);
		
		JLabel lblSgMedarbejder = new JLabel("Søg medarbejder");
		lblSgMedarbejder.setBounds(10, 10, 87, 14);
		add(lblSgMedarbejder);
		
		txtSearchName = new JTextField();
		txtSearchName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtSearchName.getText().equals("")){
					d.setRowCount(0);
					populate(d);
				} else {
					d.setRowCount(0);
					String empName = txtSearchName.getText();
					Employee[] e = empCon.getEmployee(empName);
					for (Employee employee : e) {
						if(e != null && employee.getState()) {
							String[] s = {Integer.toString(employee.getID()), employee.getName(), employee.getPhone(), Double.toString(employee.getSalery())};
							d.addRow(s);
						} else if(e != null && showNonActive) {
							String[] s = {Integer.toString(employee.getID()), employee.getName(), employee.getPhone(), Double.toString(employee.getSalery())};
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
		
		JButton openCreDia = new JButton("Opret Medarbejder");
		openCreDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				           CreateEmp a =  new CreateEmp();
				           a.setSize(250, 350);
				           a.setVisible(true);
				    }
		});
		openCreDia.setBounds(10, 166, 136, 23);
		add(openCreDia);
		
		btnRedigerKunde = new JButton("Rediger Medarbejder");
		btnRedigerKunde.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditEmp a =  new EditEmp(eid);
		           a.setSize(250, 350);
		           a.setVisible(true);
			}
		});
		btnRedigerKunde.setBounds(10, 201, 136, 23);
		add(btnRedigerKunde);
		
		btnSletKunde = new JButton("Slet Medarbejder");
		btnSletKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmations()) {
				empCon.toggleActive(eid);
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
		for (int i = 0; i < empCon.size(); i++) {
			Employee e = empCon.getEmployee(i);
			if(showNonActive) {
				String[] toTable = {Integer.toString(e.getID()), e.getName(), e.getPhone(), Double.toString(e.getSalery())};
				d.addRow(toTable);
			} else if(e.getState()) {
				String[] toTable = {Integer.toString(e.getID()), e.getName(), e.getPhone(), Double.toString(e.getSalery())};
				d.addRow(toTable);
			}
		}
	}
	
	private static void populateNonAc(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < empCon.size(); i++) {
			Employee e = empCon.getEmployee(i);
			if(showNonActive && !e.getState()) {
				String[] toTable = {Integer.toString(e.getID()), e.getName(), e.getPhone(), Double.toString(e.getSalery())};
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

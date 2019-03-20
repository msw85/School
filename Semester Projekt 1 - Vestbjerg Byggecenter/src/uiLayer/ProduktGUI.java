package uiLayer;

import javax.swing.JPanel;

import controllerLayer.ProductController;
import modelLayer.Account;
import modelLayer.Customer;
import modelLayer.Product;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JSeparator;

public class ProduktGUI extends JPanel {

	private static ProductController proCon;
	private JTextField txtSearch;
	private JTable table;
	private static DefaultTableModel d;
	private static boolean showNonActive;
	private int pid;
	private static boolean dialogResult;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProduktGUI() {
		proCon = new ProductController();
		
		setSize(640, 420);
		setLayout(null);

		d = UiUtil.getTable();
		d.addColumn("ID");
		d.addColumn("Navn");
		d.addColumn("Pris");


		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				d.setRowCount(0);
				String prodName = txtSearch.getText();
				Product[] p = proCon.getProduct(prodName);

				for(Product product : p) {
					if(p != null && product.getState()) {
						String[] arr = {Integer.toString(product.getID()), product.getName(), Double.toString(product.getPrice())};
						d.addRow(arr);
					}
				}
			}

		});
		txtSearch.setBounds(10, 50, 109, 20);
		add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnCreate = new JButton("Opret produkt");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				           CreateProd p =  new CreateProd();
				           p.setSize(250, 540);
				           p.setVisible(true);
				    }
		});
		btnCreate.setBounds(10, 166, 136, 23);
		add(btnCreate);

		JButton btnEdit = new JButton("Rediger produkt");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProd a =  new EditProd(pid);
		           a.setSize(260, 505);
		           a.setVisible(true);
			}
		});
		btnEdit.setBounds(10, 201, 136, 23);
		add(btnEdit);

		JButton btnDelete = new JButton("Slet produkt");
		btnDelete.addMouseListener(new MouseAdapter() {
			public void  mousePressed(MouseEvent e) {
				if(confirmations()) {
				proCon.toggleActive(pid);			
				updateTable();
				}
				
			}
		});
		btnDelete.setBounds(10, 235, 136, 23);
		add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 10, 474, 399);
		add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table = (JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 1) {
		        	pid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		        	pid = Integer.parseInt(table.getValueAt(row, 0).toString());
		        	ShowProd a =  new ShowProd(pid);
		        	a.setSize(250, 540);
		        	a.setVisible(true);
		        }
		    }
		});
		scrollPane.setViewportView(table);
		table.setModel(d);

		populate(d);

		JLabel lblSg = new JLabel("Søg produkt");
		lblSg.setBounds(10, 10, 97, 16);
		add(lblSg);
		
		JLabel lblPNavn = new JLabel("På navn:");
		lblPNavn.setBounds(10, 35, 56, 16);
		add(lblPNavn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 150, 136, 2);
		add(separator);

		

	}
	
	public static void updateTable() {
		populate(d);
	}
	

	private static void populate(DefaultTableModel d) {
		d.setRowCount(0);
		for (int i = 0; i < proCon.size(); i++) {
			Product p = proCon.getProduct(i); 
			if(p.getState())
			{
			String[] toTable = {Integer.toString(p.getID()), p.getName(), Double.toString(p.getPrice())};
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

package uiLayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import controllerLayer.AccountController;
import controllerLayer.CustomerController;
import controllerLayer.OrderController;
import controllerLayer.ProductController;
import modelLayer.Product;

public class SaleGUI extends JPanel {

	private JTextField txtAmt;
	JFormattedTextField txtCus;
	private JButton btnSale;
	private static ProductController proCon;
	private static OrderController odrCon;
	private AccountController accCon;
	private CustomerController cusCon;
	private static final long serialVersionUID = 1L;
	private JTextField txtPro;
	private JTable tblSale;
	private JCheckBox chckbxAcc;
	static DefaultListModel<String> productListModel;
	

	public SaleGUI() {
		proCon = new ProductController();
		odrCon = new OrderController();
		cusCon = new CustomerController();
		accCon = new AccountController();
		setLayout(null);
		setSize(640, 420);

		DefaultTableModel orderTableModel = UiUtil.getTable();
		productListModel = new DefaultListModel<String>();
		NumberFormatter formatter = UiUtil.initFormatter();

		JLabel lblNewLabel = new JLabel("Kunde ID:");
		lblNewLabel.setBounds(10, 10, 72, 14);
		add(lblNewLabel);
		
		JButton btnAdd = new JButton("Tilføj");
		btnAdd.setBounds(10, 207, 136, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] tempOdrLine = {txtAmt.getText(), proCon.getProduct(Integer.parseInt(txtPro.getText())).getName()};
				orderTableModel.addRow(tempOdrLine);
				int pro = Integer.parseInt(txtPro.getText());
				int amtnt = Integer.parseInt(txtAmt.getText());
				odrCon.addProduct(pro, amtnt);
			}
		});
		add(btnAdd);
		
		JButton btnCncl = new JButton("Annuller salg");
		btnCncl.setBounds(10, 386, 136, 23);
		btnCncl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCncl);

		orderTableModel.addColumn("Antal");
		orderTableModel.addColumn("Produkt");
		
		btnSale = new JButton("Afslut salg");
		btnSale.setBounds(10, 352, 136, 23);
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFinishClick(orderTableModel);
				
				txtCus.setText("");
				txtPro.setText("");
				txtAmt.setText("");
				chckbxAcc.setSelected(false);
				chckbxAcc.setEnabled(false);
			}
		});
		add(btnSale);
			
		txtPro = new JTextField();
		txtPro.setColumns(10);
		txtPro.setBounds(10, 130, 109, 20);
		add(txtPro);
		
		JLabel lblKonto = new JLabel("Konto køb:");
		lblKonto.setBounds(10, 53, 72, 23);
		add(lblKonto);
		
	
		
		
		JLabel lblNewLabel_1 = new JLabel("Produkt ID:");
		lblNewLabel_1.setBounds(10, 111, 72, 20);
		add(lblNewLabel_1);
		
				
		JSeparator separator = new JSeparator();
		separator.setBounds(108, 150, 0, 2);
		separator.setForeground(new Color(0, 0, 0));
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(113, 150, 0, 2);
		separator_1.setForeground(new Color(0, 0, 0));
		add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("Antal:");
		lblNewLabel_2.setBounds(10, 158, 62, 21);
		add(lblNewLabel_2);
		
		txtAmt = new JTextField();
		txtAmt.setBounds(10, 176, 109, 20);
		add(txtAmt);
		txtAmt.setColumns(10);
		
		txtCus = new JFormattedTextField(formatter);
		txtCus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtCus.getText().equals("")) {
					chckbxAcc.setEnabled(false);
				}
				else {
					chckbxAcc.setEnabled(true);
				}
					
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtCus.getText().length() == 1 && e.getKeyChar() == '\b'){
					txtCus.setValue(null);
					}

			}
		});
		txtCus.setBounds(10, 25, 109, 20);
		add(txtCus);
		
		chckbxAcc = new JCheckBox("");
		chckbxAcc.setEnabled(false);
		chckbxAcc.setBounds(7, 70, 72, 23);
		add(chckbxAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 10, 225, 399);
		add(scrollPane);
		
		JList<String> lisPro = new JList<String>(productListModel);
		scrollPane.setViewportView(lisPro);
		lisPro.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Integer indexsomething = lisPro.getSelectedIndex();
				txtPro.setText(indexsomething.toString());
			}
		});
		
		
		lisPro.setValueIsAdjusting(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(405, 10, 225, 399);
		add(scrollPane_1);
		tblSale = new JTable(orderTableModel);
		scrollPane_1.setViewportView(tblSale);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 98, 136, 2);
		add(separator_2);
		
		updateProducts(productListModel);
		
		
	}

	private static void updateProducts(DefaultListModel<String> productListModel) {
		productListModel.removeAllElements();
		Product[] prodArr = proCon.toArr();
		for (Product product : prodArr) {
			productListModel.addElement(product.getName() + ", " + product.getColor());
		}
	}

	/**
	 * @param odrTable
	 * @param orderListModel
	 */
	private void btnFinishClick(DefaultTableModel orderTableModel) {
		odrCon.addOrder("a", 1d, 1d);	

		if(txtCus.getText().equals("")) {
			odrCon.finishOrder();
		}
		else if(chckbxAcc.isSelected()) {
			for (int i = 0; i < accCon.size(); i++) {
				if(accCon.getAccount(i).hasCustomer()) {
					int a = Integer.parseInt(txtCus.getText());
					if(accCon.getAccount(i).getCustomer().getID() == a) {
						cusCon.addOrder(a, odrCon.getLastOrder()-1);
						odrCon.finishOrder(a, accCon.getTotalDiscount(i));
					}					
				}
			}
		}
		else if(!txtCus.getText().equals("")){
			int a = Integer.parseInt(txtCus.getText());
			cusCon.addOrder(a, odrCon.getLastOrder()-1);
			odrCon.finishOrder(odrCon.getLastOrder()-1);
		}
		orderTableModel.setRowCount(0);
		OrderGUI.update();
		
	}
	
	public static void updateProLis() {
		updateProducts(productListModel);
	}
	

}

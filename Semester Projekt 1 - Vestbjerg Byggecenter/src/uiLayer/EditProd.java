package uiLayer;

import javax.swing.JDialog;

import controllerLayer.ProductController;
import controllerLayer.SupplierController;
import modelLayer.Supplier;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EditProd extends JDialog {

	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtDesc;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtAmount;
	private JTextField txtMinStock;
	private JTextField txtMaxStock;
	private JTextField txtRentPrice;
	private JTextField txtDiscount;
	private JTextField txtSupPrice;
	private Supplier suppToAdd;

	private ProductController proCon = new ProductController();
	private SupplierController supCon = new SupplierController();
	
	public EditProd(int id) {
		getContentPane().setLayout(null);

		JButton btnEdit = new JButton("Gem");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Integer minStock;
			    Integer maxStock;
			    String name;
			    String type;
			    String serialNumber;
			    String description;
			    String color;
			    double rentPrice;
			    double price;
			    double amount;
			    double supplierPrice;
			    double discount;
				
				if(txtName.getText().equals("")) {
					name = proCon.getProduct(id).getName();
				} else {
					name = txtName.getText();
				}
				if(txtAmount.getText().equals("")) {
					amount = proCon.getProduct(id).getAmount();
				} else {
					amount = Double.parseDouble(txtAmount.getText());
				}
				if(txtColor.getText().equals("")) {
					color = proCon.getProduct(id).getColor();
				} else {
					color = txtColor.getText();
				}
				if(txtDesc.getText().equals("")) {
					description = proCon.getProduct(id).getDescription();
				} else {
					description = txtDesc.getText();
				}
				if(txtDiscount.getText().equals("")) {
					discount = proCon.getProduct(id).getDiscount();
				} else {
					discount = Double.parseDouble(txtDiscount.getText());
				}
				if(txtMaxStock.getText().equals("")) {
					maxStock = proCon.getProduct(id).getMaxStock();
				} else {
					maxStock = Integer.getInteger(txtMaxStock.getText()); 
				}
				if(txtMinStock.getText().equals("")) {
					minStock = proCon.getProduct(id).getMinStock();
				} else {
					minStock = Integer.getInteger(txtMinStock.getText());
				}
				if(txtPrice.getText().equals("")) {
					price = proCon.getProduct(id).getPrice();
				} else {
					price = Double.parseDouble(txtPrice.getText());
				}
				if(txtRentPrice.getText().equals("")) {
					rentPrice = proCon.getProduct(id).getRentPrice();
				} else {
					rentPrice = Double.parseDouble(txtRentPrice.getText());
				}
				if(txtSupPrice.getText().equals("")) {
					supplierPrice = proCon.getProduct(id).getSupplierPrice();
				} else {
					supplierPrice = Double.parseDouble(txtSupPrice.getText());
				}
				if(txtType.getText().equals("")) {
					type = proCon.getProduct(id).getType();
				} else {
					type = txtType.getText();
				}
				serialNumber = proCon.getProduct(id).getSerialNumber();
				proCon.updateProduct(id, minStock, maxStock, serialNumber, name, type, description, color, rentPrice, price, amount, supplierPrice, discount, suppToAdd);
				ProduktGUI.updateTable();
				dispose();
			}

		});
		btnEdit.setBounds(12, 432, 97, 25);
		getContentPane().add(btnEdit);

		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		btnCancel.setBounds(137, 432, 97, 25);
		getContentPane().add(btnCancel);		


		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(12, 16, 56, 16);
		getContentPane().add(lblNavn);

		txtName = new JTextField();
		txtName.setText(proCon.getProduct(id).getName());
		txtName.setBounds(96, 13, 138, 22);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(12, 51, 56, 16);
		getContentPane().add(lblType);

		txtType = new JTextField();
		txtType.setText(proCon.getProduct(id).getType());
		txtType.setBounds(96, 48, 138, 22);
		getContentPane().add(txtType);
		txtType.setColumns(10);

		JLabel lblBeskrivelse = new JLabel("Beskrivelse:");
		lblBeskrivelse.setBounds(12, 86, 85, 16);
		getContentPane().add(lblBeskrivelse);

		txtDesc = new JTextField();
		txtDesc.setText(proCon.getProduct(id).getDescription());
		txtDesc.setBounds(96, 83, 138, 22);
		getContentPane().add(txtDesc);
		txtDesc.setColumns(10);

		JLabel lblFarve = new JLabel("Farve:");
		lblFarve.setBounds(12, 122, 56, 16);
		getContentPane().add(lblFarve);

		txtColor = new JTextField();
		txtColor.setText(proCon.getProduct(id).getColor());
		txtColor.setBounds(96, 119, 138, 22);
		getContentPane().add(txtColor);
		txtColor.setColumns(10);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(12, 157, 56, 16);
		getContentPane().add(lblPris);

		txtPrice = new JTextField();
		txtPrice.setText(Double.toString(proCon.getProduct(id).getPrice()));
		txtPrice.setBounds(96, 154, 138, 22);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblMndge = new JLabel("Mængde:");
		lblMndge.setBounds(12, 192, 56, 16);
		getContentPane().add(lblMndge);

		txtAmount = new JTextField();
		txtAmount.setText(Double.toString(proCon.getProduct(id).getAmount()));
		txtAmount.setBounds(96, 189, 138, 22);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblMinLager = new JLabel("Min. Lager");
		lblMinLager.setBounds(12, 227, 85, 16);
		getContentPane().add(lblMinLager);

		txtMinStock = new JTextField();
		txtMinStock.setText(Integer.toString(proCon.getProduct(id).getMinStock()));
		txtMinStock.setBounds(96, 224, 138, 22);
		getContentPane().add(txtMinStock);
		txtMinStock.setColumns(10);

		JLabel lblMaxLager = new JLabel("Max. Lager");
		lblMaxLager.setBounds(12, 262, 85, 16);
		getContentPane().add(lblMaxLager);

		txtMaxStock = new JTextField();
		txtMaxStock.setText(Integer.toString(proCon.getProduct(id).getMaxStock()));
		txtMaxStock.setBounds(96, 259, 138, 22);
		getContentPane().add(txtMaxStock);
		txtMaxStock.setColumns(10);

		JLabel lblLnPris = new JLabel("Udlejningspris:");
		lblLnPris.setBounds(12, 293, 97, 16);
		getContentPane().add(lblLnPris);

		txtRentPrice = new JTextField();
		txtRentPrice.setText(Double.toString(proCon.getProduct(id).getRentPrice()));
		txtRentPrice.setBounds(96, 290, 138, 22);
		getContentPane().add(txtRentPrice);
		txtRentPrice.setColumns(10);

		JLabel lblRabat = new JLabel("Rabat:");
		lblRabat.setBounds(12, 328, 56, 16);
		getContentPane().add(lblRabat);

		txtDiscount = new JTextField();
		txtDiscount.setText(Double.toString(proCon.getProduct(id).getDiscount()));
		txtDiscount.setBounds(96, 325, 138, 22);
		getContentPane().add(txtDiscount);
		txtDiscount.setColumns(10);

		JLabel lblLeverandrPris = new JLabel("Leverandørpris:");
		lblLeverandrPris.setBounds(10, 363, 116, 16);
		getContentPane().add(lblLeverandrPris);

		txtSupPrice = new JTextField();
		txtSupPrice.setText(Double.toString(proCon.getProduct(id).getSupplierPrice()));
		txtSupPrice.setBounds(96, 360, 138, 22);
		getContentPane().add(txtSupPrice);
		txtSupPrice.setColumns(10);
		
		JComboBox<Supplier> comboSup = new JComboBox<Supplier>();
		int sup = proCon.getProduct(id).getSup().getID();
		for (int i = 0; i < supCon.size(); i++) {
			Supplier s = supCon.getSupplier(i);
			comboSup.addItem(s);
		}
		comboSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suppToAdd = (Supplier) comboSup.getSelectedItem();
			}
		});
		comboSup.setSelectedIndex(sup);
		comboSup.setBounds(96, 396, 138, 25);
		getContentPane().add(comboSup);
		
		JLabel lblLeverandr = new JLabel("Leverandør:");
		lblLeverandr.setBounds(12, 401, 85, 14);
		getContentPane().add(lblLeverandr);
	}
}

package uiLayer;

import javax.swing.JDialog;

import controllerLayer.ProductController;
import controllerLayer.SupplierController;
import modelLayer.Supplier;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CreateProd extends JDialog{

	private ProductController proCon = new ProductController();
	private SupplierController supCon = new SupplierController();

	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtDesc;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtMinStock;
	private JTextField txtMaxStock;
	private JTextField txtSerial;
	private JTextField txtAmount;
	private JTextField txtRentPrice;
	private JTextField txtDiscount;
	private JTextField txtSupPrice;
	private Supplier suppToAdd;

	public CreateProd() {
		getContentPane().setLayout(null);

		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtAmount.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtColor.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtDesc.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtMaxStock.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtMinStock.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtPrice.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtSerial.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtType.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtSupPrice.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtRentPrice.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(txtDiscount.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligts!");
				}
				else if(!txtName.getText().equals("Udfyld venligst!") && !txtAmount.getText().equals("Udfyld venligst!") && !txtColor.getText().equals("Udfyld venligst!") && 
						!txtDesc.getText().equals("Udfyld venligst!") && !txtMaxStock.getText().equals("Udfyld venligst!") && !txtMinStock.getText().equals("Udfyld venligst!")
						&& !txtPrice.getText().equals("Udfyld venligst!") && !txtSerial.getText().equals("Udfyld venligst!") && !txtType.getText().equals("Udfyld venligst!")
						&& !txtSupPrice.getText().equals("Udfyld venligst!") && !txtDiscount.getText().equals("Udfyld venligst!") && !txtRentPrice.getText().equals("Udfyld venligst!")) {
					proCon.addProduct(Integer.parseInt(txtMinStock.getText()), Integer.parseInt(txtMaxStock.getText()), txtSerial.getText(), txtName.getText(), txtType.getText(), txtDesc.getText(), txtColor.getText(), 
							Double.parseDouble(txtRentPrice.getText()), Double.parseDouble(txtPrice.getText()), Double.parseDouble(txtAmount.getText()), Double.parseDouble(txtSupPrice.getText()), Double.parseDouble(txtDiscount.getText()), suppToAdd);
					ProduktGUI.updateTable();
					txtName.setText("");
					txtAmount.setText("");
					txtColor.setText("");
					txtDesc.setText("");
					txtDiscount.setText("");
					txtMaxStock.setText("");
					txtMinStock.setText("");
					txtPrice.setText("");
					txtRentPrice.setText("");
					txtSerial.setText("");
					txtSupPrice.setText("");
					txtType.setText("");
				}
				SaleGUI.updateProLis();
			}
		});

		btnCreate.setBounds(12, 460, 97, 25);
		getContentPane().add(btnCreate);

		JButton btnExit = new JButton("Annuller");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(124, 460, 97, 25);
		getContentPane().add(btnExit);

		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(12, 13, 56, 16);
		getContentPane().add(lblNavn);

		txtName = new JTextField();
		txtName.setBounds(95, 10, 126, 22);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(12, 48, 56, 16);
		getContentPane().add(lblType);

		txtType = new JTextField();
		txtType.setBounds(95, 45, 126, 22);
		getContentPane().add(txtType);
		txtType.setColumns(10);

		JLabel lblBeskrivelse = new JLabel("Beskrivelse:");
		lblBeskrivelse.setBounds(12, 83, 73, 16);
		getContentPane().add(lblBeskrivelse);

		txtDesc = new JTextField();
		txtDesc.setBounds(95, 80, 126, 22);
		getContentPane().add(txtDesc);
		txtDesc.setColumns(10);

		JLabel lblFarve = new JLabel("Farve:");
		lblFarve.setBounds(12, 118, 56, 16);
		getContentPane().add(lblFarve);

		txtColor = new JTextField();
		txtColor.setBounds(95, 115, 126, 22);
		getContentPane().add(txtColor);
		txtColor.setColumns(10);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(12, 153, 56, 16);
		getContentPane().add(lblPris);

		txtPrice = new JTextField();
		txtPrice.setBounds(95, 150, 126, 22);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblMinLager = new JLabel("Min. lager:");
		lblMinLager.setBounds(12, 258, 83, 16);
		getContentPane().add(lblMinLager);

		txtMinStock = new JTextField();
		txtMinStock.setBounds(95, 255, 126, 22);
		getContentPane().add(txtMinStock);
		txtMinStock.setColumns(10);

		JLabel lblMaxLager = new JLabel("Max. Lager:");
		lblMaxLager.setBounds(12, 293, 83, 16);
		getContentPane().add(lblMaxLager);

		txtMaxStock = new JTextField();
		txtMaxStock.setBounds(95, 290, 126, 22);
		getContentPane().add(txtMaxStock);
		txtMaxStock.setColumns(10);

		JLabel lblSerieNr = new JLabel("Serienr:");
		lblSerieNr.setBounds(12, 188, 56, 16);
		getContentPane().add(lblSerieNr);

		txtSerial = new JTextField();
		txtSerial.setBounds(95, 185, 126, 22);
		getContentPane().add(txtSerial);
		txtSerial.setColumns(10);

		JLabel lblMngde = new JLabel("Mængde:");
		lblMngde.setBounds(12, 223, 56, 16);
		getContentPane().add(lblMngde);

		txtAmount = new JTextField();
		txtAmount.setBounds(95, 220, 126, 22);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblLnPris = new JLabel("Udlejningspris:");
		lblLnPris.setBounds(12, 328, 97, 16);
		getContentPane().add(lblLnPris);

		txtRentPrice = new JTextField();
		txtRentPrice.setBounds(95, 325, 126, 22);
		getContentPane().add(txtRentPrice);
		txtRentPrice.setColumns(10);

		JLabel lblRabat = new JLabel("Rabat:");
		lblRabat.setBounds(12, 359, 56, 16);
		getContentPane().add(lblRabat);

		txtDiscount = new JTextField();
		txtDiscount.setBounds(95, 356, 126, 22);
		getContentPane().add(txtDiscount);
		txtDiscount.setColumns(10);

		JLabel lblNewLabel = new JLabel("Leverandørpris:");
		lblNewLabel.setBounds(12, 394, 97, 16);
		getContentPane().add(lblNewLabel);

		txtSupPrice = new JTextField();
		txtSupPrice.setBounds(95, 391, 126, 22);
		getContentPane().add(txtSupPrice);
		txtSupPrice.setColumns(10);
		
		JComboBox<Supplier> comboSup = new JComboBox<Supplier>();
		for (int i = 0; i < supCon.size(); i++) {
			Supplier s = supCon.getSupplier(i);
			comboSup.addItem(s);
		}
		comboSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suppToAdd = (Supplier) comboSup.getSelectedItem();
			}
		});
		comboSup.setSelectedIndex(0);
		comboSup.setBounds(95, 424, 126, 25);
		getContentPane().add(comboSup);
		
		JLabel lblLeverandr = new JLabel("Leverandør:");
		lblLeverandr.setBounds(10, 429, 85, 14);
		getContentPane().add(lblLeverandr);


	}
}

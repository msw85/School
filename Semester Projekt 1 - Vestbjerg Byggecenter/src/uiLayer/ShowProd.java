package uiLayer;

import javax.swing.JDialog;

import controllerLayer.ProductController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowProd extends JDialog{

	private ProductController proCon = new ProductController();

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
	private JTextField txtSupp;

	public ShowProd(int id) {
		getContentPane().setLayout(null);

		JButton btnExit = new JButton("Luk");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(133, 464, 97, 25);
		getContentPane().add(btnExit);

		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(12, 13, 56, 16);
		getContentPane().add(lblNavn);

		txtName = new JTextField();
		txtName.setText(proCon.getProduct(id).getName());
		txtName.setEditable(false);
		txtName.setBounds(114, 10, 116, 22);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(12, 48, 56, 16);
		getContentPane().add(lblType);

		txtType = new JTextField();
		txtType.setText(proCon.getProduct(id).getType());
		txtType.setEditable(false);
		txtType.setBounds(114, 45, 116, 22);
		getContentPane().add(txtType);
		txtType.setColumns(10);

		JLabel lblBeskrivelse = new JLabel("Beskrivelse:");
		lblBeskrivelse.setBounds(12, 83, 92, 16);
		getContentPane().add(lblBeskrivelse);

		txtDesc = new JTextField();
		txtDesc.setText(proCon.getProduct(id).getDescription());
		txtDesc.setEditable(false);
		txtDesc.setBounds(114, 80, 116, 22);
		getContentPane().add(txtDesc);
		txtDesc.setColumns(10);

		JLabel lblFarve = new JLabel("Farve:");
		lblFarve.setBounds(12, 118, 56, 16);
		getContentPane().add(lblFarve);

		txtColor = new JTextField();
		txtColor.setText(proCon.getProduct(id).getColor());
		txtColor.setEditable(false);
		txtColor.setBounds(114, 115, 116, 22);
		getContentPane().add(txtColor);
		txtColor.setColumns(10);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(12, 153, 56, 16);
		getContentPane().add(lblPris);

		txtPrice = new JTextField();
		txtPrice.setText(Double.toString(proCon.getProduct(id).getPrice()));
		txtPrice.setEditable(false);
		txtPrice.setBounds(114, 150, 116, 22);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblMinLager = new JLabel("Min. lager:");
		lblMinLager.setBounds(12, 258, 83, 16);
		getContentPane().add(lblMinLager);

		txtMinStock = new JTextField();
		txtMinStock.setText(Integer.toString(proCon.getProduct(id).getMinStock()));
		txtMinStock.setEditable(false);
		txtMinStock.setBounds(114, 255, 116, 22);
		getContentPane().add(txtMinStock);
		txtMinStock.setColumns(10);

		JLabel lblMaxLager = new JLabel("Max. Lager:");
		lblMaxLager.setBounds(12, 293, 83, 16);
		getContentPane().add(lblMaxLager);

		txtMaxStock = new JTextField();
		txtMaxStock.setText(Integer.toString(proCon.getProduct(id).getMaxStock()));
		txtMaxStock.setEditable(false);
		txtMaxStock.setBounds(114, 290, 116, 22);
		getContentPane().add(txtMaxStock);
		txtMaxStock.setColumns(10);

		JLabel lblSerieNr = new JLabel("Serienr:");
		lblSerieNr.setBounds(12, 188, 56, 16);
		getContentPane().add(lblSerieNr);

		txtSerial = new JTextField();
		txtSerial.setText(proCon.getProduct(id).getSerialNumber());
		txtSerial.setEditable(false);
		txtSerial.setBounds(114, 185, 116, 22);
		getContentPane().add(txtSerial);
		txtSerial.setColumns(10);

		JLabel lblMngde = new JLabel("Mængde:");
		lblMngde.setBounds(12, 223, 56, 16);
		getContentPane().add(lblMngde);

		txtAmount = new JTextField();
		txtAmount.setText(Double.toString(proCon.getProduct(id).getAmount()));
		txtAmount.setEditable(false);
		txtAmount.setBounds(114, 220, 116, 22);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblLnPris = new JLabel("Udlejningspris:");
		lblLnPris.setBounds(12, 328, 97, 16);
		getContentPane().add(lblLnPris);

		txtRentPrice = new JTextField();
		txtRentPrice.setText(Double.toString(proCon.getProduct(id).getRentPrice()));
		txtRentPrice.setEditable(false);
		txtRentPrice.setBounds(114, 325, 116, 22);
		getContentPane().add(txtRentPrice);
		txtRentPrice.setColumns(10);

		JLabel lblRabat = new JLabel("Rabat:");
		lblRabat.setBounds(12, 359, 56, 16);
		getContentPane().add(lblRabat);

		txtDiscount = new JTextField();
		txtDiscount.setText(Double.toString(proCon.getProduct(id).getDiscount()));
		txtDiscount.setEditable(false);
		txtDiscount.setBounds(114, 356, 116, 22);
		getContentPane().add(txtDiscount);
		txtDiscount.setColumns(10);

		JLabel lblNewLabel = new JLabel("Leverandørpris:");
		lblNewLabel.setBounds(12, 394, 97, 16);
		getContentPane().add(lblNewLabel);

		txtSupPrice = new JTextField();
		txtSupPrice.setText(Double.toString(proCon.getProduct(id).getSupplierPrice()));
		txtSupPrice.setEditable(false);
		txtSupPrice.setBounds(114, 391, 116, 22);
		getContentPane().add(txtSupPrice);
		txtSupPrice.setColumns(10);
		
		txtSupp = new JTextField();
		txtSupp.setText(proCon.getSup(id).getName());
		txtSupp.setEditable(false);
		txtSupp.setBounds(114, 424, 116, 20);
		getContentPane().add(txtSupp);
		txtSupp.setColumns(10);
		
		JLabel lblSupp = new JLabel("Leverandør:");
		lblSupp.setBounds(10, 427, 69, 14);
		getContentPane().add(lblSupp);


	}
}

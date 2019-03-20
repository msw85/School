package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import controller.FoodController;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;


public class CreateFood extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtSize;
	private JRadioButton rdbtnPizza;
	private JRadioButton rdbtnFastFood;
	private JRadioButton rdbtnDrink;
	private JLabel lblLunchOffer;
	private FoodController foodCtr;
	private JCheckBox chkBoxOffer;
	private JComboBox<Object> comboBox;
	private JLabel lblBrand;
	private String[] brands;
	private NumberFormatter formatter;
	private JLabel lblDiet;

	public CreateFood() {
		getContentPane().setLayout(null);

		formatter = UiUtil.initFormatter();
		try {
			foodCtr = new FoodController();
			brands = foodCtr.findBrandsString();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}

		rdbtnPizza = new JRadioButton("Pizza");
		rdbtnPizza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnDrink.setSelected(false);
				rdbtnPizza.setSelected(true);
				rdbtnFastFood.setSelected(false);
				updateText();
			}
		});

		rdbtnPizza.setSelected(true);
		rdbtnPizza.setBounds(0, 0, 49, 23);
		getContentPane().add(rdbtnPizza);

		rdbtnFastFood = new JRadioButton("Fast Food");
		rdbtnFastFood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rdbtnDrink.setSelected(false);
				rdbtnFastFood.setSelected(true);
				rdbtnPizza.setSelected(false);
				updateText();
			}
		});
		rdbtnFastFood.setBounds(51, 0, 73, 23);
		getContentPane().add(rdbtnFastFood);

		JLabel lblName = new JLabel("Navn");
		lblName.setBounds(10, 30, 46, 14);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setBounds(10, 50, 208, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblNumber = new JLabel("Nummer");
		lblNumber.setBounds(10, 81, 46, 14);
		getContentPane().add(lblNumber);

		JLabel lblPrice = new JLabel("Pris");
		lblPrice.setBounds(10, 132, 46, 14);
		getContentPane().add(lblPrice);

		JLabel lblDescription = new JLabel("Beskrivelse");
		lblDescription.setBounds(10, 183, 84, 14);
		getContentPane().add(lblDescription);

		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(10, 203, 208, 20);
		getContentPane().add(txtDesc);

		JLabel lblSize = new JLabel("St\u00F8relse");
		lblSize.setBounds(10, 234, 46, 14);
		getContentPane().add(lblSize);

		txtSize = new JTextField();
		txtSize.setColumns(10);
		txtSize.setBounds(10, 254, 208, 20);
		getContentPane().add(txtSize);

		lblLunchOffer = new JLabel("Frokost tilbud");
		lblLunchOffer.setBounds(10, 285, 84, 14);
		getContentPane().add(lblLunchOffer);

		JFormattedTextField txtNumber = new JFormattedTextField(formatter);
		txtNumber.setBounds(10, 101, 208, 20);
		getContentPane().add(txtNumber);

		JFormattedTextField txtPrice = new JFormattedTextField(formatter);
		txtPrice.setBounds(10, 152, 208, 20);
		getContentPane().add(txtPrice);

		JButton btnAdd = new JButton("Tilf\u00F8j");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().equalsIgnoreCase("")) {
					txtName.setText("Udfyld venligst!");
				} else if(txtNumber.getText().equalsIgnoreCase("")) {
					txtNumber.setText("Udfyld venligst!");
				} else if(txtPrice.getText().equalsIgnoreCase("")) {
					txtPrice.setText("Udfyld venligst!");
				} else if(txtDesc.getText().equalsIgnoreCase("")) {
					txtDesc.setText("Udfyld venligst!");
				} else if(txtSize.getText().equalsIgnoreCase("")) {
					txtSize.setText("Udfyld venligst!");
				} 
				if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && rdbtnPizza.isSelected() ) {

					try {
						foodCtr.createPizza(txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText())/100,"pizza", txtDesc.getText(), txtSize.getText(), chkBoxOffer.isSelected());
					} catch (SQLException e1) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
					FoodGUI.update();
					dispose();
				}
				else if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && rdbtnFastFood.isSelected() ) {
					try {
						foodCtr.createFastFood(txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText())/100,"fast food", txtDesc.getText(), txtSize.getText());
					} catch (SQLException e1) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
					FoodGUI.update();
					dispose();
				}
				else if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && rdbtnDrink.isSelected() ) {
					try {
						foodCtr.createDrink(txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText())/100, "drink",  txtDesc.getText(),  txtSize.getText(),  chkBoxOffer.isSelected() , comboBox.getSelectedItem().toString());
					} catch (Exception e2) {
						ConfirmationDialog.showMessageDialog(txtNumber, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
					FoodGUI.update();
					dispose();
				}
			}
		});
		btnAdd.setBounds(5, 392, 89, 23);
		getContentPane().add(btnAdd);

		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(123, 392, 95, 23);
		getContentPane().add(btnCancel);

		chkBoxOffer = new JCheckBox("");
		chkBoxOffer.setBounds(6, 306, 97, 23);
		getContentPane().add(chkBoxOffer);

		rdbtnDrink = new JRadioButton("Drink");
		rdbtnDrink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnDrink.setSelected(true);
				rdbtnFastFood.setSelected(false);
				rdbtnPizza.setSelected(false);
				updateText();
			}
		});

		rdbtnDrink.setBounds(126, 0, 73, 23);
		getContentPane().add(rdbtnDrink);

		comboBox = new JComboBox<Object>(brands);
		comboBox.setBounds(10, 361, 208, 20);
		getContentPane().add(comboBox);

		lblBrand = new JLabel("M\u00E6rke");
		lblBrand.setBounds(10, 336, 189, 14);
		getContentPane().add(lblBrand);
		
		lblDiet = new JLabel("Light");
		lblDiet.setBounds(10, 285, 46, 14);
		getContentPane().add(lblDiet);
		initialize();
		updateText();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void updateText() {
		chkBoxOffer.setVisible(rdbtnPizza.isSelected() || rdbtnDrink.isSelected());
		lblLunchOffer.setVisible(rdbtnPizza.isSelected());
		comboBox.setVisible(rdbtnDrink.isSelected());
		lblBrand.setVisible(rdbtnDrink.isSelected());
		lblDiet.setVisible(rdbtnDrink.isSelected());

	}
}

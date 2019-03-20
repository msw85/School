package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import controller.FoodController;
import model.Drink;
import model.Food;
import model.Pizza;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class EditFood extends JDialog {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtSize;
	private JLabel lblLunchOffer;
	private JLabel lblDiet;
	private FoodController foodCtr;
	private JCheckBox chkBoxOffer;
	private String[] brands;
	private JComboBox<Object> comboBox;


	private NumberFormatter formatter;


	public EditFood(Food f) {

		getContentPane().setLayout(null);
		formatter = UiUtil.initFormatter();
		try {
			foodCtr = new FoodController();
			brands = foodCtr.findBrandsString();
		} catch (SQLException e1) {
			ConfirmationDialog.showMessageDialog(this, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}


		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setBounds(5, 11, 46, 14);
		getContentPane().add(lblNavn);

		txtName = new JTextField(f.getName());

		txtName.setBounds(5, 31, 208, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblNummer = new JLabel("Nummer");
		lblNummer.setBounds(5, 62, 46, 14);
		getContentPane().add(lblNummer);

		JLabel lblPris = new JLabel("Pris");
		lblPris.setBounds(5, 113, 46, 14);
		getContentPane().add(lblPris);

		JLabel lblBeskrivelse = new JLabel("Beskrivelse");
		lblBeskrivelse.setBounds(5, 164, 84, 14);
		getContentPane().add(lblBeskrivelse);

		txtDesc = new JTextField(f.getDesc());
		txtDesc.setColumns(10);
		txtDesc.setBounds(5, 184, 208, 20);
		getContentPane().add(txtDesc);

		JLabel lblStrelse = new JLabel("St\u00F8relse");
		lblStrelse.setBounds(5, 215, 46, 14);
		getContentPane().add(lblStrelse);

		txtSize = new JTextField(f.getSize());
		txtSize.setColumns(10);
		txtSize.setBounds(5, 235, 208, 20);
		getContentPane().add(txtSize);



		JFormattedTextField txtNumber = new JFormattedTextField(formatter);
		txtNumber.setBounds(5, 82, 208, 20);
		txtNumber.setText(Integer.toString(f.getMenuNo()));
		getContentPane().add(txtNumber);

		JFormattedTextField txtPrice = new JFormattedTextField(formatter);
		txtPrice.setText(Double.toString(f.getPrice() * 100));
		txtPrice.setBounds(5, 133, 208, 20);
		getContentPane().add(txtPrice);
		if (f.getType().equals("pizza")) {
			Pizza ff = (Pizza) f;
			lblLunchOffer = new JLabel("Frokost tilbud");
			lblLunchOffer.setBounds(10, 285, 84, 14);
			getContentPane().add(lblLunchOffer);

			chkBoxOffer = new JCheckBox("");
			chkBoxOffer.setBounds(6, 306, 97, 23);
			getContentPane().add(chkBoxOffer);
		}
		if(f.getType().equals("drink")) {
			Drink d = (Drink) f;
			lblLunchOffer = new JLabel("Light");
			lblLunchOffer.setBounds(10, 285, 84, 14);
			getContentPane().add(lblLunchOffer);

			chkBoxOffer = new JCheckBox("");
			chkBoxOffer.setBounds(6, 306, 97, 23);
			getContentPane().add(chkBoxOffer);
			lblDiet = new JLabel("Light");
			lblDiet.setBounds(10, 285, 46, 14);
			getContentPane().add(lblDiet);

			comboBox = new JComboBox<Object>(brands);
			comboBox.setBounds(10, 340, 208, 20);
			getContentPane().add(comboBox);

		}

		JButton btnAdd = new JButton("Opdater");
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

				if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && f.getType().equals("pizza") ) {

					try {
						foodCtr.updatePizza(txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText())/100,"pizza", txtDesc.getText(), txtSize.getText(),f.getId(), chkBoxOffer.isSelected());
					} catch (SQLException e1) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					FoodGUI.update();
					dispose();
				}
				else if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && f.getType().equals("fast food") ) {
					try {
						foodCtr.updateFastFood(txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText()),"fast food", txtDesc.getText(), txtSize.getText(), f.getId());
					} catch (SQLException e1) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					FoodGUI.update();
					dispose();
				}
				else if(!txtNumber.getText().equals("Udfyld venligst!") && !txtPrice.getText().equals("Udfyld venligst!") && !txtDesc.getText().equals("Udfyld venligst!") && !txtSize.getText().equals("Udfyld venligst!") && f.getType().equals("drink") ) {
					try {
						foodCtr.updateDrink(f.getId(), txtName.getText(), Integer.parseInt(txtNumber.getText()), Double.parseDouble(txtPrice.getText())/100, "drink", txtDesc.getText() , txtSize.getText(), chkBoxOffer.isSelected(), comboBox.getSelectedItem().toString());
					} catch (Exception e2) {
						ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
								JOptionPane.WARNING_MESSAGE);
						System.out.println(e2.getMessage());
					}
					FoodGUI.update();
					dispose();
				}
			}
		});
		btnAdd.setBounds(5, 369, 89, 23);
		getContentPane().add(btnAdd);

		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(118, 369, 95, 23);
		getContentPane().add(btnCancel);

		JButton btnSlet = new JButton("SLET");
		btnSlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (confirmations()) {
						if(foodCtr.inUse(f)){
							errorMessage();
						}
						else {
							foodCtr.deleteFood(f);
							FoodGUI.update();
							dispose();
						}
					}
				}
				catch (SQLException ee) {
					ConfirmationDialog.showMessageDialog(txtName, "Der skete en fejl under sletning.\nEr der en ordre der indeholder det valgte?", "Information",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnSlet.setBounds(5, 397, 208, 23);
		getContentPane().add(btnSlet);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public boolean confirmations() {
		int optionType = JOptionPane.YES_NO_OPTION;
		int result = ConfirmationDialog.showConfirmDialog(this, "Er du sikker på at du vil fjerne dette fra menu?", "Validering", optionType);
		if(result == JOptionPane.YES_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}

	public void errorMessage() {
		JOptionPane.showMessageDialog(this, "Den valgte menu er på en ordrelinje, ordrelinjen og ordre skal slettes først", "Information",
				JOptionPane.WARNING_MESSAGE);
	}
}

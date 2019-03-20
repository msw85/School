package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.FoodController;
import model.Drink;
import model.FastFood;
import model.Food;
import model.Pizza;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * @author Gruppe 6
 *
 */
public class FoodGUI extends JPanel {
	//Initiate local variables
	private static final long serialVersionUID = 1L;
	private static JTable tableFood;
	private static FoodController foodCtr;
	private static List<FastFood> listFastFood;
	private static List<Pizza> listPizza;
	private static List<Drink> listDrink;
	private static DefaultTableModel t;

	/**
	 * Instantiate controllers 
	 * and create GUI objects 
	 */
	public FoodGUI() {
		try {
			foodCtr = new FoodController();
			listFastFood =  foodCtr.findAllFastFood();
			listPizza = foodCtr.findAllPizza();
			listDrink = foodCtr.findAllDrinks();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(tableFood, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);		}

		String[] columnNames = {"Nummer",
				"Navn",
				"Pris",
				"Type",
				"Størrelse",
				"Frokost-tilbud",
				"Light",
		"Beskrivelse"};

		t = UiUtil.getTable();
		for (String string : columnNames) {
			t.addColumn(string);
		}

		setSize(1346, 718);
		setLayout(null);

		JLabel lblMenukort = new JLabel("Menukort");
		lblMenukort.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMenukort.setBounds(10, 11, 150, 25);
		add(lblMenukort);

		JButton btnTilfjMad = new JButton("Tilføj mad");
		btnTilfjMad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateFood a = new CreateFood();
				a.setSize(240, 470);
				a.setVisible(true);
			}
		});

		btnTilfjMad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTilfjMad.setBounds(1186, 672, 150, 35);
		add(btnTilfjMad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 1326, 614);
		add(scrollPane);

		tableFood = new JTable();
		scrollPane.setViewportView(tableFood);
		tableFood.setModel(t);		

		tableFood.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2 && tableFood.getSelectedRow() != -1) {
					int value = Integer.parseInt(tableFood.getModel().getValueAt(tableFood.getSelectedRow(), 0).toString());
					Food f;
					try {
						f = foodCtr.findOnMenuNo(value);
						if (f.getType().equals("fast food")) {
							FastFood ff = foodCtr.findFastFoodByID(f.getId());
							EditFood a = new EditFood(ff);
							a.setSize(240, 470);
							a.setVisible(true);
						}
						else if(f.getType().equals("pizza")){
							Pizza ff = foodCtr.findPizzaByID(f.getId());
							EditFood a = new EditFood(ff);
							a.setSize(240, 470);
							a.setVisible(true);
						}
						else if(f.getType().equals("drink")) {
							Drink d = foodCtr.findDrinkByID(f.getId());
							EditFood a = new EditFood(d);
							a.setSize(240, 470);
							a.setVisible(true);
						}
					} catch (SQLException ee) {
						ConfirmationDialog.showMessageDialog(tableFood, "Der skete en fejl.\nEr der forbindelse til databasen?", "Information",
								JOptionPane.WARNING_MESSAGE);
					}
					tableFood.updateUI();
				}
			}
		});
		update();

	}
	/**
	 * This method updates the DefaultTableModel with new data
	 * @param t
	 */
	private static void updateTable(DefaultTableModel t) {
		t.setRowCount(0);
		try {
			listFastFood =  foodCtr.findAllFastFood();
			listPizza = foodCtr.findAllPizza();
			listDrink = foodCtr.findAllDrinks();
		} catch (SQLException e) {
			ConfirmationDialog.showMessageDialog(tableFood, "Der skete en fejl under opdatering.\nEr der forbindelse til databasen?", "Information",
					JOptionPane.WARNING_MESSAGE);
		}
		for (int i = 0; i < listPizza.size(); i++) {
			Pizza ff = listPizza.get(i);
			String[] s = {Integer.toString(ff.getMenuNo()), ff.getName(), Double.toString(ff.getPrice()) + " kr.", ff.getType() , ff.getSize(), (ff.isLunchOffer() ? "Ja": "Nej"), "", ff.getDesc()};
			t.addRow(s);
		}
		for (int i = 0; i < listFastFood.size(); i++) {
			FastFood ff = listFastFood.get(i);
			String[] s = {Integer.toString(ff.getMenuNo()), ff.getName(),  Double.toString(ff.getPrice()) + " kr.", ff.getType(), ff.getSize(), "Nej","", ff.getDesc()};
			t.addRow(s);
		}
		for (int i = 0; i < listDrink.size(); i++) {
			Drink d = listDrink.get(i);
			if(d.getBrand() != null) {
				String[] s = {Integer.toString(d.getMenuNo()), d.getBrand() + " " + d.getName(), Double.toString(d.getPrice()) + " kr.", d.getType() , d.getSize(), "Nej", (d.isDiet() ? "Ja" : "Nej"), d.getDesc()};
				t.addRow(s);
			}		
		}	
	}

	/**
	 * This method is a public interface to the updateTable(t) method
	 */
	public static void update() {
		updateTable(t);
	}
}

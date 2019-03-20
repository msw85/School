package gui;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import database.dbUtil;
import model.DbObservable;

import javax.swing.JPanel;
import java.awt.Color;

public class MainGUI {

	private static JFrame frame;
	private JTabbedPane tabbedPane;
	private MobileGUI mobile;
	private SaleGUI sale;
	private OrderGUI order;
	private PersonGUI person;
	private EmployeeGUI employee;
	private FoodGUI food;
	private AdminGUI admin;
	public static DbObservable observable;


	public MainGUI() {

		try {
			dbUtil.commitTransaction(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		observable = new DbObservable();

		frame = new JFrame();
		tabbedPane = new JTabbedPane();
		sale = new SaleGUI();
		order = new OrderGUI();
		person = new PersonGUI();
		employee = new EmployeeGUI();
		food = new FoodGUI();
		admin = new AdminGUI();


		tabbedPane.add("Salg", sale.getPanel());
		tabbedPane.add("Ordre", order);
		tabbedPane.add("Kunder", person);
		tabbedPane.add("Medarbejder", employee);
		tabbedPane.add("Menu", food);
		tabbedPane.add("Administrator", admin);
		frame.getContentPane().add(tabbedPane);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1366, 768);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		mobile = new MobileGUI();
		mobile.setSize(402, 575);
		mobile.setVisible(true);
	}

	public static void main(String[] args) {
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)
		{
			ConfirmationDialog.showMessageDialog(frame, "Der skete en fejl.\nEr der forbindelse til databasen!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGUI();
			}
		});
	}
}

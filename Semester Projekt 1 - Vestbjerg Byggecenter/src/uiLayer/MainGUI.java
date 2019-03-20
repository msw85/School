package uiLayer;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainGUI{

	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();
	ObjectGenerator obj = new ObjectGenerator();
	SaleGUI sale = new SaleGUI();
	OrderGUI order = new OrderGUI();
	CustomerGUI cust = new CustomerGUI();
	AccountGUI acc = new AccountGUI();
	EmployeeGUI emp = new EmployeeGUI();
	RentGUI ren = new RentGUI();
	ProduktGUI prod = new ProduktGUI();
	SupplierGUI sup = new SupplierGUI();
	public MainGUI() {
		tabbedPane.add("Salg", sale);
		tabbedPane.add("Udlejning", ren);
		tabbedPane.add("Ordrer", order);
		tabbedPane.add("Kunder", cust);
		tabbedPane.add("Konti", acc);
		tabbedPane.add("Medarbejdere", emp);
		tabbedPane.add("Produkter", prod);
		tabbedPane.add("Leverandører", sup);
		frame.getContentPane().add(tabbedPane);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(650, 480);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)
		{
			System.out.println("Køre du noget unix-like, hva? Din satan!");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGUI();
			}
		});
	}
}

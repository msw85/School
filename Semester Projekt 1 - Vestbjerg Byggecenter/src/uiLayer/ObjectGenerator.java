package uiLayer;
import controllerLayer.*;
import modelLayer.OrderLine;
public class ObjectGenerator {
	
	CustomerController cusCon = new CustomerController();
	AccountController accCon = new AccountController();
	EmployeeController empCon = new EmployeeController();
	OrderController odrCon = new OrderController();
	ProductController proCon = new ProductController();
	RentController  renCon = new RentController();
	SupplierController supCon = new SupplierController();
	OrderLine odrLin = new OrderLine();
	
	public ObjectGenerator() {
		
		// Create test customers
		cusCon.addCustomer("Toms Tømrerfirma A/S", "Lægtegade 6", "9000", "Aalborg", "23345366");
		cusCon.addCustomer("John Hansen", "Blomstervænget 18", "9000", "Aalborg", "23785473");
		cusCon.addCustomer("Inge Larsen", "Mågevej 1", "6000", "Kolding", "74839238");
		
		// Create test suppliers
		supCon.createSupplier("Vild Med Træ A/S", "Alt i træ\nHar også garn.", "Reg: 3728 Konto: 75839835");
		supCon.createSupplier("Jernlast A/S", "Det bedste i søm og skruer.\n Generelt ting i jern og stål.", "Reg: 3847 Konto: 928374294");
		supCon.createSupplier("Mega Tools ApS", "Alt i værktøj og tilbehør.", "Reg: 4562 Konto: 435763576");

		// Create test products
		proCon.addProduct(100, 1000, "235246246", "Spånplade", "Træplade 2m x 4m", "God til lejehus", "Træfarvet", 0.0, 245.0, 1.0, 115.0, 0.0, supCon.getSupplier(0));
		proCon.addProduct(100, 10000, "1232345", "Dykkere", "Søm", "Gode til træ", "Krom", 0.0, 55.0, 100.0, 0.2, 0.0, supCon.getSupplier(1));
		proCon.addProduct(20, 50, "1414123", "Slagboremaskine", "Værktøj", "18v batteri boremaskine med slag i.", "Sort", 0.0, 999.0, 1.0, 350.0, 0.0, supCon.getSupplier(2));
		
		accCon.addAccount("Sidste bankdag i måneden", 5, 2, 10, 10000);
		accCon.addAccount("Hver 14. dag", 10, 5, 7, 10000);
		
		accCon.addCustomer(0, 0);
		accCon.addCustomer(1, 2);
		
		empCon.addEmployee("Anders Olesen", "Uglevej 6", "9380", "Vestbjerg", "84938473", "Reg: 1337 Konto: 298472134", 250, true);
		empCon.addEmployee("Gudrun Jensen", "Vildtgade 13", "9380", "Vestbjerg", "84930293", "Reg: 8933 Konto: 394820134", 130, false);
		empCon.addEmployee("Odin Larsen", "Tingstrædet 8", "9380", "Vestbjerg", "43225367", "Reg: 5464 Konto: 265462462", 140, false);
	}
	
	
}

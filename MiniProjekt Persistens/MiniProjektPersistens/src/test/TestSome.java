package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Country;
import model.Customer;
import model.OrderLine;
import model.Product;
import model.SaleOrder;
import model.Supplier;
import controller.CountryController;
import controller.CustomerController;
import controller.ProductController;
import controller.SaleOrderController;
import controller.SupplierController;

class TestSome {

	SupplierController sCtr;
	SaleOrderController soCtr;
	ProductController pCtr;
	CustomerController cCtr;

	@Before
	public void setUp() {
		setUpSupplierAndCountry();
		//setUpSale();
	}

	@After
	public void tearDown() {
		tearDownSupplier();
	}

	@Test
	void findAllSuppliers() {
		List<Supplier> allsup = null;
		try {
			sCtr = new SupplierController();
			allsup = sCtr.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(allsup.isEmpty());
	}
	
	@Test
	void makeSale() {
		Product p;
		Customer c;
		SaleOrder so = null;
		try {
			soCtr = new SaleOrderController();
			pCtr = new ProductController();
			cCtr = new CustomerController();
			c = cCtr.findById(0);
			soCtr.startOrder(c.getId());
			p = pCtr.findById(0);
			soCtr.addOrderLine(10, p);
			so = soCtr.finishOrder();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertNotNull(so);
	}
	
	private void setUpSupplierAndCountry() {
		CountryController cCtr;
		Country c;
		Supplier s;
		try {
			cCtr = new CountryController();
			c = cCtr.findById(2);
			s = new Supplier("Bent", "Bentsvej 21", c, "29847922", "bentErVild@svendbent.dk");
			sCtr.createSupplier(s);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setUpSale() {
		Product p;
		Customer c;
		try {
			soCtr = new SaleOrderController();
			pCtr = new ProductController();
			cCtr = new CustomerController();
			c = cCtr.findById(0);
			soCtr.startOrder(c.getId());
			p = pCtr.findById(0);
			soCtr.addOrderLine(10, p);
			soCtr.finishOrder();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	private void tearDownSupplier() {
		try {
			List<Supplier> sAll = sCtr.findAll();
			for (Supplier supplier : sAll) {
				if (supplier.getEmail().equals("bentErVild@svendbent.dk")) {
					sCtr.deleteSupplier(supplier);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import database.SaleOrderDB;
import database.SaleOrderDBIF;
import model.Customer;
import model.OrderLine;
import model.Product;
import model.SaleOrder;

public class SaleOrderController {

	private SaleOrderDBIF sodb;
	private CustomerController cCtr;
	private SaleOrder so;
	
	public SaleOrderController() throws SQLException {
		sodb = new SaleOrderDB();
		cCtr = new CustomerController();
	}
	
	public List<SaleOrder> findAll() throws SQLException {		
		return sodb.findAll();		
	}
	
	public SaleOrder findById(int id) throws SQLException {
		return sodb.findById(id);
	}
	
	public void createSaleOrder(SaleOrder s) throws SQLException {
		sodb.createOrder(s);
	}
	
	public void deleteSaleOrder(SaleOrder s) throws SQLException {
		sodb.deleteOrder(s);
	}
	
	public void updateSaleOrder(SaleOrder s) throws SQLException {
		sodb.updateOrder(s);
	}
	public void startOrder(int cid) throws SQLException {
		Date date = new Date();
		Customer c = cCtr.findById(cid);
		so = new SaleOrder(date, false, c);
		
	}
	public void addOrderLine(int quantity, Product p) {
		OrderLine ol = new OrderLine(quantity, p);
		so.addOrderline(ol);
	}
	public SaleOrder finishOrder() throws SQLException {
		Date uDate = new Date();
		java.sql.Date delDate = new java.sql.Date(uDate.getTime());
		so.setDeliveryDate(delDate);
		so.setPrice(calculateTotalPrice());
		createSaleOrder(so);
		return so;
	}

	private Double calculateTotalPrice() {
		Double total = 0d;
		List<OrderLine> allOls = so.getOrderlines();
		for (OrderLine orderLine : allOls) {
			total = total + orderLine.getAccPrice();
		}
		return total;
	}
	
}

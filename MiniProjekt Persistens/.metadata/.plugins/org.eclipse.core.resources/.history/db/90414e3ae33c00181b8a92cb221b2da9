package controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerDB;
import database.CustomerDBIF;
import model.Customer;

public class CustomerController {

	private CustomerDBIF cdb;
	
	public CustomerController() throws SQLException {
		cdb = new CustomerDB();
	}
	
	public List<Customer> findAll() throws SQLException{
		return cdb.findAll();
	}
	
	public Customer findById(int id) throws SQLException {
		return cdb.findById(id);
	}
	
	public void createCustomer(Customer c) throws SQLException {
		cdb.createCustomer(c);
	}
	
	public void deleteCustomer(Customer c) throws SQLException {
		cdb.deleteCustomer(c);
	}
	
	public void updateCustomer(Customer c) throws SQLException {
		cdb.updateCustomer(c);
	}
}

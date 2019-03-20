package database;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface CustomerDBIF {
	List<Customer> findAll() throws SQLException;
	Customer findById(int id) throws SQLException;
	void createCustomer(Customer c) throws SQLException;
	void deleteCustomer(Customer c) throws SQLException;
	void updateCustomer(Customer c) throws SQLException;

}

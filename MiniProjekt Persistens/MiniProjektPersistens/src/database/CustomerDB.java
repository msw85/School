package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private static final String FIND_ALLQ = "select id, name, address, zipcode, city, phone_no, email from customers";
	private static final String FIND_CUST_BY_IDQ = "select id, name, address, zipcode, city, phone_no, email from customers where id=?";
	private static final String CREATE_CUSTQ = "insert info customers(name, address, zipcode, city, phone_no, email) values( ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_CUSTQ = "update customers set name = ?, address = ?, zipcode = ?, city = ?, phoneno = ?, email = ? from customers where id = ?";
	private static final String DELETE_CUSTQ = "delete from customers where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findCustById;
	private PreparedStatement createCust;
	private PreparedStatement updateCust;
	private PreparedStatement deleteCust;
	
	public CustomerDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALLQ);
		findCustById = DBConnection.getInstance().getConnection().prepareStatement(FIND_CUST_BY_IDQ);
		createCust = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTQ);
		updateCust = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTQ);
		deleteCust = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CUSTQ);
	}
	
	@Override
	public List<Customer> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Customer> allCust = buildObjects(rs);
		return allCust;
	}

	@Override
	public Customer findById(int id) throws SQLException {
		Customer cust = null;
		ResultSet rs;
		findCustById.setInt(1, id);
		rs = findCustById.executeQuery();
		rs.next();
		cust = buildObject(rs);
		return cust;
	}

	@Override
	public void createCustomer(Customer c) throws SQLException {
		createCust.setString(1, c.getName());
		createCust.setString(2, c.getAddress());
		createCust.setString(3, c.getZipcode());
		createCust.setString(4, c.getCity());
		createCust.setString(5, c.getPhoneno());
		createCust.setString(6, c.getEmail());	
		createCust.execute();
	}

	@Override
	public void deleteCustomer(Customer c) throws SQLException {
		deleteCust.setInt(1, c.getId());
		deleteCust.executeQuery();	
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		updateCust.setString(1, c.getName());
		updateCust.setString(2, c.getAddress());
		updateCust.setString(3, c.getZipcode());
		updateCust.setString(4, c.getCity());
		updateCust.setString(5, c.getPhoneno());
		updateCust.setString(6, c.getEmail());
		updateCust.setInt(7, c.getId());
		updateCust.executeQuery();
		
	}
	
	private Customer buildObject(ResultSet rs) throws SQLException {
		// Make Customer from RS using constructor!
		Customer cust = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("zipcode"), rs.getString("city"), rs.getString("phone_no"), rs.getString("email"));
		return cust;
	}
	
	private List<Customer> buildObjects(ResultSet rs) throws SQLException {
		List<Customer> result = new ArrayList<>();
		while(rs.next()) {
			result.add(buildObject(rs));
		}
		return result;
	}

}

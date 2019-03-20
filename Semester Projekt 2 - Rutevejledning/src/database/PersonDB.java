package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Person;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class PersonDB implements PersonDBIF {

	/**
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for Customer 
	 * 
	 */
	private static final String FIND_ALL_CUST_Q = "SELECT p.id, p.name, p.address, z.zip, z.city, p.phoneNo, p.eMail, p.type, c.isValuedCust FROM persons p, zipAndCities z, customers c WHERE z.id = p.zacId AND p.id =  c.pId;";
	private static final String FIND_CUST_ON_ID_Q = "SELECT c.pId as id, p.name, p.address, z.zip, z.city, p.phoneNo, p.eMail, p.type, c.isValuedCust FROM persons p, zipAndCities z, customers c WHERE z.id = p.zacId AND c.pId = p.id AND p.id=?";
	private static final String CREATE_CUST_Q = "INSERT INTO customers(pId, isValuedCust) VALUES(?,?)";
	private static final String UPDATE_CUST_Q = "UPDATE customers SET isValuedCust = ? WHERE pId = ?";

	/**
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for Employee 
	 * 
	 */
	private static final String FIND_ALL_EMPL_Q = " SELECT e.pId as id, p.name, p.address, z.zip, z.city, p.phoneNo, p.eMail, p.type, e.salary, e.accountInfo, e.title FROM persons p, zipAndCities z, employees e WHERE z.id = p.zacId AND e.pId = p.id";
	private static final String FIND_EMPL_ON_ID_Q = "SELECT e.pId as id, p.name, p.address, z.zip, z.city, p.phoneNo, p.eMail, p.type, e.salary, e.accountInfo, e.title FROM persons p, zipAndCities z, employees e WHERE z.id = p.zacId AND e.pId = p.id AND p.id = ?";
	private static final String CREATE_EMPL_Q = "INSERT INTO employees(pId, salary, accountInfo, title) VALUES(?,?,?,?)";
	private static final String UPDATE_EMPL_Q = "UPDATE employees SET salary = ?, accountInfo = ?, title = ? WHERE pId = ?";

	/**
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for Person 
	 * 
	 */
	private static final String CREATE_PERS_Q = "INSERT INTO persons(name, address, zacId, phoneNo, eMail, type) VALUES(?,?, (SELECT id FROM zipAndCities WHERE zip = ? AND city = ?),?,?,?)";
	private static final String UPDATE_PERS_Q = "UPDATE persons SET name = ?, address = ?, zacId = (SELECT id FROM zipAndCities WHERE zip = ? AND city = ?), phoneNo = ?, email = ?, type = ? WHERE id = ?";
	private static final String DELETE_PERS_ON_ID_Q = "DELETE FROM persons WHERE id = ?";
	
	/**
	 * PreparedStatements for Customer
	 */
	private PreparedStatement findAllCustomers;
	private PreparedStatement findCustById;
	private PreparedStatement createCustomer;
	private PreparedStatement updateCustomer;
	
	/**
	 * PreparedStatements for Employee
	 */
	private PreparedStatement findAllEmployees;
	private PreparedStatement findEmplById;
	private PreparedStatement createEmployee;
	private PreparedStatement updateEmployee;
	
	/**
	 * PreparedStatements for Person
	 */
	private PreparedStatement createPers;
	private PreparedStatement updatePers;
	private PreparedStatement deletePers;
	


	/**
	 * @throws SQLException
	 */
	public PersonDB() throws SQLException {
		// Instantiate prepared statements
		findAllCustomers = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_CUST_Q);
		findCustById = DBConnection.getInstance().getConnection().prepareStatement(FIND_CUST_ON_ID_Q);
		createCustomer = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUST_Q);
		updateCustomer = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUST_Q, Statement.RETURN_GENERATED_KEYS);	
		
		findAllEmployees = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_EMPL_Q);
		findEmplById = DBConnection.getInstance().getConnection().prepareStatement(FIND_EMPL_ON_ID_Q);
		createEmployee = DBConnection.getInstance().getConnection().prepareStatement(CREATE_EMPL_Q);
		updateEmployee = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_EMPL_Q,	Statement.RETURN_GENERATED_KEYS);
			
		createPers = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PERS_Q,	Statement.RETURN_GENERATED_KEYS);
		updatePers = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PERS_Q, Statement.RETURN_GENERATED_KEYS);
		deletePers = DBConnection.getInstance().getConnection().prepareStatement(DELETE_PERS_ON_ID_Q);
	}

	/*
	 * This method finds all persisted employees
	 * returns a list of employees
	 */
	@Override
	public List<Person> findAllEmployees() throws SQLException {
		// Create an empty result-set
		ResultSet rs;
		// Execute query and get result set
		rs = findAllEmployees.executeQuery();
		// Build all objects from query
		List<Person> allPers = buildObjects(rs);
		// Return list of all objects
		return allPers;
	}
	
	/*
	 * This method finds a persisted employee on id
	 * returns a spcific Person object 
	 */
	@Override
	public Person findEmployeeById(int id) throws SQLException {
		// Create an empty person object
		Person per;
		// Create an empty result set
		ResultSet rs;
		// Set variables in SQL Script
		findEmplById.setInt(1, id);
		// Execute SQL script and get result-set
		rs = findEmplById.executeQuery();
		if (rs.next()) {
			// Build object from result-set
			per = buildObject(rs);
			// Return object
			return per;
		} else {
			return null;
		}
	}
	
	/*
	 * This method takes the supplied Employee object and persists the employee
	 * 
	 */
	@Override
	public void createEmployee(Employee per) throws SQLException {
		dbUtil.prepareTransaction();
		// Set variables in SQL Script
		createPers.setString(1, per.getName());
		createPers.setString(2, per.getAddress());
		createPers.setString(3, per.getZip());
		createPers.setString(4, per.getCity());
		createPers.setString(5, per.getPhoneNo());
		createPers.setString(6, per.getEmail());
		createPers.setString(7, per.getType());
		// Execute SQL script and get affected rows
		int affectedRows = createPers.executeUpdate();
		dbUtil.commitTransaction(false);
		// Check if affectedRows == 0

		if (affectedRows == 0) {
			// An error occurred and no rows were affected
			throw new SQLException("Creating user failed, no rows affected.");
		}
		// Get result-set from generated keys
		try (ResultSet generatedKeys = createPers.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				// Set ID = the generated key
				per.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
		// Set variables in SQL Script
		createEmployee.setInt(1, per.getId());
		createEmployee.setDouble(2, (int) (per.getSalary() * 100));
		createEmployee.setString(3, per.getAccountInfo());
		createEmployee.setString(4, per.getTitle());
		// Execute SQL script and get affected rows
		createEmployee.execute();
		dbUtil.commitTransaction(true);
	}
	
	/* 
	 * This method takes the supplied Employee object and updates the persisted Employee
	 */
	@Override
	public void updateEmployee(Employee per) throws SQLException {
		dbUtil.prepareTransaction();
		// Set variables in SQL Script
		updatePers.setString(1, per.getName());
		updatePers.setString(2, per.getAddress());
		updatePers.setString(3, per.getZip());
		updatePers.setString(4, per.getCity());
		updatePers.setString(5, per.getPhoneNo());
		updatePers.setString(6, per.getEmail());
		updatePers.setString(7, per.getType());
		updatePers.setInt(8, per.getId());
		updatePers.executeUpdate();
		updateEmployee.setDouble(1, (int) (per.getSalary() * 100));
		updateEmployee.setString(2, per.getAccountInfo());
		updateEmployee.setString(3, per.getTitle());
		updateEmployee.setInt(4, per.getId());
		// Execute SQL script
		updateEmployee.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/*
	 * This method finds all persisted customers
	 * returns a list of customers
	 */
	@Override
	public List<Person> findAllCustomers() throws SQLException {
		// Create an empty result-set
		ResultSet rs;
		// Execute query and get result set
		rs = findAllCustomers.executeQuery();
		// Build all objects from query
		List<Person> allPers = buildObjects(rs);
		// Return list of all objects
		return allPers;
	}

	/*
	 * This method finds a persisted customer on id
	 * returns a spcific Person object
	 */
	@Override
	public Person findCustomerById(int pId) throws SQLException {
		// Create an empty person object
		Person per;
		// Create an empty result set
		ResultSet rs;
		// Set variables in SQL Script
		findCustById.setInt(1, pId);
		// Execute SQL script and get result-set
		rs = findCustById.executeQuery();
		if (rs.next()) {
			// Build object from result-set
			per = buildObject(rs);
			// Return object
			return per;
		} else {
			return null;
		}
	}

	/*
	 * This method takes the supplied Customer object and persists the customer
	 * 
	 */
	@Override
	public void createCustomer(Customer per) throws SQLException {
		dbUtil.prepareTransaction();
		// Set variables in SQL Script
		createPers.setString(1, per.getName());
		createPers.setString(2, per.getAddress());
		createPers.setString(3, per.getZip());
		createPers.setString(4, per.getCity());
		createPers.setString(5, per.getPhoneNo());
		createPers.setString(6, per.getEmail());
		createPers.setString(7, per.getType());
		// Execute SQL script and get affected rows
		int affectedRows = createPers.executeUpdate();
		dbUtil.commitTransaction(false);
		// Check if affectedRows == 0
		if (affectedRows == 0) {
			// An error occurred and no rows were affected
			throw new SQLException("Creating user failed, no rows affected.");
		}
		// Get result-set from generated keys
		try (ResultSet generatedKeys = createPers.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				// Set ID = the generated key
				per.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
		// Set variables in SQL Script
		createCustomer.setInt(1, per.getId());
		createCustomer.setBoolean(2, per.getIsValuedCust());
		// Execute SQL script and get affected rows
		createCustomer.execute();
		dbUtil.commitTransaction(true);
	}
	
	/* 
	 * This method takes the supplied Customer object and updates the persisted Customer
	 */
	@Override
	public void updateCustomer(Customer per) throws SQLException {
		dbUtil.prepareTransaction();
		// Set variables in SQL Script
		updatePers.setString(1, per.getName());
		updatePers.setString(2, per.getAddress());
		updatePers.setString(3, per.getZip());
		updatePers.setString(4, per.getCity());
		updatePers.setString(5, per.getPhoneNo());
		updatePers.setString(6, per.getEmail());
		updatePers.setString(7, per.getType());
		updatePers.setInt(8, per.getId());
		updatePers.executeUpdate();
		updateCustomer.setBoolean(1, per.getIsValuedCust());
		updateCustomer.setInt(2, per.getId());
		// Execute SQL script
		updateCustomer.executeUpdate();
		dbUtil.commitTransaction(true);
	}
	
	/*
	 * This method takes the supplied Person object and deletes the persisted Person
	 * 
	 */
	@Override
	public void deletePerson(Person per) throws SQLException {
		dbUtil.prepareTransaction();
		// Set variables in SQL Script
		deletePers.setInt(1, per.getId());
		// Execute SQL script
		deletePers.execute();
		dbUtil.commitTransaction(true);
	}
	
	/**
	 * This method takes the persisted data and creates a person object.
	 * 
	 * @param rs the persisted data
	 * @return person object
	 * @throws SQLException
	 */
	private Person buildObject(ResultSet rs) throws SQLException {
		Person per;
		// Check type of person and build the right type of object
		if (rs.getString("type").equals("Employee")) {
			per = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("zip"),
					rs.getString("city"), rs.getString("phoneNo"), rs.getString("eMail"), rs.getString("type"),
					((Double) (rs.getDouble("salary") / 100)), rs.getString("accountInfo"), rs.getString("title"));
		} else {
			per = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("zip"),
					rs.getString("city"), rs.getString("phoneNo"), rs.getString("eMail"), rs.getString("type"),
					rs.getBoolean("isValuedCust"));
		}
		// Return the object
		return per;
	}

	/**
	 * This method takes the persisted data and runs buildObject method, then adds the object to a list
	 * 
	 * @param rs the persisted data
	 * @return List of person objects
	 * @throws SQLException
	 */
	private List<Person> buildObjects(ResultSet rs) throws SQLException {
		// Create a new empty list
		List<Person> persons = new LinkedList<>();
		while (rs.next()) {
			// Use buildObject to build objects and add them to the list
			persons.add((buildObject(rs)));
		}
		// Return the list
		return persons;
	}
}

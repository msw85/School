package controller;

import java.sql.SQLException;
import java.util.List;

import database.PersonDB;
import database.PersonDBIF;
import model.Customer;
import model.Employee;
import model.Order;
import model.Person;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class PersonController {
	// Initiate Person database interface
	private PersonDBIF perDB;
	private OrderController orderCtr;
	/**
	 * @throws SQLException
	 */
	public PersonController() throws SQLException {
		// Instantiate database
		perDB = new PersonDB();

	}

	/**
	 * This method returns a list with all employees.
	 * 
	 * @return a List with all employees
	 * @throws SQLException
	 */
	public List<Person> findAllEmployees() throws SQLException {
		// Execute method in person database to find all employee objects from database
		return perDB.findAllEmployees();
	}
	
	/**
	 * This method returns and person object by id.
	 * 
	 * @param id
	 *            - The if of the wanted customer
	 * @return a customer object with the matched id
	 * @throws SQLException
	 */
	public Person findEmployeeById(int id) throws SQLException {
		// Execute method in person database to find a employee object from database
		return perDB.findEmployeeById(id);
	}

	/**
	 * This method creates an employee in the database with and employee object.
	 * 
	 * @param empl
	 *            the employee object
	 * @throws SQLException
	 */
	public void createEmployee(Employee empl) throws SQLException {
		// Execute method in person database to create an employee object in the
		// database
		perDB.createEmployee(empl);
	}
	
	/**
	 * This method creates an employee based on the parameters and sends it to the
	 * database.
	 * 
	 * @param name
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param email
	 * @param type
	 * @param salery
	 * @param accountInfo
	 * @param title
	 * @throws SQLException
	 */
	public void createEmployee(String name, String address, String zip, String city, String phoneNo, String email,
			String type, Double salary, String accountInfo, String title) throws SQLException {
		// TODO Auto-generated method stub
		Employee empl = new Employee(name, address, zip, city, phoneNo, email, type, salary, accountInfo, title);
		perDB.createEmployee(empl);
	}
	
	/**
	 * This method updates an employee based on the parameters and sends it to the
	 * database.
	 * 
	 * @param name
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param email
	 * @param type
	 * @param salery
	 * @param accountInfo
	 * @param title
	 * @throws SQLException
	 */
	public void updateEmployee(int id, String name, String address, String zip, String city, String phoneNo,
			String email, String type, double salary, String accountInfo, String title) throws SQLException {
		// TODO Auto-generated method stub
		Employee empl = new Employee(id, name, address, zip, city, phoneNo, email, type, salary, accountInfo, title);
		perDB.updateEmployee(empl);
	}

	
	/**
	 * This method updates a employee with a employee object.
	 * 
	 * @param per
	 *            the employee object
	 * @throws SQLException
	 */
	public void updateEmployee(Employee empl) throws SQLException {
		// Execute method in person database to update an employee object in the
		// database
		perDB.updateEmployee(empl);
	}

	/**
	 * This method return a list with all customers.
	 * 
	 * @return a List with all customers
	 * @throws SQLException
	 */
	public List<Person> findAllCustomers() throws SQLException {
		// Execute method in person database to find all customer objects from database
		return perDB.findAllCustomers();
	}

	/**
	 * This method returns an person object by id.
	 * 
	 * @param id
	 *            - The id of the wanted customer
	 * @return a customer object with the matched id
	 * @throws SQLException
	 */
	public Person findCustomerById(int id) throws SQLException {
		// Execute method in person database to find a customer object from database
		return perDB.findCustomerById(id);
	}
	
	/**
	 * This method creates an customer based on the parameters and sends it to the
	 * database.
	 * 
	 * @param name
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param email
	 * @param type
	 * @param isValuedCust
	 * @throws SQLException
	 */
	public void createCustomer(String name, String address, String zip, String city, String phoneNo, String email,
			String type, boolean isValuedCust) throws SQLException {
		// TODO Auto-generated method stub
		Customer cust = new Customer(name, address, zip, city, phoneNo, email, type, isValuedCust);
		perDB.createCustomer(cust);
	}
	
	/**
	 * This method creates an customer in the database with and customer object.
	 * 
	 * @param cust
	 *            the customer object
	 * @throws SQLException
	 */
	public void createCustomer(Customer cust) throws SQLException {
		// Execute method in person database to create a customer object in the database
		perDB.createCustomer(cust);
	}

	/**
	 * This method updates an customer based on the parameters and sends it to the
	 * database.
	 * 
	 * @param name
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param email
	 * @param type
	 * @param isValuedCust
	 * @throws SQLException
	 */
	public void updateCustomer(int id, String name, String address, String zip, String city, String phoneNo,
			String email, String type, boolean isValuedCust) throws SQLException {
		// TODO Auto-generated method stub
		Customer cust = new Customer(id, name, address, zip, city, phoneNo, email, type, isValuedCust);
		updateCustomer(cust);
	}

	/**
	 * This method updates a customer with a customer object.
	 * 
	 * @param per
	 *            the customer object
	 * @throws SQLException
	 */
	public void updateCustomer(Customer cust) throws SQLException {
		// Execute method in person database to update a customer object in the database
		perDB.updateCustomer(cust);
	}

	/**
	 * This method delete an person in the datasbase with an person object.
	 * 
	 * @param per
	 *            the person object
	 * @throws SQLException
	 */
	public void deletePerson(Person per) throws SQLException {
		// Execute method in person database to delete an object
		perDB.deletePerson(per);
	}
	
	/**
	 * This method checks if person is on an order
	 * returns true if above mentioned
	 * 
	 * @param food Person object
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean inUse(Person person) throws SQLException {
		orderCtr = new OrderController();
		List<Order> allOrders = orderCtr.findAll();
		for (int i = 0; i < allOrders.size(); i++) {
			if(allOrders.get(i).getPers().toString().equals(person.toString())) {
			return true;
			}
		}
		return false;	
	}	
}

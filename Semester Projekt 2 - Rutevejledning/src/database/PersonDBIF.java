package database;

import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Person;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public interface PersonDBIF {

	/**
	 * These methods handles customers.
	 */
	List<Person> findAllCustomers() throws SQLException;
	Person findCustomerById(int id) throws SQLException;
	void createCustomer(Customer cust) throws SQLException;
	void updateCustomer(Customer cust) throws SQLException;
	
	/**
	 * These methods handles Employees.
	 */
	List<Person> findAllEmployees() throws SQLException;
	Person findEmployeeById(int id) throws SQLException;
	void createEmployee(Employee empl) throws SQLException;
	void updateEmployee(Employee empl) throws SQLException;
	
	/**
	 * This method deletes Employee or Customer.
	 */
	void deletePerson(Person per) throws SQLException;
}

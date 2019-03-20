package test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PersonController;
import model.Customer;
import model.Employee;
import model.Person;

class TestPerson {
	
	PersonController personCtr;
	Customer hans;
	Employee per;
	
	@BeforeEach
	void setUp() throws Exception {
		personCtr = new PersonController();
		hans = new Customer("Hans", "Vejgade 9", "9000", "Aalborg", "9000", "hans@vejmail.dk", "Customer", true);
		per = new Employee("Per", "Pizzavej 9", "9000", "Aalborg", "88888853", "per@pizza.dk", "Employee", 1009d, "Bank.info.hat", "Bager");
		personCtr.createCustomer(hans);
		personCtr.createEmployee(per);		
	}

	@AfterEach
	void tearDown() throws Exception {
		personCtr.deletePerson(per);
		personCtr.deletePerson(hans);
		personCtr = null;
		per = null;
		hans = null;
	}
	@Test
	void testCreateCustomer() {
		Customer hans2 = new Customer("Hans", "Vejgade 9", "9000", "Aalborg", "9000", "hans@vejmail.dk", "Customer", true);
		assertNotNull(hans.getId());
		assertNotEquals(Integer.valueOf(-1), hans.getId());
		assertEquals(Integer.valueOf(-1), hans2.getId());
		assertEquals(hans.getName(), hans2.getName());
		assertEquals(hans.getAddress(), hans2.getAddress());
		assertEquals(hans.getCity(), hans2.getCity());
		assertEquals(hans.getEmail(), hans2.getEmail());
		assertEquals(hans.getType(), hans2.getType());
		assertEquals(hans.getIsValuedCust(), hans2.getIsValuedCust());
	}
	@Test //CC1
	void testInsertAndGetCustomer() throws SQLException{
		List<Person> custList = personCtr.findAllCustomers();
		Customer hans2 = (Customer) custList.get(custList.size()-1);
		assertEquals(hans2.toString(), hans.toString());
	}
	@Test //CC2
	void testPhoneConstraint() {
		Customer hans2 = new Customer("Hans", "Vejgade 9", "9000", "Aalborg", "9000", "hans@vejmail.dk", "Customer", true);
		try {
			personCtr.createCustomer(hans2);
			fail("Customer created with a phone number in database");
			personCtr.deletePerson(hans2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	@Test //CC3
	void testNoName() {
		Customer hans2 = new Customer(null, "Vejgade 9", "9000", "Aalborg", "99887733", "hans@markmail.dk", "Customer", true);
		try {
			personCtr.createCustomer(hans2);
			personCtr.deletePerson(hans2);
			fail("Customer created without name");
		} catch (Exception e) {
			
		}
	}
	@Test
	void testUpdateCustomer() throws SQLException{
		List<Person> custList;
		hans.setEmail("hanseMail@maail.com");
		hans.setIsValuedCust(false);
		personCtr.updateCustomer(hans);
		hans = (Customer) personCtr.findCustomerById(hans.getId());
		assertEquals("hanseMail@maail.com",hans.getEmail());
		assertFalse(hans.getIsValuedCust());
	}
	@Test
	void testCreateEmployee() {
		Employee per2 = new Employee("Per", "Pizzavej 9", "9000", "Aalborg", "88888853", "per@pizza.dk", "Employee", 1009d, "Bank.info.hat", "Bager");
		assertNotNull(per.getId());
		assertNotEquals(Integer.valueOf(-1), per.getId());
		assertEquals(Integer.valueOf(-1), per2.getId());
		assertEquals(per.getName(), per2.getName());
		assertEquals(per.getAddress(), per2.getAddress());
		assertEquals(per.getCity(), per2.getCity());
		assertEquals(per.getEmail(), per2.getEmail());
		assertEquals(per.getType(), per2.getType());
		assertEquals(per.getSalary(), per2.getSalary());
		assertEquals(per.getAccountInfo(), per2.getAccountInfo());
		assertEquals(per.getTitle(), per2.getTitle());
		}
	@Test
	void testInsertAndGetEmployee() throws SQLException{
		Employee per2 = (Employee) personCtr.findEmployeeById(per.getId());
		assertEquals(per2.toString(), per.toString());
	}
	@Test
	void testUpdateEmployee() throws SQLException{
		per.setEmail("perMail@maail.com");
		per.setSalary(104.22d);
		personCtr.updateEmployee(per);
		per = (Employee) personCtr.findEmployeeById(per.getId());
		assertEquals("perMail@maail.com",per.getEmail());
		assertEquals(104.22d, per.getSalary(), 0.1d);
	}
	@Test
	void testDelete() throws SQLException{
		List<Person> custList = personCtr.findAllCustomers();
		int lengthBefore = custList.size();
		personCtr.deletePerson(hans);
		custList = personCtr.findAllCustomers();
		int lengthAfter = custList.size();
		assertEquals(lengthBefore -1, lengthAfter);
	}
}

package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class Employee extends Person {

	private Double salary;
	private String accountInfo;
	private String title;


	/**
	 * @param name name of the person
	 * @param address address where the person lives
	 * @param zip zip code where the person lives
	 * @param city city where the person lives
	 * @param phoneNo phoneno of the person
	 * @param email email of the person
	 * @param type if the person is an employee or customer
	 * @param salary The salary of the employee
	 * @param accountInfo The bank account info of the employee
	 * @param title The title of the employee
	 */
	public Employee(String name, String address, String zip, String city, String phoneNo, String email, String type,
			Double salary, String accountInfo, String title) {
		super(name, address, zip, city, phoneNo, email, type);
		this.salary = salary;
		this.accountInfo = accountInfo;
		this.title = title;
	}


	/**
	 * @param id person id
	 * @param name name of the person
	 * @param address address where the person lives
	 * @param zip zip code where the person lives
	 * @param city city where the person lives
	 * @param phoneNo phoneno of the person
	 * @param email email of the person
	 * @param type if the person is an employee or customer
	 * @param salary The salary of the employee
	 * @param accountInfo The bank account info of the employee
	 * @param title The title of the employee
	 */
	public Employee(int id, String name, String address, String zip, String city, String phoneNo, String email,
			String type, Double salary, String accountInfo, String title) {
		super(id, name, address, zip, city, phoneNo, email, type);
		this.salary = salary;
		this.accountInfo = accountInfo;
		this.title = title;
	}

	/**
	 * This method gets the salary
	 * 
	 * @return salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * This method gets the account info
	 * 
	 * @return accountInfo
	 */
	public String getAccountInfo() {
		return accountInfo;
	}

	/**
	 * This method gets the title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method sets the salary
	 * 
	 * @param salary the salary to be set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * This method sets the account info
	 * 
	 * @param accountInfo the accountInfo to be set
	 */
	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}
	
	/**
	 * This method sets the title
	 * 
	 * @param title the title to be set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Person#toString()
	 */
	@Override
	public String toString() {
		return "Employee [" + super.toString() + ", salary=" + salary + ", accountInfo=" + accountInfo + ", title="
				+ title + "]";
	}

}

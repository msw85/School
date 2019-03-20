package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class Customer extends Person {
	private Boolean isValuedCust;

	/**
	 * @param name name of the person
	 * @param address address where the person lives
	 * @param zip zip code where the person lives
	 * @param city city where the person lives
	 * @param phoneNo phoneno of the person
	 * @param email email of the person
	 * @param type if the person is an employee or customer
	 * @param isValuedCust if the customer is a valued customer
	 */
	public Customer(String name, String address, String zip, String city, String phoneNo, String email, String type,
			Boolean isValuedCust) {
		super(name, address, zip, city, phoneNo, email, type);
		this.isValuedCust = isValuedCust;
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
	 * @param isValuedCust if the customer is a valued customer
	 */
	public Customer(int id, String name, String address, String zip, String city, String phoneNo, String email,
			String type, Boolean isValuedCust) {
		super(id, name, address, zip, city, phoneNo, email, type);
		this.isValuedCust = isValuedCust;
	}

	/**
	 * This method gets valued customer
	 * 
	 * @return isValuedCust
	 */
	public Boolean getIsValuedCust() {
		return isValuedCust;
	}

	/**
	 * This method sets valued customer
	 * 
	 * @param isValuedCust the valued customer to be set
	 */
	public void setIsValuedCust(Boolean isValuedCust) {
		this.isValuedCust = isValuedCust;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Person#toString()
	 */
	@Override
	public String toString() {
		return "Customer [ " + super.toString() + ", isValuedCust=" + isValuedCust + "]";
	}

}

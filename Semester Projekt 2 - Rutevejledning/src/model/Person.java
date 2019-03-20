package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public abstract class Person {
	private String name;
	private String address;
	private String zip;
	private String city;
	private String phoneNo;
	private String email;
	private String type;
	private Integer id;

	/**
	 * @param name name of the person
	 * @param address address where person lives
	 * @param zip zip code where person lives
	 * @param city city where person lives
	 * @param phoneNo phonenumber for person
	 * @param email email for person
	 * @param type if person is an employee or customer
	 */
	public Person(String name, String address, String zip, String city, String phoneNo, String email, String type) {
		super();
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.phoneNo = phoneNo;
		this.email = email;
		this.type = type;
	}

	/**
	 * @param id person id
	 * @param name name of the person
	 * @param address address where person lives
	 * @param zip zip code where person lives
	 * @param city city where person lives
	 * @param phoneNo phonenumber for person
	 * @param email email for person
	 * @param type if person is an employee or customer
	 */
	public Person(int id, String name, String address, String zip, String city, String phoneNo, String email,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.phoneNo = phoneNo;
		this.email = email;
		this.type = type;
	}

	/**
	 * This method gets id
	 * 
	 * @return person id 
	 */
	public Integer getId() {
		if (id == null)
			return -1;
		else
			return id;
	}
	
	/**
	 * This method gets name
	 * 
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method gets address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * This method gets zipcode
	 * 
	 * @return zip 
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * This method gets city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * This method gets phonenumber
	 * 
	 * @return phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	
	/**
	 * This method gets email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method gets type
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * This method sets id
	 * 
	 * @param id the id to be set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method sets name
	 * 
	 * @param name the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method sets address
	 * 
	 * @param address the address to be set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method sets zipcode
	 * 
	 * @param zip the zip to be set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * This method sets city
	 * 
	 * @param city the city to be set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method sets phonenumber
	 * 
	 * @param phoneNo the phoneNo to be set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * This method sets email
	 * 
	 * @param email the email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method sets type
	 * 
	 * @param type the type to be set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", address=" + address + ", zip=" + zip + ", city=" + city + ", phoneNo="
				+ phoneNo + ", email=" + email + ", type=" + type;
	}

}

package model;
//TODO: LAV COUNTRY
public class Supplier {
	private Integer id;
	private String name;
	private String address;
	private Country country;
	private String phoneno;
	private String email;
	
	public Supplier(int id, String name, String address, Country country, String phoneno, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.phoneno = phoneno;
		this.email = email;
	}
	
	public Supplier(String name, String address, Country country, String phoneno, String email) {
		super();
		this.name = name;
		this.address = address;
		this.country = country;
		this.phoneno = phoneno;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", country=" + country + ", phoneno="
				+ phoneno + ", email=" + email + "]";
	}


}

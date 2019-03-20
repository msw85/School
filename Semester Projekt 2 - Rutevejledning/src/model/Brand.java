package model;
	
/**
 * @author Gruppe 6, DMAA0917
 *
 */

public class Brand {
	private int id;
	private String name;
	
	/**
	 * Instantiate variables from constructor
	 * @param id brand id
	 * @param name brand name
	 */
	public Brand(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	 * This method sets id
	 * 
	 * @param id the id to be set
	 */
	public void setId(int id) {
		this.id = id;
	}
}

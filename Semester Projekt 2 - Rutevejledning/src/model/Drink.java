package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class Drink extends Food {
	//Initiate local variables
	private boolean isDiet;
	private String brand;
	
	/**
	 * Instantiate variables from constructor and build a Drink object from super class Food
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isDiet diet drink
	 * @param brand drink brand
	 */
	public Drink(String name, int menuNo, double price, String type, String desc, String size, boolean isDiet, String brand) {
		super(name, menuNo, price, type, desc, size);
		this.isDiet = isDiet;
		this.brand = brand;
	}

	/**
	 * Instantiate variables from constructor and build a Drink object from super class Food
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isDiet diet drink
	 * @param brand drink brand
	 */
	public Drink(Integer id, String name, int menuNo, double price, String type, String desc, String size, boolean isDiet, String brand) {
		super(id, name, menuNo, price, type, desc, size);
		this.isDiet = isDiet;
		this.brand = brand;
	}

	/**
	 * This method gets the isDiet boolean value of the object 
	 * @return
	 */
	public boolean isDiet() {
		return isDiet;
	}
	
	/**
	 * This method gets the brand of the object
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * This method sets the isDiet boolean value of the object
	 * @param isDiet
	 */
	public void setDiet(boolean isDiet) {
		this.isDiet = isDiet;
	}

	/**
	 * This method sets the brand of the object
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
}

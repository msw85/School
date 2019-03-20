package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */

public class Pizza extends Food {

	//Initiate local variables
	private boolean isLunchOffer;
	
	/**
	 * Instantiate variables from constructor and build a Pizza object from super class Food
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isLunchOffer pizza lunch offer
	 */
	public Pizza(Integer id, String name, int menuNo, double price, String type, String desc, String size, boolean isLunchOffer) {
		super(id, name, menuNo, price, type, desc, size);
		this.setLunchOffer(isLunchOffer);
	}

	/**
	 * Instantiate variables from constructor and build a Pizza object from super class Food
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isLunchOffer pizza lunch offer
	 */
	public Pizza(String name, int menuNo, double price, String type, String desc, String size, boolean isLunchOffer) {
		super(name, menuNo, price, type, desc, size);
		this.setLunchOffer(isLunchOffer);
	}

	/**
	 * This method gets if pizza is lunchoffer
	 * 
	 * @return isLunchOffer
	 */
	public boolean isLunchOffer() {
		return isLunchOffer;
	}

	/*
	 * This method sets lunchOffer
	 * 
	 * @param isLunchOffer the lunchOffer to be set
	 */
	public void setLunchOffer(boolean isLunchOffer) {
		this.isLunchOffer = isLunchOffer;
	}
}

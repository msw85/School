package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class FastFood extends Food {

	/**
	 * Constructor builds a FastFood object from super class Food
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 */
	public FastFood(String name, int menuNo, double price, String type, String desc, String size) {
		super(name, menuNo, price, type, desc, size);
	}
	/**
	 * Constructor builds a FastFood object from super class Food
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 */
	public FastFood(Integer id, String name, int menuNo, double price, String type, String desc, String size) {
		super(id, name, menuNo, price, type, desc, size);
	}
	/* (non-Javadoc)
	 * @see model.Food#toString()
	 */
	@Override
	public String toString() {
		return "FastFood [" + super.toString() + "]";
	}

}

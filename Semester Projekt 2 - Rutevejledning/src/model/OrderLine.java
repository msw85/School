package model;

/**
 * @author Aske
 *
 */
public class OrderLine {
	private Integer id;
	private Double price;
	private int quantity;
	private Food food;
	
	/**
	 * @param price
	 * @param quantity
	 * @param food
	 */
	public OrderLine(Double price, int quantity, Food food) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.food = food;
	}
	
	/**
	 * @param id
	 * @param price
	 * @param quantity
	 * @param food
	 */
	public OrderLine(Integer id, Double price, int quantity, Food food) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.food = food;
	}
	
	/**
	 * @return
	 */
	public Integer getId() {
		if(id == null) {
			return -1;
		}
		return id;
	}

	/**
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return
	 */
	public Food getFood() {
		return food;
	}
	

	/**
	 * @param food
	 */
	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", price=" + price + ", quantity=" + quantity + ", food=" + food + "]";
	}
}

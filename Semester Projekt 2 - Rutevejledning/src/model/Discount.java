package model;

/**
 * @author Gruppe 6, DMAA0917
 */
public class Discount {

	private String type;
	private Integer percentage;
	private int id;

	/**
	 * This contructor is used when instantiating an object without id.
	 * 
	 * @param type the type of discount
	 * @param percentage the amount of discount
	 */
	public Discount(String type, Integer percentage) {
		this.type = type;
		this.percentage = percentage;
	}

	/**
	 * This contructor is used when instantiating an object with id.
	 * 
	 * @param type the type of discount
	 * @param percentage the amount of discount
	 */
	public Discount(Integer id, String type, Integer percentage) {
		this.id = id;
		this.type = type;
		this.percentage = percentage;
	}

	/**
	 * This method returns the type.
	 * 
	 * @return type the type of discount
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method sets the type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method returns the percentage.
	 * 
	 * @return percentage the amount of discount
	 */
	public Integer getPercentage() {
		return percentage;
	}

	/**
	 * This method sets the percentage.
	 */
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	
	/**
	 * This method returns the id.
	 * 
	 * @return id the id of the discount
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets the id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Discount [type=" + type + ", percentage=" + percentage + ", id=" + id + "]";
	}
	
}

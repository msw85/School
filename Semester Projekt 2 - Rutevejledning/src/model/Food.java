package model;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public abstract class Food {
	//Initiate local variables
	private Integer id;
	private String name;
	private int menuNo;
	private double price;
	private String type;
	private String desc;
	private String size;

	/**
	 * Instantiate variables from constructor
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 */
	public Food(String name, int menuNo, double price, String type, String desc, String size) {
		super();
		this.name = name;
		this.menuNo = menuNo;
		this.price = price;
		this.type = type;
		this.desc = desc;
		this.size = size;
	}

	/**
	 * Instantiate variables from constructor
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 */
	public Food(Integer id, String name, int menuNo, double price, String type, String desc, String size) {
		super();
		this.id = id;
		this.name = name;
		this.menuNo = menuNo;
		this.price = price;
		this.type = type;
		this.desc = desc;
		this.size = size;
	}

	/**
	 * This method gets the ID of the object
	 * @return id of instance
	 * -1 if id is null
	 */
	public Integer getId() {
		if (id == null) {
			return -1;
		} else {
			return id;
		}
	}

	/**
	 * This method gets the name of the object
	 * @return name of instance
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method gets the menuNo of the object
	 * @return menu number of instance
	 */
	public int getMenuNo() {
		return menuNo;
	}

	/**
	 * This method gets the price of the object
	 * @return price of instance
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This method gets the type of the object
	 * @return type of instance
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method gets the description of the object
	 * @return description of instance
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * This method gets the size of the object
	 * @return size of instance
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param id
	 * Set id of the object
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 * Set name of object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param menuNo
	 * Set menu number of object
	 */
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * @param price
	 * Set price of object
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param type
	 * Set type of object
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param desc
	 * Set description of object
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param size
	 * Set size of object
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return string containing all variables in the object
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", menuNo=" + menuNo + ", price=" + price + ", type=" + type
				+ ", desc=" + desc + ", size=" + size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + menuNo;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menuNo != other.menuNo)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}

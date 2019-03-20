package model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class Order {
	private Integer id;
	private Date orderDate;
	private Date dueTime;
	private boolean toDeliver;
	private boolean isDelivered;
	private boolean isReady;
	private Double totalPrice;
	private List<OrderLine> orderL;
	private Person pers;


	/** 
	 * Instantiate variables from constructor
	 * @param orderDate date and time when order is created.
	 * @param dueTime date and time when order should be delivered.
	 * @param toDeliver if its an order to be delivered.
	 * @param isDelivered if the order is delivered.
	 * @param isReady if the order is ready to be delivered.
	 * @param totalPrice total price of the order.
	 * @param pers person object.
	 */
	public Order(Date orderDate, Date dueTime, Boolean toDeliver, Boolean isDelivered, Boolean isReady,
			Double totalPrice, Person pers) {
		super();
		this.orderL = new LinkedList<OrderLine>();
		this.orderDate = orderDate;
		this.dueTime = dueTime;
		this.toDeliver = toDeliver;
		this.isDelivered = isDelivered;
		this.isReady = isReady;
		this.totalPrice = totalPrice;
		this.pers = pers;
	}


	/** 
	 * Instantiate variables from constructor
	 * @param id order id.
	 * @param orderDate date and time when order is created.
	 * @param dueTime date and time when order should be delivered.
	 * @param toDeliver if its an order to be delivered.
	 * @param isDelivered if the order is delivered.
	 * @param isReady if the order is ready to be delivered.
	 * @param totalPrice total price of the order.
	 * @param orderL list of OrderLines.
	 * @param pers person object.

	 */
	public Order(Integer id, Date orderDate, Date dueTime, Boolean toDeliver, Boolean isDelivered, Boolean isReady,
			Double totalPrice, List<OrderLine> orderL, Person pers) {
		super();
		this.orderL = new LinkedList<OrderLine>();
		this.id = id;
		this.orderDate = orderDate;
		this.dueTime = dueTime;
		this.toDeliver = toDeliver;
		this.isDelivered = isDelivered;
		this.isReady = isReady;
		this.totalPrice = totalPrice;
		this.orderL = orderL;
		this.pers = pers; 
	}

	/**
	 * This method gets id
	 * 
	 * @return order id
	 */
	public Integer getId() {
		if (id == null) {
			return -1;
		} else {
			return id;
		}
	}

	/**	 
	 * This method gets orderDate
	 * 
	 * @return date
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
 	 * This method gets dueTime
 	 * 
	 * @return dueTime
	 */
	public Date getDueTime() {
		return dueTime;
	}

	/**
	 * This method gets toDeliver
	 * 
	 * @return toDeliver.
	 */
	public boolean getToDeliver() {
		return toDeliver;
	}

	/**
	 * This method gets isDelivered
	 * 
	 * @return isDelivered.
	 */
	public boolean getIsDelivered() {
		return isDelivered;
	}

	/**
	 * This method gets isReady
	 * 
	 * @return isReady.
	 */
	public boolean getIsReady() {
		return isReady;
	}

	/**
	 * This method gets totalPrice
	 * 
	 * @return totalPrice 
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * This method gets Person
	 * 
	 * @return Person object
	 */
	public Person getPers() {
		return pers;
	}
	
	/**
	 * This method gets OrderLines
	 * 
	 * @return List of OrderLines
	 */
	public List<OrderLine> getOrderL() {
		return orderL;
	}

	/**
	 * This method sets id.
	 * 
	 * @param id the id to be set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method sets orderDate
	 * 
	 * @param orderDate the orderDate to be set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * This method sets dueTime
	 * 
	 * @param dueTime the dueTime to be set
	 */
	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	/**
	 * This method sets toDeliver
	 * 
	 * @param toDeliver the toDeliver to be set
	 */
	public void setToDeliver(Boolean toDeliver) {
		this.toDeliver = toDeliver;
	}

	/**
	 * This method sets isDelivered
	 * 
	 * @param isDelivered the isDelivered to be set
	 */
	public void setIsDelivered(Boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	/**
	 * This method sets isReady
	 * 
	 * @param isReady the isReady to be set
	 */
	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * This method sets totalPrice
	 * 
	 * @param totalPrice the totalPrice to be set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * This method sets Person
	 * 
	 * @param pers the Person to be set
	 */
	public void setPers(Person pers) {
		this.pers = pers;
	}

	/**
	 * This method sets toDeliver
	 * 
	 * @param toDeliver the toDeliver to be set
	 */
	public void setToDeliver(boolean toDeliver) {
		this.toDeliver = toDeliver;
	}

	/**
	 * This method set isDelivered
	 * 
	 * @param isDelivered the isDelivered to be set
	 */
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	/**
	 * This method sets isReady
	 * 
	 * @param isReady the isReady to be set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * This method adds Orderlines
	 * 
	 * @param ol OrderLine object
	 */
	public void addOrderL(OrderLine ol) {
		orderL.add(ol);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", dueTime=" + dueTime + ", toDeliver=" + toDeliver
				+ ", isDelivered=" + isDelivered + ", isReady=" + isReady + ", totalPrice=" + totalPrice 
				+ ", orderL=" + orderL + ", pers=" + pers + "]";
	}

}

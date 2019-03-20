package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SaleOrder {
	private Integer id;
	private Date date;
	private Double price;
	private boolean deliveryStatus;
	private Date deliveryDate;
	private Customer cust;
	private List<OrderLine> orderlines;
	
	public SaleOrder(Integer id, Date date, Double price, boolean deliveryStatus, Date deliveryDate, Customer cust) {
		super();
		orderlines = new LinkedList<>();
		this.id = id;
		this.date = date;
		this.price = price;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.cust = cust;
	}
	
	public SaleOrder(Date date, boolean deliveryStatus, Customer cust) {
		super();
		orderlines = new LinkedList<>();
		this.date = date;
		this.deliveryStatus = deliveryStatus;
		this.cust = cust;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public List<OrderLine> getOrderlines() throws NullPointerException {
		return orderlines;
	}
	public void addOrderline(OrderLine ol) {
		orderlines.add(ol);
	}
	@Override
	public String toString() {
		return "SaleOrder [id=" + id + ", date=" + date + ", price=" + price + ", deliveryStatus=" + deliveryStatus
				+ ", deliveryDate=" + deliveryDate + ", cust=" + cust + ", orderlines=" + orderlines + "]";
	}
	
	

}

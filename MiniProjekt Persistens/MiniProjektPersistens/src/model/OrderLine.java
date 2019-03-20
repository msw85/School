package model;

public class OrderLine {
	private Integer id;
	private Integer quantity;
	private Double accumulatedPrice;
	private Product prod;
	
	public OrderLine(Integer id, Integer quantity, Double accumulatedPrice, Product prod) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.accumulatedPrice = accumulatedPrice;
		this.prod = prod;
	}
	
	public OrderLine(Integer quantity, Product prod) {
		super();
		this.quantity = quantity;
		this.prod = prod;
		this.accumulatedPrice = calcuteAccPrice();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAccPrice() {
		return accumulatedPrice;
	}
	public void setAccPrice(Double accumulatedPrice) {
		this.accumulatedPrice = accumulatedPrice;
	}
	public Product getProd() {
		return prod;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	private Double calcuteAccPrice() {
		Double result = Double.valueOf(quantity) * prod.getSalesPrice();
		return result;
	}
	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", quantity=" + quantity + ", accumulatedPrice=" + accumulatedPrice + ", prod=" + prod + "]";
	}
	

}

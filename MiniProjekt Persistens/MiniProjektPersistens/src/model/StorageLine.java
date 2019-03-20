package model;

public class StorageLine {
	private Integer id;
	private Integer numInStock;
	private Storage stor;
	private Product prod;
	
	public StorageLine(Integer id, Integer numInStock, Storage stor, Product prod) {
		super();
		this.id = id;
		this.numInStock = numInStock;
		this.stor = stor;
		this.prod = prod;
	}
	public StorageLine(Integer numInStock, Storage stor) {
		super();
		this.numInStock = numInStock;
		this.stor = stor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumInStock() {
		return numInStock;
	}
	public void setNumInStock(Integer numInStock) {
		this.numInStock = numInStock;
	}
	public Storage getStor() {
		return stor;
	}
	public void setStor(Storage stor) {
		this.stor = stor;
	}
	public Product getProd() {
		return prod;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	@Override
	public String toString() {
		return "StorageLine [id=" + id + ", numInStock=" + numInStock + ", stor=" + stor + "]";
	}
	
}

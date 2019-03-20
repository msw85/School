package model;

import java.util.LinkedList;
import java.util.List;

public class Product {
	private Integer id;
	private String name;
	private double purchasePrice;
	private double salesPrice;
	private String countryOfOrigin;
	private Integer minStock;
	private Supplier supplier;
	private String barcode;
	private List<StorageLine> locations;
	
	public Product(Integer id, String name, double purchasePrice, double salesPrice, String countryOfOrigin, Integer minStock,
			Supplier supplier, String barcode, List<StorageLine> locations) {
		super();
		locations = new LinkedList<StorageLine>();
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.supplier = supplier;
		this.barcode = barcode;
		this.locations = locations;
	}
	public Product(String name, double purchasePrice, double salesPrice, String countryOfOrigin, Integer minStock,
			Supplier supplier, String barcode) {
		super();
		locations = new LinkedList<StorageLine>();
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.supplier = supplier;
		this.barcode = barcode;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public Integer getMinStock() {
		return minStock;
	}
	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public List<StorageLine> getLocations() {
		return locations;
	}

	public void addLocation(StorageLine loc) {
		locations.add(loc);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", purchasePrice=" + purchasePrice + ", salesPrice=" + salesPrice
				+ ", countryOfOrigin=" + countryOfOrigin + ", minStock=" + minStock + 
				", supplier=" + supplier + ", barcode=" + barcode + "]";
	}
	
}

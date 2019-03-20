package modelLayer;

/**
 * @author (Group 6)
 * @version (V.1.0)
 */
public class Product{
    private Integer id;
    //private static Integer supplierID = 100;
    private Integer minStock;
    private Integer maxStock;
    private String serialNumber;
    private String name;
    private String type;
    private String description;
    private String color;
    private double rentPrice;
    private double price;
    private double amount;
    private double supplierPrice;
    private double discount;
    private boolean active;
    private Supplier supplier;

    /**
     * Product Constructor
     *
     * @param minStock A parameter
     * @param maxStock A parameter
     * @param serialNumber A parameter
     * @param name A parameter
     * @param type A parameter
     * @param description A parameter
     * @param color A parameter
     * @param rentPrice A parameter
     * @param price A parameter
     * @param amount A parameter
     * @param supplierPrice A parameter
     * @param discount A parameter
     */
    public Product(Integer minStock, Integer maxStock, String serialNumber, String name, 
    String type, String description, String color, double rentPrice, double price, double amount, 
    double supplierPrice, double discount, Supplier sup){
        int sizeArr = ProductContainer.getInstance().size();
        setFields(sizeArr, minStock, maxStock, serialNumber, name, type, description, color, rentPrice, price, amount, supplierPrice, discount, sup);
        active = true;
    }

    /**
     * Method setFields
     *
     * @param id A parameter
     * @param minStock A parameter
     * @param maxStock A parameter
     * @param serialNumber A parameter
     * @param name A parameter
     * @param type A parameter
     * @param description A parameter
     * @param color A parameter
     * @param rentPrice A parameter
     * @param price A parameter
     * @param amount A parameter
     * @param supplierPrice A parameter
     * @param discount A parameter
     */
    public void setFields(Integer id, Integer minStock, Integer maxStock, String serialNumber, String name, String type, String description, String color, double rentPrice, double price, double amount, double supplierPrice, double discount, Supplier sup){
        if(this.id == null || id != null){
            this.id = id;
        }
        if(minStock != null){
            this.minStock = minStock;
        }
        if(maxStock != null){
            this.maxStock = maxStock;
        }
        if(serialNumber != null){
            this.serialNumber = serialNumber;
        }
        if(name != null){
            this.name = name;
        }
        if(type != null){
            this.type = type;
        }
        if(description != null){
            this.description = description;
        }
        if(color != null){
            this.color = color;
        }
        if(rentPrice != 0){
            this.rentPrice = rentPrice;
        }
        if(price != 0){
            this.price = price;
        }
        if(amount != 0){
            this.amount = amount;
        }
        if(supplierPrice != 0){
            this.supplierPrice = supplierPrice;
        }
        if(discount != 0){
            this.discount = discount;
        }
        if(sup != null) {
        	this.supplier = sup;
        }
    }

    /**
     * Method toString
     *
     * @return The return value
     */
    public String toString(){
        return id + " " + name;
    }

    /**
     * Method getID
     *
     * @return The return value
     */
    public Integer getID(){
        return id;
    }

    /**
     * Method getMinStock
     *
     * @return The return value
     */
    public Integer getMinStock(){
        return minStock;
    }

    /**
     * Method getMaxStock
     *
     * @return The return value
     */
    public Integer getMaxStock(){
        return maxStock;
    }

    /**
     * Method getSerialNumber
     *
     * @return The return value
     */
    public String getSerialNumber(){
        return serialNumber;
    }

    /**
     * Method getName
     *
     * @return The return value
     */
    public String getName(){
        return name;
    }

    /**
     * Method getType
     *
     * @return The return value
     */
    public String getType(){
        return type;
    }

    /**
     * Method getDescription
     *
     * @return The return value
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method getColor
     *
     * @return The return value
     */
    public String getColor(){
        return color;
    }

    /**
     * Method getRentPrice
     *
     * @return The return value
     */
    public double getRentPrice(){
        return rentPrice;
    }

    /**
     * Method getPrice
     *
     * @return The return value
     */
    public double getPrice(){
        return price;
    }

    /**
     * Method getAmount
     *
     * @return The return value
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Method getSupplierPrice
     *
     * @return The return value
     */
    public double getSupplierPrice(){
        return supplierPrice;
    }

    /**
     * Method getDiscount
     *
     * @return The return value
     */
    public double getDiscount(){
        return discount;
    }
    
    public Supplier getSup() {
    	return supplier;
    }
    
    public void setSup(Supplier sup) {
    	supplier = sup;
    }
    
    public void removeSup() {
    	supplier = null;
    }

    /**
     * Method toggleActive
     *
     */
    public void toggleActive() {
        if(active) {
            active = false;
        } else {
            active = true;
        }
    }
    
    public boolean getState() {
    	return active;
    }
}

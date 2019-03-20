package controllerLayer;
import modelLayer.*;

public class ProductController{
    private ProductContainer proCon;

    /**
     * ProductController Constructor
     *
     */
    public ProductController(){
        proCon = ProductContainer.getInstance();
    }

    /**
     * Method getProduct
     *
     * @param id A parameter
     * @return The return value
     */
    public Product getProduct(int id){
        return proCon.getProduct(id);
    }

    public Product[] getProduct(String name){
		return proCon.getProduct(name);
    }

    
    public int size() {
    	return proCon.size();
    }
    /**
     * Method addProduct
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
    public void addProduct(Integer minStock, Integer maxStock, String serialNumber, String name, 
    String type, String description, String color, double rentPrice, double price, double amount, 
    double supplierPrice, double discount, Supplier sup){
        proCon.addProduct(minStock, maxStock, serialNumber, name, type, description, 
            color, rentPrice, price, amount, supplierPrice, discount, sup);
    }

    /**
     * Method removeProduct
     *
     * @param id A parameter
     */
    public void removeProduct(int id){
        proCon.removeProduct(id);
    }

    /**
     * Method updateProduct
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
    public void updateProduct(int id, Integer minStock, Integer maxStock, String serialNumber, String name, 
    String type, String description, String color, double rentPrice, double price, double amount, 
    double supplierPrice, double discount, Supplier sup){
        proCon.updateProduct(id, minStock, maxStock, serialNumber, name, type, description, color, rentPrice, price, amount, supplierPrice, discount, sup);
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id){
        proCon.toggleActive(id);
    }

    public Product[] toArr() {
    	return proCon.toArr();
    }
    
    public Supplier getSup(int id) {
    	return proCon.getSup(id);
    }
    
    public void setSup(int id, Supplier sup) {
    	proCon.setSup(id, sup);
    }
    
    public void removeSup(int id) {
    	proCon.removeSup(id);
    }
}

package modelLayer;

import java.util.*;

public class ProductContainer
{
    private static ProductContainer instance;
    private static ArrayList<Product> product; 

    /**
     * ProductContainer Constructor
     *
     */
    private ProductContainer()
    {
        product = new ArrayList<Product>();
    }

    /**
     * Method getInstance
     *
     * @return The return value
     */
    public static ProductContainer getInstance(){
        if(instance == null){
            instance = new ProductContainer();
        }
        return instance;
    }

    /**
     * Method getProduct
     *
     * @param id A parameter
     * @return The return value
     */
    public Product getProduct(int id){
        return product.get(id);
    }    
    	 
    public Product[] getProduct(String name){
    	List<Product> foundProd = new LinkedList<Product>();
    	Product prod = null;
    	for(int i = 0; i < size(); i++) {
    		if(product.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
    			foundProd.add(product.get(i));
    		} else {
    			prod = null;
    		}
    	}
    	
      Product[] arr = new Product[foundProd.size()];
      arr = foundProd.toArray(arr);
      return arr;
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
        Product pro = new Product(minStock, maxStock, serialNumber, name, type, description, 
                color, rentPrice, price, amount, supplierPrice, discount, sup);
        product.add(pro);
    }

    /**
     * Method removeProduct
     *
     * @param id A parameter
     */
    public void removeProduct(int id){
        product.remove(id);
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
    double supplierPrice, double discount, Supplier sup)
    {  
        Product o = getProduct(id);
        o.setFields(null, minStock, maxStock, serialNumber, name, type, description, 
            color, rentPrice, price, amount, supplierPrice, discount, sup);
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size(){
        return product.size();
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id) {
        product.get(id).toggleActive();
    }
    
    public Product[] toArr() {
    	Product[] arr = new Product[size()];
    	arr = product.toArray(arr);
    	return arr;
    }
    
    public boolean getState(int id) {
    	return product.get(id).getState();
    }
    
    public Supplier getSup(int id) {
    	return product.get(id).getSup();
    }
    
    public void setSup(int id, Supplier sup) {
    	product.get(id).setSup(sup);
    }
    
    public void removeSup(int id) {
    	product.get(id).removeSup();
    }
}

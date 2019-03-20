package modelLayer;

import java.util.*;

public class CustomerContainer
{
    private static CustomerContainer instance;
    private static ArrayList<Customer> customer; 

    /**
     * CustomerContainer Constructor
     *
     */
    private CustomerContainer()
    {
        customer = new ArrayList<Customer>();
    }

    /**
     * Method getInstance
     *
     * @return The return value
     */
    public static CustomerContainer getInstance(){
        if(instance == null){
            instance = new CustomerContainer();
        }
        return instance;
    }

    /**
     * Method getCustomer
     *
     * @param id A parameter
     * @return The return value
     */
    public Customer getCustomer(int id){
        return customer.get(id);
    }
    
    public Customer[] getCustomer(String name){
    	LinkedList<Customer> foundCust = new LinkedList<Customer>();
    	//Customer cust = null;
    	for(int i = 0; i < size(); i++) {
    		if(customer.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
    			foundCust.add(customer.get(i));
    		}
    	}
    	
      Customer[] arr = new Customer[foundCust.size()];
      arr = foundCust.toArray(arr);
      return arr;
    }

    /**
     * Method addCustomer
     *
     * @param name A parameter
     * @param address A parameter
     * @param zip A parameter
     * @param city A parameter
     * @param phone A parameter
     * @param discount A parameter
     */
    public void addCustomer(String name, String address, String zip, String city, String phone){
        Customer c = new Customer(name, address, zip, city, phone);
        customer.add(c);
    }

    /**
     * Method removeCustomer
     *
     * @param id A parameter
     */
    public void removeCustomer(int id){
        customer.remove(id);
    }
    
    public void toggleActive(int id) {
    	customer.get(id).toggleActive();
    }

    /**
     * Method updateCustomer
     *
     * @param id A parameter
     * @param name A parameter
     * @param address A parameter
     * @param zip A parameter
     * @param city A parameter
     * @param phone A parameter
     * @param discount A parameter
     */
    public void updateCustomer(int id, String name, String address, String zip, String city, String phone)
    {  
        Customer o = getCustomer(id);
        o.setFields(null, name, address, zip, city, phone);
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size(){
        return customer.size();
    }

    /**
     * Method addOrder
     *
     * @param cust A parameter
     * @param o A parameter
     */
    public void addOrder(int cust, Order o){
        Customer c = getCustomer(cust);
        c.addOrder(o);
    }

    /**
     * Method getOrder
     *
     * @param cust A parameter
     * @param odr A parameter
     * @return The return value
     */
    public Order getOrder(int cust, int odr){
        Customer c = getCustomer(cust);
        return c.getOrder(odr);        
    }
    
    public boolean getState(int id) {
    	return customer.get(id).getState();
    }
}

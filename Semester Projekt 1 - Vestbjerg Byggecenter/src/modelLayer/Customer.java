 package modelLayer;

import java.util.*;
/**
 * @author (Group 6)
 * @version (V.1.0)
 */
public class Customer{
    private Integer id;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String phone;
    private boolean active;
    private LinkedList<Order> orders;

    /**
     * Customer Constructor
     *
     * @param name A parameter
     * @param address A parameter
     * @param zip A parameter
     * @param city A parameter
     * @param phone A parameter
     * @param discount A parameter
     */
    public Customer(String name, String address, String zip, String city, String phone){
        Integer sizeArr = CustomerContainer.getInstance().size();
        orders = new LinkedList<Order>();
        setFields(sizeArr++, name, address, zip, city, phone);
        active = true;
    }

    /**
     * Method setFields
     *
     * @param id A parameter
     * @param name A parameter
     * @param address A parameter
     * @param zip A parameter
     * @param city A parameter
     * @param phone A parameter
     * @param discount A parameter
     */
    public void setFields(Integer id, String name, String address, String zip, String city, String phone){
        if(this.id == null || id != null){
            this.id = id;
        }

        if(name != null){
            this.name = name;
        }
        if(address != null){
            this.address = address;
        }
        if(zip != null){
            this.zip = zip;
        }
        if(city != null){
            this.city = city;
        }
        if(phone != null){
            this.phone = phone;
        }
        // if(discount != 0){
            // this.discount = discount;
        // }
    }

    /**
     * Method addOrder
     *
     * @param o A parameter
     */
    //Add order to customer
    public void addOrder(Order o){
        orders.add(o);
    }

    /**
     * Method toString
     *
     * @return The return value
     */
//    public String toString(){
//        String act;
//        if(active) {
//            act = "Aktiv";
//        }
//        else {
//            act = "Ikke aktiv";
//        }
//        return "ID: " + id + " Navn: " + name + " Adresse: " + address + " PostNr: " + zip + 
//        " By: " + city + " Telefonnummer: " + phone + " Status: " +act+ "\n";
//    }
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
     * Method getName
     *
     * @return The return value
     */
    public String getName(){
        return name;
    }

    /**
     * Method getAddress
     *
     * @return The return value
     */
    public String getAddress(){
        return address;
    }

    /**
     * Method getZip
     *
     * @return The return value
     */
    public String getZip(){
        return zip;
    }

    /**
     * Method getCity
     *
     * @return The return value
     */
    public String getCity(){
        return city;
    }

    /**
     * Method getPhone
     *
     * @return The return value
     */
    public String getPhone(){
        return phone;
    }

    /**
     * Method getOrder
     *
     * @param i A parameter
     * @return The return value
     */
    public Order getOrder(int i){
        return orders.get(i);
    }

    
    public Order[] getOrders() { 
    	Order[] a = new Order[orders.size()];
    	a = orders.toArray(a);
    	return a;
    }
    
    public int getOrderSize() {
    	return orders.size();    	
    }
    
    public boolean getState() {
    	return active;
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
}

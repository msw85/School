package modelLayer;

import java.util.*;
public class OrderContainer{
    private static OrderContainer instance;
    private static ArrayList<Order> orders; 

    /**
     * OrderContainer Constructor
     *
     */
    private OrderContainer(){
        orders = new ArrayList<Order>();
    }

    /**
     * Method getInstance
     *
     * @return The return value
     */
    public static OrderContainer getInstance(){
        if(instance == null){
            instance = new OrderContainer();
        }
        return instance;
    }

    /**
     * Method addOrder
     *
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public void addOrder(String trackAndTrace, double weight, double coli){
        Order o = new Order(trackAndTrace, weight, coli);
        orders.add(o);
    }

    /**
     * Method getOrder
     *
     * @param id A parameter
     * @return The return value
     */
    public Order getOrder(int id){
        return orders.get(id);
    }

    /**
     * Method removeOrder
     *
     * @param id A parameter
     */
    public void removeOrder(int id){
        orders.remove(id);
    }

    /**
     * Method updateOrder
     *
     * @param id A parameter
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public void updateOrder(Integer id, String trackAndTrace, double weight, double coli){
        Order o = getOrder(id);
        o.setFields(null, trackAndTrace, weight, coli);
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size(){
        return orders.size();
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id) {
        orders.get(id).toggleActive();
    } 

    /**
     * Method addProduct
     *
     */
    public void addProduct(){
    }
    
    //public Order[] toArr() {
    	//Order[] arr = new Order[size()];
    	//arr = orders.toArray(arr);
    	//return arr;
    //}
}

package modelLayer;

import java.util.*;

/**
 * @author (Group 6)
 * @version (V.1.0)
 */
public class Account {
    // instance variables
	private int id;
    private String paymentAgreement;
    private Integer[] discount;
    private ArrayList<Order> orders;
    private double balance;
    private boolean active;
    private Customer customer;

    /**
     * Account Constructor
     *
     * @param payment A parameter
     * @param craftD A parameter
     * @param amountD A parameter
     * @param pickupD A parameter
     * @param balance A parameter
     */
    public Account(String payment, Integer craftD, Integer amountD, Integer pickupD, double balance) {
    	Integer sizeArr = AccountContainer.getInstance().size();
        // initialise instance variables
        // Discount placement in array: [0]craftsmanDiscount, [1]amountDiscount, [2]pickupDiscount.
        discount = new Integer[3];
        orders = new ArrayList<>();
        active = true;
        this.id = sizeArr++;
        this.paymentAgreement = payment;
        this.discount[0] = craftD;
        this.discount[1] = amountD;
        this.discount[2] = pickupD;
        this.balance = balance;
    }

    public Integer getID() {
    	return id;
    }
    
    /**
     * Method getPaymentAgreement
     *
     * @return The return value
     */
    //Get methodes
    public String getPaymentAgreement() {
        return paymentAgreement;
    }
    
    public boolean hasCustomer() {
    	if(customer != null)
    		return true;
    	else
    		return false;
    }
    /**
     * Method getCraftmansDiscount
     *
     * @return The return value
     */
    public Integer getCraftmansDiscount() {
        return discount[0];
    }

    /**
     * Method getAmountDiscount
     *
     * @return The return value
     */
    public Integer getAmountDiscount() {
        return discount[1];
    }

    /**
     * Method getPickupDiscount
     *
     * @return The return value
     */
    public Integer getPickupDiscount() {
        return discount[2];
    }

    /**
     * Method getOrder
     *
     */
    public void getOrder() {
        Order result = null;
        int i;
        for(i = 0; i < orders.size(); i++) {
            result = orders.get(i);
        }
        System.out.println("-----------------");
        System.out.println("Ordre nummer: "+ i);
        System.out.println(result);
    }
    
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Method getBalance
     *
     * @return The return value
     */
    public double getBalance() {
        return balance;
    }
    
    public boolean getState() {
    	return active;
    }
    
    public Integer getTotalDiscount() {
        Integer sum = 0;
        sum = discount[0] + discount[1] + discount[2];
        return sum;
    }

    /**
     * Method setFields
     *
     * @param paymentAgreement A parameter
     * @param craftmansDiscount A parameter
     * @param amountDiscount A parameter
     * @param pickupDiscount A parameter
     * @param order A parameter
     * @param balance A parameter
     */
    public void setFields(String paymentAgreement, Integer craftmansDiscount, Integer amountDiscount, Integer pickupDiscount, Order order, double balance) {
        if(paymentAgreement != null){
            this.paymentAgreement = paymentAgreement;
        }
        if(craftmansDiscount != 0){
            discount[0] = craftmansDiscount;
        }
        if(amountDiscount != 0){
            discount[1] = amountDiscount;
        }
        if(pickupDiscount != 0){
            discount[2] = pickupDiscount;
        }
        if(order != null){
            orders.add(order);
        }
        if(balance != 0){
            this.balance = balance;
        }
    }

    /**
     * Method addOrder
     *
     * @param o A parameter
     */
    public void addOrder(Order o){
        orders.add(o);
    }
    
    public void addCustomer(Customer cust) {
        this.customer = cust;
    }

    /**
     * Method toString
     *
     * @return The return value
     */
    public String toString() {
        String act;
        if(active) {
            act = "Aktiv";
        }
        else {
            act = "Ikke aktiv";
        }
        return "Betalingsaftale: " +paymentAgreement+ " Håndværkerrabat:  " +discount[0]+ " Mængderabat: " +discount[1]+ " Afhentningsrabat: " +discount[2]+ " Balance: " +balance+ " Status: " +act+ "\n";
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
    
    public boolean getStatus() {
    	return active;
    }
}

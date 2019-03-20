package modelLayer;

import java.util.*;
/**
 * @author (Group 6)
 * @version (V.1.0)
 */
public class AccountContainer {
    // instance variables
    private static AccountContainer instance;
    private static ArrayList<Account> accounts;

    /**
     * AccountContainer Constructor
     *
     */
    private AccountContainer() {
        accounts = new ArrayList<>();
    }

    /**
     * Method getInstance
     *
     * @return The return value
     */
    //Get methodes
    public static AccountContainer getInstance() {
        if(instance == null){
            instance = new AccountContainer();
        }
        return instance;
    }

    /**
     * Method getAccount
     *
     * @param id A parameter
     * @return The return value
     */
    public Account getAccount(int id) {
        return accounts.get(id);
    }
    
    public Customer getCustomer(int id) {
        return accounts.get(id).getCustomer();
    }
    
    public Integer getTotalDiscount(int id) {
        return accounts.get(id).getTotalDiscount();
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size(){
        return accounts.size();
    }

    /**
     * Method addAccount
     *
     * @param payment A parameter
     * @param craftD A parameter
     * @param ammountD A parameter
     * @param pickupD A parameter
     * @param balance A parameter
     */
    //Set methods
    public void addAccount(String payment, Integer craftD, Integer ammountD, Integer pickupD, double balance) {
        Account a = new Account(payment, craftD, ammountD, pickupD, balance);
        accounts.add(a);
    }

    /**
     * Method updateAccount
     *
     * @param id A parameter
     * @param paymentAgreement A parameter
     * @param craftmansDiscount A parameter
     * @param amountDiscount A parameter
     * @param pickupDiscount A parameter
     * @param balance A parameter
     */
    public void updateAccount(int id, String paymentAgreement, Integer craftmansDiscount, Integer amountDiscount, Integer pickupDiscount, double balance){
        Account a = getAccount(id);
        a.setFields(paymentAgreement, craftmansDiscount, amountDiscount, pickupDiscount, null, balance);
    }

    /**
     * Method removeAccount
     *
     * @param id A parameter
     */
    //Other methods
    public void removeAccount(int id){
        accounts.remove(id);
    }

    /**
     * Method addOrder
     *
     * @param id A parameter
     * @param o A parameter
     */
    public void addOrder(int id, Order o)
    {
        Account a = getAccount(id);
        a.addOrder(o);
    }
    
    public void addCustomer(int acc, Customer c) {
        Account a = getAccount(acc);
        a.addCustomer(c);
    }
    
    public void toggleActive(int id) {
        accounts.get(id).toggleActive();
    }
    
    public Account[] toArr() {
    	Account[] arr = new Account[size()];
		arr = accounts.toArray(arr);
		return arr;
    }
}

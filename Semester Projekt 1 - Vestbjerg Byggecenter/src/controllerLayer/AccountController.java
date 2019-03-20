package controllerLayer;
import modelLayer.*;

public class AccountController{
    private AccountContainer accCon;
    private OrderController ordCon;
    private CustomerController cusCon;

    /**
     * AccountController Constructor
     *
     */
    public AccountController()
    {
        accCon = AccountContainer.getInstance();
        ordCon = new OrderController();
        cusCon = new CustomerController();
    }

    /**
     * Method getAccount
     *
     * @param id A parameter
     * @return The return value
     */
    public Account getAccount(int id){
        return accCon.getAccount(id);
    }
    
    public Account getState() {
    	return getState();
    }
    
    public Customer getCustomer(int id){
        return accCon.getAccount(id).getCustomer();
    }
    
    public Integer getTotalDiscount(int id) {
        return accCon.getTotalDiscount(id);
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
    public void addAccount(String payment, Integer craftD, Integer ammountD, Integer pickupD, double balance){
        accCon.addAccount(payment, craftD, ammountD, pickupD, balance);
    }

    /**
     * Method removeAccount
     *
     * @param id A parameter
     */
    public void removeAccount(int id){
        accCon.removeAccount(id);
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
        accCon.updateAccount(id, paymentAgreement, craftmansDiscount, amountDiscount, pickupDiscount,  balance);
    }

    /**
     * Method addOrder
     *
     * @param acc A parameter
     * @param order A parameter
     */
    public void addOrder(int acc, int order){
        Order o = ordCon.getOrder(order);
        accCon.addOrder(acc, o);
    }
    
    public void addCustomer(int acc, int cust){
        Customer c = cusCon.getCustomer(cust);
        accCon.addCustomer(acc, c);
    }
    
    public int size() {
    	return accCon.size();
    }
    
    public void toggleActive(int id) {
        accCon.toggleActive(id);
    }
    
    public Account[] toArr() {
    	return accCon.toArr();
    }
}

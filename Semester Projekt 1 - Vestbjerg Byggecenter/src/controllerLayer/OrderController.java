package controllerLayer;
import modelLayer.*;
import java.util.*;

public class OrderController{
    private OrderContainer orCon;
    private OrderLine odrLine;
    /**
     * OrderController Constructor
     *
     */
    public OrderController()
    {
        orCon = OrderContainer.getInstance();
        odrLine = new OrderLine();
    }

    /**
     * Method getOrder
     *
     * @param id A parameter
     * @return The return value
     */
    public Order getOrder(int id){
        return orCon.getOrder(id);
    }

    /**
     * Method addOrder
     *
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public void addOrder(String trackAndTrace, double weight, double coli){
        orCon.addOrder(trackAndTrace, weight, coli);
    }

    /**
     * Method removeOrder
     *
     * @param id A parameter
     */
    public void removeOrder(int id){
        orCon.removeOrder(id);
    }

    /**
     * Method updateOrder
     *
     * @param id A parameter
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public void updateOrder(int id, String trackAndTrace, double weight, double coli){
        orCon.updateOrder(id, trackAndTrace, weight, coli);
    }

    /**
     * Method getLastOrder
     *
     * @return The return value
     */
    public int getLastOrder(){
        return orCon.size();
    }

    public void addProduct(int prod, int qnt){
        odrLine.addProduct(prod, qnt);
    }

    public void addProduct(int prod){
        odrLine.addProduct(prod);
    }

     public void finishOrder(int id, int a){
        odrLine.finishOrder(id,a);
    }
    
    public void finishOrder(int id){
        odrLine.finishOrder(id);
    }
    public void finishOrder(){
        odrLine.finishOrder();
    }
    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id){
        orCon.toggleActive(id);
    }
    
    public Product[] toArr(int id) {
    	return orCon.getOrder(id).toArr();
    }
}

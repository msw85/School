package modelLayer;

import modelLayer.Product;
import java.util.*;
import java.util.Date;
/**
 * @author (Group 6)
 * @version (V.1.0)
 */
public class Order{
    private Integer id;
    private String trackAndTrace;
    private double weight;
    private double coli;
    private Date timePacked;
    private Date timeReceived;
    private Date timeSent;
    private boolean active;
    private LinkedList<Product> products;

    /**
     * Order Constructor
     *
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public Order(String trackAndTrace, double weight, double coli){
        int sizeArr =  OrderContainer.getInstance().size();
        setFields(sizeArr++, trackAndTrace, weight, coli);
        timeReceived = new Date();
        active = true;
        products = new LinkedList<Product>();
    }

    /**
     * Method setFields
     *
     * @param id A parameter
     * @param trackAndTrace A parameter
     * @param weight A parameter
     * @param coli A parameter
     */
    public void setFields(Integer id, String trackAndTrace, double weight, double coli){
        if(this.id == null || id != null)
        {
            this.id = id;
        }
        if(trackAndTrace != null){
            this.trackAndTrace = trackAndTrace;
        }
        if(weight != 0){
            this.weight = weight;
        }
        if(coli != 0){
            this.coli = coli;
        }
        //if(this.timePacked == null || timePacked != null){
          //  this.timePacked = timePacked;
        //}
        //if(this.timeReceived == null || timeReceived != null){
         //   this.timeReceived = timeReceived;
        //}
        //if(this.timeSent == null || timeSent != null){
         //   this.timeSent = timeSent;
        //}
    }

    /**
     * Method toString
     *
     * @return The return value
     */
    public String toString(){
        String act;
        if(active) {
            act = "Aktiv";
        }
        else {
            act = "Ikke aktiv";
        }
        String toReturn =  "ID: " + id + " Track & Trace: " + trackAndTrace + " Vægt: " + weight + " Coli: " + coli+ " Status: " +act+ "\n";
        if(timeReceived != null)
            toReturn = toReturn +  " Tidspunkt modtaget: " + timeReceived.toString();
        if(timeSent != null)
            toReturn = toReturn +   " Tidspunkt sendt: "  + timeSent.toString();
        if(timePacked != null)
            toReturn = toReturn + " Tidspunkt pakket: " + timePacked.toString();
        return toReturn;
    }

    /**
     * Method getTrackAndTrace
     *
     * @return The return value
     */
    public String getTrackAndTrace(){
        return trackAndTrace;
    }

    /**
     * Method getWeight
     *
     * @return The return value
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Method getColi
     *
     * @return The return value
     */
    public double getColi(){
        return coli;
    }
    
    public boolean getState() {
    	return active;
    }

    /**
     * Method getTimePacked
     *
     * @return The return value
     */
    public Date getTimePacked(){
        return timePacked;
    }

    /**
     * Method gettimeReceived
     *
     * @return The return value
     */
    public Date getTimeReceived(){
        return timeReceived;
    }

    /**
     * Method getTimeSent
     *
     * @return The return value
     */
    public Date getTimeSent(){
        return timeSent;
    }

    /**
     * Method getPrice
     *
     * @return The return value
     */
    public double getPrice(){
        double sum = 0d;
        if(!products.isEmpty())
        {
            for(Product p : products){
                sum = sum + p.getPrice();
            }
        }
        return sum;
    }

    /**
     * Method addProduct
     *
     * @param p A parameter
     */
    public void addProduct(Product p){
        products.add(p);
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
    
    public Product[] toArr() {
    	Product[] arr = new Product[products.size()];
    	arr = products.toArray(arr);
    	return arr;
    }
    public String getID() {
    	Integer i = id;
    	return i.toString();	
    }

    public Integer getSize() {
    	if(products == null) {
    		return 0;
    	}
    	else {
    		return products.size();
    	}
    }
}


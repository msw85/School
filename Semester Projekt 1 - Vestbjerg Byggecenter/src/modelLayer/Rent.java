package modelLayer;

import java.util.Date;
/**
 * @author (Group 6)
 * @version (v.1.0)
 */
public class Rent{
    private int id;
    private String serialNumber;
    private String note;
    private double rentPrice;
    private double deposit;
    private Date start;
    private Date end;
    private boolean active;
    private String days;
    private Customer cus;
    private Product p;
    /**
     * Rent Constructor
     *
     * @param serialNumber A parameter
     * @param note A parameter
     * @param rentPrice A parameter
     * @param deposit A parameter
     */
    public Rent(String serialNumber, String note, double rentPrice, double deposit, Date start, Date end, Customer cus, Product p){
        // sizeArr is the size of the ArrayList inside the RentContainer class, to generate the uniqe ID of Rent.
        int sizeArr = RentContainer.getInstance().size();
        this.id = sizeArr++;
        setFields(serialNumber, note, rentPrice, deposit, start, end, cus, p);
        active = true;
    }

    /**
     * Method setFields
     *
     * @param serialNumber A parameter
     * @param note A parameter
     * @param rentPrice A parameter
     * @param deposit A parameter
     * @param id A parameter
     */
    public void setFields(String serialNumber, String note, double rentPrice, double deposit, Date start, Date end, Customer cus, Product p){
        if(serialNumber != null){
            this.serialNumber = serialNumber;
        }
        if(note != null){
            this.note = note;
        }
        if(rentPrice != 0){
            this.rentPrice = rentPrice;
        }
        if(deposit != 0){
            this.deposit = deposit;
        }
        if(start != null){
            this.start = start;
        }
        if(end != null){
            this.end = end;
        }
        if(cus != null)
        	this.cus = cus;
        if(p != null)
        	this.p = p;
    }

    
    public String toString(){
                String act;
        if(active) {
            act = "Aktiv";
        }
        else {
            act = "Ikke aktiv";
        }
        return "ID: " +id + " Serie nummer: " +serialNumber+ " Note: " +note+ 
        " Låne pris: " +rentPrice+ " Depositum: " +deposit+ " Start dato: " +start+ 
        " Slut dato: " +end+ " Status: " +act+ "\n";
    }

    /**
     * Method getID
     *
     * @return The return value
     */
    public Integer getID(){
        return id;
    }
    
    public boolean getState() {
    	return active;
    }
    
    public Double getPrice() {
    	return rentPrice;
    }

    /**
     * Method getSerialNumber
     *
     * @return The return value
     */
    public String getSerialNumber(){
        return serialNumber;
    }

    /**
     * Method getNote
     *
     * @return The return value
     */
    public String getNote(){
        return note;
    }

    /**
     * Method getRentPrice
     *
     * @return The return value
     */
    public double getRentPrice(){
        return rentPrice;
    }

    /**
     * Method getDeposit
     *
     * @return The return value
     */
    public double getDeposit(){
        return deposit;
    }

    /**
     * Method getStartDate
     *
     * @return The return value
     */
    public Date getStartDate(){
        return start;
    }

    /**
     * Method getEndDate
     *
     * @return The return value
     */
    public Date getEndDate(){
        return end;
    }
    public Customer getCustomer() {
    	return cus;
    }
    public Product getProduct() {
    	return p;
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
    
    public void setDays(String days) {
    	this.days = days;
    }
    
    public String getDays() {
    	return days;
    }
}
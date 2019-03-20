package modelLayer;

import java.util.ArrayList;

/**
 * Write a description of class Supplier here.
 *
 * @author (Group 6)
 * @version (v.1.0)
 */
public class Supplier{
    private Integer id;
    private String name;
    private String description;
    private String bankInfo;
    private boolean active;

    /**
     * Supplier Constructor
     *
     * @param name A parameter
     * @param description A parameter
     * @param bankInfo A parameter
     */
    public Supplier(String name, String description, String bankInfo){
        int sizeArr =  SupplierContainer.getInstance().size();
        setFields(sizeArr++, name, description, bankInfo);
        this.active = true;
    }

    /**
     * Method setFields
     *
     * @param id A parameter
     * @param name A parameter
     * @param description A parameter
     * @param bankInfo A parameter
     */
    public void setFields(Integer id, String name, String description, String bankInfo){
        if(this.id == null || id != null)
        {
            this.id = id;
        }
        if(name != null){
            this.name = name;
        }
        if(description != null){
            this.description = description;
        }
        if(bankInfo != null){
            this.bankInfo = bankInfo;
        }
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

    /**
     * Method toString
     *
     * @return The return value
     */
    public String toString() {
    	return id + " " + name;
    }
//    public String toString(){
//        String act;
//        if(active) {
//            act = "Aktiv";
//        }
//        else {
//            act = "Ikke aktiv";
//        }
//
//        return "ID: " + id + " Navn: " + name + " Beskrivelse: " + description + 
//        " Bankinformation: " + bankInfo + " Status: " + act + "\n";
//    }

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
     * Method getDescription
     *
     * @return The return value
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method getBankInfo
     *
     * @return The return value
     */
    public String getBankInfo(){
        return bankInfo;
    }
    
    public boolean getState() {
    	return active;
    }
    
}

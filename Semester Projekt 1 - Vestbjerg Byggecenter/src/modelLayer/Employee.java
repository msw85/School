package modelLayer;

/**
 * Write a description of class Employee here.
 *
 * @author (Group 6)
 * @version (V.1.0)
 */
public class Employee{
    private Integer id;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String phone;
    private String bankInfo;
    private double salery;
    private boolean isLeader;
    private boolean active;

    /**
     * Employee Constructor
     *
     * @param name A parameter
     * @param address A parameter
     * @param zip A parameter
     * @param city A parameter
     * @param phone A parameter
     * @param bankInfo A parameter
     * @param salery A parameter
     * @param isLeader A parameter
     */
    public Employee(String name, String address, String zip, String city, String phone, String bankInfo, double salery, boolean isLeader){
        int sizeArr = EmployeeContainer.getInstance().size();
        setFields(sizeArr++, name, address, zip, city, phone, bankInfo, salery, isLeader);
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
     * @param bankInfo A parameter
     * @param salery A parameter
     * @param isLeader A parameter
     */
    public void setFields(Integer id, String name, String address, String zip, String city, String phone, String bankInfo, double salery, boolean isLeader){
        if(this.id == null || id != null)
        {
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
        if(bankInfo != null){
            this.bankInfo = bankInfo;
        }
        if(salery != 0){
            this.salery = salery;
        }
        this.isLeader = isLeader;

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
        return "ID: " + id + " Navn: " + name + " Adresse: " + address + 
        " Postnummer: " +zip+ " By: " + city + " Tlf: " + phone + 
        " Bank information: " +bankInfo+ " Løn: " +salery+ 
        " Leder stilling: " +isLeader+ " Status: " +act+ "\n";
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
     * Method getBankInfo
     *
     * @return The return value
     */
    public String getBankInfo(){
        return bankInfo;
    }

    /**
     * Method getSalery
     *
     * @return The return value
     */
    public double getSalery(){
        return salery;
    }

    /**
     * Method getIsLeader
     *
     * @return The return value
     */
    public boolean getIsLeader(){
        return isLeader;
    }
    
    public boolean getState() {
    	return active;
    }
    
    public int getID() {
    	return id;
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

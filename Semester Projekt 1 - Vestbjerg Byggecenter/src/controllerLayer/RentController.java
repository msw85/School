package controllerLayer;

import java.util.Date;

import modelLayer.*;

public class RentController {
    private RentContainer rentCon;

    /**
     * RentController Constructor
     *
     */
    public RentController()
    {
        rentCon = RentContainer.getInstance();
    }

    public boolean getState(int id) {
    	return rentCon.getState(id);
    }
    
    /**
     * Method getRent
     *
     * @param id A parameter
     * @return The return value
     */
    public Rent getRent(int id){
        return rentCon.getRent(id);
    }

    /**
     * Method addRent
     *
     * @param serialNumber A parameter
     * @param note A parameter
     * @param rentPrice A parameter
     * @param deposit A parameter
     */
    public void addRent(String serialNumber, String note, double rentPrice, double deposit, Date start, Date end, Customer cus, Product p){
        rentCon.addRent(serialNumber, note, rentPrice, deposit, start, end, cus, p);
    }
    public void addRent(Rent r) {
    	rentCon.addRent(r);
    }

    /**
     * Method removeRent
     *
     * @param id A parameter
     */
    public void removeRent(int id){
        rentCon.removeRent(id);
    }

    /**
     * Method updateRent
     *
     * @param id A parameter
     * @param serialNumber A parameter
     * @param note A parameter
     * @param rentPrice A parameter
     * @param deposit A parameter
     */
    public void updateRent(int id,String serialNumber, String note, double rentPrice, double deposit, Date start, Date end, Customer cus, Product p){
        rentCon.updateRent(id, serialNumber, note, rentPrice, deposit, start, end, cus, p);
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id){
        rentCon.toggleActive(id);
    }
    public Rent[] toArr() {
    	return rentCon.toArr();
    }
}

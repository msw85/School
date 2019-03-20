package modelLayer;

import java.util.*;

public class RentContainer{
    private static RentContainer instance;
    private static ArrayList<Rent> rent; 

    /**
     * RentContainer Constructor
     *
     */
    private RentContainer()
    {
        rent = new ArrayList<Rent>();
    }

    public boolean getState(int id) {
    	return rent.get(id).getState();
    }
    /**
     * Method getInstance
     *
     * @return The return value
     */
    public static RentContainer getInstance(){
        if(instance == null){
            instance = new RentContainer();
        }
        return instance;
    }

    /**
     * Method getRent
     *
     * @param id A parameter
     * @return The return value
     */
    public Rent getRent(int id){
        return rent.get(id);
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
        Rent r = new Rent(serialNumber, note, rentPrice, deposit, start, end, cus, p);
        rent.add(r); 
    }
    public void addRent(Rent r) {
    	rent.add(r);
    }

    /**
     * Method removeRent
     *
     * @param id A parameter
     */
    public void removeRent(int id){
        rent.remove(id);
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
    public void updateRent(int id, String serialNumber, String note, double rentPrice, double deposit, Date start, Date end, Customer cus, Product p){
        Rent o = getRent(id);
        o.setFields(serialNumber, note, rentPrice, deposit, start, end, cus, p);
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size(){
        return rent.size();
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id){
        rent.get(id).toggleActive();
    } 
    public Rent[] toArr() {
    	Rent[] a = new Rent[rent.size()];
    	a = rent.toArray(a);
    	return a;
    }
}

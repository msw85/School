package modelLayer;

import java.util.*;

public class SupplierContainer
{
    private static SupplierContainer instance;
    private static ArrayList<Supplier> suppliers;

    /**
     * SupplierContainer Constructor
     *
     */
    private SupplierContainer()
    {
        suppliers = new ArrayList<Supplier>();
    }

    /**
     * Method getInstance
     *
     * @return The return value
     */
    public static SupplierContainer getInstance(){
        if(instance == null){
            instance = new SupplierContainer();
        }
        return instance;
    }

    /**
     * Method getSupplier
     *
     * @param id A parameter
     * @return The return value
     */
    public Supplier getSupplier(int id){
        return suppliers.get(id);
    }
    
    public Supplier[] getSupplier(String name){
    	LinkedList<Supplier> foundSup = new LinkedList<Supplier>();
    	Supplier sup = null;
    	for(int i = 0; i < size(); i++) {
    		if(suppliers.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
    			foundSup.add(suppliers.get(i));
    		}
    	}
    	
      Supplier[] arr = new Supplier[foundSup.size()];
      arr = foundSup.toArray(arr);
      return arr;
    }

    /**
     * Method createSupplier
     *
     * @param name A parameter
     * @param description A parameter
     * @param bankInfo A parameter
     */
    public void createSupplier(String name, String description, String bankInfo) {
        Supplier s = new Supplier(name, description, bankInfo);
        suppliers.add(s);
    }

    /**
     * Method addSupplier
     *
     * @param sup A parameter
     */
    public void addSupplier(Supplier sup){
        suppliers.add(sup);
    }

    /**
     * Method removeSupplier
     *
     * @param id A parameter
     */
    public void removeSupplier(int id){
        suppliers.remove(id);
    }

    /**
     * Method updateSupplier
     *
     * @param id A parameter
     * @param name A parameter
     * @param description A parameter
     * @param bankInfo A parameter
     */
    public void updateSupplier(int id, String name, String description, String bankInfo){
        Supplier s = getSupplier(id);
        s.setFields(null, name, description, bankInfo);
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id) {
        suppliers.get(id).toggleActive();
    }

    /**
     * Method size
     *
     * @return The return value
     */
    public int size() {
        return suppliers.size();
    }
    
    public boolean getState(int id) {
    	return suppliers.get(id).getState();
    }
    
}

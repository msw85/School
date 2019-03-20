package controllerLayer;

import modelLayer.*;
import java.util.*;

public class SupplierController {
    private SupplierContainer supCon;

    /**
     * SupplierController Constructor
     *
     */
    public SupplierController()
    {
        supCon = SupplierContainer.getInstance();
    }

    /**
     * Method getSupplier
     *
     * @param id A parameter
     * @return The return value
     */
    public Supplier getSupplier(int id){
        return supCon.getSupplier(id);
    }
    
    public Supplier[] getSupplier(String name){
    	return supCon.getSupplier(name);
    }

    /**
     * Method createSupplier
     *
     * @param name A parameter
     * @param description A parameter
     * @param bankInfo A parameter
     */
    public void createSupplier(String name, String description, String bankInfo){
        supCon.createSupplier(name, description, bankInfo);
    }

    /**
     * Method addSupplier
     *
     * @param sup A parameter
     */
    public void addSupplier(Supplier sup){
        supCon.addSupplier(sup);
    }

    /**
     * Method removeSupplier
     *
     * @param id A parameter
     */
    public void removeSupplier(int id){
        supCon.removeSupplier(id);
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
        supCon.updateSupplier(id, name, description, bankInfo);
    }

    /**
     * Method toggleActive
     *
     * @param id A parameter
     */
    public void toggleActive(int id) {
        supCon.toggleActive(id);
    }
    
    public int size() {
    	return supCon.size();
    }
    
}

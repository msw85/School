package controller;

import java.sql.SQLException;
import java.util.List;

import database.SupplierDB;
import database.SupplierDBIF;
import model.Supplier;


public class SupplierController  {

	private SupplierDBIF sdb;
		
	public SupplierController() {	
		try {
			sdb = new SupplierDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Supplier> findAll() throws SQLException {		
		return sdb.findAll();		
	}
	
	public Supplier findById(int id) throws SQLException {
		return sdb.findById(id);
	}
	
	public void createSupplier(Supplier s) throws SQLException {
		sdb.createSupplier(s);
	}
	
	public void deleteSupplier(Supplier s) throws SQLException {
		sdb.deleteSupplier(s);
	}
	
	public void updateSupplier(Supplier s) throws SQLException {
		sdb.updateSupplier(s);
	}
}

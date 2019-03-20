package controller;

import java.sql.SQLException;
import java.util.List;

import database.StorageLineDB;
import database.StorageLineDBIF;
import model.StorageLine;
import model.Storage;

public class StorageLineController {

	private StorageLineDBIF sdb;
	
	public StorageLineController() throws SQLException {
		sdb = new StorageLineDB();
	}
	
	public List<StorageLine> findAll() throws SQLException {		
		return sdb.findAll();
	}
	
	public StorageLine findById(int id) throws SQLException {
		return sdb.findById(id);
	}
	
	public void createStorage(StorageLine s) throws SQLException {
		sdb.createStorageLine(s);
	}
	
	public void deleteStorage(StorageLine s) throws SQLException {
		sdb.deleteStorageLine(s);
	}
	
	public void updateStorage(StorageLine s) throws SQLException {
		sdb.updateStorageLine(s);
	}
	
	public StorageLine createStorageLine(Integer numInStock, Storage stor) {
		StorageLine sl = new StorageLine(numInStock, stor);
		return sl;	
	}
	
	public void addToNumInStock(StorageLine sl, int quantityToAdd) {
		int result = sl.getNumInStock() + quantityToAdd;
		sl.setNumInStock(result);
	}
	
	public boolean subtractNumInStock(StorageLine sl, int quantityToSub) {
		boolean toReturn = false;
		int result = sl.getNumInStock() - quantityToSub;
		if(result >= 0) {
		sl.setNumInStock(result);
			toReturn = true;
		}
		return toReturn;
	}
}

package controller;

import java.sql.SQLException;
import java.util.List;

import database.StorageDB;
import database.StorageDBIF;
import model.Storage;
import model.StorageLine;

public class StorageController {

	private StorageDBIF sdb;
	
	public StorageController() throws SQLException {
		sdb = new StorageDB();
	}
	
	public List<Storage> findAll() throws SQLException {		
		return sdb.findAll();
	}
	
	public Storage findById(int id) throws SQLException {
		return sdb.findById(id);
	}
	
	public void createStorage(Storage s) throws SQLException {
		sdb.createStorage(s);
	}
	
	public void deleteStorage(Storage s) throws SQLException {
		sdb.deleteStorage(s);
	}
	
	public void updateStorage(Storage s) throws SQLException {
		sdb.updateStorage(s);
	}

	
}

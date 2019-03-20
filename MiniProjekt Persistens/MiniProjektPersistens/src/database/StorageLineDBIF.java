package database;

import java.sql.SQLException;
import java.util.List;

import model.StorageLine;

public interface StorageLineDBIF {
	List<StorageLine> findAll() throws SQLException;
	StorageLine findById(int id) throws SQLException;
	void createStorageLine(StorageLine s) throws SQLException;
	void deleteStorageLine(StorageLine s) throws SQLException;
	void updateStorageLine(StorageLine s) throws SQLException;

}

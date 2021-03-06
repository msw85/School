package database;

import java.sql.SQLException;
import java.util.List;

import model.Storage;

public interface StorageDBIF {
	List<Storage> findAll() throws SQLException;
	Storage findById(int id) throws SQLException;
	void createStorage(Storage s) throws SQLException;
	void deleteStorage(Storage s) throws SQLException;
	void updateStorage(Storage s) throws SQLException;

}

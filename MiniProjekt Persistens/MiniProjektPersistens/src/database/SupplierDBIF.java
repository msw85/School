package database;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface SupplierDBIF {
	List<Supplier> findAll() throws SQLException;
	Supplier findById(int id) throws SQLException;
	void createSupplier(Supplier s) throws SQLException;
	void deleteSupplier(Supplier s) throws SQLException;
	void updateSupplier(Supplier s) throws SQLException;

}

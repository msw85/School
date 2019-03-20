package database;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface ProductDBIF {
	List<Product> findAll() throws SQLException;
	Product findById(int id) throws SQLException;
	void createProduct(Product p) throws SQLException;
	void deleteProduct(Product p) throws SQLException;
	void updateProduct(Product p) throws SQLException;

}

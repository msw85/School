package database;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface SaleOrderDBIF {
	List<SaleOrder> findAll() throws SQLException;
	SaleOrder findById(int id) throws SQLException;
	void createOrder(SaleOrder s) throws SQLException;
	void deleteOrder(SaleOrder s) throws SQLException;
	void updateOrder(SaleOrder s) throws SQLException;

}

package database;

import java.sql.SQLException;
import java.util.List;

import model.Discount;
import model.Order;
import model.OrderLine;

/**
 * @author Gruppe 6, DMAA0917
 *
 */

public interface OrderDBIF {

	/**
	 * The below methods handles order
	 * 
	 * @throws SQLException
	 */
	List<Order> findAll() throws SQLException;
	Order findById(int id) throws SQLException;
	void createOrder(Order o) throws SQLException;
	void deleteOrder(Order o) throws SQLException;	
	void updateOrder(Order o) throws SQLException;
	
	/**
	 * The below method handles temp order
	 * 
	 * @throws SQLException
	 */
	Order createTempOrder(Order o) throws SQLException;
	
	/**
	 * The below methods handles orderline
	 * 
	 * @throws SQLException
	 */
	List<OrderLine> findOrderLinesByOrderId(int oId) throws SQLException;
	OrderLine createOrderLine(OrderLine orderline, Order order) throws SQLException;
	void deleteOrderLine(int id) throws SQLException;

	/**
	 * The below methods handles discount
	 * 
	 * @throws SQLException
	 */
	List<Discount> findAllDiscounts() throws SQLException;
	Discount findDiscountOnId(int id) throws SQLException;
	void createDiscount(String type, int percentage) throws SQLException;
	void deleteDiscount(int id) throws SQLException;
	void updateDiscount(Discount disc) throws SQLException;
}

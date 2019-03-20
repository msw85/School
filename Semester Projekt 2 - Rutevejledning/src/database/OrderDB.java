package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import database.FoodDB;
import database.PersonDB;
import model.Customer;
import model.Discount;
import model.Drink;
import model.Employee;
import model.FastFood;
import model.Food;
import model.Pizza;
import model.Order;
import model.OrderLine;

/**
 * @author Gruppe 6, DMAA0917
 *
 */

public class OrderDB implements OrderDBIF{

	private FoodDB foodDB;
	private PersonDB personDB;

	/**
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for Order
	 *  
	 */
	private static final String FIND_ALL_Q = 			"SELECT o.id, o.orderDate, o.dueTime, o.toDeliver, o.isDelivered, o.isReady, o.totalPrice, o.pId FROM orders o";
	private static final String FIND_ORDR_ON_ID_Q = 	"SELECT o.id, o.orderDate, o.dueTime, o.toDeliver, o.isDelivered, o.isReady, o.totalPrice, o.pId FROM orders o WHERE o.Id = ?";
	private static final String CREATE_ORDR_Q = 		"INSERT INTO orders(orderDate, dueTime, toDeliver, isDelivered, isReady, totalPrice, pId) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_ORDR_Q =			"UPDATE orders SET orderDate = ?,  dueTime = ?,  toDeliver = ?,  isDelivered = ?,  isReady = ?,  totalPrice = ?,  pId = ? WHERE id = ?";
	private static final String DELETE_ORDR_ON_ID_Q = 	"DELETE FROM orders WHERE id = ?";

	/**	 
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for OrderLine 
	 * 
	 */
	private static final String FIND_ORDERLINES_BY_ORDER_ID_Q = "SELECT id, fId, quantity, price, oId FROM orderlines WHERE oId = ?";	
	private static final String CREATE_ORDRL_Q =		"INSERT INTO orderLines(fId, quantity, price, oId) VALUES(?,?,?,?)";
	private static final String UPDATE_ORDL_Q = 		"UPDATE orderLines SET  fId = ?,  quantity = ?,  price = ? WHERE id = ? ";
	private static final String DELETE_ORDL_ON_OID_Q = 	"DELETE FROM orderLines WHERE oId = ?";
	private static final String DELETE_ORDL_ON_ID_Q =   "DELETE FROM orderLines WHERE Id = ?";

	/**
	 * Strings of SQL scripts to get ready for PreparedStatement
	 * Below strings are for Discount
	 * 
	 */
	private static final String FIND_ALL_DISCOUNTS_Q = 	"SELECT id, type, percentage FROM discounts";
	private static final String FIND_DISC_ON_ID_Q = 	"SELECT id, type, percentage FROM discounts WHERE id = ?";
	private static final String CREATE_DISC_Q = 		"INSERT INTO discounts(type, percentage) VALUES(?,?)";
	private static final String UPDATE_DISC_Q =			"UPDATE discounts SET type = ?, percentage = ? where id = ?";
	private static final String DELETE_DISC_ON_ID_Q = 	"DELETE FROM discounts WHERE id = ?";

	/**
	 * PreparedStatements for Order
	 */
	private PreparedStatement findAll;
	private PreparedStatement findOrderOnId;
	private PreparedStatement createOrder;
	private PreparedStatement updateOrder;
	private PreparedStatement deleteOrder;	

	/**
	 * PreparedStatements for OrderLine
	 */
	private PreparedStatement findOrderLinesByOrderId;
	private PreparedStatement createOrderLine;
	private PreparedStatement updateOrderLine;
	private PreparedStatement deleteOrderLines;
	private PreparedStatement deleteOrderLine;

	/**
	 * PreparedStatements for Discount
	 */
	private PreparedStatement findAllDiscounts;
	private PreparedStatement findDiscountOnId;
	private PreparedStatement createDiscount;
	private PreparedStatement updateDiscount;
	private PreparedStatement deleteDiscount;

	public OrderDB() throws SQLException {
		foodDB = new FoodDB();
		personDB = new PersonDB();
		// Instantiate prepared statements
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findOrderOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_ORDR_ON_ID_Q);
		createOrder = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDR_Q, Statement.RETURN_GENERATED_KEYS);
		updateOrder = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDR_Q);
		deleteOrder = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDR_ON_ID_Q);

		findOrderLinesByOrderId = DBConnection.getInstance().getConnection().prepareStatement(FIND_ORDERLINES_BY_ORDER_ID_Q);
		createOrderLine = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDRL_Q, Statement.RETURN_GENERATED_KEYS);
		updateOrderLine = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDL_Q);
		deleteOrderLines = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDL_ON_OID_Q);
		deleteOrderLine = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDL_ON_ID_Q);

		findAllDiscounts = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_DISCOUNTS_Q);
		findDiscountOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_DISC_ON_ID_Q);
		createDiscount = DBConnection.getInstance().getConnection().prepareStatement(CREATE_DISC_Q);
		updateDiscount = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_DISC_Q);
		deleteDiscount = DBConnection.getInstance().getConnection().prepareStatement(DELETE_DISC_ON_ID_Q);
	}


	/* 
	 * This method finds all persisted orders
	 * returns a list of orders
	 */
	@Override
	public List<Order> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Order> allOrder = buildObjects(rs);
		return allOrder;
	}

	/* 
	 * This method finds a persisted order on id
	 * returns specific Order object
	 */
	@Override
	public Order findById(int id) throws SQLException {
		Order order = null;
		ResultSet rs;
		findOrderOnId.setInt(1, id);
		rs = findOrderOnId.executeQuery();
		rs.next();
		order = buildObject(rs);
		return order;
	}
	
	/* 
	 * This method takes the supplied Order object and persists the order and orderline(s)
	 */
	@Override
	public void createOrder(Order o) throws SQLException {
		dbUtil.prepareTransaction();
		createOrder.setDate(1, o.getOrderDate());
		createOrder.setDate(2, o.getDueTime());
		createOrder.setBoolean(3, o.getToDeliver());
		createOrder.setBoolean(4, o.getIsDelivered());
		createOrder.setBoolean(5, o.getIsReady());
		createOrder.setInt(6, (int) (o.getTotalPrice()*100));
		createOrder.setInt(7, o.getPers().getId());
		int affectedRows = createOrder.executeUpdate();
		dbUtil.commitTransaction(false);
		if (affectedRows == 0) {
			throw new SQLException("Creating order failed, no rows affected.");
		}
		try (ResultSet generatedKeys = createOrder.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				o.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating order failed, no ID obtained.");
			}
		}
		for (int i = 0; i < o.getOrderL().size(); i++) {
			createOrderLine.setInt(1, o.getOrderL().get(i).getFood().getId());
			createOrderLine.setInt(2, o.getOrderL().get(i).getQuantity());
			createOrderLine.setInt(3, (int) (o.getOrderL().get(i).getPrice()*100));
			createOrderLine.setInt(4, o.getId());
			affectedRows = createOrderLine.executeUpdate();
			dbUtil.commitTransaction(true);
			if (affectedRows == 0) {
				throw new SQLException("Creating orderLine failed, no rows affected.");
			}
			try (ResultSet generatedKeys = createOrderLine.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					o.getOrderL().get(i).setId(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating orderLine failed, no ID obtained.");
				}
			}     
		}   
	}
	
	/* 
     * This method takes the supplied Order object and persists the order
	 */
	public Order createTempOrder(Order o) throws SQLException {
		dbUtil.prepareTransaction();
		createOrder.setDate(1, o.getOrderDate());
		createOrder.setDate(2, o.getDueTime());
		createOrder.setBoolean(3, o.getToDeliver());
		createOrder.setBoolean(4, o.getIsDelivered());
		createOrder.setBoolean(5, o.getIsReady());
		createOrder.setInt(6, (int) (o.getTotalPrice()*100));
		createOrder.setInt(7, o.getPers().getId());
		int affectedRows = createOrder.executeUpdate();
		dbUtil.commitTransaction(true);
		if (affectedRows == 0) {
			throw new SQLException("Creating order failed, no rows affected.");
		}
		try (ResultSet generatedKeys = createOrder.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				o.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating order failed, no ID obtained.");
			}
		}
		return o;
	}
	
	/* 
	 * This method takes the supplied Order object and updates the persisted order and orderline(s)
	 */
	@Override
	public void updateOrder(Order o) throws SQLException {
		dbUtil.prepareTransaction();
		updateOrder.setDate(1, o.getOrderDate());
		updateOrder.setDate(2, o.getDueTime());
		updateOrder.setBoolean(3, o.getToDeliver());
		updateOrder.setBoolean(4, o.getIsDelivered());
		updateOrder.setBoolean(5, o.getIsReady());
		updateOrder.setInt(6, (int) (o.getTotalPrice()*100));
		updateOrder.setInt(7, o.getPers().getId());
		updateOrder.setInt(8, o.getId());
		updateOrder.executeUpdate();
		dbUtil.commitTransaction(false);
		for (int i = 0; i < o.getOrderL().size(); i++) {
			if(!o.getOrderL().isEmpty()) {
				updateOrderLine.setInt(1, o.getOrderL().get(i).getFood().getId());
				updateOrderLine.setInt(2, o.getOrderL().get(i).getQuantity());
				updateOrderLine.setInt(3, (int) (o.getOrderL().get(i).getPrice()*100));
				updateOrderLine.setInt(4, o.getOrderL().get(i).getId());
				updateOrderLine.executeUpdate();
			}	
		}
		dbUtil.commitTransaction(true);
	}
	
	/* 
	 * This method takes the supplied Order object and deletes the persisted orderlines from the order,
	 * and deletes the persisted order
	 */
	@Override
	public void deleteOrder(Order o) throws SQLException {
		dbUtil.prepareTransaction();
		if(o != null) {
			if(!o.getOrderL().isEmpty()) {
				for (int i = 0; i < o.getOrderL().size(); i++) {
					if (o.getOrderL().get(i).getId() != null) {
						deleteOrderLines.setInt(1, o.getId());
						deleteOrderLines.execute();
						dbUtil.commitTransaction(false);
					}
				}
			}
			deleteOrder.setInt(1, o.getId());
			deleteOrder.execute();
			dbUtil.commitTransaction(true);
		}
	}
	
	/**
	 * This method takes the persisted data and creates an order object.
	 * 
	 * @param rs the persisted data
	 * @return Order object
	 * @throws SQLException
	 */
	private Order buildObject(ResultSet rs) throws SQLException {	
		List<OrderLine> orderLines = findOrderLinesByOrderId(rs.getInt("id"));
		Order order;
		Customer cust = (Customer) personDB.findCustomerById(rs.getInt("pId"));

		if(cust == null) {
			Employee emp = (Employee) personDB.findEmployeeById(rs.getInt("pId"));	
			order = new Order(rs.getInt("id"),rs.getDate("orderDate"), rs.getDate("dueTime"), rs.getBoolean("toDeliver"), rs.getBoolean("isDelivered"), rs.getBoolean("isReady"), (double) (rs.getInt("totalPrice")/100), orderLines, emp);
		}else {
			order = new Order(rs.getInt("id"),rs.getDate("orderDate"), rs.getDate("dueTime"), rs.getBoolean("toDeliver"), rs.getBoolean("isDelivered"), rs.getBoolean("isReady"), (double) (rs.getInt("totalPrice")/100), orderLines, cust);
		}
		return order;
	}

	/**
	 * This method takes the persisted data and runs buildObject method, then adds the object to a list
	 * 
	 * @param rs the persisted data
	 * @return List of Order objects
	 * @throws SQLException
	 */
	private List<Order> buildObjects(ResultSet rs) throws SQLException {
		List<Order> result = new LinkedList<>();
		while (rs.next()) {
			result.add(buildObject(rs));
		}
		return result;
	}

	/* 
	 * This method finds persisted orderlines on order id
	 * returns list of orderlines 
	 */
	@Override
	public List<OrderLine> findOrderLinesByOrderId(int oId) throws SQLException{
		ResultSet rs;
		findOrderLinesByOrderId.setInt(1, oId);
		rs = findOrderLinesByOrderId.executeQuery();

		return buildOrderLineObjects(rs);
	}

	/* 
	 * This method takes the supplied Order and OrderLine object and persists the orderline(s)
	 */
	public OrderLine createOrderLine(OrderLine orderline, Order order) throws SQLException {
		dbUtil.prepareTransaction();
		createOrderLine.setInt(1, orderline.getFood().getId());
		createOrderLine.setInt(2, orderline.getQuantity());
		createOrderLine.setInt(3, (int) (orderline.getPrice()*100));
		createOrderLine.setInt(4, order.getId());
		int affectedRows = createOrderLine.executeUpdate();
		dbUtil.commitTransaction(true);
		if (affectedRows == 0) {
			throw new SQLException("Creating orderLine failed, no rows affected.");
		}
		try (ResultSet generatedKeys = createOrderLine.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				orderline.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating orderLine failed, no ID obtained.");
			}
		}
		return orderline;
	}

	/* 
	 * This method takes the supplied id and deletes the persisted orderline
	 */
	@Override
	public void deleteOrderLine(int id) throws SQLException{
		dbUtil.prepareTransaction();
		deleteOrderLine.setInt(1, id);
		deleteOrderLine.execute();
		dbUtil.commitTransaction(true);
	}
	
	/**
	 * This method takes the persisted data and creates an OrderLine object.
	 * 
	 * @param rs the persisted data
	 * @return OrderLine object
	 * @throws SQLException
	 */
	private OrderLine buildOrderLineObject(ResultSet rs) throws SQLException {	
		int foodid = rs.getInt("fId");
		Food f = (Food) foodDB.findFoodById(foodid);
		if( f.getType().equals("pizza")) {
			f = (Pizza) foodDB.findPizzaById(f.getId());
			}
		if(f.getType().equals("fast food")) {
			f = (FastFood) f;
		}
		if(f.getType().equals("drink")) {
			f = (Drink)  foodDB.findDrinkOnId(f.getId());
		}
		OrderLine ol = new OrderLine(rs.getInt("id"), (double) (rs.getInt("price")/100) ,rs.getInt("quantity"), f);		
		return ol;
	}

	/**
	 * This method takes the persisted data and runs buildOrderLineObject method, then adds the object to a list
	 * 
	 * @param rs the persisted data
	 * @return List of OrderLine objects
	 * @throws SQLException
	 */
	private List<OrderLine> buildOrderLineObjects(ResultSet rs) throws SQLException {
		List<OrderLine> result = new LinkedList<>();
		while (rs.next()) {
			result.add(buildOrderLineObject(rs));
		}
		return result;
	}
	
	/* 
	 * This method finds all persisted discounts
	 * returns a list of discounts
	 */
	public List<Discount> findAllDiscounts() throws SQLException{
		Discount discount = null;
		List<Discount> allDiscounts = new LinkedList<Discount>();
		ResultSet rs = findAllDiscounts.executeQuery();
		while(rs.next()) {
			discount = new Discount(rs.getInt("id"), rs.getString("type"), rs.getInt("percentage"));
			allDiscounts.add(discount);
		} 
		return allDiscounts;	
	}

	/* 
	 * This method finds a specific persisted discount from the supplied id
	 * returns Discount object
	 */
	public Discount findDiscountOnId(int id) throws SQLException {
		Discount discount = null;
		findDiscountOnId.setInt(1, id);
		ResultSet rs = findDiscountOnId.executeQuery();
		rs.next();
		if(rs.getInt("id") == id) {
			discount = new Discount(rs.getInt("id"), rs.getString("type"), rs.getInt("percentage"));
		}

		return discount;
	}

	/* 
	 * This method takes the supplied type and percentage to create discount
	 */
	public void createDiscount(String type, int percentage) throws SQLException {
		createDiscount.setString(1, type);
		createDiscount.setInt(2, percentage);
		createDiscount.executeUpdate();
	}

	/*
	 * This method takes the supplied Discount object and updates the persisted discount
	 */
	public void updateDiscount(Discount disc) throws SQLException {
		updateDiscount.setString(1, disc.getType());
		updateDiscount.setInt(2, disc.getPercentage());
		updateDiscount.setInt(3, disc.getId());
		updateDiscount.executeUpdate();
	}

	/* 
	 * This method takes the supplied id and deletes the persisted discount with that id.
	 */
	public void deleteDiscount(int id) throws SQLException {
		deleteDiscount.setInt(1, id);
		deleteDiscount.execute();
	}
}


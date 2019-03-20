package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.OrderDB;
import database.OrderDBIF;
import model.Customer;
import model.Discount;
import model.Food;
import model.Order;
import model.OrderLine;
import model.Person;
import model.Pizza;

/**
 * @author Gruppe 6, DMAA0917
 *
 */

public class OrderController{
	//Initiate order database interface
	private OrderDBIF odb;
	private Date date;
	private Date dueTime;
	private PersonController pCtr;
	private FoodController fCtr;
	private Person person;
	private Food food;
	private Double totalPrice;
	private List<OrderLine> orderLineList;
	private static Order currentOrder;
	java.util.Date timeStamp; 
	private long time;

	/** Instantiate database, controllers and list
	 * 
	 * @throws SQLException
	 */
	public OrderController() throws SQLException {
		odb = new OrderDB();
		fCtr = new FoodController();
		pCtr = new PersonController();
		orderLineList = new LinkedList<OrderLine>();
	}

	/**
	 * This method calls the findAll() method in the DB class.
	 * Then returns a list of all Orders.
	 * 
	 * @return List<Order>
	 * @throws SQLException
	 */
	public List<Order> findAll() throws SQLException{
		//Execute method in order database to find all order objects from database
		return odb.findAll();
	}

	/**
	 * This method takes the supplied id and calls the findByID() method in the DB class.
	 * Then returns the desired Order.
	 * 
	 * @param id order id
	 * @return order object
	 * @throws SQLException
	 */
	public Order findByID(int id) throws SQLException{
		//Execute method in order database to find a order object from database
		return odb.findById(id);
	}
	
	/**
	 * This method takes the supplied Order object and calls the createOrder() method in the DB class.
	 * Then persists order.
	 * 
	 * @param order object
	 * @throws SQLException
	 */
	public void createOrder(Order order) throws SQLException{
		odb.createOrder(order);
	}
	
	/**
	 * This method takes the supplied Order object and calls the updateOrder() method in the DB class.
	 * 
	 * @param Order object.
	 * @throws SQLException
	 * Update Order object in database
	 */
	public void updateOrder(Order o) throws SQLException{
		//Updates the persisted Order. 
		odb.updateOrder(o);
	}
	
	/**
	 * This method takes the supplied Order object and calls the deleteOrder() method in the DB class.
	 * Which removes the persisted Order.
	 * 
	 * @param Order object
	 * @throws SQLException
	 */
	public void deleteOrder(Order o) throws SQLException{
		//Execute method in order database to remove a order object from the database
		odb.deleteOrder(o);
	}
	
	/**
	 * This method creates a temporary Order, this is made so an OrderLine can be added to an order.
	 * 
	 * @param pId person id.
	 * @return persistet order
	 * @throws SQLException
	 */
	public Order createTempOrder(int pId) throws SQLException{
		//Set date to current time and dueTime current time + 30min
		timeStamp = new java.util.Date();
		time = timeStamp.getTime();
		dueTime = new Date(time + 1800000);	
		date =  new java.sql.Date(time);	
		//Finds customer or employee on pId.
		person = pCtr.findCustomerById(pId);
		if(person == null) {
			person = pCtr.findEmployeeById(pId);
		}
		//Creats a new Order object.
		Order order = new Order(date, dueTime, false, false, false, 0d, person);
		//Persists Order.
		currentOrder = odb.createTempOrder(order);
		
		return currentOrder;
	}
	
	/**
	 * This method calls the deleteOrder() method in the DB class.
	 * The sets and clear is done to make ready for a new tempOrder.
	 * 
	 * @throws SQLException
	 */
	public void deleteTempOrder() throws SQLException {
		//removes the persisted currentOrder.
		odb.deleteOrder(currentOrder);
		//Sets currentOrder to null.
		currentOrder = null;
		//Sets totalPrice to 0d.
		totalPrice = 0d;
		//Clears the orderLineList.
		orderLineList.clear();

	}
	
	/**
	 * This method creats an orderline and adds it to the currentOrder.
	 *  
	 * @param quantity the quantity of food on the orderline.
	 * @param price the price of the orderline.
	 * @param fId food id.
	 * @return OrderLine object
	 * @throws SQLException
	 */
	public OrderLine createOrderLine(int quantity, double price, int fId) throws SQLException {

		//Finds fastfood or pizza on fId.
		food = fCtr.findFastFoodByID(fId);
		if(food == null) {
			food = fCtr.findPizzaByID(fId);
		}			
		//Creates an OrderLine object with quantity, price and food.
		OrderLine tempOrderLine = new OrderLine(price, quantity, food);
		//Adds the OrderLine object to orderLineList
		orderLineList.add(tempOrderLine);
		//Persist the OrderLine with the currentOrder
		OrderLine ol = odb.createOrderLine(tempOrderLine, currentOrder); 
		//ol is added to the currentOrder orderline list.
		currentOrder.addOrderL(ol);
		return ol;
	}
	
	/**
	 * This method takes the supplied id and calls the deleteOrderLine() method in the DB class.
	 * 
	 * @param id orderline id.
	 * @throws SQLException
	 */
	public void deleteOrderLine(int id) throws SQLException{
		//Removes the persisted OrderLine
		odb.deleteOrderLine(id);
		//Checks the orderLineList if the OrderLine is in the list and removes it from the list.
		for (int i = 0; i < orderLineList.size(); i++) {
			if(orderLineList.get(i).getId() == id) {
				orderLineList.remove(i);
			}
		}
	}
	
	/**
	 * This method calls the findAllDiscounts() method in the DB class.
	 * 
	 * @return list of all discounts
	 * @throws SQLException
	 */
	public List<Discount> findAllDiscounts() throws SQLException {
		return odb.findAllDiscounts();
	}
	
	/**
	 * This method takes the supplied Discount object and calls the updateDiscount() method in the DB class.
	 * 
	 * @param disc
	 * @throws SQLException
	 */
	public void updateDiscount(Discount disc) throws SQLException {
		//Updates the persisted discount. 
		odb.updateDiscount(disc);
	}
	
	/**
	 * This method calculates the total price with discounts for an orderline. 
	 * returns calculated price for the OrderLine. 
	 * 
	 * @param quantity the quantity of food on the orderline.
	 * @param price the price of the orderline.
	 * @param fId food id.
	 * @return calculated price.
	 * @throws SQLException
	 */
	public Double calcPrice(int quantity, double price, int fId) throws SQLException{
		Double calc = 0d;
		Double discount = 0d;
		//Finds all persisted discounts.
		List<Discount> allDiscounts = odb.findAllDiscounts();
		//Finds fastfood or pizza on fId.
		food = fCtr.findFastFoodByID(fId);
		if(food == null) {
			food = fCtr.findPizzaByID(fId);
		}
		//Calculates total price of the OrderLine (quantity * price).
		calc = (quantity * price);
		//Checks if pizza is lunchoffer, if so take discount and make calculation.
		if(food.getType().equalsIgnoreCase("pizza")) {
			Pizza pizza = fCtr.findPizzaByID(food.getId());

			if(pizza.isLunchOffer()) {
				discount = (double) (allDiscounts.get(2).getPercentage() / 100d);
				calc -= calc * discount;
			}
		}
		//Checks if person is valued customer, if so takes discount and makes calculation.
		if(person.getType().equalsIgnoreCase("customer")) {
			Customer customer = (Customer) pCtr.findCustomerById(person.getId());

			if(customer.getIsValuedCust() ) {
				discount = (double) (allDiscounts.get(1).getPercentage() / 100d);
				calc -= calc * discount;
			}
		}
		//Checks if person is an employee, if so takes discount and makes calculation.
		else if(person.getType().equalsIgnoreCase("employee")) {
			discount = (double) (allDiscounts.get(0).getPercentage() / 100d);
			calc -= calc * discount;
		}
		return calc;
	}

	/**
	 * Calculates total price, from all orderlines in orderLineList.
	 * 
	 * @return total price.
	 * @throws SQLException
	 */
	public Double discountPrice() throws SQLException {
		Double totalPrice = 0d;

		for (int i = 0; i < orderLineList.size(); i++) {
			totalPrice = totalPrice + orderLineList.get(i).getPrice();		
		}
		return totalPrice;
	}

	/**
	 * Then calls the updateOrder() method in the DB class, with the currentOrder. 
	 * 
	 * @param toDeliver is if the order is to deliver or not.
	 * @param totalPrice total price for the order.
	 * @throws SQLException
	 */
	public void makeSale(boolean toDeliver, Double totalPrice) throws SQLException {
		//Calls the discountPrice method and sets totalPrice.
		totalPrice = discountPrice();
		//Takes currentOrder and sets ToDeliver and TotalPrice.
		currentOrder.setToDeliver(toDeliver);
		currentOrder.setTotalPrice(totalPrice);
		
		odb.updateOrder(currentOrder);
		//Clears orderLineList and current to null.
		//it is to make ready for a new sale.
		orderLineList.clear();
		currentOrder = null;
	}

	/**
	 * @return list of orderlines.
	 */
	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderController [totalPrice=" + totalPrice + "]";
	}

}

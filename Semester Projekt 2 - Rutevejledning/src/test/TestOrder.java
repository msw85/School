package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.FoodController;
import controller.OrderController;
import controller.PersonController;
import model.Customer;
import model.FastFood;
import model.Food;
import model.Drink;
import model.Order;
import model.Person;
import model.Pizza;
import model.OrderLine;
import java.sql.Date;

class TestOrder {
	
	OrderController orderCtr;
	OrderLine orderLine;
	FoodController foodCtr;
	Order order;
	Pizza food;
	Date date;
	Date dueTime;
	Customer hans;
	PersonController perCtr;
	java.util.Date timeStamp; 
	long time;
	List<OrderLine> orderLines;
	
	@BeforeEach
	void setUp() throws Exception{
		orderCtr = new OrderController();
		perCtr = new PersonController();
		foodCtr = new FoodController();
		food = new Pizza("HotDogPizza", 191, 190, "pizza", "En hoddog", "Stor", false);
		foodCtr.createFood(food);
		orderLine = new OrderLine(10d, 1, food);
		orderLines = new LinkedList<>();
		orderLines.add(orderLine);	
		timeStamp = new java.util.Date();
		time = timeStamp.getTime();
		date = new Date(time + 1800000);		
		dueTime =  new java.sql.Date(time);
		hans = new Customer("Hans", "Vestervænget 5", "9000", "Aalborg", "99887766", "hans@vejmail.dk", "Customer", true);
		perCtr.createCustomer(hans);
		order = new Order(4, date, dueTime, true, false, true, 1000d, orderLines, (Person)hans);
		orderCtr.createOrder(order);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		orderCtr.deleteOrderLine(order.getId());
		orderCtr.deleteOrder(order);
		foodCtr.deleteFood(food);
		perCtr.deletePerson(hans);
		food = null;
		hans = null;
		order = null;
		orderCtr =null;
		perCtr = null;
		foodCtr = null;
		orderLine = null;
		date = null;
		dueTime = null;
		orderLines = null;
	}
	
	@Test
	void testCreateOrder() throws SQLException{
		Order newOrder = new Order(1, date, dueTime, true, false, true, 1000d, orderLines, (Person)hans);
		assertNotEquals(newOrder.getId(), order.getId());
		assertEquals(newOrder.getOrderDate(), order.getOrderDate());
		assertEquals(newOrder.getDueTime(), order.getDueTime());
		assertEquals(newOrder.getToDeliver(), order.getToDeliver());
		assertEquals(newOrder.getIsDelivered(), order.getIsDelivered());
		assertEquals(newOrder.getIsReady(), order.getIsReady());
		assertEquals(newOrder.getTotalPrice(), order.getTotalPrice());
		assertEquals(newOrder.getOrderL().toString(), order.getOrderL().toString());
		assertEquals(newOrder.getPers().toString(), order.getPers().toString());
	}
	
	@Test
	void testInsertAndGetOrders() throws SQLException{
		List<Order> orderList = orderCtr.findAll();
		Order newOrder = orderList.get(orderList.size()-1);
		assertEquals(newOrder.toString(), order.toString());
	}
	
	@Test
	void testUpdateOrder() throws SQLException{
		order.setIsDelivered(true);
		orderCtr.updateOrder(order);
		Order newOrder = orderCtr.findByID(order.getId());
		assertTrue(newOrder.getIsDelivered());
	}
	
	@Test
	void testDeleteOrder() throws SQLException{
		int sizeBefore = orderCtr.findAll().size();
		orderCtr.deleteOrder(order);
		int sizeAfter = orderCtr.findAll().size();
		assertEquals(sizeBefore - 1, sizeAfter);
	}

}

package controller;
import java.sql.SQLException;
import java.util.List;

import database.FoodDB;
import database.FoodDBIF;
import model.Brand;
import model.Drink;
import model.FastFood;
import model.Food;
import model.Order;
import model.OrderLine;
import model.Pizza;

/**
 * @author Gruppe 6, DMAA0917
 *
 */

public class FoodController {
	//Initiate food database interface
	private FoodDBIF fdb;
	private OrderController orderCtr;

	/**
	 * @throws SQLException
	 */
	public FoodController() throws SQLException {
		//Instantiate database
		fdb = new FoodDB();
	}

	/**
	 * This method calls the findFoodById() method in the DB class.
	 * Then returns a food object with the supplied id.
	 * 
	 * @param id food id
	 * @return Food object
	 * @throws SQLException
	 */
	public Food findFoodById(int id) throws SQLException{
		return fdb.findFoodById(id);
	}

	/**
	 * This method calls the findOnMenu() method in the DB class.
	 * Then returns a food object with the supplied menuNumber.
	 * 
	 * @param menu menu number
	 * @return Food object
	 * @throws SQLException
	 */
	public Food findOnMenuNo(int menu) throws SQLException {
		return fdb.findOnMenu(menu);	
	}

	/**
	 * This method calls one of tree methods createPizza, createFastfood og createDrink in the DB class.
	 * Then creats either a pizza, fast food or drink, from the supplied food object.
	 * 
	 * @param f Food object
	 * @throws SQLException
	 */
	public void createFood(Food f) throws SQLException{
		//Execute method in food database create a food object in the database
		if(f.getType().equals("pizza")) {
			fdb.createPizza((Pizza) f);
		}
		if(f.getType().equals("fast food")) {
			fdb.createFastFood((FastFood) f);
		}
		if(f.getType().equals("drink")) {
			fdb.createDrink((Drink) f);
		}		
	}

	/**
	 * This method calls the deleteFood() method in the DB class.
	 * Then deletes the persisted food with the supplied Food object.
	 * 
	 * @param f Food object
	 * @throws SQLException
	 */
	public void deleteFood(Food f) throws SQLException{
		//Execute method in food database to remove a food object from the database
		fdb.deleteFood(f);
	}

	/**
	 * This method calls the findAllFastFood() method in the DB class.
	 * Then returns a list of FastFood.
	 * 
	 * @return list of FastFood
	 * @throws SQLException
	 */
	public List<FastFood> findAllFastFood() throws SQLException{
		//Execute method in food database to find all food objects from database
		return fdb.findAllFastFood();
	}

	/**
	 * This method calls the findFastFoodById() method in the DB class.
	 * Then returns a fast food object with the supplied id.
	 * 
	 * @param id food id
	 * @return FastFood object
	 * @throws SQLException
	 */
	public FastFood findFastFoodByID(int id) throws SQLException{
		//Execute method in food database to find a food object from database
		return fdb.findFastFoodById(id);
	}

	/**	 
	 * This method calls the createFastFood() method in the DB class.
	 * Persists the fastfood object created from the parameters
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @throws SQLException
	 */
	public void createFastFood(String name, int menuNo, double price, String type, String desc, String size) throws SQLException{
		//Execute method in food database create a food object in the database
		FastFood f = new FastFood(name, menuNo, price, type, desc, size);
		fdb.createFastFood(f);
	}

	/**	 
	 * This method calls the createFood() method.
	 * Persists the fastfood object created from the parameters
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @throws SQLException
	 */
	public void createFastFood(int id, String name, int menuNo, double price, String type, String desc, String size) throws SQLException{
		//Execute method in food database create a food object in the database
		FastFood f = new FastFood(id, name, menuNo, price, type, desc, size);
		createFood(f);
	}

	/**
	 * This method calls the createFood() method
	 * Persists the fastfood object created from the parameters
	 * 
	 * @param f FastFood object
	 * @throws SQLException
	 */
	public void createFastFood(FastFood f) throws SQLException{
		//Execute method in food database create a food object in the database
		createFood(f);
	}

	/**	
	 * This method calls the updateFastFood() method in the DB class.
	 * Updates the persisted Fastfood with the supplied FastFood object.
	 * 
	 * @param f FastFood object
	 * @throws SQLException
	 */
	public void updateFastFood(FastFood f) throws SQLException {
		fdb.updateFastFood(f);
	}

	/**
	 * This method calls the updateFastFood() method in the DB class.
	 * Updates the persisted FastFood with the supplied parameters.
	 * 
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param id food id
	 * @throws SQLException
	 */
	public void updateFastFood(String name, int menuNo, double price, String type, String desc, String size, int id) throws SQLException {
		fdb.updateFastFood(name, menuNo, price, type, desc, size, id);
	}

	/**
	 * This method calls the findAllPizza() method in the DB class.
	 * Then returns a list of Pizzas.
	 * 
	 * @return list of Pizza
	 * @throws SQLException
	 */
	public List<Pizza> findAllPizza() throws SQLException{
		//Execute method in food database to find all food objects from database
		return fdb.findAllPizza();
	}

	/**
	 * This method calls the findPizzaById() method in the DB class.
	 * Then returns a pizza object with the supplied id.
	 * 
	 * @param id food id
	 * @return Pizza object
	 * @throws SQLException
	 */
	public Pizza findPizzaByID(int id) throws SQLException{
		//Execute method in food database to find a food object from database
		return fdb.findPizzaById(id);
	}

	/**	 
	 * This method calls the createFood() method.
	 * Persists the Pizza object created from the parameters
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isLunchOffer pizza isLunchOffer
	 * @throws SQLException
	 */
	public void createPizza(int id, String name, int menuNo, double price, String type, String desc, String size, boolean isLunchOffer) throws SQLException{
		//Execute method in food database create a food object in the database
		Pizza f = new Pizza(name, menuNo, price, type, desc, size, isLunchOffer);
		createFood(f);
	}

	/**	 
	 * This method calls the createFood() method.
	 * Persists the Pizza object created from the parameters
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isLunchOffer pizza isLunchOffer
	 * @throws SQLException
	 */
	public void createPizza(String name, int menuNo, double price, String type, String desc, String size, boolean isLunchOffer) throws SQLException{
		//Execute method in food database create a food object in the database
		Pizza f = new Pizza(name, menuNo, price, type, desc, size, isLunchOffer);
		createFood(f);
	}

	/**
	 * This method calls createFood() method.
	 * Persists the pizza object.
	 * 
	 * @param p
	 * @throws SQLException
	 */
	public void createPizza(Pizza p) throws SQLException{
		//Execute method in food database create a food object in the database
		createFood(p);
	}

	/**
	 * This method calls the updatePizza() method.
	 * Updates the persisted pizza with the parameters.
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param id food id
	 * @param isLunchOffer pizza isLunchOffer
	 * @throws SQLException
	 */
	public void updatePizza(String name, int menuNo, double price, String type, String desc, String size, int id, boolean isLunchOffer) throws SQLException {
		fdb.updatePizza(name, menuNo, price, type, desc, size, id, isLunchOffer);
	}

	/**
	 * This method calls the updatePizza() method.
	 * Updates the persisted pizza with the pizza object
	 * @param p
	 * @throws SQLException
	 */
	public void updatePizza(Pizza p) throws SQLException {
		fdb.updatePizza(p);
	}

	/**
	 * This method calls the findAllDrink() method in the DB class.
	 * Then returns a list of Drinks.
	 * 
	 * @return list of Drink
	 * @throws SQLException
	 */
	public List<Drink> findAllDrinks() throws SQLException{
		return fdb.findAllDrink();
	}

	/**
	 * This method calls the findDrinkOnId() method in the DB class.
	 * Then returns a persisted drink with the id
	 * 
	 * @param id food id
	 * @return Drink object
	 * @throws SQLException
	 */
	public Drink findDrinkByID(Integer id) throws SQLException {
		return fdb.findDrinkOnId(id);
	}

	/**
	 * This method calls the createFood()
	 * Persists the Drink object created from the parameters
	 * 
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isDiet diet drink	
	 * @param brand drink brand
	 * @throws SQLException
	 */
	public void createDrink(String name, int menuNo, double price, String type, String desc, String size, boolean isDiet, String brand) throws SQLException{
		//Execute method in food database create a food object in the database
		Drink d = new Drink(name, menuNo, price, type, desc, size, isDiet, brand);
		createFood(d);
	}

	/**
	 * This method calls the createDrink() from the DB class.
	 * Persists the Drink object created from the Drink object
	 *  
	 * @param d Drink object
	 * @throws SQLException
	 */
	public void createDrink(Drink d) throws SQLException{
		fdb.createDrink(d);
	}

	/**
	 * This method calls the updateDrink() from the DB class.
	 * Updates the persisted Drink from the supplied Drink object
	 * 
	 * @param d Drink object
	 * @throws SQLException
	 */
	public void updateDrink(Drink d) throws SQLException{
		fdb.updateDrink(d);
	}

	/**
	 * This method calls updateDrink from the DB class.
	 * Updates the persisted Drink from the supplied parameters
	 * 
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu number
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isDiet diet drink	
	 * @param brand drink brand
	 * @throws SQLException
	 */
	public void updateDrink(Integer id, String name, int menuNo, double price, String type, String desc, String size,
			boolean isDiet, String brand) throws SQLException {
		fdb.updateDrink(id, name, menuNo, price, type, desc, size, isDiet, brand);
	}

	/**
	 * This method calls the findAllBrand() method in the DB class.
	 * Then returns a list of Brands.
	 * 
	 * @return list of Brand
	 * @throws SQLException
	 */
	public List<Brand> findAllBrands() throws SQLException{
		return fdb.findAllBrand();
	}

	/**
	 * This method checks if food is on an orderline
	 * returns true if above mentioned
	 * 
	 * @param food Food object
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean inUse(Food food) throws SQLException {
		orderCtr = new OrderController();
		List<Order> allOrders = orderCtr.findAll();
		List <OrderLine> orderlines = null;
		for (int i = 0; i < allOrders.size(); i++) {
			orderlines = allOrders.get(i).getOrderL();
		}
		if(orderlines != null) {
			for (int i = 0; i < orderlines.size(); i++) {
				if(orderlines.get(i).getFood().equals(food)) {
					return true;
				}
			}
		}
		return false;	
	}

	/**
	 * This method finds all brands
	 * returns all brands as strings
	 * 
	 * @return brands as a string
	 * @throws SQLException
	 */
	public String[] findBrandsString() throws SQLException {
		List<Brand> brandList = findAllBrands();
		String[] result = new String[brandList.size()];
		for (int i = 0; i < brandList.size(); i++) {
			result[i] = brandList.get(i).getName();
		}
		return result;
	}
}
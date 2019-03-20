package database;

import java.sql.SQLException;
import java.util.List;

import model.Brand;
import model.Drink;
import model.FastFood;
import model.Food;
import model.Pizza;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public interface FoodDBIF {
	
	/**
	 * @return List containing all FastFood objects from DB
	 * @throws SQLException
	 */
	List<FastFood> findAllFastFood() throws SQLException;	
	
	/**
	 * This method finds a fast food object from the database on id
	 * @param id food id
	 * @return FastFood object
	 * @throws SQLException
	 */
	FastFood findFastFoodById(int id) throws SQLException;
	
	/**
	 * This method creates a fast food object in the database
	 * @param f FastFood object
	 * @throws SQLException
	 */
	void createFastFood(FastFood f) throws SQLException;
	
	/**
	 * This method updates a fast food object in the database
	 * @param name food name
	 * @param menuNo food menu no
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param id food id
	 * @throws SQLException
	 */	
	void updateFastFood(String name, int menuNo, double price, String type, String desc, String size, int id) throws SQLException;
	
	/**
	 * This method updates a fast food object in the database
	 * @param f FastFood object
	 * @throws SQLException
	 */
	void updateFastFood(FastFood f) throws SQLException;
	
	/**
	 * @return List containing all Pizza objects from DB
	 * @throws SQLException
	 */
	List<Pizza> findAllPizza() throws SQLException;
	
	/**
	 * this method find a pizza object from the database on id
	 * @param id food id
	 * @return Pizza object
	 * @throws SQLException
	 */
	Pizza findPizzaById(int id) throws SQLException;
	
	/**
	 * This method creates a pizza object in the database
	 * @param f Pizza object
	 * @throws SQLException
	 */
	void createPizza(Pizza f) throws SQLException;
	
	/**
	 * This method updates a Pizza object in the databse
	 * @param name food name
	 * @param menuNo food menu no
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param id food id
	 * @param isLunchOffer pizza lunchoffer
	 * @throws SQLException
	 */
	void updatePizza(String name, int menuNo, double price, String type, String desc, String size, int id, boolean isLunchOffer) throws SQLException;
	
	/**
	 * This method updates a pizza object in the database
	 * @param p Pizza object
	 * @throws SQLException
	 */
	void updatePizza(Pizza p) throws SQLException;	
	
	/**
	 * @return List containing all Drink objects from DB
	 * @throws SQLException
	 */
	List<Drink> findAllDrink() throws SQLException;
	
	/**
	 * this method finds a drink object from the database on id 
	 * @param id food id
	 * @return Drink object
	 * @throws SQLException
	 */
	Drink findDrinkOnId(int id) throws SQLException;
	
	/**
	 * This method creates a drink object in the database
	 * @param d Drink object
	 * @throws SQLException
	 */
	void createDrink(Drink d) throws SQLException;
	
	/**
	 * this method updates a drink object in the database
	 * @param id food id
	 * @param name food name
	 * @param menuNo food menu no
	 * @param price food price
	 * @param type food type
	 * @param desc food description
	 * @param size food size
	 * @param isDiet diet drink
	 * @param brand drink brand
	 * @throws SQLException
	 */
	void updateDrink(Integer id, String name, int menuNo, double price, String type, String desc, String size, boolean isDiet, String brand)  throws SQLException;
	
	/**
	 * this method updates a drink object in the database
	 * @param d Drink object
	 * @throws SQLException
	 */
	void updateDrink(Drink d) throws SQLException;
	
	/**
	 * @return List containing all Brand objects from DB
	 * @throws SQLException
	 */
	List<Brand> findAllBrand() throws SQLException;

	/**
	 * this method finds a food object on the menu number
	 * @param menu menu number
	 * @return Food object
	 * @throws SQLException
	 */
	Food findOnMenu(int menu) throws SQLException;

	/**
	 * This method finds a food object from the database on id
	 * @param id food id
	 * @return Food object
	 * @throws SQLException
	 */
	Food findFoodById(int id) throws SQLException;

	/**
	 * This method removes a food, and sub types, object from the database
	 * @param f Food object
	 * @throws SQLException
	 */
	void deleteFood(Food f) throws SQLException;
}

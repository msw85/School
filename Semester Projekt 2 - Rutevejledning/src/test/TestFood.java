package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.FoodController;
import model.Customer;
import model.Drink;
import model.FastFood;
import model.Food;
import model.Person;
import model.Pizza;

/**
 * @author Aske
 *
 */
class TestFood {
	FoodController foodCtr;
	FastFood f;
	Pizza p;
	Drink d;
	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		foodCtr = new FoodController();
		f = new FastFood("HotDog", 10, 10, "fast food", "En hoddog", "Stor");
		p = new Pizza("HotDogPizza", 12, 10, "pizza", "En hoddog pizza", "Stor", true);
		d = new Drink("Cola", 15, 30, "drink", "Stor Coka Cola", "Stor" , false, "Coca");
		foodCtr.createFood(f);
		foodCtr.createFood(p);	
	}
	/**
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		foodCtr.deleteFood(f);
		foodCtr.deleteFood(p);
		foodCtr = null;
		f = null;
		p = null;
	}
	@Test
	void testCreateFastFood() {
		Food a = new FastFood("HotDog", 10, 10, "fast food", "En hoddog", "Stor");
		assertNotNull(a.getId());
		assertEquals(a.getDesc(), f.getDesc());
		assertEquals(a.getMenuNo(), f.getMenuNo());
		assertEquals(a.getName(), f.getName());
		assertEquals(a.getPrice(), f.getPrice());
		assertEquals(a.getSize(), f.getSize());
		assertEquals(a.getType(), f.getType());
	}
	@Test
	void testCreateDrink() {
		Drink dd = new Drink("Cola", 16, 30, "drink", "Stor Coka Cola", "Stor", false, "Coca");
		assertNotNull(d.getId());
		assertEquals(d.getBrand(), dd.getBrand());
		assertEquals(d.getDesc(), dd.getDesc());
		assertEquals(d.getMenuNo(), dd.getMenuNo() - 1);
		assertEquals(d.getName(), dd.getName());
		assertEquals(d.getPrice(), dd.getPrice());
		assertEquals(d.getSize(), dd.getSize());
		assertEquals(d.getType(), dd.getType());
	}	
	@Test
	void testCreatePizza() {
		Food a = new Pizza("HotDogPizza", 13, 10, "pizza", "En hoddog pizza", "Stor", true);
		assertNotNull(a.getId());
		assertEquals(a.getDesc(), p.getDesc());
		assertEquals(a.getMenuNo() - 1, p.getMenuNo());
		assertEquals(a.getName(), p.getName());
		assertEquals(a.getPrice(), p.getPrice());
		assertEquals(a.getSize(), p.getSize());
		assertEquals(a.getType(), p.getType());
	}
	@Test
	void testInsertDrink()throws SQLException{
		Drink dd = new Drink("Cola", 17, 30, "drink", "Stor Coka Cola", "Stor", false, "Coca");
		foodCtr.createDrink(dd);
		List<Drink> allDrinks = foodCtr.findAllDrinks();
		Drink testD = allDrinks.get(allDrinks.size() -1);
		dd.setMenuNo(17);
		assertEquals(dd.toString(), testD.toString());
		foodCtr.deleteFood(dd);
	}
	@Test
	void testInsert() throws SQLException {
		Food a = new FastFood("HotDog", 11, 10, "fast food", "En hoddog", "Stor");
		foodCtr.createFood(a);
		List<FastFood> foods = foodCtr.findAllFastFood();
		Food b = foods.get(foods.size() - 1);
		assertEquals(a.toString(), b.toString());
		foodCtr.deleteFood(a);
	}
	@Test
	void testInsertPizza() throws SQLException {
		Food p = new Pizza("HotDogPizza", 13, 10, "pizza", "En hoddog pizza", "Stor", true);
		foodCtr.createFood(p);
		List<Pizza> foods = foodCtr.findAllPizza();
		Pizza a = foods.get(foods.size() - 1);
		assertEquals(p.toString(), a.toString());
		foodCtr.deleteFood(p);	
	}
	@Test
	void testSingleFastFoodGet() throws SQLException{
		Food newFood = (FastFood)foodCtr.findFastFoodByID(f.getId());
		assertEquals(f.toString(), newFood.toString());
	}
	
	@Test
	void testSinglePizzaGet() throws SQLException{
		Food newFood = (Pizza)foodCtr.findPizzaByID(p.getId());
		assertEquals(p.toString(), newFood.toString());
	}
	@Test 
	void testUpdateFastFood() throws SQLException{
		f.setDesc("ikke en HotDog");
		foodCtr.updateFastFood(f);
		Food ff = foodCtr.findFastFoodByID(f.getId());
		assertEquals(f.getDesc(),ff.getDesc());
		f.setDesc("En hoddog");
		foodCtr.updateFastFood(f);
	}
	@Test 
	void testUpdatePizza() throws SQLException{
		p.setDesc("en HotDog");
		foodCtr.updatePizza(p);
		Food ff = foodCtr.findPizzaByID(p.getId());
		assertEquals(p.getDesc(),ff.getDesc());
		p.setDesc("En hoddog pizza");
		foodCtr.updatePizza(p);
	}
	@Test
	void testDelete()throws SQLException{
		List<FastFood> foodList = foodCtr.findAllFastFood();
		int lengthBefore = foodList.size();
		foodCtr.deleteFood(f);
		foodList = foodCtr.findAllFastFood();
		int lengthAfter = foodList.size();
		assertEquals(lengthBefore -1, lengthAfter);
	}
	@Test
	void testGetOnMenu() throws SQLException{
		Food mp = foodCtr.findOnMenuNo(p.getMenuNo());
		Food mf = foodCtr.findOnMenuNo(f.getMenuNo());
		
		assertEquals(p.getName(), mp.getName());
		assertEquals(p.getDesc(), mp.getDesc());
		assertEquals(p.getId(), mp.getId());
		assertEquals(p.getMenuNo(), mp.getMenuNo());
		
		assertEquals(f.getName(), mf.getName());
		assertEquals(f.getDesc(), mf.getDesc());
		assertEquals(f.getId(), mf.getId());
		assertEquals(f.getMenuNo(), mf.getMenuNo());
	}
	
}

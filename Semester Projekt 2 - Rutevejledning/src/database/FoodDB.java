package database;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
public class FoodDB implements FoodDBIF {
	//Creation of SQL scripts
	private static final String FIND_FOOD_ON_ID_Q = "SELECT id, name, menuNo, price, type, description, size FROM foods WHERE id = ?";	
	private static final String FIND_FOOD_ON_MENU = "SELECT id, name, menuNo, price, type, description, size FROM foods WHERE menuNo = ?";
	private static final String CREATE_FOOD_Q = "INSERT INTO foods(name, menuNo, price, type, description, size) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_FOOD_ON_ID_Q = "UPDATE foods SET name = ?, menuNo = ?, price = ?, type = ?, description = ?, size = ? WHERE id = ?";
	private static final String DELETE_FOOD_ON_ID_Q = "DELETE FROM foods WHERE id = ?";

	private static final String FIND_ALL_FASTFOOD_Q = "SELECT f.id, f.name, f.menuNo, f.price, f.type, f.description, f.size FROM foods f , fastFood ff WHERE f.id = ff.fId";
	private static final String CREATE_FAST_FOOD_Q = "INSERT INTO fastFood(fId) VALUES (?)";

	private static final String FIND_ALL_PIZZA_Q = "SELECT f.id, f.name, f.menuNo, f.price, f.type, f.description, f.size, p.isLunchOffer FROM foods f , pizza p WHERE f.id = p.fId";
	private static final String FIND_PIZZA_ON_ID = "SELECT f.id, f.name, f.menuNo, f.price, f.type, f.description, f.size, p.isLunchOffer FROM foods f , pizza p WHERE f.id = p.fId AND f.id = ?";
	private static final String CREATE_PIZZA_Q = "INSERT INTO pizza(fId, isLunchOffer) VALUES (?, ?)";	
	private static final String UPDATE_PIZZA_ON_ID_Q = "UPDATE pizza SET isLunchOffer = ? WHERE fId = ?"; 

	private static final String FIND_ALL_DRINK_Q = "SELECT f.id, f.name, f.menuNo, f.price, f.type, f.description, f.size, (SELECT b.name WHERE d.bId = b.id) as brandName , d.isDiet FROM foods f , drink d, brands b WHERE f.id = d.fId";
	private static final String FIND_DRINK_ON_ID_Q = "SELECT f.id, f.name, f.menuNo, f.price, f.type, f.description, f.size, (SELECT b.name WHERE b.id = d.bId) as brandName, d.isDiet FROM foods f , drink d, brands b WHERE f.id = d.fId and f.id = ?";
	private static final String CREATE_DRINK_Q = "INSERT INTO drink(fId, bId, isDiet) VALUES (?, (SELECT id FROM brands WHERE name = ?), ?)";
	private static final String UPDATE_DRINK_ON_ID_Q = "UPDATE drink SET bId = (SELECT id FROM brands WHERE name = ? ), isDiet = ? where fId = ?";
	
	private static final String FIND_ALL_BRAND_Q = "SELECT id, name from brands";

	//Create prepared statements
	
	private PreparedStatement findFoodOnId;
	private PreparedStatement findOnMenu;
	private PreparedStatement createFood;
	private PreparedStatement updateFoodOnId;
	private PreparedStatement deleteFoodOnId;
	
	private PreparedStatement findAllFastFood;
	private PreparedStatement createFastFood;

	private PreparedStatement findAllPizza;
	private PreparedStatement findPizzaOnId;
	private PreparedStatement createPizza;
	private PreparedStatement updatePizza; 

	private PreparedStatement findAllDrink;
	private PreparedStatement findDrinkOnId;
	private PreparedStatement createDrink;
	private PreparedStatement updateDrink;

	private PreparedStatement findAllBrands;

	/**
	 * @throws SQLException
	 */
	public FoodDB() throws SQLException {
		//Instantiate prepared statements 
		findFoodOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_FOOD_ON_ID_Q);
		findOnMenu = DBConnection.getInstance().getConnection().prepareStatement(FIND_FOOD_ON_MENU);	
		createFood = DBConnection.getInstance().getConnection().prepareStatement(CREATE_FOOD_Q, Statement.RETURN_GENERATED_KEYS);
		updateFoodOnId = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_FOOD_ON_ID_Q);
		deleteFoodOnId = DBConnection.getInstance().getConnection().prepareStatement(DELETE_FOOD_ON_ID_Q);

		findAllFastFood = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_FASTFOOD_Q);
		createFastFood = DBConnection.getInstance().getConnection().prepareStatement(CREATE_FAST_FOOD_Q);

		findAllPizza = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_PIZZA_Q);
		findPizzaOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_PIZZA_ON_ID);
		createPizza = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PIZZA_Q, Statement.RETURN_GENERATED_KEYS);
		updatePizza = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PIZZA_ON_ID_Q);
		
		findAllDrink = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_DRINK_Q);
		findDrinkOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_DRINK_ON_ID_Q);
		createDrink = DBConnection.getInstance().getConnection().prepareStatement(CREATE_DRINK_Q, Statement.RETURN_GENERATED_KEYS);
		updateDrink = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_DRINK_ON_ID_Q);
	
		findAllBrands = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_BRAND_Q);
	}

	/* (non-Javadoc)
	 * @see database.FoodDBIF#findFoodById(int)
	 */
	@Override
	public Food findFoodById(int id) throws SQLException {
		//Create an empty food object
		Food food = null;
		//Create an empty result set
		ResultSet rs;
		//Set variables in SQL Script
		findFoodOnId.setInt(1, id);
		//Execute SQL script and get the result set
		rs = findFoodOnId.executeQuery();
		//Build and return the food object
		rs.next();
		food = buildObject(rs);
		return food;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#findOnMenu(int)
	 */
	@Override
	public Food findOnMenu(int menu) throws SQLException{
		//Create an empty food object
		Food food = null;
		//Create an empty result set
		ResultSet rs;
		//Set variables in SQL Script
		findOnMenu.setInt(1, menu);
		//Execute SQL script and get the result set
		rs = findOnMenu.executeQuery();
		//Build and return the food object
		rs.next();
		food = buildObject(rs);
		return food;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#deleteFood(model.Food)
	 */
	@Override
	public void deleteFood(Food f) throws SQLException {
		dbUtil.prepareTransaction();		//Set variables in SQL Script
		deleteFoodOnId.setInt(1, f.getId());
		//Execute SQL script
		deleteFoodOnId.execute();
		DBConnection.getInstance().getConnection().commit();
		DBConnection.getInstance().getConnection().setAutoCommit(true);

	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#findAllFastFood()
	 */
	@Override
	public List<FastFood> findAllFastFood() throws SQLException {
		//Create an empty result-set
		ResultSet rs;
		//Execute query and get result set
		rs = findAllFastFood.executeQuery();
		//Build all objects from query
		List<FastFood> allFood =  buildFastFoodObjects(rs);
		//Return list of all food objects
		return allFood;
	}	
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#findFastFoodById(int)
	 */
	@Override
	public FastFood findFastFoodById(int id) throws SQLException {
		//Create an empty food object
		FastFood food = null;
		//Create an empty result set
		ResultSet rs;
		//Set variables in SQL Script
		findFoodOnId.setInt(1, id);
		//Execute SQL script and get the result set
		rs = findFoodOnId.executeQuery();
		//Build and return the food object
		rs.next();
		food = buildFastFoodObject(rs);
		return food;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#createFastFood(model.FastFood)
	 */
	@Override
	public void createFastFood(FastFood f) throws SQLException {
		dbUtil.prepareTransaction();		//Set variables in SQL Script
		createFood.setString(1, f.getName());
		createFood.setInt(2, f.getMenuNo());
		createFood.setInt(3,(int) (f.getPrice() * 100));
		createFood.setString(4, f.getType());
		createFood.setString(5, f.getDesc());
		createFood.setString(6, f.getSize());
		int affectedRows = 0;
		//Execute SQL script and get affected rows
		try {
			affectedRows = createFood.executeUpdate();
			dbUtil.commitTransaction(false);

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		//Check if affectedRows == 0
		if (affectedRows == 0) {
			//An error occurred and no rows were affected
			throw new SQLException("Creating food failed, no rows affected.");
		}
		else {
			//Get result-set from generated keys
			try (ResultSet generatedKeys = createFood.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					//Set ID = the generated key
					f.setId(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating food failed, no ID obtained.");
				}
			}
			if(f.getType().equals("fast food")) {
				//Set variables in SQL Script
				createFastFood.setInt(1, f.getId());
				//Execute SQL script
				createFastFood.execute();
			}	
		}
		dbUtil.commitTransaction(true);

	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#updateFastFood(java.lang.String, int, double, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public void updateFastFood(String name, int menuNo, double price, String type, String desc, String size, int id) throws SQLException {
		dbUtil.prepareTransaction();		
		updateFoodOnId.setString(1, name);
		updateFoodOnId.setInt(2, menuNo);
		updateFoodOnId.setDouble(3, price);
		updateFoodOnId.setString(4, type);
		updateFoodOnId.setString(5, desc);
		updateFoodOnId.setString(6, size);
		updateFoodOnId.setInt(7, id);	
		updateFoodOnId.executeUpdate();
		dbUtil.commitTransaction(true);
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#updateFastFood(model.FastFood)
	 */
	@Override
	public void updateFastFood(FastFood f) throws SQLException {
		dbUtil.prepareTransaction();		
		updateFoodOnId.setString(1, f.getName());
		updateFoodOnId.setInt(2, f.getMenuNo());
		updateFoodOnId.setDouble(3, f.getPrice());
		updateFoodOnId.setString(4, f.getType());
		updateFoodOnId.setString(5, f.getDesc());
		updateFoodOnId.setString(6, f.getSize());
		updateFoodOnId.setInt(7, f.getId());	
		updateFoodOnId.executeUpdate();
		dbUtil.commitTransaction(true);	
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#findAllPizza()
	 */
	@Override
	public List<Pizza> findAllPizza() throws SQLException {
		//Create an empty result-set
		ResultSet rs;
		//Execute query and get result set
		rs = findAllPizza.executeQuery();
		//Build all objects from query
		List<Pizza> allFood =  buildPizzaObjects(rs);
		//Return list of all food objects
		return allFood;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#findPizzaById(int)
	 */
	@Override
	public Pizza findPizzaById(int id) throws SQLException {
		//Create an empty food object
		Pizza food = null;
		//Create an empty result set
		ResultSet rs;
		//Set variables in SQL Script
		findPizzaOnId.setInt(1, id);
		//Execute SQL script and get the result set
		rs = findPizzaOnId.executeQuery();
		//Build and return the food object
		rs.next();
		food = buildPizzaObject(rs);
		return food;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#createPizza(model.Pizza)
	 */
	@Override
	public void createPizza(Pizza f) throws SQLException {
		dbUtil.prepareTransaction();		//Set variables in SQL Script
		createFood.setString(1, f.getName());
		createFood.setInt(2, f.getMenuNo());
		int price = (int) BigDecimal.valueOf(f.getPrice() * 100).intValue();
 		createFood.setInt(3, price);
		createFood.setString(4, f.getType());
		createFood.setString(5, f.getDesc());
		createFood.setString(6, f.getSize());
		//Execute SQL script and get affected rows
		int affectedRows = createFood.executeUpdate();
		dbUtil.commitTransaction(false);
		//Check if affectedRows == 0
		if (affectedRows == 0) {
			//An error occurred and no rows were affected
			throw new SQLException("Creating pizza failed, no rows affected.");
		}
		//Get result-set from generated keys
		try (ResultSet generatedKeys = createFood.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				//Set ID = the generated key
				f.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating pizza failed, no ID obtained.");
			}
		}
		//Check if type == fast food, only option currently
		//isLunchOffer, lunchOfferPrice
		createPizza.setInt(1, f.getId());
		createPizza.setBoolean(2, f.isLunchOffer());
		createPizza.executeUpdate();
		dbUtil.commitTransaction(true);

	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#updatePizza(java.lang.String, int, double, java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	@Override
	public void updatePizza(String name, int menuNo, double price, String type, String desc, String size, int id, boolean isLunchOffer) throws SQLException {
		dbUtil.prepareTransaction();	
		updateFoodOnId.setString(1, name);
		updateFoodOnId.setInt(2, menuNo);
		updateFoodOnId.setInt(3, (int)(price * 100));
		updateFoodOnId.setString(4, type);
		updateFoodOnId.setString(5, desc);
		updateFoodOnId.setString(6, size);
		updateFoodOnId.setInt(7, id);
		updatePizza.setBoolean(1, isLunchOffer);
		updatePizza.setInt(2, id);
		//Execute SQL script
		updateFoodOnId.executeUpdate();	
		updatePizza.executeUpdate();
		dbUtil.commitTransaction(true);
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#updatePizza(model.Pizza)
	 */
	@Override
	public void updatePizza(Pizza p) throws SQLException {
		dbUtil.prepareTransaction();	
		updateFoodOnId.setString(1, p.getName());
		updateFoodOnId.setInt(2, p.getMenuNo());
		updateFoodOnId.setInt(3, (int)(p.getPrice()*100));
		updateFoodOnId.setString(4, p.getType());
		updateFoodOnId.setString(5, p.getDesc());
		updateFoodOnId.setString(6, p.getSize());
		updateFoodOnId.setInt(7, p.getId());
		updatePizza.setBoolean(1, p.isLunchOffer());
		updatePizza.setInt(2, p.getId());
		//Execute SQL script
		updateFoodOnId.executeUpdate();	
		updatePizza.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/* (non-Javadoc)
	 * @see database.FoodDBIF#findAllDrink()
	 */
	public List<Drink> findAllDrink() throws SQLException  {
		//Create an empty result-set
		ResultSet rs;
		//Execute query and get result set
		rs = findAllDrink.executeQuery();
		//Build all objects from query
		List<Drink> allDrink =  buildDrinkObjects(rs);
		//Return list of all food objects
		return allDrink;
	}
		/* (non-Javadoc)
	 * @see database.FoodDBIF#findDrinkOnId(int)
	 */
	@Override
	public Drink findDrinkOnId(int id) throws SQLException {
		//Create an empty food object
		Drink food = null;
		//Create an empty result set
		ResultSet rs;
		//Set variables in SQL Script
		findDrinkOnId.setInt(1, id);
		//Execute SQL script and get the result set
		rs = findDrinkOnId.executeQuery();
		//Build and return the food object
		rs.next();
		food = buildDrinkObject(rs);
		return food;
	}
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#createDrink(model.Drink)
	 */
	@Override
	public void createDrink(Drink d) throws SQLException {
		dbUtil.prepareTransaction();
		//Set variables in SQL Script
		createFood.setString(1, d.getName());
		createFood.setInt(2, d.getMenuNo());
		createFood.setInt(3, (int)(d.getPrice() * 100));
		createFood.setString(4, d.getType());
		createFood.setString(5, d.getDesc());
		createFood.setString(6, d.getSize());
		try {
			//Execute SQL script and get affected rows
			int affectedRows = createFood.executeUpdate();
			dbUtil.commitTransaction(false);
			//Check if affectedRows == 0
			if (affectedRows == 0) {
				//An error occurred and no rows were affected
				throw new SQLException("Creating drink failed, no rows affected.");
			}
			//Get result-set from generated keys
			try (ResultSet generatedKeys = createFood.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					//Set ID = the generated key
					d.setId(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating drink failed, no ID obtained.");
				}
			}
		} finally {
			// TODO: handle finally clause
		}
		
		//fId, bId, isDiet
		createDrink.setInt(1, d.getId());
		createDrink.setString(2, d.getBrand());
		createDrink.setBoolean(3, d.isDiet());
		createDrink.executeUpdate();
		dbUtil.commitTransaction(true);
	}


	/* (non-Javadoc)
	 * @see database.FoodDBIF#updateDrink(java.lang.Integer, java.lang.String, int, double, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
	 */
	@Override
	public void updateDrink(Integer id, String name, int menuNo, double price, String type, String desc, String size,
			boolean isDiet, String brand) throws SQLException {
		dbUtil.prepareTransaction();		
		updateFoodOnId.setString(1, name);
		updateFoodOnId.setInt(2, menuNo);
		updateFoodOnId.setInt(3, BigDecimal.valueOf((price * 100)).intValueExact());
		updateFoodOnId.setString(4, type);
		updateFoodOnId.setString(5, desc);
		updateFoodOnId.setString(6, size);
		updateFoodOnId.setInt(7, id);
		updateDrink.setString(1, brand);
		updateDrink.setBoolean(2, isDiet);
		updateDrink.setInt(3, id);

		//Execute SQL script
		updateFoodOnId.executeUpdate();	
		updateDrink.executeUpdate();	
		dbUtil.commitTransaction(true);
	}
	
	
	/* (non-Javadoc)
	 * @see database.FoodDBIF#updateDrink(model.Drink)
	 */
	@Override
	public void updateDrink(Drink d) throws SQLException {
		dbUtil.prepareTransaction();		
		updateFoodOnId.setString(1, d.getName());
		updateFoodOnId.setInt(2, d.getMenuNo());
		updateFoodOnId.setInt(3, BigDecimal.valueOf((d.getPrice() * 100)).intValueExact());
		updateFoodOnId.setString(4, d.getType());
		updateFoodOnId.setString(5, d.getDesc());
		updateFoodOnId.setString(6, d.getSize());
		updateFoodOnId.setInt(7, d.getId());
		updateDrink.setInt(1, d.getId());
		updateDrink.setString(2, d.getBrand());
		updateDrink.setBoolean(3, d.isDiet());
		//Execute SQL script
		updateFoodOnId.executeUpdate();	
		updateDrink.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/* (non-Javadoc)
	 * @see database.FoodDBIF#findAllBrand()
	 */
	@Override
	public List<Brand> findAllBrand() throws SQLException {
		//Create an empty result-set
		ResultSet rs;
		//Execute query and get result set
		rs = findAllBrands.executeQuery();
		//Build all objects from query
		List<Brand> allBrands =  buildBrandObjects(rs);
		//Return list of all brand objects
		return allBrands;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Food buildObject(ResultSet rs) throws SQLException {
		Food p = null;
		//Build Pizza object from result-set
		p= new FastFood(rs.getInt("id"), rs.getString("name"), rs.getInt("menuNo"), (double) (rs.getInt("price") / 100), rs.getString("type"), rs.getString("description"), rs.getString("size"));		
		//Return food object
		return (Food) p;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private FastFood buildFastFoodObject(ResultSet rs) throws SQLException {
		FastFood f = null;
		//Build FastFood object from result-set
		f = new FastFood(rs.getInt("id"), rs.getString("name"), rs.getInt("menuNo"), ((double)rs.getInt("price") / 100),
				rs.getString("type"), rs.getString("description"), rs.getString("size"));
		//Return food object
		return f;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<FastFood> buildFastFoodObjects(ResultSet rs) throws SQLException {
		//Create a list of food objects
		List<FastFood> result = new LinkedList<>();
		//Loop result-set
		while (rs.next()) {
			//Use buildObject to build objects and add them to the list
			result.add(buildFastFoodObject(rs));
		}
		//Return the list
		return result;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Pizza buildPizzaObject(ResultSet rs) throws SQLException {
		Pizza p = null;
		//Build Pizza object from result-set
		p= new Pizza(rs.getInt("id"), rs.getString("name"), rs.getInt("menuNo"), ((double) (rs.getInt("price")) / 100),
				rs.getString("type"), rs.getString("description"), rs.getString("size"), rs.getBoolean("isLunchOffer"));		
		//Return food object
		return p;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<Pizza> buildPizzaObjects(ResultSet rs) throws SQLException {
		//Create a list of food objects
		List<Pizza> result = new LinkedList<>();
		//Loop result-set
		while (rs.next()) {
			//Use buildObject to build objects and add them to the list
			result.add(buildPizzaObject(rs));
		}
		//Return the list
		return result;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Drink buildDrinkObject(ResultSet rs) throws SQLException {
		Drink d = null;
		//Build Drink object from result-set
		d = new Drink(rs.getInt("id"), rs.getString("name"), rs.getInt("menuNo"), (double) (rs.getInt("price") / 100), rs.getString("type"), rs.getString("description"), rs.getString("size"), rs.getBoolean("isDiet"), rs.getString("brandName"));
		
		//Return drink object
		return d;
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<Drink> buildDrinkObjects(ResultSet rs) throws SQLException{
		List<Drink> result = new LinkedList<>();
		while(rs.next()) {
			result.add(buildDrinkObject(rs));
		}
		return result;
	}	
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Brand buildBrandObject(ResultSet rs) throws SQLException {
		Brand b = new Brand(rs.getInt(1), rs.getString(2));
		return b;
		
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<Brand> buildBrandObjects(ResultSet rs) throws SQLException{
		List<Brand> result = new LinkedList<>();
		while(rs.next()) {
			result.add(buildBrandObject(rs));
		}
		return result;	
	}
}
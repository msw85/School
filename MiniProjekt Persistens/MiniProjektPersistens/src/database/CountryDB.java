package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Country;

public class CountryDB implements CountryDBIF {

	private static final String FIND_ALL_Q = "select id, name from countries";
	private static final String FIND_COUN_BY_ID_Q = "select id, name from countries where id=?";
	private static final String CREATE_COUN_Q = "insert into countries(name) values(?) ";
	private static final String UPDATE_COUN_Q = "update countries set name = ? from countries where id = ?";
	private static final String DELETE_COUN_Q = "delete from countries where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findCounById;
	private PreparedStatement createCoun;
	private PreparedStatement updateCoun;
	private PreparedStatement deleteCoun;
	
	public CountryDB() throws SQLException{
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findCounById = DBConnection.getInstance().getConnection().prepareStatement(FIND_COUN_BY_ID_Q);
		createCoun = DBConnection.getInstance().getConnection().prepareStatement(CREATE_COUN_Q);
		updateCoun = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_COUN_Q);
		deleteCoun = DBConnection.getInstance().getConnection().prepareStatement(DELETE_COUN_Q);
	}
	@Override
	public List<Country> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Country> allCoun = buildObjects(rs);
		return allCoun;	
	}

	@Override
	public Country findById(int id) throws SQLException {
		Country coun = null;
		ResultSet rs;
		findCounById.setInt(1, id);
		rs = findCounById.executeQuery();
		rs.next();
		coun = buildObject(rs);
		return coun;
	}

	@Override
	public void createCountry(Country s) throws SQLException{
		createCoun.setString(1, s.getName());
		createCoun.execute();
	}

	@Override
	public void deleteCountry(Country s) throws SQLException {
		deleteCoun.setInt(1, s.getId());
		deleteCoun.execute();
	}

	@Override
	public void updateCountry(Country s) throws SQLException {
		updateCoun.setString(1, s.getName());
		updateCoun.setInt(2, s.getId());
		updateCoun.execute();
	}
	
	private Country buildObject(ResultSet rs) throws SQLException {
		Country coun = new Country(rs.getInt("id"), rs.getString("name"));
		return coun;
	}
	
	private List<Country> buildObjects(ResultSet rs) throws SQLException{
		List<Country> couns = new ArrayList<>();
		while(rs.next()) {
			couns.add(buildObject(rs));
		}
		return couns;
	}
}

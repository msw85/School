package database;

import java.sql.SQLException;
import java.util.List;

import model.Country;

public interface CountryDBIF {
	List<Country> findAll() throws SQLException;
	Country findById(int id) throws SQLException;
	void createCountry(Country s) throws SQLException;
	void deleteCountry(Country s) throws SQLException;
	void updateCountry(Country s) throws SQLException;

}

package controller;

import java.sql.SQLException;
import java.util.List;

import database.CountryDB;
import database.CountryDBIF;
import model.Country;



public class CountryController {

	private CountryDBIF cdb;
	
	public CountryController() throws SQLException {
		cdb = new CountryDB();
	}
	
	public List<Country> findAll() throws SQLException {		
		return cdb.findAll();		
	}
	
	public Country findById(int id) throws SQLException {
		return cdb.findById(id);
	}
	
	public void createCountry(Country s) throws SQLException {
		cdb.createCountry(s);
	}
	
	public void deleteCountry(Country s) throws SQLException {
		cdb.deleteCountry(s);
	}
	
	public void updateCountry(Country s) throws SQLException {
		cdb.updateCountry(s);
	}
}

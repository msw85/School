package controller;

import java.sql.SQLException;
import java.util.List;

import database.ProductDB;
import database.ProductDBIF;
import model.Product;
import model.Storage;
import model.StorageLine;
import model.Supplier;


public class ProductController {

	private ProductDBIF pdb;
	private SupplierController spCtr;
	
	public ProductController() throws SQLException {
		pdb = new ProductDB();
		spCtr = new SupplierController();
	}
	
	public List<Product> findAll() throws SQLException {		
		return pdb.findAll();		
	}
	
	public Product findById(int id) throws SQLException {
		return pdb.findById(id);
	}
	
	public void createProduct(Product p) throws SQLException {
		pdb.createProduct(p);
	}
	
	public void deleteProduct(Product p) throws SQLException {
		pdb.deleteProduct(p);
	}
	
	public void updateProduct(Product p) throws SQLException {
		pdb.updateProduct(p);
		
	}
	
	public Supplier findSupplierById(int id) throws SQLException {
		return spCtr.findById(id);
	}
}

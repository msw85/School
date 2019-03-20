package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ProductController;
import controller.StorageLineController;
import controller.SupplierController;
import model.Product;
import model.StorageLine;
import model.Supplier;

public class ProductDB implements ProductDBIF {

	private static final String FIND_ALLQ = "select id, name, purchase_price, sales_price, cid, min_stock, sid, barcode from products";
	private static final String FIND_PROD_BY_IDQ = "select id, name, purchase_price, sales_price, cid, min_stock, sid, barcode from products where id=?";
	private static final String CREATE_PRODQ = "insert into product(name, purchase_price, sales_price, cid, min_stock, sid, barcode) values( ?, ?, ?, (select id from countries where name = ?), ?, (select id from suppliers where name = ?), ?, ?) ";
	private static final String UPDATE_PRODQ = "update products set name = ?, purchase_price = ?, sales_price = ?, cid = ?, min_stock = ?, sid = ?, barcode = ? from products where id = ?";
	private static final String DELETE_PRODQ = "delete from products where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findProdById;
	private PreparedStatement createProd;
	private PreparedStatement updateProd;
	private PreparedStatement deleteProd;
	
	private StorageLineController slCtr;
	private SupplierController sCtr;
	
	public ProductDB() throws SQLException {
		slCtr = new StorageLineController();
		sCtr = new SupplierController();
		
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALLQ);
		findProdById = DBConnection.getInstance().getConnection().prepareStatement(FIND_PROD_BY_IDQ);
		createProd = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PRODQ);
		updateProd = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PRODQ);
		deleteProd = DBConnection.getInstance().getConnection().prepareStatement(DELETE_PRODQ);
	}
	
	@Override
	public List<Product> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Product> allProd = buildObjects(rs);
		return allProd;
	}

	@Override
	public Product findById(int id) throws SQLException {
		Product prod = null;
		ResultSet rs;
		findProdById.setInt(1, id);
		rs = findProdById.executeQuery();
		rs.next();
		prod = buildObject(rs);
		return prod;
	}

	@Override
	public void createProduct(Product p) throws SQLException {
		createProd.setString(1, p.getName());
		createProd.setDouble(2, p.getPurchasePrice());
		createProd.setDouble(3, p.getSalesPrice());
		createProd.setString(4, p.getCountryOfOrigin());
		createProd.setInt(5, p.getMinStock());
		createProd.setInt(6, p.getSupplier().getId());
		createProd.setString(7, p.getBarcode());	
		createProd.execute();
	}

	@Override
	public void deleteProduct(Product p) throws SQLException {
		deleteProd.setInt(1, p.getId());
		deleteProd.executeQuery();	
	}

	@Override
	public void updateProduct(Product p) throws SQLException {
		updateProd.setString(1, p.getName());
		updateProd.setDouble(2, p.getPurchasePrice());
		updateProd.setDouble(3, p.getSalesPrice());
		updateProd.setString(4, p.getCountryOfOrigin());
		updateProd.setInt(5, p.getMinStock());
		updateProd.setInt(6, p.getSupplier().getId());
		updateProd.setString(7, p.getBarcode());	
		updateProd.setInt(8, p.getId());
		updateProd.executeUpdate();
	}
	
	private Product buildObject(ResultSet rs) throws SQLException {
		// Make Product from RS using constructor!
		List<StorageLine> slAll = slCtr.findAll();
		Supplier supp = sCtr.findById(rs.getInt("sid"));
		Product prod = new Product(rs.getString("name"), rs.getDouble("purchase_price"), rs.getDouble("sales_price"), rs.getString("cid"), rs.getInt("min_stock"), supp, rs.getString("barcode"));
		for (StorageLine storageLine : slAll) {
			if(storageLine.getProd().getId() == prod.getId()) {
				prod.addLocation(storageLine);
			}
		}
		return prod;
	}
	
	private List<Product> buildObjects(ResultSet rs) throws SQLException {
		List<Product> result = new ArrayList<>();
		while(rs.next()) {
			result.add(buildObject(rs));
		}
		return result;
	}
	

}

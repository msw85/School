package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.CountryController;
import database.DBConnection;
import model.Country;
import model.Supplier;

public class SupplierDB implements SupplierDBIF {
	//TODO
	//	Join in the SQL script to get country instead of country id
	//	Implement createSupplier, updateSupplier, deleteSupplier
	
	private static final String FIND_ALLQ = "select id, name, address, cid, phone_no, email from suppliers";
	private static final String FIND_SUPP_BY_IDQ = "select id, name, address, cid, phone_no, email from suppliers where id=?";
	private static final String CREATE_SUPPQ = "insert into suppliers(name, address, cid, phone_no, email) values( ?, ?, (select id from countries where name = ?), ?, ?) ";
	private static final String UPDATE_SUPPQ = "update suppliers set name = ?, address = ?, cid = ?, phone_no = ?, mail = ? from suppliers where id = ?";
	private static final String DELETE_SUPPQ = "delete from suppliers where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findSuppById;
	private PreparedStatement createSupp;
	private PreparedStatement updateSupp;
	private PreparedStatement deleteSupp;
	
	public SupplierDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALLQ);
		findSuppById = DBConnection.getInstance().getConnection().prepareStatement(FIND_SUPP_BY_IDQ);
		createSupp = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SUPPQ);
		updateSupp = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SUPPQ);
		deleteSupp = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SUPPQ);
	}
	
	
	@Override
	public List<Supplier> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Supplier> allSupp = buildObjects(rs);
		return allSupp;			
	}

	@Override
	public Supplier findById(int id) throws SQLException {
		Supplier supp = null;
		ResultSet rs;
		findSuppById.setInt(1, id);
		rs = findSuppById.executeQuery();
		rs.next();
		supp = buildObject(rs);
		return supp;		
	}

	@Override
	public void createSupplier(Supplier s) throws SQLException {
		createSupp.setString(1, s.getName());
		createSupp.setString(2, s.getAddress());
		createSupp.setInt(3, s.getCountry().getId());
		createSupp.setString(4, s.getPhoneno());
		createSupp.setString(5, s.getEmail());
		createSupp.execute();
		// TODO Auto-generated method stub		
	}

	@Override
	public void deleteSupplier(Supplier s) throws SQLException {
		deleteSupp.setInt(1, s.getId());
		deleteSupp.executeQuery();
	}

	@Override
	public void updateSupplier(Supplier s) throws SQLException {
		// TODO Auto-generated method stub		
			updateSupp.setString(1, s.getName());
			updateSupp.setString(2, s.getAddress());
			updateSupp.setInt(3, s.getCountry().getId());
			updateSupp.setString(4, s.getPhoneno());
			updateSupp.setString(5, s.getEmail());
			updateSupp.setInt(6, s.getId());
			updateSupp.executeUpdate();		
	}
	
	private Supplier buildObject(ResultSet rs) throws SQLException {
		// Make Supplier from RS using constructor!
		CountryController cCtr = new CountryController();
		Country c = cCtr.findById(rs.getInt("cid"));
		Supplier supp = new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("address"), c, rs.getString("phone_no"), rs.getString("email"));
		return supp;
	}
	
	private List<Supplier> buildObjects(ResultSet rs) throws SQLException {
		List<Supplier> result = new ArrayList<>();
		while(rs.next()) {
			result.add(buildObject(rs));
		}
		return result;
	}

}

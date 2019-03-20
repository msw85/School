package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ProductController;
import controller.StorageController;
import model.Product;
import model.Storage;
import model.StorageLine;

public class StorageLineDB implements StorageLineDBIF {
	
	private StorageController sCtr;
	

	private static final String FIND_ALL_Q = "select id, num_in_stock, stid, pid from storagelines";
	private static final String FIND_STOR_BY_ID_Q = "select id, num_in_stock, stid, pid from storagelines where id=?";
	private static final String CREATE_STLI_Q = "insert into storagelines(num_in_stock, stid, pid) values(?, ? , ?) ";
	private static final String UPDATE_STLI_Q = "update storagelines set num_in_stock = ?, stid = ?, pid = ? from storagelines where id = ?";
	private static final String DELETE_STLI_Q = "delete from storagelines where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findStLiById;
	private PreparedStatement createStLi;
	private PreparedStatement updateStLi;
	private PreparedStatement deleteStLi;
	
	public StorageLineDB() throws SQLException{
		sCtr = new StorageController();
		
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findStLiById = DBConnection.getInstance().getConnection().prepareStatement(FIND_STOR_BY_ID_Q);
		createStLi = DBConnection.getInstance().getConnection().prepareStatement(CREATE_STLI_Q);
		updateStLi = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STLI_Q);
		deleteStLi = DBConnection.getInstance().getConnection().prepareStatement(DELETE_STLI_Q);
	}
	@Override
	public List<StorageLine> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<StorageLine> allStor = buildObjects(rs);
		return allStor;	
	}

	@Override
	public StorageLine findById(int id) throws SQLException {
		StorageLine stor = null;
		ResultSet rs;
		findStLiById.setInt(1, id);
		rs = findStLiById.executeQuery();
		rs.next();
		stor = buildObject(rs);
		return stor;
	}

	@Override
	public void createStorageLine(StorageLine s) throws SQLException{
		createStLi.setInt(1, s.getNumInStock());
		createStLi.setInt(2, s.getStor().getId());
		createStLi.setInt(3, s.getProd().getId());
		createStLi.execute();
	}

	@Override
	public void deleteStorageLine(StorageLine s) throws SQLException {
		deleteStLi.setInt(1, s.getId());
		deleteStLi.execute();
	}

	@Override
	public void updateStorageLine(StorageLine s) throws SQLException {
		updateStLi.setInt(1, s.getNumInStock());
		updateStLi.setInt(2, s.getStor().getId());
		updateStLi.setInt(3, s.getProd().getId());
		updateStLi.execute();
	}
	
	private StorageLine buildObject(ResultSet rs) throws SQLException {
		ProductController pCtr = new ProductController();
		Storage s = sCtr.findById(rs.getInt("stid"));
		Product p = pCtr.findById(rs.getInt("pid"));
		StorageLine stor = new StorageLine(rs.getInt("id"), rs.getInt("num_in_stock"), s, p);
		return stor;
	}
	
	private List<StorageLine> buildObjects(ResultSet rs) throws SQLException{
		List<StorageLine> stors = new ArrayList<>();
		while(rs.next()) {
			stors.add(buildObject(rs));
		}
		return stors;
	}
}

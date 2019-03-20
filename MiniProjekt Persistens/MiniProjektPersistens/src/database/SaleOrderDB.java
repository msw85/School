package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.CustomerController;
import model.Customer;
import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDBIF {

	private static final String FIND_ALL_Q = "select id, location from saleorders";
	private static final String FIND_SALE_O_BY_ID_Q = "select id, date, ammount, deliveryStatus, deliveryDate, cust from saleorders where id=?";
	private static final String CREATE_SALE_O_Q = "insert into saleorders(date, amount, deliveryStatus, deliveryDate, cust) values(?, ?, ?, ?, ?)";
	private static final String UPDATE_SALE_O_Q = "update saleorders set date= ?, amount = ?, deliveryStatus = ?, deliveryDate = ?, cust = ? from saleorders where id = ?";
	private static final String DELETE_SALE_O_Q = "delete from saleorders where id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findSaleOById;
	private PreparedStatement createSaleO;
	private PreparedStatement updateSaleO;
	private PreparedStatement deleteSaleO;
	
	public SaleOrderDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findSaleOById = DBConnection.getInstance().getConnection().prepareStatement(FIND_SALE_O_BY_ID_Q);
		createSaleO = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SALE_O_Q);
		updateSaleO = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SALE_O_Q);
		deleteSaleO = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SALE_O_Q);
	}

	@Override
	public List<SaleOrder> findAll() throws SQLException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<SaleOrder> allSaleOs = buildObjects(rs);
		return allSaleOs;	
	}

	@Override
	public SaleOrder findById(int id) throws SQLException {
		SaleOrder saleO = null;
		ResultSet rs;
		findSaleOById.setInt(1, id);
		rs = findSaleOById.executeQuery();
		rs.next();
		saleO = buildObject(rs);
		return saleO;
	}

	@Override
	public void createOrder(SaleOrder s) throws SQLException {
		createSaleO.setDate(1, (Date) s.getDate());
		createSaleO.setDouble(2, s.getPrice());
		createSaleO.setBoolean(3, s.isDeliveryStatus());
		createSaleO.setDate(4, (java.sql.Date) s.getDeliveryDate());
		createSaleO.setInt(5, s.getCust().getId());
		createSaleO.execute();
	}

	@Override
	public void deleteOrder(SaleOrder s) throws SQLException {
		deleteSaleO.setInt(1, s.getId());
		deleteSaleO.execute();
	}

	@Override
	public void updateOrder(SaleOrder s) throws SQLException {
		updateSaleO.setDate(1, (java.sql.Date) s.getDate());
		updateSaleO.setDouble(2, s.getPrice());
		updateSaleO.setBoolean(3, s.isDeliveryStatus());
		updateSaleO.setDate(4, (java.sql.Date) s.getDeliveryDate());
		updateSaleO.setInt(5, s.getCust().getId());	
		updateSaleO.setInt(6, s.getId());
		updateSaleO.execute();
	}
	private SaleOrder buildObject(ResultSet rs) throws SQLException {
		//Integer id, Date date, Integer amount, boolean deliveryStatus, Date deliveryDate, Customer cust
		CustomerController custCon = new CustomerController();
		
		Customer c =  custCon.findById(rs.getInt("cust"));
		SaleOrder saleO = new SaleOrder(rs.getInt("id"), rs.getDate("date"), rs.getDouble("price"), rs.getBoolean("deliveryStatus"),
				rs.getDate("deliveryDate"), c);
		return saleO;
	}
	
	private List<SaleOrder> buildObjects(ResultSet rs) throws SQLException{
		List<SaleOrder> saleOs = new ArrayList<>();
		while(rs.next()) {
			saleOs.add(buildObject(rs));
		}
		return saleOs;
	}

}

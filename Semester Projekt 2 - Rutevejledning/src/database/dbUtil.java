package database;

import java.sql.SQLException;

public class dbUtil {
	
	public static void commitTransaction(boolean b) throws SQLException{
		try{
			DBConnection.getInstance().getConnection().commit();
		}
		catch(Exception e){
			DBConnection.getInstance().getConnection().rollback();
		}
		finally {
			DBConnection.getInstance().getConnection().setAutoCommit(b);
		}
	}
	public static void prepareTransaction() throws SQLException {
		DBConnection.getInstance().getConnection().setAutoCommit(false);
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiaryStatisticDAO {
	
	 private static final String SQL_INSERT_DIARY = "INSERT INTO fitness_app_db.diary VALUES ()";


	    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	    public static int insertDiary() {
	        Connection c = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        int lastInsertedId = -1;

	        try {
	            c = connectionPool.checkOut();
	            ps = c.prepareStatement(SQL_INSERT_DIARY, Statement.RETURN_GENERATED_KEYS);

	            if (ps.executeUpdate() > 0) {
	                rs = ps.getGeneratedKeys();
	                if (rs.next()) {
	                    lastInsertedId = rs.getInt(1);
	                    System.out.println("Last inserted id" + lastInsertedId);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (ps != null) {
	                    ps.close();
	                }
	                if (c != null) {
	                    connectionPool.checkIn(c);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return lastInsertedId;
	    }



}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Advicer;
import dto.User;

public class AdvicerDAO {

	private static final String SQL_SELECT_ADVICER_BY_ID = "SELECT * FROM fitness_app_db.advicer WHERE id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.advicer";

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	public AdvicerDAO() {
	}
	
	public static Advicer getById(Integer id) {
		Advicer advicer = null;
		Connection c = null;
		ResultSet rs = null;
		try {
			c = connectionPool.checkOut();
			PreparedStatement ps = DBUtil.prepareStatement(c, SQL_SELECT_ADVICER_BY_ID, false);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				advicer = new Advicer(rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),rs.getInt("id")
						);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(c);
		}
		return advicer;
	}

	public static List<Advicer> getAll() {
		List<Advicer> advicers = new ArrayList<>();

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = connectionPool.checkOut();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL, false);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				advicers.add(new Advicer(rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),rs.getInt("id")));
		} }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(c);
		}
		return advicers;
	}

	public static boolean insertAdvicer(Advicer advicer) {
		Connection c = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			c = connectionPool.checkOut();
			
			ps = c.prepareStatement(
					"INSERT INTO fitness_app_db.advicer (firstName, lastName, username, password) VALUES (?, ?, ?, ?);");
			ps.setString(1, advicer.getFirstName());
			ps.setString(2, advicer.getLastName());
			ps.setString(3, advicer.getUsername());
			ps.setString(4, advicer.getPassword());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			}
			System.out.println(success);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			connectionPool.checkIn(c);
		}

		return success;
	}
	
	public static boolean updateAdvicer(Advicer advicer) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    boolean success = false;
	    System.out.println("advicer za update " + advicer);
	    try {
	        c = connectionPool.checkOut();

	        int id = advicer.getId();

	        ps = c.prepareStatement("UPDATE fitness_app_db.advicer SET firstName=?, lastName=?, username=?, password=? WHERE id=?;");
	        ps.setString(1, advicer.getFirstName());
	        ps.setString(2, advicer.getLastName());
	        ps.setString(3, advicer.getUsername());
	        ps.setString(4, advicer.getPassword());
	        ps.setInt(5, id);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            success = true;
	        }
	        System.out.println(success);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        connectionPool.checkIn(c);
	    }

	    return success;
	}
	
	public static boolean deleteAdvicer(int advicerId) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    boolean success = false;

	    try {
	        c = connectionPool.checkOut();

	        ps = c.prepareStatement("DELETE FROM fitness_app_db.advicer WHERE id=?");
	        ps.setInt(1, advicerId);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            success = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        connectionPool.checkIn(c);
	    }

	    return success;
	}


}

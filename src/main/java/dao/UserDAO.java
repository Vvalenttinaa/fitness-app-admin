package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Admin;
import dto.Logger;
import dto.User;

public class UserDAO {

	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM fitness_app_db.user WHERE id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.user";

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	public UserDAO() {
	}

	public static User getById(Integer id) {
		User user = null;
		Connection c = null;
		ResultSet rs = null;
		try {
			c = connectionPool.checkOut();
			PreparedStatement ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_ID, false);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String cityId = rs.getString("city_id");
				String cityName = CItyDAO.getById(Integer.parseInt(cityId)).getName();

				user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("username"), rs.getString("password"), rs.getString("card"),
						rs.getString("active"), rs.getString("mail"), cityName, rs.getInt("diary_id"));
				System.out.println(user);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(c);
		}
		return user;
	}

	public static List<User> getAll() {
		List<User> users = new ArrayList<>();

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = connectionPool.checkOut();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL, false);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cityId = rs.getString("city_id");
				String cityName = CItyDAO.getById(Integer.parseInt(cityId)).getName();

				users.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("username"), rs.getString("password"), rs.getString("card"),
						rs.getString("active"), rs.getString("mail"), cityName, rs.getInt("diary_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(c);
		}

		return users;
	}

	public static boolean insertUser(User user) {
		Connection c = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			c = connectionPool.checkOut();
			String cityName = user.getCity();
			Integer cityId;

			cityId = CItyDAO.getIdByName(cityName);
			if (cityId == null) {
				CItyDAO.insertCity(cityName);
				cityId = CItyDAO.getIdByName(cityName);
			}
			
			int diary = DiaryStatisticDAO.insertDiary();
			System.out.println(user + ", " + diary);

			ps = c.prepareStatement(
					"INSERT INTO fitness_app_db.user (first_name, last_name, username, password, card, active, mail, city_id, diary_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getCard());
			ps.setString(6, user.getActive());
			ps.setString(7, user.getMail());
			ps.setInt(8, cityId);
			ps.setInt(9, diary);

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
	
	public static boolean updateUser(User user) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    boolean success = false;

	    try {
	        c = connectionPool.checkOut();

	        int userId = user.getId();

	        String cityName = user.getCity();
	        Integer cityId = CItyDAO.getIdByName(cityName);

	        if (cityId == null) {
	            CItyDAO.insertCity(cityName);
	            cityId = CItyDAO.getIdByName(cityName);
	        }

	        ps = c.prepareStatement("UPDATE fitness_app_db.user SET first_name=?, last_name=?, username=?, password=?, card=?, active=?, mail=?, city_id=? WHERE id=?;");
	        ps.setString(1, user.getFirstName());
	        ps.setString(2, user.getLastName());
	        ps.setString(3, user.getUsername());
	        ps.setString(4, user.getPassword());
	        ps.setString(5, user.getCard());
	        ps.setString(6, user.getActive());
	        ps.setString(7, user.getMail());
	        ps.setInt(8, cityId);
	        ps.setInt(9, userId);
	        System.out.print(ps);

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
	
	public static boolean deleteUser(int userId) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    boolean success = false;

	    try {
	        c = connectionPool.checkOut();

	        ps = c.prepareStatement("DELETE FROM fitness_app_db.user WHERE id=?");
	        ps.setInt(1, userId);

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

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.City;

public class CItyDAO {

		private static final String SQL_EXIST_BY_NAME = "SELECT 1 FROM city WHERE name=? LIMIT 1";
	    private static final String SQL_SELECT_CITY_BY_ID = "SELECT * FROM fitness_app_db.city WHERE id=?";
	    private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.city";
	    private static final String SQL_INSERT_CITY = "INSERT INTO fitness_app_db.city (name) VALUES (?)";


	    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	    public CItyDAO() {
	    }
	    

	    public static boolean insertCity(String name) {
	        Connection c = null;
	        PreparedStatement ps = null;
	        boolean success = false;

	        try {
	            c = connectionPool.checkOut();
	            ps = c.prepareStatement(SQL_INSERT_CITY);
	            ps.setString(1, name);

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

	    public static boolean existsByName(String name) {
	        Connection c = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        boolean exists = false;

	        try {
	            c = connectionPool.checkOut();
	            ps = c.prepareStatement(SQL_EXIST_BY_NAME);
	            ps.setString(1, name);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                exists = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            connectionPool.checkIn(c);
	        }

	        return exists;
	    }

	    

	    public static City getById(Integer id) {
	        City city = null;
	        Connection c = null;
	        ResultSet rs = null;

	        try {
	            c = connectionPool.checkOut();
	            PreparedStatement ps = c.prepareStatement(SQL_SELECT_CITY_BY_ID);
	            ps.setInt(1, id);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                city = new City(rs.getInt("id"), rs.getString("name"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.checkIn(c);
	        }

	        return city;
	    }

	    public static List<City> getAll() {
	        List<City> cities = new ArrayList<>();
	        Connection c = null;
	        ResultSet rs = null;

	        try {
	            c = connectionPool.checkOut();
	            PreparedStatement ps = c.prepareStatement(SQL_SELECT_ALL);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                cities.add(new City(rs.getInt("id"), rs.getString("name")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            connectionPool.checkIn(c);
	        }

	        return cities;
	    }
	
	    public static Integer getIdByName(String name) {
	        Connection c = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        Integer cityId = null;

	        try {
	            c = connectionPool.checkOut();
	            ps = c.prepareStatement("SELECT id FROM fitness_app_db.city WHERE name=?");
	            ps.setString(1, name);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                cityId = rs.getInt("id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            connectionPool.checkIn(c);
	        }

	        return cityId;
	    }




}

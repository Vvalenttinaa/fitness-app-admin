package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Attribute;

public class AttributeDAO implements Serializable {

	private static final String SQL_EXIST_BY_NAME = "SELECT 1 FROM attribute WHERE name=? LIMIT 1";
    private static final String SQL_SELECT_ATTRIBUTE_BY_ID = "SELECT * FROM fitness_app_db.attribute WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.attribute";
    private static final String SQL_INSERT_ATTRIBUTE = "INSERT INTO fitness_app_db.attribute (name, category_id) VALUES (?, ?)";


    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public AttributeDAO() {
    }
    

    public static boolean insertAttribute(String name, int category_id) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement(SQL_INSERT_ATTRIBUTE);
            ps.setString(1, name);
            ps.setInt(2, category_id);

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

    

    public static Attribute getById(Integer id) {
        Attribute attribute = null;
        Connection c = null;
        ResultSet rs = null;

        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = c.prepareStatement(SQL_SELECT_ATTRIBUTE_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                attribute = new Attribute(rs.getInt("id"), rs.getString("name"), rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return attribute;
    }

    public static List<Attribute> getAll() {
        List<Attribute> attributes = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;

        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = c.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                attributes.add(new Attribute(rs.getInt("id"), rs.getString("name"), rs.getInt("category_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return attributes;
    }

    public static Integer getIdByName(String name) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("SELECT id FROM fitness_app_db.attribute WHERE name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
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

        return id;
    }
    
    public static boolean updateAttribute(Attribute attribute) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("UPDATE fitness_app_db.attribute SET name=? WHERE id=?;");
            ps.setString(1, attribute.getName());
            ps.setInt(2, attribute.getId());

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
    
    public static boolean deleteAttribute(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("DELETE FROM fitness_app_db.attribute WHERE id=?");
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        }catch (java.sql.SQLIntegrityConstraintViolationException e) {
        	System.out.println("Sadrzi djecu");
        }
        
        catch (SQLException e) {
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

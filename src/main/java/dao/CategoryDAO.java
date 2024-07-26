package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Attribute;
import dto.Category;

public class CategoryDAO {

	private static final String SQL_EXIST_BY_NAME = "SELECT 1 FROM category WHERE name=? LIMIT 1";
    private static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * FROM fitness_app_db.category WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.category";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO fitness_app_db.category (name) VALUES (?)";
    private static final String SQL_SELECT_ATTRIBUTES_BY_CATEGORY_ID = "SELECT * FROM attribute WHERE category_id=?";


    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public CategoryDAO() {
    }
    

    public static boolean insertCategory(String name) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement(SQL_INSERT_CATEGORY);
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

    

    public static Category getById(Integer id) {
        Category category = null;
        Connection c = null;
        ResultSet rs = null;

        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = c.prepareStatement(SQL_SELECT_CATEGORY_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return category;
    }

    public static List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;

        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = c.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return categories;
    }

    public static Integer getIdByName(String name) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer categoryId = null;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("SELECT id FROM fitness_app_db.category WHERE name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("id");
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

        return categoryId;
    }
    
    public static boolean updateCategory(Category category) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("UPDATE fitness_app_db.category SET name=? WHERE id=?");
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());

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
    
    public static boolean deleteCategory(int categoryId) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement("DELETE FROM fitness_app_db.category WHERE id=?");
            ps.setInt(1, categoryId);

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
    
    public static List<Attribute> getAttributesByCategory(Category category) {
        List<Attribute> attributes = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionPool.checkOut();
            ps = c.prepareStatement(SQL_SELECT_ATTRIBUTES_BY_CATEGORY_ID);
            ps.setInt(1, category.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                attributes.add(new Attribute(rs.getInt("id"), rs.getString("name"), rs.getInt("category_id")));
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

        return attributes;
    }



}

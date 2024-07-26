package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Admin;

public class AdminDAO{
    private static final String SQL_SELECT_ADMIN_BY_USERNAME = "SELECT * FROM fitness_app_db.admin WHERE username=?";
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public AdminDAO() {
    }


    public static Admin getAdminByUsername(String username) {
        Admin admin = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DBUtil.prepareStatement(c, SQL_SELECT_ADMIN_BY_USERNAME, false);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(rs.getInt("id"), rs.getString("first_name"), rs.getString("LastName"), rs.getString("username"), rs.getString("password"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return admin;
    }
}
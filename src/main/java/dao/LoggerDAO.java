package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Logger;

public class LoggerDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_SELECT_ALL = "SELECT * FROM fitness_app_db.log";

    public LoggerDAO() {
    }

    public static List<Logger> getAll() {
        List<Logger> messages = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL, false);
            rs = ps.executeQuery();

            while (rs.next()) {
                messages.add(new Logger(rs.getInt("id"), rs.getString("log"),rs.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return messages;
    }
}
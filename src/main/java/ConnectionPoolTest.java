
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.ConnectionPool;

public class ConnectionPoolTest {

    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        try {
            // Check out a connection
            Connection conn = connectionPool.checkOut();

            // Execute a query
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            // Check in the connection
            connectionPool.checkIn(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

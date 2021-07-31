package DataBaseConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseConnector implements DataBaseInfo {
    private static Connection connection;

    public BaseConnector() throws SQLException, ClassNotFoundException {
        connect();
    }

    public Connection accessConnection() {
        return connection;
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(URL + BASE, USER, PASSWORD);
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

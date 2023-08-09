package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private final Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/retail_shop?useSSL=false", "root", "Asindu123");
    }

    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        return (dbConnection == null ? dbConnection = new DbConnection() : dbConnection);
    }

    public Connection getConnection(){
        return connection;
    }

}
package pe.edu.cibertec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/appweb";
    String user = "root";
    String pwd = "mysql";
    
    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pwd);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        
        return connectionFactory;
    }
}


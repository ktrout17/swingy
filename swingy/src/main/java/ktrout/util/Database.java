package util;

import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:heroes.db";
    private static Connection connection;

    public static void connect() {
        
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to db");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection getConnection() {
        if (connection == null)
            connect();
        return connection;
    }
}

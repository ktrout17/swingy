package util;

import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:sqlite::resource:heroes.db";
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
    
    public static int insert(String name, String className, int lvl, int exp, int atk, int def, int hp) {
    	String query = "INSERT INTO heroes(name, class, level, exp, att, def, hp) VALUES(?, ?, ?, ?, ?, ?)";
    	int id = 0;
    	try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
    		stmt.setString(1, name);
    		stmt.setString(2, className);
    		stmt.setInt(3, lvl);
    		stmt.setInt(4, exp);
    		stmt.setInt(5, atk);
    		stmt.setInt(6, def);
    		stmt.setInt(7, hp);
    		stmt.executeUpdate();
    		
    		String query2 = "SELECT seq FROM sqlite_sequence WHERE name=\"heroes\"";
    		Statement statement = getConnection().createStatement();
    		ResultSet resultSet = statement.executeQuery(query2);
    		if (resultSet.next())
    			id = resultSet.getInt("seq");
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return id;
    }
}

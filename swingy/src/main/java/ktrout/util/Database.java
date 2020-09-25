package ktrout.util;

import java.sql.*;
import java.util.ArrayList;

import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;
import ktrout.model.characters.CreateHero;
import ktrout.model.characters.HeroCreator;

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
		connection = conn;
    }

    public static void close() {
        try {
            if (connection != null)
				connection.close();
			connection = null;
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
    
    public static CreateHero selectHeroById(int id) {
    	String query = "SELECT * from heroes WHERE id = ?";
    	CreateHero hero = null;
    	
    	try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
    		ResultSet resultSet = stmt.executeQuery();
    		if (resultSet.next()) {
    			HeroCreator creator = new HeroCreator();
    			creator.setId(resultSet.getInt("id"));
    			creator.setName(resultSet.getString("name"));
    			creator.setHeroClass(resultSet.getString("class"));
    			creator.setLvl(resultSet.getInt("level"));
    			creator.setExp(resultSet.getInt("exp"));
    			creator.setAtk(resultSet.getInt("att"));
    			creator.setDef(resultSet.getInt("def"));
    			creator.setHp(resultSet.getInt("hp"));
    			
    			if (resultSet.getString("weapon_name") != null)
    				creator.setWeap(new Weapon(resultSet.getString("weapon_name"), resultSet.getInt("weapon_val")));
    			if (resultSet.getString("helm_name") != null) 
    				creator.setHelm(new Helm(resultSet.getString("helm_name"), resultSet.getInt("helm_val")));
    			if (resultSet.getString("armour_name") != null)
    				creator.setArmor(new Armor(resultSet.getString("armour_name"), resultSet.getInt("armour_val")));
    			
    			hero = creator.getHero();
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return hero;
    }
    
    public static ArrayList<String> selectAll() {
    	String query = "SELECT * FROM heroes";
    	ArrayList<String> arrayList = new ArrayList<>();
    	
    	try (Statement stmt = getConnection().createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
    		for (int i = 1; resultSet.next(); i++) {
    			arrayList.add(String.format("%d. %s (%s)", i, resultSet.getString("name"), resultSet.getString("class")));
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return arrayList;
	}
	
	public static void updateHero(CreateHero hero) {
		String query = "UPDATE heroes SET level = ?, exp = ?, att = ?, def = ?, hp = ?, " +
			"weapon_name = ?, weapon_val = ?, helm_name = ?, helm_val = ?, armour_name = ?, armour_val = ? " +
			"WHERE id = ?";

		try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
			stmt.setInt(1, hero.getLvl());
			stmt.setInt(2, hero.getExp());
			stmt.setInt(3, hero.getAtk());
			stmt.setInt(4, hero.getDef());
			stmt.setInt(5, hero.getHp());

			if (hero.getWeap() != null) {
				stmt.setString(6, hero.getWeap().getName());
				stmt.setInt(7, hero.getWeap().getPoints());
			}
			if (hero.getHelm() != null) {
				stmt.setString(8, hero.getHelm().getName());
				stmt.setInt(9, hero.getHelm().getPoints());
			}
			if (hero.getArmor() != null) {
				stmt.setString(10, hero.getArmor().getName());
				stmt.setInt(11, hero.getArmor().getPoints());
			}

			stmt.setInt(12, hero.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

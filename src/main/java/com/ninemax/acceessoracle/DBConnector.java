package com.ninemax.acceessoracle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
	
	private static Properties prop = new Properties();
	private static String prefix = "com/ninemax/accessoracle/";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(prop.getProperty("driverClassName"));
			conn = DriverManager.getConnection(prop.getProperty("jdbcUrl"), 
					prop.getProperty("username"), prop.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void loadProperties(String fileName) {
		try {
			prop.load(DBConnector.class.getClassLoader().getResourceAsStream(prefix + fileName + ".properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

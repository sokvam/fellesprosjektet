package calendarProject;

import java.sql.*;

public class DBConnection {

	private final String JDBC_DRIVER;
	private final String DB_URL;
	private final String USER;
	private final String PASS;
	private Connection conn;
	private Statement stmt;

	public DBConnection() {
		JDBC_DRIVER = "com.mysql.jdbc.Driver";
		DB_URL = "jdbc:mysql://127.0.0.1:3306";
		USER = "root";
		PASS = "qwertyuiop";
		initialize();
	}

	public void initialize() {
		conn = null;
		stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Problems connectiong to the database");
		}
	}

	public void close() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL problems closing database");
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("SQL problems searching through database");
			e.printStackTrace();
		}
		return null;
	}

	public void executeUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL problems updating database");
			e.printStackTrace();
		}
	}

}

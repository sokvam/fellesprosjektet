package calendarProject;

import java.sql.*;

public class SQLMethods {

	public User getUser(String email, String password) {
		int userID = 0;
		String name = "";
		int tlf = 0;
		int calendarID = 0;
		DBConnection conn = new DBConnection();
		String query = "Select * from calendardb.users where email = " + email
				+ " and password = " + password;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				userID = rs.getInt("userID");
				name = rs.getString("name");
				tlf = rs.getInt("tlf");
				calendarID = rs.getInt("calendarID");
			}
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
		}
		conn.close();
		User user = new User(email, password, userID, name, tlf, calendarID); //hvis user hadde hatt en slik konstruktør
		return user;
	}

}

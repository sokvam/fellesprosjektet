package calendarProject;

import java.sql.*;
import java.util.ArrayList;

public class SQLMethods {

	public void newUser(String email, String password, String name, int tlf){
		DBConnection conn = new DBConnection();
		String sql = "insert into calendardb.users values(null, '" + password + "', '" + name + "', '" + email + "', " + tlf + ", null";
		conn.executeUpdate(sql);
		conn.close();
	}
	
	
	public User getUser(String email, String password) {
		User user = new User(email, password);
		
		DBConnection conn = new DBConnection();
		String query = "Select * from calendardb.users where email = " + email
				+ " and password = " + password;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				int userID = rs.getInt("userID");
				String name = rs.getString("name");
				int tlf = rs.getInt("tlf");
				int calendarID = rs.getInt("calendarID");
				user.setUserID(userID);
				user.setName(name);
				user.setPersonalCalendar(calendarID);
				user.setPhoneNumber(tlf);
				
			}
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
		}
		conn.close();
		return user;
	}

	public ArrayList<Integer> findUsersInGroup(int groupID) {
		ArrayList<Integer> ID_list = new ArrayList<Integer>();
		DBConnection conn = new DBConnection();
		String query = "Select userID from calendardb.group where groupID = "
				+ groupID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt("userID");
				ID_list.add(id);
			}
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
		}
		conn.close();
		return ID_list;
	}

}

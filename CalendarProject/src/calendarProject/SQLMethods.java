package calendarProject;

import java.sql.*;
import java.util.ArrayList;

public class SQLMethods {
	
	public int setRoomID(Event event) {
		// Velg det minste rommet som oppfyller f�lgende krav:
		int minSeats = invited.size();
		DBConnection conn = new DBConnection();
		String query = "SELECT top 1 ID FROM Rooms WHERE ID NOT IN (SELECT roomID FROM Events WHERE endDate > " + event.getStartDate() + "AND startDate < " + event.getEndDate() + " GROUP BY roomID) ORDER BY size Asc";
		// M� ha minst minSeats antall plasser
		// Velg deretter alle rom, og trekk fra de som er opptatte:
		/*
		 * SELECT top 1 ID FROM Rooms WHERE ID NOT IN (SELECT
		 * roomID FROM Events WHERE endDate > this.startDate AND
		 * startDate < this.endDate GROUP BY roomID)
		 * ORDER BY Kapasitet Asc
		 */
		//Ogs� finne det minste
		int roomID = 0;
		this.roomID = roomID;
	}


	public void newGroup(String groupName, ArrayList<Integer> users){
		
		DBConnection conn = new DBConnection();
		String query = "Select top 1 groupID from calendardb.groups order by groupID desc";
		ResultSet rs = conn.executeQuery(query);
		int groupID = 1;
		try {
			while(rs.next()){
				groupID += rs.getInt("groupID"); 
			}
		} catch (SQLException e) {
			System.out.println("db problem");
			e.printStackTrace();
		}
		for (int userID : users){
			conn.executeUpdate("insert into calendarcb.group values(" + groupID	+ ", " + userID + ", " + groupName + ")");
		}
		
		String sql = "insert into calendardb.calendars values(null, null, " + groupID + ", 1)";
		conn.executeUpdate(sql);
		conn.close();
	}
W	
	public void newUser(String email, String password, String name, int tlf){
		DBConnection conn = new DBConnection();
		String sql = "insert into calendardb.users values(null, '" + password + "', '" + name + "', '" + email + "', " + tlf + ", null)";
		conn.executeUpdate(sql);
		String query = "select userID from calendarDB.users where email = '" + email + "'";
		ResultSet rs = conn.executeQuery(query);
		int userID = 0;
		try {
			while(rs.next()){
				userID = rs.getInt("userID");
			}
		} catch (SQLException e) {
			System.out.println("db problems");
			e.printStackTrace();
		}
		String sql2 = "insert into calendardb.calendars values(null, " + userID + ", null, 0)";
		String query2 = "select calendarID from calendard.calendars where userID = " + userID;
		conn.executeUpdate(sql2);
		ResultSet rs2 = conn.executeQuery(query2);
		int calendarID = 0; 
		try {
			while(rs2.next()){
				calendarID = rs.getInt("calendarID");
			}
		} catch (SQLException e) {
			System.out.println("db problems");
			e.printStackTrace();
		}
		String sql3 = "update dbcalendars.users set calendarID = " + calendarID + " where userID = " + userID;
		conn.executeUpdate(sql3);
		// lage bruker --> hente brukerID --> lage kalender --> hente kalenderID og sende til bruker
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
	
	public ArrayList<Integer> getEventsFromCalendar() {
		
	}

	public int getID(String email){
		int userID;
		DBConnection conn = new DBConnection();
		String query = "Select userID from calandardb.users where email = '" + email + "'";
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				userID = rs.getInt("userID");
				
			}
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
		}
		conn.close();
		return userID;	
	}
}

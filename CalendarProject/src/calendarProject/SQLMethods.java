package calendarProject;

import java.sql.*;
import java.util.ArrayList;

public class SQLMethods {
	
	//VI trenger egne gruppefunksjoner.
	
	
	public boolean isEventID(int eventID) {
		boolean result = false;
		DBConnection conn = new DBConnection();
		String query = "SELECT eventID FROM calendardb.events WHERE eventID = " + eventID;
		ResultSet rs = conn.executeQuery(query);
		
		try {
			result = rs.next();
				
		} catch (SQLException e) {
			System.out.println("DB Problems");
			e.printStackTrace();
		}
		conn.close();
		return result;
	}
	
	public void deleteParticipant(int calendarID, int eventID) {
		DBConnection conn = new DBConnection();
		String query = "DELETE * FROM calendardb.calendarevents WHERE calendarID = " + calendarID + "AND eventID = " + eventID;
		conn.executeQuery(query);
		conn.close();
	}
	
	public void updateEvent(int eventID, int updateField, String update) {
		DBConnection conn = new DBConnection();
		String query;
		try {
			switch (updateField) {
			case 1: //name
				query = "UPDATE calendardb.events SET name = '" + update + "' WHERE eventID = " + eventID;
				conn.executeQuery(query);
				break;
			case 2: //startDateTimeOperations
				query = "UPDATE calendardb.events SET startDateTime = '" + update + "' WHERE eventID = " + eventID;
				conn.executeQuery(query);
				break;
			case 3: //endDateTimeOperations
				query = "UPDATE calendardb.events SET endDateTime = '" + update + "' WHERE eventID = " + eventID;
				conn.executeQuery(query);
				break;
			case 4: //description
				query = "UPDATE calendardb.events SET description = '" + update + "' WHERE eventID = " + eventID;
				conn.executeQuery(query);
				break;
			default:
				System.out.println("Ingen endring utført.");
			}
		} catch (Exception e) {
			System.out.println("DB problem");
			e.printStackTrace();
		}
		conn.close();		
	}
	
	public ArrayList<Integer> getEventsForDate(String date, int userID) {
		ArrayList<Integer> events = new ArrayList<Integer>();
		DBConnection conn = new DBConnection();
		String query = "SELECT eventID FROM calendardb.events WHERE (DATE(" + date + ") = DATE(start_DateTime) OR "
				+ "(DATE(" + date + ") > DATE(start_DateTime) AND DATE(" + date + ") <= DATE(end_DateTime))) AND userID = " + userID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				int eventID = rs.getInt("eventID");
				events.add(eventID);
			}
		} catch (SQLException e) {
			System.out.println("DB problems");
			e.printStackTrace();
		}
		conn.close();
		return events;
	}
	
	public Event getEventInfo(int eventID) {
		Event event = new Event("Foo", "Faa", "Bar", "Bas");
		DBConnection conn = new DBConnection();
		String query = "SELECT * FROM calendardb.events WHERE eventID = " + eventID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				String name = rs.getString("event_Name");
				String start_datetime = rs.getString("start_datetime");
				String end_datetime = rs.getString("end_datetime");
				String description = rs.getString("description");
				event = new Event(name, start_datetime, end_datetime, description);
			}
		} catch (SQLException e) {
			System.out.println("db problems");
			e.printStackTrace();
		}
		conn.close();
		return event;
	}
	
	public void deleteEvent(int eventID) {
		DBConnection conn = new DBConnection();
		String query1 = "DELETE FROM calendardb.events WHERE eventID = " + eventID;
		String query2 = "DELETE FROM calendardb.calendarEvent WHERE eventID = " + eventID;
		conn.executeUpdate(query1);
		conn.executeUpdate(query2);
		conn.close();
	}
	
	public void createEvent(ArrayList<Integer> invited_userIDs, String start_datetime, String end_datetime, String description, int size, int owner_userID, String event_name) {
		int roomID = findRoom(size, start_datetime, end_datetime);
		DBConnection conn = new DBConnection();
		
		String sql1 = "Insert into calendardb.events values(null, " + start_datetime + ", " + end_datetime + ", '" + description + "', "+ roomID + ", " + owner_userID + ", '" + event_name + "')";
		conn.executeUpdate(sql1);
		
		int eventID = -1;
		String query = "Select eventID from calendardb.events where start_datetime = " + start_datetime + " AND event_name = '" + event_name + "' AND userID = " + owner_userID + " And roomID = " + roomID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while(rs.next()){
				eventID = rs.getInt("eventID");
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
		}
				
		for (int id : invited_userIDs){
			String query2 = "Select calendarID from calendardb.users where userID = " + id;
			ResultSet rs2 = conn.executeQuery(query2);
			try {
				while (rs2.next()){
					int calendarID = rs2.getInt("calendarID");
					String sql2 = "Insert into calendardb.calendarEvent values(" + calendarID + ", " + eventID + ")";
					conn.executeUpdate(sql2);
				}
			} catch (SQLException e) {
				System.out.println("sql error");
				e.printStackTrace();
			}
		}
		conn.close();
	}

	public int findRoom(int size, String start_datetime, String end_datetime) {
		DBConnection conn = new DBConnection();
		String query = "SELECT roomID FROM calendardb.Rooms WHERE roomID NOT IN (SELECT roomID FROM calendardb.Events WHERE end_datetime > " +
				start_datetime + " AND start_datetime < " + end_datetime + ") AND size >= " + size + " GROUP BY roomID ORDER BY size Asc limit 1";
		System.out.println(query);
		ResultSet rs = conn.executeQuery(query);
		int roomID = -1;
		try {
			while(rs.next()){
				roomID = rs.getInt("roomID");
			}
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
		}
		
		conn.close();
		return roomID;
	}
	
	public ArrayList<Integer> findRoomList() {
		//Denne må bygges ferdig.
		ArrayList<Integer> liste = new ArrayList<Integer>();
		return liste;
	}

	public void newGroup(String groupName, ArrayList<Integer> users){
		
		DBConnection conn = new DBConnection();
		String query = "Select groupID from calendardb.groups order by groupID desc limit 1";
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
			conn.executeUpdate("insert into calendardb.Groups values(" + groupID	+ ", " + userID + ", '" + groupName + "')");
		}
		
		String sql = "insert into calendardb.calendars values(null, null, " + groupID + ", 1)";
		conn.executeUpdate(sql);
		conn.close();
	}
	
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
			System.out.println("db problems1");
			e.printStackTrace();
		}
		String sql2 = "insert into calendardb.calendars values(null, " + userID + ", null, 0)";
		String query2 = "select calendarID from calendardb.calendars where userID = " + userID;
		conn.executeUpdate(sql2);
		ResultSet rs2 = conn.executeQuery(query2);
		int calendarID = 0; 
		try {
			while(rs2.next()){
				calendarID = rs.getInt("calendarID");
			}
		} catch (SQLException e) {
			System.out.println("db problems2");
			e.printStackTrace();
		}
		String sql3 = "update calendardb.users set calendarID = " + calendarID + " where userID = " + userID;
		conn.executeUpdate(sql3);
		conn.close();
	}
	
	public boolean checkPassword(String email, String password) {
		String dBPassword = "";
		
		DBConnection conn = new DBConnection();
		String query = "Select password from calendardb.users where email = '" + email + "'";
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				dBPassword = rs.getString("password");
			}
		} catch (SQLException e) {
			System.out.println("db problems");
			e.printStackTrace();
		}
		conn.close();
		return dBPassword.equals(password);
		
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
		String query = "Select userID from calendardb.Groups where groupID = "
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
	
	public ArrayList<Integer> getEventsFromCalendar(int calendarID) {
		ArrayList<Integer> eventID_list = new ArrayList<Integer>();
		DBConnection conn = new DBConnection();
		String query = "Select eventID from calendars.calendarevent where calendarID = " + calendarID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while(rs.next()){
				int eventID = rs.getInt("eventID");
				eventID_list.add(eventID);
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
		}
		return eventID_list;
	}

	public int getUserID(String email){
		int userID = -1;
		DBConnection conn = new DBConnection();
		String query = "Select userID from calendardb.users where email = '" + email + "'";
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

	public User getUser(int userID){
		DBConnection conn = new DBConnection();
		String query = "Select * from calendardb.users where userID = " + userID;
		ResultSet rs = conn.executeQuery(query);
		String password = "";
		String name = "";
		String email = "" ;
		int tlf = -1; 
		int calendarID = -1;
		try {
			while(rs.next()){
				password = rs.getString("password");
				name = rs.getString("name");
				email = rs.getString("email");
				tlf = rs.getInt("tlf");
				calendarID = rs.getInt("calendarID");
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
		}
		User user = new User(userID, password, name, email, tlf, calendarID);
		conn.close();
		return user;
	}
}

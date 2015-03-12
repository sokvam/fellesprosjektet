package calendarProject;

import java.sql.*;
import java.util.ArrayList;

public class SQLMethods {
	
	public boolean isEventID(int eventID) {
		boolean result = false;
		DBConnection conn = new DBConnection();
		String query = "SELECT eventID FROM calendardb.events WHERE eventID = " + eventID;
		ResultSet rs = conn.executeQuery(query);
		conn.close();
		try {
			result = rs.next();
				
		} catch (SQLException e) {
			System.out.println("DB Problems");
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateEvent(int eventID, int updateField, String update) {
		switch (updateField) {
		case 1: //name
			break;
		case 2: //Dato
			break;
		case 3: //Starttid
			break;
		case 4: //slutttid
			break;
		case 5: //description
			break;
		default:
			System.out.println("Ingen endring utført.");
			break;
		}
		
	}
	public ArrayList<Integer> getEventsForDate(String date, int userID) {
		ArrayList<Integer> events = new ArrayList<Integer>();
		DBConnection conn = new DBConnection();
		String query = "SELECT * FROM calendardb.events WHERE date = '" + date + "' AND userID = " + userID;
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
		Event event = new Event("Foo", "Faa", "Bar");
		DBConnection conn = new DBConnection();
		String query = "SELECT * FROM calendardb.events WHERE eventID = " + eventID;
		ResultSet rs = conn.executeQuery(query);
		try {
			while (rs.next()) {
				String name = rs.getString("eventName");
				String start_datetime = rs.getString("start_datetime");
				String end_datetime = rs.getString("end_datetime");
				event = new Event(name, start_datetime, end_datetime);
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
		String sql1 = "DELETE FROM calendardb.events WHERE eventID = " + eventID;
		String sql2 = "DELETE FROM calendardb.calendarEvent WHERE eventID = " + eventID;
		conn.executeUpdate(sql1);
		conn.executeUpdate(sql2);
		conn.close();
	}
	
	public void createEvent(ArrayList<Integer> invited_userIDs, String start_datetime, String end_datetime, String description, int size, int owner_userID, String event_name) {
		int roomID = findRoom(size, start_datetime, end_datetime);
		DBConnection conn = new DBConnection();
		
		String sql1 = "Insert into calendardb.events values('null, '" + start_datetime + "', '" + end_datetime + "', '" + description + "', "+ roomID + ", " + owner_userID + ", '" + event_name + "')";
		conn.executeUpdate(sql1);
		
		int eventID = -1;
		String query = "Select eventID from calendardb.events where start_datetime = " + start_datetime + " AND event_name = " + event_name + " AND userID = " + owner_userID + " And roomID = " + roomID;
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
			String sql2 = "Insert into calendardb.calendarEvent values(" + id + ", " + eventID + ")";
			conn.executeUpdate(sql2);
		}
		conn.close();
	}

	public int findRoom(int size, String start_datetime, String end_datetime) {
		DBConnection conn = new DBConnection();
		String query = "SELECT top 1 ID FROM Rooms WHERE ID NOT IN (SELECT roomID FROM Events WHERE end_datetime > " + start_datetime + "AND start_datetime < " + end_datetime + "AND size < " + size + " GROUP BY roomID) ORDER BY size Asc";
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
			conn.executeUpdate("insert into calendarcb.Groups values(" + groupID	+ ", " + userID + ", " + groupName + ")");
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
		conn.close();
	}
	
	public boolean checkPassword(String email, String password) {
		String dBPassword = "";
		
		DBConnection conn = new DBConnection();
		String query = "Select password from calandardb.users where email = '" + email + "'";
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

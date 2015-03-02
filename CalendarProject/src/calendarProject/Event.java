package calendarProject;

import java.util.ArrayList;
import java.util.Date;

public class Event {

	private int ID;

	private String name, info;

	private Date date;

	private Room room;

	private ArrayList<User> invited;

	public Event(String name, Date date) {
		this.setName(name);
		this.setDate(date);
	}
	
	public void inviteUser(int userID) {
		// finn riktig bruker ved å søke opp i databasen
		// og legg til personen i lista
		// deretter må personen blir notified.
		User user = new User();
		invited.add(user);
	}

	public void inviteUsers(ArrayList<Integer> userIDs) {
		for (int userID : userIDs) {
			inviteUser(userID);
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}

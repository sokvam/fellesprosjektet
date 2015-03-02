package calendarProject;

import java.util.ArrayList;
import java.util.Date;

public class Event {

	private int ID;

	private String name;

	private String info;

	private Date date;

	private Room room;

	private ArrayList<User> invited;

	public Event(String name, Date date) {
		this.setName(name);
		this.setDate(date);
	}

	public Event getEvent() {
		return this;
	}

	public void notify(int userID, Event event) {
		// denne metoden må endres totalt sannsynligvis
		/*
		 * int n = invited.size(); for (int i = 0; i < n; i++) { invited.get(i);
		 * }
		 */
	}

	public void inviteUser(int userID) {
		// finn riktig bruker ved å søke opp i databasen
		// og legg til personen i lista
		// deretter må personen blir notified.
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

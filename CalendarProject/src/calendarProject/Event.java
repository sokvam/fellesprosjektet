package calendarProject;

import java.util.ArrayList;
import java.util.Date;

public class Event {

	private int ID;

	private String name, info;

	private Date startDate;

	private Date endDate;

	private int roomID;

	private ArrayList<User> invited;

	public Event(String name, Date startDate, Date endDate) {
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public void inviteUser(int userID) {
		// finn riktig bruker ved � s�ke opp i databasen
		// og legg til personen i lista
		// deretter m� personen blir notified.
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public int getRoom() {
		return roomID;
	}

	public void setRoomID() {
		SQLMethods sqlm = new SQLMethods();
		this.roomID = sqlm.setRoomID(this);
		int roomID = 0;
		this.roomID = roomID;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}

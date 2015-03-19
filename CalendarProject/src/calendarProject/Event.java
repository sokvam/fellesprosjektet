package calendarProject;

import java.util.ArrayList;

public class Event {

	private int ID;

	private String name, description, startDateTime, endDateTime;

	private int roomID;

	private ArrayList<Integer> invitedID;
	
	private ArrayList<User> invited;

	public Event(String name, String startDateTime, String endDateTime, String description) {
		this.name = name;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.description = description;
		invitedID = new ArrayList<Integer>();
	}
	
	public void initiateUsers(ArrayList<Integer> userIDs) {
		for (int userID : userIDs) {
			invitedID.add(userID);
		}
	}
	
	public void initiateUser(int userID) {
		invitedID.add(userID);
	}

/*
	public void inviteUsers(ArrayList<Integer> userIDs) {
		invited = new ArrayList<User>();
		SQLMethods sqlm = new SQLMethods();
		for (int userID : userIDs) {
			User user = sqlm.getUser(userID);
			invited.add(user);
		}
	}
*/
	public int getID() {
		return ID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getRoom() {
		return roomID;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public int getInvNumb(){
		return invited.size();
	}
	
	public String getInvitedString() {
		String participants = "";
		for (User user : invited) {
			participants = participants + user.getName() + " ,";
		}
		participants = participants.substring(0, participants.length() -1);
		participants = participants + ".";
		return participants;
	}
}

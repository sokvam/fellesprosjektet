package calendarProject;

import java.util.ArrayList;

public class User {

	private String email, password, name;

	private int phoneNumber, userID, calendarID;

	private ArrayList<Integer> groupCalendars;
	
	public User(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	public User(int userID, String password, String name, String email, int tlf, int calendarID){
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = tlf;
		this.calendarID = calendarID;
	}

	public void addGroupCalendar(int calendarID) {
		// Telleren count er til for at funksjonene i User-klassen og
		// GroupCalendar-klassen
		// ikke skal kalle hverandre i evig l�kke.
		// Count fungerer som f�lger: I selve programmet skal funksjonen alltid
		// kalles med verdien 0.
		// N�r groupCal.addMember kalles (se linje ***), �ker den med 1.
		// P� samme m�te �ker telleren med �n n�r GroupCalendar kaller
		// User.addGroupCalendar.
		// For � hindre at funksjonene kaller hverandre i sirkel, skal
		// funksjonen kun kalles
		// hvis verdien "count" er mindre enn 2. Den har da b�de lagret brukeren
		// som en
		// del av kalenderen, og kalenderen i listen over brukerens kalendere.
		// Trololo

		//if (count < 2 && count >= 0) {
		groupCalendars.add(calendarID);
			//groupCal.addMember(this, count + 1); // ***
		//}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCalendarID() {
		return calendarID;
	}
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public int getUserID(){
		return userID;
	}

	public void setPersonalCalendar(int calendarID) {
		//Telleren count fungerer som i funksjonen addGroupCalendar
		this.calendarID = calendarID;
	}
}

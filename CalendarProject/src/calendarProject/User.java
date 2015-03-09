package calendarProject;

import java.util.ArrayList;

public class User {

	private String email, password, name;

	private int phoneNumber;

	private PersonalCalendar personalCalendar;

	private ArrayList<GroupCalendar> groupCalendars;
	
	public User(String email, String password){
		this.email = email;
		this.password = password;
	}

	public void addGroupCalendar(GroupCalendar groupCal, int count) {
		// Telleren count er til for at funksjonene i User-klassen og
		// GroupCalendar-klassen
		// ikke skal kalle hverandre i evig løkke.
		// Count fungerer som følger: I selve programmet skal funksjonen alltid
		// kalles med verdien 0.
		// Når groupCal.addMember kalles (se linje ***), øker den med 1.
		// På samme måte øker telleren med én når GroupCalendar kaller
		// User.addGroupCalendar.
		// For å hindre at funksjonene kaller hverandre i sirkel, skal
		// funksjonen kun kalles
		// hvis verdien "count" er mindre enn 2. Den har da både lagret brukeren
		// som en
		// del av kalenderen, og kalenderen i listen over brukerens kalendere.
		// Trololo

		if (count < 2 && count >= 0) {
			groupCalendars.add(groupCal);
			groupCal.addMember(this, count + 1); // ***
		}
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

	public PersonalCalendar getPersonalCal() {
		return personalCalendar;
	}

	public void setPersonalCalendar(PersonalCalendar personalCal, int count) {
		//Telleren count fungerer som i funksjonen addGroupCalendar
		if (count < 2 && count >= 0) {
			this.personalCalendar = personalCal;
			personalCal.setOwner(this, count + 1);
		}
	}
}

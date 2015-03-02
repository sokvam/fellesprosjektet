package calendarProject;

public class User {

	private String email;

	private String name;

	private int phoneNumber;

	private PersonalCalendar personalCal;

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
		return personalCal;
	}

	public void setPersonalCal(PersonalCalendar personalCal) {
		this.personalCal = personalCal;
		personalCal.setOwner(this);
	}
	
}

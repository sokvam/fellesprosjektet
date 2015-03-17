package calendarProject;

public class PersonalCalendar extends Calendar {
	
	private int calendarID;
	
	public PersonalCalendar(int calendarID){
		this.calendarID = calendarID;
	}

	public int getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(int calendarID) {
		this.calendarID = calendarID;
	}


	
	/*
	public void setOwner(User owner, int count) {
		if (count < 2 && count >= 0) {
			this.owner = owner;
			owner.setPersonalCalendar(this, count + 1);
		}
	}
	*/
}

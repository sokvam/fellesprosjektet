package calendarProject;

public class PersonalCalendar extends Calendar {
	
	private int userID;
	
	public PersonalCalendar(int userID){
		this.userID = userID;
	}


	
	/*
	public void setOwner(User owner, int count) {
		if (count < 2 && count >= 0) {
			this.owner = owner;
			owner.setPersonalCalendar(this, count + 1);
		}
	}
	*/
	public User getOwner() {
		return this.owner;
	}
}

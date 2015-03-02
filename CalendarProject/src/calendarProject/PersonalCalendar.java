package calendarProject;

public class PersonalCalendar extends Calendar {

	private User owner;

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public User getOwner(){
		return this.owner;
	}
}

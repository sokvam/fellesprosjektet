package calendarProject;

public class PersonalCalendar extends Calendar {

	private User owner;

	public void setOwner(User owner, int count) {
		if (count < 2 && count >= 0) {
			this.owner = owner;
			owner.setPersonalCalendar(this, count + 1);
		}
	}

	public User getOwner() {
		return this.owner;
	}
}

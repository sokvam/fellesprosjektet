package calendarProject;

import java.util.Date;

public class PersonalCalendar extends Calendar {

	public User owner;

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void addEvent(String name, Date date) {
		Event event = new Event(name, date);
		events.add(event);
	}

	public void removeEvent(int ID) {
		for (int i = 0; i < events.size() - 1; i++) {
			if (events.get(i).getID() == ID) {
				events.remove(i);
				i = events.size();
			}
		}
	}
}

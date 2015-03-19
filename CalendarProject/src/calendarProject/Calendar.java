package calendarProject;

import java.util.ArrayList;

public abstract class Calendar {

	ArrayList<Event> events;

	public void removeEvent(int ID) {
		for (int i = 0; i < events.size() - 1; i++) {
			if (events.get(i).getID() == ID) {
				events.remove(i);
				i = events.size();
			}
		}
	}

	public void addEvent(String name, String startDate, String endDate, String description) {
		Event event = new Event(name, startDate, endDate, description);
		events.add(event);
	}
}

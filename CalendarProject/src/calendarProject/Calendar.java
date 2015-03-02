package calendarProject;

import java.util.ArrayList;
import java.util.Date;

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

	public void addEvent(String name, Date date) {
		Event event = new Event(name, date);
		events.add(event);
	}

}

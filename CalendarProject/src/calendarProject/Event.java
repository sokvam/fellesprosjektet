package calendarProject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Event {

	private int eventID;

	private String eventName;

	private String eventInfo;
	
	private Date date;

	private int timeOfDay;

	private Room room;

	private ArrayList<User> invited;
	
	public Event getEvent(){
		return this;
	}
	
	Set<Date, timeOfDay>(Type arg) {
		
	}

	removeInvite(User person) {
		
	}

	invite(User person) {
		
	}

	public void notify(String string) {
		//denne metoden må endres totalt sannsynligvis
		int n = invited.size();
		for (int i = 0; i < n; i++) {
			invited.get(i).
		}
	}
}

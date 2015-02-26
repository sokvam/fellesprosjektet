package calendarProject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

	int eventID;

	String eventName;

	String eventInfo;

	Date date;

	int timeOfDay;

	Room room;

	List invited = new ArrayList<User>();
	
	public void getEvent(){
		
	}
}

package calendarProject;

import java.util.Date;

public class PersonalCalendar extends Calendar {
	
	public User owner;
	
	public void setOwner(User owner){
		this.owner = owner;
	}
	
	
	public static void main(String[] args) {
		final Date date = new Date();
		System.out.println(date);
	}
	
	
	
	
	
	public void addEvent(String name, Date date){
		Event event = new Event()
	}
}

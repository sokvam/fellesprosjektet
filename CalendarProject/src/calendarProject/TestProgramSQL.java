package calendarProject;

import java.util.ArrayList;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){

	}
	
	public static void main(String[] args) {
		TestProgramSQL testProg = new TestProgramSQL();
		testProg.init();
		testProg.run();
	}
	/*Fungerende: 
	* isEventID(int eventID)
	* getEventsForDate(int calendarID, int eventID)
	* getEventInfo(int eventID)
	* createEvent
	* findRoom
	* newGroup 
	* newUser
	* checkPassword
	* getUser(String email, String password)
	* findUsersInGroup
	* getEventsFromCalendar
	* getUserID
	* getUser(int userID)
	*/

}

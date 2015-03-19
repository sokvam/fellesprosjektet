package calendarProject;

import java.util.ArrayList;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){
		ArrayList<Integer> invited = new ArrayList<Integer>();
		invited.add(702);
		invited.add(703);
		sqlm.createEvent(invited, "'2015-05-05 03:03:03'" , "'2015-05-05 04:00:00'", "this is a party" , 9, 700, "fezd");
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
	* creatNotification
	*/

}

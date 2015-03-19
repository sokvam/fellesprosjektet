package calendarProject;

import java.util.ArrayList;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){
		ArrayList<Integer> noti = sqlm.getNotifications(702);
		//for (int i : noti){
			//System.out.println(i);
		//}
		System.out.println(sqlm.getUserID("orjan123@hotmail.com"));
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

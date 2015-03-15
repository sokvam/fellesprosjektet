package calendarProject;

import java.util.ArrayList;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){
		ArrayList<Integer> users = new ArrayList<>();
		users.add(7);
		users.add(9);
		users.add(3);
		
		sqlm.newUser("test@delete.no", "passord", "navn navn", 12341223);
		//problemer med calendarID
	}
	
	public static void main(String[] args) {
		TestProgramSQL testProg = new TestProgramSQL();
		testProg.init();
		testProg.run();
	}
	/*Fungerende: 
	* isEventID
	* getEventsForDate
	* getEventInfo
	* createEvent
	* findRoom
	* newGroup 
	* 
	*
	*
	*/

}

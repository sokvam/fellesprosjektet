package calendarProject;

import java.util.ArrayList;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){
		ArrayList<Integer> inv = new ArrayList<Integer>();
		inv.add(702);
		inv.add(703);
		inv.add(704);
		sqlm.createEvent(inv, "'2014-09-14 00:00:01'", "'2014-09-15 07:00:01'", "yolo", 7, 82, "begravelse");
		//System.out.println(getDescription());
		//System.out.println(sqlm.findRoom(5, "'2014-09-09 00:00:01'", "'2014-09-09 07:00:01'"));
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
	* findRoom
	* createEvent
	*
	*
	*
	*/

}

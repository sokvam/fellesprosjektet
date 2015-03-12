package calendarProject;

public class TestProgramSQL {
	
	SQLMethods sqlm;
	
	public void init(){
		sqlm = new SQLMethods();
	}
	
	public void run(){
		Event e = sqlm.getEventInfo(92);
		System.out.println(e.getDescription());
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
	*
	*
	*/

}

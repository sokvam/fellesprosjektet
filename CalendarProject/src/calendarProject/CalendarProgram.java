package calendarProject;

import java.util.*;

public class CalendarProgram {

	//Database instansering
	Scanner scanner;
	CalendarIO io;
	
	private void init() {
		io = new CalendarIO();
		scanner = new Scanner(System.in);
	}
	
	private void run() {
		io.start();
	}
	
	
	
	public static void main(String[] args) {
		CalendarProgram program = new CalendarProgram();
		program.init();
		program.run();
	}

}

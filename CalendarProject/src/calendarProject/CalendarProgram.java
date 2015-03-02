package calendarProject;

import java.util.*;

public class CalendarProgram {

	//Database instansering
	Scanner scanner;
	CalendarIO printer;
	
	private void init() {
		printer = new CalendarIO();
		scanner = new Scanner(System.in);
	}
	
	private void run() {
		printer.mainMenu();
		int input;
		while (scanner.hasNext()) {
			input = scanner.nextInt();
			printer.subMenu(input);
		}
	}
	
	
	
	public static void main(String[] args) {
		CalendarProgram program = new CalendarProgram();
		program.init();
		program.run();
		
	}

}

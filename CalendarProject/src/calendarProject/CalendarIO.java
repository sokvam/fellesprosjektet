package calendarProject;

import java.text.SimpleDateFormat;
import java.util.*;

import sun.util.calendar.Gregorian;

public class CalendarIO {
	Scanner scanner = new Scanner(System.in);
	Date tdate = new Date();
	
	// Denne klassen er i praksis nÃ¥ brukt som en printer og ikke en komplett
	// IO. Dette er for Ã¥ teste muligheten for Ã¥ lagre div. menyer pÃ¥ ett
	// sted.


	public void loggin() {
		System.out.println( "Skriv inn brukernavn: ");
		String userName = scanner.next();
		System.out.println("Skriv inn passord: ");
		String password = scanner.next();
		
		//noe for å sjekke om passord og username stemmer.
		//hvis det stemmer kjør mainmenu(); hvis ikke print feil passord eller brukernavn og kjør loggin();
	}
	
	public void mainMenu() {
		System.out.println("\nDette er hovedmenyen:");
		System.out.println("1. Vis dagens eventer");
		System.out.println("2. Vis en bestemt dato");
		System.out.println("3. Vis en bestemt måned");
		System.out.println("4. Opprett event");
		System.out.print("Skriv inn nret på det du ønsker å utføre.");
		System.out.println("P.s du kan til alltid returnere hit ved å trykke hm");
		int input = scanner.nextInt();
		switch (input) {
		case 1:
			showEvent(tdate);
		case 2:
			System.out.println("Skriv inn dato på formen ????:");
			String inputdate = scanner.next();
			//noe for å konvertere string til dato
			Date date = inputdate.to;
			showEvent(date);
		case 3:
		case 4:
		}
	}

	public void showEvents(Date tdate) {
		//viser en liste over dagens eventer
		//må hentes ut av database
		
		System.out.println("Velg et event ved å skrive inn nr:");
		int input = scanner.nextInt();
		//viser all informasjon om den valgte eventen
		//man er nå inne i en event
		System.out.println("Hva ønsker du å gjøre?");
		System.out.println("1. Endre event");
		System.out.println("2. Slett event");
		System.out.println("3. Tilbake");
		
		int input2 = scanner.nextInt();
		switch (input2) {
		case 1:
			//noe for å endre
		
		case 2:
			//slette event
		case 3:
			showEvents(tdate)
		}	
	}
	
	

	public void printCalendar() {
		GregorianCalendar cal = new GregorianCalendar(2015,8,2);
		int year = cal.get(GregorianCalendar.YEAR);
		int daysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // antall dager i måneden
		String monthString = new SimpleDateFormat("MMMMMMMMM").format(cal.getTime()); // måneden som en string
		int dayNumberInMonth = cal.get(GregorianCalendar.DAY_OF_MONTH); //1-indeksert
		int dayNumberInWeek = (cal.get(GregorianCalendar.DAY_OF_WEEK) + 6) % 7; //0-indeksert, starter mandag
		int firstDayOfMonth = ((7 - (dayNumberInMonth % 7)) + dayNumberInWeek) % 7;//første ukedagen i måneden,
		//nullindeksert (0 er mandag, 1 er tirsdag, ..., 6 er søndag
		System.out.println();
		System.out.println("\t\t\t" + monthString + " " +  year);
		System.out.println();
		System.out.println("\tman\ttir\tons\ttor\tfre\tlør\tsøn");
		int dayCount = 1;
		int i = 0;
		int currentWeekday = firstDayOfMonth;
		boolean run = true;
		while(run){
			if(i < firstDayOfMonth){  //Hvis ikke måneden har begynt enda, print tomme felter
				System.out.print("\t");
				i++;
			} else if (dayCount <= daysInMonth){
				System.out.print("\t" + dayCount);
				if(dayCount % 32 == 0){ //						FYLL INN: Hvis dagen har en avtale
					System.out.print("*");
				}
				dayCount++;
				if(currentWeekday == 6){ //Hvis søndag: Linjeskift
					System.out.println("");
				}
				currentWeekday = (currentWeekday + 1) % 7; //Oppdater ukedag
			} else {
				run = false;
			}
		}
		
	}

	public static void main(String[] args) {
		GregorianCalendar cal = new GregorianCalendar();
		//System.out.println(cal.get(GregorianCalendar.DAY_OF_MONTH));
		CalendarIO io = new CalendarIO();
		io.printCalendar();
	}
}

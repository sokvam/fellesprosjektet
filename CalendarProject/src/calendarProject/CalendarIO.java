package calendarProject;

import java.text.SimpleDateFormat;
import java.util.*;

import sun.util.calendar.Gregorian;

public class CalendarIO {
	Scanner scanner = new Scanner(System.in);

	// Denne klassen er i praksis nå brukt som en printer og ikke en komplett
	// IO. Dette er for å teste muligheten for å lagre div. menyer på ett
	// sted.

	int dybde = 0;

	public void loggin() {
		System.out.println( "Skriv inn brukernavn: ");
		String userName = scanner.next();
		System.out.println("Skriv inn passord: ");
		String password = scanner.next();
		
		//noe for � sjekke om passord og username stemmer.
		//hvis det stemmer kj�r mainmenu(); hvis ikke print feil passord eller brukernavn og kj�r loggin();
	}
	
	public void mainMenu() {
		System.out.println("\nDette er hovedmenyen:");
		System.out.println("1. ");
		System.out.println("2. Ørjan er Sondres sjelefrende.");
		System.out.println("3. Andrea Marie elsker rapporter.");
		System.out.println("4. Jarl har nice skjegg.");
		System.out.print("Enter input: ");
	}

	public void subMenu(int subMenu) {
		if (dybde == 0) {
			switch (subMenu) {
			case 0:
				if (dybde > 0)
					dybde -= 1;
				break;
			case 1:
				System.out.println("Hvor mye/hva slags pikk elsker Tobias?");
				System.out.println("1. All pikk.");
				System.out.println("2. Kun hvit.");
				System.out.println("3. Kun varm.");
				System.out.println("4. Korte peniser.");
				System.out.println("5. Lange peniser.");
				dybde += 1;
				break;
			case 2:
				System.out.println("Svaret er ja, men hvor ja?");
				System.out.println("1. Uendelig ja.");
				System.out.println("2. Særs ja.");
				System.out.println("3. Ja, for alltid.");
				dybde += 1;
				break;
			case 3:
				System.out.println("Men hva slags rapporter liker hun best?");
				System.out.println("Lange rapporter.");
				System.out.println("Korte rapporter.");
				System.out.println("Tykke rapporter.");
				System.out.println("Tynne rapporter.");
				dybde += 1;
				break;
			}
		}
	}

	public void printCalendar() {
		GregorianCalendar cal = new GregorianCalendar(2015,8,2);
		int year = cal.get(GregorianCalendar.YEAR);
		int daysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // antall dager i m�neden
		String monthString = new SimpleDateFormat("MMMMMMMMM").format(cal.getTime()); // m�neden som en string
		int dayNumberInMonth = cal.get(GregorianCalendar.DAY_OF_MONTH); //1-indeksert
		int dayNumberInWeek = (cal.get(GregorianCalendar.DAY_OF_WEEK) + 6) % 7; //0-indeksert, starter mandag
		int firstDayOfMonth = ((7 - (dayNumberInMonth % 7)) + dayNumberInWeek) % 7;//f�rste ukedagen i m�neden,
		//nullindeksert (0 er mandag, 1 er tirsdag, ..., 6 er s�ndag
		System.out.println();
		System.out.println("\t\t\t" + monthString + " " +  year);
		System.out.println();
		System.out.println("\tman\ttir\tons\ttor\tfre\tl�r\ts�n");
		int dayCount = 1;
		int i = 0;
		int currentWeekday = firstDayOfMonth;
		boolean run = true;
		while(run){
			if(i < firstDayOfMonth){  //Hvis ikke m�neden har begynt enda, print tomme felter
				System.out.print("\t");
				i++;
			} else if (dayCount <= daysInMonth){
				System.out.print("\t" + dayCount);
				if(dayCount % 32 == 0){ //						FYLL INN: Hvis dagen har en avtale
					System.out.print("*");
				}
				dayCount++;
				if(currentWeekday == 6){ //Hvis s�ndag: Linjeskift
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

package calendarProject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

public class CalendarIO {

	// Denne klassen er i praksis nå brukt som en printer og ikke en komplett
	// IO. Dette er for å teste muligheten for å lagre div. menyer på ett
	// sted.

	// Tobias koder, stay out! 9. mars 13:45


	SQLMethods sql = new SQLMethods();
	Scanner scanner = new Scanner(System.in);
	String email, password;
	User userID;


	public void loggin() {
		System.out.println( "Skriv inn email: ");
		String userName = scanner.next();
		System.out.println("Skriv inn passord: ");
		String password = scanner.next();
		if (sql.checkPassword(String email, String password) {
			
			mainMenu();
		} else {
			System.out.println("Brukernavn og passord stemte ikke, prøv på nytt");
		}
	}
		
		

	public void start() {
		System.out.println("1. Opprett ny bruker");
		System.out.println("2. Logg inn");
		int choice = scanner.nextInt();
		if (choice == 1) {
			createUser();
		} else if (choice == 2) {
			loggin();
		} else {
			System.out.println("Skjønte ikke input.");
			start();
		}
	}

	public void createUser() {
		System.out.print("Skriv inn din epost: ");
		email = scanner.nextLine();
		System.out.print("Skriv inn ønsket passord: ");
		password = scanner.nextLine();
		System.out.print("Skriv inn ditt fulle navn: ");
		String name = scanner.nextLine();
		System.out.print("Skriv inn ditt telefonnummer: ");
		int tlf = scanner.nextInt();
		sql.newUser(email, password, name, tlf);
		System.out.println("Lager SQL-bruker...");
		System.out.println("Takk, " + name
				+ "! Du har nå opprettet en ny kalenderbruker.");
		loggin();

	}


	public void mainMenu() {
		//userID = sql.getID(email);
		System.out.println("Dette er hovedmenyen:");
		System.out.println("1. Vis dagens eventer");
		System.out.println("2. Vis en bestemt dato");
		System.out.println("3. Vis en bestemt måned");
		System.out.println("4. Opprett event");
		System.out.print("Enter input: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 0:
			System.out.println("Takk for idag!");
			return;
		case 1:
			GregorianCalendar date = new GregorianCalendar();
			String datestring = convertDateToSQL(date);
			dayMenu(datestring);
		case 2:
			System.out.println("Skriv inn dato på formen 'YYYY-MM-DD'");
			String inputdate = scanner.next();
			dayMenu(inputdate);
		case 3:
			GregorianCalendar greg = new GregorianCalendar();
			System.out.print("Måned: ");
			int month = scanner.nextInt() - 1;
			System.out.print("År: ");
			int year = scanner.nextInt();
			greg.set(year, month, 1);
			monthMenu(greg);
		case 4:
			//newEvent();
		}
	}

	public void dayMenu(String date) {
		sql.showEvents(date);
		System.out.println("Hent eventer fra SQL og vis på fin måte...");
		System.out.println("1. Velg event");
		System.out.println("2. Opprett event");
		System.out.print("Enter input: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.print("Skriv inn IDen på eventet du vil se: ");
			int eventID = scanner.nextInt();
			showEvent(eventID, date);
		case 2:
			createEvent(date);
		case 123:
			mainMenu();
		}
	}

	public void showEvent(int eventID, String date) {
		sql.getEventInfo(eventID);
		System.out.println("Skriver noe fint om eventet...");
		System.out.println("1. Endre event");
		System.out.println("2. Slette event");
		System.out.println("3. Tilbake");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Endre event...");
			mainMenu(); //inntil videre
		case 2:
			sql.deleteEvent(eventID);
			mainMenu();
		case 3:
			dayMenu(date);
		case 123:
			mainMenu();
		}
	}

	public void monthMenu(GregorianCalendar greg) {
		printMonth(greg);
		System.out.println("1. Velg dato");
		System.out.println("2. Neste måned");
		System.out.println("3. Forrige måned");
		System.out.print("Enter input: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.print("Skriv inn dato: ");
			int day = scanner.nextInt();
			greg.set(greg.get(Calendar.YEAR), greg.get(Calendar.MONTH), day);
			dayMenu(convertDateToSQL(greg));
		case 2:
			greg.set(greg.get(Calendar.YEAR), greg.get(Calendar.MONTH) + 1,
					greg.get(Calendar.DATE));
			monthMenu(greg);
		case 3:
			greg.set(greg.get(Calendar.YEAR), greg.get(Calendar.MONTH) - 1,
					greg.get(Calendar.DATE));
			monthMenu(greg);
		case 123:
			mainMenu();
		}
	}

	public String convertDateToSQL(GregorianCalendar date) {

		String SQLString;

		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DATE);
		int hour = date.get(Calendar.HOUR);
		int min = date.get(Calendar.MINUTE);
		int sec = date.get(Calendar.SECOND);

		SQLString = "'" + year + "-" + month + "-" + day + " " + hour + ":"
				+ min + ":" + sec + "'";

		return SQLString;
	}

	public void createEvent(String date) {
		System.out.println("Skriv inn navn på eventen:");
		String name = scanner.next();
		System.out.println("Skriv inn tidspunkt for eventen på formen yyyy-mm-dd hh:mm:ss:");
		String starttime = scanner.next();
		System.out.println("Skriv inn varighet på eventen i antall timer: ");
		int duration = scanner.nextInt();
		System.out.println("Skriv inn informasjon om eventen, maks 150tegn: "); 
		String info = scanner.next();
		System.out.println("Ønsker du å innvitere brukere? Ja/Nei");
		String answer = scanner.next().toLowerCase();
		if (answer == "ja") {
			System.out.println("Skriv inn ");
		} else {
			sql.createEvent();
			System.out.println("Eventet er nå opprettet");
		}
	}

	public void printMonth(GregorianCalendar cal) {

		int year = cal.get(Calendar.YEAR);

		int daysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // antall
																				// dager
																				// i
																				// måneden
		String monthString = new SimpleDateFormat("MMMMMMMMM").format(cal
				.getTime()); // måneden som en string
		int dayNumberInMonth = cal.get(GregorianCalendar.DAY_OF_MONTH); // 1-indeksert
		int dayNumberInWeek = (cal.get(GregorianCalendar.DAY_OF_WEEK) + 6) % 7; // 0-indeksert,
																				// starter
																				// mandag
		int firstDayOfMonth = ((7 - (dayNumberInMonth % 7)) + dayNumberInWeek) % 7;// første
																					// ukedagen
																					// i
																					// måneden,
		// nullindeksert (0 er mandag, 1 er tirsdag, ..., 6 er søndag
		System.out.println();
		System.out.println("\t\t\t" + monthString + " " + year);
		System.out.println();
		System.out.println("\tman\ttir\tons\ttor\tfre\tlør\tsøn");
		int dayCount = 1;
		int i = 0;
		int currentWeekday = firstDayOfMonth;
		boolean run = true;
		while (run) {
			if (i < firstDayOfMonth) { // Hvis ikke måneden har begynt enda,
										// print tomme felter
				System.out.print("\t");
				i++;
			} else if (dayCount <= daysInMonth) {
				System.out.print("\t" + dayCount);
				if (dayCount % 32 == 0) { // FYLL INN: Hvis dagen har en avtale
					System.out.print("*");
				}
				dayCount++;
				if (currentWeekday == 6) { // Hvis søndag: Linjeskift
					System.out.println("");
				}
				currentWeekday = (currentWeekday + 1) % 7; // Oppdater ukedag
			} else {
				run = false;
			}
		} System.out.println("");
	}
}

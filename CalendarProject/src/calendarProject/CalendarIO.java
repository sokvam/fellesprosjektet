package calendarProject;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarIO {

	// Denne klassen er i praksis nå brukt som en printer og ikke en komplett
	// IO. Dette er for å teste muligheten for å lagre div. menyer på ett
	// sted.
	
	
	
	
	//Tobias koder, stay out! 9. mars 13:45
	
	
	

	int dybde = 0;

	SQLMethods sql = new SQLMethods();
	Scanner scanner = new Scanner(System.in);

	public void mainMenu() {
		System.out.println("Dette er hovedmenyen:");
		System.out.println("1. Opprett ny bruker");
		System.out.println("2. Logg inn");
		System.out.println("3. Kommer snart!");
		System.out.println("4. Kommer snart!");
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
				System.out.print("Skriv inn din epost: ");
				String email = scanner.nextLine();
				System.out.print("Skriv inn ønsket passord: ");
				String password = scanner.nextLine();
				System.out.print("Skriv inn ditt fulle navn: ");
				String name = scanner.nextLine();
				System.out.print("Skriv inn ditt telefonnummer: ");
				int tlf = scanner.nextInt();
				sql.newUser(email, password, name, tlf);
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
		GregorianCalendar cal = new GregorianCalendar();
		int year = cal.get(GregorianCalendar.YEAR);
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
		}
	}
}
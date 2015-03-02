package calendarProject;

import java.util.*;

public class CalendarIO {
	
	//Denne klassen er i praksis nå brukt som en printer og ikke en komplett IO. Dette er for å teste muligheten for å lagre div. menyer på ett sted.

	int dybde = 0;
	
	public void mainMenu() {
		System.out.println("Dette er hovedmenyen:");
		System.out.println("1. Tobias elsker pikk.");
		System.out.println("2. Ørjan er Sondres sjelefrende.");
		System.out.println("3. Andrea Marie elsker rapporter.");
		System.out.println("4. Jarl har nice skjegg.");
		System.out.print("Enter input: ");
	}
	
	public void subMenu(int subMenu) {
		if (dybde == 0) {
			switch (subMenu) {
			case 0:
				if (dybde > 0) dybde -=1;
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
				dybde +=1;
				break;
			}
		}
	}
}

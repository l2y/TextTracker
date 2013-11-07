import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Input {
	public static boolean start() throws Throwable {
		
		int option = 0;
		Scanner scan = new Scanner(System.in);
		
		out.println("What would you like to do?");
		out.println ("1 >> Reset file");
		out.println ("2 >> Show current file");
		out.println ("3 >> Add new game to file");
		out.println ("4 >> Sort the file into alphabetical order");
		
		if (scan.hasNextInt()) {
			option = scan.nextInt();
			scan.nextLine();
		}
		
		switch (option) {
		case 1: 
			Games.reset();
			break;
		case 2:
			Games.viewList();
			break;
		case 3:
			Input.enterGame();
			break;
		case 4: 
			Games.sortList();
			break;
		}
		
		out.println("Add more? (Y/N)");
		if ("Y".equalsIgnoreCase(scan.nextLine()))
			return true;
		else
			return false;
		
	}
	
	public static void enterGame () throws Throwable, IOException {
		int price;
		Scanner scan = new Scanner(System.in);
		String name = "";
		
		out.println ("Enter the name of the game");
		name = scan.nextLine();
		
		if (!Games.dupes(name))
		{
			out.println("Enter the price of the game");
			price = Integer.parseInt(scan.nextLine());
			PSV newGame = new PSV(name,price);
			out.println("What is the date (day) (0 for none)");
			newGame.day = Integer.parseInt(scan.nextLine());
			if (newGame.day != 0){
				out.println("What is the date (month)");
				newGame.month = Integer.parseInt(scan.nextLine());
				out.println("What is the date (year)");
				newGame.year = Integer.parseInt(scan.nextLine());
			}
			else {
				newGame.month = 0;
				newGame.year = 0;
			}
			newGame.appendInfo();
		}
		else
			out.println("That game is already in the database.");
	}
}
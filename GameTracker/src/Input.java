import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class Input {
	public static boolean start() throws Throwable {
		
		int option;
		Scanner scan = new Scanner(System.in);
		
		out.println("What would you like to do?");
		out.println ("R >> Reset File");
		out.println ("S >> Show Current File");
		out.println ("N >> Add New Game to File");
		option = Integer.parseInt(scan.nextLine());
		
		switch (option) {
		case 1: option = Integer.parseInt("R");
			Games.reset();
			break;
		case 2: option = Integer.parseInt("S");
			Games.viewList();
			break;
		case 3: option = Integer.parseInt("N");
			Input.enterGame();
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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import static java.lang.System.*;

public class Games {
	public String name;
	public int price;
	public int day;
	public int month;
	public int year;

	public Games (String nm, int p, int d, int mo, int yr){
		name = nm;
		price = p;
		day = d;
		month = mo;
		year = yr;
	}

	public Games (String nm, int p){
		name = nm;
		price = p;
		day = 0;
		month = 0;
		year = 0;
	}

	public void appendInfo() throws IOException{
		BufferedWriter buffer = null;
		FileWriter file = null;
		try {
			file = new FileWriter("list.txt", true);
			buffer = new BufferedWriter(file);
			buffer.write(this.name+","+this.price+","+this.day+","+this.month+","+this.year+";");

		} finally {
			if (buffer != null)
				buffer.close();
			if (file != null)
				file.close();
		}
	}

	public static String readList() throws IOException{
		StringBuilder list = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("list.txt"));
			String c = "";

			while ((c = in.readLine()) != null){
				list.append(c);
			}
		}
		finally {
			if (in != null)
				in.close();
		}
		return list.toString();
	}

	public static void reset() throws IOException{
		FileWriter file = new FileWriter("list.txt", false);
		BufferedWriter buffer = new BufferedWriter(file);
		buffer.write("");
		buffer.close();
	}

	public static void show() throws IOException {
		out.println(Games.readList());
	}

	public static void viewList() throws IOException {
		String fixList[] = splitList();
		if (fixList.length > 1) {
			for (int i = 0 ; i < fixList.length ; i=i+5 ) {
				out.println("_____________" );
				out.println("Game: " + fixList[i]);
				out.println("Price: " + fixList[i+1]);
				if (Integer.parseInt(fixList[i+2]) != 0) {
					out.println("Date: " + fixList[i+2] + "/" + fixList[i+3] + "/" + fixList[i+4]);
				}
			}
		}
	}

	public static boolean dupes(String name) throws IOException {
		String fixList[] = splitList();
		for (int i = 0 ; i < fixList.length ; i=i+5 ) {
			if (name.equalsIgnoreCase(fixList[i]))
				return true;
		}

		return false;
	}

	public static String[] splitList() throws IOException {
		String cleanList = Games.readList();
		String patternGame = "([\\,;])";
		return cleanList.split(patternGame);
	}

	public static String[] splitGame() throws IOException {
		String cleanList = Games.readList();
		String patternGame = "([\\;])";
		return cleanList.split(patternGame);
	}
	private static void quickSort(int start, int end, String games[]) throws IOException {
		int i = start;
		int j = end;
		String temp = "";
		char pivot = games[(start+end)/2].charAt(0);
		while (i <= j) {
			while (games[i].charAt(0) < pivot){
				i++;
			} 
			while (games[j].charAt(0) > pivot){
				j--;
			}
			if (i <= j) {
				temp = games[i];
				games[i] = games[j];
				games[j] = temp;
				j--;
				i++;
			}
		}
		if (start < j)
			quickSort (start, j, games);
		if (i < end)
			quickSort (i, end, games);
	}
	public static void sortList() throws IOException {
		String games[] = splitGame();
		//no return since array
		quickSort(0,games.length - 1, games);
		reset();
		rewrite(games);
		viewList();
	}
	private static void rewrite(String games[]) throws IOException{
		BufferedWriter buffer = null;
		FileWriter file = null;
		try {
			file = new FileWriter("list.txt", true);
			buffer = new BufferedWriter(file);
			for (int i = 0 ; i < games.length ; i++)
				buffer.write(games[i] + ";");

		} finally {
			if (buffer != null)
				buffer.close();
			if (file != null)
				file.close();
		}		
	}
	
}
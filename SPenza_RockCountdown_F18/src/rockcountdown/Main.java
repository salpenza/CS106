package rockcountdown;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(in);
		String filename = scan.nextLine();
		ArrayList<Song> topsongs = new ArrayList<Song>(30);

		String songStrings = readFile(filename);
		String[] songArray = new String[30];
		songArray = songStrings.split("\r\n");

		for (String p : songArray) {
			Song b = new Song(p);
			topsongs.add(b);

		}

		StringBuilder build = new StringBuilder();

		for (int i = topsongs.size() - 1; i >= 0; i--) {
			build.append(topsongs.get(i).getRank() + "\t" + topsongs.get(i).getTitle() + "\r\n");
		}

		println(build.toString());
		println("Complete");
		scan.close();
	}

}
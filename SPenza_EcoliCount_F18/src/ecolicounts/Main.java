package ecolicounts;

import static org.apache.commons.lang3.StringUtils.*;
import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		String dna = readFile(scan.nextLine());

		printf("#A = %d\n#C = %d\n#G = %d\n#T = %d\n", countMatches(dna, "A"), countMatches(dna, "C"),
				countMatches(dna, "G"), countMatches(dna, "T"));
		scan.close();
	}

}

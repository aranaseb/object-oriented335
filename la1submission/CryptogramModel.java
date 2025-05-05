import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
/*
 * @author Sebastian Arana
 */
public class CryptogramModel {

	private String currentString;
	private HashMap<Character, Character> encryptionTable;
	private String answer;
	private String encryptedString;

	public CryptogramModel() {
		readInputFile("input.txt");

		// makes an arraylist of the uppercase alphabet letters
		ArrayList<String> shuffledList = new ArrayList<String>(Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")));
		Collections.shuffle(shuffledList);

		// puts each alphabetical character as a key for matching encrypted letter
		encryptionTable = new HashMap<Character, Character>();
		for (int i = 0; i < 26; i++) {
			// uses index + 65 as an upercase char (ASCII)
			encryptionTable.put((char) (i + 65), shuffledList.get(i).charAt(0));
		}

		encryptString();

		// sets the "guess" string to whitespace with only punctuation
		currentString = answer.replaceAll("[a-zA-Z]", " ");
	}

	// helper method to read file and do some error checking
	private void readInputFile(String fileName) {
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(fileName));
		} catch (Exception e) {
			System.out.println("File does not exist: " + fileName + "!\n");
			System.exit(1);
		}

		// randomizes answer by only reading one random line
		int randomLine = (int) (Math.random() * 10);
		for (int i = 0; i < randomLine; i++) {
			inFile.nextLine();
		}
		this.answer = inFile.nextLine().toUpperCase();
		inFile.close();
	}

	// helper method to build encrypted string by accessing table by ASCII value
	private void encryptString() {
		encryptedString = "";
		for (int i = 0; i < answer.length(); i++) {
			if (Character.isLetter(answer.charAt(i))) {
				encryptedString += encryptionTable.get(answer.charAt(i));
			} else {
				encryptedString += answer.charAt(i);
			}
		}
	}

	public void setReplacement(char encryptedChar, char guess) {
		for (int i = 0; i < encryptedString.length(); i++) {
			if (encryptedString.charAt(i) == encryptedChar) {
				currentString = currentString.substring(0, i) + guess + currentString.substring(i + 1);
			}
		}
	}

	public String getEncryptedString() {
		return encryptedString;
	}

	public String getCurrentGuess() {
		return currentString;
	}

	public String getAnswer() {
		return answer;
	}
}

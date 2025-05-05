import java.util.Scanner;
/*
 * @author Sebastian Arana
 */
public class Cryptograms {
	public static void main(String[] args) {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		Scanner keyboard = new Scanner(System.in);

		while (!control.isGameOver()) {
			System.out.println(control.getEncryptedQuote());

			System.out.print("Enter a letter to replace: ");
			char letterToReplace = Character.toUpperCase(keyboard.nextLine().charAt(0));

			System.out.print("Enter its replacement: ");
			control.makeReplacement(letterToReplace, Character.toUpperCase(keyboard.nextLine().charAt(0)));

			System.out.println(control.getUsersProgress());
		}
		System.out.println(control.getEncryptedQuote());
		System.out.println("You got it!");
	}

}

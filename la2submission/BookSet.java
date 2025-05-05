import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BookSet {

	private ArrayList<Book> myLib;
	private ArrayList<Book> readLib;
	private HashMap<String, ArrayList<Book>> authorSet;
 
	public BookSet() {
		myLib = new ArrayList<Book>();
		readLib = new ArrayList<Book>();
		authorSet = new HashMap<String, ArrayList<Book>>();
	}

	// choice declared as object
	public void search(int choiceNum, Object choiceInp) {
		switch (choiceNum) {
		// title
		case 1:
			for (int i = 0; i < myLib.size(); i++) {
				if (myLib.get(i).getTitle().equals(choiceInp)) {
					bookPrinter(myLib.get(i));
				}
			}
			break;
		// author
		case 2:
			for (int i = 0; i < myLib.size(); i++) {
				if (myLib.get(i).getAuthor().equals(choiceInp)) {
					bookPrinter(myLib.get(i));
				}
			}
			break;
		// rating
		case 3:
			for (int i = 0; i < myLib.size(); i++) {
				if (myLib.get(i).getRating() == (Integer) choiceInp) {
					bookPrinter(myLib.get(i));
				}
			}
			break;
		// read
		case 4:
			for (int i = 0; i < myLib.size(); i++) {
				if (myLib.get(i).getRead() == (Boolean) choiceInp) {
					bookPrinter(myLib.get(i));
				}
			}
			break;
		// fill in for invalid input(?)
		default:
			System.out.print("Invalid choice input!");
			search(choiceNum, choiceInp);
		}
	}

	public void addBook(String newTitle, String newAuthor) {
		Book bookToAdd = new Book(newTitle, newAuthor);
		myLib.add(bookToAdd);
		Collections.sort(myLib);
		
		if(!authorSet.containsKey(newAuthor)) {
			authorSet.put(newAuthor, new ArrayList<Book>());
		}
		authorSet.get(newAuthor).add(bookToAdd);
	}

	public void setToRead(String bookTitle) {
		for(int i = 0; i < myLib.size(); i++) {
			if(myLib.get(i).getTitle().equals(bookTitle)) {
				myLib.get(i).setRead();
			}
		}
		readCheck();
	}

	public void rate(String name, int rating) {
		for (int i = 0; i < myLib.size(); i++) {
			if (myLib.get(i).getTitle().equals(name)) {
				myLib.get(i).setRating(rating);
			}
		}
	}

	public void getBooks(int choiceNum) {
		switch (choiceNum) {
		// title
		case 1:
			for (int i = 0; i < myLib.size(); i++) {
				bookPrinter(myLib.get(i));
			}
			break;
		// author
		case 2:
			Object[] authors = authorSet.keySet().toArray();
			Arrays.sort(authors);
			
			for(int i = 0; i < authors.length; i++) {
				ArrayList<Book> authorsBooks = authorSet.get(authors[i]);
				for(int j = 0; j < authorsBooks.size(); j++) {
					bookPrinter(authorsBooks.get(j));
				}
			}
			
			break;
		// read
		case 3:
			System.out.println("Read Books:");
			for (int i = 0; i < readLib.size(); i++) {
				bookPrinter(readLib.get(i));
			}
			break;
		// unread
		case 4:
			System.out.println("Unread Books:");
			for (int i = 0; i < myLib.size(); i++) {
				if (myLib.get(i).getRead() == false) {
					bookPrinter(myLib.get(i));
				}
			}
			break;
		// fill in for invalid input(?)
		default:
			System.out.print("Invalid choice input!");
			getBooks(choiceNum);
		}
	}

	public void suggestRead() {
		Random r = new Random();
		bookPrinter(readLib.get(r.nextInt(readLib.size())));
	}

	public void addBooks(String filename) {
		// include file input here(?)
		File readBooks = new File(filename);
		try {
			Scanner fileRead = new Scanner(readBooks);
			while (fileRead.hasNext()) {
				String[] read = fileRead.nextLine().split(";");
				String title = read[0];
				String author = read[1];
				Book bookToAdd = new Book(title, author);
				myLib.add(bookToAdd);
				if(!authorSet.containsKey(author)) {
					authorSet.put(author, new ArrayList<Book>());
				}
				authorSet.get(author).add(bookToAdd);
			}
			fileRead.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found!\tStack Trace:\n");
			e.printStackTrace();
		}
		Collections.sort(myLib);
	}

	private void readCheck() {
		for (int i = 0; i < myLib.size(); i++) {
			if (!readLib.contains(myLib.get(i)) && myLib.get(i).getRead()) {
				readLib.add(myLib.get(i));
			}
		}
		Collections.sort(readLib);
	}

	private void bookPrinter(Book other) {
		System.out.println(other.getTitle() + "\n(" + other.getAuthor() + ")\nRating: " + other.getRating() + "\nRead: "
				+ other.getRead() +"\n");
	}
}

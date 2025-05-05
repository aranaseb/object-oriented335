/*
 * @author Sebastian Arana
 */
public class CryptogramController {

	private CryptogramModel model;

	public CryptogramController(CryptogramModel model) {
		this.model = model;
	}

	public boolean isGameOver() {
		return model.getAnswer().equals(model.getCurrentGuess());
	}

	public void makeReplacement(char letterToReplace, char guess) {
		model.setReplacement(letterToReplace, guess);
	}

	public String getEncryptedQuote() {
		return model.getEncryptedString();
	}

	public String getUsersProgress() {
		return model.getCurrentGuess();
	}
}

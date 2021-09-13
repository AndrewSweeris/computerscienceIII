
import java.util.ArrayList;
import java.util.List;

/**
 * A class that generates anagrams of a word that can be found in a provided
 * dictionary.
 *
 * @author Andrew Sweeris
 */
public class Anagrams {

	private List<String> dictionary;
	private List<String> prunedDictionary;
	private List<String> used;

	/**
	 * Constructor method for class that takes argument for a List of Strings and
	 * then instantiates the object
	 *
	 * @param dictionary dictionary argument
	 */
	public Anagrams(List<String> dictionary) {
		this.dictionary = dictionary;
		prunedDictionary = new ArrayList<String>();
		used = new ArrayList<String>();
	}

	/**
	 * Function that prints anagrams from input text
	 *
	 * @param text the text to prune anagrams from
	 * @param max  the maximum amount of anagrams to be made. 0 is unlimited
	 */
	public void print(String text, int max) {
		if (max < 0) {
			throw new IllegalArgumentException("Max < 0");
		}
		LetterInventory textInv = new LetterInventory(text);
		pruner(textInv, 0);
		anagramGenerator(textInv, 0, 0, max);
		prunedDictionary.clear();
		used.clear();
	}

	/**
	 * Function that prunes impossible words from dictionary
	 *
	 * @param textInv the LetterInventory to check against the dictionary
	 * @param i       the current index
	 */
	private void pruner(LetterInventory textInv, int i) {
		if (i < dictionary.size()) {
			LetterInventory dictInv = new LetterInventory(dictionary.get(i));
			if (i < dictionary.size()) {
				if (textInv.subtract(dictInv) != null) {
					prunedDictionary.add(dictionary.get(i));
				}
				pruner(textInv, i + 1);
			}
		}
	}

	/**
	 * Method that generates all possible anagrams by parsing through both
	 * prunedDictionary and used Lists to check if word has already been used and
	 * can be used.
	 * 
	 * @param textInv Current letters to use
	 * @param i       Current index
	 * @param count   Current count of used words
	 * @param max     Maximum amount of words to use
	 */
	private void anagramGenerator(LetterInventory textInv, int i, int count, int max) {
		if (i >= prunedDictionary.size() || (count == max && max != 0) || textInv.isEmpty()) {
			if (textInv.isEmpty()) {
				System.out.println(usedString(0));
			}
		} else {
			LetterInventory dictInv = new LetterInventory(prunedDictionary.get(i));
			LetterInventory tempInv = textInv.subtract(dictInv);
			List<String> tempList = used;
			if (tempInv != null && !tempList.contains(prunedDictionary.get(i))) {
				String tempCurrent = "";
				used.add(prunedDictionary.get(i));
				anagramGenerator(tempInv, 0, count, max);
				used.remove(used.size() - 1);
				anagramGenerator(textInv, i + 1, count + 1, max);

			} else {
				anagramGenerator(textInv, i + 1, count, max);
			}
		}
	}

	/**
	 * Method that prints out Strings in used List
	 * 
	 * @param i current index
	 * @return List variables
	 */
	private String usedString(int i) {
		if (i < used.size()) {
			return used.get(i) + " " + usedString(i + 1);
		}
		return "";
	}
}

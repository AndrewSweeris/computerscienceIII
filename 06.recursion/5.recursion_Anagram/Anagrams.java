
import java.util.List;

/**
 *
 * @author sweer
 */
public class Anagrams {

    private List<String> dictionary;
    private List<String> prunedDictionary;

    /**
     * Constructor method for class that takes argument for a List of Strings
     * and then instantiates the object
     *
     * @param dictionary dictionary argument
     */
    public Anagrams(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Function that prints anagrams from input text
     *
     * @param text the text to prune anagrams from
     * @param max the maximum amount of anagrams to be made. 0 is unlimited
     */
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException("Max < 0");
        }
        LetterInventory textInv = new LetterInventory(text);
        pruner(textInv, 0);
        anagramGenerator(textInv, 0, max);
    }

    /**
     * Function that prunes impossible words from dictionary
     *
     * @param textInv the LetterInventory to check against the dictionary
     * @param i the current index
     */
    private void pruner(LetterInventory textInv, int i) {
        if (i < dictionary.size()) {
            LetterInventory dictInv = new LetterInventory(dictionary.get(i));
        }
    }

    /**
     * Function that generates anagrams from LetterInventory textInv, and then
     * calls function to subtract letters used from textInv. Generates all
     * possible anagrams within the anagram maximum.
     *
     * @param textInv LetterInventory of remaining, usable characters
     * @param i Current index in dictionary
     * @param max Maximum amount of anagrams
     */
    private void anagramGenerator(LetterInventory textInv, int i, int max) {
        if (i < dictionary.size() && (i < max || max == 0) && !textInv.isEmpty()) {
            LetterInventory remInv = new LetterInventory(dictionary.get(i));
        }
    }

    /**
     * Function that removes used characters from textInv, and then returns
     * remaining unused characters.
     *
     * @param textInv Characters remaining
     * @param remInv Characters remaining to be removed
     */
    private LetterInventory characterRemover(LetterInventory textInv, LetterInventory remInv) {
        if (textInv.isEmpty() && !remInv.isEmpty()) {
            throw new Error("textInv empty before remImv");
        }
        return textInv;
    }
}

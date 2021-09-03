//� A+ Computer Science  -  www.apluscompsci.com
//Name - Andrew Millan Sweeris
//Date - 21/09/02
//Class - Computer Science III K

import java.util.*;
import static java.lang.System.*;

/**
 * Permutation Class. Creates every possible permutation out of an input String
 *
 * @author Andrew Sweeris
 */
public class Permutation {

    private String orig;
    private String list;

    /**
     * Constructor for Permutation, takes input String
     *
     * @param word
     */
    public Permutation(String word) {
        orig = word;
        list = "";
    }

    /**
     * Method that calls recursive function permutation(String orig, String
     * sent) that does primary function of class.
     *
     */
    public void permutation() {
        out.println("\nPERMUTATION OF WORD :: " + orig);
        permutation(orig, "");
    }

    private void permutation(String orig, String sent) {
        String tempOne = "", tempTwo = "";
        if (orig.isEmpty()) {
            if (!sent.isEmpty()) {
                list += sent + "\n";
            }
        } else if (orig.length() == 1) {
            list += sent + orig + "\n";
        } else {
            for (int i = 0; i < orig.length(); i++) {
                tempOne = orig.substring(0, i) + orig.substring(i + 1);
                tempTwo = sent + orig.charAt(i);
                permutation(tempOne, tempTwo);
            }
        }
    }

    /**
     * Returns list of String permutations in printable form.
     *
     * @return
     */
    @Override
    public String toString() {
        return list;
    }
}

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;

public class OddEvenSets {

    private Set<Integer> odds;
    private Set<Integer> evens;

    public OddEvenSets() {
        odds = new TreeSet<Integer>();
        evens = new TreeSet<Integer>();
    }

    public OddEvenSets(String line) {
        odds = new TreeSet<Integer>();
        evens = new TreeSet<Integer>();
        String[] ary = line.split(" ");
        for (String var : ary) {
            if (Integer.parseInt(var) % 2 == 0) {
                evens.add(Integer.parseInt(var));
            } else {
                odds.add(Integer.parseInt(var));
            }
        }
    }

    public String toString() {
        return "ODDS : " + odds + "\nEVENS : " + evens + "\n";
    }
}

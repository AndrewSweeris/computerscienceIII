//� A+ Computer Science  -  www.apluscompsci.com
//Name - Andrew M. Sweeris
//Date - 2021/09/27
//Class - Computer Science IIIK
//Lab  -  Uniques and Dupes

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.*;

public class UniquesDupes {

    public static Set<String> getUniques(String input) {
        String[] ary = input.split(" ");
        Set<String> uniques = new TreeSet<String>();
        for (int i = 0; i < ary.length; i++) {
            if (uniques.isEmpty() || !uniques.contains(ary[i])) {
                uniques.add(ary[i]);
            }
        }

        return uniques;
    }

    public static Set<String> getDupes(String input) {
        String[] ary = input.split(" ");
        Set<String> uniques = new TreeSet<String>();
        Set<String> duplicates = new TreeSet<String>();
        for (int i = 0; i < ary.length; i++) {
            if (uniques.contains(ary[i]) && !duplicates.contains(ary[i])) {
                duplicates.add(ary[i]);
            }
            if (!uniques.contains(ary[i])) {
                uniques.add(ary[i]);
            }
        }
        return duplicates;
    }
}

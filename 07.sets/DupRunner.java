//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.*;

public class DupRunner {

    private static void testCase(String var) {
        Object[] varOneUniques = UniquesDupes.getUniques(var).toArray();
        Object[] varOneDuplicates = UniquesDupes.getDupes(var).toArray();

        System.out.println("Original List : " + var);
        System.out.print("Uniques : ");
        for (int i = 0; i < varOneUniques.length - 1; i++) {
            System.out.print(varOneUniques[i].toString() + ", ");
        }
        System.out.println(varOneUniques[varOneUniques.length - 1].toString() + "]");
        System.out.print("Dupes : [");
        for (int i = 0; i < varOneDuplicates.length - 1; i++) {
            System.out.print(varOneDuplicates[i].toString() + ", ");
        }
        System.out.println(varOneDuplicates[varOneDuplicates.length - 1].toString() + "]\n");
    }

    public static void main(String args[]) {
        testCase("a b c d e f g h a b c d e f g h i j k");
        testCase("one two three one two three six seven one two");
        testCase("1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 6");
    }
}

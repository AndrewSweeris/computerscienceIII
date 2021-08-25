
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import static java.lang.System.*;

public class IteratorReplacerRunner {

    public static void main(String[] args) {
        //add test cases
        IteratorReplacer one = new IteratorReplacer("a b c a b c", "a", "+");
        one.replace();
        System.out.println(one.toString());

        IteratorReplacer two = new IteratorReplacer("a b c d e f g h i j x x x x", "x", "7");
        two.replace();
        System.out.println(two.toString());

        IteratorReplacer three = new IteratorReplacer("1 2 3 4 5 6 a b c a b c", "b", "#");
        three.replace();
        System.out.println(three.toString());

        IteratorReplacer four = new IteratorReplacer("j k l a ;", "a", "_");
        four.replace();
        System.out.println(four.toString());

        IteratorReplacer five = new IteratorReplacer("Hello there how are you doing today", "today", "tomorrow?");
        five.replace();
        System.out.println(five.toString());

        IteratorReplacer six = new IteratorReplacer("When the teacher is sus picious", "picious", ":D");
        six.replace();
        System.out.println(six.toString());
    }
}

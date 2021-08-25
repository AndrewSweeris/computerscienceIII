
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static java.lang.System.*;

public class IteratorRemoverRunner {

    public static void main(String[] args) {
        //add test cases
        IteratorRemover one = new IteratorRemover("a b c a b c a", "a");
        one.remove();
        System.out.println(one.toString());

        IteratorRemover two = new IteratorRemover("a b c d e f g h i j x x x x", "x");
        two.remove();
        System.out.println(two.toString());

        IteratorRemover three = new IteratorRemover("1 2 3 4 5 6 a b c a b c", "b");
        three.remove();
        System.out.println(three.toString());

        IteratorRemover four = new IteratorRemover("j k l a ;", "a");
        four.remove();
        System.out.println(four.toString());

        IteratorRemover five = new IteratorRemover("Hello there how are you doing today", "today");
        five.remove();
        System.out.println(five.toString());

        IteratorRemover six = new IteratorRemover("When the teacher is sus picious", "picious");
        six.remove();
        System.out.println(six.toString());
    }
}

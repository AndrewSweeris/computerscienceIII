//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class MathSetRunner {

    private static void tester(String varOne, String varTwo) {
        MathSet tester = new MathSet(varOne, varTwo);
        System.out.println(tester.toString());
        System.out.println("Union - " + tester.union());
        System.out.println("Intersection - " + tester.intersection());
        System.out.println("Difference A-B - " + tester.differenceAMinusB());
        System.out.println("Difference B-A - " + tester.differenceBMinusA());
        System.out.println("Symmetric Difference - " + tester.symmetricDifference() + "\n");
    }

    public static void main(String args[]) throws IOException {
        Scanner reader = new Scanner(new File("mathsetdata.dat"));
        tester(reader.nextLine(), reader.nextLine());
        tester(reader.nextLine(), reader.nextLine());
        tester(reader.nextLine(), reader.nextLine());
    }
}

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class OddRunner {

    private static void tester(String line) {
        OddEvenSets tester = new OddEvenSets(line);
        System.out.println(tester.toString());
    }

    public static void main(String args[]) throws IOException {
        Scanner reader = new Scanner(new File("oddevens.dat"));
        tester(reader.nextLine());
        tester(reader.nextLine());
        tester(reader.nextLine());
        tester(reader.nextLine());
        tester(reader.nextLine());
        tester(reader.nextLine());
    }
}

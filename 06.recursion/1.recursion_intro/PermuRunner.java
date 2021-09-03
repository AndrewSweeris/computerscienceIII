//� A+ Computer Science  -  www.apluscompsci.com
//Name - Andrew Millan Sweeris
//Date - 21/09/02
//Class - Computer Science III K

import java.util.*;
import static java.lang.System.*;

public class PermuRunner {

    /**
     * Main method for PermuRunner. Tests Permutation.java
     *
     * @param args
     */
    public static void main(String args[]) {
        //add test cases
        Permutation a = new Permutation("a");
        a.permutation();
        System.out.print(a.toString());
        Permutation it = new Permutation("it");
        it.permutation();
        System.out.print(it.toString());
        Permutation ABC = new Permutation("ABC");
        ABC.permutation();
        System.out.print(ABC.toString());
        Permutation abc = new Permutation("abc");
        abc.permutation();
        System.out.print(abc.toString());
        Permutation boat = new Permutation("boat");
        boat.permutation();
        System.out.print(boat.toString());
        // Commented Out - Large runtime caused by large String size
        /*Permutation creature = new Permutation("creature");
        creature.permutation();
        System.out.print(creature.toString());*/
        Permutation cat = new Permutation("cat");
        cat.permutation();
        System.out.print(cat.toString());
        Permutation dog = new Permutation("dog");
        dog.permutation();
        System.out.print(dog.toString());
        Permutation man = new Permutation("man");
        man.permutation();
        System.out.print(man.toString());
        Permutation funny = new Permutation("funny");
        funny.permutation();
        System.out.println(funny.toString());
    }
}

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -  

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import static java.lang.System.*;

public class MathSet {

    private Set<Integer> one;
    private Set<Integer> two;

    public MathSet() {
        one = new TreeSet<Integer>();
        two = new TreeSet<Integer>();
    }

    public MathSet(String o, String t) {
        one = new TreeSet<Integer>();
        for (String var : o.split(" ")) {
            one.add(Integer.parseInt(var));
        }
        two = new TreeSet<Integer>();
        for (String var : t.split(" ")) {
            two.add(Integer.parseInt(var));
        }
    }

    public Set<Integer> union() {
        Set<Integer> union = new TreeSet<Integer>();
        for (int var : one) {
            union.add(var);
        }
        for (int var : two) {
            union.add(var);
        }
        return union;
    }

    public Set<Integer> intersection() {
        Set<Integer> intersection = new TreeSet<Integer>();
        for (int var : one) {
            if (two.contains(var)) {
                intersection.add(var);
            }
        }
        return intersection;
    }

    public Set<Integer> differenceAMinusB() {
        Set<Integer> difference = new TreeSet<Integer>();
        for (int var : one) {
            if (!two.contains(var)) {
                difference.add(var);
            }
        }
        return difference;
    }

    public Set<Integer> differenceBMinusA() {
        Set<Integer> difference = new TreeSet<Integer>();
        for (int var : two) {
            if (!one.contains(var)) {
                difference.add(var);
            }
        }
        return difference;
    }

    public Set<Integer> symmetricDifference() {
        Set<Integer> difference = new TreeSet<Integer>();
        Set<Integer> aMinusB = differenceAMinusB();
        Set<Integer> bMinusA = differenceBMinusA();

        for (int var : aMinusB) {
            difference.add(var);
        }
        for (int var : bMinusA) {
            difference.add(var);
        }
        return difference;
    }

    public String toString() {
        return "Set one " + one + "\n" + "Set two " + two + "\n";
    }
}

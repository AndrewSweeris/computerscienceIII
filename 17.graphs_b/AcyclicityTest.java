import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcyclicityTest {

    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            int n = scanner.nextInt()+1;
            int m = scanner.nextInt();
            adj = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int x, y;
                x = scanner.nextInt();
                y = scanner.nextInt();
                adj[x].add(y);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReachabilityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testOne() {
        loadFile("acyclicOne.txt");
        assertEquals(1, Acyclicity.acyclic(adj));
    }

    @Test
    public void testTwo() {
        loadFile("acyclicTwo.txt");
        assertEquals(0, Acyclicity.acyclic(adj));
    }
}

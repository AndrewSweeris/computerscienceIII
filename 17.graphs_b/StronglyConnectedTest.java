import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class StronglyConnectedTest {

    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            int n = scanner.nextInt();
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
    public void testSmall() {
        loadFile("myDag.txt");
        assertEquals(4, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(250, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }

    @Test
    public void testMedium2() {
        loadFile("med2DG.txt");
        assertEquals(60, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }
    
    @Test
    public void testMassive() {
        loadFile("sccStupendous.txt");
        assertEquals(371762, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }
}
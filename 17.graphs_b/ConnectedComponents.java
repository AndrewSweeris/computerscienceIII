import java.lang.reflect.Array;
import java.util.*;

public class ConnectedComponents {
    static int numberOfComponents(ArrayList<Integer>[] adj) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer[]> connected = new ArrayList();
        for (int i = 0; i < adj.length; i++) {
            if (!visited.contains(i)) {
                Iterator<Integer> it = compCheck(adj, i).iterator();
                ArrayList<Integer> temp = new ArrayList<>();
                int y = 0;
                while (it.hasNext()) {
                    int curr = it.next();
                    visited.add(curr);
                    temp.add(curr);
                }
                connected.add(temp.toArray( new Integer[temp.size() ]));
            }
        }
        //write your code here
        return connected.size();
    }

    static HashSet<Integer> compCheck(ArrayList<Integer>[] adj, int vertex) {

        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.add(vertex);
        visited.add(vertex);

        while(!stack.empty()) {
            int curr = stack.pop();
            for (Integer z : adj[curr]) {
                if (!visited.contains(z)) {
                    visited.add(z);
                    stack.add(z);
                }
            }
        }
        return visited;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}


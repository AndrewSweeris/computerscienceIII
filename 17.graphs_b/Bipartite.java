import java.util.*;

public class Bipartite {

    private static Queue<Integer> queue;
    private static HashSet<Integer> visited;
    private static HashMap<Integer, Boolean> color;
    public static int bipartite(ArrayList<Integer>[] adj) {
        // quick check
        if (Acyclicity.acyclic(adj)==1)return 1;
        queue = new LinkedList<>();
        visited = new HashSet<>();
        color = new HashMap<>();
        return distance(adj,1);
    }
    public static int distance(ArrayList<Integer>[] adj, int s) {
        //write your code here
        queue = new LinkedList<>();
        visited = new HashSet<>();
        boolean bool = true;

        queue.add(s);
        visited.add(s);
        color.put(s,bool);

        while (!queue.isEmpty()) {
            int c = queue.poll();
            bool = !color.get(c);
            for (int x : adj[c]) {
                if (!visited.contains(x)) {
                    color.put(x, bool);
                    visited.add(x);
                    queue.add(x);
                }
                else {
                    if (color.get(x)==color.get(c)) return 0;
                }
            }
        }
        return 1;
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
        System.out.println(bipartite(adj));
    }
}


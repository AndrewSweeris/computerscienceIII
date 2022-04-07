import java.util.*;

public class BFS {
    private static Queue<Integer> queue;
    private static HashSet<Integer> visited;
    private static HashMap<Integer, Integer> parents;
    public static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        queue = new LinkedList<>();
        visited = new HashSet<>();
        parents = new HashMap<>();

        queue.add(s);

        while (!queue.isEmpty()) {
            int c = queue.poll();
            visited.add(c);
            if (c==t) return depthFinder(t);
            for (int x : adj[c]) {
                if (!visited.contains(x)) {
                    visited.add(x);
                    parents.put(x, c);
                    queue.add(x);
                }
            }
        }
        return -1;
    }

    private static int depthFinder(int vertex) {
        if (parents.get(vertex)==null) return 0;
        else {
            return 1 + depthFinder(parents.get(vertex));
        }
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}


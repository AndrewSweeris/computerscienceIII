import java.util.*;

public class Acyclicity {

    static int acyclic(ArrayList<Integer>[] adj) {
        for (int i = 0; i < adj.length; i++) {
            if (diver(adj, new HashSet<Integer>(), i, i)==1) {
                return 1;
            }
        }
        return 0;
    }

    private static int diver(ArrayList<Integer>[] adj, HashSet<Integer> visited, int vertex, int orig) {
        visited.add(vertex);
        for (int x : adj[vertex]) {
            if (x==orig)return 1;
            if (!visited.contains(x)) {
                if (diver(adj,visited,x, orig)==1)return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}


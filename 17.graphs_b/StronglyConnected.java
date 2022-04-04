import java.util.*;

public class StronglyConnected {
    private static HashSet<Integer> visited;
    private static ArrayList<Integer>[] SCCs;
    private static Stack<Integer> stack;
    private static ArrayList<Integer>[] graph;
    static private int curr;

    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        visited = new HashSet<>();
        graph=adj;
        SCCs = (ArrayList<Integer>[]) new ArrayList[adj.length];

        curr = 0;
        ArrayList<Integer> order = toposort(adj);
        curr = 0;
        visited.clear();
        for (int i = order.size()-1; i >= 0; i--) {
            int x = order.get(i);
            if (!visited.contains(x)) {
                DFSDive(x);
                curr++;
            }
        }
        return SCCs.length;
    }

    static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        stack = new Stack<>();
        for (int i = 0; i < adj.length; i++) {
            if (!visited.contains(i)) {
                curr = 1;
                DFS(adj, i);
            }
        }
        ArrayList<Integer> output = new ArrayList<>();
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    // DFS method for toposort
    static void DFS(ArrayList<Integer>[] adj, int vertex) {
        visited.add(vertex);
        curr = vertex;

        for (int x : adj[vertex]) {
            if (!visited.contains(x)) {
                graph[x].add(curr);
                DFS(adj, x);
            }
        }
        stack.push(vertex);
    }

    // DFS method for SCCs
    static void DFSDive(int vertex) {
        visited.add(vertex);
        SCCs[curr].add(vertex);
        for (Integer x : graph[vertex]) {
            if (x!=null&&!visited.contains(x)) {
                DFSDive(x);
            }
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}


import java.util.*;

public class StronglyConnected {
    private static HashSet<Integer> visited;
    private static ArrayList<ArrayList<Integer>> SCCs;
    private static ArrayList<ArrayList<Integer>> newAdj;

    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        visited = new HashSet<>();
        SCCs = new ArrayList<ArrayList<Integer>>();
        newAdj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < adj.length; i++)newAdj.add(new ArrayList<>());

        ArrayList<Integer> order = Toposort.toposort(adj);
        transpose(adj,order.get(0));
        // System.out.println(order.toString()+ "\n");
        visited.clear();
        for (int i = 0; i < newAdj.size(); i++) {
            int x = order.get(i);
            if (!visited.contains(x)) {
                SCCs.add(new ArrayList<>());
                DFSDive(x);
            }
        }
        // Iterator<ArrayList<Integer>> it = newAdj.iterator();
        // int i = 0;
        // while (it.hasNext()) {
        //     System.out.println(i++ + " " + it.next().toString());
        // }
        return SCCs.size();
    }

    // Reverses arrows
    static void transpose(ArrayList<Integer>[] adj, Integer current) {
        visited.add(current);
        for (Integer x : adj[current]) {
            newAdj.get(x).add(current);
            if (!visited.contains(x)) {
            transpose(adj,x);}
        }
    }

    // diver for KaijuSort
    static void DFSDive(Integer vertex) {
        visited.add(vertex);
        SCCs.get(SCCs.size()-1).add(vertex);
        Iterator<Integer> it = newAdj.get(vertex).iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (!visited.contains(x)) {
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


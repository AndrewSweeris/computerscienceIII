import java.lang.reflect.Array;
import java.util.*;

public class Toposort {
    private static Stack<Integer> stack;
    private static boolean[] ary;
    private static ArrayList<Integer>[] graph;
    static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        stack = new Stack<>();
        ary = new boolean[adj.length];
        for(int i = 0; i < ary.length; i++) ary[i]=false;
        graph = adj;
        for (int i = 0; i < adj.length; i++) {
            if (!ary[i]) {
                DFS(i);
            }
        }
        ArrayList<Integer> output = new ArrayList<>();
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    static void DFS(int vertex) {
        ary[vertex]=true;
        Iterator<Integer> it = graph[vertex].iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (!ary[x]) {
                DFS(x);
            }
        }
        stack.push(vertex);
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}


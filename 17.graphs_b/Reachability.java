import java.util.*;

public class Reachability {
    static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.add(x);
        visited.add(x);

        while(!stack.empty()) {
            int curr = stack.pop();
            if (curr==y)return 1;
            for (Integer z : adj[curr]) {
                if (!visited.contains(z)) {
                    visited.add(z);
                    stack.add(z);
                }
            }
        }

        return 0;
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
        System.out.println(reach(adj, x, y));
    }
}


import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        // Since it's a tree, number of nodes n = edges.length + 1
        int n = edges.length + 1;
        
        // 1. Create the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 2. Add the undirected edges into the list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // 3. Apply BFS to find the maximum depth from root node 1
        Queue<Integer> qu = new LinkedList<>(); // Fixed capitalization
        boolean[] visited = new boolean[n + 1];
        
        qu.offer(1);
        visited[1] = true;
        
        int maxDepth = 0; // Declared outside the loop so it's visible everywhere
        
        while (!qu.isEmpty()) {
            int size = qu.size();
            
            for (int i = 0; i < size; i++) {
                int curr = qu.poll();
                
                for (int neighbor : adj.get(curr)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        qu.offer(neighbor);
                    }
                }
            }
            
            if (!qu.isEmpty()) {
                maxDepth++;
            }
        }
        
        // 4. Calculate the total combinations: (2^(maxDepth - 1)) % (10^9 + 7)
        long MOD = 1_000_000_007;
        long ans = 1;
        
        // Fast exponentiation loop to compute 2^(maxDepth - 1) % MOD safely
        for (int i = 0; i < maxDepth - 1; i++) {
            ans = (ans * 2) % MOD;
        }
        
        return (int) ans;
    }
}
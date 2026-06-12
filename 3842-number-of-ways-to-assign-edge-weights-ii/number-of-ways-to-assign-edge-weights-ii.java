class Solution {
    static final int MOD = 1_000_000_007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int l = edges.length;
        int n = l + 1;

        // 1. Build adjacency list
        List<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        // 2. Prepare binary lifting table size
        int LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] parent = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        // 3. BFS from root node 1
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;
        depth[1] = 0;
        parent[0][1] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adj[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    depth[neighbor] = depth[current] + 1;
                    parent[0][neighbor] = current;
                    queue.offer(neighbor);
                }
            }
        }

        // 4. Build parent table
        for (int level = 1; level < LOG; level++) {
            for (int node = 1; node <= n; node++) {
                int midParent = parent[level - 1][node];
                parent[level][node] = parent[level - 1][midParent];
            }
        }

        // 5. Precompute powers of 2
        long[] pow2 = new long[n + 1];

        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        // 6. Answer queries
        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int lca = getLCA(u, v, depth, parent);

            int distance = depth[u] + depth[v] - 2 * depth[lca];

            if (distance == 0) {
                answer[i] = 0;
            } else {
                answer[i] = (int) pow2[distance - 1];
            }
        }

        return answer;
    }

    private int getLCA(int u, int v, int[] depth, int[][] parent) {
        int LOG = parent.length;

        // Make u deeper
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Lift u up to same depth as v
        int diff = depth[u] - depth[v];

        for (int level = 0; level < LOG; level++) {
            if (((diff >> level) & 1) == 1) {
                u = parent[level][u];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both until just below LCA
        for (int level = LOG - 1; level >= 0; level--) {
            if (parent[level][u] != parent[level][v]) {
                u = parent[level][u];
                v = parent[level][v];
            }
        }

        return parent[0][u];
    }
}
import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indeg = new int[n];
        TreeSet<Integer> costSet = new TreeSet<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], c = e[2];
            graph[u].add(new int[]{v, c});
            indeg[v]++;
            costSet.add(c);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);

            for (int[] edge : graph[u]) {
                int v = edge[0];

                indeg[v]--;
                if (indeg[v] == 0) {
                    q.offer(v);
                }
            }
        }
        int[] costs = new int[costSet.size()];
        int idx = 0;
        for (int c : costSet) {
            costs[idx++] = c;
        }
        int ans = -1;
        int left = 0, right = costs.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (can(costs[mid], graph, topo, online, k)) {
                ans = costs[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
    private boolean can(
        int score,
        List<int[]>[] graph,
        List<Integer> topo,
        boolean[] online,
        long k
    ) {
        int n = online.length;
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        for (int u : topo) {
            if (!online[u] || dist[u] > k) {
                continue;
            }
            for (int[] edge : graph[u]) {
                int v = edge[0];
                int cost = edge[1];
                if (online[v] && cost >= score) {
                    long nd = dist[u] + cost;

                    if (nd < dist[v]) {
                        dist[v] = nd;
                    }
                }
            }
        }
        return dist[n - 1] <= k;
    }
}
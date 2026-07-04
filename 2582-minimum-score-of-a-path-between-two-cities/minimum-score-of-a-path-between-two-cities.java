import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] r : roads) {
            g[r[0]].add(new int[]{r[1], r[2]});
            g[r[1]].add(new int[]{r[0], r[2]});
        }
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seen = new boolean[n + 1];
        int ans = Integer.MAX_VALUE;
        q.offer(1);
        seen[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] e : g[u]) {
                ans = Math.min(ans, e[1]);

                if (!seen[e[0]]) {
                    seen[e[0]] = true;
                    q.offer(e[0]);
                }
            }
        }
        return ans;
    }
}
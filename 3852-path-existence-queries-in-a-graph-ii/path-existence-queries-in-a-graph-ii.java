class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> nums[a] - nums[b]);

        int[] vals = new int[n];
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            vals[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        int[] comp = new int[n];
        int cid = 0;

        for (int i = 1; i < n; i++) {
            if (vals[i] - vals[i - 1] > maxDiff) {
                cid++;
            }

            comp[i] = cid;
        }

        int LOG = 18;
        int[][] jump = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            int right = upperBound(vals, vals[i] + maxDiff) - 1;
            jump[0][i] = right;
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int a = pos[u];
            int b = pos[v];

            if (a == b) {
                ans[i] = 0;
                continue;
            }

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (comp[a] != comp[b]) {
                ans[i] = -1;
                continue;
            }

            int steps = 0;
            int cur = a;

            for (int k = LOG - 1; k >= 0; k--) {
                if (jump[k][cur] < b) {
                    cur = jump[k][cur];
                    steps += 1 << k;
                }
            }

            ans[i] = steps + 1;
        }

        return ans;
    }

    private int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
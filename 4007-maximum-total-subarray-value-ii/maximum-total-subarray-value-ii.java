import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        SparseTable st = new SparseTable(nums);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        for (int left = 0; left < n; left++) {
            long value = st.rangeMax(left, n - 1) - st.rangeMin(left, n - 1);
            pq.offer(new long[]{value, left, n - 1});
        }

        long ans = 0;

        while (k > 0) {
            long[] cur = pq.poll();

            long value = cur[0];
            int left = (int) cur[1];
            int right = (int) cur[2];

            ans += value;
            k--;

        
            if (right > left) {
                int newRight = right - 1;
                long newValue = st.rangeMax(left, newRight) - st.rangeMin(left, newRight);
                pq.offer(new long[]{newValue, left, newRight});
            }
        }

        return ans;
    }

    static class SparseTable {
        int[][] minTable;
        int[][] maxTable;
        int[] log;

        SparseTable(int[] nums) {
            int n = nums.length;

            log = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }

            int K = log[n] + 1;

            minTable = new int[K][n];
            maxTable = new int[K][n];

            for (int i = 0; i < n; i++) {
                minTable[0][i] = nums[i];
                maxTable[0][i] = nums[i];
            }

            for (int level = 1; level < K; level++) {
                int len = 1 << level;
                int half = len >> 1;

                for (int i = 0; i + len <= n; i++) {
                    minTable[level][i] = Math.min(
                        minTable[level - 1][i],
                        minTable[level - 1][i + half]
                    );

                    maxTable[level][i] = Math.max(
                        maxTable[level - 1][i],
                        maxTable[level - 1][i + half]
                    );
                }
            }
        }

        int rangeMin(int left, int right) {
            int len = right - left + 1;
            int level = log[len];

            return Math.min(
                minTable[level][left],
                minTable[level][right - (1 << level) + 1]
            );
        }

        int rangeMax(int left, int right) {
            int len = right - left + 1;
            int level = log[len];

            return Math.max(
                maxTable[level][left],
                maxTable[level][right - (1 << level) + 1]
            );
        }
    }
}
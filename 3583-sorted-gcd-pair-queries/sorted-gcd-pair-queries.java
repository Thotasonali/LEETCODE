class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        long[] F = new long[maxVal + 1];
        
        for (int g = maxVal; g >= 1; g--) {
        
            long countMultiples = 0;
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                countMultiples += cnt[multiple];
            }
            long totalPairsWithCommonDivisorG = countMultiples * (countMultiples - 1) / 2;
            long exactGcdPairs = totalPairsWithCommonDivisorG;
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                exactGcdPairs -= F[multiple];
            }
            F[g] = exactGcdPairs;
        }
        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + F[i];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int low = 1, high = maxVal;
            int best = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (pref[mid] > q) {
                    best = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = best;
        }
        return ans;
    }
}
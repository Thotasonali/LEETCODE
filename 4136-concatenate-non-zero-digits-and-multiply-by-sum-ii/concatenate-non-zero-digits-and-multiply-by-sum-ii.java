class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int MOD = 1_000_000_007;
        int n = s.length();

        int[] count = new int[n + 1];    
        long[] sum = new long[n + 1];     
        long[] value = new long[n + 1];  
        long[] pow10 = new long[n + 1];   
        pow10[0] = 1;

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';

            count[i + 1] = count[i];
            sum[i + 1] = sum[i];
            value[i + 1] = value[i];

            if (d != 0) {
                count[i + 1]++;
                sum[i + 1] += d;
                value[i + 1] = (value[i] * 10 + d) % MOD;
            }

            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int len = count[r + 1] - count[l];
            long digitSum = sum[r + 1] - sum[l];

            long x = (value[r + 1] - value[l] * pow10[len]) % MOD;
            if (x < 0) x += MOD;

            ans[i] = (int)((x * digitSum) % MOD);
        }

        return ans;
    }
}
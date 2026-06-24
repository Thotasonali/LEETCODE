class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r- l + 1;
        int size = 2 * m;

        long[] vec = new long[size];

        for (int v = 0; v < m; v++) {
            vec[v] = v;
            vec[m + v] = m - 1 - v;
        }

        long[][] trans = new long[size][size];

        for (int old = 0; old < m; old++) {
            for (int next = 0; next < old; next++) {
                trans[m + next][old] = 1;
            }

            for (int next = old + 1; next < m; next++) {
                trans[next][m + old] = 1;
            }
        }

        long power = n - 2L;

        while (power > 0) {
            if ((power & 1) == 1) {
                vec = multiply(trans, vec);
            }

            trans = multiply(trans, trans);
            power >>= 1;
        }

        return (int) sum(vec);
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = vec.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long total = 0;

            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0 && vec[j] != 0) {
                    total = (total + mat[i][j] * vec[j]) % MOD;
                }
            }

            res[i] = total;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }

    private long sum(long[] arr) {
        long ans = 0;

        for (long x : arr) {
            ans = (ans + x) % MOD;
        }

        return ans;
    }
}
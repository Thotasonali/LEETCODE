class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long totalPoints = n + k - 1;
        long choose = 2 * k;

        if (choose > totalPoints) return 0;

        long num = 1, den = 1;
        for (long i = 1; i <= choose; i++) {
            num = (num * (totalPoints - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        // Divide num by den using Fermat's Little Theorem for Modular Inverse
        return (int) (num * modInverse(den, MOD) % MOD);
    }

    private long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
class Solution {
    public long sumAndMultiply(int n) {
        long sum = 0;
        String s = String.valueOf(n);
        String m = "";

        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - '0';

            if (val != 0) {
                m += s.charAt(i);
                sum += val;
            }
        }

        if (m.length() == 0) return 0;

        return Long.parseLong(m) * sum;
    }
}
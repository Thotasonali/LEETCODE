class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];
        long length = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                length++;
            } else if (ch == '*') {
                if (length > 0) {
                    length--;
                }
            } else if (ch == '#') {
                length *= 2;
            }

            len[i] = length;
        }

        if (k >= length) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = i == 0 ? 0 : len[i - 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == prevLen) {
                    return ch;
                }
            } else if (ch == '#') {
                if (prevLen > 0) {
                    k = k % prevLen;
                }
            } else if (ch == '%') {
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}
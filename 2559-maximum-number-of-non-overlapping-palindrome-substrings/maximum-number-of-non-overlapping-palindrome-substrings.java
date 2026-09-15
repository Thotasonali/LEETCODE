class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int i = 0;

        while (i < n) {
            int foundLen = -1;
            
            for (int len = k; len <= k + 1; len++) {
                if (i + len <= n && isPalindrome(s, i, i + len - 1)) {
                    foundLen = len;
                    break;
                }
            }

            if (foundLen != -1) {
                count++;
                i += foundLen;
            } else {
                i++;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
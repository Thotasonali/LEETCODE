class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] freq = new int[2 * n + 3];
        int off = n + 1;

        int p = 0;
        long ans = 0;
        long less = 0;

        freq[off] = 1;

        for (int x : nums) {
            if (x == target) {
                less += freq[p + off];
                p++;
            } else {
                p--;
                less -= freq[p + off];
            }

            ans += less;
            freq[p + off]++;
        }

        return ans;
    }
}
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int score = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    score++;
                } else {
                    score--;
                }

                if (score > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Elimination of Math.max for current sum tracking
            sum = (nums[i] > sum + nums[i]) ? nums[i] : sum + nums[i];
            
            // Elimination of Math.max for global max tracking
            if (sum > max) {
                max = sum;
            }
        }
        
        return max;
    }
}
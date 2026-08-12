class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            count.put(current, count.getOrDefault(current, 0) + 1);
            while (count.get(current) > k) {
                int leftVal = nums[left];
                count.put(leftVal, count.get(leftVal) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
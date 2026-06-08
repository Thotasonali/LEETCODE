class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int l = nums.length;
        int[] f = new int[l];

        int left = 0;
        int right = l - 1;

        for (int i = 0, j = l - 1; i < l; i++, j--) {
            if (nums[i] < pivot) {
                f[left] = nums[i];
                left++;
            }

            if (nums[j] > pivot) {
                f[right] = nums[j];
                right--;
            }
        }

        while (left <= right) {
            f[left] = pivot;
            left++;
        }

        return f;
    }
}
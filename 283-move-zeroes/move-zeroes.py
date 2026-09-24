class Solution:
    def moveZeroes(self, nums: list[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        insert_pos = 0
        
        # Step 1: Move all non-zero elements to the front
        for num in nums:
            if num != 0:
                nums[insert_pos] = num
                insert_pos += 1
                
        # Step 2: Fill the remaining positions with zeroes
        for i in range(insert_pos, len(nums)):
            nums[i] = 0
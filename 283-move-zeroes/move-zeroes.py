class Solution:
    def moveZeroes(self, nums: list[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        l=len(nums)
        one=0
        two=0
        for two in range (l):
            if(nums[two]!=0):
                temp=nums[two]
                nums[two]=nums[one]
                nums[one]=temp
                one+=1
             
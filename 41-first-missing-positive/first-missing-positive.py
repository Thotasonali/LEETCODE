class Solution:
    def firstMissingPositive(self, nums: list[int]) -> int:
        n=len(nums)
        for i in range(n):
            if(nums[i]<=0 or nums[i]>n):
                nums[i]=n+1
        for i in range(n):
            if(1<=abs(nums[i])<=n):
                idx=abs(nums[i])-1
                nums[idx]=-abs(nums[idx])
        for i in range(n):
            if(nums[i]>0):
                return i+1
        return n+1
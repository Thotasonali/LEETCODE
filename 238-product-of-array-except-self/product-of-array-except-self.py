class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        
        n=len(nums)
        ps=[1]*n
        
        for i in range(1,n):
            ps[i]=ps[i-1]*nums[i-1]
        ss=[1]*n
        
        for i in range(n-2 ,-1,-1):
            ss[i]=ss[i+1]*nums[i+1]
        res=[1]*n
        for i in range(n):
            res[i]=ss[i]*ps[i]
        return res
        

        
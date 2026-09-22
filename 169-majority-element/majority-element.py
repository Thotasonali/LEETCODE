class Solution:
    def majorityElement(self, nums: list[int]) -> int:
        count=0
        maj=0
        for num in nums:
            if count==0:
                maj=num
            if num==maj:
                count+=1
            else:
                count-=1
        return maj

       

        

        
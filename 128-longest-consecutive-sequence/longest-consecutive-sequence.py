class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        l=len(nums)
        check = set(nums) 
        max_seq= 0
        for num in check:
            #only start counting if num is in check
            if num-1 not in check:
                current_num=num
                current_seq=1

                while current_num+1 in check:
                    current_num+=1
                    current_seq+=1
                max_seq=max(current_seq,max_seq)
        return max_seq



            

        
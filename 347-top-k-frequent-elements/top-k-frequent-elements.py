import heapq
from collections import Counter
class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        freq=Counter(nums)
        heap=[]
        for num,count in freq.items():
            heapq.heappush(heap,(count,num))
            if len(heap)>k:
                heapq.heappop(heap)
        result=[]
        for i in range(k):
            count,num=heapq.heappop(heap)
            result.append(num)
        return result



        
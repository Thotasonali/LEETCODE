from collections import defaultdict
from typing import List


class Solution:

  def subarraySum(self, nums: List[int], k: int) -> int:
    check =defaultdict(int)
    check[0]=1
    count=0
    current=0
    for n in nums:
        current=current+n
        need=current-k
        if(need in check):
            count+=check[need]
        check[current]+=1
    return count
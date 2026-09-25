class Solution:
    def maxArea(self, height: list[int]) -> int:
        n=len(height)
        left=0
        right=n-1
        max_area = float("-inf")
        #here we will compare hei
        if(n<=1): return 0

        while(left>=0 and right <n):
            level=min(height[left],height[right])
            area=level*abs(left-right)
            max_area=max(area,max_area)
            if(left==right):
                return max_area
            if (height[left]<height[right]):
                left+=1
            else:
                right-=1
        return max_area



        
class Solution:
    def trap(self, height: list[int]) -> int:
        l=[]
        r=[]
        ans=0
        n=len(height)
        maxL,maxR=0,0
        for i in range(n):
            l.append(maxL)
            
            maxL=max(height[i],maxL)
        for i in range(n-1,-1,-1):
            r.append(maxR)
            
            maxR=max(height[i],maxR)
        #print(l)
        r.reverse()
        #print(r)
        for i in range(n):
            mini=min(l[i],r[i])
            #print(l[i],r[i])
            store=mini-height[i] #sub present val in min height 
            #print(mini,store,ans)
            if(store<=0):
                continue
            else:
                ans+=store
        return ans
           
            
      
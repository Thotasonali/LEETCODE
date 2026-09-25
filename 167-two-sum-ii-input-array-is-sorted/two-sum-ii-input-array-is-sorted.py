class Solution:
    def twoSum(self, numbers: list[int], target: int) -> list[int]:
        n=len(numbers)
        i=0
        j=n-1
        
        
        summ=0
        while(i>=0 and j<n):
            
            summ=numbers[i]+numbers[j]
            if(summ==target):
                return [i+1,j+1]
            if (summ)>target:
                j-=1   
            else:
                i+=1
               
      
        
                
            
        


            i
        
class Solution {
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int sum=Integer.MIN_VALUE;
        int current=0;
        for(int i=0;i<n;i++){
            current=current+nums[i];
            sum=Math.max(sum,current);
            if(current<0){
                current=0;
            }
            
            
        }
        return sum;
    }
}
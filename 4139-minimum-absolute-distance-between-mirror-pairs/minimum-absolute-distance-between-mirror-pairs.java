class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n=nums.length;
        int min=Integer.MAX_VALUE;
        HashMap <Integer,Integer> map=new HashMap<>();
        int absol,revindex,rev;
        for (int i=0;i<n;i++){
            rev=rever(nums[i]);
            if(map.containsKey(nums[i])){
                revindex=map.get(nums[i]);
                absol=Math.abs(i-revindex);
                min=Math.min(min,absol);
            }
            map.put(rev,i);


        }
        return min==Integer.MAX_VALUE?-1:min;
        
    }
    public int rever(int num){
        int reverse=0;
        int rem;
        while (num>0){
            rem=num%10;
            reverse=reverse*10+rem;
            num=num/10;
        }
        return reverse;
      
    }
}

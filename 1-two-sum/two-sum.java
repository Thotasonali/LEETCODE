class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hsh=new HashMap();
        int l=nums.length;
        int diff=0;
        for (int i=0;i<l;i++){
            diff=target-nums[i];
            if(hsh.containsKey(diff)){
                return new int[]{hsh.get(diff),i };
            } 
            else{
                hsh.put(nums[i],i);
            }

        }
    
    return new int[]{};
    }
}
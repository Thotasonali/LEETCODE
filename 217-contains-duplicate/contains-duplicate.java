class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> check=new HashSet();
        for(int i=0;i<nums.length;i++){
            if(check.contains(nums[i])){
                return true;
            }
            else{
                check.add(nums[i]);
            }
        }
        return false;


        
    }
}
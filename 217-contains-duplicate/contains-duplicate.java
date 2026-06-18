class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> check=new HashSet<>();
        for(int num:nums ){
            if(!check.add(num)){
                return true;
            }
            else{
                check.add(num);
            }
        }
        return false;


        
    }
}
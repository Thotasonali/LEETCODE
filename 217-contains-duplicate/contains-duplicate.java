
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}
/*class Solution {
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
*/
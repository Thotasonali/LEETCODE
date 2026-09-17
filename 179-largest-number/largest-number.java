class Solution {
    public String largestNumber(int[] nums) {
        String arr[]=new String[nums.length];
        // convert to string array
        for(int i =0;i<nums.length;i++){
            arr[i]=String.valueOf(nums[i]);
        }
        //sort the array list
        Arrays.sort(arr, (a,b)->(b + a).compareTo(a + b));

        //convert arrays has only zeroes

        if(arr[0].equals("0")){
            return "0";
        }

        //return arrayslist into string
        StringBuilder sb=new StringBuilder();
        for(String s :arr){
            sb.append(s);
        }
    return sb.toString();



       
    }
}
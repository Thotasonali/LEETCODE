class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set <Character> jew =new HashSet<>();
        int count=0;
        for(int i=0;i<jewels.length();i++){
            jew.add(jewels.charAt(i));
        }
        for(char c: stones.toCharArray()){
            if(jew.contains(c)){
                count++;
            }
        }
        return count;
    }
}
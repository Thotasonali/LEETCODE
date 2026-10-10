class Solution {
    public int characterReplacement(String s, int k) {
             //in brute force we are tring to change all the characters into current character until k replacements and counts length and again starts from next element in the strng
        // but this is taking multiple times to traverse to strng with o(n^2) 
        //So we are trying to change all the remain chars replace with the most occured char
        HashMap <Character,Integer> count=new HashMap<>();
        int maxOccur=0;
        int n =s.length();
        int left =0;
        int ans=0;
        for (int right=0;right<n;right++) {
            char ch=s.charAt(right);
            
            count.put(ch, count.getOrDefault(ch,0)+1); 
            int current=count.get(ch);
            maxOccur = Math.max(maxOccur, current);
           //shrink  if invalid
            while((right- left+1)-maxOccur>k){
                
                count.put(s.charAt(left),(count.get(s.charAt(left)))-1);
                
                left++;
            }
            //cal after shrinkin
            ans=Math.max((right-left)+1, ans);
        }
        return ans;

        

        

        
        
    }
}
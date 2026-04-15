class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n=words.length;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(words[i].equals(target)){
                int forward=Math.abs(i-startIndex);
                int backward=n-forward;
                int dist=Math.min(forward,backward);
                min=Math.min(min,dist);
            }
        }
        return min==Integer.MAX_VALUE?-1:min;

        
    }
}
        

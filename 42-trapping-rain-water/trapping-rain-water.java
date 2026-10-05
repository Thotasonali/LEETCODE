class Solution {
    public int trap(int[] height) {
        // we need to store holding for waterso we are storing both the bondaries  and then we are finding minimum height as it is limiting (decidinig factor) then we are subtracting the current value in the min boundary 

        int n=height.length;
        int maxL=0;
        int maxR=0;
        int ans=0;
        int l[]=new int[n];
        int r[]=new int[n];
        for (int i=0;i<n;i++){
            //traversing through left array and populating left array to note left bpoundaries
            l[i]=maxL;
            maxL=Math.max(height[i],maxL);


        }
        for (int i=n-1;i>-1;i--){
            //traversing through right array and populating right array to note left bpoundaries
            r[i]=maxR;
            maxR=Math.max(height[i],maxR);


        }
        for (int i=0;i<n;i++){
            int mini=Math.min(l[i],r[i]);
            int store=mini-height[i];
            if (store>0){
                ans+=store;
            }
        }
        return ans;
        
    }
}
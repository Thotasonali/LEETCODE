class Solution {
    public int maxProfit(int[] prices) {
        int profit=0,diff=0;
        int len=prices.length;
        int l=0;
        for(int r=1;r<len;r++){
            
            if(prices[l]<prices[r]){
                diff=prices[r]-prices[l];
                if(profit<diff){
                    profit=diff;
                    
                }
                

            }else {
                l=r;
            
            }
            
        }
        return profit ; 
    }
}
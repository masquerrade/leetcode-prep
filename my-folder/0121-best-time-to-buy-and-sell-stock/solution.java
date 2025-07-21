class Solution {
    public int maxProfit(int[] prices) {

        //currMin 7     1   1   1   1   1
        //currDiff 0  -6    4   2   6   4
        //maxDiff 4 5

        int currMin=prices[0];
        int currDiff=0;
        int maxDiff=0;


        for(int p : prices){
            
            //Trick is only running minimum and maximum we need to take. If something is gone we don't need to go back.


            currMin=Math.min(p,currMin);
            currDiff=p - currMin;
            maxDiff=Math.max(currDiff,maxDiff);
        }

        return maxDiff;
        
    }
}

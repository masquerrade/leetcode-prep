class Solution {
    public int change(int amount, int[] coins) {

        //Fix the coin and find all the ways to make the amount one by one 

        int[] dp=new int[amount+1];
        //No of ways to make 0 is 1 that is to take 0 coins

        dp[0]=1;

        for(int c:coins){

            for(int start=c;start<=amount;start++){
                dp[start]+=dp[start-c];
            }
        }

        return dp[amount];
        
    }
}

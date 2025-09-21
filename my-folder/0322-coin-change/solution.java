class Solution {
    public int coinChange(int[] coins, int amount) {

        //[1,2] ->2
        //[1,]
        //What is the best approach is to start from the small and find the minimum coins needed for that and then for a bigger value use the current coin and take the minimum coins needed for the remaining amount from the already calculated value

        int [] dp=new int[amount+1];
        Arrays.fill(dp,amount+1);

        dp[0]=0;

        for(int i=0;i<=amount;i++ ){
            //I've to calculate minimum for each amount less than the final amount
            for(int c:coins){
                if(c<=i){
                    //I'm checking the minimum by using each coin as the last coin
                    dp[i]=Math.min(1+dp[i-c],dp[i]);
                }
            }
        }
        if(dp[amount]<amount+1){
            return dp[amount];
        }
        else{
            return -1;
        }

        
    }
}

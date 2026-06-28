class Solution {
    public int coinChange(int[] coins, int amount) {

        //Core logic-> Break the total amount into smaller steps and using the previos step to find the smallest for the next step

        //DP
        /* Step1 -> I want to make 1 , min no of coins needed 
        Iterate throgh all the options min(current, (1+dp[target-option]))
        */

        //Recursion 
        //min (1+min(target-currentOption) for all the options

        int[] dp=new int[amount+1];

        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;

        //For each amount I need to explore all the options and keep the minimum

        for(int i=1;i<dp.length;i++){

            for(int cost:coins){
                dp[i]=(i>=cost && dp[i-cost]!=Integer.MAX_VALUE)?Math.min(dp[i-cost]+1,dp[i]):dp[i];
            }

        }
        System.out.println(Arrays.toString(dp));

        return (dp[amount]==Integer.MAX_VALUE)?-1:dp[amount];
    }
}


// class Solution {
//     public int coinChange(int[] coins, int amount) {

//         //[1,2] ->2
//         //[1,]
//         //What is the best approach is to start from the small and find the minimum coins needed for that and then for a bigger value use the current coin and take the minimum coins needed for the remaining amount from the already calculated value

//         int [] dp=new int[amount+1];
//         Arrays.fill(dp,amount+1);

//         dp[0]=0;

//         for(int i=0;i<=amount;i++ ){
//             //I've to calculate minimum for each amount less than the final amount
//             for(int c:coins){
//                 if(c<=i){
//                     //I'm checking the minimum by using each coin as the last coin
//                     dp[i]=Math.min(1+dp[i-c],dp[i]);
//                 }
//             }
//         }
//         if(dp[amount]<amount+1){
//             return dp[amount];
//         }
//         else{
//             return -1;
//         }

        
//     }
// }

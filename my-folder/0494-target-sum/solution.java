class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        //p-n=target
        //p+n=totalSum
        //p=(target+totalSum)/2= Subset sum
        //How many ways I can achieve a subset sum of p

        //Total sum
        int totalSum=0;
        for(int num:nums){
            totalSum+=num;
        }

        //If total sum is less than target we can't reach the target
        if(totalSum<target){
            return 0;
        }

        int subsetSum=(target+totalSum)/2;


        if((target+totalSum)%2!=0 || subsetSum<0){
            return 0;
        }


        //In how many ways I can make subset sum using nums

        int[] dp=new int[subsetSum+1];

        //1 way to make 0 sum is that we don't choose anything
        dp[0]=1;

        for(int num:nums){

            //Iterate through the dp array backward as we can use num only once in one iteration
            for(int i=dp.length-1;i>=0;i--){
                //Current ways to reach till i + No of ways to reach from i-num
                dp[i]=(i>=num)?dp[i]+dp[i-num]:dp[i];
            }
        }

        return dp[subsetSum];
        
    }
}

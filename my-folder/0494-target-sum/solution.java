class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Edge Case 1: Target is unreachable even if all elements are positive or negative
        if (Math.abs(target) > totalSum) {
            return 0;
        }

        // Edge Case 2: The required subset sum must be an integer
        if ((totalSum + target) % 2 != 0) {
            return 0;
        }

        // The target sum for the positive subset
        int subsetSum = (totalSum + target) / 2;

        // DP array to store the number of ways to reach each sum
        int[] dp = new int[subsetSum + 1];
        
        // Base case: 1 way to make sum 0 (choose nothing)
        dp[0] = 1; 

        // Populate the DP table
        for (int num : nums) {
            // Traverse backward to simulate 0/1 Knapsack behavior (each item used at most once)
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[subsetSum];
    }
}

// class Solution {
//     public int findTargetSumWays(int[] nums, int target) {

//         //p-n=target
//         //p+n=totalSum
//         //p=(target+totalSum)/2= Subset sum
//         //How many ways I can achieve a subset sum of p

//         //Total sum
//         int totalSum=0;
//         for(int num:nums){
//             totalSum+=num;
//         }

//         //If total sum is less than target we can't reach the target
//         if(totalSum<target){
//             return 0;
//         }

//         int subsetSum=(target+totalSum)/2;

//         //Subset sum should always be positive
//         if((target+totalSum)%2!=0 || subsetSum<0){
//             return 0;
//         }


//         //In how many ways I can make subset sum using nums

//         int[] dp=new int[subsetSum+1];

//         //1 way to make 0 sum is that we don't choose anything
//         dp[0]=1;

//         for(int num:nums){

//             //Iterate through the dp array backward as we can use num only once in one iteration
//             for(int i=dp.length-1;i>=0;i--){
//                 //Current ways to reach till i + No of ways to reach from i-num
//                 dp[i]=(i>=num)?dp[i]+dp[i-num]:dp[i];
//             }
//         }

//         return dp[subsetSum];
        
//     }
// }

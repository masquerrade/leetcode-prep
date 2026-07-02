// // // //Gemini solution
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // Edge case: if nums is null or empty, no combinations can be formed
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] represents the number of combinations that sum up to i
        int[] dp = new int[target + 1];

        // Base case: 1 way to reach sum 0 (choose no elements)
        dp[0] = 1;

        // Build the DP table bottom-up
        for (int currentSum = 1; currentSum <= target; currentSum++) {
            for (int num : nums) {
                // If the current number can be a part of the currentSum
                if (currentSum - num >= 0) {
                    dp[currentSum] += dp[currentSum - num];
                }
            }
        }

        return dp[target];
    }
}



// class Solution {
//     public int combinationSum4(int[] nums, int target) {

//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         //I can see the overlapping subproblems that I'm going to use 
//         //I'll simply solve the subproblems
//         int[] dp=new int[target+1];
//         //No of combination that add up to 0. IMp
//         dp[0]=1;

//         // for(int i=1;i<=dp.length;i++){ This reduces speed
//         for(int i=1;i<=target;i++){
//             for(int num:nums){
//                 if(i>=num){
//                     dp[i]=dp[i]+dp[i-num];
//                 }
//                 //dp[i]=(i>=num)?dp[i]+dp[i-num]:dp[i];// This is the trap. For this to work dp[0] should be initialised to 1 //Also this reduces speed


//             }
//         }

//         return dp[target];
        
//     }
// }


// class Solution {
//     public int combinationSum4(int[] nums, int target) {

//         int [] dp=new int[target+1];
//         dp[0]=1;

//         for(int i=1;i<=target;i++){
//             for(int num :nums){
//                 //For current iteration i is my target
//                 if(num<=i){
//                     dp[i]=dp[i]+dp[i-num];
//                 }
//             }
//         }


//         return dp[target];
        
//     }
// }

class Solution {
    public int combinationSum4(int[] nums, int target) {

        //I can see the overlapping subproblems that I'm going to use 
        //I'll simply solve the subproblems
        int[] dp=new int[target+1];
        //No of combination that add up to 0. IMp
        dp[0]=1;

        for(int i=0;i<dp.length;i++){
            System.out.println(Arrays.toString(dp));
            for(int num:nums){
                dp[i]=(i>=num)?dp[i]+dp[i-num]:dp[i];// This is the trap. For this to work dp[0] should be initialised to 1


            }
        }

        return dp[target];
        
    }
}


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

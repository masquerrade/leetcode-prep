class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        int []dp=new int[n+1];

        dp[1]=cost[0];

        for(int i=2;i<=n;i++){
            dp[i]=cost[i-1]+Math.min(dp[i-1],dp[i-2]);
        }
        //I need to climb on top not on the  last stair
        return Math.min(dp[n],dp[n-1]);


        
    }
}

// class Solution {
//     /**
//      * Calculates the minimum cost to reach the top of the staircase.
//      *
//      * @param cost An array where cost[i] is the cost to step on the i-th stair.
//      * @return The minimum cost to climb to the top.
//      */
//     public int minCostClimbingStairs(int[] cost) {
//         // 'n' is the index of the "top" of the floor, one step past the last stair.
//         int n = cost.length;

//         // 'two_steps_back' stores the minimum cost to reach the stair two steps behind the current one.
//         // Corresponds to dp[i-2]. Initialized to 0 for the start.
//         int two_steps_back = 0;
        
//         // 'one_step_back' stores the minimum cost to reach the stair one step behind the current one.
//         // Corresponds to dp[i-1]. Initialized to 0 for the start.
//         int one_step_back = 0;

//         // Iterate from the third step (index 2) up to and including the "top" floor (index n).
//         for (int i = 2; i <= n; i++) {
//             // The minimum cost to reach the current step 'i' is the minimum of two options:
//             // 1. Taking a single step from stair 'i-1' (cost[i-1]) plus the cost to get there (one_step_back).
//             // 2. Taking two steps from stair 'i-2' (cost[i-2]) plus the cost to get there (two_steps_back).
//             int current_cost = Math.min(one_step_back + cost[i - 1], two_steps_back + cost[i - 2]);

//             // Update the pointers for the next iteration.
//             two_steps_back = one_step_back;
//             one_step_back = current_cost;
//         }

//         // 'one_step_back' now holds the minimum cost to reach the top of the floor (step 'n').
//         return one_step_back;
//     }
// }

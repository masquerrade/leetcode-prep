class Solution {
    public boolean canPartition(int[] nums) {

        int fullSum=0;
        for(int n:nums){
            fullSum+=n;
        }

        if(fullSum%2!=0){
            return false;
        }


        int subsetSum=fullSum/2;

        //Here I'm finding all the possible combinations till the target
        boolean [] isSumPossible=new boolean[subsetSum+1];

        isSumPossible[0]=true;

        //One issue with this approach is that I cannot go from front to back
        //[3,4,5] ->  In this case it will fail
        //[3,3,4,5]
        //[1,2,5] -> Fail

        // for(int i=1;i<=subsetSum;i++){
        //     for(int e:nums){
        //         //This is also not a valid solution as intermediate sums would have been made with using the creent char and now we cannot use them
        //         if(i-e>=0 && isSumPossible[i-e]&& (e!=(i-e))){
        //             //What will happen in case of 6 is that with one 3 only it will be able to make 6 as we have already marked it as true
        //             //Here I need to set the target as true
        //             isSumPossible[i]=true;
        //             break;
        //         }
        //     }
        //     System.out.println(Arrays.toString(isSumPossible));
        // }

        //I need to reverse the for loop. Previously I was cecking for each 
        //I'm going to take a candidate number and find out with this number what all targets can be achieved
        //[3,3,4,5] Very important optimization 
        //We ierate through the candidates multiple times for each target in case candidates can be used multiple times 
        //We iterate throught the target multiple times for each candidate when we can use a cn=andidate only once and also in reverse direction 

        for(int num:nums){
            for(int i=subsetSum;i>0;i--){
                 if(i-num>=0 && isSumPossible[i-num]){
                    //What will happen in case of 6 is that with one 3 only it will be able to make 6 as we have already marked it as true
                    //Here I need to set the target as true
                    isSumPossible[i]=true;
                }
            }
        }


        return isSumPossible[subsetSum];
        
    }
}

// class Solution {
//     /**
//      * Determines if the array can be partitioned into two subsets with equal sums.
//      *
//      * @param nums An array of positive integers.
//      * @return true if the array can be partitioned, false otherwise.
//      */
//     public boolean canPartition(int[] nums) {
//         // Step 1: Calculate the total sum of the array.
//         int totalSum = 0;
//         for (int num : nums) {
//             totalSum += num;
//         }

//         // Step 2: If the total sum is odd, it's impossible to partition into two equal sums.
//         if (totalSum % 2 != 0) {
//             return false;
//         }

//         // Step 3: Define the target sum for each subset.
//         int target = totalSum / 2;

//         // Step 4: Create a DP array. dp[i] is true if a sum of 'i' can be achieved.
//         boolean[] dp = new boolean[target + 1];
        
//         // Base case: A sum of 0 is always possible (by choosing no elements).
//         dp[0] = true;

//         // Step 5: Iterate through each number and update the DP table.
//         // This is the core 0/1 Knapsack logic.
//         for (int num : nums) {
//             // We iterate backward to prevent using the same element multiple times in one subset.
//             for (int j = target; j >= num; j--) {
//                 // A sum 'j' can be made if:
//                 // 1. It was already possible to make 'j' (dp[j]).
//                 // 2. We can make 'j - num' and we add the current 'num'.
//                 dp[j] = dp[j] || dp[j - num];
//             }
//         }

//         // Step 6: The result is whether the target sum can be achieved.
//         return dp[target];
//     }
// }

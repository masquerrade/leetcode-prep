class Solution {
    public int lengthOfLIS(int[] nums) {        
        //DP approach
        //Iterate through the array and find longest increasing subsequence till now and store it in the dp array.
        //[10,9,2,5,3,7,101,18]
        //No need to make arint n=nums.length;
        // int ls=1;
        // int currMin=nums[1];


        // This won't work because LIS is not only dependent on LIS till now and Max or min till now. It is dependent on LIS till the previous element and LIS till the current element
        // for(int i=1;i<n;i++){
        //     if(nums[i]>currMin){
        //         ls++
        //     }
        //     else{
        //         currMin=Math.min(currMin,nums[i]);
        //     }

        // The trick is to calculate the LIS for each subarray ending at the current index and make use of the LIS of the previous index when the value at the previous index is less than the current index

        int n=nums.length;
        int [] dp =new int[n];

        Arrays.fill(dp,1);
        int lis=1;
        for(int i=1;i<n;i++){
            //This is important for the case when there is number till the current j which is smaller than the current i then it should hold the LIS of the previos index
            //In this case we cannot carry forward because in the dp I need max LIS for the current element and not till now
            /*dp[i]=dp[i-1];*/
            //In this case I need to keep track of the max LIS for each index in a seperate variable
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    /*dp[i]=Math.max(dp[i-1],dp[j]+1);This is not dependent of i-1 but on i and j*/
                    //System.out.println(Arrays.toString(dp));
                }
                lis=Math.max(lis,dp[i]);
            }
        }

        return lis;
     
        
    }
}

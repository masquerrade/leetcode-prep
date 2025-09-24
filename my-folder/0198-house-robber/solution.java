class Solution {
    public int rob(int[] nums) {
        //[1,2,3,1]

        //The trick is that profit till now depends on the max of last two houses 
        //Either we will take the last second house and add the current one or we will take the last house and not add the current one
        //DP approach
        // int lastDayPf=nums[0];
        // int last2DayPf=0;

        // for(int i=1;i<nums.length;i++){
        //     int maxTillNow=Math.max(lastDayPf,last2DayPf+nums[i]);
        //     last2DayPf=lastDayPf;
        //     lastDayPf=maxTillNow;      
        // }

        // return lastDayPf;

        //Brute force recursion approach

        //I will take the cuurent value and ask recursion the value of the remaining by skipping one index
        //I will skip the current and find the max of the remaining
        int [] memo=new int [nums.length];
        Arrays.fill(memo,-1);

        return robHelperMemo(nums,0,memo);


        
    }
    //This will give TLE error
    int robHelper(int[] nums,int startInd ){

        if(startInd>=nums.length){
            return 0;
        }

        int maxIncluding=nums[startInd]+robHelper(nums,startInd+2);
        int maxEncluding=robHelper(nums,startInd+1);
        return Math.max(maxIncluding,maxEncluding);

    }
    //memoization
    int robHelperMemo(int[] nums,int startInd ,int []memo){

        //I need to store the result for each start index in my memo array
         if(startInd>=nums.length){
            return 0;
        }

        //Memo should not be initialised here
        // int [] memo=new int [nums.length];
        // Arrays.fill(memo,-1);
        

        if(memo[startInd]!=-1){
            return memo[startInd];
        }

        int maxIncluding=nums[startInd]+robHelperMemo(nums,startInd+2,memo);
        int maxEncluding=robHelperMemo(nums,startInd+1,memo);
        memo[startInd]=Math.max(maxIncluding,maxEncluding);

        return memo[startInd];


    }
}

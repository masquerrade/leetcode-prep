class Solution {
    public int maxSubArray(int[] nums) {
        int currSum=0,maxSum=nums[0];
        for(int e:nums){
            currSum=Math.max(currSum+e,e);
            maxSum=Math.max(currSum,maxSum);
        }

        return maxSum;
    }
}

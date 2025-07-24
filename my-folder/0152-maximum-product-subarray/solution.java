class Solution {
    public int maxProduct(int[] nums) {

        //
        int minProd=nums[0];
        int maxProd=nums[0];
        int currProd=nums[0];

        for(int i=1;i<nums.length;i++){
            int tempMin=minProd;
            minProd=Math.min(nums[i],Math.min(minProd*nums[i],currProd*nums[i]));
            currProd=Math.max(nums[i],Math.max(tempMin*nums[i],currProd*nums[i]));
            maxProd=Math.max(maxProd,currProd);
        }

        return maxProd;
        
    }
}

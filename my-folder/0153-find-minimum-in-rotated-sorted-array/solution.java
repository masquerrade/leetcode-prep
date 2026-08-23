class Solution {
    public int findMin(int[] nums) {

        //What I can think is that if both the halves are sorted the minimum will be first of left half 
        //Otherwise min will be in the unsorted half
        //Go on finding the unsorted half , iignoring the sorted half until both the half are sorted

        //Base case
        if(nums == null || nums.length==0){
            throw new IllegalArgumentException("Array can't be empty");
        }

        int l= 0;
        int r= nums.length-1;

        //Not l<=r as it is the termination case
        while(l<r){
            int  m= l + (r-l)/2;

            if(nums[m] < nums[r]){
                //Either m is the min or min in the left half
                r=m;
            }
            else{
                l=m+1;
            }
        }

        return nums[r];
        
        
    }
}

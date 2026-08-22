class Solution {
    public int search(int[] nums, int target) {
        //I've seen this before 
        //No need to find the intersection point as the trick is that in each split one side will definitely be sorted 
        //I just need to find whether my element falls on that side of the array

        //Mandatory edge case check -> nums not empty
        if(nums == null || nums.length == 0){
            return -1;
        }

        //In the loop while left pointer is less than the right pointer keep on verifying the mid and shifting the limits
        int l=0;
        // int r=nums.length;
        int r=nums.length-1;


        // while(l<r){ We need to check equality also 
        while(l<=r){


            //Find the mid index
            int mid=l+(r-l)/2;

            //If mid element is what we want return the index
            if(nums[mid] == target){
                return mid;
            }

            //Check whether left half if fully sorted
            //Left can be less than or equal to mid
            if(nums[l]<=nums[mid]){

                //And my target lies within this range
                //Do I need to check the full range
                //Yes I need to check both the limits
                //Numbers less than the target can be in the right half also 
                // if(nums[l]<= target < nums[mid] ) {Not valid in java
                if(nums[l]<=target && target < nums[mid] ){

                    r=mid-1;
                }
                else{
                    l=mid+1;
                }
            }

            //Otherwise right is definitely sorted
            else{
                if(nums[r]>=target && target>nums[mid]){
                    l=mid+1;
                }
                else{
                    r=mid-1;
                }
            }

        }

        return -1;

    }
}

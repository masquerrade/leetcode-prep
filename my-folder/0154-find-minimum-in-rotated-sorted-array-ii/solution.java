//Initial analysis time: 43 min
class Solution {
    public int findMin(int[] nums) {

        //General notion to find minimum 
        //If right half if fully sorted then either mid is min or min in left half 
        //Else min lies definitely in the right half
        //Compare mid and right 
        //But duplicates are allowed
        //1,0,1,1,1
        //I thought of that I'll keep on dropping the right
        //I was right

        //Base case 
        if(nums==null || nums.length==0){

            throw new IllegalArgumentException("Array cannot be null or empty");
            
        }
        

        int l=0;
        int r=nums.length-1;


        //In this case the terminating condition is l==r, because there will always be a minimum 
        //So l<r

        while(l<r){
            int m=l+(r-l)/2;

            //If right is strictly increasing
            if(nums[m]<nums[r]){
                //In this case either the min is mid or to the left
                r=m;
            }
            else if(nums[m]>nums[r]){
                //This means m is in logically rotated left half and min will surely be to the right
                l=m+1;
            }
            else{
                //In this case we don't know to which half the min lies but we can safely drop the right as that value is already present in the mid
                r--;
            }
        }

        return nums[l];
    }
}

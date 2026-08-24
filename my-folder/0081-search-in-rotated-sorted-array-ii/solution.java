class Solution {
    public boolean search(int[] nums, int target) {

        //[2,5,6,0,0,1,2]
        //The only trick here is that the normal binary search for rotated sorted arrays will fail here when nums[l]==nums[m]==nums[r]

        if(nums==null || nums.length==0){
            return false;
        }

        int l=0;
        int r=nums.length-1;

        //Why l<=r or l<r
        //l == r is necessary If both l==r we need to check if this element is our target
        while(l<=r){
            int m=l+(r-l)/2;

            if(nums[m]==target){
                return true;
            }

            //I need to find the sorted half and check whether my target lies there
            //I can't find the sorted half shrink the array
            while(nums[l]==nums[m] && nums[m]==nums[r] && l<r){
                //Shrink from both sides
                r--;
                l++;
            }

            //Is the mid same if I shrink from both sides?
            //Yes
            if(nums[l]<=nums[m]){
                //Left half seems to be sorted
                if(target>=nums[l] && target<nums[m]){
                    r=m-1;
                }
                else{
                    l=m+1;
                }
            }
            else{
                //Right half seems to be sorted
                if(target>nums[m] && target<=nums[r]){
                    l=m+1;
                }
                else{
                    r=m-1;
                }

            }


        }

        return false;
        
    }
}

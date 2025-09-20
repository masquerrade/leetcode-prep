class Solution {
    

       public  boolean canJump(int [] nums){

          //Store the current max which you can reach and keep on checking your current position is not out of your reach
        int maxReach=0;

        for(int i=0;i<nums.length;i++){
            //System.out.println(Arrays.toString(nums));
            if(i>maxReach){
                return false;
            }
            //Here we don't have to add to max reach but to the current index
            maxReach=Math.max(maxReach,i+nums[i]);
            //System.out.println(maxReach);

            //If max reach already covers the end then return true
            if(maxReach>=nums.length-1){
                return true;
            }
            
        }

        return true;

    }

}

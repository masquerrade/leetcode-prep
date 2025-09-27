class Solution {
    public int jump(int[] nums) {

        //The trick is to go till the end of the current maximum jump keeping track of the maximum jummps till the last step of current jump.
        //Exerytime we make a jump increment count by 1
        //If current max jump is greater than the current length return adding 1 to the current jump
        //Start with 0 so that for each jump it is updated in the beginning
        int prevMaxJmp=0;
        int currMaxJmp=0;
        int minJmp=0;

        if(nums.length==1){
            return 0;
        }

        for(int i=0;i<nums.length;i++){
                                    //Keep track of till where you can go previously and go till there finding the max you can reach. After reaching there jump to the maximum point
            //prevMax=2
            //currmax =2,4,4
            currMaxJmp=Math.max(currMaxJmp,(i+nums[i]));
            //Check previous max jump because I need to increment the jmp first befeore quiting
            

            
            if(i==prevMaxJmp){
                prevMaxJmp=currMaxJmp;
                minJmp++; 

                //No need to check this evertime just when it is updated
                if(prevMaxJmp>=nums.length-1){
                break;
            }     
            }
        }

        return minJmp;
        
    }
}

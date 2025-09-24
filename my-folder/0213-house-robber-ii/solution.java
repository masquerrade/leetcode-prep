class Solution {
    public int rob(int[] nums) {
        //This way it is not possibble because if we reach the last element with maximum we don't know whether we took the first element or not
        // firstLastElm=nums[nums.length-1];

        // prevElm=nums[0];
        // prev2Elm=firstLastElm;

        //In this case we need to make cases

        // //Taking the first elemnt 

        // if(nums.length==1){
        //     return nums[0];
        // }
        //  if(nums.length==2){
        //     return Math.max(nums[0],nums[1]);
        // }
        // //In this case I'm not considering the last element
        // //this is very important we can eiter take 1 or 0
        // int prevElm=Math.max(nums[0],nums[1]);
        // int prev2Elm=nums[0];

        // for(int i=2;i<nums.length-1;i++){
        //     int tmp=prevElm;
        //     prevElm=Math.max(nums[i]+prev2Elm,prevElm);
        //     prev2Elm=tmp;
        // }

        // int case1Max=prevElm;

        // prev2Elm=nums[1];
        // prevElm=Math.max(nums[2],nums[1]);

        // for(int i=3;i<nums.length;i++){
        //     int tmp=prevElm;
        //     prevElm=Math.max(nums[i]+prev2Elm,prevElm);
        //     prev2Elm=tmp;
        // }

        // int case2Max=prevElm;

        // return Math.max(case1Max,case2Max);



        //Not taking the first element

        // In this case the easy solution is to divide the problem into two linear problem . One will exclude the first and another will exclude the last
        //First is not included

        if(nums.length==1){
            return nums[0];
        }
        int withoutFirst=robMaxLinear(1,nums.length,nums);
        int withoutLast=robMaxLinear(0,nums.length-1,nums);

        return Math.max(withoutFirst,withoutLast);

        
    }


    public int robMaxLinear(int start,int end,int [] nums){

        int prev=0;
        int prev2=0;

        for(int i=start;i<end;i++){
            int temp=prev;
            prev=Math.max(prev,prev2+nums[i]);
            prev2=temp;
        }

        return prev;

    }
}

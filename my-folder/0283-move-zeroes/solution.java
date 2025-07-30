class Solution {
    public void moveZeroes(int[] nums) {
        //Keep counter for current index
        //Swap non zero element with the correct index

        int c=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                //swap with counter and increase counter
                int temp=nums[i];
                nums[i]=nums[c];
                nums[c]=temp;
                c++;
            }
        }

        System.out.println(Arrays.toString(nums));
    }
}

class Solution {
    public int firstMissingPositive(int[] nums) {
        //How to use the array as the hash table 
        //If the actual value of the element 
        //Swapping is also necessary to move negative element to the end

        //Swapping part
        //Ending should be not in the direction of swapping as the swapped part will be swapped again
        //Do it so that you don't check the same number again
        int n=nums.length;
        int p=0;
        for(int i=0;i<n;i++){
            if(nums[i]>0&&nums[i]<=n){
                int temp=nums[i];
                nums[i]=nums[p];
                nums[p]=temp;
                p++;
            }
        }
        // System.out.println(Arrays.toString(nums));
        // System.out.println(p);

        for(int j=0;j<p;j++){
            //What if the same number is marked before in the case there is a duplicate number
            int curr=Math.abs(nums[j]);
            //I need to check if the current number is already marked before marking it again
            if(nums[curr-1]>0){
                 nums[curr-1]*=-1;
            }
           
        }
        // System.out.println(Arrays.toString(nums));
        int j=0;
        while(j<p){
            //When the number is positive then only we have to return 
            if(nums[j]>0){
                return j+1;
            }
            j++;
        }

        return j+1;

    }
}

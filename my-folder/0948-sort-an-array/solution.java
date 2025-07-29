class Solution {
    public int[] sortArray(int[] nums) {
        
        sort(nums,0,nums.length-1);

        return nums;

    }

    public void sort(int [] nums, int s, int e){

        if(s<e){
            int pivot= partition(nums,s,e);

        sort(nums, s,pivot-1);
        sort(nums, pivot+1,e);
        }

        


    }

    public int partition(int [] nums, int s, int e){
        Random rand= new Random();
        int randInt=rand.nextInt(e-s+1);
        swap(nums,s+randInt,e);
        int pivot=nums[e];
        //System.out.println(pivot);
        int i=s-1;

        for(int j=s;j<e;j++){
            if(nums[j]<pivot){
                i++;
                swap(nums,i,j);
            }
        }
        //System.out.println(Arrays.toString(nums));
        swap(nums,i+1,e);
        //System.out.println(Arrays.toString(nums));
        return i+1;


    }

    public void swap(int [] nums,int i,int j){
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;

    }

    
}

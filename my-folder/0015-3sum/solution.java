class Solution {
    //Two pointer approach
    public List<List<Integer>> threeSum(int[] nums) {
        //I need to sort the array and go from the beginning to the end so that the same triplet is not taken multiple times.
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));

        List<List<Integer>> lst=new ArrayList<>();

        for(int i=0;i<nums.length-2;i++)
        {

            
            int l=i+1;
            int r=nums.length-1;
         
            
            //System.out.println("l="+l+" r="+r);
            while(l<r){
                //System.out.println("i="+i+" l="+l+" r="+r);
                int sum=nums[i]+nums[l]+nums[r];
                //System.out.println(sum);
                if(sum==0){
                    //Making a list of 3 numbers
                    lst.add(Arrays.asList(nums[i],nums[l],nums[r]));

                    //This condition need to be reversed as we need to checck l<r before accewssing the element
                    while(l<r&&nums[l]==nums[l+1]){
                        l++;
                    }

                    while(l<r&&nums[r]==nums[r-1]){
                        r--;
                    }

                    //When sum is 0 then also l and r should update
                    l++;
                    r--;                   
                }
                //Write terminating condition of while loop before internal logic
                //Simply we can't move both the pointers . We have to move one pointer at a time
                //  l++;
                //  r--;
                else if(sum<0){
                    l++;
                }
                else{
                    r--;
                }
                //System.out.println(lst);
            }

            //Skipping the duplicates
            while(nums[i]==nums[i+1]&&i<nums.length-2){
                i++;
            }

            //[-1,0,1,2,-1,-4]
            
        }

        return lst;
    }
}

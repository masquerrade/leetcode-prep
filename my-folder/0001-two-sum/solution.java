class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map<Integer,Integer> mp = new HashMap<> ();
        // for(int i=0;i<nums.length;i++){
        //     mp.put(nums[i],i);
        // }
        //int [] nums1=new int[] {1,2,5,6,1};
        Map<Integer,Integer> mp =IntStream.range(0,nums.length).boxed().collect(Collectors.toMap(i->nums[i],i->i,(a,b)->b));


        for(int i=0;i<nums.length;i++){
            if(mp.containsKey(target-nums[i])){
                //If required value and current value are same it will give wrong result
                if(mp.get(target-nums[i])!=i){
                return new int[] {i,mp.get(target-nums[i])};
                }
            }
        }

        //Like this we can return empty array
        return new int[] {-1,-1};
    }

    
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hm=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            int comp=target-nums[i];
            if(hm.containsKey(comp)){
                //Way to return a new array
                return new int[] {i,hm.get(comp)};
            }
            //Check what will happenn if same value and complement
            hm.put(nums[i],i);
        }
        return new int[]{};
    }
}

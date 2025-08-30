class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        

        Deque<Integer> stk=new ArrayDeque<>();

        stk.push(nums2[0]);

        int [] arr=new int[nums1.length];

        Map<Integer,Integer> ng=new HashMap<>();

        for(int i=1;i<nums2.length;i++){
            int elm=nums2[i];

            //Need to check size first otherwise null pointer exception
            //Hashmap solution will work only when elements are distict otherwise elements will get updated later
            while(stk.size()>0 && stk.peek()<elm){
                ng.put(stk.pop(),elm);
            }
            stk.push(elm);
        }

        for(int i=0;i<nums1.length;i++){
            arr[i]=ng.getOrDefault(nums1[i],-1);
        }

        return arr;
    }
}

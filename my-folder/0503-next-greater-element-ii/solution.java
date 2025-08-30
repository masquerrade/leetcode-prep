class Solution {
    public int[] nextGreaterElements(int[] nums) {


        //Hashmap solution won't work in case duplicate elements are present
        // Map<Integer,Integer> ng=new HashMap<>();

        // for(int n:nums){
        //     while(!q.isEmpty() && q.peek()<n){
        //         ng.put(q.pop(),n);
        //     }
        //     q.push(n);
        // }
        // System.out.println(q);
        // System.out.println(ng);

        
        //This solution is wrong because we are losing some element in this case
        //[1,2,3,2,1]
        // if(!q.isEmpty()){
        //     int n=q.pollLast();
        //     while(!q.isEmpty() && q.peekLast()<n){
        //         ng.put(q.pollLast(),n);
        //     }
        // }

        
        //We have already removed some elements from the stack which could be the answer . The answer is persent in the remaining array 
        //We just have the elements for which we have not found the answer.

        //Iterating again through the stack to find the remaining answer

        // for(int n:nums){
        //     while(!q.isEmpty() && q.peek()<n){
        //         ng.put(q.pop(),n);
        //     }
        // }

       
        
        // int [] op=new int[nums.length];
        // for(int i=0;i<nums.length;i++){
        //     op[i]=ng.getOrDefault(nums[i],-1);
        // }


        Deque<Integer> q = new ArrayDeque<>();

        int [] arr= new int[nums.length];

        Arrays.fill(arr,-1);

        for(int i=0;i<nums.length;i++){
            while(!q.isEmpty() && nums[q.peek()]<nums[i]){
                arr[q.pop()]=nums[i];
            }
            q.push(i);
        }

        for(int i=0;i<nums.length;i++){
            while(!q.isEmpty() && nums[q.peek()]<nums[i]){
                arr[q.pop()]=nums[i];
            }
        }

        return arr;

        
    }
}

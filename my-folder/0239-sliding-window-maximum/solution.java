class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //Method 1

        // int s=0;
        // //int currMax=0;
        // //Double ended queue is needed to manage the addition and removal of numbers
        // Deque<Integer> dq=new ArrayDeque<Integer>();
        // for(int e=0;e<k;e++){
        //     //currMax=Math.max(nums[e],currMax);
        //     //Fill the first queue            
        //     while(!dq.isEmpty() && dq.peekLast()<nums[e] ){
        //         dq.pollLast();
        //     }
        //     dq.offer(nums[e]);
        // }

        // //int [] maxWin=new int[nums.length-k+1];
        // List<Integer> mw=new ArrayList<>();
        // mw.add(dq.peek());

        
        // for(int e=s+k;e<nums.length;e++){//
        //     //Remove the starting element if present and shift the start pointer one right
        //     if(dq.peek().equals(nums[s])){
        //         dq.pollFirst();
        //     }
        //     s++;
        //     //Remove all the element befor the ending element which are smaller than it and add the ending element in the end
        //      while(!dq.isEmpty() && dq.peekLast()<nums[e] ){
        //         dq.pollLast();
        //     }

        //     dq.offer(nums[e]);
        //     mw.add(dq.peek());

        // }

        // int [] arr=mw.stream().mapToInt(Integer::intValue).toArray();

        //Method 2
        // We should store index in the queue to avoid duplicate issues
        //No need to make seperate loop for creating the window .Initially the loop will fill the window
        //If we know the size we can use the array also
        int n=nums.length;
        int [] fa=new int [n-k+1];
        int ind=0;
        Deque<Integer> dq=new ArrayDeque<>();
        for(int e=0;e<n;e++){
            //If deque has any elements before the window remove it
            if(!dq.isEmpty() && dq.peek()<e-k+1){
                dq.pop();
            }

            //If deque tail has element lesser than the current element then remove it and add the current element

            while(!dq.isEmpty() && nums[dq.peekLast()]<nums[e]){
                dq.pollLast();
            }

            //Need to add index
            dq.offerLast(e);

            if(e-k+1>=0){
                fa[ind++]=nums[dq.peek()];
            }

        }



        return fa;
        
    }
}

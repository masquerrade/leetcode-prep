class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        //Itr thriugh array 
        //Add to queue 
        //First remove from q which doesn't belong to the window
        Deque<Integer> dq=new ArrayDeque<>();
        List<Integer> fa=new ArrayList<>();
        
        int l=0;
        for(int r=0;r<nums.length;r++){
            while(!dq.isEmpty() && dq.peek()<l){
                //rempove from the beg wheicj is not present inthe que
                dq.poll();
            }

            //Take elm in the r and remove from the last whatever less than r

            while(!dq.isEmpty()&&nums[dq.peekLast()]<nums[r]){
                dq.pollLast();
            }

            dq.offer(r);

            if(r-l+1==k){
                fa.add(nums[dq.peek()]);
                l++;             
            }


        }

        // Integer []a=fa.toArray(new Integer[0]); //Not valid


        return fa.stream().mapToInt(i->i).toArray();
        
    }
}

// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         //Method 1

//         // int s=0;
//         // //int currMax=0;
//         // //Double ended queue is needed to manage the addition and removal of numbers
//         // Deque<Integer> dq=new ArrayDeque<Integer>();
//         // for(int e=0;e<k;e++){
//         //     //currMax=Math.max(nums[e],currMax);
//         //     //Fill the first queue            
//         //     while(!dq.isEmpty() && dq.peekLast()<nums[e] ){
//         //         dq.pollLast();
//         //     }
//         //     dq.offer(nums[e]);
//         // }

//         // //int [] maxWin=new int[nums.length-k+1];
//         // List<Integer> mw=new ArrayList<>();
//         // mw.add(dq.peek());

        
//         // for(int e=s+k;e<nums.length;e++){//
//         //     //Remove the starting element if present and shift the start pointer one right
//         //     if(dq.peek().equals(nums[s])){
//         //         dq.pollFirst();
//         //     }
//         //     s++;
//         //     //Remove all the element befor the ending element which are smaller than it and add the ending element in the end
//         //      while(!dq.isEmpty() && dq.peekLast()<nums[e] ){
//         //         dq.pollLast();
//         //     }

//         //     dq.offer(nums[e]);
//         //     mw.add(dq.peek());

//         // }

//         // int [] arr=mw.stream().mapToInt(Integer::intValue).toArray();

//         //Method 2
//         // We should store index in the queue to avoid duplicate issues
//         //No need to make seperate loop for creating the window .Initially the loop will fill the window
//         //If we know the size we can use the array also
//         int n=nums.length;
//         int [] fa=new int [n-k+1];
//         int ind=0;
//         Deque<Integer> dq=new ArrayDeque<>();
//         for(int e=0;e<n;e++){
//             //If deque has any elements before the window remove it
//             if(!dq.isEmpty() && dq.peek()<e-k+1){
//                 dq.pop();
//             }

//             //If deque tail has element lesser than the current element then remove it and add the current element

//             while(!dq.isEmpty() && nums[dq.peekLast()]<nums[e]){
//                 dq.pollLast();
//             }

//             //Need to add index
//             dq.offerLast(e);

//             if(e-k+1>=0){
//                 fa[ind++]=nums[dq.peek()];
//             }

//         }



//         return fa;
        
//     }
// }

//Gemini solution
// import java.util.ArrayDeque;
// import java.util.Deque;

// class Solution {
//     /**
//      * Finds the maximum value in a sliding window of size k as it moves across the array.
//      *
//      * @param nums The input array of integers.
//      * @param k    The size of the sliding window.
//      * @return An array containing the maximum for each sliding window.
//      */
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         // Handle edge cases
//         if (nums == null || nums.length == 0 || k <= 0) {
//             return new int[0];
//         }

//         int n = nums.length;
//         int[] result = new int[n - k + 1];
//         int resultIndex = 0;

//         // The deque will store indices of elements.
//         // The elements at these indices will be in decreasing order.
//         Deque<Integer> dq = new ArrayDeque<>();

//         for (int i = 0; i < n; i++) {
//             // 1. Remove indices from the front that are now out of the window's bounds.
//             // The current window covers indices from [i - k + 1, i].
//             if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
//                 dq.pollFirst();
//             }

//             // 2. Maintain the decreasing order property.
//             // Remove indices from the back whose corresponding values are less than the current element.
//             // They can never be the maximum in any future window that includes the current element.
//             while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
//                 dq.pollLast();
//             }

//             // 3. Add the current element's index to the back of the deque.
//             dq.offerLast(i);

//             // 4. Once the window is fully formed (i.e., its size is k),
//             // the maximum for that window is at the front of the deque.
//             if (i >= k - 1) {
//                 result[resultIndex++] = nums[dq.peekFirst()];
//             }
//         }
//         return result;
//     }
// }

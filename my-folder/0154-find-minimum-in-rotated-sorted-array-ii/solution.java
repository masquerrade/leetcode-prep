// //Initial analysis time: 43 min
// //First attempt time 12:05
// class Solution {
//     public int findMin(int[] nums) {

//         //General notion to find minimum 
//         //If right half if fully sorted then either mid is min or min in left half 
//         //Else min lies definitely in the right half
//         //Compare mid and right 
//         //But duplicates are allowed
//         //1,0,1,1,1
//         //I thought of that I'll keep on dropping the right
//         //I was right

//         //Base case 
//         if(nums==null || nums.length==0){

//             throw new IllegalArgumentException("Array cannot be null or empty");
            
//         }
        

//         int l=0;
//         int r=nums.length-1;


//         //In this case the terminating condition is l==r, because there will always be a minimum 
//         //So l<r

//         while(l<r){
//             int m=l+(r-l)/2;

//             //If right is strictly increasing
//             if(nums[m]<nums[r]){
//                 //In this case either the min is mid or to the left
//                 r=m;
//             }
//             else if(nums[m]>nums[r]){
//                 //This means m is in logically rotated left half and min will surely be to the right
//                 l=m+1;
//             }
//             else{
//                 //In this case we don't know to which half the min lies but we can safely drop the right as that value is already present in the mid
//                 r--;
//             }
//         }

//         return nums[l];
//     }
// }

//Gemini solution
class Solution {
    public int findMin(int[] nums) {
        // L5 Polish: Always validate inputs even if LeetCode constraints say otherwise.
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        // Loop invariant: The minimum element is always in the range [left, right].
        while (left < right) {
            int mid = left + (right - left) / 2; // Prevents integer overflow
            
            if (nums[mid] > nums[right]) {
                // The minimum MUST be to the right of mid.
                // We know nums[mid] itself is not the minimum because nums[right] is smaller.
                left = mid + 1;
            } 
            else if (nums[mid] < nums[right]) {
                // The minimum is at mid or to the left of mid.
                // We cannot do right = mid - 1 because nums[mid] might be the minimum.
                right = mid;
            } 
            else {
                // nums[mid] == nums[right]
                // We cannot determine which half contains the minimum.
                // However, because nums[mid] == nums[right], we can safely discard nums[right]
                // without losing the minimum value (since nums[mid] is keeping a copy of it safe).
                right--;
            }
        }
        
        // When left == right, we have narrowed our search down to one element.
        return nums[left];
    }
}

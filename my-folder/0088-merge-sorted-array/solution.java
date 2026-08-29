
// //First attempt with dry run 5:46
// class Solution {
//     public void merge(int[] nums1, int m, int[] nums2, int n) {

//         //Overall logic is that I need to start from end as the cells are empty and doesn't need any shifting
//         //By the time I reach the elements of the first array I will have process that element already or that element is at it's correct place

        
//         if(n==0){
//             return;
//         }

//         // if(m==0){
//         //     nums1=nums2;
//         //     //Need to return after this
//         //     //Since java is pass by value the value is not updated here so this needs to be handled naturally
//         //     return;
//         // }

//         int startPos=m+n-1;
//         int first=m-1;
//         int sec=n-1;
//         //If nums2 is completely added then the full array is sorted

//         //I've to start comparing elements at the m and n and start inserting

//         /**
//             startPos    first   sec   m    n    nums1           nums2
//                 5         2      2    3    3    [1,2,3,0,0,0]   [2,5,6]

//          */
//         while(sec>=0){
//             /**
//                 sec     first   startPos    nums1           nums2       cond
//                 2       2            5      [1,2,3,0,0,0]   [2,5,6]     
//                  1      1           4       [1,2,3,0,0,6]               3<=6
//                 0                    3       [1,2,3,0,5,6]              3<=5
//                 -1                   2      [1,2,3,3,5,6]              3<=2
//                                      1        [1,2,2,3,5,6]             2<=2
//             */
//             if(first>=0){
//                 if(nums1[first]<=nums2[sec]){
//                     nums1[startPos--]=nums2[sec--];
//                 }
//                 else{
//                     nums1[startPos--]=nums1[first--];
//                 }
//             }
//             else{
//                 nums1[startPos--]=nums2[sec--];
//             }

//         }
        
        
//     }
// } 

//Gemini solution
class Solution {
    /**
     * Merges two sorted arrays in-place.
     * 
     * @param nums1 The target array with size m + n, containing m valid elements.
     * @param m     The number of valid elements in nums1.
     * @param nums2 The source array of size n.
     * @param n     The number of elements in nums2.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Defensive boundary checks for production safety
        if (nums1 == null || nums2 == null || n == 0) {
            return;
        }

        int p1 = m - 1;             // Pointer to the last valid element in nums1
        int p2 = n - 1;             // Pointer to the last element in nums2
        int insertPos = m + n - 1;  // Pointer to the last available slot in nums1
        
        // We only need to iterate while there are elements in nums2.
        // If p2 exhausts first, the remaining elements in p1 are already in place.
        while (p2 >= 0) {
            // Check if p1 is valid AND the nums1 element is strictly greater
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[insertPos--] = nums1[p1--];
            } else {
                nums1[insertPos--] = nums2[p2--];
            }
        }
    }
}

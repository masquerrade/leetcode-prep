class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //Overall logic is that I need to start from end as the cells are empty and doesn't need any shifting
        //By the time I reach the elements of the first array I will have process that element already or that element is at it's correct place

        
        if(n==0){
            return;
        }

        // if(m==0){
        //     nums1=nums2;
        //     //Need to return after this
        //     //Since java is pass by value the value is not updated here so this needs to be handled naturally
        //     return;
        // }

        int startPos=m+n-1;
        int first=m-1;
        int sec=n-1;
        //If nums2 is completely added then the full array is sorted

        //I've to start comparing elements at the m and n and start inserting

        /**
            startPos    first   sec   m    n    nums1           nums2
                5         2      2    3    3    [1,2,3,0,0,0]   [2,5,6]

         */
        while(sec>=0){
            /**
                sec     first   startPos    nums1           nums2       cond
                2       2            5      [1,2,3,0,0,0]   [2,5,6]     
                 1      1           4       [1,2,3,0,0,6]               3<=6
                0                    3       [1,2,3,0,5,6]              3<=5
                -1                   2      [1,2,3,3,5,6]              3<=2
                                     1        [1,2,2,3,5,6]             2<=2
            */
            if(first>=0){
                if(nums1[first]<=nums2[sec]){
                    nums1[startPos--]=nums2[sec--];
                }
                else{
                    nums1[startPos--]=nums1[first--];
                }
            }
            else{
                nums1[startPos--]=nums2[sec--];
            }

        }
        
        
    }
} 

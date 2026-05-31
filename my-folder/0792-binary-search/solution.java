//Iterative solution
class Solution {
    public int search(int[] nums, int target) {
        //What changes when I'm  switching from recursive to the iterative solution
        //In recurive solution base case handles the limit and in iterative solution we need to manage the limits manually

        if(nums==null && nums.length==0){
            return -1;
        }

       int start=0;
       int end=nums.length;

       //For each iteration I need to calculate the mid 
       //In the iteration my start and end will update so I need to exit the iteration based on the start and end 

       while(start<end){

            int mid=start+(end-start)/2;

            if(nums[mid]==target){
                return mid;
            }

            if(target<nums[mid]){
                end=mid;
            }
            else{
                start=mid+1;
            }
       }

       return -1;
    }
}


// //Recursive solution 
// class Solution {
//     public int search(int[] nums, int target) {

//         //I need to take care what happens if the nums is null
//         if(nums==null || nums.length==0){
//             return -1;
//         }

//         //Helper to take range and find the target

//         return searchHelper(0,nums.length,nums,target);

       
//     }

//     private int searchHelper(int start,int end,int[] nums,int target){

//         if(start>=end){
//             return -1;
//         }

//         //Calculate the mid 
//         // int mid=(end-start)/2;
//         int mid=start+(end-start)/2;


               
//         //Search either in the left half or right half
//         if(nums[mid]==target){
//             return mid;
//         }

//         if(target<nums[mid]){
//             return searchHelper(start,mid,nums,target);
//         }
//         else{
//             return searchHelper(mid+1,end,nums,target);
//         }        

        
//     }
// }

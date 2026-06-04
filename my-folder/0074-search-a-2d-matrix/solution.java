class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        //The basic logic is I'm going to search in the big flattened array
        if(matrix==null ||matrix.length==0 ||matrix[0].length==0){
            return false;
        }

        int m=matrix.length;
        int n=matrix[0].length;

        int len= m*n;

        //How do I do the mapping . I just do the mapping to get the value from my matrix
        //In the iterative way I need to update the range of my search in each iteration

        int l=0;
        int r=len-1;

        while(l<=r){
            int mid=l+(r-l)/2;

            //Compare left side 
            int mappedRow=mid/n;
            int mappedCol=mid%n;
            int midVal=matrix[mappedRow][mappedCol];

            if(target==midVal){
                return true;
            }

            if(target<midVal){
                r=mid-1;
            }
            else{
                l=mid+1;
            }

        }

        return false;
        
    }
}


// //Double Binary search
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {

//         //This feels little easy
//         //First I'll split the matrix row wise and then find the element in that row

//         //Find the middle row 
//         //If the element falls within this row , do BS on this row
//         //If it is less than the lower limit do BSM on the first half or else the 2nd half
//         if(matrix==null || matrix.length==0){
//             return false;
//         }
//         return binarySearchMatrix(matrix,0,matrix.length-1,target);
        
//     }

//     private boolean binarySearchMatrix(int[][] matrix,int start,int end, int target){
        
//         if(start>end){
//             return false;
//         }

//         //Find middle row
//         int midRow=start+(end-start)/2;
//         int m=matrix.length;
//         int n=matrix[midRow].length;

//         //If it is less than mid row search in the first half
//         if(target<matrix[midRow][0]){
//             return binarySearchMatrix(matrix,start,midRow-1,target);
//         }

//         //IF the element is in mid row binary seatch the mid row
//         if(target<=matrix[midRow][n-1]){
//             // return binarySearch(matrix[midRow],0,n,target); Boundary
//             return binarySearch(matrix[midRow],0,n-1,target);
//         }
                

//          //If element in the first half
//         // if(target<matrix[m][n]){  
//         // if(target<matrix[m-1][n-1]){  Don't miss the last element
//         if(target<=matrix[m-1][n-1]){
//              return binarySearchMatrix(matrix,midRow+1,end,target);
//         }

//         return false;
//     }

//     private boolean binarySearch(int[] row,int start,int end ,int target){
//         if(start>end){
//             return false;
//         }

//         int mid=start+(end-start)/2;

//         if(target<row[mid]){
//             return binarySearch(row,start,mid-1,target);
//         }

//         if(target==row[mid]){
//             return true;
//         }

//         if(target<=row[end]){
//             return binarySearch(row,mid+1,end,target);
//         }

//         return false;
//     }
   
// }

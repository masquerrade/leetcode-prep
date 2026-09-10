//Boolean solution 
class Solution {
    public int totalNQueens(int n) {
        
        //Here I'll track the number of rows, minor diagonal and major diagonal
        int row=0;
        boolean[] colVal=new boolean[n];
        boolean[] maxDiag=new boolean[2*n-1];
        boolean[] minDiag=new boolean[2*n-1];
        return backtrack(row, colVal, maxDiag, minDiag);
    }

    private int backtrack(int row,boolean[] colVal,boolean[] maxDiag,boolean[] minDiag){

        //If all rows are filled safely then this config is valid
        int n=colVal.length;
        int noOfBoards=0;

        if(row == n){
            return 1;
        }
        //This recursion will end when all the columns are explored
        //0 to 2n-2

        for(int col=0;col<n;col++){

            //Maj and Min diagonal value for the current column
            int maj= n-1+row-col;
            int min= row+col;

            if(colVal[col] || maxDiag[maj] || minDiag[min]){
                continue;
            }

            colVal[col]=true;
            maxDiag[maj]=true;
            minDiag[min]=true;

            //By placing the queen on this column whether it is valid
            noOfBoards+= backtrack(row+1, colVal, maxDiag, minDiag);

            //I forgot to unselect
            colVal[col]=false;
            maxDiag[maj]=false;
            minDiag[min]=false;
        }

        return noOfBoards;

    }
}


// //Bitmask array solution
// class Solution {
//     public int totalNQueens(int n) {
//         //What the fuck is this boolean solution

//         int row=0;
//         int colMask=0;
//         int allColsMask=(1<<n)-1;
//         int majDiag=0;
//         int minDiag=0;

//         return backtrack(allColsMask, colMask, majDiag, minDiag);
//         // return backtrack(row, allColsMask, colMask, majDiag, minDiag); row is not needed in this approach
//     }

//     // public int backtrack(int row,int allColsMask,int  colMask,int majDiag,int minDiag){
//     public int backtrack(int allColsMask,int  colMask,int majDiag,int minDiag){
//         //This checks whether all the columns have been occupied in the current array.
//         if(colMask==allColsMask){
//             return 1;
//         }

//         //For the current iteration how many valid solutions are present - 0
//         int solcount=0;

//         //Now I'll find how many solutions with the current combination

//         int availableSpots=~(colMask|majDiag|minDiag) & allColsMask;
//         //The recursion tree will end as there are no available spots in the current row
//         while(availableSpots!=0){

//             //Bit where I'll keep my queen lowest available bit
//             int bit= availableSpots & -availableSpots;

//             // solcount+=backtrack(row+1, allColsMask, colMask|bit , majDiag|bit<<1, minDiag|bit>>1);
//             // solcount+=backtrack(allColsMask, colMask|bit , majDiag|bit<<1, minDiag|bit>>1); Bracket is imp here
//             solcount+=backtrack(allColsMask, colMask|bit , (majDiag|bit)<<1, (minDiag|bit)>>1); 


//             //Clear the used bit for next iteration
//             availableSpots^=bit; 

//         }

//         return solcount;

//     }
// }

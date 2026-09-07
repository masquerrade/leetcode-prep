//Proper bitmasking approach
class Solution {
    public List<List<String>> solveNQueens(int n) {

        //How do I need to start 
        //Make colMask ,mainDiag, minDiag

        int colMask=0;
        int mainDiag=0;
        int minDiag=0;
        int row=0;

        //Bitmask representing 
        //Why do I need finalColMask
        List<List<String>> finalString=new ArrayList<>(n);

        //Mask for all the n bits
        //Move 1 to the nth bit and subtract 1
        int finalColMask=(1<<n)-1;
        int []colList=new int[n];


        backtrack(row, finalColMask , colMask, mainDiag, minDiag,colList, finalString);

        return finalString;
        
    }


    private void backtrack(int row,int finalColMask ,int colMask,int mainDiag,int minDiag,int[] colList, List<List<String>> finalString){


        //Condition for my loop
        //When all the cols are visited for the current row
        //How I'll move to the next iteration
        //1st I need to place the queen in the current position
        if(finalColMask == colMask){
            finalString.add(getBoard(colList));
            //I need to return here
            return;
        }

        //My while should only visit the column in the current row which is not available
        //What are the available slots for  the current row
        int availableSlots = ~(colMask | mainDiag | minDiag) & finalColMask;
        while(availableSlots!=0){

            //MSB
            int bit= availableSlots & - availableSlots;
            colList[row]=Integer.numberOfTrailingZeros(bit);
            
            //Update my masks 
            //Don't store the updated result just pass them onto recursion
            // colMask|=bit;
            //Min diagonal shift right 
            // minDiag= (minDiag | bit)>>1;

            //Max diagonal shift left
            // mainDiag= (mainDiag | bit)<<1;

            //Call the backtrack for the next row
            backtrack(row+1, finalColMask ,  colMask|bit,(mainDiag | bit)<<1, (minDiag | bit)>>1,colList, finalString);

            //Now that one slot is not available for the next iteration
            availableSlots^=bit;
            


        }

        
    }

    private List<String> getBoard(int[] colList){
        int n=colList.length;
        List<String> board= new ArrayList<>(n);
        for(int i=0; i<colList.length; i++){

            char[] row=new char[n];
            // row.fill('.');
            Arrays.fill(row,'.');
            // row[i]='Q'; This is hardcoded
            row[colList[i]]='Q';
            board.add(new String(row));

        }

        return board;

    }
}

// //Boolean array soln
// class Solution {
//     public List<List<String>> solveNQueens(int n) {
//         if(n==0 || n==2 || n==3){
//             return Collections.emptyList();
//         }

//         if(n==1){
//             return List.of(List.of("Q"));
//         }

//         //Need to track the attacked cells
//         //n rows
//         //r-c + n-1 0 to 2n-2 diagonals 
//         //r+c 0 to 2n-2 diagonals
//         //I need boolean arrays of size 2n-2+1 = 2n-1

//         boolean[] col=new boolean[n];
//         boolean[] majDiag=new boolean[2*n-1];
//         boolean[] minDiag=new boolean[2*n-1];

//         //Now I need to start backtracking starting from row 
//         List<List<String>> result=new ArrayList<>();

//         backtrack(0, col, majDiag, minDiag, new int[n], result);

//         return result;

//     }

//     private void backtrack(int row, boolean[] colm,boolean[] majDiag ,boolean[] minDiag, int[] colList, List<List<String>> result){
//         int n=colList.length;

//         if(row==colList.length){
//             result.add(getBoard(colList));
//             return;
//         }

//         //Iterate all columns for this row
//         for(int col=0;col<n;col++){

//             //Check if any of the column is attacked 
//             int currMaj=(row-col)+n-1;
//             int currMin=row+col;

//             if(colm[col] || majDiag[currMaj] || minDiag[currMin]){
//                 continue;
//             }

//             //Update the boolean arrays
//             colm[col]=true;
//             majDiag[currMaj]=true;
//             minDiag[currMin]=true;
//             colList[row]=col;


//             //Move to the next row with a valid column
//             backtrack(row+1, colm, majDiag, minDiag, colList, result);

//             //Revert the changes
//             colm[col]=false;
//             majDiag[currMaj]=false;
//             minDiag[currMin]=false;


//         }

//     }

//     private List<String> getBoard(int[] colList){
//         int n=colList.length;
//         List<String> board= new ArrayList<>(n);
//         for(int i=0; i<colList.length; i++){

//             char[] row=new char[n];
//             // row.fill('.');
//             Arrays.fill(row,'.');
//             // row[i]='Q'; This is hardcoded
//             row[colList[i]]='Q';
//             board.add(new String(row));

//         }

//         return board;

//     }

// }

// class Solution {
//     public List<List<String>> solveNQueens(int n) {
//         //I had a question how will I keep track of all the  cells which are being attacked by the queens I've already placed

//         //Clarifying the output format is very important 
//         //1 list of strings contain 1 board, each string represent one row
//         //2nd list represents second board configuration

//         if(n==0 || n==2 || n==3){
//             return Collections.emptyList();
//         }

//         if(n==1){
//             return List.of(List.of("Q"));
//         }
//         //We don't know how many solutions will be there
//         List<List<String>> finalBoard=new ArrayList<>();
//         //colBit
//         //majorDiagBit
//         //minotDiagBit
//         int colBit=0;
//         int majorDiagBit=0;
//         int minorDiagBit=0;
//         int row=0;
//         int[] queenCol=new int[n];
//         placeQueens(n,row,colBit,majorDiagBit,minorDiagBit,queenCol,finalBoard );

//         return finalBoard;

//     }

//     private void placeQueens(int n,int row,int colBit,int majorDiagBit,int minorDiagBit,int[] queenCol,List<List<String>> finalBoard ){
//         //Actual row number goes from 0 to n-1
//         if(row==n){
//             finalBoard.add(buildBoard(n,queenCol));
//             return;
//         }

//         //Itertate through all the columns in this row and try to place queen and make the permutation
//         for(int col=0;col<n;col++){
//             //Current col bit
//             int currColBit=1<<col;
//             //Same Maj and Minor bit will be visited multiple times for different rows to indicate collision
//             //Current major diagonal bit
//             int currMajBit=1<<((row-col)+(n-1));
//             //Current minor diagonal bit
//             int currMinBit=1<<(row+col);

//             //Check if this col or row has been previously used
//             if(
//                     (currColBit & colBit) !=0 
//                 ||  (currMajBit & majorDiagBit) !=0
//                 ||  (currMinBit & minorDiagBit) !=0 
//             ){
//                 continue;
//             }

//             //In this case column is valid so add to the queenCol
//             queenCol[row]=col;
//             //Update the new attack details
//             //I should not modify the original bits as it will be used for next possibility
//             // colBit = currColBit | colBit;
//             // minorDiagBit = minorDiagBit | currMinBit;
//             // majorDiagBit = majorDiagBit | currMajBit;

//             int nextcolBit = currColBit | colBit;
//             int nextminorDiagBit = minorDiagBit | currMinBit;
//             int nextmajorDiagBit = majorDiagBit | currMajBit;

//             //Now I've used this column in the row and registered it move on to the other row
//             placeQueens(n,row+1,nextcolBit,nextmajorDiagBit,nextminorDiagBit,queenCol,finalBoard );


//         }
//     }

//     private List<String> buildBoard(int n,int[] queenCol){
//         //Making string from character buffer array
//         //One complete board I need to make , with nelements 
//         List<String> board=new ArrayList<>(n);
//         //Build each of n elemeents

//         char[] rowBuffer =new char[n];

//         for(int i=0;i<n;i++){
//             Arrays.fill(rowBuffer,'.');
//             rowBuffer[queenCol[i]]='Q';
//             board.add(new String(rowBuffer));
//         }

//         return board;
//     }
// }

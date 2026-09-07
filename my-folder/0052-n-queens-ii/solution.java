//Boolean array solution
class Solution {
    public int totalNQueens(int n) {
        //What the fuck is this boolean solution

        int row=0;
        int colMask=0;
        int allColsMask=(1<<n)-1;
        int majDiag=0;
        int minDiag=0;

        return backtrack(allColsMask, colMask, majDiag, minDiag);
        // return backtrack(row, allColsMask, colMask, majDiag, minDiag); row is not needed in this approach
    }

    // public int backtrack(int row,int allColsMask,int  colMask,int majDiag,int minDiag){
    public int backtrack(int allColsMask,int  colMask,int majDiag,int minDiag){
        if(colMask==allColsMask){
            return 1;
        }

        //For the current iteration how many valid solutions are present - 0
        int solcount=0;

        //Now I'll find how many solutions with the current combination

        int availableSpots=~(colMask|majDiag|minDiag) & allColsMask;
        while(availableSpots!=0){

            //Bit where I'll keep my queen lowest available bit
            int bit= availableSpots & -availableSpots;

            // solcount+=backtrack(row+1, allColsMask, colMask|bit , majDiag|bit<<1, minDiag|bit>>1);
            // solcount+=backtrack(allColsMask, colMask|bit , majDiag|bit<<1, minDiag|bit>>1); Bracket is imp here
            solcount+=backtrack(allColsMask, colMask|bit , (majDiag|bit)<<1, (minDiag|bit)>>1); 


            //Clear the used bit for next iteration
            availableSpots^=bit; 

        }

        return solcount;

    }
}

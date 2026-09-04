class Solution {
    public List<List<String>> solveNQueens(int n) {
        //I had a question how will I keep track of all the  cells which are being attacked by the queens I've already placed

        //Clarifying the output format is very important 
        //1 list of strings contain 1 board, each string represent one row
        //2nd list represents second board configuration

        if(n==0 || n==2 || n==3){
            return Collections.emptyList();
        }

        if(n==1){
            return List.of(List.of("Q"));
        }
        //We don't know how many solutions will be there
        List<List<String>> finalBoard=new ArrayList<>();
        //colBit
        //majorDiagBit
        //minotDiagBit
        int colBit=0;
        int majorDiagBit=0;
        int minorDiagBit=0;
        int row=0;
        int[] queenCol=new int[n];
        placeQueens(n,row,colBit,majorDiagBit,minorDiagBit,queenCol,finalBoard );

        return finalBoard;

    }

    private void placeQueens(int n,int row,int colBit,int majorDiagBit,int minorDiagBit,int[] queenCol,List<List<String>> finalBoard ){
        //Actual row number goes from 0 to n-1
        if(row==n){
            finalBoard.add(buildBoard(n,queenCol));
            return;
        }

        //Itertate through all the columns in this row and try to place queen and make the permutation
        for(int col=0;col<n;col++){
            //Current col bit
            int currColBit=1<<col;
            //Same Maj and Minor bit will be visited multiple times for different rows to indicate collision
            //Current major diagonal bit
            int currMajBit=1<<((row-col)+(n-1));
            //Current minor diagonal bit
            int currMinBit=1<<(row+col);

            //Check if this col or row has been previously used
            if(
                    (currColBit & colBit) !=0 
                ||  (currMajBit & majorDiagBit) !=0
                ||  (currMinBit & minorDiagBit) !=0 
            ){
                continue;
            }

            //In this case column is valid so add to the queenCol
            queenCol[row]=col;
            //Update the new attack details
            //I should not modify the original bits as it will be used for next possibility
            // colBit = currColBit | colBit;
            // minorDiagBit = minorDiagBit | currMinBit;
            // majorDiagBit = majorDiagBit | currMajBit;

            int nextcolBit = currColBit | colBit;
            int nextminorDiagBit = minorDiagBit | currMinBit;
            int nextmajorDiagBit = majorDiagBit | currMajBit;

            //Now I've used this column in the row and registered it move on to the other row
            placeQueens(n,row+1,nextcolBit,nextmajorDiagBit,nextminorDiagBit,queenCol,finalBoard );


        }
    }

    private List<String> buildBoard(int n,int[] queenCol){
        //Making string from character buffer array
        //One complete board I need to make , with nelements 
        List<String> board=new ArrayList<>(n);
        //Build each of n elemeents

        char[] rowBuffer =new char[n];

        for(int i=0;i<n;i++){
            Arrays.fill(rowBuffer,'.');
            rowBuffer[queenCol[i]]='Q';
            board.add(new String(rowBuffer));
        }

        return board;
    }
}

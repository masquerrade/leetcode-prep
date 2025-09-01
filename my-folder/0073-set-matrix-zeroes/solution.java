class Solution {
    public void setZeroes(int[][] matrix) {
        boolean col1=false;
        boolean row1=false;
        int nr=matrix.length;
        int nc=matrix[0].length;

        //If first row has 0
        for(int i=0;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                row1=true;
                break;
            }
        }

        //If first column has 0
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0]==0){
                col1=true;
                break;
            }
        }

        //Mark row 1 and col 1 zeros based on the element
        //First row and column should not be considered for marking

        for(int i=1;i<nr;i++){
            for(int j=1;j<nc;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }

        //We should not mark the first column because I'm going to use that later
        for(int i=1;i<nc;i++){
            if(matrix[0][i]==0){
                for(int j=1;j<nr;j++){
                    matrix[j][i]=0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(matrix));
        for(int i=1;i<nr;i++){
            if(matrix[i][0]==0){
                for(int j=1;j<nc;j++){
                    matrix[i][j]=0;
                }
            }
        }

        //System.out.println(Arrays.deepToString(matrix));

        if(col1){

             for(int j=0;j<nr;j++){
                    matrix[j][0]=0;
                }
        }

        if(row1){

             for(int j=0;j<nc;j++){
                    matrix[0][j]=0;
                }
        }


        
    }
}

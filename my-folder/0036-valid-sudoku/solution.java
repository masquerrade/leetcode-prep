class Solution {
    public boolean isValidSudoku(char[][] board) {

        //Declare the array of Hashset

        Set<Character> [] c =new HashSet[9];
        Set<Character> [] r =new HashSet[9];
        Set<Character> [] b =new HashSet[9];

        //Initializing Hashset
        for(int i=0;i<9;i++){
            c[i]=new HashSet<>();
            r[i]=new HashSet<>();
            b[i]=new HashSet<>();
        }


        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char val=board[i][j];

                if(val=='.'){
                    continue;
                }

                if(!c[j].add(val)){
                    return false;
                }

                if(!r[i].add(val)){
                    return false;
                }
                //Formaula to fill all the elements which come in the current row. All the boxes in the current row will be filled
                int bi=(i/3)*3+j/3;

                if(!b[bi].add(val)){
                    return false;
                }


            }
        }
        return true;
        
    }
}

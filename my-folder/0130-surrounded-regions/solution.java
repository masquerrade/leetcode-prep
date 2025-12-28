//Practise 1
class Solution {
    public void solve(char[][] board) {
        int rows=board.length;
        int cols=board[0].length;
        //Mitake 1 : Boundary should be proper
        for(int i=0;i<cols;i++){
            if(board[0][i]=='O'){
                dfs(0,i,board);
            }

            if(board[rows-1][i]=='O'){
                dfs(rows-1,i,board);
            }
        }

        for(int i=0;i<rows;i++){
            if(board[i][0]=='O'){
                dfs(i,0,board);
            }

            if(board[i][cols-1]=='O'){
                dfs(i,cols-1,board);
            }
        }

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j]=='E'){
                    board[i][j]='O';
                }
                //Here else if is needed -Mistake 2
                else if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }

        
        
    }

    public void dfs(int row,int col,char[][] board){

        if(row>=0 && row<board.length && col>=0 && col<board[0].length && board[row][col]=='O'){
            board[row][col]='E';
            dfs(row+1,col,board);
            dfs(row,col+1,board);
            dfs(row-1,col,board);
            dfs(row,col-1,board);

        }
    }
}

// class Solution {
//     public void solve(char[][] board) {

//         int m=board.length;
//         int n=board[0].length;

//         //Can I make it correct in one go
//         //I have the adjacency list that is four sides
//         //I only have to visit the edges to start dfs

//         //Since it is m*n ,definitely I need 2 loop

//         //1st loop will go from 1 to m and mark all the 1st and mth row
//         //Here 0 and m constant
//         for(int i=0;i<n;i++){
//             //If (m,i) or (0,i) row is 0 then call dfs and change 
//             if(board[0][i]=='O'){
//                 //I need to call dfs in all 4 directions inside the dfs function
//                 // dfs(board,0,i);
//                 bfs(board,0,i);
//             }

//             if(board[m-1][i]=='O'){
//                 //I need to call dfs in all 4 directions inside the dfs function
//                 // dfs(board,m-1,i);
//                 bfs(board,m-1,i);
//             }
//         }

//         for(int i=0;i<m;i++){
//             //If (m,i) or (0,i) row is 0 then call dfs and change 
//             if(board[i][0]=='O'){
//                 //I need to call dfs in all 4 directions inside the dfs function
//                 // dfs(board,i,0);
//                 bfs(board,i,0);
//             }

//             if(board[i][n-1]=='O'){
//                 //I need to call dfs in all 4 directions inside the dfs function
//                 // dfs(board,i,n-1);
//                 bfs(board,i,n-1);
//             }
//         }

//         //System.out.println(Arrays.deepToString(board));


//         //Once I have marked all the safe zeros ,I will iterate through all the cells and mark safe cells as 0 and 0 as cross

//         for(int i=0;i<m;i++){
//             for(int j=0;j<n;j++){
//                 if(board[i][j]=='@'){
//                     board[i][j]='O';
//                 }
//                 //Need to use else if so that both the conditions are not executed
//                 else if(board[i][j]=='O'){
//                     board[i][j]='X';
//                 }
//             }
//         }
        
//     }

//     // public void dfs(char[][] board,int i,int j){
//     //     int m=board.length;
//     //     int n=board[0].length;

//     //     if(board[i][j]=='O'){
//     //         board[i][j]='@';
            
//     //         if(i-1>=0){
//     //             dfs(board,i-1,j);
//     //         }
//     //         if(i+1<m){
//     //             dfs(board,i+1,j);
//     //         }
//     //         if(j-1>=0){
//     //             dfs(board,i,j-1);
//     //         }
//     //         if(j+1<n){
//     //             dfs(board,i,j+1);
//     //         }
//     //     }
//     // }

//     public void bfs(char [][] board,int i,int j){
//         //I need to put all the adjacent element in the queue, better do it with a loop
//         //I need to look in the 4 sides
//         int m=board.length;
//         int n=board[0].length;

//         int[][] delta={{0,-1},{-1,0},{1,0},{0,1}};

//         Queue<int[]> q=new ArrayDeque<>();
//         //I have to mark the node as visited before adding to the queue

//         board[i][j]='@';
//         q.offer(new int[]{i,j});

//         while(!q.isEmpty()){
//             int[] ent=q.poll();
//             i=ent[0];
//             j=ent[1];

//             for(int[] del:delta){
//                 int r=del[0];
//                 int c=del[1];

//                 if(i+r>=0 && i+r<m && j+c>=0 && j+c<n && board[i+r][j+c]=='O'){
//                     board[i+r][j+c]='@';
//                     q.offer(new int[]{i+r,j+c});

//                 }

//             }
//         }

//     }
// }

//Gemini solution

// class Solution {
    
//     public void solve(char[][] board) {
//         if (board == null || board.length == 0 || board[0].length == 0) {
//             return;
//         }

//         int rows = board.length;
//         int cols = board[0].length;

//         // 1. Mark "safe" 'O's connected to the top and bottom borders
//         for (int j = 0; j < cols; j++) {
//             if (board[0][j] == 'O') {
//                 dfs(board, 0, j); // Top row
//             }
//             if (board[rows - 1][j] == 'O') {
//                 dfs(board, rows - 1, j); // Bottom row
//             }
//         }

//         // 2. Mark "safe" 'O's connected to the left and right borders
//         for (int i = 0; i < rows; i++) {
//             if (board[i][0] == 'O') {
//                 dfs(board, i, 0); // Left column
//             }
//             if (board[i][cols - 1] == 'O') {
//                 dfs(board, i, cols - 1); // Right column
//             }
//         }

//         // 3. Flip the board based on the marks
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (board[i][j] == 'O') {
//                     // This 'O' was not reached from a border, so it's surrounded.
//                     board[i][j] = 'X';
//                 } else if (board[i][j] == '$') {
//                     // This was a "safe" 'O' we marked. Restore it.
//                     board[i][j] = 'O';
//                 }
//             }
//         }
//     }

//     /**
//      * Helper DFS function to mark all 'O's connected to a border 'O'.
//      * We change them to '$' to mark them as "safe".
//      */
//     private void dfs(char[][] board, int r, int c) {
//         int rows = board.length;
//         int cols = board[0].length;

//         // Stop conditions (out of bounds or not an 'O')
//         if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') {
//             return;
//         }

//         // 1. Mark the current cell as "safe"
//         board[r][c] = '$';

//         // 2. Recurse in all 4 directions
//         dfs(board, r + 1, c); // Down
//         dfs(board, r - 1, c); // Up
//         dfs(board, r, c + 1); // Right
//         dfs(board, r, c - 1); // Left
//     }
// }

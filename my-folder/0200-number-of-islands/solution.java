// //DFS approach
// class Solution {
//     public int numIslands(char[][] grid) {

//         //I need to find all the disconnected graphs which are surrounded by water
//         //Call dfs when you find 1 and sink the complete land and 
//         //Here I don't need adjacency list because we can go to 4 sides
        

//         //Iterate through each element in the list and ,if it is one start dfs /bfs
//         int m=grid.length;
//         int n=grid[0].length;
//         int c=0;
//         for(int i=0;i<m;i++){
//             for(int j=0;j<n;j++){
//                 //Check if it is a land which has not been visited yet
//                 //Then start dfs from that and sink the land
//                 if(grid[i][j]=='1'){
//                     dfs(grid,i,j);
//                     c++;
//                 }
//             }
//         }

//         return c;
        
//     }
//     public void dfs(char [][]grid,int i,int j){
//         // System.out.println(Arrays.deepToString(grid));
//         int m=grid.length;
//         int n=grid[0].length;

//         //Check i and j are valid .If it is valid and it is 1 then make it 0 and call dfs for the adjacent nodes

//         if(i>=0&&i<m&&j<n&&j>=0&&grid[i][j]=='1'){
//             grid[i][j]='0';
//             dfs(grid,i-1,j);
//             dfs(grid,i+1,j);
//             dfs(grid,i,j-1);
//             dfs(grid,i,j+1);
//         }
//     }
// }

//BFS aproach
//Put all the valid nodes in the queue and start processing on the first come first serve basis 
//I found a valid node and then I will out that in the queue-> this one iteration -> Before going into the adjacent nodes I will process my current queue -> and then add all the adjacent valid nodeds in the queue
//I'll keep in popping all the nodes which are processed and mark the visited nodes as visited
//If it is directed graph do I need to maintain the visited , I can maintain an indegree 
//Here the graph is not directed so I need to maintain the visited array 
//I will maintain the integer visited array
//Here no need to maintain the visited array as I have maintained the visited array in the form of grid


// class Solution {
//     public int numIslands(char[][] grid) {

//         //Here I don't need any adjacent array or any visited array

//         //How to maintain queue of 2d array
//         int c=0;

//         Queue<int []> bfs=new ArrayDeque<>();

//         int n=grid.length;
//         int m=grid[0].length;
        
//         for(int i=0;i<n;i++){
//             for(int j=0;j<m;j++){

//                 // System.out.println(Arrays.deepToString(grid));
//                 // System.out.println("**");
//                 if(grid[i][j]=='1'){
//                     bfs.offer(new int[]{i,j});
//                     //Just after adding in queue mark as visited
//                      grid[i][j]='0';
//                     c++;
//                 }
//                 //I've added my first element to the queue and now I will go through all the adjacent element in the qraph and sink the land

//                 while(!bfs.isEmpty()){
//                     int [] ar=bfs.poll();
//                     //I need to change this to visited and add all the valid adjacent elements in the queue

//                     int a=ar[0];
//                     int b=ar[1];
                   

//                     //Keep in mind that lower bound should be greater = 0

//                     if(b-1>=0 && grid[a][b-1]=='1'){
//                         bfs.offer(new int[]{a,b-1});
//                         //Just after adding to queue mark as visited so that no other element can add it to the queue
//                         grid[a][b-1]='0';
//                     }

//                     if(a-1>=0 && grid[a-1][b]=='1'){
//                         bfs.offer(new int[]{a-1,b});
//                         grid[a-1][b]='0';
//                     }

//                     if(a+1<n && grid[a+1][b]=='1'){
//                         bfs.offer(new int[]{a+1,b});
//                         grid[a+1][b]='0';
//                     }

//                     if( b+1<m && grid[a][b+1]=='1'){
//                         bfs.offer(new int[]{a,b+1});
//                         grid[a][b+1]='0';
//                     }
//                 }
//             }
//         }

//         return c;

        
//     }
// }

// import java.util.LinkedList;
// import java.util.Queue;

// class Solution {
//     public int numIslands(char[][] grid) {
//         // Handle edge case of null or empty grid
//         if (grid == null || grid.length == 0) {
//             return 0;
//         }

//         int numRows = grid.length;
//         int numCols = grid[0].length;
//         int islandCount = 0;

//         // Iterate through each cell of the grid
//         for (int r = 0; r < numRows; r++) {
//             for (int c = 0; c < numCols; c++) {
//                 // If we find a piece of land ('1'), start a BFS to find all its parts
//                 if (grid[r][c] == '1') {
//                     islandCount++; // Found a new island
//                     bfs(grid, r, c); // Sink the entire island
//                 }
//             }
//         }

//         return islandCount;
//     }


//Gemini BFS solution
class Solution {
    public int numIslands(char[][] grid) {
        // Handle edge case of null or empty grid
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int islandCount = 0;

        // Iterate through each cell of the grid
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                // If we find a piece of land ('1'), start a BFS to find all its parts
                if (grid[r][c] == '1') {
                    islandCount++; // Found a new island
                    bfs(grid, r, c); // Sink the entire island
                }
            }
        }

        return islandCount;
    }
    /**
     * Helper method to perform BFS and sink the current island.
     * It uses a queue to find all connected '1's and changes them to '0's.
     * @param grid The grid of land and water.
     * @param startR The starting row index for the BFS.
     * @param startC The starting column index for the BFS.
     */
    private void bfs(char[][] grid, int startR, int startC) {
        Queue<int[]> queue = new LinkedList<>();
        
        // Add the starting cell to the queue
        queue.offer(new int[]{startR, startC});
        // Mark the starting cell as visited by sinking it immediately
        grid[startR][startC] = '0'; 

        int numRows = grid.length;
        int numCols = grid[0].length;
        
        // Define the 4 directions to explore (up, down, left, right)
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int r = currentCell[0];
            int c = currentCell[1];

            // Explore all 4 neighbors of the current cell
            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                // Check if the neighbor is valid (within bounds and is land)
                if (newR >= 0 && newR < numRows && newC >= 0 && newC < numCols && grid[newR][newC] == '1') {
                    // Add the valid neighbor to the queue to visit later
                    queue.offer(new int[]{newR, newC});
                    // Mark it as visited immediately to avoid adding it multiple times
                    grid[newR][newC] = '0';
                }
            }
        }
    }
}



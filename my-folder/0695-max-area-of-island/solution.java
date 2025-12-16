//Gemini solution
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int maxArea = 0; // Use a local variable for maxArea
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // Start a fresh count for this new island
                    int currentIslandArea = dfs(grid, i, j, rows, cols);
                    maxArea = Math.max(maxArea, currentIslandArea);
                }         
            }
        }
        return maxArea;
    }

    // Change return type to int to return the count of cells found
    private int dfs(int[][] grid, int i, int j, int rows, int cols) {
        // Base case: if out of bounds or water, return 0 area
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] != 1) {
            return 0;
        }

        // Mark visited
        grid[i][j] = 0;
        
        // Calculate area: 1 (myself) + sum of all neighbors
        return 1 + dfs(grid, i + 1, j, rows, cols)
                 + dfs(grid, i, j + 1, rows, cols)
                 + dfs(grid, i - 1, j, rows, cols)
                 + dfs(grid, i, j - 1, rows, cols);
    }
}


// class Solution {

//     private int maxSize=0;
//     private int currentSize=0;

//     public int maxAreaOfIsland(int[][] grid) {

//         //If grid is empty or null
//         if(grid==null || grid.length==0){
//             return 0;
//         }

//         //Iterate through the grid and count the size of each island
//         int rows=grid.length;
//         int cols=grid[0].length;

//         for(int i=0;i<rows;i++){
//             for(int j=0;j<cols;j++){
//                 if(grid[i][j]==1){
//                     currentSize=0;
//                     dfs(grid,rows,cols,i,j);
//                     System.out.println(maxSize);
//                 }         
//             }
//         }

//         return maxSize;
        
//     }

//     private void dfs(int[][] grid,int rows,int cols,int i,int j){
//         if(i<0||j<0||i>=rows||j>=cols||grid[i][j]!=1){
//             return;
//         }

//         currentSize++;
//         //Mark visited
//         grid[i][j]=0;
//         maxSize=Math.max(maxSize,currentSize);
        
//         //Here is a big trap that if at the same level two calls are made both will have the same size and size will increse in parrallel. So current size also needs to be kept as a global variable
//         dfs(grid,rows,cols,i+1,j);
//         dfs(grid,rows,cols,i,j+1);
//         dfs(grid,rows,cols,i-1,j);
//         dfs(grid,rows,cols,i,j-1);
//     }
// }

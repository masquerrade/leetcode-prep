class Solution {
    public int minPathSum(int[][] grid) {

        //Cost at any index depends on previous two indices 

        // First I will calculate the cost of first row and first column
        int m=grid.length;
        int n=grid[0].length;
        int [][]dp=new int[m][n];

        //Don't make bigger grid but a perfect grid
        dp[0][0]=grid[0][0];

        //Go through m and fill the dp
        for(int i=1;i<m;i++){
            dp[i][0]= dp[i-1][0]+ grid[i][0];
        }

        for(int i=1;i<n;i++){
            dp[0][i]= dp[0][i-1]+ grid[0][i];
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }  
        //System.out.println(Arrays.deepToString(dp));
        return dp[m-1][n-1];
    }
}


// class Solution {
//     /**
//      * Calculates the minimum path sum from the top-left to the bottom-right of a grid.
//      *
//      * @param grid An m x n grid of non-negative integers.
//      * @return The minimum sum of a path from top-left to bottom-right.
//      */
//     public int minPathSum(int[][] grid) {
//         // Get the dimensions of the grid
//         int m = grid.length;
//         int n = grid[0].length;

//         // 1. Initialize the first row. The path can only come from the left.
//         for (int j = 1; j < n; j++) {
//             grid[0][j] += grid[0][j - 1];
//         }

//         // 2. Initialize the first column. The path can only come from above.
//         for (int i = 1; i < m; i++) {
//             grid[i][0] += grid[i - 1][0];
//         }

//         // 3. Fill the rest of the grid using the DP relation.
//         // For each cell, add the minimum of the path sum from the top or the left.
//         for (int i = 1; i < m; i++) {
//             for (int j = 1; j < n; j++) {
//                 grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
//             }
//         }

//         // 4. The bottom-right cell now holds the minimum path sum.
//         return grid[m - 1][n - 1];
//     }
// }

//Gemini BFS solution

class Solution {
    // Directions: Right, Down, Left, Up
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case check
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Queues for BFS - one for each ocean
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        
        // Visited/Reachable matrices
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        // 1. Initialize Queues with Border Cells
        for (int i = 0; i < rows; i++) {
            // Pacific: Left border
            pacificQueue.offer(new int[]{i, 0});
            pacificReachable[i][0] = true;

            // Atlantic: Right border
            atlanticQueue.offer(new int[]{i, cols - 1});
            atlanticReachable[i][cols - 1] = true;
        }

        for (int j = 0; j < cols; j++) {
            // Pacific: Top border
            // Check if not already added (to avoid duplicates, though set handles it)
            if (!pacificReachable[0][j]) {
                pacificQueue.offer(new int[]{0, j});
                pacificReachable[0][j] = true;
            }

            // Atlantic: Bottom border
            if (!atlanticReachable[rows - 1][j]) {
                atlanticQueue.offer(new int[]{rows - 1, j});
                atlanticReachable[rows - 1][j] = true;
            }
        }

        // 2. Perform BFS for each ocean
        bfs(heights, pacificQueue, pacificReachable);
        bfs(heights, atlanticQueue, atlanticReachable);

        // 3. Find Intersection
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        int rows = heights.length;
        int cols = heights[0].length;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int[] dir : DIRECTIONS) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];

                // Bounds check
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // If already visited/reachable, skip
                if (reachable[newRow][newCol]) {
                    continue;
                }

                // Uphill Condition:
                // Water flows FROM neighbor TO current if neighbor >= current.
                // Since we are moving against the flow (Ocean -> Mountain), 
                // we only move to neighbors that are taller or equal.
                if (heights[newRow][newCol] < heights[r][c]) {
                    continue;
                }

                // Valid move: mark reachable and add to queue
                reachable[newRow][newCol] = true;
                queue.offer(new int[]{newRow, newCol});
            }
        }
    }
}

// //Gemini DFS solution

// class Solution {
//     // Directions array for clear neighbor navigation (Right, Down, Left, Up)
//     private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

//     public List<List<Integer>> pacificAtlantic(int[][] heights) {
//         List<List<Integer>> result = new ArrayList<>();
        
//         // 1. Edge Case Handling
//         if (heights == null || heights.length == 0 || heights[0].length == 0) {
//             return result;
//         }

//         int rows = heights.length;
//         int cols = heights[0].length;

//         // 2. State Matrices: Track reachability from each ocean
//         boolean[][] pacificReachable = new boolean[rows][cols];
//         boolean[][] atlanticReachable = new boolean[rows][cols];

//         // 3. Initiate DFS from Ocean Borders
//         for (int i = 0; i < rows; i++) {
//             // Left border (Pacific) and Right border (Atlantic)
//             dfs(heights, pacificReachable, i, 0);
//             dfs(heights, atlanticReachable, i, cols - 1);
//         }

//         for (int j = 0; j < cols; j++) {
//             // Top border (Pacific) and Bottom border (Atlantic)
//             dfs(heights, pacificReachable, 0, j);
//             dfs(heights, atlanticReachable, rows - 1, j);
//         }

//         // 4. Collect Result: Intersection of both reachable sets
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (pacificReachable[i][j] && atlanticReachable[i][j]) {
//                     result.add(Arrays.asList(i, j));
//                 }
//             }
//         }

//         return result;
//     }

//     // Helper method: Performs DFS moving "uphill"
//     private void dfs(int[][] heights, boolean[][] reachable, int r, int c) {
//         // Mark the current cell as reachable
//         reachable[r][c] = true;

//         for (int[] dir : DIRECTIONS) {
//             int newRow = r + dir[0];
//             int newCol = c + dir[1];

//             // Bounds check
//             if (newRow < 0 || newRow >= heights.length || 
//                 newCol < 0 || newCol >= heights[0].length) {
//                 continue;
//             }

//             // Optimization: If already visited/reachable, skip
//             if (reachable[newRow][newCol]) {
//                 continue;
//             }

//             // Core Logic: Water flows downhill, so we move UPHILL (>=)
//             // If the neighbor is LOWER than us, water cannot flow from there to us.
//             if (heights[newRow][newCol] < heights[r][c]) {
//                 continue;
//             }

//             dfs(heights, reachable, newRow, newCol);
//         }
//     }
// }


// class Solution {
//     public List<List<Integer>> pacificAtlantic(int[][] heights) {

//         //Here I don't know where to start
//         // I will start the pacific side and then the atlantic side and keep one seperate visited grid
//         int rows=heights.length;
//         int cols=heights[0].length;
//         char[][] visited=new char[rows][cols];


//         for(int i=0;i<rows;i++){
//              dfs(heights,visited,rows,cols,i,0,0,'P');
//         }

//         for(int i=0;i<cols;i++){
//              dfs(heights,visited,rows,cols,0,i,0,'P');
//         }

//         for(int i=0;i<rows;i++){
//              dfs(heights,visited,rows,cols,i,cols-1,0,'A');
//         }

//         for(int i=0;i<cols;i++){
//              dfs(heights,visited,rows,cols,rows-1,i,0,'A');
//         }



//         List<List<Integer>> result=new ArrayList<>();

//         for(int i=0;i<rows;i++){
//             for(int j=0;j<cols;j++){
//                 if(visited[i][j]=='B'){
//                     result.add(Arrays.asList(i,j));
//                 }
//             }

//         }
//             return result;

//     }

//     private void dfs(int[][] heights,char[][] visited,int rows,int cols,int i,int j, int prevHt, char preOc){

//             if(i<0||i>=rows||j<0||j>=cols||visited[i][j]==preOc||visited[i][j]=='B'||prevHt>heights[i][j]){
//                 return;
//             }

//             if(visited[i][j]=='A'||visited[i][j]=='P'){

//                 visited[i][j]='B';
//             }
//             else{
//                 visited[i][j]=preOc;
//             }
//             dfs(heights,visited,rows,cols,i+1,j,heights[i][j],visited[i][j]);
//             dfs(heights,visited,rows,cols,i,j+1,heights[i][j],visited[i][j]);
//             dfs(heights,visited,rows,cols,i,j-1,heights[i][j],visited[i][j]);
//             dfs(heights,visited,rows,cols,i-1,j,heights[i][j],visited[i][j]);
//     }
// }

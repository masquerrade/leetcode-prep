//Gemini BFS solution

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // 1. Sanity check (Good practice for Senior roles)
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int m = mat.length;
        int n = mat[0].length;
        
        // We can modify the input matrix in-place to save space, 
        // or create a result matrix. Here we modify 'mat' to track distances.
        // However, usually, mutating input is a discussion point. 
        // Let's use a separate result matrix implies cleaner separation of concerns.
        // For O(1) space constraints, we would use 'mat' directly.
        
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        
        // Max value acts as "unvisited"
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j}); // Add source to queue
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Directions array for cleaner neighbor checking (Up, Down, Left, Right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // 2. Multi-source BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check boundaries and if we found a shorter path
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    // If the neighbor has a larger distance than current + 1, update it
                    if (dist[nr][nc] > dist[r][c] + 1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return dist;
    }
}

// //Gemini DP solution

// class Solution {
//     public int[][] updateMatrix(int[][] mat) {
//         int m = mat.length;
//         int n = mat[0].length;
        
//         // Use a large value essentially as "Infinity". 
//         // DO NOT use Integer.MAX_VALUE directly, because (MAX_VALUE + 1) causes overflow.
//         // Since m, n <= 10^4, the max possible path is roughly 20,000. 
//         // 1,000,000 is safe and readable.
//         int INF = m * n; 
        
//         int[][] dist = new int[m][n];

//         // --- First Pass: Check Top and Left ---
//         // We move from (0,0) down to (m-1, n-1)
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 //No need to have a seperate pass to mark this
//                 if (mat[i][j] == 0) {
//                     dist[i][j] = 0;
//                 } else {
//                     // Default to INF
//                     //This is a very important trick which eliminates the use of multiple if and else
//                     int top = (i > 0) ? dist[i - 1][j] : INF;
//                     int left = (j > 0) ? dist[i][j - 1] : INF;
                    
//                     dist[i][j] = Math.min(top, left) + 1;
//                 }
//             }
//         }

//         // --- Second Pass: Check Bottom and Right ---
//         // We move from (m-1, n-1) up to (0,0)
//         for (int i = m - 1; i >= 0; i--) {
//             for (int j = n - 1; j >= 0; j--) {
//                 if (mat[i][j] == 0) {
//                     //Good observation
//                     continue; // Distance is already 0
//                 }
                
//                 int bottom = (i < m - 1) ? dist[i + 1][j] : INF;
//                 int right = (j < n - 1) ? dist[i][j + 1] : INF;
                
//                 // We take the min of the value from Pass 1 vs (Bottom/Right + 1)
//                 dist[i][j] = Math.min(dist[i][j], Math.min(bottom, right) + 1);
//             }
//         }
        
//         return dist;
//     }
// }


// //DP approach 
// //DP approach and BFS approach are both doing things in order so that you never repeat yourself
// class Solution {
//     public int[][] updateMatrix(int[][] mat) {
//         //I need to become used to thinking on the spot and defining and implemenating my idea
//         //Will I keep a distance array and then update the distance after two pass
//         //I need to return a grid so I will create a grid
//         int rows=mat.length;
//         int cols=mat[0].length;
//         int[][] dist=new int[rows][cols];
//         //Initially I need to initialise it with very large number
//         for(int i=0;i<rows;i++){
//             for(int j=0;j<cols;j++){
//                  if(mat[i][j]==0){
//                     dist[i][j]=0;
//                  }
//                  else{
//                      //This will cause integer overflow ,that is why we need to use 10000
//                      dist[i][j]=10000;
//                  }
//             }
//         }

//         System.out.println(Arrays.deepToString(dist));


//         //1st pass
//         for(int i=0;i<rows;i++){
//             for(int j=0;j<cols;j++){
//                 if(mat[i][j]==0 || (i==0 && j==0)){
//                     continue;
//                 }

//                 else if(i==0){
//                     //I need to check the left only
//                     //No  need to check this if condition
//                     // if(dist[i][j-1]+1<dist[i][j]){
//                     //     dist[i][j]=dist[i][j-1]+1;
//                     // }
//                     dist[i][j]=dist[i][j-1]+1;
//                 }

//                 else if(j==0){
//                     //I need to check the jtop only
//                     // if(dist[i-1][j]+1<dist[i][j]){
//                     //     dist[i][j]=dist[i-1][j]+1;
//                     // }
//                     dist[i][j]=dist[i-1][j]+1;
//                 }
//                 else{
//                     //If I put this outside else even after the if condition is executed this will be executed
//                     dist[i][j]=1+Math.min(dist[i][j-1],dist[i-1][j]);  

//                 }


//             }
//         }

//         //2nd pass
//         for(int i=rows-1;i>=0;i--){
//             for(int j=cols-1;j>=0;j--){
//                 if(mat[i][j]==0 || (i==rows-1 && j==cols-1)){
//                     continue;
//                 }

//                 else if(i==rows-1){
                
//                     dist[i][j]=Math.min(dist[i][j],dist[i][j+1]+1);
//                 }

//                 else if(j==cols-1){
//                     dist[i][j]=Math.min(dist[i][j],dist[i+1][j]+1);
//                 }

//                 else{
//                     dist[i][j]=Math.min(dist[i][j],1+Math.min(dist[i][j+1],dist[i+1][j]));
//                 }


//             }
//         }
//          return dist;
//     }
   
// }



// //Multisource bfs approach
// class Solution {
//     public int[][] updateMatrix(int[][] mat) {

//         //Create a heights grid and mark all the cells containing 0 as 0 and remaining as -1
//         int rows=mat.length;
//         int cols=mat[0].length;
//         int[][] heights=new int[rows][cols];

//         //I need a queue and I need to add all the zero cells in the queue 
        
//         //What if the matrix is empty
//         if(rows==0 || mat==null ){
//             return new int[1][1];
//         }


//         Queue<int[]> bfs=new ArrayDeque<>();

//         for(int i=0;i<rows;i++){
//             for(int j=0;j<cols;j++){
//                 if(mat[i][j]!=0){
//                     heights[i][j]=-1;
//                 }
//                 else{
//                     bfs.offer(new int[] {i,j});
//                 }
//             }
//         }

//         //Now I have my heights array ready 
//         //Also the queue is ready for the first level
//         int levelDist=0;

//         while(bfs.size()!=0){
//             levelDist++;

//             //How many times I need to pop
//             int currSize=bfs.size();

//             for(int i=0;i<currSize;i++){
//                 int[] ind=bfs.poll();

//                 int row=ind[0];
//                 int col=ind[1];

//                 //I need to see all the four directions 
//                 int[][] delta={{-1,0},{1,0},{0,-1},{0,1}};

//                 for(int[] del:delta){
//                     int newRow=del[0]+row;
//                     int newCol=del[1]+col;

//                     if(newRow>=0 && newRow<rows && newCol>=0 && newCol<cols && heights[newRow][newCol]==-1){
//                         heights[newRow][newCol]=levelDist;
//                         bfs.offer(new int[]{newRow,newCol}) ;
//                     }
//                 }
//             }


//         }

//         return heights;


        
//     }
// }



class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        // Queue stores {row, col, time}
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Step 1: Initialize
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    // Rotten oranges start at time 0
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        int maxTime = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Step 2: Flattened BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int time = current[2];

            // Update result to the latest time seen
            maxTime = Math.max(maxTime, time);

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    freshCount--;
                    // Push neighbor with incremented time
                    queue.offer(new int[]{nr, nc, time + 1});
                }
            }
        }

        // Step 3: Check distinct oranges
        return freshCount == 0 ? maxTime : -1;
    }
}

// //Gemini soln 2


// class Solution {
//     public int orangesRotting(int[][] grid) {
//         if (grid == null || grid.length == 0) return 0;

//         int rows = grid.length;
//         int cols = grid[0].length;
//         Queue<int[]> queue = new LinkedList<>();
//         int freshCount = 0;

//         // Step 1: Initialize Queue with all rotten oranges and count fresh ones
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (grid[i][j] == 2) {
//                     queue.offer(new int[]{i, j});
//                 } else if (grid[i][j] == 1) {
//                     freshCount++;
//                 }
//             }
//         }

//         // Edge Case: If there are no fresh oranges, no time is needed.
//         if (freshCount == 0) return 0;

//         int minutes = 0;
//         // Directions array for moving Up, Down, Left, Right
//         int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

//         // Step 2: Multi-source BFS
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             boolean infectedThisRound = false;

//             for (int i = 0; i < size; i++) {
//                 int[] point = queue.poll();
//                 int r = point[0];
//                 int c = point[1];

//                 for (int[] dir : dirs) {
//                     int nr = r + dir[0];
//                     int nc = c + dir[1];

//                     // Check boundaries and if the cell is a fresh orange
//                     if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
//                         // Rot the orange
//                         grid[nr][nc] = 2;
//                         freshCount--;
//                         queue.offer(new int[]{nr, nc});
//                         infectedThisRound = true;
//                     }
//                 }
//             }

//             // Only increment time if we successfully infected at least one orange
//             if (infectedThisRound) {
//                 minutes++;
//             }
//         }

//         // Step 3: If fresh oranges remain, it's impossible to rot them all
//         return freshCount == 0 ? minutes : -1;
//     }
// }

// class Solution {
//     public int orangesRotting(int[][] grid) {


//         // Explore all the rows and add all the rotten oranges to q
//         Queue<int []> bfs=new ArrayDeque<>();

//         int fO=0;
//         int m=grid.length;
//         int n=grid[0].length;

//         for(int i=0;i<m;i++){
//             for(int j=0;j<n;j++){
//                 //If the orange is rotten add it to the queue
//                 //If it is fresh increment fO
//                 if(grid[i][j]==2){
//                     bfs.offer(new int[]{i,j,0});
//                 }

//                 else if(grid[i][j]==1){
//                     fO++;
//                 }
//             }
//         }

//         //Now my queue is ready I need to traverse the queue 

//         //Max time taken to rot all the orange =0
//         int maxT=0;

//         //System.out.println("fO ="+fO);
//         while(!bfs.isEmpty() && fO!=0){

//             //In the last iteration fO=0 but q still has the last orange which I rot for which the time won't be counted

//             //Pop the first node and basically keep on popping all the nodes
//             //System.out.println("fO ="+fO);
//             int [] ora=bfs.poll();

//             int r=ora[0];
//             int c=ora[1];
//             int t=ora[2];
//             //System.out.println("Time ="+t);


//             //If the orange is rotten then only it is in the queue

//             //Traverse it's all four nodes

//             int [][] delta ={{0,1},{0,-1},{1,0},{-1,0}};

//             for(int []del : delta){
//                 int nr=r+del[0];
//                 int nc=c+del[1];
//                 //I need to check whether index is valid also 
//                 //Don't miss = for 0
//                 if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc]==1){
//                     //If a fresh orange is found rot it and add it to the queue
//                     fO--;
//                     grid[nr][nc]=2;
//                     maxT=t+1;
//                     bfs.offer(new int[]{nr,nc,t+1});
//                 }
//             }
//             //Once the for loop ends next layer have been added in the queue.
//             //Now next layer will be popped until no more fresh oranges are reachable.
//             //I need to keep track of the max time and return this max time

//             //System.out.println(Arrays.deepToString(grid));
            

//         }
//     //Only if fO==0 return maxT otherwise return -1
//     if(fO==0){
//         return maxT;
//     }
//     return -1;
//     }
// }


/*

//Performing bfs layer by layer
class Solution {
    public int orangesRotting(int[][] grid) {

        //This won't be solved by normal bfs
        //Layered bfs
        //One way is to track the time of each node while doing bfs
        //Other is to perform bfs layer by layer

        //Multisource bfs
        //I will add all the sources in the current level in the queue
        //Run a loop to pop all the nodes in the first level and mark all the adjacent nodes,the nodes which are marked should be added in the queue
        //That forms the second level of the bfs and go on doing that till there are no fresh oranges or the queue becomes empty

        //Base case is that there are no rotten or fresh oranges

        //Iterate through the grid and add the rotten oranges to the priority queue and count the number of fresh oranges

        int fO=0;
        
        //Queue what do I need to add
        //I will add index to the queue

        Queue<int []> bfs=new ArrayDeque<>();

        int m=grid.length;
        int n=grid[0].length;


        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    bfs.offer(new int[]{i,j});
                }
                else if(grid[i][j]==1){
                    fO++;
                }

            }
        }


        //Now I have all the rotten oranges in the queue and number of all the fresh oranges
        int nM=0;

        while(!bfs.isEmpty() && fO!=0){

            nM++;

            int k=bfs.size();

            //I need to poll all these k elements

            for(int i=0;i<k;i++){
                int [] ind=bfs.poll();

                //I got the index of rotten orange ,Now I need to rot all the oranges in the four directions
                int [][]adj={{0,1},{1,0},{0,-1},{-1,0}};

                for(int [] del:adj){
                    //If it falls under the valid cond,it should be fresh and it should be within the boundary
                    int r=ind[0]+del[0];
                    int c=ind[1]+del[1];

                    if(r>=0 && r<m && c>=0 && c<n &&grid[r][c]==1){
                        grid[r][c]=2;
                        bfs.offer(new int[]{r,c});
                        fO--;
                    }
                }
            }
        }

        if(fO!=0){
            return -1;
        }

        return nM;
        




        
    }
}

*/

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

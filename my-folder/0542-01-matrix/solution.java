class Solution {
    public int[][] updateMatrix(int[][] mat) {

        //Create a heights grid and mark all the cells containing 0 as 0 and remaining as -1
        int rows=mat.length;
        int cols=mat[0].length;
        int[][] heights=new int[rows][cols];

        //I need a queue and I need to add all the zero cells in the queue 
        
        //What if the matrix is empty
        if(rows==0 || mat==null ){
            return new int[1][1];
        }


        Queue<int[]> bfs=new ArrayDeque<>();

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(mat[i][j]!=0){
                    heights[i][j]=-1;
                }
                else{
                    bfs.offer(new int[] {i,j});
                }
            }
        }

        //Now I have my heights array ready 
        //Also the queue is ready for the first level
        int levelDist=0;

        while(bfs.size()!=0){
            levelDist++;

            //How many times I need to pop
            int currSize=bfs.size();

            for(int i=0;i<currSize;i++){
                int[] ind=bfs.poll();

                int row=ind[0];
                int col=ind[1];

                //I need to see all the four directions 
                int[][] delta={{-1,0},{1,0},{0,-1},{0,1}};

                for(int[] del:delta){
                    int newRow=del[0]+row;
                    int newCol=del[1]+col;

                    if(newRow>=0 && newRow<rows && newCol>=0 && newCol<cols && heights[newRow][newCol]==-1){
                        heights[newRow][newCol]=levelDist;
                        bfs.offer(new int[]{newRow,newCol}) ;
                    }
                }
            }


        }

        return heights;


        
    }
}

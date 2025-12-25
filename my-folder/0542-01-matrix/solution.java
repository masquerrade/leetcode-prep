//DP approach 
//DP approach and BFS approach are both doing things in order so that you never repeat yourself
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        //I need to become used to thinking on the spot and defining and implemenating my idea
        //Will I keep a distance array and then update the distance after two pass
        //I need to return a grid so I will create a grid
        int rows=mat.length;
        int cols=mat[0].length;
        int[][] dist=new int[rows][cols];
        //Initially I need to initialise it with very large number
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                 if(mat[i][j]==0){
                    dist[i][j]=0;
                 }
                 else{
                     //This will cause integer overflow ,that is why we need to use 10000
                     dist[i][j]=10000;
                 }
            }
        }

        System.out.println(Arrays.deepToString(dist));


        //1st pass
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(mat[i][j]==0 || (i==0 && j==0)){
                    continue;
                }

                else if(i==0){
                    //I need to check the left only
                    //No  need to check this if condition
                    // if(dist[i][j-1]+1<dist[i][j]){
                    //     dist[i][j]=dist[i][j-1]+1;
                    // }
                    dist[i][j]=dist[i][j-1]+1;
                }

                else if(j==0){
                    //I need to check the jtop only
                    // if(dist[i-1][j]+1<dist[i][j]){
                    //     dist[i][j]=dist[i-1][j]+1;
                    // }
                    dist[i][j]=dist[i-1][j]+1;
                }
                else{
                    //If I put this outside else even after the if condition is executed this will be executed
                    dist[i][j]=1+Math.min(dist[i][j-1],dist[i-1][j]);  

                }


            }
        }

        //2nd pass
        for(int i=rows-1;i>=0;i--){
            for(int j=cols-1;j>=0;j--){
                if(mat[i][j]==0 || (i==rows-1 && j==cols-1)){
                    continue;
                }

                else if(i==rows-1){
                
                    dist[i][j]=Math.min(dist[i][j],dist[i][j+1]+1);
                }

                else if(j==cols-1){
                    dist[i][j]=Math.min(dist[i][j],dist[i+1][j]+1);
                }

                else{
                    dist[i][j]=Math.min(dist[i][j],1+Math.min(dist[i][j+1],dist[i+1][j]));
                }


            }
        }
         return dist;
    }
   
}



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

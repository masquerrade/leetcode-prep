class Solution {
    public int numEnclaves(int[][] grid) {
        //Sink all the boundary cells

        int m=grid.length;
        int n=grid[0].length;

        //Sink with dfs
        for(int i=0;i<n;i++){
            if(grid[0][i]==1){
                dfs(grid,0,i);
            }
            if(grid[m-1][i]==1){
                dfs(grid,m-1,i);
            }
        }

        for(int i=0;i<m;i++){
            if(grid[i][0]==1){
                dfs(grid,i,0);
            }
            if(grid[i][n-1]==1){
                dfs(grid,i,n-1);
            }
        }

        int c=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){

                    c++;
                }
            }
        }

        return c;
    }


    public void dfs(int [][] grid,int i,int j){

        int m=grid.length;
        int n=grid[0].length;
        
        //I need to check the original row and column whether they are valid or not ,no need to worry whether the call will be valid or not
        if(i<0||i>=m||j<0||j>=n||grid[i][j]!=1){
            return;
        }

        grid[i][j]=0;

        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}

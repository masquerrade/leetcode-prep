//DFS approach
class Solution {
    public int numIslands(char[][] grid) {

        //I need to find all the disconnected graphs which are surrounded by water
        //Call dfs when you find 1 and sink the complete land and 
        //Here I don't need adjacency list because we can go to 4 sides
        

        //Iterate through each element in the list and ,if it is one start dfs /bfs
        int m=grid.length;
        int n=grid[0].length;
        int c=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                //Check if it is a land which has not been visited yet
                //Then start dfs from that and sink the land
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    c++;
                }
            }
        }

        return c;
        
    }
    public void dfs(char [][]grid,int i,int j){
        // System.out.println(Arrays.deepToString(grid));
        int m=grid.length;
        int n=grid[0].length;

        //Check i and j are valid .If it is valid and it is 1 then make it 0 and call dfs for the adjacent nodes

        if(i>=0&&i<m&&j<n&&j>=0&&grid[i][j]=='1'){
            grid[i][j]='0';
            dfs(grid,i-1,j);
            dfs(grid,i+1,j);
            dfs(grid,i,j-1);
            dfs(grid,i,j+1);
        }
    }
}

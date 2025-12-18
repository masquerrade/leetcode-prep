class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        //Here I don't know where to start
        // I will start the pacific side and then the atlantic side and keep one seperate visited grid
        int rows=heights.length;
        int cols=heights[0].length;
        char[][] visited=new char[rows][cols];


        for(int i=0;i<rows;i++){
             dfs(heights,visited,rows,cols,i,0,0,'P');
        }

        for(int i=0;i<cols;i++){
             dfs(heights,visited,rows,cols,0,i,0,'P');
        }

        for(int i=0;i<rows;i++){
             dfs(heights,visited,rows,cols,i,cols-1,0,'A');
        }

        for(int i=0;i<cols;i++){
             dfs(heights,visited,rows,cols,rows-1,i,0,'A');
        }



        List<List<Integer>> result=new ArrayList<>();

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(visited[i][j]=='B'){
                    result.add(Arrays.asList(i,j));
                }
            }

        }
            return result;

    }

    private void dfs(int[][] heights,char[][] visited,int rows,int cols,int i,int j, int prevHt, char preOc){

            if(i<0||i>=rows||j<0||j>=cols||visited[i][j]==preOc||visited[i][j]=='B'||prevHt>heights[i][j]){
                return;
            }

            if(visited[i][j]=='A'||visited[i][j]=='P'){

                visited[i][j]='B';
            }
            else{
                visited[i][j]=preOc;
            }
            dfs(heights,visited,rows,cols,i+1,j,heights[i][j],visited[i][j]);
            dfs(heights,visited,rows,cols,i,j+1,heights[i][j],visited[i][j]);
            dfs(heights,visited,rows,cols,i,j-1,heights[i][j],visited[i][j]);
            dfs(heights,visited,rows,cols,i-1,j,heights[i][j],visited[i][j]);
    }
}

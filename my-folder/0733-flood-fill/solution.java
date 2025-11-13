class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {


        //Directly call dfs for the current node 
        int m=image.length;
        int n=image[0].length;



        int inC=image[sr][sc];
        int [][]visited=new int[m][n];

        dfs(image,sr,sc,inC,color,visited);
        


        return image;
        
    }

    public void dfs(int[][] image, int sr, int sc, int inC,int fnC,int [][]visited) {

        // System.out.println(sr);

        int m=image.length;
        int n=image[0].length;

        //Directly call dfs for the current node 

        //Here nowhere I'm marking as visited that is why this issue

        if(sr<0||sr>=m||sc<0||sc>=n||image[sr][sc]!=inC ||visited[sr][sc]==1){
            // System.out.println("Base condition called");
            return;
        }

        image[sr][sc]=fnC;
        visited[sr][sc]=1;

        dfs(image,sr-1,sc,inC,fnC,visited);
        dfs(image,sr+1,sc,inC,fnC,visited);
        dfs(image,sr,sc-1,inC,fnC,visited);
        dfs(image,sr,sc+1,inC,fnC,visited);              

        
    }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        
        //What I can think initially is that it will be basic bfs and dfs
        //Basically I need to count the number of disconnected components
        //Here layer by layer traversal is not needed

        //So I will use dfs

        //I already have the adjacency list and I just need to run the dfs. 
        //Undirected graph

        //Undirected graph can also have cycle

        //Are we going to have visited , visiting and not visited here also .Is it in any way different from directed graph

        //Or are we going to have a visited array. Here we need only the visited array as we don't actually care about cycles .

        //For tracking the visited it is better to have boolean array or set 

        int n=isConnected.length;
        boolean[] isVisited=new boolean[n];
        int noOfProvince=0;

        //I need to start dfs from each of the n cities if the city is not visited in the previous dfs cycle

        for(int i=0;i<n;i++){
            if(!isVisited[i]){
                dfs(i,isVisited,isConnected);
                noOfProvince++;
            }
        }

        return noOfProvince;
    }

    void dfs(int node,boolean[] isVisited,int[][] isConnected){
        isVisited[node]=true;

        int n=isVisited.length;

        for(int i=0;i<n;i++){
            if(!isVisited[i] && isConnected[node][i]==1){
                dfs(i,isVisited,isConnected);
            }
        }

    }
}

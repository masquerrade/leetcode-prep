//Union find method
//First of all I need to make an array to track the parent.
//Why I'm using union find. I'll make independent trees for each parent
//If two trees have same parent will connect it.
//At the end how many seperate parents we have will be the no of provinces.

class Solution {
    int noOfProvince;
    int[] parent;
    int[] rank;
    public int findCircleNum(int[][] isConnected) {

        // First of all I'll make a parent array 
        //Then I need to make a rank array
        //What my find function will do
        //I need to make a find function which will find the parent of current node and update the parent array
        int n=isConnected.length;
        noOfProvince=n;
        parent= new int[n];
        rank= new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=0;
        }

        //I need to iterate through the isConnected and make union of nodes which are connected
        //i-i will always be connected and I need to check only in one direction i->j
        //0 ,0
        //0->0  ,1
        //0->1  ,2
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(isConnected[i][j]==1){
                    union(i,j);
                }
            }
        }
        return noOfProvince;       
    }

    //What will find function do. It will not bother if the current node is root or the current node is not root 
    //What is the main use of the rank - To measure the height of the current tree. It will update only when we union two nodes of same height
    public int find(int node){
        //What I'm going to do is find whether current node is not root
        //If it is not root , then compress it till all the node till the root has the same parent as the root
        if(parent[node]!=node){
            parent[node]=find(parent[node]);         
        }
        return parent[node];
    }

    //To find the union I'll check the root and thee rank . Attach the root with lower rank to the root with higher rank

    public void union(int city1, int city2){

        //Find the parent of each city
        //We don't know the actual parent of the 
        //For checkin that they belong to the same province I need to know their parent . So I need to upadate the number of province here
        int parent1=find(city1);
        int parent2=find(city2);

        if(parent1!=parent2){
            if(rank[parent1]>rank[parent2]){
                parent[parent2]=parent1;
                //Height remains same so no need to increase the rank
                //rank[parent1]++;
            }
            else if(rank[parent1]<rank[parent2]){
                parent[parent1]=parent2;
                //Don't need to increase the height 
                //rank[parent2]++;
            }
            else{
                parent[parent1]=parent2;
                //Only when rank of both same then only I need to increase the rank
                rank[parent2]++;

            }

            //Roots were not same and I made a union of those . I need to decrease the number of provinces
            noOfProvince--;
        }

        //If two province does not have the same parent and we connect them we should decrease the number of province
     
    }
}

// class Solution {
//     public
//     int findCircleNum(int[][] isConnected) {
        
//         //What I can think initially is that it will be basic bfs and dfs
//         //Basically I need to count the number of disconnected components
//         //Here layer by layer traversal is not needed

//         //So I will use dfs

//         //I already have the adjacency list and I just need to run the dfs. 
//         //Undirected graph

//         //Undirected graph can also have cycle

//         //Are we going to have visited , visiting and not visited here also .Is it in any way different from directed graph

//         //Or are we going to have a visited array. Here we need only the visited array as we don't actually care about cycles .

//         //For tracking the visited it is better to have boolean array or set 

//         int n=isConnected.length;
//         boolean[] isVisited=new boolean[n];
//         int noOfProvince=0;

//         //I need to start dfs from each of the n cities if the city is not visited in the previous dfs cycle

//         for(int i=0;i<n;i++){
//             if(!isVisited[i]){
//                 dfs(i,isVisited,isConnected);
//                 noOfProvince++;
//             }
//         }

//         return noOfProvince;
//     }

//     void dfs(int node,boolean[] isVisited,int[][] isConnected){
//         isVisited[node]=true;

//         int n=isVisited.length;

//         for(int i=0;i<n;i++){
//             if(!isVisited[i] && isConnected[node][i]==1){
//                 dfs(i,isVisited,isConnected);
//             }
//         }

//     }
// }

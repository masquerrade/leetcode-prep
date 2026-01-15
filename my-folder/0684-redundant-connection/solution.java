//Gemini solution

class Solution {
    /**
     * Finds the redundant connection in a graph.
     * * Time Complexity: O(N * alpha(N)) - effectively O(N)
     * Space Complexity: O(N) for the parent and rank arrays
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        
        // Initialize DSU structure
        // Nodes are 1-indexed based on problem description (1 to N)
        DSU dsu = new DSU(n + 1);
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // Try to union the two nodes. 
            // If union returns false, they were already connected.
            if (!dsu.union(u, v)) {
                return edge;
            }
        }
        
        return new int[0]; // Should not happen given problem constraints
    }
    
    // Inner class for Disjoint Set Union logic
    // This demonstrates OO design principles to the interviewer
    static class DSU {
        private int[] parent;
        private int[] rank;
        
        public DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // Each node is its own parent initially
                rank[i] = 1;   // Initial rank/size is 1
            }
        }
        
        // Find with Path Compression
        // Recursively points nodes directly to the root to flatten the tree
        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]); 
            }
            return parent[node];
        }
        
        // Union by Rank
        // Returns true if merge was successful, false if already connected
        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            
            if (rootU == rootV) {
                return false; // Cycle detected
            }
            
            // Attach the shorter tree to the taller tree to keep depth low
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootV] > rank[rootU]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }
}





// class Solution {
//     public int[] findRedundantConnection(int[][] edges) {

//         //If there is no cycle no need to remove any edge 
//         //If any cycle is there , any edge in the cycle can be removed so that the graph remains connected.
//         //And I need to return the last edge 
//         //So I need to remove the last edge when the cycle is found

//         //I need to just add the the edge to the array

//         //What I'm gonna do is that as soon as the edge is coming ,I need to check how much of the graph has been constructed till now. In the graph which has been constructed till now ,does a path already exist between the given edges . If is esists then the current edge will create a cycle. If the path doesn't exist then add the path to the graph.
//         //We're actually not constructing the graph we are just connecting together the connected components
//         //Disjoint set data structure is to be used in this case.
//         //Disjoint set union contains two member variables rank and parent to track rank and parent of each node

//         //As soon as I get the edge I will try to do the union between them .

//         //Union operation will find the parents of the two edges .If they have the same parent then these two edges are already connected somehow in the graph.

//         //So return the current edges.

//         //When doing union update the rank   

//         Dsu dsu=new Dsu(edges.length);

//         //I made my dsu data structure 
//         //I have my 1st pair of edges now
//         for(int []edge:edges){

//             if(dsu.union(edge[0],edge[1])){
//                 return edge;          
//             }

//         }

//         return new int[]{0};
        
//     }

//     class Dsu{
//         int [] parent;
//         int [] rank;
//         Dsu(int n){
//             //Make a size 1 larger
//             parent=new int[n+1];
//             rank=new int[n+1];

//             for(int i=0;i<=n;i++){
//                 parent[i]=i;
//                 rank[i]=1;
//             }
//         }

//         //What am I going to do in the find operation
//         int find(int node){
//             //What is the  character of the root that its parent is itself
//             if(parent[node]==node){
//                 return node;
//             }

//             return parent[node]=find(parent[node]);
//         }

//         //Now I need to the come to the union

//         //What will a union return . I think it won't return anything .It will just update the parents and rank
//         boolean union(int node1,int node2){

//             //Union will return true or false whether cycle exists and union not possible or whether cycle doesn't exist and the union is possible
//             // System.out.println("Nodes ="+node1+" , "+node2);
//             // System.out.println(Arrays.toString(parent));
//             // System.out.println(Arrays.toString(rank));

//             //I need to find parent seperately as I need to do union of parent 

//             int p1=find(node1);
//             int p2=find(node2);

//             if(p1==p2){
//                 return true;
//             }

//             //In this case I need to return false as well as I need to update my graph
//             //I don't need to check rank of the node but of the parent of the node
//             if(rank[p1]==rank[p2]){
//                 parent[p1]=p2;
//                 rank[p2]++;
//             }

//             else if(rank[p1]<rank[p2]){
//                 parent[p1]=p2;
//             } 
//             else{
//                 parent[p2]=p1;
//             }

//             return false;

            
//         }
//     }


// }

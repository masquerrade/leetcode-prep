/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
//Gemini BFS solution
class Solution {
    public Node cloneGraph(Node node) {
        // 1. Handle Edge Case: Empty graph
        if (node == null) {
            return null;
        }

        // 2. HashMap to map Original Node -> Cloned Node
        // This serves as our 'visited' tracker and storage for new nodes.
        Map<Node, Node> map = new HashMap<>();

        // 3. Initialize the traversal
        // Create the first clone and map it
        Node newNode = new Node(node.val);
        map.put(node, newNode);

        // Queue for BFS traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // 4. Standard BFS Loop
        while (!queue.isEmpty()) {
            // Get the current node from the original graph
            Node current = queue.poll();

            // Iterate through its neighbors
            for (Node neighbor : current.neighbors) {
                
                // If this neighbor hasn't been cloned yet (not visited)
                if (!map.containsKey(neighbor)) {
                    // Clone it
                    map.put(neighbor, new Node(neighbor.val));
                    // Add to queue for future processing
                    queue.add(neighbor);
                }

                // 5. Link the Clones
                // Get the clone of the current node
                // Add the clone of the neighbor to its neighbors list
                map.get(current).neighbors.add(map.get(neighbor));
            }
        }

        // Return the clone of the starting node
        return newNode;
    }
}


//BFS solution
//Instead of making use of the recursion depth to store the nodes we are using queue

// class Solution {
//     public Node cloneGraph(Node node) {
//         if(node==null){
//             return null;
//         }

//         Map<Node,Node> visited=new HashMap<>();

//         Node copy=new Node(node.val);

//         //Add this to the visited

//         visited.put(node,copy);

//         //I'll make a queue and add the first node to the queue
//         Queue<Node> bfs=new ArrayDeque<>();

//         bfs.offer(node);

//         while(!bfs.isEmpty()){
//             Node currentNode=bfs.poll();
//             //Go through all the neighbours and clone them adding them to the queue to later process their neighbors
//             for(Node neighbor:currentNode.neighbors){
//                 //Before cloning I need to check if it is already present in the visited
//                 if(visited.containsKey(neighbor)){
//                     visited.get(currentNode).neighbors.add(visited.get(neighbor));
//                 }
//                 else{
//                     Node copyNode=new Node(neighbor.val);
//                     visited.get(currentNode).neighbors.add(copyNode);
//                     visited.put(neighbor,copyNode);
//                     //Now I need to add this neighbor to the queue so that later we can go through their neighbors. And this should be inside the else block
//                     bfs.offer(neighbor);
//                 }
                
//             }
//         }
//         return copy;
//     }
// }

// //Gemini dfs solution

// class Solution {
//     public Node cloneGraph(Node node) {
//         if (node == null) return null;
        
//         // Pass the map as a parameter to keep the method stateless (Thread-Safety best practice)
//         return clone(node, new HashMap<>());
//     }
    
//     private Node clone(Node node, Map<Node, Node> visited) {
//         // Base Case: If the node is already cloned, return the reference from the map
//         if (visited.containsKey(node)) {
//             return visited.get(node);
//         }
        
//         // 1. Create the new node
//         Node newNode = new Node(node.val);
        
//         // 2. Add to map IMMEDIATELY to handle cycles
//         // If we wait until neighbors are done, we'll infinite loop on cycles.
//         visited.put(node, newNode);
        
//         // 3. Recursively clone all neighbors
//         for (Node neighbor : node.neighbors) {
//             // The recursive call returns the clone of the neighbor
//             newNode.neighbors.add(clone(neighbor, visited));
//         }
        
//         return newNode;
//     }
// }

// //DFS 
// class Solution {
//     public Node cloneGraph(Node node) {

//         //Adjacency list is not given
//         //So I don't know the total number of nodes, so my visited will be a list
//         //1st I need to traverse the graph and go on making the  new graph
//         //Call dfs with the adjacency list and the visited list
//         if(node==null){
//             return null;
//         }
        
//         //Here I need to track the cyclic node and the corresponding new node so that when the cycle happens I need to do two things - I need to use the previous node to replicate the cycle and this node to the adjacency list to complete the cycle.
//         List<Node> adjList=node.neighbors;
//         int n=adjList.size();

//         // List<Integer> visited=new ArrayList<>(); Wrong
//         Map<Node,Node> orgVsCopy=new HashMap<>();
//         //Now I need to go through the adjacency list completely and keep on copying the  nodes
//         //Whenever I get a new node ,create a new node and add it to its adjacency list

//         Node copyRoot=new Node(node.val);

//         orgVsCopy.put(node,copyRoot);

//         //Each node I need to add individually in the arraylist 
//         for(int i=0;i<n;i++){
//             //First I need to add this node to the copy
//             Node orgNode=adjList.get(i);

//             //What if I already processed the current  node then I don't need to traverse its neighbors     
//             if(!orgVsCopy.containsKey(orgNode)){
//                 Node copyNode=new Node(orgNode.val);
//                 copyRoot.neighbors.add(copyNode);
//                 orgVsCopy.put(orgNode,copyNode);
//                 dfs(orgNode.neighbors,copyNode.neighbors,orgVsCopy);
//             }
//             else{
//                 copyRoot.neighbors.add(orgVsCopy.get(orgNode));
//             }
            
//         }

//         return copyRoot;
        
//     }

//     public void dfs(List<Node> adjList,List<Node> copyList,Map<Node,Node> orgVsCopy){

//         //I need to call dfs on the whole adjacency list of the node
//            //Each node I need to add individually in the arraylist 

//         if(adjList==null  || adjList.size()==0){
//             return ;
//         }

//         int n=adjList.size();
        
//         for(int i=0;i<n;i++){
//              //First I need to add this node to the copy
//             Node orgNode=adjList.get(i);

//             //What if I already processed the current  node then I don't need to traverse its neighbors     
//             if(!orgVsCopy.containsKey(orgNode)){
//                 Node copyNode=new Node(orgNode.val);
//                 copyList.add(copyNode);
//                 orgVsCopy.put(orgNode,copyNode);
//                 dfs(orgNode.neighbors,copyNode.neighbors,orgVsCopy);
//             }
//             else{
//                 copyList.add(orgVsCopy.get(orgNode));
//             }
            
//         }

//     }
// }

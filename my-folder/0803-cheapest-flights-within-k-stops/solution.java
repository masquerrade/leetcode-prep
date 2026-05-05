//BFS approach
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        //adj list
        List<List<int []>> adj=new ArrayList<>();

        //Initialize this list
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        //Fill the adj list
        for(int[] flight:flights){
            int sr=flight[0];
            int dt=flight[1];
            int cost=flight[2];

            adj.get(sr).add(new int[]{dt,cost});
        }
        
        
        //Queue is needed to track the state
        //[node,distance frm src node]
        Queue<int []> bfs=new ArrayDeque<>();

        bfs.offer(new int[]{src,0});

        //Track the minCost for a node
        //It will store min cost for each node for k stops
        int[] minCost=new int[n];
        Arrays.fill(minCost,Integer.MAX_VALUE);
        minCost[src]=0;

        //Track levels for stops
        int stopLevel=0;

        //Continue till the level is k
        while(!bfs.isEmpty() && stopLevel<=k){
            //No of elm in this level
            int levelSize=bfs.size();

            for(int i=0;i<levelSize;i++){
                 int[] currNodeState=bfs.poll();
                 int node=currNodeState[0];
                 int cost=currNodeState[1];

                 //Explore all the neighbours
                 for(int[] neighbour:adj.get(node)){
                    int nextNode=neighbour[0];
                    int nextCost=neighbour[1];
                    int newTotalCost=cost+nextCost;
                    
                    if(newTotalCost<minCost[nextNode]){
                        minCost[nextNode]=newTotalCost;
                        bfs.offer(new int[]{nextNode,newTotalCost});
                    }
                 }
            }
            stopLevel++;
        }

        //At the end return 
        if(minCost[dst]!=Integer.MAX_VALUE){
            return minCost[dst];
        }
        return -1;        
                
    }
}


// //Dijkstra Approach
// class Solution {
//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

//         //Making adj list to get the neighbours
//         //dst,cost

//         List<List<int []>> adj=new ArrayList<>();
//         //Initialize each index so that we can get and add to the list

//         for(int i=0;i<n;i++){
//             adj.add(i,new ArrayList<>());
//         }


//         //I missed to populate the adjacency list itself after initializing
//         for(int[] flight:flights){
//             int sr=flight[0];
//             int dt=flight[1];
//             int ct=flight[2];
//             adj.get(sr).add(new int[]{dt,ct});
//         }

//         //Create PQ to track the nodes
//         //[cost,node,stopsToReachHere]
//         //This is not the correct declaration of the priority queue
//         //PriorityQueue<Integer> pq=new PriorityQueue<>(a->Comparator.comparingInt(a[0]));
        
//         PriorityQueue<int []> pq=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));

//         //I need to track minStops to reach this node

//         int[] minStops=new int[n];
//         //Initialize to max value
//         Arrays.fill(minStops,Integer.MAX_VALUE);

//         //Add 1st entry for the source node
//         pq.offer(new int[]{0,src,0});

//         while(!pq.isEmpty()){
//             //Get the current node 
//             int [] currStop=pq.poll();
//             //Total cost of the path till now
//             int cost=currStop[0];
//             int stop=currStop[1];
//             //No of stops taken from source till now
//             int noStop=currStop[2];

//             //If the current node == destination then return the node
//             //As it falls within the stop limit and is the stop with least cost

//             if(stop==dst){
//                 return cost;
//             }

//             //If I've exhasted the noOfStops I don't need to look at it's neighbours
//             //Till now I've exhausted all the stops but if still my destination is diretly the neighbour I can still go there
//             //I need to lock the minStop here because the node I've got now just after popping is the node which can be reached with least cost 

//             //This is the least cost to reach this stop with these min no of stops. I'll explore it's neighbours only if the no of stops less than previously recorded
//             if(minStops[stop]<=noStop || noStop==k+1){
//                 continue;
//             }
//             else{
//                 minStops[stop]=noStop;
//             }
            

//             //If stops are still left I need to explore it's neighbours
//             for(int [] ngb:adj.get(stop)){
//                 int nextStop=ngb[0];
//                 int newCost=ngb[1]+cost;
//                 int newStopCount= noStop+1;
//                 pq.offer(new int[] {newCost,nextStop,newStopCount});
//                 //When I'm here I still have stop left and I don't care whether the cost of this node is smaller than the prev node
//                 //What if I can already reach till the current node in less no of stops that means I've encountered till this node before and that node obviously had had less cost and if the noOfstops to reach the current node was less then there is no need to add this node to the queue
//                 //I cannot lock the minStop when I'm just exploring the neighbour .As there may come a stop with stops within the limit but with less cost
//                 // if(minStops[nextStop]>newStopCount){
//                 //     pq.offer(new int[] {newCost,nextStop,newStopCount});
//                 //     minStops[nextStop]=newStopCount;
//                 // }
//             }
            
//         }

//         //Till now I've explored all the nodes and their neighbours. If till now also destination is not found then I need to return-1
//         return -1;

        
//     }
// }


// //Bellman Ford Approach
// class Solution {
//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//         //Test test testing 
//         //In Djikstra we're just tracking the shortest distance found till now but not the number of levels tracked
//         //Initialize the cost array
//         int [] cost=new int[n];
//         int [] costCopy=new int[n];

//         Arrays.fill(cost,Integer.MAX_VALUE);

//         cost[src]=0;

//         //I need to scan all the edges k+1 times. k stops means k edges and k+1 cities
//         //Outer loop should run k+1 times
//         for(int i=0;i<=k;i++){

//             //Inner loop to update the cost array
//             //This loop should only see the cost updated by the previous iteration of the outer loop and not of the inner loop 
//             costCopy=Arrays.copyOf(cost,n);
//             for(int[] flight:flights){

//                 int from=flight[0];
//                 int to=flight[1];
//                 int price=flight[2];

//                 //Here I need a check to prevent overflow
//                 if(costCopy[from]==Integer.MAX_VALUE){
//                     continue;
//                 }

//                 int newCost=costCopy[from]+price;
//                 //Here we cannot use costCopy for comparison since it is getting refreshed in every iteration.We need to use cost array in comparison to track the correct least price in that whole iteration
//                 // if(newCost<costCopy[to]){
//                 if(newCost<cost[to])
//                 {
//                     cost[to]=newCost;
//                 }
//             }

//         }

//         if(cost[dst]==Integer.MAX_VALUE){
//             return -1;
//         }

//         return cost[dst];
        
//     }
// }

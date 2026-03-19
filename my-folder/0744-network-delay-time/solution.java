//Gathering the requirement

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        //If not weighted we can look at bfs
        //I'm tracking the shortest distance in the directed graph so I will go bfs

        //Adjacency List for graph

        List<int[]>[] adj=new ArrayList[n+1];

        //Fill the adj list
        //This way we cannot fill array because of same reference problem
        //Arrays.fill(adj,new ArrayList()<>);
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();   
        }

        for(int [] time:times){
            //Here I just need to add . No need to overwrite
            //adj[time[0]]=new ArrayList<>(Arrays.asList(time[0],time[1]));
            adj[time[0]].add(new int[]{time[1],time[2]});
            //What if some nodes are there with no neighbours then those will not be in the list
        }

        //Initialise the dist array to max value

        int[] dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        //Add kth node to the PQ
        //Queue<int[]> bfs=new PriorityQueue((a,b)->Interger.compare(a[0],b[0]));
        //Don't miss <>
        Queue<int[]> bfs=new PriorityQueue<>((a,b)->a[0]-b[0]);
        bfs.offer(new int[]{0,k });
        //I need to update the shortest distance for the kth node
        dist[k]=0;


        //Top node is the closest node in the queue

        //Check the time taken to reach the neighbour node from current path.If for any of the neighbour a shorter time is found then I'll add it to the queue as we need to reprocess all it's neighbours

        //What if one node comes in the neighbours list multiple times ,if it's total time is less then only will add to the queue

        //When will the loop end ,when there are no more neighbours to add no elements remained in the queue

        while(!bfs.isEmpty()){
            int[] currNode=bfs.poll();
            int currTime=currNode[0];
            int currId=currNode[1];

            //Before exploring the neighour we need to check if this node has already been updated to a lesser time by the time it was   popped
            //Here = is not needed as it will skip processing the node in the queuq
            if(currTime>dist[currId]){
                continue;
            }
            
            //Exploring all the neighbours
            //This is very common mistake adj is array of list so adj[currId] will be one list of array, so when iterating over the list its element will be array
            for(int [] neighbour:adj[currId]){
                int neighbourId=neighbour[0];
                int newTime=currTime+neighbour[1];
                if(newTime<dist[neighbourId]){
                    dist[neighbourId]=newTime;
                    bfs.offer(new int[]{newTime,neighbourId});
                }
            }

        }

        //When this loop ends I will have all the minimum times for each node
        int max=dist[1];
        //Here we need to skip the 0th index so we need to use the normal loop
        //for(int d:dist){
        for(int i=1;i<=n;i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            else if(dist[i]>max){
                max=dist[i];
            }
        }

        return max;

        //return (Arrays.stream(dist).anyMatch(x->x==Integer.MAX_VALUE))?-1:Arrays.stream(dist).max().orElse(-1);      

    }
}


// class Solution {
//     public int networkDelayTime(int[][] times, int n, int k) {

//         //First step is I need to create an adjacency list
//         //Adjacency list will contain source node target node and the weight
//         //Then using this adjacency list I will form a queue and this queue will  not be on the basis of first come first serve basis 
//         //But it will be a priority queue which will be on the weight basis
//         //Each node has a weight attached to it and weight defines the distance from the adjacent node

//         //I will have to maintain a cost array for each node, which will store the minimum cost for this node from the source , this is the time taken for the signal to reach this node 
        

//         //The max of all these values is the time taken to reach the farthest node\


//         //Here I don't maintain the visited array as I keep on visiting the same node again and again till the shortest path is found

//         //Creating the adjacency matrix/ list

//         //Why I can create an array as adjacency list because I don't know how many total elements will be there in the graph

//         //I need to create adjacency list as list because we need array of list of interger arrays or map of list of integer arrays
//         //Or map of list of integer arrays

//         //int [][] adj=new int [n+1];

//         List<int []> [] adj=new ArrayList[n+1];

//         //I need to initialise each element 
//         for(int i=1;i<=n;i++){
//             adj[i]=new ArrayList<>();
//         }

//         for(int [] tm:times){
//             int u=tm[0];
//             int v=tm[1];
//             int w=tm[2];
//             //Adding a single dimensional array
//             //This won't work
//             // if(adj[u]==null){
//             //     adj[u]=new ArrayList<>();
//             // }
//             adj[u].add(new int[]{v,w});
//         }

//         //Now my adjacency list is ready, I need to add the source element in the queue
//         //Pop it and iterate through the adjacent nodes

//         //I need a distance array which will store the smallest distance of any node to the source
        
//         //I need min heap
//         //What min heap will do is keep the nodes with shortest distance on the top
//         //So I will always have node with shortest ditance till now
//         //If a shorter distance is found add that node in the queue and rprocess its adjacent nodes

//         int dis[]=new int[n+1];

        

//         for(int i=0;i<=n;i++){
//             dis[i]=Integer.MAX_VALUE;
//         }

//         dis[k]=0;

//         // Queue<int[]> djk=new PriorityQueue<>(a[1]-b[1]);//This is wrong declaration .From where this a and b is coming so I need to use lambda expression
//         Queue<int[]> djk=new PriorityQueue<>((a,b)->(a[1]-b[1]));//In the queue I will only have the destination node and its distance from the adjacent nodes

//         //In priority queue we are adding the current node and total distance of the current node to the origin

//         djk.offer(new int[]{k,0});

//         while(!djk.isEmpty()){
//             int []ent=djk.poll();

//             int u=ent[0];
//             int t=ent[1];
//             //I pop the current node and check if it is a fresh entry or stale entry before checking its neighbours
//             if(t>dis[u]){
//                 continue;
//             }

//             //If it is a fresh entry check it's neighbours
//             //There may be a condition when current node may not have any adjacent nodes
//             //Since I am using array no need to check the condition that if the current node doesn't exist in the adj list
//             //List is an iterable so I can directly iterate

//             for(int[] ls:adj[u]){
//                 //Now I'm inside the adj list
//                 int v=ls[0];
//                 int w=ls[1];

//                 //This is total w of v
//                 int tw=w+t;

//                 if(tw<dis[v]){
//                     dis[v]=tw;
//                     djk.offer(new int[]{v,tw});
//                 }
//             }


//         }

//         //Now my dis array will have all the min times .Now I want to fix max of this times
//         int maxT=0;


//         for(int i=1;i<=n;i++){
//             //I missed one condition when any node is not reachble from the source the I need to return -1
//             if(dis[i]==Integer.MAX_VALUE){
//                 return -1;
//             }
//             if(dis[i]>maxT){
//                 maxT=dis[i];
//             }

//         }

//         return maxT;        

        
//     }
// }
